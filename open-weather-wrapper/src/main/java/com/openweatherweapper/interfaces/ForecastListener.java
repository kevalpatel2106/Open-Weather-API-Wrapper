package com.openweatherweapper.interfaces;

import com.openweatherweapper.models.MultipleCitiesWeathers;
import com.openweatherweapper.models.WeatherForecast;

/**
 * Created by Keval on 16-Jan-17.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public interface ForecastListener {

    void onResponse(WeatherForecast weatherForecasts);

    void onError(String message);
}
