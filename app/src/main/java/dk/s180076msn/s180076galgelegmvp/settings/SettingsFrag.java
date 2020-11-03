package dk.s180076msn.s180076galgelegmvp.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import dk.s180076msn.s180076galgelegmvp.R;
import dk.s180076msn.s180076galgelegmvp.MainMenuFrag;

public class SettingsFrag extends Fragment implements View.OnClickListener {
    Button easyButton, mediumButton, hardButton, goToMenu;
    ImageView imageView;
    private final String DIFFICULTY_LEVEL_EASY = "easy";
    private final String DIFFICULTY_LEVEL_MEDIUM = "medium";
    private final String DIFFICULTY_LEVEL_HARD = "hard";
    String difficultyLevel = "";
    SettingsModel settings;
    ArrayList<SettingsModel> difficultySettings;

    String SHAREDPREFKEY = "shared_preferences";
    String SETTINGSKEY = "settingskey";

    Fragment f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        imageView = root.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.won);

        easyButton = root.findViewById(R.id.easyButton);
        mediumButton = root.findViewById(R.id.mediumButton);
        hardButton = root.findViewById(R.id.hardButton);
        goToMenu = root.findViewById(R.id.gotoMainMenu);

        easyButton.setOnClickListener(this);
        mediumButton.setOnClickListener(this);
        hardButton.setOnClickListener(this);
        goToMenu.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        if (v == easyButton) {
            difficultyLevel = DIFFICULTY_LEVEL_EASY;
            makeToast(difficultyLevel);
        } else if (v == mediumButton) {
            difficultyLevel = DIFFICULTY_LEVEL_MEDIUM;
            makeToast(difficultyLevel);
        } else if (v == hardButton) {
            difficultyLevel = DIFFICULTY_LEVEL_HARD;
            makeToast(difficultyLevel);
        } else if (v == goToMenu) {
            f = new MainMenuFrag();
            setFragment(f);
        }
        saveSettings();
    }

    public void makeToast(String difficultyLevel) {
        Toast toast = Toast.makeText(getActivity(), "Sværhedsgraden er nu sat til: " + difficultyLevel, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, -500);
        toast.show();
    }

    public void saveSettings() {
        difficultySettings = new ArrayList<>();
        difficultySettings.clear();
        settings = new SettingsModel(difficultyLevel);
        difficultySettings.add(settings);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHAREDPREFKEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(difficultySettings);
        editor.putString(SETTINGSKEY, json);
        editor.apply();
    }

    public void setFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.MainActivityFL, fragment)
                .addToBackStack(null)
                .commit();
    }
}