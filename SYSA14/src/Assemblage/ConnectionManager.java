package Assemblage;

import java.sql.*;

public class ConnectionManager {
	static Connection con;
	String url;
	public static Connection getConnection() {
		
		try {
			 // Replace with your credentials:
			String url = "jdbc:sqlserver://localhost:1433;database=logintest;";
			String user = "admin";
			String pass = "123";
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			try {
				con = DriverManager.getConnection(url, user, pass);
				} catch (SQLException ex){ 
					ex.printStackTrace(); 
				} 
		} catch(ClassNotFoundException e) { 
			System.out.println(e); 
		} 
		return con;
	}
		
	
}