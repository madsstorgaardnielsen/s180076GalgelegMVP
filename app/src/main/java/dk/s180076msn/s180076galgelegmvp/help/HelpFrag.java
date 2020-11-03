package dk.s180076msn.s180076galgelegmvp.help;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dk.s180076msn.s180076galgelegmvp.MainMenuFrag;
import dk.s180076msn.s180076galgelegmvp.R;


public class HelpFrag extends Fragment implements View.OnClickListener {
    Button goToMenu;
    Fragment f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_help, container, false);
        goToMenu = root.findViewById(R.id.gotoMainMenu);

        goToMenu.setOnClickListener(this);
        return root;
    }

    public void setFragment(Fragment f) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.MainActivityFL, f)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(View v) {
        f = new MainMenuFrag();
        setFragment(f);
    }
}