package com.openweatherweapper.models.params;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Keval on 16-Jan-17.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class Rain {

    @SerializedName("3h")
    @Expose
    private int last3hVolume;

    /**
     * @return Rain volume for the last 3 hours
     */
    public int getLast3hVolume() {
        return last3hVolume;
    }
}
