package lesson7.homework.view;

import lesson7.homework.model.*;
import lib.Console;
import lombok.*;

import java.util.*;

@Getter
public final class Viewer {
    private static final Console console = Console.out;

    public static void showLog(List<Log> data) {
        console.color("c").log(getLine("Log"));
        for (Log log : data) {
            if (log.getError().isEmpty()) console.log(log);
            else console.color("r").log(log);
        }
        console.color("c").log(getLine(null));
    }

    /** Service */
    private static String getLine(String name) {
        int length = 77;
        if (name != null) {
            name = "#[#" + name + "#]#";
            length -= name.length();
        }
        return String.format("---%s%" + length + "s", name != null ? name : "", "")
                .replace(" ", "-")
                .replace("#", " ");
    }
}