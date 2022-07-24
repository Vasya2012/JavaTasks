package marketplace.shop;

import marketplace.interfaces.ProductInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Product implements ProductInterface {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int productId;
    private final String name;
    private final int price;

    private List<User> userList = new ArrayList<>();
    public Product(String name, int price){
        productId = count.incrementAndGet();
        this.name = name;
        this.price = price;

    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getProductId() {
        return productId;
    }

    public void addUser(User user){
        userList.add(user);
    }

    public void deleteUsersById(int userId){
        userList.removeIf(user -> user.getUserId() == userId);
    }

    public String productUsers(){
        System.out.println(String.format(
                "%4s | %10s %10s %s", "id" , "name", "surname", "balance"));
        userList.stream().map(user -> String.format(
                "%4d | %10s %10s %d",
                user.getUserId(),
                user.getName(),
                user.getSurname(),
                user.getBalance()
        )).forEach(java.lang.System.out::println);
        return String.valueOf(userList);
    }

    public String toString(){

        return name + " " + price;
    }

}
