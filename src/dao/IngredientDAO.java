package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Ingredient;

public class IngredientDAO {
	public ArrayList<Ingredient> readAll(){
		ArrayList<Ingredient> arr = new ArrayList<Ingredient>();
		String sql = "select * from ingredient;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Ingredient p = new Ingredient();
				p.setIng_id(rs.getString("ing_id"));
				p.setIng_name(rs.getString("ing_name"));
				p.setAmount(rs.getDouble("amount"));
				p.setMeasure(rs.getString("measure"));
				p.setUnit_price(rs.getDouble("unit_price"));
				p.setStatus(rs.getInt("status"));
				arr.add(p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.close();
		}
		return arr;
	}
	public ArrayList<Ingredient> read(){
		ArrayList<Ingredient> arr = new ArrayList<Ingredient>();
		String sql = "select * from ingredient where status = 0;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Ingredient p = new Ingredient();
				p.setIng_id(rs.getString("ing_id"));
				p.setIng_name(rs.getString("ing_name"));
				p.setAmount(rs.getDouble("amount"));
				p.setMeasure(rs.getString("measure"));
				p.setUnit_price(rs.getDouble("unit_price"));
				p.setStatus(rs.getInt("status"));
				arr.add(p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.close();
		}
		return arr;
	}
	public int delete(String id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("delete from ingredient where ing_id = '%s';", id);
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
	public int disable(String id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update ingredient set status = 1 where ing_id = '%s';", id);
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
	public int active(String id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update ingredient set status = 0 where ing_id = '%s';", id);
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
	public int add(Ingredient p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("insert into ingredient(ing_id,ing_name,unit_price,amount,measure,status) "
				+ "values ('%s','%s',%f,%f,'%s','%s',%d);", p.getIng_id(),p.getIng_name(),p.getUnit_price(),p.getAmount(),p.getMeasure(),p.getStatus());
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
	public int update(Ingredient p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update ingredient set ing_name ='%s', unit_price= %f, amount = %f, measure = '%s',status = %d "
				+ "where ing_id = '%s';",p.getIng_name(),p.getUnit_price(),p.getAmount(),p.getMeasure(),p.getStatus(),p.getIng_id());
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
