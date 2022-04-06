package com.livixa.apacam.client.response.isdatasyncedwithserver;

import java.io.Serializable;

import com.livixa.apacam.client.response.ServerResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Association_Response extends ServerResponse implements Serializable {

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

	/**
	 * @return The shResult
	 */
	

}
