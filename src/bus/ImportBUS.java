package bus;

import java.util.ArrayList;

import dao.ImportDAO;
import dto.Import;
import bus.ImportDetailBUS;

import java.util.*;
public class ImportBUS {
	private ImportDAO dao = new ImportDAO();
	private SupplierBUS supBus = new SupplierBUS();
	private EmployerBUS empBus = new EmployerBUS();
	private ImportDetailBUS impdetBus = new ImportDetailBUS();
	public ArrayList<Import> readAll(){
		return dao.readAll();
	}
	public ArrayList<Import> read(){
		return dao.read();
	}
	public Import searchByID(String id) {
		ArrayList<Import> arr = dao.readAll();
		for(Import p : arr) {
			if(p.getImp_id().equals(id)) return p;
		}
		return null;
	}
	public ArrayList<Import> searchByEmpID(String emp_id) {
		ArrayList<Import> arr = dao.readAll();
		ArrayList<Import> temp = new ArrayList<Import>();
		for(Import p : arr) {
			if(!(!p.getEmp_id().equals(emp_id) && p.getStatus()==2)) temp.add(p);
		}
		return temp;
	}
	public ArrayList<Import> search(ArrayList<Import> arr, String s, String choice, String from, String to){
		ArrayList<Import> temp = new ArrayList<Import>();
		s = StringExe.removeAccent(s);
		
		for(Import p : arr) {
			boolean start = from.equals("null")?true:(DateExe.compares(p.getImport_dateStr(), from) >= 0);
			boolean end = to.equals("null")?true:(DateExe.compares(p.getImport_dateStr(), to) <= 0);
			if(start && end) {
				switch(choice) {
				case "NCC": if(StringExe.removeAccent(supBus.searchByID(p.getSup_id()).getSup_name()).contains(s)) temp.add(p); break;
				case "NV": if(StringExe.removeAccent(empBus.searchByID(p.getEmp_id()).getEmp_name()).contains(s)) temp.add(p); break;
				default: if(StringExe.removeAccent(p.getImp_id()).contains(s)) temp.add(p); break;
				}
			}
		}
		return temp;
	}
	public ArrayList<Import> sort(ArrayList<Import> arr, String type, String order){
		switch(type) {
		case "Mã NV": Collections.sort(arr, new Comparator<Import>() {
			@Override
			public int compare(Import x, Import y) {
				return StringExe.removeAccent(empBus.searchByID(x.getEmp_id()).getEmp_name()).compareTo(StringExe.removeAccent(empBus.searchByID(y.getEmp_id()).getEmp_name()));
			}
		}); break;
		case "Mã NCC": Collections.sort(arr, new Comparator<Import>() {
			@Override
			public int compare(Import x, Import y) {
				return StringExe.removeAccent(supBus.searchByID(x.getSup_id()).getSup_name()).compareTo(StringExe.removeAccent(supBus.searchByID(y.getSup_id()).getSup_name()));
			}
		}); break;
		case "Ngày lập": Collections.sort(arr, Comp.DATE); break;
		case "Thành tiền": Collections.sort(arr, new Comparator<Import>() {
			@Override
			public int compare(Import x, Import y) {
				return Double.compare(impdetBus.getTotalPrice(x.getImp_id()) , impdetBus.getTotalPrice(y.getImp_id()));
			}
		}); 
		break;
		default: Collections.sort(arr, Comp.ID); break;
		}
		if(order.equals("Giảm dần")) Collections.reverse(arr);
		return arr;
	}
	public static class Comp{
		public final static Comparator<Import> ID = (Import x, Import y) -> x.getImp_id().compareTo(y.getImp_id());
		public final static Comparator<Import> DATE = (Import x, Import y) -> DateExe.compares(x.getImport_dateStr(), y.getImport_dateStr());
	}
	public int add(Import p) {
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
	public int update(Import p) {
		return dao.update(p);
	}
}
