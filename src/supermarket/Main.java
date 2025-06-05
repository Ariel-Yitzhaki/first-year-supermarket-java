package supermarket;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
        private Sellers[] sellers;
        private Buyers[] buyers;
        private int sellerCount;
        private int buyerCount;

        public Main() {
                sellers = new Sellers[2];
                buyers = new Buyers[2];
                sellerCount = 0;
                buyerCount = 0;
        }
        // 0. Print and exit
        public static void exit() {
                //need to fix the print
                System.out.println("Exiting.");
                System.exit(0);
        }

        //checks if the seller name already exists and adds it to the sellers array if it doesn't | returns true of found
        public static boolean addSeller(String name, Sellers[] sellers, int counter) {
                for (int i = 0; i < counter; i++) {
                        //returns true so the loop keeps asking for a name
                        if (sellers[i].getName().equals(name)) {
                                System.out.println(name + " is already on the list! Try a different name! ");
                                return true;
                        }
                }
                //sets the next index to the seller's name
                sellers[counter] = new Sellers(name);
                //stops the loop from asking for a name
                return false;
        }
        //checks if the buyer name exists in the array, if not adds it and returns false
        public static boolean addBuyer(String name, Buyers[] buyers, int counter) {
                for (int i = 0; i < counter; i++) {
                        //returns true so the loop keeps asking for a name
                        if (buyers[i].getName().equals(name)) {
                                System.out.println(name + " is already on the list! Try a different name! ");
                                return true;
                        }
                }
                //sets the next index to the seller's name
                buyers[counter] = new Buyers(name);
                //stops the loop from asking for a name
                return false;
        }

        //prints all the seller names
        public static void printSellers(Sellers[] sellers, int sellerCount){
                System.out.println("List of sellers: ");
                for (int i = 0; i < sellerCount; i++){
                        System.out.println(sellers[i].getName());
                }
        }
        //prints all the seller names together with their all their products
        public static void printSellersFullDetails(Sellers[] sellers, int sellerCount){
                for (int i = 0; i < sellerCount; i++){
                        System.out.println("Name: " + sellers[i].getName());
                        System.out.println("List of products: ");
                        printProducts(sellers[i].getProducts(), sellers[i].getProductsCount());
                        System.out.println("\n");
                }
                if (sellerCount == 0){
                        System.out.println("EMPTY!\n");
                }
        }
        //prints all the buyer names
        public static void printBuyers(Buyers[] buyers, int buyerCount){
                System.out.println("List of buyers: ");
                for (int i = 0; i < buyerCount; i++){
                        System.out.println(buyers[i].getName());
                }
        }
        //prints all the products in the product array or "empty" if productCount = 0
        public static void printProducts(Product[] products, int productCount){
                for (int i = 0; i < productCount; i++){
                        System.out.println("Product name: " + products[i].getProductName() + " | Price: " + products[i].getProductPrice());
                }
                if (productCount == 0) {
                        System.out.println("Empty!\n");
                }
        }
        //checks if the buyer exists in the buyer array and returns its index, returns -1 if not found
        public static int getBuyerIndex(Buyers[] buyers, String buyerName, int buyerCount) {//finds the buyers index if exists and returns it, else returns -1
                int buyerIndex = -1;
                //if the name exists return i
                for (int i = 0; i < buyerCount; i++) {
                        if (buyers[i].getName().equals(buyerName)) {
                                return i;
                        }
                }
                return buyerIndex;
        }
        //checks if the seller name exists and returns its index in the seller array if found, returns -1 if not found
        public static int getSellerIndex(Sellers[] sellers, String sellerName, int sellerCount) {//finds the sellers index if exists
                int sellerIndex = -1;
                //if the name exists return i
                for (int i = 0; i < sellerCount; i++) {
                        if (sellers[i].getName().equals(sellerName)) {
                                return i;
                        }
                }
                return sellerIndex;
        }
        //checks if the product already exists in the sellers product array, returns its index if it does, otherwise returns -1
        public static int getProductIndex(Product[] products, String productName, int sellerCount) {//returns the products index if found, else -1
                int productIndex = -1;
                //if the name exists return i
                for (int i = 0; i < sellerCount; i++) {
                        if (products[i].getProductName().equals(productName)) {
                                return i;
                        }
                }
                return productIndex;
        }
        //adds the selected product to the selected buyer
        public static void addProductToBuyer(Product product, Buyers buyer){//sets the latest product the buyer purchased
                buyer.setProductsBag(buyer.getProductsBagCount(), product.getProductName(), product.getProductPrice());
                System.out.println(product.getProductName() + " has been added!\n");
        }
        //adds the selected buyers current productsBag to the buyers shopping cart and updates relevant information in the shopping cart
        public static int finalizeCart (Buyers buyer) {
                buyer.setShoppingCartHistory(buyer.getProductsBag(), buyer.getProductsBagCount()); // Create the shopping cart for the current shopping cart
                int currentCartIndex = buyer.getTotalShoppingCarts() - 1; // Get the index of the newly added cart
                ShoppingCart currentCart = buyer.getShoppingCartHistory()[currentCartIndex]; // Access the newly added cart
                return currentCart.getTotalCart(); // Get the total sum of the current cart
        }
        //prints all the ShoppingCarts information
        public static void printShoppingCartHistory(ShoppingCart[] shoppingCart, int totalShoppingCarts) {
                System.out.println("ShoppingCart history:\n");
                for (int i = 0; i < totalShoppingCarts; i++) {
                        System.out.println("List of products: ");
                        printProducts(shoppingCart[i].getProducts(), shoppingCart[i].getProductsCount());
                        System.out.println("ShoppingCart total: " + shoppingCart[i].getTotalCart());
                        System.out.println("ShoppingCart check out date: " + shoppingCart[i].getDate());
                        System.out.println("\n");
                }
                if (totalShoppingCarts == 0) {
                        System.out.println("Empty!\n");
                }
        }
        //prints all the buyers on the buyers list in full detail
        public static void printBuyersFullDetails(Buyers[] buyers, int buyersCount) {
                for (int i = 0; i < buyersCount; i++){
                        System.out.println("Name: " + buyers[i].getName());
                        System.out.println("Address: " + buyers[i].getAddress());
                        System.out.println("Current ShoppingCart: ");
                        printProducts(buyers[i].getProductsBag(), buyers[i].getProductsBagCount());
                        printShoppingCartHistory(buyers[i].getShoppingCartHistory(), buyers[i].getTotalShoppingCarts());
                }
                if (buyersCount == 0){
                        System.out.println("EMPTY!\n");
                }
        }


        public static void main (String[] args) {
                Main manager = new Main();
                int sellerIndex, buyerIndex, productIndex;
                boolean check;
                Scanner scanner = new Scanner(System.in);
                //runMenu is an option to turn off the loop if later on needed
                boolean runMenu = true;
                while (runMenu) {
                        System.out.println("Menu:");
                        System.out.println("1. Add seller");
                        System.out.println("2. Add buyer");
                        System.out.println("3. Add product to seller");
                        System.out.println("4. Add product to buyers shopping cart");
                        System.out.println("5. Check out a with a buyer");
                        System.out.println("6. Print out all of the buyers on the list");
                        System.out.println("7. Print out all of the sellers on the list");
                        System.out.println("0. Exit\n");
                        System.out.println("Choose an option from the menu:");
                        int option = scanner.nextInt();
                        scanner.nextLine();

                        switch (option) {
                                case 0:
                                        exit();
                                case 1:
                                        check = true;
                                        //as long as function returns true will keep asking for a name that doesn't already exist
                                        while (check) {
                                                System.out.println("Enter seller name: ");
                                                String sellerName = scanner.nextLine();
                                                check = addSeller(sellerName, manager.sellers, manager.sellerCount);
                                                //if name is valid, get the password input too from the user
                                                if (!check) {
                                                        System.out.println("Enter seller password: ");
                                                        String password = scanner.nextLine();
                                                        manager.sellers[manager.sellerCount].setPassword(password);
                                                        manager.sellerCount += 1;
                                                        System.out.println(sellerName + "has been added to the sellers list!\n");
                                                }
                                        }
                                        //resizes the array
                                        if (manager.sellerCount >= manager.sellers.length) {
                                                manager.sellers = Arrays.copyOf(manager.sellers, manager.sellerCount * 2);
                                        }
                                        break;
                                case 2:
                                        check = true;
                                        //as long as function returns true will keep asking for a name that doesn't already exist
                                        while (check) {
                                                System.out.println("Enter buyer name: ");
                                                String buyerName = scanner.nextLine();
                                                check = addBuyer(buyerName, manager.buyers, manager.buyerCount);
                                                //if name is valid, get the password and address input too from the user
                                                if (!check) {
                                                        System.out.println("Enter buyer password: ");
                                                        String password = scanner.nextLine();
                                                        manager.buyers[manager.buyerCount].setPassword(password);
                                                        System.out.println("Enter buyer address: ");
                                                        String address = scanner.nextLine();
                                                        manager.buyers[manager.buyerCount].setAddress(address);
                                                        manager.buyerCount += 1;
                                                        System.out.println(buyerName + " has been added to the buyers list!\n");
                                                }
                                        }
                                        //resizes the array
                                        if (manager.buyerCount >= manager.buyers.length) {
                                                manager.buyers = Arrays.copyOf(manager.buyers, manager.buyerCount * 2);
                                        }
                                        break;
                                case 3:
                                        check = true;
                                        if (manager.sellerCount > 0) {
                                                while (check) {
                                                        printSellers(manager.sellers, manager.sellerCount);
                                                        System.out.println("Please enter the name of the seller you wish to add a product to from the list: ");
                                                        String sellerSelected = scanner.nextLine();
                                                        sellerIndex = getSellerIndex(manager.sellers, sellerSelected, manager.sellerCount);

                                                        if (sellerIndex != -1) {
                                                                Sellers selectedSeller = manager.sellers[sellerIndex];
                                                                System.out.println("Enter product name: ");
                                                                String productName = scanner.nextLine();
                                                                //if the product name doesn't exist already returns -1, else continues to ask for a different product name to add
                                                                while (getProductIndex(selectedSeller.getProducts(), productName, manager.sellers[sellerIndex].getProductsCount()) != -1) {
                                                                        System.out.println("Product name already exists! Try again.\n");
                                                                        System.out.println("Enter product name: ");
                                                                        productName = scanner.nextLine();
                                                                }
                                                                int productPrice = -1;
                                                                //checks that the product price is a valid int input
                                                                while (productPrice < 0) {
                                                                        System.out.println("Enter product price: ");
                                                                        productPrice = scanner.nextInt();
                                                                        if (productPrice < 0) {
                                                                                System.out.println("Invalid price! Try again.\n");
                                                                        }
                                                                }
                                                                //adds the products to the seller and updates the productsCount
                                                                selectedSeller.setProducts(selectedSeller.getProductsCount(), productName, productPrice);
                                                                scanner.nextLine();
                                                                check = false;
                                                        } else {
                                                                System.out.println(sellerSelected + " does not exist! Try again.\n");
                                                        }
                                                }
                                        }
                                        else {
                                                System.out.println("The Sellers list is empty! Returning to Menu.\n");
                                        }
                                        break;
                                case 4:
                                        sellerIndex = -1;
                                        buyerIndex = -1;
                                        if (manager.buyerCount > 0 && manager.sellerCount > 0) {
                                                while (buyerIndex == -1) {//loop to find a buyer to make a purchase with
                                                        printBuyers(manager.buyers, manager.buyerCount);
                                                        System.out.println("Please enter the name of the buyer you wish to select: ");
                                                        String buyerName = scanner.nextLine();
                                                        buyerIndex = getBuyerIndex(manager.buyers, buyerName, manager.buyerCount);
                                                        if (buyerIndex == -1) {
                                                                System.out.println("does not exist! Try again");
                                                        }
                                                }
                                                while (sellerIndex == -1) {//loop to find a seller to buy a product from
                                                        printSellers(manager.sellers, manager.sellerCount);
                                                        System.out.println("Enter the name of the seller you would like to buy a product from: ");
                                                        String sellerName = scanner.nextLine();
                                                        sellerIndex = getSellerIndex(manager.sellers, sellerName, manager.buyerCount);
                                                        if (sellerIndex == -1) {
                                                                System.out.println("does not exist! Try again");
                                                        }
                                                }
                                                if (manager.sellers[sellerIndex].getProductsCount() > 0) {
                                                        // Get the seller and buyer by index
                                                        Sellers selectedSeller = manager.sellers[sellerIndex];
                                                        Buyers selectedBuyer = manager.buyers[buyerIndex];
                                                        // Select product
                                                        productIndex = -1;
                                                        while (productIndex == -1) {
                                                                // print the selected seller's products
                                                                System.out.println("List of products: ");
                                                                printProducts(selectedSeller.getProducts(), selectedSeller.getProductsCount());
                                                                System.out.println("Enter the name of the product you would like to buy");
                                                                String productName = scanner.nextLine();
                                                                //returns -1 so long as the product doesn't exist, if found returns the product's index in the sellers array
                                                                productIndex = getProductIndex(selectedSeller.getProducts(), productName, selectedSeller.getProductsCount());
                                                        }

                                                        Product selectedProduct = selectedSeller.getProducts()[productIndex];//gets the info of the product
                                                        addProductToBuyer(selectedProduct, selectedBuyer);//sets the buyers product info
                                                }
                                                else {
                                                        System.out.println("The seller has no products to sell! Returning to Menu.\n");
                                                }
                                        }
                                        else {
                                                System.out.println("Can't do that yet! Either the Sellers or the Buyers list is empty." +
                                                        " Returning to Menu.\n");
                                        }
                                        break;
                                case 5:
                                        buyerIndex = -1;
                                        if (manager.buyerCount > 0) {
                                                while (buyerIndex == -1) {//asks the user to select a buyer to check out with
                                                        printBuyers(manager.buyers, manager.buyerCount);
                                                        System.out.print("Please enter the name of the buyer you would like to check out with: ");
                                                        String chosenBuyer = scanner.nextLine();
                                                        buyerIndex = getBuyerIndex(manager.buyers, chosenBuyer, manager.buyerCount);//returns the index of the buyer if found
                                                        if (buyerIndex == -1) {
                                                                System.out.println("does not exist! Try again");
                                                        }
                                                }
                                                if (manager.buyers[buyerIndex].getProductsBagCount() > 0) {
                                                        int cartTotal;
                                                        LocalDate date = LocalDate.now();
                                                        cartTotal = finalizeCart(manager.buyers[buyerIndex]);//sets product info into shoppingCart and gets the cart's total sum and current date
                                                        System.out.println("The total price of your cart is: " + cartTotal);
                                                        System.out.println("Current date: " + date + "\n");
                                                        manager.buyers[buyerIndex].resetProductsBag();//reset the buyers product info after updating the cart
                                                }
                                                else {
                                                        System.out.println("Shopping-cart is empty! Returning to Menu.\n");
                                                }
                                        }
                                        else{
                                                System.out.println("The buyers list is empty! Returning to Menu.\n");
                                        }
                                        break;
                                case 6:
                                        System.out.println("Buyers list details:");
                                        printBuyersFullDetails(manager.buyers, manager.buyerCount);
                                        break;
                                case 7:
                                        System.out.println("Sellers list Details:");
                                        printSellersFullDetails(manager.sellers, manager.sellerCount);
                                        break;
                                default:
                                        System.out.println("Invalid option. Please try again.\n");
                        }
                }
        }
}
