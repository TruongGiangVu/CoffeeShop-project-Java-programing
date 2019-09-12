package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Recipe;

public class RecipeDAO {
	public  ArrayList<Recipe> readAll(){
		ArrayList<Recipe> arr = new ArrayList<Recipe>();
		String sql = "select * from recipe;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Recipe p = new Recipe();
				p.setProd_id(rs.getString("prod_id"));
				p.setIng_id(rs.getString("Ing_id"));
				p.setAmount(rs.getDouble("amount"));
				
				arr.add(p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.close();
		}
		return arr;
	}
	public  int delete(String prod_id, String ing_id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("delete from recipe where prod_id ='%s' and ing_id = '%s';", prod_id, ing_id);
		int check = 0;
		try {
			data.connect();
			check = data.update(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.close();
		}
		return check;
	}
	public  int delete(String prod_id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("delete from recipe where prod_id ='%s';", prod_id);
		int check = 0;
		try {
			data.connect();
			check = data.update(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.close();
		}
		return check;
	}
	public  int add(Recipe p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("insert into recipe(prod_id,ing_id,amount) "
				+ "values ('%s','%s',%f);", p.getProd_id(),p.getIng_id(),p.getAmount());
		int check = 0;
		try {
			data.connect();
			check = data.update(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.close();
		}
		return check;
	}
	public  int update(Recipe p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update recipe set amount = %f "
				+ "where prod_id = '%s' and ing_id = '%s';",p.getAmount(), p.getProd_id(),p.getIng_id());
		int check = 0;
		try {
			data.connect();
			check = data.update(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.close();
		}
		return check;
	}
}
