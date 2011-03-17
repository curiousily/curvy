package net.mobilespirit.curvy.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import net.mobilespirit.curvy.R;
import net.mobilespirit.curvy.application.CurvyApplication;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 09-03-2011
 * Time: 18-41
 * Package: net.mobilespirit.curvy.activity
 */
public class BaseCurvyActivity extends Activity {

    private static final String POINT_COUNT_KEY = "point_count";
    private static final String COEFFICIENT_KEY = "coefficient";

    protected static final int DEFAULT_POINT_COUNT = 3;
    protected static final float DEFAULT_CURVE_COEFFICIENT = 0.4F;

    public CurvyApplication getCurvyApplication() {
        return (CurvyApplication) getApplication();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.application_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.edit_points:
                intent = new Intent(this, PointPickerActivity.class);
                startActivity(intent);
                return true;

            case R.id.preferences:
                intent = new Intent(this, EditPreferencesActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected int getPointCount() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        try {
            return Integer.valueOf(preferences.getString(POINT_COUNT_KEY, "dummy"));
        } catch (NumberFormatException e) {
            return DEFAULT_POINT_COUNT;
        }
    }

    protected float getCurveCoefficient() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        try {
            return Float.valueOf(preferences.getString(COEFFICIENT_KEY, "dummy"));
        } catch (NumberFormatException e) {
            return DEFAULT_CURVE_COEFFICIENT;
        }
    }
}
