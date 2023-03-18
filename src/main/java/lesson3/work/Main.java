package lesson3.work;

import lombok.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO Доработать

public class Main {
    public static void main(String[] args) {
        System.out.println("Меню");
        Scanner scanner = new Scanner(System.in);
        iGame game = null;
        switch (scanner.nextInt()) {
            case 1: game = new NumberGame(); break;
//            case 2: game = new WordGame(); break;
            default: System.out.println("Такой игры еще нет!");
        }
        game.start(4, 10);
        game.inputAnswer(scanner.nextLine());
        System.out.println(game.getGameStatus());
    }
}

interface iGame {
    void start(Integer sizeWord, Integer maxCountTry);
    Answer inputAnswer(String value);
    GameStatus getGameStatus();
}

@Data
@NoArgsConstructor
abstract class AbstractGame implements iGame {
    Integer sizeWord;
    String word;
    static Integer countTry = 0;
    Integer maxCountTry;
    GameStatus gameStatus = GameStatus.OFF;
    @Override
    public void start(Integer sizeWord, Integer maxCountTry) {
        this.sizeWord = sizeWord;
        this.maxCountTry = maxCountTry;
        this.word = this.generateWord();
        this.gameStatus = GameStatus.START;
    }
    private String generateWord() {
        List<String> charList = this.generateCharList();
        SecureRandom random = new SecureRandom();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.sizeWord; i++) res.append(charList.get(random.nextInt(charList.size())));
        return res.toString();
    }
    public abstract List<String> generateCharList();
    @Override
    public Answer inputAnswer(String value) {
        int cow = 0;
        int bull = 0;

        for (int i = 0; i < value.length(); i++) {
            if (this.word.contains(String.valueOf(value.charAt(i)))) cow++;
            if (this.word.charAt(i) == value.charAt(i)) bull++;
        }

        countTry++;
        if (countTry > this.maxCountTry) this.gameStatus = GameStatus.LOOSE;
        else if (bull == this.sizeWord) this.gameStatus = GameStatus.WIN;
        return new Answer(cow, bull, value);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Answer {
    private Integer cows;
    private Integer bulls;
    private String userInput;
}
enum GameStatus {
    OFF, START, LOOSE, WIN
}
class Response {
    //
}

// classes... -> Game

@EqualsAndHashCode(callSuper = true)
@Data
class NumberGame extends AbstractGame {
    @Override
    public List<String> generateCharList() {
        List<String> charList = new ArrayList<>();
        for (int i = 0; i < 10; i++) charList.add(String.valueOf(i));
        return charList;
    }
}