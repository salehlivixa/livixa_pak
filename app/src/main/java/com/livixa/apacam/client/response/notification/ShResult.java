package com.livixa.apacam.client.response.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.livixa.apacam.client.response.tariff_energy.Sh_Room_Watage_result;
import com.sawas.ashisuto.methods.Method;

import java.io.Serializable;
import java.util.ArrayList;

public class ShResult implements Serializable {

    @SerializedName("sh_total")
    @Expose
    private int sh_total;


    @SerializedName("sh_models")
    @Expose
    private ArrayList<NotificationObject> sh_models;


    public int getSh_total() {
        return sh_total;
    }

    public void setSh_total(int sh_total) {
        this.sh_total = sh_total;
    }

    public ArrayList<NotificationObject> getSh_models() {
        return sh_models;
    }

    public void setSh_models(ArrayList<NotificationObject> sh_models) {
        this.sh_models = sh_models;
    }
}