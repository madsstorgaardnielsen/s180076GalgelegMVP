package dk.s180076msn.s180076galgelegmvp.gamefactory;

import java.util.ArrayList;
import java.util.Random;


public class GameFactory {
    private ArrayList<String> wordList;
    private int amountWrongGuess;
    private boolean isLost = false;
    private boolean isWon = false;
    private String correctWord;

    public Game makeGame(String difficultyLevel) {
        wordList = new ArrayList<>();
        wordList.clear();

        switch (difficultyLevel) {
            case "easy":
                easyGame();
                startNewGame();
                return new EasyGame(wordList, amountWrongGuess, isLost, isWon, correctWord);
            case "medium":
                mediumGame();
                startNewGame();
                return new MediumGame(wordList, amountWrongGuess, isLost, isWon, correctWord);
            case "hard":
                hardGame();
                startNewGame();
                return new HardGame(wordList, amountWrongGuess, isLost, isWon, correctWord);
            default:
                return null;
        }
    }

    private void startNewGame() {
        correctWord = wordList.get(new Random().nextInt(wordList.size()));
        amountWrongGuess = 0;
        isLost = false;
        isWon = false;
    }

    private void easyGame() {
        wordList.add("e");
        /*wordList.add("dæk");
        wordList.add("død");
        wordList.add("bøf");
        wordList.add("damp");
        wordList.add("drab");*/
    }

    private void mediumGame() {
        wordList.add("kager");
        wordList.add("penge");
        wordList.add("prag");
        wordList.add("hunde");
        wordList.add("fodder");
    }

    public void hardGame() {
        wordList.add("kastanjerne");
        wordList.add("kolibrierne");
        wordList.add("musvågen");
        wordList.add("elefanter");
        wordList.add("guitaren");
    }
}
