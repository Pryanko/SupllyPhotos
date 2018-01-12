package ru.supplyphotos.data.answers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @autor user on 12.01.2018.
 */

public class Manual {

    @SerializedName("header")
    @Expose
    private String header;
    @SerializedName("infoText")
    @Expose
    private String infoText;
    @SerializedName("image")
    @Expose
    private String image;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
