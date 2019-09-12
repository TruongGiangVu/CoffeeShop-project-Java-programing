package bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dao.BillDAO;
import dto.Bill;
import dto.BillDetail;
import dto.Sale;


public class BillBUS {
	private BillDAO dao = new BillDAO();
	private CustomerBUS cusBus = new CustomerBUS();
	private EmployerBUS empBus = new EmployerBUS();
	private BillDetailBUS billdetailBus = new BillDetailBUS();
	private SaleBUS saleBus = new SaleBUS();
	//private SaleBUS saleBus = new SaleBUS();
	public ArrayList<Bill> readAll(){
		return dao.readAll();
	}
	public ArrayList<Bill> read(){
		return dao.read();
	}
	public Bill searchByID(String id) {
		ArrayList<Bill> arr = dao.readAll();
		for(Bill p : arr) {
			if(p.getBill_id().equals(id)) return p;
		}
		return null;
	}
	public ArrayList<Bill> searchByEmpID(String emp_id) {
		ArrayList<Bill> arr = dao.readAll();
		ArrayList<Bill> temp = new ArrayList<Bill>();
		for(Bill p : arr) {
			if(!(!p.getEmp_id().equals(emp_id) && p.getStatus()==2)) temp.add(p);
		}
		return temp;
	}
	public ArrayList<Bill> searchByCusID(String cus_id) {
		ArrayList<Bill> arr = dao.readAll();
		ArrayList<Bill> temp = new ArrayList<Bill>();
		for(Bill p : arr) {
			if(p.getCus_id().equals(cus_id) && p.getStatus()==1) temp.add(p);
		}
		return temp;
	}
	public ArrayList<Bill> search(ArrayList<Bill> arr, String s, String choice, String from, String to){
		ArrayList<Bill> temp = new ArrayList<Bill>();
		s = StringExe.removeAccent(s);
		
		for(Bill p : arr) {
			boolean start = from.equals("null")?true:(DateExe.compares(p.getDateStr(), from) >= 0);
			boolean end = to.equals("null")?true:(DateExe.compares(p.getDateStr(), to) <= 0);
			if(start && end) {
				switch(choice) {
				case "KH": if(StringExe.removeAccent(cusBus.searchByID(p.getCus_id()).getCus_name()).contains(s)) temp.add(p); break;
				case "NV": if(StringExe.removeAccent(empBus.searchByID(p.getEmp_id()).getEmp_name()).contains(s)) temp.add(p); break;
				default: if(StringExe.removeAccent(p.getBill_id()).contains(s)) temp.add(p); break;
				}
			}
		}
		return temp;
	}
	public ArrayList<Bill> sort(ArrayList<Bill> arr, String type, String order){
		switch(type) {
		case "Mã NV": Collections.sort(arr, new Comparator<Bill>() {
			@Override
			public int compare(Bill x, Bill y) {
				return StringExe.removeAccent(empBus.searchByID(x.getEmp_id()).getEmp_name()).compareTo(StringExe.removeAccent(empBus.searchByID(y.getEmp_id()).getEmp_name()));
			}
		}); break;
		case "Mã KH": Collections.sort(arr, new Comparator<Bill>() {
			@Override
			public int compare(Bill x, Bill y) {
				return StringExe.removeAccent(cusBus.searchByID(x.getCus_id()).getCus_name()).compareTo(StringExe.removeAccent(cusBus.searchByID(y.getCus_id()).getCus_name()));
			}
		}); break;
		case "Ngày lập": Collections.sort(arr, Comp.DATE); break;
		case "Thành tiền": Collections.sort(arr, new Comparator<Bill>() {
			@Override
			public int compare(Bill x, Bill y) {
				return Double.compare(billdetailBus.getTotalPrice(x.getBill_id()) , billdetailBus.getTotalPrice(y.getBill_id()));
			}
		}); 
		case "Khuyến mãi": Collections.sort(arr, new Comparator<Bill>() {
			@Override
			public int compare(Bill x, Bill y) {
				return Double.compare(x.getSale_total() , y.getSale_total());
			}
		});
		break;
		default: Collections.sort(arr, Comp.ID); break;
		}
		if(order.equals("Giảm dần")) Collections.reverse(arr);
		return arr;
	}
	public static class Comp{
		public final static Comparator<Bill> ID = (Bill x, Bill y) -> x.getBill_id().compareTo(y.getBill_id());
		public final static Comparator<Bill> DATE = (Bill x, Bill y) -> DateExe.compares(x.getDateStr(), y.getDateStr());
	}
	
	public double getTotalSalePrice(String billID) {
		Bill p = this.searchByID(billID);
		Sale temp = saleBus.searchByID(p.getSale_id());
		double total = billdetailBus.getTotalPrice(billID);
		if(temp != null) {
			if(temp.getMeasure().equals("%")) {
				return total * temp.getNumber() / 100;
			}else return total - temp.getNumber();
		}
		else return total;
	}
	public int getPurchaseAmount(String billD, int month, int year) {
		int s = 0;
		if(month != 0) {
			Bill p = this.searchByID(billD);
			if(p.getDate().getMonthValue() == month && p.getDate().getYear() == year) {
				s += billdetailBus.getPurchaseAmount(p.getBill_id());
			}
		}else {
			Bill p = this.searchByID(billD);
				s += billdetailBus.getPurchaseAmount(p.getBill_id());
		}
		return s;
	}
	public int getPurchaseValue(String billID, int month, int year) {
		int s = 0;
		if(month != 0) {
			Bill p = this.searchByID(billID);
			if(p.getDate().getMonthValue() == month && p.getDate().getYear() == year) {
				s += this.getTotalSalePrice(billID);
			}
		}else {
			Bill p = this.searchByID(billID);
				s += this.getTotalSalePrice(billID);
		}
		return s;
	}
	public int add(Bill p) {
		return dao.add(p);
	}
	public int delete(String id) {
		return dao.delete(id);
	}
	public int lock(String id) {
		return dao.lock(id);
	}
	public int active(String id) {
		return dao.active(id);
	}
	public int disable(String id) {
		return dao.disable(id);
	}
	public int update(Bill p) {
		return dao.update(p);
	}
}
