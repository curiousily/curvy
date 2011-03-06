package net.mobilespirit.curvy.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import net.mobilespirit.curvy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 28-02-2011
 * Time: 14:30
 * Package: net.mobilespirit.curvy.activity
 */
public class PointPickerActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int pointCount = getIntent().getIntExtra("pointCount", 3);

        LinearLayout root = new LinearLayout(this);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams rootParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        root.setLayoutParams(rootParams);

        TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        TableLayout table = new TableLayout(this);
        table.setGravity(Gravity.CENTER_HORIZONTAL);
        table.setLayoutParams(params);
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

        final List<EditText> coordinateInputs = new ArrayList<EditText>();
        for(int i = 0; i < pointCount; i++) {
            
            TableRow row = new TableRow(this);
            row.setPadding(20, 0, 20, 0);
            row.setLayoutParams(rowParams);

            TextView pointLabel = new TextView(this);
            pointLabel.setText(R.string.point_label);
            pointLabel.setPadding(3, 3, 10, 3);

            EditText xPoint = new EditText(this);
            xPoint.setWidth(100);
            xPoint.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            xPoint.setHint(R.string.x_coordinate_hint);

            EditText yPoint = new EditText(this);
            yPoint.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            yPoint.setWidth(100);
            yPoint.setHint(R.string.y_coordinate_hint);

            row.addView(pointLabel);
            row.addView(xPoint);
            row.addView(yPoint);

            coordinateInputs.add(xPoint);
            coordinateInputs.add(yPoint);

            table.addView(row);
        }
        Button doneButton = new Button(this);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        doneButton.setLayoutParams(buttonParams);
        doneButton.setText(R.string.done_label);
        doneButton.setPadding(60, 0, 60, 0);
        doneButton.setGravity(Gravity.CENTER);
        doneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PointPickerActivity.this, CasteljauTreeActivity.class);
                double[] coordinates = new double[coordinateInputs.size()];
                for(int i = 0; i < coordinateInputs.size(); i++) {
//                    Log.d("Picker", coordinateInputs.get(i).getText().toString());
                    coordinates[i] = Double.valueOf(coordinateInputs.get(i).getText().toString());
                }
                intent.putExtra("coordinates", coordinates);
                startActivity(intent);
                finish();
            }
        });
        root.addView(table);
        root.addView(doneButton);

        setContentView(root);
    }
}