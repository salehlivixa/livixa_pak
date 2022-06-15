package com.livixa.apacam.client.response.isdatasyncedwithserver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Sh_Order implements Serializable {

    @SerializedName( "sh_created_at")
    @Expose
    private String sh_created_at;

    @SerializedName( "sh_updated_at")
    @Expose
    private String sh_updated_at;

    @SerializedName( "sh_order_details")
    @Expose
    private ArrayList<Sh_Order_Detail> sh_order_details;

    public String getSh_created_at() {
        return sh_created_at;
    }

    public void setSh_created_at(String sh_created_at) {
        this.sh_created_at = sh_created_at;
    }

    public String getSh_updated_at() {
        return sh_updated_at;
    }

    public void setSh_updated_at(String sh_updated_at) {
        this.sh_updated_at = sh_updated_at;
    }

    public ArrayList<Sh_Order_Detail> getSh_order_details() {
        return sh_order_details;
    }

    public void setSh_order_details(ArrayList<Sh_Order_Detail> sh_order_details) {
        this.sh_order_details = sh_order_details;
    }
}
