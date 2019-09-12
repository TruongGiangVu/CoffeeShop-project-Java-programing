package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.util.*;
import javax.swing.JPasswordField;

import bus.DateExe;

import com.toedter.calendar.JDateChooser;

import dto.Employer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import bus.EmployerBUS;

public class AddEmpDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblMNv;
	private JLabel lblTnNv;
	private JLabel lblaCh;
	private JLabel lblSt;
	private JLabel lblNgyVoLm;
	private JTextField txPhone;
	private JTextField txAddress;
	private JTextField txName;
	private JTextField txID;
	private JLabel lblLng;
	private JTextField txSalary;
	private JLabel lblNewLabel;
	private JTextField txShift;
	private JComboBox<String> comboJob;
	private JLabel lblChcV;
	private JLabel lblTiKhon;
	private JTextField txUser;
	private JLabel lblMtKhu;
	private JPasswordField txPass;
	private JLabel lblNhpLi;
	private JPasswordField txRePass;
	private JDateChooser txDate;
	private EmployerBUS empBus = new EmployerBUS();
	private boolean confirm;
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			AddEmpDialog dialog = new AddEmpDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddEmpDialog() {
		setTitle("Thêm nhân viên");
		setBounds(100, 100, 516, 341);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblMNv = new JLabel("Mã NV:");
		lblMNv.setBounds(21, 25, 48, 20);
		contentPanel.add(lblMNv);
		
		lblTnNv = new JLabel("Tên NV:");
		lblTnNv.setBounds(21, 60, 48, 20);
		contentPanel.add(lblTnNv);
		
		lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setBounds(21, 95, 48, 20);
		contentPanel.add(lblaCh);
		
		lblSt = new JLabel("SĐT:");
		lblSt.setBounds(21, 130, 48, 20);
		contentPanel.add(lblSt);
		
		lblNgyVoLm = new JLabel("Ngày vào làm:");
		lblNgyVoLm.setBounds(21, 165, 81, 20);
		contentPanel.add(lblNgyVoLm);
		
		txPhone = new JTextField();
		txPhone.setBounds(92, 127, 106, 30);
		contentPanel.add(txPhone);
		txPhone.setColumns(10);
		
		txAddress = new JTextField();
		txAddress.setBounds(92, 92, 160, 30);
		contentPanel.add(txAddress);
		txAddress.setColumns(10);
		
		txName = new JTextField();
		txName.setBounds(92, 57, 160, 30);
		contentPanel.add(txName);
		txName.setColumns(10);
		
		txID = new JTextField();
		txID.setEditable(false);
		txID.setBounds(92, 22, 106, 30);
		contentPanel.add(txID);
		txID.setColumns(10);
		
		lblLng = new JLabel("Lương:");
		lblLng.setBounds(21, 200, 48, 20);
		contentPanel.add(lblLng);
		
		txSalary = new JTextField();
		txSalary.setBounds(92, 197, 106, 30);
		contentPanel.add(txSalary);
		txSalary.setColumns(10);
		
		lblNewLabel = new JLabel("Ca:");
		lblNewLabel.setBounds(21, 235, 48, 20);
		contentPanel.add(lblNewLabel);
		
		txShift = new JTextField();
		txShift.setBounds(92, 232, 48, 30);
		contentPanel.add(txShift);
		txShift.setColumns(10);
		
		String[] jobs= {"Nhân viên", "Admin"};
		comboJob = new JComboBox(jobs);
		comboJob.setBounds(343, 21, 106, 30);
		contentPanel.add(comboJob);
		
		lblChcV = new JLabel("Chức vụ:");
		lblChcV.setBounds(275, 25, 61, 20);
		contentPanel.add(lblChcV);
		
		lblTiKhon = new JLabel("Tài khoản:");
		lblTiKhon.setBounds(275, 60, 61, 20);
		contentPanel.add(lblTiKhon);
		
		txUser = new JTextField();
		txUser.setBounds(343, 57, 140, 30);
		contentPanel.add(txUser);
		txUser.setColumns(10);
		
		lblMtKhu = new JLabel("Mật khẩu:");
		lblMtKhu.setBounds(275, 95, 61, 20);
		contentPanel.add(lblMtKhu);
		
		txPass = new JPasswordField();
		txPass.setBounds(343, 92, 140, 30);
		contentPanel.add(txPass);
		
		lblNhpLi = new JLabel("Nhập lại:");
		lblNhpLi.setBounds(275, 130, 61, 20);
		contentPanel.add(lblNhpLi);
		
		txRePass = new JPasswordField();
		txRePass.setBounds(343, 127, 140, 30);
		contentPanel.add(txRePass);
		
		txDate = new JDateChooser(new Date());
		txDate.setBounds(102, 162, 150, 30);
		txDate.setDateFormatString("dd/MM/yyyy");
		txDate.setDate(new Date());
		
		
		contentPanel.add(txDate);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Thêm");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(checkValid()) {
							confirm = true;
							dispose();
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Hủy");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						confirm = false;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		setID();
	}
	private void setID() {
		int temp = empBus.readAll().size();
		txID.setText(String.format("%05d", temp));
	}
	private Employer getEmp(boolean confirm) {
		if(confirm) {
			Employer emp = new Employer(txID.getText(),txName.getText(),txAddress.getText(),txPhone.getText(),DateExe.convertDateToString(txDate.getDate()),
						Double.parseDouble(txSalary.getText()),Integer.parseInt(txShift.getText()),String.valueOf(comboJob).equals("Admin")?true:false,
								txUser.getText(),String.valueOf(txPass.getPassword()),0);
			return emp;
		}
		else return null;
		
	}
	public Employer getEmp() {
		return getEmp(this.confirm);
	}
	public boolean checkValid() {
		boolean check = true;
		if(!txID.getText().matches("^\\d{5}$")) {
			JOptionPane.showMessageDialog(this, "Mã nhân viên phải là 5 chữ số!");
			check = false;
		}
		if (!txPhone.getText().matches("^[0-9]{10}$")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 chữ số!");
			check = false;
		}
		if (!DateExe.convertDateToString(txDate.getDate()).matches("^[0-9][0-9]/[0-9][0-9]/[0-9]{4}$")) {
			JOptionPane.showMessageDialog(this, "Ngày phải có dạng dd/MM/yyyy!");
			check = false;
		}else {
			try {
				DateExe.isValidDate(DateExe.convertDateToString(txDate.getDate()));
				if(DateExe.compares(DateExe.convertDateToString(txDate.getDate()), DateExe.convertDateToString(new Date())) < 0) {
					JOptionPane.showMessageDialog(this, "Ngày vào làm không được nhỏ hơn ngày hiện tại!");
					check = false;
				}
			}
			catch(Exception p)
			{
				JOptionPane.showMessageDialog(this, "Ngày không hợp lệ!");
				check = false;
			}
			
		}
		if (!txSalary.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
			JOptionPane.showMessageDialog(this, "Lương là một con số!");
			check = false;
		}
		if (!txShift.getText().matches("^[1-9]$")) {
			JOptionPane.showMessageDialog(this, "Ca làm là một chữ số nguyên > 0!");
			check = false;
		}
		if(txUser.getText().length() < 5) {
			JOptionPane.showMessageDialog(null, "Tài khoản phải có từ 5 kí tự trở lên!");
			check = false;
		}
		if(String.valueOf(txPass.getPassword()).length() < 5) {
			JOptionPane.showMessageDialog(null, "Mật khẩu phải có từ 5 kí tự trở lên!");
			check = false;
		}else if(!String.valueOf(txPass.getPassword()).equals(String.valueOf(txRePass.getPassword()))) {
			check=false;
			JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không khớp!");
		}
		
		
		Employer temp = empBus.searchByID(this.txID.getText());
		if(temp != null) {
			check = false;
			JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại!");
		}
		return check;
	}
}
