package com.livixa.apacam.client.response.tariff_energy;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sh_Room_Watage_result implements Serializable 
{

	@SerializedName("sh_room_id")
	@Expose
	private String sh_room_id;
	
	
	@SerializedName("sh_title")
	@Expose
	private String sh_title;
	
	
	@SerializedName("sh_picture_url")
	@Expose
	private String sh_picture_url;
	
	
	@SerializedName("sh_model_status")
	@Expose
	private String sh_model_status;
	
	@SerializedName("sh_wattage")
	@Expose
	private String sh_wattage;
	
	@SerializedName("sh_price")
	@Expose
	private String sh_price;
	
	
	@SerializedName("sh_switches")
	@Expose
	private ArrayList<Sh_Switch_Watage_result> sh_switchesList;
	
	
	
	public String getSh_room_id() {
		return sh_room_id;
	}

	public void setSh_room_id(String sh_room_id) {
		this.sh_room_id = sh_room_id;
	}

	public String getSh_title() {
		return sh_title;
	}

	public void setSh_title(String sh_title) {
		this.sh_title = sh_title;
	}

	public String getSh_picture_url() {
		return sh_picture_url;
	}

	public void setSh_picture_url(String sh_picture_url) {
		this.sh_picture_url = sh_picture_url;
	}

	public String getSh_model_status() {
		return sh_model_status;
	}

	public void setSh_model_status(String sh_model_status) {
		this.sh_model_status = sh_model_status;
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

	public ArrayList<Sh_Switch_Watage_result> getSh_switches() {
		return sh_switchesList;
	}

	public void setSh_switches(ArrayList<Sh_Switch_Watage_result> sh_switchesList) {
		this.sh_switchesList = sh_switchesList;
	}
	
	
}
