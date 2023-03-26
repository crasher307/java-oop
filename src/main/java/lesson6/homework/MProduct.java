package lesson6.homework;

import lombok.Getter;

/** Models */
// PRODUCT
@Getter
final class MProduct implements IProduct, IProductList {
    private static int idx = 0;
    private final int id;
    private final String name;
    private final double price;
    private int count = 0;

    public MProduct(IProduct product, int count) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.count = count;
    }

    public MProduct(String name, double price) {
        this.id = idx++;
        this.name = name;
        this.price = price;
    }

    public void incCount(int amount) {
        this.count += amount;
    }

    public void decCount(int amount) {
        this.count -= amount;
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        data.append(String.format("name: %s, price: %1.2f", this.name, this.price));
        if (this.count > 0) data.append(String.format(", count: %d", this.count));
        return String.format("MProduct [%s]", data);
    }
}
