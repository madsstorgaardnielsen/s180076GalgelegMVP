package dk.s180076msn.s180076galgelegmvp.highscore;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dk.s180076msn.s180076galgelegmvp.R;

public class HighscoreFrag extends Fragment {
    private ListView listView;
    private ArrayList<HighscoreModel> highscores;
    private final String SHAREDPREFKEY = "shared_preferences";
    private final String HIGHSCOREKEY = "highscore";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_highscore, container, false);

        loadData();
        sortHighscores();

        listView = root.findViewById(R.id.ListView);
        listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, highscores));

        return root;
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHAREDPREFKEY, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(HIGHSCOREKEY, null);
        Type type = new TypeToken<ArrayList<HighscoreModel>>() {
        }.getType();
        highscores = gson.fromJson(json, type);

        if (highscores == null) {
            highscores = new ArrayList<>();
        }
    }

    private void sortHighscores() {
        loadData();
        Collections.sort(highscores, new Comparator<HighscoreModel>() {
            @Override
            public int compare(HighscoreModel hs1, HighscoreModel hs2) {
                return Integer.compare(hs1.getAmountWrongGuess(), hs2.getAmountWrongGuess());
            }
        });
    }
}