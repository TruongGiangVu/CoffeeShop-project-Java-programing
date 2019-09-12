package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import bus.*;
import dto.*;
public class AddImportDetailDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txID;
	private JTextField txName;
	private JTextField txImpID;
	private JLabel lblMNh;
	private JLabel lblSLng;
	private JTextField txAmount;
	private JTextField txUnitPrice;
	private JLabel lblNewLabel;
	private boolean confirm = false;
	private ImportDetailBUS impdetBus = new ImportDetailBUS();
	private ProductBUS prodBus = new ProductBUS();
	private IngredientBUS ingBus = new IngredientBUS();
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
	public AddImportDetailDialog(String id, String impID, String prodID, double amount, double unitprice) {
		setTitle("Thêm nhập hàng");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "M\u00E3 SP", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 64, 414, 76);
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
				if(!prodID.equals("")) txName.setText(prodID.substring(0, 2).equals("IN")?ingBus.searchByID(prodID).getIng_name():prodBus.searchByID(prodID).getProd_name());
				txName.setEditable(false);
				txName.setColumns(10);
				txName.setBounds(90, 30, 263, 30);
				panel.add(txName);
			}
			{
				JButton btnSelect = new JButton("...");
				btnSelect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int check = JOptionPane.showConfirmDialog(null, "Thêm sản phẩm(YES) hay nguyên liệu(NO)?", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION);
						if ( check == 0) {
							RecordTable rec = new RecordTable(id,"prod_imp");
							rec.setLocationRelativeTo(null);
							rec.setModal(true);
							rec.setVisible(true);
							Product temp = rec.getProd();
							if(temp != null) {
								txID.setText(temp.getProd_id());
								txName.setText(prodBus.searchByID(temp.getProd_id()).getProd_name());
								txUnitPrice.setText(String.valueOf(temp.getUnit_price()));
							}
							
						}else if(check == 1){
							RecordTable rec = new RecordTable(id,"ing");
							rec.setLocationRelativeTo(null);
							rec.setModal(true);
							rec.setVisible(true);
							Ingredient temp = rec.getIng();
							if(temp != null) {
								txID.setText(temp.getIng_id());
								txName.setText(ingBus.searchByID(temp.getIng_id()).getIng_name());
								txUnitPrice.setText(String.valueOf(temp.getUnit_price()));
							}
						}
					}
				});
				btnSelect.setBounds(363, 30, 41, 30);
				panel.add(btnSelect);
			}
		}
		
		txImpID = new JTextField(impID);
		txImpID.setEditable(false);
		txImpID.setBounds(87, 23, 96, 30);
		contentPanel.add(txImpID);
		txImpID.setColumns(10);
		
		lblMNh = new JLabel("Mã NH:");
		lblMNh.setBounds(23, 24, 48, 26);
		contentPanel.add(lblMNh);
		
		lblSLng = new JLabel("Số lượng:");
		lblSLng.setBounds(23, 165, 61, 26);
		contentPanel.add(lblSLng);
		
		txAmount = new JTextField("0.0");
		if(!prodID.equals("")) txAmount.setText(String.valueOf(amount));
		txAmount.setBounds(87, 163, 96, 30);
		contentPanel.add(txAmount);
		txAmount.setColumns(10);
		
		txUnitPrice = new JTextField("0.0");
		if(!prodID.equals("")) txUnitPrice.setText(String.valueOf(unitprice));
		txUnitPrice.setColumns(10);
		txUnitPrice.setBounds(273, 163, 151, 30);
		contentPanel.add(txUnitPrice);
		
		lblNewLabel = new JLabel("Đơn giá:");
		lblNewLabel.setBounds(215, 165, 48, 26);
		contentPanel.add(lblNewLabel);
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
		}else if(impdetBus.searchByID(txImpID.getText(), txID.getText()) != null){
			check = false;
			JOptionPane.showMessageDialog(null, "Thiết lập này đã tồn tại!");
		}else {
			if(txID.getText().substring(0, 2).equals("IN")) {
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
			
			if(!txUnitPrice.getText().matches("^[1-9]\\d*\\.?\\d*$")){
				check = false;
				JOptionPane.showMessageDialog(null, "Đơn giá là một con số là một con số > 0!");
			}
		}
		return check;
	}
	public ImportDetail getImpDetail(){
		if(confirm) {
			return new ImportDetail(txImpID.getText(),txID.getText(),Double.parseDouble(txAmount.getText()),Double.parseDouble(txUnitPrice.getText()));
		}return null;
	}
}
