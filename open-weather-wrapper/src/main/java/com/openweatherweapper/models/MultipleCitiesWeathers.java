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
