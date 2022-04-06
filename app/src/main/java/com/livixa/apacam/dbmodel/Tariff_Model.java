package com.livixa.apacam.dbmodel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

public class Tariff_Model extends Model
{
	
	

	@Column(name = "tariff_id")
	public
	String tariff_id="";
	

	@Column(name = "lower_limit")
	public
	String lower_limit="";
	
	
	@Column(name = "upper_limit")
	public
	String upper_limit="";
	
	@Column(name = "price")
	public
	String price="";
	
	@Column(name = "user_id")
	public
	String user_id="";
	
	@Column(name = "model_status")
	public
	String model_status="";
	
	@Column(name = "isSyncedWithServer")
	public
	String isSyncedWithServer="";
	
	
	
	
}
