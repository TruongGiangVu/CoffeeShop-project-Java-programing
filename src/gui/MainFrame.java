package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;

//import org.apache.poi.ss.usermodel.Font;

import javax.swing.border.EtchedBorder;
import dto.*;
import gui.AddBillDialog;
import bus.*;
import design.*;
import design.Icon;
public class MainFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	JPanel pnMenu;
	JPanel pnContent;
	JPanel pnMenuLogo;
	JPanel pnMenuBtn;
	JPanel pnCalculation;
	JTabbedPane pnProdList;
	JPanel pnCusList;
	JPanel pnEmpList;
	JPanel pnBillList;
	JPanel pnSaleList;
	JPanel pnSupList;
	JPanel pnImpList;
	CardLayout deck = new CardLayout();
	
	JButton btnMakeBill;
	JButton btnProdList;
	JButton btnCusList;
	JButton btnEmpList;
	JButton btnBillList;
	JButton btnLogout;
	JButton btnExit;
	JButton btnCal;
	private JButton btnImpList;
	String id;
	private JButton btnSaleList;
	private JButton btnSupList;
	private boolean authority;
	JLabel lblogo=new JLabel();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public MainFrame(String id) {
		Icon icon = new Icon();
		this.id=id;
		EmployerBUS empBus = new EmployerBUS();
		Employer emp = empBus.searchByID(id);
		authority = emp.isEmp_type();
		
		JOptionPane.showMessageDialog(rootPane, String.format("Chào %s, chức vụ: %s", emp.getEmp_name(),authority?"Admin":"Nhân viên"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 710);
		
		contentPane = new JPanel();
		contentPane.setSize(1200, 700);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle(String.format("Coffe House - Chào %s",emp.getEmp_name()));
		
		//panels
		pnMenu = new JPanel();
		pnMenu.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Menu", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.text));
		pnMenu.setPreferredSize(new Dimension(200, 10));
		pnMenu.setBackground(SystemColor.windowBorder);
		contentPane.add(pnMenu, BorderLayout.WEST);
		pnMenu.setLayout(null);
		
		
		pnMenuLogo = new JPanel(); pnMenuLogo.setBackground(new Color(0,0,18));
		pnMenuLogo.setBounds(15, 18, 170, 170);
		lblogo.setBounds(5,5,180,180);
		lblogo.setBackground(new Color(55,55,55));
		lblogo.setIcon(icon.imageIconSize("icons/avatar.png", 165, 165));
		pnMenuLogo.add(lblogo);
		pnMenu.add(pnMenuLogo);
		
		pnMenuBtn = new JPanel();
		pnMenuBtn.setBackground(SystemColor.windowBorder);
		pnMenuBtn.setBounds(10, 202, 180, 445);
		pnMenu.add(pnMenuBtn);
		pnMenuBtn.setLayout(new GridLayout(0, 1, 0, 5));
		
		btnCusList = new JButton("DANH SÁCH KHÁCH HÀNG"); btnCusList.addActionListener(this);
		
		btnMakeBill = new JButton("LẬP HÓA ĐƠN"); 
		btnMakeBill.addActionListener(this);
		btnCal = new JButton("THỐNG KÊ"); 
		btnCal.addActionListener(this);
		if(this.authority) pnMenuBtn.add(btnCal);
		else pnMenuBtn.add(btnMakeBill);
		//buttons
		
		btnProdList = new JButton("DANH SÁCH SẢN PHẨM"); 
		btnProdList.addActionListener(this);
		pnMenuBtn.add(btnProdList);
		
		btnSaleList = new JButton("DANH SÁCH KHUYẾN MÃI");
		btnSaleList.addActionListener(this);
		pnMenuBtn.add(btnSaleList);
		pnMenuBtn.add(btnCusList);
		
		btnEmpList = new JButton("DANH SÁCH NHÂN VIÊN"); btnEmpList.addActionListener(this);
		pnMenuBtn.add(btnEmpList);
		
		btnBillList = new JButton("DANH SÁCH HÓA ĐƠN"); btnBillList.addActionListener(this);
		
		btnSupList = new JButton("DANH SÁCH CUNG CẤP"); btnSupList.addActionListener(this);
		pnMenuBtn.add(btnSupList);
		pnMenuBtn.add(btnBillList);
		
		btnImpList = new JButton("DANH SÁCH NHẬP HÀNG");
		btnImpList.addActionListener(this);
		pnMenuBtn.add(btnImpList);
		
		btnLogout = new JButton("ĐĂNG XUẤT"); btnLogout.addActionListener(this);
		pnMenuBtn.add(btnLogout);
		
		btnExit = new JButton("THOÁT"); btnExit.addActionListener(this);
		pnMenuBtn.add(btnExit);
		
		pnContent = new JPanel();
		pnContent.setBackground(SystemColor.menu);
		contentPane.add(pnContent, BorderLayout.CENTER);
		pnContent.setSize(1000, 700);
		pnContent.setLayout(deck);
		
		
		//card layout panels
		pnCalculation = new CalculationGUI();
		pnContent.add(pnCalculation, "calcu");
		
		this.pnProdList= new ProTabbedPane(id);
		pnContent.add(pnProdList,"prodlist");
		
		/*
		pnEmpList = new EmployerGUI(id);
		pnContent.add(pnEmpList, "emplist");
		
		pnSaleList = new SaleGUI(id);
		pnContent.add(pnSaleList, "salelist");
		
		pnCusList = new CustomerGUI(id);
		pnContent.add(pnCusList, "cuslist");
		
		pnSupList = new SupplierGUI(id);
		pnContent.add(pnSupList, "suplist");
		
		pnBillList = new BillGUI(id);
		pnContent.add(pnBillList, "billlist");
		
		pnImpList = new ImportGUI(id);
		pnContent.add(pnImpList, "implist");
		*/
		
		
		
		Color black= new Color(0,0,0);
		Color white= new Color(255,255,255);
		this.setBackground(black);
		this.pnMenu.setBackground(black);
		this.pnContent.setBackground(black);
		this.pnMenuBtn.setBackground(black);
		
		this.btnBillList.setBackground(black);
		this.btnBillList.setBorder(null);
		this.btnBillList.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnBillList.setForeground(white);
		
		this.btnCal.setBackground(black);
		this.btnCal.setBorder(null);
		this.btnCal.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnCal.setForeground(white);
		
		this.btnEmpList.setBackground(black);
		this.btnEmpList.setBorder(null);
		this.btnEmpList.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnEmpList.setForeground(white);
		
		this.btnCusList.setBackground(black);
		this.btnCusList.setBorder(null);
		this.btnCusList.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnCusList.setForeground(white);
		
		this.btnProdList.setBackground(black);
		this.btnProdList.setBorder(null);
		this.btnProdList.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnProdList.setForeground(white);
		
		this.btnSaleList.setBackground(black);
		this.btnSaleList.setBorder(null);
		this.btnSaleList.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnSaleList.setForeground(white);
		
		this.btnExit.setBackground(black);
		this.btnExit.setBorder(null);
		this.btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnExit.setForeground(white);
		
		this.btnSupList.setBackground(black);
		this.btnSupList.setBorder(null);
		this.btnSupList.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnSupList.setForeground(white);
		
		this.btnLogout.setBackground(black);
		this.btnLogout.setBorder(null);
		this.btnLogout.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnLogout.setForeground(white);

		this.btnImpList.setBackground(black);
		this.btnImpList.setBorder(null);
		this.btnImpList.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnImpList.setForeground(white);
		
		this.btnMakeBill.setBackground(black);
		this.btnMakeBill.setBorder(null);
		this.btnMakeBill.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnMakeBill.setForeground(white);
		
		
		
		deck.show(pnContent, "prodlist");
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnCal) {
			deck.removeLayoutComponent(pnCalculation);
			this.pnCalculation= new CalculationGUI();
			pnContent.add(pnCalculation,"calcu");
			deck.show(pnContent, "calcu");
		}
		if(obj == btnMakeBill) {
			BillBUS billBus = new BillBUS();
			if(billBus.readAll().size() < 100000) {
				AddBillDialog dial = new AddBillDialog(id,"");
				dial.setLocationRelativeTo(null);
				dial.setModal(true);
				dial.setVisible(true);
				Bill temp = dial.getBill();
				if(temp != null) {
					billBus.add(temp);
					dial.updateBillDetail();
					
					JOptionPane.showMessageDialog(null, "Thêm thành công");
				}
			}
			else JOptionPane.showMessageDialog(null, "Hết bộ nhớ!");
		}
		if(obj == btnProdList) {
			//deck.removeLayoutComponent(pnProdList);
			this.pnProdList= new ProTabbedPane(id);
			pnContent.add(pnProdList,"prodlist");
			deck.show(pnContent, "prodlist");
		}
		if(obj == btnSupList) {
			//deck.removeLayoutComponent(pnSupList);
			this.pnSupList = new SupplierGUI(id);
			pnContent.add(pnSupList,"suplist");
			deck.show(pnContent, "suplist");
		}
		if(obj == btnCusList) {
			//deck.removeLayoutComponent(pnCusList);
			this.pnCusList = new CustomerGUI(id);
			pnContent.add(pnCusList,"cuslist");
			deck.show(pnContent, "cuslist");
		}
		if(obj == btnSaleList) {
			//deck.removeLayoutComponent(pnSaleList);
			this.pnSaleList = new SaleGUI(id);
			pnContent.add(pnSaleList,"salelist");
			deck.show(pnContent, "salelist");
		}
		if(obj == btnEmpList) {
			//deck.removeLayoutComponent(pnEmpList);
			this.pnEmpList = new EmployerGUI(id);
			pnContent.add(pnEmpList,"emplist");
			deck.show(pnContent, "emplist");
		}
		if(obj == btnBillList) {
			//deck.removeLayoutComponent(pnBillList);
			this.pnBillList = new BillGUI(id);
			pnContent.add(pnBillList,"billlist");
			deck.show(pnContent, "billlist");
		}
		if(obj == btnImpList) {
			//deck.removeLayoutComponent(pnImpList);
			this.pnImpList = new ImportGUI(id);
			pnContent.add(pnImpList,"implist");
			deck.show(pnContent, "implist");
		}
		if(obj == btnLogout) {
			int check = JOptionPane.showConfirmDialog(null, "Bạn muốn đăng xuất?", "", JOptionPane.YES_NO_OPTION);
			if(check == 0) {
				this.dispose();
				LoginFrame lg = new LoginFrame();
			}
		}
		if(obj == btnExit) {
			int check = JOptionPane.showConfirmDialog(null, "Bạn muốn thoát?", "", JOptionPane.YES_NO_OPTION);
			if(check == 0) {
				this.dispose();
			}
		}
	}
}
