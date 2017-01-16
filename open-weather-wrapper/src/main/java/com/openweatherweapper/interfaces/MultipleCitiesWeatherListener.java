package com.openweatherweapper.interfaces;

import com.openweatherweapper.models.CurrentWeather;
import com.openweatherweapper.models.MultipleCitiesWeathers;

/**
 * Created by Keval on 16-Jan-17.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public interface MultipleCitiesWeatherListener {

    void onResponse(MultipleCitiesWeathers citiesWeathers);

    void onError(String message);
}
