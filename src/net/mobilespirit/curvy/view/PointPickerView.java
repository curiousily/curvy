package net.mobilespirit.curvy.view;

import android.content.Context;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import net.mobilespirit.curvy.R;
import net.mobilespirit.curvy.domain.point.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 09-03-2011
 * Time: 17-53
 * Package: net.mobilespirit.curvy.view
 */
public class PointPickerView extends ScrollView{

    private Context context;
    private int pointCount;
    private List<EditText> coordinateTexts = new ArrayList<EditText>();
    private TableLayout table;

    private TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
    private LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    private Button addPointButton;
    private Button doneButton;

    public PointPickerView(Context context, int pointCount) {
        super(context);
        this.context = context;
        this.pointCount = pointCount;
        setFillViewport(true);
        ViewGroup root = createRoot();
        table = createTable();
        root.addView(table);
        addTableRows();
        root.addView(createButtonLayout());
        addView(root);
    }

    private View createButtonLayout() {
        addPointButton = createAddPointButton();
        doneButton = createDoneButton();
        RelativeLayout.LayoutParams buttonLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(buttonLayoutParams);

        RelativeLayout.LayoutParams addButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        addButtonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        layout.addView(addPointButton, addButtonParams);

        RelativeLayout.LayoutParams doneButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        doneButtonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        layout.addView(doneButton, doneButtonParams);
        return layout;
    }

    private void addTableRows() {
        for(int i = 0; i < pointCount; i++) {
            addTableRow();
        }
    }

    public void addPoint() {
        addTableRow(true);
        pointCount++;
    }

    private void addTableRow() {
        addTableRow(false);
    }

    private void addTableRow(boolean requestFocusOnComplete) {
        addTableRow(requestFocusOnComplete, null);
    }

    private void addTableRow(boolean requestFocusOnComplete, Point2D point) {
        TableRow row = new TableRow(context);
        row.setPadding(asPx(20), 0, asPx(20), 0);
        row.setLayoutParams(rowParams);

        TextView pointLabel = new TextView(context);
        pointLabel.setText(R.string.point_label);
        pointLabel.setPadding(asPx(3), asPx(3), asPx(10), asPx(3));

        EditText xPoint = new EditText(context);
        xPoint.setWidth(asPx(100));
        if(point != null) {
            xPoint.setText(String.valueOf(point.x()));
        } else {
            xPoint.setHint(R.string.x_coordinate_hint);
        }

        EditText yPoint = new EditText(context);
        yPoint.setWidth(asPx(100));
        if(point != null) {
            yPoint.setText(String.valueOf(point.y()));
        } else {
            yPoint.setHint(R.string.y_coordinate_hint);
        }

        row.addView(pointLabel);
        row.addView(xPoint);
        row.addView(yPoint);

        coordinateTexts.add(xPoint);
        coordinateTexts.add(yPoint);

        table.addView(row);
        if(requestFocusOnComplete) {
            xPoint.requestFocus();
        }
    }

    public void setOnDoneClickListener(OnClickListener listener) {
        doneButton.setOnClickListener(listener);
    }

    public void setOnAddPointClickListener(OnClickListener listener) {
        addPointButton.setOnClickListener(listener);
    }

    public List<EditText> getCoordinateTexts() {
        return coordinateTexts;
    }

    private ViewGroup createRoot() {
        LinearLayout root = new LinearLayout(context);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams rootParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        root.setLayoutParams(rootParams);
        return root;
    }

    private TableLayout createTable() {
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        TableLayout table = new TableLayout(context);
        table.setGravity(Gravity.CENTER_HORIZONTAL);
        table.setLayoutParams(params);
        return table;
    }

    private Button createDoneButton() {
        Button button = new Button(context);
        button.setLayoutParams(buttonParams);
        button.setPadding(asPx(60), 0, asPx(60), 0);
        button.setText(R.string.done_label);
        return button;
    }

    private Button createAddPointButton() {
        Button button = new Button(context);
        button.setLayoutParams(buttonParams);
        button.setPadding(asPx(50), 0, asPx(50), 0);
        button.setText(R.string.add_point_label);
        return button;
    }


    public void setPointList(List<Point2D> pointList) {
        table.removeAllViews();
        for(Point2D point: pointList) {
            addPoint(point);
        }
    }

    private void addPoint(Point2D point) {
        addTableRow(false, point);
    }

    private int asPx(int dips) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dips, getResources().getDisplayMetrics());
    }
}
