package com.livixa.apacam.client.response.tariff_energy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.request.ShMeta;

import java.io.Serializable;

public class TariffAlertResponse extends ServerResponse implements Serializable {


    @SerializedName("sh_meta")
    @Expose
    private ShMeta shMeta;


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
