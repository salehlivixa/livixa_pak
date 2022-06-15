package com.livixa.apacam.client.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.livixa.client.BridgeService;
import com.livixa.client.R;
import com.livixa.client.R.string;
import com.livixa.client.StartActivity;
import com.livixa.apacam.client.activity.ShSwitchView.OnSwitchStateChangeListener;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.appconfig.AppKeys.LANGUAGES;
import com.livixa.apacam.client.base.KisafaActivity;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_Data_to_Server_WebServiceCall;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_Sub_User_Cam_Association;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_Data_to_Server_WebServiceCall.Data_Status;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.client.response.request.RequestResponse;
import com.livixa.apacam.client.response.tariff_energy.DecimalFilter;
import com.livixa.apacam.client.utility.ACache;
import com.livixa.apacam.client.utility.AppConstants;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons.CustomDialogueTwoButtonsClickListner;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue.CustomDialogueClickListner;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.dbmodel.Camera_Model;
import com.livixa.apacam.dbmodel.ESP_Result_Model;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.dbmodel.Tariff_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import com.livixa.apacam.dbmodel.User_Room_Model;
import com.livixa.apacam.services.Sync_Service;
import object.p2pipcam.system.SystemValue;
import object.p2pipcam.utils.DataBaseHelper;
import retrofit2.Call;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mrengineer13.snackbar.SnackBar;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.StorageUtils;

;

public class SettingsActivity extends Activity implements
		OnClickListener, ServerConnectListener {

	// Layout Variables
	private View view;
	private ImageView mIvBack;
	private ImageView mIvHome;
	private RelativeLayout rl_edit_users;
	private RelativeLayout rl_add_edit_rooms;
	private RelativeLayout rl_add_edit_switch;
	private RelativeLayout rl_configureSwitches;
	private RelativeLayout rl_inside_home;
	private RelativeLayout rl_privacy_and_security;
	private RelativeLayout rl_control_remotly;
	private RelativeLayout rl_subscription;
	
	private RelativeLayout rl_profile;
	private RelativeLayout rl_changePass;
	private RelativeLayout rl_about;
	
	private RelativeLayout rl_energy;
	
	private RelativeLayout rl_language;
	
	
	private ShSwitchView remotlySwitch;
	
	private LinearLayout   topLinearLyout;
	private RelativeLayout mRlLogout;
	private ProgressDialog mProgressDialog;

	// Local
	private String ACTIVITY_TITLE = "Settings";
	private Map<String, String> map;

	private DataBaseHelper helper = null;
	private Context mContext;
	
	
	// Class Methods
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setOrientation();
		
		
		if(AppPreference.getValue(SettingsActivity.this,AppKeys.KEY_IS_SUB_USER)!=null && AppPreference.getValue(SettingsActivity.this,AppKeys.KEY_IS_SUB_USER).equals("1"))
		{
			
			setContentView(R.layout.activity_settings_subuser);
		}
		else
		{
			setContentView(R.layout.activity_settings);
		}
		
		
				initComponents();
				
				setClickListner(SettingsActivity.this);
				
				
				
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(SettingsActivity.this);
			hideui();
		
	}

	// Helping Methods
	public void initComponents() {
		//super.initUIComponents(ACTIVITY_TITLE);
		mContext = this;
		view = (View) findViewById(R.id.rl_root);
		mRlLogout = (RelativeLayout) findViewById(R.id.rl_logout);
		
		topLinearLyout=(LinearLayout) findViewById(R.id.ll_1);
		rl_add_edit_rooms=(RelativeLayout) findViewById(R.id.rl_add_edit_rooms);
		rl_add_edit_switch=(RelativeLayout) findViewById(R.id.rl_add_edit_switch);
		rl_configureSwitches=(RelativeLayout) findViewById(R.id.rl_configureSwitches);
		rl_inside_home=(RelativeLayout) findViewById(R.id.rl_inside_home);
		remotlySwitch=(ShSwitchView) findViewById(R.id.remotlySwitch);
		rl_profile=(RelativeLayout) findViewById(R.id.rl_profile);
		rl_about=(RelativeLayout) findViewById(R.id.rl_about);
		rl_privacy_and_security=(RelativeLayout)findViewById(R.id.rl_privacy_and_security);
		rl_control_remotly=(RelativeLayout)findViewById(R.id.rl_control_remotly);
		rl_subscription=(RelativeLayout)findViewById(R.id.rl_subscription);
		
		rl_energy=(RelativeLayout) findViewById(R.id.rl_energy);
		rl_language=(RelativeLayout) findViewById(R.id.rl_language);
		mIvBack = (ImageView) findViewById(R.id.iv_back);
		
		rl_edit_users=(RelativeLayout) findViewById(R.id.rl_edit_users);
//		rl_changePass=(RelativeLayout) findViewById(R.id.rl_changePass);
		
		
		
		
			try
			{
			
			remotlySwitch.setOn(AppPreference.getSavedData(mContext, AppKeys.KEY_REMOTE_OPTION_TAG));
			
			}catch (Exception e) {
				
			}
		
		remotlySwitch.setOnSwitchStateChangeListener(new OnSwitchStateChangeListener() {
			
			@Override
			public void onSwitchStateChange(boolean isOn) {
				
				
				if(isOn)
				{
					//Toast.makeText(SettingsActivity.this, "On", Toast.LENGTH_SHORT).show();
					
					AppPreference.saveData(mContext, true, AppKeys.KEY_REMOTE_OPTION_TAG);
				}
				else
				{
					//Toast.makeText(SettingsActivity.this, "Off", Toast.LENGTH_SHORT).show();
					
					AppPreference.saveData(mContext, false, AppKeys.KEY_REMOTE_OPTION_TAG);
				}
				
			}
		});
		
		
		
	}

	public void setClickListner(OnClickListener onclick) {
		mRlLogout.setOnClickListener(onclick);
		mIvBack.setOnClickListener(onclick);
		
		
		if(AppPreference.getValue(SettingsActivity.this,AppKeys.KEY_IS_SUB_USER)!=null && AppPreference.getValue(SettingsActivity.this,AppKeys.KEY_IS_SUB_USER).equals("1"))
		{
		
			rl_inside_home.setOnClickListener(onclick);
			rl_profile.setOnClickListener(onclick);
			rl_changePass.setOnClickListener(onclick);
			rl_about.setOnClickListener(onclick);
			//rl_energy.setOnClickListener(onclick);
			
			
		}
		else
		{
			rl_edit_users.setOnClickListener(onclick);
			rl_add_edit_rooms.setOnClickListener(onclick);
			rl_add_edit_switch.setOnClickListener(onclick);
			rl_configureSwitches.setOnClickListener(onclick);
			rl_inside_home.setOnClickListener(onclick);
			rl_privacy_and_security.setOnClickListener(onclick);
//			rl_profile.setOnClickListener(onclick);
//			rl_changePass.setOnClickListener(onclick);
     		rl_about.setOnClickListener(onclick);
			rl_energy.setOnClickListener(onclick);
			rl_language.setOnClickListener(onclick);
			rl_subscription.setOnClickListener(onclick);
			
		}
		
		
	}

	public void setOrientation() {
		if (AppPreference.getSavedData(mContext, AppKeys.KEY_IS_LANDSCAPE)) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
	}

	private void showProgressDialog(String text, int progress) {
		mProgressDialog = new ProgressDialog(mContext,R.style.progressDialogTheme);
		mProgressDialog.setMessage(text);
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(progress);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();
	}

	public void callIntentWithFlag() {
		
		try
		{
		Intent intent = new Intent();
		intent = new Intent(mContext, LoginActivity.class);
		intent.putExtra("IsUserLogOut", true);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
		finish();
		KisafaApplication.perFormActivityBackTransition(this);
			
			//Runtime.getRuntime().exit(0);
		/*
		Intent i = getBaseContext().getPackageManager()
	             .getLaunchIntentForPackage( getBaseContext().getPackageName() );
	      i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
	      startActivity(i);*/
		
		//overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		//KisafaApplication.perFormActivityBackTransition(this);
		
		
		/* 
		Intent mStartActivity = new Intent(this, LoginActivity.class);
		mStartActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		int mPendingIntentId = 123456;
		PendingIntent mPendingIntent = PendingIntent.getActivity(this, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager mgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
		//System.exit(0);*/
		 //Runtime.getRuntime().exit(0);	*/
		 
		 	
		
		
		}catch(Exception ex)
		{
			
		}
		
	}
	
	
	

	private void showDialog(String text) {
		
		
		
		
		MaterialDialog dialog = new MaterialDialog.Builder(mContext)
				.content(text)
				.positiveText("Yes")
				.negativeText("No")
				.negativeColor(
						KisafaApplication.getAppResources().getColor(
								R.color.app_header_bg))
				.positiveColor(
						KisafaApplication.getAppResources().getColor(
								R.color.app_header_bg))
				.callback(new MaterialDialog.ButtonCallback() {
					@Override
					public void onPositive(MaterialDialog dialog) {
						logout();
					}
				}).build();
		dialog.show();
	}

	// Request
	public void logout() {
		map = new HashMap<String, String>();
		map.put("session", AppPreference.getValue(this, AppKeys.KEY_SESSION));
		map.put(AppKeys.KEY_CURRENT_LANGUAGE, AppPreference.getValue(this, AppKeys.KEY_CURRENT_LANGUAGE));
		//showProgressDialog("Signing out...", 100);
		WaitingStaticProgress.showProgressDialog("", SettingsActivity.this);
		ApiService service = KisafaApplication.getRestClient().getApiService();
		Call<RequestResponse> call = service.logout(map);
		call.enqueue(new RestCallback<RequestResponse>(this,
				ServerCodes.ServerRequestCodes.LOGOUT_REQUEST_CODE, mContext));
	}

	@Override
	public void onSuccess(ServerResponse response,String raw) {
		/*if (mProgressDialog != null) {
			mProgressDialog.hide();
		}*/
		
		WaitingStaticProgress.hideProgressDialog();
		
		if (response.getRequestCode() == ServerCodes.ServerRequestCodes.LOGOUT_REQUEST_CODE) {
			RequestResponse requestResponse = (RequestResponse) response;
			if (requestResponse.getShMeta().getShErrorCode()
					.equalsIgnoreCase("0")) {
				AppPreference.saveData(this, false, AppKeys.KEY_IS_LOGIN);
				AppPreference.saveValue(this, null, AppKeys.KEY_SESSION);
				ACache.get(this).remove(AppConstants.USER_OBJECT);
				
				
				
				try
				{
					Sync_Service.stopWebserviceCalling=true;
					Intent in = new Intent(SettingsActivity.this,Sync_Service.class);
					stopService(in);
					
				}
				catch(Exception ex)
				{
					
				}	
				
				
				try
				{
					if(SystemValue.arrayList!=null)
					SystemValue.arrayList.clear();
					helper.deleteAllCameras();
					//Toast.makeText(mContext, "Cleared", Toast.LENGTH_SHORT).show();
				}
				catch(Exception ex)
				{
					ex.toString();
				}
				try
				{
					new Delete().from(SUB_USER_DB.class).execute();
					new Delete().from(Camera_Model.class).execute();
					new Delete().from(User_Camera_Model.class).execute();
					new Delete().from(Switch_Model.class).execute();
					new Delete().from(Room_Model.class).execute();
					new Delete().from(User_Room_Model.class).execute();
					new Delete().from(ESP_Result_Model.class).execute();
					
					new Delete().from(Mood_Model.class).execute();
					
					new Delete().from(Tariff_Model.class).execute();
					
					
					
					
					AppPreference.saveData(mContext, false, AppKeys.KEY_REMOTE_OPTION_TAG);
					
					
					ImageLoader.getInstance().clearMemoryCache();
					ImageLoader.getInstance().clearDiskCache();
				
				}catch(Exception ex)
				{
					
				}
				//stop sync service
				
				
				RemoveCameraServiceThreadAndActivityIfRunning();
				
				mIvBack.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						
						callIntentWithFlag();
						
					}
				}, 300);
				
				
				
				
				
			} else {
				onFailure(requestResponse.getShMeta().getShMessage());
			}
		}
	}

	@Override
	public void onFailure(ServerResponse response) {
		onFailure(response.getMessage());
	}

	public void onFailure(String retrofitError) {
		
			WaitingStaticProgress.hideProgressDialog();
		
		new SnackBar.Builder((Activity) mContext).withMessage(retrofitError)
				.withDuration(SnackBar.SHORT_SNACK).show();
	}

	// Override Methods
	@Override
	public void onClick(View v) {
		 
		Intent intent = null;
		switch (v.getId()) {
		case R.id.rl_logout:
			//showDialog("Are you sure to logout?");
			
			
			
			
			    final Sync_Data_to_Server_WebServiceCall sync_Data_to_Server_WebServiceCall=new Sync_Data_to_Server_WebServiceCall(SettingsActivity.this);
	    	   
			    if(sync_Data_to_Server_WebServiceCall.isSyncedRequired())
		   		{
	    		  
			    	
			    	final CustomAlertDialogueTwoButtons myASlertDialog=new CustomAlertDialogueTwoButtons(SettingsActivity.this,getResources().getString(string.Yourdataneedstobesyncedwithserverlogout));
					
					myASlertDialog.setListner(new CustomDialogueTwoButtonsClickListner() {
						
						@Override
						public void onCustomDialoguePositiveClick() {
							
							myASlertDialog.dismiss();
							
							helper = DataBaseHelper.getInstance(SettingsActivity.this);
							
							logout();
							
						}
						
						@Override
						public void onCustomDialogueNegativeClick() {
							
							myASlertDialog.dismiss();
							
						}
					});
					
					myASlertDialog.show();
			    	
				}
			    else
			    {
			    	final CustomAlertDialogueTwoButtons myASlertDialog=new CustomAlertDialogueTwoButtons(SettingsActivity.this,getResources().getString(string.Areyousuretologout));
					
					myASlertDialog.setListner(new CustomDialogueTwoButtonsClickListner() {
						
						@Override
						public void onCustomDialoguePositiveClick() {
							
							myASlertDialog.dismiss();
							
							helper = DataBaseHelper.getInstance(SettingsActivity.this);
							
							logout();
							
						}
						
						@Override
						public void onCustomDialogueNegativeClick() {
							
							myASlertDialog.dismiss();
							
						}
					});
					
					myASlertDialog.show();
			    	
			    }
			
			
			
			
			break;
		case R.id.iv_back:
			
			intent = new Intent(SettingsActivity.this, HomeActivity.class);
			
			startActivity(intent);
			
			finish();
			
			
			KisafaApplication.perFormActivityBackTransition(mContext);
			break;
		
		case R.id.rl_edit_users:
			
			intent = new Intent(this, Add_OR_Edit_UserActivity.class);
			startActivity(intent);
			finish();
			//overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
			
			KisafaApplication.perFormActivityNextTransition(mContext);
			
			//overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
			break;
		case R.id.rl_add_edit_rooms:
			
			intent = new Intent(this, Add_Edit_RoomsActivity.class);
			startActivity(intent);
			finish();
			KisafaApplication.perFormActivityNextTransition(mContext);
			//overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
			break;
		case R.id.rl_add_edit_switch:
			
			intent = new Intent(this, Add_Edit_SwitchActivity.class);
			startActivity(intent);
			finish();
			KisafaApplication.perFormActivityNextTransition(mContext);
			//overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
			break;
		case R.id.rl_configureSwitches:
			
			intent = new Intent(this, SwitchConfigurationActivity.class);
			startActivity(intent);
			finish();
			KisafaApplication.perFormActivityNextTransition(mContext);
			//overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
			break;
			
		case R.id.rl_inside_home:
			
			intent = new Intent(this, SwitchScanActivity.class);
			startActivity(intent);
			finish();
			KisafaApplication.perFormActivityNextTransition(mContext);
			
			
			
			break;	
			
			case R.id.rl_profile:
			
			intent = new Intent(this, UpdateProfileActivity.class);
			startActivity(intent);
			finish();
			KisafaApplication.perFormActivityNextTransition(mContext);
			
			
			
			break;	
			
			case R.id.rl_changePass:
				
				intent = new Intent(this, ChangePasswordActivity.class);
				startActivity(intent);
				finish();
				KisafaApplication.perFormActivityNextTransition(mContext);
				
				break;	
				
			case R.id.rl_about:
				
				intent = new Intent(this, AboutActivity.class);
				startActivity(intent);
				finish();
				KisafaApplication.perFormActivityNextTransition(mContext);
				
				break;	
				

			case R.id.rl_energy:
				intent = new Intent(this, TariffMainActivity.class);
				startActivity(intent);
				finish();
				KisafaApplication.perFormActivityNextTransition(mContext);
				
				break;	
				
				
			case R.id.rl_language:
				
				LanguageAndCurrencySettingPopup();
				
				break;

			case R.id.rl_notification:
				intent = new Intent(this, FaqActivity.class);
				startActivity(intent);
				finish();
				KisafaApplication.perFormActivityNextTransition(mContext);
				break;

			case R.id.rl_privacy_and_security:
				intent = new Intent(this, Privacyandsecurity.class);
				startActivity(intent);
				finish();
				KisafaApplication.perFormActivityNextTransition(mContext);
				break;

			case R.id.rl_subscription:
				intent = new Intent(this, Subscription_packages.class);
				startActivity(intent);
				finish();
				KisafaApplication.perFormActivityNextTransition(mContext);
				break;




			default:
			break;
		}
	}
	
	
	
	@Override
	public void onBackPressed() {
		 
		super.onBackPressed();
		
		
		
		Intent intent = new Intent();
		intent = new Intent(SettingsActivity.this, HomeActivity.class);
		/*intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);*/
		startActivity(intent);
		/*overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);*/
		
		KisafaApplication.perFormActivityBackTransition(mContext);
		finish();
	}

	
	
	
	
	
	
	public void onhomeButttonClick(View view)
	{
		
		Intent intent = new Intent();
		intent = new Intent(SettingsActivity.this, HomeActivity.class);
		/*intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);*/
		startActivity(intent);
		KisafaApplication.perFormActivityBackTransition(mContext);
		finish();
	}
	
	
	private void LanguageAndCurrencySettingPopup() {

		 LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		 View popupView = layoutInflater.inflate(R.layout.language_currency_setting_popup, null);
		
		TextView tv_cancel=(TextView) popupView.findViewById(R.id.tv_Cancel);
		TextView tv_Done=(TextView) popupView.findViewById(R.id.tv_Done);
		
		final View  languageView=popupView.findViewById(R.id.linearLayout1);
		
		final View  currencyView=popupView.findViewById(R.id.currencyLY);
		
		final  TextView  engTV=(TextView) popupView.findViewById(R.id.engTV);
		
		final  TextView  curTV=(TextView) popupView.findViewById(R.id.curTV);
		
		
		if(KisafaApplication.currentAppLanguage.equals(LANGUAGES.ENGLISH))
		{
			
			String curentCrncy=AppPreference.getValue(SettingsActivity.this, AppKeys.KEY_CURRENT_CURRENCY);
			
			
			engTV.setText(getString(string.ENGLISH));
			
			if(curentCrncy==null || curentCrncy.equals("USD"))
			{
				curTV.setText(getString(string.USD));
			}
			else
			{
				curTV.setText(getString(string.SAR));
			}
			
			
		}
		else
		{
			String curentCrncy=AppPreference.getValue(SettingsActivity.this, AppKeys.KEY_CURRENT_CURRENCY);
			
			
			engTV.setText(getString(string.ARABIC));
			
			if(curentCrncy==null || curentCrncy.equals("دولار أمريكي"))
			{
				curTV.setText(getString(string.USD));
			}
			else
			{
				curTV.setText(getString(string.SAR));
			}
			
		}
		
		
		final PopupWindow popupWindow=new PopupWindow(popupView,LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);

		 popupWindow.setTouchable(true);
		 popupWindow.setFocusable(true);
		 
		 popupWindow.setAnimationStyle(R.style.BottonUpAnimation);
		 
		 popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

		 popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
		 
		 
		 
		 
		 languageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					LanguageCallPopup(engTV);
					
				}
			});
		 
		 
		 
		 
		 currencyView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					CurrencyCallPopup(curTV);
				}
			});
		 
		
		 
		 tv_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
				}
			});
		 
		 tv_Done.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					
					popupWindow.dismiss();
					
					
					
					
				}
			});
		 
		 
		
	}
	
	
	
	
	private void LanguageCallPopup(final TextView language) {

		 LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		 View popupView = layoutInflater.inflate(R.layout.select_language, null);
		
		TextView tv_cancel=(TextView) popupView.findViewById(R.id.tv_cancel);
		
		TextView tv_englishSelection=(TextView) popupView.findViewById(R.id.tv_englishSelection);
		
		TextView tv_arabicSelection=(TextView) popupView.findViewById(R.id.tv_arabicSelection);
		
		
		
		

		final PopupWindow popupWindow=new PopupWindow(popupView,
		         LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,        
		 true);

		 popupWindow.setTouchable(true);
		 popupWindow.setFocusable(true);
		 
		 popupWindow.setAnimationStyle(R.style.BottonUpAnimation);
		 
		

		 popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
		 
		 tv_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
				}
			});
		 
		 
		 tv_englishSelection.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					
					if(language.getText().toString().equals(getString(string.ENGLISH)))
					{
						
						final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(SettingsActivity.this,getString(string.ENGLISH) +" "+ getString(string.isalreadyappscurrentlanguage));
						
						cusDial.setListner(new CustomDialogueClickListner() {
							
							@Override
							public void onCustomDialogueClick() {
								
								cusDial.dismiss();
								
							}
						});
						
						
						cusDial.show();
						
					}
					else
					{
						
						final CustomAlertDialogueTwoButtons myASlertDialog=new CustomAlertDialogueTwoButtons(SettingsActivity.this,getString(string.Areyousuretochangeyourlanguageto)+" " +getString(string.ENGLISH));
						myASlertDialog.setListner(new CustomDialogueTwoButtonsClickListner() {
							@Override
							public void onCustomDialoguePositiveClick() {
								myASlertDialog.dismiss();
								language.setText(getString(string.ENGLISH));
								
								try
								{
									Locale locale = new Locale("en");
									Locale.setDefault(locale);
									Configuration config = getBaseContext().getResources().getConfiguration();
									config.locale = locale;
									getBaseContext().getResources().updateConfiguration(config,
									      getBaseContext().getResources().getDisplayMetrics());
									
								}catch(Exception ex)
								{
									ex.toString();
									ex.printStackTrace();
								}
								
								AppPreference.saveValue(getApplicationContext(),LANGUAGES.ENGLISH.getValue(), AppKeys.KEY_CURRENT_LANGUAGE);
								
								KisafaApplication.currentAppLanguage=LANGUAGES.ENGLISH;
								
								
								Sync_Service.removeWaringMessageOnLanguageChange();
								
								/*Intent  intnt=getIntent();
								finish();
								startActivity(intnt);*/
								
								RemoveCameraServiceThreadAndActivityIfRunning();
								
								mIvBack.postDelayed(new Runnable() {
									
									@Override
									public void run() {
										
										Intent intent = new Intent();
										intent = new Intent(mContext, SettingsActivity.class);
										intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
										startActivity(intent);
										finish();
										
									}
								}, 300);
							}
							
							@Override
							public void onCustomDialogueNegativeClick() {
								
								myASlertDialog.dismiss();
								}
						});
						
						myASlertDialog.show();
					}
					
					
					
				}
			});
		 
		 tv_arabicSelection.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					if(language.getText().toString().equals(getString(string.ARABIC)))
					{
						
						final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(SettingsActivity.this,getString(string.ARABIC) +" "+ getString(string.isalreadyappscurrentlanguage));
						
						cusDial.setListner(new CustomDialogueClickListner() {
							
							@Override
							public void onCustomDialogueClick() {
								
								cusDial.dismiss();
								
							}
						});
						
						
						cusDial.show();
						
					}
					else
					{
						
						final CustomAlertDialogueTwoButtons myASlertDialog=new CustomAlertDialogueTwoButtons(SettingsActivity.this,getString(string.Areyousuretochangeyourlanguageto)+" " +getString(string.ARABIC));
						myASlertDialog.setListner(new CustomDialogueTwoButtonsClickListner() {
							@Override
							public void onCustomDialoguePositiveClick() {
								myASlertDialog.dismiss();
								language.setText(getString(string.ARABIC));
								
								try
								{
									Locale locale = new Locale("ar");
									Locale.setDefault(locale);
									Configuration config = getBaseContext().getResources().getConfiguration();
									config.locale = locale;
									getBaseContext().getResources().updateConfiguration(config,
									      getBaseContext().getResources().getDisplayMetrics());
									
								}catch(Exception ex)
								{
								ex.printStackTrace();
									ex.toString();
								}
								
								AppPreference.saveValue(getApplicationContext(),LANGUAGES.ARABIC.getValue(), AppKeys.KEY_CURRENT_LANGUAGE);
								
								
								KisafaApplication.currentAppLanguage=LANGUAGES.ARABIC;
								
								Sync_Service.removeWaringMessageOnLanguageChange();
								
								/*Intent  intnt=getIntent();
								finish();
								startActivity(intnt);*/
								
								RemoveCameraServiceThreadAndActivityIfRunning();
								
								mIvBack.postDelayed(new Runnable() {
									
									@Override
									public void run() {
										
										Intent intent = new Intent();
										intent = new Intent(mContext, SettingsActivity.class);
										intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
										startActivity(intent);
										finish();
										
									}
								}, 300);
								
								
							}
							
							@Override
							public void onCustomDialogueNegativeClick() {
								
								myASlertDialog.dismiss();
								}
						});
						
						myASlertDialog.show();
					}
					
				}
			});
		 
		 
		 
		 
		
	}
	
	
	
	private void CurrencyCallPopup(final TextView currency) {

		 LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		 View popupView = layoutInflater.inflate(R.layout.select_currency, null);
		
		TextView tv_cancel=(TextView) popupView.findViewById(R.id.tv_cancel);
		
		TextView tv_USD_Selection=(TextView) popupView.findViewById(R.id.tv_USD_Selection);
		
		TextView tv_SAR_Selection=(TextView) popupView.findViewById(R.id.tv_SAR_Selection);
		
		
		
		

		final PopupWindow popupWindow=new PopupWindow(popupView,
		         LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,        
		 true);

		 popupWindow.setTouchable(true);
		 popupWindow.setFocusable(true);
		 
		 popupWindow.setAnimationStyle(R.style.BottonUpAnimation);
		 
		

		 popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
		 
		 tv_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
				}
			});
		 
		 
		 tv_USD_Selection.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					
					currency.setText(getString(string.USD));
					
					
					
					Change_Currency change_Currency=new Change_Currency(getString(string.USD),mContext);
				}
			});
		 
		 tv_SAR_Selection.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					currency.setText(getString(string.SAR));
					
					
					
					Change_Currency change_Currency=new Change_Currency(getString(string.SAR),mContext);
					
				}
			});
		 
		 
		 
		 
		
	}
	
	private boolean isMyServiceRunning(Class<?> serviceClass) {
	    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (serviceClass.getName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	private void RemoveCameraServiceThreadAndActivityIfRunning()
	{
		try
		{
		if(isMyServiceRunning(BridgeService.class))
		{
			Intent in = new Intent(this,BridgeService.class);
			stopService(in);
		}
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	public void hideui(){
		rl_control_remotly.setClickable(false);

		rl_edit_users.setClickable(false);

		remotlySwitch.setVisibility(View.GONE);
		ArrayList<String> value = KisafaApplication.getSubscription(SettingsActivity.this);
		if (value != null && value.size() != 0) {
			String id = value.get(0);

			if (id.equals("1")) {
				rl_edit_users.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						Toast.makeText(getApplicationContext(),"You are not authorized for this feature",Toast.LENGTH_LONG).show();
					}
				});

				rl_control_remotly.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						Toast toast =Toast.makeText(getApplicationContext(),"You are not authorized for this feature",Toast.LENGTH_LONG);
						toast.show();
					}
				});

			} else if (id.equals("2")) {
				rl_edit_users.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						Toast.makeText(getApplicationContext(),"You are not authorized for this feature",Toast.LENGTH_LONG).show();
					}
				});

				rl_control_remotly.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						Toast toast =Toast.makeText(getApplicationContext(),"You are not authorized for this feature",Toast.LENGTH_LONG);
						toast.show();
					}
				});

			}else if(id.equals("3")){
				rl_control_remotly.setClickable(true);
				rl_edit_users.setClickable(true);
				remotlySwitch.setVisibility(View.GONE);
			}else if(id.equals("4")){
				rl_control_remotly.setClickable(true);
				rl_edit_users.setClickable(true);
				remotlySwitch.setVisibility(View.VISIBLE);
			}
		}


	}
}
