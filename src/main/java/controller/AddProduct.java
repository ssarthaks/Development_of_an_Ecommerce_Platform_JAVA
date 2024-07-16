package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Product;
import model.ProductImage;
import service.ImageDbo;
import service.ProductDao;

@WebServlet("/addProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
                 maxFileSize = 1024 * 1024 * 10,      // 10 MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50 MB
public class AddProduct extends HttpServlet {
    private ProductDao productDao;
    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productDao = new ProductDao();  // Initialize the DAO. Consider using Dependency Injection.
    }

    public AddProduct() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(ViewPages.ADD_PRODUCT).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve product information from form data
            String productName = request.getParameter("productName");
            String productModelNumber = request.getParameter("productModelNumber");
            String productSize = request.getParameter("productSize");
            String productSwitch = request.getParameter("productSwitch");
            int productStock = Integer.parseInt(request.getParameter("productStock"));
            int productPrice = Integer.parseInt(request.getParameter("productPrice"));
            String productDescription = request.getParameter("productDescription");
            String productFeature = request.getParameter("productFeature");
            String productBox = request.getParameter("productBox");
            String productDownload = request.getParameter("productDownload");

            // Initialize product object and set its properties
            Product product = new Product();
            product.setProductname(productName);
            product.setProductmodelnumber(productModelNumber);
            product.setProductsize(productSize);
            product.setProductswitch(productSwitch);
            product.setProductstock(productStock);
            product.setProdutprice(productPrice);
            product.setProductdescription(productDescription);
            product.setProductfeature(productFeature);
            product.setProductbox(productBox);
            product.setProductdownload(productDownload);

            // Process the image upload
            Part filePart = request.getPart("image");
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            try (InputStream imageStream = filePart.getInputStream()) {
                byte[] data = new byte[4096];
                int bytesRead;
                while ((bytesRead = imageStream.read(data)) != -1) {
                    buffer.write(data, 0, bytesRead);
                }
            }
            byte[] imageData = buffer.toByteArray();

            ProductImage image = new ProductImage();
            image.setImage_data(imageData);
            image.setImage_name(productModelNumber);  // Use model number as the image name

            ImageDbo dbo = new ImageDbo();
            int row = dbo.insert(image);
            if (row <= 0) {
                throw new SQLException("Failed to insert image.");
            }

            // Save the product
            boolean isSaved = productDao.saveProduct(product);
            if (!isSaved) {
                throw new SQLException("Failed to save product details.");
            }

            response.sendRedirect(request.getContextPath() + "/ViewProduct");
        } catch (Exception e) {
            request.setAttribute("error", "Error processing request: " + e.getMessage());
            request.getRequestDispatcher(ViewPages.ADD_PRODUCT).forward(request, response);
        }
    }
}
