package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.BillSale;

public class BillSaleDAO {
	public ArrayList<BillSale> readAll(){
		ArrayList<BillSale> arr = new ArrayList<BillSale>();
		String sql = "select * from bill_sale;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				BillSale p = new BillSale();
				p.setSale_id(rs.getString("sale_id"));
				p.setBill_id(rs.getString("bill_id"));
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
	public ArrayList<BillSale> read(){
		ArrayList<BillSale> arr = new ArrayList<BillSale>();
		String sql = "select * from bill_sale where status = 0;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				BillSale p = new BillSale();
				p.setSale_id(rs.getString("sale_id"));
				p.setBill_id(rs.getString("bill_id"));
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
	public int delete(String sale_id, String bill_id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("delete from bill_sale where sale_id ='%s' and bill_id = '%s';", sale_id, bill_id);
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
	public int delete(String sale_id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("delete from bill_sale where sale_id ='%s';", sale_id);
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
	public int add(BillSale p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("insert into bill_sale(sale_id, bill_id, status) "
				+ "values ('%s','%s',%d);",p.getSale_id(), p.getBill_id(), p.getStatus());
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
	public int disable(String sale_id, String bill_id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update bill_sale set status = 1 "
				+ "where sale_id = '%s' and bill_id = '%s';", sale_id, bill_id);
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
	public int active(String sale_id, String bill_id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update bill_sale set status = 0 "
				+ "where sale_id = '%s' and bill_id = '%s';", sale_id, bill_id);
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
	public int update(BillSale p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update bill_sale set status = %d "
				+ "where sale_id = '%s' and bill_id = '%s';",p.getStatus(), p.getSale_id(),p.getBill_id());
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
