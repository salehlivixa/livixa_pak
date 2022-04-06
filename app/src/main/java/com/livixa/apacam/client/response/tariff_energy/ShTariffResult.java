package com.livixa.apacam.client.response.tariff_energy;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShTariffResult implements Serializable 
{

	@SerializedName("sh_tariff_id")
	@Expose
	private String sh_tariff_id;
	
	
	@SerializedName("sh_lower_limit")
	@Expose
	private String sh_lower_limit;
	
	@SerializedName("sh_upper_limit")
	@Expose
	private String sh_upper_limit;
	
	
	@SerializedName("sh_price")
	@Expose
	private String sh_price;
	
	
	@SerializedName("sh_user_id")
	@Expose
	private String sh_user_id;
	
	@SerializedName("sh_model_status")
	@Expose
	private String sh_model_status;
	
	
	public String getSh_tariff_id() {
		return sh_tariff_id;
	}

	public void setSh_tariff_id(String sh_tariff_id) {
		this.sh_tariff_id = sh_tariff_id;
	}

	public String getSh_lower_limit() {
		return sh_lower_limit;
	}

	public void setSh_lower_limit(String sh_lower_limit) {
		this.sh_lower_limit = sh_lower_limit;
	}

	public String getSh_upper_limit() {
		return sh_upper_limit;
	}

	public void setSh_upper_limit(String sh_upper_limit) {
		this.sh_upper_limit = sh_upper_limit;
	}

	public String getSh_price() {
		return sh_price;
	}

	public void setSh_price(String sh_price) {
		this.sh_price = sh_price;
	}

	public String getSh_user_id() {
		return sh_user_id;
	}

	public void setSh_user_id(String sh_user_id) {
		this.sh_user_id = sh_user_id;
	}

	public String getSh_model_status() {
		return sh_model_status;
	}

	public void setSh_model_status(String sh_model_status) {
		this.sh_model_status = sh_model_status;
	}
	
	
	
	
}
