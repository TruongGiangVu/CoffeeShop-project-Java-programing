package dto;

public class Product {
	private String prod_id;
	private String prod_name;
	private double unit_price;
	private int amount;
	private String measure;
	private int status;
	private String description;
	private String img;
	public Product(){}
	public Product(String prod_id, String prod_name, double unit_price, int amount, String measure, int status,
			String description,String img) {
		super();
		this.prod_id = prod_id;
		this.prod_name = prod_name;
		this.unit_price = unit_price;
		this.amount = amount;
		this.measure = measure;
		this.status = status;
		this.description = description;
		this.img = img;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
}
