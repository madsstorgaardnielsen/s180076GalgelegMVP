package dk.s180076msn.s180076galgelegmvp.SheetDataLoader;

import java.io.IOException;

public class DataLoadTest {
    public static void main(String[] args) throws IOException {

        System.out.println(DataLoader.get().getWords("easy"));
        System.out.println(DataLoader.get().getWords("medium"));
        System.out.println(DataLoader.get().getWords("hard"));
        System.out.println(DataLoader.get().getAllWords("easy"));

    }
}
