package net.mobilespirit.curvy;

import android.app.Application;
import net.mobilespirit.curvy.domain.Point.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 05-03-2011
 * Time: 16-32
 */
public class CurvyApplication extends Application {

    private List<Point2D> selectedPoints = new ArrayList<Point2D>();

    public List<Point2D> getSelectedPoints() {
        return selectedPoints;
    }

    public void setSelectedPoints(List<Point2D> selectedPoints) {
        this.selectedPoints = selectedPoints;
    }



}
