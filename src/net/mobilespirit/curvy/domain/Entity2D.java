package net.mobilespirit.curvy.domain;

/**
 * Bezier curves - University related project
 * User: Vini
 * Date: 01-02-2011
 * Time: 13:58
 * Package: com.vini.geometry.entity
 */
public class Entity2D {
    private double x,y;

    public Entity2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }
}
