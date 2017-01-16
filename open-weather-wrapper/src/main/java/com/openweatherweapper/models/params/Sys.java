
package com.openweatherweapper.models.params;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("sunrise")
    @Expose
    private Long sunrise;
    @SerializedName("sunset")
    @Expose
    private Long sunset;

    /**
     * @return Country code (GB, JP etc.)
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return Sunrise time, unix, UTC
     */
    public Long getSunrise() {
        return sunrise;
    }

    /**
     * @return Sunset time, unix, UTC
     */
    public Long getSunset() {
        return sunset;
    }
}
