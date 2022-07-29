package marketplace.shop;

import marketplace.interfaces.UserInterface;
import marketplace.marketplaceException.NotEnoughMoney;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class User implements UserInterface {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int userId;
    private final String name;
    private final String surname;
    private double balance;
    private Map<Integer, Product> productMap = new HashMap<>();

    public User(String name, String surname, double balance) {
        userId = count.incrementAndGet();
        this.name = name;
        this.surname = surname;
        this.balance = balance;
    }


    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getBalance() {
        return balance;
    }
    public void userProducts(){
        productMap.values().stream().map(product1 -> String.format(
                "%4d | %10s %.2f",
                product1.getProductId(),
                product1.getName(),
                product1.getPrice()
        )).forEach(java.lang.System.out::println);
    }

    public void deleteProductById(int productId){
        productMap.remove(productId);
    }

    public void buyProduct(Product product) throws NotEnoughMoney {

        if(balance<product.getPrice()) {
            throw new NotEnoughMoney("Not enough money\nproduct will not be added");
        }else {
            double price = product.getPrice();
            balance = balance - price;
            productMap.put(product.getProductId(), product);
        }
    }
    public String toString(){
        return userId + " " + name + " " + balance;
    }
}
