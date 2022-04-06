package com.livixa.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.nativecaller.NativeCaller;
import object.p2pipcam.utils.DataBaseHelper;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.client.BridgeService.UserInterface;
import android.widget.EditText;
import android.widget.Toast;

/**
 * �û�����
 * */
public class SettingUserActivity extends BaseActivity implements
		OnCheckedChangeListener, OnClickListener, UserInterface {
	private String TAG = "SettingUserActivity";
	private boolean successFlag = false;// ��ȡ�����õĽ��
	private int CAMERAPARAM = 0xffffffff;// ����״̬
	private final int TIMEOUT = 3000;
	private final int FAILED = 0;
	private final int SUCCESS = 1;
	private final int PARAMS = 3;
	private String strDID = null;// camera id
	private String cameraName = null;
	private String operatorName = "";
	private String operatorPwd = "";
	private String visitorName = "";
	private String visitorPwd = "";
	private String adminName = "";
	private String adminPwd = "";
	private EditText editName = null;
	private EditText editPwd = null;
	private CheckBox cbxShowPwd = null;
	private Button btnOk = null;
	private Button btnCancel = null;
	
	private DataBaseHelper helper = null;
	// private TextView tvCameraName = null;
	private EditText editOperatorName = null;
	private EditText editOperatorPwd = null;
	private CheckBox cboxShowOperatorPwd = null;
	private EditText editVisitorName = null;
	private EditText editVisitorPwd = null;
	private CheckBox cboxShowVisitorPwd = null;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case FAILED:// set failed
				showToast(R.string.user_set_failed);
				break;
			case SUCCESS:// set success
				showToast(R.string.user_set_success);
				NativeCaller.PPPPRebootDevice(strDID);

				helper.updateCameraUser(strDID, adminName, adminPwd);

				Log.d(TAG, "user:" + adminName + " pwd:" + adminPwd);

				final Intent intent = new Intent(
						ContentCommon.STR_CAMERA_INFO_RECEIVER);
				intent.putExtra(ContentCommon.STR_CAMERA_NAME, cameraName);
				intent.putExtra(ContentCommon.STR_CAMERA_ID, strDID);
				intent.putExtra(ContentCommon.STR_CAMERA_USER, adminName);
				intent.putExtra(ContentCommon.STR_CAMERA_PWD, adminPwd);
				intent.putExtra(ContentCommon.STR_CAMERA_OLD_ID, strDID);
				intent.putExtra(ContentCommon.CAMERA_OPTION,
						ContentCommon.CHANGE_CAMERA_USER);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {

						sendBroadcast(intent);
					}
				}, 3000);
				Intent intent2 = new Intent("myback");
				sendBroadcast(intent2);
				// Intent intent2=new
				// Intent(SettingUserActivity.this,IpcamClientActivity.class);
				// startActivity(intent2);
				// Intent intent3 = new Intent(SettingUserActivity.this,
				// MainActivity.class);
				// intent3.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				// startActivity(intent3);
				// finish();
				finish();
				break;
			case PARAMS:// get user params
				successFlag = true;
				WaitingStaticProgress.hideProgressDialog();
				editName.setText(adminName);
				editPwd.setText(adminPwd);
				editOperatorName.setText(operatorName);
				editOperatorPwd.setText(operatorPwd);
				editVisitorName.setText(visitorName);
				editVisitorPwd.setText(visitorPwd);
				break;

			default:
				break;
			}
		}
	};

	private Runnable runnable = new Runnable() {

		@Override
		public void run() {
			if (!successFlag) {
				successFlag = false;
				WaitingStaticProgress.hideProgressDialog();
				// showToast(R.string.user_getparams_failed);
			}
		}
	};

	@Override
	protected void onPause() {
		KisafaApplication.perFormActivityBackTransition(this);// �˳�����
		super.onPause();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getDataFromOther();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.settinguser);
		/*progressDialog = new ProgressDialog(this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage(getString(R.string.user_getparams));
		progressDialog.show();*/
		
		WaitingStaticProgress.showProgressDialog(getString(R.string.user_getparams), this);
		
		mHandler.postDelayed(runnable, TIMEOUT);
		helper = DataBaseHelper.getInstance(this);
		findView();
		setLisetener();
		BridgeService.setUserInterface(this);
		NativeCaller.PPPPGetSystemParams(strDID,
				ContentCommon.MSG_TYPE_GET_PARAMS);
		// tvCameraName.setText(cameraName+"  "+getResources().getString(R.string.setting_user));
	}

	private void setLisetener() {
		cbxShowPwd.setOnCheckedChangeListener(this);
		cboxShowOperatorPwd.setOnCheckedChangeListener(this);
		cboxShowVisitorPwd.setOnCheckedChangeListener(this);
		btnOk.setOnClickListener(this);
		btnCancel.setOnClickListener(this);

		MyTextWatch myNameTextWatch = new MyTextWatch(R.id.edit_admin_name);
		editName.addTextChangedListener(myNameTextWatch);
		MyTextWatch myPwdTextWatch = new MyTextWatch(R.id.edit_admin_pwd);
		editPwd.addTextChangedListener(myPwdTextWatch);
		MyTextWatch myOpNameWatch = new MyTextWatch(R.id.edit_operator_name);
		editOperatorName.addTextChangedListener(myOpNameWatch);
		MyTextWatch myOpPwdWatch = new MyTextWatch(R.id.edit_operator_pwd);
		editOperatorPwd.addTextChangedListener(myOpPwdWatch);
		MyTextWatch myVsNameWatch = new MyTextWatch(R.id.edit_visitor_name);
		editVisitorName.addTextChangedListener(myVsNameWatch);
		MyTextWatch myVsPwdWatch = new MyTextWatch(R.id.edit_visitor_pwd);
		editVisitorPwd.addTextChangedListener(myVsPwdWatch);
	}

	private void getDataFromOther() {
		Intent intent = getIntent();
		strDID = intent.getStringExtra(ContentCommon.STR_CAMERA_ID);
		cameraName = intent.getStringExtra(ContentCommon.STR_CAMERA_NAME);
	}

	private void findView() {
		editName = (EditText) findViewById(R.id.edit_admin_name);
		if (!("ptp".equalsIgnoreCase(strDID.substring(0,3))))editName.setEnabled(false);
		editPwd = (EditText) findViewById(R.id.edit_admin_pwd);
		cbxShowPwd = (CheckBox) findViewById(R.id.cbox_show_admin_pwd);
		editOperatorName = (EditText) findViewById(R.id.edit_operator_name);
		editOperatorPwd = (EditText) findViewById(R.id.edit_operator_pwd);
		cboxShowOperatorPwd = (CheckBox) findViewById(R.id.cbox_show_operator_pwd);
		editVisitorName = (EditText) findViewById(R.id.edit_visitor_name);
		editVisitorPwd = (EditText) findViewById(R.id.edit_visitor_pwd);
		cboxShowVisitorPwd = (CheckBox) findViewById(R.id.cbox_show_visitor_pwd);

		btnOk = (Button) findViewById(R.id.user_ok);
		btnCancel = (Button) findViewById(R.id.user_cancel);

		// tvCameraName = (TextView)findViewById(R.id.tv_camera_setting);
		// RelativeLayout layout = (RelativeLayout) findViewById(R.id.top);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.top_bg);
		BitmapDrawable drawable = new BitmapDrawable(bitmap);
		drawable.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
		drawable.setDither(true);
		// layout.setBackgroundDrawable(drawable);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.user_ok:
			String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~��@#��%����&*��������+|{}������������������������]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(adminName);
			Matcher m1 = p.matcher(adminPwd);
			Matcher m2 = p.matcher(visitorName);
			Matcher m3 = p.matcher(visitorPwd);
			Matcher m4 = p.matcher(operatorName);
			Matcher m5 = p.matcher(operatorPwd);
			// private String operatorName = "";
			// private String operatorPwd = "";
			// private String visitorName = "";
			// private String visitorPwd = "";
			// private String adminName = "";
			// private String adminPwd = "";
			if (m.find() || m1.find() || m2.find() || m3.find() || m4.find()
					|| m5.find()) {
				showToast(R.string.user_pwd_no_show);
				return;
			}
			setUser();

			break;
		case R.id.user_cancel:
			finish();
			overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);// �˳�����
			break;

		default:
			break;
		}
	}

	private void setUser() {
		// if (successFlag) {
		if (TextUtils.isEmpty(adminName)) {
			showToast(R.string.user_name_no_empty);
			return;
		}
		// if(TextUtils.isEmpty(pwd)){
		// showToast(R.string.pwd_no_empty);
		// return;
		// }

		Log.d(TAG, "adminName:" + adminName + " adminPwd:" + adminPwd);
		NativeCaller.PPPPUserSetting(strDID, visitorName, visitorPwd,
				operatorName, operatorPwd, adminName, adminPwd);

		// } else {
		// showToast(R.string.user_set_failed);
		// }
	}

	private void settingTimeOut() {
		successFlag = false;
		mHandler.postAtTime(settingRunnable, TIMEOUT);
	}

	private Runnable settingRunnable = new Runnable() {

		@Override
		public void run() {
			if (!successFlag) {
				showToast(R.string.user_set_failed);
			}
		}
	};

	private class MyTextWatch implements TextWatcher {
		private int id;

		public MyTextWatch(int id) {
			this.id = id;
		}

		@Override
		public void afterTextChanged(Editable s) {
			String result = s.toString();
			switch (id) {
			case R.id.edit_admin_name:
				adminName = result;
				break;
			case R.id.edit_admin_pwd:
				adminPwd = result;
				break;
			case R.id.edit_operator_name:
				operatorName = result;
				break;
			case R.id.edit_operator_pwd:
				operatorPwd = result;
				break;
			case R.id.edit_visitor_name:
				visitorName = result;
				break;
			case R.id.edit_visitor_pwd:
				visitorPwd = result;
				break;
			default:
				break;
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onCheckedChanged(CompoundButton v, boolean isChecked) {
		switch (v.getId()) {
		case R.id.cbox_show_admin_pwd:
			if (isChecked) {
				editPwd.setTransformationMethod(HideReturnsTransformationMethod
						.getInstance());
			} else {
				editPwd.setTransformationMethod(PasswordTransformationMethod
						.getInstance());
			}
			break;
		case R.id.cbox_show_operator_pwd:
			if (isChecked) {
				editOperatorPwd
						.setTransformationMethod(HideReturnsTransformationMethod
								.getInstance());
			} else {
				editOperatorPwd
						.setTransformationMethod(PasswordTransformationMethod
								.getInstance());
			}
			break;
		case R.id.cbox_show_visitor_pwd:
			if (isChecked) {
				editVisitorPwd
						.setTransformationMethod(HideReturnsTransformationMethod
								.getInstance());
			} else {
				editVisitorPwd
						.setTransformationMethod(PasswordTransformationMethod
								.getInstance());
			}
			break;
		default:
			break;
		}
	}

	/**
	 * BridgeService callback
	 * **/
	@Override
	public void callBackUserParams(String did, String user1, String pwd1,
			String user2, String pwd2, String user3, String pwd3) {
		Log.d(TAG, " did:" + did + " user1:" + user1 + " pwd1:" + pwd1
				+ " user2:" + user2 + " pwd2:" + pwd2 + " user3:" + user3
				+ " pwd3:" + pwd3);
		adminName = user3;
		adminPwd = pwd3;
		operatorName = user2;
		operatorPwd = pwd2;
		visitorName=user1;
	    visitorPwd=pwd1;
		mHandler.sendEmptyMessage(PARAMS);
	}

	/**
	 * BridgeService callback
	 * **/
	@Override
	public void callBackSetSystemParamsResult(String did, int paramType,
			int result) {
		Log.d(TAG, "result:" + result + " paramType:" + paramType);
		mHandler.sendEmptyMessage(result);
	}

	/**
	 * BridgeService callback
	 * **/
	@Override
	public void callBackPPPPMsgNotifyData(String did, int type, int param) {
		if (strDID.equals(did)) {
			if (ContentCommon.PPPP_MSG_TYPE_PPPP_STATUS == type) {
				CAMERAPARAM = param;
			}
		}
	}

}
