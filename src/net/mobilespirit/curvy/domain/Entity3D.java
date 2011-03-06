package net.mobilespirit.curvy.domain;

import net.mobilespirit.curvy.domain.Entity2D;

/**
 * Bezier curves - University related project
 * User: Vini
 * Date: 01-02-2011
 * Time: 13:58
 * Package: com.vini.geometry.entity
 */
public class Entity3D extends Entity2D {
    private double z;

    public Entity3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public double z() {
        return z;
    }
}
