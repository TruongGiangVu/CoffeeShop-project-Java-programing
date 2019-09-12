package dto;

public class Customer {
	private String cus_id;
	private String cus_name;
	private String phone;
	private int status;
	public Customer(){}
	public Customer(String cus_id, String cus_name, String phone, int status) {
		super();
		this.cus_id = cus_id;
		this.cus_name = cus_name;
		this.phone = phone;
		this.status = status;
	}
	public String getCus_id() {
		return cus_id;
	}
	public void setCus_id(String cus_id) {
		this.cus_id = cus_id;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
