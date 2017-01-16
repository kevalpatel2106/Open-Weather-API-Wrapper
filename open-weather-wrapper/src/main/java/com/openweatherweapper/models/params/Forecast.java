package com.openweatherweapper.models.params;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Keval on 16-Jan-17.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class Forecast {
    @SerializedName("dt")
    @Expose
    private Long forecastTime;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("rain")
    @Expose
    private Rain rain;

    /**
     * @return Time of data forecasted, unix, UTC
     */
    public Long getForecastTime() {
        return forecastTime;
    }

    /**
     * @return {@link Main}
     * @see Main
     */
    public Main getMain() {
        return main;
    }

    /**
     * @return Weather information
     * @see Weather
     */
    public List<Weather> getWeather() {
        return weather;
    }

    /**
     * @return Get cloudiness information
     * @see Clouds
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * @return Wind information
     * @see Wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * @return Rain information
     * @see Rain
     */
    public Rain getRain() {
        return rain;
    }
}
