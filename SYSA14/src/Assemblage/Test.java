package Assemblage;

import javax.servlet.http.HttpSession;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UserBean user = new UserBean();
			user.setUserName("joel");
			user.setPassword("123");
			user = UserDAO.login(user);
			if (user.isValid()) {
				// Respond with logged in page
				System.out.println("Logged in");
			} else {
				// Respond with invalid login page
				System.out.println("Not logged in");
			}
		} catch (Error e){
			System.out.println(e.getStackTrace());
		}
	}

}
