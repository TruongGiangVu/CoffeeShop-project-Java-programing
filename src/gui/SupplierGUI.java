package gui;

import java.awt.Font;
import java.awt.event.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Pattern;
import design.*;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import dto.Supplier;
import bus.SupplierBUS;
import bus.EmployerBUS;
import dto.Customer;
import dto.Employer;
import bus.Excel;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

public class SupplierGUI extends JPanel implements ActionListener {
	private boolean authority = false;
	private ReadExcelDialog dialogexcel;
	private Excel excel=new Excel();
	private AddSupDialog dialog;
	ArrayList<Supplier> list;
	SupplierBUS bus = new SupplierBUS();
	private JPanel pninfo = new JPanel();
	JLabel lbid = new JLabel("Mã nhà cung cấp: ");
	JLabel lbname = new JLabel("Tên nhà Cung cấp:");
	JLabel lbphone = new JLabel("Số điện thoại: ");
	private JTextField txid = new JTextField();
	private JTextField txname = new JTextField();
	private JTextField txphone = new JTextField();

	JPanel pnbutton = new JPanel();
	JButton btinsert = new JButton("THÊM");
	JButton btdelete = new JButton("XÓA");
	JButton btedit = new JButton("SỬA");
	JButton btreset = new JButton("XÓA HIỂN THỊ");
	JButton btrecover = new JButton("MỞ");

	JPanel pnsearch = new JPanel();
	private JTextField txsearch = new JTextField();
	JButton btsearch = new JButton("TÌM");
	JRadioButton rbid = new JRadioButton("Mã ");
	JRadioButton rbname = new JRadioButton("Tên");
	JRadioButton rbphone = new JRadioButton("Số điện thoại");

	JPanel pnsort = new JPanel();
	JComboBox cbtype = new JComboBox();
	JLabel lbtype = new JLabel("Sắp xếp theo:");
	JLabel lblThT = new JLabel("Thứ tự:");
	JComboBox cbside = new JComboBox();
	JButton btsort = new JButton("SẮP XẾP");
	private JLabel lblNewLabel;
	private JTextField txaddress;
	JRadioButton rbaddress = new JRadioButton("Địa chỉ");

	JPanel pntable = new JPanel();
	Vector<String> header = new Vector<String>();
	JTable table = new myTable();
	private JButton btimport;
	private JButton btexport;

	public SupplierGUI(String id) {

		Icon icon = new Icon();
		Color paneColor = new Color(204, 228, 228);
		EmployerBUS emp1 = new EmployerBUS();
		Employer emp2 = emp1.searchByID(id);
		this.authority = emp2.isEmp_type();

		this.setSize(1000, 700);
		this.setLayout(null);
		this.setBackground(new Color(78, 186, 163));

		this.pnbutton.setBackground(paneColor);
		this.pninfo.setBackground(paneColor);
		this.pnsearch.setBackground(paneColor);
		this.pnsort.setBackground(paneColor);
		this.pntable.setBackground(new Color(152, 209, 203));
		this.rbaddress.setBackground(paneColor);
		this.rbid.setBackground(paneColor);
		this.rbphone.setBackground(paneColor);
		this.rbname.setBackground(paneColor);
		btinsert.setBorder(null);
		btinsert.setForeground(Color.WHITE);
		// button
		btinsert.setBackground(icon.getInsertColor());
		btdelete.setBorder(null);
		btdelete.setForeground(Color.WHITE);
		btdelete.setBackground(icon.getDeleteColor());
		btedit.setBorder(null);
		btedit.setForeground(Color.WHITE);
		btedit.setBackground(icon.getEditColor());
		btrecover.setForeground(Color.WHITE);
		btrecover.setBackground(icon.getCoverColor());
		btreset.setBorder(null);
		btreset.setForeground(Color.WHITE);
		btreset.setBackground(icon.getClearColor());
		btsearch.setForeground(Color.BLACK);
		btsearch.setBackground(icon.getSearchColor());
		btsort.setBorder(null);
		btsort.setForeground(Color.WHITE);
		btsort.setBackground(icon.getSortColor());

		pninfo.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng tin nh\u00E0 cung c\u1EA5p",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pninfo.setBounds(10, 10, 400, 259);
		add(pninfo);
		pninfo.setLayout(null);
		lbid.setHorizontalAlignment(SwingConstants.CENTER);

		lbid.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbid.setSize(125, 25);
		lbid.setLocation(20, 30);
		pninfo.add(lbid);

		txid.setEditable(false);
		txid.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txid.setBounds(145, 30, 200, 30);
		pninfo.add(txid);
		txid.setColumns(10);
		lbname.setHorizontalAlignment(SwingConstants.CENTER);

		lbname.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbname.setBounds(20, 80, 125, 25);
		pninfo.add(lbname);
		txname.setToolTipText("Tên của nhà cung cấp");

		txname.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txname.setBounds(145, 80, 200, 30);
		pninfo.add(txname);
		txname.setColumns(10);
		lbphone.setHorizontalAlignment(SwingConstants.CENTER);

		lbphone.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbphone.setBounds(20, 130, 125, 25);
		pninfo.add(lbphone);
		txphone.setToolTipText("Số điện thoại gồm 10 chứ số và có số 0 ở đầu");

		txphone.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txphone.setColumns(10);
		txphone.setBounds(145, 130, 200, 30);
		pninfo.add(txphone);

		lblNewLabel = new JLabel("Địa chỉ:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 180, 125, 25);
		pninfo.add(lblNewLabel);

		txaddress = new JTextField();
		txaddress.setToolTipText("Địa chỉ mà có thể tìm được");
		txaddress.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txaddress.setBounds(145, 180, 200, 30);
		pninfo.add(txaddress);
		txaddress.setColumns(10);
		btreset.setBounds(141, 223, 145, 25);
		pninfo.add(btreset);

		btreset.setFont(new Font("sans-serif", Font.BOLD, 14));
		btreset.addActionListener(this);

		pnbutton.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Thao t\u00E1c tr\u00EAn nh\u00E0 cung c\u1EA5p", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnbutton.setBounds(10, 280, 400, 89);
		add(pnbutton);
		pnbutton.setLayout(null);
		btinsert.setBounds(64, 21, 135, 25);

		btinsert.setFont(new Font("Dialog", Font.BOLD, 14));
		pnbutton.add(btinsert);
		btdelete.setBounds(64, 53, 135, 25);

		btdelete.setFont(new Font("Dialog", Font.BOLD, 14));
		pnbutton.add(btdelete);
		btedit.setBounds(209, 21, 135, 25);

		btedit.setFont(new Font("sans-serif", Font.BOLD, 14));
		pnbutton.add(btedit);
		btrecover.setBounds(209, 53, 135, 25);

		btrecover.setFont(new Font("sans-serif", Font.BOLD, 14));
		if (authority == false)
			btrecover.setVisible(false);
		pnbutton.add(btrecover);

		pnsearch.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"T\u00ECm ki\u1EBFm nh\u00E0 cung c\u1EA5p", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnsearch.setBounds(10, 380, 400, 113);
		add(pnsearch);
		pnsearch.setLayout(null);
		txsearch.setToolTipText("Thông tin bạn muốn tìm");

		txsearch.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txsearch.setBounds(20, 24, 219, 30);
		pnsearch.add(txsearch);
		txsearch.setColumns(10);

		btsearch.setFont(new Font("sans-serif", Font.BOLD, 14));
		btsearch.setBounds(249, 24, 126, 30);
		pnsearch.add(btsearch);

		ButtonGroup group = new ButtonGroup();
		group.add(rbid);
		group.add(rbphone);
		group.add(rbname);
		group.add(rbaddress);
		rbname.setSelected(true);
		rbid.setFont(new Font("sans-serif", Font.PLAIN, 14));
		rbid.setBounds(36, 63, 55, 23);
		pnsearch.add(rbid);

		rbname.setFont(new Font("sans-serif", Font.PLAIN, 14));
		rbname.setBounds(87, 63, 55, 23);
		pnsearch.add(rbname);

		rbphone.setFont(new Font("sans-serif", Font.PLAIN, 14));
		rbphone.setBounds(144, 63, 109, 23);
		pnsearch.add(rbphone);

		rbaddress.setFont(new Font("sans-serif", Font.PLAIN, 14));
		rbaddress.setBounds(255, 63, 109, 23);
		pnsearch.add(rbaddress);

		pnsort.setBorder(
				new TitledBorder(null, "S\u1EAFp x\u1EBFp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnsort.setBounds(420, 10, 543, 60);
		add(pnsort);
		pnsort.setLayout(null);

		cbtype.setFont(new Font("sans-serif", Font.PLAIN, 13));
		cbtype.setModel(new DefaultComboBoxModel(new String[] { "Mã ", "Tên", "Địa chỉ" }));
		cbtype.setBounds(111, 19, 109, 33);
		pnsort.add(cbtype);
		lbtype.setHorizontalAlignment(SwingConstants.CENTER);

		lbtype.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbtype.setBounds(10, 18, 102, 33);
		pnsort.add(lbtype);
		lblThT.setHorizontalAlignment(SwingConstants.CENTER);

		lblThT.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lblThT.setBounds(246, 17, 62, 33);
		pnsort.add(lblThT);

		cbside.setModel(new DefaultComboBoxModel(new String[] { "Tăng dần", "Giảm dần" }));
		cbside.setFont(new Font("sans-serif", Font.PLAIN, 13));
		cbside.setBounds(306, 19, 89, 33);
		pnsort.add(cbside);

		btsort.setFont(new Font("sans-serif", Font.BOLD, 14));
		btsort.setBounds(405, 18, 121, 33);
		pnsort.add(btsort);

		pntable.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Danh s\u00E1ch nh\u00E0 cung c\u1EA5p",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pntable.setBounds(420, 81, 543, 543);
		add(pntable);

		header.add("Mã");
		header.add("Tên nhà cung cấp");
		header.add("Số điện thoại");
		header.add("Đia chỉ");
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

		// icon button
		btinsert.setIcon(icon.imageIconSize("icons/add.png", 20, 20));
		btdelete.setIcon(icon.imageIconSize("icons/delete.png", 20, 20));
		btedit.setIcon(icon.imageIconSize("icons/edit.png", 20, 20));
		btreset.setIcon(icon.imageIconSize("icons/broom2.png", 20, 20));
		btrecover.setIcon(icon.imageIconSize("icons/return.png", 20, 20));
		btsearch.setIcon(icon.imageIconSize("icons/search_more.png", 20, 20));
		btsort.setIcon(icon.imageIconSize("icons/sort.png", 20, 20));

		btimport = new JButton();
		btimport.setToolTipText("Đọc dữ liệu từ file excel mà bạn chọn.");
		btimport.setBorder(null);
		btimport.setBackground(Color.WHITE);
		btimport.setBounds(10, 504, 40, 40);
		add(btimport);

		btexport = new JButton();
		btexport.setToolTipText("Ghi dữ liệu vào file excel");
		btexport.setBorder(null);
		btexport.setBackground(Color.WHITE);
		btexport.setBounds(60, 504, 40, 40);
		add(btexport);

		btimport.setIcon(icon.imageIconSize("icons/import2.png", 40, 40));
		btimport.setBackground(new Color(255, 255, 255));
		btimport.setBorder(null);

		btexport.setIcon(icon.imageIconSize("icons/export2.png", 40, 40));
		btexport.setBackground(new Color(255, 255, 255));
		btexport.setBorder(null);

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
			ArrayList<Supplier> arr = bus.read();
			for (Supplier p : arr) {
				Object[] row = { p.getSup_id(), p.getSup_name(), p.getPhone(), p.getAddress() };
				md.addRow(row);
			}
		} else {
			ArrayList<Supplier> arr = bus.readAll();
			for (Supplier p : arr) {
				if (p.getStatus() == 1) {
					Object[] row = { p.getSup_id(), p.getSup_name(), p.getPhone(), p.getAddress(), "x" };
					md.addRow(row);
				} else {
					Object[] row = { p.getSup_id(), p.getSup_name(), p.getPhone(), p.getAddress(), "" };
					md.addRow(row);
				}
			}
		}
		table.setModel(md);
		this.setColSize(table);
	}

	void loadData(ArrayList<Supplier> arr) {
		DefaultTableModel md = new DefaultTableModel(header, 0);
		if (authority == false)
			for (Supplier p : arr) {
				Object[] row = { p.getSup_id(), p.getSup_name(), p.getPhone(), p.getAddress() };
				md.addRow(row);
			}
		else
			for (Supplier p : arr) {
				if (p.getStatus() == 1) {
					Object[] row = { p.getSup_id(), p.getSup_name(), p.getPhone(), p.getAddress(), "x" };
					md.addRow(row);
				} else {
					Object[] row = { p.getSup_id(), p.getSup_name(), p.getPhone(), p.getAddress(), "" };
					md.addRow(row);
				}
			}
		if (arr.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả nào! ");
		}
		table.setModel(md);
		this.setColSize(table);
	}

	Supplier getData() {
		Supplier sup = new Supplier(txid.getText(), txname.getText(), txaddress.getText(), txphone.getText(),  0);
		return sup;
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
		}else if (source == this.btexport) {
			btexportAction();
		} else if (source == this.btimport) {
			btimportAction();
			this.loadData();
		}
	}

	public void btinsertaction() {
		dialog = new AddSupDialog();
		this.dialog.setLocationRelativeTo(null);
		this.dialog.setModal(true);
		this.dialog.setVisible(true);
		Supplier sup = this.dialog.getSup();
		if (sup != null) {
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
			sup.setSup_id(id);
			bus.add(sup);
		}
	}

	public void btdeleteaction() {
		int i = table.getSelectedRow();
		if (i >= 0) {
			Supplier sup = this.getData();
			String id = sup.getSup_id();
			int a = JOptionPane.showConfirmDialog(this, "Bạn chắc là muốn xóa nhà cung cấp với mã: " + id + ".", "Xóa",
					JOptionPane.YES_NO_OPTION);
			if (a == 0) {
				bus.disable(id);
				JOptionPane.showMessageDialog(this,
						"Bạn đã xóa nhà cung cấp " + sup.getSup_name() + " khỏi danh sách.");
			}
		}
	}

	public void bteditaction() {
		int i = table.getSelectedRow();
		if (i >= 0) {
			if (!this.checkValue())
				return;
			Supplier sup = this.getData();
			int a = JOptionPane.showConfirmDialog(this,
					"Bạn chắc là muốn sửa thông tin nhà cung cấp với mã: " + sup.getSup_id() + ".", "Sửa",
					JOptionPane.YES_NO_OPTION);
			if (a == 0) {
				bus.update(sup);
				JOptionPane.showMessageDialog(this,
						"Bạn đã sửa thông tin nhà cung cấp với mã" + sup.getSup_id() + " thành công.");
			}
		}
	}

	public void btresetaction() {
		txid.setText("");
		txname.setText("");
		txphone.setText("");
		txaddress.setText("");
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
			Supplier sup = bus.searchByID(text, this.authority);
			if (sup != null) {
				Object[] row = { sup.getSup_id(), sup.getSup_name(), sup.getPhone(), sup.getAddress() };
				md.addRow(row);
			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả nào! ");
			}
			table.setModel(md);
			return;
		} else {
			ArrayList<Supplier> ab = new ArrayList<Supplier>();
			if (rbname.isSelected()) {
				ab = bus.searchByName(text, this.authority);
			} else if (rbphone.isSelected()) {
				ab = bus.searchByPhone(text, this.authority);
			} else if (rbaddress.isSelected()) {
				ab = bus.searchByAddress(text, this.authority);
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
		ArrayList<Supplier> ab = bus.sort(list, type, side, authority);
		this.loadData(ab);
	}

	public void btrecoveraction() {
		int i = table.getSelectedRow();
		if (i >= 0) {
			Supplier sup = this.getData();
			int a = JOptionPane.showConfirmDialog(this,
					"Bạn chắc là muốn khôi phục nhà cung cấp với mã: " + sup.getSup_id() + ".", "Khôi phục",
					JOptionPane.YES_NO_OPTION);
			if (a == 0)
				bus.active(sup.getSup_id());
		}
	}
	public void btimportAction() {
		dialogexcel = new ReadExcelDialog("supplier");
		this.dialogexcel.setLocationRelativeTo(null);
		this.dialogexcel.setModal(true);
		this.dialogexcel.setVisible(true);
		int select = dialogexcel.getConfirm();
		if (select == 0)
			return;
		else if (select == 1) {
			ArrayList<Supplier> arr = excel.readSupplier(this.dialogexcel.getLink());
			if(arr == null) return ;	
			for (Supplier p : arr) {
				bus.update(p);
			}
			JOptionPane.showMessageDialog(this, "Đã cập nhật lại dữ liệu thành công", "Cập nhật dữ liệu",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void btexportAction() {
		int a=JOptionPane.showConfirmDialog(this,"Bạn có muốn ghi dữ liệu vào file excel?","Ghi file excel",JOptionPane.YES_NO_OPTION);
		if(a==0) {
			excel.writeSupplier();
			JOptionPane.showMessageDialog(this, "Đã ghi dữ liệu vào file excel thành công!");
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
		this.setFixColSize(col, 1, 150);
		this.setFixColSize(col, 2, 120);
		this.setFixColSize(col, 3, 140);
		if (this.authority == true)
			this.setFixColSize(col, 4, 20);

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
			txaddress.setText(table.getModel().getValueAt(i, 3).toString());
			if (authority == true)
				if (table.getModel().getValueAt(i, 4).toString().equals("x")) {
					txname.setEditable(false);
					txphone.setEditable(false);
					txaddress.setEditable(false);
					btrecover.setEnabled(true);
					btdelete.setEnabled(false);
					btedit.setEnabled(false);
				} else {
					txname.setEditable(true);
					txphone.setEditable(true);
					txaddress.setEditable(true);
					btrecover.setEnabled(false);
					btdelete.setEnabled(true);
					btedit.setEnabled(true);
				}
		}
	}
}
