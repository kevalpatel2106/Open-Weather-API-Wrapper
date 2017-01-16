package com.openweatherweapper;

import com.openweatherweapper.models.CurrentWeather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Keval on 16-Jan-17.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

interface APIService {
    String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    String OPEN_WEATHER_API_KEY = "5ae14346421dddcb597ebcf1e3579d83";

    @GET("weather")
    Observable<CurrentWeather> getCurrentWeather(@Query("q") String city, @Query("appid") String apikey);
}
