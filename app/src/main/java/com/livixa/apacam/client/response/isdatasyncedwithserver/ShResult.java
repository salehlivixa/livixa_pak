package com.livixa.apacam.client.response.isdatasyncedwithserver;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.livixa.apacam.client.response.tariff_energy.ShTariffResult;

public class ShResult implements Serializable {

	
	
	@SerializedName("sh_cameras")
	@Expose
	private ArrayList<Sh_Camera> shCameras;
	
	@SerializedName("sh_user_cameras")
	@Expose
	private ArrayList<Sh_UserCamera> shUserCamera;
	
	
	@SerializedName("sh_switches")
	@Expose
	private ArrayList<Sh_Switch> shSwitchList;
	
	
	@SerializedName("sh_rooms")
	@Expose
	private ArrayList<Sh_Room> shRoomList;

	
	@SerializedName("sh_user_rooms")
	@Expose
	private ArrayList<Sh_User_Room> shUserRoomList;
	
	
	@SerializedName("sh_tariffs")
	@Expose
	private ArrayList<ShTariffResult> shtariffList;
	
	
	@SerializedName("sh_moods")
	@Expose
	private ArrayList<Sh_Mood> shMoodsList;
	

	public ArrayList<Sh_Camera> getShCameras() {
		return shCameras;
	}

	public void setShCameras(ArrayList<Sh_Camera> shCameras) {
		this.shCameras = shCameras;
	}

	public ArrayList<Sh_UserCamera> getShUserCamera() {
		return shUserCamera;
	}

	public void setShUserCamera(ArrayList<Sh_UserCamera> shUserCamera) {
		this.shUserCamera = shUserCamera;
	}

	public ArrayList<Sh_Switch> getShSwitchList() {
		return shSwitchList;
	}

	public void setShSwitchList(ArrayList<Sh_Switch> shSwitchList) {
		this.shSwitchList = shSwitchList;
	}

	public ArrayList<Sh_Room> getShRoomList() {
		return shRoomList;
	}

	public void setShRoomList(ArrayList<Sh_Room> shRoomList) {
		this.shRoomList = shRoomList;
	}

	public ArrayList<Sh_User_Room> getShUserRoomList() {
		return shUserRoomList;
	}

	public void setShUserRoomList(ArrayList<Sh_User_Room> shUserRoomList) {
		this.shUserRoomList = shUserRoomList;
	}
	
	public ArrayList<ShTariffResult> getSHTariffList() {
		return shtariffList;
	}

	public void setSHTariffList(ArrayList<ShTariffResult> shUserRoomList) {
		this.shtariffList = shUserRoomList;
	}
	
	public ArrayList<Sh_Mood> getSHMoodList() {
		return shMoodsList;
	}

	public void setSHMoodList(ArrayList<Sh_Mood> shMoodsList) {
		this.shMoodsList = shMoodsList;
	}
	
	
	
	
}
