package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CartDao;
import service.ProductDao;
import service.UserDao;

/**
 * Servlet implementation class AdminDashboard
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AdminDashboard" })
public class AdminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
        int userCount = userDao.getUserCount();
        
        ProductDao productDao = new ProductDao();
        int productCount = productDao.getProductCount();
        
        CartDao orderDao = new CartDao();
        int orderCount = orderDao.getOrderCount();
        
        // Set attributes to pass counts to JSP
        request.setAttribute("userCount", userCount);
        request.setAttribute("productCount", productCount);
        request.setAttribute("orderCount", orderCount);
        
        request.getRequestDispatcher(ViewPages.ADMIN_DASHBOARD).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
