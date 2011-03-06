package net.mobilespirit.curvy.util;

/**
 * Bezier curves - University related project
 * User: Vini
 * Date: 01-02-2011
 * Time: 14:44
 * Package: com.vini.geometry.util
 */
public class MathUtil {
    public static int progression(int n) {
        return (n * (n + 1)) / 2;
    }

    public static double roundTo(double n, int places) {
        double p = Math.pow(10, places);
        n = n * p;
        return Math.round(n) / p;
    }

}
