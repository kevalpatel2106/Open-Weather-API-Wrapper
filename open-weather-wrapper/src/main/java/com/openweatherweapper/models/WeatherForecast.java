package com.openweatherweapper.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.openweatherweapper.models.params.City;
import com.openweatherweapper.models.params.Forecast;

import java.util.List;

/**
 * Created by Keval on 16-Jan-17.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class WeatherForecast {

    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("cnt")
    @Expose
    private Long noOfForecastResult;
    @SerializedName("list")
    @Expose
    private List<Forecast> forecast = null;

    public City getCity() {
        return city;
    }
    public Long getNoOfForecastResult() {
        return noOfForecastResult;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }
}
