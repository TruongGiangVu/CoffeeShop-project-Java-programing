package dao;

import java.sql.*;
import com.mysql.cj.jdbc.Driver;
import javax.swing.*;

public class DataTransfer {
	String user = "root";
	String password = "";
	String url = "jdbc:mysql://localhost:3306/coffeehousedb?useUnicode=yes&characterEncoding=UTF-8";
	Connection conn = null;
	void DataTranfer(){
		
	}
	public void connect() {
		try {
			Driver d = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(d);
			conn = DriverManager.getConnection(url, user, password);
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Chưa kết nối CSDL!");
		}
		
		
	}
	public void close() {
		try {
			if(conn != null) conn = DriverManager.getConnection(url, user, password);
		}catch(SQLException ex) {
			this.displayError(ex);
		}
	}
	public ResultSet execute(String sql) {
		ResultSet rs = null;
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
		}catch(SQLException ex) {
			this.displayError(ex);
		}
		return rs;
	}
	public int update(String sql) {
		int check = 0;
		try {
			Statement st = conn.createStatement();
			check = st.executeUpdate(sql);
		}catch(SQLException ex) {
			this.displayError(ex);
		}
		return check;
	}
	public void displayError(SQLException ex){ 
	       System.out.println(" Error Message:" + ex.getMessage()); 
	       System.out.println(" SQL State:" + ex.getSQLState()); 
	       System.out.println(" Error Code:" + ex.getErrorCode()); 
	   } 
}
