package marketplace.services;
import marketplace.marketplaceException.NotEnoughMoney;
import marketplace.shop.Product;
import marketplace.shop.User;

import java.util.HashMap;
import java.util.Map;

public class ShopController implements marketplace.interfaces.ShopController {
    private Map<Integer, User> userMap = new HashMap<>();
    private Map<Integer, Product> productMap = new HashMap<>();

    public void userAdd(User user) {
        userMap.put(user.getUserId(), user);
    }

    public void productAdd(Product product) {
        productMap.put(product.getProductId(), product);
    }

    public void showAllUsers() {
        userMap.values().stream().map(user -> String.format(
                "%4d | %10s %10s %f",
                user.getUserId(),
                user.getName(),
                user.getSurname(),
                user.getBalance()
        )).forEach(java.lang.System.out::println);
    }

    public void showAllProducts() {
        productMap.values().stream().map(product1 -> String.format(
                "%4d | %10s %f",
                product1.getProductId(),
                product1.getName(),
                product1.getPrice()
        )).forEach(System.out::println);
    }

    public void userBuy(int userId, int productId) {
        try {
            userMap.get(userId).buyProduct(productMap.get(productId));
            productMap.get(productId).addOwnerUser(userMap.get(userId));
        } catch (NotEnoughMoney e) {
            System.out.println("Not enough money");
        } catch (NullPointerException e) {
            System.out.println("ERROR: wrong id of user or product");
        }
    }

    public void showUserProducts(int id) {
        userMap.get(id).userProducts();
    }

    public void showProductUsers(int id) {
        productMap.get(id).getOwners();
    }

    public void deleteUserById(int id) {
        for (Map.Entry<Integer, Product> product : productMap.entrySet()) {
            product.getValue().deleteOwnerUsersById(id);
        }
        userMap.remove(id);
    }

    public void deleteProductById(int id) {
        for (Map.Entry<Integer, User> user : userMap.entrySet()) {
            user.getValue().deleteProductById(id);
        }
        productMap.remove(id);
    }
}