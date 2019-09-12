package bus;

import java.util.ArrayList;
import java.util.*;
import dao.SaleDAO;
import dto.ProdSale;
import dto.Sale;

public class SaleBUS {
	private SaleDAO dao = new SaleDAO();
	private ProdSaleBUS prodsaleBus = new ProdSaleBUS();
	
	public ArrayList<Sale> readAll(){
		return dao.readAll();
	}
	public ArrayList<Sale> read(){
		return dao.read();
	}
	public Sale searchByID(String id) {
		ArrayList<Sale> arr = dao.readAll();
		for(Sale p : arr) {
			if(p.getSale_id().equals(id)) return p;
		}
		return null;
	}
	public Sale searchByID(ArrayList<Sale> arr, String id) {
		for(Sale p : arr) {
			if(p.getSale_id().equals(id)) return p;
		}
		return null;
	}
	public ArrayList<Sale> getSaleForProdbyProdSale(String prod_id) {
		ArrayList<Sale> arr = new ArrayList<Sale>();
		ArrayList<ProdSale> temp = prodsaleBus.searchByProductID(prod_id);
		for(ProdSale p : temp) {
			Sale sale = searchByID(p.getSale_id());
			arr.add(sale);
		}
		return arr;
	}
	public ArrayList<Sale> getSaleForBillbyConditions(double total, double amount){
		ArrayList<Sale> temp = this.getSaleForBill(this.read());
		ArrayList<Sale> arr = new ArrayList<Sale>();
		for(Sale p : temp) {
			if(p.getThreshold_unit().equals("món")) {
				if(Double.compare(p.getThreshold(), amount) <= 0) arr.add(p);
			}else {
				if(Double.compare(p.getThreshold(), total) <= 0) arr.add(p);
			}
		}
		return arr;
	}
	public ArrayList<Sale> sort(ArrayList<Sale> arr, String choice, String order, String type){
		ArrayList<Sale> a = new ArrayList<Sale>();
		ArrayList<Sale> b = new ArrayList<Sale>();
		this.filter(arr,a,b);
		switch(type) {
		case "SP trước": 
			this.sortOrder(a, choice, order);
			this.sortOrder(b, choice, order);
			return this.addArray(a, b);
		case "HĐ trước":
			this.sortOrder(a, choice, order);
			this.sortOrder(b, choice, order);
			return this.addArray(b, a);
		case "Chỉ SP":
			this.sortOrder(a, choice, order);
			return a;
		case "Chỉ HĐ":
			this.sortOrder(b, choice, order);
			return b;
		default: 
			this.sortOrder(a, choice, order);
			this.sortOrder(b, choice, order);
			return this.addArray(a, b);
		}
	}
	public ArrayList<Sale> getSaleForProd(ArrayList<Sale> arr){
		ArrayList<Sale> temp = new ArrayList<Sale>();
		for(Sale p : arr) {
			if(p.getSale_id().charAt(0) == 'A') {
				temp.add(p);
			}
		}
		return temp;
	}
	public ArrayList<Sale> getSaleForBill(ArrayList<Sale> arr){
		ArrayList<Sale> temp = new ArrayList<Sale>();
		for(Sale p : arr) {
			if(p.getSale_id().charAt(0) == 'B') {
				temp.add(p);
			}
		}
		return temp;
	}
	private ArrayList<Sale> addArray(ArrayList<Sale> head, ArrayList<Sale> tail) {
		for(Sale p : tail) {
			head.add(p);
		}
		return head;
	}
	private void filter(ArrayList<Sale> arr,ArrayList<Sale> prod,ArrayList<Sale> bill){
		for(Sale p : arr) {
			if(p.getSale_id().charAt(0) == 'A') {
				prod.add(p);
			}else {
				bill.add(p);
			}
		}
	}
	public ArrayList<Sale> sortOrder(ArrayList<Sale> arr, String choice, String order){
		switch(choice) {
		case "Mã KM": Collections.sort(arr, Comp.ID); break;
		case "Tên KM": Collections.sort(arr, Comp.NAME); break;
		case "Ngày bắt đầu": Collections.sort(arr, Comp.START); break;
		case "Ngày kết thúc":Collections.sort(arr, Comp.END);  break;
		default: break;
		}
		if(order.equals("Giảm dần")) Collections.reverse(arr);
		return arr;
	}
	public static class Comp {
		public final static Comparator<Sale> ID = (Sale x, Sale y) -> x.getSale_id().compareTo(y.getSale_id());
		public final static Comparator<Sale> NAME = (Sale x, Sale y) -> StringExe.removeAccent(x.getSale_name()).compareTo(StringExe.removeAccent(y.getSale_name()));
		public final static Comparator<Sale> START = (Sale x, Sale y) -> DateExe.compares(x.getStart_date(), y.getStart_date());
		public final static Comparator<Sale> END = (Sale x, Sale y) -> DateExe.compares(x.getEnd_date(), y.getEnd_date());
	}
	public ArrayList<Sale> search(ArrayList<Sale> arr, String s, String type, String from, String to){
		ArrayList<Sale> temp = new ArrayList<Sale>();
		s = StringExe.removeAccent(s.toLowerCase());
		for( Sale p : arr) {
			boolean start = from.equals("null")?true:(DateExe.compares(p.getStart_dateStr(), from) >= 0);
			boolean end = to.equals("null")?true:(DateExe.compares(p.getEnd_dateStr(), to) <= 0);
			if(type.equals("Mã")) {
				if(p.getSale_id().toLowerCase().contains(s) && start && end ) 
					temp.add(p);
			}else {
				if(StringExe.removeAccent(p.getSale_name()).contains(s) && start && end) 
					temp.add(p);
			}
		}
		return temp;
		
	}
	public int add(Sale p) {
		return dao.add(p);
	}
	public int delete(String id) {
		return dao.delete(id);
	}
	public int disable(String id) {
		return dao.disable(id);
	}
	public int active(String id) {
		return dao.active(id);
	}
	public int update(Sale p) {
		return dao.update(p);
	}
}
