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
 * Servlet implementation class ViewProduct
 */
@WebServlet("/ViewProduct")
public class ViewProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao productDao;
    private ImageDbo imageDbo;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productDao = new ProductDao();
        imageDbo = new ImageDbo();
    }

    public ViewProduct() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetching all product images
            List<ProductImage> listOfImage = imageDbo.getImageData();
            for (ProductImage image : listOfImage) {
                String base64ImageData = Base64.getEncoder().encodeToString(image.getImage_data());
                image.setBase64ImageData(base64ImageData);
            }
            request.setAttribute("imageList", listOfImage);

            // Fetching all products
            List<Product> productList = productDao.getAllProduct();
            request.setAttribute("productList", productList);

            // Forwarding to the view page
            request.getRequestDispatcher(ViewPages.PRODUCT).forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve products and images.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
