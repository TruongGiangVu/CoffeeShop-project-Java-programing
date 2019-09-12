package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import design.*;
import bus.EmployerBUS;
import bus.ProductBUS;
import dto.Employer;
import dto.Product;
import bus.IngredientBUS;
import dto.Ingredient;
import bus.RecipeBUS;
import dto.Recipe;
import gui.ReadExcelDialog;
import bus.Excel;

import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

public class RecipeGUI extends JPanel implements ActionListener {

	ArrayList<Product> listProduct;
	AddRepDialog dialog;
	ProductBUS busProduct = new ProductBUS();
	RecipeBUS busRecipe = new RecipeBUS();
	IngredientBUS busIngredient = new IngredientBUS();
	private boolean authority = false;
	private JPanel pnsearch;
	private JTextField txsearch;
	private JButton btsearch;
	private JLabel lblNewLabel;
	private JComboBox cbtype;
	private JPanel pnsort;
	private JLabel lblSpXpTheo;
	private JComboBox cbcolunm;
	private JLabel label_1;
	private JComboBox cbside;
	private JButton btsort;
	private JPanel pnproduct;
	private JPanel pninfoProduct;
	private JLabel lblMSnPhm;
	private JTextField txproid;
	private JTextField txproname;
	private JLabel lblTnSnPhm;
	private JButton btinsert;
	private JPanel pnrecipe;
	private JPanel pninfoIngredient;
	private JLabel lblTnNguynLiu;
	private JLabel lblMNguynLiu;
	private JTextField txingid;
	private JTextField txingname;
	private JLabel lblSLng;
	private JTextField txingmeasure;
	private JTextField txingamount;
	private JLabel lblSLng_1;
	private JButton btreset;
	private JPanel pnbutton;
	private JButton btdelete;
	private JButton btedit;
	Vector<String> headerProduct = new Vector<String>();
	private JTable tableProduct = new myTable();
	Vector<String> headerRecipe = new Vector<String>();
	private JTable tableRecipe = new myTable();
	private JButton btimport=new JButton();
	private JButton btexport=new JButton();

	/**
	 * Create the panel.
	 */
	public RecipeGUI(String id) {
		 Icon icon = new Icon();
		EmployerBUS emp1 = new EmployerBUS();
		Employer emp2 = emp1.searchByID(id);
		this.authority = emp2.isEmp_type();
		this.setSize(1000, 690);
		this.setLayout(null);
		this.setBackground(icon.getContentColor());

		pnsearch = new JPanel();
		pnsearch.setBorder(
				new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnsearch.setBounds(10, 5, 510, 49);
		add(pnsearch);
		pnsearch.setLayout(null);

		txsearch = new JTextField();
		txsearch.setToolTipText("Tên sản phẩm cần tìm");
		txsearch.setBounds(10, 15, 162, 30);
		pnsearch.add(txsearch);
		txsearch.setColumns(10);

		btsearch = new JButton("TÌM");
		btsearch.setFont(new Font("sans-serif", Font.BOLD, 14));
		btsearch.setBounds(180, 15, 91, 25);
		pnsearch.add(btsearch);

		lblNewLabel = new JLabel("Phân loại:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("sans-serif", Font.PLAIN, 13));
		lblNewLabel.setBounds(303, 14, 62, 24);
		pnsearch.add(lblNewLabel);

		cbtype = new JComboBox();
		cbtype.setModel(new DefaultComboBoxModel(
				new String[] { "Tất cả", "Cà phê", "Sinh Tố", "Nước ngọt có ga", "Rượu", "Bia", "Trà" }));
		cbtype.setFont(new Font("sans-serif", Font.PLAIN, 13));
		cbtype.setBounds(370, 14, 130, 24);
		pnsearch.add(cbtype);

		pnsort = new JPanel();
		pnsort.setBorder(
				new TitledBorder(null, "S\u1EAFp x\u1EBFp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnsort.setBounds(10, 59, 510, 55);
		add(pnsort);
		pnsort.setLayout(null);

		lblSpXpTheo = new JLabel("Sắp xếp theo:");
		lblSpXpTheo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpXpTheo.setFont(new Font("sans-serif", Font.PLAIN, 14));
		lblSpXpTheo.setBounds(10, 12, 89, 25);
		pnsort.add(lblSpXpTheo);

		cbcolunm = new JComboBox();
		cbcolunm.setModel(new DefaultComboBoxModel(new String[] { "Mã", "Tên", "Giá trị" }));
		cbcolunm.setFont(new Font("sans-serif", Font.PLAIN, 13));
		cbcolunm.setBounds(109, 12, 96, 25);
		pnsort.add(cbcolunm);

		label_1 = new JLabel("Thứ tự:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("sans-serif", Font.PLAIN, 14));
		label_1.setBounds(210, 11, 62, 25);
		pnsort.add(label_1);

		cbside = new JComboBox();
		cbside.setModel(new DefaultComboBoxModel(new String[] { "Tăng dần", "Giảm dần" }));
		cbside.setFont(new Font("sans-serif", Font.PLAIN, 13));
		cbside.setBounds(270, 12, 89, 25);
		pnsort.add(cbside);

		btsort = new JButton("SẮP XẾP");
		btsort.setFont(new Font("sans-serif", Font.BOLD, 14));
		btsort.setBounds(369, 11, 117, 25);
		pnsort.add(btsort);

		pnproduct = new JPanel();
		pnproduct.setBorder(new TitledBorder(null, "Danh s\u00E1ch s\u1EA3n ph\u1EA9m", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnproduct.setBackground(SystemColor.inactiveCaption);
		pnproduct.setForeground(Color.BLACK);
		pnproduct.setBounds(10, 114, 510, 508);
		add(pnproduct);

		pninfoProduct = new JPanel();
		pninfoProduct.setBorder(
				new TitledBorder(null, "S\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pninfoProduct.setBounds(530, 11, 435, 85);
		add(pninfoProduct);
		pninfoProduct.setLayout(null);

		lblMSnPhm = new JLabel("Mã sản phẩm:");
		lblMSnPhm.setHorizontalAlignment(SwingConstants.CENTER);
		lblMSnPhm.setFont(new Font("sans-serif", Font.PLAIN, 13));
		lblMSnPhm.setBounds(10, 14, 94, 24);
		pninfoProduct.add(lblMSnPhm);

		txproid = new JTextField();
		txproid.setFont(new Font("sans-serif", Font.PLAIN, 13));
		txproid.setBounds(110, 15, 137, 30);
		pninfoProduct.add(txproid);
		txproid.setColumns(10);

		txproname = new JTextField();
		txproname.setFont(new Font("sans-serif", Font.PLAIN, 13));
		txproname.setColumns(10);
		txproname.setBounds(110, 50, 137, 30);
		pninfoProduct.add(txproname);

		txproid.setEditable(false);
		txproname.setEditable(false);

		lblTnSnPhm = new JLabel("Tên sản phẩm:");
		lblTnSnPhm.setHorizontalAlignment(SwingConstants.CENTER);
		lblTnSnPhm.setFont(new Font("sans-serif", Font.PLAIN, 13));
		lblTnSnPhm.setBounds(10, 49, 94, 24);
		pninfoProduct.add(lblTnSnPhm);

		btinsert = new JButton("THÊM");
		btinsert.setFont(new Font("Dialog", Font.BOLD, 14));
		btinsert.setBounds(280, 31, 124, 25);
		if (this.authority == false)
			btinsert.setVisible(false);
		pninfoProduct.add(btinsert);

		pnrecipe = new JPanel();
		pnrecipe.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "C\u00F4ng th\u1EE9c",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnrecipe.setBounds(530, 250, 435, 332);
		add(pnrecipe);

		pninfoIngredient = new JPanel();
		pninfoIngredient.setBorder(
				new TitledBorder(null, "Nguy\u00EAn li\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pninfoIngredient.setBounds(528, 107, 435, 132);
		add(pninfoIngredient);
		pninfoIngredient.setLayout(null);

		lblTnNguynLiu = new JLabel("Tên nguyên liệu:");
		lblTnNguynLiu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTnNguynLiu.setFont(new Font("sans-serif", Font.PLAIN, 13));
		lblTnNguynLiu.setBounds(10, 63, 100, 24);
		pninfoIngredient.add(lblTnNguynLiu);

		lblMNguynLiu = new JLabel("Mã nguyên liệu:");
		lblMNguynLiu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMNguynLiu.setFont(new Font("sans-serif", Font.PLAIN, 13));
		lblMNguynLiu.setBounds(10, 27, 100, 24);
		pninfoIngredient.add(lblMNguynLiu);

		txingid = new JTextField();
		txingid.setFont(new Font("sans-serif", Font.PLAIN, 13));
		txingid.setColumns(10);
		txingid.setBounds(110, 28, 100, 30);
		pninfoIngredient.add(txingid);

		txingname = new JTextField();
		txingname.setFont(new Font("sans-serif", Font.PLAIN, 13));
		txingname.setColumns(10);
		txingname.setBounds(110, 63, 137, 30);
		pninfoIngredient.add(txingname);

		lblSLng = new JLabel("Đơn vị:");
		lblSLng.setHorizontalAlignment(SwingConstants.CENTER);
		lblSLng.setFont(new Font("sans-serif", Font.PLAIN, 13));
		lblSLng.setBounds(250, 26, 65, 24);
		pninfoIngredient.add(lblSLng);

		txingmeasure = new JTextField();
		txingmeasure.setFont(new Font("sans-serif", Font.PLAIN, 13));
		txingmeasure.setColumns(10);
		txingmeasure.setBounds(315, 27, 110, 30);
		pninfoIngredient.add(txingmeasure);

		txingamount = new JTextField();
		txingamount.setToolTipText("Số nguyên hoặc số thực, không được chứa chữ");
		txingamount.setFont(new Font("sans-serif", Font.PLAIN, 13));
		txingamount.setColumns(10);
		txingamount.setBounds(315, 64, 110, 30);
		pninfoIngredient.add(txingamount);

		lblSLng_1 = new JLabel("Số lượng:");
		lblSLng_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSLng_1.setFont(new Font("sans-serif", Font.PLAIN, 13));
		lblSLng_1.setBounds(250, 63, 65, 24);
		pninfoIngredient.add(lblSLng_1);

		txingid.setEditable(false);
		txingname.setEditable(false);
		txingmeasure.setEditable(false);

		btreset = new JButton("XÓA HIỂN THỊ");
		btreset.setFont(new Font("sans-serif", Font.BOLD, 14));
		btreset.setBounds(146, 99, 148, 25);
		pninfoIngredient.add(btreset);

		pnbutton = new JPanel();
		if (this.authority==false)
			pnbutton.setVisible(false);
		pnbutton.setBounds(530, 581, 435, 41);

		add(pnbutton);
		pnbutton.setLayout(null);

		btedit = new JButton("SỬA");
		btedit.setBounds(142, 5, 80, 25);
		btedit.setFont(new Font("sans-serif", Font.BOLD, 14));
		pnbutton.add(btedit);

		btdelete = new JButton("XÓA");
		btdelete.setBounds(244, 5, 80, 25);
		btdelete.setFont(new Font("sans-serif", Font.BOLD, 14));
		pnbutton.add(btdelete);
		
		this.pnbutton.setBackground(icon.getTableColor());
		this.pninfoProduct.setBackground(icon.getPaneColor());
		this.pninfoIngredient.setBackground(icon.getPaneColor());
		this.pnproduct.setBackground(icon.getTableColor());
		this.pnrecipe.setBackground(icon.getTableColor());
		this.pnsearch.setBackground(icon.getPaneColor());
		this.pnsort.setBackground(icon.getPaneColor());
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
		btreset.setBorder(null);
		btreset.setForeground(Color.WHITE);
		btreset.setBackground(icon.getClearColor());
		btsearch.setForeground(Color.BLACK);
		btsort.setBorder(null);
		btsort.setForeground(Color.WHITE);
		btsort.setBackground(icon.getSortColor());
		
		// icon button
		btinsert.setIcon(icon.imageIconSize("icons/add.png", 20, 20));
		btdelete.setIcon(icon.imageIconSize("icons/delete.png", 20, 20));
		btedit.setIcon(icon.imageIconSize("icons/edit.png", 20, 20));
		
		btimport.setToolTipText("Đọc dữ liệu từ file excel mà bạn chọn.");
		btimport.setBorder(null);
		btimport.setBackground(Color.WHITE);
		btimport.setBounds(345, 0, 35, 35);
		pnbutton.add(btimport);
		
		btexport.setToolTipText("Ghi dữ liệu vào file excel");
		btexport.setBorder(null);
		btexport.setBackground(Color.WHITE);
		btexport.setBounds(395, 0, 35, 35);
		pnbutton.add(btexport);
		
		btimport.setIcon(icon.imageIconSize("icons/import2.png", 40, 40));
		btimport.setBackground(new Color(255, 255, 255));
		btimport.setBorder(null);

		btexport.setIcon(icon.imageIconSize("icons/export2.png", 40, 40));
		btexport.setBackground(new Color(255, 255, 255));
		btexport.setBorder(null);
		
		btreset.setIcon(icon.imageIconSize("icons/broom2.png", 20, 20));
		btsearch.setIcon(icon.imageIconSize("icons/search_more.png", 20, 20));
		btsort.setIcon(icon.imageIconSize("icons/sort.png", 20, 20));
// table product
		headerProduct.add("Mã");
		headerProduct.add("Tên");
		headerProduct.add("Đơn giá");
		headerProduct.add("Đơn vị");
		if (this.authority == true)
			headerProduct.add("khóa");
		DefaultTableModel model = new DefaultTableModel(headerProduct, 0);
		tableProduct.setModel(model);
		tableProduct.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				selectRowTableProduct();
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
		tableProduct.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					selectRowTableProduct();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					selectRowTableProduct();
				}
			}

			public void keyTyped(KeyEvent e) {
			}
		});
		pnproduct.setLayout(new BoxLayout(pnproduct, BoxLayout.X_AXIS));
		JScrollPane scroll = new JScrollPane(tableProduct);
		pnproduct.add(scroll);
		this.loadDataProduct();
// table recipe 
		headerRecipe.add("Mã SP");
		headerRecipe.add("Mã NL");
		headerRecipe.add("Số lượng cần");
		DefaultTableModel model1 = new DefaultTableModel(headerRecipe, 0);
		tableRecipe.setModel(model1);
		tableRecipe.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				selectRowTableRecipe();
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
		tableRecipe.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					selectRowTableRecipe();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					selectRowTableRecipe();
				}
			}

			public void keyTyped(KeyEvent e) {
			}
		});
		
		btinsert.addActionListener(this);
		btdelete.addActionListener(this);
		btedit.addActionListener(this);
		btreset.addActionListener(this);
		btsort.addActionListener(this);
		btsearch.addActionListener(this);
		btimport.addActionListener(this);
		btexport.addActionListener(this);
		
		pnrecipe.setLayout(new BoxLayout(pnrecipe, BoxLayout.X_AXIS));
		JScrollPane scroll1 = new JScrollPane(tableRecipe);
		pnrecipe.add(scroll1);
		this.loadDataProduct();
	}

	void loadDataProduct() {
		DefaultTableModel md = new DefaultTableModel(headerProduct, 0);
		if (authority == false) {
			ArrayList<Product> arr = busProduct.read();
			for (Product p : arr) {
				Object[] row = { p.getProd_id(), p.getProd_name(), p.getUnit_price(), p.getMeasure() };
				md.addRow(row);
			}
		} else {
			ArrayList<Product> arr = busProduct.readAll();
			for (Product p : arr) {
				if (p.getStatus() == 1) {
					Object[] row = { p.getProd_id(), p.getProd_name(), p.getUnit_price(), p.getMeasure(), "x" };
					md.addRow(row);
				} else {
					Object[] row = { p.getProd_id(), p.getProd_name(), p.getUnit_price(), p.getMeasure(), "" };
					md.addRow(row);
				}
			}
		}
		tableProduct.setModel(md);
		this.setColSize(tableProduct);

		if (this.authority == true)
			listProduct = busProduct.readAll();
		else
			listProduct = busProduct.read();
		if(!this.authority) {
			btimport.setVisible(false);
			btexport.setVisible(false);
		}
	}

	void loadDataProduct(ArrayList<Product> arr) {
		DefaultTableModel md = new DefaultTableModel(headerProduct, 0);
		if (authority == false) {
			for (Product p : arr) {
				Object[] row = { p.getProd_id(), p.getProd_name(), p.getUnit_price(), p.getMeasure() };
				md.addRow(row);
			}
		} else {
			for (Product p : arr) {
				if (p.getStatus() == 1) {
					Object[] row = { p.getProd_id(), p.getProd_name(), p.getUnit_price(), p.getMeasure(), "x" };
					md.addRow(row);
				} else {
					Object[] row = { p.getProd_id(), p.getProd_name(), p.getUnit_price(), p.getMeasure(), "" };
					md.addRow(row);
				}
			}
		}
		if(arr.size()==0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả nào! ");
		}
		tableProduct.setModel(md);
		this.setColSize(tableProduct);
	}

	void loadRecipe(String prod_id) {
		DefaultTableModel md = new DefaultTableModel(headerRecipe, 0);
		ArrayList<Recipe> arr = this.busRecipe.searchByProductID(prod_id);
		for (Recipe p : arr) {
			Object[] row = { p.getProd_id(), p.getIng_id(), p.getAmount() };
			md.addRow(row);
		}
		tableRecipe.setModel(md);
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

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.btinsert) {
			btinsertAction();
			this.loadRecipe(txproname.getText());
		} else if (source == this.btdelete) {
			this.btdeleteAction();
			this.loadRecipe(txproname.getText());
		} else if (source == this.btedit) {
			this.bteditaction();
			this.loadRecipe(txproname.getText());
		} else if (source == this.btreset) {
			this.btresetaction();
		} else if (source == this.btsearch) {
			this.btsearchaction();
		} else if (source == this.btsort) {
			this.btsortaction();
		}else if (source == this.btexport) {
			btexportAction();
		} else if (source == this.btimport) {
			btimportAction();
		}
	}

	public void btsortaction() {
		String type = this.cbcolunm.getSelectedItem().toString();
		String side = this.cbside.getSelectedItem().toString();
		type = type.toLowerCase();
		side = side.toLowerCase();
		ArrayList<Product> arr = busProduct.sort(listProduct, type, side, this.authority);
		this.loadDataProduct(arr);
	}

	public void btresetaction() {
		txingid.setText("");
		txingname.setText("");
		txingmeasure.setText("");
		txingamount.setText("");
	}

	public void btsearchaction() {
		ArrayList<Product> arr;
		String text = txsearch.getText();
		text = text.trim();
		String type = cbtype.getSelectedItem().toString();
		if (text.equals("") && type.equals("Tất cả")) {
			this.loadDataProduct();
			if (this.authority == true)
				listProduct = busProduct.readAll();
			else
				listProduct = busProduct.read();
			return;
		} else {
			type = this.getIdType(type);
			try {
				arr = busProduct.search(this.authority, text, 0, 1000000, type);
				listProduct = arr;
				this.loadDataProduct(arr);
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void btdeleteAction() {
		String aa=this.txingid.getText();
		if (!aa.trim().equals("")) {
			String pro_name = txproname.getText();
			String pro_id = txproid.getText();
			String ing_id = txingid.getText();
			int a = JOptionPane.showConfirmDialog(this,
					"Bạn chắc là muốn xóa nguyên liệu mã " + ing_id + " của sản phẩm " + pro_name + ".", "Xóa",
					JOptionPane.YES_NO_OPTION);
			if (a == 0) {
				busRecipe.delete(pro_id, ing_id);
				JOptionPane.showMessageDialog(this,
						"Bạn đã xóa nguyên liệu mã " + ing_id + " của sản phẩm " + pro_name + ".");
			}
		}
	}

	public boolean checkValue() {
		String amount = txingamount.getText();
		if (!amount.matches("^\\d+.?\\d*$")) {
			JOptionPane.showMessageDialog(this, "Số lượng phải là số thực hoặc số nguyên dương.");
			return false;
		}
		return true;
	}

	public void bteditaction() {
		String aa=this.txingid.getText();
		if (!aa.trim().equals("")) {
			if (!this.checkValue()) {
				return;
			}
			String pro_name = txproname.getText();
			String pro_id = txproid.getText();
			String ing_id = txingid.getText();
			String amount = txingamount.getText();
			int a = JOptionPane.showConfirmDialog(this,
					"Bạn chắc là muốn sửa nguyên liệu mã " + ing_id + " của sản phẩm " + pro_name + ".", "Sửa",
					JOptionPane.YES_NO_OPTION);
			if (a == 0) {
				Recipe rep = new Recipe(pro_id, ing_id, Double.parseDouble(amount));
				busRecipe.update(rep);
				JOptionPane.showMessageDialog(this,
						"Bạn đã sửa nguyên liệu mã " + ing_id + " của sản phẩm " + pro_name + ".");
			}
		}
	}
	public void btimportAction() {
		Excel excel = new Excel();
		ReadExcelDialog dialogexcel = new ReadExcelDialog("recipe");
		dialogexcel.setLocationRelativeTo(null);
		dialogexcel.setModal(true);
		dialogexcel.setVisible(true);
		int select = dialogexcel.getConfirm();
		if (select == 0)
			return;
		else if (select == 1) {
			ArrayList<Recipe> arr = excel.readRecipe(dialogexcel.getLink());
			if (arr == null)
				return;
			for (Recipe p : arr) {
				Recipe temp = busRecipe.searchByID(p.getProd_id(),p.getIng_id());
				if (temp == null)
					busRecipe.add(p);
				else
					busRecipe.update(p);
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

	public void btinsertAction() {
		if (txproid.getText().equals(""))
			return;
		dialog = new AddRepDialog(txproid.getText());
		this.dialog.setLocationRelativeTo(null);
		this.dialog.setModal(true);
		this.dialog.setVisible(true);
		Recipe rep = this.dialog.getRep();
		if (rep != null) {
			busRecipe.add(rep);
		}
	}

	public void setColSize(JTable table) {
		TableColumnModel col = table.getColumnModel();
		this.setFixColSize(col, 0, 50);
		this.setFixColSize(col, 1, 220);
		this.setFixColSize(col, 2, 80);
		this.setFixColSize(col, 3, 80);
		if (this.authority == true)
			this.setFixColSize(col, 4, 20);

	}

	public void setFixColSize(TableColumnModel col, int index, int size) {
		col.getColumn(index).setMinWidth(size);
		// col.getColumn(index).setResizable(false);
	}

	public void selectRowTableProduct() {
		int i = tableProduct.getSelectedRow();
		if (i >= 0) {
			txproid.setText(tableProduct.getModel().getValueAt(i, 0).toString());
			txproname.setText(tableProduct.getModel().getValueAt(i, 1).toString());
			loadRecipe(txproid.getText());
			if (authority == true) {
				if (tableProduct.getModel().getValueAt(i, 4).toString().equals("x")) {
					btinsert.setEnabled(false);
					btdelete.setEnabled(false);
					btedit.setEnabled(false);
				} else {
					btinsert.setEnabled(true);
					btdelete.setEnabled(true);
					btedit.setEnabled(true);
				}
			}
		}
	}

	public void selectRowTableRecipe() {
		int i = tableRecipe.getSelectedRow();
		if (i >= 0) {
			String code = tableRecipe.getModel().getValueAt(i, 1).toString();
			Ingredient ing = busIngredient.searchByID(code, authority);
			txingid.setText(ing.getIng_id());
			txingname.setText(ing.getIng_name());
			txingmeasure.setText(ing.getMeasure());
			txingamount.setText(tableRecipe.getModel().getValueAt(i, 2).toString());
		}
	}
}
