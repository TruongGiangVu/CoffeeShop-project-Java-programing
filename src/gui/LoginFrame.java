package gui;

import bus.EmployerBUS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginFrame extends JFrame implements ActionListener,KeyListener{
	Container cont = getContentPane();
	String id="";
	JPanel p0 = new JPanel();
	JPanel pnTop = new JPanel();
	JPanel pnBot = new JPanel();
	JPanel pnBtn = new JPanel();
	JTextField txAcc = new JTextField("employer");
	JPasswordField txPass = new JPasswordField("12345");
	JLabel lbTitle = new JLabel("THÔNG TIN ĐĂNG NHẬP");
	JLabel lbAcc = new JLabel("Tài khoản:");
	JLabel lbPass = new JLabel("Mật khẩu:");
	JButton btSubmit = new JButton("Đăng nhập");
	JButton btExit = new JButton("Thoát");
	public LoginFrame(){
		this.setSize(500, 400);
		this.setResizable(false);
		//this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("Coffe Shop");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//top panel
		pnTop.setLayout(null); pnTop.setBounds(0, 0, 500, 150);
		lbTitle.setBackground(Color.white);
		lbTitle.setBounds(0, 50, 490, 50);
		lbTitle.setFont(new Font("Calibri",Font.BOLD,30));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setVerticalAlignment(SwingConstants.CENTER);
		pnTop.add(lbTitle);
		
		//bottom panel
		pnBot.setLayout(null); pnBot.setBounds(0, 150, 494, 100); 
		pnBot.add(lbAcc); pnBot.add(txAcc);
		pnBot.add(lbPass); pnBot.add(txPass);
		lbAcc.setBounds(90, 10, 80, 28);
		lbPass.setBounds(90, 45, 80, 28);
		txAcc.setBounds(180, 10, 200, 30);
		txPass.setBounds(180, 45, 200, 30);
		txAcc.addKeyListener(this);
		txPass.addKeyListener(this);
		
		
		//buttons panel
		pnBtn.setBounds(0, 250, 494, 121);
		pnBtn.add(btSubmit); pnBtn.add(btExit);
		btSubmit.addActionListener(this);
		btExit.addActionListener(this);
		
		//main panel
		p0.setLayout(null); p0.setBounds(0, 0, 500, 500);
		p0.add(pnTop);
		p0.add(pnBot);
		p0.add(pnBtn);
		
		//container
		cont.add(p0);
		
	}
	public void loginCheck() {
		EmployerBUS emp_bus = new EmployerBUS();
		String check = emp_bus.loginCheck(txAcc.getText(), String.valueOf(txPass.getPassword()));
		if(txAcc.getText().equals("") && String.valueOf(txPass.getPassword()).equals("")){
			JOptionPane.showMessageDialog(rootPane, "Chưa nhập đầy đủ thông tin!");
		}else if(check.equals("null")) {
			JOptionPane.showMessageDialog(rootPane, "Tài khoản không tồn tại!");
		}else {
			this.dispose();
			MainFrame frMain = new MainFrame(emp_bus.searchByUser(txAcc.getText()).getEmp_id());
		}
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btSubmit) {
			loginCheck();
			
		}
		if(obj == btExit) {
			this.dispose();
		}
	}
	
	public void keyPressed(KeyEvent k) {
		if( k.getKeyCode() == KeyEvent.VK_ENTER) {
			loginCheck();
		}
	}
	public void keyReleased(KeyEvent e) {
        
    }
 
    public void keyTyped(KeyEvent e) {
        
    }

}
