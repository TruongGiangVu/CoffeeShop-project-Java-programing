package dto;

import java.time.*;
import java.time.format.DateTimeFormatter;
public class Employer {
	final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String emp_id;
	private String emp_name;
	private String address;
	private String phone;
	private LocalDate start_date;
	private double salary;
	private int shift;
	private boolean emp_type;
	private String username;
	private String password;
	private int status;
	public Employer(){}
	public Employer(String emp_id, String emp_name, String address, String phone, String date, double salary,
			int shift, boolean emp_type, String username, String password, int status) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.address = address;
		this.phone = phone;
		this.setStart_date(date);
		this.salary = salary;
		this.shift = shift;
		this.emp_type = emp_type;
		this.username = username;
		this.password = password;
		this.status = status;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public LocalDate getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}
	public String getStart_dateStr() {
		return df.format(getStart_date());
	}
	public void setStart_date(String date) {
		this.start_date = LocalDate.parse(date,df);
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getShift() {
		return shift;
	}
	public void setShift(int shift) {
		this.shift = shift;
	}
	public boolean isEmp_type() {
		return emp_type;
	}
	public void setEmp_type(boolean emp_type) {
		this.emp_type = emp_type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
