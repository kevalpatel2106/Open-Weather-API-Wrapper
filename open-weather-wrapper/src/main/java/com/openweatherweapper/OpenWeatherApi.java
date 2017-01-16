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
     * @param cityName
     * @param listener
     */
    @SuppressWarnings("WeakerAccess")
    public static void getCurrentWeather(@NonNull String cityName,
                                         @NonNull final CurrentWeatherListener listener) {
        getCurrentWeather(cityName, null, listener);
    }


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
                        listener.onResponse(currentWeather);
                    }
                });

    }

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
                        listener.onResponse(currentWeather);
                    }
                });

    }

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
                        listener.onResponse(currentWeather);
                    }
                });

    }

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
                        listener.onResponse(currentWeather);
                    }
                });
    }


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
                        listener.onResponse(citiesWeathers);
                    }
                });
    }

    /***********************************************************************************************
     * THREE HOURLY FORECAST FOR MAX 5 DAYS FORECAST API
     ***********************************************************************************************/

    /**
     * @param cityId
     * @param listener
     */
    @SuppressWarnings("WeakerAccess")
    public static void getThreeHoursForecast(int cityId,
                                             @NonNull final ForecastListener listener) {
        getThreeHoursForecast(cityId, NO_LIMIT, listener);
    }

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
                        listener.onResponse(weatherForecasts);
                    }
                });
    }

    @SuppressWarnings("WeakerAccess")
    public static void getThreeHoursForecast(@NonNull String cityName,
                                             @NonNull final ForecastListener listener) {
        getThreeHoursForecast(cityName, null, NO_LIMIT, listener);
    }

    @SuppressWarnings("WeakerAccess")
    public static void getThreeHoursForecast(@NonNull String cityName,
                                             int limit,
                                             @NonNull final ForecastListener listener) {
        getThreeHoursForecast(cityName, null, limit, listener);
    }

    @SuppressWarnings("WeakerAccess")
    public static void getThreeHoursForecast(@NonNull String cityName,
                                             @Nullable String countryCode,
                                             @NonNull final ForecastListener listener) {
        getThreeHoursForecast(cityName, countryCode, NO_LIMIT, listener);
    }

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
                        (limit < 0 ? limit + "" : ""), sUnit, sApiKey);

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
                        listener.onResponse(weatherForecasts);
                    }
                });
    }

    @SuppressWarnings("WeakerAccess")
    public static void getThreeHoursForecast(double latitude,
                                             double longitude,
                                             @NonNull final ForecastListener listener) {
        getThreeHoursForecast(latitude, longitude, NO_LIMIT, listener);
    }

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
                        (limit < 0 ? limit + "" : ""),
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
                        listener.onResponse(weatherForecasts);
                    }
                });
    }


    /***********************************************************************************************
     * DAILY FORECAST FOR MAX 16 DAYS FORECAST API
     ***********************************************************************************************/

}
