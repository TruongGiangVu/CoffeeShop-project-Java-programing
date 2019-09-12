package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ExcelImportDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JFileChooser file = new JFileChooser();
	JFileChooser file2 = new JFileChooser();
	private int confirm = 3;
	private JLabel lblNewLabel;
	private JTextField txlink;
	private JRadioButton rbkeep;
	private JRadioButton rbwrite;
	private String linkpath = "";
	private String linkpath2 = "";
	private JButton btlink;
	private JPanel panel;
	private JTextField txlink2;
	private JLabel lblngDnFile;
	private JButton btlink2;

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
	public ExcelImportDialog() {
		setTitle("Sự cố trùng lặp mã và cách giải quyết");
		setBounds(100, 100, 353, 257);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbinfo = new JLabel("Xử lý trùng lặp mã");

			lbinfo.setHorizontalAlignment(SwingConstants.CENTER);
			lbinfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbinfo.setBounds(65, 117, 195, 31);
			contentPanel.add(lbinfo);
		}
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 176, 334, 42);
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
					else if (txlink.getText().trim().equals("") || txlink2.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Bạn chưa nhập đường dẫn cho 2 file!");
					} else {
						confirm = selectConfirm();
						linkpath = txlink.getText().trim();
						linkpath2 = txlink2.getText().trim();
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

		lblNewLabel = new JLabel("Đường dẫn file Import:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 0, 134, 25);
		contentPanel.add(lblNewLabel);

		txlink = new JTextField();
		txlink.setBounds(10, 31, 269, 25);
		contentPanel.add(txlink);
		txlink.setColumns(10);
		ButtonGroup group = new ButtonGroup();

		btlink = new JButton("...");
		btlink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btloadaction();
			}
		});
		btlink.setBounds(289, 32, 40, 25);
		contentPanel.add(btlink);

		panel = new JPanel();
		panel.setBounds(0, 143, 334, 33);
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
		
		txlink2 = new JTextField();
		txlink2.setColumns(10);
		txlink2.setBounds(10, 94, 269, 25);
		contentPanel.add(txlink2);
		
		lblngDnFile = new JLabel("Đường dẫn file Import Detail:");
		lblngDnFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblngDnFile.setBounds(10, 63, 182, 25);
		contentPanel.add(lblngDnFile);
		
		btlink2 = new JButton("...");
		btlink2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btloadaction2();
			}
		});
		btlink2.setBounds(289, 95, 40, 25);
		contentPanel.add(btlink2);
	}

	public int selectConfirm() {
		if (this.rbkeep.isSelected()) {
			confirm = 0;
		} else if (this.rbwrite.isSelected()) {
			confirm = 1;
		} 
		return confirm;
	}

	public int getConfirm() {
		return confirm;
	}

	public String getImport() {
		return this.linkpath;
	}
	
	public String getImportDetail() {
		return this.linkpath2;
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
	public void btloadaction2() {
		int select = file2.showSaveDialog(this);
		if (select == JFileChooser.APPROVE_OPTION) {
//			filename = file.getSelectedFile().getName();
//			dir = file.getCurrentDirectory().toString();
			String filePath = file2.getSelectedFile().toString();
			this.txlink2.setText(filePath);
		} else {
			this.txlink2.setText("");
		}
	}
}