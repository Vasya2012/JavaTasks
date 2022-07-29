package marketplace.interfaces;

import marketplace.shop.Product;
import marketplace.shop.User;

public interface ShopController {
    void userAdd(User user);
    void productAdd(Product product);
    void showAllUsers();
    void showAllProducts();
    void userBuy(int userId, int productId);
    void showUserProducts(int id);
    void showProductUsers(int id);
    void deleteUserById(int id);
    void deleteProductById(int id);
}
