package com.openweatherweapper;

import com.openweatherweapper.models.CurrentWeather;
import com.openweatherweapper.models.MultipleCitiesWeathers;

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

    @GET("weather")
    Observable<CurrentWeather> getCurrentWeatherByName(@Query("q") String city,
                                                       @Query("units") String unit,
                                                       @Query("appid") String apikey);

    @GET("weather")
    Observable<CurrentWeather> getCurrentWeatherByLatLng(@Query("lat") double latitude,
                                                         @Query("lon") double longitude,
                                                         @Query("units") String unit,
                                                         @Query("appid") String apikey);

    @GET("weather")
    Observable<CurrentWeather> getCurrentWeatherById(@Query("id") int cityId,
                                                     @Query("units") String unit,
                                                     @Query("appid") String apikey);

    @GET("weather")
    Observable<CurrentWeather> getCurrentWeatherByZipCode(@Query("zip") String zipCode,
                                                          @Query("units") String unit,
                                                          @Query("appid") String apikey);

    @GET("group")
    Observable<MultipleCitiesWeathers> getCurrentWeatherMultipleCities(@Query("id") String cityIds,
                                                                       @Query("units") String unit,
                                                                       @Query("appid") String apikey);
}
