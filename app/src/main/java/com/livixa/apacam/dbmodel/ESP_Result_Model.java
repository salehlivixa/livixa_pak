package com.livixa.apacam.dbmodel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

public class ESP_Result_Model extends Model {
	
	
	
	@Column(name = "isSuccessfull")
	public
	String isSuccessfull=""; 
	
	@Column(name = "isCanceled")
	public
	String isCanceled=""; 

	@Column(name = "BSSID")
	public
	String BSSID=""; 
	
	@Column(name = "IpAddress")
	public
	String IpAddress="";
	
	@Column(name = "hostName")
	public
	String hostName="";
	
}
