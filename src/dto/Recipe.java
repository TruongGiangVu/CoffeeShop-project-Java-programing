package dto;

public class Recipe {
	private String prod_id;
	private String ing_id;
	private double amount;
	public Recipe(){}
	public Recipe(String prod_id, String ing_id, double amount) {
		super();
		this.prod_id = prod_id;
		this.ing_id = ing_id;
		this.amount = amount;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public String getIng_id() {
		return ing_id;
	}
	public void setIng_id(String ing_id) {
		this.ing_id = ing_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
