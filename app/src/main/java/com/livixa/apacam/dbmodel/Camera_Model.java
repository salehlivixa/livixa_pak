package com.livixa.apacam.dbmodel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

public class Camera_Model extends Model
{
	
	

	@Column(name = "alarm")
	public
	String alarm="1";
	
	@Column(name = "authority")
	public
	String authority="0";
	
	
	@Column(name = "cameraId")
	public
	String cameraId="";
	
	
	@Column(name = "did")
	public
	String did="";
	
	
	@Column(name = "isAdd")
	public
	String isAdd="0";
	
	
	@Column(name = "isSyncedWithServer")
	public
	String isSyncedWithServer="";
	
	@Column(name = "modelStatus")
	public
	String modelStatus="";
	
	
	@Column(name = "model")
	public
	String model="";
	
	@Column(name = "name")
	public
	String name="";
	
	@Column(name = "password")
	public
	String password="";
	
	
	@Column(name = "ppppMood")
	public
	String  ppppMood="";
	
	
	@Column(name = "ppppStatus")
	public
	String ppppStatus="";
	
	@Column(name = "user")
	public
	String user="";
	
	@Column(name = "userId")
	public
	String userId="";
	
}
