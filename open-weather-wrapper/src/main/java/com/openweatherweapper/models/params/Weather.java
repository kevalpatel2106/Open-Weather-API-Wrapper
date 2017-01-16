
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

/**
 * This class contains more info Weather condition codes.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}x
 */
public class Weather {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;

    /**
     * @return Weather condition id
     */
    public long getId() {
        return id;
    }

    /**
     * @return Group of weather parameters (Rain, Snow, Extreme etc.)
     */
    public String getMain() {
        return main;
    }

    /**
     * @return Weather condition within the group
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Weather icon id
     */
    public String getIcon() {
        return icon;
    }
}
