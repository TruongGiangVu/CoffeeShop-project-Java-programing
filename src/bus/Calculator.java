package bus;

import dto.*;
public class Calculator {
	public int countEmp() {
		EmployerBUS empBus = new EmployerBUS();
		return empBus.read().size();
	}
	public int countCus() {
		CustomerBUS cusBus = new CustomerBUS();
		return cusBus.read().size();
	}
	public double totalIncome() {
		BillBUS bus = new BillBUS();
		double total = 0;
		for(Bill p : bus.read()) {
			if(p.getStatus() == 1) total += p.getSale_total();
		}
		return total;
	}
	public double totalPay() {
		ImportBUS bus = new ImportBUS();
		ImportDetailBUS dbus = new ImportDetailBUS();
		double cost = 0;
		for(Import p : bus.read()) {
			if(p.getStatus() == 1) cost += dbus.getTotalPrice(p.getImp_id());
		}
		return cost;
	}
	public double totalRevenue() {
		return this.totalIncome() - this.totalPay();
	}
	public double totalIncomebyEmp(String empID, int month, int year){
		BillBUS bus = new BillBUS();
		double total = 0;
		if(month != 0) {
			for(Bill p : bus.read()) {
				if(p.getStatus() == 1 && p.getEmp_id().equals(empID) && p.getDate().getMonthValue() == month && p.getDate().getYear() == year) 
					total += p.getSale_total();
			}
		}else {
			for(Bill p : bus.read()) {
				if(p.getStatus() == 1 && p.getEmp_id().equals(empID)) total += p.getSale_total();
			}
		}
		
		return total;
	}
	public double totalPaybyEmp(String empID, int month, int year) {
		ImportBUS bus = new ImportBUS();
		ImportDetailBUS dbus = new ImportDetailBUS();
		double cost = 0;
		if(month != 0) {
			for(Import p : bus.read()) {
				if(p.getStatus() == 1 && p.getEmp_id().equals(empID) && p.getImport_date().getMonthValue() == month && p.getImport_date().getYear() == year) 
					cost += dbus.getTotalPrice(p.getImp_id());
			}
		}else {
			for(Import p : bus.read()) {
				if(p.getStatus() == 1 && p.getEmp_id().equals(empID)) cost += dbus.getTotalPrice(p.getImp_id());
			}
		}
		return cost;
	}
	public double totalRevenuebyEmp(String empID, int month, int year) {
		return this.totalIncomebyEmp(empID, month, year) - this.totalPaybyEmp(empID, month, year);
	}
	public int purchaseAmountbyCus(String cusID, int month, int year) {
		BillBUS bus = new BillBUS();
		int total = 0;
		for(Bill p : bus.searchByCusID(cusID)) {
			if(p.getStatus() == 1) total += bus.getPurchaseAmount(p.getBill_id(), month, year);
		}
		return total;
	}
	public int purchaseValuebyCus(String cusID, int month, int year) {
		BillBUS bus = new BillBUS();
		int total = 0;
		for(Bill p : bus.searchByCusID(cusID)) {
			if(p.getStatus() == 1) total += bus.getPurchaseValue(p.getBill_id(), month, year);
		}
		return total;
	}
	public int exportAmountbyProd(String prodID,int month, int year) {
		BillBUS bus = new BillBUS();
		BillDetailBUS dbus = new BillDetailBUS();
		int total = 0;
		if(month != 0) {
			for(Bill p : bus.read()) {
				if(p.getStatus() == 1 && p.getDate().getMonthValue() == month && p.getDate().getYear() == year) {
					for(BillDetail k : dbus.searchByBillID(p.getBill_id())) {
						if(k.getProd_id().equals(prodID)) total += k.getAmount();
					}
				}
			}
		}else {
			for(Bill p : bus.read()) {
				if(p.getStatus() == 1) {
					for(BillDetail k : dbus.searchByBillID(p.getBill_id())) {
						if(k.getProd_id().equals(prodID)) total += k.getAmount();
					}
				}
			}
		}
		return total;
	}
	public int importAmountbyProd(String prodID,int month, int year) {
		ImportBUS bus = new ImportBUS();
		ImportDetailBUS dbus = new ImportDetailBUS();
		int total = 0;
		if(month != 0) {
			for(Import p : bus.read()) {
				if(p.getStatus() == 1 && p.getImport_date().getMonthValue() == month && p.getImport_date().getYear() == year) {
					for(ImportDetail k : dbus.searchByImportID(p.getImp_id())) {
						if(k.getProd_id().equals(prodID)) total += (int)k.getAmount();
					}
				}
			}
		}else {
			for(Import p : bus.read()) {
				if(p.getStatus() == 1) {
					for(ImportDetail k : dbus.searchByImportID(p.getImp_id())) {
						if(k.getProd_id().equals(prodID)) total += (int)k.getAmount();
					}
				}
			}
		}
		return total;
	}
	
}
