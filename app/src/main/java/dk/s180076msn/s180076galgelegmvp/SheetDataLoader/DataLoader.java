package dk.s180076msn.s180076galgelegmvp.SheetDataLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class DataLoader {
    final String SHEET_ID = "1Ye6rFRlVKmxII9rKiQTt2QHWiF1x3P8pQ-RoYPIBy2A";
    private final String URL = "https://docs.google.com/spreadsheets/d/" + SHEET_ID + "/export?format=csv&id=" + SHEET_ID;
    private static DataLoader instance;
    ArrayList<String> words = new ArrayList<>();
    ;

    public static DataLoader get() {
        if (instance == null) {
            instance = new DataLoader();
        }
        return instance;
    }

    public ArrayList<String> getWords(String difficulty) throws IOException {
        parseData(difficulty, false);
        return words;
    }

    public ArrayList<String> getAllWords() throws IOException {
        parseData("easy", true);
        return words;
    }

    private void parseData(String difficulty, boolean isFullList) throws IOException {
        String data = readData();
        words.clear();
        for (String line : data.split("\n")) {
            String[] fields = line.split(",", -1);
            String diff = fields[0].trim();
            String word = fields[1].trim().toLowerCase();
            if (diff.isEmpty() || word.isEmpty()) continue;
            if (!difficulty.contains(diff) && !isFullList) continue;
            words.add(word);
        }
    }

    private String readData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(URL).openStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        line = br.readLine();
        while (line != null) {
            sb.append(line).append("\n");
            line = br.readLine();
        }
        br.close();
        return sb.toString();
    }
}
