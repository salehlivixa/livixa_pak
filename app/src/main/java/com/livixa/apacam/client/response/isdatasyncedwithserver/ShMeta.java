package com.livixa.apacam.client.response.isdatasyncedwithserver;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShMeta implements Serializable {

	@SerializedName("sh_error_code")
	@Expose
	private String shErrorCode;
	@SerializedName("sh_message")
	@Expose
	private String shMessage;

	/**
	 * 
	 * @return The shErrorCode
	 */
	public String getShErrorCode() {
		return shErrorCode;
	}

	/**
	 * 
	 * @param shErrorCode
	 *            The sh_error_code
	 */
	public void setShErrorCode(String shErrorCode) {
		this.shErrorCode = shErrorCode;
	}

	/**
	 * 
	 * @return The shMessage
	 */
	public String getShMessage() {
		return shMessage;
	}

	/**
	 * 
	 * @param shMessage
	 *            The sh_message
	 */
	public void setShMessage(String shMessage) {
		this.shMessage = shMessage;
	}

}
