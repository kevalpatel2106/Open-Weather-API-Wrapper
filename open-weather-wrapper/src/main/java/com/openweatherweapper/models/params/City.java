
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

public class City {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("population")
    @Expose
    private long population;

    /**
     * @return id of the city
     */
    public Long getId() {
        return id;
    }

    /**
     * @return nam eof the city
     */
    public String getName() {
        return name;
    }

    /**
     * @return Coordinates of the city
     * @see Coord
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * @return ISO 3166 country code.
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return Population of the city. If not available, value will be 0.
     */
    public Long getPopulation() {
        return population;
    }
}
