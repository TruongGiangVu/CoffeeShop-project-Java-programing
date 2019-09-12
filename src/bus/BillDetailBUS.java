package bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import bus.ImportDetailBUS.Comp;
import dao.BillDetailDAO;
import dto.BillDetail;
import dto.ImportDetail;
import dto.Ingredient;
import dto.Product;
import dto.Sale;

public class BillDetailBUS {
	//private SaleBUS saleBus = new SaleBUS();
	private ProductBUS prodBus = new ProductBUS();
	private BillDetailDAO dao = new BillDetailDAO();
	public ArrayList<BillDetail> readAll(){
		return dao.readAll();
	}
	public BillDetail searchByID(String bill_id,String prod_id) {
		ArrayList<BillDetail> arr = dao.readAll();
		for(BillDetail p : arr) {
			if(p.getBill_id().equals(bill_id) && p.getProd_id().equals(prod_id)) return p;
		}
		return null;
	}
	public ArrayList<BillDetail> searchByProductID(String prod_id) {
		ArrayList<BillDetail> arr = dao.readAll();
		ArrayList<BillDetail> temp = new ArrayList<BillDetail>();
		for(BillDetail p : arr) {
			if(p.getProd_id().equals(prod_id)) temp.add(p);
		}
		return temp;
	}
	public ArrayList<BillDetail> searchByBillID(String bill_id) {
		ArrayList<BillDetail> arr = dao.readAll();
		ArrayList<BillDetail> temp = new ArrayList<BillDetail>();
		for(BillDetail p : arr) {
			if(p.getBill_id().equals(bill_id)) temp.add(p);
		}
		return temp;
	}
	public ArrayList<BillDetail> search(ArrayList<BillDetail> arr, String s, String choice){
		ArrayList<BillDetail> temp = new ArrayList<BillDetail>();
		s = StringExe.removeAccent(s);
		
		for(BillDetail p : arr) {
				switch(choice) {
				case "Tên SP": {
					String x = StringExe.removeAccent(prodBus.searchByID(p.getProd_id()).getProd_name());
					if(x.contains(s)) temp.add(p); break;
				}
				default: if(StringExe.removeAccent(p.getProd_id()).contains(s)) temp.add(p); break;
				}
			
		}
		return temp;
	}
	public ArrayList<BillDetail> sort(ArrayList<BillDetail> arr, String type, String order){
		switch(type) {
		case "Tên SP": Collections.sort(arr, new Comparator<BillDetail>() {
			@Override
			public int compare(BillDetail x, BillDetail y) {
				String a = prodBus.searchByID(x.getProd_id()).getProd_name();
				String b = prodBus.searchByID(y.getProd_id()).getProd_name();
				return StringExe.removeAccent(a).compareTo(StringExe.removeAccent(b));
			}
		});break;
		case "Đơn giá": Collections.sort(arr, Comp.UNIT_PRICE); break;
		case "Giá KM": Collections.sort(arr, Comp.SALE_UNIT_PRICE); break;
		case "Số lượng": Collections.sort(arr, Comp.AMOUNT);
		default: Collections.sort(arr, Comp.ID); break;
		}
		if(order.equals("Giảm dần")) Collections.reverse(arr);
		return arr;
	}
	public static class Comp{
		public final static Comparator<BillDetail> ID = (BillDetail x, BillDetail y) -> x.getProd_id().compareTo(y.getProd_id());
		public final static Comparator<BillDetail> UNIT_PRICE = (BillDetail x, BillDetail y) -> Double.compare(x.getUnit_price(), y.getUnit_price());
		public final static Comparator<BillDetail> SALE_UNIT_PRICE = (BillDetail x, BillDetail y) -> Double.compare(x.getSale_unit_price(), y.getSale_unit_price());
		public final static Comparator<BillDetail> AMOUNT = (BillDetail x, BillDetail y) -> Double.compare(x.getAmount(), y.getAmount());
	}
	public double getTotalPrice(String billID) {
		ArrayList<BillDetail> arr = this.searchByBillID(billID);
		double s = 0;
		for(BillDetail p : arr) {
			s = s + p.getAmount()*p.getSale_unit_price();
		}
		return s;
	}
	public double getTotalPrice(ArrayList<BillDetail> arr) {
		double s = 0;
		for(BillDetail p : arr) {
			s = s + p.getAmount()*p.getSale_unit_price();
		}
		return s;
	}
	public int getPurchaseAmount(String billID) {
		ArrayList<BillDetail> arr = this.searchByBillID(billID);
		int s = 0;
		for(BillDetail p : arr) {
			s = s + p.getAmount();
		}
		return s;
	}
	/*
	public void setSaleUnitPrice(String billID, String prodID) {
		BillDetail p = this.searchByID(billID, prodID);
		Sale temp = saleBus.searchByID(p.getSale_id());
		if(temp != null) {
			if(temp.getMeasure().equals("%")) {
				p.setSale_unit_price(p.getUnit_price() * temp.getNumber() / 100);
			}else p.setSale_unit_price(p.getUnit_price() - temp.getNumber());
		}
		this.update(p);
	}
	*/
	public boolean updateStorage(ArrayList<BillDetail> arr) {
		boolean check = true;
		for(BillDetail p : arr) {
			Product temp = prodBus.searchByID(p.getProd_id());
			if(p.getAmount() > temp.getAmount()) {
				check = false;
				break;
			}
		}
		if(check) {
			for(BillDetail p : arr) {
					Product temp = prodBus.searchByID(p.getProd_id());
					temp.setAmount(temp.getAmount() - p.getAmount());
					prodBus.update(temp);
			}
		}
		return check;
		
	}
	public int add(BillDetail p) {
		return dao.add(p);
	}
	public int delete(String prod_id) {
		return dao.delete(prod_id);
	}
	public int delete(String prod_id,String ing_id) {
		return dao.delete(prod_id,ing_id);
	}
	public int update(BillDetail p) {
		return dao.update(p);
	}
}
