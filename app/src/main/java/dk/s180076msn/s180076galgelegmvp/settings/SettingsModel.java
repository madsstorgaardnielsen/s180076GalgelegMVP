package dk.s180076msn.s180076galgelegmvp.settings;

public class SettingsModel {
    String difficultyLevel = "";

    public SettingsModel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public SettingsModel() {
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
