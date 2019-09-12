package bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dao.ProdSaleDAO;

import dto.ProdSale;
import dto.*;

public class ProdSaleBUS {
	private ProdSaleDAO dao = new ProdSaleDAO();
	public ArrayList<ProdSale> readAll(){
		return dao.readAll();
	}
	public ArrayList<ProdSale> read(){
		return dao.readAll();
	}
	public ProdSale searchByID(String sale_id,String prod_id) {
		ArrayList<ProdSale> arr = dao.readAll();
		for(ProdSale p : arr) {
			if(p.getSale_id().equals(sale_id) && p.getProd_id().equals(prod_id)) return p;
		}
		return null;
	}
	public ArrayList<ProdSale> searchByProductID(String prod_id) {
		ArrayList<ProdSale> arr = dao.readAll();
		ArrayList<ProdSale> temp = new ArrayList<ProdSale>();
		for(ProdSale p : arr) {
			if(p.getProd_id().equals(prod_id)) temp.add(p);
		}
		return temp;
	}
	public ArrayList<ProdSale> searchBySaleID(String sale_id) {
		ArrayList<ProdSale> arr = dao.readAll();
		ArrayList<ProdSale> temp = new ArrayList<ProdSale>();
		for(ProdSale p : arr) {
			if(p.getSale_id().equals(sale_id)) temp.add(p);
		}
		return temp;
	}
	
	public ArrayList<ProdSale> search(ArrayList<ProdSale> arr,SaleBUS saleBus, ProductBUS prodBus, String s, String choice){
		ArrayList<ProdSale> temp = new ArrayList<ProdSale>();
		s = s.toLowerCase();
		for( ProdSale p : arr) {
			if(choice.equals("Mã KM")) {
				if(p.getSale_id().toLowerCase().contains(s)) temp.add(p);
			}else if(choice.equals("Mã SP")){
				if(p.getProd_id().toLowerCase().contains(s)) temp.add(p);
			}else if(choice.equals("Tên KM")) {
				if(StringExe.removeAccent(saleBus.searchByID(p.getSale_id()).getSale_name()).contains(s)) temp.add(p);
			}else {
				if(StringExe.removeAccent(prodBus.searchByID(p.getProd_id()).getProd_name()).contains(s)) temp.add(p);
			}
		}
		return temp;
		
	}
	public ArrayList<ProdSale> sort(ArrayList<ProdSale> arr,SaleBUS saleBus, ProductBUS prodBus, String type, String order) {
		switch(type) {
		case "Mã SP" : Collections.sort(arr, Comp.PROD_ID); break;
		case "Tên SP": Collections.sort(arr, new Comparator<ProdSale>() {
			public int compare(ProdSale x, ProdSale y) {
				return StringExe.removeAccent(prodBus.searchByID(prodBus.readAll(), x.getProd_id()).getProd_name()).compareTo(
						StringExe.removeAccent(prodBus.searchByID(prodBus.readAll(), y.getProd_id()).getProd_name()));
			}
		}); break;
		case "Tên KM" : Collections.sort(arr, new Comparator<ProdSale>() {
			public int compare(ProdSale x, ProdSale y) {
				return saleBus.searchByID(saleBus.readAll(), x.getSale_id()).getSale_name().compareTo(saleBus.searchByID(saleBus.readAll(), y.getSale_id()).getSale_name());
			}
		}); break;
		default: Collections.sort(arr, Comp.SALE_ID); break;
		}
		if(order.equals("Giảm dần")) Collections.reverse(arr);
		return arr;
	}
	private static class Comp{
		public final static Comparator<ProdSale> SALE_ID = (ProdSale x, ProdSale y) -> x.getSale_id().compareTo(y.getSale_id());
		public final static Comparator<ProdSale> PROD_ID = (ProdSale x, ProdSale y) -> x.getProd_id().compareTo(y.getProd_id());
	}
	public int add(ProdSale p) {
		return dao.add(p);
	}
	public int delete(String sale_id) {
		return dao.delete(sale_id);
	}
	public int delete(String sale_id,String prod_id) {
		return dao.delete(sale_id,prod_id);
	}
	public int disable(String sale_id,String prod_id) {
		return dao.disable(sale_id,prod_id);
	}
	public int active(String sale_id,String prod_id) {
		return dao.active(sale_id,prod_id);
	}
	public int update(ProdSale p) {
		return dao.update(p);
	}
}
