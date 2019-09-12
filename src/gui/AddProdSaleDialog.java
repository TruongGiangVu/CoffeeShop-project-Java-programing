package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.ProdSale;
import dto.Product;
import dto.Sale;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import bus.ProdSaleBUS;
import bus.ProductBUS;
import bus.SaleBUS;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddProdSaleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private SaleBUS saleBus = new SaleBUS();
	private ProductBUS prodBus = new ProductBUS();
	private ProdSaleBUS prodsaleBus = new ProdSaleBUS();
	private ArrayList<Sale> saleArr = new ArrayList<Sale>();
	private ArrayList<Product> prodArr = new ArrayList<Product>();
	private ArrayList<ProdSale> prodsaleArr = new ArrayList<ProdSale>();
	private JPanel panel;
	private JPanel panel_1;
	private JTextField txSaleID;
	private JTextField txSaleName;
	private JButton btnSale;
	private JTextField txProdID;
	private JTextField txProdName;
	private JButton btnProd;
	private boolean confirm = false;
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			AddProdSaleDialog dialog = new AddProdSaleDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddProdSaleDialog(String id, String saleID, String prodID) {
		saleArr = saleBus.readAll();
		prodArr = prodBus.readAll();
		
		setTitle("Thiết lập khuyến mãi");
		setBounds(100, 100, 450, 286);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 25, 380, 76);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		txSaleID = new JTextField();
		txSaleID.setEditable(false);
		txSaleID.setBounds(10, 30, 70, 30);
		panel.add(txSaleID);
		txSaleID.setColumns(10);
		
		txSaleName = new JTextField();
		txSaleName.setEditable(false);
		txSaleName.setBounds(90, 30, 224, 30);
		panel.add(txSaleName);
		txSaleName.setColumns(10);
		
		btnSale = new JButton("...");
		btnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RecordTable dialog = new RecordTable(id, "sale");
				dialog.setLocationRelativeTo(null);
				dialog.setModal(true);
				dialog.setVisible(true);
				Sale temp = dialog.getSale();
				if(temp != null) {
					txSaleID.setText(temp.getSale_id()); txSaleName.setText(temp.getSale_name());
				}
				
			}
		});
		btnSale.setBounds(324, 30, 41, 30);
		panel.add(btnSale);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(25, 112, 380, 76);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		txProdID = new JTextField();
		txProdID.setEditable(false);
		txProdID.setBounds(10, 29, 70, 30);
		panel_1.add(txProdID);
		txProdID.setColumns(10);
		
		txProdName = new JTextField();
		txProdName.setEditable(false);
		txProdName.setColumns(10);
		txProdName.setBounds(90, 29, 224, 30);
		panel_1.add(txProdName);
		
		btnProd = new JButton("...");
		btnProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RecordTable dialog = new RecordTable(id,"prod");
				dialog.setLocationRelativeTo(null);
				dialog.setModal(true);
				dialog.setVisible(true);
				Product temp = dialog.getProd();
				if(temp != null) {
					txProdID.setText(temp.getProd_id()); txProdName.setText(temp.getProd_name());
				}
			}
		});
		if(saleID != null && prodID != null) {
			txSaleID.setText(saleID);
			txProdID.setText(prodID);
			txSaleName.setText(saleBus.searchByID(saleArr, saleID).getSale_name());
			txProdName.setText(prodBus.searchByID(prodArr, prodID).getProd_name());
		}
		btnProd.setBounds(324, 29, 41, 30);
		panel_1.add(btnProd);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("THỰC THI");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(txSaleID.getText().equals("") || txProdID.getText().equals("")) {
							confirm = false;
							JOptionPane.showMessageDialog(null, "Không được để trống!");
						}else if(prodsaleBus.searchByID(txSaleID.getText(), txProdID.getText()) != null){
							confirm = false;
							JOptionPane.showMessageDialog(null, "Thiết lập này đã tồn tại!");
						}else {
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
						confirm = false; dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public ProdSale getProdSale() {
		if(confirm) {
			return new ProdSale(txSaleID.getText(), txProdID.getText(), 0);
		}else {
			return null;
		}
	}
}
