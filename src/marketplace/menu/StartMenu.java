package marketplace.menu;

import marketplace.services.ShopController;
import marketplace.shop.Product;
import marketplace.shop.User;

import java.util.*;

public class StartMenu {
    ShopController shopController = new ShopController();
    public static final Scanner scanner = new Scanner(System.in);
    private int choise = 0;

    public StartMenu(){
        System.out.println("1. Create User menu");
        System.out.println("2. Create Product menu");
        System.out.println("3. User buying menu");
        System.out.println("4. Show all users menu");
        System.out.println("5. Show all products menu");
        System.out.println("6. Delete User menu");
        System.out.println("7. Delete Product menu");
        System.out.println("8. Display list of user products by user id ");
        System.out.println("9. Display list of users that bought product by product id");
        System.out.println("10. Exit App");
        start();
    }

    public void start() {
        while (true) {
            int choice = choise();
            switch (choice) {
                case 1:
                    userCreate();
                    break;
                case 2:
                    productCreate();
                    break;
                case 3:
                    userBuy();
                    break;
                case 4:
                    showAllUsers();
                    break;
                case 5:
                    showAllProducts();
                    break;
                case 6:
                    deleteUser();
                    break;
                case 7:
                    deleteProduct();
                    break;
                case 8:
                    showUserProducts();
                    break;
                case 9:
                    showProductUsers();
                    break;

                case 10:
                    exitApp();
                    scanner.close();
                    return;
            }
        }
    }

    public int choise() {
        System.out.println("----------------------------\nstart menu\nwrite your choice: ");
        try {
            choise = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Incorect choise");
        }catch (InputMismatchException e){
            System.out.println("ERROR: Write digit from 1 to 9, and 10 for EXIT");
        }
        return choise;
    }

    public void userCreate() {
        System.out.println("User creating menu \nwrite 3 words separated by space. Example: 'name surname balance' ");
        System.out.println(String.format(
                "%4s | %10s %10s %s", "id" , "name", "surname", "balance"));
        if (scanner.hasNext()) {
            try {
                String[] inputLine = scanner.nextLine().split("\\s+");
                String name = inputLine[0];
                String surname = inputLine[1];
                double balance = Double.parseDouble(inputLine[2]);
                if (balance < 0) {
                    System.out.println("user has not been added. Balance must be greater than 0\nTry again with correct balance");
                    userCreate();
                }
                shopController.userAdd(new User(name, surname, balance));
                System.out.println("user added");
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("wrong parameters: u must write 3 words separated by space");
                userCreate();
            }catch (NumberFormatException e1){
                System.out.println(e1.getMessage() + " balance must be a positive number");
                userCreate();
            }
        }
    }

    public void productCreate(){

        System.out.println("Product creating menu \nwrite 2 words separated by space");
        System.out.println(String.format(
                "%4s | %10s %s", "id" , "name", "price"));
        try {
            String[] inputLine = scanner.nextLine().split("\\s+");
            String nameProduct = inputLine[0];
            double cost = Double.parseDouble(inputLine[1]);
            if (cost < 0) {
                System.out.println("The product must have a positive price");
                productCreate();
                return;
            }
            shopController.productAdd(new Product(nameProduct, cost));
            System.out.println("product added");
        }catch (NumberFormatException e){
            System.out.println("Wrong price: must be a number");
            productCreate();
        }catch (IndexOutOfBoundsException e2){
            System.out.println("Wrong");
            productCreate();
        }
    }

    public void userBuy() {
        System.out.println("Buying menu \nwrite 2 numbers separated by space");
        try {
            String[] inputLine = scanner.nextLine().split("\\s+");
            int userId1 = Integer.parseInt(inputLine[0]);
            int productId1 = Integer.parseInt(inputLine[1]);
            shopController.userBuy(userId1,productId1);
        } catch (NumberFormatException e2) {
            System.out.println(e2.getMessage() + "\nIncorect input\nWrite [id, id], where id is number");
            userBuy();
        } catch (IndexOutOfBoundsException e1) {
            System.out.println("Incorrect ID");
            userBuy();
        }
    }

    public void showAllUsers() {
        System.out.println("List of all users");
        System.out.println(String.format(
                "%4s | %10s %10s %s", "id" , "name", "surname", "balance"));
        shopController.showAllUsers();
    }

    public void showAllProducts() {
        System.out.println("List of products");
        System.out.println(String.format(
                "%4s | %10s %s", "id" , "name", "price"));
        shopController.showAllProducts();
    }

    public void deleteUser() {
        System.out.println("Delete user by id");
        try {
            int userId = Integer.parseInt(scanner.nextLine());
            shopController.deleteUserById(userId);
            System.out.println("user has been deleted");
        }catch (NumberFormatException e2) {
            System.out.println(e2.getMessage() + "\nIncorect input\nWrite [id, id], where id is number");
        }catch (IndexOutOfBoundsException e1) {
            System.out.println("Incorrect ID");
        }
    }

    public void deleteProduct() {
        System.out.println("Delete product by id");
        try {
            int productId = Integer.parseInt(scanner.nextLine());
            shopController.deleteProductById(productId);
        }catch (NumberFormatException e2) {
            System.out.println(e2.getMessage() + "\nIncorect input\nWrite [id, id], where id is number");
        }catch (IndexOutOfBoundsException e1) {
            System.out.println("Incorrect ID");
        }
        System.out.println("Product has been deleted");
    }
    public void showUserProducts() {
        System.out.println("Display user products by user id");
        try {
            int userId = Integer.parseInt(scanner.nextLine());
            shopController.showUserProducts(userId);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Incorrect id, not found user\n" + e.getMessage());
        } catch (NumberFormatException e1) {
            System.out.println(e1.getMessage() + " Incorrect number");
        }
    }

    public void showProductUsers() {
        System.out.println("Display list of users that bought product by product id");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("List of users");
            shopController.showProductUsers(id);
        } catch (NumberFormatException e) {
            System.out.println(e + "Incorrect id");
        }
    }

    public void exitApp() {
        System.out.println("Exit");
    }
}


