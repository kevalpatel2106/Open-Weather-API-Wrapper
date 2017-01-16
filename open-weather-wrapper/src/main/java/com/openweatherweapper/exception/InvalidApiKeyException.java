package com.openweatherweapper.exception;

/**
 * Created by Keval on 16-Jan-17.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class InvalidApiKeyException extends RuntimeException {

    public InvalidApiKeyException(){
        super("Invalid API key. Go to http://openweathermap.org/appid and generate the API key.");
    }
}
