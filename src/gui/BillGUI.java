package gui;

import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;
import dto.*;
import bus.*;
import design.myTable;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;

public class BillGUI extends JPanel implements ActionListener {
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
	private JTextField txBillID;
	private JTextField txEmpID;
	private JTextField txCusID;
	private JButton btnUpdateBill;
	private JButton btnSelectCus;
	private JButton btnAddBill;
	private JButton btnLockBill;
	private JLabel lblMSp;
	private JLabel lblSLng;
	private JLabel lblnGi;
	private JTextField txProdID;
	private JTextField txAmount;
	private JTextField txUnitPrice;
	private JLabel lblGiKm;
	private JTextField txSalePrice;
	private JLabel lblThnhTin;
	private JTextField txTotal;
	private JTextField txMeasure;
	private JButton btnDeleteBill;
	private JButton btnAddDetail;
	private JButton btnDeleteDetail;
	private JButton btnUpdateDetail;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTable tableBill;
	private JTable tableDetail;
	private JLabel lblKhuynMi;
	private JTextField txSaleTotal;
	private JPanel panel_6;
	private JTextField txSearchBill;
	private JRadioButton rdbtnH;
	private JRadioButton rdbtnNv;
	private JRadioButton rdbtnKh;
	private JPanel panel_7;
	private JDateChooser txDateFrom;
	private JDateChooser txDateTo;
	private JLabel lblT;
	private JLabel lbln;
	private JPanel panel_8;
	private JComboBox comboBillType;
	private JComboBox comboBillOrder;
	private JButton btnApplyBill;
	private String billID = "";
	private int islocked = 0;

	/**
	 * Create the panel.
	 */

	private boolean authority;
	private String empID;
	private EmployerBUS empBus = new EmployerBUS();
	private CustomerBUS cusBus = new CustomerBUS();
	private BillBUS billBus = new BillBUS();
	private BillDetailBUS billdetailBus = new BillDetailBUS();
	private ProductBUS prodBus = new ProductBUS();
	private SaleBUS saleBus = new SaleBUS();
	private BillSaleBUS billsaleBus = new BillSaleBUS();
	private ArrayList<Bill> billArr;
	private ArrayList<BillDetail> billdetailArr;
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
	private JLabel lblMKm;
	private JTextField txBillSaleID;
	private JButton btnSelectProd;
	private JLabel label;
	private JTextField txProdSaleID;
	private JButton btnSelectBillSale;
	private JButton btnSelectProdSale;
	private JTextField txDate;
	private JButton btnBillSaleClear;
	private JButton btnProdSaleClear;
	private JButton btimport;
	private JButton btexport;
	private JButton btnPDF;

	public BillGUI(String id) {
		design.Icon icon = new design.Icon();
		this.authority = empBus.searchByID(id).isEmp_type();
		this.empID = empBus.searchByID(id).getEmp_id();

		this.setSize(1000, 700);
		setLayout(null);
		this.setBackground(icon.getContentColor());
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin h\u00F3a \u0111\u01A1n", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 253, 337);
		panel.setBackground(icon.getPaneColor());
		add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Mã HĐ:");
		lblNewLabel.setBounds(29, 29, 58, 26);
		panel.add(lblNewLabel);

		lblMNv = new JLabel("Mã NV:");
		lblMNv.setBounds(29, 66, 58, 26);
		panel.add(lblMNv);

		lblMKh = new JLabel("Mã KH:");
		lblMKh.setBounds(29, 103, 58, 26);
		panel.add(lblMKh);

		lblNgyLp = new JLabel("Ngày lập:");
		lblNgyLp.setBounds(29, 140, 58, 26);
		panel.add(lblNgyLp);

		txBillID = new JTextField();
		txBillID.setEditable(false);
		txBillID.setBounds(86, 27, 107, 30);
		panel.add(txBillID);
		txBillID.setColumns(10);

		txEmpID = new JTextField();
		txEmpID.setEditable(false);
		txEmpID.setColumns(10);
		txEmpID.setBounds(86, 62, 107, 30);
		panel.add(txEmpID);

		txCusID = new JTextField();
		txCusID.setEditable(false);
		txCusID.setColumns(10);
		txCusID.setBounds(86, 99, 107, 30);
		panel.add(txCusID);

		btnUpdateBill = new JButton("SỬA");
		btnUpdateBill.setBounds(132, 296, 111, 30);
		btnUpdateBill.setBackground(icon.getEditColor());
		btnUpdateBill.setForeground(Color.white);
		btnUpdateBill.setFont(new Font("sans-serif", Font.BOLD, 14));
		btnUpdateBill.setIcon(icon.imageIconSize("icons/edit.png", 20, 20));
		panel.add(btnUpdateBill);

		btnSelectCus = new JButton("...");
		btnSelectCus.setBounds(202, 101, 41, 30);

		panel.add(btnSelectCus);

		lblThnhTin = new JLabel("Thành tiền:");
		lblThnhTin.setBounds(22, 178, 65, 26);
		panel.add(lblThnhTin);

		txTotal = new JTextField();
		txTotal.setEditable(false);
		txTotal.setColumns(10);
		txTotal.setBounds(86, 176, 107, 30);
		panel.add(txTotal);

		lblKhuynMi = new JLabel("Khuyến mãi:");
		lblKhuynMi.setBounds(17, 217, 70, 26);
		panel.add(lblKhuynMi);

		txSaleTotal = new JTextField();
		txSaleTotal.setEditable(false);
		txSaleTotal.setColumns(10);
		txSaleTotal.setBounds(86, 215, 107, 30);
		panel.add(txSaleTotal);

		lblMKm = new JLabel("Mã KM:");
		lblMKm.setBounds(29, 259, 58, 26);
		panel.add(lblMKm);

		txBillSaleID = new JTextField();
		txBillSaleID.setEditable(false);
		txBillSaleID.setColumns(10);
		txBillSaleID.setBounds(86, 255, 107, 30);
		panel.add(txBillSaleID);

		btnSelectBillSale = new JButton("...");
		btnSelectBillSale.setBounds(202, 255, 41, 30);
		panel.add(btnSelectBillSale);

		txDate = new JTextField();
		txDate.setEditable(false);
		txDate.setBounds(86, 138, 107, 30);
		panel.add(txDate);
		txDate.setColumns(10);

		btnBillSaleClear = new JButton("XÓA KM");
		btnBillSaleClear.setBackground(icon.getDeleteColor());
		btnBillSaleClear.setForeground(Color.white);
		btnBillSaleClear.setFont(new Font("sans-serif", Font.BOLD, 14));
		btnBillSaleClear.setIcon(icon.imageIconSize("icons/delete.png", 20, 20));
		btnBillSaleClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txBillSaleID.setText("");
				if (txTotal.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
					txSaleTotal.setText(txTotal.getText());
				} else {
					txSaleTotal.setText("Error");
				}
			}
		});
		btnBillSaleClear.setEnabled(false);
		btnBillSaleClear.setBounds(11, 296, 111, 30);
		panel.add(btnBillSaleClear);

		panel_1 = new JPanel();
		panel_1.setBounds(273, 11, 705, 337);
		panel_1.setBorder(
				new TitledBorder(null, "Danh sách hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(icon.getPaneColor());
		add(panel_1);
		panel_1.setLayout(null);

		panel_4 = new JPanel();
		panel_4.setBounds(10, 101, 670, 192);
		panel_4.setBackground(icon.getPaneColor());
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panel_4.add(scrollPane);

		tableBill = new myTable();
		tableBill.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Bill temp = billBus.searchByID((String.valueOf(tableBill.getValueAt(tableBill.getSelectedRow(), 0))));
				showBillTableData(temp);
				billID = temp.getBill_id();
				islocked = temp.getStatus();
				if (!temp.getEmp_id().equals(empID) || islocked == 1 || islocked == 2) {
					lockBill();
					btnAddDetail.setEnabled(false);
					if (!authority) {
						if (temp.getEmp_id().equals(empID) && islocked == 2) {
							btnDeleteBill.setText("MỞ");
							btnDeleteBill.setEnabled(true);
						}
					}
				} else {
					if (!authority) {
						btnDeleteBill.setText("XÓA");
						unlockBill();
						btnAddDetail.setEnabled(true);
					}
				}
				clearDetail();
				lockDetail();
				billdetailArr = billdetailBus.searchByBillID(temp.getBill_id());
				loadDetailData(billdetailArr);
			}
		});
		tableBill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Bill temp = billBus.searchByID((String.valueOf(tableBill.getValueAt(tableBill.getSelectedRow(), 0))));
				showBillTableData(temp);
				billID = temp.getBill_id();
				islocked = temp.getStatus();
				if (!temp.getEmp_id().equals(empID) || islocked == 1 || islocked == 2) {
					lockBill();
					btnAddDetail.setEnabled(false);
					if (!authority) {
						if (temp.getEmp_id().equals(empID) && islocked == 2) {
							btnDeleteBill.setText("MỞ");
							btnDeleteBill.setEnabled(true);
						}
					}
				} else {
					if (!authority) {
						btnDeleteBill.setText("XÓA");
						unlockBill();
						btnAddDetail.setEnabled(true);
					}
				}
				clearDetail();
				lockDetail();
				billdetailArr = billdetailBus.searchByBillID(temp.getBill_id());
				loadDetailData(billdetailArr);
			}
		});
		scrollPane.setViewportView(tableBill);

		panel_6 = new JPanel();
		panel_6.setBorder(
				new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(0, 0, 203, 101);
		panel_6.setBackground(icon.getPaneColor());
		panel_1.add(panel_6);
		panel_6.setLayout(null);

		txSearchBill = new JTextField();
		txSearchBill.setBounds(10, 21, 183, 30);
		panel_6.add(txSearchBill);
		txSearchBill.setColumns(10);

		rdbtnH = new JRadioButton("HĐ");
		rdbtnH.setSelected(true);
		rdbtnH.setBounds(20, 58, 48, 23);
		panel_6.add(rdbtnH);

		rdbtnNv = new JRadioButton("NV");
		rdbtnNv.setBounds(145, 58, 48, 23);
		panel_6.add(rdbtnNv);

		rdbtnKh = new JRadioButton("KH");
		rdbtnKh.setBounds(84, 58, 48, 23);
		panel_6.add(rdbtnKh);

		panel_7 = new JPanel();
		panel_7.setBorder(
				new TitledBorder(null, "Ng\u00E0y l\u1EADp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(208, 0, 203, 101);
		panel_7.setBackground(icon.getPaneColor());
		panel_1.add(panel_7);
		panel_7.setLayout(null);

		txDateFrom = new JDateChooser();
		txDateFrom.setDateFormatString("dd/MM/yyyy");
		txDateFrom.setBounds(55, 22, 138, 30);
		panel_7.add(txDateFrom);

		txDateTo = new JDateChooser();
		txDateTo.setDateFormatString("dd/MM/yyyy");
		txDateTo.setBounds(55, 55, 138, 30);
		panel_7.add(txDateTo);

		lblT = new JLabel("Từ:");
		lblT.setBounds(10, 22, 41, 26);
		panel_7.add(lblT);

		lbln = new JLabel("Đến:");
		lbln.setBounds(10, 55, 41, 26);
		panel_7.add(lbln);

		panel_8 = new JPanel();
		panel_8.setBorder(
				new TitledBorder(null, "S\u1EAFp x\u1EBFp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(414, 0, 171, 101);
		panel_8.setBackground(icon.getPaneColor());
		panel_1.add(panel_8);
		panel_8.setLayout(null);

		String[] billtype = { "Mã HĐ", "Mã NV", "Mã KH", "Ngày lập", "Thành tiền", "Khuyến mãi" };
		comboBillType = new JComboBox(billtype);
		comboBillType.setBounds(10, 22, 151, 30);
		panel_8.add(comboBillType);

		String[] order = { "Tăng dần", "Giảm dần" };
		comboBillOrder = new JComboBox(order);
		comboBillOrder.setBounds(10, 60, 151, 30);
		panel_8.add(comboBillOrder);

		btnApplyBill = new JButton("ÁP DỤNG");
		btnApplyBill.setBackground(icon.getSearchColor());
		btnApplyBill.setFont(new Font("sans-serif", Font.BOLD, 14));
		;
		btnApplyBill.setForeground(Color.black);
		btnApplyBill.setIcon(icon.imageIconSize("icons/search_more.png", 20, 20));
		btnApplyBill.setBounds(585, 11, 115, 79);
		panel_1.add(btnApplyBill);

		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Th\u00F4ng tin chi ti\u1EBFt h\u00F3a \u0111\u01A1n",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 359, 253, 300);
		panel_2.setBackground(icon.getPaneColor());
		add(panel_2);
		panel_2.setLayout(null);

		lblMSp = new JLabel("Mã SP:");
		lblMSp.setBounds(26, 25, 58, 26);
		panel_2.add(lblMSp);

		lblSLng = new JLabel("Số lượng:");
		lblSLng.setBounds(26, 105, 58, 26);
		panel_2.add(lblSLng);

		lblnGi = new JLabel("Đơn giá:");
		lblnGi.setBounds(26, 142, 58, 26);
		panel_2.add(lblnGi);

		txProdID = new JTextField();
		txProdID.setEditable(false);
		txProdID.setColumns(10);
		txProdID.setBounds(84, 23, 114, 30);
		panel_2.add(txProdID);

		txAmount = new JTextField();
		txAmount.setEditable(false);
		txAmount.setColumns(10);
		txAmount.setBounds(84, 103, 89, 30);
		panel_2.add(txAmount);

		txUnitPrice = new JTextField();
		txUnitPrice.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
			}

			public void removeUpdate(DocumentEvent e) {
				warn();
			}

			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				if (txUnitPrice.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
					double u = Double.parseDouble(txUnitPrice.getText());
					Sale temp = saleBus.searchByID(txProdSaleID.getText());
					if (temp != null) {
						if (temp.getMeasure().equals("%")) {
							u = u * temp.getNumber() / 100;
						} else
							u = u - temp.getNumber();
						if (u < 0)
							u = 0;
					}
					txSalePrice.setText(String.valueOf(u));
				} else {
					txSalePrice.setText("Error");
				}
			}
		});
		txUnitPrice.setEditable(false);
		txUnitPrice.setColumns(10);
		txUnitPrice.setBounds(84, 140, 114, 30);
		panel_2.add(txUnitPrice);

		lblGiKm = new JLabel("Giá KM:");
		lblGiKm.setBounds(26, 181, 58, 26);
		panel_2.add(lblGiKm);

		txSalePrice = new JTextField();
		txSalePrice.setEditable(false);
		txSalePrice.setColumns(10);
		txSalePrice.setBounds(84, 179, 114, 30);
		panel_2.add(txSalePrice);

		txMeasure = new JTextField();
		txMeasure.setEditable(false);
		txMeasure.setText("");
		txMeasure.setBounds(179, 103, 64, 30);
		panel_2.add(txMeasure);
		txMeasure.setColumns(10);

		btnUpdateDetail = new JButton("SỬA");
		btnUpdateDetail.setBackground(icon.getEditColor());
		btnUpdateDetail.setForeground(Color.white);
		btnUpdateDetail.setFont(new Font("sans-serif", Font.BOLD, 14));
		btnUpdateDetail.setIcon(icon.imageIconSize("icons/edit.png", 20, 20));
		btnUpdateDetail.setEnabled(false);
		btnUpdateDetail.setBounds(130, 259, 114, 30);
		panel_2.add(btnUpdateDetail);

		txProdName = new JTextField();
		txProdName.setEditable(false);
		txProdName.setColumns(10);
		txProdName.setBounds(84, 62, 159, 30);
		panel_2.add(txProdName);

		lblTnSp = new JLabel("Tên SP:");
		lblTnSp.setBounds(26, 64, 58, 26);
		panel_2.add(lblTnSp);

		btnSelectProd = new JButton("...");
		btnSelectProd.setBounds(201, 23, 41, 30);
		panel_2.add(btnSelectProd);

		label = new JLabel("Mã KM:");
		label.setBounds(26, 220, 58, 26);
		panel_2.add(label);

		txProdSaleID = new JTextField();
		txProdSaleID.setEditable(false);
		txProdSaleID.setColumns(10);
		txProdSaleID.setBounds(84, 218, 114, 30);
		panel_2.add(txProdSaleID);

		btnSelectProdSale = new JButton("...");
		btnSelectProdSale.setBounds(202, 218, 41, 30);
		panel_2.add(btnSelectProdSale);

		btnProdSaleClear = new JButton("XÓA KM");
		btnProdSaleClear.setBackground(icon.getDeleteColor());
		btnProdSaleClear.setForeground(Color.white);
		btnProdSaleClear.setFont(new Font("sans-serif", Font.BOLD, 14));
		;
		btnProdSaleClear.setIcon(icon.imageIconSize("icons/delete.png", 20, 20));
		btnProdSaleClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txProdSaleID.setText("");
				if (txTotal.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
					txSalePrice.setText(txUnitPrice.getText());
				} else {
					txSalePrice.setText("Error");
				}
			}
		});
		btnProdSaleClear.setEnabled(false);
		btnProdSaleClear.setBounds(9, 259, 111, 30);
		panel_2.add(btnProdSaleClear);

		panel_3 = new JPanel();
		panel_3.setBounds(273, 359, 705, 243);
		panel_3.setBackground(icon.getPaneColor());
		panel_3.setBorder(new TitledBorder(null, "Danh sách chi tiết hóa đơn", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		add(panel_3);
		panel_3.setLayout(null);

		panel_5 = new JPanel();
		panel_5.setBounds(10, 63, 670, 135);
		panel_5.setBackground(icon.getPaneColor());
		panel_3.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		scrollPane_1 = new JScrollPane();
		panel_5.add(scrollPane_1, BorderLayout.CENTER);

		tableDetail = new myTable();
		tableDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				BillDetail temp = billdetailBus.searchByID(billID,
						String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 0)));
				showDetailTableData(temp);
				if (!txEmpID.getText().equals(empID))
					lockDetail();
				else if (islocked == 1 || islocked == 2)
					lockDetail();
				else if (!authority) {
					unlockDetail();
				}
			}
		});
		tableDetail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				BillDetail temp = billdetailBus.searchByID(billID,
						String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 0)));
				showDetailTableData(temp);
				if (!txEmpID.getText().equals(empID))
					lockDetail();
				else if (islocked == 1 || islocked == 2)
					lockDetail();
				else if (!authority) {
					unlockDetail();
				}
			}
		});
		scrollPane_1.setViewportView(tableDetail);

		panel_9 = new JPanel();
		panel_9.setBorder(
				new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		panel_10.setBorder(
				new TitledBorder(null, "S\u1EAFp x\u1EBFp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setBounds(296, 0, 287, 64);
		panel_10.setBackground(icon.getPaneColor());
		panel_3.add(panel_10);
		panel_10.setLayout(null);

		String[] detailtype = { "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Giá KM" };
		comboDetailType = new JComboBox(detailtype);
		comboDetailType.setBounds(10, 23, 138, 30);
		panel_10.add(comboDetailType);

		comboDetailOrder = new JComboBox(order);
		comboDetailOrder.setBounds(158, 23, 119, 30);
		panel_10.add(comboDetailOrder);

		btnApplyDetail = new JButton("ÁP DỤNG");
		btnApplyDetail.setBackground(icon.getSearchColor());
		btnApplyDetail.setForeground(Color.black);
		btnApplyDetail.setFont(new Font("sans-serif", Font.BOLD, 14));
		btnApplyDetail.setIcon(icon.imageIconSize("icons/search_more.png", 20, 20));
		btnApplyDetail.setBounds(585, 11, 115, 41);
		panel_3.add(btnApplyDetail);

		bg1.add(rdbtnH);
		bg1.add(rdbtnKh);
		bg1.add(rdbtnNv);

		btnAddBill = new JButton("THÊM");
		if (authority)
			btnAddBill.setEnabled(false);
		btnAddBill.setBounds(10, 296, 140, 30);
		btnAddBill.setBackground(icon.getInsertColor());
		btnAddBill.setFont(new Font("sans-serif", Font.BOLD, 14));
		btnAddBill.setIcon(icon.imageIconSize("icons/add.png", 20, 20));
		btnAddBill.setForeground(Color.white);
		panel_1.add(btnAddBill);

		btnLockBill = new JButton("THANH TOÁN");
		btnLockBill.setEnabled(false);
		btnLockBill.setBackground(icon.getContentColor());
		btnLockBill.setForeground(Color.white);
		btnLockBill.setFont(new Font("sans-serif", Font.BOLD, 14));
		btnLockBill.setBounds(310, 296, 140, 30);
		panel_1.add(btnLockBill);

		btnDeleteBill = new JButton("XÓA");
		btnDeleteBill.setBackground(icon.getDeleteColor());
		btnDeleteBill.setForeground(Color.white);
		btnDeleteBill.setFont(new Font("sans-serif", Font.BOLD, 14));
		;
		btnDeleteBill.setIcon(icon.imageIconSize("icons/delete.png", 20, 20));
		btnDeleteBill.setBounds(160, 296, 140, 30);
		panel_1.add(btnDeleteBill);
		btnDeleteBill.setEnabled(false);
		bg2.add(rdbtnMSp);
		bg2.add(rdbtnTnSp);

		btnAddDetail = new JButton("THÊM");
		btnAddDetail.setBackground(icon.getInsertColor());
		btnAddDetail.setForeground(Color.white);
		btnAddDetail.setFont(new Font("sans-serif", Font.BOLD, 14));
		
		btnAddDetail.setIcon(icon.imageIconSize("icons/add.png", 20, 20));
		btnAddDetail.setBounds(20, 209, 140, 30);
		panel_3.add(btnAddDetail);
		btnAddDetail.setEnabled(false);

		btnDeleteDetail = new JButton("XÓA");
		btnDeleteDetail.setBackground(icon.getDeleteColor());
		btnDeleteDetail.setForeground(Color.white);
		btnDeleteDetail.setFont(new Font("sans-serif", Font.BOLD, 14));
		
		btnDeleteDetail.setIcon(icon.imageIconSize("icons/delete.png", 20, 20));
		btnDeleteDetail.setBounds(170, 209, 140, 30);
		panel_3.add(btnDeleteDetail);
		btnDeleteDetail.setEnabled(false);
		
		btnPDF = new JButton("IN HÓA ĐƠN");
		btnPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PDFreport rp = new PDFreport();
				rp.BillReport(billID);
			}
		});
		btnPDF.setBounds(616, 610, 191, 35);
		add(btnPDF);
		btnPDF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
				btimport = new JButton();
				btimport.setBounds(814, 608, 40, 40);
				add(btimport);
				btimport.setToolTipText("Đọc dữ liệu từ file excel mà bạn chọn.");
				btimport.setBorder(null);
				btimport.setBackground(Color.WHITE);
				
						btimport.setIcon(icon.imageIconSize("icons/import2.png", 40, 40));
						btimport.setBackground(new Color(255, 255, 255));
						btimport.setBorder(null);
						
								btimport.addActionListener(this);
								
										btexport = new JButton();
										btexport.setBounds(864, 608, 40, 40);
										add(btexport);
										btexport.setToolTipText("Ghi dữ liệu vào file excel");
										btexport.setBorder(null);
										btexport.setBackground(Color.WHITE);
										
												btexport.setIcon(icon.imageIconSize("icons/export2.png", 40, 40));
												btexport.setBackground(new Color(255, 255, 255));
												btexport.setBorder(null);
												btexport.addActionListener(this);

		this.addActionListener();
		this.lockBill();
		this.lockDetail();
		this.getBillData();
		this.loadBillData(billArr);
		if(!this.authority) {
			btimport.setVisible(false);
			btexport.setVisible(false);
		}
	}

	public void addActionListener() {
		this.btnAddBill.addActionListener(this);
		this.btnDeleteBill.addActionListener(this);
		this.btnUpdateBill.addActionListener(this);
		this.btnLockBill.addActionListener(this);
		this.btnSelectCus.addActionListener(this);
		this.btnApplyBill.addActionListener(this);
		this.btnSelectBillSale.addActionListener(this);
		this.btnAddDetail.addActionListener(this);
		this.btnDeleteDetail.addActionListener(this);
		this.btnUpdateDetail.addActionListener(this);
		this.btnSelectProd.addActionListener(this);
		this.btnApplyDetail.addActionListener(this);
		this.btnSelectProdSale.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == this.btnAddBill) {
			if (billBus.readAll().size() < 100000) {
				AddBillDialog dial = new AddBillDialog(empID, "");
				dial.setLocationRelativeTo(null);
				dial.setModal(true);
				dial.setVisible(true);
				Bill temp = dial.getBill();
				if (temp != null) {
					billBus.add(temp);
					dial.updateBillDetail();
					this.searchAndSortBill();
					this.searchAndSortDetail();
					JOptionPane.showMessageDialog(null, "Thêm thành công");
				}
			} else
				JOptionPane.showMessageDialog(null, "Hết bộ nhớ!");
		}
		if (obj == this.btnDeleteBill) {
			if (btnDeleteBill.getText().equals("XÓA")) {
				if (JOptionPane.showConfirmDialog(null, "Xóa hóa đơn này?", "Xác nhận",
						JOptionPane.YES_NO_OPTION) == 0) {
					billBus.disable(txBillID.getText());
					searchAndSortBill();
					searchAndSortDetail();
					btnDeleteBill.setText("MỞ");
					lockBill();
					islocked = 2;
					BillSale temp = billsaleBus.searchByBillID(billID);
					if (temp != null)
						billsaleBus.disable(temp.getSale_id(), temp.getBill_id());
					this.btnAddDetail.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Xóa thành công!");
				}
			} else {
				if (JOptionPane.showConfirmDialog(null, "Mở khóa hóa đơn này?", "Xác nhận",
						JOptionPane.YES_NO_OPTION) == 0) {
					billBus.active(txBillID.getText());
					searchAndSortBill();
					searchAndSortDetail();
					btnDeleteBill.setText("XÓA");
					// unlockBill();
					this.btnAddDetail.setEnabled(true);
					islocked = 0;
					BillSale temp = billsaleBus.searchByBillID(billID);
					if (temp != null)
						billsaleBus.active(temp.getSale_id(), temp.getBill_id());
					this.btnAddDetail.setEnabled(true);
					JOptionPane.showMessageDialog(null, "Mở thành công!");
				}
			}
		}
		if (obj == this.btnUpdateBill) {
			if (JOptionPane.showConfirmDialog(null, "Sửa hóa đơn này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
				boolean check = true;
				if (!txDate.getText().matches("^[0-9][0-9]/[0-9][0-9]/[0-9]{4}$")) {
					JOptionPane.showMessageDialog(null, "Ngày phải có dạng dd/MM/yyyy!");
					check = false;
				} else {
					try {
						DateExe.isValidDate(txDate.getText());
					} catch (Exception p) {
						JOptionPane.showMessageDialog(null, "Ngày không hợp lệ!");
						check = false;
					}

				}
				if (check) {
					Bill temp = new Bill(txBillID.getText(), txEmpID.getText(), txCusID.getText(), txDate.getText(),
							txBillSaleID.getText(), Double.parseDouble(this.txSaleTotal.getText()), islocked);
					billBus.update(temp);
					searchAndSortBill();
					if (!txBillSaleID.getText().equals("")) {
						BillSale billsale = new BillSale(txBillSaleID.getText(), billID, 0);
						BillSale toDelete = billsaleBus.searchByBillID(billID);
						if(toDelete != null) billsaleBus.delete(toDelete.getSale_id(), toDelete.getBill_id());
						billsaleBus.add(billsale);
					} else {
						BillSale toDelete = billsaleBus.searchByBillID(billID);
						billsaleBus.delete(toDelete.getSale_id(), toDelete.getBill_id());
					}
					JOptionPane.showMessageDialog(null, "Sửa thành công!");
				}

			}
		}
		if (obj == this.btnLockBill) {
			if (JOptionPane.showConfirmDialog(null,
					"Xác nhận hóa đơn này đã được thanh toán(Cập nhật vào số lượng tồn kho)?", "Xác nhận",
					JOptionPane.YES_NO_OPTION) == 0) {
				if (billdetailBus.updateStorage(billdetailArr)) {
					billBus.lock(billID);
					clearBill();
					searchAndSortBill();
					JOptionPane.showMessageDialog(null, "Cập nhật kho hàng thành công!");
				} else
					JOptionPane.showMessageDialog(null, "Kho hàng không đủ nguồn đáp ứng!!");

			}
		}
		if (obj == this.btnSelectCus) {
			RecordTable rec = new RecordTable(empID, "cus");
			rec.setLocationRelativeTo(null);
			rec.setModal(true);
			rec.setVisible(true);
			Customer temp = rec.getCus();
			if (temp != null) {
				txCusID.setText(temp.getCus_id());
				searchAndSortBill();
			}
		}
		if (obj == this.btnSelectBillSale) {
			RecordTable rec = new RecordTable(empID, "bill_sale");
			rec.getBillData(Double.parseDouble(txTotal.getText()), countProdAmount());
			rec.setLocationRelativeTo(null);
			rec.setModal(true);
			rec.setVisible(true);
			Sale sale = rec.getSale();
			if (sale != null) {
				txBillSaleID.setText(sale.getSale_id());
				if (txTotal.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
					double u = Double.parseDouble(txTotal.getText());
					Sale temp = saleBus.searchByID(txBillSaleID.getText());
					if (temp != null) {
						if (temp.getMeasure().equals("%")) {
							u = u * temp.getNumber() / 100;
						} else
							u = u - temp.getNumber();
						if (u < 0)
							u = 0;
					}
					txSaleTotal.setText(String.valueOf(u));
				} else {
					txSaleTotal.setText("Đơn giá không đúng");
				}
			}
		}
		if (obj == this.btnApplyBill) {
			this.searchAndSortBill();
			this.clearBill();
			this.lockBill();
			this.btnAddDetail.setEnabled(false);
			this.searchAndSortDetail();
		}
		if (obj == this.btnAddDetail) {
			AddBillDetailDialog dial = new AddBillDetailDialog(empID, billID, "", 0, 0, txProdSaleID.getText());
			dial.setLocationRelativeTo(null);
			dial.setModal(true);
			dial.setVisible(true);
			BillDetail temp = dial.getBillDetail();
			if (temp != null) {
				billdetailBus.add(temp);
				// this.searchAndSortBill();
				this.searchAndSortDetail();
				this.updateBillDislay();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			}
		}
		if (obj == this.btnDeleteDetail) {
			if (JOptionPane.showConfirmDialog(null, "Xóa chi tiết này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
				billdetailBus.delete(txBillID.getText(),
						String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 0)));
				// this.searchAndSortBill();
				searchAndSortDetail();
				this.updateBillDislay();
				JOptionPane.showMessageDialog(null, "Xóa thành công!");
			}
		}
		if (obj == this.btnUpdateDetail) {
			if (JOptionPane.showConfirmDialog(null, "Sửa chi tiết này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
				boolean check = true;
				if (txProdID.getText().substring(0, 2).equals("IN")) {
					if (!txAmount.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
						JOptionPane.showMessageDialog(null, "Số lượng là một con số > 0!");
						check = false;
					}
				} else {
					if (!txAmount.getText().matches("^[1-9]\\d*$")) {
						JOptionPane.showMessageDialog(null, "Số lượng là một con số nguyên > 0!");
						check = false;
					}
				}
				if (!txUnitPrice.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
					JOptionPane.showMessageDialog(null, "Đơn giá là một con số! > 0");
					check = false;
				}
				if (check) {
					BillDetail temp = new BillDetail(billID, txProdID.getText(), Integer.parseInt(txAmount.getText()),
							Double.parseDouble(txUnitPrice.getText()), Double.parseDouble(txSalePrice.getText()),
							txProdSaleID.getText());
					if (txProdID.getText()
							.equals(String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 0)))) {
						billdetailBus.update(temp);
						// this.searchAndSortBill();
						searchAndSortDetail();
						this.updateBillDislay();
						clearDetail();
						lockDetail();
						JOptionPane.showMessageDialog(null, "Sửa thành công!");
					} else if (billdetailBus.searchByID(billID, txProdID.getText()) == null) {
						billdetailBus.delete(billID,
								String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 0)));
						// this.searchAndSortBill();
						billdetailBus.add(temp);
						searchAndSortDetail();
						this.updateBillDislay();
						clearDetail();
						lockDetail();
						JOptionPane.showMessageDialog(null, "Sửa thành công!");
					} else {
						JOptionPane.showMessageDialog(null, "Chi tiết đã tồn tại!");
					}
				}
			}
		}
		if (obj == this.btnSelectProd) {
			RecordTable rec = new RecordTable(empID, "prod");
			rec.setLocationRelativeTo(null);
			rec.setModal(true);
			rec.setVisible(true);
			Product temp = rec.getProd();
			if (temp != null) {
				this.txProdID.setText(temp.getProd_id());
				this.txProdName.setText(prodBus.searchByID(temp.getProd_id()).getProd_name());
				this.txMeasure.setText(prodBus.searchByID(temp.getProd_id()).getMeasure());
			}
		}
		if (obj == this.btnSelectProdSale) {
			RecordTable rec = new RecordTable(empID, "prod_sale");
			rec.getProdData(txProdID.getText());
			rec.setLocationRelativeTo(null);
			rec.setModal(true);
			rec.setVisible(true);
			Sale temp = rec.getSale();
			if (temp != null) {
				txProdSaleID.setText(temp.getSale_id());
				if (txUnitPrice.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
					double u = Double.parseDouble(txUnitPrice.getText());
					// Sale temp = saleBus.searchByID(txSaleID.getText());
					// if(temp != null) {
					if (temp.getMeasure().equals("%")) {
						u = u * temp.getNumber() / 100;
					} else
						u = u - temp.getNumber();
					// }
					if (u < 0)
						u = 0;
					txSalePrice.setText(String.valueOf(u));
				} else {
					txSalePrice.setText("Error");
				}
			}
		}
		if (obj == this.btnApplyDetail) {
			this.searchAndSortDetail();
			this.clearDetail();
			this.lockDetail();
			this.btnAddDetail.setEnabled(false);
		}
		if (obj == this.btexport) {
			btexportAction();
		} 
		if (obj == this.btimport) {
			btimportAction();
//			loadImpData(impArr);	
		}
	}
	public void btimportAction() {
		Excel excel = new Excel();
		ExcelBillDialog dialogexcel = new ExcelBillDialog();
		dialogexcel.setLocationRelativeTo(null);
		dialogexcel.setModal(true);
		dialogexcel.setVisible(true);
		int select = dialogexcel.getConfirm();
		if (select == 0)
			return;
		else if (select == 1) {
			String link1=dialogexcel.getBill();
			String link2=dialogexcel.getBillDetail();
			String link3=dialogexcel.getBillSale();
			if(this.checkFile(link1) + this.checkFile(link2)+ this.checkFile(link3) == 3) {
				if(excel.checkfile(link1) && excel.checkfile(link2) && excel.checkfile(link3)) {
					ArrayList<Bill> arr = excel.readBill(link1);
					if (arr == null)
						return;
					for (Bill p : arr) {
						Bill temp = billBus.searchByID(p.getBill_id());
						if (temp == null)
							billBus.add(p);
						else
							billBus.update(p);
					}
					ArrayList<BillDetail> arr2 = excel.readBillDetail(link2);
					if (arr2 == null)
						return;
					for (BillDetail p : arr2) {
						BillDetail temp = billdetailBus.searchByID(p.getBill_id(),p.getProd_id());
						if (temp == null)
							billdetailBus.add(p);
						else
							billdetailBus.update(p);
					}
					ArrayList<BillSale> arr3 = excel.readBillSale(link3);
					if (arr3 == null)
						return;
					for (BillSale p : arr3) {
						BillSale temp = billsaleBus.searchByID(p.getSale_id(),p.getBill_id());
						if (temp == null)
							billsaleBus.add(p);
						else
							billsaleBus.update(p);
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
			excel.writeBill();
			excel.writeBillDetail();
			excel.writeBillSale();
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
	public void updateBillDislay() {
		Bill p = billBus.searchByID(billID);
		p.setSale_total(billBus.getTotalSalePrice(billID));
		billBus.update(p);
		this.searchAndSortBill();
		if (p != null)
			this.showBillTableData(p);
	}

	public int countProdAmount() {
		int count = 0;
		for (int i = 0; i < tableDetail.getRowCount(); i++) {
			count += Integer.parseInt(String.valueOf(tableDetail.getValueAt(i, 2)));
		}
		return count;
	}

	public boolean getAuthority() {
		return this.authority;
	}

	public void setColSize(JTable table) {
		TableColumnModel col = table.getColumnModel();
		this.setFixColSize(col, 0, 18);
		this.setFixColSize(col, 1, 120);
		this.setFixColSize(col, 2, 120);
		this.setFixColSize(col, 3, 85);
		this.setFixColSize(col, 4, 85);
		this.setFixColSize(col, 7, 60);
		if (this.getAuthority())
			this.setFixColSize(col, 8, 8);

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

	public void searchAndSortBill() {
		this.getBillData();
		boolean check = true;
		String from = "", to = "";
		try {
			if (txDateFrom.getDate() == null)
				from = "null";
			else
				DateExe.isValidDate(DateExe.convertDateToString(txDateFrom.getDate()));
			if (txDateTo.getDate() == null)
				to = "null";
			else
				DateExe.isValidDate(DateExe.convertDateToString(txDateTo.getDate()));
		} catch (Exception p) {
			JOptionPane.showMessageDialog(null, "Ngày không hợp lệ!");
			check = false;
		}
		if (check) {
			if (!from.equals("null"))
				from = DateExe.convertDateToString(txDateFrom.getDate());
			if (!to.equals("null"))
				to = DateExe.convertDateToString(txDateTo.getDate());
			billArr = billBus.sort(
					billBus.search(billArr, txSearchBill.getText(), this.getSelectedButtonText(bg1), from, to),
					String.valueOf(this.comboBillType.getSelectedItem()),
					String.valueOf(this.comboBillOrder.getSelectedItem()));
			this.loadBillData(billArr);

			if (billArr.size() == 0)
				JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả nào!");
		}
	}

	public void searchAndSortDetail() {
		this.getDetailData(billID);
		billdetailArr = billdetailBus.sort(
				billdetailBus.search(billdetailArr, txSearchDetail.getText(), this.getSelectedButtonText(bg2)),
				String.valueOf(this.comboDetailType.getSelectedItem()),
				String.valueOf(this.comboDetailOrder.getSelectedItem()));
		this.loadDetailData(billdetailArr);

	}

	public void showBillTableData(Bill p) {
		this.txBillID.setText(p.getBill_id());
		this.txCusID.setText(p.getCus_id());
		this.txEmpID.setText(p.getEmp_id());
		this.txDate.setText(p.getDateStr());
		this.txTotal.setText(String.valueOf(this.billdetailBus.getTotalPrice(p.getBill_id())));
		this.txBillSaleID.setText(p.getSale_id());
		this.txSaleTotal.setText(String.valueOf(p.getSale_total()));
	}

	public void showDetailTableData(BillDetail p) {
		Product prodTemp = prodBus.searchByID(p.getProd_id());
		this.txProdID.setText(p.getProd_id());
		this.txProdName.setText(prodTemp.getProd_name());
		this.txAmount.setText(String.valueOf(p.getAmount()));
		this.txUnitPrice.setText(String.valueOf(p.getUnit_price()));
		this.txMeasure.setText(prodTemp.getMeasure());
		this.txSalePrice.setText(String.valueOf(p.getSale_unit_price()));
		this.txProdSaleID.setText(p.getSale_id());
	}

	public void loadBillData(ArrayList<Bill> arr) {
		String[] header = new String[] { "Mã HĐ", "Tên NV", "Tên KH", "Ngày lập", "Thành tiền", "Mã KM", "Giá KM",
				"Tình trạng" };
		DefaultTableModel md = new DefaultTableModel(header, 0);
		for (Bill p : arr) {
			Object[] row;
			row = new Object[] { p.getBill_id(), empBus.searchByID(p.getEmp_id()).getEmp_name(),
					cusBus.searchByID(p.getCus_id()).getCus_name(), p.getDateStr(),
					billdetailBus.getTotalPrice(p.getBill_id()), p.getSale_id(), p.getSale_total(),
					p.getStatus() == 1 ? "Đã bán" : p.getStatus() == 2 ? "Đã xóa" : "" };
			md.addRow(row);
		}
		tableBill.setModel(md);
		this.setBillColSize();
	}

	public void loadDetailData(ArrayList<BillDetail> arr) {
		String[] header = new String[] { "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Đ.vị", "Mã KM", "Giá KM" };
		DefaultTableModel md = new DefaultTableModel(header, 0);
		for (BillDetail p : arr) {
			Object[] row;
			row = new Object[] { p.getProd_id(), prodBus.searchByID(p.getProd_id()).getProd_name(), p.getAmount(),
					p.getUnit_price(), prodBus.searchByID(p.getProd_id()).getMeasure(), p.getSale_id(),
					p.getSale_unit_price() };
			md.addRow(row);
		}
		tableDetail.setModel(md);
		this.setDetailColSize();
	}

	public void getBillData() {
		if (this.authority)
			this.billArr = billBus.readAll();
		else
			this.billArr = billBus.searchByEmpID(empID);
	}

	public void getDetailData(String billID) {
		this.billdetailArr = this.billdetailBus.searchByBillID(billID);
	}

	public void setBillColSize() {
		TableColumnModel col = tableBill.getColumnModel();
		this.setFixColSize(col, 0, 50);
		this.setFixColSize(col, 1, 130);
		this.setFixColSize(col, 2, 130);
		this.setFixColSize(col, 3, 75);
		this.setFixColSize(col, 4, 75);
		this.setFixColSize(col, 5, 50);
		this.setFixColSize(col, 6, 75);
		this.setFixColSize(col, 7, 50);

	}

	public void setDetailColSize() {
		TableColumnModel col = tableDetail.getColumnModel();
		this.setFixColSize(col, 0, 80);
		this.setFixColSize(col, 1, 160);
		this.setFixColSize(col, 2, 90);
		this.setFixColSize(col, 3, 90);
		this.setFixColSize(col, 4, 60);
		this.setFixColSize(col, 5, 80);
		this.setFixColSize(col, 6, 100);

	}

	public void lockBill() {

		this.btnSelectCus.setEnabled(false);

		this.btnDeleteBill.setEnabled(false);
		this.btnUpdateBill.setEnabled(false);
		this.btnLockBill.setEnabled(false);
		this.btnBillSaleClear.setEnabled(false);
		this.btnSelectBillSale.setEnabled(false);
		this.btnPDF.setEnabled(false);
		this.lockDetail();

	}

	public void lockDetail() {

		this.txAmount.setEditable(false);
		this.txUnitPrice.setEditable(false);

		this.btnSelectProd.setEnabled(false);
		this.btnUpdateDetail.setEnabled(false);
		this.btnDeleteDetail.setEnabled(false);
		this.btnSelectProdSale.setEnabled(false);
		this.btnProdSaleClear.setEnabled(false);
	}

	public void unlockBill() {

		this.btnSelectCus.setEnabled(true);

		this.btnDeleteBill.setEnabled(true);
		this.btnUpdateBill.setEnabled(true);
		this.btnLockBill.setEnabled(true);
		this.btnBillSaleClear.setEnabled(true);
		this.btnSelectBillSale.setEnabled(true);
		this.btnPDF.setEnabled(true);
		this.unlockDetail();
	}

	public void unlockDetail() {

		this.txAmount.setEditable(true);
		this.txUnitPrice.setEditable(true);

		this.btnSelectProd.setEnabled(true);
		this.btnUpdateDetail.setEnabled(true);
		this.btnDeleteDetail.setEnabled(true);
		this.btnSelectProdSale.setEnabled(true);
		this.btnProdSaleClear.setEnabled(true);

	}

	public void clearBill() {
		billID = "";
		islocked = 0;
		this.txBillID.setText("");
		this.txCusID.setText("");
		this.txEmpID.setText("");
		this.txDate.setText("");
		this.txTotal.setText("");
		this.txSaleTotal.setText("");
		this.txBillSaleID.setText("");
		this.clearDetail();
	}

	public void clearDetail() {
		this.txProdID.setText("");
		this.txProdName.setText("");
		this.txAmount.setText("");
		this.txUnitPrice.setText("");
		this.txMeasure.setText("");
		this.txSalePrice.setText("");
		this.txProdSaleID.setText("");
	}
}
