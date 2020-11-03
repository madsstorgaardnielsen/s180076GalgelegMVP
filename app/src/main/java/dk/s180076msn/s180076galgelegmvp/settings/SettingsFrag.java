package dk.s180076msn.s180076galgelegmvp.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import dk.s180076msn.s180076galgelegmvp.R;
import dk.s180076msn.s180076galgelegmvp.mainmenu.MainMenuFrag;
import dk.s180076msn.s180076galgelegmvp.playgame.Observer;
import dk.s180076msn.s180076galgelegmvp.playgame.Subject;

public class SettingsFrag extends Fragment implements View.OnClickListener {
    Button easyButton, mediumButton,hardButton, goToMenu;
    String DIFFICULTY_LEVEL_EASY = "easy";
    String DIFFICULTY_LEVEL_MEDIUM = "medium";
    String DIFFICULTY_LEVEL_HARD = "hard";
    String difficultyLevel = "";
    SettingsModel settings;
    ArrayList<SettingsModel> difficultySettings;

    String SHAREDPREFKEY = "shared_preferences";
    String SETTINGSKEY = "settingskey";

    Fragment f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        //TODO knapperne skal sætte en sværhedsgrad, denne gemmes vha pref manager, og indlæses i gamepresenter når init game kaldes.

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
        } else if (v == mediumButton) {
            difficultyLevel = DIFFICULTY_LEVEL_MEDIUM;
        } else if (v == hardButton) {
            difficultyLevel = DIFFICULTY_LEVEL_HARD;
        } else if (v == goToMenu) {
            f = new MainMenuFrag();
            setFragment(f);
        }
        saveSettings();
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

/*    private void loadSettings() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHAREDPREFKEY, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(SETTINGSKEY, null);
        Type type = new TypeToken<ArrayList<SettingsModel>>() {}.getType();
        difficultySettings = gson.fromJson(json, type);

        if (difficultySettings == null) {
            difficultySettings = new ArrayList<>();
        }
    }*/
}