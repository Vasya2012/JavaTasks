package marketplace.interfaces;

import marketplace.shop.User;

public interface ProductInterface {
    double getPrice();
    String getName();
    int getProductId();
    void addOwnerUser(User user);
    void deleteOwnerUsersById(int userId);
    void getOwners();
}
