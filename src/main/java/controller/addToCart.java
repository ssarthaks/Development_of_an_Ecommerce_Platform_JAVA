package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.DatabaseConnectivity;

@WebServlet("/addToCart")
public class addToCart extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.sendRedirect(request.getContextPath() + ViewPages.NO_PRODUCT_FOUND);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String userEmail = (String) session.getAttribute("email"); // Assuming user email is stored in the session
        String productModelNumber = request.getParameter("productmodelnumber");
        int productPrice = Integer.parseInt(request.getParameter("productprice"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        if (userEmail == null) {
            // Send an alert and redirect to login page
            String alertScript = "<script>alert('Please login to add products to your cart.'); window.location='" 
                + request.getContextPath() + "/login'</script>";
            response.getWriter().println(alertScript);
            return;
        }

        Connection conn = null;
        try {
            conn = DatabaseConnectivity.getDbConnection();
            int cartId = ensureCartExists(conn, userEmail);
            addProductToCart(conn, cartId, productModelNumber, productPrice,quantity);
            response.sendRedirect(request.getContextPath() + "/ViewProduct");
        } catch (SQLException ex) {
            throw new ServletException("Database error", ex);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private int ensureCartExists(Connection conn, String userEmail) throws SQLException {
        int cartId = findCartId(conn, userEmail);
        if (cartId == -1) {
            // No cart exists, so create a new one
            cartId = createCart(conn, userEmail);
        }
        return cartId;
    }

    private int findCartId(Connection conn, String userEmail) throws SQLException {
        String sql = "SELECT cartID FROM cart WHERE userEmail = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userEmail);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("cartID");
            }
        }
        return -1;
    }

    private int createCart(Connection conn, String userEmail) throws SQLException {
        String sql = "INSERT INTO cart (userEmail) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, userEmail);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            throw new SQLException("Creating cart failed, no ID obtained.");
        }
    }

    private void addProductToCart(Connection conn, int cartId, String productModelNumber, double productPrice, int quantity) throws SQLException {
        String sql = "INSERT INTO cart_items (cartID, productModelNumber, productPrice, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cartId);
            stmt.setString(2, productModelNumber);
            stmt.setDouble(3, productPrice);
            stmt.setInt(4, quantity); // Added this line to set the quantity
            stmt.executeUpdate();
        }
    }

    }
    

