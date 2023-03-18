package lesson3.homework;

import java.util.ArrayList;
import java.util.List;

class GamesList {
    static List<Game> games = new ArrayList<>(List.of(
            new NumberGame(),
            new WordEnGame(),
            new WordRuGame(),
            new WordsGame()
    ));

    static void view() {
        System.out.println("Список игр:");
        for (int i = 0; i < games.size(); i++) System.out.printf("\t%d - %s\n", i + 1, games.get(i).getGameName());
    }

    static boolean run(int idx) {
        return GamesList.run(idx, 4, 10);
    }

    static boolean run(int idx, int wordSize, int tryCountMax) {
        try {
            Game game = games.get(idx - 1);
            game.start(wordSize, tryCountMax);

            System.out.println("История:");
            game.getHistory().forEach(answer -> System.out.printf("\t%s, Коров: %s, Быков: %s\n", answer.getWord(), answer.getCows(), answer.getBulls()));

            return true;
        } catch (Exception e) {
            System.out.println("Такой игры еще не существует");
        }
        return false;
    }
}
