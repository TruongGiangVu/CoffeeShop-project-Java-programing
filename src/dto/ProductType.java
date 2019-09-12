package dto;

public class ProductType {
	private String type_id;
	private String type_name;
	private int status;
	public ProductType(){}
	public ProductType(String type_id, String type_name, int status) {
		super();
		this.type_id = type_id;
		this.type_name = type_name;
		this.status = status;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
