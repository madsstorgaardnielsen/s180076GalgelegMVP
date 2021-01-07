package dk.s180076msn.s180076galgelegmvp.settings;

import java.util.ArrayList;

public class SettingsModel {
    String difficultyLevel = "";
    ArrayList<String> customWordArr;
    boolean isCustomWordlist;

    public SettingsModel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public SettingsModel(ArrayList<String> customWordList) {
        this.customWordArr = customWordList;
    }

    public SettingsModel(boolean isCustomWordlist) {
        this.isCustomWordlist = isCustomWordlist;
    }

    public SettingsModel() {
    }

    public void setIsCustomWordList(boolean isCustomWordList) {
        this.isCustomWordlist = isCustomWordList;
    }

    public void setCustomWordList(ArrayList<String> customWordList) {
        this.customWordArr = customWordList;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
