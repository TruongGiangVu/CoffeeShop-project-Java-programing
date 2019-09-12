package dto;

public class BillSale {
	private String sale_id;
	private String bill_id;
	private int status;
	public BillSale(){}
	public BillSale(String sale_id, String bill_id, int status) {
		super();
		this.sale_id = sale_id;
		this.bill_id = bill_id;
		this.status = status;
	}
	public String getSale_id() {
		return sale_id;
	}
	public void setSale_id(String sale_id) {
		this.sale_id = sale_id;
	}
	public String getBill_id() {
		return bill_id;
	}
	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
