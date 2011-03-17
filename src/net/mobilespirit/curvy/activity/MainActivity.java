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
public class MainActivity extends BaseCurvyActivity
{

    private static final int PICK_POINTS = 0;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICK_POINTS:
                if(resultCode == Activity.RESULT_OK) {
                    startCasteljauTreeActivity();
                }
        }
        super.onActivityResult(requestCode, resultCode, data);    
    }

    public void casteljauAlgorithmHandler(View view) {
        if(getCurvyApplication().hasPoints()) {
            startCasteljauTreeActivity();
        } else {
            Intent intent = new Intent(this, PointPickerActivity.class);
            startActivityForResult(intent, PICK_POINTS);
        }
    }

    private void startCasteljauTreeActivity() {
        Intent intent = new Intent(this, CasteljauTreeActivity.class);
        startActivity(intent);
    }

    public void editPointsHandler(View view) {
        Intent intent = new Intent(this, PointPickerActivity.class);
        startActivity(intent);
    }

//    public void increaseDegreeHandler(View view) {
//
//    }

//    public void exitHandler(View view) {
//
//    }
}