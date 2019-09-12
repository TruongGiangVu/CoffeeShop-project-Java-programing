package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.BillDetail;

public class BillDetailDAO {
	public ArrayList<BillDetail> readAll(){
		ArrayList<BillDetail> arr = new ArrayList<BillDetail>();
		String sql = "select * from bill_detail;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				BillDetail p = new BillDetail();
				p.setBill_id(rs.getString("bill_id"));
				p.setProd_id(rs.getString("prod_id"));
				p.setAmount(rs.getInt("amount"));
				p.setUnit_price(rs.getDouble("unit_price"));
				p.setSale_unit_price(rs.getDouble("sale_unit_price"));
				p.setSale_id(rs.getString("sale_id"));
				
				arr.add(p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.close();
		}
		return arr;
	}
	public int delete(String bill_id, String prod_id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("delete from bill_detail where bill_id ='%s' and prod_id = '%s';",bill_id, prod_id);
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
	public int delete(String bill_id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("delete from bill_detail where bill_id ='%s';", bill_id);
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
	public int add(BillDetail p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("insert into bill_detail(bill_id,prod_id,amount,unit_price,sale_unit_price,sale_id) "
				+ "values ('%s','%s',%d,%f,%f,'%s');",p.getBill_id(), p.getProd_id(),p.getAmount(),p.getUnit_price(),p.getSale_unit_price(),p.getSale_id());
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
	public int update(BillDetail p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update bill_detail set amount = %d, unit_price=%f, sale_unit_price=%f, sale_id='%s' "
				+ "where bill_id = '%s' and prod_id = '%s';",p.getAmount(),p.getUnit_price(),p.getSale_unit_price(), p.getSale_id(), p.getBill_id(), p.getProd_id());
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
