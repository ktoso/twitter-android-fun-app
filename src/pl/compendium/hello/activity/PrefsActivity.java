package pl.compendium.hello.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import pl.compendium.hello.R;

public class PrefsActivity extends PreferenceActivity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.prefs_for_twitter);
    }
}