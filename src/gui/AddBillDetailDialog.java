package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import bus.ImportDetailBUS;
import bus.IngredientBUS;
import bus.ProductBUS;
import dto.ImportDetail;
import dto.Ingredient;
import dto.Product;
import dto.Sale;
import bus.*;
import dto.*;

public class AddBillDetailDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txID;
	private JTextField txName;
	private JTextField txBillID;
	private JLabel lblMNh;
	private JLabel lblSLng;
	private JTextField txAmount;
	private JTextField txUnitPrice;
	private JLabel lblNewLabel;
	private boolean confirm = false;
	private BillDetailBUS billdetailBus = new BillDetailBUS();
	private ProductBUS prodBus = new ProductBUS();
	private IngredientBUS ingBus = new IngredientBUS();
	private SaleBUS saleBus = new SaleBUS();
	private JLabel label_1;
	private JTextField txSaleID;
	private JLabel lblGiKm;
	private JTextField txSaleUnitPrice;
	private JButton btnSelectSale;
	private JLabel lbMax;
	private JLabel lbMaxAmount;
	private JButton btnClear;
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			AddImportDetailDialog dialog = new AddImportDetailDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddBillDetailDialog(String id, String billID, String prodID, double amount, double unitprice, String saleID) {
		setTitle("Thêm hóa đơn");
		setBounds(100, 100, 450, 346);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "M\u00E3 SP", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 56, 414, 76);
			contentPanel.add(panel);
			{
				txID = new JTextField(prodID);
				txID.setEditable(false);
				txID.setColumns(10);
				txID.setBounds(10, 30, 70, 30);
				panel.add(txID);
			}
			{
				txName = new JTextField();
				if(!prodID.equals("")) txName.setText(prodBus.searchByID(prodID).getProd_name());
				txName.setEditable(false);
				txName.setColumns(10);
				txName.setBounds(90, 30, 263, 30);
				panel.add(txName);
			}
			{
				JButton btnSelect = new JButton("...");
				btnSelect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						RecordTable rec = new RecordTable(id,"prod");
						rec.setLocationRelativeTo(null);
						rec.setModal(true);
						rec.setVisible(true);
						Product temp = rec.getProd();
						if(temp != null) {
							txID.setText(temp.getProd_id());
							txName.setText(prodBus.searchByID(temp.getProd_id()).getProd_name());
							txUnitPrice.setText(String.valueOf(temp.getUnit_price()));
							lbMaxAmount.setText(String.valueOf(temp.getAmount()));
						}
					}
				});
				btnSelect.setBounds(363, 30, 41, 30);
				panel.add(btnSelect);
			}
		}
		
		txBillID = new JTextField(billID);
		txBillID.setEditable(false);
		txBillID.setBounds(87, 23, 96, 30);
		contentPanel.add(txBillID);
		txBillID.setColumns(10);
		
		lblMNh = new JLabel("Mã HĐ:");
		lblMNh.setBounds(23, 24, 48, 26);
		contentPanel.add(lblMNh);
		
		lblSLng = new JLabel("Số lượng:");
		lblSLng.setBounds(23, 165, 61, 26);
		contentPanel.add(lblSLng);
		
		txAmount = new JTextField();
		if(!prodID.equals("")) txAmount.setText(String.valueOf(amount));
		txAmount.setBounds(87, 163, 109, 30);
		contentPanel.add(txAmount);
		txAmount.setColumns(10);
		
		txUnitPrice = new JTextField("0.0");
		if(!prodID.equals("")) txUnitPrice.setText(String.valueOf(unitprice));
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
				if(txUnitPrice.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
					double u = Double.parseDouble(txUnitPrice.getText());
					Sale temp = saleBus.searchByID(txSaleID.getText());
					if(temp != null) {
						if(temp.getMeasure().equals("%")) {
							u = u * temp.getNumber() / 100;
						}else u = u - temp.getNumber();
						if(u < 0 ) u = 0;
					}
					txSaleUnitPrice.setText(String.valueOf(u));
				}else {
					txSaleUnitPrice.setText("Đơn giá không đúng");
				}
			}
		});
		txUnitPrice.setColumns(10);
		txUnitPrice.setBounds(273, 163, 151, 30);
		contentPanel.add(txUnitPrice);
		
		lblNewLabel = new JLabel("Đơn giá:");
		lblNewLabel.setBounds(215, 165, 48, 26);
		contentPanel.add(lblNewLabel);
		
		label_1 = new JLabel("Mã HĐ:");
		label_1.setBounds(23, 203, 48, 26);
		contentPanel.add(label_1);
		
		txSaleID = new JTextField(saleID);
		txSaleID.setEditable(false);
		txSaleID.setColumns(10);
		txSaleID.setBounds(87, 202, 109, 30);
		contentPanel.add(txSaleID);
		
		lblGiKm = new JLabel("Giá KM:");
		lblGiKm.setBounds(215, 206, 48, 26);
		contentPanel.add(lblGiKm);
		
		txSaleUnitPrice = new JTextField(txUnitPrice.getText());
		txSaleUnitPrice.setEditable(false);
		txSaleUnitPrice.setColumns(10);
		txSaleUnitPrice.setBounds(273, 204, 151, 30);
		contentPanel.add(txSaleUnitPrice);
		
		btnSelectSale = new JButton("...");
		btnSelectSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RecordTable rec = new RecordTable(id,"prod_sale");
				rec.getProdData(txID.getText());
				
				rec.setLocationRelativeTo(null);
				rec.setModal(true);
				rec.setVisible(true);
				Sale temp = rec.getSale();
				if(temp != null) {
					txSaleID.setText(temp.getSale_id());
					if(txUnitPrice.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
						double u = Double.parseDouble(txUnitPrice.getText());
						//Sale temp = saleBus.searchByID(txSaleID.getText());
						//if(temp != null) {
							if(temp.getMeasure().equals("%")) {
								u = u * temp.getNumber() / 100;
							}else u = u - temp.getNumber();
						//}
						if(u < 0 ) u = 0;
						txSaleUnitPrice.setText(String.valueOf(u));
					}else {
						txSaleUnitPrice.setText("Đơn giá không đúng");
					}
				}
			}
		});
		btnSelectSale.setBounds(155, 236, 41, 30);
		contentPanel.add(btnSelectSale);
		
		lbMax = new JLabel("Hiện có:");
		lbMax.setBounds(87, 130, 48, 26);
		contentPanel.add(lbMax);
		
		lbMaxAmount = new JLabel("0");
		if(!prodID.equals("")) lbMaxAmount.setText(String.valueOf(prodBus.searchByID(txID.getText()).getAmount()));
		lbMaxAmount.setBounds(142, 130, 48, 26);
		contentPanel.add(lbMaxAmount);
		
		btnClear = new JButton("XÓA");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txSaleID.setText("");
				if(txUnitPrice.getText().matches("^[1-9]\\d*\\.?\\d*$")) {
					txSaleUnitPrice.setText(txUnitPrice.getText());
				}else {
					txSaleUnitPrice.setText("Đơn giá không đúng");
				}
			}
		});
		btnClear.setBounds(87, 236, 66, 30);
		contentPanel.add(btnClear);
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
	}
	public boolean checkValid() {
		boolean check = true;
		if(txID.getText().equals("")) {
			check = false;
			JOptionPane.showMessageDialog(null, "Không được để trống mã hàng!");
		}else if(billdetailBus.searchByID(txBillID.getText(), txID.getText()) != null){
			check = false;
			JOptionPane.showMessageDialog(null, "Thiết lập này đã tồn tại!");
		}else {
			if(!txAmount.getText().matches("^[1-9]\\d*$")) {
				JOptionPane.showMessageDialog(null, "Số lượng là một con số nguyên > 0!");
				check = false;
			}else if(Double.compare(Double.parseDouble(txAmount.getText()), Double.parseDouble(lbMaxAmount.getText())) > 0) {
				check = false;
				JOptionPane.showMessageDialog(null, "Số lượng bán không được vượt quá số lượng hiện có!");
			}
			if(!txUnitPrice.getText().matches("^[1-9]\\d*\\.?\\d*$")){
				check = false;
				JOptionPane.showMessageDialog(null, "Đơn giá là một con số là một con số > 0!");
			}
		}
		return check;
	}
	public BillDetail getBillDetail(){
		if(confirm) {
			return new BillDetail(txBillID.getText(),txID.getText(),Integer.parseInt(txAmount.getText()),Double.parseDouble(txUnitPrice.getText()),
					Double.parseDouble(txSaleUnitPrice.getText()), txSaleID.getText());
		}return null;
	}
}
