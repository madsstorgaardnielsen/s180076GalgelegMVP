package dk.s180076msn.s180076galgelegmvp.gamefactory;

import java.util.ArrayList;

public abstract class Game {
    private ArrayList<String> wordList = new ArrayList<>();
    private boolean isWon = false;
    private boolean isLost = false;
    private String correctWord;
    private String guess;
    private String playerName;
    private int amountWrongGuess;

    public ArrayList<String> getWordList() {
        return wordList;
    }

    public boolean isWon() {
        return isWon;
    }

    public boolean isLost() {
        return isLost;
    }

    public String getCorrectWord() {
        return correctWord;
    }

    public String getGuess() {
        return guess;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getAmountWrongGuess() {
        return amountWrongGuess;
    }

    public void setWordList(ArrayList<String> wordList) {
        this.wordList = wordList;
    }

    public void setWon(boolean won) {
        isWon = won;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }

    public void setCorrectWord(String correctWord) {
        this.correctWord = correctWord;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setAmountWrongGuess(int amountWrongGuess) {
        this.amountWrongGuess = amountWrongGuess;
    }
}
