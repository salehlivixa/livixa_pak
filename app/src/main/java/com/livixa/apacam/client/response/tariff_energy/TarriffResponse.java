package com.livixa.apacam.client.response.tariff_energy;

import java.io.Serializable;

import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.request.ShMeta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TarriffResponse extends ServerResponse implements Serializable {

	@SerializedName("sh_meta")
	@Expose
	private ShMeta shMeta;
	@SerializedName("sh_result")
	@Expose
	private ShTariffResult shResult;

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
	public ShTariffResult getShResult() {
		return shResult;
	}

	/**
	 * @param shResult
	 *            The sh_result
	 */
	public void setShResult(ShTariffResult shResult) {
		this.shResult = shResult;
	}

}
