package com.livixa.apacam.client.response.delete;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShResult implements Serializable 
{

	@SerializedName("sh_is_push_notification_on")
	@Expose
	private String shIsPushNotificationOn;
	@SerializedName("sh_session")
	@Expose
	private String shSession;
	@SerializedName("sh_user_id")
	@Expose
	private String shUserId;
	@SerializedName("sh_email")
	@Expose
	private String shEmail;
	@SerializedName("sh_username")
	@Expose
	private String shUsername;
	@SerializedName("sh_is_subuser")
	@Expose
	private String shIsSubuser;
	@SerializedName("sh_created_by_user_id")
	@Expose
	private String shCreatedByUserId;
	@SerializedName("sh_full_name")
	@Expose
	private String shFullName;
	@SerializedName("sh_profile_image")
	@Expose
	private String shProfileImage;
	@SerializedName("sh_phone_number")
	@Expose
	private String shPhoneNumber;
	
	
	
	
	
	public String getShIsPushNotificationOn() {
		return shIsPushNotificationOn;
	}

	/**
	 * @param shIsPushNotificationOn
	 *            The sh_is_push_notification_on
	 */
	public void setShIsPushNotificationOn(String shIsPushNotificationOn) {
		this.shIsPushNotificationOn = shIsPushNotificationOn;
	}
	
	
	

	/**
	 * @return The shSession
	 */
	public String getShSession() {
		return shSession;
	}

	/**
	 * @param shSession
	 *            The sh_session
	 */
	public void setShSession(String shSession) {
		this.shSession = shSession;
	}

	/**
	 * @return The shUserId
	 */
	public String getShUserId() {
		return shUserId;
	}

	/**
	 * @param shUserId
	 *            The sh_user_id
	 */
	public void setShUserId(String shUserId) {
		this.shUserId = shUserId;
	}

	/**
	 * @return The shEmail
	 */
	public String getShEmail() {
		return shEmail;
	}

	/**
	 * @param shEmail
	 *            The sh_email
	 */
	public void setShEmail(String shEmail) {
		this.shEmail = shEmail;
	}

	/**
	 * @return The shUsername
	 */
	public String getShUsername() {
		return shUsername;
	}

	/**
	 * @param shUsername
	 *            The sh_username
	 */
	public void setShUsername(String shUsername) {
		this.shUsername = shUsername;
	}

	/**
	 * @return The shIsSubuser
	 */
	public String getShIsSubuser() {
		return shIsSubuser;
	}

	/**
	 * @param shIsSubuser
	 *            The sh_is_subuser
	 */
	public void setShIsSubuser(String shIsSubuser) {
		this.shIsSubuser = shIsSubuser;
	}

	/**
	 * @return The shCreatedByUserId
	 */
	public String getShCreatedByUserId() {
		return shCreatedByUserId;
	}

	/**
	 * @param shCreatedByUserId
	 *            The sh_created_by_user_id
	 */
	public void setShCreatedByUserId(String shCreatedByUserId) {
		this.shCreatedByUserId = shCreatedByUserId;
	}

	/**
	 * @return The shFullName
	 */
	public String getShFullName() {
		return shFullName;
	}

	/**
	 * @param shFullName
	 *            The sh_full_name
	 */
	public void setShFullName(String shFullName) {
		this.shFullName = shFullName;
	}

	/**
	 * @return The shProfileImage
	 */
	public String getShProfileImage() {
		return shProfileImage;
	}

	/**
	 * @param shProfileImage
	 *            The sh_profile_image
	 */
	public void setShProfileImage(String shProfileImage) {
		this.shProfileImage = shProfileImage;
	}

	/**
	 * @return The shPhoneNumber
	 */
	public String getShPhoneNumber() {
		return shPhoneNumber;
	}

	/**
	 * @param shPhoneNumber
	 *            The sh_phone_number
	 */
	public void setShPhoneNumber(String shPhoneNumber) {
		this.shPhoneNumber = shPhoneNumber;
	}
	
	
	/*"sh_user_id": "6",
    "sh_email": "qasimj87@gmail.com",
    "sh_username": "user1",
    "sh_is_subuser": "1",
    "sh_created_by_user_id": "5",
    "sh_full_name": "",
    "sh_profile_image": "",
    "sh_phone_number": ""*/
	
	
}
