package lesson1.homework;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Домашнее задание на закрепление:
 * <p>
 * 1)Создать класс Товар, имеющий переменные имя, цена, рейтинг.
 * 2)Создать класс Категория, имеющий переменные имя и массив товаров. Создать несколько объектов класса Категория.
 * 3)Создать класс Basket, содержащий массив купленных товаров.
 * 4)Создать класс User, содержащий логин, пароль и объект класса Basket. Создать несколько объектов класса User.
 * 5)Вывести на консоль каталог продуктов. (все продукты магазина)
 * 6)Вывести на консоль покупки посетителей магазина. (После покупки у пользователя добавляется товар, а из магазина - удаляется)
 */

public class Main {
    static ArrayList<Product> products = Product.data;
    static ArrayList<Category> categories = Category.data;
    static ArrayList<User> users = User.data;
    public static void main(String[] args) throws Exception {
        init();

        basketAdd("username1", "Картофель", 14);
        basketAdd("username1", "Груши", 5);
        basketDel("username1", "Картофель");
        basketAdd("username1", "Картофель", 6);

        basketAdd("username2", "Картофель", 14);
        basketAdd("username2", "Картофель", 6);

        viewCategories();
        viewUsers();
    }
    private static void init() {
        products.addAll(List.of(
                new Product("Картофель", 56.90, 100, 9),
                new Product("Морковь", 86.30, 100, 6),
                new Product("Редис", 99.50, 100, 8),
                new Product("Яблоки", 104.70, 100, 8),
                new Product("Груши", 123.50, 100, 10),
                new Product("Бананы", 94.30, 100, 9)
        ));
        categories.addAll(List.of(
                new Category("Овощи", new Product[]{
                        Product.search("Картофель"),
                        Product.search("Морковь"),
                        Product.search("Редис"),
                }),
                new Category("Фрукты", new Product[]{
                        Product.search("Яблоки"),
                        Product.search("Груши"),
                        Product.search("Бананы"),
                })
        ));
        users.addAll(List.of(
                new User("username1", "password1"),
                new User("username2", "password2")
        ));
    }
    private static void basketAdd(String username, String productName, int count) {
        User user = User.search(username);
        Product product = Product.search(productName);
        if (user != null && product != null) {
            user.basket.add(product, count);
        }
    }
    private static void basketDel(String username, String productName) {
        User user = User.search(username);
        Product product = Product.search(productName);
        if (user != null && product != null) {
            user.basket.remove(product);
        }
    }
    private static void viewCategories() {
        System.out.println();
        for (Category category : categories) {
            viewCategory(category);
        }
        System.out.println();
    }
    private static void viewCategory(Category category) {
        System.out.printf("%17s %9s %7s %8s\n", "[ "+category.getName()+" ]", "Цена", "Кол-во", "Рейтинг");
        line();
        category.getProducts().forEach(product -> {
            System.out.printf("%17s %9.2f %7d %8.2f\n", product.getName(), product.getPrice(), product.getCount(), product.getRating());
        });
        line();
        System.out.println();
    }
    private static void viewUsers() {
        System.out.println();
        for (User user : users) {
            viewUser(user);
        }
        System.out.println();
    }
    private static void viewUser(User user) {
        System.out.printf("%17s %9s %7s %8s\n", "[ "+user.getUsername()+" ]", "Цена", "Кол-во", "Итого");
        line();
        double sum = 0;
        for (Product product : user.basket.getProducts()) {
            sum += product.getPrice() * product.getCount();
            System.out.printf("%17s %9.2f %7d %8.2f\n", product.getName(), product.getPrice(), product.getCount(), product.getPrice() * product.getCount());
        }
        line();
        System.out.printf("%44.2f\n", sum);
        System.out.println();
    }
    private static void line() {
        for (int i = 0; i < 44; i++) System.out.print("-");
        System.out.print("\n");
    }
}

@Getter
class Product {
    public static ArrayList<Product> data = new ArrayList<>();
    public static Product search(String name) {
        for (Product product : data) if (product.getName().equals(name)) return product;
        return null;
    }
    /** *** */
    private String name;
    private double price;
    private double rating;
    private int count;
    /** *** */
    public Product(String name, double price, int count, double rating) {
        try {
            this.name = name;
            this.price = price;
            this.rating = rating;
            this.count = count;
            this.validate();
        } catch (Exception e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
    }
    public void setCount(int count) {
        this.count = count;
    }
    private void validate() throws Exception {
        String error;
        error = this.name.length() < 3 ? "Error: use [3 < name.length]" : null;
        error = this.price <= 0 ? "Error: use [0 < price]" : null;
        error = count < 0 ? "Error: use [0 <= count]" : null;
        error = rating < 0 || rating > 10 ? "Error: use [0 <= rating <= 10]" : null;
        if (error != null) throw new Exception(error);
    }
}
@Getter
final class Category {
    public static ArrayList<Category> data = new ArrayList<>();
    public static Category search(String name) {
        for (Category category : data) if (category.getName().equals(name)) return category;
        return null;
    }
    /** *** */
    private final String name;
    private final ArrayList<Product> products = new ArrayList<>();
    /** *** */
    public Category(String name) {
        this.name = name;
    }
    public Category(String name, Product[] products) {
        this.name = name;
        this.products.addAll(Arrays.asList(products));
    }
    public void add(Product product) {
        this.products.add(product);
    }
    public void remove(Product product) {
        this.products.remove(product);
    }
}

@Getter
@NoArgsConstructor
final class Basket {
    private final ArrayList<Product> products = new ArrayList<>();
    /** *** */
    public void add(Product product, int count) {
        // TODO требует доработок
        var findProduct = Product.search(product.getName());
        if (findProduct != null) {
            if (findProduct.getCount() < count) count = findProduct.getCount();
            findProduct.setCount(findProduct.getCount() - count);
            this.products.add(new Product(product.getName(), product.getPrice(), count, product.getRating()));
        }
    }
    public void remove(Product product) {
        // TODO требует доработок
        Product findProduct;
        for (Product item : this.products) {
            if (item.getName().equals(product.getName())) {
                findProduct = Product.search(item.getName());
                if (findProduct != null) {
                    findProduct.setCount(findProduct.getCount() + item.getCount());
                }
                this.products.remove(item);
            }
        }
    }
}
class User {
    public static ArrayList<User> data = new ArrayList<>();
    public static User search(String username) {
        for (User user : data) if (user.getUsername().equals(username)) return user;
        return null;
    }
    /** *** */
    public final Basket basket = new Basket();
    private final String username;
    private String password;
    /** *** */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void auth(String password) throws Exception {
        if (!this.password.equals(password)) throw new Exception("Введен неверный логин или пароль");
        // todo sign in
    }
    public void newPassword(String oldPassword, String newPassword, String newPasswordRepeat) throws Exception {
        if (!this.password.equals(oldPassword)) throw new Exception("Введен неверный пароль");
        if (!newPassword.equals(newPasswordRepeat)) throw new Exception("Пароли не совпадают");
        this.password = newPassword;
        // todo save user
    }
}