package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import design.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;

import dto.Recipe;
import dto.Ingredient;
import bus.IngredientBUS;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class AddRepDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Icon icon = new Icon();
	private JTextField txidpro;
	private JTextField txiding;
	private JTextField txname;
	private JTextField txmeasure;
	private JTextField txamount;
	private JTextField txsearch;
	private JButton btsearch;
	private JRadioButton rbname;
	private JRadioButton rbid;
	private JPanel panel_1;
	Vector<String> header = new Vector<String>();
	private JTable table = new myTable();
	private boolean confirm = false;
	private IngredientBUS bus = new IngredientBUS();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AddRepDialog dialog = new AddRepDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AddRepDialog(String pro_id) {
		this.setTitle("Thêm công thức");
		setBounds(100, 100, 474, 449);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 256, 440, 112);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Mã SP:");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel.setBounds(79, 11, 60, 24);
				panel.add(lblNewLabel);
			}
			{
				txidpro = new JTextField(pro_id);
				txidpro.setEditable(false);
				txidpro.setBounds(149, 11, 127, 24);
				panel.add(txidpro);
				txidpro.setColumns(10);
			}
			{
				JLabel lblMNguynLiu = new JLabel("Mã NL:");
				lblMNguynLiu.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblMNguynLiu.setBounds(10, 46, 60, 24);
				panel.add(lblMNguynLiu);
			}
			{
				txiding = new JTextField();
				txiding.setEditable(false);
				txiding.setColumns(10);
				txiding.setBounds(64, 47, 133, 24);
				panel.add(txiding);
			}
			{
				JLabel lblTnNl = new JLabel("Tên NL:");
				lblTnNl.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblTnNl.setBounds(216, 46, 60, 24);
				panel.add(lblTnNl);
			}
			{
				txname = new JTextField();
				txname.setEditable(false);
				txname.setColumns(10);
				txname.setBounds(280, 47, 133, 24);
				panel.add(txname);
			}
			{
				JLabel lblnV = new JLabel("Đơn vị:");
				lblnV.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblnV.setBounds(10, 81, 60, 24);
				panel.add(lblnV);
			}
			{
				txmeasure = new JTextField();
				txmeasure.setEditable(false);
				txmeasure.setColumns(10);
				txmeasure.setBounds(64, 82, 133, 24);
				panel.add(txmeasure);
			}
			{
				JLabel lblSLng = new JLabel("Số lượng:");
				lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblSLng.setBounds(216, 81, 60, 24);
				panel.add(lblSLng);
			}
			{
				txamount = new JTextField();
				txamount.setColumns(10);
				txamount.setBounds(280, 82, 133, 24);
				panel.add(txamount);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 11, 440, 45);
			contentPanel.add(panel);
			panel.setLayout(null);

			txsearch = new JTextField();
			txsearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txsearch.setColumns(10);
			txsearch.setBounds(6, 11, 200, 24);
			panel.add(txsearch);

			btsearch = new JButton("Tìm kiếm");
			btsearch.setIcon(icon.imageIconSize("icons/search_more.png", 20, 20));
			btsearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btsearchAction();
				}
			});
			btsearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btsearch.setBounds(312, 10, 120, 25);
			panel.add(btsearch);

			rbname = new JRadioButton("Tên");
			rbname.setSelected(true);
			rbname.setFont(new Font("Tahoma", Font.PLAIN, 13));
			rbname.setBounds(258, 12, 57, 23);
			panel.add(rbname);

			rbid = new JRadioButton("Mã");
			rbid.setFont(new Font("Tahoma", Font.PLAIN, 13));
			rbid.setBounds(212, 12, 43, 23);
			panel.add(rbid);
		}
		ButtonGroup group = new ButtonGroup();
		group.add(rbname);
		group.add(rbid);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Danh s\u00E1ch nguy\u00EAn li\u1EC7u", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 57, 440, 188);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		header.add("Mã");
		header.add("Tên");
		header.add("Đơn vị");
		DefaultTableModel model = new DefaultTableModel(header, 0);
		table.setModel(model);
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				selectRowTable();
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
		table.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					selectRowTable();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					selectRowTable();
				}
			}

			public void keyTyped(KeyEvent e) {
			}
		});
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 20, 420, 160);
		panel_1.add(scroll);

		this.loadData();

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Thêm");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				okButton.setIcon(icon.imageIconSize("icons/ok.png", 20, 20));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (checkValue()) {
							Recipe rep = getData();
							int a = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm công thức: Nguyên liệu "
									+ txname.getText() + " vào Sản phẩm mã " + rep.getProd_id(), "Thêm",
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

	public void loadData() {
		DefaultTableModel md = new DefaultTableModel(header, 0);
		ArrayList<Ingredient> arr = this.bus.readAll();
		for (Ingredient p : arr) {
			Object[] row = { p.getIng_id(), p.getIng_name(), p.getMeasure() };
			md.addRow(row);
		}
		table.setModel(md);
	}

	public void loadData(ArrayList<Ingredient> arr) {
		if(arr.size()==0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả nào! ");
			return ;
		}
		DefaultTableModel md = new DefaultTableModel(header, 0);
		for (Ingredient p : arr) {
			Object[] row = { p.getIng_id(), p.getIng_name(), p.getMeasure() };
			md.addRow(row);
		}
		table.setModel(md);
	}

	public boolean checkValue() {
		String amount = txamount.getText();
		if (!amount.matches("^[1-9]\\d*\\.?\\d*$")) {
			JOptionPane.showMessageDialog(this, "Số lượng phải là số thực hoặc số nguyên dương");
			return false;
		}
		return true;
	}

	public Recipe getData() {
		Recipe rep = new Recipe(txidpro.getText(), txiding.getText(), Double.parseDouble(txamount.getText()));
		return rep;
	}

	Recipe getRep() {
		return getRep(this.confirm);
	}

	Recipe getRep(boolean confirm) {
		if (confirm) {
			return getData();
		} else
			return null;
	}

	public void btsearchAction() {
		DefaultTableModel md = new DefaultTableModel(header, 0);
		String text = txsearch.getText();
		text = text.trim();
		if (text.equals("")) {
			this.loadData();
		} else if (rbid.isSelected()) {
			Ingredient p = bus.searchByID(text, true);
			if (p != null) {
				Object[] row = { p.getIng_id(), p.getIng_name(), p.getMeasure() };
				md.addRow(row);
			}
			else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả nào! ");
			}
			table.setModel(md);
			return;
		} else if (rbname.isSelected()) {
			ArrayList<Ingredient> arr = bus.searchByName(text);
			this.loadData(arr);
		}
	}

	public void selectRowTable() {
		int i = table.getSelectedRow();
		if (i >= 0) {
			txiding.setText(table.getModel().getValueAt(i, 0).toString());
			txname.setText(table.getModel().getValueAt(i, 1).toString());
			txmeasure.setText(table.getModel().getValueAt(i, 2).toString());
		}
	}
}
