package lesson6.homework;

// TODO Дописать склады и заказы
final class Controller {
    static void addProduct(String name, double price) {
        IProduct.create(name, price);
    }

    static void removeProduct(int id) {
        IProduct.remove(IProduct.getById(id));
    }

    static void viewProduct(int id) {
        IProduct p = IProduct.getById(id);
        if (p == null) Viewer.error("Товар не найден");
        else Viewer.info("Товар:\n\tID: %d\n\tНаименование: %s\n\tСтоимость: %1.2f\n",
                p.getId(),
                p.getName(),
                p.getPrice()
        );
    }

    static void viewProducts() {
        for (IProduct item : IProduct.data) {
            Viewer.info("Товар:\n\tID: %d\n\tНаименование: %s\n\tСтоимость: %1.2f\n",
                    item.getId(),
                    item.getName(),
                    item.getPrice());
        }
    }
}
