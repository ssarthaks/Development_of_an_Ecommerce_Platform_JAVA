package model;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String productname;
    private String productmodelnumber;
    private String productsize;
    private String productswitch;
    private int productstock;
    private int produtprice;
    private String productdescription;
    private String productfeature;
    private String productbox;
    private String productdownload;
    private String imagePath;
    private boolean isTrending;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductmodelnumber() {
        return productmodelnumber;
    }

    public void setProductmodelnumber(String productmodelnumber) {
        this.productmodelnumber = productmodelnumber;
    }

    public String getProductsize() {
        return productsize;
    }

    public void setProductsize(String productsize) {
        this.productsize = productsize;
    }

    public String getProductswitch() {
        return productswitch;
    }

    public void setProductswitch(String productswitch) {
        this.productswitch = productswitch;
    }

    public int getProductstock() {
        return productstock;
    }

    public void setProductstock(int productstock) {
        this.productstock = productstock;
    }

    public int getProdutprice() {
        return produtprice;
    }
    

    
    public void setProdutprice(int produtprice) {
        this.produtprice = produtprice;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public String getProductfeature() {
        return productfeature;
    }

    public void setProductfeature(String productfeature) {
        this.productfeature = productfeature;
    }

    public String getProductbox() {
        return productbox;
    }

    public void setProductbox(String productbox) {
        this.productbox = productbox;
    }

    public String getProductdownload() {
        return productdownload;
    }

    public void setProductdownload(String productdownload) {
        this.productdownload = productdownload;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

	public boolean getisTrending() {
		return isTrending;
	}

	public void setTrending(boolean isTrending) {
		this.isTrending = isTrending;
	}
    

}
