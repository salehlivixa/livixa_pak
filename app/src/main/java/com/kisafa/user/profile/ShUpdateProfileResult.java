package com.kisafa.user.profile;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShUpdateProfileResult implements Serializable {

	
	
	
	@SerializedName("sh_full_name")
	@Expose
	private String shFullName;
	@SerializedName("sh_phone_number")
	@Expose
	private String shPhoneNumber;
	
	@SerializedName("sh_email")
	@Expose
	private String shEmail;
	@SerializedName("sh_username")
	@Expose
	private String shUsername;
	
	@SerializedName("sh_profile_image")
	@Expose
	private String shProfileImage;
	
	@SerializedName("sh_country_name")
	@Expose
	private String sh_country_name;
	
	@SerializedName("sh_country_code")
	@Expose
	private String sh_country_code;
	
	@SerializedName("sh_time_zone")
	@Expose
	private String sh_time_zone;
	
	@SerializedName("sh_time_zone_name")
	@Expose
	private String sh_time_zone_name;
	
	@SerializedName("sh_city")
	@Expose
	private String sh_city;
	
	
	
	
	
	public String getShFullName() {
		return shFullName;
	}

	public void setShFullName(String shFullName) {
		this.shFullName = shFullName;
	}

	public String getShPhoneNumber() {
		return shPhoneNumber;
	}

	public void setShPhoneNumber(String shPhoneNumber) {
		this.shPhoneNumber = shPhoneNumber;
	}

	public String getShEmail() {
		return shEmail;
	}

	public void setShEmail(String shEmail) {
		this.shEmail = shEmail;
	}

	public String getShUsername() {
		return shUsername;
	}

	public void setShUsername(String shUsername) {
		this.shUsername = shUsername;
	}

	public String getShProfileImage() {
		return shProfileImage;
	}

	public void setShProfileImage(String shProfileImage) {
		this.shProfileImage = shProfileImage;
	}

	public String getSh_country_name() {
		return sh_country_name;
	}

	public void setSh_country_name(String sh_country_name) {
		this.sh_country_name = sh_country_name;
	}

	public String getSh_country_code() {
		return sh_country_code;
	}

	public void setSh_country_code(String sh_country_code) {
		this.sh_country_code = sh_country_code;
	}

	public String getSh_time_zone() {
		return sh_time_zone;
	}

	public void setSh_time_zone(String sh_time_zone) {
		this.sh_time_zone = sh_time_zone;
	}

	public String getSh_time_zone_name() {
		return sh_time_zone_name;
	}

	public void setSh_time_zone_name(String sh_time_zone_name) {
		this.sh_time_zone_name = sh_time_zone_name;
	}

	public String getSh_city() {
		return sh_city;
	}

	public void setSh_city(String sh_city) {
		this.sh_city = sh_city;
	}
	

}
