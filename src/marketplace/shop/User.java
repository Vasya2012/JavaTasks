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
    private int balance;

    private List<Product> productList = new ArrayList<>();
/*    private Map<Integer, Product> productMap = new HashMap<>();*/



    public User(String name, String surname, int balance) {
/*        Objects.requireNonNull(name);
        Objects.requireNonNull(surname, "null object");*/
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

    public String userProdacts(){
        System.out.println(String.format(
                "%4s | %10s %s", "id" , "name", "price"));
        productList.stream().map(product1 -> String.format(
                "%4d | %10s %d",
                product1.getProductId(),
                product1.getName(),
                product1.getPrice()
        )).forEach(java.lang.System.out::println);
        return String.valueOf(productList);
    }

    public void deleteProductsById(int productId){
        Iterator itr = productList.iterator();

        while (itr.hasNext()) {

            Product x = (Product) itr.next();
            if (x.getProductId()==productId)
                itr.remove();
        }
        //productList.removeIf(product -> product.getProductId() == productId);
    }

    public void buyProduct(Product product) throws NotEnoughMoney {

        if(balance<product.getPrice()) {
            throw new NotEnoughMoney("Not enough money\nproduct will not be added");
        }else {
            int price = product.getPrice();
            balance = balance - price;
            productList.add(product);
        }
    }


    public int getBalance() {
        return balance;
    }
    public String toString(){

        return userId + " " + name + " " + balance;
    }

}
