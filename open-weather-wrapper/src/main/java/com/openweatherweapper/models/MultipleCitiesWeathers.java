package com.openweatherweapper.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keval on 16-Jan-17.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class MultipleCitiesWeathers {

    @SerializedName("cnt")
    @Expose
    private int noOfResult;
    @SerializedName("list")
    @Expose
    private List<CurrentWeather> weatherInfos = new ArrayList<>();
    @Expose
    private int code;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * @return list of {@link CurrentWeather} information for each city
     */
    public List<CurrentWeather> getWeatherInfos() {
        return weatherInfos;
    }

    public int getNoOfResult() {
        return noOfResult;
    }

    public int getStatusCode() {
        return code;
    }

    public String statusMessage() {
        return message;
    }
}
