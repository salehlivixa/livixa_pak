package com.livixa.apacam.client.response.isdatasyncedwithserver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Sh_Order_Detail implements Serializable {

    public ArrayList<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ArrayList<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @SerializedName( "subscription")
    @Expose
    private ArrayList<Subscription> subscriptions;



}
