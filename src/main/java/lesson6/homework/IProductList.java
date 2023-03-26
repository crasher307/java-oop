package lesson6.homework;

interface IProductList extends IProduct {
    void incCount(int amount);

    void decCount(int amount);

    int getCount();
}
