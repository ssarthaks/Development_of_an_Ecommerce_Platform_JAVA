package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartItem;
import model.Product;
import service.CartDao;
import service.ProductDao;

/**
 * Servlet implementation class CartCheckout
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Checkout" })
public class CartCheckout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private CartDao cartDao;   
	 private ProductDao productDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        cartDao = new CartDao(); 
        productDao = new ProductDao();
    }
    public CartCheckout() {
        super();
       
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("email");

        try {
            if (userEmail == null) {
                // User is not logged in, send an error message
                response.setContentType("text/plain");
                response.getWriter().write("Error: User not logged in.");
                return;
            }

            // Retrieve the cart items associated with the user from the database
            List<CartItem> cartItems = cartDao.getCartItems(userEmail);

            // Check if the cart is not empty
            if (!cartItems.isEmpty()) {
                // Iterate through each cart item to update product stock
                for (CartItem cartItem : cartItems) {
                    String productModelNumber = cartItem.getProductModelNumber();
                    int quantity = cartItem.getQuantity();
                    // Fetch the product details from the database
                    Product product = productDao.getProductByModelNumber(productModelNumber);
                    // Reduce the product stock by the quantity in the cart
                    int updatedStock = product.getProductstock() - quantity;
                    // Update the product stock in the database
                    productDao.updateProductStock(productModelNumber, updatedStock);
                }

                // Call the checkoutCart method of the CartDao to process the checkout
                boolean checkoutSuccess = cartDao.checkoutCart(userEmail, cartItems);

                // Send message indicating the checkout status
                response.setContentType("text/plain");
                if (checkoutSuccess) {
                	response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Checkout successful!');");
                    out.println("window.location.href='" + request.getContextPath() + "/myorder';");
                    out.println("</script>");                } else {
                    response.getWriter().write("Checkout failed. Please try again later.");
                }
            } else {
                // Send message indicating the cart is empty
            	response.setContentType("text/html");
            	PrintWriter out = response.getWriter();
            	out.println("<script type=\"text/javascript\">");
            	out.println("alert('Your cart is empty.');");
            	out.println("window.location.href='" + request.getContextPath() + "/cart';");
            	out.println("</script>");
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during checkout
            e.printStackTrace(); // Log the exception or handle it appropriately
            // Send message indicating an error occurred
            response.setContentType("text/plain");
            response.getWriter().write("An unexpected error occurred. Please try again later.");
        }
    }

}
