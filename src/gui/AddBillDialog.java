package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import bus.DateExe;
import bus.ImportBUS;
import bus.ImportDetailBUS;
import bus.IngredientBUS;
import bus.ProductBUS;
import dto.*;
import bus.*;

public class AddBillDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txBillID;
	private JTextField txEmpID;
	private JTextField txCusID;
	private JTextField txTotal;
	private JScrollPane scrollPane;
	private JTable tableDetail;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JPanel panel_1;
	private JTextField txSearch;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JPanel panel_2;
	private JComboBox comboType;
	private JComboBox comboOrder;
	private JButton btnApply;
	private ButtonGroup bg = new ButtonGroup();
	private boolean confirm = false;
	private ProductBUS prodBus = new ProductBUS();
	private SaleBUS saleBus = new SaleBUS();
	private BillDetailBUS billdetailBus = new BillDetailBUS();
	private BillBUS billBus = new BillBUS();
	private BillSaleBUS billsaleBus = new BillSaleBUS();
	private ArrayList<BillDetail> billdetailArr = new ArrayList<BillDetail>();
	private JLabel lblMKm;
	private JTextField txSaleID;
	private JButton btnSelectSale;
	private JLabel lblKhuynMi;
	private JTextField txSaleTotal;
	private JTextField txDate;
	private JButton btnClear;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			AddImportDialog dialog = new AddImportDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddBillDialog(String empID, String billID) {
		setTitle("Thêm chi tiết hóa đơn");
		setBounds(100, 100, 668, 591);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 11, 632, 200);
			contentPanel.add(panel);
			{
				JLabel lblMH = new JLabel("Mã HĐ:");
				lblMH.setBounds(29, 29, 58, 26);
				panel.add(lblMH);
			}
			{
				JLabel label = new JLabel("Mã NV:");
				label.setBounds(29, 70, 58, 26);
				panel.add(label);
			}
			{
				JLabel lblMKh = new JLabel("Mã KH:");
				lblMKh.setBounds(29, 111, 58, 26);
				panel.add(lblMKh);
			}
			{
				JLabel label = new JLabel("Ngày lập:");
				label.setBounds(301, 29, 58, 26);
				panel.add(label);
			}
			{
				txBillID = new JTextField();
				int temp = billBus.readAll().size();
				txBillID.setText(String.format("%05d", temp));
				txBillID.setEditable(false);
				txBillID.setColumns(10);
				txBillID.setBounds(86, 27, 107, 30);
				panel.add(txBillID);
			}
			{
				txEmpID = new JTextField(empID);
				txEmpID.setEditable(false);
				txEmpID.setColumns(10);
				txEmpID.setBounds(86, 66, 107, 30);
				panel.add(txEmpID);
			}
			{
				txCusID = new JTextField();
				txCusID.setEditable(false);
				txCusID.setColumns(10);
				txCusID.setBounds(86, 107, 107, 30);
				panel.add(txCusID);
			}
			{
				JButton btnSelectCus = new JButton("...");
				btnSelectCus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						RecordTable rec = new RecordTable(empID,"cus");
						rec.setLocationRelativeTo(null);
						rec.setModal(true);
						rec.setVisible(true);
						Customer temp = rec.getCus();
						if(temp != null) {
							txCusID.setText(temp.getCus_id());
						}
					}
				});
				btnSelectCus.setBounds(202, 109, 41, 30);
				panel.add(btnSelectCus);
			}
			{
				JLabel label = new JLabel("Thành tiền:");
				label.setBounds(294, 70, 65, 26);
				panel.add(label);
			}
			{
				txTotal = new JTextField("0.0");
				txTotal.getDocument().addDocumentListener(new DocumentListener() {
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
						if(txTotal.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
							double u = Double.parseDouble(txTotal.getText());
							Sale temp = saleBus.searchByID(txSaleID.getText());
							if(temp != null) {
								if(temp.getMeasure().equals("%")) {
									u = u * temp.getNumber() / 100;
								}else u = u - temp.getNumber();
								if(u < 0 ) u = 0;
							}
							txSaleTotal.setText(String.valueOf(u));
						}else {
							txSaleTotal.setText("Đơn giá không đúng");
						}
					}
				});
				txTotal.setEditable(false);
				txTotal.setColumns(10);
				txTotal.setBounds(369, 66, 114, 30);
				panel.add(txTotal);
			}
			{
				lblMKm = new JLabel("Mã KM:");
				lblMKm.setBounds(29, 152, 58, 26);
				panel.add(lblMKm);
			}
			{
				txSaleID = new JTextField();
				txSaleID.setEditable(false);
				txSaleID.setColumns(10);
				txSaleID.setBounds(86, 148, 107, 30);
				panel.add(txSaleID);
			}
			{
				btnSelectSale = new JButton("...");
				btnSelectSale.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						RecordTable rec = new RecordTable(empID,"bill_sale");
						rec.getBillData(Double.parseDouble(txTotal.getText()), countProdAmount());
						rec.setLocationRelativeTo(null);
						rec.setModal(true);
						rec.setVisible(true);
						Sale sale = rec.getSale();
						if(sale != null) {
							txSaleID.setText(sale.getSale_id());
							if(txTotal.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
								double u = Double.parseDouble(txTotal.getText());
								Sale temp = saleBus.searchByID(txSaleID.getText());
								if(temp != null) {
									if(temp.getMeasure().equals("%")) {
										u = u * temp.getNumber() / 100;
									}else u = u - temp.getNumber();
									if(u < 0 ) u = 0;
								}
								txSaleTotal.setText(String.valueOf(u));
							}else {
								txSaleTotal.setText("Đơn giá không đúng");
							}
						}
					}
				});
				btnSelectSale.setBounds(202, 150, 41, 30);
				panel.add(btnSelectSale);
			}
			{
				lblKhuynMi = new JLabel("Khuyến mãi:");
				lblKhuynMi.setBounds(283, 111, 76, 26);
				panel.add(lblKhuynMi);
			}
			{
				txSaleTotal = new JTextField(txTotal.getText());
				txSaleTotal.setEditable(false);
				txSaleTotal.setColumns(10);
				txSaleTotal.setBounds(369, 107, 114, 30);
				panel.add(txSaleTotal);
			}
			
			txDate = new JTextField(DateExe.convertDateToString(new Date()));
			txDate.setEditable(false);
			txDate.setBounds(369, 27, 114, 30);
			panel.add(txDate);
			txDate.setColumns(10);
			{
				btnClear = new JButton("XÓA KM");
				btnClear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						txSaleID.setText("");
						if(txTotal.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
							txSaleTotal.setText(txTotal.getText());
						}else {
							txSaleTotal.setText("Đơn giá không đúng");
						}
					}
				});
				btnClear.setBounds(253, 150, 84, 30);
				panel.add(btnClear);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chi ti\u1EBFt h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 297, 632, 170);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			
			scrollPane = new JScrollPane();
			panel.add(scrollPane);
			
			tableDetail = new JTable();
			tableDetail.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
				}
			});
			tableDetail.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
				}
			});
			scrollPane.setViewportView(tableDetail);
		}
		
		btnAdd = new JButton("THÊM");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddBillDetailDialog dial = new AddBillDetailDialog(txEmpID.getText(),txBillID.getText(),"",0,0,txSaleID.getText());
				dial.setLocationRelativeTo(null);
				dial.setModal(true);
				dial.setVisible(true);
				BillDetail temp = dial.getBillDetail();
				if(temp != null) {
					boolean check = true;
					for(BillDetail p : billdetailArr) {
						if(p.getProd_id().equals(temp.getProd_id())) {
							JOptionPane.showMessageDialog(null, "Thiết lập này đã tồn tại");
							check = false;
							break;
						}
					}
					if(check) {
						billdetailArr.add(temp);
						searchAndSortDetail();
						getTotal();
						JOptionPane.showMessageDialog(null, "Thêm thành công");
					}
				}
			}
		});
		btnAdd.setBounds(22, 478, 65, 30);
		contentPanel.add(btnAdd);
		
		btnUpdate = new JButton("SỬA");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddBillDetailDialog dial = new AddBillDetailDialog(txEmpID.getText(),txBillID.getText(),String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 0)),
						Double.parseDouble(String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 2))),
						Double.parseDouble(String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 3))),
						txSaleID.getText());
				dial.setLocationRelativeTo(null);
				dial.setModal(true);
				dial.setVisible(true);
				BillDetail temp = dial.getBillDetail();
				if(temp != null) {
					billdetailArr.remove(tableDetail.getSelectedRow());
					billdetailArr.add(temp);
					searchAndSortDetail();
					getTotal();
					JOptionPane.showMessageDialog(null, "Sửa thành công");
				}
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(107, 478, 65, 30);
		contentPanel.add(btnUpdate);
		
		btnDelete = new JButton("XÓA");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Xóa chi tiết này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
					billdetailArr.remove(tableDetail.getSelectedRow());
					searchAndSortDetail();
					getTotal();
					JOptionPane.showMessageDialog(null, "Xóa thành công");
				}
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBounds(190, 478, 65, 30);
		contentPanel.add(btnDelete);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 222, 226, 64);
		contentPanel.add(panel_1);
		
		txSearch = new JTextField();
		txSearch.setColumns(10);
		txSearch.setBounds(10, 21, 132, 30);
		panel_1.add(txSearch);
		
		radioButton = new JRadioButton("Mã SP");
		radioButton.setSelected(true);
		radioButton.setBounds(148, 7, 72, 23);
		panel_1.add(radioButton);
		
		radioButton_1 = new JRadioButton("Tên SP");
		radioButton_1.setBounds(148, 34, 72, 23);
		panel_1.add(radioButton_1);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "S\u1EAFp x\u1EBFp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(246, 222, 287, 64);
		contentPanel.add(panel_2);
		
		String[] detailtype = {"Mã SP","Tên SP","Số lượng","Đơn giá","Giá KM"};
		comboType = new JComboBox(detailtype);
		comboType.setBounds(10, 23, 138, 30);
		panel_2.add(comboType);
		
		comboOrder = new JComboBox(new String[] {"Tăng dần","Giảm dần"});
		comboOrder.setBounds(158, 23, 119, 30);
		panel_2.add(comboOrder);
		
		btnApply = new JButton("ÁP DỤNG");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchAndSortDetail();
				
			}
		});
		btnApply.setBounds(543, 234, 89, 41);
		contentPanel.add(btnApply);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(txCusID.getText().equals("")) {
							confirm = false;
							JOptionPane.showMessageDialog(null, "Không được để trống mã khách hàng!");
						}else {		
							confirm = true;
							dispose();
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Hủy");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						confirm = false; dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		bg.add(radioButton); bg.add(radioButton_1);
		
		this.loadDetailData(billdetailArr);
	}
		public int countProdAmount() {
			int count = 0;
			for(int i=0;i<tableDetail.getRowCount();i++) {
				count += Integer.parseInt(String.valueOf(tableDetail.getValueAt(i, 2)));
			}
			return count;
		}
		public void searchAndSortDetail() {
			//this.getDetailData(txImpID.getText());
			billdetailArr = billdetailBus.sort(billdetailBus.search(billdetailArr, txSearch.getText(), this.getSelectedButtonText(bg)),
					String.valueOf(this.comboType.getSelectedItem()), String.valueOf(this.comboOrder.getSelectedItem()));
			this.loadDetailData(billdetailArr);
			btnUpdate.setEnabled(false);
			btnDelete.setEnabled(false);
			if(billdetailArr.size() == 0) JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả nào!");
		}
		public void loadDetailData(ArrayList<BillDetail> arr) {
			String[] header = new String[] {"Mã SP","Tên SP","Số lượng","Đơn giá","Đ.vị","Mã KM","Giá KM"};
			DefaultTableModel md = new DefaultTableModel(header, 0);
			for (BillDetail p : arr) {
				Object[] row;
					row = new Object[]{ p.getProd_id(), prodBus.searchByID(p.getProd_id()).getProd_name(),
							p.getAmount(), p.getUnit_price(), prodBus.searchByID(p.getProd_id()).getMeasure(),
							p.getSale_id(),p.getSale_unit_price()};
				md.addRow(row);
			}
			tableDetail.setModel(md);
			this.setColSize();
			
		}
		public Bill getBill() {
			if(confirm) {
				return new Bill(txBillID.getText(),txEmpID.getText(),txCusID.getText(),txDate.getText(),txSaleID.getText(),
						Double.parseDouble(txSaleTotal.getText()),0);
			}return null;
		}
		public void updateBillDetail(){
			for(BillDetail p : billdetailArr) {
				billdetailBus.add(p);
			}
			if( !txSaleID.getText().equals("")) {
				BillSale billsale = new BillSale(txSaleID.getText(),txBillID.getText(),0);
				billsaleBus.add(billsale);
			}
		}
		public boolean checkValid() {
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
			return check;
		}
	public void setColSize() {
		TableColumnModel col = tableDetail.getColumnModel();
		this.setFixColSize(col, 0, 40);
		this.setFixColSize(col, 1, 150);
		this.setFixColSize(col, 2, 80);
		this.setFixColSize(col, 3, 80);
		this.setFixColSize(col, 4, 80);
		this.setFixColSize(col, 5, 80);
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
	public void getTotal() {
		txTotal.setText(String.valueOf(billdetailBus.getTotalPrice(billdetailArr)));
	}
}
