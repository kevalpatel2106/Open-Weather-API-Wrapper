package com.openweatherweapper.interfaces;

import com.openweatherweapper.models.CurrentWeather;

/**
 * Created by Keval on 16-Jan-17.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public interface CurrentWeatherListener {

    void onResponse(CurrentWeather currentWeather);

    void onError(String message);
}
