package Assemblage;

import java.text.*;
import java.util.*;
import java.sql.*;

public class UserDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;

	public static UserBean login(UserBean bean) {
		//preparing some objects for connection
		Statement stmt = null;
		String username = bean.getUsername();
		String password = bean.getPassword();
		String searchQuery = "SELECT * FROM users WHERE username ='" + username + "' AND password='" + password + "'";
		
		System.out.println("Your user name is " + username);
		System.out.println("Your password is " + password);
		System.out.println("Query: " + searchQuery);
		try {
			//connect to DB
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();
			
			if (!more) {
				// if user does not exist set the isValid variable to false
				System.out.println("Sorry, you are not a registered user! Please sign up first");
				bean.setValid(false);
			} else if (more) {
				//if user exists set the isValid variable to true
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				System.out.println("Welcome " + firstName);
				bean.setFirstName(firstName);
				bean.setLastName(lastName);
				bean.setValid(true);
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} finally {
			// Exception handling
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					
				}
				rs = null;
			}
			
			if (stmt != null) {
				try {
					stmt.close(); 
				} catch (Exception e) {
				
				}
			stmt = null; 
			}
			
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
					
				}
				currentCon = null;
			}
		}
		return bean;
	}
}
