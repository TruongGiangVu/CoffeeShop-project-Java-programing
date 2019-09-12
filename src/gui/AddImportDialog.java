package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.*;
import bus.*;
import dto.*;
import java.util.*;
public class AddImportDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txImpID;
	private JTextField txEmpID;
	private JTextField txSupID;
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
	private IngredientBUS ingBus = new IngredientBUS();
	private ImportDetailBUS impdetailBus = new ImportDetailBUS();
	private ImportBUS impBus = new ImportBUS();
	private ArrayList<ImportDetail> impdetailArr = new ArrayList<ImportDetail>();
	private JTextField txDate;

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
	public AddImportDialog(String empID, String impID) {
		setTitle("Thêm chi tiết nhập hàng");
		setBounds(100, 100, 668, 535);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin nh\u1EADp h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 11, 632, 144);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("Mã NH:");
				label.setBounds(29, 29, 58, 26);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Mã NV:");
				label.setBounds(29, 66, 58, 26);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Mã NCC:");
				label.setBounds(29, 103, 58, 26);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Ngày lập:");
				label.setBounds(301, 29, 58, 26);
				panel.add(label);
			}
			{
				txImpID = new JTextField();
				int temp = impBus.readAll().size();
				txImpID.setText(String.format("%05d", temp));
				txImpID.setEditable(false);
				txImpID.setColumns(10);
				txImpID.setBounds(86, 27, 107, 30);
				panel.add(txImpID);
			}
			{
				txEmpID = new JTextField(empID);
				txEmpID.setEditable(false);
				txEmpID.setColumns(10);
				txEmpID.setBounds(86, 62, 107, 30);
				panel.add(txEmpID);
			}
			{
				txSupID = new JTextField();
				txSupID.setEditable(false);
				txSupID.setColumns(10);
				txSupID.setBounds(86, 99, 107, 30);
				panel.add(txSupID);
			}
			{
				JButton btnSelectSup = new JButton("...");
				btnSelectSup.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						RecordTable rec = new RecordTable(empID,"sup");
						rec.setLocationRelativeTo(null);
						rec.setModal(true);
						rec.setVisible(true);
						Supplier temp = rec.getSup();
						if(temp != null) {
							txSupID.setText(temp.getSup_id());
						}
					}
				});
				btnSelectSup.setBounds(202, 101, 41, 30);
				panel.add(btnSelectSup);
			}
			{
				JLabel label = new JLabel("Thành tiền:");
				label.setBounds(294, 66, 65, 26);
				panel.add(label);
			}
			{
				txTotal = new JTextField("0.0");
				txTotal.setEditable(false);
				txTotal.setColumns(10);
				txTotal.setBounds(369, 64, 107, 30);
				panel.add(txTotal);
			}
			{
				txDate = new JTextField(DateExe.convertDateToString(new Date()));
				txDate.setEditable(false);
				txDate.setBounds(369, 27, 107, 30);
				panel.add(txDate);
				txDate.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Chi ti\u1EBFt nh\u1EADp h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 241, 632, 170);
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
				AddImportDetailDialog dial = new AddImportDetailDialog(txEmpID.getText(),txImpID.getText(),"",0,0);
				dial.setLocationRelativeTo(null);
				dial.setModal(true);
				dial.setVisible(true);
				ImportDetail temp = dial.getImpDetail();
				if(temp != null) {
					boolean check = true;
					for(ImportDetail p : impdetailArr) {
						if(p.getProd_id().equals(temp.getProd_id())) {
							JOptionPane.showMessageDialog(null, "Thiết lập này đã tồn tại");
							check = false;
							break;
						}
					}
					if(check) {
						impdetailArr.add(temp);
						searchAndSortDetail();
						getTotal();
						JOptionPane.showMessageDialog(null, "Thêm thành công");
					}
				}
			}
		});
		btnAdd.setBounds(22, 422, 65, 30);
		contentPanel.add(btnAdd);
		
		btnUpdate = new JButton("SỬA");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddImportDetailDialog dial = new AddImportDetailDialog(txEmpID.getText(),txImpID.getText(),String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 0)),
						Double.parseDouble(String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 2))),
						Double.parseDouble(String.valueOf(tableDetail.getValueAt(tableDetail.getSelectedRow(), 3))));
				dial.setLocationRelativeTo(null);
				dial.setModal(true);
				dial.setVisible(true);
				ImportDetail temp = dial.getImpDetail();
				if(temp != null) {
					impdetailArr.remove(tableDetail.getSelectedRow());
					impdetailArr.add(temp);
					searchAndSortDetail();
					getTotal();
					JOptionPane.showMessageDialog(null, "Sửa thành công");
				}
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(107, 422, 65, 30);
		contentPanel.add(btnUpdate);
		
		btnDelete = new JButton("XÓA");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Xóa chi tiết này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
					impdetailArr.remove(tableDetail.getSelectedRow());
					searchAndSortDetail();
					getTotal();
					JOptionPane.showMessageDialog(null, "Xóa thành công");
				}
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBounds(190, 422, 65, 30);
		contentPanel.add(btnDelete);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 166, 226, 64);
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
		panel_2.setBounds(238, 166, 287, 64);
		contentPanel.add(panel_2);
		
		String[] detailtype = {"Mã SP","Tên SP","Số lượng","Đơn giá"};
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
		btnApply.setBounds(535, 176, 89, 41);
		contentPanel.add(btnApply);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Áp dụng");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(checkValid()) {		
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
		
		this.loadDetailData(impdetailArr);
	}
		public void searchAndSortDetail() {
			//this.getDetailData(txImpID.getText());
			impdetailArr = impdetailBus.sort(impdetailBus.search(impdetailArr, txSearch.getText(), this.getSelectedButtonText(bg)),
					String.valueOf(this.comboType.getSelectedItem()), String.valueOf(this.comboOrder.getSelectedItem()));
			this.loadDetailData(impdetailArr);
			btnUpdate.setEnabled(false);
			btnDelete.setEnabled(false);
			if(impdetailArr.size() == 0) JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả nào!");
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
			this.setColSize();
		}
		public Import getImp() {
			if(confirm) {
				return new Import(txImpID.getText(),txEmpID.getText(),txSupID.getText(),txDate.getText(),0);
			}return null;
		}
		public void updateImpDetail(){
			for(ImportDetail p : impdetailArr) {
				impdetailBus.add(p);
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
			if(txSupID.getText().equals("")) {
				check = false;
				JOptionPane.showMessageDialog(null, "Không được để trống mã nhà cung cấp!");
			}
			return check;
		}
	public void setColSize() {
		TableColumnModel col = tableDetail.getColumnModel();
		this.setFixColSize(col, 0, 40);
		this.setFixColSize(col, 1, 180);
		this.setFixColSize(col, 2, 100);
		this.setFixColSize(col, 3, 100);
		
	
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
		txTotal.setText(String.valueOf(impdetailBus.getTotalPrice(impdetailArr)));
	}
}
