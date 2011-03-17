package net.mobilespirit.curvy.application;

import android.app.Application;
import net.mobilespirit.curvy.domain.point.Point2D;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 09-03-2011
 * Time: 17-47
 * Package: net.mobilespirit.curvy.application
 */
public class CurvyApplication extends Application {

    private List<Point2D> pointList;

    @Override
    public void onCreate() {
        super.onCreate();
        pointList = new ArrayList<Point2D>();
    }

    public void setPointList(List<Point2D> pointList) {
        this.pointList = pointList;
    }

    public void addPointList(Collection<Point2D> pointList) {
        this.pointList.addAll(pointList);
    }

    public List<Point2D> getPointList() {
        return pointList;
    }

    public boolean hasPoints() {
        return !pointList.isEmpty();
    }
}
