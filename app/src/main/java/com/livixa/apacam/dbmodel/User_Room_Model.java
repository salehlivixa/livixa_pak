package com.livixa.apacam.dbmodel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

public class User_Room_Model extends Model
{
	
	

	@Column(name = "room_id")
	public
	String room_id="";
	
	@Column(name = "isSyncedWithServer")
	public
	String isSyncedWithServer="";
	
	@Column(name = "modelStatus")
	public
	String modelStatus="";
	
	@Column(name = "user_room_id")
	public
	String user_room_id="";
	
	@Column(name = "userId")
	public
	String userId="";
	

	
}
