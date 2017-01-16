
package com.openweatherweapper.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Wind information.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */
public class Wind {

    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Integer degree;

    /**
     * @return Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * @return Wind direction, degrees (meteorological)
     */
    public Integer getDegree() {
        return degree;
    }
}
