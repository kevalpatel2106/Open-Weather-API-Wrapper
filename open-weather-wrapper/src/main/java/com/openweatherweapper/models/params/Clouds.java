
package com.openweatherweapper.models.params;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    @Expose
    private Double cloudiness;

    /**
     * @return Cloudiness in %
     */
    public Double getCloudiness() {
        return cloudiness;
    }
}
