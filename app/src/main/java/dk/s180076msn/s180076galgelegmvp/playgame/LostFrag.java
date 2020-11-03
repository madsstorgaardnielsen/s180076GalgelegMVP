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

public class LostFrag extends Fragment implements Observer, View.OnClickListener {
    private Fragment f;
    private Button gotoMainMenu;
    private TextView loserNameTextView;
    private TextView hiddenWordTextView;
    private ImageView lostGameImg;
    private String playerName;
    private String hiddenWord;
    private Subject sgp;

    public LostFrag(Subject sgp) {
        this.sgp = sgp;
        sgp.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lost, container, false);
        loserNameTextView = root.findViewById(R.id.loserNameTextView);
        hiddenWordTextView = root.findViewById(R.id.hiddenWordTextView);
        gotoMainMenu = root.findViewById(R.id.gotoMainMenu);
        lostGameImg = root.findViewById(R.id.loserImageView);
        lostGameImg.setImageResource(R.drawable.lost);

        loserNameTextView.setText("Desværre " + playerName + ", du tabte!");
        hiddenWordTextView.setText("Ordet var: " + hiddenWord);

        gotoMainMenu.setOnClickListener(this);
        return root;
    }

    @Override
    public void update(boolean isWon, boolean isLost, String hiddenWordProgress, int amountWrongGuess, String usedLetters, String hiddenWord, String playerName) {
        this.hiddenWord = hiddenWord;
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