package com.livixa.apacam.client.response.deletion;

import java.io.Serializable;

import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.request.ShMeta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeletionResponse extends ServerResponse implements Serializable {


	@SerializedName("sh_meta")
	@Expose
	private ShMeta shMeta;

	
	public ShMeta getShMeta() {
		return shMeta;
	}

	
	public void setShMeta(ShMeta shMeta) {
		this.shMeta = shMeta;
	}

}
