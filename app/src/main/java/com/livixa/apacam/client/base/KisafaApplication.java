package com.livixa.apacam.client.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import com.livixa.apacam.client.activity.HomeActivity;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.appconfig.AppKeys.LANGUAGES;
import com.livixa.apacam.client.network.RestClient;
import com.livixa.apacam.client.response.isdatasyncedwithserver.ShUserCamera;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Camera;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.client.utility.AppFolders;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.dbmodel.Camera_Model;
import com.livixa.apacam.dbmodel.ESP_Result_Model;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Sub_User_Cam_Association;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.dbmodel.Tariff_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import com.livixa.apacam.dbmodel.User_Room_Model;
import com.livixa.client.R;
import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.nativecaller.NativeCaller;
import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager.OnActivityDestroyListener;
import android.provider.Settings;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.BitmapAjaxCallback;
import com.androidquery.util.AQUtility;
import com.kisafa.user.profile.USER_Model;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.oro.orochi.application.Orochi;
import com.sawas.ashisuto.db.TinyDB;

public class KisafaApplication extends Orochi {
	public static String TAG = "Livixa Application";

	private static KisafaApplication instance;

	 private static RestClient restClient;
	 private static RestClient restClientHeader;

	public static Context context;

	private static File cacheDir;
	public static boolean DUMMY_DATA_FLAG = false;
	
	public static  LANGUAGES  currentAppLanguage=LANGUAGES.ENGLISH;

	@Override
	public void onCreate() {
		 
		super.onCreate();
		instance = this;
		
		
		try
		
		{
			Thread.setDefaultUncaughtExceptionHandler (new Thread.UncaughtExceptionHandler()
		    {
		      @Override
		      public void uncaughtException (Thread thread, Throwable e)
		      {
		        handleUncaughtException (thread, e);
		      }
		    });
		}catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		
		
		 try
		 {
			
			 initializeDB();
			// ActiveAndroid.initialize(this);
		 }catch(Exception ex)
		 {
			 ex.toString();
		 }
		
		 
		/* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
		     
				try
				{
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							NativeCaller.PPPPInitial(ContentCommon.SERVER_STRING);
						
							NativeCaller.Free();
							
						} catch (Exception e) {
		
						}
					}
				}).start();
				
				}catch(Exception ex){}
			
		 }*/
		 
		
		 
		 try
		 {
			 

			 
			 String currentLanguage=AppPreference.getValue(getApplicationContext(), AppKeys.KEY_CURRENT_LANGUAGE);
			 
			  
			 if(currentLanguage==null || currentLanguage.trim().length() == 0)
			 {
				 
				 AppPreference.saveValue(getApplicationContext(),LANGUAGES.ENGLISH.getValue(), AppKeys.KEY_CURRENT_LANGUAGE);
			 }
			 else
			 {
				 
				 String  tempLanguage="en";
				    if(currentLanguage.equals(LANGUAGES.ENGLISH.getValue()))
					{
				    	tempLanguage="en";
				    	
				    	currentAppLanguage=LANGUAGES.ENGLISH;
					}
				    else
				    {
				    	tempLanguage="ar";
				    	
				    	currentAppLanguage=LANGUAGES.ARABIC;
				    }
				 
				 try
					{
						Locale locale = new Locale(tempLanguage);
						Locale.setDefault(locale);
						android.content.res.Configuration config = getApplicationContext().getResources().getConfiguration();
						config.locale = locale;
						getBaseContext().getResources().updateConfiguration(config,
						      getBaseContext().getResources().getDisplayMetrics());
						
					
						
					}catch(Exception ex)
					{
						ex.toString();
					}
				 
			 }
			 
			/* File cacheDir = StorageUtils.getCacheDirectory(instance);
			 ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(instance)
			         .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
			         .diskCacheExtraOptions(480, 800, null)
			        
			         
			         .threadPoolSize(3) // default
			         .threadPriority(Thread.NORM_PRIORITY - 2) // default
			         .tasksProcessingOrder(QueueProcessingType.FIFO) // default
			         .denyCacheImageMultipleSizesInMemory()
			         .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
			         .memoryCacheSize(2 * 1024 * 1024)
			         .memoryCacheSizePercentage(13) // default
			         .diskCache(new UnlimitedDiscCache(cacheDir)) // default
			         .diskCacheSize(50 * 1024 * 1024)
			         .diskCacheFileCount(100)
			         .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
			         .imageDownloader(new BaseImageDownloader(instance)) // default
			         .imageDecoder(new BaseImageDecoder(true)) // default
			         .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
			         .writeDebugLogs()
			         .build();
*/
			 
		 }catch(Exception ex)
		 {
			 ex.toString();
		 }
		 
		 
		KisafaApplication.context = getApplicationContext();
		cacheDir = AppFolders.getImageFilePath();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).memoryCache(new WeakMemoryCache())
				.memoryCacheSize(50 * 1024 * 1024)
				.discCache(new UnlimitedDiscCache(cacheDir))
				.discCacheSize(50 * 1024 * 1024).build();
		ImageLoader.getInstance().init(config);
		File file = new File(Environment.getExternalStorageDirectory()
				.toString() + AppFolders.IMAGE_FILE_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(Environment.getExternalStorageDirectory().toString()
				+ AppFolders.FILE_PATH_BASE);
		if (!file.exists()) {
			file.mkdirs();
		}
		initializeRestClient();
//		initializeRestClientWithHeader();
		initAqueryCacheLocation();
		initAQuery();
		getDeviceToken();
		initParse();
	}
	
	
	
	
	public static  DisplayImageOptions  getImageDisplayOption()
	{
		
		
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		        .cacheInMemory(true) // default
		        .cacheOnDisk(true) // default
		        .displayer(new SimpleBitmapDisplayer()) // default
		        .build();
		
		
		return  options; 
		
		
	}
	
	
	
	
	protected void initializeDB() {
		
	    Configuration.Builder configurationBuilder = new Configuration.Builder(this);
	    
	    
	    configurationBuilder.addModelClass(Camera_Model.class);
	    configurationBuilder.addModelClass(User_Camera_Model.class);
	    configurationBuilder.addModelClass(SUB_USER_DB.class);
	    configurationBuilder.addModelClass(Sub_User_Cam_Association.class);
	    configurationBuilder.addModelClass(Switch_Model.class);
	    configurationBuilder.addModelClass(Room_Model.class);
	    configurationBuilder.addModelClass(User_Room_Model.class);
	    configurationBuilder.addModelClass(ESP_Result_Model.class);
	    configurationBuilder.addModelClass(Mood_Model.class);
	    configurationBuilder.addModelClass(USER_Model.class);
	    configurationBuilder.addModelClass(Tariff_Model.class);
	    
	    
	    
	    
	    
	    ActiveAndroid.initialize(configurationBuilder.create());
	}

	public KisafaApplication() {
	}

	public static KisafaApplication getInstance() {
		return KisafaApplication.instance;
	}

	public static Context getAppContext() {
		return instance;
	}

	public static Resources getAppResources() {
		return instance.getResources();
	}

	private void initializeRestClient() {
		 restClient = new RestClient();
	}

	 public static RestClient getRestClient() {
	 return restClient;
	 }

	public void getDeviceToken() {
		String android_id = Settings.Secure.getString(getApplicationContext()
				.getContentResolver(), Settings.Secure.ANDROID_ID);
		 AppPreference.saveValue(this, android_id, AppKeys.deviceToken);
		 AppPreference.saveValue(this, "android", AppKeys.deviceType);
	}

	 private void initializeRestClientWithHeader() {
	 restClientHeader = new RestClient(true);
	 }
	//
	// //
	public static RestClient getRestClientWithHeaders() {
		if (restClientHeader == null) {
			return new RestClient(true);
		} else {
			return restClientHeader;
		}
	}

	private void initAQuery() {
		// set the max number of concurrent network connections, default is 4
		AjaxCallback.setNetworkLimit(8);

		// set the max number of icons (image width <= 50) to be cached in
		// memory, default is 20
		BitmapAjaxCallback.setIconCacheLimit(20);

		// set the max number of images (image width > 50) to be cached in
		// memory, default is 20
		BitmapAjaxCallback.setCacheLimit(160);

		// set the max size of an image to be cached in memory, default is 1600
		// pixels (ie. 400x400)
		BitmapAjaxCallback.setPixelLimit(400 * 400);

		// set the max size of the memory cache, default is 1M pixels (4MB)
		BitmapAjaxCallback.setMaxPixelLimit(8000000);
	}

	private void initAqueryCacheLocation() {
		cacheDir = new File(AppFolders.getRootPath());
		AQUtility.setCacheDir(cacheDir);
	}

	public static File getAQCacheDir() {
		return KisafaApplication.cacheDir;
	}

	private void createDirectory(String filePath) {
		File file = new File(filePath);
		file.mkdirs();
	}

	public static void set_sh_features(Context context, String values){
		TinyDB tb = new TinyDB(context);
		tb.putString("__sh_features",values);
	}

	public static String get_sh_features(Context context){
		return new TinyDB(context).getString("__sh_features");
	}

	public static void set_sh_subscriptions(Context context, String values){
		TinyDB tb = new TinyDB(context);
		tb.putString("__sh_subscriptions",values);
	}

	public static String get_sh_subscriptions(Context context){
		return new TinyDB(context).getString("__sh_subscriptions");
	}

	public static void setSubscription(Context context, ArrayList<String> values){
		TinyDB tb = new TinyDB(context);
		tb.putList("__subscription",values);
	}

	public static ArrayList<String> getSubscription(Context context){
		return new TinyDB(context).getList("__subscription");
	}

//	Sh_subscription

	public static void setShsubscription(Context context, ArrayList<String> values){
		TinyDB tb = new TinyDB(context);
		tb.putList("__ShSubscription",values);
	}

	public static ArrayList<String> getShSubscription(Context context){
		return new TinyDB(context).getList("__ShSubscription");
	}

//end

//	Sh_Feature

	public static void setShFeatures(Context context, ArrayList<String> values){
		TinyDB tb = new TinyDB(context);
		tb.putList("__ShFeatures",values);
	}

	public static ArrayList<String> getShFeatures(Context context){
		return new TinyDB(context).getList("__ShFeatures");
	}


//end


	public void initParse() {
		// Parse.initialize(this,
		// AppKeys.getInstance().getParseAppId(),
		// AppKeys.getInstance().getParseClientKey());
		// ParseInstallation.getCurrentInstallation().saveInBackground();
	}
	
	public void showToastInThread(final String str){
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                
                Looper.loop();

            }
        }.start();
    }
	
	public void handleUncaughtException (Thread thread, Throwable e)
	  {
	    e.printStackTrace(); // not all Android versions will print the stack trace automatically

	    showToastInThread(e.getLocalizedMessage());
	    
	 
	    Looper.prepare();
	    Intent intent = new Intent (getApplicationContext(),HomeActivity.class);
	    startActivity(intent);
	    Looper.loop();

	    //System.exit(1); // kill off the crashed app
	  }
	
	public static void perFormActivityNextTransition(Context mContext)
	{
		
		
		if(currentAppLanguage!=null && currentAppLanguage==LANGUAGES.ENGLISH)
		{
			((Activity) mContext).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		}
		else
		{
			((Activity) mContext).overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
		}
	}
	
	public static void perFormActivityBackTransition(Context mContext)
	{
		if(currentAppLanguage!=null && currentAppLanguage==LANGUAGES.ENGLISH)
		{
		    ((Activity) mContext).overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
		}
		else
		{
			((Activity) mContext).overridePendingTransition( R.anim.in_from_right,R.anim.out_to_left);
		}
	}

}