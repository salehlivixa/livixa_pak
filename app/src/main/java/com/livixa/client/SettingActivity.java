package com.livixa.client;

import com.livixa.apacam.client.activity.LoginActivity;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons.CustomDialogueTwoButtonsClickListner;
import com.livixa.client.R;
import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.nativecaller.NativeCaller;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * ��ʾĳ̨���������������б�
 * */
public class SettingActivity extends BaseActivity implements OnClickListener {
	// private static final String LOG_TAG = "SettingActivity";
	private final int WIFI = 0;
	private final int USER = 1;
	private final int ALERM = 2;
	private final int DATETIME = 3;
	private final int MAIL = 4;
	private final int FTP = 5;
	private final int SDCard = 6;
	private String strDID;
	private String cameraName;
	private int authority;
	private TextView tvCameraName;
	private Button btnBack;
	private MyBackBrod myBackBrod = null;
	private View buttonWifi, buttonAp, buttonUser, buttonTime, buttonAler,
			buttonMail, buttonFtp, buttonSd, buttonReboot, setting_user_time;//			buttonRF;

	class MyBackBrod extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			String action = arg1.getAction();
			if ("myback".equals(action)) {
				finish();
				overridePendingTransition(R.anim.out_to_right,
						R.anim.in_from_left);// �˳�����
			}

		}

	}

	public void showSureReboot() {
		/*AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.app);
		builder.setTitle(getResources().getString(R.string.restart));
		builder.setMessage(R.string.restart_show);
		builder.setPositiveButton(R.string.str_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Process.killProcess(Process.myPid());
						NativeCaller.PPPPRebootDevice(strDID);
						finish();
					}
				});
		builder.setNegativeButton(R.string.str_cancel, null);
		builder.show();*/
		
		
		final CustomAlertDialogueTwoButtons myASlertDialog=new CustomAlertDialogueTwoButtons(this,getResources().getString(R.string.restartdecivemessage));
				
				myASlertDialog.setListner(new CustomDialogueTwoButtonsClickListner() {
					
					@Override
					public void onCustomDialoguePositiveClick() {
						
						myASlertDialog.dismiss();
						NativeCaller.PPPPRebootDevice(strDID);
						finish();
						
					}
					
					@Override
					public void onCustomDialogueNegativeClick() {
						
						myASlertDialog.dismiss();
						
					}
				});
				
				myASlertDialog.show();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getDataFromOther();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting);
		findView();

		tvCameraName.setText(getResources().getString(R.string.setting));

		// if (authority == 1) {
		// buttonWifi.setVisibility(View.GONE);
		// buttonUser.setVisibility(View.INVISIBLE);
		// setting_user_time.setVisibility(View.GONE);
		// buttonReboot.setVisibility(View.GONE);
		// buttonTime.setBackgroundResource(R.drawable.newbuttonall);
		// }
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (myBackBrod == null) {
			myBackBrod = new MyBackBrod();
			IntentFilter filter = new IntentFilter();
			filter.addAction("myback");
			registerReceiver(myBackBrod, filter);
		}
	}

	private void getDataFromOther() {
		Intent intent = getIntent();
		strDID = intent.getStringExtra(ContentCommon.STR_CAMERA_ID);
		cameraName = intent.getStringExtra(ContentCommon.STR_CAMERA_NAME);
		authority = intent.getIntExtra("authority", 0);
	}

	private void findView() {
		tvCameraName = (TextView) findViewById(R.id.tv_camera_setting);
		btnBack = (Button) findViewById(R.id.back);
		buttonWifi = (View) findViewById(R.id.setting_wifi);
		buttonAp = (View) findViewById(R.id.setting_ap);
		buttonAler = (View) findViewById(R.id.setting_alerm);
		buttonUser = (View) findViewById(R.id.setting_use);
		buttonMail = (View) findViewById(R.id.setting_mail);
		buttonFtp = (View) findViewById(R.id.setting_ftp);
		buttonSd = (View) findViewById(R.id.setting_sdcard);
		buttonTime = (View) findViewById(R.id.setting_time);
		buttonReboot = (View) findViewById(R.id.setting_reboot);
		setting_user_time = (View) findViewById(R.id.setting_user_time);
		//buttonRF=(Button) findViewById(R.id.setting_restore_factory);
		
		setting_user_time.setOnClickListener(this);
		buttonReboot.setOnClickListener(this);
		buttonWifi.setOnClickListener(this);
		buttonAp.setOnClickListener(this);
		buttonUser.setOnClickListener(this);
		buttonTime.setOnClickListener(this);
		buttonAler.setOnClickListener(this);
		buttonMail.setOnClickListener(this);
		buttonFtp.setOnClickListener(this);
		buttonSd.setOnClickListener(this);
		btnBack.setOnClickListener(this);
		//buttonRF.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.setting_user_time:
			Intent intent8 = new Intent(this, SettingUserTimeActivity.class);
			intent8.putExtra(ContentCommon.STR_CAMERA_ID, strDID);
			intent8.putExtra(ContentCommon.STR_CAMERA_NAME, cameraName);
			startActivity(intent8);
			KisafaApplication.perFormActivityNextTransition(this);
			break;
		case R.id.setting_reboot:
			showSureReboot();
			break;
		case R.id.setting_wifi:
			if (authority == 0) {
				Intent intent = new Intent(this, SettingWifiActivity.class);
				intent.putExtra(ContentCommon.STR_CAMERA_ID, strDID);
				intent.putExtra(ContentCommon.STR_CAMERA_NAME, cameraName);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);
			} else {
				showToast(R.string.setting_show);
			}

			break;
		case R.id.setting_use:
			// if (authority == 0) {
			Intent intent1 = new Intent(this, SettingUserActivity.class);
			intent1.putExtra(ContentCommon.STR_CAMERA_ID, strDID);
			intent1.putExtra(ContentCommon.STR_CAMERA_NAME, cameraName);
			startActivity(intent1);
			KisafaApplication.perFormActivityNextTransition(this);
			// } else {
			// showToast(R.string.setting_show1);
			// }

			break;

		case R.id.setting_alerm:
			Intent intent2 = new Intent(this, SettingAlarmActivity.class);
			intent2.putExtra(ContentCommon.STR_CAMERA_ID, strDID);
			intent2.putExtra(ContentCommon.STR_CAMERA_NAME, cameraName);
			startActivity(intent2);
			KisafaApplication.perFormActivityNextTransition(this);
			break;
		case R.id.setting_time:
			Intent intent3 = new Intent(this, SettingDateActivity.class);
			intent3.putExtra(ContentCommon.STR_CAMERA_ID, strDID);
			intent3.putExtra(ContentCommon.STR_CAMERA_NAME, cameraName);
			startActivity(intent3);
			KisafaApplication.perFormActivityNextTransition(this);
			break;
		case R.id.setting_mail:
			Intent intent4 = new Intent(this, SettingMailActivity.class);
			intent4.putExtra(ContentCommon.STR_CAMERA_ID, strDID);
			intent4.putExtra(ContentCommon.STR_CAMERA_NAME, cameraName);
			startActivity(intent4);
			KisafaApplication.perFormActivityNextTransition(this);
			break;
		case R.id.setting_ftp:
			Intent intent5 = new Intent(this, SettingFtpActivity.class);
			intent5.putExtra(ContentCommon.STR_CAMERA_ID, strDID);
			intent5.putExtra(ContentCommon.STR_CAMERA_NAME, cameraName);
			startActivity(intent5);
			KisafaApplication.perFormActivityNextTransition(this);
			break;
		case R.id.setting_sdcard:
			Intent intent6 = new Intent(this, SettingSDCardActivity.class);
			intent6.putExtra(ContentCommon.STR_CAMERA_ID, strDID);
			intent6.putExtra(ContentCommon.STR_CAMERA_NAME, cameraName);
			startActivity(intent6);
			KisafaApplication.perFormActivityNextTransition(this);
			break;
		case R.id.setting_ap:
			Intent intent7 = new Intent(this, SettingApActivity.class);
			intent7.putExtra(ContentCommon.STR_CAMERA_ID, strDID);
			intent7.putExtra(ContentCommon.STR_CAMERA_NAME, cameraName);
			startActivity(intent7);
			KisafaApplication.perFormActivityNextTransition(this);
			break;
		case R.id.back:
			finish();
			KisafaApplication.perFormActivityBackTransition(this);// �˳�����
			break;
			
//		case R.id.setting_restore_factory:
//			showSureReboot(2);
//			
//			break;
		}

	}
	
	
	@Override
	public void onBackPressed() {
		 
		super.onBackPressed();
		
		finish();
		KisafaApplication.perFormActivityBackTransition(this);//
	}
	
	public void showSureReboot(final int number) {
		int message = 0;
		String title = null;
		if(number == 1){
			title = getResources().getString(R.string.restart);
			message = R.string.restart_show;
		}else{
			title = getResources().getString(R.string.setting_restore_factory);
			message = R.string.restartrf_show;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.app);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(R.string.str_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Process.killProcess(Process.myPid());
						if(number == 1){
							NativeCaller.PPPPRebootDevice(strDID);
						}else{
							NativeCaller.PPPPRestorFactory(strDID);
						}
						finish();
					}
				});
		builder.setNegativeButton(R.string.str_cancel, null);
		builder.show();
	}
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (myBackBrod != null) {
			unregisterReceiver(myBackBrod);
		}

	}
}