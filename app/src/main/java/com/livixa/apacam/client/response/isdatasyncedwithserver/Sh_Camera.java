package com.livixa.apacam.client.response.isdatasyncedwithserver;

import java.io.Serializable;

import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sh_Camera implements Serializable{
	
	
	
		
	@SerializedName( "sh_alarm")
	@Expose
	private String alarm;
	
	@SerializedName( "sh_authority")
	@Expose
	private int authority;
	
	@SerializedName( "sh_camera_id")
	
	private String cameraId;
	
	@SerializedName( "sh_did")
	@Expose
	private String did;
	
	@SerializedName( "sh_is_add")
	@Expose
	private boolean isAdd;
	
	/*@SerializedName( "isSyncedWithServer")
	@Expose
	private String isSyncedWithServer;*/
	
	
	
	@SerializedName( "sh_model")
	@Expose
	private String model;
	
	@SerializedName( "sh_name")
	@Expose
	private String name;
	
	@SerializedName( "sh_password")
	@Expose
	private String password;
	
	@SerializedName( "sh_pppp_mode")
	@Expose
	private String ppppMood;
	
	@SerializedName( "sh_pppp_status")
	@Expose
	private String ppppStatus;
	
	@SerializedName( "sh_user")
	@Expose
	private String user;
	
	@SerializedName( "sh_model_status")
	@Expose
	private String modelStatus;
	
	

		

		public String isAlarm() {
			return alarm;
		}

		public void setAlarm(String alarm) {
			this.alarm = alarm;
		}

		public int getAuthority() {
			return authority;
		}

		public void setAuthority(int authority) {
			this.authority = authority;
		}

		public String getCameraId() {
			return cameraId;
		}

		public void setCameraId(String cameraId) {
			this.cameraId = cameraId;
		}

		public String getDid() {
			return did;
		}

		public void setDid(String did) {
			this.did = did;
		}

		public boolean isAdd() {
			return isAdd;
		}

		public void setAdd(boolean isAdd) {
			this.isAdd = isAdd;
		}

		/*public String getIsSyncedWithServer() {
			return isSyncedWithServer;
		}

		public void setIsSyncedWithServer(String isSyncedWithServer) {
			this.isSyncedWithServer = isSyncedWithServer;
		}*/

		public String getModelStatus() {
			return modelStatus;
		}

		public void setModelStatus(String modelStatus) {
			this.modelStatus = modelStatus;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String  getPpppMood() {
			return ppppMood;
		}

		public void setPpppMood(String ppppMood) {
			this.ppppMood = ppppMood;
		}

		public String getPpppStatus() {
			return ppppStatus;
		}

		public void setPpppStatus(String ppppStatus) {
			this.ppppStatus = ppppStatus;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}
	

	

}
