package net.mobilespirit.curvy.domain.Point;

import net.mobilespirit.curvy.domain.Entity2D;

/**
 * Bezier curves - University related project
 * User: Vini
 * Date: 01-02-2011
 * Time: 13:59
 * Package: com.vini.geometry.entity.Point
 */
public class Point2D extends Entity2D {

    public Point2D(double x, double y) {
        super(x, y);
    }

    public Point3D to3D() {
        return new Point3D(x(), y(), 0);
    }
}
