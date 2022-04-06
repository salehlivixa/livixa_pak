package com.livixa.apacam.client.response.login;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class _SubUser implements Serializable
{
	
	@Expose
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
	private String shSUB_User_Phone_Number;
	
	
	
	
	
	public String getShSUB_User_ID() {
		return shSUB_User_ID;
	}

	public void setShSUB_User_ID(String shSUB_User_ID) {
		this.shSUB_User_ID = shSUB_User_ID;
	}

	public String getShSUB_User_Email() {
		return shSUB_User_Email;
	}

	public void setShSUB_User_Email(String shSUB_User_Email) {
		this.shSUB_User_Email = shSUB_User_Email;
	}

	public String getShSUB_User_Name() {
		return shSUB_User_Name;
	}

	public void setShSUB_User_Name(String shSUB_User_Name) {
		this.shSUB_User_Name = shSUB_User_Name;
	}

	public String getShSUB_User_TRUE() {
		return shSUB_User_TRUE;
	}

	public void setShSUB_User_TRUE(String shSUB_User_TRUE) {
		this.shSUB_User_TRUE = shSUB_User_TRUE;
	}

	public String getShSUB_User_Created_BY() {
		return shSUB_User_Created_BY;
	}

	public void setShSUB_User_Created_BY(String shSUB_User_Created_BY) {
		this.shSUB_User_Created_BY = shSUB_User_Created_BY;
	}

	public String getShSUB_User_Full_Name() {
		return shSUB_User_Full_Name;
	}

	public void setShSUB_User_Full_Name(String shSUB_User_Full_Name) {
		this.shSUB_User_Full_Name = shSUB_User_Full_Name;
	}

	public String getShSUB_User_Profile_Image() {
		return shSUB_User_Profile_Image;
	}

	public void setShSUB_User_Profile_Image(String shSUB_User_Profile_Image) {
		this.shSUB_User_Profile_Image = shSUB_User_Profile_Image;
	}

	public String getShSUB_User_Phone_Number() {
		return shSUB_User_Phone_Number;
	}

	public void setShSUB_User_Phone_Number(String shSUB_User_Phone_Number) {
		this.shSUB_User_Phone_Number = shSUB_User_Phone_Number;
	}
	
}

