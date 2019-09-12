package gui;

import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Pattern;
import design.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;

import javax.imageio.ImageIO;

import bus.ProductBUS;
import dto.Product;
import bus.EmployerBUS;
import dto.Employer;
import bus.IngredientBUS;
import dto.Ingredient;
import dto.Recipe;
import dto.Supplier;
import bus.RecipeBUS;
import bus.Excel;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.border.EtchedBorder;

public class ProductGUI extends JPanel implements ActionListener {
	ArrayList<Product> list;
	JFileChooser file = new JFileChooser();
	private AddProdDialog dialog;
	private ReadExcelDialog dialogexcel;
	ProductBUS bus = new ProductBUS();
	private boolean authority = false;
	private JPanel pninfo = new JPanel();
	JPanel pnimage = new JPanel();
	JLabel lbid = new JLabel("Mã SP: ");
	JLabel lbname = new JLabel("Tên SP: ");
	JLabel lbprice = new JLabel("Đơn giá:");
	JLabel lbamount = new JLabel("Số lượng:");
	JLabel lbmeasure = new JLabel("Đơn vị:");
	JLabel lbdescrip = new JLabel("Mô tả:");
	private JTextField txid = new JTextField();
	private JTextField txname = new JTextField();
	private JTextField txprice = new JTextField();
	private JTextField txamount = new JTextField();
	private JTextField txmeasure = new JTextField();
	JTextArea txdescription = new JTextArea();

	JPanel pnbutton = new JPanel();
	JButton btinsert = new JButton("THÊM");
	JButton btdelete = new JButton("XÓA");
	JButton btedit = new JButton("SỬA");
	JButton btreset = new JButton("XOÁ HIỂN THỊ");

	JPanel pnsearch = new JPanel();
	JPanel pnsearchprice = new JPanel();
	private JTextField txsearch = new JTextField();
	JButton btsearch = new JButton("TÌM");
	private JTextField txfrom = new JTextField();
	private JTextField txto = new JTextField();
	JComboBox cbsearchtype = new JComboBox();

	JPanel pnsort = new JPanel();
	JComboBox cbsorttype = new JComboBox();
	JLabel lbtype = new JLabel("Theo:");
	JLabel lblThT = new JLabel("Thứ tự:");
	JComboBox cbside = new JComboBox();
	JButton btsort = new JButton("SẮP XẾP");

	JPanel pntable = new JPanel();
	Vector<String> header = new Vector<String>();
	JTable table = new myTable();
	private final JLabel lblNewLabel = new JLabel("Phân loại:");
	private JButton btrecover = new JButton("KHÔI PHỤC");
	private JLabel lbimage;
	private final JButton btload = new JButton("TẢI ẢNH");
	private JButton btplus;
	private JButton btminus;
	private final JButton btimport = new JButton();
	private final JButton btexport = new JButton();
	

	public ProductGUI(String id) {
		Icon icon = new Icon();
		EmployerBUS emp1 = new EmployerBUS();
		Employer emp2 = emp1.searchByID(id);
		this.authority = emp2.isEmp_type();

		this.setSize(1000, 690);
		this.setLayout(null);
		this.setBackground(icon.getContentColor());

		this.pnimage.setBackground(icon.getPaneColor());
		this.pnbutton.setBackground(icon.getPaneColor());
		this.pninfo.setBackground(icon.getPaneColor());
		this.pnsearch.setBackground(icon.getPaneColor());
		this.pnsearchprice.setBackground(icon.getPaneColor());
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

		pninfo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Th\u00F4ng tin s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pninfo.setBounds(10, 5, 764, 225);
		add(pninfo);
		pninfo.setLayout(null);
		lbid.setHorizontalAlignment(SwingConstants.CENTER);

		lbid.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbid.setSize(68, 25);
		lbid.setLocation(227, 50);
		pninfo.add(lbid);

		txid.setEditable(false);
		txid.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txid.setBounds(294, 50, 95, 30);
		pninfo.add(txid);
		txid.setColumns(10);
		lbname.setHorizontalAlignment(SwingConstants.CENTER);

		lbname.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbname.setBounds(227, 85, 68, 25);
		pninfo.add(lbname);
		txname.setToolTipText("Tên của sản phẩm (^_^)");

		txname.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txname.setBounds(294, 86, 170, 30);
		pninfo.add(txname);
		txname.setColumns(10);
		lbprice.setHorizontalAlignment(SwingConstants.CENTER);

		lbprice.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbprice.setBounds(227, 120, 68, 25);
		pninfo.add(lbprice);
		txprice.setToolTipText("Số nguyên nha, đơn vị VNĐ(^_^)");

		txprice.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txprice.setColumns(10);
		txprice.setBounds(294, 121, 170, 30);
		pninfo.add(txprice);
		lbamount.setHorizontalAlignment(SwingConstants.CENTER);

		lbamount.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbamount.setBounds(478, 50, 75, 25);
		pninfo.add(lbamount);
		txamount.setToolTipText("Chỉ những món có công thức thì mới được điều chỉnh số lượng.");
		txamount.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txamount.setEditable(false);

		txamount.setBounds(552, 50, 170, 30);
		pninfo.add(txamount);
		txamount.setColumns(10);
		lbmeasure.setHorizontalAlignment(SwingConstants.CENTER);

		lbmeasure.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbmeasure.setBounds(474, 85, 75, 25);
		pninfo.add(lbmeasure);
		txmeasure.setToolTipText("Là đơn vị đo đếm nha (^_^)");
		txmeasure.setFont(new Font("sans-serif", Font.PLAIN, 14));

		txmeasure.setBounds(552, 85, 170, 30);
		pninfo.add(txmeasure);
		txmeasure.setColumns(10);
		lbdescrip.setHorizontalAlignment(SwingConstants.CENTER);

		lbdescrip.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbdescrip.setBounds(474, 120, 75, 25);
		pninfo.add(lbdescrip);
		JScrollPane scroll1 = new JScrollPane();
		scroll1.setBounds(552, 119, 170, 59);
		pninfo.add(scroll1);
		scroll1.setViewportView(txdescription);
		txdescription.setToolTipText("Thích gì viết đó");
		
				txdescription.setLineWrap(true);
				txdescription.setWrapStyleWord(true);

		pnimage.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "H\u00ECnh \u1EA3nh", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnimage.setBounds(27, 24, 190, 190);
		pninfo.add(pnimage);
		pnimage.setLayout(new GridLayout(1, 1, 0, 0));

		lbimage = new JLabel("");
		pnimage.add(lbimage);
		btload.setFont(new Font("sans-serif", Font.PLAIN, 13));
		btload.setBounds(225, 190, 102, 30);

		pninfo.add(btload);
		btreset.setBounds(552, 189, 135, 30);
		pninfo.add(btreset);
		btreset.setFont(new Font("sans-serif", Font.BOLD, 14));
		btreset.addActionListener(this);

		pnsearch.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "T\u00ECm ki\u1EBFm s\u1EA3n ph\u1EA9m",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnsearch.setBounds(10, 236, 407, 138);
		add(pnsearch);
		pnsearch.setLayout(null);
		txsearch.setToolTipText("Tên sản phẩm bạn muốn tìm");

		txsearch.setFont(new Font("sans-serif", Font.PLAIN, 14));
		txsearch.setBounds(22, 20, 267, 26);
		pnsearch.add(txsearch);
		txsearch.setColumns(10);

		btsearch.setFont(new Font("sans-serif", Font.BOLD, 14));
		btsearch.setBounds(299, 20, 90, 25);
		pnsearch.add(btsearch);

		cbsearchtype.setFont(new Font("sans-serif", Font.PLAIN, 14));
		cbsearchtype.setModel(new DefaultComboBoxModel(
				new String[] { "Tất cả", "Cà phê", "Sinh Tố", "Nước ngọt có ga", "Rượu", "Bia", "Trà" }));
		cbsearchtype.setBounds(189, 49, 200, 25);
		pnsearch.add(cbsearchtype);
		lblNewLabel.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lblNewLabel.setBounds(43, 49, 80, 25);

		pnsearch.add(lblNewLabel);

		pnsort.setBorder(
				new TitledBorder(null, "S\u1EAFp x\u1EBFp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnsort.setBounds(540, 236, 234, 138);
		add(pnsort);
		pnsort.setLayout(null);

		cbsorttype.setFont(new Font("sans-serif", Font.PLAIN, 13));
		cbsorttype.setModel(new DefaultComboBoxModel(new String[] { "Mã", "Tên", "Đơn giá" }));
		cbsorttype.setBounds(111, 30, 96, 25);
		pnsort.add(cbsorttype);

		lbtype.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lbtype.setBounds(32, 30, 69, 25);
		pnsort.add(lbtype);

		lblThT.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lblThT.setBounds(32, 66, 69, 25);
		pnsort.add(lblThT);

		cbside.setModel(new DefaultComboBoxModel(new String[] { "Tăng dần", "Giảm dần" }));
		cbside.setFont(new Font("sans-serif", Font.PLAIN, 13));
		cbside.setBounds(111, 66, 96, 25);
		pnsort.add(cbside);

		btsort.setFont(new Font("sans-serif", Font.BOLD, 14));
		btsort.setBounds(58, 102, 121, 25);
		pnsort.add(btsort);

		pntable.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Danh s\u00E1ch s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pntable.setBounds(10, 385, 939, 238);
		add(pntable);

		pnbutton.setBounds(784, 5, 165, 225);
		if (this.authority == false) {
			pnbutton.setVisible(false);
			this.btload.setEnabled(false);
		}
		add(pnbutton);

		pnbutton.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thao t\u00E1c",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnbutton.setLayout(new GridLayout(0, 1, 0, 5));

		btinsert.setFont(new Font("Dialog", Font.BOLD, 14));
		pnbutton.add(btinsert);

		btdelete.setFont(new Font("Dialog", Font.BOLD, 14));
		pnbutton.add(btdelete);

		btedit.setFont(new Font("sans-serif", Font.BOLD, 14));
		pnbutton.add(btedit);

		btrecover.setFont(new Font("sans-serif", Font.BOLD, 14));
		pnbutton.add(btrecover);

		this.lbimage.setBackground(icon.getPaneColor());

		btplus = new JButton("");
		btplus.setBounds(628, 11, 30, 30);
		pninfo.add(btplus);
		if (this.authority == false)
			btplus.setVisible(false);

		btminus = new JButton("");
		btminus.setBounds(672, 11, 30, 30);
		pninfo.add(btminus);
		if (this.authority == false)
			btminus.setVisible(false);

		btinsert.addActionListener(this);
		btdelete.addActionListener(this);
		btedit.addActionListener(this);
		btsort.addActionListener(this);
		btsearch.addActionListener(this);
		btrecover.addActionListener(this);
		btload.addActionListener(this);
		btplus.addActionListener(this);
		btminus.addActionListener(this);
		btimport.addActionListener(this);
		btexport.addActionListener(this);

		// icon button
		btinsert.setIcon(icon.imageIconSize("icons/add.png", 20, 20));
		btdelete.setIcon(icon.imageIconSize("icons/delete.png", 20, 20));
		btedit.setIcon(icon.imageIconSize("icons/edit.png", 20, 20));
		btreset.setIcon(icon.imageIconSize("icons/broom2.png", 20, 20));
		btrecover.setIcon(icon.imageIconSize("icons/return.png", 20, 20));
		btsearch.setIcon(icon.imageIconSize("icons/search_more.png", 20, 20));
		
		
		pnsearchprice.setBorder(new TitledBorder(null, "Gi\u00E1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnsearchprice.setBounds(22, 78, 367, 49);
		pnsearch.add(pnsearchprice);
		pnsearchprice.setLayout(null);
		txfrom.setBounds(64, 11, 100, 26);
		pnsearchprice.add(txfrom);
		txfrom.setToolTipText("Chỉ nhận giá trị tìm kiếm là số");
		
				txfrom.setFont(new Font("sans-serif", Font.PLAIN, 14));
				txfrom.setColumns(10);
				txto.setBounds(256, 11, 100, 26);
				pnsearchprice.add(txto);
				txto.setToolTipText("Chỉ nhận giá trị tìm kiếm là số");
				
						txto.setFont(new Font("sans-serif", Font.PLAIN, 14));
						txto.setColumns(10);
						
								JLabel lbto = new JLabel("Đến:");
								lbto.setBounds(200, 11, 46, 25);
								pnsearchprice.add(lbto);
								lbto.setHorizontalAlignment(SwingConstants.CENTER);
								lbto.setFont(new Font("sans-serif", Font.PLAIN, 14));
								
										JLabel lbfrom = new JLabel("Từ:");
										lbfrom.setBounds(10, 11, 57, 25);
										pnsearchprice.add(lbfrom);
										lbfrom.setHorizontalAlignment(SwingConstants.CENTER);
										lbfrom.setFont(new Font("sans-serif", Font.PLAIN, 14));
		btsort.setIcon(icon.imageIconSize("icons/sort.png", 20, 20));
		btload.setIcon(icon.imageIconSize("icons/image.png", 20, 20));
		btplus.setIcon(icon.imageIconSize("icons/plus.png", 15, 15));
		btminus.setIcon(icon.imageIconSize("icons/minus.png", 15, 15));

		header.add("Mã");
		header.add("Tên");
		header.add("Đơn giá");
		header.add("Số lượng");
		header.add("Đơn vị");
		header.add("Mô tả");
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
		btimport.setBounds(859, 334, 40, 40);
		add(btimport);
		btexport.setToolTipText("Ghi dữ liệu vào file excel");
		btexport.setBorder(null);
		btexport.setBackground(Color.WHITE);
		btexport.setBounds(909, 334, 40, 40);
		add(btexport);

		btimport.setIcon(icon.imageIconSize("icons/import2.png", 40, 40));
		btimport.setBackground(new Color(255, 255, 255));
		btimport.setBorder(null);

		btexport.setIcon(icon.imageIconSize("icons/export2.png", 40, 40));
		btexport.setBackground(new Color(255, 255, 255));
		btexport.setBorder(null);

		this.loadData();
		this.loadData();
		if (this.authority == true)
			list = bus.readAll();
		else
			list = bus.read();
		
		if(!this.authority) {
			btimport.setVisible(false);
			btexport.setVisible(false);
		}
	}

	void loadData() {
		DefaultTableModel md = new DefaultTableModel(header, 0);
		if (authority == false) {
			ArrayList<Product> arr = bus.read();
			for (Product p : arr) {
				Object[] row = { p.getProd_id(), p.getProd_name(), p.getUnit_price(), p.getAmount(), p.getMeasure(),
						p.getDescription() };
				md.addRow(row);
			}
		} else {
			ArrayList<Product> arr = bus.readAll();
			for (Product p : arr) {
				if (p.getStatus() == 1) {
					Object[] row = { p.getProd_id(), p.getProd_name(), p.getUnit_price(), p.getAmount(), p.getMeasure(),
							p.getDescription(), "x" };
					md.addRow(row);
				} else {
					Object[] row = { p.getProd_id(), p.getProd_name(), p.getUnit_price(), p.getAmount(), p.getMeasure(),
							p.getDescription(), "" };
					md.addRow(row);
				}
			}
		}
		table.setModel(md);
		this.setColSize(table);
	}

	void loadData(ArrayList<Product> arr) {
		DefaultTableModel md = new DefaultTableModel(header, 0);
		if (authority == false) {
			for (Product p : arr) {
				Object[] row = { p.getProd_id(), p.getProd_name(), p.getUnit_price(), p.getAmount(), p.getMeasure(),
						p.getDescription() };
				md.addRow(row);
			}
		} else {
			for (Product p : arr) {
				if (p.getStatus() == 1) {
					Object[] row = { p.getProd_id(), p.getProd_name(), p.getUnit_price(), p.getAmount(), p.getMeasure(),
							p.getDescription(), "x" };
					md.addRow(row);
				} else {
					Object[] row = { p.getProd_id(), p.getProd_name(), p.getUnit_price(), p.getAmount(), p.getMeasure(),
							p.getDescription(), "" };
					md.addRow(row);
				}
			}
		}
		if (arr.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả nào! ");
		}
		table.setModel(md);
		this.setColSize(table);
	}

	String getIdType(String type) {
		switch (type) {
		case "Cà phê":
			return "CF";
//		case "Nguyên liệu":return "IN";
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

	Product getData() {
		try {
			Product pro = new Product(txid.getText(), txname.getText(), Double.parseDouble(txprice.getText()),
					Integer.parseInt(txamount.getText()), txmeasure.getText(), 0, txdescription.getText(), "");
			return pro;
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
		} else if (source == this.btload) {
			this.btloadaction();
		} else if (source == this.btplus) {
			this.btplusAction();
		} else if (source == this.btminus) {
			this.btminusAction();
		} else if (source == this.btexport) {
			btexportAction();
		} else if (source == this.btimport) {
			btimportAction();
			this.loadData();
		}
	}

	public void btinsertaction() {
		dialog = new AddProdDialog();
		this.dialog.setLocationRelativeTo(null);
		this.dialog.setModal(true);
		this.dialog.setVisible(true);
		Product prod = this.dialog.getProd();
		if (prod != null) {
			bus.add(prod);
		}
	}

	public void btdeleteaction() {
		int i = table.getSelectedRow();
		if (i >= 0) {
			Product pro = this.getData();
			String id = pro.getProd_id();
			int a = JOptionPane.showConfirmDialog(this, "Bạn chắc là muốn xóa sản phẩm với mã: " + id + ".", "Xóa",
					JOptionPane.YES_NO_OPTION);
			if (a == 0) {
				bus.disable(id);
				JOptionPane.showMessageDialog(this, "Bạn đã xóa sản phẩm " + pro.getProd_name() + " khỏi danh sách.");
			}
		}
	}

	public void bteditaction() {
		int i = table.getSelectedRow();
		if (i >= 0) {
			if (!this.checkValue()) {
				return;
			}
			Product pro = this.getData();
			Product temp = bus.searchByID(table.getModel().getValueAt(i, 0).toString(), this.authority);
			int a = JOptionPane.showConfirmDialog(this,
					"Bạn chắc là muốn sửa thông tin sản phẩm với mã: " + pro.getProd_id() + ".", "Sửa",
					JOptionPane.YES_NO_OPTION);
			if (a == 0) {
				String filePath = "", imageName = "";
				if (file.getSelectedFile() != null) {
					filePath = file.getSelectedFile().toString();
					imageName = pro.getProd_id() + ".jpg";
					this.saveImage(filePath, imageName);
					pro.setImg("images/" + imageName);
				} else
					pro.setImg(temp.getImg());
				if (this.calculateAmount(pro.getProd_id(), temp.getAmount(), pro.getAmount())) {
					bus.update(pro);
					JOptionPane.showMessageDialog(this,
							"Bạn đã sửa thông tin sản phẩm với mã " + pro.getProd_id() + " thành công.");
				}
			}
		}
	}

	public boolean calculateAmount(String id, int oldAmount, int newAmount) {
		boolean check = true;
		IngredientBUS busIng = new IngredientBUS();
		RecipeBUS busRep = new RecipeBUS();
		if (newAmount == oldAmount)
			return true;
		if (newAmount > oldAmount) {
			String error = "";
			int amount = newAmount - oldAmount;
			ArrayList<Recipe> arrRep = busRep.searchByProductID(id);
			for (Recipe p : arrRep) {
				Ingredient ing = busIng.searchByID(p.getIng_id());
				if (ing.getAmount() < amount * p.getAmount()) {
					check = false;
					error += ing.getIng_name() + ", ";
				}
			}
			if (check == false) {
				if (error.length() > 0) {
					int length = error.length();
					error = error.substring(0, length - 2);
				}
				JOptionPane.showMessageDialog(this,
						"Lượng tồn của " + error + " trong kho không để tăng thêm " + amount + " món!");
				return false;
			} else {
				for (Recipe p : arrRep) {
					Ingredient ing = busIng.searchByID(p.getIng_id());
					double now = ing.getAmount();
					ing.setAmount(now - amount * p.getAmount());
					busIng.update(ing);
				}
				return true;
			}
		} else {
			int amount = oldAmount - newAmount;
			ArrayList<Recipe> arrRep = busRep.searchByProductID(id);
			for (Recipe p : arrRep) {
				Ingredient ing = busIng.searchByID(p.getIng_id());
				double now = ing.getAmount();
				ing.setAmount(now + amount * p.getAmount());
				busIng.update(ing);
			}
			return true;
		}
	}

	public void btresetaction() {
		txid.setText("");
		txname.setText("");
		txamount.setText("");
		txprice.setText("");
		txmeasure.setText("");
		txdescription.setText("");
		lbimage.setIcon(null);
	}

	public void btsearchaction() {
		ArrayList<Product> arr;
		String text = txsearch.getText();
		text = text.trim();
		String mind = txfrom.getText();
		mind = mind.trim();
		String maxd = txto.getText();
		maxd = maxd.trim();
		String type = cbsearchtype.getSelectedItem().toString();
		if (text.equals("") && maxd.equals("") && mind.equals("") && type.equals("Tất cả")) {
			this.loadData();
			if (this.authority == true)
				list = bus.readAll();
			else
				list = bus.read();
			return;
		} else {
			type = this.getIdType(type);
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
				arr = bus.search(this.authority, text, i, j, type);
				list = arr;
				this.loadData(arr);
			} catch (NumberFormatException ex) {
//				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Không được nhập chữ vào khung tìm kiếm giá trị!");
			}
		}
	}

	public void btsortaction() {
		String type = this.cbsorttype.getSelectedItem().toString();
		String side = this.cbside.getSelectedItem().toString();
		type = type.toLowerCase();
		side = side.toLowerCase();
		ArrayList<Product> arr = bus.sort(list, type, side, this.authority);
		this.loadData(arr);
	}
	public void searchAndSort() {
		ArrayList<Product> arr;
		String type = this.cbsorttype.getSelectedItem().toString();
		String side = this.cbside.getSelectedItem().toString();
		type = type.toLowerCase();
		side = side.toLowerCase();
		
		String text = txsearch.getText();
		text = text.trim();
		String mind = txfrom.getText();
		mind = mind.trim();
		String maxd = txto.getText();
		maxd = maxd.trim();
		String prod_type = cbsearchtype.getSelectedItem().toString();
		if (text.equals("") && maxd.equals("") && mind.equals("") && prod_type.equals("Tất cả")) {
			if (this.authority == true)
				arr = bus.readAll();
			else
				arr = bus.read();
			arr = bus.sort(arr, type, side, this.authority);
			return;
		} else {
			prod_type = this.getIdType(prod_type);
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
				arr = bus.search(this.authority, text, i, j, prod_type);
				arr = bus.sort(arr, type, side, this.authority);
			} catch (NumberFormatException ex) {
//				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Không được nhập chữ vào khung tìm kiếm giá trị!");
			}
		}
		
		
	}
	public void btrecoveraction() {
		int i = table.getSelectedRow();
		if (i >= 0) {
			Product cus = this.getData();
			int a = JOptionPane.showConfirmDialog(this,
					"Bạn chắc là muốn khôi phục sản phẩm với mã: " + cus.getProd_id() + ".", "Khôi phục",
					JOptionPane.YES_NO_OPTION);
			if (a == 0)
				bus.active(cus.getProd_id());
		}
	}

	public void btloadaction() {
		int i = table.getSelectedRow();
		if (i >= 0 && this.authority == true) {
			String status = table.getModel().getValueAt(i, 6).toString();
			if (status.equals("1"))
				return;
		}
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

	public void btplusAction() {
		String text = txamount.getText().trim();
		if (text.equals(""))
			return;
		int a = Integer.parseInt(text);
		txamount.setText((a + 1) + "");
	}

	public void btminusAction() {
		String text = txamount.getText().trim();
		if (text.equals(""))
			return;
		int a = Integer.parseInt(text);
		if (a - 1 >= 0)
			txamount.setText((a - 1) + "");
		else
			txamount.setText(0 + "");
	}

	public void btimportAction() {
		Excel excel = new Excel();
		dialogexcel = new ReadExcelDialog("product");
		this.dialogexcel.setLocationRelativeTo(null);
		this.dialogexcel.setModal(true);
		this.dialogexcel.setVisible(true);
		int select = dialogexcel.getConfirm();
		if (select == 0)
			return;
		else if (select == 1) {
			ArrayList<Product> arr = excel.readProduct(this.dialogexcel.getLink());
			if (arr == null)
				return;
			for (Product p : arr) {
				bus.update(p);
			}
			JOptionPane.showMessageDialog(this, "Đã cập nhật lại dữ liệu thành công", "Cập nhật dữ liệu",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (select == 2) {
			ArrayList<Product> arr = excel.readProduct(this.dialogexcel.getLink());
			if (arr == null)
				return;
			for (Product p : arr) {
				Product prod = bus.searchByID(p.getProd_id());
				int now = prod.getAmount();
				prod.setAmount(now + p.getAmount());
				bus.update(prod);
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
			excel.writeProduct();
			JOptionPane.showMessageDialog(this, "Đã ghi dữ liệu vào file excel thành công!");
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
			error += "Tên sản phẩm chữ đầu tiên phải viết hoa.\n";
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
		if (!measure.matches("^([A-Z][a-z]*\\s*)+$")) {
			error += "Đơn vị là chữ viết hoa chữ đầu.\n";
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
		this.setFixColSize(col, 1, 160);
		this.setFixColSize(col, 2, 60);
		this.setFixColSize(col, 3, 60);
		this.setFixColSize(col, 4, 60);
		this.setFixColSize(col, 5, 260);
		if (this.authority == true)
			this.setFixColSize(col, 6, 20);

	}

	public void setFixColSize(TableColumnModel col, int index, int size) {
		col.getColumn(index).setMinWidth(size);
		// col.getColumn(index).setResizable(false);
	}

	public void selectRowTable() {
		int i = table.getSelectedRow();
		if (i >= 0) {
			Product pro = bus.searchByID(table.getModel().getValueAt(i, 0).toString(), authority);
			txid.setText(pro.getProd_id());
			txname.setText(pro.getProd_name());
			txprice.setText(pro.getUnit_price() + "");
			txamount.setText(pro.getAmount() + "");
			txmeasure.setText(pro.getMeasure());
			txdescription.setText(pro.getDescription());
			// image
			loadImage(pro.getImg());
			if (bus.hasRep(pro.getProd_id())) {
				btplus.setEnabled(true);
				btminus.setEnabled(true);
			} else {
				btplus.setEnabled(false);
				btminus.setEnabled(false);
			}
			if (authority == true)
				if (pro.getStatus() == 1) {
					txname.setEditable(false);
					txprice.setEditable(false);
					txmeasure.setEditable(false);
					txdescription.setEditable(false);
					btrecover.setEnabled(true);
					btdelete.setEnabled(false);
					btedit.setEnabled(false);
				} else {
					txname.setEditable(true);
					txprice.setEditable(true);
					txmeasure.setEditable(true);
					txdescription.setEditable(true);
					btrecover.setEnabled(false);
					btdelete.setEnabled(true);
					btedit.setEnabled(true);
				}
		}
	}
}
