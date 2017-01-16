
package com.openweatherweapper.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("pressure")
    @Expose
    private Integer pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;
    @SerializedName("sea_level")
    @Expose
    private Double seaLevelPressure;
    @SerializedName("grnd_level")
    @Expose
    private Double groundLevelPressure;

    /**
     * @return Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
     */
    public Double getTemp() {
        return temp;
    }

    /**
     * @return Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
     */
    public Integer getPressure() {
        return pressure;
    }

    /**
     * @return Humidity, %
     */
    public Integer getHumidity() {
        return humidity;
    }

    /**
     * @return Minimum temperature at the moment. This is deviation from current temp that is possible
     * for large cities and megalopolises geographically expanded (use these parameter optionally).
     * Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
     */
    public Double getTempMin() {
        return tempMin;
    }

    /**
     * @return Maximum temperature at the moment. This is deviation from current temp that is possible
     * for large cities and megalopolises geographically expanded (use these parameter optionally).
     * Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
     */
    public Double getTempMax() {
        return tempMax;
    }

    /**
     * @return Atmospheric pressure on the sea level, hPa
     */
    public Double getSeaLevelPressure() {
        return seaLevelPressure;
    }

    /**
     * @return Atmospheric pressure on the ground level, hPa
     */
    public Double getGroundLevelPressure() {
        return groundLevelPressure;
    }
}
