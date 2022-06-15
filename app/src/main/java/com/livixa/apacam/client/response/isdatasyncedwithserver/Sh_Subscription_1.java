package com.livixa.apacam.client.response.isdatasyncedwithserver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sh_Subscription_1 implements Serializable {



    private String sh_id;

    @SerializedName( "sh_subscription_eng")
    @Expose
    private String sh_subscription_eng;


    @SerializedName( "sh_subscription_arabic")
    @Expose
    private String sh_subscription_arabic;

    @SerializedName( "sh_cart_id")
    @Expose
    private String sh_cart_id;

    @SerializedName( "sh_feature_id")
    @Expose
    private String sh_feature_id;

    public String getSh_id() {
        return sh_id;
    }

    public void setSh_id(String sh_id) {
        this.sh_id = sh_id;
    }

    public String getSh_subscription_eng() {
        return sh_subscription_eng;
    }

    public void setSh_subscription_eng(String sh_subscription_eng) {
        this.sh_subscription_eng = sh_subscription_eng;
    }

    public String getSh_subscription_arabic() {
        return sh_subscription_arabic;
    }

    public void setSh_subscription_arabic(String sh_subscription_arabic) {
        this.sh_subscription_arabic = sh_subscription_arabic;
    }

    public String getSh_cart_id() {
        return sh_cart_id;
    }

    public void setSh_cart_id(String sh_cart_id) {
        this.sh_cart_id = sh_cart_id;
    }

    public String getSh_feature_id() {
        return sh_feature_id;
    }

    public void setSh_feature_id(String sh_feature_id) {
        this.sh_feature_id = sh_feature_id;
    }
}
