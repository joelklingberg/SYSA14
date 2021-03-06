package Assemblage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message = "";
		try {
			UserBean user = new UserBean();
			user.setUserName(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user = UserDAO.login(user);
			if (user.isValid()) {
				// Respond with logged in page
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", user);
				response.sendRedirect("loggedInPage.jsp"); //logged-in page
			} else {
				// Respond with invalid login page
				//response.sendRedirect("invalidLogin.jsp");
				message = "Incorrect username or password";
			}
		} catch (Error e){
			System.out.println(e.getStackTrace());
		}
		
		//Response message
				request.setAttribute("message", message);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
