package gui;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
//import com.itextpdf.io.font.FontConstants; 
//import com.itextpdf.kernel.color.Color; 
//import com.itextpdf.kernel.font.PdfFontFactory; 
//import com.itextpdf.kernel.font.PdfFont; 
//import com.itextpdf.kernel.pdf.PdfDocument; 
//import com.itextpdf.kernel.pdf.PdfWriter; 
//import com.itextpdf.text.pdf.fonts.*;
import java.io.File;
//import com.itextpdf.layout.Document; 
//import com.itextpdf.layout.element.Paragraph; 
//import com.itextpdf.layout.element.Text;  
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.b
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Element;
import com.itextpdf.text.BaseColor;
import bus.*;
import dto.*;
//import java.awt.Font;
import java.awt.Color;

public class PDFreport {
//	Font blueFont = FontFactory.getFont("calibri",14 , Font.NORMAL, new CMYKColor(100, 100, 100, 100));
	File fontFile = new File("font/vuArial.ttf");

	public void ImportReport(String imp_id) {
		Document document = new Document();
		try {
			System.setProperty("file.encoding", "UTF-8");
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("C:\\Users\\ASUS\\Desktop\\ReportImport.pdf"));
//			InputStream is = new ByteArrayInputStream(hovinko.getBytes(Charset.forName("UTF-8")));
//			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, Charset.forName("UTF-8"));
			document.open();
			this.setAttribute(document);
			this.headerImport(document, imp_id);
			this.contentImport(document, imp_id);
			document.close();
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setAttribute(Document document) {
		document.addAuthor("CoffeeHousedb");
		document.addCreationDate();
		document.addCreator("CoffeeHousedb");
		document.addTitle("PDF report");
		document.addSubject("PDF report.");
	}

	public void headerImport(Document document, String imp_id) {
		try {
			BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 14);

			ImportBUS bus = new ImportBUS();
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD,
					new CMYKColor(255, 255, 255, 255));
			Paragraph par = new Paragraph("Coffee House", headerFont);
			par.setAlignment(1);
			document.add(par);

			Import imp = bus.searchByID(imp_id);
			par = new Paragraph("Date: " + imp.getImport_dateStr());
			par.setAlignment(1);
			document.add(par);

			par = new Paragraph("Mã phiếu nhập: " + imp.getImp_id(), font);
			document.add(par);

			EmployerBUS empBus = new EmployerBUS();
			par = new Paragraph(
					"Mã nhân viên: " + imp.getEmp_id() + "-" + empBus.searchByID(imp.getEmp_id()).getEmp_name(), font);
			document.add(par);

			SupplierBUS supBus = new SupplierBUS();
			par = new Paragraph(
					"Mã nhà cung cấp: " + imp.getSup_id() + "-" + supBus.searchByID(imp.getSup_id()).getSup_name(),
					font);
			document.add(par);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void contentImport(Document document, String imp_id) {
		try {
			BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13);

			Paragraph par = new Paragraph("Mô tả:", font);
			document.add(par);

			PdfPTable table = new PdfPTable(4); // 3 columns.

			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table

			PdfPCell cell1 = new PdfPCell(new Paragraph("Tên sản phẩm", font));
			cell1.setBorderColor(BaseColor.BLACK);
			cell1.setPaddingLeft(10);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell2 = new PdfPCell(new Paragraph("Số lượng", font));
			cell2.setBorderColor(BaseColor.BLACK);
			cell2.setPaddingLeft(10);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell3 = new PdfPCell(new Paragraph("Đơn giá", font));
			cell3.setBorderColor(BaseColor.BLACK);
			cell3.setPaddingLeft(10);
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell4 = new PdfPCell(new Paragraph("Thành tiền", font));
			cell4.setBorderColor(BaseColor.BLACK);
			cell4.setPaddingLeft(10);
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			PdfPCell cell = new PdfPCell();
			cell.setBorderColor(BaseColor.BLACK);
			cell.setPaddingLeft(10);
//			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			ProductBUS buspro = new ProductBUS();
			IngredientBUS busing = new IngredientBUS();
			ImportDetailBUS busdetail = new ImportDetailBUS();
			ArrayList<ImportDetail> arr = busdetail.searchByImportID(imp_id);
//			System.out.println(arr.size());
			double sum = 0;
			for (ImportDetail p : arr) {
				String bb = p.getProd_id().substring(0, 2);
				if (bb.equals("IN")) {
					Ingredient ing = busing.searchByID(p.getProd_id());
					cell = new PdfPCell(new Paragraph(ing.getIng_name() + "", font));
				} else {
					Product pro = buspro.searchByID(p.getProd_id());
					cell = new PdfPCell(new Paragraph(pro.getProd_name() + "", font));
				}
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(p.getAmount() + "", font));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(p.getUnit_price() + "", font));
				table.addCell(cell);
				double a = p.getUnit_price() * p.getAmount();
				sum += a;
				cell = new PdfPCell(new Paragraph(a + "", font));
				table.addCell(cell);
			}
			par = new Paragraph("Tông tiền: " + sum + " VND", font);
			document.add(table);
			document.add(par);

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// bill

	public void BillReport(String bill_id) {
		Document document = new Document();
		try {
			System.setProperty("file.encoding", "UTF-8");
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("C:\\Users\\ASUS\\Desktop\\ReportBill.pdf"));
//			InputStream is = new ByteArrayInputStream(hovinko.getBytes(Charset.forName("UTF-8")));
//			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, Charset.forName("UTF-8"));
			document.open();
			this.setAttribute(document);
			this.headerBill(document, bill_id);
			this.contentBill(document, bill_id);
			document.close();
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void headerBill(Document document, String bill_id) {
		try {
			BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 14);

			BillBUS busbill = new BillBUS();
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD,
					new CMYKColor(255, 255, 255, 255));
			Paragraph par = new Paragraph("Coffee House", headerFont);
			par.setAlignment(1);
			document.add(par);

			Bill bill = busbill.searchByID(bill_id);
			par = new Paragraph("Date: " + bill.getDateStr());
			par.setAlignment(1);
			document.add(par);

			par = new Paragraph("Mã hóa đơn: " + bill.getBill_id(), font);
			document.add(par);

			EmployerBUS empBus = new EmployerBUS();
			par = new Paragraph(
					"Mã nhân viên: " + bill.getEmp_id() + "-" + empBus.searchByID(bill.getEmp_id()).getEmp_name(),
					font);
			document.add(par);

			CustomerBUS cusBus = new CustomerBUS();
			par = new Paragraph(
					"Mã khách hàng: " + bill.getCus_id() + "-" + cusBus.searchByID(bill.getCus_id()).getCus_name(),
					font);
			document.add(par);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void contentBill(Document document, String bill_id) {
		try {
			BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13);
			BillBUS busbill = new BillBUS();
			Bill temp = busbill.searchByID(bill_id);
			SaleBUS salebus = new SaleBUS();
			Sale sale = salebus.searchByID(temp.getSale_id());
			Paragraph  par ;
			if(sale != null ) {
				par = new Paragraph("Tên khuyến mãi: " + sale.getSale_name(), font);
				document.add(par);
			}
			par = new Paragraph("Mô tả:", font);
			document.add(par);

			PdfPTable table = new PdfPTable(5); // 3 columns.

			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table

			PdfPCell cell1 = new PdfPCell(new Paragraph("Tên sản phẩm", font));
			cell1.setBorderColor(BaseColor.BLACK);
			cell1.setPaddingLeft(10);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell2 = new PdfPCell(new Paragraph("Số lượng", font));
			cell2.setBorderColor(BaseColor.BLACK);
			cell2.setPaddingLeft(10);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell3 = new PdfPCell(new Paragraph("Đơn giá", font));
			cell3.setBorderColor(BaseColor.BLACK);
			cell3.setPaddingLeft(10);
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			
			PdfPCell cell4 = new PdfPCell(new Paragraph("Giá sale", font));
			cell4.setBorderColor(BaseColor.BLACK);
			cell4.setPaddingLeft(10);
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			

			PdfPCell cell5 = new PdfPCell(new Paragraph("Thành tiền", font));
			cell5.setBorderColor(BaseColor.BLACK);
			cell5.setPaddingLeft(10);
			cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			PdfPCell cell = new PdfPCell();
			cell.setBorderColor(BaseColor.BLACK);
			cell.setPaddingLeft(10);
//			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			ProductBUS buspro = new ProductBUS();
			IngredientBUS busing = new IngredientBUS();
			BillDetailBUS busdetail = new BillDetailBUS();
			ArrayList<BillDetail> arr = busdetail.searchByBillID(bill_id);
//			System.out.println(arr.size());
			double sum = 0;
			for (BillDetail p : arr) {
				Product pro = buspro.searchByID(p.getProd_id());
				cell = new PdfPCell(new Paragraph(pro.getProd_name() + "", font));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(p.getAmount() + "", font));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(p.getUnit_price() + "", font));
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(p.getSale_unit_price() + "", font));
				table.addCell(cell);
				double a = p.getSale_unit_price() * p.getAmount();
				sum += a;
				cell = new PdfPCell(new Paragraph(a + "", font));
				table.addCell(cell);
			}
			document.add(table);
			
			par = new Paragraph("Tông tiền: " + sum + " VND", font);
			document.add(par);
			
			if (sum > temp.getSale_total()) {		
				par = new Paragraph("Giá khuyến mãi: " + temp.getSale_total()+" VND", font);
				document.add(par);
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
