package com.livixa.apacam.client.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.activeandroid.query.Select;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import com.livixa.client.R;
import com.livixa.client.R.layout;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.dbmodel.Camera_Model;
import com.livixa.apacam.dbmodel.Manage_DB_Model;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Sub_User_Cam_Association;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model_Relation;
import com.livixa.apacam.dbmodel.User_Room_Model;
import com.livixa.apacam.services.Sync_Service;
import object.p2pipcam.adapter.RoomAssignedtoUsers_List_Adapter;
import object.p2pipcam.adapter.Sub_User_List_Adapter;
import object.p2pipcam.adapter.Sub_User_with_assigned_camera_List_Adapter;

public class RoomAssignedToSubusersActivity extends Activity {

	
	ListView subUsersAssignedCameraListView;
	
	
	String roomid="";
	String subUserid="";
	String roomtitle="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(layout.room_assigned_users);
		
		subUsersAssignedCameraListView=(ListView) findViewById(R.id.subUsersAssignedCameraListView);
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
		try
		{
	
			
			
			
			Intent subUserIntent=getIntent();
			
			roomid=subUserIntent.getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
			roomtitle=subUserIntent.getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
			subUserid=subUserIntent.getStringExtra(AppKeys.KEY_SUB_USER_TAG);
			
			RoomAssignedtoUsers_List_Adapter sub_User_with_assigned_camera_List_Adapter = new RoomAssignedtoUsers_List_Adapter(RoomAssignedToSubusersActivity.this,roomid,roomtitle);	
			
		
			subUsersAssignedCameraListView.setAdapter(sub_User_with_assigned_camera_List_Adapter);
		
		}catch(Exception ex)
		{
			ex.toString();//SUB_USER_DB
		}
		
	}
	
	
	
	



private List<SUB_USER_DB> getAllSub_Users() {
	
    
	 return new Select()
	                .from(SUB_USER_DB.class)
	                .orderBy("username")
	                .execute();
  }


public void onbackButttonClick(View view)
{
	onBackPressed();
}


@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	super.onBackPressed();
	
	finish();
	
	Intent intent = new Intent(this, AssignedRoomtoSubuserActivity.class);
	intent.putExtra(AppKeys.KEY_SUB_USER_TAG, subUserid);
	
	startActivity(intent);
	KisafaApplication.perFormActivityBackTransition(this);
	//overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
}







}