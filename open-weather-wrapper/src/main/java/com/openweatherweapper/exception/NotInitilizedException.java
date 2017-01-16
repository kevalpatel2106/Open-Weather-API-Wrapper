package com.openweatherweapper.exception;

/**
 * Created by Keval on 16-Jan-17.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class NotInitilizedException extends RuntimeException {

    public NotInitilizedException(){
        super("The library is not initialized yet. Use OpenWeatherApi.initilize() at the lunch activity of your application.");
    }
}
