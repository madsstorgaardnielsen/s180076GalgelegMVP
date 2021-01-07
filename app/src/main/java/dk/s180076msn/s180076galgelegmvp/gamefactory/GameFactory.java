package dk.s180076msn.s180076galgelegmvp.gamefactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import dk.s180076msn.s180076galgelegmvp.SheetDataLoader.DataLoader;


public class GameFactory {
    private ArrayList<String> wordList;
    private int amountWrongGuess;
    private boolean isLost = false;
    private boolean isWon = false;
    private String correctWord;

    public Game makeCustomGame(String difficultyLevel, ArrayList<String> customWordList) {

        switch (difficultyLevel) {
            case "easy":
                startNewCustomGame(customWordList);
                return new EasyGame(customWordList, amountWrongGuess, isLost, isWon, correctWord);
            case "medium":
                startNewCustomGame(customWordList);
                return new MediumGame(customWordList, amountWrongGuess, isLost, isWon, correctWord);
            case "hard":
                startNewCustomGame(customWordList);
                return new HardGame(customWordList, amountWrongGuess, isLost, isWon, correctWord);
            default:
                return null;
        }
    }

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

    private void startNewCustomGame(ArrayList<String> customWordList) {
        System.out.println(correctWord);
        correctWord = customWordList.get(new Random().nextInt(customWordList.size()));
        amountWrongGuess = 0;
        isLost = false;
        isWon = false;
    }

    private void startNewGame() {
        correctWord = wordList.get(new Random().nextInt(wordList.size()));
        amountWrongGuess = 0;
        isLost = false;
        isWon = false;
    }

    private void easyGame() {
        wordList.add("a");
        /*wordList.add("dæk");
        wordList.add("død");
        wordList.add("bøf");
        wordList.add("damp");
        wordList.add("drab");*/
    }

    private void mediumGame() {
        wordList.add("b");
        /*wordList.add("kager");
        wordList.add("penge");
        wordList.add("prag");
        wordList.add("hunde");
        wordList.add("fodder");*/
    }

    public void hardGame() {
        wordList.add("c");
        /*wordList.add("kastanjerne");
        wordList.add("kolibrierne");
        wordList.add("musvågen");
        wordList.add("elefanter");
        wordList.add("guitaren");*/
    }
}
