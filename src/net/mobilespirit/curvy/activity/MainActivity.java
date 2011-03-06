package net.mobilespirit.curvy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import net.mobilespirit.curvy.R;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 06-03-2011
 * Time: 23-15
 * Package: net.mobilespirit.curvy.activity
 */
public class MainActivity extends Activity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
    }

    public void casteljauAlgorithmHandler(View view) {
        Intent intent = new Intent(this, PointPickerActivity.class);
        startActivity(intent);
    }

    public void increaseDegreeHandler(View view) {
        
    }
}