package net.mobilespirit.curvy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import net.mobilespirit.curvy.data.Point;

import java.util.List;

/**
 * Bezier curves - University related project
 * User: Vini
 * Date: 28-02-2011
 * Time: 15:01
 * Package: net.mobilespirit.curvy.view
 */
public class CasteljauTreeView extends View {

    private List<Point> pointList;

    public CasteljauTreeView(Context context) {
        super(context);
    }

    public CasteljauTreeView(List<Point> pointList, Context context) {
        this(context);
        this.pointList = pointList;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint grayPaint = new Paint();
        grayPaint.setColor(Color.GRAY);
        Paint whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        whitePaint.setTextSize(7);
        
        int drawBottom = getHeight() - 45;

        int textStart = 10;
        int circleX = 30;
        int outerCircleRadius = 26;
        int innerCircleRadius = 25;

        int outerCircleDiameter = outerCircleRadius * 2;
        int innerCircleDiameter = innerCircleRadius * 2;
        for(int i = 0; i < 5; i++) {
            canvas.drawCircle(circleX + (i * 60), drawBottom, outerCircleRadius, whitePaint);
            canvas.drawCircle(circleX + (i * 60), drawBottom, innerCircleRadius, grayPaint);
            canvas.drawText("(22,32 21,43)",textStart + (i * 60), drawBottom, whitePaint);
        }

        circleX = 60;
        textStart = 40;
        for(int i = 0; i < 4; i++) {
            canvas.drawCircle(circleX + (i * 60), drawBottom - outerCircleDiameter, outerCircleRadius, whitePaint);
            canvas.drawCircle(circleX + (i * 60), drawBottom - innerCircleDiameter, innerCircleRadius, grayPaint);
            canvas.drawText("(22,32 21,43)",textStart + (i * 60), drawBottom - innerCircleDiameter, whitePaint);
        }
    }
}