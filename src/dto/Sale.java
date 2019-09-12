package dto;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Sale {
	final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String sale_id;
	private String sale_name;
	private double number;
	private String measure;
	private LocalDate start_date;
	private LocalDate end_date;
	private double threshold;
	private String threshold_unit;
	private int status;
	public Sale(){}
	public Sale(String sale_id, String sale_name, double number, String measure, String start_date,
			String end_date, double threshold, String threshold_unit, int status) {
		super();
		this.sale_id = sale_id;
		this.sale_name = sale_name;
		this.number = number;
		this.measure = measure;
		this.setStart_date(start_date);
		this.setEnd_date(end_date);
		this.threshold = threshold;
		this.threshold_unit = threshold_unit;
		this.status = status;
	}
	public String getSale_id() {
		return sale_id;
	}
	public void setSale_id(String sale_id) {
		this.sale_id = sale_id;
	}
	public String getSale_name() {
		return sale_name;
	}
	public void setSale_name(String sale_name) {
		this.sale_name = sale_name;
	}
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public LocalDate getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}
	public String getStart_dateStr() {
		return df.format(getStart_date());
	}
	public void setStart_date(String date) {
		this.start_date = LocalDate.parse(date, df);
	}
	public LocalDate getEnd_date() {
		return end_date;
	}
	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}
	public String getEnd_dateStr() {
		return df.format(getEnd_date());
	}
	public void setEnd_date(String date) {
		this.end_date = LocalDate.parse(date, df);
	}
	public double getThreshold() {
		return threshold;
	}
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	public String getThreshold_unit() {
		return threshold_unit;
	}
	public void setThreshold_unit(String threshold_unit) {
		this.threshold_unit = threshold_unit;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
