package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartItem;
import model.ProductImage;
import service.CartDao;
import service.ImageDbo;

/**
 * Servlet implementation class UserCart
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Cart" })
public class UserCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CartDao cartDao;
    private ImageDbo imageDbo;
    
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	cartDao = new CartDao();
    	imageDbo = new ImageDbo();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            String userEmail = (String) session.getAttribute("email");
            if (userEmail != null) {
                try {
                    // Assuming you have a method to retrieve cart items based on user email
                    List<CartItem> cartItems = cartDao.retrieveCartItems(userEmail);
                    List<ProductImage> listOfImage = imageDbo.getImageData();
                    for (ProductImage image : listOfImage) {
                        String base64ImageData = Base64.getEncoder().encodeToString(image.getImage_data());
                        image.setBase64ImageData(base64ImageData);
                    }
                    double grandTotal = cartDao.calculateGrandTotal(cartItems);
                    request.setAttribute("imageList", listOfImage);
                    request.setAttribute("cartItems", cartItems);
                    request.setAttribute("userEmail", userEmail);
                    request.setAttribute("grandTotal", grandTotal);
                    request.getRequestDispatcher(ViewPages.CART).forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving cart items.");
                }
            } else {
                // User is not logged in, redirect to login page
                String redirectURL = request.getContextPath() + "/login";
                String alertScript = "<script>alert('User is not logged in'); window.location='" + redirectURL + "'</script>";
                response.getWriter().println(alertScript);
            }
        } else {
            // Session is not active, redirect to login page
            String redirectURL = request.getContextPath() + "/login";
            String alertScript = "<script>alert('User is not logged in'); window.location='" + redirectURL + "'</script>";
            response.getWriter().println(alertScript);
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
