package com.livixa.apacam.client.response.register;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShResult implements Serializable {

	@SerializedName("sh_is_push_notification_on")
	@Expose
	private String shIsPushNotificationOn;
	@SerializedName("sh_session")
	@Expose
	private String shSession;
	@SerializedName("sh_email")
	@Expose
	private String shEmail;
	@SerializedName("sh_user_id")
	@Expose
	private String shUserId;
	@SerializedName("sh_username")
	@Expose
	private String shUsername;
	@SerializedName("sh_full_name")
	@Expose
	private String shFullName;
	@SerializedName("sh_phone_number")
	@Expose
	private String shPhoneNumber;
	
	@SerializedName("sh_is_subuser")
	@Expose
	private String shIsSubuser;
	
	
	
	@SerializedName("sh_created_by_user_id")
	@Expose
	private String shCreatedByUserId;
	
	
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
	
	
	@SerializedName("sh_wattage_unit")
	@Expose
	private String sh_wattage_unit;
	
	@SerializedName("sh_price_unit")
	@Expose
	private String sh_price_unit;
	
	@SerializedName("sh_currency")
	@Expose
	private String sh_currency;
	
	

	/**
	 * 
	 * @return The shIsPushNotificationOn
	 */
	public String getShIsPushNotificationOn() {
		return shIsPushNotificationOn;
	}

	/**
	 * 
	 * @param shIsPushNotificationOn
	 *            The sh_is_push_notification_on
	 */
	public void setShIsPushNotificationOn(String shIsPushNotificationOn) {
		this.shIsPushNotificationOn = shIsPushNotificationOn;
	}

	/**
	 * 
	 * @return The shSession
	 */
	public String getShSession() {
		return shSession;
	}

	/**
	 * 
	 * @param shSession
	 *            The sh_session
	 */
	public void setShSession(String shSession) {
		this.shSession = shSession;
	}

	/**
	 * 
	 * @return The shEmail
	 */
	public String getShEmail() {
		return shEmail;
	}

	/**
	 * 
	 * @param shEmail
	 *            The sh_email
	 */
	public void setShEmail(String shEmail) {
		this.shEmail = shEmail;
	}

	/**
	 * 
	 * @return The shUserId
	 */
	public String getShUserId() {
		return shUserId;
	}

	/**
	 * 
	 * @param shUserId
	 *            The sh_user_id
	 */
	public void setShUserId(String shUserId) {
		this.shUserId = shUserId;
	}

	/**
	 * 
	 * @return The shUsername
	 */
	public String getShUsername() {
		return shUsername;
	}

	/**
	 * 
	 * @param shUsername
	 *            The sh_username
	 */
	public void setShUsername(String shUsername) {
		this.shUsername = shUsername;
	}

	/**
	 * 
	 * @return The shFullName
	 */
	public String getShFullName() {
		return shFullName;
	}

	/**
	 * 
	 * @param shFullName
	 *            The sh_full_name
	 */
	public void setShFullName(String shFullName) {
		this.shFullName = shFullName;
	}

	/**
	 * 
	 * @return The shPhoneNumber
	 */
	public String getShPhoneNumber() {
		return shPhoneNumber;
	}

	/**
	 * 
	 * @param shPhoneNumber
	 *            The sh_phone_number
	 */
	public void setShPhoneNumber(String shPhoneNumber) {
		this.shPhoneNumber = shPhoneNumber;
	}

	/**
	 * 
	 * @return The shIsSubuser
	 */
	public String getShIsSubuser() {
		return shIsSubuser;
	}

	/**
	 * 
	 * @param shIsSubuser
	 *            The sh_is_subuser
	 */
	public void setShIsSubuser(String shIsSubuser) {
		this.shIsSubuser = shIsSubuser;
	}

	/**
	 * 
	 * @return The shCreatedByUserId
	 */
	public String getShCreatedByUserId() {
		return shCreatedByUserId;
	}

	/**
	 * 
	 * @param shCreatedByUserId
	 *            The sh_created_by_user_id
	 */
	public void setShCreatedByUserId(String shCreatedByUserId) {
		this.shCreatedByUserId = shCreatedByUserId;
	}

	/**
	 * 
	 * @return The shProfileImage
	 */
	public String getShProfileImage() {
		return shProfileImage;
	}

	/**
	 * 
	 * @param shProfileImage
	 *            The sh_profile_image
	 */
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
	
	public String getSh_sh_price_unit() {
		return sh_price_unit;
	}

	public void setSh_sh_price_unit(String sh_price_unit) {
		this.sh_price_unit = sh_price_unit;
	}
	
	
	
	public String getSh_wattage_unit() {
		return sh_wattage_unit;
	}

	public void setSh_wattage_unit(String sh_wattage_unit) {
		this.sh_wattage_unit = sh_wattage_unit;
	}
	
	public String getSh_currency() {
		return sh_currency;
	}

	public void setSh_currency(String sh_currency) {
		this.sh_currency = sh_currency;
	}

}
