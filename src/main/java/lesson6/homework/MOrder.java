package lesson6.homework;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
final class MOrder extends MBasket implements IOrder {
    private final IWarehouse warehouse;

    @Override
    public String toString() {
        return String.format("MWarehouse [warehouse<address>: %s, productList: %s]", this.warehouse.getAddress(), this.productList);
    }
}
