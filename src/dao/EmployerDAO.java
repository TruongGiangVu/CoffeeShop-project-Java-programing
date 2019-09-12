package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Employer;

public class EmployerDAO {
	public ArrayList<Employer> readAll(){
		ArrayList<Employer> arr = new ArrayList<Employer>();
		String sql = "select * from employer;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Employer p = new Employer();
				p.setEmp_id(rs.getString("emp_id"));
				p.setEmp_name(rs.getString("emp_name"));
				p.setAddress(rs.getString("address"));
				p.setPhone(rs.getString("phone"));
				p.setStart_date(rs.getString("start_date"));
				p.setSalary(rs.getDouble("salary"));
				p.setShift(rs.getInt("shift"));
				p.setEmp_type(rs.getBoolean("emp_type"));
				p.setUsername(rs.getString("username"));
				p.setPassword(rs.getString("password"));
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
	public ArrayList<Employer> read(){
		ArrayList<Employer> arr = new ArrayList<Employer>();
		String sql = "select * from employer where status = 0;";
		DataTransfer data = new DataTransfer();
		
		try {
			data.connect();
			ResultSet rs = data.execute(sql);
			while(rs.next()) {
				Employer p = new Employer();
				p.setEmp_id(rs.getString("emp_id"));
				p.setEmp_name(rs.getString("emp_name"));
				p.setAddress(rs.getString("address"));
				p.setPhone(rs.getString("phone"));
				p.setStart_date(rs.getString("start_date"));
				p.setSalary(rs.getDouble("salary"));
				p.setShift(rs.getInt("shift"));
				p.setEmp_type(rs.getBoolean("emp_type"));
				p.setUsername(rs.getString("username"));
				p.setPassword(rs.getString("password"));
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
		String sql =String.format("delete from employer where emp_id = '%s';", id);
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
		String sql =String.format("update employer set status = 1 where emp_id = '%s';", id);
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
		String sql =String.format("update employer set status = 0 where emp_id = '%s';", id);
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
	public int add(Employer p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("insert into employer(emp_id,emp_name,address,phone,start_date,salary,shift,emp_type,username,password,status) "
				+ "values ('%s','%s','%s','%s','%s',%f,%d,%b,'%s','%s',%d);",
				p.getEmp_id(),p.getEmp_name(),p.getAddress(),p.getPhone(),p.getStart_dateStr(),p.getSalary(),
				p.getShift(),p.isEmp_type(),p.getUsername(),p.getPassword(),p.getStatus());
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
	public int update(Employer p) {
		DataTransfer data = new DataTransfer();
		String sql =String.format("update employer set emp_name = '%s', "
				+ "address= '%s', "
				+ "phone = '%s', "
				+ "start_date = '%s', "
				+ "salary = %f, "
				+ "shift = %d, emp_type = %b, username = '%s', password = '%s', status = %d "
				+ "where emp_id = '%s';",
				p.getEmp_name(),p.getAddress(),p.getPhone(),p.getStart_dateStr(),p.getSalary(),p.getShift(),p.isEmp_type(),
				p.getUsername(),p.getPassword(),p.getStatus(),p.getEmp_id());
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
