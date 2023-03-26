package lesson6.homework;

import java.util.ArrayList;
import java.util.List;

interface IWarehouse extends IBasket {
    List<IWarehouse> data = new ArrayList<>();

    static IWarehouse create(String address) {
        IWarehouse item = new MWarehouse(address);
        data.add(item);
        return item;
    }

    static IWarehouse getById(int id) {
        for (IWarehouse item : data) {
            if (item.getId() == id) return item;
        }
        return null;
    }

    String getAddress();
}
