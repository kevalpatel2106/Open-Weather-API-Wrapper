
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
