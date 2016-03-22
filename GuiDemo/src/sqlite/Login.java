package sqlite;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frmLoginToSqlite;
	// declare connection
	Connection conn = null;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JLabel lblMessage;
	private JLabel lblImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLoginToSqlite.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		conn=SqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginToSqlite = new JFrame();
		frmLoginToSqlite.setTitle("Login to SQLITE");
		frmLoginToSqlite.setBounds(100, 100, 510, 362);
		frmLoginToSqlite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginToSqlite.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(194, 30, 89, 14);
		frmLoginToSqlite.getContentPane().add(lblUsername);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(194, 80, 89, 14);
		frmLoginToSqlite.getContentPane().add(lblNewLabel_1);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(325, 27, 113, 20);
		frmLoginToSqlite.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		//Login button
		JButton btnLogin = new JButton("Login");
		Image okImg = new ImageIcon(this.getClass().getResource("/okIcon1.png")).getImage();
		btnLogin.setIcon(new ImageIcon(okImg));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//build a query
					String query ="select * from employeeInfo where username=? and password=?";
					
					// prepare a statement
					PreparedStatement pst = conn.prepareStatement(query);
					
					// statement takes user input and assign to the query.
					// statement is index based meaning: each ? in the query is assigned in index, so:
					// username=? is index 1
					// password=? is index 2
					pst.setString(1, textFieldUsername.getText());
					pst.setString(2, passwordField.getText());
					
					// execute query and result is transferred to rs object.
					ResultSet rs = pst.executeQuery();
					int count=0;
					
					// as long as the condition is true
					while(rs.next()){
						count++;
					}
					// the query returns one result only (expected)
					if(count==1){
						
						JOptionPane.showMessageDialog(null, "Username and Password are correct");
						//lblMessage.setText("Username and Password are correct");
						frmLoginToSqlite.dispose();
						EmployeeInfo emplInfo = new EmployeeInfo();
						emplInfo.setVisible(true);
					}
					// the query returns more than one result (duplicate usrename and password in DB...?)
					else if(count>1){
						
						//JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
						lblMessage.setText("Duplicate Username and Password");
					}
					// the query returns no results
					else{
						
						//JOptionPane.showMessageDialog(null, "Username and Password are not correct. Try again...");
						lblMessage.setText("Username and Password are not correct. Try again...");
					}
					
					// DO NOT FORGET TO CLOSE THE CONNECTION TO THE DB
					// SQLITE DB MAKES ONLY ONE CONNECTION !!
					rs.close();
					pst.close();
					
				} catch (Exception eLogin){
					JOptionPane.showMessageDialog(null, eLogin);
					
				}
			}
		});
		btnLogin.setBounds(194, 147, 120, 48);
		frmLoginToSqlite.getContentPane().add(btnLogin);
		
		// Clear button
		JButton btnClear = new JButton("Clear");
		Image clearImg = new ImageIcon(this.getClass().getResource("/clearIcon.png")).getImage();
		btnClear.setIcon(new ImageIcon(clearImg));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldUsername.setText("");
				passwordField.setText("");
				lblMessage.setText("Cleared: Try again.");
			}
		});
		btnClear.setBounds(349, 147, 120, 48);
		frmLoginToSqlite.getContentPane().add(btnClear);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('$');
		passwordField.setBounds(325, 77, 113, 20);
		frmLoginToSqlite.getContentPane().add(passwordField);
		
		// Message Center
		lblMessage = new JLabel("\tMessage Center");
		Image msgImg = new ImageIcon(this.getClass().getResource("/msgIcon.png")).getImage();
		lblMessage.setIcon(new ImageIcon(msgImg));
		lblMessage.setBounds(23, 208, 461, 104);
		frmLoginToSqlite.getContentPane().add(lblMessage);
		
		// keyboard image
		lblImage = new JLabel("");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		Image loginImg = new ImageIcon(this.getClass().getResource("/loginIcon.png")).getImage();
		lblImage.setIcon(new ImageIcon(loginImg));
		lblImage.setBounds(10, 30, 144, 128);
		frmLoginToSqlite.getContentPane().add(lblImage);
		
	}
}
