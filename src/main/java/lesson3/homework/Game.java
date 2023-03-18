package lesson3.homework;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@NoArgsConstructor
abstract class Game implements iGame {
    private final boolean debug = Main.debug;
    private final Scanner scanner = new Scanner(System.in);
    private final SecureRandom random = new SecureRandom();
    private String gameName; // Название
    private int wordSize; // Длина загаданного
    private String word; // Загадано
    private int tryCountMax; // Макс. попыток
    private int tryCount = 0; // Тек. попытка
    private GameStatus gameStatus = GameStatus.INIT; // Статус
    private final List<Answer> history = new ArrayList<>(); // TODO написать вычитку истории

    @Override
    public void start(int wordSize, int tryCountMax) {
        this.wordSize = wordSize;
        this.word = this.generateWord().toLowerCase();
        if (this.word.length() != this.wordSize) this.wordSize = this.word.length();
        this.tryCountMax = tryCountMax;
        this.gameStatus = GameStatus.WORK;

        String word;
        while (this.gameStatus != GameStatus.FINISH) {
            System.out.printf("Введите слово (%d символа): ", this.wordSize);
            word = scanner.nextLine();
            if (word.length() == this.wordSize) {
                this.setAnswer(word);
            } else {
                System.out.println("Неверное кол-во символов");
            }
        }
    }

    protected String generateWord() {
        List<String> charList = charList();
        StringBuilder res = new StringBuilder();
        for (int i = 0, idx; i < this.wordSize; i++) {
            // TODO подумать над удалением, тк. символов может не хватить
            idx = random.nextInt(charList.size());
            res.append(charList.get(idx));
            charList.remove(idx);
        }
        return res.toString();
    }

    abstract List<String> charList(); // Список использ. для генерации символов

    private void setAnswer(String value) {
        int cow = 0;
        int bull = 0;
        for (int i = 0; i < value.length(); i++) {
            if (this.word.contains(Character.toString(value.charAt(i)))) cow++;
            if (this.word.charAt(i) == value.charAt(i)) bull++;
        }
        this.tryCount++;

        Answer answer = new Answer(cow, bull, value);
        this.history.add(answer);

        this.response(answer);
    }

    private void response(Answer answer) {
        if (this.wordSize == answer.getBulls() || this.tryCount >= this.tryCountMax) {
            this.gameStatus = GameStatus.FINISH;
        }
        String response = String.format(
                "\n\tВведенное слово:\t%s\n\tКоров:\t%s\n\tБыков:\t%s",
                answer.getWord(),
                answer.getCows(),
                answer.getBulls()
        );
        System.out.println(switch (this.gameStatus) {
            case WORK -> String.format("%s%s\n\tЗагаданное слово:\t%s%s",
                    this.debug ? String.format("*** DEBUG [Status: %s, Word: %s] ***\n", this.gameStatus, this.word) : "",
                    String.format("Попытка %d из %d", this.tryCount, this.tryCountMax),
                    String.format("%" + this.wordSize + "s", "").replace(' ', '?'),
                    response
            );
            case FINISH -> String.format("%s\n\tЗагаданное слово:\t%s%s",
                    this.wordSize == answer.getBulls() ? "Победа!" : "Поражение!",
                    this.word,
                    response
            );
            default -> this.gameStatus;
        });
    }
}
