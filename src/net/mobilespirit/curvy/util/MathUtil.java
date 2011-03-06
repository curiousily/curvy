package net.mobilespirit.curvy.util;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 06-03-2011
 * Time: 23-15
 * Package: net.mobilespirit.curvy.util
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
