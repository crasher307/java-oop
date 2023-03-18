package lesson3.homework;

import java.util.ArrayList;
import java.util.List;

class WordRuGame extends Game {
    @Override
    public String getGameName() {
        return "Буквы RU";
    }

    @Override
    List<String> charList() {
        String[] abc = new String[]{
                "А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й",
                "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф",
                "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я"
        };
        return new ArrayList<>(List.of(abc));
    }
}
