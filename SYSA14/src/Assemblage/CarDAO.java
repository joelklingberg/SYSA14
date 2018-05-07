package Assemblage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	
	public static String register(CarBean bean) {
		//preparing some objects for connection
		Statement stmt = null;
		String owner = bean.getOwnerUsername();
		String brand = bean.getBrand();
		String year = bean.getYear();
		int price = bean.getPrice();
		String description = bean.getDescription();
		
		String insertQuery = "INSERT INTO cars (owner, brand, year, price, forsale, description) VALUES ('" + owner + "', '" + brand
				+ "', '" + year + "', '" + price + "', 0" + ", '" + description + "')";
		
		System.out.println("Query: " + insertQuery);
		
		String message = "";
		try {
			//connect to DB
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.execute(insertQuery);
			message = "Successfully registered car.";

		} catch (SQLException ex) {
			System.out.println("Errorcode " + ex.getErrorCode() + ": Registering car failed: An Exception has occurred! " + ex);
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
		return message;
	}
	
	public static ArrayList<CarBean> getAllCars() {
		// Preparing some objects for connection.
		Statement stmt = null;
	
		String query = "SELECT * FROM cars";

		ArrayList<CarBean> cars = new ArrayList<CarBean>();
		try {
			//connect to DB
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				// Found cars in database.
				String username = rs.getString("owner");
				UserBean owner = UserDAO.findByUsername(username);
				CarBean car = new CarBean();
				
				int id = rs.getInt("id");
				String brand = rs.getString("brand");
				int price = rs.getInt("price");
				String year = rs.getString("year");
				boolean forSale = rs.getBoolean("forsale");
				String description = rs.getString("description");
				
				car.setId(id);
				car.setOwner(owner);
				car.setBrand(brand);
				car.setPrice(price);
				car.setYear(year);
				car.setForSale(forSale);
				car.setDescription(description);
				
				cars.add(car);
			}

		} catch (SQLException ex) {
			System.out.println("Errorcode " + ex.getErrorCode() + ": Getting cars failed: An Exception has occurred! " + ex);
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
		return cars;
	}

	public static CarBean findCarById(int id) {
		// Preparing some objects for connection.
				Statement stmt = null;
			
				String query = "SELECT * FROM cars WHERE id = " + id;
				CarBean car = new CarBean();
				try {
					//connect to DB
					currentCon = ConnectionManager.getConnection();
					stmt = currentCon.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					while(rs.next()){
						// Found car in database.
						String username = rs.getString("owner");
						UserBean owner = UserDAO.findByUsername(username);
						
						int carId = rs.getInt("id");
						String brand = rs.getString("brand");
						int price = rs.getInt("price");
						String year = rs.getString("year");
						boolean forSale = rs.getBoolean("forsale");
						String description = rs.getString("description");
						
						car.setOwner(owner);
						car.setBrand(brand);
						car.setPrice(price);
						car.setYear(year);
						car.setForSale(forSale);
						car.setId(carId);
						car.setDescription(description);
					}

				} catch (SQLException ex) {
					System.out.println("Errorcode " + ex.getErrorCode() + ": Getting cars failed: An Exception has occurred! " + ex);
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
				return car;
	}
	
	public static ArrayList<CarBean> findCarsOwnedBy(String username) {
		// Preparing some objects for connection.
				Statement stmt = null;
			
				String query = "SELECT * FROM cars WHERE owner = '" + username + "';";
				ArrayList<CarBean> cars = new ArrayList<CarBean>();
				try {
					//connect to DB
					currentCon = ConnectionManager.getConnection();
					stmt = currentCon.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					
					while(rs.next()){
						// Found car in database.
						CarBean car = new CarBean();
						String ownerUsername = rs.getString("owner");
						UserBean owner = UserDAO.findByUsername(ownerUsername);
						
						int carId = rs.getInt("id");
						String brand = rs.getString("brand");
						int price = rs.getInt("price");
						String year = rs.getString("year");
						boolean forSale = rs.getBoolean("forSale");
						String description = rs.getString("description");
						
						car.setOwner(owner);
						car.setBrand(brand);
						car.setPrice(price);
						car.setYear(year);
						car.setForSale(forSale);
						car.setId(carId);
						car.setDescription(description);
						
						cars.add(car);
					}

				} catch (SQLException ex) {
					System.out.println("Errorcode " + ex.getErrorCode() + ": Getting cars failed: An Exception has occurred! " + ex);
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
				return cars;
	}
	
	public static void changeOwner(int id, UserBean newOwner) {
		// Preparing some objects for connection.
				Statement stmt = null;
			
				String query = "UPDATE cars SET owner = '" + newOwner.getUsername() + "' FROM cars WHERE id = " + id + ";";
				try {
					//connect to DB
					currentCon = ConnectionManager.getConnection();
					stmt = currentCon.createStatement();
					stmt.execute(query);
					// Stop selling the car after changing owner:
					stopSelling(id);

				} catch (SQLException ex) {
					System.out.println("Errorcode " + ex.getErrorCode() + ": Getting cars failed: An Exception has occurred! " + ex);
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
	}
	
	public static void sell(int id) {
		// Preparing some objects for connection.
				Statement stmt = null;
			
				String query = "UPDATE cars SET forsale = 1 FROM cars WHERE id = " + id + ";";
				try {
					//connect to DB
					currentCon = ConnectionManager.getConnection();
					stmt = currentCon.createStatement();
					stmt.execute(query);

				} catch (SQLException ex) {
					System.out.println("Errorcode " + ex.getErrorCode() + ": Getting cars failed: An Exception has occurred! " + ex);
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
	}
	
	public static void stopSelling(int id) {
		// Preparing some objects for connection.
				Statement stmt = null;
			
				String query = "UPDATE cars SET forsale = 0 FROM cars WHERE id = " + id + ";";
				try {
					//connect to DB
					currentCon = ConnectionManager.getConnection();
					stmt = currentCon.createStatement();
					stmt.execute(query);

				} catch (SQLException ex) {
					System.out.println("Errorcode " + ex.getErrorCode() + ": Getting cars failed: An Exception has occurred! " + ex);
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
	}
	
	public static void deleteCar(int id) {
		// Preparing some objects for connection.
				Statement stmt = null;
			
				String query = "DELETE FROM cars WHERE id = " + id + ";";
				try {
					//connect to DB
					currentCon = ConnectionManager.getConnection();
					stmt = currentCon.createStatement();
					stmt.execute(query);

				} catch (SQLException ex) {
					System.out.println("Errorcode " + ex.getErrorCode() + ": Deleting car failed: An Exception has occurred! " + ex);
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
	}
	
	public static String editCar(int id, CarBean newCar) {
		//preparing some objects for connection
		Statement stmt = null;
		
		CarBean car = CarDAO.findCarById(id);
		String message = "";
		if(car != null){
			// Found car.
			String editQuery = "UPDATE cars SET brand = '" + newCar.getBrand() + "', year = '" + newCar.getYear() 
					+ "', price = '" + newCar.getPrice() + "', description = '" + newCar.getDescription() + "' WHERE id = " + id + ";";
			System.out.println("Query: " + editQuery);
			
			
			
			try {
				//connect to DB
				currentCon = ConnectionManager.getConnection();
				stmt = currentCon.createStatement();
				stmt.execute(editQuery);
				message = "Successfully updated car details.";

			} catch (SQLException ex) {
				System.out.println("Errorcode " + ex.getErrorCode() + ": Updating car failed: An Exception has occurred! " + ex);
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
			return message;
			
		} else {
			// Could not find car.
			message = "Could not find car.";
			return message;
		}
	}
	
}
