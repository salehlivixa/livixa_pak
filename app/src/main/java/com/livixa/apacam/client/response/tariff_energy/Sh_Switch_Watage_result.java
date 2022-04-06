package com.livixa.apacam.client.response.tariff_energy;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sh_Switch_Watage_result implements Serializable 
{

	@SerializedName("sh_switch_id")
	@Expose
	private String sh_switch_id;
	
	
	@SerializedName("sh_wattage")
	@Expose
	private String sh_wattage;
	
	
	@SerializedName("sh_price")
	@Expose
	private String sh_price;
	
	
	public String getSh_switch_id() {
		return sh_switch_id;
	}

	public void setSh_switch_id(String sh_switch_id) {
		this.sh_switch_id = sh_switch_id;
	}

	public String getSh_wattage() {
		return sh_wattage;
	}

	public void setSh_wattage(String sh_wattage) {
		this.sh_wattage = sh_wattage;
	}

	public String getSh_price() {
		return sh_price;
	}

	public void setSh_price(String sh_price) {
		this.sh_price = sh_price;
	}
	
	
}
