package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import bus.*;
import dto.*;
import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.JButton;
public class CalculationGUI extends JPanel {
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JComboBox cbOrder;
	private JPanel panel_2;
	private JLabel lblSSNhn;
	private JTextField textField;
	private JLabel lblSKhchHng;
	private JTextField textField_1;
	private JLabel lblTngThu;
	private JTextField txtTngThu;
	private JTextField textField_2;
	private JLabel lblTngChi;
	private JTextField textField_3;
	private JLabel lblLiNhun;
	private Calculator cal = new Calculator();
	private JComboBox cbYear;
	private JComboBox cbMonth;
	private JLabel lblNewLabel_1;
	private JLabel lblNm;
	private JPanel panel_3;
	private JComboBox cbType;
	private JComboBox cbMode;
	private JButton btnApply;
	private JComboBox cbChoice;
	/**
	 * Create the panel.
	 */
	public CalculationGUI() {
		this.setSize(1000, 700);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 207, 949, 417);
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("THỐNG KÊ");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 949, 46);
		add(lblNewLabel);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "S\u1EAFp x\u1EBFp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(511, 113, 448, 83);
		add(panel_1);
		panel_1.setLayout(null);
		
		String[] order = {"Tăng dần","Giảm dần"};
		
		cbYear = new JComboBox(new Object[]{2018,2019});
		cbYear.setEnabled(false);
		cbYear.setBounds(90, 45, 124, 30);
		panel_1.add(cbYear);
		
		cbMonth = new JComboBox(new Object[]{1,2,3,4,5,6,7,8,9,10,11,12});
		cbMonth.setEnabled(false);
		cbMonth.setBounds(90, 10, 124, 30);
		panel_1.add(cbMonth);
		
		lblNewLabel_1 = new JLabel("Tháng:");
		lblNewLabel_1.setBounds(31, 12, 48, 26);
		panel_1.add(lblNewLabel_1);
		
		lblNm = new JLabel("Năm:");
		lblNm.setBounds(31, 47, 48, 26);
		panel_1.add(lblNm);
		
		btnApply = new JButton("ÁP DỤNG");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(String.valueOf(cbMode.getSelectedItem()).equals("Tổng quan")) {
					loadTable(String.valueOf(cbType.getSelectedItem()),String.valueOf(cbMode.getSelectedItem()), 
							0,0,String.valueOf(cbChoice.getSelectedItem()),String.valueOf(cbOrder.getSelectedItem()));
				}else{
					loadTable(String.valueOf(cbType.getSelectedItem()),String.valueOf(cbMode.getSelectedItem()), 
							Integer.parseInt(String.valueOf(cbMonth.getSelectedItem())),Integer.parseInt(String.valueOf(cbYear.getSelectedItem())),
							String.valueOf(cbChoice.getSelectedItem()),String.valueOf(cbOrder.getSelectedItem()));
				}
			}
		});
		btnApply.setEnabled(false);
		btnApply.setBounds(333, 31, 89, 30);
		panel_1.add(btnApply);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "T\u1ED5ng quan", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 44, 949, 67);
		add(panel_2);
		panel_2.setLayout(null);
		
		lblSSNhn = new JLabel("Sỉ số nhân viên:");
		lblSSNhn.setBounds(22, 28, 91, 25);
		panel_2.add(lblSSNhn);
		
		textField = new JTextField(String.valueOf(cal.countEmp()));
		textField.setBounds(110, 25, 70, 30);
		panel_2.add(textField);
		textField.setColumns(10);
		
		lblSKhchHng = new JLabel("Số khách hàng:");
		lblSKhchHng.setBounds(220, 26, 91, 25);
		panel_2.add(lblSKhchHng);
		
		textField_1 = new JTextField(String.valueOf(cal.countCus()));
		textField_1.setColumns(10);
		textField_1.setBounds(308, 23, 70, 30);
		panel_2.add(textField_1);
		
		lblTngThu = new JLabel("Tống thu:");
		lblTngThu.setBounds(418, 26, 60, 25);
		panel_2.add(lblTngThu);
		
		txtTngThu = new JTextField(String.valueOf(cal.totalIncome()));
		txtTngThu.setColumns(10);
		txtTngThu.setBounds(472, 23, 98, 30);
		panel_2.add(txtTngThu);
		
		textField_2 = new JTextField(String.valueOf(cal.totalPay()));
		textField_2.setColumns(10);
		textField_2.setBounds(658, 23, 98, 30);
		panel_2.add(textField_2);
		
		lblTngChi = new JLabel("Tống chi:");
		lblTngChi.setBounds(604, 26, 60, 25);
		panel_2.add(lblTngChi);
		
		textField_3 = new JTextField(String.valueOf(cal.totalRevenue()));
		textField_3.setColumns(10);
		textField_3.setBounds(841, 23, 98, 30);
		panel_2.add(textField_3);
		
		lblLiNhun = new JLabel("Lợi nhuận");
		lblLiNhun.setBounds(787, 26, 60, 25);
		panel_2.add(lblLiNhun);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Ki\u1EC3u th\u1ED1ng k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 113, 491, 83);
		add(panel_3);
		panel_3.setLayout(null);
		
		cbType = new JComboBox(new String[] {"Khách hàng","Nhân viên","Sản phẩm"});
		cbType.setBounds(10, 28, 135, 30);
		panel_3.add(cbType);
		
		
		cbMode = new JComboBox(new String[] {"Tổng quan","Theo tháng"});
		cbMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(String.valueOf(cbMode.getSelectedItem()).equals("Tổng quan")) {
					loadTable(String.valueOf(cbType.getSelectedItem()),String.valueOf(cbMode.getSelectedItem()), 
							0,0,String.valueOf(cbChoice.getSelectedItem()),String.valueOf(cbOrder.getSelectedItem()));
					cbMonth.setEnabled(false);
					cbYear.setEnabled(false);
					btnApply.setEnabled(false);
				}else{
					loadTable(String.valueOf(cbType.getSelectedItem()),String.valueOf(cbMode.getSelectedItem()), 
							Integer.parseInt(String.valueOf(cbMonth.getSelectedItem())),Integer.parseInt(String.valueOf(cbYear.getSelectedItem())),
							String.valueOf(cbChoice.getSelectedItem()),String.valueOf(cbOrder.getSelectedItem()));
					cbMonth.setEnabled(true);
					cbYear.setEnabled(true);
					btnApply.setEnabled(true);
				}
			}
		});
		
		cbType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] temp; 
				switch(String.valueOf(cbType.getSelectedItem())) {
				case "Khách hàng": {
					temp = new String[] {"Theo số lượng","Theo thanh toán"};
					break;
				}
				case "Nhân viên":temp = new String[] {"Theo tổng thu","Theo tổng chi","Theo lãi"}; break;
				default:temp = new String[] {"Theo SL bán","Theo SL nhập"}; break;
				}
				DefaultComboBoxModel bm = new DefaultComboBoxModel (temp);
				cbChoice.setModel(bm);
				
				if(String.valueOf(cbMode.getSelectedItem()).equals("Tổng quan")) {
					loadTable(String.valueOf(cbType.getSelectedItem()),String.valueOf(cbMode.getSelectedItem()), 
							0,0,String.valueOf(cbChoice.getSelectedItem()),String.valueOf(cbOrder.getSelectedItem()));
				}else{
					loadTable(String.valueOf(cbType.getSelectedItem()),String.valueOf(cbMode.getSelectedItem()), 
							Integer.parseInt(String.valueOf(cbMonth.getSelectedItem())),Integer.parseInt(String.valueOf(cbYear.getSelectedItem())),
							String.valueOf(cbChoice.getSelectedItem()),String.valueOf(cbOrder.getSelectedItem()));
				}
			}
		});
		cbMode.setBounds(155, 28, 185, 30);
		panel_3.add(cbMode);
		cbOrder = new JComboBox(order);
		cbOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(String.valueOf(cbMode.getSelectedItem()).equals("Tổng quan")) {
					loadTable(String.valueOf(cbType.getSelectedItem()),String.valueOf(cbMode.getSelectedItem()), 
							0,0,String.valueOf(cbChoice.getSelectedItem()),String.valueOf(cbOrder.getSelectedItem()));
				}else{
					loadTable(String.valueOf(cbType.getSelectedItem()),String.valueOf(cbMode.getSelectedItem()), 
							Integer.parseInt(String.valueOf(cbMonth.getSelectedItem())),Integer.parseInt(String.valueOf(cbYear.getSelectedItem())),
							String.valueOf(cbChoice.getSelectedItem()),String.valueOf(cbOrder.getSelectedItem()));
				}
			}
		});
		cbOrder.setBounds(350, 45, 124, 30);
		panel_3.add(cbOrder);
		
		DefaultComboBoxModel bm = new DefaultComboBoxModel(new String[] {"Theo số lượng","Theo thanh toán"});
		cbChoice = new JComboBox() ;
		cbChoice.setModel(bm);
		cbChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(String.valueOf(cbMode.getSelectedItem()).equals("Tổng quan")) {
					loadTable(String.valueOf(cbType.getSelectedItem()),String.valueOf(cbMode.getSelectedItem()), 
							0,0,String.valueOf(cbChoice.getSelectedItem()),String.valueOf(cbOrder.getSelectedItem()));
				}else{
					loadTable(String.valueOf(cbType.getSelectedItem()),String.valueOf(cbMode.getSelectedItem()), 
							Integer.parseInt(String.valueOf(cbMonth.getSelectedItem())),Integer.parseInt(String.valueOf(cbYear.getSelectedItem())),
							String.valueOf(cbChoice.getSelectedItem()),String.valueOf(cbOrder.getSelectedItem()));
				}
			}
		});
		cbChoice.setBounds(350, 11, 124, 30);
		panel_3.add(cbChoice);
		
		loadTable(String.valueOf(cbType.getSelectedItem()),String.valueOf(cbMode.getSelectedItem()), 
				0,0,String.valueOf(cbChoice.getSelectedItem()),String.valueOf(cbOrder.getSelectedItem()));
	}
	private void loadTable(String type, String mode,int month,int year,String choice, String order) {
		switch(type) {
		case"Khách hàng":{
			String[] header = new String[] {"Mã KH","Tên KH","Số lượng mua","Tổng thanh toán"};
			DefaultTableModel md = new DefaultTableModel(header,0);
			CustomerBUS cusBus =new CustomerBUS();
			ArrayList<Object[]> obj = new ArrayList<Object[]>();
			for(Customer p : cusBus.read()) {
				Object[] row = new Object[] { p.getCus_id(),p.getCus_name(),
						cal.purchaseAmountbyCus(p.getCus_id(), month,year),cal.purchaseValuebyCus(p.getCus_id(), month,year) };
				obj.add(row);
			}
			if(order.equals("Tăng dần")) {
				switch(choice){
					
				case "Theo số lượng":	obj.sort(new Comparator<Object[]>() {
						public int compare(Object[] x, Object[] y) {
							return Integer.parseInt(String.valueOf(x[2])) - Integer.parseInt(String.valueOf(y[2]));
						}
					});
					break;
				default: obj.sort(new Comparator<Object[]>() {
					public int compare(Object[] x, Object[] y) {
						return Double.compare(Double.parseDouble(String.valueOf(x[3])) , Double.parseDouble(String.valueOf(y[3])));
					}
				});
				break;
				}
			}
			else {
				switch(choice){
				
				case "Theo số lượng":	obj.sort(new Comparator<Object[]>() {
						public int compare(Object[] x, Object[] y) {
							return Integer.parseInt(String.valueOf(y[2])) - Integer.parseInt(String.valueOf(x[2]));
						}
					});
					break;
				default: obj.sort(new Comparator<Object[]>() {
					public int compare(Object[] x, Object[] y) {
						return Double.compare(Double.parseDouble(String.valueOf(y[3])) ,Double.parseDouble(String.valueOf(x[3])));
					}
				});
				break;
				}
			}
			int sum_amount= 0;
			double sum_value= 0;
			for(Object[] p : obj) {
				md.addRow(p);
				sum_amount += Integer.parseInt(String.valueOf(p[2]));
				sum_value += Double.parseDouble(String.valueOf(p[3]));
			}
			Object[] sumRow = new Object[] {"Tổng:","",sum_amount,sum_value};
			Object[] spaceRow = new Object[] {"","","",""};
			md.addRow(spaceRow);
			md.addRow(sumRow);
			table.setModel(md);
			break;
		}
		
		//<<------------------------------------------------------------------------------------------------------>>
		
		case"Nhân viên": {
			String[] header = new String[] {"Mã NV","Tên NV","Tổng thu","Tổng chi","Lợi nhuận"};
			DefaultTableModel md = new DefaultTableModel(header,0);
			EmployerBUS empBus = new EmployerBUS();
			ArrayList<Object[]> obj = new ArrayList<Object[]>();
			for(Employer p : empBus.read()) {
				Object[] row = new Object[] { p.getEmp_id(),p.getEmp_name(),
						cal.totalIncomebyEmp(p.getEmp_id(), month, year), cal.totalPaybyEmp(p.getEmp_id(), month, year),
						cal.totalRevenuebyEmp(p.getEmp_id(), month, year)};
				obj.add(row);
			}
			if(order.equals("Tăng dần")) {
				switch(choice){
					
				case "Theo tổng thu":	obj.sort(new Comparator<Object[]>() {
						public int compare(Object[] x, Object[] y) {
							return Double.compare(Double.parseDouble(String.valueOf(x[2])) , Double.parseDouble(String.valueOf(y[2])));
						}
					});
					break;
				case "Theo tổng chi":	obj.sort(new Comparator<Object[]>() {
					public int compare(Object[] x, Object[] y) {
						return Double.compare(Double.parseDouble(String.valueOf(x[3])) , Double.parseDouble(String.valueOf(y[3])));
					}
				});
				break;
				default: obj.sort(new Comparator<Object[]>() {
					public int compare(Object[] x, Object[] y) {
						return Double.compare(Double.parseDouble(String.valueOf(x[4])) , Double.parseDouble(String.valueOf(y[4])));
					}
				});
				break;
				}
			}
			else {
				switch(choice){
				
				case "Theo tổng thu":	obj.sort(new Comparator<Object[]>() {
						public int compare(Object[] x, Object[] y) {
							return Double.compare(Double.parseDouble(String.valueOf(y[2])) , Double.parseDouble(String.valueOf(x[2])));
						}
					});
					break;
				case "Theo tổng chi":	obj.sort(new Comparator<Object[]>() {
					public int compare(Object[] x, Object[] y) {
						return Double.compare(Double.parseDouble(String.valueOf(y[3])) , Double.parseDouble(String.valueOf(x[3])));
					}
				});
				break;
				default: obj.sort(new Comparator<Object[]>() {
					public int compare(Object[] x, Object[] y) {
						return Double.compare(Double.parseDouble(String.valueOf(y[4])) , Integer.parseInt(String.valueOf(x[4])));
					}
				});
				break;
				}
			}
			double sum_income= 0;
			double sum_pay= 0;
			double sum_revenue = 0;
			for(Object[] p : obj) {
				md.addRow(p);
				sum_income += Double.parseDouble(String.valueOf(p[2]));
				sum_pay += Double.parseDouble(String.valueOf(p[3]));
				sum_revenue += Double.parseDouble(String.valueOf(p[4]));
			}
			Object[] sumRow = new Object[] {"Tổng:","",sum_income,sum_pay,sum_revenue};
			Object[] spaceRow = new Object[] {"","","","",""};
			md.addRow(spaceRow);
			md.addRow(sumRow);
			table.setModel(md);
			break;
		}
		
		//<<------------------------------------------------------------------------------------------------------>>
		
		default:{
			String[] header = new String[] {"Mã SP","Tên SP","Số lượng đã bán","Số lượng đã nhập"};
			DefaultTableModel md = new DefaultTableModel(header,0);
			ProductBUS prodBus = new ProductBUS();
			ArrayList<Object[]> obj = new ArrayList<Object[]>();
			for(Product p : prodBus.readAll()) {
				Object[] row = new Object[] { p.getProd_id(),p.getProd_name(),
						cal.exportAmountbyProd(p.getProd_id(), month, year), cal.importAmountbyProd(p.getProd_id(), month, year)};
				obj.add(row);
			}
			if(order.equals("Tăng dần")) {
				switch(choice){
					
				case "Theo SL bán":	obj.sort(new Comparator<Object[]>() {
						public int compare(Object[] x, Object[] y) {
							return Integer.parseInt(String.valueOf(x[2])) - Integer.parseInt(String.valueOf(y[2]));
						}
					});
					break;
				default: obj.sort(new Comparator<Object[]>() {
					public int compare(Object[] x, Object[] y) {
						return Integer.parseInt(String.valueOf(x[3])) - Integer.parseInt(String.valueOf(y[3]));
					}
				});
				break;
				}
			}
			else {
				switch(choice){
				
				case "Theo SL bán":	obj.sort(new Comparator<Object[]>() {
						public int compare(Object[] x, Object[] y) {
							return Integer.parseInt(String.valueOf(y[2])) - Integer.parseInt(String.valueOf(x[2]));
						}
					});
					break;
				default: obj.sort(new Comparator<Object[]>() {
					public int compare(Object[] x, Object[] y) {
						return Integer.parseInt(String.valueOf(y[3])) - Integer.parseInt(String.valueOf(x[3]));
					}
				});
				break;
				}
			}
			double sum_exp= 0;
			double sum_imp= 0;
			
			for(Object[] p : obj) {
				md.addRow(p);
				sum_exp += Integer.parseInt(String.valueOf(p[2]));
				sum_imp += Integer.parseInt(String.valueOf(p[3]));
				
			}
			Object[] sumRow = new Object[] {"Tổng:","",sum_exp,sum_imp};
			Object[] spaceRow = new Object[] {"","","",""};
			md.addRow(spaceRow);
			md.addRow(sumRow);
			table.setModel(md);
			break;
		}
		}
	}
}
