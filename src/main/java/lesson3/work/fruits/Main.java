package lesson3.work.fruits;

public class Main {
    public static void main(String[] args) {
        Fruit fruit = Fruit.APPLE;

        System.out.println(switch (fruit) {
            case APPLE -> "Яблоко";
            case ORANGE -> "Апельсин";
            case BANANA -> "Банан";
            case PINEAPPLE -> "Ананас";
            case PEAR -> "Груша";
        });
    }
}

