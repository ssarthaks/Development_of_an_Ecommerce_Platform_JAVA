package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import model.Order;
import service.CartDao;


@WebServlet(asyncSupported = true, urlPatterns = { "/AdminOrder" })
public class AdminOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartDao cartDao;

    public void init() throws ServletException {
        this.cartDao = new CartDao(); // Assuming CartDao is your DAO class
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve all orders with their associated items from the database
            List<Order> orders = cartDao.getAllOrdersWithItems();

            // Set the orders as an attribute in the request
            request.setAttribute("orders", orders);

            // Forward the request and response to the orders.jsp page for rendering
            request.getRequestDispatcher(ViewPages.ADMIN_ORDER).forward(request, response);
        } catch (SQLException e) {
            // Log the exception or redirect to an error page
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching orders.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
