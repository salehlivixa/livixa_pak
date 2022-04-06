package com.livixa.apacam.client.response.isdatasyncedwithserver;

import java.io.Serializable;

import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShCamera implements Serializable{
	
	
	
	@SerializedName( "camera_id")
	@Expose
	private String cameraId;
	
		@SerializedName( "alarm")
		@Expose
		private String alarm;
		
		@SerializedName( "authority")
		@Expose
		private String authority;
		
		/*@SerializedName("user_id")
		@Expose
		private String userId;*/
		
		@SerializedName( "did")
		@Expose
		private String did;
		
		@SerializedName( "is_add")
		@Expose
		private String isAdd;
		
		/*@SerializedName( "isSyncedWithServer")
		@Expose
		private String isSyncedWithServer;*/
		
		
		
		@SerializedName( "model")
		@Expose
		private String model;
		
		@SerializedName( "name")
		@Expose
		private String name;
		
		@SerializedName( "password")
		@Expose
		private String password;
		
		@SerializedName( "pppp_mode")
		@Expose
		private String ppppMood;
		
		@SerializedName( "pppp_status")
		@Expose
		private String ppppStatus;
		
		@SerializedName( "user")
		@Expose
		private String user;
		
		@SerializedName( "model_status")
		@Expose
		private String modelStatus;

		

		public String isAlarm() {
			return alarm;
		}

		public void setAlarm(String alarm) {
			this.alarm = alarm;
		}

		public String getAuthority() {
			return authority;
		}

		public void setAuthority(String authority) {
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

		public String isAdd() {
			return isAdd;
		}

		public void setAdd(String isAdd) {
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

		public String getPpppMood() {
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
	
		/*public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}
	*/
		
		public String toString()
		{
			String temp="";
			temp="cameras[0][camera_id]:"+ getCameraId()+"\n" +
			"cameras[0][alarm]:" + isAlarm() +"\n"+
			"cameras[0][authority]:" + getAuthority()+"\n" +
			"cameras[0][did]:"+ getDid()+"\n" +
			"cameras[0][is_add]:" + isAdd()+"\n"+
			"cameras[0][model]:mode" + getModel()+"\n" +
			"cameras[0][name]:"+getName() +"\n"+
			"cameras[0][password]:"+getPassword()+"\n" +
			"cameras[0][pppp_mode]:" + getPpppMood()+"\n" +
			"cameras[0][pppp_status]:"+ getPpppStatus()+"\n" +
			"cameras[0][user]:"+ getUser()+"\n" +
			"cameras[0][model_status]:" + getModelStatus();
			return temp;
			
		}
		
		

}
