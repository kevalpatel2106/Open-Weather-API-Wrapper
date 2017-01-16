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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.openweatherweapper.exception.InvalidApiKeyException;
import com.openweatherweapper.exception.NotInitilizedException;
import com.openweatherweapper.interfaces.CurrentWeatherListener;
import com.openweatherweapper.interfaces.ForecastListener;
import com.openweatherweapper.interfaces.MultipleCitiesWeatherListener;
import com.openweatherweapper.models.CurrentWeather;
import com.openweatherweapper.models.MultipleCitiesWeathers;
import com.openweatherweapper.models.WeatherForecast;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Keval on 16-Jan-17.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */
public class OpenWeatherApi {

    private static final int NO_LIMIT = -1;
    private static final int SUCCESS_CODE = 200;

    @SuppressWarnings("NullableProblems")
    @NonNull
    private static String sApiKey;
    private static String sUnit;

    private OpenWeatherApi() {
        throw new RuntimeException("Private constructor cannot be access by another class.");
    }

    /**
     * Initialize the library before using it.
     *
     * @param apiKey The api key to get data from openweathermap.org. If you don't have api key yet,
     *               you can generate from http://openweathermap.org/appid .
     * @see 'http://openweathermap.org/appid'
     */
    public static void initialize(@NonNull String apiKey, @Unit.Units String unit) {
        //noinspection ConstantConditions
        if (apiKey == null) throw new InvalidApiKeyException();

        sApiKey = apiKey;
        sUnit = unit;

        if (!Unit.isValidUnit(sUnit)) sUnit = Unit.STANDARD;
    }

    @NonNull
    static String getApiKey() {
        return sApiKey;
    }

    @NonNull
    @Unit.Units
    static String getUnit() {
        return sUnit;
    }

    private static void checkInitializeOrThrow() {
        //noinspection ConstantConditions
        if (sApiKey == null) throw new NotInitilizedException();
    }

    /***********************************************************************************************
     * CURRENT WEATHER APIS
     ***********************************************************************************************/

    /**
     * Get the current weather condition for the given city name. If the city name does not mach to any location
     * error will occur.
     *
     * @param cityName name of the city. (e.g. Landon)
     * @param listener {@link CurrentWeatherListener} to listen the response
     */
    @SuppressWarnings("WeakerAccess")
    public static void getCurrentWeather(@NonNull String cityName,
                                         @NonNull final CurrentWeatherListener listener) {
        getCurrentWeather(cityName, null, listener);
    }


    /**
     * Get the current weather condition for the given city name. If the city name does not mach to any location
     * error will occur.
     *
     * @param cityName    name of the city. (e.g. Landon)
     * @param countryCode ISO 3166 country code. (e.g. uk)
     * @param listener    {@link CurrentWeatherListener} to listen the response
     */
    @SuppressWarnings("WeakerAccess")
    public static void getCurrentWeather(@NonNull String cityName,
                                         @Nullable String countryCode,
                                         @NonNull final CurrentWeatherListener listener) {

        //Check if the sdk initialized?
        checkInitializeOrThrow();

        APIService apiService = RetrofitBuilder.getApiService();
        Observable<CurrentWeather> observable = apiService
                .getCurrentWeatherByName(countryCode == null ? cityName : (cityName + countryCode), sUnit, sApiKey);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<CurrentWeather>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(CurrentWeather currentWeather) {
                        if (currentWeather.getStatusCode() == SUCCESS_CODE)
                            listener.onResponse(currentWeather);
                        else listener.onError(currentWeather.statusMessage());
                    }
                });

    }

    /**
     * Get current weather condition pointed by the geo point.
     *
     * @param latitude  latitude of the point
     * @param longitude latitude of the point
     * @param listener  {@link CurrentWeatherListener} to listen the response
     */
    @SuppressWarnings("WeakerAccess")
    public static void getCurrentWeather(double latitude,
                                         double longitude,
                                         @NonNull final CurrentWeatherListener listener) {

        //Check if the sdk initialized?
        checkInitializeOrThrow();

        APIService apiService = RetrofitBuilder.getApiService();
        Observable<CurrentWeather> observable = apiService
                .getCurrentWeatherByLatLng(latitude, longitude, sUnit, sApiKey);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<CurrentWeather>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(CurrentWeather currentWeather) {
                        if (currentWeather.getStatusCode() == SUCCESS_CODE)
                            listener.onResponse(currentWeather);
                        else listener.onError(currentWeather.statusMessage());
                    }
                });

    }

    /**
     * Get current weather of the city provided by the city id. This method is recommended to get the
     * accurate result for the give city.  If the city id does not exist then, error will occur.
     *
     * @param cityId   city id.
     * @param listener {@link CurrentWeatherListener} to listen the response
     */
    @SuppressWarnings("WeakerAccess")
    public static void getCurrentWeather(int cityId,
                                         @NonNull final CurrentWeatherListener listener) {

        //Check if the sdk initialized?
        checkInitializeOrThrow();

        APIService apiService = RetrofitBuilder.getApiService();
        Observable<CurrentWeather> observable = apiService.getCurrentWeatherById(cityId, sUnit, sApiKey);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<CurrentWeather>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(CurrentWeather currentWeather) {
                        if (currentWeather.getStatusCode() == SUCCESS_CODE)
                            listener.onResponse(currentWeather);
                        else listener.onError(currentWeather.statusMessage());
                    }
                });

    }

    /**
     * Get current weather of the city provided by zip/postal code. If the zip/postal code does not
     * exist then, error will occur.
     *
     * @param zipCode     Zip code of the area.
     * @param countryCode ISO 3166 country code. (e.g. uk)
     * @param listener    {@link CurrentWeatherListener} to listen the response
     */
    @SuppressWarnings("WeakerAccess")
    public static void getCurrentWeather(long zipCode,
                                         @NonNull String countryCode,
                                         @NonNull final CurrentWeatherListener listener) {

        //Check if the sdk initialized?
        checkInitializeOrThrow();

        APIService apiService = RetrofitBuilder.getApiService();
        Observable<CurrentWeather> observable = apiService
                .getCurrentWeatherByZipCode(zipCode + "," + countryCode, sUnit, sApiKey);

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<CurrentWeather>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(CurrentWeather currentWeather) {
                        if (currentWeather.getStatusCode() == SUCCESS_CODE)
                            listener.onResponse(currentWeather);
                        else listener.onError(currentWeather.statusMessage());
                    }
                });
    }

    /**
     * Get the current weather condition for more than one city by sending the list of city ids.
     *
     * @param idsOfCities List of the city ids
     * @param listener    {@link MultipleCitiesWeatherListener} to listen the response
     */
    @SuppressWarnings("WeakerAccess")
    public static void getMultipleCitiesCurrentWeather(ArrayList<String> idsOfCities,
                                                       @NonNull final MultipleCitiesWeatherListener listener) {

        //Check if the sdk initialized?
        checkInitializeOrThrow();

        APIService apiService = RetrofitBuilder.getApiService();
        Observable<MultipleCitiesWeathers> observable = apiService
                .getCurrentWeatherMultipleCities(TextUtils.join(",", idsOfCities), sUnit, sApiKey);

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<MultipleCitiesWeathers>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(MultipleCitiesWeathers citiesWeathers) {
                        if (citiesWeathers.getStatusCode() == SUCCESS_CODE)
                            listener.onResponse(citiesWeathers);
                        else listener.onError(citiesWeathers.statusMessage());
                    }
                });
    }

    /***********************************************************************************************
     * THREE HOURLY FORECAST FOR MAX 5 DAYS FORECAST API
     ***********************************************************************************************/

    /**
     * Get weather forecast for every three hours for given city id. This will return maximum 5 days
     * forecast if available.
     *
     * @param cityId   id of the city to get the forecast
     * @param listener {@link ForecastListener} to get the forecast data.
     */
    @SuppressWarnings("WeakerAccess")
    public static void getThreeHoursForecast(int cityId,
                                             @NonNull final ForecastListener listener) {
        getThreeHoursForecast(cityId, NO_LIMIT, listener);
    }

    /**
     * Get weather forecast for every three hours for given city id.
     *
     * @param cityId   id of the city to get the forecast
     * @param limit    number of forecast result required. The value must be between 1 to 40.
     * @param listener {@link ForecastListener} to get the forecast data.
     */
    @SuppressWarnings("WeakerAccess")
    public static void getThreeHoursForecast(int cityId,
                                             int limit,
                                             @NonNull final ForecastListener listener) {

        //Check if the sdk initialized?
        checkInitializeOrThrow();

        APIService apiService = RetrofitBuilder.getApiService();
        Observable<WeatherForecast> observable = apiService
                .getThreeHoursForecast(cityId, (limit < 0 ? limit + "" : ""), sUnit, sApiKey);

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<WeatherForecast>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherForecast weatherForecasts) {
                        if (weatherForecasts.getStatusCode() == SUCCESS_CODE)
                            listener.onResponse(weatherForecasts);
                        else listener.onError(weatherForecasts.statusMessage());
                    }
                });
    }

    /**
     * Get weather forecast for every three hours for given city name. If the city name does not mach
     * to any location error will occur. This will return maximum 5 days
     * forecast if available.
     *
     * @param cityName name of the city. (e.g. Landon)
     * @param listener {@link ForecastListener} to get the forecast data.
     */
    @SuppressWarnings("WeakerAccess")
    public static void getThreeHoursForecast(@NonNull String cityName,
                                             @NonNull final ForecastListener listener) {
        getThreeHoursForecast(cityName, null, NO_LIMIT, listener);
    }

    /**
     * Get weather forecast for every three hours for given city name. If the city name does not mach
     * to any location error will occur.
     *
     * @param cityName name of the city. (e.g. Landon)
     * @param limit    number of forecast result required. The value must be between 1 to 40.
     * @param listener {@link ForecastListener} to get the forecast data.
     */
    @SuppressWarnings("WeakerAccess")
    public static void getThreeHoursForecast(@NonNull String cityName,
                                             int limit,
                                             @NonNull final ForecastListener listener) {
        getThreeHoursForecast(cityName, null, limit, listener);
    }

    /**
     * Get weather forecast for every three hours for given city name. If the city name does not mach
     * to any location error will occur. This will return maximum 5 days forecast if available.
     *
     * @param cityName    name of the city. (e.g. Landon)
     * @param countryCode ISO 3166 country code. (e.g. uk)
     * @param listener    {@link ForecastListener} to get the forecast data.
     */
    @SuppressWarnings("WeakerAccess")
    public static void getThreeHoursForecast(@NonNull String cityName,
                                             @Nullable String countryCode,
                                             @NonNull final ForecastListener listener) {
        getThreeHoursForecast(cityName, countryCode, NO_LIMIT, listener);
    }

    /**
     * Get weather forecast for every three hours for given city name. If the city name does not mach
     * to any location error will occur.
     *
     * @param cityName    name of the city. (e.g. Landon)
     * @param countryCode ISO 3166 country code. (e.g. uk)
     * @param limit       number of forecast result required. The value must be between 1 to 40.
     * @param listener    {@link ForecastListener} to get the forecast data.
     */
    @SuppressWarnings("WeakerAccess")
    public static void getThreeHoursForecast(@NonNull String cityName,
                                             @Nullable String countryCode,
                                             int limit,
                                             @NonNull final ForecastListener listener) {

        //Check if the sdk initialized?
        checkInitializeOrThrow();

        APIService apiService = RetrofitBuilder.getApiService();
        Observable<WeatherForecast> observable = apiService
                .getThreeHoursForecast(countryCode == null ? cityName : (cityName + countryCode),
                        (limit > 0 ? limit + "" : ""), sUnit, sApiKey);

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<WeatherForecast>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherForecast weatherForecasts) {
                        if (weatherForecasts.getStatusCode() == SUCCESS_CODE)
                            listener.onResponse(weatherForecasts);
                        else listener.onError(weatherForecasts.statusMessage());
                    }
                });
    }

    /**
     * Get weather forecast for every three hours for given geo point. This will return maximum 5 days forecast
     * if available.
     *
     * @param latitude  latitude of the point
     * @param longitude latitude of the point
     * @param listener  {@link ForecastListener} to listen the response
     */
    @SuppressWarnings("WeakerAccess")
    public static void getThreeHoursForecast(double latitude,
                                             double longitude,
                                             @NonNull final ForecastListener listener) {
        getThreeHoursForecast(latitude, longitude, NO_LIMIT, listener);
    }

    /**
     * Get weather forecast for every three hours for given geo point.
     *
     * @param latitude  latitude of the point
     * @param limit     number of forecast result required. The value must be between 1 to 40.
     * @param longitude latitude of the point
     * @param listener  {@link CurrentWeatherListener} to listen the response
     */
    @SuppressWarnings("WeakerAccess")
    public static void getThreeHoursForecast(double latitude,
                                             double longitude,
                                             int limit,
                                             @NonNull final ForecastListener listener) {

        //Check if the sdk initialized?
        checkInitializeOrThrow();

        APIService apiService = RetrofitBuilder.getApiService();
        Observable<WeatherForecast> observable = apiService
                .getThreeHoursForecast(latitude, longitude,
                        (limit > 0 ? limit + "" : ""),
                        sUnit, sApiKey);

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<WeatherForecast>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherForecast weatherForecasts) {
                        if (weatherForecasts.getStatusCode() == SUCCESS_CODE)
                            listener.onResponse(weatherForecasts);
                        else listener.onError(weatherForecasts.statusMessage());
                    }
                });
    }


    /***********************************************************************************************
     * DAILY FORECAST FOR MAX 16 DAYS FORECAST API
     ***********************************************************************************************/
    /**
     * Get daily weather forecast. This will return maximum 16 days forecast if available.
     *
     * @param cityId   id of the city to get the forecast
     * @param listener {@link ForecastListener} to get the forecast data.
     */
    @SuppressWarnings("WeakerAccess")
    public static void getDailyForecast(int cityId,
                                        @NonNull final ForecastListener listener) {
        getDailyForecast(cityId, NO_LIMIT, listener);
    }

    /**
     * Get daily weather forecast.
     *
     * @param cityId   id of the city to get the forecast
     * @param limit    number of forecast result required. The value must be between 1 to 16.
     * @param listener {@link ForecastListener} to get the forecast data.
     */
    @SuppressWarnings("WeakerAccess")
    public static void getDailyForecast(int cityId,
                                        int limit,
                                        @NonNull final ForecastListener listener) {

        //Check if the sdk initialized?
        checkInitializeOrThrow();

        APIService apiService = RetrofitBuilder.getApiService();
        Observable<WeatherForecast> observable = apiService
                .getDailyForecast(cityId, (limit > 0 ? limit + "" : ""), sUnit, sApiKey);

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<WeatherForecast>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherForecast weatherForecasts) {
                        if (weatherForecasts.getStatusCode() == SUCCESS_CODE)
                            listener.onResponse(weatherForecasts);
                        else listener.onError(weatherForecasts.statusMessage());
                    }
                });
    }

    /**
     * Get daily weather forecast. If the city name does not match to any location error will occur.
     * This will return maximum 16 days forecast if available.
     *
     * @param cityName name of the city. (e.g. Landon)
     * @param listener {@link ForecastListener} to get the forecast data.
     */
    @SuppressWarnings("WeakerAccess")
    public static void getDailyForecast(@NonNull String cityName,
                                        @NonNull final ForecastListener listener) {
        getDailyForecast(cityName, null, NO_LIMIT, listener);
    }

    /**
     * Get daily weather forecast. If the city name does not match to any location error will occur.
     *
     * @param cityName name of the city. (e.g. Landon)
     * @param limit    number of forecast result required. The value must be between 1 to 16.
     * @param listener {@link ForecastListener} to get the forecast data.
     */
    @SuppressWarnings("WeakerAccess")
    public static void getDailyForecast(@NonNull String cityName,
                                        int limit,
                                        @NonNull final ForecastListener listener) {
        getDailyForecast(cityName, null, limit, listener);
    }

    /**
     * Get daily weather forecast. If the city name does not match to any location error will occur.
     * This will return maximum 16 days forecast if available.
     *
     * @param cityName    name of the city. (e.g. Landon)
     * @param countryCode ISO 3166 country code. (e.g. uk)
     * @param listener    {@link ForecastListener} to get the forecast data.
     */
    @SuppressWarnings("WeakerAccess")
    public static void getDailyForecast(@NonNull String cityName,
                                        @Nullable String countryCode,
                                        @NonNull final ForecastListener listener) {
        getDailyForecast(cityName, countryCode, NO_LIMIT, listener);
    }

    /**
     * Get daily weather forecast. If the city name does not match to any location error will occur.
     *
     * @param cityName    name of the city. (e.g. Landon)
     * @param countryCode ISO 3166 country code. (e.g. uk)
     * @param limit       number of forecast result required. The value must be between 1 to 16.
     * @param listener    {@link ForecastListener} to get the forecast data.
     */
    @SuppressWarnings("WeakerAccess")
    public static void getDailyForecast(@NonNull String cityName,
                                        @Nullable String countryCode,
                                        int limit,
                                        @NonNull final ForecastListener listener) {

        //Check if the sdk initialized?
        checkInitializeOrThrow();

        APIService apiService = RetrofitBuilder.getApiService();
        Observable<WeatherForecast> observable = apiService
                .getDailyForecast(countryCode == null ? cityName : (cityName + countryCode),
                        (limit > 0 ? limit + "" : ""), sUnit, sApiKey);

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<WeatherForecast>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherForecast weatherForecasts) {
                        if (weatherForecasts.getStatusCode() == SUCCESS_CODE)
                            listener.onResponse(weatherForecasts);
                        else listener.onError(weatherForecasts.statusMessage());
                    }
                });
    }

    /**
     * Get daily weather forecast for given geo point. This will return maximum 16 days forecast if
     * available.
     *
     * @param latitude  latitude of the point
     * @param longitude latitude of the point
     * @param listener  {@link ForecastListener} to listen the response
     */
    @SuppressWarnings("WeakerAccess")
    public static void getDailyForecast(double latitude,
                                        double longitude,
                                        @NonNull final ForecastListener listener) {
        getDailyForecast(latitude, longitude, NO_LIMIT, listener);
    }

    /**
     * Get daily weather forecast for given geo point.
     *
     * @param latitude  latitude of the point
     * @param limit     number of forecast result required. The value must be between 1 to 16.
     * @param longitude latitude of the point
     * @param listener  {@link ForecastListener} to listen the response
     */
    @SuppressWarnings("WeakerAccess")
    public static void getDailyForecast(double latitude,
                                        double longitude,
                                        int limit,
                                        @NonNull final ForecastListener listener) {

        //Check if the sdk initialized?
        checkInitializeOrThrow();

        APIService apiService = RetrofitBuilder.getApiService();
        Observable<WeatherForecast> observable = apiService
                .getDailyForecast(latitude, longitude,
                        (limit > 0 ? limit + "" : ""),
                        sUnit, sApiKey);

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<WeatherForecast>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherForecast weatherForecasts) {
                        if (weatherForecasts.getStatusCode() == SUCCESS_CODE)
                            listener.onResponse(weatherForecasts);
                        else listener.onError(weatherForecasts.statusMessage());
                    }
                });
    }
}
