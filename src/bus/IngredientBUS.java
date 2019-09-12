package bus;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

import bus.IngredientBUS.Comparators;
import dao.IngredientDAO;
import dto.Ingredient;
import dto.Product;
import dto.Ingredient;

public class IngredientBUS {
	private IngredientDAO dao = new IngredientDAO();
	public ArrayList<Ingredient> readAll(){
		return dao.readAll();
	}
	public ArrayList<Ingredient> read(){
		return dao.read();
	}
	public Ingredient searchByID(String id) {
		ArrayList<Ingredient> arr = dao.readAll();
		for(Ingredient p : arr) {
			if(p.getIng_id().equals(id)) return p;
		}
		return null;
	}
	public Ingredient searchByID(String id,boolean au) {
		ArrayList<Ingredient> arr;
		if(au==true)arr=this.readAll();
		else arr=this.read();
		for(Ingredient p : arr) {
			if(p.getIng_id().equals(id)) return p;
		}
		return null;
	}
	public ArrayList<Ingredient> searchByName(String name,ArrayList<Ingredient> arr) {
		ArrayList<Ingredient> kq=new ArrayList<Ingredient>();
		name=this.covertStringToURL(name);
		for(Ingredient p : arr) {
			String now=p.getIng_name();
			now=this.covertStringToURL(now);
			if(now.indexOf(name)>=0) kq.add(p);
		}
		return kq;
	}
	public ArrayList<Ingredient> searchByName(String name) {
		ArrayList<Ingredient> arr=this.readAll();
		ArrayList<Ingredient> kq=new ArrayList<Ingredient>();
		name=this.covertStringToURL(name);
		for(Ingredient p : arr) {
			String now=p.getIng_name();
			now=this.covertStringToURL(now);
			if(now.indexOf(name)>=0) kq.add(p);
		}
		return kq;
	}
	public ArrayList<Ingredient> searchByMeasure(String measure,ArrayList<Ingredient> arr) {
		ArrayList<Ingredient> kq=new ArrayList<Ingredient>();
		measure=this.covertStringToURL(measure);
		for(Ingredient p : arr) {
			String now=p.getMeasure();
			now=this.covertStringToURL(now);
			if(now.indexOf(measure)>=0) kq.add(p);
		}
		return kq;
	}
	public int add(Ingredient p) {
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
	public int update(Ingredient p) {
		return dao.update(p);
	}
	public  ArrayList<Ingredient> sort(ArrayList<Ingredient>arr,String type,String side,boolean au){
		String s =covertStringToURL(side);
		String t =covertStringToURL(type);
		Comparators com=new Comparators();
		switch(t) {
			case "ma": Collections.sort(arr, com.ID); break;
			case "ten": arr.sort(com.NAME); break;
			case "gia tri": arr.sort(com.PRICE); break;
			case "don vi": arr.sort(com.MEASURE); break;
			default :break;
		}
		if( s.equals("giam dan")) {
			Collections.reverse(arr);
		}
		return arr;
	}
	public class Comparators{
		public   Comparator<Ingredient> ID = (Ingredient p1, Ingredient p2) -> p1.getIng_id().compareTo(p2.getIng_id());
		public   Comparator<Ingredient> NAME= (Ingredient p1, Ingredient p2) -> covertStringToURL(p1.getIng_name()).compareTo(covertStringToURL(p2.getIng_name()));
		public   Comparator<Ingredient> PRICE = (Ingredient p1, Ingredient p2) -> (int)(p1.getUnit_price()-p2.getUnit_price());
		public   Comparator<Ingredient> MEASURE = (Ingredient p1, Ingredient p2) -> covertStringToURL(p1.getMeasure()).compareTo(covertStringToURL(p2.getMeasure()));
	}
	public  String covertStringToURL(String str) {
		   try {
		       String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
		       Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		       return pattern.matcher(temp).replaceAll("").toLowerCase();
		   } catch (Exception e) {
		       e.printStackTrace(); 
		   }
		   return "";
	}
	public ArrayList<Ingredient> searchByPrice(int mind,int maxd,boolean au) {
		ArrayList<Ingredient> arr;
		if(au==true)arr=this.readAll();
		else arr=this.read();
		ArrayList<Ingredient> kq=new ArrayList<Ingredient>();
		for(Ingredient p : arr) {
			if(p.getUnit_price()>=mind && p.getUnit_price()<=maxd) kq.add(p);
		}
		return kq;
	}
	public int getRow() {
		return dao.readAll().size();
	}
}
