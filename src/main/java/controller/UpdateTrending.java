package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProductDao;

/**
 * Servlet implementation class UpdateTrending
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Trending" })
public class UpdateTrending extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTrending() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String productModelNumber = request.getParameter("productModelNumber");
        boolean isTrending = request.getParameter("isTrending") != null;
        
        ProductDao productDao = new ProductDao();
        boolean success = productDao.updateTrending(productModelNumber, isTrending);

        if (success) {
            // If the update is successful, redirect back to the admin panel
            response.sendRedirect(request.getContextPath() + "/AdminProduct");
        } else {
            // If the update fails, display an error message
            response.getWriter().println("Failed to update trending status.");
        }
	}

}
