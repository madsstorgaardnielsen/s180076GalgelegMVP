package dk.s180076msn.s180076galgelegmvp.playgame;

public interface Observer {
    void update(boolean isWon, boolean isLost, String hiddenWordProgress, int amountWrongGuess, String usedLetters, String hiddenWord, String playerName);
}
