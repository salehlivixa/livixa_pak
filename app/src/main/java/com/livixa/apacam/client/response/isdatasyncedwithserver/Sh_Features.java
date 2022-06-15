package com.livixa.apacam.client.response.isdatasyncedwithserver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sh_Features implements Serializable {

    @SerializedName( "sh_id")
    @Expose
    private String sh_id;


    @SerializedName( "sh_feature_eng")
    @Expose
    private String sh_feature_eng;

    @SerializedName( "sh_feature_arabic")
    @Expose
    private String sh_feature_arabic;


    @SerializedName( "sh_cart_id")
    @Expose
    private String sh_cart_id;




    public String getSh_id() {
        return sh_id;
    }

    public void setSh_id(String sh_id) {
        this.sh_id = sh_id;
    }

    public String getSh_feature_eng() {
        return sh_feature_eng;
    }

    public void setSh_feature_eng(String sh_feature_eng) {
        this.sh_feature_eng = sh_feature_eng;
    }

    public String getSh_feature_arabic() {
        return sh_feature_arabic;
    }

    public void setSh_feature_arabic(String sh_feature_arabic) {
        this.sh_feature_arabic = sh_feature_arabic;
    }

    public String getSh_cart_id() {
        return sh_cart_id;
    }

    public void setSh_cart_id(String sh_cart_id) {
        this.sh_cart_id = sh_cart_id;
    }


}
