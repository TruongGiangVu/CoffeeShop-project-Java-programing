package gui;

import java.text.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.table.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

import bus.DateExe;
import bus.EmployerBUS;
import bus.Excel;
import dto.Employer;
import dto.Ingredient;
import design.*;
import java.util.*;
import java.util.regex.Pattern;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.JRadioButton;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;



public class EmployerGUI extends JPanel implements ActionListener {
	private boolean authority;
	private JTextField txID;
	private JTextField txName;
	private JTextField txAddress;
	private JTextField txPhone;
	private JTextField txSalary;
	private JTextField txShift;
	private JTextField txUser;
	private JPasswordField txPass;
	private JPasswordField txRePass;
	private JTable table = new myTable();
	private Employer emp;
	private JButton btnUpdate;
	private ButtonGroup btngrp;
	private ArrayList<Employer> empArr = new ArrayList<Employer>();
	private EmployerBUS empBus = new EmployerBUS();
	private JTextField txSearch;
	private JButton btnApply;
	private JButton btnDelete;
	private JButton btnAdd;
	private String[] header;
	private JComboBox<String> choiceOrder;
	private JComboBox<String> choiceType;
	private JRadioButton rdbtnID;
	private JRadioButton rdbtnName;
	private AddEmpDialog dialog;
	private JComboBox<String> comboJob;
	private JTextField txDate;
	private JButton btimport = new JButton();
	private JButton btexport = new JButton();
	/**
	 * Launch the application.
	 */

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { PanelEmpList frame = new
	 * PanelEmpList(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 * 
	 * /** Create the frame.
	 */
	public EmployerGUI(String id) {
                design.Icon icon = new design.Icon();
		emp = empBus.searchByID(id);
		authority = emp.isEmp_type();
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(1000, 700);
		setLayout(null);
		this.setBackground(new Color(78, 186, 163));
		
		

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 250, 650);
		add(panel);
		panel.setLayout(null);
                panel.setBackground(new Color(78, 186, 163));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "Thông tin cá nhân", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 11, 230, 269);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblMNv = new JLabel("Mã NV:");
		lblMNv.setBounds(23, 33, 52, 20);
		panel_2.add(lblMNv);

		txID = new JTextField(emp.getEmp_id());
		txID.setEditable(false);
		txID.setBounds(85, 30, 70, 30);
		panel_2.add(txID);
		txID.setColumns(10);

		JLabel lblTnNv = new JLabel("Tên NV:");
		lblTnNv.setBounds(23, 64, 48, 20);
		panel_2.add(lblTnNv);

		txName = new JTextField(emp.getEmp_name());
		txName.setBounds(85, 61, 135, 30);
		panel_2.add(txName);
		txName.setColumns(10);

		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setBounds(23, 95, 48, 20);
		panel_2.add(lblaCh);

		txAddress = new JTextField(emp.getAddress());
		txAddress.setBounds(85, 92, 135, 30);
		panel_2.add(txAddress);
		txAddress.setColumns(10);

		JLabel lblSt = new JLabel("SĐT:");
		lblSt.setBounds(23, 126, 48, 20);
		panel_2.add(lblSt);

		txPhone = new JTextField(emp.getPhone());
		txPhone.setBounds(85, 123, 135, 30);
		panel_2.add(txPhone);
		txPhone.setColumns(10);

		JLabel lblNgyVoLm = new JLabel("Vào làm:");
		lblNgyVoLm.setBounds(23, 157, 59, 20);
		panel_2.add(lblNgyVoLm);

		JLabel lblLng = new JLabel("Lương:");
		lblLng.setBounds(23, 188, 48, 20);
		panel_2.add(lblLng);

		txSalary = new JTextField(String.valueOf(emp.getSalary()));
		txSalary.setBounds(85, 185, 135, 30);
		panel_2.add(txSalary);
		txSalary.setColumns(10);

		JLabel lblCaLm = new JLabel("Ca làm:");
		lblCaLm.setBounds(23, 219, 48, 20);
		panel_2.add(lblCaLm);

		txShift = new JTextField(String.valueOf(emp.getShift()));
		txShift.setBounds(85, 216, 135, 30);
		panel_2.add(txShift);
		txShift.setColumns(10);
		
		txDate = new JTextField(emp.getStart_dateStr());
		txDate.setEditable(false);
		txDate.setBounds(85, 154, 135, 30);
		panel_2.add(txDate);
		txDate.setColumns(10);
                
                panel_2.setBackground(new Color(204,228,228));
		
		if(!authority) {
			txSalary.setEditable(false);
			txShift.setEditable(false);
		}
		JPanel panel_3 = new JPanel();
                panel_3.setBackground(new Color(204,228,228));
		panel_3.setBorder(
				new TitledBorder(null, "Thông tin tài khoản", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 291, 230, 196);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblChcV = new JLabel("Chức vụ:");
		lblChcV.setBounds(25, 47, 69, 20);
		panel_3.add(lblChcV);

		JLabel lblTiKhon = new JLabel("Tài khoản:");
		lblTiKhon.setBounds(25, 78, 69, 20);
		panel_3.add(lblTiKhon);

		txUser = new JTextField(emp.getUsername());
		txUser.setBounds(104, 75, 101, 30);
		panel_3.add(txUser);
		txUser.setColumns(10);

		JLabel lblMtKhu = new JLabel("Mật khẩu:");
		lblMtKhu.setBounds(25, 109, 69, 20);
		panel_3.add(lblMtKhu);

		txPass = new JPasswordField(emp.getPassword());
		txPass.setBounds(104, 106, 101, 30);
		txPass.getDocument().addDocumentListener(new DocumentListener() {
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
				txRePass.setEditable(true);
			}
		});
		panel_3.add(txPass);

		JLabel lblNhpLi = new JLabel("Nhập lại:");
		lblNhpLi.setBounds(25, 140, 69, 20);
		panel_3.add(lblNhpLi);
		this.

		txRePass = new JPasswordField();
		this.txRePass.setEditable(false);
		txRePass.setBounds(104, 137, 101, 30);
		panel_3.add(txRePass);
		
		String[] jobs = {"Admin","Nhân viên"};
		comboJob = new JComboBox(jobs);
		comboJob.setBounds(104, 43, 101, 30);
		panel_3.add(comboJob);
		if(authority) {
			comboJob.setSelectedItem("Admin");
		}else {
			comboJob.setEnabled(false);
			comboJob.setSelectedItem("Nhân viên");
		}

		btnUpdate = new JButton("SỬA");
		btnUpdate.setBounds(60, 498, 130, 30);
		btnUpdate.addActionListener(this);
                btnUpdate.setBackground(icon.getEditColor());
                btnUpdate.setIcon(icon.imageIconSize("icons/edit.png", 20, 20));
                btnUpdate.setFont(new Font("sans-serif", Font.BOLD, 14));
                btnUpdate.setForeground(Color.white);
		panel.add(btnUpdate);
		
		btimport.setToolTipText("Đọc dữ liệu từ file excel mà bạn chọn.");
		btimport.setBorder(null);
		btimport.setBackground(Color.WHITE);
		btimport.setBounds(10, 588, 40, 40);
		panel.add(btimport);
		
		btexport.setToolTipText("Ghi dữ liệu vào file excel");
		btexport.setBorder(null);
		btexport.setBackground(Color.WHITE);
		btexport.setBounds(60, 588, 40, 40);
		panel.add(btexport);
		
		btimport.setIcon(icon.imageIconSize("icons/import2.png", 40, 40));
		btimport.setBackground(new Color(255, 255, 255));
		btimport.setBorder(null);

		btexport.setIcon(icon.imageIconSize("icons/export2.png", 40, 40));
		btexport.setBackground(new Color(255, 255, 255));
		btexport.setBorder(null);
		btimport.addActionListener(this);
		btexport.addActionListener(this);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(270, 11, 710, 650);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblSpXpTheo = new JLabel("Sắp xếp theo:");
		lblSpXpTheo.setBounds(33, 28, 86, 20);
		panel_1.add(lblSpXpTheo);

		String[] combo1 = { "Mã", "Tên", "Lương", "Ngày vào làm", "Ca" };
		choiceType = new JComboBox(combo1);
		choiceType.setBounds(121, 24, 135, 30);
		panel_1.add(choiceType);

		JLabel lblChiu = new JLabel("Thứ tự:");
		lblChiu.setBounds(320, 28, 48, 20);
		panel_1.add(lblChiu);

		String[] combo2 = { "Tăng dần", "Giảm dần" };
		choiceOrder = new JComboBox(combo2);
		choiceOrder.setBounds(378, 24, 135, 30);
		panel_1.add(choiceOrder);
                panel_1.setBackground(new Color(204,228,228));
                panel_1.setBorder(
				new TitledBorder(null, "Danh sách nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(15, 101, 680, 500);
		panel_1.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		if(this.authority) {
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					String tempID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0));
					Employer emp = empBus.searchByID(tempID);
					if(emp.getStatus() == 1) {
						btnDelete.setText("MỞ KHÓA");
						btnUpdate.setEnabled(false);
						lock();
					}else {
						btnDelete.setText("XÓA");
						btnUpdate.setEnabled(true);
						unlock();
					}
					btnDelete.setEnabled(true);
					showTableData(emp);
					
				}
			});
			table.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					String tempID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0));
					Employer emp = empBus.searchByID(tempID);
					if(emp.getStatus() == 1) {
						btnDelete.setText("MỞ KHÓA");
						btnUpdate.setEnabled(false);
						lock();
					}else {
						btnDelete.setText("XÓA");
						btnUpdate.setEnabled(true);
						unlock();
					}
					btnDelete.setEnabled(true);
					showTableData(emp);
				}
			});
		}
		
		
		

		JScrollPane scrollPane = new JScrollPane(table);
		panel_4.add(scrollPane);

		table.setSize(700, 500);

		btnApply = new JButton("ÁP DỤNG");
		btnApply.setBounds(550, 28, 120, 48);
		btnApply.addActionListener(this);
                btnApply.setBackground(icon.getSearchColor());
                btnApply.setFont(new Font("sans-serif", Font.BOLD, 14));
                btnApply.setForeground(Color.black);
                btnApply.setIcon(icon.imageIconSize("icons/search_more.png", 20, 20));
		panel_1.add(btnApply);

		JLabel lblTmKimTheo = new JLabel("Tìm kiếm:");
		lblTmKimTheo.setBounds(33, 68, 86, 20);
		panel_1.add(lblTmKimTheo);

		btngrp = new ButtonGroup();

		rdbtnID = new JRadioButton("Mã"); rdbtnID.setSelected(true);
		rdbtnID.setBounds(320, 69, 65, 23);
		panel_1.add(rdbtnID);

		rdbtnName = new JRadioButton("Tên");
		rdbtnName.setBounds(404, 69, 109, 23);
		panel_1.add(rdbtnName);

		btngrp.add(rdbtnID);
		btngrp.add(rdbtnName);

		txSearch = new JTextField();
		txSearch.setBounds(121, 63, 135, 30);
		panel_1.add(txSearch);
		txSearch.setColumns(10);

		if (authority) {
			btnAdd = new JButton("THÊM");
			btnAdd.setBounds(10, 612, 120, 23);
			btnAdd.addActionListener(this);
                        btnAdd.setBackground(icon.getInsertColor());
                        btnAdd.setFont(new Font("sans-serif", Font.BOLD, 14));
                        btnAdd.setForeground(Color.white);
                        btnAdd.setIcon(icon.imageIconSize("icons/add.png", 20, 20));
			panel_1.add(btnAdd);

			btnDelete = new JButton("XÓA");
			btnDelete.setBounds(140, 612, 120, 23);
			btnDelete.addActionListener(this);
                        btnDelete.setForeground(Color.white);
                        btnDelete.setFont(new Font("sans-serif", Font.BOLD, 14));
                        btnDelete.setBackground(icon.getDeleteColor());
                        btnDelete.setIcon(icon.imageIconSize("icons/delete.png", 20, 20));
			panel_1.add(btnDelete);

		}
		
		if(this.authority) empArr = empBus.readAll();
		else empArr = empBus.read();
		this.loadData(empArr);
		if(!this.authority) {
			btimport.setVisible(false);
			btexport.setVisible(false);
		}
	}

	public void loadData(ArrayList<Employer> arr) {
		if(this.getAuthority()) 
			header = new String[]{ "Mã NV", "Tên NV", "Địa chỉ", "Số điện thoại", "Ngày vào làm", "Lương", "Ca", "Chức vụ", "Khóa" };
		else 
			header = new String[]{ "Mã NV", "Tên NV", "Địa chỉ", "Số điện thoại", "Ngày vào làm", "Lương", "Ca", "Chức vụ" };
		DefaultTableModel md = new DefaultTableModel(header, 0);
		for (Employer p : arr) {
			Object[] row;
			if(this.getAuthority()) 
				row = new Object[]{ p.getEmp_id(), p.getEmp_name(), p.getAddress(), p.getPhone(), p.getStart_dateStr(),
						p.getSalary(), p.getShift(), p.isEmp_type() ? "Admin" : "Nhân viên", p.getStatus() == 1?"X":"" };
			else row = new Object[]{ p.getEmp_id(), p.getEmp_name(), p.getAddress(), p.getPhone(), p.getStart_dateStr(),
					p.getSalary(), p.getShift(), p.isEmp_type() ? "Admin" : "Nhân viên"};
			md.addRow(row);
		}
		table.setModel(md);
		this.setColSize(table);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnUpdate) {
			boolean check = true;
			if (JOptionPane.showConfirmDialog(null, "Bạn muốn sửa thông tin?", "Xác nhận",
					JOptionPane.YES_NO_OPTION) == 0) {
				if (!txPhone.getText().matches("^[0-9]{10}$")) {
					JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 chữ số!");
					check = false;
				}
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
				if (!txSalary.getText().matches("^\\d+.?\\d*$")) {
					JOptionPane.showMessageDialog(null, "Lương phải là một con số!");
					check = false;
				}
				if (!txShift.getText().matches("^\\d$")) {
					JOptionPane.showMessageDialog(null, "Ca làm phải là một chữ số!");
					check = false;
				}
				if(this.getAuthority() && txID.getText().equals(emp.getEmp_id()) && !String.valueOf(comboJob.getSelectedItem()).equals("Admin")) {
					JOptionPane.showMessageDialog(null, "Không được tự thay đổi chức vụ!");
					check = false;
				}
				if(txUser.getText().length() < 5) {
					JOptionPane.showMessageDialog(null, "Tài khoản phải có từ 5 kí tự trở lên!");
					check = false;
				}
				if(String.valueOf(txPass.getPassword()).length() < 5) {
					JOptionPane.showMessageDialog(null, "Mật khẩu phải có từ 5 kí tự trở lên!");
					check = false;
				}
				if (txRePass.isEditable()) {
					if (String.valueOf(txPass.getPassword()).equals(String.valueOf(txRePass.getPassword()))) {

					} else {
						JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không đúng");
						this.txRePass.setText("");
						check = false;
					}
				}
				if (check) {
					Employer p = new Employer(this.txID.getText(), this.txName.getText(), this.txAddress.getText(),
							this.txPhone.getText(), txDate.getText(),
							Double.parseDouble(this.txSalary.getText()), Integer.parseInt(this.txShift.getText()),
							String.valueOf(comboJob.getSelectedItem()).equals("Admin")?true:false, this.txUser.getText(), String.valueOf(txPass.getPassword()),0);
					empBus.update(p);
					this.txRePass.setText("");
					this.txRePass.setEditable(false);
					
					this.searchAndSort();
					
					if(this.getAuthority()) {
						this.btnDelete.setText("XÓA");
						this.btnDelete.setEnabled(false);
					}
					JOptionPane.showMessageDialog(null, "Sửa thành công");
				}

			}
		}
		if (obj == this.btnApply) {
			this.searchAndSort();
		}
		if (obj == this.btnAdd) {
			if(empBus.readAll().size() < 100000) {
				this.dialog = new AddEmpDialog();
				this.dialog.setLocationRelativeTo(null);
				this.dialog.setModal(true);
				this.dialog.setVisible(true);
				Employer temp = this.dialog.getEmp();
				if(temp != null) {
					empBus.add(temp);
					this.searchAndSort();
					JOptionPane.showMessageDialog(null, "Thêm thành công");
				}
			}else JOptionPane.showMessageDialog(null, "Bộ nhớ đã đầy!");
		}
		if (obj == this.btnDelete) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
					if(txID.getText().equals(emp.getEmp_id())) {
						JOptionPane.showMessageDialog(null, "Không thể tự xóa tài khoản!");
					}else {
						if(btnDelete.getText().equals("XÓA")) {
							if(JOptionPane.showConfirmDialog(null, "Khóa tài khoản này (ẩn đối với nhân viên)?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
								this.lock();
								String tempID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0));
								empBus.disable(tempID);
								btnUpdate.setEnabled(false);
								//btnDelete.setText("MỞ KHÓA");
								
								empArr.get(table.getSelectedRow()).setStatus(1);
								JOptionPane.showMessageDialog(null, "Xóa thành công!");
								
							}
						}else {
							if(JOptionPane.showConfirmDialog(null, "Mở khóa tài khoản này (hiện đối với nhân viên)?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
								this.unlock();
								String tempID = String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0));
								empBus.active(tempID);
								btnUpdate.setEnabled(true);
								btnDelete.setText("XÓA");
								empArr.get(table.getSelectedRow()).setStatus(0);
								JOptionPane.showMessageDialog(null, "Mở thành công!");
							}
						}
						this.searchAndSort();
					}
				}
				else JOptionPane.showMessageDialog(null, "Không có dòng nào được chọn!");
		}
		if (obj == btexport) {
			btexportAction();
		} 
		if (obj == btimport) {
			btimportAction();
			this.loadData(empArr);
		}
	}

	public void btimportAction() {
		Excel excel = new Excel();
		ReadExcelDialog dialogexcel = new ReadExcelDialog("employer");
		dialogexcel.setLocationRelativeTo(null);
		dialogexcel.setModal(true);
		dialogexcel.setVisible(true);
		int select = dialogexcel.getConfirm();
		if (select == 0)
			return;
		else if (select == 1) {
			ArrayList<Employer> arr = excel.readEmployer(dialogexcel.getLink());
			if (arr == null)
				return;
			for (Employer p : arr) {
				Employer temp = empBus.searchByID(p.getEmp_id());
				if (temp == null)
					empBus.add(p);
				else
					empBus.update(p);
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
			excel.writeEmployer();
			JOptionPane.showMessageDialog(this, "Đã ghi dữ liệu vào file excel thành công!");
		}
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
		if(this.getAuthority())
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
	public void searchAndSort() {
		empArr = this.authority?empBus.readAll():empBus.read();
		empArr = empBus.search(empArr, this.txSearch.getText(), this.getSelectedButtonText(btngrp));
		empArr = empBus.sort(empArr, String.valueOf(this.choiceType.getSelectedItem()), String.valueOf(this.choiceOrder.getSelectedItem()));
		this.loadData(empArr);
		if(empArr.size() == 0) JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả nào!");
	}
	public void showTableData(Employer emp) {
		this.txID.setText(emp.getEmp_id());
		this.txName.setText(emp.getEmp_name());
		this.txAddress.setText(emp.getAddress());
		this.txPhone.setText(emp.getPhone());
		this.txDate.setText(emp.getStart_dateStr());
		this.txSalary.setText(String.valueOf(emp.getSalary()));
		this.txShift.setText(String.valueOf(emp.getShift()));
		if(emp.isEmp_type()) this.comboJob.setSelectedItem("Admin");
			else this.comboJob.setSelectedItem("Nhân viên");
		this.txUser.setText(emp.getUsername());
		this.txPass.setText(emp.getPassword());
		this.txRePass.setText(""); this.txRePass.setEditable(false);
	}
	public void lock() {
	
		this.txName.setEditable(false);
		this.txAddress.setEditable(false);
		this.txPhone.setEditable(false);
		this.txDate.setEditable(false);
		this.txSalary.setEditable(false);
		this.txShift.setEditable(false);
		this.comboJob.setEnabled(false);
		this.txUser.setEditable(false);
		this.txPass.setEditable(false);
		
	}
	public void unlock() {
		this.txName.setEditable(true);
		this.txAddress.setEditable(true);
		this.txPhone.setEditable(true);
		this.txDate.setEditable(true);
		this.txSalary.setEditable(true);
		this.txShift.setEditable(true);
		this.comboJob.setEnabled(true);
		this.txUser.setEditable(true);
		this.txPass.setEditable(true);
	}
}
