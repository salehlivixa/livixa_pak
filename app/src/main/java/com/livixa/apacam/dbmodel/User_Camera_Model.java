package com.livixa.apacam.dbmodel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

public class User_Camera_Model extends Model
{
	
	

	@Column(name = "cameraId")
	public
	String cameraId="";
	
	@Column(name = "isAccessCameraSetting")
	public
	String isAccessCameraSetting="";
	
	@Column(name = "isSyncedWithServer")
	public
	String isSyncedWithServer="";
	
	@Column(name = "modelStatus")
	public
	String modelStatus="";
	
	@Column(name = "userCameraId")
	public
	String userCameraId="";
	
	@Column(name = "userId")
	public
	String userId="";
	
	
}
