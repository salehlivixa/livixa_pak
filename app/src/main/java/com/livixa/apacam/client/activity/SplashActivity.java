package com.livixa.apacam.client.activity;

import com.livixa.client.R;
import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.nativecaller.NativeCaller;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaActivity;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.services.KillNotificationsService;
import com.livixa.apacam.services.Sync_Service;

import java.util.Calendar;
import java.util.TimeZone;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SplashActivity extends Activity implements OnClickListener {

	// Local
	public final String TAG = getClass().getName();
	private View view;
	
	
	View mainView;
	
	View spash_window;
	
	WindowManager windowManager;
	
	RelativeLayout layout;
	
	Animation animation2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//mContext = this;
		setContentView(R.layout.activity_splash);
		//getSupportActionBar().hide();
		initComponents();
		
		 layout=(RelativeLayout) findViewById(R.id.rl_root_splash);
		
		setClickListner();
		animateView();
		
		
		
		try
		{
			if(isMyServiceRunning(Sync_Service.class))
			{
				Intent in = new Intent(SplashActivity.this,Sync_Service.class);
				Sync_Service.stopWebserviceCalling=true;
				stopService(in);
			}
		}
		catch(Exception ex){}
		/*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
		     
		
					hideDialogue();
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
						
						
						Intent intent = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
						sendBroadcast(intent);
					
					}catch(Exception ex){}
					
					
					 animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out_to_left_splash);
				
		}*/
		
		Intent intent = new Intent(this, KillNotificationsService.class);
		startService(intent);
		
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
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
		
		 
		
		super.onDestroy();
		
		
		
	}

	public void initComponents() {
		//mContext = this;
		view = (View) findViewById(R.id.rl_root_splash);
	}

	public void setClickListner() {
	}

	public void animateView() {
		final Animation fadeInAnimation = AnimationUtils.loadAnimation(this,
				R.anim.custom_fadein_translation);
		fadeInAnimation.setDuration(2000);
		
		
		
		
		layout.startAnimation(fadeInAnimation);

		fadeInAnimation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			@Override
			public void onAnimationEnd(Animation view) {
				
				//added line for camera native library acccess  dialogue issue
				AppPreference.saveData(getApplicationContext(), false, "RECREATED");
				
				
				
				moveNext();
				
			}
		});
	}

	public void callIntent(Context c, Class<?> cls) {
		final Intent intent = new Intent(c, cls);
		
		

		/*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
		     
			spash_window.setAnimation(animation2);
			
			layout.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					
					animation2.start();
				}
			}, 200);
		
		
		
		
				try
				{
				
					removeView();
				
				}catch(Exception ex){}
		
		
		}*/
		
		startActivity(intent);
		SplashActivity.this.finish();
		KisafaApplication.perFormActivityNextTransition(this);
	
		
		
		
		
		
		
	}

	public void moveNext() {
		try
		{
		if (AppPreference.getSavedData(this, AppKeys.KEY_IS_LOGIN)) {
			callIntent(this, HomeActivity.class);
			
			
		} else {
			callIntent(this, LoginActivity.class);
			// callIntent(this, MainActivity.class);
		}
		}catch(Exception ex)
		{
			callIntent(this, LoginActivity.class);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		default:
			break;
		}
	}
	
	
	public void hideDialogue()
	{
		windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

		LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

		 mainView= (View)inflater.inflate(R.layout.activity_splash, null);
		
		
		WindowManager.LayoutParams paramRemove = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.TYPE_PHONE,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
				PixelFormat.TRANSLUCENT);
		
		paramRemove.gravity = Gravity.CENTER;

		
		
		windowManager.addView(mainView, paramRemove);
		
		
		 spash_window=mainView.findViewById(R.id.spash_window);
		
		
		
		final Animation fadeInAnimation = AnimationUtils.loadAnimation(this,
				R.anim.custom_fadein_translation);
		fadeInAnimation.setDuration(2000);
		
		spash_window.setAnimation(fadeInAnimation);
		
		fadeInAnimation.start();
		
		fadeInAnimation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			@Override
			public void onAnimationEnd(Animation view) {
				
				
				
				
				
				mainView.setBackgroundColor(Color.parseColor("#00000000"));
				
				
				
				
				
			}
		});
		
		
		
		
	}
	
	private void removeView(){
		
		
		spash_window.postDelayed(new Runnable() {
			
			@Override
			public void run() {
			
				try
				{
					
					windowManager.removeView(mainView);
				}catch(Exception ex)
				{
					
				}
			}
		}, 600);
		
		
		
	}
	
	
	
	
}
