package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

import model.Product;
import service.ProductDao;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateProductdetail" })
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductDao productDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init() throws ServletException {
        super.init();
        productDao = new ProductDao();
    }
    public UpdateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productModelNumber = request.getParameter("productModelNumber");
        // Set the productModelNumber as an attribute in the request
        request.setAttribute("productModelNumber", productModelNumber);
        // Forward the request to updateProductForm.jsp
        request.getRequestDispatcher(ViewPages.UPDATE_PRODUCT).forward(request, response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve updated product details from the form
        String productModelNumber = request.getParameter("productModelNumber");
        String productName = request.getParameter("productName");
        String productSize = request.getParameter("productSize");
        String productSwitch = request.getParameter("productSwitch");
        int productStock = Integer.parseInt(request.getParameter("productStock"));
        int productPrice = Integer.parseInt(request.getParameter("productPrice"));
        String productDescription = request.getParameter("productDescription");
        String productFeature = request.getParameter("productFeature");
        String productBox = request.getParameter("productBox");
        String productDownload = request.getParameter("productDownload");
        // Retrieve other product details similarly
        
        try {
            // Retrieve the product based on the model number
            Product product = productDao.getProductDetails(productModelNumber);
            // Update the product details
            product.setProductname(productName);
            product.setProductsize(productSize);
            product.setProductswitch(productSwitch);
            product.setProductstock(productStock);
            product.setProdutprice(productPrice);
            product.setProductdescription(productDescription);
            product.setProductfeature(productFeature);
            product.setProductbox(productBox);
            product.setProductdownload(productDownload);
            // Update other product details similarly
            // Save the updated product
            boolean success = productDao.updateProduct(product);
            if (success) {
            	request.getRequestDispatcher(ViewPages.ADMIN_DASHBOARD).forward(request, response);
            } else {
                // Handle update failure
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
}
