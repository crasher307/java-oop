package lesson3.homework;

import java.util.ArrayList;
import java.util.List;

class WordEnGame extends Game {
    @Override
    public String getGameName() {
        return "Буквы EN";
    }

    @Override
    List<String> charList() {
        String[] abc = new String[]{
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        };
        return new ArrayList<>(List.of(abc));
    }
}
