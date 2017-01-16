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
