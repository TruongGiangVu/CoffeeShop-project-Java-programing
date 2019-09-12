package bus;


import dto.Product;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

import dao.ProductDAO;

public class ProductBUS {
	private ProductDAO dao = new ProductDAO();
	private RecipeBUS repBus =new RecipeBUS();

	public ArrayList<Product> readAll() {
		return dao.readAll();
	}

	public ArrayList<Product> read() {
		return dao.read();
	}

	public Product searchByID(ArrayList<Product> arr, String id) {
		for (Product p : arr) {
			if (p.getProd_id().equals(id))
				return p;
		}
		return null;
	}
	public Product searchByID( String id) {
		ArrayList<Product> arr = dao.readAll();
		for(Product p : arr) {
			if(p.getProd_id().equals(id)) return p;
		}
		return null;
	}

	public Product searchByID(String id, boolean au) {
		ArrayList<Product> arr;
		if (au == true)
			arr = this.readAll();
		else
			arr = this.read();
		for (Product p : arr) {
			if (p.getProd_id().equals(id))
				return p;
		}
		return null;
	}
	public boolean hasRep(String prod_id) {
		return repBus.hasRep(prod_id);
	}
	public int add(Product p) {
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

	public int update(Product p) {
		return dao.update(p);
	}

	public int getRow(String id) {
		return dao.getRow(id);
	}

	public ArrayList<Product> searchByName(ArrayList<Product> arr, String name) {
		ArrayList<Product> kq = new ArrayList<Product>();
		for (Product p : arr) {
			String now = p.getProd_name();
			now = this.covertStringToURL(now);
			if (now.indexOf(name) >= 0)
				kq.add(p);
		}
		return kq;
	}

	public ArrayList<Product> searchByPrice(ArrayList<Product> arr, int mind, int maxd) {
		ArrayList<Product> kq = new ArrayList<Product>();
		for (Product p : arr) {
			double now = p.getUnit_price();
			if (now >= mind && now <= maxd)
				kq.add(p);
		}
		return kq;
	}
	public ArrayList<Product> searchByPrice(ArrayList<Product> arr, double mind, double maxd) {
		ArrayList<Product> kq = new ArrayList<Product>();
		for (Product p : arr) {
			double now = p.getUnit_price();
			if (now >= mind && now <= maxd)
				kq.add(p);
		}
		return kq;
	}

	public ArrayList<Product> searchByType(ArrayList<Product> arr, String type) {
		ArrayList<Product> kq = new ArrayList<Product>();
		for (Product p : arr) {
			String now = p.getProd_id().substring(0, 2);
			if (now.equals(type))
				kq.add(p);
		}
		return kq;
	}

	public ArrayList<Product> search(boolean au, String text, int mind, int maxd, String type) {
		ArrayList<Product> arr;
		if (au == true)
			arr = this.readAll();
		else
			arr = this.read();
		text = this.covertStringToURL(text);
		arr = this.searchByName(arr, text);
		arr = this.searchByPrice(arr, mind, maxd);
		if(!type.equals(""))
		arr = this.searchByType(arr, type);
		return arr;
	}
	public ArrayList<Product> search(boolean au, String text, double mind, double maxd, String type) {
		ArrayList<Product> arr;
		if (au == true)
			arr = this.readAll();
		else
			arr = this.read();
		text = this.covertStringToURL(text);
		arr = this.searchByName(arr, text);
		arr = this.searchByPrice(arr, mind, maxd);
		if(!type.equals(""))
		arr = this.searchByType(arr, type);
		return arr;
	}

	public ArrayList<Product> sort(ArrayList<Product> arr, String type, String side, boolean au) {
		String s = covertStringToURL(side);
		String t = covertStringToURL(type);
		Comparators com = new Comparators();
		switch (t) {
		case "ma":
			Collections.sort(arr, com.ID);
			break;
		case "ten":
			arr.sort(com.NAME);
			break;
		case "don gia":
			arr.sort(com.PRICE);
			break;
		default:
			break;
		}
		if (s.equals("giam dan")) {
			Collections.reverse(arr);
		}
		return arr;
	}

	public class Comparators {
		public Comparator<Product> ID = (Product p1, Product p2) -> p1.getProd_id().compareTo(p2.getProd_id());
		public Comparator<Product> NAME = (Product p1, Product p2) -> covertStringToURL(p1.getProd_name())
				.compareTo(covertStringToURL(p2.getProd_name()));
		public Comparator<Product> PRICE = (Product p1, Product p2) -> (int) (p1.getUnit_price() - p2.getUnit_price());
	}

	public String covertStringToURL(String str) {
		try {
			String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll("đ", "d");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
