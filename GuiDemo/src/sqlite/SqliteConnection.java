package sqlite;

import java.sql.*;
import javax.swing.*;
/**
 * 
 * @author
 * This Connection class used to connect to the sqlite database.
 */
public class SqliteConnection {
	
	Connection conn = null;
	
	public static Connection dbConnector(){
		
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Temp\\_PROJECTS\\SQLite DB\\EmployeeData.sqlite");
			JOptionPane.showMessageDialog(null, "Connection Successful");
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}	

}
