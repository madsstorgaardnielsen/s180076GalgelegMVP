package dk.s180076msn.s180076galgelegmvp.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dk.s180076msn.s180076galgelegmvp.R;
import dk.s180076msn.s180076galgelegmvp.MainMenuFrag;

public class SettingsFrag extends Fragment implements View.OnClickListener {
    Button easyButton, mediumButton, hardButton, useCustomWords, gotoMenu;
    ImageView imageView;
    private final String DIFFICULTY_LEVEL_EASY = "easy";
    private final String DIFFICULTY_LEVEL_MEDIUM = "medium";
    private final String DIFFICULTY_LEVEL_HARD = "hard";
    String difficultyLevel = "";
    boolean isCustomList;
    String[] customWordListOG = null;
    SettingDataHandler getData;
    Executor bgThread;
    Handler uiThread;
    Button mOrder;
    TextView mItemSelected;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    ArrayList<String> customWordList;
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
        gotoMenu = root.findViewById(R.id.gotoMainMenu);

        mItemSelected = (TextView) root.findViewById(R.id.tvChosenWords);
        mOrder = root.findViewById(R.id.gotoCustomWordList);

        easyButton.setOnClickListener(this);
        mediumButton.setOnClickListener(this);
        hardButton.setOnClickListener(this);
        useCustomWords.setOnClickListener(this);
        mOrder.setOnClickListener(this);
        gotoMenu.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        f = new MainMenuFrag();
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
        } else if (v == gotoMenu) {
            setFragment(f);
        } else if (v == mOrder) {
            bgThread.execute(() -> {
                try {
                    customWordListOG = getData.loadData();
                    uiThread.post(() -> {
                        listItems = customWordListOG;
                        checkedItems = new boolean[listItems.length];
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                        mBuilder.setTitle("Vælg ord");
                        mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                                if (isChecked) {
                                    mUserItems.add(position);
                                } else {
                                    mUserItems.remove((Integer.valueOf(position)));
                                }
                            }
                        });

                        mBuilder.setCancelable(false);
                        mBuilder.setPositiveButton("Gem", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                customWordList = new ArrayList<>();
                                String item = "";
                                for (int i = 0; i < mUserItems.size(); i++) {
                                    item = item + listItems[mUserItems.get(i)];
                                    customWordList.add(listItems[mUserItems.get(i)]);
                                    if (i != mUserItems.size() - 1) {
                                        item = item + ", ";
                                    }
                                }
                                System.out.println(customWordList);
                                makeCustomWordlistToast();
                                saveCustomWordlist();
                            }
                        });

                        mBuilder.setNegativeButton("Fortryd", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        AlertDialog mDialog = mBuilder.create();
                        mDialog.show();


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

    public void makeCustomWordlistToast() {
        Toast toast = Toast.makeText(getActivity(), "Ordliste gemt", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, -500);
        toast.show();
    }

    String CUSTOM_WORD_LIST_KEY = "wordlistkey";

    public void saveCustomWordlist() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(CUSTOM_WORD_LIST_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(customWordList);
        editor.putString(CUSTOM_WORD_LIST_KEY, json);
        editor.apply();
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