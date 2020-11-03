package dk.s180076msn.s180076galgelegmvp.playgame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import dk.s180076msn.s180076galgelegmvp.R;
import dk.s180076msn.s180076galgelegmvp.MainMenuFrag;


public class WonFrag extends Fragment implements Observer, View.OnClickListener {
    Fragment f;
    Button gotoMainMenu;
    TextView winnerNameTextView;
    TextView amountWrongGuessTextView;
    ImageView wonGameImg;
    Subject sgp;
    int amountWrongGuess;
    String playerName;

    public WonFrag(Subject sgp) {
        this.sgp = sgp;
        sgp.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_won, container, false);
        winnerNameTextView = root.findViewById(R.id.winnerNameTextView);
        amountWrongGuessTextView = root.findViewById(R.id.winnerStatTextView);
        gotoMainMenu = root.findViewById(R.id.gotoMainMenu);

        wonGameImg = root.findViewById(R.id.winnerImageView);
        wonGameImg.setImageResource(R.drawable.won);

        winnerNameTextView.setText("Tillykke " + playerName + ", du vandt!");
        if (amountWrongGuess >= 1) {
            amountWrongGuessTextView.setText("med " + amountWrongGuess + " forkert svar!");
        } else
            amountWrongGuessTextView.setText("med " + amountWrongGuess + " forkerte svar!");

        gotoMainMenu.setOnClickListener(this);
        return root;
    }

    @Override
    public void update(boolean isWon, boolean isLost, String hiddenWordProgress, int amountWrongGuess, String usedLetters, String hiddenWord, String playerName) {
        this.amountWrongGuess = amountWrongGuess;
        this.playerName = playerName;
    }

    @Override
    public void onClick(View v) {
        f = new MainMenuFrag();
        setFragment(f);
    }

    public void setFragment(Fragment f) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.MainActivityFL, f)
                .addToBackStack(null)
                .commit();
    }
}