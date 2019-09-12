package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.border.EmptyBorder;

import bus.*;
import dto.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
public class RecordTable extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTable table;
	
	private String[] header;
	private boolean authority;
	private EmployerBUS empBus = new EmployerBUS();
	private SaleBUS saleBus = new SaleBUS();
	private CustomerBUS cusBus = new CustomerBUS();
	private IngredientBUS ingBus = new IngredientBUS();
	private ProductBUS prodBus = new ProductBUS();
	private SupplierBUS supBus = new SupplierBUS();
	
	private ArrayList<Sale> saleArr;
	private ArrayList<Ingredient> ingArr;
	private ArrayList<Product> prodArr;
	private ArrayList<Employer> empArr;
	private ArrayList<Customer> cusArr;
	private ArrayList<Supplier> supArr;
	private JPanel panel_2;
	private JTextField txSearch;
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnTn;
	private JPanel panel_3;
	private JButton btnApply;
	private JComboBox comboType;
	private JComboBox comboOrder;
	private ButtonGroup bg = new ButtonGroup();
	private boolean confirm = false;
	private double bill_total=0;
	private int bill_amount=0;
	private JDateChooser txFrom;
	private JDateChooser txTo;
	private JLabel lblNewLabel;
	private JLabel lbln;
	private String type;
	private String prod_id="";
	private JLabel lblGiT;
	private JLabel label_1;
	private JTextField txMin;
	private JTextField txMax;
	private JLabel lblNewLabel_1;
	private JComboBox comboProdType;
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			RecordTable dialog = new RecordTable();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RecordTable(String id, String type) {
		Employer emp = empBus.searchByID(id);
		this.authority = emp.isEmp_type();
		this.type = type;
		
		setTitle("Chọn");
		setBounds(100, 100, 803, 425);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 147, 757, 185);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Tìm kiếm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 11, 591, 89);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		txSearch = new JTextField();
		txSearch.setBounds(10, 20, 177, 30);
		panel_2.add(txSearch);
		txSearch.setColumns(10);
		
		rdbtnM = new JRadioButton("Mã"); rdbtnM.setSelected(true);
		rdbtnM.setBounds(30, 57, 59, 23);
		
		
		rdbtnTn = new JRadioButton("Tên");
		rdbtnTn.setBounds(115, 57, 59, 23);
		
		
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Sắp xếp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(611, 11, 156, 89);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		comboType = new JComboBox(this.getComboBox(type));
		comboType.setBounds(10, 15, 135, 26);
		panel_3.add(comboType);
		
		String[] order = {"Tăng dần","Giảm dần"};
		comboOrder = new JComboBox(order);
		comboOrder.setBounds(10, 52, 135, 26);
		panel_3.add(comboOrder);
		
		btnApply = new JButton("ÁP DỤNG");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchAndSort(type);
			}
		});
		btnApply.setBounds(339, 111, 139, 30);
		panel.add(btnApply);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("CHỌN");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(!table.getSelectionModel().isSelectionEmpty()) {
							confirm = true; dispose();
						}else JOptionPane.showMessageDialog(null, "Không có dòng nào được chọn!");
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("HỦY");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						confirm = false; dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		bg.add(rdbtnM); bg.add(rdbtnTn);
 
 txFrom = new JDateChooser();
 txFrom.setDateFormatString("dd/MM/yyyy");
 txFrom.setEnabled(false);
 txFrom.setBounds(441, 11, 140, 30);
 panel_2.add(txFrom);
 
 txTo = new JDateChooser();
 txTo.setDateFormatString("dd/MM/yyyy");
 txTo.setEnabled(false);
 txTo.setBounds(441, 50, 140, 30);
 panel_2.add(txTo);
 
 lblNewLabel = new JLabel("Từ:");
 lblNewLabel.setBounds(401, 11, 40, 28);
 panel_2.add(lblNewLabel);
 
 lbln = new JLabel("Đến:");
 lbln.setBounds(401, 50, 40, 28);
 panel_2.add(lbln);
 
 lblGiT = new JLabel("Giá từ:");
 lblGiT.setBounds(219, 11, 47, 28);
 panel_2.add(lblGiT);
 
 label_1 = new JLabel("Đến:");
 label_1.setBounds(219, 50, 47, 28);
 panel_2.add(label_1);
 
 txMin = new JTextField();
 txMin.setEditable(false);
 txMin.setText("0.0");
 txMin.setBounds(267, 11, 111, 30);
 panel_2.add(txMin);
 txMin.setColumns(10);
 
 txMax = new JTextField();
 txMax.setEditable(false);
 txMax.setText("0.0");
 txMax.setColumns(10);
 txMax.setBounds(267, 50, 113, 30);
 panel_2.add(txMax);
 
 lblNewLabel_1 = new JLabel("Phân loại:");
 lblNewLabel_1.setBounds(10, 57, 66, 23);
 //panel_2.add(lblNewLabel_1);
 
 String[] prodtype = {"Tất cả", "Cà phê", "Sinh Tố", "Nước ngọt có ga", "Rượu", "Bia", "Trà" };
 comboProdType = new JComboBox(prodtype);
 comboProdType.setBounds(74, 53, 113, 30);
 //panel_2.add(comboProdType);
		
 
 		if(type.equals("sale") || type.equals("prod_sale") || type.equals("bill_sale")) {
 			txFrom.setEnabled(true);
 			txTo.setEnabled(true);
 		}
 		if(type.equals("prod") || type.equals("ing") || type.equals("prod_imp")) {
 			txMax.setEditable(true);
 			txMin.setEditable(true);
 		}
 		if(type.equals("prod") || type.equals("prod_imp")) {
 			panel_2.add(lblNewLabel_1);
 			panel_2.add(comboProdType);
 		}
 		else {
 			panel_2.add(rdbtnM);
 			panel_2.add(rdbtnTn);
 		}
		this.getData(type);
		this.loadData(type);
	}
	private void loadData(String type) {
		switch(type) {
		
		case "prod": {
			this.getHeader(type);
			DefaultTableModel md = new DefaultTableModel(header, 0);
			for (Product p : prodArr) {
				Object[] row;
				row = new Object[]{ p.getProd_id(), p.getProd_name(),p.getUnit_price(), p.getAmount(), p.getMeasure(),p.getDescription()};
				md.addRow(row);
			}
			table.setModel(md);
			this.setColSize(type);
			break;
		}
		case "prod_imp": {
			this.getHeader(type);
			DefaultTableModel md = new DefaultTableModel(header, 0);
			for (Product p : prodArr) {
				if(!prodBus.hasRep(p.getProd_id())) {
					Object[] row;
					row = new Object[]{ p.getProd_id(), p.getProd_name(),p.getUnit_price(), p.getAmount(), p.getMeasure(),p.getDescription()};
					md.addRow(row);
				}
			}
			table.setModel(md);
			this.setColSize(type);
			break;
		}
		case "sale": case "prod_sale": case "bill_sale":{
			this.getHeader(type);
			DefaultTableModel md = new DefaultTableModel(header, 0);
			for (Sale p : saleArr) {
				Object[] row;
				row = new Object[]{ p.getSale_id(), p.getSale_name(),p.getNumber(), p.getMeasure(), p.getThreshold(), p.getThreshold_unit(),
						p.getStart_dateStr(), p.getEnd_dateStr() };
				md.addRow(row);
			}
			table.setModel(md);
			this.setColSize(type);
			break;
		}
		case "ing": {
			this.getHeader(type);
			DefaultTableModel md = new DefaultTableModel(header, 0);
			for (Ingredient p : ingArr) {
				Object[] row;
				row = new Object[]{ p.getIng_id(), p.getIng_name(),p.getAmount(), p.getMeasure()};
				md.addRow(row);
			}
			table.setModel(md);
			this.setColSize(type);
			break;
		}
		case "emp": {
			this.getHeader(type);
			DefaultTableModel md = new DefaultTableModel(header, 0);
			for (Employer p : empArr) {
				Object[] row;
				row = new Object[]{ p.getEmp_id(), p.getEmp_name(),p.isEmp_type()?"Admin":"Nhân viên",p.getShift()};
				md.addRow(row);
			}
			table.setModel(md);
			this.setColSize(type);
			break;
		}
		case "sup": {
			this.getHeader(type);
			DefaultTableModel md = new DefaultTableModel(header, 0);
			for (Supplier p : supArr) {
				Object[] row;
				row = new Object[]{ p.getSup_id(), p.getSup_name(), p.getAddress(), p .getPhone()};
				md.addRow(row);
			}
			table.setModel(md);
			this.setColSize(type);
			break;
		}
		default: {
			this.getHeader(type);
			DefaultTableModel md = new DefaultTableModel(header, 0);
			for (Customer p : cusArr) {
				Object[] row;
				row = new Object[]{ p.getCus_id(), p.getCus_name(),p.getPhone()};
				md.addRow(row);
			}
			table.setModel(md);
			this.setColSize(type);
			break;
		}
		}
	}
	private void getData(String type) {
		switch(type) {
		case "sale": saleArr= saleBus.getSaleForProd(saleBus.read());
		case "prod_sale": saleArr = saleBus.getSaleForProdbyProdSale(prod_id); break;
		case "bill_sale": saleArr = saleBus.getSaleForBillbyConditions(bill_total, bill_amount); break;
		case "prod": case "prod_imp": prodArr = prodBus.read(); break;
		case "ing": ingArr = ingBus.read(); break;
		case "emp": empArr = empBus.read(); break;
		case "sup": supArr = supBus.read(); break;
		default: cusArr = cusBus.read(); break;
		}
	}
	public void getProdData(String prod_id) {
		this.prod_id = prod_id;
		this.getData(type);
		this.loadData(type);
	}
	
	public void getBillData(double total, int amount) {
		this.bill_amount = amount;
		this.bill_total = total;
		this.getData(type);
		this.loadData(type);
	}
	private String[] getComboBox(String type) {
		switch(type) {
		
		case "prod": case "prod_imp": {
			return new String[] {"Mã","Tên","Đơn giá"};
		}
		case "sale": case "prod_sale": case "bill_sale": {
			return new String[] {"Mã KM","Tên KM","Ngày bắt đầu","Ngày kết thúc"};
		}
		case "ing": {
			return new String[] {"Mã", "Tên", "Giá trị", "Đơn vị"};
		}
		case "emp": {
			return new String[] {"Mã","Tên","Ca","Lương","Ngày vào làm"};
		}
		case "sup": {
			return new String[] {"Mã ", "Tên", "Địa chỉ"};
		}
		default: {
			return new String[] {"Mã ", "Họ và Tên"};
		}
		}
	}
	private void searchAndSort(String type) {
		switch(type) {
		
		case "prod": case "prod_imp":{
			this.getData(type);
			String s = txSearch.getText();
			s = s.trim();
			String mind = txMin.getText();
			mind = mind.trim();
			String maxd = txMax.getText();
			maxd = maxd.trim();
			String prod_type = comboProdType.getSelectedItem().toString();
			if (s.equals("") && maxd.equals("") && mind.equals("") && prod_type.equals("Tất cả")) {
				prodArr = prodBus.sort(prodArr,String.valueOf(comboType.getSelectedItem()), String.valueOf(this.comboOrder.getSelectedItem()), false);
				this.loadData(type);
			} else {
				prod_type = this.getIdType(prod_type);
				try {
					double i, j;
					if (!mind.equals(""))
						i = Double.parseDouble(mind);
					else
						i = 0;
					if (!maxd.equals(""))
						j = Double.parseDouble(maxd);
					else
						j = 1000000;
					
					prodArr = prodBus.search(false, s, i, j, prod_type);
					prodArr = prodBus.sort(prodArr,String.valueOf(comboType.getSelectedItem()), String.valueOf(this.comboOrder.getSelectedItem()), false);
					this.loadData(type);
				} catch (NumberFormatException ex) {
//					ex.printStackTrace();
					JOptionPane.showMessageDialog(this, "Không được nhập chữ vào khung tìm kiếm giá trị!");
				}
			}
			
			
			break;
		}
		case "sale": case "prod_sale": case "bill_sale": {
			this.getData(type);
			boolean check = true;
			String from = "",to = "";
			try {
				if(txFrom.getDate() == null) from = "null";
				else DateExe.isValidDate(DateExe.convertDateToString(txFrom.getDate()));
				if(txTo.getDate() == null) to = "null";
				else DateExe.isValidDate(DateExe.convertDateToString(txTo.getDate()));
			}
			catch(Exception p)
			{
				JOptionPane.showMessageDialog(null, "Ngày không hợp lệ!"); check = false;
			}
			if(check) {
				if(!from.equals("null")) from = DateExe.convertDateToString(txFrom.getDate());
				if(!to.equals("null")) to = DateExe.convertDateToString(txTo.getDate());
				saleArr = saleBus.sortOrder(saleBus.search(saleArr, txSearch.getText(), this.getSelectedButtonText(bg), from, to),
						String.valueOf(comboType.getSelectedItem()), String.valueOf(this.comboOrder.getSelectedItem()));
				this.loadData(type);
				if(saleArr.size() == 0) JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả nào!");
			}
			
			break;
		}
		case "ing": {
			this.getData(type);
			String choice = this.comboType.getSelectedItem().toString();
			String side = this.comboOrder.getSelectedItem().toString();
			choice = choice.toLowerCase();
			side = side.toLowerCase();
			
			
			
			ingArr = ingBus.sort(ingArr, choice, side, false);
			this.loadData(type);
			break;
		}
		case "emp": {
			this.getData(type);
			empArr = empBus.sort(empBus.search(empArr, txSearch.getText(), this.getSelectedButtonText(bg)),
					String.valueOf(comboType.getSelectedItem()), String.valueOf(this.comboOrder.getSelectedItem()));
			this.loadData(type);
		}
		case "sup": {
			this.getData(type);
			String choice = this.comboType.getSelectedItem().toString();
			String side = this.comboOrder.getSelectedItem().toString();
			type = type.toLowerCase();
			side = side.toLowerCase();
			supArr = supBus.sort(supArr, choice, side, false);
			this.loadData(type);
		}
		default: {
			this.getData(type);
			String choice = this.comboType.getSelectedItem().toString();
			String side = this.comboOrder.getSelectedItem().toString();
			type = type.toLowerCase();
			side = side.toLowerCase();
			cusArr = cusBus.sort(cusArr, choice, side, false);
			this.loadData(type);
			break;
		}
		}
	}
	String getIdType(String type) {
		switch (type) {
		case "Cà phê":
			return "CF";
//		case "Nguyên liệu":return "IN";
		case "Sinh Tố":
			return "FJ";
		case "Nước ngọt có ga":
			return "SD";
		case "Rượu":
			return "WI";
		case "Bia":
			return "BR";
		case "Trà":
			return "TE";
		}
		return "";
	}
	private void getHeader(String type) {
		switch(type) {
		
		case "prod": case "prod_imp": {
			header = new String[] {"Mã","Tên","Đơn giá","Số lượng","Đ.vị","Mô tả"};
			break;
		}
		case "sale": case "prod_sale": case "bill_sale": {
			header = new String[] {"Mã","Tên","Mức KM","Đ.vị","Mức áp dụng","Đ.vị","Bắt đầu","Kết thúc"};
			break;
		}
		case "ing": {
			header = new String[] {"Mã","Tên","Đơn giá","Đ.vị"};
			break;
		}
		case "emp": {
			header = new String[] {"Mã","Tên","Chức vụ","Ca"};
			break;
		}
		case "sup": {
			header = new String[] {"Mã","Tên","Địa chỉ","SĐT"};
			break;
		}
		default: {
			header = new String[] {"Mã","Tên","SĐT"};
			break;
		}
		}
	}
	public void setColSize(String type) {
		TableColumnModel col = table.getColumnModel();
		switch(type) {
		
		case "prod": case "prod_imp": {
			header = new String[] {"Mã","Tên","Đơn giá","Số lượng","Đ.vị","Mô tả"};
			this.setFixColSize(col, 0, 40);
			this.setFixColSize(col, 1, 200);
			this.setFixColSize(col, 2, 80);
			this.setFixColSize(col, 3, 70);
			this.setFixColSize(col, 4, 40);
			this.setFixColSize(col, 5, 200);
			break;
		}
		case "sale": case "prod_sale": case "bill_sale": {
			header = new String[] {"Mã","Tên","Mức KM","Đ.vị","Mức áp dụng","Đ.vị","Bắt đầu","Kết thúc"};
			this.setFixColSize(col, 0, 40);
			this.setFixColSize(col, 1, 150);
			this.setFixColSize(col, 2, 70);
			this.setFixColSize(col, 3, 40);
			this.setFixColSize(col, 4, 90);
			this.setFixColSize(col, 5, 40);
			this.setFixColSize(col, 6, 80);
			this.setFixColSize(col, 7, 80);
			break;
		}
		case "ing": {
			header = new String[] {"Mã","Tên","Đơn giá","Đ.vị"};
			this.setFixColSize(col, 0, 30);
			this.setFixColSize(col, 1, 250);
			this.setFixColSize(col, 2, 100);
			this.setFixColSize(col, 3, 50);
			break;
		}
		case "emp": {
			this.setFixColSize(col, 0, 30);
			this.setFixColSize(col, 1, 250);
			this.setFixColSize(col, 2, 80);
			this.setFixColSize(col, 3, 50);
			break;
		}
		case "sup": {
			this.setFixColSize(col, 0, 30);
			this.setFixColSize(col, 1, 250);
			this.setFixColSize(col, 2, 150);
			this.setFixColSize(col, 3, 100);
			break;
		}
		default: {
			//customer
			this.setFixColSize(col, 0, 30);
			this.setFixColSize(col, 1, 250);
			this.setFixColSize(col, 2, 120);
			break;
		}
		}
	
	}
	//emloyer
	public Employer getEmp() {
		return getEmp(confirm);
	}
	private Employer getEmp(boolean confirm) {
		if(confirm) {
			return empBus.searchByID(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
		}else return null;
	}
	
	//customer
		public Customer getCus() {
			return getCus(confirm);
		}
		private Customer getCus(boolean confirm) {
			if(confirm) {
				return cusBus.searchByID(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
			}else return null;
		}
		
	//sale
		public Sale getSale() {
			return getSale(confirm);
		}
		private Sale getSale(boolean confirm) {
			if(confirm) {
				return saleBus.searchByID(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
			}else return null;
		}
		
		//product
				public Product getProd() {
					return getProd(confirm);
				}
				private Product getProd(boolean confirm) {
					if(confirm) {
						return prodBus.searchByID(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
					}else return null;
				}
	
				//ingredient
				public Ingredient getIng() {
					return getIng(confirm);
				}
				private Ingredient getIng(boolean confirm) {
					if(confirm) {
						return ingBus.searchByID(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
					}else return null;
				}
				//supplier
				public Supplier getSup() {
					return getSup(confirm);
				}
				private Supplier getSup(boolean confirm) {
					if(confirm) {
						return supBus.searchByID(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
					}else return null;
				}
	public boolean getAuthority() {
		return this.authority;
	}
	
	public void setFixColSize(TableColumnModel col, int index, int size) {
		col.getColumn(index).setMinWidth(size);
		//col.getColumn(index).setResizable(false);
	}
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}
