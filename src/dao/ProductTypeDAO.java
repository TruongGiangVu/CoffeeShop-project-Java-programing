package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.ProductType;

public class ProductTypeDAO {
	public ArrayList<ProductType> readAll(){
		ArrayList<ProductType> arr = new ArrayList<ProductType>();
		String sql = "select * from product_type;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				ProductType p = new ProductType();
				p.setType_id(rs.getString("type_id"));
				p.setType_name(rs.getString("type_name"));
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
	public ArrayList<ProductType> read(){
		ArrayList<ProductType> arr = new ArrayList<ProductType>();
		String sql = "select * from product_type where status = 0;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				ProductType p = new ProductType();
				p.setType_id(rs.getString("type_id"));
				p.setType_name(rs.getString("type_name"));
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
		String sql =String.format("delete from product_type where type_id = '%s';", id);
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
		String sql =String.format("update product_type set status = 1 where type_id = '%s';", id);
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
		String sql =String.format("update product_type set status = 0 where type_id = '%s';", id);
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
	public int add(ProductType p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("insert into product_type(type_id,type_name,status) "
				+ "values ('%s','%s',%d);", p.getType_id(),p.getType_name());
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
	public int update(ProductType p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update product_type set type_name = '%s', status = %d "
				+ "where type_id = '%s';",p.getType_name(),p.getStatus(),p.getType_id());
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
