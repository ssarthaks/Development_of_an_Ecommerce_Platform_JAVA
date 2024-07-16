package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CartDao;

/**
 * Servlet implementation class DeleteOrder
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/DeleteOrder" })
public class DeleteOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CartDao cartDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init() throws ServletException {
        super.init();
        cartDao = new CartDao();
    }
    public DeleteOrder() {
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
        // Retrieve orderId parameter from the request
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        
        // Call the deleteOrder method from CartDao
        boolean deleted = cartDao.deleteOrder(orderId);
        
        // Optionally handle the result
        if (deleted) {
            // Order successfully deleted
        	response.sendRedirect(request.getContextPath() + "/AdminOrder");
        } else {
            // Order deletion failed
            response.getWriter().write("Failed to delete order!");
        }
    }

}
