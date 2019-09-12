package dto;

public class Supplier {
	private String sup_id;
	private String sup_name;
	private String address;
	private String phone;
	private int status;
	public Supplier(){}
	public Supplier(String sup_id, String sup_name, String address, String phone, int status) {
		super();
		this.sup_id = sup_id;
		this.sup_name = sup_name;
		this.address = address;
		this.phone = phone;
		this.status = status;
	}
	public String getSup_id() {
		return sup_id;
	}
	public void setSup_id(String sup_id) {
		this.sup_id = sup_id;
	}
	public String getSup_name() {
		return sup_name;
	}
	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
