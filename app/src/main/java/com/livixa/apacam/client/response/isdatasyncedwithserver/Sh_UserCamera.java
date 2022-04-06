package com.livixa.apacam.client.response.isdatasyncedwithserver;

import java.io.Serializable;

import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sh_UserCamera implements Serializable
{

	@SerializedName("sh_user_camera_id")
	@Expose
	private String userCameraId;
	
	

	@SerializedName("sh_user_id")
	@Expose
	private String userId;
	
	
	@SerializedName("sh_camera_id")
	@Expose
	private String cameraId;
	
	@SerializedName("sh_is_access_camera_settings")
	@Expose
	private String isAccessCameraSetting;
	
	/*@SerializedName("isSyncedWithServer")
	@Expose
	private String isSyncedWithServer;*/
	
	@SerializedName("sh_model_status")
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
	}*/

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
	
}
