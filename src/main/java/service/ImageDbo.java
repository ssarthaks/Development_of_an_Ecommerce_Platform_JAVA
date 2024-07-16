package service;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProductImage;
import utils.DatabaseConnectivity;

public class ImageDbo {
	public int insert(ProductImage image) {
	    Connection conn = null;
	    PreparedStatement st = null;
	    int row = 0;
	    try {
	        conn = DatabaseConnectivity.getDbConnection();
	        st = conn.prepareStatement("INSERT INTO product_image (image_data, image_name) VALUES (?, ?)");
	        Blob blob = conn.createBlob();
	        blob.setBytes(1, image.getImage_data());
	        st.setBlob(1, blob);
	        st.setString(2, image.getImage_name());
	        row = st.executeUpdate();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace(); // Handle or log the exception appropriately
	    } finally {
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle or log the exception appropriately
	        }
	    }
	    return row;
	}

	public List<ProductImage> getImageData() throws SQLException {
		
		Connection conn = null;
		try {
			conn = DatabaseConnectivity.getDbConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement st=conn.prepareStatement("select * from product_image");
		ResultSet set=st.executeQuery();
		
		List<ProductImage> listOfImage=new ArrayList<>();
		while(set.next())
		{
			ProductImage image=new ProductImage();
			image.setImage_data(set.getBlob("image_data").getBytes(1, (int)set.getBlob("image_data").length()));
			image.setImage_name(set.getString("image_name"));
			listOfImage.add(image);
			
		}
		return listOfImage;
	}
	

}
