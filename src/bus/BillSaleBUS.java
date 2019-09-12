package bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import dao.BillSaleDAO;
import dto.BillSale;
import dto.ProdSale;

public class BillSaleBUS {
	private BillSaleDAO dao = new BillSaleDAO();
	public BillBUS billBus = new BillBUS();
	public ArrayList<BillSale> readAll(){
		return dao.readAll();
	}
	public ArrayList<BillSale> read(){
		return dao.read();
	}
	public BillSale searchByID(String sale_id,String bill_id) {
		ArrayList<BillSale> arr = dao.readAll();
		for(BillSale p : arr) {
			if(p.getSale_id().equals(sale_id) && p.getBill_id().equals(bill_id)) return p;
		}
		return null;
	}
	public BillSale searchByBillID(String bill_id) {
		ArrayList<BillSale> arr = dao.readAll();
		for(BillSale p : arr) {
			if(p.getBill_id().equals(bill_id)) return p;
		}
		return null;
	}
	public BillSale searchBySaleID(String sale_id) {
		ArrayList<BillSale> arr = dao.readAll();
		for(BillSale p : arr) {
			if(p.getSale_id().equals(sale_id)) return p;
		}
		return null;
	}
	public ArrayList<BillSale> search(ArrayList<BillSale> arr, String s, String choice, String from, String to){
		ArrayList<BillSale> temp = new ArrayList<BillSale>();
		s = StringExe.removeAccent(s);
		for( BillSale p : arr) {
			boolean start = from.equals("null")?true:(DateExe.compares(billBus.searchByID(p.getBill_id()).getDateStr(), from) >= 0);
			boolean end = to.equals("null")?true:(DateExe.compares(billBus.searchByID(p.getBill_id()).getDateStr(), to) <= 0);
			if(start && end) {
				if(choice.equals("Mã KM")) {
					if(p.getSale_id().toLowerCase().contains(s)) temp.add(p);
				}else {
					if(p.getBill_id().toLowerCase().contains(s)) temp.add(p);
				}
			}
		}
		return temp;
		
	}
	public ArrayList<BillSale> sort(ArrayList<BillSale> arr, String type, String order) {
		switch(type) {
		case "Mã HĐ" : Collections.sort(arr, Comp.BILL_ID); break;
		default: Collections.sort(arr, Comp.SALE_ID); break;
		}
		if(order.equals("Giảm dần")) Collections.reverse(arr);
		return arr;
	}
	private static class Comp{
		public final static Comparator<BillSale> SALE_ID = (BillSale x, BillSale y) -> x.getSale_id().compareTo(y.getSale_id());
		public final static Comparator<BillSale> BILL_ID = (BillSale x, BillSale y) -> x.getBill_id().compareTo(y.getBill_id());
	}
	public int add(BillSale p) {
		return dao.add(p);
	}
	public int delete(String sale_id) {
		return dao.delete(sale_id);
	}
	public int delete(String sale_id,String bill_id) {
		return dao.delete(sale_id,bill_id);
	}
	public int disable(String sale_id,String bill_id) {
		return dao.disable(sale_id,bill_id);
	}
	public int active(String sale_id,String bill_id) {
		return dao.active(sale_id,bill_id);
	}
	public int update(BillSale p) {
		return dao.update(p);
	}
}
