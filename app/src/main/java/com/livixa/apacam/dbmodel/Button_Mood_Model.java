package com.livixa.apacam.dbmodel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

public class Button_Mood_Model extends Model{

	
	
	
	@Column(name = "button_mood_id")
	public
	String button_mood_id="";
	
	
	@Column(name = "button_id")
	public
	String button_id="";
	
	@Column(name = "mood_id")
	public
	String mood_id="";
	
	@Column(name = "on_duration")
	public
	String on_duration="";
	
	
	@Column(name = "off_duration")
	public
	String off_duration="";
	
	
	@Column(name = "button_on_off_state")
	public
	String button_on_off_state="";
	
	
	@Column(name = "model_status")
	public
	String model_status="";
	
	
	@Column(name = "isSyncedWithServer")
	public
	String isSyncedWithServer="";
	
}
