package com.livixa.apacam.dbmodel;

import java.sql.Date;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

public class Mood_Model extends Model
{
	
	
	

	
	
	@Column(name = "awayEndTime")
	public
	String awayEndTime=null;   
	
	
	//@Column(name = "awayStartTime")
	public
	String awayStartTime=null; 
	
	
	@Column(name = "awayOffDuration")
	public
	String awayOffDuration=""; 
	
	
	@Column(name = "awayOnDuration")
	public
	String awayOnDuration=""; 
	
	
	@Column(name = "isPictureSyncedWithServer")
	public
	String isPictureSyncedWithServer=""; 
	
	
	@Column(name = "isRepeatOn")
	public
	String isRepeatOn=""; 
	
	
	@Column(name = "isSyncedWithServer")
	public
	String isSyncedWithServer=""; 
	
	@Column(name = "modelStatus")
	public
	String modelStatus=""; 
	
	
	@Column(name = "moodId")
	public
	String moodId=""; 
	
	@Column(name = "moodIdentifer")
	public
	int moodIdentifer=0;
	
	@Column(name = "picture")
	public
	byte[] picture=null;
	
	@Column(name = "pictureURL")
	public
	String pictureURL="";
	
	@Column(name = "switchId")
	public
	String switchId="";
	
	@Column(name = "time")
	public
	String time=null;
	
	@Column(name = "title")
	public
	String title="";
	
	/*@Column(name = "isButtonOneAssigned")
	public
	boolean isButtonOneAssigned=false;
	
	@Column(name = "isButtonTwoAssigned")
	public
	boolean isButtonTwoAssigned=false;
	
	
	@Column(name = "isButtonThreeAssigned")
	public
	boolean isButtonThreeAssigned=false;*/
	
	
	
}
