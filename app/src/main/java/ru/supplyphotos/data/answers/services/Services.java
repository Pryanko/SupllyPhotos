package ru.supplyphotos.data.answers.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Libgo on 24.03.2018.
 */

public class Services {

    @SerializedName("data")
    @Expose
    private List<ItemService> data = null;

    public List<ItemService> getData() {
        return data;
    }

    public void setData(List<ItemService> data) {
        this.data = data;
    }


}
