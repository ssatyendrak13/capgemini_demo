import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.sql.*;
import javax.swing.*;
public class Login extends JFrame implements ActionListener{
	
	JButton login;
	JButton close;
	JTextField nameField;
	JPasswordField passwordField ;
	
	Login(){
		
		setLayout(null);
		JLabel userName = new JLabel("User Name : ");
		userName.setBounds(150 , 100 ,95 , 30);
		userName.setFont(new Font("tohoma" , Font.BOLD ,14 ));
		add(userName);
		
		JLabel password = new JLabel("Password : ");
		password.setBounds(150 , 150 , 90 , 30);
		password.setFont(new Font("tohoma" , Font.BOLD , 14));
		add(password);
		
		nameField = new JTextField();
		nameField.setBounds( 270, 100 , 160 , 30);
		nameField.setFont(new Font("tohoma" , Font.BOLD , 14));
		add(nameField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds( 270, 150 , 160 , 30);
		passwordField.setFont(new Font("tohoma" , Font.BOLD , 14));
		add(passwordField);
		
		JCheckBox show = new JCheckBox("Show");
		show.setBounds( 270, 180 , 60 , 30);
		add(show);
		
		show.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(show.isSelected()) {
					passwordField.setEchoChar((char)0);
				}else {
					passwordField.setEchoChar('*');
				}
			}
			
		});
		
		login = new JButton("Login");
		login.setBounds( 270, 220 , 70 , 30);
		login.addActionListener(this);
		add(login);
		
		close = new JButton("close");
		close.setBounds( 357, 220 , 70 , 30);
		close.addActionListener(this);
		add(close);
		
		setSize(600,450);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==close) {
			System.exit(0);
		}
		if(e.getSource()==login) {
			String userId = nameField.getText().trim();
			String userPass = passwordField.getText().trim();
			String dbid="" ;
			String dbpass="";
			
			// sql operation 
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/tempdb";
				String id = "root";
				String pass = "Root@123";
				Connection con = DriverManager.getConnection(url , id , pass);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select *from login");
				while(rs.next()) {
					dbid = rs.getString(2);
					dbpass=rs.getString(3);
				}
			}catch(Exception e1) {
				
			}
			if(userId.equals(dbid) && userPass.equals(dbpass) ) {
				JOptionPane.showMessageDialog(null, "login sucessfully");
				setVisible(false);
				new Example2();
			}else {
				JOptionPane.showMessageDialog(this, "incorrect userName or password");
			}
		}
	}

	public static void main(String[] args) {
		new Login();
	}

}
