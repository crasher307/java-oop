package lesson3.homework;

import java.util.ArrayList;
import java.util.List;

class NumberGame extends Game {
    @Override
    public String getGameName() {
        return "Цифры";
    }

    @Override
    List<String> charList() {
        List<String> charList = new ArrayList<>();
        for (int i = 0; i < 10; i++) charList.add(String.valueOf(i));
        return charList;
    }
}
