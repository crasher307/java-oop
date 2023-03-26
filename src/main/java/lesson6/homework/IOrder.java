package lesson6.homework;

import java.util.ArrayList;
import java.util.List;

interface IOrder extends IBasket {
    List<IOrder> data = new ArrayList<>();

    static IOrder create(MWarehouse warehouse) {
        IOrder item = new MOrder(warehouse);
        data.add(item);
        return item;
    }

    static IOrder getById(int id) {
        for (IOrder item : data) {
            if (item.getId() == id) return item;
        }
        return null;
    }

    IWarehouse getWarehouse();
}
