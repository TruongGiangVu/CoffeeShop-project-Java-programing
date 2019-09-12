package dao;

import dto.Product;
import java.util.*;
import java.sql.*;
public class ProductDAO {
	public ArrayList<Product> readAll(){
		ArrayList<Product> arr = new ArrayList<Product>();
		String sql = "select * from product;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Product p = new Product();
				p.setProd_id(rs.getString("prod_id"));
				p.setProd_name(rs.getString("prod_name"));
				p.setAmount(rs.getInt("amount"));
				p.setMeasure(rs.getString("measure"));
				p.setUnit_price(rs.getDouble("unit_price"));
				p.setStatus(rs.getInt("status"));
				p.setDescription(rs.getString("description"));
				p.setImg(rs.getString("img"));
				arr.add(p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.close();
		}
		return arr;
	}
	
	public ArrayList<Product> read(){
		ArrayList<Product> arr = new ArrayList<Product>();
		String sql = "select * from product where status = 0;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Product p = new Product();
				p.setProd_id(rs.getString("prod_id"));
				p.setProd_name(rs.getString("prod_name"));
				p.setAmount(rs.getInt("amount"));
				p.setMeasure(rs.getString("measure"));
				p.setUnit_price(rs.getDouble("unit_price"));
				p.setStatus(rs.getInt("status"));
				p.setDescription(rs.getString("description"));
				p.setImg(rs.getString("img"));
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
		String sql =String.format("delete from product where prod_id = '%s';", id);
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
		String sql =String.format("update product set status = 1 where prod_id = '%s';", id);
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
		String sql =String.format("update product set status = 0 where prod_id = '%s';", id);
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
	public int add(Product p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("insert into product(prod_id,prod_name,unit_price,amount,measure,status,description,img) "
				+ "values ('%s','%s',%f,%d,'%s',%d,'%s','%s');", p.getProd_id(),p.getProd_name(),p.getUnit_price(),p.getAmount(),
				p.getMeasure(),p.getStatus(),p.getDescription(),p.getImg());
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
	public int update(Product p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update product set prod_name = '%s', unit_price= %f, amount = %d, measure = '%s',status=%d, description = '%s', img = '%s' "
				+ "where prod_id = '%s';",p.getProd_name(),p.getUnit_price(),p.getAmount(),p.getMeasure(),p.getStatus(),p.getDescription(),p.getImg(),p.getProd_id());
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
	public int getRow(String id) {
		int a=0;
		String sql = "select * from product where (status = 0) and (prod_id like '"+id+"%');";
		DataTransfer data = new DataTransfer();
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			rs.last();
			a=rs.getRow();
			return a;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.close();
		}
		return 0;
	}
}
