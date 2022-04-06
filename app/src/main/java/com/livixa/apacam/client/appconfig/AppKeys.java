package com.livixa.apacam.client.appconfig;

public class AppKeys {

	// Parse Keys
	public static String parseAppId = "";
	public static String parseClientKey = "";

	// Device Keys
	public static String deviceType = "android";
	public static String deviceToken = "device_token";

	// All keys for preference
	public static final String KEY_IS_LOGDIN = "logedin";
	public static final String KEY_SCREEN_WIDTH = "key_screen_width";
	public static final String KEY_SCREEN_HEIGHT = "key_screen_height";
	public static final String KEY_APP_VERSION = "key_app_version";

	// All keys for user profile
	public static final String KEY_X_API_AUTHID = "X-API-AUTHID";
	public static final String KEY_X_API_AUTHKEY = "X-API-AUTHKEY";
	public static final String KEY_TOKEN = "user_token";
	public static final String KEY_SESSION = "user_session";
	public static final String KEY_USER_ID = "sh_user_id";
	public static final String KEY_USER_NAME = "sh_user_name";
	public static final String KEY_USER_FULL_NAME = "sh_user_full_name";
	public static final String KEY_USER_PHONE = "sh_user_phone";
	
	public static final String KEY_USER_CITY = "sh_user_city";
	
	public static final String KEY_USER_COUNTRY = "sh_user_country";
	
    public static final String KEY_USER_WATAGE_UNIT = "watage_unit";
	
	public static final String KEY_USER_PRICE_UNIT = "price_unit";
	
	
	public static final String KEY_USER_COUNTYY_CODE = "sh_user_country_code";
	public static final String KEY_USER_TIME_ZONE = "sh_user_time_zone";
	public static final String KEY_USER_TIME_ZONE_NAME= "sh_user_time_zone_name";
	
	public static final String KEY_USER_EMAIL = "sh_user_email";
	public static final String KEY_IS_SUB_USER = "sh_is_subuser";
	public static final String KEY_SUB_USER_TAG = "sub_user";
	public static final String KEY_CAMERA_NAME_TAG = "cam_name";
	public static final String KEY_CAMERA_ID_TAG = "cam_id";
	public static final String KEY_IS_LOGIN_FIRST_TIME = "is_login_first_time";
	
	public static final String KEY_IS_LOGIN = "user_login";
	public static final String KEY_USER_FNAME = "userfname";
	public static final String KEY_USER_LNAME = "userlname";

	// All keys for tablet
	public static final String KEY_IS_TABLET = "is_tablet";
	public static final String KEY_IS_LANDSCAPE = "is_landscape";
	
	
	public static String  KEY_IS_CREATED="created";
	public static String  KEY_IS_UPDATED="updated";
	public static String  KEY_IS_DELETED="deleted";

	public static String  KEY_IS_SYNCED="1";
	public static String  KEY_IS_NOT_SYNCED="0";
	
	public static final String KEY_ROOM_ID_TAG = "room";
	
	public static final String KEY_MOOD_ID_TAG = "moodid";
	
	public static final String KEY_SWITCH_ID_TAG = "switch";
	
	public static final String KEY_ROOM_TITLE_TAG = "room_title";
	
	public static final String KEY_REMOTE_OPTION_TAG = "remotely";
	
	//moods
	
	
	
	public static String  KEY_MOOD_TRAVEL="Travel";
	public static String  KEY_MOOD_SLEEP="Sleep";
	public static String  KEY_MOOD_WAKEUP="Wake up";
	public static String  KEY_MOOD_GUEST="Guest";
	public static String  KEY_MOOD_Living="Living";
	public static String  KEY_MOOD_SAFETY="Safety";
	public static String  KEY_MOOD_AWAY="Away";
	public static String  KEY_MOOD_NOT_AVAILABLE="N/A";
	
	
	
	public static final String KEY_BUTTON_ONE_CMD = "button1";
	
	public static final String KEY_BUTTON_TWO_CMD = "button2";
	
	public static final String KEY_BUTTON_THREE_CMD = "button3";
	
	public static final String KEY_BUTTON_TYPE = "button_type";
	
	public static final String KEY_CASE_REPEAT = "00000000";
	
	
	public static final String KEY_FIRST_MOVE_TO_HOME = "move_to_home";
	
	public static final String KEY_CURRENT_LANGUAGE = "app_lang";
	
	public static final String KEY_CURRENT_CURRENCY = "currency";
	
	public enum LANGUAGES {
		ENGLISH("ENGLISH"), ARABIC("ARABIC");
		
		 private String value;

		 LANGUAGES(final String value) {
		        this.value = value;
		    }

		    public String getValue() {
		        return value;
		    }

		    @Override
		    public String toString() {
		        return this.getValue();
		    }
	};
	
	
}
