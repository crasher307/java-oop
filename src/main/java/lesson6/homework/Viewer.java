package lesson6.homework;

import lib.Console;
import lombok.AllArgsConstructor;

import java.util.List;

/** Views */
@AllArgsConstructor
final class Viewer {
    public static void list(List<?> data) {
        for (Object item : data) {
            Console.out.log(item);
        }
    }

    public static void info(String format, Object... data) {
        Console.out.log(format, data);
    }

    public static void error(String message) {
        Console.out.err(message);
    }
}
