package com.livixa.apacam.client.response.request;

import java.io.Serializable;

import com.livixa.apacam.client.response.ServerResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestResponse extends ServerResponse implements Serializable {

	@SerializedName("sh_meta")
	@Expose
	private ShMeta shMeta;
	@SerializedName("sh_result")
	@Expose
	private Object shResult;

	/**
	 * 
	 * @return The shMeta
	 */
	public ShMeta getShMeta() {
		return shMeta;
	}

	/**
	 * 
	 * @param shMeta
	 *            The sh_meta
	 */
	public void setShMeta(ShMeta shMeta) {
		this.shMeta = shMeta;
	}

	/**
	 * 
	 * @return The shResult
	 */
	public Object getShResult() {
		return shResult;
	}

	/**
	 * 
	 * @param shResult
	 *            The sh_result
	 */
	public void setShResult(Object shResult) {
		this.shResult = shResult;
	}

}
