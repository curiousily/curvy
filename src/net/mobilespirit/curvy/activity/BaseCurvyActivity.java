package net.mobilespirit.curvy.activity;

import android.app.Activity;
import net.mobilespirit.curvy.application.CurvyApplication;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 09-03-2011
 * Time: 18-41
 * Package: net.mobilespirit.curvy.activity
 */
public class BaseCurvyActivity extends Activity {

    public CurvyApplication getCurvyApplication() {
        return (CurvyApplication) getApplication();
    }
}
