package gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import design.*;

import javax.swing.JButton;
import java.awt.Color;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.event.*;

import dto.Customer;
import bus.CustomerBUS;
import bus.EmployerBUS;
import dto.Employer;
import bus.Excel;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

public class CustomerGUI extends JPanel implements ActionListener {
	Excel excel = new Excel();
	private boolean authority = false;
	ArrayList<Customer> list;
	private AddCusDialog dialog;
	private ReadExcelDialog dialogexcel;
	CustomerBUS bus = new CustomerBUS();
	private JPanel pninfo = new JPanel();
	JLabel lbid = new JLabel("Mã Khách hàng: ");
	JLabel lbname = new JLabel("Họ và tên: ");
	JLabel lbphone = new JLabel("Số điện thoại: ");
	private JTextField txid = new JTextField();
	private JTextField txname = new JTextField();
	private JTextField txphone = new JTextField();

	JPanel pnbutton = new JPanel();
	JButton btinsert = new JButton("THÊM");
	JButton btdelete = new JButton("XÓA");
	JButton btedit = new JButton("SỬA");
	JButton btreset = new JButton("XÓA HIỄN THỊ");
	JButton btrecover = new JButton("MỞ");

	JPanel pnsearch = new JPanel();
	private JTextField txsearch = new JTextField();
	JButton btsearch = new JButton("TÌM");
	JRadioButton rbid = new JRadioButton("Mã ");
	JRadioButton rbname = new JRadioButton("Họ và tên");
	JRadioButton rbphone = new JRadioButton("Số điện thoại");

	JPanel pnsort = new JPanel();
	JComboBox cbtype = new JComboBox();
	JLabel lbtype = new JLabel("Sắp xếp theo:");
	JLabel lblThT = new JLabel("Thứ tự:");
	JComboBox cbside = new JComboBox();
	JButton btsort = new JButton("SẮP XẾP");

	JPanel pntable = new JPanel();
	Vector<String> header = new Vector<String>();
	JTable table = new myTable();
	private JPanel pnexcel;
	private JButton btimport= new JButton();
	private JButton btexport = new JButton();

	/**
	 * Create the panel.
	 */
	public CustomerGUI(String id) {
		Icon icon = new Icon();
		EmployerBUS emp1 = new EmployerBUS();
		Employer emp2 = emp1.searchByID(id);
		this.authority = emp2.isEmp_type();
		Color orange = new Color(253, 203, 151);
		Color gray = new Color(101, 98, 97);
		this.setSize(1000,700);
		btinsert.setBorder(null);
		btinsert.setBounds(46, 21, 146, 30);
		btinsert.setForeground(new Color(255, 255, 255));
		btinsert.setBackground(new Color(0, 255, 0));
		// cursor
//		btinsert.setCursor(icon.imageCursor("icons/cursor.png"));
		btdelete.setBorder(null);
		btdelete.setBounds(46, 59, 146, 30);
		btdelete.setForeground(Color.WHITE);
		btdelete.setBackground(new Color(178, 34, 34));
//		btdelete.setCursor(icon.imageCursor("icons/cursor.png"));
		btedit.setBorder(null);
		btedit.setBounds(202, 21, 146, 30);
		btedit.setForeground(Color.WHITE);
		btedit.setBackground(new Color(244, 117, 32));
//		btedit.setCursor(icon.imageCursor("icons/cursor.png"));
		btrecover.setBorder(null);
		btrecover.setBounds(202, 59, 146, 30);
		btrecover.setForeground(new Color(255, 255, 255));
//		btrecover.setCursor(icon.imageCursor("icons/cursor.png"));
		btsearch.setBackground(icon.getSearchColor());
//		btsearch.setCursor(icon.imageCursor("icons/cursor.png"));
		btsort.setForeground(Color.WHITE);
		btsort.setBorder(null);
		btsort.setBackground(new Color(0, 128, 128));
//		btsort.setCursor(icon.imageCursor("icons/cursor.png"));
//		rbid.setCursor(icon.imageCursor("icons/cursor.png"));
//		rbname.setCursor(icon.imageCursor("icons/cursor.png"));
//		rbphone.setCursor(icon.imageCursor("icons/cursor.png"));
//		cbtype.setCursor(icon.imageCursor("icons/cursor.png"));
//		cbside.setCursor(icon.imageCursor("icons/cursor.png"));
//		table.setCursor(icon.imageCursor("icons/cursor.png"));
//		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// color
		Color littleBlue = new Color(204, 228, 228);
		rbid.setBackground(littleBlue);
		rbname.setBackground(littleBlue);
		rbphone.setBackground(littleBlue);
		pninfo.setBackground(new Color(204, 228, 228));
		pnbutton.setBackground(new Color(204, 228, 228));
		pnsearch.setBackground(new Color(204, 228, 228));
		pnsort.setBackground(new Color(204, 228, 228));
		pntable.setBackground(new Color(152, 209, 203));
		this.setBackground(new Color(78, 186, 163));
		this.setLayout(null);

		pninfo.setBorder(new TitledBorder(null, "Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pninfo.setBounds(10, 10, 393, 203);

		add(pninfo);
		pninfo.setLayout(null);
		lbid.setHorizontalAlignment(SwingConstants.CENTER);

		lbid.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbid.setSize(110, 30);
		lbid.setLocation(22, 30);
		pninfo.add(lbid);

		txid.setEditable(false);
		txid.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txid.setBounds(130, 30, 200, 30);
		pninfo.add(txid);
		txid.setColumns(10);
		lbname.setHorizontalAlignment(SwingConstants.CENTER);

		lbname.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbname.setBounds(22, 80, 110, 30);
		pninfo.add(lbname);
		txname.setToolTipText("Họ và tên của khách hàng");

		txname.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txname.setBounds(130, 80, 200, 30);
		pninfo.add(txname);
		txname.setColumns(10);
		lbphone.setHorizontalAlignment(SwingConstants.CENTER);

		lbphone.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbphone.setBounds(22, 130, 110, 30);
		pninfo.add(lbphone);
		txphone.setToolTipText("Số điện thoại gồm 10 chữ số và bắt đầu bằng 0");

		txphone.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txphone.setColumns(10);
		txphone.setBounds(130, 130, 200, 30);
		pninfo.add(txphone);
		btreset.setBorder(null);
		btreset.setForeground(new Color(255, 255, 255));
		btreset.setBackground(new Color(178, 34, 34));
		btreset.setBounds(126, 171, 143, 25);
		pninfo.add(btreset);
//		btreset.setCursor(icon.imageCursor("icons/cursor.png"));

		btreset.setFont(new Font("sans-serif", Font.BOLD, 14));
		btreset.setIcon(icon.imageIconSize("icons/broom2.png", 20, 20));
		btreset.addActionListener(this);

		pnbutton.setBorder(new TitledBorder(null, "Thao t\u00E1c tr\u00EAn kh\u00E1ch h\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnbutton.setBounds(10, 224, 393, 100);
		add(pnbutton);
		pnbutton.setLayout(null);

		btinsert.setFont(new Font("sans-serif", Font.BOLD, 14));
		btinsert.setIcon(icon.imageIconSize("icons/add.png", 20, 20));
		pnbutton.add(btinsert);

		btdelete.setFont(new Font("sans-serif", Font.BOLD, 14));
		btdelete.setIcon(icon.imageIconSize("icons/delete.png", 20, 20));
		pnbutton.add(btdelete);

		btedit.setFont(new Font("sans-serif", Font.BOLD, 14));
		btedit.setIcon(icon.imageIconSize("icons/edit.png", 20, 20));
		pnbutton.add(btedit);

		btrecover.setFont(new Font("sans-serif", Font.BOLD, 14));
		btrecover.setBackground(new Color(30, 100, 174));
		btrecover.setIcon(icon.imageIconSize("icons/return.png", 20, 20));
		if (authority == false)
			btrecover.setVisible(false);
		pnbutton.add(btrecover);

		pnsearch.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm kh\u00E1ch h\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnsearch.setBounds(10, 330, 393, 113);
		btsearch.setIcon(icon.imageIconSize("icons/search_more.png", 20, 20));
		add(pnsearch);
		pnsearch.setLayout(null);
		txsearch.setToolTipText("Thông tin bạn muốn tìm");

		txsearch.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txsearch.setBounds(26, 22, 219, 30);
		pnsearch.add(txsearch);
		txsearch.setColumns(10);

		btsearch.setFont(new Font("sans-serif", Font.PLAIN, 14));
		btsearch.setBounds(255, 21, 115, 30);
		pnsearch.add(btsearch);

		ButtonGroup group = new ButtonGroup();
		group.add(rbid);
		group.add(rbphone);
		group.add(rbname);
		rbname.setSelected(true);
		rbid.setFont(new Font("sans-serif", Font.PLAIN, 14));
		rbid.setBounds(43, 63, 60, 23);
		pnsearch.add(rbid);

		rbname.setFont(new Font("sans-serif", Font.PLAIN, 14));
		rbname.setBounds(105, 63, 95, 23);
		pnsearch.add(rbname);

		rbphone.setFont(new Font("sans-serif", Font.PLAIN, 14));
		rbphone.setBounds(203, 63, 109, 23);
		pnsearch.add(rbphone);

		pnsort.setBorder(
				new TitledBorder(null, "S\u1EAFp x\u1EBFp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnsort.setBounds(413, 10, 550, 62);
		add(pnsort);
		pnsort.setLayout(null);

		cbtype.setFont(new Font("sans-serif", Font.PLAIN, 13));
		cbtype.setModel(new DefaultComboBoxModel(new String[] { "Mã ", "Họ và Tên" }));
		cbtype.setBounds(109, 18, 109, 33);
		pnsort.add(cbtype);
		lbtype.setHorizontalAlignment(SwingConstants.CENTER);

		lbtype.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbtype.setBounds(10, 18, 89, 33);
		pnsort.add(lbtype);
		lblThT.setHorizontalAlignment(SwingConstants.CENTER);

		lblThT.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lblThT.setBounds(244, 17, 62, 33);
		pnsort.add(lblThT);

		cbside.setModel(new DefaultComboBoxModel(new String[] { "Tăng dần", "Giảm dần" }));
		cbside.setFont(new Font("sans-serif", Font.PLAIN, 13));
		cbside.setBounds(304, 18, 89, 33);
		pnsort.add(cbside);

		btsort.setFont(new Font("sans-serif", Font.BOLD, 14));
		btsort.setBounds(424, 20, 109, 30);
		btsort.setIcon(icon.imageIconSize("icons/sort.png", 20, 20));
		pnsort.add(btsort);

		pntable.setBorder(new TitledBorder(null, "Danh s\u00E1ch kh\u00E1ch h\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pntable.setBounds(413, 83, 550, 570);
		add(pntable);

		header.add("Mã");
		header.add("Họ và tên");
		header.add("Số điện thoại");
		if (authority == true)
			header.add("Khóa");
		DefaultTableModel model = new DefaultTableModel(header, 2);
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
		pntable.setLayout(new BoxLayout(pntable, BoxLayout.X_AXIS));
		JScrollPane scrollPane = new JScrollPane(table);
		pntable.add(scrollPane);
		this.loadData();
		this.loadData();
		if (this.authority == true)
			list = bus.readAll();
		else
			list = bus.read();

		pnexcel = new JPanel();
		pnexcel.setBounds(10, 454, 393, 70);
		add(pnexcel);
		pnexcel.setLayout(null);
		btimport.setToolTipText("Đọc dữ liệu từ file excel mà bạn chọn.");

		btimport.setIcon(icon.imageIconSize("icons/import2.png", 40, 40));
		btimport.setBackground(new Color(255, 255, 255));
		btimport.setBorder(null);
		btimport.setBounds(10, 11, 40, 40);
		pnexcel.add(btimport);
		btexport.setToolTipText("Ghi dữ liệu vào file excel");

		btexport.setIcon(icon.imageIconSize("icons/export2.png", 40, 40));
		btexport.setBackground(new Color(255, 255, 255));
		btexport.setBorder(null);
		btexport.setBounds(60, 11, 40, 40);
		pnexcel.add(btexport);
		pnexcel.setBackground(icon.getContentColor());

		// event
		btinsert.addActionListener(this);
		btdelete.addActionListener(this);
		btedit.addActionListener(this);
		btsort.addActionListener(this);
		btsearch.addActionListener(this);
		btrecover.addActionListener(this);
		btimport.addActionListener(this);
		btexport.addActionListener(this);
		if(!this.authority) {
			btimport.setVisible(false);
			btexport.setVisible(false);
		}
	}

	void loadData() {
		DefaultTableModel md = new DefaultTableModel(header, 0);
		if (authority == false) {
			ArrayList<Customer> arr = bus.read();
			for (Customer p : arr) {
				Object[] row = { p.getCus_id(), p.getCus_name(), p.getPhone() };
				md.addRow(row);
			}
		} else {
			ArrayList<Customer> arr = bus.readAll();
			for (Customer p : arr) {
				if (p.getStatus() == 1) {
					Object[] row = { p.getCus_id(), p.getCus_name(), p.getPhone(), "x" };
					md.addRow(row);
				} else {
					Object[] row = { p.getCus_id(), p.getCus_name(), p.getPhone(), "" };
					md.addRow(row);
				}
			}
		}
		table.setModel(md);
		this.setColSize(table);
	}

	void loadData(ArrayList<Customer> arr) {
		DefaultTableModel md = new DefaultTableModel(header, 0);
		if (authority == false)
			for (Customer p : arr) {
				Object[] row = { p.getCus_id(), p.getCus_name(), p.getPhone() };
				md.addRow(row);
			}
		else
			for (Customer p : arr) {
				if (p.getStatus() == 1) {
					Object[] row = { p.getCus_id(), p.getCus_name(), p.getPhone(), "x" };
					md.addRow(row);
				} else {
					Object[] row = { p.getCus_id(), p.getCus_name(), p.getPhone(), "" };
					md.addRow(row);
				}
			}
		if(arr.size()==0) {
			JOptionPane.showMessageDialog(this,"Không tìm thấy kết quả nào!");
		}
		table.setModel(md);
		this.setColSize(table);
	}

	Customer getData() {
		Customer cus = new Customer(txid.getText(), txname.getText(), txphone.getText(), 0);
		return cus;
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.btinsert) {
			btinsertaction();
			this.loadData();
		} else if (source == this.btdelete) {
			this.btdeleteaction();
			this.loadData();
		} else if (source == this.btedit) {
			this.bteditaction();
			this.loadData();
		} else if (source == this.btreset) {
			this.btresetaction();
		} else if (source == this.btsearch) {
			this.btsearchaction();
		} else if (source == this.btsort) {
			this.btsortaction();
		} else if (source == this.btrecover) {
			this.btrecoveraction();
			this.loadData();
		} else if (source == this.btexport) {
			btexportAction();
		} else if (source == this.btimport) {
			btimportAction();
			this.loadData();
		}
	}

	public void btinsertaction() {
		dialog = new AddCusDialog();
		this.dialog.setLocationRelativeTo(null);
		this.dialog.setModal(true);
		this.dialog.setVisible(true);
		Customer cus = this.dialog.getCus();
		if (cus != null) {
			String id = "";
			int code = bus.getRow() + 1;
			if (code < 10)
				id = "0000" + code;
			else if (code < 100)
				id = "000" + code;
			else if (code < 1000)
				id = "00" + code;
			else if (code < 10000)
				id = "0" + code;
			else if (code < 100000)
				id = "" + code;
			else {
				JOptionPane.showMessageDialog(this, "Hết dung lượng bộ nhớ.");
				return;
			}
			cus.setCus_id(id);
			bus.add(cus);
		}
	}

	public void btdeleteaction() {
		int i = table.getSelectedRow();
		if (i >= 0) {
			Customer cus = this.getData();
			String id = cus.getCus_id();
			int a = JOptionPane.showConfirmDialog(this, "Bạn chắc là muốn xóa khách hàng với mã: " + id + ".", "Xóa",
					JOptionPane.YES_NO_OPTION);
			if (a == 0) {
				bus.disable(id);
				JOptionPane.showMessageDialog(this, "Bạn đã xóa khách hàng " + cus.getCus_name() + " khỏi danh sách.");
			}
		}
	}

	public void bteditaction() {
		int i = table.getSelectedRow();
		if (i >= 0) {
			if (!this.checkValue()) {
				return;}
			Customer cus = this.getData();
			int a = JOptionPane.showConfirmDialog(this,
					"Bạn chắc là muốn sửa thông tin khách hàng với mã: " + cus.getCus_id() + ".", "Sửa",
					JOptionPane.YES_NO_OPTION);
			if (a == 0) {
				bus.update(cus);
				JOptionPane.showMessageDialog(this,
						"Bạn đã sửa thông tin khách hàng với mã" + cus.getCus_id() + " thành công.");
			}
		}
	}

	public void btresetaction() {
		txid.setText("");
		txname.setText("");
		txphone.setText("");
	}

	public void btimportAction() {
		dialogexcel = new ReadExcelDialog("customer");
		this.dialogexcel.setLocationRelativeTo(null);
		this.dialogexcel.setModal(true);
		this.dialogexcel.setVisible(true);
		int select = dialogexcel.getConfirm();
		if (select == 0)
			return;
		else if (select == 1) {
			ArrayList<Customer> arr = excel.readCustomer(this.dialogexcel.getLink());
			if(arr == null) return ;	
			for (Customer p : arr) {
				bus.update(p);
			}
			JOptionPane.showMessageDialog(this, "Đã cập nhật lại dữ liệu thành công", "Cập nhật dữ liệu",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void btexportAction() {
		int a=JOptionPane.showConfirmDialog(this,"Bạn có muốn ghi dữ liệu vào file excel?","Ghi file excel",JOptionPane.YES_NO_OPTION);
		if(a==0) {
			excel.writeCustomer();
			JOptionPane.showMessageDialog(this, "Đã ghi dữ liệu vào file excel thành công!");
		}	
	}
	public void btsearchaction() {
		String text = txsearch.getText();
		text = text.trim();
		DefaultTableModel md = new DefaultTableModel(header, 0);
		if (text.equals("")) {
			this.loadData();
			if (this.authority == true)
				list = bus.readAll();
			else
				list = bus.read();
		} else if (rbid.isSelected()) {
			Customer cus = bus.searchByID(text, this.authority);
			if (cus != null) {
				Object[] row = { cus.getCus_id(), cus.getCus_name(), cus.getPhone() };
				md.addRow(row);
			}
			else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả nào! ");
			}
			table.setModel(md);
			return;
		} else {
			ArrayList<Customer> ab = new ArrayList<Customer>();
			if (rbname.isSelected()) {
				ab = bus.searchByName(text, this.authority);
			} else if (rbphone.isSelected()) {
				ab = bus.searchByPhone(text, this.authority);
			}
			list = ab;
			this.loadData(ab);
		}
	}

	public void btsortaction() {
		String type = this.cbtype.getSelectedItem().toString();
		String side = this.cbside.getSelectedItem().toString();
		type = type.toLowerCase();
		side = side.toLowerCase();
		ArrayList<Customer> ab = bus.sort(list, type, side, authority);
		this.loadData(ab);
	}

	public void btrecoveraction() {
		int i = table.getSelectedRow();
		if (i >= 0) {
			Customer cus = this.getData();
			int a = JOptionPane.showConfirmDialog(this,
					"Bạn chắc là muốn khôi phục khách hàng với mã: " + cus.getCus_id() + ".", "Khôi phục",
					JOptionPane.YES_NO_OPTION);
			if (a == 0)
				bus.active(cus.getCus_id());
		}
	}

	public boolean checkValue() {
		boolean check = true;
		String error = "";
		String name = txname.getText();
		name = this.covertStringToURL(name);
		String phone = txphone.getText();
		if (!name.matches("^([A-Z][a-z]*\\s*)+$")) {
			error += "Họ tên có thể đã viết sai định dạng.\n";
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

	public void setColSize(JTable table) {
		TableColumnModel col = table.getColumnModel();
		this.setFixColSize(col, 0, 50);
		this.setFixColSize(col, 1, 250);
		this.setFixColSize(col, 2, 120);
		if (this.authority == true)
			this.setFixColSize(col, 3, 20);
	}

	public void setFixColSize(TableColumnModel col, int index, int size) {
		col.getColumn(index).setMinWidth(size);
		// col.getColumn(index).setResizable(false);
	}

	public void selectRowTable() {
		int i = table.getSelectedRow();
		if (i >= 0) {
			txid.setText(table.getModel().getValueAt(i, 0).toString());
			txname.setText(table.getModel().getValueAt(i, 1).toString());
			txphone.setText(table.getModel().getValueAt(i, 2).toString());
			if (authority == true)
				if (table.getModel().getValueAt(i, 3).toString().equals("x")) {
					txname.setEditable(false);
					txphone.setEditable(false);
					btrecover.setEnabled(true);
					btdelete.setEnabled(false);
					btedit.setEnabled(false);
				} else {
					txname.setEditable(true);
					txphone.setEditable(true);
					btrecover.setEnabled(false);
					btdelete.setEnabled(true);
					btedit.setEnabled(true);
				}
		}
	}
}
