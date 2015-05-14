package MainWin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterNew extends JFrame implements ActionListener{

	JPanel list;
	JScrollPane listScrl;
	
	JLabel usrL, passL, emailL, phoneNumL, phoneCarL, nameFL, nameML, nameLL;
	JTextField usrInput = new JTextField();
	JTextField passInput = new JTextField();
	JTextField emailInput = new JTextField();
	JTextField nameFInput = new JTextField();
	JTextField nameMInput = new JTextField();
	JTextField nameLInput = new JTextField();
	JTextField phoneNumInput = new JTextField();
	JTextField phoneCarInput = new JTextField(); //Needs to be a dropdown list and required if phone number is input
	JButton create = new JButton("Create Account");
	
	
	
	static JFrame frame;
	
	Connection conn;
	PreparedStatement pst;
	PreparedStatement pstTwo;
	
	RegisterNew(){
		setSize(260, 450);
		setLocationRelativeTo(null);
		setResizable(false);  //disables resizing the window by user
		setTitle("Register New Account");
		
		list = new JPanel();
		list.setLayout(null);
		
		usrL = new JLabel("Username:");
		usrL.setSize(100, 30);
		usrL.setLocation(10, 10);
		list.add(usrL);
		
		usrInput.setSize(100, 30);
		usrInput.setLocation(130, 10);
		list.add(usrInput);
		
		passL = new JLabel("Password:");
		passL.setSize(100, 30);
		passL.setLocation(10, 50);
		list.add(passL);
		
		passInput.setSize(100, 30);
		passInput.setLocation(130, 50);
		list.add(passInput);
		
		emailL = new JLabel("Email:");
		emailL.setSize(100, 30);
		emailL.setLocation(10, 90);
		list.add(emailL);
		
		emailInput.setSize(100, 30);
		emailInput.setLocation(130, 90);
		list.add(emailInput);
		
		phoneNumL = new JLabel("Phone Number:");
		phoneNumL.setSize(110, 30);
		phoneNumL.setLocation(10, 130);
		list.add(phoneNumL);
		
		phoneNumInput.setSize(100, 30);
		phoneNumInput.setLocation(130, 130);
		list.add(phoneNumInput);
		
		phoneCarL = new JLabel("Phone Carrier:");
		phoneCarL.setSize(110, 30);
		phoneCarL.setLocation(10, 170);
		list.add(phoneCarL);
		
		nameFL = new JLabel("First Name:");
		nameFL.setSize(100, 30);
		nameFL.setLocation(10, 210);
		list.add(nameFL);
		
		nameFInput.setSize(100, 30);
		nameFInput.setLocation(130, 210);
		list.add(nameFInput);
		
		nameML = new JLabel("Middle Name:");
		nameML.setSize(100, 30);
		nameML.setLocation(10, 250);
		list.add(nameML);
		
		nameMInput.setSize(100, 30);
		nameMInput.setLocation(130, 250);
		list.add(nameMInput);
		
		nameLL = new JLabel("Last Name:");
		nameLL.setSize(100, 30);
		nameLL.setLocation(10, 290);
		list.add(nameLL);
		
		
		nameLInput.setSize(100, 30);
		nameLInput.setLocation(130, 290);
		list.add(nameLInput);
		
		create.setSize(150, 30);
		create.setLocation(55, 340);
		create.addActionListener(this);
		list.add(create);
		
		add(list, BorderLayout.CENTER);
	}
	
	public static void main(String[] args){
		CreateGUI();
	}
	
	public static void CreateGUI(){
		frame = new RegisterNew();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == create){
			InsertAccount();
		}
	}
	
	private void InsertAccount(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(MysqlStuff.conn() + MysqlStuff.loginAero());
			pst = conn.prepareStatement("INSERT INTO users (user_id,name_first,name_mid,name_last,username,usr_pass,email,phone_num,phone_carrier) VALUES(?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1, CmnCode.RandomGen(10));
			pst.setString(2, nameFInput.getText());
			pst.setString(3, nameMInput.getText());
			pst.setString(4, nameLInput.getText());
			pst.setString(5, usrInput.getText());
			pst.setString(6, CmnCode.HashBash(passInput.getText())); //pass
			pst.setString(7, emailInput.getText()); //email
			pst.setString(8, phoneNumInput.getText()); //phone num
			pst.setString(9, ""); //phone carrier
			
			pst.execute();
		}catch(SQLIntegrityConstraintViolationException e){
			InsertAccount();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
