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
import com.livixa.apacam.dbmodel.Sub_User_Cam_Association;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import com.livixa.apacam.services.Sync_Service;
import object.p2pipcam.adapter.Sub_User_List_Adapter;
import object.p2pipcam.adapter.Sub_User_with_assigned_camera_List_Adapter;

public class CameraAssignedToSubusersActivity extends Activity {

	
	ListView subUsersAssignedCameraListView;
	
	
	
	Sub_User_with_assigned_camera_List_Adapter sub_User_with_assigned_camera_List_Adapter;

	HashMap<String, Object> subUsers=new HashMap<String, Object>();

	private SUB_USER_DB _sUB_USER_DB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(layout.activity_assigned_camerasto_subusers);
		
		subUsersAssignedCameraListView=(ListView) findViewById(R.id.subUsersAssignedCameraListView);
		
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		try
		{
	
			
			
			
			Intent subUserIntent=getIntent();
			
			String subUser_id=subUserIntent.getStringExtra(AppKeys.KEY_SUB_USER_TAG);
			String cam_Id=subUserIntent.getStringExtra(AppKeys.KEY_CAMERA_ID_TAG);
			String cam_Name=subUserIntent.getStringExtra(AppKeys.KEY_CAMERA_NAME_TAG);
			String cam_DID=subUserIntent.getStringExtra("DID");
			
			_sUB_USER_DB = SUB_USER_DB.searchSubUserInDb(subUser_id);
			
			
			
			List<User_Camera_Model> user_Camera_Model_List=Manage_DB_Model.getAllAssociationOfSubUserOfThisCamera(cam_Id);
			
			List<User_Camera_Model> user_Camera_Model_List_to_Pass=new ArrayList<User_Camera_Model>();
			
			ArrayList<SUB_USER_DB> tempSubusers=(ArrayList<SUB_USER_DB>) getAllSub_Users();
			
			if(tempSubusers!=null)
			for(int i=0; i<tempSubusers.size();i++)
			{
				
				subUsers.put(tempSubusers.get(i).sh_user_id, tempSubusers.get(i));
			}
			
			
			if(user_Camera_Model_List!=null)
				for(int i=0; i<user_Camera_Model_List.size();i++)
				{
					if(subUsers.containsKey(user_Camera_Model_List.get(i).userId) && (!user_Camera_Model_List.get(i).modelStatus.equals(AppKeys.KEY_IS_DELETED)))
					{
						user_Camera_Model_List_to_Pass.add(user_Camera_Model_List.get(i));
					}
				}
			
			
			//List<Camera_Model> camera_Model_List=Manage_DB_Model.getAllCamerasfromDb();
			if(user_Camera_Model_List_to_Pass==null)
				user_Camera_Model_List_to_Pass=new ArrayList<User_Camera_Model>();
			
            User_Camera_Model user_Camera_Model=new User_Camera_Model();
            
          
			
			user_Camera_Model.cameraId=cam_Id;
			
			user_Camera_Model.isAccessCameraSetting="1";
			
			user_Camera_Model_List_to_Pass.add(0, user_Camera_Model);
			
			
			
			sub_User_with_assigned_camera_List_Adapter=new Sub_User_with_assigned_camera_List_Adapter(CameraAssignedToSubusersActivity.this, user_Camera_Model_List_to_Pass,subUsers,cam_Name,AppPreference.getValue(this, AppKeys.KEY_USER_NAME),cam_DID);
		
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
	 
	super.onBackPressed();
	
	finish();
	
	Intent intent = new Intent(this, AssignedCamerasToSubuserActivity.class);
	intent.putExtra(AppKeys.KEY_SUB_USER_TAG, _sUB_USER_DB.sh_user_id);
	
	startActivity(intent);
	KisafaApplication.perFormActivityBackTransition(this);
	//overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
}
}