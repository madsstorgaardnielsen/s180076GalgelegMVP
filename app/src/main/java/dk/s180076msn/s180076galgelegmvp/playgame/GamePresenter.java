package dk.s180076msn.s180076galgelegmvp.playgame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import dk.s180076msn.s180076galgelegmvp.gamefactory.Game;
import dk.s180076msn.s180076galgelegmvp.gamefactory.GameFactory;
import dk.s180076msn.s180076galgelegmvp.highscore.HighscoreModel;
import dk.s180076msn.s180076galgelegmvp.settings.SettingsModel;

public class GamePresenter implements Subject {
    private GameModel gm;
    private SettingsModel sm;
    private GameFactory gf;
    private Game g;
    private ArrayList<Observer> observers;
    private final String SETTINGSKEY = "difficultysettingskey";
    private final String SHAREDPREFKEY = "shared_preferences";


    private ArrayList<SettingsModel> difficultySettings;
    ArrayList<String> customWordlist;


    private boolean customWordlistSettings;
    private Context context;
    String CUSTOM_WORDLIST_SETTING_KEY = "iscustomwordlist";
    String CUSTOM_WORD_LIST_KEY = "wordlistkey";

    public GamePresenter(Context context) {
        this.context = context;
        sm = new SettingsModel();
        gm = new GameModel();
        gf = new GameFactory();
        observers = new ArrayList<>();
        initGame();
    }

    public void initGame() {
        sm.setDifficultyLevel(loadDifficultySettings());
        if (isWordlistCustom()) {
            ArrayList<String> custom;
            custom = loadCustomWordList();
            g = gf.makeCustomGame(sm.getDifficultyLevel(), custom);
        } else {
            g = gf.makeGame(sm.getDifficultyLevel());
        }

        setCorrectWord(g.getCorrectWord());
        setAmountWrongGuess(g.getAmountWrongGuess());
        setGuess(g.getGuess());
        setLost(g.isLost());
        setWon(g.isWon());
        setPlayerName(g.getPlayerName());
        setWordProgress(getHiddenStr());
        clearUsedLetters();
    }

    String IS_CUSTOM_WORDLIST_KEY = "customwordlistkey";

    private boolean isWordlistCustom() {
        boolean setting;
        SharedPreferences sharedPreferences = context.getSharedPreferences(IS_CUSTOM_WORDLIST_KEY, Context.MODE_PRIVATE);
        setting = sharedPreferences.getBoolean(CUSTOM_WORDLIST_SETTING_KEY, false);
        return setting;
    }

    String CUSTOM_WORDS_KEY = "wordlistkey";

    private ArrayList<String> loadCustomWordList() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CUSTOM_WORDS_KEY, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(CUSTOM_WORDS_KEY, "fejl");
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        customWordlist = gson.fromJson(json, type);

        if (customWordlist == null) {
            customWordlist = new ArrayList<>();
            customWordlist.add("Fejl, ingen ord valgt fra ordliste i settings.");
        }
        return customWordlist;
    }


    String DIFFICULTY_SETTING_KEY = "difficultysettingskey";
    private String loadDifficultySettings() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DIFFICULTY_SETTING_KEY, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DIFFICULTY_SETTING_KEY, "easy");
    }


    public boolean guessLetter(String letter) {
        addUsedLetter(letter.toLowerCase());
        String correctWord = gm.getCorrectWord().toLowerCase();
        int amountWrongGuess = gm.getAmountWrongGuess();
        if (correctWord.contains(letter)) {
            setHiddenWordProgress();
            return true;
        } else {
            ++amountWrongGuess;
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

    public String getUsedLetters() {
        return Arrays.toString(gm.getUsedLetters().toArray()).replace("[", "").replace("]", "");
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
            observer.update(isWon(), isLost(), getHiddenStr(), getAmountWrongGuess(), getUsedLetters(), getCorrectWord(), getPlayerName());
        }
        System.out.println("OBSERVERS UPDATED");
    }
}
