package dk.s180076msn.s180076galgelegmvp.playgame;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import dk.s180076msn.s180076galgelegmvp.R;
import dk.s180076msn.s180076galgelegmvp.MainMenuFrag;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class WonFrag extends Fragment implements Observer, View.OnClickListener {
    Fragment f;
    Button gotoMainMenu;
    TextView winnerNameTextView;
    TextView amountWrongGuessTextView;
    ImageView wonGameImg;
    Subject sgp;
    int amountWrongGuess;
    String playerName;
    Animation anim;

    public WonFrag(Subject sgp) {
        this.sgp = sgp;
        sgp.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_won, container, false);
        final KonfettiView konfettiView = root.findViewById(R.id.viewKonfetti);
        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.winning);
        winnerNameTextView = root.findViewById(R.id.winnerNameTextView);
        amountWrongGuessTextView = root.findViewById(R.id.winnerStatTextView);
        gotoMainMenu = root.findViewById(R.id.gotoMainMenu);


        wonGameImg = root.findViewById(R.id.winnerImageView);
        wonGameImg.setImageResource(R.drawable.won);
        wonGameImg.startAnimation(anim);

        konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.BLUE, Color.CYAN, Color.RED)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, konfettiView.getWidth() + 1250f, -50f, -50f)
                .streamFor(500, 5000L);


        winnerNameTextView.setText("Tillykke " + playerName + ", du vandt!");
        winnerNameTextView.startAnimation(anim);

        if (amountWrongGuess >= 1) {
            amountWrongGuessTextView.setText("med " + amountWrongGuess + " forkert gæt!");
            amountWrongGuessTextView.startAnimation(anim);
        } else
            amountWrongGuessTextView.setText("med " + amountWrongGuess + " forkerte gæt!");
        amountWrongGuessTextView.startAnimation(anim);

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