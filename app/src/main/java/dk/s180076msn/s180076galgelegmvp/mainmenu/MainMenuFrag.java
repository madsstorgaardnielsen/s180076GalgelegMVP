package dk.s180076msn.s180076galgelegmvp.mainmenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import dk.s180076msn.s180076galgelegmvp.R;
import dk.s180076msn.s180076galgelegmvp.playgame.GameFrag;

public class MainMenuFrag extends Fragment implements View.OnClickListener {
    Button playGameButton, highscoreButton, helpButton, settingsButton;
    ImageView imageView;
    Fragment f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_menu, container, false);

        imageView = root.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.logo);

        playGameButton = root.findViewById(R.id.playGameButton);
        highscoreButton = root.findViewById(R.id.highscoreButton);
        helpButton = root.findViewById(R.id.helpButton);
        settingsButton = root.findViewById(R.id.settingsButton);

        playGameButton.setOnClickListener(this);
        highscoreButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        if (v == playGameButton) {
            f = new GameFrag();
            setFragment(f);
        } else if (v == highscoreButton) {
            //TODO highscorefrag

        } else if (v == settingsButton) {
            //TODO settingsfrag
        } else if (v == helpButton) {
            //TODO helpfrag
        }
    }

    public void setFragment(Fragment f) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.MainActivityFL, f)
                .addToBackStack(null)
                .commit();
    }
}