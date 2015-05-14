package MainWin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterNew extends JFrame implements ActionListener{

	JPanel list;
	JScrollPane listScrl;
	
	JLabel usrL = new JLabel("Username");
	JTextField usrInput = new JTextField();
	JLabel nameFL = new JLabel("First Name");
	JTextField nameFInput = new JTextField();
	JLabel nameML = new JLabel("Middle Name");
	JTextField nameMInput = new JTextField();
	JLabel nameLL = new JLabel("Last Name");
	
	
	static JFrame frame;
	
	RegisterNew(){
		setSize(300, 300);
		setLocationRelativeTo(null);
		//setResizable(false);  //disables resizing the window by user
		setTitle("Register New Account");
		
		list = new JPanel();
		list.setLayout(null);
		
		usrL.setSize(100, 30);
		usrL.setLocation(10, 10);
		list.add(usrL);
		
		usrInput.setSize(100, 30);
		usrInput.setLocation(120, 10);
		list.add(usrInput);
		
		nameML.setSize(100, 30);
		nameML.setLocation(10, 500);
		list.add(nameML);
		
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
		
	}
	
	
}
