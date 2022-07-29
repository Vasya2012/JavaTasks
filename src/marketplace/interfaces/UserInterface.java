package marketplace.interfaces;

import marketplace.marketplaceException.NotEnoughMoney;
import marketplace.shop.Product;
public interface UserInterface {
    int getUserId();
    String getName();
    String getSurname();
    double getBalance();
    void userProducts();
    void deleteProductById(int id);
    void buyProduct(Product product) throws NotEnoughMoney;

}
