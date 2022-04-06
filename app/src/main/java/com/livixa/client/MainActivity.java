package com.livixa.client;

import com.livixa.apacam.client.activity.HomeActivity;
import com.livixa.apacam.client.activity.LoginActivity;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.listener.ChangeEvents;
import com.livixa.apacam.client.listener.EventObserver;
import com.livixa.apacam.client.listener.EventsListeners;
import com.livixa.apacam.client.listener.ListinerCategory;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.dbmodel.Manage_DB_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model_Relation;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.IpcamClientActivity.HierarchicalViewIdViewer;
import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.nativecaller.NativeCaller;
import object.p2pipcam.system.SystemValue;
import object.p2pipcam.utils.DataBaseHelper;

import java.util.ArrayList;

import android.app.ActivityGroup;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.LocalActivityManager;
import android.app.NotificationManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActivityGroup implements OnTouchListener,
		EventObserver {
	private LinearLayout layoutVidicon;
	private LinearLayout layoutAlarm;
	private LinearLayout layoutPic;
	private LinearLayout layoutAbout;
	private LinearLayout layoutVid;

	private ImageView mIv1;
	private ImageView mIv2;
	private ImageView mIv3;
	private ImageView mIv4;

	private TextView mTv1;
	private TextView mTv2;
	private TextView mTv3;
	private TextView mTv4;

	private int tabPosition = 1;
	private LinearLayout container;
	private MyBroadCast receiver;
	private boolean isService = false;
	private int timeTag = 0;
	private int timeOne = 0;
	private int timeTwo = 0;
	private DataBaseHelper baseHelper = null;
	private ImageView img;
	Handler handler = new Handler();
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				getWindow().clearFlags(
						WindowManager.LayoutParams.FLAG_FULLSCREEN);
				img.setVisibility(View.GONE);
				break;
			default:
				break;
			}

		}
	};
	
	
	private boolean isMyServiceRunning(Class<?> serviceClass) {
	    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (serviceClass.getName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		
		// set to home activity to remove from app on exit
		HomeActivity.MainCameraActivity=this;
		
		NoUserLoginAndCallFromNotificationThenMoveBack();
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
		initListener();
		
		if(!isMyServiceRunning(BridgeService.class))
		{
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, BridgeService.class);
			startService(intent);
			
			
			
			isService = true;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						NativeCaller.PPPPInitial(ContentCommon.SERVER_STRING);
						// long lStartTime = new Date().getTime();
						//int nRes = NativeCaller.PPPPNetworkDetect();
						// long lEndTime = new Date().getTime();
						// Message msg1 = new Message();
						// msg1.what = 0;
						// mHandler.sendMessage(msg1);
						// if (lEndTime - lStartTime <= 3000) {
						//Thread.sleep(2000);
						// }
						//Message msg = new Message();
						//msg.what = 1;
						//mHandler.sendMessage(msg);
					} catch (Exception e) {

					}
				}
			}).start();
			
		}
		
		
		
		
		findView();
		mIv1.setSelected(true);
		mTv1.setActivated(true);
		mIv2.setSelected(false);
		mTv2.setActivated(false);
		mIv3.setSelected(false);
		mTv3.setActivated(false);
		mIv4.setSelected(false);
		mTv4.setActivated(false);
		baseHelper = DataBaseHelper.getInstance(this);
		setListener();
		ShowScreen(IpcamClientActivity.class);
		receiver = new MyBroadCast();
		IntentFilter filter = new IntentFilter();
		filter.addAction("back");
		registerReceiver(receiver, filter);
		// handler.postDelayed(new Runnable() {
		//
		// @Override
		// public void run() {
		//  
		// popupWindow_bit.showAtLocation(img, Gravity.CENTER, 0, 0);
		// }
		// }, 1000);
		
		
		
		

	}
	
	
	

	public void initListener() {
		EventsListeners.getInstance().registerListeners(this,
				ListinerCategory.ON_BACK);
	}

	private void setListener() {
		
		
		//when there in no camera than disable the bottom bar
		
		
		User_Camera_Model_Relation user_Camera_Model_Relation=Manage_DB_Model.getCameraWithRelationAgainst_UserId(AppPreference.getValue(MainActivity.this,AppKeys.KEY_USER_ID));
		
		if(user_Camera_Model_Relation.camera_Model==null || user_Camera_Model_Relation.camera_Model.size()==0)
		{
			
		}
		else
		{

			layoutVidicon.setOnTouchListener(this);
			layoutAlarm.setOnTouchListener(this);
			layoutPic.setOnTouchListener(this);
			layoutVid.setOnTouchListener(this);
			layoutAbout.setOnTouchListener(this);
		}

	}
	
	
	
	
	@Override
	protected void onResume() {
		
		
		super.onResume();
				updateTouchListnersOnBottomBar();
		
		
	}
	
	
	public void updateTouchListnersOnBottomBar()
	{
		
        User_Camera_Model_Relation user_Camera_Model_Relation=Manage_DB_Model.getCameraWithRelationAgainst_UserId(AppPreference.getValue(MainActivity.this,AppKeys.KEY_USER_ID));
		
		if(user_Camera_Model_Relation.camera_Model==null || user_Camera_Model_Relation.camera_Model.size()==0)
		{
			layoutVidicon.setOnTouchListener(null);
			layoutAlarm.setOnTouchListener(null);
			layoutPic.setOnTouchListener(null);
			layoutVid.setOnTouchListener(null);
			layoutAbout.setOnTouchListener(null);
		}
		else
		{
			
			
			layoutVidicon.setOnTouchListener(this);
			layoutAlarm.setOnTouchListener(this);
			layoutPic.setOnTouchListener(this);
			layoutVid.setOnTouchListener(this);
			layoutAbout.setOnTouchListener(this);
		}
		
	}
	
	

	private void findView() {
		layoutVidicon = (LinearLayout) findViewById(R.id.main_layout_vidicon);
		layoutAlarm = (LinearLayout) findViewById(R.id.main_layout_alarm);
		layoutPic = (LinearLayout) findViewById(R.id.main_layout_pic);
		layoutVid = (LinearLayout) findViewById(R.id.main_layout_vid);
		layoutAbout = (LinearLayout) findViewById(R.id.main_layout_about);
		container = (LinearLayout) findViewById(R.id.container2);
		img = (ImageView) findViewById(R.id.img);
		mIv1 = (ImageView) findViewById(R.id.main_img_vidicon);
		mIv2 = (ImageView) findViewById(R.id.main_img_alarm);
		mIv3 = (ImageView) findViewById(R.id.main_img_picture);
		mIv4 = (ImageView) findViewById(R.id.main_img_vid);

		mTv1 = (TextView) findViewById(R.id.main_tv_vidicon);
		mTv2 = (TextView) findViewById(R.id.main_tv_alarm);
		mTv3 = (TextView) findViewById(R.id.main_tv_picture);
		mTv4 = (TextView) findViewById(R.id.main_tv_vid);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			switch (v.getId()) {
			case R.id.main_layout_vidicon:
				mIv1.setSelected(true);
				mTv1.setActivated(true);
				mIv2.setSelected(false);
				mTv2.setActivated(false);
				mIv3.setSelected(false);
				mTv3.setActivated(false);
				mIv4.setSelected(false);
				mTv4.setActivated(false);
				tabPosition = 1;
				ShowScreen(IpcamClientActivity.class);
				break;
			case R.id.main_layout_alarm:
				mIv1.setSelected(false);
				mTv1.setActivated(false);
				mIv2.setSelected(true);
				mTv2.setActivated(true);
				mIv3.setSelected(false);
				mTv3.setActivated(false);
				mIv4.setSelected(false);
				mTv4.setActivated(false);
				tabPosition = 2;
				ShowScreen(AlarmActivity.class);
				break;
			case R.id.main_layout_pic:
				mIv1.setSelected(false);
				mTv1.setActivated(false);
				mIv2.setSelected(false);
				mTv2.setActivated(false);
				mIv3.setSelected(true);
				mTv3.setActivated(true);
				mIv4.setSelected(false);
				mTv4.setActivated(false);
				tabPosition = 3;
				ShowScreen(PictureActivity.class);
				break;
			case R.id.main_layout_vid:
				mIv1.setSelected(false);
				mTv1.setActivated(false);
				mIv2.setSelected(false);
				mTv2.setActivated(false);
				mIv3.setSelected(false);
				mTv3.setActivated(false);
				mIv4.setSelected(true);
				mTv4.setActivated(true);
				tabPosition = 4;
				ShowScreen(VideoActivity.class);
				break;
			case R.id.main_layout_about:
				layoutAbout.setBackgroundColor(0x80111111);
				switch (tabPosition) {
				case 1:
					layoutVidicon.setBackgroundColor(0x00111111);
					break;
				case 2:
					layoutAlarm.setBackgroundColor(0x00111111);
					break;
				case 3:
					layoutPic.setBackgroundColor(0x00111111);
					break;
				case 4:
					layoutVid.setBackgroundColor(0x00111111);
					break;
				default:
					break;
				}
				tabPosition = 5;
				ShowScreen(AboutActivity.class);
				break;

			}
			break;
		}
		return false;
	}

	@Override
	public void onBackPressed() {
		 
		
		//super.onBackPressed();
		
		//finish();
		
		Intent intent = new Intent();
		
		intent.setClass(MainActivity.this, HomeActivity.class);
		startActivity(intent);
		//overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		KisafaApplication.perFormActivityBackTransition(this);
		
		
		
	}
	
	/*@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			showSureDialog();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			// Date date = new Date();
			// if (timeTag == 0) {
			// timeOne = date.getSeconds();
			// timeTag = 1;
			// Toast.makeText(MainActivity.this, R.string.main_show_back, 0)
			// .show();
			// } else if (timeTag == 1) {
			// timeTwo = date.getSeconds();
			// if (timeTwo - timeOne <= 3) {
			// moveTaskToBack(true);
			// timeTag = 0;
			// } else {
			// timeTag = 1;
			// Toast.makeText(MainActivity.this, R.string.main_show_back,
			// 0).show();
			// }
			// }
			//
			// return true;
			
			
			if (isService) 
			{
				Intent intent = new Intent();
				NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
				nMgr.cancel(1514);
				intent.setClass(MainActivity.this, BridgeService.class);
				stopService(intent);
				isService = false;
			}
			
			Toast.makeText(MainActivity.this, "Moving Back", Toast.LENGTH_SHORT).show();
			
			finish();
			
			
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}*/

	/****
	 * �˳�ȷ��dialog
	 * */
	private void showSureDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.app);
		builder.setTitle(getResources().getString(R.string.exit)
				+ getResources().getString(R.string.app_name));
		builder.setMessage(R.string.exit_chenxu_show);
		builder.setPositiveButton(R.string.str_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Process.killProcess(Process.myPid());
						finish();
					}
				});
		builder.setNegativeButton(R.string.str_cancel, null);
		builder.show();
	}

	/**
	 * 
	 * ����Activity��ӵ�ActivityGroup����
	 * **/
	private void ShowScreen(Class clzz) {
		
		try
		{
		LocalActivityManager localActivityManager = getLocalActivityManager();
		String id = clzz.getSimpleName();
		Intent intent = new Intent(this, clzz);
		Window window = localActivityManager.startActivity(id, intent);
		View view = window.getDecorView();
		container.removeAllViews();
		container.setVisibility(View.VISIBLE);
		container.addView(view, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		
		KisafaApplication.perFormActivityNextTransition(this);
		//overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
		}catch(Exception ex)
		{
			
		}
	}

	private class MyBroadCast extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			moveTaskToBack(true);
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
		//NativeCaller.Free();
		//Intent intent = new Intent();
		//intent.setClass(this, BridgeService.class);
		baseHelper.close();
		//stopService(intent);
		SystemValue.checkSDStatu = 0;
		/*NotificationManager notificationManager = (NotificationManager) this
				.getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(1514);*/
	}

	@Override
	public void broadCastEvent(ListinerCategory tabType, ChangeEvents event,
			Bundle data) {
		
		
		//Toast.makeText(MainActivity.this, "Recieved", Toast.LENGTH_SHORT).show();
		
		if (tabType == ListinerCategory.ON_BACK) {
			if (event == ChangeEvents.ON_BACK) {
				// if (receiver != null && isReceiver) {
				// unregisterReceiver(receiver);
				// isReceiver = false;
				// }
				/*if (isService) {
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, BridgeService.class);
					//intent.addFlags(Intent.CL);
					stopService(intent);
					isService = false;
				}*/
				// Intent i = new Intent(this, HomeActivity.class);
				// //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				// startActivity(i);
				//finish();
			}
		}
	}
	
	
	private void NoUserLoginAndCallFromNotificationThenMoveBack()
	{
		
		Intent intnt=getIntent();
		
		if(intnt!=null)
		{
			boolean isCommingFromNotification=intnt.getBooleanExtra("commingFromNotification", false);
			
			boolean isUserLogin=AppPreference.getSavedData(this, AppKeys.KEY_IS_LOGIN);
			
			if(isCommingFromNotification && !isUserLogin)
			{
				
				Intent intent=new Intent(this,LoginActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				finish();
				
			}
		}
					
		
	}
	
}
