package gui;

import java.awt.Color;
import java.awt.Font;
import java.text.Normalizer;
import java.awt.event.*;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import bus.EmployerBUS;
import dto.Employer;
import dto.Ingredient;
import bus.IngredientBUS;
import bus.Excel;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

public class IngredientGUI extends JPanel implements ActionListener {

	ArrayList<Ingredient> list;
	AddIngDialog dialog;
	IngredientBUS bus = new IngredientBUS();
	private JPanel pninfo = new JPanel();
	private boolean authority = false;
	JLabel lbid = new JLabel("Mã nguyên liệu: ");
	JLabel lbname = new JLabel("Tên nguyên liệu: ");
	JLabel lbprice = new JLabel("Đơn giá:");
	JLabel lbamount = new JLabel("Số lượng:");
	JLabel lbmeasure = new JLabel("Đơn vị:");
	private JTextField txid = new JTextField();
	private JTextField txname = new JTextField();
	private JTextField txprice = new JTextField();
	private JTextField txamount = new JTextField();
	private JTextField txmeasure = new JTextField();

	JPanel pnbutton = new JPanel();
	JButton btinsert = new JButton("THÊM");
	JButton btdelete = new JButton("XÓA");
	JButton btedit = new JButton("SỬA");
	JButton btreset = new JButton("XÓA HIỂN THỊ");

	JPanel pnsearch = new JPanel();
	private JTextField txsearch = new JTextField();
	JButton btsearch = new JButton("TÌM");
	private JTextField txfrom = new JTextField();
	private JTextField txto = new JTextField();

	JPanel pnsort = new JPanel();
	JComboBox cbsorttype = new JComboBox();
	JLabel lbtype = new JLabel("Theo:");
	JLabel lblThT = new JLabel("Thứ tự:");
	JComboBox cbside = new JComboBox();
	JButton btsort = new JButton("SẮP XẾP");

	JPanel pntable = new JPanel();
	Vector<String> header = new Vector<String>();
	JTable table = new myTable();
	private JButton btrecover = new JButton("KHÔI PHỤC");
	private JRadioButton rbid = new JRadioButton("Mã");
	private JRadioButton rbname = new JRadioButton("Tên");
	private JRadioButton rbmeasure = new JRadioButton("Đơn vị");
	private final JButton btimport = new JButton();
	private final JButton btexport = new JButton();

	public IngredientGUI(String id) {
		Icon icon = new Icon();
		EmployerBUS emp1 = new EmployerBUS();
		Employer emp2 = emp1.searchByID(id);
		this.authority = emp2.isEmp_type();

		this.setSize(1000, 690);
		this.setLayout(null);
		this.setBackground(icon.getContentColor());

		this.pnbutton.setBackground(icon.getPaneColor());
		this.pninfo.setBackground(icon.getPaneColor());
		this.pnsearch.setBackground(icon.getPaneColor());
		this.pnsort.setBackground(icon.getPaneColor());
		this.pntable.setBackground(new Color(152, 209, 203));
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
		rbid.setBackground(icon.getPaneColor());
		rbname.setBackground(icon.getPaneColor());
		rbmeasure.setBackground(icon.getPaneColor());

		pninfo.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng tin nguy\u00EAn li\u1EC7u",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pninfo.setBounds(10, 5, 584, 148);
		add(pninfo);
		pninfo.setLayout(null);
		lbid.setHorizontalAlignment(SwingConstants.CENTER);

		lbid.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbid.setSize(110, 25);
		lbid.setLocation(25, 30);
		pninfo.add(lbid);

		txid.setEditable(false);
		txid.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txid.setBounds(134, 30, 117, 30);
		pninfo.add(txid);
		txid.setColumns(10);
		lbname.setHorizontalAlignment(SwingConstants.CENTER);

		lbname.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbname.setBounds(25, 70, 110, 25);
		pninfo.add(lbname);
		txname.setToolTipText("Tên của nguyên liệu(^_^)");

		txname.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txname.setBounds(134, 70, 170, 30);
		pninfo.add(txname);
		txname.setColumns(10);
		lbprice.setHorizontalAlignment(SwingConstants.CENTER);

		lbprice.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbprice.setBounds(25, 110, 110, 25);
		pninfo.add(lbprice);
		txprice.setToolTipText("Số nguyên, đơn vị là VNĐ (^_^)");

		txprice.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txprice.setColumns(10);
		txprice.setBounds(134, 110, 170, 30);
		pninfo.add(txprice);
		lbamount.setHorizontalAlignment(SwingConstants.CENTER);

		lbamount.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbamount.setBounds(330, 30, 75, 25);
		pninfo.add(lbamount);
		txamount.setToolTipText("Cái này chỉ có thể nhập vào để tăng số lượng thui.");
		txamount.setFont(new Font("sans-serif", Font.PLAIN, 14));

		txamount.setBounds(403, 30, 170, 30);
		pninfo.add(txamount);
		txamount.setColumns(10);
		txamount.setEditable(false);
		lbmeasure.setHorizontalAlignment(SwingConstants.CENTER);

		lbmeasure.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbmeasure.setBounds(330, 70, 75, 25);
		pninfo.add(lbmeasure);
		txmeasure.setToolTipText("Phải là dạng đơn vị đo lường nha(^_^)");
		txmeasure.setFont(new Font("sans-serif", Font.PLAIN, 14));

		txmeasure.setBounds(403, 70, 170, 30);
		pninfo.add(txmeasure);
		txmeasure.setColumns(10);
		btreset.setBounds(403, 110, 146, 25);
		pninfo.add(btreset);
		btreset.setFont(new Font("sans-serif", Font.BOLD, 14));

		pnsearch.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "T\u00ECm ki\u1EBFm nguy\u00EAn li\u1EC7u",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnsearch.setBounds(10, 155, 370, 122);
		add(pnsearch);
		pnsearch.setLayout(null);
		txsearch.setToolTipText("Tên nguyên liệu bạn cần tìm.");

		txsearch.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txsearch.setBounds(39, 20, 180, 30);
		pnsearch.add(txsearch);
		txsearch.setColumns(10);

		btsearch.setFont(new Font("sans-serif", Font.BOLD, 14));
		btsearch.setBounds(229, 20, 131, 30);
		pnsearch.add(btsearch);

		JLabel lbfrom = new JLabel("Giá: Từ:");
		lbfrom.setHorizontalAlignment(SwingConstants.CENTER);
		lbfrom.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbfrom.setBounds(23, 82, 65, 25);
		pnsearch.add(lbfrom);
		txfrom.setToolTipText("Chỉ nhận giá trị tìm kiếm là số");

		txfrom.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txfrom.setBounds(94, 82, 100, 30);
		pnsearch.add(txfrom);
		txfrom.setColumns(10);

		JLabel lbto = new JLabel("Đến:");
		lbto.setHorizontalAlignment(SwingConstants.CENTER);
		lbto.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbto.setBounds(214, 82, 46, 25);
		pnsearch.add(lbto);
		txto.setToolTipText("Chỉ nhận giá trị tìm kiếm là số");

		txto.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txto.setBounds(260, 82, 100, 30);
		pnsearch.add(txto);
		txto.setColumns(10);

		ButtonGroup group = new ButtonGroup();
		group.add(rbid);
		group.add(rbmeasure);
		group.add(rbname);
		rbname.setSelected(true);

		rbid.setFont(new Font("sans-serif", Font.PLAIN, 13));
		rbid.setBounds(39, 52, 57, 23);
		pnsearch.add(rbid);
		rbname.setFont(new Font("sans-serif", Font.PLAIN, 13));
		rbname.setBounds(98, 52, 57, 23);
		pnsearch.add(rbname);
		rbmeasure.setFont(new Font("sans-serif", Font.PLAIN, 13));
		rbmeasure.setBounds(157, 52, 70, 23);
		pnsearch.add(rbmeasure);

		pnsort.setBorder(
				new TitledBorder(null, "S\u1EAFp x\u1EBFp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnsort.setBounds(390, 164, 562, 72);
		add(pnsort);
		pnsort.setLayout(null);

		cbsorttype.setFont(new Font("sans-serif", Font.PLAIN, 13));
		cbsorttype.setModel(new DefaultComboBoxModel(new String[] { "Mã", "Tên", "Giá trị", "Đơn vị" }));
		cbsorttype.setBounds(99, 24, 96, 30);
		pnsort.add(cbsorttype);
		lbtype.setHorizontalAlignment(SwingConstants.CENTER);

		lbtype.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbtype.setBounds(22, 26, 67, 25);
		pnsort.add(lbtype);
		lblThT.setHorizontalAlignment(SwingConstants.CENTER);

		lblThT.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lblThT.setBounds(225, 26, 62, 25);
		pnsort.add(lblThT);

		cbside.setModel(new DefaultComboBoxModel(new String[] { "Tăng dần", "Giảm dần" }));
		cbside.setFont(new Font("sans-serif", Font.PLAIN, 13));
		cbside.setBounds(297, 24, 89, 30);
		pnsort.add(cbside);

		btsort.setFont(new Font("sans-serif", Font.BOLD, 14));
		btsort.setBounds(431, 26, 121, 25);
		pnsort.add(btsort);

		pntable.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Danh s\u00E1ch nguy\u00EAn li\u1EC7u",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pntable.setBounds(10, 280, 942, 337);
		add(pntable);

		pnbutton.setBounds(604, 5, 348, 148);
		if (this.authority == false)
			pnbutton.setVisible(false);
		add(pnbutton);

		pnbutton.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Thao t\u00E1c tr\u00EAn nguy\u00EAn li\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnbutton.setLayout(null);
		btinsert.setBounds(16, 21, 152, 25);

		btinsert.setFont(new Font("sans-serif", Font.BOLD, 14));
		pnbutton.add(btinsert);
		btdelete.setBounds(178, 21, 152, 25);

		btdelete.setFont(new Font("sans-serif", Font.BOLD, 14));
		pnbutton.add(btdelete);

		btrecover.setFont(new Font("sans-serif", Font.BOLD, 14));
		btrecover.setBounds(178, 57, 152, 25);
		pnbutton.add(btrecover);
		btedit.setBounds(16, 57, 152, 25);
		pnbutton.add(btedit);

		btedit.setFont(new Font("sans-serif", Font.BOLD, 14));

		btinsert.addActionListener(this);
		btdelete.addActionListener(this);
		btedit.addActionListener(this);
		btreset.addActionListener(this);
		btsort.addActionListener(this);
		btsearch.addActionListener(this);
		btrecover.addActionListener(this);
		btimport.addActionListener(this);
		btexport.addActionListener(this);
		// icon button
		btinsert.setIcon(icon.imageIconSize("icons/add.png", 20, 20));
		btdelete.setIcon(icon.imageIconSize("icons/delete.png", 20, 20));
		btedit.setIcon(icon.imageIconSize("icons/edit.png", 20, 20));
		btreset.setIcon(icon.imageIconSize("icons/broom2.png", 20, 20));
		btrecover.setIcon(icon.imageIconSize("icons/return.png", 20, 20));
		btsearch.setIcon(icon.imageIconSize("icons/search_more.png", 20, 20));
		btsort.setIcon(icon.imageIconSize("icons/sort.png", 20, 20));
		
		btimport.setIcon(icon.imageIconSize("icons/import2.png", 40, 40));
		btimport.setBackground(new Color(255, 255, 255));
		btimport.setBorder(null);

		btexport.setIcon(icon.imageIconSize("icons/export2.png", 40, 40));
		btexport.setBackground(new Color(255, 255, 255));
		btexport.setBorder(null);

		header.add("Mã");
		header.add("Tên");
		header.add("Đơn giá");
		header.add("Số lượng");
		header.add("Đơn vị");
		if (this.authority == true)
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
		
		
		
		pntable.setLayout(null);
		pntable.setLayout(new BoxLayout(pntable, BoxLayout.X_AXIS));
		JScrollPane scroll = new JScrollPane(table);
		pntable.add(scroll);
		btimport.setToolTipText("Đọc dữ liệu từ file excel mà bạn chọn.");
		btimport.setBorder(null);
		btimport.setBackground(Color.WHITE);
		btimport.setBounds(862, 237, 40, 40);
		
		add(btimport);
		btexport.setToolTipText("Ghi dữ liệu vào file excel");
		btexport.setBorder(null);
		btexport.setBackground(Color.WHITE);
		btexport.setBounds(912, 237, 40, 40);
		
		add(btexport);
		this.loadData();
		this.loadData();
		if (this.authority == true)
			list = bus.readAll();
		else
			list = bus.read();
		
		if(this.authority==false) {
			btexport.setVisible(false);
			btimport.setVisible(false);
		}

	}

	void loadData() {
		DefaultTableModel md = new DefaultTableModel(header, 0);
		if (authority == false) {
			ArrayList<Ingredient> arr = bus.read();
			for (Ingredient p : arr) {
				Object[] row = { p.getIng_id(), p.getIng_name(), p.getUnit_price(), p.getAmount(), p.getMeasure() };
				md.addRow(row);
			}
		} else {
			ArrayList<Ingredient> arr = bus.readAll();
			for (Ingredient p : arr) {
				if (p.getStatus() == 1) {
					Object[] row = { p.getIng_id(), p.getIng_name(), p.getUnit_price(), p.getAmount(), p.getMeasure(),
							"x" };
					md.addRow(row);
				} else {
					Object[] row = { p.getIng_id(), p.getIng_name(), p.getUnit_price(), p.getAmount(), p.getMeasure(),
							"" };
					md.addRow(row);
				}
			}
		}
		table.setModel(md);
		this.setColSize(table);
	}

	void loadData(ArrayList<Ingredient> arr) {
		DefaultTableModel md = new DefaultTableModel(header, 0);
		if (authority == false) {
			for (Ingredient p : arr) {
				if (p.getStatus() == 1) {
					Object[] row = { p.getIng_id(), p.getIng_name(), p.getUnit_price(), p.getAmount(), p.getMeasure(),
							"x" };
					md.addRow(row);
				} else {
					Object[] row = { p.getIng_id(), p.getIng_name(), p.getUnit_price(), p.getAmount(), p.getMeasure(),
							"" };
					md.addRow(row);
				}
			}
		} else {
			for (Ingredient p : arr) {
				Object[] row = { p.getIng_id(), p.getIng_name(), p.getUnit_price(), p.getAmount(), p.getMeasure(),
						p.getStatus() };
				md.addRow(row);
			}
		}
		if (arr.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy két quả nào!");
		}
		table.setModel(md);
		this.setColSize(table);
	}

	Ingredient getData() {
		try {
			Ingredient ing = new Ingredient(txid.getText(), txname.getText(), Double.parseDouble(txprice.getText()),
					Double.parseDouble(txamount.getText()), txmeasure.getText(), 0);
			return ing;
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Không để trống thông tin.");
		}
		return null;
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
		dialog = new AddIngDialog();
		this.dialog.setLocationRelativeTo(null);
		this.dialog.setModal(true);
		this.dialog.setVisible(true);
		Ingredient ing = this.dialog.geting();
		if (ing != null) {
			String id = "";
			int code = bus.getRow() + 1;
			if (code < 10)
				id = "00" + code;
			else if (code < 100)
				id = "0" + code;
			else if (code < 1000)
				id = "" + code;
			else {
				JOptionPane.showMessageDialog(this, "Hết dung lượng bộ nhớ.");
				return;
			}
			id = "IN" + id;
			ing.setIng_id(id);
			bus.add(ing);
		}
	}

	public void btdeleteaction() {
		String aa = this.txid.getText();
		if (!aa.trim().equals("")) {
			Ingredient ing = this.getData();
			String id = ing.getIng_id();
			int a = JOptionPane.showConfirmDialog(this, "Bạn chắc là muốn xóa nguyên liệu với mã: " + id + ".", "Xóa",
					JOptionPane.YES_NO_OPTION);
			if (a == 0) {
				bus.disable(id);
				JOptionPane.showMessageDialog(this, "Bạn đã xóa nguyên liệu " + ing.getIng_name() + " khỏi danh sách.");
			}
		}
	}

	public void bteditaction() {
		String aa = this.txid.getText();
		if (!aa.trim().equals("")) {
			if (!this.checkValue()) {
				return;
			}
			Ingredient ing = this.getData();
			int a = JOptionPane.showConfirmDialog(this,
					"Bạn chắc là muốn sửa thông tin nguyên liệu với mã: " + ing.getIng_id() + ".", "Sửa",
					JOptionPane.YES_NO_OPTION);
			if (a == 0) {
				bus.update(ing);
				JOptionPane.showMessageDialog(this,
						"Bạn đã sửa thông tin nguyên liệu với mã" + ing.getIng_id() + " thành công.");
			}
		}
	}

	public void btresetaction() {
		txid.setText("");
		txname.setText("");
		txamount.setText("");
		txprice.setText("");
		txmeasure.setText("");
	}

	public void btsearchaction() {
		ArrayList<Ingredient> arr;
		String text = txsearch.getText();
		text = text.trim();
		String mind = txfrom.getText();
		mind = mind.trim();
		String maxd = txto.getText();
		maxd = maxd.trim();
		if (text.equals("") && maxd.equals("") && mind.equals("")) {
			this.loadData();
			if (this.authority == true)
				list = bus.readAll();
			else
				list = bus.read();
			return;
		}
		if (rbid.isSelected()) {
			DefaultTableModel md = new DefaultTableModel(header, 0);
			Ingredient p = bus.searchByID(text, this.authority);
			if (p != null) {
				Object[] row = { p.getIng_id(), p.getIng_name(), p.getUnit_price(), p.getAmount(), p.getMeasure() };
				md.addRow(row);
			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả nào! ");
			}
			table.setModel(md);
			return;
		}
		try {
			int i, j;
			if (!mind.equals(""))
				i = Integer.parseInt(mind);
			else
				i = 0;
			if (!maxd.equals(""))
				j = Integer.parseInt(maxd);
			else
				j = 1000000;
			arr = bus.searchByPrice(i, j, this.authority);
			if (rbname.isSelected()) {
				arr = bus.searchByName(text, arr);
			}
			if (rbmeasure.isSelected()) {
				arr = bus.searchByMeasure(text, arr);
			}
			list = arr;
			this.loadData(arr);
		} catch (NumberFormatException ex) {
//			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Không được nhập chữ vào khung tìm kiếm giá trị!");
		}
	}

	public void btsortaction() {
		String type = this.cbsorttype.getSelectedItem().toString();
		String side = this.cbside.getSelectedItem().toString();
		type = type.toLowerCase();
		side = side.toLowerCase();
		ArrayList<Ingredient> arr = bus.sort(list, type, side, this.authority);
		this.loadData(arr);
	}

	public void btrecoveraction() {
		int i = table.getSelectedRow();
		if (i >= 0) {
			Ingredient ing = this.getData();
			int a = JOptionPane.showConfirmDialog(this,
					"Bạn chắc là muốn khôi phục nguyên liệu với mã: " + ing.getIng_id() + ".", "Khôi phục",
					JOptionPane.YES_NO_OPTION);
			if (a == 0)
				bus.active(ing.getIng_id());
		}
	}

	public void btimportAction() {
		Excel excel = new Excel();
		ReadExcelDialog dialogexcel = new ReadExcelDialog("ingredient");
		dialogexcel.setLocationRelativeTo(null);
		dialogexcel.setModal(true);
		dialogexcel.setVisible(true);
		int select = dialogexcel.getConfirm();
		if (select == 0)
			return;
		else if (select == 1) {
			ArrayList<Ingredient> arr = excel.readIngredient(dialogexcel.getLink());
			if (arr == null)
				return;
			for (Ingredient p : arr) {
				Ingredient temp = bus.searchByID(p.getIng_id());
				if (temp == null)
					bus.add(p);
				else
					bus.update(p);
			}
			JOptionPane.showMessageDialog(this, "Đã cập nhật lại dữ liệu thành công", "Cập nhật dữ liệu",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (select == 2) {
			ArrayList<Ingredient> arr = excel.readIngredient(dialogexcel.getLink());
			if (arr == null)
				return;
			for (Ingredient p : arr) {
				Ingredient prod = bus.searchByID(p.getIng_id());
				if (prod == null)
					bus.add(p);
				else {
					double now = prod.getAmount();
					prod.setAmount(now + p.getAmount());
					bus.update(prod);
				}

			}
			JOptionPane.showMessageDialog(this, "Đã cập nhật lại dữ liệu thành công", "Cập nhật dữ liệu",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void btexportAction() {
		Excel excel = new Excel();
		int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn ghi dữ liệu vào file excel?", "Ghi file excel",
				JOptionPane.YES_NO_OPTION);
		if (a == 0) {
			excel.writeIngredient();
			JOptionPane.showMessageDialog(this, "Đã ghi dữ liệu vào file excel thành công!");
		}
	}

	public boolean checkValue() {
		boolean check = true;
		String error = "";
		String name = txname.getText();
		name = this.covertStringToURL(name);
		String price = txprice.getText();
		String amount = txamount.getText();
		String measure = txmeasure.getText();
		measure = this.covertStringToURL(measure);
		if (!name.matches("^[A-Z]+[a-z]*[0-9]*(\\s+(([A-Z][a-z]*)|([a-z]*)|([0-9]*)))*$")) {
			error += "Tên nguyên liệu viết hoa chữ cái đầu.\n";
			check = false;
		}
		if (!price.matches("^[1-9]\\d*\\.?\\d*$")) {
			error += "Đơn giá phải là số và không có chữ.\n";
			check = false;
		}
		if (!amount.matches("^[1-9]\\d*\\.?\\d*$")) {
			error += "Số lượng phải là số và không có chữ.\n";
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
		this.setFixColSize(col, 1, 220);
		this.setFixColSize(col, 2, 80);
		this.setFixColSize(col, 3, 80);
		this.setFixColSize(col, 4, 80);
		if (this.authority == true)
			this.setFixColSize(col, 5, 20);

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
			txprice.setText(table.getModel().getValueAt(i, 2).toString());
			txamount.setText(table.getModel().getValueAt(i, 3).toString());
			txmeasure.setText(table.getModel().getValueAt(i, 4).toString());
			if (authority == true)
				if (table.getModel().getValueAt(i, 5).toString().equals("x")) {
					txname.setEditable(false);
					txprice.setEditable(false);
					txmeasure.setEditable(false);
					btrecover.setEnabled(true);
					btdelete.setEnabled(false);
					btedit.setEnabled(false);
				} else {
					txname.setEditable(true);
					txprice.setEditable(true);
					txmeasure.setEditable(true);
					btrecover.setEnabled(false);
					btdelete.setEnabled(true);
					btedit.setEnabled(true);
				}
		}
	}
}
