package net.mobilespirit.curvy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import net.mobilespirit.curvy.R;

/**
 * Project info
 * User: Vini
 * Date: 28-02-2011
 * Time: 14:30
 * Package: net.mobilespirit.curvy.activity
 */
public class PointPickerActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.point_input);
        int pointCount = getIntent().getIntExtra("pointCount", 3);
//        Intent data = new Intent();
//
//        data.putIntegerArrayListExtra("points");
//        setResult(RESULT_OK, );
    }
}