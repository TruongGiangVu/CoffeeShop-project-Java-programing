package dto;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Bill {
	final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String bill_id;
	private String emp_id;
	private String cus_id;
	private LocalDate date;
	private String sale_id;
	private double sale_total;
	private int status;
	public Bill(){}
	public Bill(String bill_id, String emp_id, String cus_id, String date, String sale_id, double sale_total, int status) {
		super();
		this.bill_id = bill_id;
		this.emp_id = emp_id;
		this.cus_id = cus_id;
		this.date = LocalDate.parse(date, df);
		this.sale_id = sale_id;
		this.sale_total = sale_total;
		this.status = status;
	}
	
	public String getBill_id() {
		return bill_id;
	}
	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getCus_id() {
		return cus_id;
	}
	public void setCus_id(String cus_id) {
		this.cus_id = cus_id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getDateStr() {
		return df.format(getDate());
	}
	public void setDate(String date) {
		this.date = LocalDate.parse(date, df);
	}
	public String getSale_id() {
		return sale_id;
	}
	public void setSale_id(String sale_id) {
		this.sale_id = sale_id;
	}
	public double getSale_total() {
		return sale_total;
	}
	public void setSale_total(double sale_total) {
		this.sale_total = sale_total;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
