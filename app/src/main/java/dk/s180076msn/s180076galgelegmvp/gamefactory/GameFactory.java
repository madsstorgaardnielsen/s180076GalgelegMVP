package dk.s180076msn.s180076galgelegmvp.gamefactory;

import java.util.ArrayList;
import java.util.Random;


public class GameFactory {

    ArrayList<String> wordList;
    int amountWrongGuess;
    boolean isLost = false;
    boolean isWon = false;
    String correctWord;

    public Game makeGame(String difficultyLevel) {
        wordList = new ArrayList<>();
        wordList.clear();

        if (difficultyLevel.equals("easy")) {
            easyGame();
            startNewGame();
            return new EasyGame(wordList, amountWrongGuess, isLost, isWon, correctWord);
        } else if (difficultyLevel.equals("medium")) {
            mediumGame();
            startNewGame();
            return new MediumGame(wordList, amountWrongGuess, isLost, isWon, correctWord);
        } else if (difficultyLevel.equals("hard")) {
            hardGame();
            startNewGame();
            return new HardGame(wordList, amountWrongGuess, isLost, isWon, correctWord);
        } else return null;
    }

    public void startNewGame() {
        correctWord = wordList.get(new Random().nextInt(wordList.size()));
        amountWrongGuess = 0;
        isLost = false;
        isWon = false;
    }

    public void easyGame() {
        wordList.add("e");
/*        wordList.add("dæk");
        wordList.add("død");
        wordList.add("bøf");
        wordList.add("Damp");
        wordList.add("Drab");*/
    }

    public void mediumGame() {
        wordList.add("medium");
/*        wordList.add("Kager");
        wordList.add("Penge");
        wordList.add("Prag");
        wordList.add("Hunde");
        wordList.add("Fodder");*/
    }

    public void hardGame() {
        wordList.add("hard");
/*        wordList.add("Kastanjerne");
        wordList.add("Kolibrierne");
        wordList.add("Musvågen");
        wordList.add("Elefanter");
        wordList.add("Guitaren");*/
    }


}
