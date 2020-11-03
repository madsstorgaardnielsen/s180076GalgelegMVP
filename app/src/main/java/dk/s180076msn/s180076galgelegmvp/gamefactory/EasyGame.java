package dk.s180076msn.s180076galgelegmvp.gamefactory;

import java.util.ArrayList;

public class EasyGame extends Game {
    ArrayList<String> wordList;
    int amountWrongGuess;
    boolean isLost;
    boolean isWon;
    String correctWord;

    public EasyGame(ArrayList<String> wordList, int amountWrongGuess, boolean isLost, boolean isWon, String correctWord) {
        this.wordList = wordList;
        this.amountWrongGuess = amountWrongGuess;
        this.isLost = isLost;
        this.isWon = isWon;
        this.correctWord = correctWord;
    }

    @Override
    public ArrayList<String> getWordList() {
        return wordList;
    }

    @Override
    public int getAmountWrongGuess() {
        return amountWrongGuess;
    }

    @Override
    public boolean isLost() {
        return isLost;
    }

    @Override
    public boolean isWon() {
        return isWon;
    }

    @Override
    public String getCorrectWord() {
        return correctWord;
    }

    @Override
    public void setWordList(ArrayList<String> wordList) {
        this.wordList = wordList;
    }

    @Override
    public void setAmountWrongGuess(int amountWrongGuess) {
        this.amountWrongGuess = amountWrongGuess;
    }

    @Override
    public void setLost(boolean lost) {
        isLost = lost;
    }

    @Override
    public void setWon(boolean won) {
        isWon = won;
    }

    @Override
    public void setCorrectWord(String correctWord) {
        this.correctWord = correctWord;
    }
}
