package dto;

public class Ingredient {
	private String ing_id;
	private String ing_name;
	private double unit_price;
	private double amount;
	private String measure;
	private int status;
	public Ingredient(){}
	public Ingredient(String ing_id, String ing_name, double unit_price, double amount, String measure, int status) {
		super();
		this.ing_id = ing_id;
		this.ing_name = ing_name;
		this.unit_price = unit_price;
		this.amount = amount;
		this.measure = measure;
		this.status = status;
	}
	public String getIng_id() {
		return ing_id;
	}
	public void setIng_id(String ing_id) {
		this.ing_id = ing_id;
	}
	public String getIng_name() {
		return ing_name;
	}
	public void setIng_name(String ing_name) {
		this.ing_name = ing_name;
	}
	public double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
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
	
}
