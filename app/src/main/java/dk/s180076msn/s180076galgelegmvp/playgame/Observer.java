package dk.s180076msn.s180076galgelegmvp.playgame;

import java.util.ArrayList;

public interface Observer {
        void update(boolean isWon, boolean isLost, String hiddenWordProgress, int amountWrongGuess, ArrayList<String> usedLetters);
}
