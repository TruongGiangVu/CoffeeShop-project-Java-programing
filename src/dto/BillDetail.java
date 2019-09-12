package dto;

public class BillDetail {
	private String bill_id;
	private String prod_id;
	private double unit_price;
	private double sale_unit_price;
	private String sale_id;
	private int amount;
	public BillDetail(){}
	public BillDetail(String bill_id, String prod_id, int amount, double unit_price, double sale_unit_price, String sale_id) {
		super();
		this.bill_id = bill_id;
		this.prod_id = prod_id;
		this.unit_price = unit_price;
		this.sale_unit_price = sale_unit_price;
		this.amount = amount;
		this.sale_id = sale_id;
	}
	public String getBill_id() {
		return bill_id;
	}
	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	public double getSale_unit_price() {
		return sale_unit_price;
	}
	public void setSale_unit_price(double sale_unit_price) {
		this.sale_unit_price = sale_unit_price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getSale_id() {
		return sale_id;
	}
	public void setSale_id(String sale_id) {
		this.sale_id = sale_id;
	}
	
}
