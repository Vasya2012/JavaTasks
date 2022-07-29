package marketplace.shop;

import marketplace.interfaces.ProductInterface;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Product implements ProductInterface {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int productId;
    private final String name;
    private final double price;
    private Map<Integer, User> userMap = new HashMap<>();
    public Product(String name, double price){
        productId = count.incrementAndGet();
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getProductId() {
        return productId;
    }

    public void addOwnerUser(User user){
        userMap.put(user.getUserId(), user);
    }

    public void deleteOwnerUsersById(int userId){
        userMap.remove(userId);
    }

    public void getOwners(){
        System.out.println(String.format(
                "%4s | %10s %10s %s", "id" , "name", "surname", "balance"));
        userMap.values().stream().map(user -> String.format(
                "%4d | %10s %10s %.2f",
                user.getUserId(),
                user.getName(),
                user.getSurname(),
                user.getBalance()
        )).forEach(java.lang.System.out::println);
    }

    public String toString(){
        return name + " " + price;
    }

}
