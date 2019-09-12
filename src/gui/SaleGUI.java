package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.GridLayout;

import com.toedter.calendar.JDateChooser;
import javax.swing.border.EtchedBorder;

import java.awt.SystemColor;

import bus.*;
import design.myTable;
import dto.*;
import gui.ReadExcelDialog;

import java.awt.Font;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SaleGUI extends JPanel implements ActionListener {
	private JPanel pn0;
	private JTabbedPane tabbedPane;
	private JPanel pnSale;
	private SaleDetailGUI pnSub;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnAdd;
	private JButton btnDelete;
	private JLabel lblMKm;
	private JLabel lblTnKm;
	private JTextField txID;
	private JTextField txName;
	private JButton btnUpdate;
	private JLabel lblNgyBtu;
	private JLabel lblKtThc;
	private JDateChooser txStart;
	private JDateChooser txEnd;
	private JPanel panel_3;
	private JPanel panel_4;
	private JTextField txNum;
	private JComboBox comboMeasure;
	private JTextField txThres;
	private JComboBox comboThresMeasure;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel_5;
	private JComboBox comboChoice;
	private JPanel panel_6;
	private JComboBox comboOrder;
	private JPanel panel_7;
	private JTextField txSearch;
	private JButton btnApply;
	private JRadioButton rdbtnID;
	private JRadioButton rdbtnName;
	private JPanel panel_8;
	private JComboBox comboType;
	private JPanel panel_10;
	private JLabel label;
	private JLabel label_1;
	private ButtonGroup bg;
	private AddSaleDialog dialog;

	private ArrayList<Sale> saleArr = new ArrayList<Sale>();
	private SaleBUS saleBus = new SaleBUS();
	private EmployerBUS empBus = new EmployerBUS();
	private Employer emp;
	private boolean authority;
	private String[] header;
	private JDateChooser txDateFrom;
	private JDateChooser txDateTo;
	private JButton btnClear;
	private JButton btimport;
	private JButton btexport;

	/**
	 * Create the panel.
	 */
	public SaleGUI(String id) {
		design.Icon icon = new design.Icon();
		emp = empBus.searchByID(id);
		authority = emp.isEmp_type();

		setLayout(new BorderLayout(0, 0));
		setSize(1000, 700);
		pn0 = new JPanel();
		add(pn0, BorderLayout.CENTER);
		pn0.setLayout(new BorderLayout(0, 0));
		pn0.setBackground(icon.getPaneColor());

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		pn0.add(tabbedPane, BorderLayout.CENTER);

		pnSale = new JPanel();
		tabbedPane.addTab("Thông tin khuyến mãi", null, pnSale, null);
		pnSale.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin chi ti\u1EBFt", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 802, 173);
		panel.setBackground(icon.getPaneColor());
		pnSale.add(panel);
		pnSale.setBackground(icon.getContentColor());
		panel.setLayout(null);

		lblMKm = new JLabel("Mã KM:");
		lblMKm.setBounds(20, 30, 48, 20);
		panel.add(lblMKm);

		lblTnKm = new JLabel("Tên KM:");
		lblTnKm.setBounds(20, 60, 48, 20);
		panel.add(lblTnKm);

		txID = new JTextField();
		txID.setEditable(false);
		txID.setBounds(78, 26, 80, 30);
		panel.add(txID);
		txID.setColumns(10);

		txName = new JTextField();
		txName.setEditable(false);
		txName.setText("");
		txName.setBounds(78, 57, 205, 30);
		panel.add(txName);
		txName.setColumns(10);

		btnDelete = new JButton("XÓA");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(this);
		btnDelete.setBounds(659, 122, 121, 40);
		btnDelete.setBackground(icon.getDeleteColor());
		btnDelete.setFont(new Font("sans-serif", Font.BOLD, 14));
		btnDelete.setIcon(icon.imageIconSize("icons/delete.png", 20, 20));
		btnDelete.setForeground(Color.white);
		panel.add(btnDelete);

		btnUpdate = new JButton("SỬA");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(this);
		btnUpdate.setBounds(659, 71, 121, 40);
		btnUpdate.setBackground(icon.getEditColor());
		btnUpdate.setFont(new Font("sans-serif", Font.BOLD, 14));
		btnUpdate.setIcon(icon.imageIconSize("icons/Edit.png", 20, 20));
		btnUpdate.setForeground(Color.white);
		panel.add(btnUpdate);

		lblNgyBtu = new JLabel("Bắt đầu:");
		lblNgyBtu.setBounds(20, 94, 61, 20);
		panel.add(lblNgyBtu);

		lblKtThc = new JLabel("Kết thúc:");
		lblKtThc.setBounds(20, 127, 61, 20);
		panel.add(lblKtThc);

		txStart = new JDateChooser();
		txStart.setDateFormatString("dd/MM/yyyy");
		txStart.setBounds(78, 88, 160, 30);
		txStart.setEnabled(false);
		panel.add(txStart);

		txEnd = new JDateChooser();
		txEnd.setDateFormatString("dd/MM/yyyy");
		txEnd.setBounds(78, 122, 160, 30);
		txEnd.setEnabled(false);
		panel.add(txEnd);

		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"M\u1EE9c khuy\u1EBFn m\u00E3i", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(293, 11, 325, 66);
		panel_3.setBackground(icon.getPaneColor());
		panel.add(panel_3);
		panel_3.setLayout(null);

		txNum = new JTextField();
		txNum.setEditable(false);
		txNum.setBounds(35, 24, 160, 30);
		panel_3.add(txNum);
		txNum.setColumns(10);

		String[] meas = { "đồng", "%" };
		comboMeasure = new JComboBox(meas);
		comboMeasure.setEnabled(false);
		comboMeasure.setBounds(205, 23, 105, 30);
		panel_3.add(comboMeasure);

		panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"M\u1EE9c \u00E1p d\u1EE5ng (Mua tr\u00EAn...)", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_4.setBounds(293, 90, 325, 72);
		panel_4.setBackground(icon.getPaneColor());
		panel.add(panel_4);
		panel_4.setLayout(null);

		txThres = new JTextField();
		txThres.setEditable(false);
		txThres.setBounds(35, 28, 160, 30);
		panel_4.add(txThres);
		txThres.setColumns(10);

		String[] thres = { "đồng", "món" };
		comboThresMeasure = new JComboBox(thres);
		comboThresMeasure.setEnabled(false);
		comboThresMeasure.setBounds(205, 27, 105, 30);
		panel_4.add(comboThresMeasure);

		btnAdd = new JButton("THÊM");
		btnAdd.setBounds(659, 17, 121, 40);
		btnAdd.addActionListener(this);
		btnAdd.setBackground(icon.getInsertColor());
		btnAdd.setFont(new Font("sans-serif", Font.BOLD, 14));
		btnAdd.setIcon(icon.imageIconSize("icons/add.png", 20, 20));
		btnAdd.setForeground(Color.white);
		panel.add(btnAdd);
		if (!this.authority)
			btnAdd.setEnabled(false);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Danh sách khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP, null,
				Color.BLACK));
		panel_1.setBounds(10, 195, 802, 400);
		pnSale.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_1.setBackground(icon.getPaneColor());

		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		table = new myTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String tempID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0));
				Sale sale = saleBus.searchByID(tempID);
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
				if (sale.getSale_id().charAt(0) == 'A') {
					comboThresMeasure.setSelectedItem("món");
					comboThresMeasure.setEnabled(false);
				} else {
					if (getAuthority())
						comboThresMeasure.setEnabled(true);
				}
				showTableData(sale);
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String tempID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0));
				Sale sale = saleBus.searchByID(tempID);
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
				if (sale.getSale_id().charAt(0) == 'A') {
					comboThresMeasure.setSelectedItem("món");
					comboThresMeasure.setEnabled(false);
					txThres.setText("1.0");
					txThres.setEditable(false);
				} else {
					if (getAuthority()) {
						comboThresMeasure.setEnabled(true);
						txThres.setEditable(false);
					}

				}
				showTableData(sale);
			}
		});
		scrollPane.setViewportView(table);

		panel_2 = new JPanel();
		panel_2.setBounds(817, 11, 168, 650);
		panel_2.setBackground(icon.getContentColor());
		pnSale.add(panel_2);
		panel_2.setLayout(null);

		panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Sắp xếp theo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(0, 0, 158, 60);
		panel_5.setBackground(icon.getPaneColor());
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		String[] choice = { "Mã KM", "Tên KM", "Ngày bắt đầu", "Ngày kết thúc" };
		comboChoice = new JComboBox(choice);
		comboChoice.setBounds(10, 16, 138, 30);
		panel_5.add(comboChoice);

		panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Thứ tự", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(0, 61, 158, 60);
		panel_6.setBackground(icon.getPaneColor());
		panel_2.add(panel_6);
		panel_6.setLayout(null);

		String[] order = { "Tăng dần", "Giảm dần" };
		comboOrder = new JComboBox(order);
		comboOrder.setBounds(10, 16, 138, 30);
		panel_6.add(comboOrder);

		panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Tìm kiếm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(0, 181, 158, 87);
		panel_7.setBackground(icon.getPaneColor());
		panel_2.add(panel_7);
		panel_7.setLayout(null);

		txSearch = new JTextField();
		txSearch.setBounds(10, 30, 138, 30);
		panel_7.add(txSearch);
		txSearch.setColumns(10);

		rdbtnID = new JRadioButton("Mã");
		rdbtnID.setSelected(true);
		rdbtnID.setBounds(10, 57, 51, 23);
		panel_7.add(rdbtnID);

		rdbtnName = new JRadioButton("Tên");
		rdbtnName.setBounds(91, 57, 57, 23);
		panel_7.add(rdbtnName);

		btnApply = new JButton("ÁP DỤNG");
		btnApply.setBounds(0, 490, 158, 40);
		btnApply.setBackground(icon.getSearchColor());
		btnApply.setForeground(Color.black);
		btnApply.setFont(new Font("sans-serif", Font.BOLD, 14));
		btnApply.setIcon(icon.imageIconSize("icons/search_more.png", 20, 20));
		btnApply.addActionListener(this);
		panel_2.add(btnApply);

		panel_8 = new JPanel();
		panel_8.setBorder(
				new TitledBorder(null, "Phân loại khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(0, 120, 158, 60);
		panel_8.setBackground(icon.getPaneColor());
		panel_2.add(panel_8);
		panel_8.setLayout(null);

		String[] type = { "SP trước", "HĐ trước", "Chỉ SP", "Chỉ HĐ" };
		comboType = new JComboBox(type);
		comboType.setBounds(10, 17, 138, 30);
		panel_8.add(comboType);

		panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ng\u00E0y",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_10.setBounds(0, 279, 158, 188);
		panel_10.setBackground(icon.getPaneColor());
		panel_2.add(panel_10);

		label = new JLabel("Từ:");
		label.setBounds(10, 26, 28, 20);
		panel_10.add(label);

		label_1 = new JLabel("Đến:");
		label_1.setBounds(10, 92, 34, 20);
		panel_10.add(label_1);

		txDateFrom = new JDateChooser();
		txDateFrom.setDateFormatString("dd/MM/yyyy");
		txDateFrom.setBounds(10, 51, 138, 30);
		panel_10.add(txDateFrom);

		txDateTo = new JDateChooser();
		txDateTo.setDateFormatString("dd/MM/yyyy");
		txDateTo.setBounds(10, 113, 138, 30);
		panel_10.add(txDateTo);

		btnClear = new JButton("Xóa");
		btnClear.addActionListener(this);
		btnClear.setBounds(49, 154, 57, 23);
		panel_10.add(btnClear);

		pnSub = new SaleDetailGUI(id);
		tabbedPane.addTab("Thiết lập khuyến mãi", null, pnSub, null);

		bg = new ButtonGroup();
		bg.add(rdbtnID);
		bg.add(rdbtnName);

		btimport = new JButton();
		btimport.setBounds(10, 596, 35, 35);
		pnSale.add(btimport);
		btimport.setToolTipText("Đọc dữ liệu từ file excel mà bạn chọn.");
		btimport.setBorder(null);
		btimport.setBackground(Color.WHITE);

		btimport.setIcon(icon.imageIconSize("icons/import2.png", 40, 40));
		btimport.setBackground(new Color(255, 255, 255));
		btimport.setBorder(null);

		btexport = new JButton();
		btexport.setBounds(55, 596, 35, 35);
		pnSale.add(btexport);
		btexport.setToolTipText("Ghi dữ liệu vào file excel");
		btexport.setBorder(null);
		btexport.setBackground(Color.WHITE);

		btexport.setIcon(icon.imageIconSize("icons/export2.png", 40, 40));
		btexport.setBackground(new Color(255, 255, 255));
		btexport.setBorder(null);
		btexport.addActionListener(this);
		btimport.addActionListener(this);

		if (this.authority)
			saleArr = saleBus.readAll();
		else
			saleArr = saleBus.read();
		this.loadData(saleArr);
		
		if(!this.authority) {
			btimport.setVisible(false);
			btexport.setVisible(false);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnClear) {
			txDateFrom.setDate(null);
			txDateTo.setDate(null);
		}
		if (obj == btnDelete) {
			if (!table.getSelectionModel().isSelectionEmpty()) {
				if (btnDelete.getText().equals("XÓA")) {
					if (JOptionPane.showConfirmDialog(null, "Xóa khuyến mãi này?", "Xác nhận",
							JOptionPane.YES_NO_OPTION) == 0) {
						this.lock();
						String tempID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0));
						saleBus.disable(tempID);
						btnUpdate.setEnabled(false);
						// btnDelete.setText("MỞ KHÓA");

						// saleArr.get(table.getSelectedRow()).setStatus(1);
						JOptionPane.showMessageDialog(null, "Xóa thành công!");

					}
				} else {
					if (JOptionPane.showConfirmDialog(null, "Mở khóa khuyến mãi này?", "Xác nhận",
							JOptionPane.YES_NO_OPTION) == 0) {
						this.unlock();
						String tempID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0));
						saleBus.active(tempID);
						btnUpdate.setEnabled(true);

						// saleArr.get(table.getSelectedRow()).setStatus(0);
						JOptionPane.showMessageDialog(null, "Mở thành công!");

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
			this.dialog = new AddSaleDialog();
			this.dialog.setLocationRelativeTo(null);
			this.dialog.setModal(true);
			this.dialog.setVisible(true);
			Sale temp = this.dialog.getSale();
			if (temp != null) {
				saleBus.add(temp);
				this.searchAndSort();
				JOptionPane.showMessageDialog(null, "Thêm thành công!");
			}
		}
		if (obj == btnUpdate) {
			boolean check = true;
			if (JOptionPane.showConfirmDialog(null, "Bạn muốn sửa thông tin?", "Xác nhận",
					JOptionPane.YES_NO_OPTION) == 0) {
				if (!DateExe.convertDateToString(txStart.getDate()).matches("^[0-9][0-9]/[0-9][0-9]/[0-9]{4}$")
						|| !DateExe.convertDateToString(txEnd.getDate()).matches("^[0-9][0-9]/[0-9][0-9]/[0-9]{4}$")) {
					JOptionPane.showMessageDialog(null, "Ngày phải có dạng dd/MM/yyyy!");
					check = false;
				} else {
					try {
						DateExe.isValidDate(DateExe.convertDateToString(txStart.getDate()));
						DateExe.isValidDate(DateExe.convertDateToString(txEnd.getDate()));
					} catch (Exception p) {
						JOptionPane.showMessageDialog(null, "Ngày không hợp lệ!");
						check = false;
					}

				}
				if (DateExe.compares(DateExe.convertDateToString(txStart.getDate()),
						DateExe.convertDateToString(txEnd.getDate())) > 0) {
					check = false;
					JOptionPane.showMessageDialog(null, "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu!");
				}
				if (String.valueOf(comboMeasure.getSelectedItem()).equals("%")) {

					if (txNum.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
						double test = Double.parseDouble(txNum.getText());
						if (test < 0 || test > 100) {
							JOptionPane.showMessageDialog(this, "Mức khuyến mãi là một con số (0-100%)!");
							check = false;
						}

					} else {
						JOptionPane.showMessageDialog(this, "Mức khuyến mãi là một con số (0-100%)!");
						check = false;
					}
				} else {

					if (!txNum.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
						JOptionPane.showMessageDialog(this, "Mức khuyến mãi là một con số!");
						check = false;
					}
				}
				if (check) {
					Sale p = new Sale(this.txID.getText(), this.txName.getText(),
							Double.parseDouble(this.txNum.getText()),
							String.valueOf(this.comboMeasure.getSelectedItem()),
							DateExe.convertDateToString(txStart.getDate()),
							DateExe.convertDateToString(txEnd.getDate()), Double.parseDouble(this.txThres.getText()),
							String.valueOf(this.comboThresMeasure.getSelectedItem()), 0);
					saleBus.update(p);

					this.searchAndSort();

					this.btnDelete.setText("XÓA");
					this.btnDelete.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Sửa thành công!");
				}

			}
		}
		if (obj == btnApply) {

			this.searchAndSort();
		}
		if (obj == btexport) {
			btexportAction();
		}
		if (obj == btimport) {
			btimportAction();
			this.loadData(saleArr);
		}
	}

	public void btimportAction() {
		Excel excel = new Excel();
		ReadExcelDialog dialogexcel = new ReadExcelDialog("sale");
		dialogexcel.setLocationRelativeTo(null);
		dialogexcel.setModal(true);
		dialogexcel.setVisible(true);
		int select = dialogexcel.getConfirm();
		if (select == 0)
			return;
		else if (select == 1) {
			ArrayList<Sale> arr = excel.readSale(dialogexcel.getLink());
			if (arr == null)
				return;
			for (Sale p : arr) {
				Sale temp = saleBus.searchByID(p.getSale_id());
				if (temp == null)
					saleBus.add(p);
				else
					saleBus.update(p);
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
			excel.writeSale();
			JOptionPane.showMessageDialog(this, "Đã ghi dữ liệu vào file excel thành công!");
		}
	}

	public void loadData(ArrayList<Sale> arr) {
		if (this.getAuthority())
			header = new String[] { "Mã KM", "Tên KM", "Mức KM", "Đ.vị", "Mức áp dụng", "Đ.vị", "Bắt đầu", "Kết thúc",
					"Khóa" };
		else
			header = new String[] { "Mã KM", "Tên KM", "Mức KM", "Đ.vị", "Mức áp dụng", "Đ.vị", "Bắt đầu", "Kết thúc" };
		DefaultTableModel md = new DefaultTableModel(header, 0);
		for (Sale p : arr) {
			Object[] row;
			if (this.getAuthority())
				row = new Object[] { p.getSale_id(), p.getSale_name(), p.getNumber(), p.getMeasure(), p.getThreshold(),
						p.getThreshold_unit(), p.getStart_dateStr(), p.getEnd_dateStr(),
						p.getStatus() == 1 ? "X" : "" };
			else
				row = new Object[] { p.getSale_id(), p.getSale_name(), p.getNumber(), p.getMeasure(), p.getThreshold(),
						p.getThreshold_unit(), p.getStart_dateStr(), p.getEnd_dateStr() };
			md.addRow(row);
		}
		table.setModel(md);
		this.setColSize(table);

	}

	public void showTableData(Sale sale) {
		this.txID.setText(sale.getSale_id());
		this.txName.setText(sale.getSale_name());
		this.txNum.setText(String.valueOf(sale.getNumber()));
		this.comboMeasure.setSelectedItem(sale.getMeasure());
		this.txThres.setText(String.valueOf(sale.getThreshold()));
		this.comboThresMeasure.setSelectedItem(sale.getThreshold_unit());
		this.txStart.setDate(DateExe.convertStringToDate(sale.getStart_dateStr()));
		this.txEnd.setDate(DateExe.convertStringToDate(sale.getEnd_dateStr()));

	}

	public boolean getAuthority() {
		return this.authority;
	}

	public void setColSize(JTable table) {
		TableColumnModel col = table.getColumnModel();
		this.setFixColSize(col, 0, 20);
		this.setFixColSize(col, 1, 200);
		this.setFixColSize(col, 2, 80);
		this.setFixColSize(col, 3, 20);
		this.setFixColSize(col, 4, 80);
		this.setFixColSize(col, 5, 20);
		this.setFixColSize(col, 6, 100);
		this.setFixColSize(col, 7, 100);
		if (this.getAuthority())
			this.setFixColSize(col, 8, 8);

	}

	public void setFixColSize(TableColumnModel col, int index, int size) {
		col.getColumn(index).setMinWidth(size);
		// col.getColumn(index).setResizable(false);
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
		if (this.getAuthority())
			saleArr = saleBus.readAll();
		else
			saleArr = saleBus.read();
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
			saleArr = saleBus.search(saleArr, txSearch.getText(), this.getSelectedButtonText(bg), from, to);
			saleArr = saleBus.sort(saleArr, String.valueOf(comboChoice.getSelectedItem()),
					String.valueOf(comboOrder.getSelectedItem()), String.valueOf(comboType.getSelectedItem()));
			this.loadData(saleArr);
			if (saleArr.size() == 0)
				JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả nào!");
		}
	}

	public void getData() {
		if (this.getAuthority())
			saleArr = saleBus.readAll();
		else
			saleArr = saleBus.read();
	}

	public void lock() {
		this.txName.setEditable(false);
		this.txNum.setEditable(false);
		this.txThres.setEditable(false);
		this.txStart.setEnabled(false);
		this.txEnd.setEnabled(false);
		this.comboMeasure.setEnabled(false);
		this.comboThresMeasure.setEnabled(false);
	}

	public void unlock() {
		this.txName.setEditable(true);
		this.txNum.setEditable(true);
		this.txThres.setEditable(true);
		this.txStart.setEnabled(true);
		this.txEnd.setEnabled(true);
		this.comboMeasure.setEnabled(true);
		this.comboThresMeasure.setEnabled(true);
	}

	public void clear() {
		this.txID.setText("");
		this.txName.setText("");
		this.txNum.setText("");
		this.txThres.setText("");
		this.txStart.setDate(null);
		this.txEnd.setDate(null);
		this.comboMeasure.setSelectedItem(null);
		this.comboThresMeasure.setSelectedItem(null);
	}
}
