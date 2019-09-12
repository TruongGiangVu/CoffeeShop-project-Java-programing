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
import com.toedter.calendar.JDateChooser;

import bus.DateExe;
import dto.Sale;
import bus.*;

import java.time.*;
import java.util.*;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddSaleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txID;
	private JTextField txName;
	private JDateChooser txStart;
	private JDateChooser txEnd;
	private JLabel lblLoiKm;
	private JComboBox comboType;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField txNum;
	private JComboBox comboMeasure;
	private JTextField txThres;
	private JComboBox comboThresMeasure;
	private boolean confirm = false;
	private SaleBUS saleBus = new SaleBUS();
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			AddSaleDialog dialog = new AddSaleDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddSaleDialog() {
		setTitle("Thêm khuyến mãi");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMKm = new JLabel("Mã KM:");
			lblMKm.setBounds(10, 56, 48, 20);
			contentPanel.add(lblMKm);
		}
		{
			JLabel lblTnKm = new JLabel("Tên KM:");
			lblTnKm.setBounds(10, 95, 48, 20);
			contentPanel.add(lblTnKm);
		}
		{
			JLabel lblBtu = new JLabel("Bắt đâu:");
			lblBtu.setBounds(10, 135, 48, 20);
			contentPanel.add(lblBtu);
		}
		{
			JLabel lblKtThc = new JLabel("Kết thúc:");
			lblKtThc.setBounds(10, 171, 48, 20);
			contentPanel.add(lblKtThc);
		}
		{
			txID = new JTextField();
			txID.setEditable(false);
			txID.setBounds(68, 53, 100, 30);
			contentPanel.add(txID);
			txID.setColumns(10);
		}
		{
			txName = new JTextField();
			txName.setBounds(68, 92, 158, 30);
			contentPanel.add(txName);
			txName.setColumns(10);
		}
		
		txStart = new JDateChooser();
		txStart.setDate(new Date());
		txStart.setDateFormatString("dd/MM/yyyy");
		txStart.setBounds(68, 129, 158, 30);
		contentPanel.add(txStart);
		
		txEnd = new JDateChooser(new Date());
		txEnd.setDateFormatString("dd/MM/yyyy");
		txEnd.setBounds(68, 165, 158, 30);
		contentPanel.add(txEnd);
		
		lblLoiKm = new JLabel("Loại KM:");
		lblLoiKm.setBounds(10, 19, 59, 20);
		contentPanel.add(lblLoiKm);
		
		String[] types = {"Sản phẩm","Hóa đơn"};
		comboType = new JComboBox(types);
		comboType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox temp = (JComboBox) e.getSource();
				int k=0;
				if(String.valueOf(temp.getSelectedItem()).equals("Sản phẩm")) {
					comboThresMeasure.setSelectedItem("món");
					comboThresMeasure.setEnabled(false);
					txThres.setText("1.0");
					txThres.setEditable(false);
					k=saleBus.getSaleForProd(saleBus.readAll()).size();
				}else {
					comboThresMeasure.setEnabled(true);
					txThres.setEditable(true);
					k=saleBus.getSaleForBill(saleBus.readAll()).size();
				}
				if(k < 10000) setID(String.valueOf(temp.getSelectedItem()).equals("Sản phẩm")?"A":"B");
				else txID.setText("Error");
			}
		});
		comboType.setBounds(68, 15, 100, 30);
		contentPanel.add(comboType);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Mức khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(240, 15, 184, 80);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		txNum = new JTextField();
		txNum.setBounds(10, 32, 85, 30);
		panel.add(txNum);
		txNum.setColumns(10);
		
		String[] meas = {"đồng","%"};
		comboMeasure = new JComboBox(meas);
		comboMeasure.setBounds(104, 31, 70, 30);
		panel.add(comboMeasure);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Mức áp dụng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(240, 120, 184, 80);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		txThres = new JTextField();
		txThres.setText("1.0");
		txThres.setEditable(false);
		txThres.setBounds(10, 33, 85, 30);
		panel_1.add(txThres);
		txThres.setColumns(10);
		
		String[] thres = {"đồng","món"};
		comboThresMeasure = new JComboBox(thres);
		comboThresMeasure.setBounds(105, 32, 69, 30);
		panel_1.add(comboThresMeasure);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("THÊM");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
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
				JButton cancelButton = new JButton("HỦY");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		comboThresMeasure.setSelectedItem("món");
		comboThresMeasure.setEnabled(false);
		
		setID("A");
	}
	private void setID(String s) {
		txID.setText(String.format("%s%04d", s, s.equals("A")?saleBus.getSaleForProd(saleBus.readAll()).size():saleBus.getSaleForBill(saleBus.readAll()).size()) );
	}
	private Sale getSale(boolean confirm) {
		if(confirm) {
			Sale temp = new Sale(txID.getText(),txName.getText(),Double.parseDouble(txNum.getText()), String.valueOf(comboMeasure.getSelectedItem()),
					DateExe.convertDateToString(txStart.getDate()), DateExe.convertDateToString(txEnd.getDate()),
					Double.parseDouble(txThres.getText().equals("")?"1":txThres.getText()), String.valueOf(comboThresMeasure.getSelectedItem()),0);
			return temp;
		}
		else return null;
	}
	public Sale getSale() {
		return getSale(confirm);
	}
	public boolean checkValid() {
		boolean check = true;
		if(String.valueOf(comboType.getSelectedItem()).equals("Sản phẩm")) {
			if(txID.getText().equals("Error")) {
				JOptionPane.showMessageDialog(this, "Hết mã khuyến mãi cho sản phẩm!"); check = false;
			}
			
		}else {
			if(txID.getText().equals("Error")) {
				JOptionPane.showMessageDialog(this, "Hết mã khuyến mãi cho hóa đơn!"); check = false;
			}

		}

		if(!txID.getText().equals("Error") && !txID.getText().matches("^\\w\\d{4}$")) {
			JOptionPane.showMessageDialog(this, "Mã khuyến mãi phải là 4 chữ số!");
			check = false;
		}
		if (!DateExe.convertDateToString(txStart.getDate()).matches("^[0-9][0-9]/[0-9][0-9]/[0-9]{4}$") || 
				!DateExe.convertDateToString(txEnd.getDate()).matches("^[0-9][0-9]/[0-9][0-9]/[0-9]{4}$")) {
			JOptionPane.showMessageDialog(null, "Ngày phải có dạng dd/MM/yyyy!");
			check = false;
		}else {
			try {
				DateExe.isValidDate(DateExe.convertDateToString(txStart.getDate()));
				DateExe.isValidDate(DateExe.convertDateToString(txEnd.getDate()));
			}
			catch(Exception p)
			{
				JOptionPane.showMessageDialog(null, "Ngày không hợp lệ!");
				check = false;
			}
			
		}
		if(DateExe.compares(DateExe.convertDateToString(txStart.getDate()), DateExe.convertDateToString(txEnd.getDate())) > 0) {
			check = false;
			JOptionPane.showMessageDialog(null, "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu!");
		}
		if(String.valueOf(comboMeasure.getSelectedItem()).equals("%")) {
			
			if (txNum.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
				double test = Double.parseDouble(txNum.getText());
				if(test < 0 || test > 100) {
					JOptionPane.showMessageDialog(this, "Mức khuyến mãi là một con số (0-100%)!");
					check = false;
				}
				
			}else {
				JOptionPane.showMessageDialog(this, "Mức khuyến mãi là một con số (0-100%)!");
				check = false;
			}
		}else {
			if (!txNum.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
				JOptionPane.showMessageDialog(this, "Mức khuyến mãi là một con số!");
				check = false;
			}
		}
		if(!String.valueOf(comboType.getSelectedItem()).equals("Sản phẩm")) {
			if (!txThres.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
				JOptionPane.showMessageDialog(this, "Mức áp dụng là một con số!");
				check = false;
			}
		}
		String tempID = String.valueOf(comboType.getSelectedItem()).equals("Sản phẩm")?"A":"B";
		tempID += txID.getText();
		Sale temp = saleBus.searchByID(tempID);
		if(temp != null) {
			JOptionPane.showMessageDialog(this, "Mã khuyến mãi đã tồn tại!");
			check = false;
		}
		
		return check;
	}
}
