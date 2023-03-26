package lesson6.homework;

import java.util.ArrayList;
import java.util.List;

/** Interfaces */
// PRODUCT
interface IProduct {
    List<IProduct> data = new ArrayList<>();

    static IProduct create(String name, double price) {
        IProduct item = new MProduct(name, price);
        data.add(item);
        return item;
    }

    static void remove(IProduct product) {
        data.remove(product);
    }

    static IProduct getById(int id) {
        for (IProduct item : data) {
            if (item.getId() == id) return item;
        }
        return null;
    }

    int getId();

    String getName();

    double getPrice();
}
