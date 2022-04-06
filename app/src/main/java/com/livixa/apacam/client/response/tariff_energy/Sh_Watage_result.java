package com.livixa.apacam.client.response.tariff_energy;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sh_Watage_result implements Serializable 
{

	@SerializedName("sh_total_wattage")
	@Expose
	private String sh_total_wattage;
	
	
	@SerializedName("sh_wattage_unit")
	@Expose
	private String sh_wattage_unit;
	
	
	@SerializedName("sh_price_unit")
	@Expose
	private String sh_price_unit;
	
	
	@SerializedName("sh_total_price")
	@Expose
	private String sh_total_price;
	
	@SerializedName("sh_rooms")
	@Expose
	private ArrayList<Sh_Room_Watage_result> sh_roomsList;
	
	public String getSh_total_wattage() {
		return sh_total_wattage;
	}

	public void setSh_total_wattage(String sh_total_wattage) {
		this.sh_total_wattage = sh_total_wattage;
	}

	public String getSh_wattage_unit() {
		return sh_wattage_unit;
	}

	public void setSh_wattage_unit(String sh_wattage_unit) {
		this.sh_wattage_unit = sh_wattage_unit;
	}

	public String getSh_price_unit() {
		return sh_price_unit;
	}

	public void setSh_price_unit(String sh_price_unit) {
		this.sh_price_unit = sh_price_unit;
	}

	public String getSh_total_price() {
		return sh_total_price;
	}

	public void setSh_total_price(String sh_total_price) {
		this.sh_total_price = sh_total_price;
	}

	public ArrayList<Sh_Room_Watage_result> getSh_rooms() {
		return sh_roomsList;
	}

	public void setSh_rooms(ArrayList<Sh_Room_Watage_result> sh_roomsList) {
		this.sh_roomsList = sh_roomsList;
	}
	
	
	
}
