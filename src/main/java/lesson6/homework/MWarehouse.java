package lesson6.homework;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
final class MWarehouse extends MBasket implements IWarehouse {
    private final String address;

    @Override
    public String toString() {
        return String.format("MWarehouse [address: %s, productList: %s]", this.address, this.productList);
    }
}
