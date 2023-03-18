package lesson3.homework;

import java.util.List;

class WordsGame extends Game {
    @Override
    public String getGameName() {
        return "Слова";
    }

    @Override
    List<String> charList() {
        return null;
    }

    @Override
    protected String generateWord() {
        String[] words = new String[]{
                // TODO добавить слов
                "Hello",
                "World",
                "test1",
                "TEST2"
        };
        return words[super.getRandom().nextInt(words.length)];
    }
}
