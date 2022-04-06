package com.livixa.apacam.client.activity;

import java.util.HashMap;
import java.util.Map;

import com.livixa.client.R;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaActivity;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.request.RequestResponse;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.AppUtil;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.services.Sync_Service;
import retrofit2.Call;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.github.mrengineer13.snackbar.SnackBar;

public class AboutActivity extends Activity 
		 {

	// Layout Variables
	
	private AboutActivity mContext;

	// Class Methods
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext=this;
		
		setContentView(R.layout.activity_about);
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		finish();
		
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
		KisafaApplication.perFormActivityBackTransition(mContext);
	}
	
	
	public void onbackButttonClick(View view)
	{
		finish();
		
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
		KisafaApplication.perFormActivityBackTransition(mContext);
	}
	
	
}
