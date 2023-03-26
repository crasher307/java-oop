package lesson6.homework;

import lesson6.homework.exchange.ExWarehouse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
abstract class MBasket implements IBasket {
    private static int idx = 0;
    private final int id = idx++;
    List<IProductList> productList = new ArrayList<>();

    public IBasket incProduct(IProduct product, int amount) {
        int id = product.getId();
        for (IProductList item : productList) {
            if (id == item.getId()) {
                item.incCount(amount);
                return this;
            }
        }
        productList.add(new MProduct(product, amount));
        return this;
    }

    public IBasket decProduct(IProduct product, int amount) throws ExWarehouse {
        int id = product.getId();
        for (IProductList item : productList) {
            if (id == item.getId()) {
                if (amount < item.getCount()) item.decCount(amount);
                else if (amount == item.getCount()) productList.remove(item);
                else throw new ExWarehouse(String.format("Нехватка товара (запрошено %d)\n%s", amount, item));
                return this;
            }
        }
        throw new ExWarehouse(String.format("Товар отсутствует в списке\n%s", product));
    }
}
