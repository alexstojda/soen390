package org.wikipedia.incognito;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.wikipedia.R;

/**
 * The IncognitoActiveFragment class contains the logic behind the Incognito active display.
 */
public class IncognitoActiveFragment extends Fragment {

    /**
     * Simply inflates the layout for the incognito active bar.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_incognito_active, container, false);
    }

    /**
     * Checks whether incognito mode is active and sets the visibility of the fragment accordingly.
     */
    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean isIncognitoEnabled = prefs.getBoolean(getString(R.string.preference_key_incognito_mode), false);
        int visibility = isIncognitoEnabled ? View.VISIBLE : View.GONE;

        getView().setVisibility(visibility);
    }
}
