package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Sale;

public class SaleDAO {
	public ArrayList<Sale> readAll(){
		ArrayList<Sale> arr = new ArrayList<Sale>();
		String sql = "select * from sale;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Sale p = new Sale();
				p.setSale_id(rs.getString("sale_id"));
				p.setSale_name(rs.getString("sale_name"));
				p.setNumber(rs.getDouble("number"));
				p.setMeasure(rs.getString("measure"));
				p.setStart_date(rs.getString("start_date"));
				p.setEnd_date(rs.getString("end_date"));
				p.setThreshold(rs.getDouble("threshold"));
				p.setThreshold_unit(rs.getString("threshold_unit"));
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
	public ArrayList<Sale> read(){
		ArrayList<Sale> arr = new ArrayList<Sale>();
		String sql = "select * from sale where status = 0;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Sale p = new Sale();
				p.setSale_id(rs.getString("sale_id"));
				p.setSale_name(rs.getString("sale_name"));
				p.setNumber(rs.getDouble("number"));
				p.setMeasure(rs.getString("measure"));
				p.setStart_date(rs.getString("start_date"));
				p.setEnd_date(rs.getString("end_date"));
				p.setThreshold(rs.getDouble("threshold"));
				p.setThreshold_unit(rs.getString("threshold_unit"));
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
		String sql =String.format("delete from sale where sale_id = '%s';", id);
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
		String sql =String.format("update sale set status = 1 where sale_id = '%s';", id);
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
		String sql =String.format("update sale set status = 0 where sale_id = '%s';", id);
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
	public int add(Sale p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("insert into sale(sale_id,sale_name,number,measure,start_date,end_date,threshold,threshold_unit,status) "
				+ "values ('%s','%s',%f,'%s','%s','%s',%f,'%s',%d);", 
				p.getSale_id(),p.getSale_name(),p.getNumber(),p.getMeasure(),p.getStart_dateStr(),p.getEnd_dateStr(),p.getThreshold(),p.getThreshold_unit(),p.getStatus());
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
	public int update(Sale p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update sale set sale_name = '%s', number= %f, measure = '%s', start_date = '%s', end_date='%s', threshold=%f,threshold_unit='%s', status = %d "
				+ "where sale_id = '%s';",p.getSale_name(),p.getNumber(),p.getMeasure(),p.getStart_dateStr(),p.getEnd_dateStr(),p.getThreshold(),p.getThreshold_unit(),p.getStatus(),p.getSale_id());
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
