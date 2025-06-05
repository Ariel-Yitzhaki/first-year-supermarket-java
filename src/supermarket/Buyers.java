package supermarket;

import java.time.LocalDate;

public class Buyers {
    private String name;
    private String password;
    private String address;
    private Product[] productsBag = new Product[2];
    private int productsBagCount = 0;
    private ShoppingCart[] shoppingCartHistory = new ShoppingCart[2];
    private int totalShoppingCarts = 0;


    public Buyers(String name) {//used as setName
        this.name = name;
    }

    public void resizeProducts(int newSize) {//resize the products array
        Product[] newProducts = new Product[newSize];
        for (int i = 0; i < productsBagCount; i++){
            if (productsBag[i] != null) {
                newProducts[i] = new Product(productsBag[i].getProductName(), productsBag[i].getProductPrice());
            }
            else{
                System.out.println("Error in resizing! Exiting.");
                System.exit(0);
            }
        }
        productsBag = newProducts;
    }

    public void resizeShoppingCart(int newSize) {//resizes the shoppingCarts 2d array
        ShoppingCart[] newShoppingCart = new ShoppingCart[newSize];
        for (int i = 0; i < totalShoppingCarts; i++) {
            if (shoppingCartHistory[i] != null) {
                newShoppingCart[i] = new ShoppingCart(shoppingCartHistory[i].getProducts(), shoppingCartHistory[i].getDate(), shoppingCartHistory[i].getProducts().length);
            }
            else {
                System.out.println("Error in resizing the array! Exiting.");
                System.exit(0);
            }
        }
        shoppingCartHistory = newShoppingCart;
    }

    public void setShoppingCartHistory(Product[] productsBag, int productsBagCount) {//sets the information for the current product that's being checked out with in the shoppingCart
        if (totalShoppingCarts >= shoppingCartHistory.length) {
            resizeShoppingCart(shoppingCartHistory.length * 2);
        }
        Product[] newShoppingCart = new Product[productsBagCount];
        for (int i = 0; i < productsBagCount; i++) {
            newShoppingCart[i] = new Product(productsBag[i].getProductName(), productsBag[i].getProductPrice());
        }
        shoppingCartHistory[totalShoppingCarts] = new ShoppingCart(newShoppingCart, LocalDate.now(), productsBagCount);//adds the ProductBag + the current date to the shopping cart
        totalShoppingCarts++;//increases the index count for shoppingCartHistory by 1 since a new shoppingCart was added
    }

    public void resetProductsBag(){//resets the buyers productBag back to its initialized state
        productsBag = new Product[2];
        productsBagCount = 0;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setProductsBag(int productIndex, String productName, int productPrice) {//sets the products for the buyer and resizes if necessary
        productsBag[productIndex] = new Product(productName, productPrice);
        productsBagCount++;//increases the productsBagCount since a new product has been added
        if (productsBag.length <= productsBagCount){
            resizeProducts(productsBag.length * 2);
        }
    }
    public int getTotalShoppingCarts(){
        return totalShoppingCarts;
    }

    public int getProductsBagCount(){
        return productsBagCount;
    }
    public Product[] getProductsBag(){
        return productsBag;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){//will use in the future?
        return password;
    }
    public String getAddress(){
        return address;
    }
    public ShoppingCart[] getShoppingCartHistory(){
        return shoppingCartHistory;
    }
}
