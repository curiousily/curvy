package net.mobilespirit.curvy.view;

import android.content.Context;
import android.text.InputType;
import android.view.Gravity;
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
    private LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
        addPointButton = createAddPointButton();
        root.addView(addPointButton);
        doneButton = createDoneButton();
        root.addView(doneButton);
        addView(root);
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
        TableRow row = new TableRow(context);
        row.setPadding(20, 0, 20, 0);
        row.setLayoutParams(rowParams);

        TextView pointLabel = new TextView(context);
        pointLabel.setText(R.string.point_label);
        pointLabel.setPadding(3, 3, 10, 3);

        EditText xPoint = new EditText(context);
        xPoint.setWidth(100);
        xPoint.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        xPoint.setHint(R.string.x_coordinate_hint);

        EditText yPoint = new EditText(context);
        yPoint.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        yPoint.setWidth(100);
        yPoint.setHint(R.string.y_coordinate_hint);

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
        button.setText(R.string.done_label);
        return button;
    }

    private Button createAddPointButton() {
        Button button = new Button(context);
        button.setLayoutParams(buttonParams);
        button.setText(R.string.add_point_label);
        return button;
    }


    public void setPointList(List<Point2D> pointList) {
        // @TODO add point coordinates to the view
    }
}
