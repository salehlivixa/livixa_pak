package com.livixa.apacam.client.utility;

/**
 * Created by Wahib on 12/31/2015.
 */
public class ServerCodes {

	public static final class ServerRequestCodes {
		public static final int MSG_DELETE_REQUEST_CODE = 100;
		public static final int MSG_INBOX_REQUEST_CODE = 1001;

		public static final int LOGIN_REQUEST_CODE = 10000;
		public static final int REGISTER_REQUEST_CODE = 10001;
		public static final int UPDATE_PROFILE_REQUEST_CODE = 10011;
		public static final int UPDATE_PICTURES_REQUEST_CODE = 10015;
		public static final int FORGOT_REQUEST_CODE = 1001;
		public static final int WATAGE_REQUEST_CODE = 1012;
		public static final int NOTIFICATION_REQUEST_CODE = 1013;
		public static final int TARRIF_ALERT_REQUEST_CODE = 1014;
		public static final int LOGOUT_REQUEST_CODE = 10003;
		
		public static final int CHANGE_CURRENCY_REQUEST_CODE = 10016;
		
		public static final int ADD_EDIT_SWITCH_REQUEST_CODE = 10017;
		
		public static final int SYNCED_DATA_REQUEST_CODE = 10004;
		
		public static final int SYNCED_ASSOCIATION_DATA_REQUEST_CODE = 10006;
		
		public static final int GET_SYNCED_DATA_REQUEST_CODE = 10005;

		private ServerRequestCodes() {
		}
	}

	public static final class ServerStatusCodes {
		public static final String SERVER_CALL_SUCCESS = "success";
		public static final String SERVER_CALL_TRUE = "true";
		public static final String SERVER_CALL_ERROR = "error";

		private ServerStatusCodes() {
		}
	}

	public enum ServerResultTypes {
		Success(1), Failure(2), Empty(3), Warning(4), Information(5), ValidationError(
				6), Exception(7), CodeWord(8), Redirect(9), Function(10), Code(
				11), Duplicate(12), AccessRights(13), DeActivated(14);

		private int resultTypeValue;

		public int value() {
			return this.resultTypeValue;
		}

		ServerResultTypes(final int value) {
			this.resultTypeValue = value;
		}

		public static ServerResultTypes getResultType(final int ordinal) {
			return ServerResultTypes.values()[ordinal];
		}
	}
}
