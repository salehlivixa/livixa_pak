package com.livixa.apacam.client.response.isdatasyncedwithserver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Sh_Order implements Serializable {

    @SerializedName( "sh_created_at")
    @Expose
    private String sh_created_at;

    @SerializedName( "sh_subscription_end")
    @Expose
    private String sh_subscription_end;

    @SerializedName( "sh_order_details")
    @Expose
    private ArrayList<Sh_Order_Detail> sh_order_details;

    public String getSh_created_at() {
        return sh_created_at;
    }

    public void setSh_created_at(String sh_created_at) {
        this.sh_created_at = sh_created_at;
    }

    public String getSh_subscription_end() {
        return sh_subscription_end;
    }

    public void setSh_subscription_end(String sh_subscription_end) {
        this.sh_subscription_end = sh_subscription_end;
    }

    public ArrayList<Sh_Order_Detail> getSh_order_details() {
        return sh_order_details;
    }

    public void setSh_order_details(ArrayList<Sh_Order_Detail> sh_order_details) {
        this.sh_order_details = sh_order_details;
    }
}
