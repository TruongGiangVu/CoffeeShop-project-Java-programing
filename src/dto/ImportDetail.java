package dto;

public class ImportDetail {
	private String imp_id;
	private String prod_id;
	private double amount;
	private double unit_price;
	public ImportDetail(){}
	public ImportDetail(String imp_id, String prod_id, double amount, double unit_price) {
		super();
		this.imp_id = imp_id;
		this.prod_id = prod_id;
		this.amount = amount;
		this.unit_price = unit_price;
	}
	public String getImp_id() {
		return imp_id;
	}
	public void setImp_id(String imp_id) {
		this.imp_id = imp_id;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	
}
