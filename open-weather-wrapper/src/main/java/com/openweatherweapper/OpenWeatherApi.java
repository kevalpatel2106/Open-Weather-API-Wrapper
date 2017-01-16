package com.openweatherweapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.openweatherweapper.exception.InvalidApiKeyException;
import com.openweatherweapper.exception.NotInitilizedException;
import com.openweatherweapper.interfaces.CurrentWeatherResponseListener;
import com.openweatherweapper.models.CurrentWeather;

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

    @NonNull
    private static String sApiKey;

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
    public static void initilize(@NonNull String apiKey) {
        //noinspection ConstantConditions
        if (apiKey == null) throw new InvalidApiKeyException();

        sApiKey = apiKey;
    }

    @NonNull
    static String getApiKey() {
        return sApiKey;
    }

    private static void checkInitilizeOrThrow() {
        //noinspection ConstantConditions
        if (sApiKey == null) throw new NotInitilizedException();
    }

    public static void getCurrentWeather(@NonNull String cityName,
                                         @NonNull final CurrentWeatherResponseListener listener) {
        getCurrentWeather(cityName, null, listener);
    }


    public static void getCurrentWeather(@NonNull String cityName,
                                         @Nullable String countryCode,
                                         @NonNull final CurrentWeatherResponseListener listener) {

        //Check if the sdk initialized?
        checkInitilizeOrThrow();

        APIService apiService = RetrofitBuilder.getApiService();
        Observable<CurrentWeather> observable = apiService
                .getCurrentWeatherByName(countryCode == null ? cityName : (cityName + countryCode), sApiKey);
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

    public static void getCurrentWeather(double latitude,
                                         double longitude,
                                         @NonNull final CurrentWeatherResponseListener listener) {

        //Check if the sdk initialized?
        checkInitilizeOrThrow();

        APIService apiService = RetrofitBuilder.getApiService();
        Observable<CurrentWeather> observable = apiService
                .getCurrentWeatherByLatLng(latitude, longitude, sApiKey);
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

    public static void getCurrentWeather(int cityId,
                                         @NonNull final CurrentWeatherResponseListener listener) {

        //Check if the sdk initialized?
        checkInitilizeOrThrow();

        APIService apiService = RetrofitBuilder.getApiService();
        Observable<CurrentWeather> observable = apiService.getCurrentWeatherById(cityId, sApiKey);
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

    public static void getCurrentWeather(int zipCode,
                                         @NonNull String countryCode,
                                         @NonNull final CurrentWeatherResponseListener listener) {

        //Check if the sdk initialized?
        checkInitilizeOrThrow();

        APIService apiService = RetrofitBuilder.getApiService();
        Observable<CurrentWeather> observable = apiService
                .getCurrentWeatherByZipCode(zipCode + "," + countryCode, sApiKey);

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
}
