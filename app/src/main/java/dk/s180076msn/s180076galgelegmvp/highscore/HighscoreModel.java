package dk.s180076msn.s180076galgelegmvp.highscore;

public class HighscoreModel {
    private String playerName;
    private String correctWord;
    private int amountWrongGuess;

    public int getAmountWrongGuess() {
        return amountWrongGuess;
    }

    public HighscoreModel(String playerName, String correctWord, int amountWrongGuess) {
        this.playerName = playerName;
        this.correctWord = correctWord;
        this.amountWrongGuess = amountWrongGuess;
    }

    public String toString() {
        if (amountWrongGuess == 0) {
            return playerName + " havde ordet \"" + correctWord + "\" og fandt det med " + amountWrongGuess + " forkerte gæt.";
        }
        if (amountWrongGuess == 1) {
            return playerName + " havde ordet \"" + correctWord + "\" og fandt det med " + amountWrongGuess + " forkert gæt.";
        } else
            return playerName + " havde ordet \"" + correctWord + "\" og fandt det med " + amountWrongGuess + " forkerte gæt.";
    }
}
