package net.mobilespirit.curvy.domain;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 06-03-2011
 * Time: 23-15
 * Package: net.mobilespirit.curvy.domain
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
