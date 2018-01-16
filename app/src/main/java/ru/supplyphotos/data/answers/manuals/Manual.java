package ru.supplyphotos.data.answers.manuals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @autor user on 12.01.2018.
 */

public class Manual {

    @SerializedName("data")
    @Expose
    private List<Guide> data = null;

    public List<Guide> getData() {
        return data;
    }

    public void setData(List<Guide> data) {
        this.data = data;
    }

}
