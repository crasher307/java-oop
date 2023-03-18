package lesson3.homework;

import java.util.Scanner;

// Интерфейс улучшать не стал, тк. непонятно что еще может в нем понадобиться на тек. момент
// В GamesList имеется список всех классов, наследуемых от Game (abstractGame)

public class Main {
    static boolean debug = true;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean launch = false;
        while (!launch) {
            GamesList.view();
            System.out.print("Выберите желаемую: ");
            launch = GamesList.run(scanner.nextInt());
        }
    }
}

