package com.livixa.apacam.client.response.login;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Table(name = "SUB_USER_DB")



public class SUB_USER_DB extends Model
{
	
	

	@Column(name = "sh_user_id")
	public
	String sh_user_id="";
	@Column(name = "username")
	public
	String username="";
	@Column(name = "email")
	public
	String email="";
	
	@Column(name = "is_subuser")
	public
	String is_subuser="";       // �0�, �1�
	@Column(name = "created_by_user_id")
	public
	String created_by_user_id="";      // if is_subuser is true than it contains sh_id of the user which is creating the subuser
	
	@Column(name = "device_token")
	public
	String device_token ="";             // if device token is not empty string then is_push_notification_on = �1� and �0� other wise
	@Column(name = "full_name")
	public
	String full_name ="";             // ��
	@Column(name = "phone_number")
	public
	String phone_number="";   // ��
	@Column(name = "profile_image")
	public
	String profile_image ="";     // ��
	
	/*@Expose
	@SerializedName("sh_user_id")
	private String shSUB_User_ID;
	@Expose
	@SerializedName("sh_email")
	private String shSUB_User_Email;
	@Expose
	@SerializedName("sh_username")
	private String shSUB_User_Name;
	@Expose
	@SerializedName("sh_is_subuser")
	private String shSUB_User_TRUE;
	@Expose
	@SerializedName("sh_created_by_user_id")
	private String shSUB_User_Created_BY;
	@Expose
	@SerializedName("sh_full_name")
	private String shSUB_User_Full_Name;
	@Expose
	@SerializedName("sh_profile_image")
	private String shSUB_User_Profile_Image;
	@Expose
	@SerializedName("sh_phone_number")
	private String shSUB_User_Phone_Number;*/
	
   public static SUB_USER_DB searchSubUserInDb(String subUserId)
   {
	   SUB_USER_DB _sUB_USER_DB=null;
	   
	   try
	   {
	   return _sUB_USER_DB = new Select().from(SUB_USER_DB.class).where("sh_user_id = ?",subUserId ).executeSingle();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   return _sUB_USER_DB;
   }
   
   public static boolean UpdateSubUserInDb(String subUserId)
   {
	   SUB_USER_DB _sUB_USER_DB=null;
	   
	   try
	   {
	   _sUB_USER_DB = searchSubUserInDb(subUserId);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   
	   if(_sUB_USER_DB!=null)
	   {
		   
		   
	   }
	   
	   
	   return true;
   }
}
