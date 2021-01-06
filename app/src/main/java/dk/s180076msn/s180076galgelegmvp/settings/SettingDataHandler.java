package dk.s180076msn.s180076galgelegmvp.settings;

import android.os.Handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dk.s180076msn.s180076galgelegmvp.SheetDataLoader.DataLoader;

public class SettingDataHandler {
    ArrayList<String> words;

    public String[] loadData() throws IOException {
        words = new ArrayList<>();
        words = DataLoader.get().getAllWords();
        return parseToStringArray(words);
    }

    private String[] parseToStringArray(ArrayList<String> arrayList) {
        String[] str = new String[arrayList.size()];
        for (int j = 0; j < arrayList.size(); j++) {
            str[j] = arrayList.get(j);
        }
        return str;
    }
}
