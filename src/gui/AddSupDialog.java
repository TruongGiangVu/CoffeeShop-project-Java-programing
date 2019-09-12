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

import dto.Customer;
import dto.Supplier;

import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AddSupDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Icon icon = new Icon();
	private JLabel lbname;
	private JTextField txname;
	private JLabel lbphone;
	private JTextField txphone;
	private boolean confirm = false;
	private JLabel lblNewLabel;
	private JTextField txaddress;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AddSupDialog dialog = new AddSupDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AddSupDialog() {
		setTitle("Thêm nhà cung cấp");
		setBounds(100, 100, 395, 232);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lbname = new JLabel("Tên nhà cung cấp:");
		lbname.setHorizontalAlignment(SwingConstants.CENTER);
		lbname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbname.setBounds(20, 25, 123, 25);
		contentPanel.add(lbname);

		txname = new JTextField();
		txname.setBounds(150, 25, 185, 25);
		contentPanel.add(txname);
		txname.setColumns(10);

		lbphone = new JLabel("Số điện thoại:");
		lbphone.setHorizontalAlignment(SwingConstants.CENTER);
		lbphone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbphone.setBounds(20, 60, 123, 25);
		contentPanel.add(lbphone);

		txphone = new JTextField();
		txphone.setBounds(150, 60, 185, 25);
		contentPanel.add(txphone);
		txphone.setColumns(10);

		lblNewLabel = new JLabel("Địa chỉ:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 95, 123, 25);
		contentPanel.add(lblNewLabel);

		txaddress = new JTextField();
		txaddress.setColumns(10);
		txaddress.setBounds(150, 95, 185, 25);
		contentPanel.add(txaddress);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Thêm");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				okButton.setIcon(icon.imageIconSize("icons/ok.png", 20, 20));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (checkValue()) {
							Supplier sup = getData();
							int a = JOptionPane.showConfirmDialog(null,
									"Bạn có muốn thêm nhà cung cấp :" + sup.getSup_name() + ".", "Thêm",
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
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cancelButton.setIcon(icon.imageIconSize("icons/cancel.png", 20, 20));
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
		String phone = txphone.getText();
		if (!name.matches("^([A-Z][a-z]*\\s*)+$")) {
			error += "Họ tên có thể viết sai định dạng.\n";
			check = false;
		}
		if (!phone.matches("^0[1-9]\\d{8,9}$")) {
			error += "Số điện thoại không được có chữ và ít nhất 10 số.\n";
			check = false;
		}
		if (check == false)
			JOptionPane.showMessageDialog(this, error);
		return check;
	}

	Supplier getData() {
		Supplier sup = new Supplier("", txname.getText(), txphone.getText(), txaddress.getText(), 0);
		return sup;
	}

	Supplier getSup() {
		return getSup(this.confirm);
	}

	Supplier getSup(boolean confirm) {
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
