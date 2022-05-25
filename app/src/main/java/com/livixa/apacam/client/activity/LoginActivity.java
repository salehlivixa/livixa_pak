package com.livixa.apacam.client.activity;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

import com.activeandroid.query.Delete;
import com.github.mrengineer13.snackbar.SnackBar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kisafa.user.profile.USER_Model;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.appconfig.AppKeys.LANGUAGES;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.AppWebServices;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.login.LoadAllDataFromServer;
import com.livixa.apacam.client.response.login.LoginResponse;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.client.response.login.ShResult;
import com.livixa.apacam.client.response.login._SubUser;
import com.livixa.apacam.client.utility.ACache;
import com.livixa.apacam.client.utility.AppConstants;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.AppUtil;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons.CustomDialogueTwoButtonsClickListner;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.client.MainActivity;
import com.livixa.client.R;
import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.nativecaller.NativeCaller;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class LoginActivity extends Activity implements OnClickListener,
		ServerConnectListener {

	// Layout Variables
	private View view;
	private EditText mEtEmail;
	private EditText mEtPassword;
	private Button mBtnLogin;
	private TextView mTvForgot;
	private TextView mTvSignUp;
	private ProgressDialog mProgressDialog;

//	boimetric
	private ImageView Fingerlogin;
	private static  final int REQUEST_CODE = 101010;
	private Executor executor;
	private BiometricPrompt biometricPrompt;
	private BiometricPrompt.PromptInfo promptInfo;

	// Local
	private String ACTIVITY_TITLE = "Log In";
	private Map<String, String> map;
	
	RelativeLayout loginSc;
	
	View tv_message_lyout;
	private LoginActivity mContext;

	// Class Methods
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setOrientation();
		setContentView(R.layout.activity_login);

		initComponents();
		//getSupportActionBar().hide();
		setClickListner(this);

//		biometric work

//		BiometricManager biometricManager = BiometricManager.from(this);
//		switch (biometricManager.canAuthenticate(BIOMETRIC_STRONG | DEVICE_CREDENTIAL)) {
//			case BiometricManager.BIOMETRIC_SUCCESS:
//				Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
//				break;
//			case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
//				Log.e("MY_APP_TAG", "No biometric features available on this device.");
//				break;
//			case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
//				Log.e("MY_APP_TAG", "Biometric features are currently unavailable.");
//				break;
//			case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
//				// Prompts the user to create credentials that your app accepts.
//				final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
//				enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
//						BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
//				startActivityForResult(enrollIntent, REQUEST_CODE);
//				break;
//		}
//
//		executor = ContextCompat.getMainExecutor(this);
//		biometricPrompt = new BiometricPrompt(LoginActivity.this,
//				executor, new BiometricPrompt.AuthenticationCallback()
//		{
//			@Override
//			public void onAuthenticationError(int errorCode,
//											  @NonNull CharSequence errString) {
//				super.onAuthenticationError(errorCode, errString);
//				Toast.makeText(getApplicationContext(),
//						"Authentication error: " + errString, Toast.LENGTH_SHORT)
//						.show();
//			}
//
//			@Override
//			public void onAuthenticationSucceeded(
//					@NonNull BiometricPrompt.AuthenticationResult result) {
//				super.onAuthenticationSucceeded(result);
//				Toast.makeText(getApplicationContext(),
//						"Authentication succeeded!", Toast.LENGTH_SHORT).show();
//			}
//
//			@Override
//			public void onAuthenticationFailed() {
//				super.onAuthenticationFailed();
//				Toast.makeText(getApplicationContext(), "Authentication failed",
//						Toast.LENGTH_SHORT)
//						.show();
//			}
//		});

//		promptInfo = new BiometricPrompt.PromptInfo.Builder()
//				.setTitle("Biometric login for my app")
//				.setSubtitle("Log in using your biometric credential")
//				.setNegativeButtonText("Use account password")
//				.build();
//
//
//		Fingerlogin  = (ImageView) findViewById(R.id.fingerlogin);
//		Fingerlogin.setOnClickListener(view -> {
//			biometricPrompt.authenticate(promptInfo);
//		});


//biometric end



		//CommingFromLogoutThenFreeResources();
		
	}


	private void CommingFromLogoutThenFreeResources()
	{
		try
		{
		boolean isUserLogout=getIntent().getBooleanExtra("IsUserLogOut", false);
		
		if(isUserLogout)
		{
			/*new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						NativeCaller.PPPPInitial("");
						NativeCaller.Free();
						
					} catch (Exception e) {

					}
				}
			}).start();*/
			
			
			Intent mStartActivity = new Intent(this, LoginActivity.class);
			//mStartActivity.addFlags(Intent.ACTION_);
			int mPendingIntentId = 123456;
			PendingIntent mPendingIntent = PendingIntent.getActivity(this, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
			AlarmManager mgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
			mgr.setExact(AlarmManager.RTC, System.currentTimeMillis() + 10, mPendingIntent);
			//mgr.setExact(type, triggerAtMillis, operation);
			//System.exit(0);*/
			Runtime.getRuntime().exit(0);
			

		}
		
		}catch(Exception ex){}
	}
	

	// Helping Methods
	public void initComponents() {


		//super.initUIComponents(ACTIVITY_TITLE);
		mContext = this;
		view = (View) findViewById(R.id.rl_root);
		mBtnLogin = (Button) findViewById(R.id.btn_login);
		mEtEmail = (EditText) findViewById(R.id.et_username);
		mEtPassword = (EditText) findViewById(R.id.et_password);
		mTvForgot = (TextView) findViewById(R.id.tv_forgot);
		mTvSignUp = (TextView) findViewById(R.id.tv_sign_up);
		loginSc= (RelativeLayout) findViewById(R.id.loginSc);
		tv_message_lyout= (View) findViewById(R.id.tv_message_lyout);
		Fingerlogin=(ImageView)findViewById(R.id.fingerlogin);

		String currentLanguage=AppPreference.getValue(getApplicationContext(), AppKeys.KEY_CURRENT_LANGUAGE);
		 
		  
		 if(currentLanguage!=null || currentLanguage.trim().length() > 0)
		 {
			 String  tempLanguage="en";
			 
			    if(currentLanguage.equals(LANGUAGES.ENGLISH.getValue()))
				{
			    	tempLanguage="en";
				}
			    else
			    {
			    	tempLanguage="ar";
			    	
			    	mEtPassword.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
			    	
			    	
			    	mEtPassword.addTextChangedListener(new TextWatcher() {
						
						@Override
						public void onTextChanged(CharSequence s, int start, int before, int count) {
							
							if(s.length()==0)
							{
								mEtPassword.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
							}
							else
							{
								
								mEtPassword.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
							}
							
						}
						
						@Override
						public void beforeTextChanged(CharSequence s, int start, int count, int after) {
							
							
						}
						
						@Override
						public void afterTextChanged(Editable s) {
							
							
							
						}
					});
			    	
			    }
		 }

		
		
		
	}

	public void setClickListner(OnClickListener onclick) {
		mBtnLogin.setOnClickListener(onclick);
		mTvForgot.setOnClickListener(onclick);
		mTvSignUp.setOnClickListener(onclick);
	}

	public void setOrientation() {
		if (AppPreference.getSavedData(mContext, AppKeys.KEY_IS_LANDSCAPE)) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		
		//getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);
	}

	public void callIntentWithFlag() {
		Intent intent = new Intent();
		intent = new Intent(mContext, HomeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	public void verifyData(boolean check) {
		AppUtil.hideKeyBoard(mContext, mEtEmail);
		AppUtil.hideKeyBoard(mContext, mEtPassword);
		boolean flag = setErrorMsg(mEtEmail, getResources().getString(R.string.Error_MSG_UserName));
		if (!flag) {
			flag = setErrorMsg(mEtPassword,getResources().getString(R.string.Error_MSG_UserPass));
			if (!flag) {
				if (check) {
					validateLogin(mEtEmail.getText().toString().toLowerCase(),
							mEtPassword.getText().toString(), "1");
				} else {
					validateLogin(mEtEmail.getText().toString().toLowerCase(),
							mEtPassword.getText().toString(), "0");
				}
			}
		}
	}

	private boolean setErrorMsg(EditText editText, String msg) {
		if (editText.getEditableText().toString().equals("")) {
			editText.requestFocus();
			editText.setError(msg);
			return true;
		}
		return false;
	}

	private void showProgressDialog(String text, int progress) {
		/*mProgressDialog = new ProgressDialog(mContext,R.style.progressDialogTheme);
		mProgressDialog.setMessage(text);
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(progress);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();*/
		
		WaitingStaticProgress.showProgressDialog(text, mContext);
	}

	private void showDialog(String text) {
		/*MaterialDialog dialog = new MaterialDialog.Builder(mContext)
				.content(text)
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
						verifyData(true);
					}
				}).build();
		dialog.show();*/
		
		
		
		
		final CustomAlertDialogueTwoButtons myASlertDialog=new CustomAlertDialogueTwoButtons(LoginActivity.this,text);
		
		myASlertDialog.setListner(new CustomDialogueTwoButtonsClickListner() {
			
			@Override
			public void onCustomDialoguePositiveClick() {
				
				myASlertDialog.dismiss();
				verifyData(true);
				
			}
			
			@Override
			public void onCustomDialogueNegativeClick() {
				
				myASlertDialog.dismiss();
				
			}
		});
		
		myASlertDialog.show();
		
		
	}
	
	
	public class user  
	{
		
		public String username="";
		
		
		public String password="";
		
		
		public String is_logout_before_login="";
		
		
		public String device_token="";
		
		
		
		public String device_type="";
		
		
		public String toString()
		{
			
			/*username:test01
			password:123
			device_token:
			device_type:
			is_logout_before_login:1*/
			
			String temp="";
			temp="username:"+ username+"\n" +
			"password:" + password +"\n"+
			"device_token:" + device_token +"\n" +
			"device_type:"+ device_type +"\n" +
			"is_logout_before_login:" + is_logout_before_login;
			
			return temp;
			
			
			
		}
		
		
	}
	
	
	@Override
	public void onBackPressed() {
		
		Intent i = new Intent();
		i.setAction(Intent.ACTION_MAIN);
		i.addCategory(Intent.CATEGORY_HOME);
		this.startActivity(i);
		
	}

	// Request
	public void validateLogin(String username, String password,
			String is_logout_before_login) {
		
		
		/*user _user=new user();
		
		_user.username=username;
		_user.password=password;
		_user.is_logout_before_login=is_logout_before_login;
		_user.device_token=AppPreference.getValue(this, AppKeys.deviceToken);
		_user.device_type=AppPreference.getValue(this, AppKeys.deviceType);*/
		
		map = new HashMap<String, String>();
		map.put("username", username);
		map.put(AppKeys.KEY_CURRENT_LANGUAGE, AppPreference.getValue(mContext, AppKeys.KEY_CURRENT_LANGUAGE));
		map.put("password", password);
		map.put("is_logout_before_login", is_logout_before_login);
		map.put("device_token",
				AppPreference.getValue(this, AppKeys.deviceToken));
		map.put("device_type", AppPreference.getValue(this, AppKeys.deviceType));
		showProgressDialog("", 100);
		
		
		
		ApiService service = KisafaApplication.getRestClient().getApiService();
		
		Gson gson = new GsonBuilder().setDateFormat(
				"yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'").create();
		String gsonString=gson.toJson(map);
		
		Call<LoginResponse> call = service.login(map);
		call.enqueue(new RestCallback<LoginResponse>(this,ServerCodes.ServerRequestCodes.LOGIN_REQUEST_CODE, mContext));
		
		
		//LoginService loginService =  ServiceGenerator.createService(LoginService.class);
		//Call<user> call1 = loginService.createTask(_user);  
		//call1.enqueue(new RestCallback<LoginResponse>(this,ServerCodes.ServerRequestCodes.LOGIN_REQUEST_CODE, mContext));  
		
	}

	@Override
	public void onSuccess(ServerResponse response) {
		/*if (mProgressDialog != null) {
			mProgressDialog.hide();
		}*/
		//Log.e("ServerResponse",response.getMessage());
		
		
		if (response.getRequestCode() == ServerCodes.ServerRequestCodes.LOGIN_REQUEST_CODE) {
			LoginResponse loginResponse = (LoginResponse) response;
			if (loginResponse.getShMeta().getShErrorCode()
					.equalsIgnoreCase("0")) {
				AppPreference.saveData(this, true, AppKeys.KEY_IS_LOGIN);
				AppPreference.saveValue(this, loginResponse.getShResult()
						.getShSession(), AppKeys.KEY_SESSION);
				AppPreference.saveValue(this, loginResponse.getShResult()
						.getShUserId(), AppKeys.KEY_USER_ID);
				AppPreference.saveValue(this, loginResponse.getShResult()
						.getShEmail(), AppKeys.KEY_USER_EMAIL);
				AppPreference.saveValue(this, loginResponse.getShResult()
						.getShUsername(), AppKeys.KEY_USER_NAME);
				
				
				AppPreference.saveValue(this, loginResponse.getShResult()
						.getShFullName(), AppKeys.KEY_USER_FULL_NAME);
				
				AppPreference.saveValue(this, loginResponse.getShResult()
						.getSh_country_name(), AppKeys.KEY_USER_COUNTRY);
				
				AppPreference.saveValue(this, loginResponse.getShResult()
						.getSh_city(), AppKeys.KEY_USER_CITY);
				
				AppPreference.saveValue(this, loginResponse.getShResult()
						.getShPhoneNumber(), AppKeys.KEY_USER_PHONE);
				
				AppPreference.saveValue(this, loginResponse.getShResult()
						.getSh_country_code(), AppKeys.KEY_USER_COUNTYY_CODE);
				
				AppPreference.saveValue(this, loginResponse.getShResult()
						.getSh_time_zone(), AppKeys.KEY_USER_TIME_ZONE);
				
				AppPreference.saveValue(this, loginResponse.getShResult()
						.getSh_time_zone_name(), AppKeys.KEY_USER_TIME_ZONE_NAME);
				
				
				
				AppPreference.saveValue(this, loginResponse.getShResult()
						.getShIsSubuser(), AppKeys.KEY_IS_SUB_USER);
				AppPreference.saveValue(this, loginResponse.getShResult()
						.getShIsSubuser(), AppKeys.KEY_IS_SUB_USER);
				
				AppPreference.saveValue(this,"1", AppKeys.KEY_IS_LOGIN_FIRST_TIME);
				
				
				
				AppPreference.saveValue(this, loginResponse.getShResult()
						.getSh_wattage_unit(), AppKeys.KEY_USER_WATAGE_UNIT);
				AppPreference.saveValue(this, loginResponse.getShResult()
						.getSh_sh_price_unit(), AppKeys.KEY_USER_PRICE_UNIT);
				
				AppPreference.saveValue(this, loginResponse.getShResult().getSh_currency(), AppKeys.KEY_CURRENT_CURRENCY);
				
				
				ACache.get(this).put(AppConstants.USER_OBJECT,
						loginResponse.getShResult());
				
				
				//Added By Kh
				if(AppPreference.getValue(LoginActivity.this,AppKeys.KEY_IS_SUB_USER)!=null && AppPreference.getValue(LoginActivity.this,AppKeys.KEY_IS_SUB_USER).equals("1"))
				{
					
					
					
				}
				else
				{
				   SaveSunUsersInDB(loginResponse.getShResult());
				}
				
				
				try
		        {
		        USER_Model   user =USER_Model.GetUser();
		        
		       	
			        
			        if(user==null)
			        {
				        user=new USER_Model();
				        user.sh_user_id=USER_Model.USER_TEMP_ID;
			        }
			        
			        if(loginResponse.getShResult().getShProfileImage()!=null && loginResponse.getShResult().getShProfileImage().length() > 0)
			        {
			        	user.profile_url=loginResponse.getShResult().getShProfileImage();	
			        }
			        else
			        {
		        	user.profile_url="";
			        }
		        	
		        	user.save();
		        
		        }catch(Exception ex)
		        {
		        	ex.toString();
		        }
				
					
				WaitingStaticProgress.hideProgressDialog();	
				
				loginSc.setVisibility(View.GONE);
				tv_message_lyout.setVisibility(View.VISIBLE);
			    //showProgressDialog("Please wait loading data", 100);
				LoadAllDataFromServer loadAllDataFromServer=new LoadAllDataFromServer(AppPreference.getValue(LoginActivity.this,AppKeys.KEY_SESSION),LoginActivity.this);
				loadAllDataFromServer.execute();
				
				
				
				
			} else {
				
				
				
				if (loginResponse.getShMeta().getShErrorCode()
						.equalsIgnoreCase("4")) {
					WaitingStaticProgress.hideProgressDialog();	
					showDialog(getResources().getString(R.string.AlreadyLoginMessage));
				} else {
					onFailure(loginResponse.getShMeta().getShMessage());
				}
			}
		}
	}

	@Override
	public void onFailure(ServerResponse response) {
		onFailure(response.getMessage());
	}

	public void onFailure(String retrofitError) {
		/*if (mProgressDialog != null) {
			mProgressDialog.hide();
		}*/
		
		WaitingStaticProgress.hideProgressDialog();
		
		new SnackBar.Builder((Activity) mContext).withMessage(retrofitError)
				.withDuration(SnackBar.SHORT_SNACK).show();
	}

	// Override Methods
	@Override
	public void onClick(View v) {

		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn_login:
			verifyData(false);
			break;
		case R.id.tv_sign_up:
			intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			finish();
			KisafaApplication.perFormActivityNextTransition(this);

			break;
		case R.id.tv_forgot:
			intent = new Intent(this, ForgotPasswordActivity.class);
			startActivity(intent);
			finish();
			KisafaApplication.perFormActivityNextTransition(this);

			break;
		default:
			break;
		}
	}
	
	
	private void SaveSunUsersInDB(ShResult result)
	{
		
		
		
		//SUB_USER_DB.delete(SUB_USER_DB.class , 0);
		
		new Delete().from(SUB_USER_DB.class).execute();
		
		 ArrayList<_SubUser> sh_SubUsersList= (ArrayList<_SubUser>) result.getShsh_SubUsersList();
		 
		 if(sh_SubUsersList==null || sh_SubUsersList.size()==0)
			 return;
		 
		 for(int i=0; i<sh_SubUsersList.size();i++)
		 {
			 
			 SUB_USER_DB s_user_db=new SUB_USER_DB();
			 
			 _SubUser  s_user=sh_SubUsersList.get(i);
			 
			 s_user_db.sh_user_id=s_user.getShSUB_User_ID();
			 s_user_db.username=s_user.getShSUB_User_Name();
			 s_user_db.email=s_user.getShSUB_User_Email();
			 
			 s_user_db.is_subuser=s_user.getShSUB_User_TRUE();
			 s_user_db.created_by_user_id=s_user.getShSUB_User_Created_BY();
			 s_user_db.full_name=s_user.getShSUB_User_Full_Name();
			 s_user_db.profile_image=s_user.getShSUB_User_Profile_Image();
			 s_user_db.phone_number=s_user.getShSUB_User_Phone_Number();
			 
			 
			 s_user_db.save();
			 
		 }
		
		
	}
	
	
	public interface TaskService {  
		
		@POST(AppWebServices.API_LOGIN)
	    Call<user> createTask(@Body user task);
	}
	
	public interface LoginService {  
		@POST(AppWebServices.API_LOGIN)
		 Call<user> createTask(@Body user _user);
	}
	
	public class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap> {


	    private String url;
	    private String path, imgName;
	    //private final WeakReference<ImageView> imageViewReference;


	    public BitmapDownloaderTask(ImageView imageView, String path, String imgName) {

	        //imageViewReference = new WeakReference<ImageView>(imageView);

	        this.path = path;
	        this.imgName = imgName;

	    }//BitmapDownloaderTask


	    @Override
	    // Actual download method, run in the task thread
	    protected Bitmap doInBackground(String... params) {
	        // params comes from the execute() call: params[0] is the url.
	        return getBitmapFromURL(path);

	    }//doInBackground


	    @Override
	    // Once the image is downloaded, associates it to the imageView
	    protected void onPostExecute(Bitmap bitmap) {

	        if (isCancelled()) {
	            bitmap = null;
	        }

	       
	        
	        try
	        {
	        USER_Model   user =USER_Model.GetUser();
	        
	        if(bitmap!=null)
	        {
	        	ByteArrayOutputStream baos = new ByteArrayOutputStream();  
//	        	bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		        byte[] photo = baos.toByteArray();
		        
		        if(user==null)
		        {
		        user=new USER_Model();
		        user.sh_user_id=USER_Model.USER_TEMP_ID;
		        }
		        
	        	user.profile_image=photo;
	        	
	        	user.save();
	        	
	        }
	        else
	        {
	        
	        	if(user!=null)
	        	{
	        		user.profile_image=null;
		        	
		        	user.save();
	        	}
	        	
	        }
	        }catch(Exception ex)
	        {
	        	ex.toString();
	        }
	        
	        
	        
	        WaitingStaticProgress.hideProgressDialog();
	        
	       
			loginSc.setVisibility(View.GONE);
			tv_message_lyout.setVisibility(View.VISIBLE);
		    //showProgressDialog("Please wait loading data", 100);
			LoadAllDataFromServer loadAllDataFromServer=new LoadAllDataFromServer(AppPreference.getValue(LoginActivity.this,AppKeys.KEY_SESSION),LoginActivity.this);
			loadAllDataFromServer.execute();

	    }//onPostExecute


	    public  Bitmap getBitmapFromURL(String link) {
	        /* this method downloads an Image from the given URL,
	        *  then decodes and returns a Bitmap object
	        */
	        try {
	            URL url = new URL(link);
	            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	            connection.setDoInput(true);
	            connection.connect();
	            connection.setReadTimeout(10000);
	            InputStream input = connection.getInputStream();
	            Bitmap myBitmap = BitmapFactory.decodeStream(input);

	            return myBitmap;

	        } catch (IOException e) {
	            e.printStackTrace();
	            Log.e("getBmpFromUrl error: ", e.getMessage().toString());
	            return null;
	        }

	    }//getBitmapFromURL


	}//class
}
