package com.openweatherweapper;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.PublicKey;

/**
 * Created by Keval on 16-Jan-17.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

@SuppressWarnings("WeakerAccess")
public class Unit {
    public static final String MATRIX = "metric";
    public static final String STANDARD = "";
    public static final String IMPERIAL = "imperial";


    static boolean isValidUnit(String unit) {
        return unit == null || unit.equals(IMPERIAL) || unit.equals(MATRIX) || unit.equals(STANDARD);
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({MATRIX, IMPERIAL, STANDARD})
    public @interface Units {
    }
}
