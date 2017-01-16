
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
