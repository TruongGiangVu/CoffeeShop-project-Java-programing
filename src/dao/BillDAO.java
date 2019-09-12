package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Bill;

public class BillDAO {
	public ArrayList<Bill> readAll(){
		ArrayList<Bill> arr = new ArrayList<Bill>();
		String sql = "select * from bill;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Bill p = new Bill();
				p.setBill_id(rs.getString("bill_id"));
				p.setEmp_id(rs.getString("emp_id"));
				p.setCus_id(rs.getString("cus_id"));
				p.setDate(rs.getString("date"));
				p.setStatus(rs.getInt("status"));
				p.setSale_id(rs.getString("sale_id"));
				p.setSale_total(rs.getDouble("sale_total"));
				arr.add(p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.close();
		}
		return arr;
	}
	public ArrayList<Bill> read(){
		ArrayList<Bill> arr = new ArrayList<Bill>();
		String sql = "select * from bill where status <> 2 ;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Bill p = new Bill();
				p.setBill_id(rs.getString("bill_id"));
				p.setEmp_id(rs.getString("emp_id"));
				p.setCus_id(rs.getString("cus_id"));
				p.setDate(rs.getString("date"));
				p.setStatus(rs.getInt("status"));
				p.setSale_id(rs.getString("sale_id"));
				p.setSale_total(rs.getDouble("sale_total"));
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
		String sql =String.format("delete from bill where bill_id = '%s';", id);
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
	public int add(Bill p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("insert into bill(bill_id,emp_id,cus_id,date,sale_id,sale_total,status) "
				+ "values ('%s','%s','%s','%s','%s',%f,%d);", p.getBill_id(),p.getEmp_id(),p.getCus_id(),p.getDateStr(),p.getSale_id(),p.getSale_total(),p.getStatus());
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
	public int lock(String id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update bill set status = 1 "
				+ "where bill_id = '%s';",id);
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
		String sql =String.format("update bill set status = 0 "
				+ "where bill_id = '%s';",id);
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
		String sql =String.format("update bill set status = 2 "
				+ "where bill_id = '%s';",id);
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
	public int update(Bill p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update bill set emp_id = '%s', cus_id = '%s', date = '%s', sale_id = '%s', sale_total = %f, status = %d "
				+ "where bill_id = '%s';",p.getEmp_id(),p.getCus_id(),p.getDateStr(),p.getSale_id(),p.getSale_total(),p.getStatus(),p.getBill_id());
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
