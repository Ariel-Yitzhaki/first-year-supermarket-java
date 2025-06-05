package supermarket;


public class Product {
    private String productName;
    private int productPrice;

    public Product (String productName, int productPrice) {//initializes a product
        setProductName(productName);
        setProductPrice(productPrice);
    }

    public void setProductName(String productName){
        this.productName = productName;
    }
    public void setProductPrice(int productPrice){
        this.productPrice = productPrice;
    }

    public String getProductName(){
        return productName;
    }
    public int getProductPrice(){
        return productPrice;
    }
}
