
package com.openweatherweapper.models.params;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    @Expose
    private Integer cloudiness;

    /**
     * @return Cloudiness in %
     */
    public Integer getCloudiness() {
        return cloudiness;
    }
}
