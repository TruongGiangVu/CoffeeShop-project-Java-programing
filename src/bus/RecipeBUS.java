package bus;


import java.util.ArrayList;

import dao.RecipeDAO;
import dto.Recipe;

public class RecipeBUS {
	private RecipeDAO dao = new RecipeDAO();
	public ArrayList<Recipe> readAll(){
		return dao.readAll();
	}
	public Recipe searchByID(String prod_id,String ing_id) {
		ArrayList<Recipe> arr = dao.readAll();
		for(Recipe p : arr) {
			if(p.getProd_id().equals(prod_id) && p.getIng_id().equals(ing_id)) return p;
		}
		return null;
	}
	public ArrayList<Recipe> searchByProductID(String prod_id) {
		ArrayList<Recipe> arr = dao.readAll();
		ArrayList<Recipe> kq=new ArrayList<Recipe>();
		for(Recipe p : arr) {
			if(p.getProd_id().equals(prod_id)) kq.add(p);
		}
		return kq;
	}
	public Recipe searchByIngredientID(String ing_id) {
		ArrayList<Recipe> arr = dao.readAll();
		for(Recipe p : arr) {
			if(p.getIng_id().equals(ing_id)) return p;
		}
		return null;
	}
	public boolean hasRep(String prod_id) {
		ArrayList<Recipe> arr = dao.readAll();
		for(Recipe p : arr) {
			if(p.getProd_id().equals(prod_id)) return true;
		}
		return false;
	}
	public int add(Recipe p) {
		return dao.add(p);
	}
	public int delete(String prod_id) {
		return dao.delete(prod_id);
	}
	public int delete(String prod_id,String ing_id) {
		return dao.delete(prod_id,ing_id);
	}
	public int update(Recipe p) {
		return dao.update(p);
	}
}
