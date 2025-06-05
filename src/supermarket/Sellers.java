package supermarket;

public class Sellers {
    private String name;
    private String password;
    private Product[] products = new Product[2];
    private int productsCount = 0;

    public Sellers(String name) {//used as setName
        this.name = name;
    }

    public void resizeProducts(int newSize){//resizes the product[] of the seller
        Product[] newProducts = new Product[newSize];
        for (int i = 0; i < productsCount; i++) {
            if (products[i] != null) {
                newProducts[i] = new Product(products[i].getProductName(), products[i].getProductPrice());
            }
            else{
                System.out.println("Error in resizing! Exiting.");
                System.exit(0);
            }
        }
        products = newProducts;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProducts(int productIndex, String productName, int productPrice) {//sets the products for the seller and resizes if necessary
        products[productIndex] = new Product(productName, productPrice);
        productsCount++;//increases the productsCount by 1 since a new product was registered
        if (products.length <= productsCount){
            resizeProducts(products.length * 2);
        }
    }

    public String getName() {
        return name;
    }

    public String getPassword() {//will use in the future?
        return password;
    }

    public Product[] getProducts() {
        return products;
    }

    public int getProductsCount() {
        return productsCount;
    }
}
