package dk.s180076msn.s180076galgelegmvp.playgame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dk.s180076msn.s180076galgelegmvp.R;


public class GameFrag extends Fragment implements Observer {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_game, container, false);

        return root;
    }


    @Override
    public void update(boolean isWon, boolean isLost, String hiddenWordProgress, int amountWrongGuess, ArrayList<String> usedLetters) {
        if (isWon) {
            //TODO set win frag
            //TODO Bundle evt data med fra Gamefrag
        } else if (isLost) {
            //TODO set lost frag
        }
    }
}