package dk.s180076msn.s180076galgelegmvp.playgame;

import java.util.ArrayList;

import dk.s180076msn.s180076galgelegmvp.gamefactory.Game;
import dk.s180076msn.s180076galgelegmvp.gamefactory.GameFactory;
import dk.s180076msn.s180076galgelegmvp.settings.SettingsModel;

public class GamePresenter implements Subject{
    private GameModel gm;
    private SettingsModel sm;
    private GameFactory gf;
    private Game g;
    private ArrayList<Observer> observers;

    public GamePresenter() {
        sm = new SettingsModel();
        gm = new GameModel(sm.getDifficultyLevel());
        gf = new GameFactory();
        observers = new ArrayList<>();
        initGame();
    }

    public void initGame() {
        //TODO alle gamemodels variable sættes af gamefactory
        g = gf.makeGame(sm.getDifficultyLevel());

        setCorrectWord(g.getCorrectWord());
        setAmountWrongGuess(g.getAmountWrongGuess());
        setGuess(g.getGuess());
        setLost(g.isLost());
        setWon(g.isWon());
        setPlayerName(g.getPlayerName());
        setWordProgress(getHiddenStr());
        clearUsedLetters();
    }

    public boolean guessLetter(String letter) {
        addUsedLetter(letter);
        String correctWord = gm.getCorrectWord();
        int amountWrongGuess = gm.getAmountWrongGuess();
        if (correctWord.contains(letter)) {
            setHiddenWordProgress();
            return true;
        } else {
            amountWrongGuess++;
            setAmountWrongGuess(amountWrongGuess);
            if (amountWrongGuess == 7) {
                setLost(true);
            }
            return false;
        }
    }

    public String getHiddenStr() {
        String str = "";
        for (int i = 0; i < gm.getCorrectWord().length(); i++) {
            str += "*";
        }
        return str;
    }

    public void setHiddenWordProgress() {
        String correctWord = gm.getCorrectWord();
        String wordProgress = "";
        int wordLength = correctWord.length();
        setWon(true);

        for (int n = 0; n < wordLength; n++) {
            String letter = correctWord.substring(n, n + 1);
            if (getUsedLetters().contains(letter)) {
                wordProgress = wordProgress + letter;
            } else {
                wordProgress = wordProgress + "*";
                setWon(false);
            }
        }
        setWordProgress(wordProgress);
    }

    public ArrayList<String> getUsedLetters() {
        return gm.getUsedLetters();
    }
    public void addUsedLetter(String letter) {
        gm.addUsedLetter(letter);
    }
    public void clearUsedLetters() {
        gm.clearUsedLetters();
    }

    public String getGuess() {
        return gm.getGuess();
    }

    public void setGuess(String guess) {
        gm.setGuess(guess);
        notifyObservers();
    }

    public String getPlayerName() {
        return gm.getPlayerName();
    }

    public void setPlayerName(String playerName) {
        gm.setPlayerName(playerName);
        notifyObservers();
    }

    public String getWordProgress() {
        return gm.getWordProgress();
    }

    public void setWordProgress(String wordProgress) {
        gm.setWordProgress(wordProgress);
        notifyObservers();
    }

    public String getCorrectWord() {
        return gm.getCorrectWord();
    }

    public void setCorrectWord(String correctWord) {
        gm.setCorrectWord(correctWord);
        notifyObservers();
    }

    public int getAmountWrongGuess() {
        return gm.getAmountWrongGuess();
    }

    public void setAmountWrongGuess(int amountWrongGuess) {
        gm.setAmountWrongGuess(amountWrongGuess);
        notifyObservers();
    }

    public boolean isLost() {
        return gm.isLost();
    }

    public void setLost(boolean lost) {
        gm.setLost(lost);
        notifyObservers();
    }

    public boolean isWon() {
        return gm.isWon();
    }

    public void setWon(boolean won) {
        gm.setWon(won);
        notifyObservers();
    }

    @Override
    public void register(Observer newObserver) {
        observers.add(newObserver);
        System.out.println("OBSERVER ADDED");
    }

    @Override
    public void unregister(Observer deleteObserver) {
        observers.remove(deleteObserver);
        System.out.println("OBSERVER DELETED");
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(isWon(), isLost(), getHiddenStr(), getAmountWrongGuess(), getUsedLetters());
        }
        System.out.println("OBSERVERS UPDATED");
    }
}
