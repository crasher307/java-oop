package lesson6.homework;

import lib.*;

public class Main {
    public static void main(String[] args) {
        load();
        Controller.viewProducts();
        // Viewer.list(IProduct.data);
        // Viewer.info(IProduct.data.get(0));

        // Console.out.reset(false).color(Console.Color.CYAN); // Set color
        // for (IProduct item : IProduct.data) Console.out.log(item); // view products default
        // Console.out.color(Console.Color.YELLOW); // Set color
        // // View products in warehouse
        // String products;
        // for (IWarehouse item : IWarehouse.data) {
        //     products = item.getProductList().toString();
        //     products = products.substring(1, products.length() - 1);
        //     Console.out.log("Склад: %s\n\t%s\n",
        //             item.getAddress(),
        //             products.replace("], ", "]\n\t")
        //     );
        // }
        // Console.out.initBcp(); // Clear color


        // ArrayList<Product> myOrder = new ArrayList<>(List.of(
        //         new Product("Test product 1", 12, 20.50),
        //         new Product("Test product 2", 20, 70.20),
        //         new Product("Test product 3", 4, 120.50)
        // ));
        // var order = new Order(myOrder);
    }

    private static void load() {
        IProduct.create("Test product 1", 100.00);
        IProduct.create("Test product 2", 200.00);
        IProduct.create("Test product 3", 300.00);
        try {
            IWarehouse.create("Test address 1")
                    .incProduct(IProduct.getById(0), 10)
                    .incProduct(IProduct.getById(1), 20)
                    .decProduct(IProduct.getById(0), 5)
                    .decProduct(IProduct.getById(1), 3);
            IWarehouse.create("Test address 2")
                    .incProduct(IProduct.getById(1), 10)
                    .incProduct(IProduct.getById(2), 20)
                    .decProduct(IProduct.getById(2), 5)
                    .decProduct(IProduct.getById(1), 3);
        } catch (Exception e) {
            Console.out.err(e.getMessage()
                    .replace("[", "[\n\t")
                    .replace("]", "\n]")
                    .replace(", ", "\n\t")
            );
        }
    }
}

