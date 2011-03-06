package net.mobilespirit.curvy.domain.curve;

import net.mobilespirit.curvy.domain.point.Point2D;
import net.mobilespirit.curvy.domain.tree.CasteljauTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 01-02-2011
 * Time: 14:05
 * Package: net.mobilespirit.curvy.domain.curve
 */
public class BezierCurve {

    private double u;
    private List<Point2D> pointList;

    public BezierCurve() {
        this(0);
        pointList = new ArrayList<Point2D>();
    }

    public BezierCurve(double u) {
        this.u = u;
    }

    public BezierCurve(double u, List<Point2D> pointList) {
        this(u);
        this.pointList = pointList;
    }

    public BezierCurve(List<Point2D> pointList) {
        this(0, pointList);
    }

    public void addPoint(Point2D point) {
        pointList.add(point);
    }

    public void addPoints(Collection<Point2D> pointList) {
        pointList.addAll(pointList);
    }

    public CasteljauTree buildCasteljauTree() {
        CasteljauTree tree = new CasteljauTree(u, pointList);
        tree.build();
        return tree;
    }

    public int rank() {
        return pointList.size() - 1;
    }

    public int pointCount() {
        return pointList.size();
    }
}
