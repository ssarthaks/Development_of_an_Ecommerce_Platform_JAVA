package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import model.CartItem;
import model.Order;
import model.OrderDetail;
import utils.DatabaseConnectivity;

public class CartDao {
    public List<CartItem> retrieveCartItems(String userEmail) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnectivity.getDbConnection();
            String sql = "SELECT ci.productModelNumber, ci.productPrice, ci.quantity FROM cart_items ci INNER JOIN cart c ON ci.cartID = c.cartID WHERE c.userEmail = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userEmail);
            rs = stmt.executeQuery();
            while (rs.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setProductModelNumber(rs.getString("productModelNumber"));
                cartItem.setProductPrice(rs.getInt("productPrice"));
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItems.add(cartItem);
            }
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return cartItems;
    }
    public boolean deleteCartItem(String userEmail, String productModelNumber) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            try {
				conn = DatabaseConnectivity.getDbConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String sql = "DELETE FROM cart_items WHERE cartID IN (SELECT cartID FROM cart WHERE userEmail = ?) AND productModelNumber = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userEmail);
            stmt.setString(2, productModelNumber);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
		return false;
    }
    public List<CartItem> getCartItems(String userEmail) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnectivity.getDbConnection(); // Implement getConnection() method to get database connection
            String sql = "SELECT ci.cartID, ci.productModelNumber, ci.quantity, ci.productPrice "
                + "FROM cart_items ci "
                + "INNER JOIN cart c ON ci.cartID = c.cartID "
                + "WHERE c.userEmail = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userEmail);
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Create CartItem object for each row and add to the list
                CartItem cartItem = new CartItem();
                cartItem.setProductModelNumber(rs.getString("productModelNumber"));
                cartItem.setProductPrice(rs.getInt("productPrice"));
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItems.add(cartItem);
            }
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // Close resources
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return cartItems;
    }

    public boolean checkoutCart(String userEmail, List<CartItem> cartItems) throws SQLException {
        Connection conn = null;
        PreparedStatement moveItemsStmt = null;
        PreparedStatement createOrderStmt = null;
        PreparedStatement clearCartStmt = null;
        ResultSet generatedKeys = null; // To retrieve the generated orderID
        try {
            conn = DatabaseConnectivity.getDbConnection();

            // Calculate total amount
            int totalAmount = calculateTotalAmount(cartItems);

            // Move specific items from cart to order_details table
            String moveItemsSql = "INSERT INTO order_details (orderID, productModelNumber, quantity, productPrice) " +
                                  "VALUES (?, ?, ?, ?)";
            moveItemsStmt = conn.prepareStatement(moveItemsSql);

            // Create a new order record and retrieve the generated orderID
            String createOrderSql = "INSERT INTO orders (userEmail, totalAmount) VALUES (?, ?)";
            createOrderStmt = conn.prepareStatement(createOrderSql, Statement.RETURN_GENERATED_KEYS);
            createOrderStmt.setString(1, userEmail);
            createOrderStmt.setInt(2, totalAmount);
            int rowsAffected = createOrderStmt.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated orderID
                generatedKeys = createOrderStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int orderID = generatedKeys.getInt(1); // Assuming orderID is the first column
                    // Iterate through cart items and move them to order_details
                    for (CartItem item : cartItems) {
                        moveItemsStmt.setInt(1, orderID);
                        moveItemsStmt.setString(2, item.getProductModelNumber());
                        moveItemsStmt.setInt(3, item.getQuantity());
                        moveItemsStmt.setDouble(4, item.getProductPrice());
                        moveItemsStmt.addBatch(); // Add the current item to the batch
                    }
                    // Execute batch insert for all cart items
                    moveItemsStmt.executeBatch();

                    // Clear cart items
                    String clearCartSql = "DELETE FROM cart_items WHERE cartID IN (SELECT cartID FROM cart WHERE userEmail = ?)";
                    clearCartStmt = conn.prepareStatement(clearCartSql);
                    clearCartStmt.setString(1, userEmail);
                    clearCartStmt.executeUpdate();
                    return true;
                } else {
                    // Failed to retrieve the generated orderID
                    return false;
                }
            } else {
                // Failed to insert the new order
                return false;
            }
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // Close resources
            if (generatedKeys != null) {
                generatedKeys.close();
            }
            if (moveItemsStmt != null) {
                moveItemsStmt.close();
            }
            if (createOrderStmt != null) {
                createOrderStmt.close();
            }
            if (clearCartStmt != null) {
                clearCartStmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
		return false;
    }

    private int calculateTotalAmount(List<CartItem> cartItems) {
        int totalAmount = 0;
        for (CartItem item : cartItems) {
            totalAmount += item.getQuantity() * item.getProductPrice();
        }
        return totalAmount;
    }

    public List<Order> getAllOrdersWithItems() throws SQLException {
        List<Order> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnectivity.getDbConnection();
            String sql = "SELECT o.*, od.productModelNumber, od.quantity, od.productPrice " +
                         "FROM orders o " +
                         "INNER JOIN order_details od ON o.orderID = od.orderID";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                Order order = getOrderFromResultSet(rs);

                // Check if the order is already in the list
                Order existingOrder = getOrderById(orders, orderID);
                if (existingOrder != null) {
                    // If order already exists, add order details to its list
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setProductModelNumber(rs.getString("productModelNumber"));
                    orderDetail.setQuantity(rs.getInt("quantity"));
                    orderDetail.setProductPrice(rs.getInt("productPrice"));
                    existingOrder.getOrderDetails().add(orderDetail);
                } else {
                    // If order doesn't exist, add it to the list along with order details
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setProductModelNumber(rs.getString("productModelNumber"));
                    orderDetail.setQuantity(rs.getInt("quantity"));
                    orderDetail.setProductPrice(rs.getInt("productPrice"));
                    order.getOrderDetails().add(orderDetail);
                    orders.add(order);
                }
            }
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return orders;
    }

    private Order getOrderFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("orderID"));
        order.setUserEmail(rs.getString("userEmail"));
        order.setOrderDate(rs.getDate("orderDate"));
        order.setTotalAmount(rs.getDouble("totalAmount"));
        return order;
    }

    private Order getOrderById(List<Order> orders, int orderID) {
        for (Order order : orders) {
            if (order.getOrderId() == orderID) {
                return order;
            }
        }
        return null;
    }
    public boolean deleteOrder(int orderId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseConnectivity.getDbConnection();
            String sql = "DELETE FROM orders WHERE orderId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderId);
            
            // Execute the delete query
            int rowsAffected = stmt.executeUpdate();
            
            // Return true if rows are affected (order is deleted), false otherwise
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Return false if an exception occurs during deletion
            return false;
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // Close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return false;
    }
    public List<Order> getUserOrders(String userEmail) throws SQLException {
        List<Order> userOrders = new ArrayList<>();
        String sql = "SELECT o.orderId, o.orderDate, o.totalAmount, od.productModelNumber, od.quantity, od.productPrice " +
                     "FROM orders o " +
                     "INNER JOIN order_details od ON o.orderId = od.orderId " +
                     "WHERE o.userEmail = ?";
        
        try (Connection conn = DatabaseConnectivity.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, userEmail);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int orderId = rs.getInt("orderId");
                    Order existingOrder = getOrderById(userOrders, orderId);
                    
                    if (existingOrder == null) {
                        // If order doesn't exist, create a new order object
                        Order order = new Order();
                        order.setOrderId(orderId);
                        order.setOrderDate(rs.getDate("orderDate"));
                        order.setTotalAmount(rs.getDouble("totalAmount"));
                        userOrders.add(order);
                        existingOrder = order;
                    }
                    
                    // Create a new order detail and add it to the order's orderDetails list
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setProductModelNumber(rs.getString("productModelNumber"));
                    orderDetail.setQuantity(rs.getInt("quantity"));
                    orderDetail.setProductPrice(rs.getInt("productPrice"));
                    existingOrder.getOrderDetails().add(orderDetail);
                }
            }
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return userOrders;
    }
    public double calculateGrandTotal(List<CartItem> cartItems) {
        double grandTotal = 0.0;
        for (CartItem item : cartItems) {
            grandTotal += item.getQuantity() * item.getProductPrice();
        }
        return grandTotal;
    }
    public int getOrderCount() {
        int count = 0;
        String query = "SELECT COUNT(*) AS order_count FROM orders";
        try (Connection conn = DatabaseConnectivity.getDbConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                count = resultSet.getInt("order_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return count;
    }
}


