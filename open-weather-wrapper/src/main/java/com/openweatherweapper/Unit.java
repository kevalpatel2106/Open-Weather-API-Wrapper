/*
 * Copyright 2016 Keval Patel.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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
    /**
     * Matrix units.
     * <p>
     * <li>Temperature: Celsius</li>
     * <li>Speed: meter/sec</li>
     */
    public static final String MATRIX = "metric";

    /**
     * Standard international units.
     * <p>
     * <li>Temperature: Kelvin</li>
     * <li>Speed: meter/sec</li>
     */
    public static final String STANDARD = "";

    /**
     * Imperial units.
     * <p>
     * <li>Temperature: Fahrenheit</li>
     * <li>Speed: miles/hour</li>
     */
    public static final String IMPERIAL = "imperial";


    static boolean isValidUnit(String unit) {
        return unit == null || unit.equals(IMPERIAL) || unit.equals(MATRIX) || unit.equals(STANDARD);
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({MATRIX, IMPERIAL, STANDARD})
    public @interface Units {
    }
}
