package dk.s180076msn.s180076galgelegmvp.playgame;

import java.util.ArrayList;

public class GameModel {

    private ArrayList<String> usedLetters = new ArrayList<>();
    private String guess;
    private String playerName;
    private String wordProgress;
    private String correctWord;
    private String difficultyLevel;
    private int amountWrongGuess;
    private boolean isLost = false;
    private boolean isWon = false;

    public GameModel(ArrayList<String> usedLetters, String guess, String playerName, String wordProgress, String correctWord, int amountWrongGuess, boolean isLost, boolean isWon) {
        this.usedLetters = usedLetters;
        this.guess = guess;
        this.playerName = playerName;
        this.wordProgress = wordProgress;
        this.correctWord = correctWord;
        this.amountWrongGuess = amountWrongGuess;
        this.isLost = isLost;
        this.isWon = isWon;
    }

    public GameModel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void clearUsedLetters() {
        usedLetters.clear();
    }

    public ArrayList<String> getUsedLetters() {
        return usedLetters;
    }

    public void addUsedLetter(String letter) {
        usedLetters.add(letter);
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getWordProgress() {
        return wordProgress;
    }

    public void setWordProgress(String wordProgress) {
        this.wordProgress = wordProgress;
    }

    public String getCorrectWord() {
        return correctWord;
    }

    public void setCorrectWord(String correctWord) {
        this.correctWord = correctWord;
    }

    public int getAmountWrongGuess() {
        return amountWrongGuess;
    }

    public void setAmountWrongGuess(int amountWrongGuess) {
        this.amountWrongGuess = amountWrongGuess;
    }

    public boolean isLost() {
        return isLost;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }

    public boolean isWon() {
        return isWon;
    }

    public void setWon(boolean won) {
        isWon = won;
    }
}
