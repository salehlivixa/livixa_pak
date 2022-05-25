package com.livixa.apacam.client.activity;


import java.util.ArrayList;
import java.util.List;

import com.activeandroid.query.Select;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Window;

import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import  com.livixa.client.R;

import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.services.Sync_Service;
import object.p2pipcam.adapter.Sub_User_List_Adapter;



public class Add_OR_Edit_UserActivity extends Activity {

	
	

	private Context mContext;
	
	private GridView subUsersListView;
	
	private LinearLayout subUsersEmptylayout;
	
	 private ListAdapter adapter;
	
	
	private Sub_User_List_Adapter  sub_User_List_Adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__or__edit__user);
		subUsersListView=(GridView) findViewById(R.id.subUsersListView);
		subUsersEmptylayout=(LinearLayout) findViewById(R.id.subUsersEmptylayout);
		
		this.mContext=this;
		
		//getSupportActionBar().hide();
		
		try
		{
			
			ArrayList<SUB_USER_DB> tempSubusers=(ArrayList<SUB_USER_DB>) getAllSub_Users();
			
			if(tempSubusers!=null && tempSubusers.size()==0)
			{
				subUsersListView.setVisibility(View.GONE);
				subUsersEmptylayout.setVisibility(View.VISIBLE);
			}
			
		    sub_User_List_Adapter=new Sub_User_List_Adapter(Add_OR_Edit_UserActivity.this,tempSubusers,subUsersListView,subUsersEmptylayout);
		
		
		
		}catch(Exception ex)
		{
			ex.toString();//SUB_USER_DB
		}
		
		
		
		
		subUsersListView.setAdapter(sub_User_List_Adapter);
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
		
		
	}

	
	
	private List<SUB_USER_DB> getAllSub_Users() {
		
	       
	 return new Select()
	                .from(SUB_USER_DB.class)
	                .orderBy("username")
	                .execute();
   }
	
	
	
	@Override
	public void onBackPressed() {
		 
		super.onBackPressed();
		
		
		finish();
		
		Intent intent = new Intent(this, SettingsActivity.class);
		
		KisafaApplication.perFormActivityBackTransition(this);
		
		startActivity(intent);
		
		
	}



	
	
	
	
	
	
	public void onAddEditUserClick(View view)
	{
		
		
		if(AppPreference.getValue(Add_OR_Edit_UserActivity.this,AppKeys.KEY_IS_SUB_USER)!=null && AppPreference.getValue(Add_OR_Edit_UserActivity.this,AppKeys.KEY_IS_SUB_USER).equals("1"))
		{
			
			Toast.makeText(mContext, "Subuser cannot Add/Edit", Toast.LENGTH_SHORT).show();
			
		}
		else
		{
			Intent intent = new Intent(this, RegisterActivityForSubUser.class);
			
			startActivity(intent);
			finish();
			KisafaApplication.perFormActivityNextTransition(this);
			//overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
		}
		
	}
	
	
	public void onbackButttonClick(View view)
	{
		onBackPressed();
	}
	
	
	public void onhomeButttonClick(View view)
	{
		finish();
		
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		//overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		KisafaApplication.perFormActivityBackTransition(this);
	}
	
	
	
	 
	
}
