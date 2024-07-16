package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

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
 * Servlet implementation class SearchProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Search" })
public class SearchProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao productDao;
	private ImageDbo imageDbo;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init() throws ServletException {
        super.init();
        productDao = new ProductDao();
        imageDbo = new ImageDbo();
    }
    public SearchProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("search");
		try {
			List<Product> searchResult = productDao.searchProducts(query);
			if(searchResult.isEmpty()) {
				request.setAttribute("searchResultEmpty", true);
				request.getRequestDispatcher(ViewPages.SEARCH_RESULT).forward(request, response);
			}
			else {
	            List<ProductImage> listOfImage = imageDbo.getImageData();
	            for (ProductImage image : listOfImage) {
	                String base64ImageData = Base64.getEncoder().encodeToString(image.getImage_data());
	                image.setBase64ImageData(base64ImageData);
	            }
				request.setAttribute("searchResult", searchResult);
				request.setAttribute("imageList", listOfImage);
				request.getRequestDispatcher(ViewPages.SEARCH_RESULT).forward(request, response);
			}
		} catch (SQLException e) {
			System.out.println("exception"+e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
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
