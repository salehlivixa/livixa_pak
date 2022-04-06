package com.livixa.apacam.dbmodel;



import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

public class Room_Model extends Model
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
	
	@Column(name = "title")
	public
	String title="";
	
	@Column(name = "isPictureSyncedWithServer")
	public
	String isPictureSyncedWithServer="";
	
	
	@Column(name = "pictureURL")
	public
	String pictureURL="";
	
	@Column(name = "picture")
	public
	byte[]  picture;
	
	
	
	
	
}
