package com.livixa.apacam.client.response.isdatasyncedwithserver;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.livixa.apacam.client.response.ServerResponse;



public class Uploaded_Pictures_Response extends ServerResponse implements Serializable 
{

	@SerializedName("sh_meta")
	@Expose
	private ShMeta shMeta;
	@SerializedName("sh_result")
	@Expose
	private ShResult_Uploaded_Images shResult;

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
	public ShResult_Uploaded_Images getShResult() {
		return shResult;
	}

	/**
	 * @param shResult
	 *            The sh_result
	 */
	public void setShResult(ShResult_Uploaded_Images shResult) {
		this.shResult = shResult;
	}

	
}
