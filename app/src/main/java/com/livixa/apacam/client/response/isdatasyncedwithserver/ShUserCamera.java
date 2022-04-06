package com.livixa.apacam.client.response.isdatasyncedwithserver;

import java.io.Serializable;

import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShUserCamera implements Serializable
{

	
	
	@SerializedName("user_camera_id")
	@Expose
	private String userCameraId;
	
	

	@SerializedName("user_id")
	@Expose
	private String userId;
	
	
	@SerializedName("camera_id")
	@Expose
	private String cameraId;
	
	@SerializedName("is_access_camera_settings")
	@Expose
	private String isAccessCameraSetting;
	
	/*@SerializedName("isSyncedWithServer")
	@Expose
	private String isSyncedWithServer;*/
	
	@SerializedName("model_status")
	@Expose
	private String modelStatus;
	
	
	
	public String getCameraId() {
		return cameraId;
	}

	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}

	public String getIsAccessCameraSetting() {
		return isAccessCameraSetting;
	}

	public void setIsAccessCameraSetting(String isAccessCameraSetting) {
		this.isAccessCameraSetting = isAccessCameraSetting;
	}

	/*public String getIsSyncedWithServer() {
		return isSyncedWithServer;
	}

	public void setIsSyncedWithServer(String isSyncedWithServer) {
		this.isSyncedWithServer = isSyncedWithServer;
	}
*/
	public String getModelStatus() {
		return modelStatus;
	}

	public void setModelStatus(String modelStatus) {
		this.modelStatus = modelStatus;
	}

	public String getUserCameraId() {
		return userCameraId;
	}

	public void setUserCameraId(String userCameraId) {
		this.userCameraId = userCameraId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String toString()
	{
		String temp="";
		temp="user_cameras[0][user_camera_id]:"+ getUserCameraId()+"\n" +
		"user_cameras[0][user_id]:" + getUserId() +"\n"+
		"user_cameras[0][camera_id]:" + getCameraId()+"\n"+
		"user_cameras[0][is_access_camera_settings]:"+ getIsAccessCameraSetting() +"\n"+
		"user_cameras[0][model_status]:" + getModelStatus();
		
		
		
		return temp;
		
	}
}
