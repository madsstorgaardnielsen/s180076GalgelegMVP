package dk.s180076msn.s180076galgelegmvp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import dk.s180076msn.s180076galgelegmvp.R;
import dk.s180076msn.s180076galgelegmvp.help.HelpFrag;
import dk.s180076msn.s180076galgelegmvp.highscore.HighscoreFrag;
import dk.s180076msn.s180076galgelegmvp.playgame.GameFrag;
import dk.s180076msn.s180076galgelegmvp.settings.SettingsFrag;

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
        settingsButton.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        if (v == playGameButton) {
            f = new GameFrag();
            setFragment(f);
        } else if (v == highscoreButton) {
            f = new HighscoreFrag();
            setFragment(f);
        } else if (v == settingsButton) {
            f = new SettingsFrag();
            setFragment(f);
        } else if (v == helpButton) {
            f = new HelpFrag();
            setFragment(f);
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