package zombie.deliziusz.audiolibros.chido.fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import zombie.deliziusz.audiolibros.R;

public class PreferenciasFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
    }
}
