package org.wahlzeit.extension;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Provides functions for double calculations and comparison
 */
public class DoubleUtils {
    private final static int DEFAULT_ROUNDING_PLACES = 14;

    public static boolean compareDoubles(double a, double b){
        return compareDoubles(a, b, DEFAULT_ROUNDING_PLACES);
    }

    public static boolean compareDoubles(double a, double b, int roundingPlaces) {
        BigDecimal roundedA = getRoundedBigDecimal(a, roundingPlaces);
        BigDecimal roundedB = getRoundedBigDecimal(b, roundingPlaces);
        return roundedA.compareTo(roundedB) == 0;
    }

    public static double getRoundedValue(double value) {
        return getRoundedValue(value, DEFAULT_ROUNDING_PLACES);
    }

    public static double getRoundedValue(double value, int places) {
        BigDecimal bd = getRoundedBigDecimal(value, places);
        return bd.doubleValue();
    }

    private static BigDecimal getRoundedBigDecimal(double value, int places){
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd;
    }
}