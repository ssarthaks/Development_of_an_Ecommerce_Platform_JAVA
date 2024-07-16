package model;

public class ProductImage {
	private byte[] image_data;
	private String image_name;
	  private String base64ImageData;
	public String getBase64ImageData() {
		return base64ImageData;
	}
	public void setBase64ImageData(String base64ImageData) {
		this.base64ImageData = base64ImageData;
	}
	public byte[] getImage_data() {
		return image_data;
	}
	public void setImage_data(byte[] image_data) {
		this.image_data = image_data;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
}