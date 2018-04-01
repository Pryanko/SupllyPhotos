package ru.supplyphotos.data.answers.manuals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.parceler.Parcel;

/**
 * @autor user on 16.01.2018.
 */
@Parcel
public class Guide {

    @SerializedName("id")
    @Expose
    Integer id;
    @SerializedName("header")
    @Expose
    String header;
    @SerializedName("image1024")
    @Expose
    String image1024;
    @SerializedName("image480")
    @Expose
    String image480;
    @SerializedName("text")
    @Expose
    String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getImage1024() {
        return image1024;
    }

    public void setImage1024(String image1024) {
        this.image1024 = image1024;
    }

    public String getImage480() {
        return image480;
    }

    public void setImage480(String image480) {
        this.image480 = image480;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
