package dk.s180076msn.s180076galgelegmvp.highscore;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dk.s180076msn.s180076galgelegmvp.R;

public class HighscoreFrag extends Fragment implements AbsListView.OnScrollListener {
    private ListView listView1;
    private ListView listView2;
    private ListView listView3;
    private ArrayList<HighscoreModel> highscores;
    private final String SHAREDPREFKEY = "shared_preferences";
    private final String HIGHSCOREKEY = "highscore";
    ArrayList<String> names;
    ArrayList<String> correctWord;
    ArrayList<String> wrongGuesses;
    int posY;
    boolean isLeftEnabled = true;
    boolean isRightEnabled = true;
    boolean isMidEnabled = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_highscore, container, false);

        loadData();
        sortHighscores();
        parseData();

        listView1 = root.findViewById(R.id.ListView1);
        listView1.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, names));

        listView2 = root.findViewById(R.id.ListView2);
        listView2.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, correctWord));

        listView3 = root.findViewById(R.id.ListView3);
        listView3.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, wrongGuesses));

        listView1.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
        listView2.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
        listView3.setOverScrollMode(ListView.OVER_SCROLL_NEVER);

        listView1.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    isRightEnabled = false;
                    isMidEnabled = false;
                } else if (scrollState == SCROLL_STATE_IDLE) {
                    isRightEnabled = true;
                    isMidEnabled = true;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View c = view.getChildAt(0);
                if (c != null && isLeftEnabled) {
                    listView2.setSelectionFromTop(firstVisibleItem, c.getTop());
                    listView3.setSelectionFromTop(firstVisibleItem, c.getTop());
                }
            }
        });

        listView2.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    isLeftEnabled = false;
                    isRightEnabled = false;
                } else if (scrollState == SCROLL_STATE_IDLE) {
                    isLeftEnabled = true;
                    isRightEnabled = true;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View c = view.getChildAt(0);
                if (c != null && isMidEnabled) {
                    listView1.setSelectionFromTop(firstVisibleItem, c.getTop());
                    listView3.setSelectionFromTop(firstVisibleItem, c.getTop());
                }
            }
        });

        listView3.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    isLeftEnabled = false;
                    isMidEnabled = false;
                } else if (scrollState == SCROLL_STATE_IDLE) {
                    isLeftEnabled = true;
                    isMidEnabled = true;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View c = view.getChildAt(0);
                if (c != null && isRightEnabled) {
                    listView2.setSelectionFromTop(firstVisibleItem, c.getTop());
                    listView1.setSelectionFromTop(firstVisibleItem, c.getTop());
                }
            }
        });

        return root;
    }

    private void parseData() {
        names = new ArrayList<>();
        correctWord = new ArrayList<>();
        wrongGuesses = new ArrayList<>();
        names.add("Navn");
        correctWord.add("Ordet");
        wrongGuesses.add("Forkerte gæt");

        for (int i = 0; i < highscores.size(); i++) {
            names.add(highscores.get(i).getPlayerName());
        }

        for (int i = 0; i < highscores.size(); i++) {
            correctWord.add(highscores.get(i).getCorrectWord());
        }

        for (int i = 0; i < highscores.size(); i++) {
            wrongGuesses.add(highscores.get(i).getAmountWrongGuess()+"");
        }
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

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}