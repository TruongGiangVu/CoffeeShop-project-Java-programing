package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Import;

public class ImportDAO {
	public ArrayList<Import> readAll(){
		ArrayList<Import> arr = new ArrayList<Import>();
		String sql = "select * from import;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Import p = new Import();
				p.setImp_id(rs.getString("imp_id"));
				p.setEmp_id(rs.getString("emp_id"));
				p.setSup_id(rs.getString("sup_id"));
				p.setImport_date(rs.getString("import_date"));
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
	public ArrayList<Import> read(){
		ArrayList<Import> arr = new ArrayList<Import>();
		String sql = "select * from import where status <> 2;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Import p = new Import();
				p.setImp_id(rs.getString("imp_id"));
				p.setEmp_id(rs.getString("emp_id"));
				p.setSup_id(rs.getString("sup_id"));
				p.setImport_date(rs.getString("import_date"));
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
		String sql =String.format("delete from import where imp_id = '%s';", id);
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
	public int add(Import p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("insert into import(imp_id,sup_id,emp_id,import_date,status) "
				+ "values ('%s','%s','%s','%s',%d);", p.getImp_id(),p.getSup_id(),p.getEmp_id(),p.getImport_dateStr(),p.getStatus());
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
		String sql =String.format("update import set status = 1 "
				+ "where imp_id = '%s';",id);
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
		String sql =String.format("update import set status = 0 "
				+ "where imp_id = '%s';",id);
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
		String sql =String.format("update import set status = 2 "
				+ "where imp_id = '%s';",id);
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
	public int update(Import p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update import set sup_id = '%s', emp_id = '%s', import_date = '%s', status = %d "
				+ "where imp_id = '%s';",p.getSup_id(),p.getEmp_id(),p.getImport_dateStr(),p.getStatus(),p.getImp_id());
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
