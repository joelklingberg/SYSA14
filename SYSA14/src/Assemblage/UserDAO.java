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
	
	public static String register(UserBean bean) {
		//preparing some objects for connection
		Statement stmt = null;
		String username = bean.getUsername();
		String password = bean.getPassword();
		String firstName = bean.getFirstName();
		String lastName = bean.getLastName();
		
		String insertQuery = "INSERT INTO users (username, password, firstName, lastName) VALUES ('" + username + "', '" + password
				+ "', '" + firstName + "', '" + lastName + "')";
		
		System.out.println("Your user name is " + username);
		System.out.println("Your password is " + password);
		System.out.println("Your firstname is " + firstName);
		System.out.println("Your lastname is " + lastName);
		System.out.println("Query: " + insertQuery);
		
		String message = "";
		try {
			//connect to DB
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.execute(insertQuery);
			message = "Successfully registered user.";

		} catch (SQLException ex) {
			System.out.println("Errorcode " + ex.getErrorCode() + ": Registering user failed: An Exception has occurred! " + ex);
			int errorcode = ex.getErrorCode();
			
			switch(errorcode){
				case 2601: message = "User with that username already exists. Please pick another username.";
				break;
				case 2627: message = "User with that username already exists. Please pick another username.";
				break;
				default: message = "Error registering user";
				break;
			}
			
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
		return message;
	}
	
	public static UserBean findByUsername(String username) {
		//preparing some objects for connection
		Statement stmt = null;
		UserBean user = new UserBean();
		
		String findQuery = "SELECT * FROM users WHERE username = '" + username + "';";
		
		System.out.println("Find by username query: " + findQuery);

		try {
			//connect to DB
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			ResultSet rs = stmt.executeQuery(findQuery);
			
			while(rs.next()){
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setPassword(rs.getString("password"));
				user.setUserName(rs.getString("username"));
			}

		} catch (SQLException ex) {
			System.out.println("Errorcode " + ex.getErrorCode() + ": Find user by username failed: An Exception has occurred! " + ex);
			int errorcode = ex.getErrorCode();
			
			/*
			switch(errorcode){
				case 2601: message = "User with that username already exists. Please pick another username.";
				break;
				case 2627: message = "User with that username already exists. Please pick another username.";
				break;
				default: message = "Error registering user";
				break;
			}
			*/
			
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
		return user;
	}
}
