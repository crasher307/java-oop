package lesson6.homework;

import java.util.List;

// BASKET
interface IBasket {
    IBasket incProduct(IProduct product, int amount);

    IBasket decProduct(IProduct product, int amount) throws Exception;

    int getId();

    List<IProductList> getProductList();
}
