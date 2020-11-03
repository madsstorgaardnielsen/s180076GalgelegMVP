package dk.s180076msn.s180076galgelegmvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.lang.reflect.Type;
import java.util.ArrayList;

import dk.s180076msn.s180076galgelegmvp.mainmenu.MainMenuFrag;

public class MainActivity extends AppCompatActivity {
    Fragment f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        f = new MainMenuFrag();
        setFragment(f);
    }

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.MainActivityFL, fragment)
                .commit();
    }

/*    public void saveHighscore() {
        loadHighscore();
        hsModel = new HighscoreModel(correctWord, playerName, gameController.getAmountWrongGuesses() + "");
        highscoreList.add(hsModel);
        SharedPreferences sharedPreferences = getSharedPreferences(SHAREDPREFKEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(highscoreList);
        editor.putString(HIGHSCOREKEY, json);
        editor.apply();
    }

    private void loadHighscore() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHAREDPREFKEY, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(HIGHSCOREKEY, null);
        Type type = new TypeToken<ArrayList<HighscoreModel>>() {
        }.getType();
        highscoreList = gson.fromJson(json, type);

        if (highscoreList == null) {
            highscoreList = new ArrayList<>();
        }
    }*/
}