package com.livixa.apacam.client.network;

public interface AppWebServices {

	// Sample APIs
	public static final String BASE_URL_HTTPS = "https://www.gigglemail.com/api";
	public static final String API_USER_MESSAGE = "messages/inbox/{page_number}";
	public static final String API_USER_REFRESH_MESSAGE = "messages/inbox/{page_number}/refresh";
	public static final String API_MESSAGE_DELETE = "/messages/delete";
	
	
	public static final String liveURL = "http://www.kisafavps.com/live/smarthome/v2/";
	public static final String imageUrl = "http://www.kisafavps.com/live/smarthome";
	//public static final String liveURL = "http://www.kisafavps.com/live/smarthome/";

	public static final String testURL = "http://www.kisafavps.com/dev/smarthome/";
	
	
	

	
	public static final String BASE_URL = liveURL;          
	public static final String API_LOGIN = "login.php";
	public static final String API_REGISTER = "register.php";
	
	public static final String API_WAITAGE = "wattage.php";
	public static final String API_WAITAGE2 = "wattage2.php";
	public static final String API_NOTIFICATION = "notifications.php";

	public static final String API_FORGOT = "forgotpassword.php";
	public static final String API_CHANGE_PASS = "changepassword.php";
	
	public static final String API_LOGOUT = "logout.php";
	public static final String API_DELETE = "deleteuser.php";
	public static final String API_SYNCED_WITH_SERVER = "upload2server.php";
	public static final String API_GET_SYNCED_DATA_FROM_SERVER = "allrecords.php";
	
	public static final String API_SUB_USER_CAM_ASSO = "sub_user_cam_asso.php";
	
	public static final String API_EDIT_PROFILE = "edit_profile.php";


	public static final String API_TarifAlert = "set_tariffalert.php";
	
	public static final String API_UPLOAD_PICTURES = "upload_image.php";
	
	public static final String API_CHANGE_CURRENCY = "updatecurrency.php";
	
	
	public static final String API_ADD_SWITCH_MOODS = "add_switch_moods.php";
	
	
	
	
	}
