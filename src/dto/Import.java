package dto;

import java.time.*;
import java.time.format.DateTimeFormatter;
public class Import {
	final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String imp_id;
	private String emp_id;
	private String sup_id;
	private LocalDate import_date;
	private int status;
	public Import(){}
	public Import(String imp_id, String emp_id, String sup_id, String date, int status) {
		super();
		this.imp_id = imp_id;
		this.emp_id = emp_id;
		this.sup_id = sup_id;
		this.setImport_date(date);
		this.status = status;
	}
	public String getImp_id() {
		return imp_id;
	}
	public void setImp_id(String imp_id) {
		this.imp_id = imp_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getSup_id() {
		return sup_id;
	}
	public void setSup_id(String sup_id) {
		this.sup_id = sup_id;
	}
	public LocalDate getImport_date() {
		return import_date;
	}
	public void setImport_date(LocalDate import_date) {
		this.import_date = import_date;
	}
	public String getImport_dateStr() {
		return df.format(getImport_date());
	}
	public void setImport_date(String date) {
		this.import_date = LocalDate.parse(date, df);
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
