package Assemblage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (name="CarServlet", urlPatterns = {"/cars"})
public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public CarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = "";
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		
		System.out.println("Car Servlet Action = " + action);
		
		if (action.equals("showCars")) {
			showCars(request, response);
		} 
		else if (action.equals("registerCar")) {
			registerCar(request, response);
		}
		else if (action.equals("buyCar")) {
			buyCar(request, response);
		}
		else if (action.equals("sellCar")) {
			sellCar(request, response);
		}
		else if (action.equals("stopSelling")) {
			stopSelling(request, response);
		}
		else {
			System.out.println("No action found for action: " + action);
			showCars(request, response);
		}    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/* Servlet actions */
	
	private void showCars(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<CarBean> cars = CarDAO.getAllCars();
		HttpSession session = request.getSession(true);
		session.setAttribute("cars", cars);
	    request.getRequestDispatcher("/showCars.jsp").forward(request, response);
	}
	
	private void registerCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	private void buyCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("carId"));
		
		try {
			UserBean newOwner = (UserBean) request.getSession().getAttribute("currentSessionUser");
			
			// Check so the user is logged in before proceeding:
			if(newOwner != null){
				// Logged in.
				CarDAO.changeOwner(id, newOwner);

			} else {
				// Not logged in.
			}
		} catch (Error e){
			System.out.println(e.getStackTrace());
		}
		String referer = request.getHeader("Referer");
	    response.sendRedirect(referer);
	}
	
	private void sellCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("carId"));
		
		try {
			UserBean currentUser = (UserBean) request.getSession().getAttribute("currentSessionUser");
			
			// Check so the user is logged in before proceeding:
			if(currentUser != null){
				// Logged in.
				CarBean car = CarDAO.findCarById(id);
				// Check so the user owns the car:
				if(car.getOwnerUsername().equals(currentUser.getUsername())){
					// Sell the car.
					CarDAO.sell(id);
				}

			} else {
				// Not logged in.
			}
		} catch (Error e){
			System.out.println(e.getStackTrace());
		}
		String referer = request.getHeader("Referer");
	    response.sendRedirect(referer);
	}
	
	private void stopSelling(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("carId"));
		
		try {
			UserBean currentUser = (UserBean) request.getSession().getAttribute("currentSessionUser");
			
			// Check so the user is logged in before proceeding:
			if(currentUser != null){
				// Logged in.
				CarBean car = CarDAO.findCarById(id);
				// Check so the user owns the car:
				if(car.getOwnerUsername().equals(currentUser.getUsername())){
					// Sell the car.
					CarDAO.stopSelling(id);
				}

			} else {
				// Not logged in.
			}
		} catch (Error e){
			System.out.println(e.getStackTrace());
		}
		String referer = request.getHeader("Referer");
	    response.sendRedirect(referer);
	}
}
