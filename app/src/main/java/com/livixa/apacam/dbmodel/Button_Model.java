package com.livixa.apacam.dbmodel;

import java.util.Arrays;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

public class Button_Model extends Model
{
	
	

	
	
	@Column(name = "button_id")
	public
	String button_id="";                   
	
	@Column(name = "on_off_state")
	public
	String  on_off_state="";                             
	
	@Column(name = "switch_id")
	public
	String switch_id="";
	
	
	
	@Column(name = "button_identifier")
	public
	String button_identifier="";
	
	
	@Column(name = "is_associated_with_Mood")
	public
	boolean  is_associated_with_Mood=false;
	
	
	
	@Column(name = "moods")
	public
	Mood_Model []  moodsArray =null;
	
	
}
