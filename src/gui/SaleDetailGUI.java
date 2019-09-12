package gui;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.BorderLayout;

import javax.swing.*;
import java.util.*;
import dto.*;
import gui.ReadExcelDialog;
import bus.*;
import java.awt.event.*;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;
import design.myTable;
import java.awt.Color;
import java.awt.Font;

public class SaleDetailGUI extends JPanel implements ActionListener {
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblMSp;
	private JLabel lblTnSp;
	private JTextField txProdID;
	private JTextField txProdName;
	private JLabel lblMKm;
	private JLabel lblTnKm;
	private JTextField txSaleID;
	private JTextField txSaleName;
	private JPanel panel_3;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel_4;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JPanel panel_5;
	private JTextField txSearch;
	private JRadioButton rdbtnMKm;
	private JRadioButton rdbtnTnKm;
	private JRadioButton rdbtnMSp;
	private JRadioButton rdbtnTnSp;
	private JPanel panel_6;
	private JComboBox comboChoice;
	private JPanel panel_7;
	private JComboBox comboOrder;

	private SaleBUS saleBus = new SaleBUS();
	private ProductBUS prodBus = new ProductBUS();
	private ProdSaleBUS prodsaleBus = new ProdSaleBUS();
	private BillBUS bilBus = new BillBUS();
	private BillSaleBUS billsaleBus = new BillSaleBUS();
	private ArrayList<Sale> saleArr = new ArrayList<Sale>();
	private ArrayList<Product> prodArr = new ArrayList<Product>();
	private ArrayList<ProdSale> prodsaleArr = new ArrayList<ProdSale>();
	private ArrayList<BillSale> billsaleArr = new ArrayList<BillSale>();
	private ArrayList<Bill> billArr = new ArrayList<Bill>();
	private AddProdSaleDialog dialog;
	private ButtonGroup bg = new ButtonGroup();
	private ButtonGroup bg2 = new ButtonGroup();
	private boolean authority;
	private String[] header;
	private JButton btnApply;
	private String id;
	private JPanel panel_8;
	private JPanel panel_9;
	private JScrollPane scrollPane_1;
	private JTable tableBill;
	private JPanel panel_10;
	private JComboBox comboBillType;
	private JComboBox comboBillOrder;
	private JButton btnBillApply;
	private JTextField txBillSale;
	private JLabel lblNewLabel;
	private JTextField txBillSearch;
	private JRadioButton rdbtnMKm_1;
	private JRadioButton rdbtnMH;
	private JDateChooser txFrom;
	private JLabel lblT;
	private JDateChooser txTo;
	private JLabel lbln;
	private JButton btimport;
	private JButton btexport;

	/**
	 * Create the panel.
	 */
	public SaleDetailGUI(String id) {
		design.Icon icon = new design.Icon();
		Employer emp = new EmployerBUS().searchByID(id);
		this.id = id;
		authority = emp.isEmp_type();
		saleArr = saleBus.readAll();
		prodArr = prodBus.readAll();

		setSize(1000, 700);
		setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 11, 600, 580);
		add(panel);
		panel.setLayout(null);
		panel.setBackground(icon.getPaneColor());
		this.setBackground(icon.getContentColor());
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 285, 90);
		panel_1.setBackground(icon.getPaneColor());
		panel.add(panel_1);
		panel_1.setLayout(null);

		lblMKm = new JLabel("Mã KM:");
		lblMKm.setBounds(10, 24, 48, 20);
		panel_1.add(lblMKm);

		lblTnKm = new JLabel("Tên KM:");
		lblTnKm.setBounds(10, 52, 48, 20);
		panel_1.add(lblTnKm);

		txSaleID = new JTextField();
		txSaleID.setEditable(false);
		txSaleID.setBounds(68, 19, 80, 30);
		panel_1.add(txSaleID);
		txSaleID.setColumns(10);

		txSaleName = new JTextField();
		txSaleName.setEditable(false);
		txSaleName.setBounds(68, 49, 194, 30);
		panel_1.add(txSaleName);
		txSaleName.setColumns(10);

		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(305, 11, 285, 90);
		panel_2.setBackground(icon.getPaneColor());
		panel.add(panel_2);
		panel_2.setLayout(null);

		lblMSp = new JLabel("Mã SP:");
		lblMSp.setBounds(10, 26, 48, 20);
		panel_2.add(lblMSp);

		txProdID = new JTextField();
		txProdID.setEditable(false);
		txProdID.setBounds(58, 21, 80, 30);
		panel_2.add(txProdID);
		txProdID.setColumns(10);

		lblTnSp = new JLabel("Tên SP:");
		lblTnSp.setBounds(10, 54, 48, 20);
		panel_2.add(lblTnSp);

		txProdName = new JTextField();
		txProdName.setEditable(false);
		txProdName.setBounds(58, 51, 206, 30);
		panel_2.add(txProdName);
		txProdName.setColumns(10);

		panel_3 = new JPanel();
		panel_3.setBounds(10, 241, 580, 330);
		panel_3.setBackground(icon.getPaneColor());
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);

		table = new myTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String saleID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0));
				String prodID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 1));
				ProdSale sale = prodsaleBus.searchByID(saleID, prodID);
				if (sale.getStatus() == 1) {
					btnDelete.setText("MỞ KHÓA");
					btnUpdate.setEnabled(false);
					lock();
				} else {
					btnDelete.setText("XÓA");
					btnUpdate.setEnabled(true);
					unlock();
				}
				if (!authority) {
					btnDelete.setEnabled(false);
					btnUpdate.setEnabled(false);
				} else
					btnDelete.setEnabled(true);
				showTableData(sale);
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String saleID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0));
				String prodID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 1));
				ProdSale sale = prodsaleBus.searchByID(saleID, prodID);
				if (sale.getStatus() == 1) {
					btnDelete.setText("MỞ KHÓA");
					btnUpdate.setEnabled(false);
					lock();
				} else {
					btnDelete.setText("XÓA");
					btnUpdate.setEnabled(true);
					unlock();
				}
				if (!authority) {
					btnDelete.setEnabled(false);
					btnUpdate.setEnabled(false);
				} else
					btnDelete.setEnabled(true);
				showTableData(sale);
			}
		});
		scrollPane.setViewportView(table);

		panel_4 = new JPanel();
		panel_4.setBounds(10, 112, 580, 118);
		panel_4.setBackground(icon.getPaneColor());
		panel.add(panel_4);
		panel_4.setLayout(null);

		btnAdd = new JButton("THÊM");
		if (!this.getAuthority())
			btnAdd.setEnabled(false);
		btnAdd.addActionListener(this);
		btnAdd.setBounds(461, 11, 109, 30);
		btnAdd.setFont(new Font("sans-serif", Font.BOLD, 14));
		btnAdd.setBackground(icon.getInsertColor());
		btnAdd.setIcon(icon.imageIconSize("icons/add.png", 20, 20));
		btnAdd.setForeground(Color.white);
		panel_4.add(btnAdd);

		btnDelete = new JButton("XÓA");
		btnDelete.addActionListener(this);
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("sans-serif", Font.BOLD, 14));
		btnDelete.setBackground(icon.getDeleteColor());
		btnDelete.setForeground(Color.white);
		btnDelete.setIcon(icon.imageIconSize("icons/delete.png", 20, 20));
		btnDelete.setBounds(461, 77, 109, 30);
		panel_4.add(btnDelete);

		btnUpdate = new JButton("SỬA");
		btnUpdate.addActionListener(this);
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(461, 44, 109, 30);
		btnUpdate.setFont(new Font("sans-serif", Font.BOLD, 14));
		btnUpdate.setBackground(icon.getEditColor());
		btnUpdate.setForeground(Color.white);
		btnUpdate.setIcon(icon.imageIconSize("icons/edit.png", 20, 20));
		panel_4.add(btnUpdate);

		panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Tìm kiếm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(0, 0, 184, 118);
		panel_5.setBackground(icon.getPaneColor());
		panel_4.add(panel_5);
		panel_5.setLayout(null);

		txSearch = new JTextField();
		txSearch.setBounds(10, 25, 159, 30);
		panel_5.add(txSearch);
		txSearch.setColumns(10);

		rdbtnMKm = new JRadioButton("Mã KM");
		rdbtnMKm.setSelected(true);
		rdbtnMKm.setBounds(10, 62, 72, 23);
		panel_5.add(rdbtnMKm);

		rdbtnTnKm = new JRadioButton("Tên KM");
		rdbtnTnKm.setBounds(10, 88, 72, 23);
		panel_5.add(rdbtnTnKm);

		rdbtnMSp = new JRadioButton("Mã SP");
		rdbtnMSp.setBounds(105, 62, 64, 23);
		panel_5.add(rdbtnMSp);

		rdbtnTnSp = new JRadioButton("Tên SP");
		rdbtnTnSp.setBounds(105, 88, 64, 23);
		panel_5.add(rdbtnTnSp);

		panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(null, "Sắp xếp theo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(194, 0, 153, 57);
		panel_6.setBackground(icon.getPaneColor());
		panel_4.add(panel_6);

		String[] choices = { "Mã SP", "Mã KM", "Tên SP", "Tên KM" };
		comboChoice = new JComboBox(choices);
		comboChoice.setBounds(10, 16, 130, 30);
		panel_6.add(comboChoice);

		panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new TitledBorder(null, "Thứ tự", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(194, 61, 153, 57);
		panel_7.setBackground(icon.getPaneColor());
		panel_4.add(panel_7);

		String[] order = { "Tăng dần", "Giảm dần" };
		comboOrder = new JComboBox(order);
		comboOrder.setBounds(10, 16, 130, 30);
		panel_7.add(comboOrder);

		btnApply = new JButton("ÁP DỤNG");
		btnApply.addActionListener(this);
		btnApply.setBounds(357, 11, 94, 96);
		btnApply.setBackground(icon.getSortColor());
		btnApply.setForeground(Color.white);
		btnApply.setFont(new Font("sans-serif", Font.BOLD, 14));
		panel_4.add(btnApply);

		bg.add(rdbtnMKm);
		bg.add(rdbtnMSp);
		bg.add(rdbtnTnKm);
		bg.add(rdbtnTnSp);

		panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Danh sách các hóa đơn được khuyến mãi", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_8.setBounds(620, 11, 350, 580);
		panel_8.setBackground(icon.getPaneColor());
		add(panel_8);
		panel_8.setLayout(null);

		panel_9 = new JPanel();
		panel_9.setBounds(10, 259, 330, 310);
		panel_9.setBackground(icon.getPaneColor());
		panel_8.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		scrollPane_1 = new JScrollPane();
		panel_9.add(scrollPane_1, BorderLayout.CENTER);

		tableBill = new myTable();
		tableBill.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txBillSale.setText(saleBus
						.searchByID(String.valueOf(tableBill.getModel().getValueAt(tableBill.getSelectedRow(), 0)))
						.getSale_name());
			}
		});
		tableBill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				txBillSale.setText(saleBus
						.searchByID(String.valueOf(tableBill.getModel().getValueAt(tableBill.getSelectedRow(), 0)))
						.getSale_name());

			}
		});
		scrollPane_1.setViewportView(tableBill);

		panel_10 = new JPanel();
		panel_10.setBorder(
				new TitledBorder(null, "Tìm và sắp xếp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setBounds(10, 22, 330, 187);
		panel_10.setBackground(icon.getPaneColor());
		panel_8.add(panel_10);
		panel_10.setLayout(null);

		String[] billType = { "Mã HĐ", "Mã KM", "Ngày lập HĐ" };
		comboBillType = new JComboBox(billType);
		comboBillType.setBounds(183, 15, 137, 30);
		panel_10.add(comboBillType);

		comboBillOrder = new JComboBox(order);
		comboBillOrder.setBounds(183, 52, 137, 30);
		panel_10.add(comboBillOrder);

		btnBillApply = new JButton("Áp dụng");
		btnBillApply.addActionListener(this);
		btnBillApply.setBounds(103, 148, 137, 30);
		btnBillApply.setFont(null);
		btnBillApply.setBackground(icon.getSearchColor());
		btnBillApply.setForeground(Color.black);
		btnBillApply.setIcon(icon.imageIconSize("icons/search_more.png", 20, 20));
		btnBillApply.setFont(new Font("sans-serif", Font.BOLD, 14));
		panel_10.add(btnBillApply);

		txBillSearch = new JTextField();
		txBillSearch.setBounds(10, 15, 163, 30);
		panel_10.add(txBillSearch);
		txBillSearch.setColumns(10);

		rdbtnMKm_1 = new JRadioButton("Mã KM");
		rdbtnMKm_1.setSelected(true);
		rdbtnMKm_1.setBounds(20, 52, 72, 23);
		panel_10.add(rdbtnMKm_1);

		rdbtnMH = new JRadioButton("Mã HĐ");
		rdbtnMH.setBounds(94, 52, 72, 23);
		panel_10.add(rdbtnMH);

		txFrom = new JDateChooser();
		txFrom.setDateFormatString("dd/MM/yyyy");
		txFrom.setBounds(36, 107, 120, 30);
		panel_10.add(txFrom);

		lblT = new JLabel("Từ:");
		lblT.setBounds(10, 107, 32, 28);
		panel_10.add(lblT);

		txTo = new JDateChooser();
		txTo.setDateFormatString("dd/MM/yyyy");
		txTo.setBounds(200, 107, 120, 30);
		panel_10.add(txTo);

		lbln = new JLabel("Đến:");
		lbln.setBounds(166, 107, 32, 28);
		panel_10.add(lbln);

		txBillSale = new JTextField();
		txBillSale.setEditable(false);
		txBillSale.setBounds(68, 220, 272, 28);
		panel_8.add(txBillSale);
		txBillSale.setColumns(10);

		lblNewLabel = new JLabel("Tên KM:");
		lblNewLabel.setBounds(20, 220, 48, 28);
		panel_8.add(lblNewLabel);

		bg2.add(rdbtnMKm_1);
		bg2.add(rdbtnMH);

		btimport = new JButton();
		btimport.setToolTipText("Đọc dữ liệu từ file excel mà bạn chọn.");
		btimport.setBorder(null);
		btimport.setBackground(Color.WHITE);
		btimport.setBounds(20, 592, 35, 35);
		add(btimport);

		btexport = new JButton();
		btexport.setToolTipText("Ghi dữ liệu vào file excel");
		btexport.setBorder(null);
		btexport.setBackground(Color.WHITE);
		btexport.setBounds(70, 592, 35, 35);
		add(btexport);

		btimport.setIcon(icon.imageIconSize("icons/import2.png", 40, 40));
		btimport.setBackground(new Color(255, 255, 255));
		btimport.setBorder(null);
		btexport.setIcon(icon.imageIconSize("icons/export2.png", 40, 40));
		btexport.setBackground(new Color(255, 255, 255));
		btexport.setBorder(null);
		btexport.addActionListener(this);
		btimport.addActionListener(this);

		this.getData();
		this.loadData(prodsaleArr);
		this.getBillData();
		this.loadBillData(billsaleArr);
		
		if(!this.authority) {
			btimport.setVisible(false);
			btexport.setVisible(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnDelete) {
			if (!table.getSelectionModel().isSelectionEmpty()) {
				if (btnDelete.getText().equals("XÓA")) {
					if (JOptionPane.showConfirmDialog(null, "Xóa dòng này?", "Xác nhận",
							JOptionPane.YES_NO_OPTION) == 0) {
						this.lock();
						String saleID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0));
						String prodID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 1));
						prodsaleBus.disable(saleID, prodID);
						btnUpdate.setEnabled(false);
						// btnDelete.setText("MỞ KHÓA");

					}
				} else {
					if (JOptionPane.showConfirmDialog(null, "Mở khóa khuyến mãi này?", "Xác nhận",
							JOptionPane.YES_NO_OPTION) == 0) {
						this.unlock();
						String saleID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0));
						String prodID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 1));
						prodsaleBus.active(saleID, prodID);
						btnUpdate.setEnabled(true);

					}
				}
				this.searchAndSort();
				btnUpdate.setEnabled(false);
				btnDelete.setText("XÓA");
				btnDelete.setEnabled(false);
				clear();
				lock();
			} else
				JOptionPane.showMessageDialog(null, "Không có dòng nào được chọn!");
		}
		if (obj == btnAdd) {

			this.dialog = new AddProdSaleDialog(id, null, null);
			this.dialog.setLocationRelativeTo(null);
			this.dialog.setModal(true);
			this.dialog.setVisible(true);

			ProdSale temp = this.dialog.getProdSale();
			if (temp != null) {
				prodsaleBus.add(temp);
				this.searchAndSort();
			}

		}
		if (obj == btnUpdate) {
			this.dialog = new AddProdSaleDialog(id, txSaleID.getText(), txProdID.getText());
			this.dialog.setLocationRelativeTo(null);
			this.dialog.setModal(true);
			this.dialog.setVisible(true);
		}
		if (obj == btnApply) {

			this.searchAndSort();
		}
		if (obj == btnBillApply) {
			this.searchAndSortBill();
		}
		if (obj == btexport) {
			btexportAction();
		} 
		if (obj == btimport) {
			btimportAction();
			this.loadData(prodsaleArr);
		}
	}
	public void btimportAction() {
		Excel excel = new Excel();
		ReadExcelDialog dialogexcel = new ReadExcelDialog("prod_sale");
		dialogexcel.setLocationRelativeTo(null);
		dialogexcel.setModal(true);
		dialogexcel.setVisible(true);
		int select = dialogexcel.getConfirm();
		if (select == 0)
			return;
		else if (select == 1) {
			ArrayList<ProdSale> arr = excel.readProdSale(dialogexcel.getLink());
			if (arr == null)
				return;
			for (ProdSale p : arr) {
				ProdSale temp = prodsaleBus.searchByID(p.getSale_id(),p.getProd_id());
				if (temp == null)
					prodsaleBus.add(p);
				else
					prodsaleBus.update(p);
			}
			JOptionPane.showMessageDialog(this, "Đã cập nhật lại dữ liệu thành công", "Cập nhật dữ liệu",
					JOptionPane.INFORMATION_MESSAGE);
		} 
	}

	public void btexportAction() {
		Excel excel = new Excel();
		int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn ghi dữ liệu vào file excel?", "Ghi file excel",
				JOptionPane.YES_NO_OPTION);
		if (a == 0) {
			excel.writeProdSale();
			JOptionPane.showMessageDialog(this, "Đã ghi dữ liệu vào file excel thành công!");
		}
	}
	public void loadData(ArrayList<ProdSale> arr) {
		if (this.authority) {
			header = new String[] { "Mã KM", "Mã SP", "Tên KM", "Tên SP", "Khóa" };
		} else
			header = new String[] { "Mã KM", "Mã SP", "Tên KM", "Tên SP" };
		DefaultTableModel md = new DefaultTableModel(header, 0);
		for (ProdSale p : arr) {
			Object[] row;
			if (this.getAuthority())
				row = new Object[] { p.getSale_id(), p.getProd_id(),
						saleBus.searchByID(saleArr, p.getSale_id()).getSale_name(),
						prodBus.searchByID(prodArr, p.getProd_id()).getProd_name(), p.getStatus() == 1 ? "X" : "" };
			else
				row = new Object[] { p.getSale_id(), p.getProd_id(),
						saleBus.searchByID(saleArr, p.getSale_id()).getSale_name(),
						prodBus.searchByID(prodArr, p.getProd_id()).getProd_name() };
			md.addRow(row);
		}
		table.setModel(md);
		this.setColSize(table);
	}

	public void loadBillData(ArrayList<BillSale> arr) {
		if (this.authority) {
			header = new String[] { "Mã KM", "Mã HĐ", "Ngày lập", "Thanh toán" };
		} else
			header = new String[] { "Mã KM", "Mã HĐ", "Ngày lập HĐ" };
		DefaultTableModel md = new DefaultTableModel(header, 0);
		for (BillSale p : arr) {
			Object[] row;
			if (this.getAuthority())
				row = new Object[] { p.getSale_id(), p.getBill_id(), bilBus.searchByID(p.getBill_id()).getDateStr(),
						bilBus.searchByID(p.getBill_id()).getStatus() == 1 ? "X" : "" };
			else
				row = new Object[] { p.getSale_id(), p.getBill_id(), bilBus.searchByID(p.getBill_id()).getDateStr() };
			md.addRow(row);
		}
		tableBill.setModel(md);
		setColSizeBill(tableBill);
	}

	public void setColSize(JTable table) {
		TableColumnModel col = table.getColumnModel();
		this.setFixColSize(col, 0, 30);
		this.setFixColSize(col, 1, 30);
		this.setFixColSize(col, 2, 200);
		this.setFixColSize(col, 3, 200);
		if (this.getAuthority())
			this.setFixColSize(col, 4, 8);

	}

	public void setColSizeBill(JTable table) {
		TableColumnModel col = table.getColumnModel();
		this.setFixColSize(col, 0, 80);
		this.setFixColSize(col, 1, 80);
		this.setFixColSize(col, 2, 200);
		if (this.getAuthority())
			this.setFixColSize(col, 3, 40);

	}

	public void setFixColSize(TableColumnModel col, int index, int size) {
		col.getColumn(index).setMinWidth(size);
		col.getColumn(index).setResizable(true);
	}

	public boolean getAuthority() {
		return this.authority;
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

	public void searchAndSort() {
		this.getData();

		prodsaleArr = prodsaleBus.search(prodsaleArr, saleBus, prodBus, this.txSearch.getText(),
				this.getSelectedButtonText(bg));
		prodsaleArr = prodsaleBus.sort(prodsaleArr, saleBus, prodBus,
				String.valueOf(this.comboChoice.getSelectedItem()), String.valueOf(this.comboOrder.getSelectedItem()));

		this.loadData(prodsaleArr);
		if (prodsaleArr.size() == 0)
			JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả nào!");
	}

	public void searchAndSortBill() {
		this.getBillData();
		boolean check = true;
		String from = "", to = "";
		try {
			if (txFrom.getDate() == null)
				from = "null";
			else
				DateExe.isValidDate(DateExe.convertDateToString(txFrom.getDate()));
			if (txTo.getDate() == null)
				to = "null";
			else
				DateExe.isValidDate(DateExe.convertDateToString(txTo.getDate()));
		} catch (Exception p) {
			JOptionPane.showMessageDialog(null, "Ngày không hợp lệ!");
			check = false;
		}
		if (check) {
			if (!from.equals("null"))
				from = DateExe.convertDateToString(txFrom.getDate());
			if (!to.equals("null"))
				to = DateExe.convertDateToString(txTo.getDate());
			billsaleArr = billsaleBus.sort(
					billsaleBus.search(billsaleArr, txBillSearch.getText(), this.getSelectedButtonText(bg2), from, to),
					String.valueOf(comboBillType.getSelectedItem()), String.valueOf(comboBillOrder.getSelectedItem()));
			this.loadBillData(billsaleArr);
			if (billsaleArr.size() == 0)
				JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả nào!");
		}

	}

	public void showTableData(ProdSale p) {
		this.txSaleID.setText(p.getSale_id());
		this.txSaleName.setText(saleBus.searchByID(saleArr, p.getSale_id()).getSale_name());
		this.txProdID.setText(p.getProd_id());
		this.txProdName.setText(prodBus.searchByID(prodArr, p.getProd_id()).getProd_name());
	}

	public void getData() {
		prodsaleArr = prodsaleBus.read();
	}

	public void getBillData() {
		if (this.getAuthority())
			billsaleArr = billsaleBus.readAll();
		else
			billsaleArr = billsaleBus.read();
	}

	public void clear() {
		this.txSaleID.setText("");
		this.txSaleName.setText("");
		this.txProdID.setText("");
		this.txProdName.setText("");
	}

	public void lock() {

	}

	public void unlock() {

	}
}
