package bus;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

import dao.SupplierDAO;
import dto.Supplier;


public class SupplierBUS {
	private SupplierDAO dao = new SupplierDAO();
	public ArrayList<Supplier> readAll(){
		return dao.readAll();
	}
	public ArrayList<Supplier> read(){
		return dao.read();
	}
	public Supplier searchByID(String id) {
		ArrayList<Supplier> arr = dao.readAll();
		for(Supplier p : arr) {
			if(p.getSup_id().equals(id)) return p;
		}
		return null;
	}
	public Supplier searchByID(String id,boolean au) {
		ArrayList<Supplier> arr;
		if(au==true) arr= dao.readAll();
		else  arr= dao.read();
		for(Supplier p : arr) {
			if(p.getSup_id().equals(id)) return p;
		}
		return null;
	}
	public ArrayList<Supplier> searchByName(String name,boolean au) {
		ArrayList<Supplier> arr;
		if(au==true) arr= dao.readAll();
		else  arr= dao.read();
		ArrayList<Supplier> kq=new ArrayList<Supplier>(); 
		name=name.toLowerCase();
		name=covertStringToURL(name);
		for(Supplier p : arr) {
			String now =p.getSup_name();
			now=now.toLowerCase();
			now=covertStringToURL(now);
			if(now.indexOf(name)>=0) kq.add(p);
		}
		return kq; 
	}
	public ArrayList<Supplier> searchByPhone(String phone,boolean au) {
		ArrayList<Supplier> arr;
		if(au==true) arr= dao.readAll();
		else  arr= dao.read();
		ArrayList<Supplier> kq=new ArrayList<Supplier>(); 
		for(Supplier p : arr) {
			if(p.getPhone().indexOf(phone)>=0) kq.add(p);
		}
		return kq; 
	}
	public ArrayList<Supplier> searchByAddress(String address,boolean au) {
		ArrayList<Supplier> arr;
		if(au==true) arr= dao.readAll();
		else  arr= dao.read();
		ArrayList<Supplier> kq=new ArrayList<Supplier>(); 
		address=address.toLowerCase();
		address=covertStringToURL(address);
		for(Supplier p : arr) {
			String now =p.getAddress();
			now=now.toLowerCase();
			now=covertStringToURL(now);
			if(now.indexOf(address)>=0) kq.add(p);
		}
		return kq; 
	}
	public int add(Supplier p) {
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
	public int update(Supplier p) {
		return dao.update(p);
	}
	public int getRow() {
		return dao.readAll().size();
	}
	public  ArrayList<Supplier> sort(ArrayList <Supplier> arr,String type,String side,boolean au){
		String s =covertStringToURL(side);
		String t =covertStringToURL(type);
		//System.out.println(t + " "+ s);
		Comparators com=new Comparators();
		switch(t) {
		case "ma": Collections.sort(arr, com.ID); break;
		case "ten": arr.sort(com.NAME); break;
		case "dia chi": arr.sort(com.ADDRESS); break;
		default :break;
		}
		if( s.equals("giam dan")) {
			Collections.reverse(arr);
		}
		return arr;
	}
	public class Comparators{
		public   Comparator<Supplier> ID = (Supplier p1, Supplier p2) -> p1.getSup_id().compareTo(p2.getSup_id());
		public   Comparator<Supplier> NAME= (Supplier p1, Supplier p2) -> covertStringToURL(p1.getSup_name()).compareTo(covertStringToURL(p2.getSup_name()));
		public   Comparator<Supplier> ADDRESS= (Supplier p1, Supplier p2) -> covertStringToURL(p1.getAddress()).compareTo(covertStringToURL(p2.getAddress()));
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
