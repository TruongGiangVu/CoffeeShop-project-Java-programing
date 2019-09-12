package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.ImportDetail;

public class ImportDetailDAO {
	public ArrayList<ImportDetail> readAll(){
		ArrayList<ImportDetail> arr = new ArrayList<ImportDetail>();
		String sql = "select * from import_detail;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				ImportDetail p = new ImportDetail();
				p.setImp_id(rs.getString("imp_id"));
				p.setProd_id(rs.getString("prod_id"));
				p.setAmount(rs.getDouble("amount"));
				p.setUnit_price(rs.getDouble("unit_price"));
				
				arr.add(p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			data.close();
		}
		return arr;
	}
	public int delete(String imp_id, String prod_id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("delete from import_detail where imp_id ='%s' and prod_id = '%s';",imp_id, prod_id);
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
	public int delete(String imp_id) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("delete from import_detail where imp_id ='%s';", imp_id);
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
	public int add(ImportDetail p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("insert into import_detail(imp_id,prod_id,amount,unit_price) "
				+ "values ('%s','%s',%f,%f);",p.getImp_id(), p.getProd_id(),p.getAmount(),p.getUnit_price());
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
	public int update(ImportDetail p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update import_detail set amount=%f, unit_price=%f "
				+ "where imp_id = '%s' and prod_id = '%s';",p.getAmount(),p.getUnit_price(), p.getImp_id(),p.getProd_id());
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
