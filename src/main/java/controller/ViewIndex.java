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

@WebServlet(asyncSupported = true, urlPatterns = { "/ViewIndex" })
public class ViewIndex extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao productDao;
    private ImageDbo imageDbo;

    public void init() throws ServletException {
        super.init();
        productDao = new ProductDao();
        imageDbo = new ImageDbo();
    }

    public ViewIndex() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> productList = productDao.getAllProduct();
            List<ProductImage> imageList = imageDbo.getImageData();

            // Encode image data as Base64 strings
            for (ProductImage image : imageList) {
                String base64ImageData = Base64.getEncoder().encodeToString(image.getImage_data());
                image.setBase64ImageData(base64ImageData);
            }

            request.setAttribute("productList", productList);
            request.setAttribute("imageList", imageList);
            request.getRequestDispatcher(ViewPages.INDEX).forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
