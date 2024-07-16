package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Order;
import service.CartDao;

/**
 * Servlet implementation class UserOrder
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/myorder" })
public class UserOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CartDao cartDao;
	  public void init() throws ServletException {
	        super.init();
	        cartDao = new CartDao();
	    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userEmail = (String) session.getAttribute("email");
            if (userEmail != null) {
                try {
                    List<Order> userOrders = cartDao.getUserOrders(userEmail);
                    request.setAttribute("userOrders", userOrders);
                    request.getRequestDispatcher(ViewPages.ORDER).forward(request, response);
                    return; // Exit the method after forwarding
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle database error
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    return; // Exit the method after handling the error
                }
            }
        }
        // If user is not logged in or session is not active, send alert and redirect to login page
        String alertScript = "<script>alert('Please login to view your orders.'); window.location='" 
                + request.getContextPath() + "/login'</script>";
        response.getWriter().println(alertScript);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
