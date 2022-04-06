package com.livixa.apacam.dbmodel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

public class Switch_Model extends Model
{

	@Column(name = "switch_id")
	public
	String switch_id="";
	
	

	@Column(name = "title")
	public
	String title="";
	
	
	@Column(name = "type")
	public
	String type="";
	
	
	@Column(name = "mac_address")
	public
	String mac_address="";
	
	
	@Column(name = "ip_address")
	public
	String ip_address="";
	
	
	@Column(name = "is_connected")
	public
	String is_connected="";
	
	@Column(name = "is_activated")
	public
	String is_activated="";
	
	@Column(name = "room_id")
	public
	String room_id="";
	
	@Column(name = "user_id")
	public
	String user_id="";
	
	@Column(name = "port")
	public
	String port="";
	
	@Column(name = "model_status")
	public
	String model_status="";
	
	@Column(name = "isSyncedWithServer")
	public
	String isSyncedWithServer="";
	
	@Column(name = "dimmingValue1")
	public
	String dimmingValue1="";
	
	@Column(name = "dimmingValue2")
	public
	String dimmingValue2="";
	
	@Column(name = "dimmingValue3")
	public
	String dimmingValue3="";
	
	
	
}

