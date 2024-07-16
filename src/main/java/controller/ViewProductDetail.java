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

import model.Product;
import model.ProductImage;
import service.ImageDbo;
import service.ProductDao;

/**
 * Servlet implementation class ViewProductDetail
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Detail" })
public class ViewProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductDao productDao;
	private ImageDbo imageDbo;
	
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productDao = new ProductDao();
        imageDbo = new ImageDbo();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProductDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productModelNumber = request.getParameter("productModelNumber");
        Product product = null; // Initialize product variable
        List<ProductImage> listOfImage = null;
		try {
			listOfImage = imageDbo.getImageData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (ProductImage image : listOfImage) {
            String base64ImageData = Base64.getEncoder().encodeToString(image.getImage_data());
            image.setBase64ImageData(base64ImageData);
        }
        System.out.println(productModelNumber);
        try {
            product = productDao.getProductDetails(productModelNumber);
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            e.printStackTrace(); 

            response.sendRedirect(request.getContextPath() + ViewPages.NO_PRODUCT_FOUND);
            return; // Stop further execution
        }
        
        // Set the product attribute in the request
        request.setAttribute("product", product);
        request.setAttribute("imageList", listOfImage);
        // Forward the request to the product detail page
        request.getRequestDispatcher(ViewPages.PRODUCT_DETAIL).forward(request, response);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
