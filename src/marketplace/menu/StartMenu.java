package marketplace.menu;

import marketplace.marketplaceException.NotEnoughMoney;
import marketplace.shop.Product;
import marketplace.shop.User;

import java.util.*;

/**toDo1
 * need create UserMenu, ProductMenu and use them for better readable code
 * need create UserServise or UserController and Product and use them for better readable code
 * Dont have enough time, because i lost my time on task3 which i didnt end
 * */
public class StartMenu {
    public static final Scanner scanner = new Scanner(System.in);
    List<User> userList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();
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

                default:
                    System.out.println("Smth wrong");
            }
        }
    }

    public int choise() {
        System.out.println("start menu");
        try {
            choise = scanner.nextInt();
            scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Incorect choise");
        }catch (InputMismatchException e){
            scanner.next();
            System.out.println("ERROR: Write digit from 1 to 9, and 0 for EXIT");
        }
        return choise;
    }

    public void userCreate() {
        System.out.println("User creating menu \nwrite 3 words separated by space. Example: 'name surname balance' ");
        if (scanner.hasNext()) {
            try {
                String[] inputLine = scanner.nextLine().split("\\s+");
                String name = inputLine[0];
                String surname = inputLine[1];
                int balance = Integer.parseInt(inputLine[2]);
                if (balance < 0) {
                    System.out.println("user has not been added. Balance must be greater than 0\nTry again with correct balance");
                    return;
                }
                userList.add(new User(name, surname, balance));
                System.out.println("user added");
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("wrong parameters: u must write 3 words separated by space");
                return;
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
            int cost = Integer.parseInt(inputLine[1]);
            productList.add(new Product(nameProduct, cost));
            System.out.println("product added");
        }catch (NumberFormatException e){
            System.out.println("Wrong price: must be integer\nGo to start menu");
            return;
        }catch (IndexOutOfBoundsException e2){
            System.out.println("Wrong");
            return;
        }
    }

    public void userBuy() {
        System.out.println("Buying menu \nwrite 2 numbers separated by space");
        try {
            String[] inputLine = scanner.nextLine().split("\\s+");
            int userId1 = Integer.parseInt(inputLine[0]);
            int productId1 = Integer.parseInt(inputLine[1]);
            for (User user : userList) {
                if (user.getUserId() == userId1) {
                    for (Product product1 : productList) {
                        if (product1.getProductId() == productId1) {
                            product1.addUser(user);
                            user.buyProduct(product1);
                        }
                    }
                }else {
                    System.out.println("Id not found");
                }
            }
        } catch (NotEnoughMoney e) {
            System.out.println(e.getMessage() + "\nYou are redirected to the start menu");
        } catch (NumberFormatException e2) {
            System.out.println(e2.getMessage() + "\nIncorect input\nWrite [id, id], where id is number");
        } catch (IndexOutOfBoundsException e1) {
            System.out.println("Incorrect ID");
        } finally {
        }
    }

    public void showAllUsers() {
        System.out.println("List of all users");
        System.out.println(String.format(
                "%4s | %10s %10s %s", "id" , "name", "surname", "balance"));
        userList.stream().map(user -> String.format(
                        "%4d | %10s %10s %d",
                        user.getUserId(),
                        user.getName(),
                        user.getSurname(),
                        user.getBalance()
                ))
                .forEach(System.out::println);
    }

    public void showAllProducts() {
        System.out.println("List of products");
        System.out.println(String.format(
                "%4s | %10s %s", "id" , "name", "price"));
        productList.stream().map(product1 -> String.format(
                "%4d | %10s %d",
                product1.getProductId(),
                product1.getName(),
                product1.getPrice()
        )).forEach(System.out::println);
    }
    public void deleteUser() {
        System.out.println("Delete user by id");
        try {
            int userId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("user has been deleted");
            for(User user: userList){
                if(user.getUserId()==userId){
                    for(Product product: productList){
                        product.deleteUsersById(userId);
                    }
                }
            }
            userList.removeIf(user -> user.getUserId() == userId);
        }catch (NumberFormatException e){
            System.out.println(e + "Incorrect id");
            return;
        }
    }

    public void deleteProduct() {
        System.out.println("Delete product by id");
        try {
            int productId = scanner.nextInt();
            scanner.nextLine();
            for (Product product : productList) {
                if (product.getProductId() == productId) {
                    for (User user : userList) {
                        user.deleteProductsById(product.getProductId());
                    }
                }
            }
            productList.removeIf(product1 -> product1.getProductId() == productId);
        }catch (NumberFormatException e){
            System.out.println(e + "Incorrect id");
            return;
        }
        System.out.println("Product has been deleted");
    }
    public void showUserProducts() {
        System.out.println("Display user products by user id");
        try {
            int userId = scanner.nextInt();
            scanner.nextLine();
            for (User user : userList) {
                if (user.getUserId() == userId) {
                    user.userProdacts();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Incorrect id, not found user\n" + e.getMessage());
            return;
        } catch (NumberFormatException e1) {
            System.out.println("Incorrect number");
            return;
        }
    }

    public void showProductUsers() {
        System.out.println("Display list of users that bought product by product id");
        try {
            int productIdFind = scanner.nextInt();
            scanner.nextLine();
            System.out.println("List of users");
            System.out.println(String.format(
                    "%4s | %10s %10s %s", "id", "name", "surname", "balance"));
            for (Product product : productList) {
                if (product.getProductId() == productIdFind) {
                    product.productUsers();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e + "Incorrect id");
            return;
        }
    }

    public void exitApp() {
        System.out.println("Exit");
        return;
    }
}


