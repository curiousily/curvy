package net.mobilespirit.curvy.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.*;
import net.mobilespirit.curvy.R;
import net.mobilespirit.curvy.application.CurvyApplication;
import net.mobilespirit.curvy.domain.point.Point2D;
import net.mobilespirit.curvy.view.PointPickerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 28-02-2011
 * Time: 14:30
 * Package: net.mobilespirit.curvy.activity
 */
public class PointPickerActivity extends BaseCurvyActivity {

    private PointPickerView pointPickerView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        pointPickerView = new PointPickerView(this, getPointCount());
        setContentView(pointPickerView);
        pointPickerView.setOnAddPointClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pointPickerView.addPoint();
            }
        });

        pointPickerView.setOnDoneClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getCurvyApplication().setPointList(getPointListFromView());
                if(getCurvyApplication().hasPoints()) {
                    setResult(Activity.RESULT_OK);
                } else {
                    setResult(Activity.RESULT_CANCELED);
                }
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        getCurvyApplication().setPointList(getPointListFromView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getCurvyApplication().hasPoints()) {
            pointPickerView.setPointList(getCurvyApplication().getPointList());
        }
    }

    private List<Point2D> getPointListFromView() {
        List<EditText> coordinateTexts = pointPickerView.getCoordinateTexts();
        List<Point2D> pointList = new ArrayList<Point2D>(coordinateTexts.size() / 2);
        for(int i = 0; i < coordinateTexts.size(); i += 2) {
            String xString = coordinateTexts.get(i).getText().toString();
            String yString = coordinateTexts.get(i + 1).getText().toString();
            if(xString.isEmpty() || yString.isEmpty()) {
                continue;
            }
            try {
                double xCoordinate = Double.valueOf(xString);
                double yCoordinate = Double.valueOf(yString);
                Point2D point = new Point2D(xCoordinate, yCoordinate);
                pointList.add(point);
            } catch (NumberFormatException e) {
                // don't add the point
            }
        }
        return pointList;
    }
}