package bus;

import java.text.Normalizer;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

import dao.CustomerDAO;
import dto.Customer;

public class CustomerBUS {
	private CustomerDAO dao = new CustomerDAO();
	public ArrayList<Customer> readAll(){
		return dao.readAll();
	}
	public ArrayList<Customer> read(){
		return dao.read();
	}
	public Customer searchByID(String id,boolean au) {
		ArrayList<Customer> arr;
		if(au==true) arr= dao.readAll();
		else  arr= dao.read();
		for(Customer p : arr) {
			if(p.getCus_id().equals(id)) return p;
		}
		return null;
	}
	public Customer searchByID(String id) {
		ArrayList<Customer> arr = dao.readAll();
		for(Customer p : arr) {
			if(p.getCus_id().equals(id)) return p;
		}
		return null;
	}
	public ArrayList<Customer> searchByName(String name,boolean au) {
		ArrayList<Customer> arr;
		if(au==true) arr= dao.readAll();
		else  arr= dao.read();
		ArrayList<Customer> kq=new ArrayList<Customer>(); 
		name=name.toLowerCase();
		name=covertStringToURL(name);
		for(Customer p : arr) {
			String now =p.getCus_name();
			now=now.toLowerCase();
			now=covertStringToURL(now);
			if(now.indexOf(name)>=0) kq.add(p);
		}
		return kq; 
	}
	public ArrayList<Customer> searchByPhone(String phone,boolean au) {
		ArrayList<Customer> arr;
		if(au==true) arr= dao.readAll();
		else  arr= dao.read();
		ArrayList<Customer> kq=new ArrayList<Customer>(); 
		for(Customer p : arr) {
			if(p.getPhone().indexOf(phone)>=0) kq.add(p);
		}
		return kq; 
	}
	public int add(Customer p) {
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
	public int update(Customer p) {
		return dao.update(p);
	}
	public int getRow() {
		return dao.readAll().size();
	}
	public  ArrayList<Customer> sort(ArrayList<Customer> arr ,String type,String side,boolean au){
		String s =covertStringToURL(side);
		String t =covertStringToURL(type);
		//System.out.println(t + " "+ s);
		Comparators com=new Comparators();
		switch(t) {
		case "ma": Collections.sort(arr, com.ID); break;
		case "ho va ten": arr.sort(com.NAME); break;
		default :break;
		}
		if( s.equals("giam dan")) {
			Collections.reverse(arr);
		}
		return arr;
	}
	public class Comparators{
		public   Comparator<Customer> ID = (Customer p1, Customer p2) -> p1.getCus_id().compareTo(p2.getCus_id());
		public   Comparator<Customer> NAME= (Customer p1, Customer p2) -> covertStringToURL(p1.getCus_name()).compareTo(covertStringToURL(p2.getCus_name()));
	}
	public  String covertStringToURL(String str) {
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
