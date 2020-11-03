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
}