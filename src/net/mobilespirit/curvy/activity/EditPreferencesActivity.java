package net.mobilespirit.curvy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import net.mobilespirit.curvy.R;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 17-03-2011
 * Time: 18-28
 * Package: net.mobilespirit.curvy.activity
 */
public class EditPreferencesActivity extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}