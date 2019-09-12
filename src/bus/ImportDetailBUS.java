package bus;

import dao.ImportDetailDAO;
import dto.Import;
import dto.ImportDetail;

import java.util.*;
import dto.*;
public class ImportDetailBUS {
	private ImportDetailDAO dao = new ImportDetailDAO();
	private IngredientBUS ingBus = new IngredientBUS();
	private ProductBUS prodBus = new ProductBUS();
	public ArrayList<ImportDetail> readAll(){
		return dao.readAll();
	}
	public ImportDetail searchByID(String imp_id,String prod_id) {
		ArrayList<ImportDetail> arr = dao.readAll();
		for(ImportDetail p : arr) {
			if(p.getImp_id().equals(imp_id) && p.getProd_id().equals(prod_id)) return p;
		}
		return null;
	}
	public ArrayList<ImportDetail> searchByProductID(String prod_id) {
		ArrayList<ImportDetail> arr = dao.readAll();
		ArrayList<ImportDetail> temp = new ArrayList<ImportDetail>();
		for(ImportDetail p : arr) {
			if(p.getProd_id().equals(prod_id)) temp.add(p);
		}
		return temp;
	}
	public ArrayList<ImportDetail> searchByImportID(String imp_id) {
		ArrayList<ImportDetail> arr = dao.readAll();
		ArrayList<ImportDetail> temp = new ArrayList<ImportDetail>();
		for(ImportDetail p : arr) {
			if(p.getImp_id().equals(imp_id)) temp.add(p);
		}
		return temp;
	}
	public ArrayList<ImportDetail> search(ArrayList<ImportDetail> arr, String s, String choice){
		ArrayList<ImportDetail> temp = new ArrayList<ImportDetail>();
		s = StringExe.removeAccent(s);
		
		for(ImportDetail p : arr) {
				switch(choice) {
				case "Tên SP": {
					String x = StringExe.removeAccent(p.getProd_id().substring(0, 2).equals("IN")?ingBus.searchByID(p.getProd_id()).getIng_name():prodBus.searchByID(p.getProd_id()).getProd_name());
					if(x.contains(s)) temp.add(p); break;
				}
				default: if(StringExe.removeAccent(p.getProd_id()).contains(s)) temp.add(p); break;
				}
			
		}
		return temp;
	}
	public ArrayList<ImportDetail> sort(ArrayList<ImportDetail> arr, String type, String order){
		switch(type) {
		case "Tên SP": Collections.sort(arr, new Comparator<ImportDetail>() {
			@Override
			public int compare(ImportDetail x, ImportDetail y) {
				String a = x.getProd_id().substring(0, 2).equals("IN")?ingBus.searchByID(x.getProd_id()).getIng_name():prodBus.searchByID(x.getProd_id()).getProd_name();
				String b = y.getProd_id().substring(0, 2).equals("IN")?ingBus.searchByID(y.getProd_id()).getIng_name():prodBus.searchByID(y.getProd_id()).getProd_name();
				return StringExe.removeAccent(a).compareTo(StringExe.removeAccent(b));
			}
		});break;
		case "Đơn giá": Collections.sort(arr, Comp.UNIT_PRICE); break;
		case "Số lượng": Collections.sort(arr, Comp.AMOUNT); break;
		default: Collections.sort(arr, Comp.ID); break;
		}
		if(order.equals("Giảm dần")) Collections.reverse(arr);
		return arr;
	}
	public static class Comp{
		public final static Comparator<ImportDetail> ID = (ImportDetail x, ImportDetail y) -> x.getProd_id().compareTo(y.getProd_id());
		public final static Comparator<ImportDetail> UNIT_PRICE = (ImportDetail x, ImportDetail y) -> Double.compare(x.getUnit_price(), y.getUnit_price());
		public final static Comparator<ImportDetail> AMOUNT = (ImportDetail x, ImportDetail y) -> Double.compare(x.getAmount(), y.getAmount());
	}
	public double getTotalPrice(String impID) {
		ArrayList<ImportDetail> arr = this.searchByImportID(impID);
		double s = 0;
		for(ImportDetail p : arr) {
			s = s + p.getAmount()*p.getUnit_price();
		}
		return s;
	}
	public double getTotalPrice(ArrayList<ImportDetail> arr) {
		double s = 0;
		for(ImportDetail p : arr) {
			s = s + p.getAmount()*p.getUnit_price();
		}
		return s;
	}
	public void updateStorage(ArrayList<ImportDetail> arr) {
		for(ImportDetail p : arr) {
			if(p.getProd_id().substring(0, 2).equals("IN")) {
				Ingredient temp = ingBus.searchByID(p.getProd_id());
				temp.setAmount(temp.getAmount() + p.getAmount());
				ingBus.update(temp);
			}else {
				Product temp = prodBus.searchByID(p.getProd_id());
				temp.setAmount(temp.getAmount() + (int)p.getAmount());
				prodBus.update(temp);
			}
		}
	}
	public int add(ImportDetail p) {
		return dao.add(p);
	}
	public int delete(String imp_id) {
		return dao.delete(imp_id);
	}
	public int delete(String imp_id,String prod_id) {
		return dao.delete(imp_id,prod_id);
	}
	public int update(ImportDetail p) {
		return dao.update(p);
	}
}
