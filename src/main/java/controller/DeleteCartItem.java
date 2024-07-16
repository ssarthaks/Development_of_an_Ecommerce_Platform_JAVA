package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CartDao;

/**
 * Servlet implementation class DeleteCartItem
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RemoveCartItem" })
public class DeleteCartItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartDao cartDao;
       
	public void init() throws ServletException {
		super.init();
		cartDao = new CartDao();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCartItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(ViewPages.NO_PRODUCT_FOUND).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productModelNumber = request.getParameter("productModelNumber");
		String userEmail = request.getParameter("userEmail");
		System.out.println(userEmail);
		System.out.println(productModelNumber);
		try {
            boolean deleted = cartDao.deleteCartItem(userEmail, productModelNumber);
            if (deleted) {
                request.getRequestDispatcher(ViewPages.CART).forward(request, response);
            } else {

                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {

            e.printStackTrace();
            response.sendRedirect("error.jsp"); 
        }
	}

}
