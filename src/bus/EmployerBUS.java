package bus;

import java.util.ArrayList;
import java.util.*;

import dao.EmployerDAO;
import dto.Employer;

public class EmployerBUS {
	private EmployerDAO dao = new EmployerDAO();

	public ArrayList<Employer> readAll() {
		return dao.readAll();
	}

	public ArrayList<Employer> read() {
		return dao.read();
	}

	public Employer searchByID(String id) {
		ArrayList<Employer> arr = dao.readAll();
		for (Employer p : arr) {
			if (p.getEmp_id().equals(id))
				return p;
		}
		return null;
	}

	public Employer searchByUser(String user) {
		ArrayList<Employer> arr = dao.readAll();
		for (Employer p : arr) {
			if (p.getUsername().equals(user))
				return p;
		}
		return null;
	}
	
	public ArrayList<Employer> search(ArrayList<Employer> arr, String s, String type){
		ArrayList<Employer> temp = new ArrayList<Employer>();
		s = StringExe.removeAccent(s.toLowerCase());
		for(Employer e : arr) {
			if(type.equals("Mã")) {
				if(e.getEmp_id().toLowerCase().contains(s)) temp.add(e);
			}else if(StringExe.removeAccent(e.getEmp_name()).contains(s)) temp.add(e);
		}
		return temp;
	}
	public ArrayList<Employer> sort(ArrayList<Employer> arr, String type, String order) {
		switch (type) {
		case "Mã":
			Collections.sort(arr, Comp.ID);
			break;
		case "Tên":
			Collections.sort(arr, Comp.NAME);
			break;
		case "Ca":
			Collections.sort(arr, Comp.SHIFT);
			break;
		case "Lương":
			Collections.sort(arr, Comp.SALARY);
			break;
		case "Ngày vào làm":
			Collections.sort(arr, Comp.START_DATE);
			break;
		default:
			Collections.sort(arr, Comp.ID);
			break;
		}
		if (order.equals("Giảm dần"))
			Collections.reverse(arr);
		return arr;
	}

	public static class Comp {
		public final static Comparator<Employer> ID = (Employer x, Employer y) -> x.getEmp_id().compareTo(y.getEmp_id());
		public final static Comparator<Employer> NAME = (Employer x, Employer y) -> StringExe.removeAccent(x.getEmp_name()).compareTo(StringExe.removeAccent(y.getEmp_name()));
		public final static Comparator<Employer> SALARY = (Employer x, Employer y) -> Double.compare(x.getSalary(),y.getSalary());
		public final static Comparator<Employer> SHIFT = (Employer x, Employer y) -> Integer.compare(x.getShift(),y.getShift());
		public final static Comparator<Employer> START_DATE = (Employer x, Employer y) -> DateExe.compares(x.getStart_dateStr(), y.getStart_dateStr());
				
	}

	public int add(Employer p) {
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

	public int update(Employer p) {
		return dao.update(p);
	}

	public String loginCheck(String user, String pass) {
		ArrayList<Employer> arr = dao.read();
		for (Employer p : arr) {
			if (p.getUsername().equals(user) && p.getPassword().equals(pass)) {
				if (p.isEmp_type())
					return "admin";
				else
					return "employer";
			}
		}
		return "null";
	}
}
