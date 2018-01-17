package ru.supplyphotos.data.answers.start_login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @autor user on 17.01.2018.
 */

public class DeviceToken {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("token")
    @Expose
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
