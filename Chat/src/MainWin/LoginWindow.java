package MainWin;

import javax.swing.*;

import java.awt.event.*;
import java.sql.*;

public class LoginWindow extends JFrame implements ActionListener
{
	JLabel userL = new JLabel("Username");
	static JTextField userIn = new JTextField("");
	JLabel passL = new JLabel("Password");
	static JPasswordField passIn = new JPasswordField();
	JButton login = new JButton("Login");
	JButton resetPass = new JButton("Reset Password");
	JButton showPass = new JButton("Show Password");
	JButton register = new JButton("Register");//placeholder for future implementation
	
	static JFrame frame = null;
	
	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement pst = null;
	
	public static String usrName = "";
	public static String usrUIC = "";
	static int sh = 0;

	LoginWindow()
	{
		this.setSize(280, 180);
		//this.setLocation(400, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Login");
		this.setLayout(null);
		
		userL.setSize(100, 30);
		userL.setLocation(10, 10);
		this.add(userL);
		
		userIn.setSize(100, 30);
		userIn.setLocation(10, 40);
		this.add(userIn);
		
		passL.setSize(100, 30);
		passL.setLocation(120, 10);
		this.add(passL);
		
		passIn.setEchoChar('*');
		passIn.setSize(130, 30);
		passIn.setLocation(120, 40);
		passIn.addActionListener(this);
		this.add(passIn);
		
		login.setSize(100, 30);
		login.setLocation(10, 80);
		login.addActionListener(this);
		this.add(login);
		
		showPass.setSize(130, 30);
		showPass.setLocation(120, 80);
		showPass.addActionListener(this);
		this.add(showPass);
		
		resetPass.setSize(150, 30);
		resetPass.setLocation(10, 120);
		resetPass.addActionListener(this);
		this.add(resetPass);
		
		register.setSize(100, 30);
		register.setLocation(170, 120);
		register.addActionListener(this);
		this.add(register);
	}
	
	public static void main(String[] args) 
	{
		CreateGUI();
	}

	public static void CreateGUI()
	{
		userIn.setText("");
		passIn.setText("");
		frame = new LoginWindow();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource() == login || ae.getSource() == passIn){
			LoginCode();
		}else if(ae.getSource() == showPass){
			if(sh == 0){
				passIn.setEchoChar((char)0);
				sh = 1;
				showPass.setText("Hide Password");
			}else{
				passIn.setEchoChar('*');
				sh = 0;
				showPass.setText("Show Password");
			}
		}else if(ae.getSource() == register){
			RegisterNew.CreateGUI();
		}
	}
	
	public static void LoginCode(){
		String terms = "This is a placeholder item for this program's" + '\n' +
				"Terms and Conditions." + '\n' +
				"" + '\n' +
				"You must agree to the Terms and Conditions to continue." + '\n' +
				"If you agree, please select Yes. If not, please select No or Cancel.";
		String usr = userIn.getText();
		String password = "";
		char[] pass = passIn.getPassword();
		for(int count = 0; count < pass.length; count++){
			password += pass[count];
		}

		String passSHA = CmnCode.HashBash(password);
		String username = "";
		if(usr.equalsIgnoreCase("root") && passSHA.equalsIgnoreCase(CmnCode.HashBash("halorvb1"))){
			JOptionPane.showMessageDialog(null, "Welcome! You are \nlogged in as ROOT.");
			//AdminWindow.CreateGUI("root"); TODO
		}else{
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				String connStr = MysqlStuff.conn();
				conn = DriverManager.getConnection(connStr + MysqlStuff.loginAero());
				
				pst = conn.prepareStatement("Select * from users where username = ? and usr_pass = ?");
				pst.setString(1, usr);
				pst.setString(2, passSHA);
				ResultSet rs = pst.executeQuery();
	
				if(rs.next()){
					
					String userIDNum = rs.getString("user_id");
					username = rs.getString("name_first") + " " + rs.getString("name_last");
					
					frame.dispose();	
					JOptionPane.showMessageDialog(null, "Welcome " + username + "!");
					//MainWindow.CreateGUI(); TODO
									
				}else{
					JOptionPane.showMessageDialog(null, "Login failed, please try again.");
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
	
	public static boolean CleanRegNum(String login, String regnum)
	{
		boolean cleanreg = false;
		
		try
		{
			conn = DriverManager.getConnection(MysqlStuff.conn() + "user=aerothosis&password=halorvb1");
			
			pst = conn.prepareStatement("Select * from reg_users where login = ? and regnum = ?");
			pst.setString(1, login);
			pst.setString(2, regnum);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				cleanreg = true;
			}
			else
			{
				cleanreg = false;
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
		return cleanreg;
	}
}
