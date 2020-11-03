package dk.s180076msn.s180076galgelegmvp.mainmenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dk.s180076msn.s180076galgelegmvp.R;

public class MainMenuFrag extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_menu, container, false);

        return root;
    }
}