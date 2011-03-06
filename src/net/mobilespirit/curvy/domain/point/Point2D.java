package net.mobilespirit.curvy.domain.point;

import net.mobilespirit.curvy.domain.Entity2D;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 06-03-2011
 * Time: 23-15
 * Package: net.mobilespirit.curvy.domain.point
 */
public class Point2D extends Entity2D {

    public Point2D(double x, double y) {
        super(x, y);
    }

    public Point3D to3D() {
        return new Point3D(x(), y(), 0);
    }
}
