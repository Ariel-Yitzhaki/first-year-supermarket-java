package supermarket;

import java.time.LocalDate;

public class ShoppingCart {
    private Product[] products;
    private LocalDate date;
    private int productsCount;
    private int totalCart = 0;

    public ShoppingCart(Product[] products, LocalDate date, int productsBagCount) {//copies the buyers products into the shoppingCarts products
        this.products = new Product[productsBagCount];
        for (int i = 0; i < productsBagCount; i++) {
            this.products[i] = new Product(products[i].getProductName(), products[i].getProductPrice());
        }
        this.date = date;
        this.productsCount = productsBagCount;
        setTotalCart(products, productsBagCount);//gets the total price of all the products in the cart
    }

    public void setTotalCart(Product[] products, int arrSize){
        for (int i = 0; i < arrSize; i++){
            totalCart += products[i].getProductPrice();
        }
    }
    public int getTotalCart(){
        return totalCart;
    }
    public Product[] getProducts() {
        return products;
    }
    public int getProductsCount(){
        return productsCount;
    }
    public LocalDate getDate() {
        return date;
    }
}