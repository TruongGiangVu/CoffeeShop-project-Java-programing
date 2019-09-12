package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import design.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import dto.Product;
import bus.ProductBUS;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AddProdDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Icon icon=new Icon();
	JFileChooser file = new JFileChooser();
	ProductBUS bus = new ProductBUS();
	private JComboBox cbtype;
	private JTextField txname;
	private JLabel lblNewLabel_2;
	private JTextField txprice;
	private JLabel lblnV;
	private JTextField txmeasure;
	private JPanel panel;
	private JLabel lblMT;
	private JTextArea txdescription;
	private JLabel lbimage;
	private boolean confirm = false;
	private JButton btload;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AddProdDialog dialog = new AddProdDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AddProdDialog() {
		setTitle("Thêm sản phẩm");
		setBounds(100, 100, 511, 331);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Loại sản phẩm:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel.setBounds(210, 11, 93, 25);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Tên sản phẩm:");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(210, 47, 93, 25);
			contentPanel.add(lblNewLabel_1);
		}

		cbtype = new JComboBox();
		cbtype.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbtype.setModel(new DefaultComboBoxModel(
				new String[] { "Cà phê", "Sinh Tố", "Nước ngọt có ga", "Rượu", "Bia", "Trà" }));
		cbtype.setBounds(313, 12, 148, 25);
		contentPanel.add(cbtype);

		txname = new JTextField();
		txname.setBounds(313, 49, 148, 25);
		contentPanel.add(txname);
		txname.setColumns(10);

		lblNewLabel_2 = new JLabel("Đơn giá:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(210, 84, 93, 25);
		contentPanel.add(lblNewLabel_2);

		txprice = new JTextField();
		txprice.setColumns(10);
		txprice.setBounds(313, 84, 148, 25);
		contentPanel.add(txprice);

		lblnV = new JLabel("Đơn vị:");
		lblnV.setHorizontalAlignment(SwingConstants.CENTER);
		lblnV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblnV.setBounds(210, 120, 93, 25);
		contentPanel.add(lblnV);

		txmeasure = new JTextField();
		txmeasure.setColumns(10);
		txmeasure.setBounds(313, 120, 148, 25);
		contentPanel.add(txmeasure);

		panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "H\u00ECnh \u1EA3nh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 190, 190);
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(1, 1, 0, 0));

		lbimage = new JLabel("");
		panel.add(lbimage);

		lblMT = new JLabel("Mô tả:");
		lblMT.setHorizontalAlignment(SwingConstants.CENTER);
		lblMT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMT.setBounds(210, 156, 93, 25);
		contentPanel.add(lblMT);

		txdescription = new JTextArea();
		txdescription.setLineWrap(true);
		txdescription.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(txdescription);
		scroll.setBounds(313, 156, 148, 58);
		contentPanel.add(scroll);

		btload = new JButton("Tải ảnh");
		btload.setIcon(icon.imageIconSize("icons/image.png", 20, 20));
		btload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btloadAction();
			}
		});
		btload.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btload.setBounds(10, 204, 110, 23);
		contentPanel.add(btload);
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
							Product prod = getData();
							int a = JOptionPane.showConfirmDialog(null,
									"Bạn có muốn thêm sản phẩm :" + prod.getProd_name(), "Thêm",
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

	Product getData() {
		Product pro = new Product("", txname.getText(), Double.parseDouble(txprice.getText()),
				0, txmeasure.getText(), 0, txdescription.getText(), "");
		String type = cbtype.getSelectedItem().toString();
		String id = "";
		int code = bus.getRow(this.getIdType(type)) + 1;
		if (code < 10)
			id = "00" + code;
		else if (code < 100)
			id = "0" + code;
		else if (code < 1000)
			id = "" + code;
		else {
			JOptionPane.showMessageDialog(this, "Hết dung lượng bộ nhớ.");
			return null;
		}
		id = this.getIdType(type) + id;
		String filePath = "", imageName = "";
		if (file.getSelectedFile() != null) {
			filePath = file.getSelectedFile().toString();
			imageName = id + ".jpg";
			this.saveImage(filePath, imageName);
		}
		pro.setImg("images/" + imageName);
		pro.setProd_id(id);
		return pro;
	}

	Product getProd() {
		return getProd(this.confirm);
	}

	Product getProd(boolean confirm) {
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

	String getIdType(String type) {
		switch (type) {
		case "Cà phê":
			return "CF";
		case "Sinh Tố":
			return "FJ";
		case "Nước ngọt có ga":
			return "SD";
		case "Rượu":
			return "WI";
		case "Bia":
			return "BR";
		case "Trà":
			return "TE";
		}
		return "";
	}

	public boolean checkValue() {
		boolean check = true;
		String error = "";
		String name = txname.getText();
		name = this.covertStringToURL(name);
		String price = txprice.getText();
		
		String measure = txmeasure.getText();
		measure = this.covertStringToURL(measure);
		if (!name.matches("^([A-Z][a-z]*\\s*)+$")) {
			error += "Tên sản phẩm có thể viết sai định dạng.\n";
			check = false;
		}
		if (!price.matches("^[1-9]\\d*\\.?\\d*$")) {
			error += "Đơn giá phải là số.\n";
			check = false;
		}
		
		if (!measure.matches("^([A-Z][a-z]*\\s*)+$")) {
			error += "Đơn vị là chữ viết hoa chữ đầu.\n";
			check = false;
		}
		if (check == false)
			JOptionPane.showMessageDialog(this, error);
		return check;
	}

	public void btloadAction() {
		int select = file.showSaveDialog(this);
		if (select == JFileChooser.APPROVE_OPTION) {
//			filename = file.getSelectedFile().getName();
//			dir = file.getCurrentDirectory().toString();
			String filePath = file.getSelectedFile().toString();
			this.loadImage(filePath);
		} else {
			lbimage.setIcon(null);
		}
	}

	public void saveImage(String imagepath, String filename) {
		BufferedImage image = null;
		try {
			// URL url = new URL(imagepath);
			image = ImageIO.read(new File(imagepath));
			ImageIO.write(image, "jpg", new File("./images/" + filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadImage(String nameImage) {
		ImageIcon icon = new ImageIcon(nameImage); // load the image to a imageIcon
		Image image = icon.getImage(); // transform it
		Image newimg = image.getScaledInstance(180, 180, java.awt.Image.SCALE_SMOOTH); // scale it the// smooth way
		icon = new ImageIcon(newimg);
		lbimage.setIcon(icon);
	}
}
