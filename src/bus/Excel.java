package bus;
// write

// customer, employer, product, ingredient, recipe, supplier, product_type, 
// bill, bill_detail, bill_sale, import, import_detail, prod_sale, sale

//read
// customer, bill, bill_detail, bill_sale, employer, import, import_detail, 
// ingredient, prod_sale, product, product_type, sale, recipe, supplier

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;

//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import javax.swing.JOptionPane;

import dto.*;
import java.util.ArrayList;

public class Excel {
	HSSFWorkbook workbook;

	public Excel() {
		workbook = new HSSFWorkbook();
	}

	private void autosizeColumn(Sheet sheet, int lastColumn) {
		for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
	}

	private static Object getCellValue(Cell cell) {
		@SuppressWarnings("deprecation")
		CellType cellType = cell.getCellTypeEnum();
		Object cellValue = null;
		switch (cellType) {
		case BOOLEAN:
			cellValue = cell.getBooleanCellValue();
			break;
		case NUMERIC:
			cellValue = cell.getNumericCellValue();
			break;
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		case _NONE:
		case BLANK:
		case ERROR:
			break;
		default:
			break;
		}
		return cellValue;
	}
	public boolean checkfile(String link) {
		String type=link.substring(link.length()-3,link.length());
		if(type.equals("xls")) return true;
		JOptionPane.showMessageDialog(null,"Định dạng file không hợp lệ. Phải là file excel, có đuôi '.xls'");
		return false;
	}
	// bill
	public void writeBill() {
		try {
			BillBUS bus = new BillBUS();
			HSSFSheet sheet;
			if (workbook.getNumberOfSheets() == 0)
				sheet = workbook.createSheet("Bill");
			else
				sheet = workbook.getSheet("Bill");
			List<Bill> list = bus.readAll();
			int rownum = 0;
			Cell cell;
			Row row;
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Bill_id");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Emp_id");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Cus_id");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Date");
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Sale_id");
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("Sale_total");
			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue("Status");
			for (Bill p : list) {
				rownum++;
				row = sheet.createRow(rownum);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(p.getBill_id());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(p.getEmp_id());
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue(p.getCus_id());
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue(p.getDateStr());
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue(p.getSale_id());
				cell = row.createCell(5, CellType.NUMERIC);
				cell.setCellValue(p.getSale_total());
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue(p.getStatus());
			}
			File file = new File("excel\\Bill.xls");
			if (file.exists() == false) {
				file.getParentFile().mkdirs();
			}
			this.autosizeColumn(sheet, 5);
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Bill> readBill(String linkpath) {
		if(!this.checkfile(linkpath)) return null;
		try {
//			FileInputStream inputStream = new FileInputStream(new File("excel\\Bill.xls"));
			FileInputStream inputStream = new FileInputStream(new File(linkpath));
			workbook = new HSSFWorkbook(inputStream);// Đối tượng workbook cho file XSL.
			HSSFSheet sheet = workbook.getSheet("Bill");// Lấy ra sheet từ workbook
			Iterator<Row> rowIterator = sheet.iterator();// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Row row = rowIterator.next();
			Iterator<Cell> header = row.cellIterator();
			int colunm = 0;
			int check = 1;
			while (header.hasNext()) {
				Cell cell = header.next();
				switch (colunm) {
				case 0:
					if (!cell.getStringCellValue().equals("Bill_id"))
						check = 0;
					break;
				case 1:
					if (!cell.getStringCellValue().equals("Emp_id"))
						check = 0;
					break;
				case 2:
					if (!cell.getStringCellValue().equals("Cus_id"))
						check = 0;
					break;
				case 3:
					if (!cell.getStringCellValue().equals("Date"))
						check = 0;
					break;
				case 4:
					if (!cell.getStringCellValue().equals("Sale_id"))
						check = 0;
					break;
				case 5:
					if (!cell.getStringCellValue().equals("Sale_total"))
						check = 0;
					break;
				case 6:
					if (!cell.getStringCellValue().equals("Status"))
						check = 0;
					break;
				}
				colunm++;
			}
			if (check == 0)
				return null;
			ArrayList<Bill> arr = new ArrayList<Bill>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();// Lấy Iterator cho tất cả các cell của dòng hiện tại.
				Iterator<Cell> cellIterator = row.cellIterator();
				Bill p = new Bill();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();// Set value for object
					switch (columnIndex) {
					case 0:
						p.setBill_id((String) cellValue);
						break;
					case 1:
						p.setEmp_id((String) cellValue);
						break;
					case 2:
						p.setCus_id((String) cellValue);
						break;
					case 3:
						p.setDate((String) cellValue);
						break;
					case 4:
						p.setSale_id((String) cellValue);
						break;
					case 5:
						p.setSale_total(Double.parseDouble(cellValue.toString()));
						break;
					case 6:
						p.setStatus((int) Double.parseDouble(cellValue.toString()));
						break;
					default:
						break;
					}
				}
				arr.add(p);
			}
			inputStream.close();
			return arr;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Không tìm thấy file này!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// BillDetail
	public void writeBillDetail() {
		try {
			BillDetailBUS bus = new BillDetailBUS();
			HSSFSheet sheet;
			if (workbook.getNumberOfSheets() == 0)
				sheet = workbook.createSheet("BillDetail");
			else
				sheet = workbook.getSheet("BillDetail");
			List<BillDetail> list = bus.readAll();
			int rownum = 0;
			Cell cell;
			Row row;
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Bill_id");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Prod_id");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Amount");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Unit_price");
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Sale_unit_price");
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("Sale_id");
			for (BillDetail p : list) {
				rownum++;
				row = sheet.createRow(rownum);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(p.getBill_id());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(p.getProd_id());
				cell = row.createCell(2, CellType.NUMERIC);
				cell.setCellValue(p.getAmount());
				cell = row.createCell(3, CellType.NUMERIC);
				cell.setCellValue(p.getUnit_price());
				cell = row.createCell(4, CellType.NUMERIC);
				cell.setCellValue(p.getSale_unit_price());
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue(p.getSale_id());
			}
			File file = new File("excel\\BillDetail.xls");
			if (file.exists() == false) {
				file.getParentFile().mkdirs();
			}
			this.autosizeColumn(sheet, 5);
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<BillDetail> readBillDetail(String linkpath) {
		if(!this.checkfile(linkpath)) return null;
		try {
//			FileInputStream inputStream = new FileInputStream(new File("excel\\BillDetail.xls"));
			FileInputStream inputStream = new FileInputStream(new File(linkpath));
			workbook = new HSSFWorkbook(inputStream);// Đối tượng workbook cho file XSL.
			HSSFSheet sheet = workbook.getSheet("BillDetail");// Lấy ra sheet từ workbook
			Iterator<Row> rowIterator = sheet.iterator();// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Row row = rowIterator.next();
			Iterator<Cell> header = row.cellIterator();
			int colunm = 0;
			int check = 1;
			while (header.hasNext()) {
				Cell cell = header.next();
				switch (colunm) {
				case 0:
					if (!cell.getStringCellValue().equals("Bill_id"))
						check = 0;
					break;
				case 1:
					if (!cell.getStringCellValue().equals("Prod_id"))
						check = 0;
					break;
				case 2:
					if (!cell.getStringCellValue().equals("Amount"))
						check = 0;
					break;
				case 3:
					if (!cell.getStringCellValue().equals("Unit_price"))
						check = 0;
					break;
				case 4:
					if (!cell.getStringCellValue().equals("Sale_unit_price"))
						check = 0;
					break;
				case 5:
					if (!cell.getStringCellValue().equals("Sale_id"))
						check = 0;
					break;
				}
				colunm++;
			}
			if (check == 0)
				return null;
			ArrayList<BillDetail> arr = new ArrayList<BillDetail>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();// Lấy Iterator cho tất cả các cell của dòng hiện tại.
				Iterator<Cell> cellIterator = row.cellIterator();
				BillDetail p = new BillDetail();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();// Set value for object
					switch (columnIndex) {
					case 0:
						p.setBill_id((String) cellValue);
						break;
					case 1:
						p.setProd_id((String) cellValue);
						break;
					case 2:
						p.setAmount((int) Double.parseDouble(cellValue.toString()));
						;
						break;
					case 3:
						p.setUnit_price(Double.parseDouble(cellValue.toString()));
						break;
					case 4:
						p.setSale_unit_price(Double.parseDouble(cellValue.toString()));
						;
						break;
					case 5:
						p.setSale_id(cellValue.toString());
						;
						break;
					default:
						break;
					}
				}
				arr.add(p);
			}
			inputStream.close();
			return arr;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Không tìm thấy file này!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// BillSale
	public void writeBillSale() {
		try {
			BillSaleBUS bus = new BillSaleBUS();
			HSSFSheet sheet;
			if (workbook.getNumberOfSheets() == 0)
				sheet = workbook.createSheet("BillSale");
			else
				sheet = workbook.getSheet("BillSale");

			List<BillSale> list = bus.readAll();
			int rownum = 0;
			Cell cell;
			Row row;
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Sale_id");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Bill_id");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Status");
			for (BillSale p : list) {
				rownum++;
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(p.getSale_id());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(p.getBill_id());
				cell = row.createCell(2, CellType.NUMERIC);
				cell.setCellValue(p.getStatus());
			}
			File file = new File("excel\\BillSale.xls");
			if (file.exists() == false) {
				file.getParentFile().mkdirs();
			}
			this.autosizeColumn(sheet, 3);
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<BillSale> readBillSale(String linkpath) {
		if(!this.checkfile(linkpath)) return null;
		try {
//			FileInputStream inputStream = new FileInputStream(new File("excel\\BillSale.xls"));
			FileInputStream inputStream = new FileInputStream(new File(linkpath));
			workbook = new HSSFWorkbook(inputStream);// Đối tượng workbook cho file XSL.
			HSSFSheet sheet = workbook.getSheet("BillSale");// Lấy ra sheet từ workbook
			Iterator<Row> rowIterator = sheet.iterator();// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Row row = rowIterator.next();
			Iterator<Cell> header = row.cellIterator();
			int colunm = 0;
			int check = 1;
			while (header.hasNext()) {
				Cell cell = header.next();
				switch (colunm) {
				case 0:
					if (!cell.getStringCellValue().equals("Sale_id"))
						check = 0;
					break;
				case 1:
					if (!cell.getStringCellValue().equals("Bill_id"))
						check = 0;
					break;
				case 2:
					if (!cell.getStringCellValue().equals("Status"))
						check = 0;
					break;
				}
				colunm++;
			}
			if (check == 0)
				return null;
			ArrayList<BillSale> arr = new ArrayList<BillSale>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();// Lấy Iterator cho tất cả các cell của dòng hiện tại.
				Iterator<Cell> cellIterator = row.cellIterator();
				BillSale p = new BillSale();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();// Set value for object
					switch (columnIndex) {
					case 0:
						p.setSale_id((String) cellValue);
						break;
					case 1:
						p.setBill_id((String) cellValue);
						break;
					case 2:
						p.setStatus((int) Double.parseDouble(cellValue.toString()));
						;
						break;
					default:
						break;
					}
				}
				arr.add(p);
			}
			inputStream.close();
			return arr;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Không tìm thấy file này!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// customer
	public void writeCustomer() {
		try {
			CustomerBUS bus = new CustomerBUS();
			HSSFSheet sheet;
			if (workbook.getNumberOfSheets() == 0)
				sheet = workbook.createSheet("Customer");
			else
				sheet = workbook.getSheet("Customer");
			List<Customer> list = bus.readAll();
			int rownum = 0;
			Cell cell;
			Row row;
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("ID");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Name");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Phone");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Status");
			for (Customer cus : list) {
				rownum++;
				row = sheet.createRow(rownum);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(cus.getCus_id());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(cus.getCus_name());
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue(cus.getPhone());
				cell = row.createCell(3, CellType.NUMERIC);
				cell.setCellValue(cus.getStatus());
			}
			File file = new File("excel\\Customer.xls");
			if (file.exists() == false) {
				file.getParentFile().mkdirs();
			}
			this.autosizeColumn(sheet, 4);
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Customer> readCustomer(String linkpath) {
		if(!this.checkfile(linkpath)) return null;
		try {
//			FileInputStream inputStream = new FileInputStream(new File("excel\\Customer.xls"));
			FileInputStream inputStream = new FileInputStream(new File(linkpath));
			workbook = new HSSFWorkbook(inputStream);// Đối tượng workbook cho file XSL.
			HSSFSheet sheet = workbook.getSheet("Customer");// Lấy ra sheet từ workbook
			Iterator<Row> rowIterator = sheet.iterator();// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Row row = rowIterator.next();
			Iterator<Cell> header = row.cellIterator();
			int colunm = 0;
			int check = 1;
			while (header.hasNext()) {
				Cell cell = header.next();
				switch (colunm) {
				case 0:
					if (!cell.getStringCellValue().equals("ID"))
						check = 0;
					break;
				case 1:
					if (!cell.getStringCellValue().equals("Name"))
						check = 0;
					break;
				case 2:
					if (!cell.getStringCellValue().equals("Phone"))
						check = 0;
					break;
				case 3:
					if (!cell.getStringCellValue().equals("Status"))
						check = 0;
					break;
				}
				colunm++;
			}
			if (check == 0)
				return null;
			ArrayList<Customer> arr = new ArrayList<Customer>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();// Lấy Iterator cho tất cả các cell của dòng hiện tại.
				Iterator<Cell> cellIterator = row.cellIterator();
				Customer p = new Customer();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();// Set value for object
					switch (columnIndex) {
					case 0:
						p.setCus_id((String) cellValue);
						break;
					case 1:
						p.setCus_name((String) cellValue);
						break;
					case 2:
						p.setPhone((String) cellValue);
						break;
					case 3:
						p.setStatus((int) Double.parseDouble(cellValue.toString()));
						break;
					default:
						break;
					}
				}
				arr.add(p);
			}
			inputStream.close();
			return arr;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			System.out.println("Không tìm thấy file");
			JOptionPane.showMessageDialog(null,"Không tìm thấy file này!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// employer
	public void writeEmployer() {
		try {
			EmployerBUS bus = new EmployerBUS();
			HSSFSheet sheet;
			if (workbook.getNumberOfSheets() == 0)
				sheet = workbook.createSheet("Employer");
			else
				sheet = workbook.getSheet("Employer");

			List<Employer> list = bus.readAll();
			int rownum = 0;
			Cell cell;
			Row row;
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("ID");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Name");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Address");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Phone");
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Start_date");
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("Salary");
			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue("Shift");
			cell = row.createCell(7, CellType.STRING);
			cell.setCellValue("Emp_type");
			cell = row.createCell(8, CellType.STRING);
			cell.setCellValue("Username");
			cell = row.createCell(9, CellType.STRING);
			cell.setCellValue("Password");
			cell = row.createCell(10, CellType.STRING);
			cell.setCellValue("Status");

			for (Employer p : list) {
				rownum++;
				row = sheet.createRow(rownum);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(p.getEmp_id());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(p.getEmp_name());
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue(p.getAddress());
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue(p.getPhone());
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue(p.getStart_dateStr());
				cell = row.createCell(5, CellType.NUMERIC);
				cell.setCellValue(p.getSalary());
				cell = row.createCell(6, CellType.NUMERIC);
				cell.setCellValue(p.getShift());
				cell = row.createCell(7, CellType.BOOLEAN);
				cell.setCellValue(p.isEmp_type());
				cell = row.createCell(8, CellType.STRING);
				cell.setCellValue(p.getUsername());
				cell = row.createCell(9, CellType.STRING);
				cell.setCellValue(p.getPassword());
				cell = row.createCell(10, CellType.NUMERIC);
				cell.setCellValue(p.getStatus());

			}
			File file = new File("excel\\Employer.xls");
			if (file.exists() == false) {
				file.getParentFile().mkdirs();
			}
			this.autosizeColumn(sheet, 11);
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Employer> readEmployer(String linkpath) {
		if(!this.checkfile(linkpath)) return null;
		try {
//			FileInputStream inputStream = new FileInputStream(new File("excel\\Employer.xls"));
			FileInputStream inputStream = new FileInputStream(new File(linkpath));
			workbook = new HSSFWorkbook(inputStream);// Đối tượng workbook cho file XSL.
			HSSFSheet sheet = workbook.getSheet("Employer");// Lấy ra sheet từ workbook
			Iterator<Row> rowIterator = sheet.iterator();// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Row row = rowIterator.next();
			Iterator<Cell> header = row.cellIterator();
			int colunm = 0;
			int check = 1;
			while (header.hasNext()) {
				Cell cell = header.next();
				switch (colunm) {
				case 0:
					if (!cell.getStringCellValue().equals("ID"))
						check = 0;
					break;
				case 1:
					if (!cell.getStringCellValue().equals("Name"))
						check = 0;
					break;
				case 2:
					if (!cell.getStringCellValue().equals("Address"))
						check = 0;
					break;
				case 3:
					if (!cell.getStringCellValue().equals("Phone"))
						check = 0;
					break;
				case 4:
					if (!cell.getStringCellValue().equals("Start_date"))
						check = 0;
					break;
				case 5:
					if (!cell.getStringCellValue().equals("Salary"))
						check = 0;
					break;
				case 6:
					if (!cell.getStringCellValue().equals("Shift"))
						check = 0;
					break;
				case 7:
					if (!cell.getStringCellValue().equals("Emp_type"))
						check = 0;
					break;
				case 8:
					if (!cell.getStringCellValue().equals("Username"))
						check = 0;
					break;
				case 9:
					if (!cell.getStringCellValue().equals("Password"))
						check = 0;
					break;
				case 10:
					if (!cell.getStringCellValue().equals("Status"))
						check = 0;
					break;
				}
				colunm++;
			}
			if (check == 0)
				return null;
			ArrayList<Employer> arr = new ArrayList<Employer>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();// Lấy Iterator cho tất cả các cell của dòng hiện tại.
				Iterator<Cell> cellIterator = row.cellIterator();
				Employer p = new Employer();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();// Set value for object
					switch (columnIndex) {
					case 0:
						p.setEmp_id((String) cellValue);
						break;
					case 1:
						p.setEmp_name((String) cellValue);
						break;
					case 2:
						p.setAddress((String) cellValue);
						break;
					case 3:
						p.setPhone(cellValue.toString());
						break;
					case 4:
						p.setStart_date(cellValue.toString());
						break;
					case 5:
						p.setSalary(Double.parseDouble(cellValue.toString()));
						break;
					case 6:
						p.setShift((int) Double.parseDouble(cellValue.toString()));
						break;
					case 7:
						p.setEmp_type(Boolean.parseBoolean(cellValue.toString()));
						break;
					case 8:
						p.setUsername(cellValue.toString());
						break;
					case 9:
						p.setPassword(cellValue.toString());
						break;
					case 10:
						p.setStatus((int) Double.parseDouble(cellValue.toString()));
						break;
					default:
						break;
					}
				}
				arr.add(p);
			}
			inputStream.close();
			return arr;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Không tìm thấy file này!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// import
	public void writeImport() {
		try {
			ImportBUS bus = new ImportBUS();
			HSSFSheet sheet;
			if (workbook.getNumberOfSheets() == 0)
				sheet = workbook.createSheet("Import");
			else
				sheet = workbook.getSheet("Import");

			List<Import> list = bus.readAll();
			int rownum = 0;
			Cell cell;
			Row row;
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Imp_id");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Sup_id");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Emp_id");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Import_date");
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Status");

			for (Import p : list) {
				rownum++;
				row = sheet.createRow(rownum);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(p.getImp_id());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(p.getSup_id());
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue(p.getEmp_id());
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue(p.getImport_dateStr());
				cell = row.createCell(4, CellType.NUMERIC);
				cell.setCellValue(p.getStatus());

			}
			File file = new File("excel\\Import.xls");
			if (file.exists() == false) {
				file.getParentFile().mkdirs();
			}
			this.autosizeColumn(sheet, 5);
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Import> readImport(String linkpath) {
		if(!this.checkfile(linkpath)) return null;
		try {
//			FileInputStream inputStream = new FileInputStream(new File("excel\\Import.xls"));
			FileInputStream inputStream = new FileInputStream(new File(linkpath));
			workbook = new HSSFWorkbook(inputStream);// Đối tượng workbook cho file XSL.
			HSSFSheet sheet = workbook.getSheet("Import");// Lấy ra sheet từ workbook
			Iterator<Row> rowIterator = sheet.iterator();// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Row row = rowIterator.next();
			Iterator<Cell> header = row.cellIterator();
			int colunm = 0;
			int check = 1;
			while (header.hasNext()) {
				Cell cell = header.next();
				switch (colunm) {
				case 0:
					if (!cell.getStringCellValue().equals("Imp_id"))
						check = 0;
					break;
				case 1:
					if (!cell.getStringCellValue().equals("Sup_id"))
						check = 0;
					break;
				case 2:
					if (!cell.getStringCellValue().equals("Emp_id"))
						check = 0;
					break;
				case 3:
					if (!cell.getStringCellValue().equals("Import_date"))
						check = 0;
					break;
				case 4:
					if (!cell.getStringCellValue().equals("Status"))
						check = 0;
					break;
				}
				colunm++;
			}
			if (check == 0)
				return null;
			ArrayList<Import> arr = new ArrayList<Import>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();// Lấy Iterator cho tất cả các cell của dòng hiện tại.
				Iterator<Cell> cellIterator = row.cellIterator();
				Import p = new Import();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();// Set value for object
					switch (columnIndex) {
					case 0:
						p.setImp_id((String) cellValue);
						break;
					case 1:
						p.setSup_id((String) cellValue);
						break;
					case 2:
						p.setEmp_id((String) cellValue);
						break;
					case 3:
						p.setImport_date(cellValue.toString());
						break;
					case 4:
						p.setStatus((int) Double.parseDouble(cellValue.toString()));
						break;
					default:
						break;
					}
				}
				arr.add(p);
			}
			inputStream.close();
			return arr;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Không tìm thấy file này!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// importDetail
	public void writeImportDetail() {
		try {
			ImportDetailBUS bus = new ImportDetailBUS();
			HSSFSheet sheet;
			if (workbook.getNumberOfSheets() == 0)
				sheet = workbook.createSheet("ImportDetail");
			else
				sheet = workbook.getSheet("ImportDetail");

			List<ImportDetail> list = bus.readAll();
			int rownum = 0;
			Cell cell;
			Row row;
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Imp_id");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Prod_id");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Amount");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Unit_price");

			for (ImportDetail p : list) {
				rownum++;
				row = sheet.createRow(rownum);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(p.getImp_id());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(p.getProd_id());
				cell = row.createCell(2, CellType.NUMERIC);
				cell.setCellValue(p.getAmount());
				cell = row.createCell(3, CellType.NUMERIC);
				cell.setCellValue(p.getUnit_price());

			}
			File file = new File("excel\\ImportDetail.xls");
			if (file.exists() == false) {
				file.getParentFile().mkdirs();
			}
			this.autosizeColumn(sheet, 4);
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ImportDetail> readImportDetail(String linkpath) {
		if(!this.checkfile(linkpath)) return null;
		try {
//			FileInputStream inputStream = new FileInputStream(new File("excel\\ImportDetail.xls"));
			FileInputStream inputStream = new FileInputStream(new File(linkpath));
			workbook = new HSSFWorkbook(inputStream);// Đối tượng workbook cho file XSL.
			HSSFSheet sheet = workbook.getSheet("ImportDetail");// Lấy ra sheet từ workbook
			Iterator<Row> rowIterator = sheet.iterator();// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Row row = rowIterator.next();
			Iterator<Cell> header = row.cellIterator();
			int colunm = 0;
			int check = 1;
			while (header.hasNext()) {
				Cell cell = header.next();
				switch (colunm) {
				case 0:
					if (!cell.getStringCellValue().equals("Imp_id"))
						check = 0;
					break;
				case 1:
					if (!cell.getStringCellValue().equals("Prod_id"))
						check = 0;
					break;
				case 2:
					if (!cell.getStringCellValue().equals("Amount"))
						check = 0;
					break;
				case 3:
					if (!cell.getStringCellValue().equals("Unit_price"))
						check = 0;
					break;
				}
				colunm++;
			}
			if (check == 0)
				return null;
			ArrayList<ImportDetail> arr = new ArrayList<ImportDetail>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();// Lấy Iterator cho tất cả các cell của dòng hiện tại.
				Iterator<Cell> cellIterator = row.cellIterator();
				ImportDetail p = new ImportDetail();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();// Set value for object
					switch (columnIndex) {
					case 0:
						p.setImp_id((String) cellValue);
						break;
					case 1:
						p.setProd_id((String) cellValue);
						break;
					case 2:
						p.setAmount(Double.parseDouble(cellValue.toString()));
						break;
					case 3:
						p.setUnit_price(Double.parseDouble(cellValue.toString()));
						break;
					default:
						break;
					}
				}
				arr.add(p);
			}
			inputStream.close();
			return arr;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Không tìm thấy file này!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ingredient
	public void writeIngredient() {
		try {
			IngredientBUS bus = new IngredientBUS();
			HSSFSheet sheet;
			if (workbook.getNumberOfSheets() == 0)
				sheet = workbook.createSheet("Ingredient");
			else
				sheet = workbook.getSheet("Ingredient");

			List<Ingredient> list = bus.readAll();
			int rownum = 0;
			Cell cell;
			Row row;
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("ID");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Name");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Measure");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Amount");
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Unit_price");
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("Status");

			for (Ingredient p : list) {
				rownum++;
				row = sheet.createRow(rownum);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(p.getIng_id());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(p.getIng_name());
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue(p.getMeasure());
				cell = row.createCell(3, CellType.NUMERIC);
				cell.setCellValue(p.getAmount());
				cell = row.createCell(4, CellType.NUMERIC);
				cell.setCellValue(p.getUnit_price());
				cell = row.createCell(5, CellType.NUMERIC);
				cell.setCellValue(p.getStatus());

			}
			File file = new File("excel\\Ingredient.xls");
			if (file.exists() == false) {
				file.getParentFile().mkdirs();
			}
			this.autosizeColumn(sheet, 6);
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();JOptionPane.showMessageDialog(null,"Không tìm thấy file này!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Ingredient> readIngredient(String linkpath) {
		if(!this.checkfile(linkpath)) return null;
		try {
//			FileInputStream inputStream = new FileInputStream(new File("excel\\Ingredient.xls"));
			FileInputStream inputStream = new FileInputStream(new File(linkpath));
			workbook = new HSSFWorkbook(inputStream);// Đối tượng workbook cho file XSL.
			HSSFSheet sheet = workbook.getSheet("Ingredient");// Lấy ra sheet từ workbook
			Iterator<Row> rowIterator = sheet.iterator();// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Row row = rowIterator.next();
			Iterator<Cell> header = row.cellIterator();
			int colunm = 0;
			int check = 1;
			while (header.hasNext()) {
				Cell cell = header.next();
				switch (colunm) {
				case 0:
					if (!cell.getStringCellValue().equals("ID"))
						check = 0;
					break;
				case 1:
					if (!cell.getStringCellValue().equals("Name"))
						check = 0;
					break;
				case 2:
					if (!cell.getStringCellValue().equals("Measure"))
						check = 0;
					break;
				case 3:
					if (!cell.getStringCellValue().equals("Amount"))
						check = 0;
					break;
				case 4:
					if (!cell.getStringCellValue().equals("Unit_price"))
						check = 0;
					break;
				case 5:
					if (!cell.getStringCellValue().equals("Status"))
						check = 0;
					break;
				}
				colunm++;
			}
			if (check == 0)
				return null;
			ArrayList<Ingredient> arr = new ArrayList<Ingredient>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();// Lấy Iterator cho tất cả các cell của dòng hiện tại.
				Iterator<Cell> cellIterator = row.cellIterator();
				Ingredient p = new Ingredient();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();// Set value for object
					switch (columnIndex) {
					case 0:
						p.setIng_id((String) cellValue);
						break;
					case 1:
						p.setIng_name((String) cellValue);
						break;
					case 2:
						p.setMeasure((String) cellValue);
						break;
					case 3:
						p.setAmount(Double.parseDouble(cellValue.toString()));
						break;
					case 4:
						p.setUnit_price(Double.parseDouble(cellValue.toString()));
						break;
					case 5:
						p.setStatus((int) Double.parseDouble(cellValue.toString()));
						break;
					default:
						break;
					}
				}
				arr.add(p);
			}
			inputStream.close();
			return arr;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Không tìm thấy file này!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ProdSale
	public void writeProdSale() {
		try {
			ProdSaleBUS bus = new ProdSaleBUS();
			HSSFSheet sheet;
			if (workbook.getNumberOfSheets() == 0)
				sheet = workbook.createSheet("ProdSale");
			else
				sheet = workbook.getSheet("ProdSale");

			List<ProdSale> list = bus.readAll();
			int rownum = 0;
			Cell cell;
			Row row;
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Sale_id");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Prod_id");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Status");

			for (ProdSale p : list) {
				rownum++;
				row = sheet.createRow(rownum);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(p.getSale_id());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(p.getProd_id());
				cell = row.createCell(2, CellType.NUMERIC);
				cell.setCellValue(p.getStatus());

			}
			File file = new File("excel\\ProdSale.xls");
			if (file.exists() == false) {
				file.getParentFile().mkdirs();
			}
			this.autosizeColumn(sheet, 3);
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ProdSale> readProdSale(String linkpath) {
		if(!this.checkfile(linkpath)) return null;
		try {
//			FileInputStream inputStream = new FileInputStream(new File("excel\\ProdSale.xls"));
			FileInputStream inputStream = new FileInputStream(new File(linkpath));
			workbook = new HSSFWorkbook(inputStream);// Đối tượng workbook cho file XSL.
			HSSFSheet sheet = workbook.getSheet("ProdSale");// Lấy ra sheet từ workbook
			Iterator<Row> rowIterator = sheet.iterator();// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Row row = rowIterator.next();
			Iterator<Cell> header = row.cellIterator();
			int colunm = 0;
			int check = 1;
			while (header.hasNext()) {
				Cell cell = header.next();
				switch (colunm) {
				case 0:
					if (!cell.getStringCellValue().equals("Sale_id"))
						check = 0;
					break;
				case 1:
					if (!cell.getStringCellValue().equals("Prod_id"))
						check = 0;
					break;
				case 2:
					if (!cell.getStringCellValue().equals("Status"))
						check = 0;
					break;
				}
				colunm++;
			}
			if (check == 0)
				return null;
			ArrayList<ProdSale> arr = new ArrayList<ProdSale>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();// Lấy Iterator cho tất cả các cell của dòng hiện tại.
				Iterator<Cell> cellIterator = row.cellIterator();
				ProdSale p = new ProdSale();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();// Set value for object
					switch (columnIndex) {
					case 0:
						p.setSale_id((String) cellValue);
						break;
					case 1:
						p.setProd_id((String) cellValue);
						break;
					case 2:
						p.setStatus((int) Double.parseDouble(cellValue.toString()));
						break;
					default:
						break;
					}
				}
				arr.add(p);
			}
			inputStream.close();
			return arr;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Không tìm thấy file này!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// product
	public void writeProduct() {
		try {
			ProductBUS bus = new ProductBUS();
			HSSFSheet sheet;
			if (workbook.getNumberOfSheets() == 0)
				sheet = workbook.createSheet("Product");
			else
				sheet = workbook.getSheet("Product");
			List<Product> list = bus.readAll();
			int rownum = 0;
			Cell cell;
			Row row;
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("ID");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Name");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Unit_price");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Amount");
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Measure");
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("Status");
			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue("Description");
			cell = row.createCell(7, CellType.STRING);
			cell.setCellValue("Image");
			for (Product p : list) {
				rownum++;
				row = sheet.createRow(rownum);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(p.getProd_id());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(p.getProd_name());
				cell = row.createCell(2, CellType.NUMERIC);
				cell.setCellValue(p.getUnit_price());
				cell = row.createCell(3, CellType.NUMERIC);
				cell.setCellValue(p.getAmount());
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue(p.getMeasure());
				cell = row.createCell(5, CellType.NUMERIC);
				cell.setCellValue(p.getStatus());
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue(p.getDescription());
				cell = row.createCell(7, CellType.STRING);
				cell.setCellValue(p.getImg());
			}
			File file = new File("excel\\Product.xls");
			if (file.exists() == false) {
				file.getParentFile().mkdirs();
			}
			this.autosizeColumn(sheet, 8);
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Product> readProduct(String linkpath) {
		if(!this.checkfile(linkpath)) return null;
		try {
//			FileInputStream inputStream = new FileInputStream(new File("excel\\Product.xls"));
			FileInputStream inputStream = new FileInputStream(new File(linkpath));
			workbook = new HSSFWorkbook(inputStream);// Đối tượng workbook cho file XSL.
			HSSFSheet sheet = workbook.getSheet("Product");// Lấy ra sheet từ workbook
			Iterator<Row> rowIterator = sheet.iterator();// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Row row = rowIterator.next();
			Iterator<Cell> header = row.cellIterator();
			int colunm = 0;
			int check = 1;
			while (header.hasNext()) {
				Cell cell = header.next();
				switch (colunm) {
				case 0:
					if (!cell.getStringCellValue().equals("ID"))
						check = 0;
					break;
				case 1:
					if (!cell.getStringCellValue().equals("Name"))
						check = 0;
					break;
				case 2:
					if (!cell.getStringCellValue().equals("Unit_price"))
						check = 0;
					break;
				case 3:
					if (!cell.getStringCellValue().equals("Amount"))
						check = 0;
					break;
				case 4:
					if (!cell.getStringCellValue().equals("Measure"))
						check = 0;
					break;
				case 5:
					if (!cell.getStringCellValue().equals("Status"))
						check = 0;
					break;
				case 6:
					if (!cell.getStringCellValue().equals("Description"))
						check = 0;
					break;
				case 7:
					if (!cell.getStringCellValue().equals("Image"))
						check = 0;
					break;
				}
				colunm++;
			}
			if (check == 0)
				return null;
			ArrayList<Product> arr = new ArrayList<Product>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();// Lấy Iterator cho tất cả các cell của dòng hiện tại.
				Iterator<Cell> cellIterator = row.cellIterator();
				Product p = new Product();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();// Set value for object
					switch (columnIndex) {
					case 0:
						p.setProd_id((String) cellValue);
						break;
					case 1:
						p.setProd_name((String) cellValue);
						break;
					case 2:
						p.setUnit_price(Double.parseDouble(cellValue.toString()));
						break;
					case 3:
						p.setAmount((int) Double.parseDouble(cellValue.toString()));
						break;
					case 4:
						p.setMeasure((String) cellValue);
						break;
					case 5:
						p.setStatus((int) Double.parseDouble(cellValue.toString()));
						break;
					case 6:
						p.setDescription((String) cellValue);
						break;
					case 7:
						p.setImg((String) cellValue);
						break;
					default:
						break;
					}
				}
				arr.add(p);
			}
			inputStream.close();
			return arr;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Không tìm thấy file này!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// productType
	public void writeProductType() {
		try {
			ProductTypeBUS bus = new ProductTypeBUS();
			HSSFSheet sheet;
			if (workbook.getNumberOfSheets() == 0)
				sheet = workbook.createSheet("ProductType");
			else
				sheet = workbook.getSheet("ProductType");
			List<ProductType> list = bus.readAll();
			int rownum = 0;
			Cell cell;
			Row row;
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("ID");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Name");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Status");

			for (ProductType p : list) {
				rownum++;
				row = sheet.createRow(rownum);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(p.getType_id());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(p.getType_name());
				cell = row.createCell(2, CellType.NUMERIC);
				cell.setCellValue(p.getStatus());
			}
			File file = new File("excel\\ProductType.xls");
			if (file.exists() == false) {
				file.getParentFile().mkdirs();
			}
			this.autosizeColumn(sheet, 3);
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ProductType> readProductType(String linkpath) {
		if(!this.checkfile(linkpath)) return null;
		try {
//			FileInputStream inputStream = new FileInputStream(new File("excel\\ProductType.xls"));
			FileInputStream inputStream = new FileInputStream(new File(linkpath));
			workbook = new HSSFWorkbook(inputStream);// Đối tượng workbook cho file XSL.
			HSSFSheet sheet = workbook.getSheet("ProductType");// Lấy ra sheet từ workbook
			Iterator<Row> rowIterator = sheet.iterator();// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Row row = rowIterator.next();
			Iterator<Cell> header = row.cellIterator();
			int colunm = 0;
			int check = 1;
			while (header.hasNext()) {
				Cell cell = header.next();
				switch (colunm) {
				case 0:
					if (!cell.getStringCellValue().equals("ID"))
						check = 0;
					break;
				case 1:
					if (!cell.getStringCellValue().equals("Name"))
						check = 0;
					break;
				case 2:
					if (!cell.getStringCellValue().equals("Status"))
						check = 0;
					break;
				}
				colunm++;
			}
			if (check == 0)
				return null;
			ArrayList<ProductType> arr = new ArrayList<ProductType>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();// Lấy Iterator cho tất cả các cell của dòng hiện tại.
				Iterator<Cell> cellIterator = row.cellIterator();
				ProductType p = new ProductType();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();// Set value for object
					switch (columnIndex) {
					case 0:
						p.setType_id((String) cellValue);
						break;
					case 1:
						p.setType_name((String) cellValue);
						break;
					case 2:
						p.setStatus((int) Double.parseDouble(cellValue.toString()));
						break;
					default:
						break;
					}
				}
				arr.add(p);
			}
			inputStream.close();
			return arr;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Không tìm thấy file này!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// recipe
	public void writeRecipe() {
		try {
			RecipeBUS bus = new RecipeBUS();
			HSSFSheet sheet;
			if (workbook.getNumberOfSheets() == 0)
				sheet = workbook.createSheet("Recipe");
			else
				sheet = workbook.getSheet("Recipe");
			List<Recipe> list = bus.readAll();
			int rownum = 0;
			Cell cell;
			Row row;
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Prod_id");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Ing_id");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Amount");
			for (Recipe p : list) {
				rownum++;
				row = sheet.createRow(rownum);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(p.getProd_id());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(p.getIng_id());
				cell = row.createCell(2, CellType.NUMERIC);
				cell.setCellValue(p.getAmount());
			}
			File file = new File("excel\\Recipe.xls");
			if (file.exists() == false) {
				file.getParentFile().mkdirs();
			}
			this.autosizeColumn(sheet, 3);
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Recipe> readRecipe(String linkpath) {
		if(!this.checkfile(linkpath)) return null;
		try {
//			FileInputStream inputStream = new FileInputStream(new File("excel\\Recipe.xls"));
			FileInputStream inputStream = new FileInputStream(new File(linkpath));
			workbook = new HSSFWorkbook(inputStream);// Đối tượng workbook cho file XSL.
			HSSFSheet sheet = workbook.getSheet("Recipe");// Lấy ra sheet từ workbook
			Iterator<Row> rowIterator = sheet.iterator();// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Row row = rowIterator.next();
			Iterator<Cell> header = row.cellIterator();
			int colunm = 0;
			int check = 1;
			while (header.hasNext()) {
				Cell cell = header.next();
				switch (colunm) {
				case 0:
					if (!cell.getStringCellValue().equals("Prod_id"))
						check = 0;
					break;
				case 1:
					if (!cell.getStringCellValue().equals("Ing_id"))
						check = 0;
					break;
				case 2:
					if (!cell.getStringCellValue().equals("Amount"))
						check = 0;
					break;
				}
				colunm++;
			}
			if (check == 0)
				return null;
			ArrayList<Recipe> arr = new ArrayList<Recipe>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();// Lấy Iterator cho tất cả các cell của dòng hiện tại.
				Iterator<Cell> cellIterator = row.cellIterator();
				Recipe p = new Recipe();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();// Set value for object
					switch (columnIndex) {
					case 0:
						p.setProd_id((String) cellValue);
						break;
					case 1:
						p.setIng_id((String) cellValue);
						break;
					case 2:
						p.setAmount(Double.parseDouble(cellValue.toString()));
						break;
					default:
						break;
					}
				}
				arr.add(p);
			}
			inputStream.close();
			return arr;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Không tìm thấy file này!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Sale
	public void writeSale() {
		try {
			SaleBUS bus = new SaleBUS();
			HSSFSheet sheet;
			if (workbook.getNumberOfSheets() == 0)
				sheet = workbook.createSheet("Sale");
			else
				sheet = workbook.getSheet("Sale");
			List<Sale> list = bus.readAll();
			int rownum = 0;
			Cell cell;
			Row row;
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Sale_id");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Sale_name");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Number");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Measure");
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Start_date");
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("End_date");
			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue("Threshold");
			cell = row.createCell(7, CellType.STRING);
			cell.setCellValue("Threshold_unit");
			cell = row.createCell(8, CellType.STRING);
			cell.setCellValue("Status");
			for (Sale p : list) {
				rownum++;
				row = sheet.createRow(rownum);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(p.getSale_id());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(p.getSale_name());
				cell = row.createCell(2, CellType.NUMERIC);
				cell.setCellValue(p.getNumber());
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue(p.getMeasure());
				cell = row.createCell(4, CellType.NUMERIC);
				cell.setCellValue(p.getStart_dateStr());
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue(p.getEnd_dateStr());
				cell = row.createCell(6, CellType.NUMERIC);
				cell.setCellValue(p.getThreshold());
				cell = row.createCell(7, CellType.STRING);
				cell.setCellValue(p.getThreshold_unit());
				cell = row.createCell(8, CellType.NUMERIC);
				cell.setCellValue(p.getStatus());
			}
			File file = new File("excel\\Sale.xls");
			if (file.exists() == false) {
				file.getParentFile().mkdirs();
			}
			this.autosizeColumn(sheet, 9);
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Sale> readSale(String linkpath) {
		if(!this.checkfile(linkpath)) return null;
		try {
//			FileInputStream inputStream = new FileInputStream(new File("excel\\Sale.xls"));
			FileInputStream inputStream = new FileInputStream(new File(linkpath));
			workbook = new HSSFWorkbook(inputStream);// Đối tượng workbook cho file XSL.
			HSSFSheet sheet = workbook.getSheet("Sale");// Lấy ra sheet từ workbook
			Iterator<Row> rowIterator = sheet.iterator();// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Row row = rowIterator.next();
			Iterator<Cell> header = row.cellIterator();
			int colunm = 0;
			int check = 1;
			while (header.hasNext()) {
				Cell cell = header.next();
				switch (colunm) {
				case 0:
					if (!cell.getStringCellValue().equals("Sale_id"))
						check = 0;
					break;
				case 1:
					if (!cell.getStringCellValue().equals("Sale_name"))
						check = 0;
					break;
				case 2:
					if (!cell.getStringCellValue().equals("Number"))
						check = 0;
					break;
				case 3:
					if (!cell.getStringCellValue().equals("Measure"))
						check = 0;
					break;
				case 4:
					if (!cell.getStringCellValue().equals("Start_date"))
						check = 0;
					break;
				case 5:
					if (!cell.getStringCellValue().equals("End_date"))
						check = 0;
					break;
				case 6:
					if (!cell.getStringCellValue().equals("Threshold"))
						check = 0;
					break;
				case 7:
					if (!cell.getStringCellValue().equals("Threshold_unit"))
						check = 0;
					break;
				case 8:
					if (!cell.getStringCellValue().equals("Status"))
						check = 0;
					break;
				}
				colunm++;
			}
			if (check == 0)
				return null;
			ArrayList<Sale> arr = new ArrayList<Sale>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();// Lấy Iterator cho tất cả các cell của dòng hiện tại.
				Iterator<Cell> cellIterator = row.cellIterator();
				Sale p = new Sale();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();// Set value for object
					switch (columnIndex) {
					case 0:
						p.setSale_id((String) cellValue);
						break;
					case 1:
						p.setSale_name((String) cellValue);
						break;
					case 2:
						p.setNumber(Double.parseDouble(cellValue.toString()));
						break;
					case 3:
						p.setMeasure(cellValue.toString());
						break;
					case 4:
						p.setStart_date(cellValue.toString());
						break;
					case 5:
						p.setEnd_date(cellValue.toString());
						break;
					case 6:
						p.setThreshold(Double.parseDouble(cellValue.toString()));
						break;
					case 7:
						p.setThreshold_unit(cellValue.toString());
						break;
					case 8:
						p.setStatus((int) Double.parseDouble(cellValue.toString()));
						break;
					default:
						break;
					}
				}
				arr.add(p);
			}
			inputStream.close();
			return arr;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Không tìm thấy file này!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// supplier
	public void writeSupplier() {
		try {
			SupplierBUS bus = new SupplierBUS();
			HSSFSheet sheet;
			if (workbook.getNumberOfSheets() == 0)
				sheet = workbook.createSheet("Supplier");
			else
				sheet = workbook.getSheet("Supplier");
			List<Supplier> list = bus.readAll();
			int rownum = 0;
			Cell cell;
			Row row;
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("ID");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Name");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Address");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Phone");
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Status");
			for (Supplier sup : list) {
				rownum++;
				row = sheet.createRow(rownum);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(sup.getSup_id());
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(sup.getSup_name());
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue(sup.getAddress());
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue(sup.getPhone());
				cell = row.createCell(4, CellType.NUMERIC);
				cell.setCellValue(sup.getStatus());
			}
			File file = new File("excel\\Supplier.xls");
			if (file.exists() == false) {
				file.getParentFile().mkdirs();
			}
			this.autosizeColumn(sheet, 5);
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Supplier> readSupplier(String linkpath) {
		if(!this.checkfile(linkpath)) return null;
		try {
//			FileInputStream inputStream = new FileInputStream(new File("excel\\Supplier.xls"));
			FileInputStream inputStream = new FileInputStream(new File(linkpath));
			workbook = new HSSFWorkbook(inputStream);// Đối tượng workbook cho file XSL.
			HSSFSheet sheet = workbook.getSheet("Supplier");// Lấy ra sheet từ workbook
			Iterator<Row> rowIterator = sheet.iterator();// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Row row = rowIterator.next();
			Iterator<Cell> header = row.cellIterator();
			int colunm = 0;
			int check = 1;
			while (header.hasNext()) {
				Cell cell = header.next();
				switch (colunm) {
				case 0:
					if (!cell.getStringCellValue().equals("ID"))
						check = 0;
					break;
				case 1:
					if (!cell.getStringCellValue().equals("Name"))
						check = 0;
					break;
				case 2:
					if (!cell.getStringCellValue().equals("Address"))
						check = 0;
					break;
				case 3:
					if (!cell.getStringCellValue().equals("Phone"))
						check = 0;
					break;
				case 4:
					if (!cell.getStringCellValue().equals("Status"))
						check = 0;
					break;
				}
				colunm++;
			}
			if (check == 0)
				return null;
			ArrayList<Supplier> arr = new ArrayList<Supplier>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();// Lấy Iterator cho tất cả các cell của dòng hiện tại.
				Iterator<Cell> cellIterator = row.cellIterator();
				Supplier p = new Supplier();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();// Set value for object
					switch (columnIndex) {
					case 0:
						p.setSup_id((String) cellValue);
						break;
					case 1:
						p.setSup_name((String) cellValue);
						break;
					case 2:
						p.setAddress((String) cellValue);
						break;
					case 3:
						p.setPhone((String) cellValue);
						break;
					case 4:
						p.setStatus((int) Double.parseDouble(cellValue.toString()));
						break;
					default:
						break;
					}
				}
				arr.add(p);
			}
			inputStream.close();
			return arr;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Không tìm thấy file này!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
