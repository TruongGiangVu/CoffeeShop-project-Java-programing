package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.*;
import bus.*;
import dto.ProdSale;
import java.util.*;

public class ProdSaleDAO {
	public ArrayList<ProdSale> readAll(){
		ArrayList<ProdSale> arr = new ArrayList<ProdSale>();
		String sql = "select * from prod_sale;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				ProdSale p = new ProdSale();
				p.setSale_id(rs.getString("sale_id"));
				p.setProd_id(rs.getString("prod_id"));
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
	public ArrayList<ProdSale> read(){
		ArrayList<ProdSale> arr = new ArrayList<ProdSale>();
		String sql = "select * from prod_sale where status = 0;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				ProdSale p = new ProdSale();
				p.setSale_id(rs.getString("sale_id"));
				p.setProd_id(rs.getString("prod_id"));
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
	
	public int delete(String sale_id, String prod_id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("delete from prod_sale where sale_id ='%s' and prod_id = '%s';", sale_id, prod_id);
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
		String sql =String.format("delete from prod_sale where sale_id ='%s';", sale_id);
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
	public int add(ProdSale p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("insert into prod_sale(sale_id, prod_id, status) "
				+ "values ('%s','%s',%d);",p.getSale_id(), p.getProd_id(), p.getStatus());
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
	public int disable(String sale_id, String prod_id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update prod_sale set status = 1 "
				+ "where sale_id = '%s' and prod_id = '%s';",sale_id, prod_id);
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
	public int active(String sale_id, String prod_id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update prod_sale set status = 0 "
				+ "where sale_id = '%s' and prod_id = '%s';",sale_id, prod_id);
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
	
	public int update(ProdSale p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update prod_sale set status = %d "
				+ "where sale_id = '%s' and prod_id = '%s';",p.getStatus(), p.getSale_id(),p.getProd_id());
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
