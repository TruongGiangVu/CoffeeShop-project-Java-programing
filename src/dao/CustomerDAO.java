package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Customer;

public class CustomerDAO {
	public ArrayList<Customer> readAll(){
		ArrayList<Customer> arr = new ArrayList<Customer>();
		String sql = "select * from customer;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Customer p = new Customer();
				p.setCus_id(rs.getString("cus_id"));
				p.setCus_name(rs.getString("cus_name"));
				p.setPhone(rs.getString("phone"));
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
	public ArrayList<Customer> read(){
		ArrayList<Customer> arr = new ArrayList<Customer>();
		String sql = "select * from customer where status = 0;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Customer p = new Customer();
				p.setCus_id(rs.getString("cus_id"));
				p.setCus_name(rs.getString("cus_name"));
				p.setPhone(rs.getString("phone"));
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
		String sql =String.format("delete from customer where cus_id = '%s';", id);
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
		String sql =String.format("update customer set status = 1 where cus_id = '%s';", id);
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
		String sql =String.format("update customer set status = 0 where cus_id = '%s';", id);
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
	public int add(Customer p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("insert into customer(cus_id,cus_name,phone,status) "
				+ "values ('%s','%s','%s',%d);", p.getCus_id(),p.getCus_name(),p.getPhone(),p.getStatus());
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
	public int update(Customer p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update customer set cus_name = '%s', phone = '%s', status = %d "
				+ "where cus_id = '%s';",p.getCus_name(),p.getPhone(),p.getStatus(),p.getCus_id());
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
