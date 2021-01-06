package dk.s180076msn.s180076galgelegmvp.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dk.s180076msn.s180076galgelegmvp.R;
import dk.s180076msn.s180076galgelegmvp.MainMenuFrag;

public class SettingsFrag extends Fragment implements View.OnClickListener {
    Button easyButton, mediumButton, hardButton, useCustomWords, customWordListeBtn;
    ImageView imageView;
    private final String DIFFICULTY_LEVEL_EASY = "easy";
    private final String DIFFICULTY_LEVEL_MEDIUM = "medium";
    private final String DIFFICULTY_LEVEL_HARD = "hard";
    String difficultyLevel = "";
    boolean isCustomList;
    CustomWordListFrag f2;
    String[] customWordList = null;
    SettingDataHandler getData;
    Executor bgThread;
    Handler uiThread;

    String SHAREDPREFKEY = "shared_preferences";
    String DIFFICULTYSETTINGSKEY = "difficultysettingskey";
    String CUSTOM_WORDLIST_SETTING_KEY = "iscustomwordlist";

    Fragment f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        imageView = root.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.won);

        easyButton = root.findViewById(R.id.easyButton);
        mediumButton = root.findViewById(R.id.mediumButton);
        hardButton = root.findViewById(R.id.hardButton);
        useCustomWords = root.findViewById(R.id.useCustomWordsBtn);
        customWordListeBtn = root.findViewById(R.id.gotoCustomWordList);

        easyButton.setOnClickListener(this);
        mediumButton.setOnClickListener(this);
        hardButton.setOnClickListener(this);
        useCustomWords.setOnClickListener(this);
        customWordListeBtn.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        f = new MainMenuFrag();
        f2 = new CustomWordListFrag();
        getData = new SettingDataHandler();
        uiThread = new Handler();
        bgThread = Executors.newSingleThreadExecutor();
        if (v == easyButton) {
            difficultyLevel = DIFFICULTY_LEVEL_EASY;
            isCustomList = false;
            saveIsCustomWordList(isCustomList);
            saveDifficultySettings();
            makeToast("Nem");
            setFragment(f);
        } else if (v == mediumButton) {
            difficultyLevel = DIFFICULTY_LEVEL_MEDIUM;
            isCustomList = false;
            saveIsCustomWordList(isCustomList);
            saveDifficultySettings();
            makeToast("Mellem");
            setFragment(f);
        } else if (v == hardButton) {
            difficultyLevel = DIFFICULTY_LEVEL_HARD;
            isCustomList = false;
            saveIsCustomWordList(isCustomList);
            saveDifficultySettings();
            makeToast("Svær");
            setFragment(f);

        } else if (v == useCustomWords) {
            isCustomList = true;
            saveIsCustomWordList(isCustomList);
            makeToast("Brugerdefineret");
            setFragment(f);
        } else if (v == customWordListeBtn) {
            bgThread.execute(() -> {
                try {
                    customWordList = getData.loadData();
                    uiThread.post(() -> {
                        passData(f2, customWordList);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    public void makeToast(String difficultyLevel) {
        Toast toast = Toast.makeText(getActivity(), "Sværhedsgraden er nu sat til: " + difficultyLevel, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, -500);
        toast.show();
    }

    private void passData(CustomWordListFrag f2, String[] words) {
        Bundle args = new Bundle();
        args.putStringArray("stringarraykey", words);
        f2.setArguments(args);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.MainActivityFL, f2)
                .addToBackStack(null)
                .commit();
    }

    String CUSTOM_WORDLIST_KEY = "customwordlistkey";

    public void saveIsCustomWordList(boolean isCustomList) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(CUSTOM_WORDLIST_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(CUSTOM_WORDLIST_SETTING_KEY, isCustomList);
        editor.apply();
    }

    String DIFFICULTY_SETTING_KEY = "difficultysettingskey";

    public void saveDifficultySettings() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(DIFFICULTY_SETTING_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DIFFICULTY_SETTING_KEY, difficultyLevel);
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