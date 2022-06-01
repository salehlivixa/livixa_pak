package com.livixa.apacam.client.response.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NotificationObject implements Serializable {

    @SerializedName("sh_id")
    @Expose
    private String sh_id;
    @SerializedName("sh_type")
    @Expose
    private String sh_type;
    @SerializedName("sh_content")
    @Expose
    private String sh_content;
    @SerializedName("sh_date")
    @Expose
    private String sh_date;

    public String getSh_id() {
        return sh_id;
    }

    public void setSh_id(String sh_id) {
        this.sh_id = sh_id;
    }

    public String getSh_type() {
        return sh_type;
    }

    public void setSh_type(String sh_type) {
        this.sh_type = sh_type;
    }

    public String getSh_content() {
        return sh_content;
    }

    public void setSh_content(String sh_content) {
        this.sh_content = sh_content;
    }

    public String getSh_date() {
        return sh_date;
    }

    public void setSh_date(String sh_date) {
        this.sh_date = sh_date;
    }
}
