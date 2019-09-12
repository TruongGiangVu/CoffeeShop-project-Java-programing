package dto;

public class ProdSale {
	private String sale_id;
	private String prod_id;
	private int status;
	public ProdSale(){}
	public ProdSale(String sale_id, String prod_id, int status) {
		super();
		this.sale_id = sale_id;
		this.prod_id = prod_id;
		this.status = status;
	}
	public String getSale_id() {
		return sale_id;
	}
	public void setSale_id(String sale_id) {
		this.sale_id = sale_id;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
