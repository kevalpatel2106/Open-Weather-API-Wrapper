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

    public List<CurrentWeather> getWeatherInfos() {
        return weatherInfos;
    }

    public int getNoOfResult() {
        return noOfResult;
    }
}
