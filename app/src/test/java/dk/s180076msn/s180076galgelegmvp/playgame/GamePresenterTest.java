package dk.s180076msn.s180076galgelegmvp.playgame;

import org.junit.Test;

import static org.junit.Assert.*;

public class GamePresenterTest {
    GamePresenter gp = new GamePresenter();

    @Test
    public void initGame() {
        gp.initGame();

        assertEquals(gp.getWordProgress(), "*");
        assertEquals(gp.getCorrectWord(), "e");
        assertEquals(gp.getAmountWrongGuess(), 0);
    }

    @Test
    public void guessLetter() {
        gp.initGame();
        assertEquals(gp.guessLetter("e"), true);
        assertEquals(gp.guessLetter("l"), false);
    }

    @Test
    public void getHiddenStr() {
        gp.initGame();

        assertEquals(gp.getHiddenStr(), "*");
        gp.setCorrectWord("abc");
        assertEquals(gp.getHiddenStr(), "***");
    }

    @Test
    public void getHiddenWordProgress() {
        gp.initGame();
        gp.setCorrectWord("abc");
        gp.setHiddenWordProgress();
        assertEquals(gp.getWordProgress(), "***");
        gp.guessLetter("a");
        assertEquals(gp.getWordProgress(), "a**");
    }

    @Test
    public void clearUsedLetters() {
        gp.initGame();
        gp.addUsedLetter("l");

        assertEquals(1, gp.getUsedLetters().size());
        gp.clearUsedLetters();
        assertEquals(0, gp.getUsedLetters().size());
    }

    @Test
    public void getGuess() {
        gp.initGame();
        gp.setGuess("guess");
        assertEquals("guess",gp.getGuess());
    }

    @Test
    public void getPlayerName() {
        gp.initGame();
        gp.setPlayerName("name");
        assertEquals("name",gp.getPlayerName());
    }

    @Test
    public void getCorrectWord() {
        gp.initGame();
        gp.setCorrectWord("correctword");
        assertEquals("correctword",gp.getCorrectWord());
    }

    @Test
    public void getAmountWrongGuess() {
        gp.initGame();
        gp.setAmountWrongGuess(1);
        assertEquals(1,gp.getAmountWrongGuess());
    }

    @Test
    public void isLost() {
        gp.initGame();
        assertEquals(false,gp.isLost());
    }


    @Test
    public void isWon() {
        gp.initGame();
        assertEquals(false,gp.isWon());
    }

}