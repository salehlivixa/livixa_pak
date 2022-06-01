package com.livixa.apacam.client.response.notification;

import com.livixa.apacam.client.response.ServerResponse;

import com.livixa.apacam.client.response.notification.ShResult;
import com.livixa.apacam.client.response.request.ShMeta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NotificatioinResponse extends ServerResponse implements Serializable {


    @SerializedName("sh_meta")
    @Expose
    private ShMeta shMeta;

    @SerializedName("sh_result")
    @Expose
    private ShResult shResult;

    public ShResult getShResult() {
        return shResult;
    }

    public void setShResult(ShResult shResult) {
        this.shResult = shResult;
    }

    /**
     * @return The shMeta
     */
    public ShMeta getShMeta() {
        return shMeta;
    }

    /**
     * @param shMeta
     *            The sh_meta
     */
    public void setShMeta(ShMeta shMeta) {
        this.shMeta = shMeta;
    }
}
