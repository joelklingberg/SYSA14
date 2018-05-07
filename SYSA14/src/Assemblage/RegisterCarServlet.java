package Assemblage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterCarServlet")
public class RegisterCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		try {
			UserBean owner = (UserBean) request.getSession().getAttribute("currentSessionUser");
			CarBean car = new CarBean();
			String brand = request.getParameter("brand");
			int price = Integer.parseInt(request.getParameter("price"));
			boolean forSale = false;
			String year = request.getParameter("year");
			
			// Check so the user is logged in before proceeding:
			if(owner != null){
				// Logged in.
				car.setOwner(owner);
				car.setBrand(brand);
				car.setPrice(price);
				car.setForSale(forSale);
				car.setYear(year);
				
				message = CarDAO.register(car);
			} else {
				// Not logged in.
				message = "Not logged in.";
			}
		} catch (Error e){
			System.out.println(e.getStackTrace());
		}
		
		//Response message
		request.setAttribute("message", message);
		request.getRequestDispatcher("/registerCar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
