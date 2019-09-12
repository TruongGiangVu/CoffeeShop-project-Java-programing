package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import design.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.Ingredient;

import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AddIngDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Icon icon =new Icon();
	private JLabel lbname;
	private JTextField txname;
	private JLabel lbphone;
	private JTextField txprice;
	private boolean confirm = false;
	private JLabel lblnV;
	private JTextField txmeasure;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AddIngDialog dialog = new AddIngDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AddIngDialog() {
		this.setTitle("Thêm nguyên liệu");
		setBounds(100, 100, 400, 264);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lbname = new JLabel("Tên nguyên liệu:");
		lbname.setHorizontalAlignment(SwingConstants.CENTER);
		lbname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbname.setBounds(23, 25, 123, 25);
		contentPanel.add(lbname);

		txname = new JTextField();
		txname.setBounds(150, 25, 185, 25);
		contentPanel.add(txname);
		txname.setColumns(10);

		lbphone = new JLabel("Đơn giá:");
		lbphone.setHorizontalAlignment(SwingConstants.CENTER);
		lbphone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbphone.setBounds(23, 60, 123, 25);
		contentPanel.add(lbphone);

		txprice = new JTextField();
		txprice.setBounds(150, 60, 185, 25);
		contentPanel.add(txprice);
		txprice.setColumns(10);

		lblnV = new JLabel("Đơn vị:");
		lblnV.setHorizontalAlignment(SwingConstants.CENTER);
		lblnV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblnV.setBounds(23, 96, 123, 25);
		contentPanel.add(lblnV);

		txmeasure = new JTextField();
		txmeasure.setColumns(10);
		txmeasure.setBounds(150, 96, 185, 25);
		contentPanel.add(txmeasure);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 191, 384, 35);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("Thêm");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				okButton.setIcon(icon.imageIconSize("icons/ok.png", 20, 20));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (checkValue()) {
							Ingredient ing = getData();
							int a = JOptionPane.showConfirmDialog(null,
									"Bạn có muốn thêm nhà cung cấp :" + ing.getIng_name(), "Thêm",
									JOptionPane.YES_NO_OPTION);
							if (a == 0)
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
				cancelButton.setIcon(icon.imageIconSize("icons/cancel.png", 20, 20));
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
	}

	public boolean checkValue() {
		boolean check = true;
		String error = "";
		String name = txname.getText();
		name = this.covertStringToURL(name);
		String price = txprice.getText();
		
		if (!name.matches("^[A-Z]([a-z]+\\s*)+$")) {
			error += "Tên nguyên liệu có thể viết sai định dạng.\n";
			check = false;
		}
		if (!price.matches("^[1-9]\\d*\\.?\\d*$")) {
			error += "Đơn giá không chứa chữ.\n";
			check = false;
		}
		
		if (check == false)
			JOptionPane.showMessageDialog(this, error);
		return check;
	}

	Ingredient getData() {
		Ingredient ing = new Ingredient("", txname.getText(), Double.parseDouble(txprice.getText()),
				0, txmeasure.getText(), 0);
		return ing;
	}

	Ingredient geting() {
		return geting(this.confirm);
	}

	Ingredient geting(boolean confirm) {
		if (confirm) {
			return getData();
		} else
			return null;
	}

	public String covertStringToURL(String str) {
		try {
			String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			return pattern.matcher(temp).replaceAll("").replaceAll("đ", "d").replaceAll("Đ", "D");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
