package com.kisafa.user.profile;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.livixa.apacam.client.response.login.SUB_USER_DB;

@Table(name = "USER_Model")



public class USER_Model extends Model
{
	
	public static String USER_TEMP_ID="-1";

	@Column(name = "sh_user_id")
	public
	String sh_user_id="";
	
	@Column(name = "profile_image")
	public
	byte [] profile_image =null;    
	
	
	@Column(name = "profile_url")
	public
	String profile_url =null; 
	
	
	
	
	public static USER_Model GetUser()
	{
		
		USER_Model um=null;
		
		try
		   {
		   return um = new Select().from(USER_Model.class).where("sh_user_id = ?",USER_TEMP_ID ).executeSingle();
		   }catch(Exception ex)
		   {
			   ex.toString();
		   }
		
		
		 return um;
		
		
	}
	
	 public static SUB_USER_DB searchSubUserInDb(String subUserId)
	   {
		   SUB_USER_DB _sUB_USER_DB=null;
		   
		   try
		   {
		   return _sUB_USER_DB = new Select().from(SUB_USER_DB.class).where("sh_user_id = ?",subUserId ).executeSingle();
		   }catch(Exception ex)
		   {
			   
		   }
		   return _sUB_USER_DB;
	   }
	
}
