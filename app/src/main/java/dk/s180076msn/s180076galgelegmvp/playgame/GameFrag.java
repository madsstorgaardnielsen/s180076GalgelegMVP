package dk.s180076msn.s180076galgelegmvp.playgame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import dk.s180076msn.s180076galgelegmvp.R;
import dk.s180076msn.s180076galgelegmvp.highscore.HighscoreModel;
import dk.s180076msn.s180076galgelegmvp.settings.SettingsModel;


public class GameFrag extends Fragment implements Observer, View.OnClickListener {
    private Fragment wf;
    private Fragment lf;
    private GamePresenter gp;
    private Subject sgp;
    private HighscoreModel hm;
    private ImageView progressImage;
    private Button doGuessButton;
    private TextView hiddenWordProgressTextView, usedLettersTextView, amountWrongGuessTextView;
    private EditText guessEditText;
    private ArrayList<HighscoreModel> highscores;

    private final String SHAREDPREFKEY = "shared_preferences";
    private final String HIGHSCOREKEY = "highscore";

    String hiddenWordProgress;
    String amountWrongStr = "ud af 7 forkerte gæt brugt.";
    String noGuessYet = "Ingen gæt foretaget endnu.";
    String usedLetters;
    int amountWrongGuess;
    boolean isWon;
    boolean isLost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_game, container, false);

        gp = new GamePresenter(getActivity());


        wf = new WonFrag(gp);
        lf = new LostFrag(gp);

        sgp = gp;
        sgp.register(this);

        progressImage = root.findViewById(R.id.imageView);
        progressImage.setImageResource(R.drawable.forkert0);

        doGuessButton = root.findViewById(R.id.tryGuessButton);
        hiddenWordProgressTextView = root.findViewById(R.id.hiddenWordTextView);
        guessEditText = root.findViewById(R.id.guessEditText);
        usedLettersTextView = root.findViewById(R.id.usedLettersTextView);
        amountWrongGuessTextView = root.findViewById(R.id.numberOfGuessesTextView);

        amountWrongGuessTextView.setText(0 + " " + amountWrongStr);
        usedLettersTextView.setText(noGuessYet);
        hiddenWordProgressTextView.setText(gp.getWordProgress());

        getName();

        doGuessButton.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        String guess = guessEditText.getText().toString();
        if (gp.guessLetter(guess)) {
            hiddenWordProgressTextView.setText(gp.getWordProgress());
        } else {
            amountWrongGuessTextView.setText(gp.getAmountWrongGuess() + " " + amountWrongStr);
        }
        usedLettersTextView.setText(gp.getUsedLetters());
        guessEditText.setText("");
        isGameOver();
    }

    public void isGameOver() {
        if (isWon) {
            saveHighscore();
            setFragment(wf);
        } else if (isLost) {
            setFragment(lf);
        }
    }

    public void setFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.MainActivityFL, fragment)
                .commit();
    }

    public void saveHighscore() {
        loadHighscore();
        hm = new HighscoreModel(gp.getPlayerName(), gp.getCorrectWord(), gp.getAmountWrongGuess());
        highscores.add(hm);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHAREDPREFKEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(highscores);
        editor.putString(HIGHSCOREKEY, json);
        editor.apply();
    }

    private void loadHighscore() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHAREDPREFKEY, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(HIGHSCOREKEY, null);
        Type type = new TypeToken<ArrayList<HighscoreModel>>() {
        }.getType();
        highscores = gson.fromJson(json, type);

        if (highscores == null) {
            highscores = new ArrayList<>();
        }
    }

    public void getName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Indtast navn");
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Gem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gp.setPlayerName(input.getText().toString());
            }
        });
        builder.setNegativeButton("Nej tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gp.setPlayerName("Unnamed");
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    public void update(boolean isWon, boolean isLost, String hiddenWordProgress, int amountWrongGuess, String usedLetters, String hiddenWord, String playerName) {
        this.hiddenWordProgress = hiddenWordProgress;
        this.amountWrongGuess = amountWrongGuess;
        this.usedLetters = usedLetters;
        this.isLost = isLost;
        this.isWon = isWon;
    }
}