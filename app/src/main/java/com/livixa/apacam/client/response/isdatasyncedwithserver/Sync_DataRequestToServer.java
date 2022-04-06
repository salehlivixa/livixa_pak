package com.livixa.apacam.client.response.isdatasyncedwithserver;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Sync_DataRequestToServer implements Serializable{

	@SerializedName("session")  
	
	private String session="";
	
	@SerializedName("rooms") 
	@Expose  
	private ArrayList<String> rooms=new ArrayList<String>();
	
	@SerializedName("user_rooms")
	@Expose  
	private ArrayList<String> user_rooms=new ArrayList<String>();
	
	
	
	@SerializedName("cameras")
	
	private ArrayList<ShCamera> camList;
	
	@SerializedName("user_cameras") 
	  
	private ArrayList<ShUserCamera> user_camerasList;
	
	
	
	@SerializedName("switches")
	@Expose 
	private ArrayList<String> switches=new ArrayList<String>();;
	@SerializedName("buttons")
	@Expose 
	private ArrayList<String> buttons=new ArrayList<String>();;
	@SerializedName("moods") 
	@Expose  
	private ArrayList<String> moods=new ArrayList<String>();
	@SerializedName("button_moods")
	@Expose
	private ArrayList<String> button_moods=new ArrayList<String>();
	
   
	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public ArrayList<String> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<String> rooms) {
		this.rooms = rooms;
	}

	public ArrayList<String> getUser_rooms() {
		return user_rooms;
	}

	public void setUser_rooms(ArrayList<String> user_rooms) {
		this.user_rooms = user_rooms;
	}

	public ArrayList<ShCamera> getCamList() {
		return camList;
	}

	public void setCamList(ArrayList<ShCamera> camList) {
		this.camList = camList;
	}

	public ArrayList<ShUserCamera> getUser_camerasList() {
		return user_camerasList;
	}

	public void setUser_camerasList(ArrayList<ShUserCamera> user_camerasList) {
		this.user_camerasList = user_camerasList;
	}

	public ArrayList<String> getSwitches() {
		return switches;
	}

	public void setSwitches(ArrayList<String> switches) {
		this.switches = switches;
	}

	public ArrayList<String> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<String> buttons) {
		this.buttons = buttons;
	}

	public ArrayList<String> getMoods() {
		return moods;
	}

	public void setMoods(ArrayList<String> moods) {
		this.moods = moods;
	}

	public ArrayList<String> getButton_moods() {
		return button_moods;
	}

	public void setButton_moods(ArrayList<String> button_moods) {
		this.button_moods = button_moods;
	}
}
