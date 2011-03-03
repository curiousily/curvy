package net.mobilespirit.curvy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import net.mobilespirit.curvy.activity.CasteljauTreeActivity;
import net.mobilespirit.curvy.activity.PointPickerActivity;

public class MainActivity extends Activity
{
    public static final int CHOOSE_POINTS = 1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
    }

    public void casteljauHandler(View view) {
        Intent intent = new Intent(this, PointPickerActivity.class);
        intent.putExtra("pointCount", 4);
        startActivityForResult(intent, CHOOSE_POINTS);
    }

    public void increaseDegreeHandler(View view) {
        Intent intent = new Intent(this, CasteljauTreeActivity.class);
        startActivity(intent);
    }

    public void newPointHandler(View view) {
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_POINTS:
                if(resultCode == RESULT_OK) {

                } else {
                    
                }
            break;
        }
    }
}