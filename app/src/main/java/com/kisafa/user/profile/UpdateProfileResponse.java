package com.kisafa.user.profile;

import java.io.Serializable;

import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.request.ShMeta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProfileResponse extends ServerResponse implements Serializable {

	@SerializedName("sh_result")
	@Expose
	private ShUpdateProfileResult shResult;
	@SerializedName("sh_meta")
	@Expose
	private ShMeta shMeta;

	/**
	 * @return The shResult
	 */
	public ShUpdateProfileResult getShResult() {
		return shResult;
	}

	/**
	 * @param shResult
	 *            The sh_result
	 */
	public void setShResult(ShUpdateProfileResult shResult) {
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
