package gui;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import java.awt.BorderLayout;
import java.util.*;
import dto.*;
import gui.ExcelImportDialog;
import bus.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import design.*;
import java.awt.Font;
public class ImportGUI extends JPanel implements ActionListener{
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	private JLabel lblMNv;
	private JLabel lblMKh;
	private JLabel lblNgyLp;
	private JTextField txImpID;
	private JTextField txEmpID;
	private JTextField txSupID;
	private JButton btnUpdateImp;
	private JButton btnSelectSup;
	private JButton btnAddImp;
	private JButton btnLockImp;
	private JLabel lblMSp;
	private JLabel lblSLng;
	private JLabel lblnGi;
	private JTextField txProdID;
	private JTextField txAmount;
	private JTextField txUnitPrice;
	private JLabel lblThnhTin;
	private JTextField txTotal;
	private JTextField txMeasure;
	private JButton btnDeleteImp;
	private JButton btnAddDetail;
	private JButton btnDeleteDetail;
	private JButton btnUpdateDetail;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTable tableImp;
	private JTable tableDetail;
	private JPanel panel_6;
	private JTextField txSearchImp;
	private JRadioButton rdbtnH;
	private JRadioButton rdbtnNv;
	private JRadioButton rdbtnKh;
	private JPanel panel_7;
	private JDateChooser txDateFrom;
	private JDateChooser txDateTo;
	private JLabel lblT;
	private JLabel lbln;
	private JPanel panel_8;
	private JComboBox comboImpType;
	private JComboBox comboImpOrder;
	private JButton btnApplyImp;
	

	/**
	 * Create the panel.
	 */
	
	private boolean authority;
	private EmployerBUS empBus = new EmployerBUS();
	private ImportBUS impBus = new ImportBUS();
	private ImportDetailBUS impdetailBus = new ImportDetailBUS();
	private ProductBUS prodBus = new ProductBUS();
	private IngredientBUS ingBus = new IngredientBUS();
	private SupplierBUS supBus = new SupplierBUS();
	private String impID = "";
	private int islocked = 0;
	private ArrayList<Import> impArr;
	private ArrayList<ImportDetail> impdetailArr;
	private JTextField txProdName;
	private JLabel lblTnSp;
	private JPanel panel_9;
	private JTextField txSearchDetail;
	private JRadioButton rdbtnMSp;
	private JRadioButton rdbtnTnSp;
	private JPanel panel_10;
	private JComboBox comboDetailType;
	private JComboBox comboDetailOrder;
	private JButton btnApplyDetail;
	private ButtonGroup bg1 = new ButtonGroup();
	private ButtonGroup bg2 = new ButtonGroup();
	private JButton btnSelectProd;
	private String empID;
	private JTextField txDate;
	private JButton btimport;
	private JButton btexport;
	private JButton btnPDF;
	public ImportGUI(String id) {
		design.Icon icon = new design.Icon();
		Employer emp = empBus.searchByID(id);
		this.authority = emp.isEmp_type();
		this.empID = emp.getEmp_id();
		
		this.setBackground(icon.getContentColor());
		this.setSize(1000, 700);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin nh\u1EADp h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 253, 337);
                panel.setBackground(icon.getPaneColor());
		add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Mã NH:");
		lblNewLabel.setBounds(29, 29, 58, 26);
		panel.add(lblNewLabel);
		
		lblMNv = new JLabel("Mã NV:");
		lblMNv.setBounds(29, 66, 58, 26);
		panel.add(lblMNv);
		
		lblMKh = new JLabel("Mã NCC:");
		lblMKh.setBounds(29, 103, 58, 26);
		panel.add(lblMKh);
		
		lblNgyLp = new JLabel("Ngày lập:");
		lblNgyLp.setBounds(29, 140, 58, 26);
		panel.add(lblNgyLp);
		
		txImpID = new JTextField();
		txImpID.setEditable(false);
		txImpID.setBounds(86, 27, 107, 30);
		panel.add(txImpID);
		txImpID.setColumns(10);
		
		txEmpID = new JTextField();
		txEmpID.setEditable(false);
		txEmpID.setColumns(10);
		txEmpID.setBounds(86, 62, 107, 30);
		panel.add(txEmpID);
		
		txSupID = new JTextField();
		txSupID.setEditable(false);
		txSupID.setColumns(10);
		txSupID.setBounds(86, 99, 107, 30);
		panel.add(txSupID);
		
		btnUpdateImp = new JButton("SỬA"); 
		btnUpdateImp.setBounds(133, 233, 110, 30);
                btnUpdateImp.setBackground(icon.getEditColor());
                btnUpdateImp.setFont(new Font("sans-serif", Font.BOLD, 14));
                btnUpdateImp.setForeground(Color.white);
                btnUpdateImp.setIcon(icon.imageIconSize("icons/edit.png", 20,20));
		panel.add(btnUpdateImp);
		
		btnSelectSup = new JButton("...");
		btnSelectSup.setBounds(202, 101, 41, 30);
		panel.add(btnSelectSup);
		
		btnAddImp = new JButton("THÊM");
		if(this.authority) btnAddImp.setEnabled(false);
		btnAddImp.setBounds(19, 233, 104, 30);
                btnAddImp.setBackground(icon.getInsertColor());
                btnAddImp.setForeground(Color.white);
                btnAddImp.setFont(new Font("sans-serif", Font.BOLD, 14));
                btnAddImp.setIcon(icon.imageIconSize("icons/add.png", 20,20));
		panel.add(btnAddImp);
		
		lblThnhTin = new JLabel("Thành tiền:");
		lblThnhTin.setBounds(22, 178, 65, 26);
		panel.add(lblThnhTin);
		
		txTotal = new JTextField();
		txTotal.setEditable(false);
		txTotal.setColumns(10);
		txTotal.setBounds(87, 176, 107, 30);
		panel.add(txTotal);
		
		btnDeleteImp = new JButton("XÓA");
		btnDeleteImp.setBounds(77, 274, 104, 30);
                btnDeleteImp.setBackground(icon.getDeleteColor());
                btnDeleteImp.setForeground(Color.white);
                btnDeleteImp.setIcon(icon.imageIconSize("icons/delete.png",20,20));
                btnDeleteImp.setFont(new Font("sans-serif", Font.BOLD, 14));
		panel.add(btnDeleteImp);
		
		txDate = new JTextField();
		txDate.setEditable(false);
		txDate.setBounds(86, 138, 107, 30);
		panel.add(txDate);
		txDate.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBounds(273, 11, 705, 337);
                panel_1.setBackground(icon.getPaneColor());
		add(panel_1);
		panel_1.setLayout(null);
		
		panel_4 = new JPanel();
		panel_4.setBounds(0, 101, 692, 196);
                panel_4.setBackground(icon.getPaneColor());
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_4.add(scrollPane);
		
		tableImp = new myTable();
		tableImp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Import temp = impBus.searchByID((String.valueOf(tableImp.getValueAt(tableImp.getSelectedRow(), 0))));
				showImpTableData(temp);
				impID = temp.getEmp_id();
				islocked = temp.getStatus();
				if(!temp.getEmp_id().equals(empID) || islocked == 1 || islocked == 2) {
					lockImp();
					btnAddDetail.setEnabled(false);
					if(!authority) {
						if(temp.getEmp_id().equals(empID) && islocked == 2) {
							btnDeleteImp.setText("MỞ");
							btnDeleteImp.setEnabled(true);
						}
					}
				}
				else {
					if(!authority) {
						btnDeleteImp.setText("XÓA");
						unlockImp();
						btnAddDetail.setEnabled(true);
					}
				}
				
				clearDetail();
				lockDetail();
				impdetailArr = impdetailBus.searchByImportID(temp.getImp_id());
				loadDetailData(impdetailArr);
			}
		});
		tableImp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Import temp = impBus.searchByID((String.valueOf(tableImp.getValueAt(tableImp.getSelectedRow(), 0))));
				showImpTableData(temp);
				impID = temp.getImp_id();
				islocked = temp.getStatus();
				
				if(!temp.getEmp_id().equals(empID) || islocked == 1 || islocked == 2) {
					lockImp();
					btnAddDetail.setEnabled(false);
					if(!authority) {
						if(temp.getEmp_id().equals(empID) && islocked == 2) {
							btnDeleteImp.setText("MỞ");
							btnDeleteImp.setEnabled(true);
						}
					}
				}
				else {
					if(!authority) {
						btnDeleteImp.setText("XÓA");
						unlockImp();
						btnAddDetail.setEnabled(true);
					}
				}
				clearDetail();
				lockDetail();
				
				impdetailArr = impdetailBus.searchByImportID(temp.getImp_id());
				loadDetailData(impdetailArr);
			}
		});
		scrollPane.setViewportView(tableImp);
		
		panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(0, 0, 203, 101);
                panel_6.setBackground(icon.getPaneColor());
		panel_1.add(panel_6);
		panel_6.setLayout(null);
		
		txSearchImp = new JTextField();
		txSearchImp.setBounds(10, 21, 183, 30);
		panel_6.add(txSearchImp);
		txSearchImp.setColumns(10);
		
		rdbtnH = new JRadioButton("NH");
		rdbtnH.setSelected(true);
		rdbtnH.setBounds(20, 58, 48, 23);
		panel_6.add(rdbtnH);
		
		rdbtnNv = new JRadioButton("NV");
		rdbtnNv.setBounds(145, 58, 48, 23);
		panel_6.add(rdbtnNv);
		
		rdbtnKh = new JRadioButton("NCC");
		rdbtnKh.setBounds(78, 58, 65, 23);
		panel_6.add(rdbtnKh);
		
		panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Ng\u00E0y l\u1EADp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(208, 0, 203, 101);
                panel_7.setBackground(icon.getPaneColor());
		panel_1.add(panel_7);
		panel_7.setLayout(null);
		
		txDateFrom = new JDateChooser(); txDateFrom.setDateFormatString("dd/MM/yyyy");
		txDateFrom.setBounds(55, 22, 138, 30);
		panel_7.add(txDateFrom);
		
		txDateTo = new JDateChooser(); txDateTo.setDateFormatString("dd/MM/yyyy");
		txDateTo.setBounds(55, 55, 138, 30);
		panel_7.add(txDateTo);
		
		lblT = new JLabel("Từ:");
		lblT.setBounds(10, 22, 41, 26);
		panel_7.add(lblT);
		
		lbln = new JLabel("Đến:");
		lbln.setBounds(10, 55, 41, 26);
		panel_7.add(lbln);
		
		panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "S\u1EAFp x\u1EBFp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(414, 0, 171, 101);
                panel_8.setBackground(icon.getPaneColor());
		panel_1.add(panel_8);
		panel_8.setLayout(null);
		
		String[] billtype = {"Mã NH","Mã NV","Mã NCC","Ngày lập","Thành tiền"};
		comboImpType = new JComboBox(billtype);
		comboImpType.setBounds(10, 22, 151, 30);
		panel_8.add(comboImpType);
		
		String[] order = {"Tăng dần","Giảm dần"};
		comboImpOrder = new JComboBox(order);
		comboImpOrder.setBounds(10, 60, 151, 30);
		panel_8.add(comboImpOrder);
		
		btnApplyImp = new JButton("ÁP DỤNG");
		btnApplyImp.setBounds(585, 11, 107, 79);
                btnApplyImp.setBackground(icon.getSearchColor());
                btnApplyImp.setForeground(Color.black);
                btnApplyImp.setFont(new Font("sans-serif", Font.BOLD, 14));
                btnApplyImp.setIcon(icon.imageIconSize("icons/search_more.png", 20, 20));
		panel_1.add(btnApplyImp);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin chi ti\u1EBFt nh\u1EADp h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 359, 253, 279);
                panel_2.setBackground(icon.getPaneColor());
		add(panel_2);
		panel_2.setLayout(null);
		
		lblMSp = new JLabel("Mã SP:");
		lblMSp.setBounds(26, 42, 58, 26);
		panel_2.add(lblMSp);
		
		lblSLng = new JLabel("Số lượng:");
		lblSLng.setBounds(26, 122, 58, 26);
		panel_2.add(lblSLng);
		
		lblnGi = new JLabel("Đơn giá:");
		lblnGi.setBounds(26, 159, 58, 26);
		panel_2.add(lblnGi);
		
		txProdID = new JTextField();
		txProdID.setEditable(false);
		txProdID.setColumns(10);
		txProdID.setBounds(84, 40, 107, 30);
		panel_2.add(txProdID);
		
		txAmount = new JTextField();
		txAmount.setEditable(false);
		txAmount.setColumns(10);
		txAmount.setBounds(84, 120, 89, 30);
		panel_2.add(txAmount);
		
		txUnitPrice = new JTextField();
		txUnitPrice.setEditable(false);
		txUnitPrice.setColumns(10);
		txUnitPrice.setBounds(84, 157, 107, 30);
		panel_2.add(txUnitPrice);
		
		txMeasure = new JTextField();
		txMeasure.setEditable(false);
		txMeasure.setText("");
		txMeasure.setBounds(179, 120, 64, 30);
		panel_2.add(txMeasure);
		txMeasure.setColumns(10);
		
		btnAddDetail = new JButton("THÊM");
		btnAddDetail.setEnabled(false);
		btnAddDetail.setBounds(19, 196, 110, 30);
                btnAddDetail.setBackground(icon.getInsertColor());
                btnAddDetail.setForeground(Color.white);
                btnAddDetail.setFont(new Font("sans-serif", Font.BOLD, 14));
                btnAddDetail.setIcon(icon.imageIconSize("icons/add.png", 20, 20));
		panel_2.add(btnAddDetail);
		
		btnDeleteDetail = new JButton("XÓA");
		btnDeleteDetail.setBounds(69, 236,110, 30);
                btnDeleteDetail.setBackground(icon.getDeleteColor());
                btnDeleteDetail.setForeground(Color.white);
                btnDeleteDetail.setFont(new Font("sans-serif", Font.BOLD, 14));
                btnDeleteDetail.setIcon(icon.imageIconSize("icons/delete.png", 20, 20));
		panel_2.add(btnDeleteDetail);
		
		btnUpdateDetail = new JButton("SỬA");
		btnUpdateDetail.setBounds(133, 196, 110, 30);
                btnUpdateDetail.setBackground(icon.getEditColor());
                btnUpdateDetail.setForeground(Color.white);
                btnUpdateDetail.setFont(new Font("sans-serif", Font.BOLD, 14));;
                btnUpdateDetail.setIcon(icon.imageIconSize("icons/edit.png", 20, 20));
		panel_2.add(btnUpdateDetail);
		
		txProdName = new JTextField();
		txProdName.setEditable(false);
		txProdName.setColumns(10);
		txProdName.setBounds(84, 79, 159, 30);
		panel_2.add(txProdName);
		
		lblTnSp = new JLabel("Tên SP:");
		lblTnSp.setBounds(26, 81, 58, 26);
		panel_2.add(lblTnSp);
		
		btnSelectProd = new JButton("...");
		btnSelectProd.setBounds(201, 42, 41, 30);
		panel_2.add(btnSelectProd);
		
		panel_3 = new JPanel();
		panel_3.setBounds(273, 359, 705, 244);
                panel_3.setBackground(icon.getPaneColor());
		add(panel_3);
		panel_3.setLayout(null);
		
		panel_5 = new JPanel();
		panel_5.setBounds(0, 63, 692, 171);
                panel_5.setBackground(icon.getPaneColor());
		panel_3.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		scrollPane_1 = new JScrollPane();
		panel_5.add(scrollPane_1, BorderLayout.CENTER);
		
		tableDetail = new myTable();
		tableDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ImportDetail temp = impdetailBus.searchByID(impID, String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 0)));
				showDetailTableData(temp);
				if(!txEmpID.getText().equals(empID)) lockDetail();
				else if( islocked == 1 || islocked == 2) lockDetail();
					else if(!authority) {
						unlockDetail();
				}
			}
		});
		tableDetail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				ImportDetail temp = impdetailBus.searchByID(impID, String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 0)));
				showDetailTableData(temp);
				if(!txEmpID.getText().equals(empID)) lockDetail();
				else if( islocked == 1 || islocked == 2) lockDetail();
					else if(!authority) {
						unlockDetail();
				}
			}
		});
		scrollPane_1.setViewportView(tableDetail);
		
		panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setBounds(0, 0, 294, 64);
                panel_9.setBackground(icon.getPaneColor());
		panel_3.add(panel_9);
		panel_9.setLayout(null);
		
		txSearchDetail = new JTextField();
		txSearchDetail.setBounds(10, 21, 167, 30);
		panel_9.add(txSearchDetail);
		txSearchDetail.setColumns(10);
		
		rdbtnMSp = new JRadioButton("Mã SP");
		rdbtnMSp.setSelected(true);
		rdbtnMSp.setBounds(198, 7, 90, 23);
		panel_9.add(rdbtnMSp);
		
		rdbtnTnSp = new JRadioButton("Tên SP");
		rdbtnTnSp.setBounds(198, 34, 90, 23);
		panel_9.add(rdbtnTnSp);
		
		panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(null, "S\u1EAFp x\u1EBFp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setBounds(296, 0, 287, 64);
                panel_10.setBackground(icon.getPaneColor());
		panel_3.add(panel_10);
		panel_10.setLayout(null);
		
		String[] detailtype = {"Mã SP","Tên SP","Số lượng","Đơn giá"};
		comboDetailType = new JComboBox(detailtype);
		comboDetailType.setBounds(10, 23, 138, 30);
		panel_10.add(comboDetailType);
		
		comboDetailOrder = new JComboBox(order);
		comboDetailOrder.setBounds(158, 23, 119, 30);
		panel_10.add(comboDetailOrder);
		
		btnApplyDetail = new JButton("ÁP DỤNG");
		btnApplyDetail.setBounds(585, 11, 115, 41);
                btnApplyDetail.setBackground(icon.getSearchColor());
                btnApplyDetail.setForeground(Color.black);
                btnApplyDetail.setFont(new Font("sans-serif", Font.BOLD, 14));;
                btnApplyDetail.setIcon(icon.imageIconSize("icons/search_more.png", 20,20));
		panel_3.add(btnApplyDetail);
		
		bg1.add(rdbtnH); bg1.add(rdbtnKh); bg1.add(rdbtnNv);
 
 btnLockImp = new JButton("THANH TOÁN");
 btnLockImp.setBounds(10, 300, 171, 30);
 panel_1.add(btnLockImp);
 btnLockImp.setBackground(icon.getContentColor());
 btnLockImp.setForeground(Color.white);
 btnLockImp.setFont(new Font("sans-serif", Font.BOLD, 14));
		bg2.add(rdbtnMSp); bg2.add(rdbtnTnSp);
 
 btimport = new JButton();
 btimport.setBounds(867, 605, 40, 40);
 add(btimport);
 btimport.setToolTipText("Đọc dữ liệu từ file excel mà bạn chọn.");
 btimport.setBorder(null);
 btimport.setBackground(Color.WHITE);
 
 btimport.setIcon(icon.imageIconSize("icons/import2.png", 40, 40));
 btimport.setBackground(new Color(255, 255, 255));
 btimport.setBorder(null);
 
 btimport.addActionListener(this);
 
 btexport = new JButton();
 btexport.setBounds(912, 605, 40, 40);
 add(btexport);
 btexport.setToolTipText("Ghi dữ liệu vào file excel");
 btexport.setBorder(null);
 btexport.setBackground(Color.WHITE);
 
 		btexport.setIcon(icon.imageIconSize("icons/export2.png", 40, 40));
 		btexport.setBackground(new Color(255, 255, 255));
 		btexport.setBorder(null);
 		btexport.addActionListener(this);
 		
 		btnPDF = new JButton("IN PHIẾU NHẬP HÀNG");
 		btnPDF.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent arg0) {
 				PDFreport report=new PDFreport();
 				report.ImportReport(impID);
 				
 			}
 		});
 		btnPDF.setFont(new Font("Tahoma", Font.PLAIN, 14));
 		btnPDF.setBounds(666, 605, 191, 40);
 		add(btnPDF);
		this.addActionListener();
		this.lockImp(); this.lockDetail();
		getImpData();
		loadImpData(impArr);
		if(!this.authority) {
			btimport.setVisible(false);
			btexport.setVisible(false);
		}
	}
	public void addActionListener() {
		this.btnAddImp.addActionListener(this);
		this.btnDeleteImp.addActionListener(this);
		this.btnUpdateImp.addActionListener(this);
		this.btnLockImp.addActionListener(this);
		this.btnSelectSup.addActionListener(this);
		this.btnApplyImp.addActionListener(this);
		this.btnAddDetail.addActionListener(this);
		this.btnDeleteDetail.addActionListener(this);
		this.btnUpdateDetail.addActionListener(this);
		this.btnSelectProd.addActionListener(this);
		this.btnApplyDetail.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == this.btnAddImp) {
			if(impBus.readAll().size() < 100000) {
				AddImportDialog dial = new AddImportDialog(empID,"");
				dial.setLocationRelativeTo(null);
				dial.setModal(true);
				dial.setVisible(true);
				Import temp = dial.getImp();
				if(temp != null) {
					impBus.add(temp);
					dial.updateImpDetail();
					this.searchAndSortImp();
					this.searchAndSortDetail();
					JOptionPane.showMessageDialog(null, "Thêm thành công");
				}
			}
			else JOptionPane.showMessageDialog(null, "Hết bộ nhớ!");
		}
		if(obj == this.btnDeleteImp) {
			
				if(btnDeleteImp.getText().equals("XÓA")) {
					if (JOptionPane.showConfirmDialog(null, "Xóa đơn nhập hàng này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
						impBus.disable(txImpID.getText());
						searchAndSortImp();
						searchAndSortDetail();
						btnDeleteImp.setText("MỞ");
						this.lockImp();
						islocked = 2;
						this.btnAddDetail.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Xóa thành công!");
					}
				}else {
					if (JOptionPane.showConfirmDialog(null, "Mở khóa đơn nhập hàng này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
						impBus.active(txImpID.getText());
						searchAndSortImp();
						searchAndSortDetail();
						btnDeleteImp.setText("XÓA");
						this.btnAddDetail.setEnabled(true);
						islocked = 0;
						this.btnAddDetail.setEnabled(true);
						JOptionPane.showMessageDialog(null, "Mở thành công!");
					}
				}
		}
		if(obj == this.btnUpdateImp) {
			if (JOptionPane.showConfirmDialog(null, "Sửa đơn nhập hàng này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
				boolean check = true;
				if (!txDate.getText().matches("^[0-9][0-9]/[0-9][0-9]/[0-9]{4}$")) {
					JOptionPane.showMessageDialog(null, "Ngày phải có dạng dd/MM/yyyy!");
					check = false;
				}else {
					try {
						DateExe.isValidDate(txDate.getText());
					}
					catch(Exception p)
					{
						JOptionPane.showMessageDialog(null, "Ngày không hợp lệ!");
						check = false;
					}
					
				}
				if (check) {
					Import temp = new Import(txImpID.getText(),txEmpID.getText(),txSupID.getText(),txDate.getText(),islocked);
					impBus.update(temp);
					searchAndSortImp();	
					JOptionPane.showMessageDialog(null, "Sửa thành công!");
				}
				
			}
		}
		if(obj == this.btnLockImp) {
			if (JOptionPane.showConfirmDialog(null, "Xác nhận đơn hàng này đã được thanh toán(Cập nhật vào số lượng tồn kho)?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
				impdetailBus.updateStorage(impdetailArr);
				impBus.lock(impID);
				clearImp();
				searchAndSortImp();
				JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
			}
		}
		if(obj == this.btnSelectSup) {
			RecordTable rec = new RecordTable(empID,"sup");
			rec.setLocationRelativeTo(null);
			rec.setModal(true);
			rec.setVisible(true);
			Supplier temp = rec.getSup();
			if(temp != null) {
				txSupID.setText(temp.getSup_id());
				searchAndSortImp();
			}
		}
		if(obj == this.btnApplyImp) {
			this.searchAndSortImp();
			this.clearImp(); this.lockImp();
			this.btnAddDetail.setEnabled(false);
			this.searchAndSortDetail();
		}
		if(obj == this.btnAddDetail) {
			AddImportDetailDialog dial = new AddImportDetailDialog(empID,impID,"",0,0);
			dial.setLocationRelativeTo(null);
			dial.setModal(true);
			dial.setVisible(true);
			ImportDetail temp = dial.getImpDetail();
			if(temp != null) {
				impdetailBus.add(temp);
				this.searchAndSortImp();
				this.searchAndSortDetail();
				this.updateImpDislay();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			}
		}
		if(obj == this.btnDeleteDetail) {
			if (JOptionPane.showConfirmDialog(null, "Xóa chi tiết này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
				impdetailBus.delete(txImpID.getText(),String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 0)));
				this.searchAndSortImp();
				searchAndSortDetail();
				this.updateImpDislay();
				JOptionPane.showMessageDialog(null, "Xóa thành công!");
			}
		}
		if(obj == this.btnUpdateDetail) {
			if (JOptionPane.showConfirmDialog(null, "Sửa chi tiết này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
				boolean check = true;
				if(txProdID.getText().substring(0, 2).equals("IN")) {
					if(!txAmount.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
						JOptionPane.showMessageDialog(null, "Số lượng là một con số > 0!");
						check = false;
					}
				}else {
					if(!txAmount.getText().matches("^[1-9]\\d*$")) {
						JOptionPane.showMessageDialog(null, "Số lượng là một con số nguyên > 0!");
						check = false;
					}
				}
				if(!txUnitPrice.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
					JOptionPane.showMessageDialog(null, "Đơn giá là một con số! > 0");
					check = false;
				}
				if (check) {
					ImportDetail temp = new ImportDetail(impID,txProdID.getText(),Double.parseDouble(txAmount.getText()),Double.parseDouble(txUnitPrice.getText()));
					if(txProdID.getText().equals(String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 0)))) {
						impdetailBus.update(temp);
						this.searchAndSortImp();
						searchAndSortDetail();
						this.updateImpDislay();
						clearDetail();
						lockDetail();
						JOptionPane.showMessageDialog(null, "Sửa thành công!");
					}else if(impdetailBus.searchByID(impID, txProdID.getText()) == null) {
						impdetailBus.delete(impID, String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 0)));
						impdetailBus.add(temp);
						this.searchAndSortImp();
						searchAndSortDetail();
						this.updateImpDislay();
						clearDetail();
						lockDetail();
						JOptionPane.showMessageDialog(null, "Sửa thành công!");
					}else {
						JOptionPane.showMessageDialog(null, "Chi tiết đã tồn tại!");
					}
				}
			}
		}
		if(obj == this.btnSelectProd) {
			int check = JOptionPane.showConfirmDialog(null, "Thêm sản phẩm(YES) hay nguyên liệu(NO)?", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION);
			if ( check == 0) {
				RecordTable rec = new RecordTable(empID,"prod_imp");
				rec.setLocationRelativeTo(null);
				rec.setModal(true);
				rec.setVisible(true);
				Product temp = rec.getProd();
				if(temp != null) {
					this.txProdID.setText(temp.getProd_id());
					this.txProdName.setText(prodBus.searchByID(temp.getProd_id()).getProd_name());
					this.txMeasure.setText(prodBus.searchByID(temp.getProd_id()).getMeasure());
				}
				
			}else if(check == 1){
				RecordTable rec = new RecordTable(empID,"ing");
				rec.setLocationRelativeTo(null);
				rec.setModal(true);
				rec.setVisible(true);
				Ingredient temp = rec.getIng();
				if(temp != null) {
					this.txProdID.setText(temp.getIng_id());
					this.txProdName.setText(ingBus.searchByID(temp.getIng_id()).getIng_name());
					this.txMeasure.setText(ingBus.searchByID(temp.getIng_id()).getMeasure());
				}
			}
		}
		if(obj == this.btnApplyDetail) {
			this.searchAndSortDetail();
			this.clearDetail(); this.lockDetail();
			this.btnAddDetail.setEnabled(false);
		}
		if (obj == this.btexport) {
			btexportAction();
		} 
		if (obj == this.btimport) {
			btimportAction();
			loadImpData(impArr);	
		}
		
	}
	public void btimportAction() {
		Excel excel = new Excel();
		ExcelImportDialog dialogexcel = new ExcelImportDialog();
		dialogexcel.setLocationRelativeTo(null);
		dialogexcel.setModal(true);
		dialogexcel.setVisible(true);
		int select = dialogexcel.getConfirm();
		if (select == 0)
			return;
		else if (select == 1) {
			String link1=dialogexcel.getImport();
			String link2=dialogexcel.getImportDetail();
			if(this.checkFile(link1) + this.checkFile(link2) == 2) {
				if(excel.checkfile(link1) && excel.checkfile(link2)) {
					ArrayList<Import> arr = excel.readImport(link1);
					if (arr == null)
						return;
					for (Import p : arr) {
						Import temp = impBus.searchByID(p.getImp_id());
						if (temp == null)
							impBus.add(p);
						else
							impBus.update(p);
					}
					ArrayList<ImportDetail> arr2 = excel.readImportDetail(link2);
					if (arr2 == null)
						return;
					for (ImportDetail p : arr2) {
						ImportDetail temp = impdetailBus.searchByID(p.getImp_id(),p.getProd_id());
						if (temp == null)
							impdetailBus.add(p);
						else
							impdetailBus.update(p);
					}
					JOptionPane.showMessageDialog(this, "Đã cập nhật lại dữ liệu thành công", "Cập nhật dữ liệu",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(this, "Những file này không tồn tại!");
			}
		}
	}

	public void btexportAction() {
		Excel excel = new Excel();
		int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn ghi dữ liệu vào file excel?", "Ghi file excel",
				JOptionPane.YES_NO_OPTION);
		if (a == 0) {
			excel.writeImport();
			excel.writeImportDetail();
			JOptionPane.showMessageDialog(this, "Đã ghi dữ liệu vào file excel thành công!");
		}
	}
	public int checkFile(String path) {
		File f = new File(path);
		if (f.exists()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public boolean getAuthority() {
		return this.authority;
	}
	public void setImpColSize() {
		TableColumnModel col = tableImp.getColumnModel();
		this.setFixColSize(col, 0, 60);
		this.setFixColSize(col, 1, 160);
		this.setFixColSize(col, 2, 160);
		this.setFixColSize(col, 3, 100);
		this.setFixColSize(col, 4, 100);
		this.setFixColSize(col, 5, 50);
	
	}
	public void setDetailColSize() {
		TableColumnModel col = tableDetail.getColumnModel();
		this.setFixColSize(col, 0, 100);
		this.setFixColSize(col, 1, 200);
		this.setFixColSize(col, 2, 100);
		this.setFixColSize(col, 3, 100);
		this.setFixColSize(col, 4, 60);
	
	}
	public void setFixColSize(TableColumnModel col, int index, int size) {
		col.getColumn(index).setMinWidth(size);
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
	public void updateImpDislay() {
		Import p = impBus.searchByID(impID);
		if(p != null) this.showImpTableData(p);
	}
	public void searchAndSortImp() {
		this.getImpData();
		boolean check = true;
		String from = "",to = "";
		try {
			if(txDateFrom.getDate() == null) from = "null";
			else DateExe.isValidDate(DateExe.convertDateToString(txDateFrom.getDate()));
			if(txDateTo.getDate() == null) to = "null";
			else DateExe.isValidDate(DateExe.convertDateToString(txDateTo.getDate()));
		}
		catch(Exception p)
		{
			JOptionPane.showMessageDialog(null, "Ngày không hợp lệ!"); check = false;
		}
		if(check) {
			if(!from.equals("null")) from = DateExe.convertDateToString(txDateFrom.getDate());
			if(!to.equals("null")) to = DateExe.convertDateToString(txDateTo.getDate());
			impArr = impBus.sort(impBus.search(impArr, txSearchImp.getText(), this.getSelectedButtonText(bg1), from, to),
				String.valueOf(this.comboImpType.getSelectedItem()), String.valueOf(this.comboImpOrder.getSelectedItem()));
			this.loadImpData(impArr);
			
			if(impArr.size() == 0) JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả nào!");
		}
	}
	public void searchAndSortDetail() {
		this.getDetailData(impID);
		impdetailArr = impdetailBus.sort(impdetailBus.search(impdetailArr, txSearchDetail.getText(), this.getSelectedButtonText(bg2)),
				String.valueOf(this.comboDetailType.getSelectedItem()), String.valueOf(this.comboDetailOrder.getSelectedItem()));
		this.loadDetailData(impdetailArr);
		
	}
	public void showImpTableData(Import p) {
		this.txImpID.setText(p.getImp_id());
		this.txSupID.setText(p.getSup_id());
		this.txEmpID.setText(p.getEmp_id());
		this.txDate.setText(p.getImport_dateStr());
		this.txTotal.setText(String.valueOf(this.impdetailBus.getTotalPrice(p.getImp_id())));
	}
	public void showDetailTableData(ImportDetail p) {
		Ingredient ingTemp = null;
		Product prodTemp = null;
		boolean check = false;
		if(p.getProd_id().substring(0, 2).equals("IN")) {
			check = false;
			 ingTemp = ingBus.searchByID(p.getProd_id());
		}else  {
			check = true;
			prodTemp = prodBus.searchByID(p.getProd_id());
		}
		
		this.txProdID.setText(p.getProd_id());
		this.txProdName.setText(check?prodTemp.getProd_name():ingTemp.getIng_name());
		this.txAmount.setText(check?String.valueOf((int)p.getAmount()):String.valueOf(p.getAmount()));
		this.txUnitPrice.setText(String.valueOf(p.getUnit_price()));
		this.txMeasure.setText(check?prodTemp.getMeasure():ingTemp.getMeasure());
	}
	public void loadImpData(ArrayList<Import> arr) {
		String[] header = new String[] {"Mã NH","Tên NV","Tên NCC","Ngày nhập","Thành tiền","Tình trạng"};
		DefaultTableModel md = new DefaultTableModel(header, 0);
		for (Import p : arr) {
			Object[] row;
				row = new Object[]{ p.getImp_id(), empBus.searchByID(p.getEmp_id()).getEmp_name(), supBus.searchByID(p.getSup_id()).getSup_name(),
						p.getImport_dateStr(), this.impdetailBus.getTotalPrice(p.getImp_id()), p.getStatus() == 1?"Đã nhận":p.getStatus() == 2?"Đã xóa":"" };
			md.addRow(row);
		}
		tableImp.setModel(md);
		this.setImpColSize();
	}
	public void loadDetailData(ArrayList<ImportDetail> arr) {
		String[] header = new String[] {"Mã SP","Tên SP","Số lượng","Đơn giá","Đ.vị"};
		DefaultTableModel md = new DefaultTableModel(header, 0);
		for (ImportDetail p : arr) {
			Object[] row;
				row = new Object[]{ p.getProd_id(), p.getProd_id().substring(0, 2).equals("IN")?ingBus.searchByID(p.getProd_id()).getIng_name():prodBus.searchByID(p.getProd_id()).getProd_name(),
						p.getAmount(), p.getUnit_price(),
						p.getProd_id().substring(0, 2).equals("IN")?ingBus.searchByID(p.getProd_id()).getMeasure():prodBus.searchByID(p.getProd_id()).getMeasure()};
			md.addRow(row);
		}
		tableDetail.setModel(md);
		this.setDetailColSize();
	}
	public void getImpData() {
		if(this.authority) this.impArr = impBus.readAll();
		else {
			this.impArr = impBus.searchByEmpID(this.empID);
		}
	}
	public void getDetailData(String impID) {
		this.impdetailArr = this.impdetailBus.searchByImportID(impID);
	}
	public void lockImp() {
		
		this.btnSelectSup.setEnabled(false);
		
		this.btnDeleteImp.setEnabled(false);
		this.btnUpdateImp.setEnabled(false);
		this.btnLockImp.setEnabled(false);
		this.btnPDF.setEnabled(false);
		this.lockDetail();
		
	}
	public void lockDetail() {
		
		this.txAmount.setEditable(false);
		this.txUnitPrice.setEditable(false);
		this.btnSelectProd.setEnabled(false);
		this.btnUpdateDetail.setEnabled(false);
		this.btnDeleteDetail.setEnabled(false);
		
	}
	public void unlockImp() {
		
		this.btnSelectSup.setEnabled(true);
		
		
		this.btnDeleteImp.setEnabled(true);
		this.btnUpdateImp.setEnabled(true);
		this.btnLockImp.setEnabled(true);
		this.btnPDF.setEnabled(true);
		this.unlockDetail();
	}
	public void unlockDetail() {
		
		this.txAmount.setEditable(true);
		this.txUnitPrice.setEditable(true);
		this.btnSelectProd.setEnabled(true);
		this.btnUpdateDetail.setEnabled(true);
		this.btnDeleteDetail.setEnabled(true);
		
	}
	public void clearImp() {
		impID = "";
		islocked = 0;
		this.txImpID.setText("");
		this.txSupID.setText("");
		this.txEmpID.setText("");
		this.txDate.setText("");
		this.txTotal.setText("");
		this.clearDetail();
	}
	public void clearDetail() {
		this.txProdID.setText("");
		this.txProdName.setText("");
		this.txAmount.setText("");
		this.txUnitPrice.setText("");
		this.txMeasure.setText("");
	}
}
