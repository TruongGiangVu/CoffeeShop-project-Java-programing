package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class ReadExcelDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JFileChooser file = new JFileChooser();
	private int confirm = 3;
	private JLabel lblNewLabel;
	private JTextField txlink;
	private JRadioButton rbkeep;
	private JRadioButton rbwrite;
	private JRadioButton rbadd;
	private String linkpath = "";
	private JButton btnNewButton;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ReadExcelDialog dialog = new ReadExcelDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ReadExcelDialog(String type) {
		setTitle("Sự cố trùng lặp mã và cách giải quyết");
		setBounds(100, 100, 351, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbinfo = new JLabel("Xử lý trùng lặp mã");

			lbinfo.setHorizontalAlignment(SwingConstants.CENTER);
			lbinfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbinfo.setBounds(63, 58, 195, 31);
			contentPanel.add(lbinfo);
		}
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 134, 334, 42);
		contentPanel.add(buttonPane);
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
		fl_buttonPane.setAlignOnBaseline(true);
		buttonPane.setLayout(fl_buttonPane);
		{
			JButton okButton = new JButton("Áp dụng");
			okButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (rbkeep.isSelected())
						dispose();
					else if (txlink.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Bạn chưa nhập đường dẫn!");
					} else {
						confirm = selectConfirm();
						linkpath = txlink.getText().trim();
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
			cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
		}

		lblNewLabel = new JLabel("Đường dẫn file excel:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 0, 134, 25);
		contentPanel.add(lblNewLabel);

		txlink = new JTextField();
		txlink.setBounds(10, 31, 269, 25);
		contentPanel.add(txlink);
		txlink.setColumns(10);
		ButtonGroup group = new ButtonGroup();

		btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btloadaction();
			}
		});
		btnNewButton.setBounds(289, 32, 40, 25);
		contentPanel.add(btnNewButton);

		panel = new JPanel();
		panel.setBounds(0, 90, 334, 33);
		contentPanel.add(panel);

		rbkeep = new JRadioButton("Giữ nguyên");
		panel.add(rbkeep);
		rbkeep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rbkeep.setSelected(true);
		group.add(rbkeep);

		rbwrite = new JRadioButton("Ghi đè");
		panel.add(rbwrite);
		rbwrite.setFont(new Font("Tahoma", Font.PLAIN, 13));
		group.add(rbwrite);

		rbadd = new JRadioButton("Cộng dồn");
		panel.add(rbadd);
		rbadd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		group.add(rbadd);
		if (!this.getType(type)) {
			rbadd.setVisible(false);
		}
	}

	private boolean getType(String type) {
		type = type.toLowerCase();
		type = type.trim();
		switch (type) {
		case "customer":
		case "employer":
		case "supplier":
		case "bill":
		case "bill_sale":
		case "import":
		case "product_type":
		case "prod_sale":
		case "sale":
		case "recipe":
		case "bill_detail":
		case "import_detail":
			return false;
		case "product":
		case "ingredient":
			return true;
		}
		return true;
	}

	public int selectConfirm() {
		if (this.rbkeep.isSelected()) {
			confirm = 0;
		} else if (this.rbwrite.isSelected()) {
			confirm = 1;
		} else if (this.rbadd.isSelected()) {
			confirm = 2;
		}
		return confirm;
	}

	public int getConfirm() {
		return confirm;
	}

	public String getLink() {
		return this.linkpath;
	}

	public void btloadaction() {
		int select = file.showSaveDialog(this);
		if (select == JFileChooser.APPROVE_OPTION) {
//			filename = file.getSelectedFile().getName();
//			dir = file.getCurrentDirectory().toString();
			String filePath = file.getSelectedFile().toString();
			this.txlink.setText(filePath);
		} else {
			this.txlink.setText("");
		}
	}
}
