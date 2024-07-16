package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import utils.DatabaseConnectivity;

public class ProductDao {
	 private Connection conn;

	 public ProductDao() {
		    try {
				this.conn = DatabaseConnectivity.getDbConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public boolean saveProduct(Product product) {
		    try {
		        if (!isProductExists(product)) {
		            return insertProductData(product);
		        } else {
		            return false; // Product already exists
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}

		public boolean insertProductData(Product product) throws SQLException {
		    String query = "INSERT INTO product(productName, productModelNumber, productSize, productSwitch, productStock, productPrice, productDescription, productFeature, productBox, productDownload) VALUES (?,?,?,?,?,?,?,?,?,?)";
		    try (PreparedStatement statement = conn.prepareStatement(query)) {
		        statement.setString(1, product.getProductname());
		        statement.setString(2, product.getProductmodelnumber());
		        statement.setString(3, product.getProductsize());
		        statement.setString(4, product.getProductswitch());
		        statement.setInt(5, product.getProductstock());
		        statement.setInt(6, product.getProdutprice()); // Corrected method name
		        statement.setString(7, product.getProductdescription());
		        statement.setString(8, product.getProductfeature());
		        statement.setString(9, product.getProductbox());
		        statement.setString(10, product.getProductdownload());
		        int rowsInserted = statement.executeUpdate();
		        return rowsInserted > 0;	
		    }
		}


		private boolean isProductExists(Product product) throws SQLException {
		    String query = "SELECT COUNT(*) FROM product WHERE ProductModelNumber=?";
		    try (PreparedStatement statement = conn.prepareStatement(query)) {
		        statement.setString(1, product.getProductmodelnumber());
		        try (ResultSet resultSet = statement.executeQuery()) {
		            if (resultSet.next()) {
		                return resultSet.getInt(1) > 0;
		            }
		        }
		    }
		    return false;
		}

		public List<Product> getAllProduct() {
	        List<Product> productList = new ArrayList<>();
	        try {
	            String query = "SELECT * FROM product";
	            try (PreparedStatement statement = conn.prepareStatement(query)) {
	                ResultSet resultSet = statement.executeQuery();
	                while (resultSet.next()) {
	                    Product product = new Product();
	                    product.setProductname(resultSet.getString("productName"));
	                    product.setProductmodelnumber(resultSet.getString("productModelNumber"));
	                    product.setProductsize(resultSet.getString("productSize"));
	                    product.setProductswitch(resultSet.getString("productSwitch"));
	                    product.setProductstock(resultSet.getInt("productStock"));
	                    product.setProdutprice(resultSet.getInt("productPrice"));
	                    product.setProductdescription(resultSet.getString("productDescription"));
	                    product.setProductfeature(resultSet.getString("productFeature"));
	                    product.setProductbox(resultSet.getString("productBox"));
	                    product.setProductdownload(resultSet.getString("productDownload"));
	                    product.setTrending(resultSet.getBoolean("is_trending"));
	                    System.out.println(resultSet.getBoolean("is_trending"));
	                    productList.add(product);
	                    
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return productList;
	    }

	    public boolean deleteProduct(String productmodelnumber) {
	        String query = "DELETE FROM product WHERE productModelNumber=?";
	        try (PreparedStatement statement = conn.prepareStatement(query)) {
	            statement.setString(1, productmodelnumber);
	            int rowsDeleted = statement.executeUpdate();
	            return rowsDeleted > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    public List<Product> searchProducts(String query) throws SQLException {
	        List<Product> searchResults = new ArrayList<>();
	        try (Connection conn = DatabaseConnectivity.getDbConnection();
	             PreparedStatement statement = conn.prepareStatement("SELECT * FROM product WHERE productName LIKE ?")) {
	            statement.setString(1, "%" + query + "%");
	            try (ResultSet resultset = statement.executeQuery()) {
	                while (resultset.next()) {
	                    Product product = new Product();
	                    product.setProdutprice(resultset.getInt("productprice"));
	                    product.setProductmodelnumber(resultset.getString("productmodelnumber"));
	                    product.setProductname(resultset.getString("productName"));
	                    // Set other product fields as needed
	                    searchResults.add(product);
	                }
	            }
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return searchResults;
	    }
	    public boolean updateTrending(String productModelNumber, boolean isTrending) {
	        String query = "UPDATE product SET is_trending = ? WHERE productModelNumber = ?";
	        try (
	             PreparedStatement statement = conn.prepareStatement(query)) {
	            statement.setBoolean(1, isTrending);
	            statement.setString(2, productModelNumber);
	            int rowsUpdated = statement.executeUpdate();
	            return rowsUpdated > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    public List<Product> getTrendingProducts() {
	        List<Product> trendingProducts = new ArrayList<>();
	        String query = "SELECT * FROM product WHERE is_trending = 1";

	        try (PreparedStatement statement = conn.prepareStatement(query)) {
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                Product product = new Product();
	                product.setProductname(resultSet.getString("productName"));
	                product.setProductdescription(resultSet.getString("productDescription"));
	                product.setProdutprice(resultSet.getInt("productPrice"));
	                trendingProducts.add(product);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return trendingProducts;
	    }
	    public Product getProductDetails(String productModelNumber) throws SQLException {
	        Product product = null;
	        String query = "SELECT * FROM product WHERE productModelNumber = ?";
	        
	        try (PreparedStatement statement = conn.prepareStatement(query)) {
	            statement.setString(1, productModelNumber);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    // Populate product details from the result set
	                    product = new Product();
	                    product.setProductname(resultSet.getString("productName"));
	                    product.setProductmodelnumber(resultSet.getString("productModelNumber"));
	                    product.setProductsize(resultSet.getString("productSize"));
	                    product.setProductswitch(resultSet.getString("productSwitch"));
	                    product.setProductstock(resultSet.getInt("productStock"));
	                    product.setProdutprice(resultSet.getInt("productPrice"));
	                    product.setProductdescription(resultSet.getString("productDescription"));
	                    product.setProductfeature(resultSet.getString("productFeature"));
	                    product.setProductbox(resultSet.getString("productBox"));
	                    product.setProductdownload(resultSet.getString("productDownload"));
	                }
	            }
	        }
	        
	        return product;
	    }
	    public boolean updateProduct(Product product) {
	        String query = "UPDATE product SET productName=?, productSize=?, productSwitch=?, productStock=?, productPrice=?, productDescription=?, productFeature=?, productBox=?, productDownload=? WHERE productModelNumber=?";
	        try (PreparedStatement statement = conn.prepareStatement(query)) {
	            statement.setString(1, product.getProductname());
	            statement.setString(2, product.getProductsize());
	            statement.setString(3, product.getProductswitch());
	            statement.setInt(4, product.getProductstock());
	            statement.setInt(5, product.getProdutprice());
	            statement.setString(6, product.getProductdescription());
	            statement.setString(7, product.getProductfeature());
	            statement.setString(8, product.getProductbox());
	            statement.setString(9, product.getProductdownload());
	            statement.setString(10, product.getProductmodelnumber());
	            int rowsUpdated = statement.executeUpdate();
	            return rowsUpdated > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    public Product getProductByModelNumber(String modelNumber) throws ClassNotFoundException {
	        Product product = null;
	        String sql = "SELECT * FROM product WHERE productModelNumber = ?";
	        try (Connection conn = DatabaseConnectivity.getDbConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, modelNumber);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    product = new Product();
	                    product.setProductmodelnumber(rs.getString("productModelNumber"));
	                    product.setProductname(rs.getString("productName"));
	                    product.setProductstock(rs.getInt("productStock"));
	                    product.setProdutprice(rs.getInt("productPrice"));
	                    // Set other properties as needed
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception or log it
	        }
	        return product;
	    }
	    public void updateProductStock(String modelNumber, int newStock) {
	        String sql = "UPDATE product SET productStock = ? WHERE productModelNumber = ?";
	        try (Connection conn = DatabaseConnectivity.getDbConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, newStock);
	            stmt.setString(2, modelNumber);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception or log it
	        } catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	    public int getProductCount() {
	        int count = 0;
	        String query = "SELECT COUNT(*) AS product_count FROM product";
	        try (
	             PreparedStatement statement = conn.prepareStatement(query);
	             ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                count = resultSet.getInt("product_count");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return count;
	    }
}
