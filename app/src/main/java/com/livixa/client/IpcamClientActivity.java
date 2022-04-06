package com.livixa.client;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.afollestad.materialdialogs.MaterialDialog;
import com.androidquery.auth.GoogleHandle;

import com.livixa.apacam.client.activity.HomeActivity;
import com.livixa.apacam.client.activity.LoginActivity;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.listener.ChangeEvents;
import com.livixa.apacam.client.listener.EventsListeners;
import com.livixa.apacam.client.listener.ListinerCategory;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons.CustomDialogueTwoButtonsClickListner;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.dbmodel.Camera_Model;
import com.livixa.apacam.dbmodel.Manage_DB_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model_Relation;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.BridgeService.ControllerBinder;
import com.livixa.client.BridgeService.IpcamClientInterface;
import object.p2pipcam.adapter.CameraEditAdapter;
import object.p2pipcam.adapter.CameraListAdapter;
import object.p2pipcam.bean.CameraParamsBean;
import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.nativecaller.NativeCaller;
import object.p2pipcam.system.SystemValue;
import object.p2pipcam.utils.DataBaseHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.transition.Scene;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;



public class IpcamClientActivity extends BaseActivity implements
		OnClickListener, IpcamClientInterface, OnItemClickListener {
	private static final String TAG = "IpcamClientActivity";
	private final int SNAPSHOT = 200;
	private static final String STR_DID = "did";
	private static final String STR_MSG_PARAM = "msgparam";
	private CameraInfoReceiver receiver = null;
	public static CameraListAdapter listAdapter = null;
	private DataBaseHelper helper = null;
	private static int cameraStatus;
	private boolean isEdited = false;
	public ListView cameraListView = null;
	public View cameraEmptylayout;
	private ImageView btnEdit;
	private CameraEditAdapter editAdapter;
	private LinearLayout delBottomLayout;
	private Button btnSelectAll;
	private Button btnSelectReverse;
	private Button btnDelCamera;
	private int timeTag = 0;
	private int timeOne = 0;
	private int timeTwo = 0;
	private LinearLayout addCameraListHeader;
	private ImageView imageButtonRefresh;
	private ImageView mIvLogo;
	private ProgressBar progressBar;
	private ImageButton imageButton_apphome;
	private int screen_width;
	private int screen_height;
	private ProgressDialog progressDialog;
	
	BridgeService mServer;
	protected boolean mBounded;

	public static void changerCameraStatus(int status) {
		cameraStatus = status;
	}
	
	
	
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ipcamclient);
		
		upDateLanguageonConfigurationChange();
		
		screen_width = getWindowManager().getDefaultDisplay().getWidth();
		screen_height = getWindowManager().getDefaultDisplay().getHeight();
		helper = DataBaseHelper.getInstance(this);
		UpdateLocalDB();
		
		findView();
		setControlListener();
		// getJPush();
		listAdapter = new CameraListAdapter(this, this);
		editAdapter = new CameraEditAdapter(this);
		cameraListView.setAdapter(listAdapter);
		
		
		// initCameraList();
		BridgeService.setIpcamClientInterface(this);
		new Thread(new StartPPPPThread()).start();

		// 连接设备
		if (receiver == null) {
			receiver = new CameraInfoReceiver();
			IntentFilter filter = new IntentFilter();
			filter.addAction(ContentCommon.STR_CAMERA_INFO_RECEIVER);
			filter.addAction("back");
			filter.addAction("other");
			registerReceiver(receiver, filter);
		}
		
		
		
		
		 
	}
	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {

		
		
		
		
		super.onConfigurationChanged(newConfig);
		
		upDateLanguageonConfigurationChange();
		
	}
	
	
	private void upDateLanguageonConfigurationChange()
	{
		
		String  tempLanguage="en";
	    if(KisafaApplication.currentAppLanguage.equals(AppKeys.LANGUAGES.ENGLISH))
		{
	    	tempLanguage="en";
	    	
	    	
		}
	    else
	    {
	    	tempLanguage="ar";
	    	
	    	
	    }
	 
	 try
		{
			Locale locale = new Locale(tempLanguage);
			Locale.setDefault(locale);
			Configuration config = getApplicationContext().getResources().getConfiguration();
			config.locale = locale;
			getBaseContext().getResources().updateConfiguration(config,
			      getBaseContext().getResources().getDisplayMetrics());
			
		}catch(Exception ex)
		{
			ex.toString();
		}
	 
		
	}
	
	public class HierarchicalViewIdViewer {

		public View debugViewIds(View view, String logtag) {
			Log.v(logtag, "traversing: " + view.getClass().getSimpleName() + ", id: " + view.getId());
			if (view.getParent() != null && (view.getParent() instanceof ViewGroup)) {
				return debugViewIds((View)view.getParent(), logtag);
			}
			else {
				debugChildViewIds(view, logtag, 0);
				return view;
			}
		}

		private void debugChildViewIds(View view, String logtag, int spaces) {
			if (view instanceof ViewGroup) {
				ViewGroup group = (ViewGroup)view;
				for (int i = 0; i < group.getChildCount(); i++) {
					View child = group.getChildAt(i);
					Log.v(logtag, padString("view: " + child.getClass().getSimpleName() + "(" + child.getId() + ")", spaces));
					debugChildViewIds(child, logtag, spaces + 1);
				}
			}
		}

		private String padString(String str, int noOfSpaces) {
			if (noOfSpaces <= 0) {
				return str;
			}
			StringBuilder builder = new StringBuilder(str.length() + noOfSpaces);
			for (int i = 0; i < noOfSpaces; i++) {
				builder.append(' ');
			}
			return builder.append(str).toString();
		}

	}
	
	
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
	        super.onWindowFocusChanged(hasFocus);

	       
	        
	        //this.recreate();
	    }
	
	
	public void UpdateLocalDB()
	{
	
		if(SystemValue.arrayList!=null)
		SystemValue.arrayList.clear();
		
		if(AppPreference.getValue(this, AppKeys.KEY_IS_LOGIN_FIRST_TIME).equals("1"))
		{
		
				AppPreference.saveValue(this,"0", AppKeys.KEY_IS_LOGIN_FIRST_TIME);
				
				User_Camera_Model_Relation user_Camera_Model_Relation=Manage_DB_Model.getCameraWithRelationAgainst_UserId(AppPreference.getValue(IpcamClientActivity.this,AppKeys.KEY_USER_ID));
				
				if(AppPreference.getValue(IpcamClientActivity.this,AppKeys.KEY_IS_SUB_USER)!=null && AppPreference.getValue(IpcamClientActivity.this,AppKeys.KEY_IS_SUB_USER).equals("1"))
				{
					if(user_Camera_Model_Relation.camera_Model!=null)
					for(int i=0;i<user_Camera_Model_Relation.camera_Model.size();i++)
					{
					Camera_Model cm=user_Camera_Model_Relation.camera_Model.get(i);
					User_Camera_Model ucm=user_Camera_Model_Relation.user_Camera_Model.get(i);
					
					helper.createCamera(cm.name, cm.did, cm.user, cm.password,ucm.isAccessCameraSetting);
					}
				}
				else
				{
					if(user_Camera_Model_Relation.camera_Model!=null)
						for(int i=0;i<user_Camera_Model_Relation.camera_Model.size();i++)
						{
						Camera_Model cm=user_Camera_Model_Relation.camera_Model.get(i);
						User_Camera_Model ucm=user_Camera_Model_Relation.user_Camera_Model.get(i);
						
						helper.createCamera(cm.name, cm.did, cm.user, cm.password,"1");
						}
				}
				
				
				
		
		}
		else
		{
			
			if(AppPreference.getValue(IpcamClientActivity.this,AppKeys.KEY_IS_SUB_USER)!=null && AppPreference.getValue(IpcamClientActivity.this,AppKeys.KEY_IS_SUB_USER).equals("1"))
			{
				
				
				helper.deleteAllCameras();
				
				User_Camera_Model_Relation user_Camera_Model_Relation=Manage_DB_Model.getSubUserCameraWithRelationAgainst_UserId(AppPreference.getValue(IpcamClientActivity.this,AppKeys.KEY_USER_ID));
				if(user_Camera_Model_Relation.camera_Model!=null)
					for(int i=0;i<user_Camera_Model_Relation.camera_Model.size();i++)
					{
					Camera_Model cm=user_Camera_Model_Relation.camera_Model.get(i);
					User_Camera_Model ucm=user_Camera_Model_Relation.user_Camera_Model.get(i);
					
					
					
					
				 if(!ucm.modelStatus.equals(AppKeys.KEY_IS_DELETED))
					{
						helper.createCamera(cm.name, cm.did, cm.user, cm.password,ucm.isAccessCameraSetting);
					}
					
					}
				
			}
			else
			{
			User_Camera_Model_Relation user_Camera_Model_Relation=Manage_DB_Model.getCameraWithRelationAgainst_UserId(AppPreference.getValue(IpcamClientActivity.this,AppKeys.KEY_USER_ID));
			if(user_Camera_Model_Relation.camera_Model!=null)
				for(int i=0;i<user_Camera_Model_Relation.camera_Model.size();i++)
				{
				Camera_Model cm=user_Camera_Model_Relation.camera_Model.get(i);
				User_Camera_Model ucm=user_Camera_Model_Relation.user_Camera_Model.get(i);
				if(cm.modelStatus.equals(AppKeys.KEY_IS_DELETED))
				helper.deleteCamera(cm.did);
				
				}
			}
		}
		
		
		
		
		
	}
	
	
	

	class StartPPPPThread implements Runnable {
		@Override
		public void run() {
			
			
			
			
			
			Cursor cursor = helper.fetchAllCameras();
			
			
			
			if (cursor != null) {//abc change
				if (cursor.getCount() >= 0) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							cameraListView.setVisibility(View.VISIBLE);
						}
					});
				} else {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							cameraListView.setVisibility(View.GONE);
						}
					});
				}
				
				if(AppPreference.getValue(IpcamClientActivity.this,AppKeys.KEY_IS_SUB_USER)!=null && AppPreference.getValue(IpcamClientActivity.this,AppKeys.KEY_IS_SUB_USER).equals("1"))
				{
					while (cursor.moveToNext()) {
						String name = cursor.getString(1);
						String did = cursor.getString(2);
						String user = cursor.getString(3);
						String pwd = cursor.getString(4);
						String isSettingAllowed = cursor.getString(5);
						listAdapter.AddCameraSubUserCase(name, did, user, pwd,isSettingAllowed);
					}
				}
				else
				{
					while (cursor.moveToNext()) {
						String name = cursor.getString(1);
						String did = cursor.getString(2);
						String user = cursor.getString(3);
						String pwd = cursor.getString(4);
						
						listAdapter.AddCamera(name, did, user, pwd);
					}
				}
				try
				{
				manageEmptyListMessage();
				}catch(Exception ex){}
				
				PPPPMsgHandler.sendEmptyMessage(12345);
			}
			if (cursor != null) {
				cursor.close();
			}
			try {
				NativeCaller.Init();
				Thread.sleep(500);
				StartCameraPPPP();
			} catch (Exception e) {

			}
		}
	}

	class StopPPPPThread implements Runnable {
		@Override
		public void run() {
			try {
				Thread.sleep(10);
				StopCameraPPPP();
			} catch (Exception e) {

			}
		}
	}

	private void StartCameraPPPP() {
		int count = listAdapter.getCount();
		for (int i = 0; i < count; i++) {
			CameraParamsBean bean = listAdapter.getOnItem(i);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
			StartPPPP(bean.getDid(), bean.getUser(), bean.getPwd());
			// new CamerStatuThread(bean).start();
		}
	}

	private void StopCameraPPPP() {
		int count = listAdapter.getCount();
		for (int i = 0; i < count; i++) {
			CameraParamsBean bean = listAdapter.getOnItem(i);
			//
			if (bean.getStatus() != ContentCommon.PPPP_STATUS_CONNECTING
					&& bean.getStatus() != ContentCommon.PPPP_STATUS_ON_LINE) {
				NativeCaller.StopPPPP(bean.getDid());
				try {
					Thread.sleep(300);
				} catch (Exception e) {
				}
				// new CamerStatuThread(bean).start();
				StartPPPP(bean.getDid(), bean.getUser(), bean.getPwd());
			}
		}
	}

	
	
	
	
	class CamerStatuThread extends Thread {
		String id;
		CameraParamsBean bean1;

		public CamerStatuThread(CameraParamsBean bean) {
			id = bean.getDid();
			bean1 = bean;
		}

		@Override
		public void run() {
			super.run();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (bean1.getStatus() == ContentCommon.PPPP_STATUS_CONNECTING) {
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						if (listAdapter.UpdataCameraStatus(id,
								ContentCommon.PPPP_STATUS_CONNECT_TIMEOUT)) {
							listAdapter.notifyDataSetChanged();
						}
					}
				});
			}
		}
	}

	private void findView() {
		cameraListView = (ListView) findViewById(R.id.listviewCamera);
		cameraEmptylayout=findViewById(R.id.cameraEmptylayout);
		imageButton_apphome = (ImageButton) findViewById(R.id.app_home);
		addCameraListHeader = (LinearLayout) findViewById(R.id.addvidicon_listitem);
		imageButtonRefresh = (ImageView) findViewById(R.id.refresh);
		mIvLogo = (ImageView) findViewById(R.id.logo);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		btnEdit = (ImageView) findViewById(R.id.main_edit);
		if(AppPreference.getValue(IpcamClientActivity.this,AppKeys.KEY_IS_SUB_USER)!=null && AppPreference.getValue(IpcamClientActivity.this,AppKeys.KEY_IS_SUB_USER).equals("1"))
		{
			addCameraListHeader.setVisibility(View.GONE);
			btnEdit.setVisibility(View.GONE);
		}
		
		
		addCameraListHeader.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent in = new Intent(IpcamClientActivity.this,
						AddCameraActivity.class);
				startActivity(in);
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);
				
				//cameraListView.setVisibility(View.VISIBLE);
				//CameraParamsBean temp=Manage_DB_Model.addCameraModelAndUserModelRelationInDB();
				//SystemValue.arrayList.add(Manage_DB_Model.addCameraModelAndUserModelRelationInDB());
				//listAdapter.AddCamera(temp.getName(), temp.getDid(), temp.getUser(), temp.getPwd());
				//listAdapter.notifyDataSetChanged();

			}
		});
		mIvLogo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				/*EventsListeners.getInstance().broadCastEvent(
						ListinerCategory.ON_BACK, ChangeEvents.ON_BACK, null);*/
				
				
				/*boolean moveToBackground=true;
				if(SystemValue.arrayList!=null && SystemValue.arrayList.size() > 0)
				{
					for(int i=0; i<SystemValue.arrayList.size();i++)
					{
						
						if(SystemValue.arrayList.get(i).getStatus()==ContentCommon.PPPP_STATUS_CONNECTING)
						{
							
							moveToBackground=false;
							break;
							
						}
						
						//

					}
					
					
					if(!moveToBackground)
					{
						
						Toast.makeText(IpcamClientActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();
						return;
					}
				}*/

				onBackPressed();
			}
		});
		imageButtonRefresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new GetDataTask().execute();
			}
		});
		imageButton_apphome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("tag", "screen_width:" + screen_width
						+ "  screen_height:" + screen_height);
				if (screen_width > 480 && screen_height > 800) {
					Intent intent = new Intent(IpcamClientActivity.this,
							IpcamClientActivityFourTest.class);
					startActivity(intent);
				} else {
					showToastLong(R.string.play_for_show_on);
				}
			}
		});
		
		btnSelectAll = (Button) findViewById(R.id.main_selectall);
		btnSelectReverse = (Button) findViewById(R.id.main_selectreverse);
		btnDelCamera = (Button) findViewById(R.id.main_delete_camera);
		delBottomLayout = (LinearLayout) findViewById(R.id.del_bottom_layout);
	}

	private class GetDataTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressBar.setVisibility(View.VISIBLE);
			imageButtonRefresh.setVisibility(View.GONE);
			if (SystemValue.ISRUN == false) {
				Intent intent = new Intent();
				intent.setClass(IpcamClientActivity.this, BridgeService.class);
				startService(intent);
				Log.d("tagx",
						"SystemValue.ISRUN == false--and--server is run to");
				new Thread() {
					public void run() {
						NativeCaller.PPPPInitial(ContentCommon.SERVER_STRING);
						NativeCaller.PPPPNetworkDetect();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
						}
						NativeCaller.Init();
					};
				}.start();

			}
		}

		@Override
		protected Void doInBackground(Void... params) {

			new Thread(new StopPPPPThread()).start();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			listAdapter.notifyDataSetChanged();
			progressBar.setVisibility(View.GONE);
			imageButtonRefresh.setVisibility(View.VISIBLE);
			super.onPostExecute(result);
		}

	}

	private void setControlListener() {
		cameraListView.setOnItemClickListener(this);
		btnEdit.setOnClickListener(this);
		btnSelectAll.setOnClickListener(this);
		btnSelectReverse.setOnClickListener(this);
		btnDelCamera.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_edit:
			int count = listAdapter.getCount();
			if (isEdited) {
				isEdited = false;
				addCameraListHeader.setVisibility(View.VISIBLE);

				delBottomLayout.setVisibility(View.GONE);
				// btnEdit.setText(getResources().getString(R.string.main_edit));
				cameraListView.setAdapter(listAdapter);
				
				manageEmptyListMessage();

			} else {
				if (count > 0) {
					addCameraListHeader.setVisibility(View.GONE);

					isEdited = true;
					delBottomLayout.setVisibility(View.VISIBLE);
					// btnEdit.setText(getResources().getString(R.string.done));
					cameraListView.setAdapter(editAdapter);
				} else {
					showToast(R.string.main_plea_addcam);
				}
			}
			break;
		case R.id.main_selectall:
			editAdapter.selectAll(true);
			editAdapter.notifyDataSetChanged();
			break;
		case R.id.main_selectreverse:
			editAdapter.reverseSelect(false);
			editAdapter.notifyDataSetChanged();
			break;
		case R.id.main_delete_camera:
			if (editAdapter.hasSelect) {
				showDelSureDialog();
			}
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 2) {// playActivity
			if (resultCode == 2) {
				String did = data.getStringExtra("did");
				showPlayLastBmp(did);
			}
		}
	}

	private void showPlayLastBmp(final String did) {
		new Thread() {
			public void run() {
				File div = new File(Environment.getExternalStorageDirectory(),
						"ipcam/pic");
				File file = new File(div, did + ".jpg");
				String filepath = file.getAbsolutePath();
				Bitmap bitmap = BitmapFactory.decodeFile(filepath);
				if (listAdapter.UpdateCameraImage(did, bitmap)) {
					PPPPMsgHandler.sendEmptyMessage(SNAPSHOT);
				}
			}
		}.start();

	}
	
	


	/*@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			showSureDialog(this);
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			// Date date = new Date();
			// if (timeTag == 0) {
			// timeOne = date.getSeconds();
			// timeTag = 1;
			// Toast.makeText(IpcamClientActivity.this,
			// R.string.main_show_back, 0).show();
			// } else if (timeTag == 1) {
			// timeTwo = date.getSeconds();
			// if (timeTwo - timeOne <= 3) {
			// Intent intent = new Intent("back");
			// sendBroadcast(intent);
			// timeTag = 0;
			// } else {
			// timeTag = 1;
			// Toast.makeText(IpcamClientActivity.this,
			// R.string.main_show_back, 0).show();
			// }
			// }
			
			//Toast.makeText(IpcamClientActivity.this, "Moving Back to CamMain", Toast.LENGTH_SHORT).show();
			
			isDestroyed=true;
			//EventsListeners.getInstance().broadCastEvent(
					//ListinerCategory.ON_BACK, ChangeEvents.ON_BACK, null);
			//finish();
			
			
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}*/

	//abc
	private String did;
	private Handler PPPPMsgHandler = new Handler() {
		public void handleMessage(Message msg) {
			
			
			
			Bundle bd = msg.getData();
			int msgParam = bd.getInt(STR_MSG_PARAM);
			int msgType = msg.what;
			did = bd.getString(STR_DID);
			Log.d("test", "did==" + did + "  msgType=" + msgType);
			switch (msgType) {
			case 12345:
				listAdapter.notifyDataSetChanged();
				break;
			case ContentCommon.PPPP_MSG_TYPE_PPPP_STATUS:
				Intent intent = new Intent("camera_status_change");
				intent.putExtra(ContentCommon.STR_CAMERA_ID, did);
				intent.putExtra(ContentCommon.STR_PPPP_STATUS, msgParam);
				sendBroadcast(intent);

				if (listAdapter.UpdataCameraStatus(did, msgParam)) {
					listAdapter.notifyDataSetChanged();
					if (msgParam == ContentCommon.PPPP_STATUS_ON_LINE) {
						NativeCaller.PPPPGetSystemParams(did,
								ContentCommon.MSG_TYPE_GET_PARAMS);
						// new Thread() {
						// public void run() {
						//
						// try {
						// Thread.sleep(500);
						// } catch (InterruptedException e) {
						// // TODO Auto-generated catch block
						// e.printStackTrace();
						// }
						// NativeCaller.PPPPCameraControl(did, 0, 1);
						// try {
						// Thread.sleep(500);
						// } catch (InterruptedException e) {
						// // TODO Auto-generated catch block
						// e.printStackTrace();
						// }
						// NativeCaller.PPPPCameraControl(did, 13, 500);
						// };
						// }.start();
					}
					if (msgParam == ContentCommon.PPPP_STATUS_INVALID_ID
							|| msgParam == ContentCommon.PPPP_STATUS_CONNECT_FAILED
							|| msgParam == ContentCommon.PPPP_STATUS_DEVICE_NOT_ON_LINE
							|| msgParam == ContentCommon.PPPP_STATUS_CONNECT_TIMEOUT
							|| msgParam == ContentCommon.PPPP_STATUS_CONNECT_ERRER
							|| msgParam == ContentCommon.PPPP_STATUS_INVALID_TIME) {
						// case ContentCommon.PPPP_STATUS_INVALID_TIME:
						// resid = R.string.set_user_time_pppp_statu;
						// break;
						NativeCaller.StopPPPP(did);
					}
				}
				break;
			case ContentCommon.PPPP_MSG_TYPE_PPPP_MODE:
				Log.d("shix", "shix:" + msgParam);
				if (listAdapter.UpdataCameraType(did, msgParam)) {
					listAdapter.notifyDataSetChanged();
				}
				break;
			case SNAPSHOT:
				listAdapter.notifyDataSetChanged();
				break;
			}
		}
	};
	private ProgressDialog mProgressDialog;

	/****
	 * 删除确定dialog
	 * */
	private void showDelSureDialog() {
		
		
		
		
		
		
		
		
final CustomAlertDialogueTwoButtons myASlertDialog=new CustomAlertDialogueTwoButtons(IpcamClientActivity.this,getString(R.string.delete_camera_msg));
		
		myASlertDialog.setListner(new CustomDialogueTwoButtonsClickListner() {
			
			@Override
			public void onCustomDialoguePositiveClick() {
				
				myASlertDialog.dismiss();
				
				
				new AsyncTask<Void, Void, Void>() {
					protected void onPreExecute() {
						/*progressDialog = new ProgressDialog(
								IpcamClientActivity.this,R.style.progressDialogTheme);
						progressDialog
								.setMessage(getResources().getString(
										R.string.main_show_delecting));
						progressDialog.setCancelable(false);
						progressDialog.show();*/
						
						WaitingStaticProgress.showProgressDialog("", IpcamClientActivity.this);
					};

					@Override
					protected Void doInBackground(Void... params) {
						ArrayList<String> didList = editAdapter
								.delCamera();
						Intent intent = new Intent(
								"del_add_modify_camera");
						for (int i = 0; i < didList.size(); i++) {
							String did = didList.get(i);
							if (delCameraFromdb(did)) {
								listAdapter.delCamera(did);
								
								
								Manage_DB_Model.deleteCameraModelAndUserModelRelationInDB(did,AppPreference.getValue(IpcamClientActivity.this, AppKeys.KEY_USER_ID));
								NativeCaller.StopPPPP(did);
								listAdapter.notifyDataSetChanged();
								intent.putExtra(
										ContentCommon.STR_CAMERA_ID,
										did);
								intent.putExtra("type",
										ContentCommon.DEL_CAMERA);
								sendBroadcast(intent);
							}
						}

						return null;
					}

					protected void onPostExecute(Void result) {
						
						
						
						WaitingStaticProgress.hideProgressDialog();
						
						
						if (editAdapter.getCount() == 0) {
							cameraListView.setVisibility(View.GONE);
						}
						editAdapter.notifyDataSetChanged();
						
						
						
						try
						{// to update listeners of bottom bar
						MainActivity  activity=(MainActivity) IpcamClientActivity.this.getParent();
						activity.updateTouchListnersOnBottomBar();
						}catch(Exception ex)
						{
							
						}
						
						
					};
				}.execute();
				
			}
			
			@Override
			public void onCustomDialogueNegativeClick() {
				
				myASlertDialog.dismiss();
				
			}
		});
		
		myASlertDialog.show();
		
		
		
		
		
		
		
		
		/*
		MaterialDialog dialog = new MaterialDialog.Builder(this)
				.content(R.string.del_alert)
				.positiveText(android.R.string.ok)
				.negativeText(android.R.string.cancel)
				.negativeColor(
						KisafaApplication.getAppResources().getColor(
								R.color.app_header_bg))
				.positiveColor(
						KisafaApplication.getAppResources().getColor(
								R.color.app_header_bg))
				.callback(new MaterialDialog.ButtonCallback() {
					@Override
					public void onPositive(MaterialDialog dialog) {
						

						
						
						
						new AsyncTask<Void, Void, Void>() {
							protected void onPreExecute() {
								progressDialog = new ProgressDialog(
										IpcamClientActivity.this,R.style.progressDialogTheme);
								progressDialog
										.setMessage(getResources().getString(
												R.string.main_show_delecting));
								progressDialog.setCancelable(false);
								progressDialog.show();
							};

							@Override
							protected Void doInBackground(Void... params) {
								ArrayList<String> didList = editAdapter
										.delCamera();
								Intent intent = new Intent(
										"del_add_modify_camera");
								for (int i = 0; i < didList.size(); i++) {
									String did = didList.get(i);
									if (delCameraFromdb(did)) {
										listAdapter.delCamera(did);
										
										
										Manage_DB_Model.deleteCameraModelAndUserModelRelationInDB(did,AppPreference.getValue(IpcamClientActivity.this, AppKeys.KEY_USER_ID));
										NativeCaller.StopPPPP(did);
										listAdapter.notifyDataSetChanged();
										intent.putExtra(
												ContentCommon.STR_CAMERA_ID,
												did);
										intent.putExtra("type",
												ContentCommon.DEL_CAMERA);
										sendBroadcast(intent);
									}
								}

								return null;
							}

							protected void onPostExecute(Void result) {
								
								
								
								progressDialog.cancel();
								if (editAdapter.getCount() == 0) {
									cameraListView.setVisibility(View.GONE);
								}
								editAdapter.notifyDataSetChanged();
								
								
							};
						}.execute();
					
					}
				}).build();
		dialog.show();*/
		
		
		
		/*MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
		builder.content(R.string.del_alert);
		builder.positiveText(R.string.str_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						
						
						
						new AsyncTask<Void, Void, Void>() {
							protected void onPreExecute() {
								progressDialog = new ProgressDialog(
										IpcamClientActivity.this);
								progressDialog
										.setMessage(getResources().getString(
												R.string.main_show_delecting));
								progressDialog.setCancelable(false);
								progressDialog.show();
							};

							@Override
							protected Void doInBackground(Void... params) {
								ArrayList<String> didList = editAdapter
										.delCamera();
								Intent intent = new Intent(
										"del_add_modify_camera");
								for (int i = 0; i < didList.size(); i++) {
									String did = didList.get(i);
									if (delCameraFromdb(did)) {
										listAdapter.delCamera(did);
										
										
										Manage_DB_Model.deleteCameraModelAndUserModelRelationInDB(did,AppPreference.getValue(IpcamClientActivity.this, AppKeys.KEY_USER_ID));
										NativeCaller.StopPPPP(did);
										listAdapter.notifyDataSetChanged();
										intent.putExtra(
												ContentCommon.STR_CAMERA_ID,
												did);
										intent.putExtra("type",
												ContentCommon.DEL_CAMERA);
										sendBroadcast(intent);
									}
								}

								return null;
							}

							protected void onPostExecute(Void result) {
								
								
								
								progressDialog.cancel();
								if (editAdapter.getCount() == 0) {
									cameraListView.setVisibility(View.GONE);
								}
								editAdapter.notifyDataSetChanged();
								
								
							};
						}.execute();
					}
				});
		builder.setNegativeButton(R.string.str_cancel, null);
		builder.show();*/
	}

	private synchronized boolean delCameraFromdb(String did) {
		boolean bRes = false;
		if (helper.deleteCamera(did)) {
			bRes = true;
		}
		return bRes;
	}

	private synchronized boolean UpdataCamera2db(String oldDID, String name,
			String did, String user, String pwd) {
		boolean bRes = false;
		if(did!=null)
			did=did.toUpperCase();
		if (helper.updateCamera(oldDID, name, did, user, pwd)) {
			bRes = true;
		}
		return bRes;
	}

	private synchronized void addCamera2db(String name, String did,
			String user, String pwd) {
		if(did!=null)
			did=did.toUpperCase();
		helper.createCamera(name, did, user, pwd);
	}

	private void initCameraList() {
		Cursor cursor = helper.fetchAllCameras();
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				cameraListView.setVisibility(View.VISIBLE);
			} else {
				cameraListView.setVisibility(View.GONE);
			}
			while (cursor.moveToNext()) {
				String name = cursor.getString(1);
				String did = cursor.getString(2);
				String user = cursor.getString(3);
				String pwd = cursor.getString(4);
				String isSettingAllowed = cursor.getString(5);
				//listAdapter.AddCamera(name, did, user, pwd,isSettingAllowed);
			}
			listAdapter.notifyDataSetChanged();
		}
		if (cursor != null) {
			cursor.close();
		}
	}

	@Override
	protected void onResume() {
		Log.d("IpcamClientActivity", "onResume()");
		super.onResume();
		
		upDateLanguageonConfigurationChange();
		
		manageEmptyListMessage();
		
	}
	
	
	public void manageEmptyListMessage()
	{
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				try
				{
				
				if(SystemValue.arrayList==null || SystemValue.arrayList.size()==0)
				{
				  cameraEmptylayout.setVisibility(View.VISIBLE);
				  cameraListView.setVisibility(View.GONE);
				}
				else
				{
					cameraEmptylayout.setVisibility(View.GONE);
					cameraListView.setVisibility(View.VISIBLE);
				}
				}catch(Exception ex){}
			}
		});
		
		
	}
	

	@Override
	protected void onRestart() {
		SystemValue.TAG_CAMERLIST = 0;
		listAdapter.notifyDataSetChanged();
		editAdapter.notifyDataSetChanged();
		Log.d("IpcamClientActivity", "onRestart()");
		super.onRestart();
	}

	
	//// abc123
	class CameraInfoReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(final Context context, Intent intent) {
			
			
			
			
			
			String action = intent.getAction();
			Log.e("zhaogenghuai", "CameraInfoReceiver");
			if ("other".equals(action)) {
				listAdapter.sendCameraStatus();
			} else {
				int option = intent.getIntExtra(ContentCommon.CAMERA_OPTION,
						ContentCommon.INVALID_OPTION);
				if (option == ContentCommon.INVALID_OPTION)
					return;
				String strOldDID = "";
				final String name = intent
						.getStringExtra(ContentCommon.STR_CAMERA_NAME);
				final String did = intent
						.getStringExtra(ContentCommon.STR_CAMERA_ID);
				final String user = intent
						.getStringExtra(ContentCommon.STR_CAMERA_USER);
				final String pwd = intent
						.getStringExtra(ContentCommon.STR_CAMERA_PWD);

				if (option == ContentCommon.EDIT_CAMERA) {
					strOldDID = intent
							.getStringExtra(ContentCommon.STR_CAMERA_OLD_ID);
					if (UpdataCamera2db(strOldDID, name, did, user, pwd)) {
						if (listAdapter.UpdateCamera(strOldDID, name, did,
								user, pwd)) {
							NativeCaller.PPPPGetSystemParams(did,
									ContentCommon.MSG_TYPE_GET_PARAMS);
							listAdapter.notifyDataSetChanged();
							NativeCaller.StopPPPP(did);
							StartPPPP(did, user, pwd);
						}
						editAdapter.modifyCamera(strOldDID, did, name, user,
								pwd);
						Intent intentChange = new Intent(
								"del_add_modify_camera");
						intentChange
								.putExtra("type", ContentCommon.EDIT_CAMERA);
						intentChange.putExtra(ContentCommon.STR_CAMERA_ID, did);
						intentChange.putExtra("olddid", strOldDID);
						intentChange.putExtra("name", name);
						sendBroadcast(intentChange);
						
						
						
						CameraParamsBean bean = new CameraParamsBean();
						bean.setAuthority(0);
						bean.setName(name);
						bean.setDid(did);
						bean.setUser(user);
						bean.setPwd(pwd);
						bean.setStatus(ContentCommon.PPPP_STATUS_UNKNOWN);
						bean.setMode(ContentCommon.PPPP_MODE_UNKNOWN);
						
						
						
						Manage_DB_Model.editCameraModelAndUserModelRelationInDB(bean,AppPreference.getValue(IpcamClientActivity.this, AppKeys.KEY_USER_ID));
						
						
						
					}
				} else if (option == ContentCommon.CHANGE_CAMERA_USER) {
					strOldDID = intent
							.getStringExtra(ContentCommon.STR_CAMERA_OLD_ID);
					if (listAdapter.UpdateCamera(strOldDID, name, did, user,
							pwd)) {
						listAdapter.notifyDataSetChanged();
						NativeCaller.StopPPPP(did);
						StartPPPP(did, user, pwd);
						if (editAdapter.modifyCamera(strOldDID, did, name,
								user, pwd)) {
							editAdapter.notifyDataSetChanged();
						}
					}
				} else {
					if (listAdapter.getCount() < 20) {
						
						String tempDid="";
						if(did!=null)
						{
							tempDid=did.toUpperCase();
						}
						
						if (listAdapter.AddCamera(name, tempDid, user, pwd)) {
							cameraListView.setVisibility(View.VISIBLE);
							listAdapter.notifyDataSetChanged();
							StartPPPP(tempDid, user, pwd);
							
							
							CameraParamsBean bean = new CameraParamsBean();
							bean.setAuthority(0);
							bean.setName(name);
							bean.setDid(tempDid);
							bean.setUser(user);
							bean.setPwd(pwd);
							bean.setStatus(ContentCommon.PPPP_STATUS_UNKNOWN);
							bean.setMode(ContentCommon.PPPP_MODE_UNKNOWN);
							
							
							Manage_DB_Model.createCameraModelAndUserModelRelationInDB(bean,AppPreference.getValue(IpcamClientActivity.this, AppKeys.KEY_USER_ID));
							
							
							
							
							new Thread() {
								public void run() {
									
									String tempDid="";
									if(did!=null)
									{
										tempDid=did.toUpperCase();
									}
									
									addCamera2db(name, tempDid, user, pwd);
									editAdapter.addCamera(name, tempDid, user, pwd);
									
									
									
									
									
									Intent intentAdd = new Intent(
											"del_add_modify_camera");
									intentAdd.putExtra("type",
											ContentCommon.ADD_CAMERA);
									intentAdd.putExtra(
											ContentCommon.STR_CAMERA_ID, did);
									intentAdd.putExtra("name", name);
									sendBroadcast(intentAdd);
									
									///abc1234
								}
							}.start();
						}
					} else {
						showToast(R.string.add_camer_no_add);
					}
				}
			}
		}
	}

	@Override
	protected void onStart() {
		Log.d("IpcamClientActivity", "onStart()");
		super.onStart();
		
		
		
		
		// if (receiver == null) {
		// receiver = new CameraInfoReceiver();
		// IntentFilter filter = new IntentFilter();
		// filter.addAction(ContentCommon.STR_CAMERA_INFO_RECEIVER);
		// filter.addAction("back");
		// filter.addAction("other");
		// registerReceiver(receiver, filter);
		// }
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (helper != null) {
			
			helper = null;
		}
		
		
		
		
		
		SystemValue.TAG_CAMERLIST = 0;
		unregisterReceiver(receiver);
		
		
		
		
	}

	public void showSetting(int position) {
		CameraParamsBean bean = listAdapter.getItemCamera(position);
		int status = bean.getStatus();
		if (status == ContentCommon.PPPP_STATUS_ON_LINE) {
			int authority = bean.isAuthority();
			// if (authority == 0) {
			Intent intent = new Intent(IpcamClientActivity.this,
					SettingActivity.class);
			intent.putExtra(ContentCommon.STR_CAMERA_ID, bean.getDid());
			intent.putExtra(ContentCommon.STR_CAMERA_NAME, bean.getName());
			intent.putExtra("authority", bean.isAuthority());
			startActivity(intent);
			KisafaApplication.perFormActivityNextTransition(this);
			// } else {
			// showToast(R.string.main_not_administrator);
			// }
		} else {
			showToast(R.string.main_setting_prompt);
		}
	}

	@Override
	public void BSMsgNotifyData(String did, int type, int param) {
		Log.d(TAG, "type:" + type + " param:" + param);
		
		
		//Toast.makeText(IpcamClientActivity.this, "calling", Toast.LENGTH_SHORT).show();
		
		Bundle bd = new Bundle();
		Message msg = PPPPMsgHandler.obtainMessage();
		msg.what = type;
		bd.putInt(STR_MSG_PARAM, param);
		bd.putString(STR_DID, did);
		msg.setData(bd);
		PPPPMsgHandler.sendMessage(msg);
	}

	@Override
	public void BSSnapshotNotify(String did, byte[] bImage, int len) {
		Bitmap bmp = BitmapFactory.decodeByteArray(bImage, 0, len);
		if (bmp == null) {
			Log.d(TAG, "bmp can't be decode...");
			return;
		}
		if (listAdapter.UpdateCameraImage(did, bmp)) {
			PPPPMsgHandler.sendEmptyMessage(SNAPSHOT);
		}
	}

	@Override
	public void callBackUserParams(String did, String user1, String pwd1,
			String user2, String pwd2, String user3, String pwd3, int mode) {
		Log.i("user", "user1:" + user1 + "   pwd1" + pwd1 + "\n" + "user2:"
				+ user2 + "   pwd2" + pwd2 + "\n" + "user3:" + user3
				+ "   pwd3" + pwd3 + "mode" + mode);
		listAdapter.upadeUserAuthority(did, user1, pwd1, user2, pwd2, user3,
				pwd3, mode);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// if (position == 0) {
		// Intent in = new Intent(IpcamClientActivity.this,
		// AddCameraActivity.class);
		// startActivity(in);
		// overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		// Log.d("tag", "header");
		// return;
		// }
		if (isEdited) {
			if (editAdapter != null) {
				CameraParamsBean bean = editAdapter.getItemCamera(position);
				String name = bean.getName();
				String did = bean.getDid();
				String user = bean.getUser();
				String pwd = bean.getPwd();
				Intent in = new Intent(IpcamClientActivity.this,
						AddCameraActivity.class);
				in.putExtra(ContentCommon.CAMERA_OPTION,
						ContentCommon.EDIT_CAMERA);
				in.putExtra(ContentCommon.STR_CAMERA_NAME, name);
				in.putExtra(ContentCommon.STR_CAMERA_ID, did);
				in.putExtra(ContentCommon.STR_CAMERA_USER, user);
				in.putExtra(ContentCommon.STR_CAMERA_PWD, pwd);
				startActivity(in);
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);
			}
		} else {
			CameraParamsBean bean = listAdapter.getOnItem(position);
			
			
			//Manage_DB_Model.deleteCameraModelAndUserModelRelationInDB(bean);
			//SystemValue.arrayList.remove(position);
			//listAdapter.notifyDataSetChanged();
			
			
			Log.d("test", "00000000");
			if (bean == null) {
				Log.d("test", "111111");
				return;
			}
			int status = bean.getStatus();
			Log.d("test", "22222222");
			if (status == ContentCommon.PPPP_STATUS_INVALID_ID
					|| status == ContentCommon.PPPP_STATUS_CONNECT_TIMEOUT
					|| status == ContentCommon.PPPP_STATUS_DEVICE_NOT_ON_LINE
					|| status == ContentCommon.PPPP_STATUS_CONNECT_FAILED
					|| status == ContentCommon.PPPP_STATUS_INVALID_TIME) {
				Log.d("test", "33333333");
				Log.d("test", "55555555");
				String did = bean.getDid();
				String user = bean.getUser();
				String pwd = bean.getPwd();
				StartPPPP(did, user, pwd);
				return;
			}
			Log.d("test", "4444444444");
			if (status != ContentCommon.PPPP_STATUS_ON_LINE) {

				return;
			}

			// ///////////////////////////////////////////////////////////////////////////
			String did = bean.getDid();
			String name = bean.getName();
			String user = bean.getUser();
			String pwd = bean.getPwd();
			int p2pMode = bean.getMode();
			int mode = bean.getKmode();

			Intent in = new Intent(IpcamClientActivity.this,
					PlayActivitySport.class);
			in.putExtra(ContentCommon.STR_CAMERA_TYPE,
					ContentCommon.CAMERA_TYPE_MJPEG);
			in.putExtra(ContentCommon.STR_STREAM_TYPE,
					ContentCommon.MJPEG_SUB_STREAM);
			in.putExtra(ContentCommon.STR_CAMERA_NAME, name);
			in.putExtra(ContentCommon.STR_CAMERA_ID, did);
			in.putExtra(ContentCommon.STR_CAMERA_USER, user);
			in.putExtra(ContentCommon.STR_CAMERA_PWD, pwd);
			in.putExtra(ContentCommon.STR_CAMERA_MODE, mode);
			in.putExtra("modep", p2pMode);
			startActivityForResult(in, 2);
			KisafaApplication.perFormActivityNextTransition(this);
			// /////////////////////////////////////////////////////////////////////
		}

	}
	
	
	
	
	@Override
	public void onBackPressed() {
		
		
		//NativeCaller.Free();
		
		/*Intent stopIntent = new Intent(IpcamClientActivity.this,
				BridgeService.class);
		
		stopService(stopIntent);*/
		
		//playInterface
		
		
		/*boolean moveToBackground=true;
		if(SystemValue.arrayList!=null && SystemValue.arrayList.size() > 0)
		{
			for(int i=0; i<SystemValue.arrayList.size();i++)
			{
				
				if(SystemValue.arrayList.get(i).getStatus()==ContentCommon.PPPP_STATUS_CONNECTING)
				{
					
					moveToBackground=false;
					break;
					
				}
				
				//

			}
			
			
			if(!moveToBackground)
			{
				
				Toast.makeText(IpcamClientActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();
				return;
			}
		}
		*/
		
		
		/*BridgeService.playInterface=null;
		BridgeService.ipcamClientInterface=null;
		BridgeService.wifiInterface=null;
		BridgeService.ipPlayInterface=null;
		BridgeService.userInterface=null;
		*/
		
		
		
		
		
		/*Intent intent = new Intent();
		
		intent.setClass(IpcamClientActivity.this, HomeActivity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
		
		super.onBackPressed();*/
		
		MainActivity activity=(MainActivity) getParent();
		
		activity.onBackPressed();
		
		
		
	}
	
	private void showProgressDialog(String text, int progress) {
		mProgressDialog = new ProgressDialog(IpcamClientActivity.this,R.style.progressDialogTheme);
		mProgressDialog.setMessage(text);
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(progress);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();
	}
    
	
	
	
    
}