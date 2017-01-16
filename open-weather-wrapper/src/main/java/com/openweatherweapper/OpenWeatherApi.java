package com.openweatherweapper;

import android.support.annotation.NonNull;

import com.openweatherweapper.exception.InvalidApiKeyException;

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
}
