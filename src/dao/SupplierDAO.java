package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Supplier;

public class SupplierDAO {
	public ArrayList<Supplier> readAll(){
		ArrayList<Supplier> arr = new ArrayList<Supplier>();
		String sql = "select * from supplier;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Supplier p = new Supplier();
				p.setSup_id(rs.getString("sup_id"));
				p.setSup_name(rs.getString("sup_name"));
				p.setAddress(rs.getString("address"));
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
	public ArrayList<Supplier> read(){
		ArrayList<Supplier> arr = new ArrayList<Supplier>();
		String sql = "select * from supplier where status = 0;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Supplier p = new Supplier();
				p.setSup_id(rs.getString("sup_id"));
				p.setSup_name(rs.getString("sup_name"));
				p.setAddress(rs.getString("address"));
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
		String sql =String.format("delete from supplier where sup_id = '%s';", id);
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
	}public int disable(String id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update supplier set status = 1 where sup_id = '%s';", id);
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
		String sql =String.format("update supplier set status = 0 where sup_id = '%s';", id);
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
	public int add(Supplier p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("insert into supplier(sup_id,sup_name,address,phone,status) "
				+ "values ('%s','%s','%s','%s',%d);", p.getSup_id(),p.getSup_name(),p.getAddress(),p.getPhone(),p.getStatus());
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
	public int update(Supplier p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update supplier set sup_name = '%s', address= '%s', phone = '%s',status = %d "
				+ "where sup_id = '%s';",p.getSup_name(),p.getAddress(),p.getPhone(),p.getStatus(),p.getSup_id());
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
