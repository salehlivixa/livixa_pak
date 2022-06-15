package com.livixa.apacam.client.response.isdatasyncedwithserver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Subscription implements Serializable {

    @SerializedName( "id")
    @Expose
    private String id;

    @SerializedName( "subscription_eng")
    @Expose
    private String subscription_eng;

    @SerializedName( "subscription_arabic")
    @Expose
    private String subscription_arabic;


    @SerializedName( "cart_id")
    @Expose
    private String cart_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubscription_eng() {
        return subscription_eng;
    }

    public void setSubscription_eng(String subscription_eng) {
        this.subscription_eng = subscription_eng;
    }

    public String getSubscription_arabic() {
        return subscription_arabic;
    }

    public void setSubscription_arabic(String subscription_arabic) {
        this.subscription_arabic = subscription_arabic;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }
}
