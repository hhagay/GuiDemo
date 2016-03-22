package sqlite;

import java.sql.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeInfo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeInfo frame = new EmployeeInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	private JLabel lblEID;
	private JLabel lblFN;
	private JLabel lblLN;
	private JLabel lblAge;
	private JTextField textFieldEID;
	private JTextField textFieldFN;
	private JTextField textFieldLN;
	private JTextField textFieldAge;
	private JButton btnDelete;
	
	public void refreshTable(){
		try{
			String query="select EID, firstname, lastname, age from EmployeeInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void fillComboBox(){
		
		try{
			String query="select * from EmployeeInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				comboBox.addItem(rs.getString("firstname"));
			}
			
			pst.close();
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public EmployeeInfo() {
		connection=SqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadTable = new JButton("Load Employee Data");
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query = "select EID, firstname, lastname, age from EmployeeInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch (Exception e){
					e.printStackTrace();
					
				}
			}
		});
		btnLoadTable.setBounds(300, 11, 172, 23);
		contentPane.add(btnLoadTable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(199, 55, 388, 222);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					int row = table.getSelectedRow();
					String eID = (table.getModel().getValueAt(row, 0)).toString();
					
					String query="select * from EmployeeInfo where EID = '"+eID+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
					
					ResultSet rs = pst.executeQuery();
					while (rs.next()){
						textFieldEID.setText(rs.getString("EID"));
						textFieldFN.setText(rs.getString("firstname"));
						textFieldLN.setText(rs.getString("lastname"));
						textFieldAge.setText(rs.getString("Age"));
					}
					pst.close();

				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		lblEID = new JLabel("EID");
		lblEID.setBounds(15, 56, 46, 14);
		contentPane.add(lblEID);
		
		lblFN = new JLabel("First \r\nName");
		lblFN.setBounds(10, 99, 51, 14);
		contentPane.add(lblFN);
		
		lblLN = new JLabel("Last Name");
		lblLN.setBounds(10, 156, 71, 14);
		contentPane.add(lblLN);
		
		lblAge = new JLabel("Age");
		lblAge.setBounds(15, 202, 46, 14);
		contentPane.add(lblAge);
		
		textFieldEID = new JTextField();
		textFieldEID.setBounds(10, 68, 86, 20);
		contentPane.add(textFieldEID);
		textFieldEID.setColumns(10);
		
		textFieldFN = new JTextField();
		textFieldFN.setBounds(10, 114, 86, 20);
		contentPane.add(textFieldFN);
		textFieldFN.setColumns(10);
		
		textFieldLN = new JTextField();
		textFieldLN.setBounds(10, 171, 86, 20);
		contentPane.add(textFieldLN);
		textFieldLN.setColumns(10);
		
		textFieldAge = new JTextField();
		textFieldAge.setBounds(10, 216, 86, 20);
		contentPane.add(textFieldAge);
		textFieldAge.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					String query="insert into EmployeeInfo (EID, firstname, lastname, age) values (?, ?, ?, ?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldEID.getText());
					pst.setString(2, textFieldFN.getText());
					pst.setString(3, textFieldLN.getText());
					pst.setString(4, textFieldAge.getText());
					
					pst.execute();
					
					
					JOptionPane.showMessageDialog(null, "Data Saved");
					
					pst.close();
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		btnSave.setBounds(15, 261, 64, 23);
		contentPane.add(btnSave);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="select * from EmployeeInfo where firstname = ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, (String) comboBox.getSelectedItem());
					ResultSet rs = pst.executeQuery();
					while (rs.next()){
						textFieldEID.setText(rs.getString("EID"));
						textFieldFN.setText(rs.getString("firstname"));
						textFieldLN.setText(rs.getString("lastname"));
						textFieldAge.setText(rs.getString("Age"));
					}
					pst.close();

				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		comboBox.setBounds(10, 11, 150, 23);
		contentPane.add(comboBox);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="delete from EmployeeInfo where EID='"+textFieldEID.getText()+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					JOptionPane.showMessageDialog(null, "Data Deleted");
					
					pst.close();
					
				}catch(Exception e){
					e.printStackTrace();
				}
				refreshTable();
			}
		});
		btnDelete.setBounds(89, 261, 71, 23);
		contentPane.add(btnDelete);
		
		refreshTable();
		fillComboBox();
	}
}
