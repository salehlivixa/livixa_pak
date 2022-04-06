package com.livixa.apacam.client.activity;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_Data_to_Server_WebServiceCall;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_Data_to_Server_WebServiceCall.Data_Status;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.dbmodel.Camera_Model;
import com.livixa.apacam.dbmodel.Manage_DB_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.R;
import object.p2pipcam.adapter.CamerasGridViewAdapter;

;

public class AssignedCamerasToSubuserActivity extends Activity  {

	// Layout Variables
	private View view;
	private ImageView mIvBack;
	private ImageView mIvHome;
	private RelativeLayout rl_edit_users;
	private RelativeLayout mRlLogout;
	private ProgressDialog mProgressDialog;

	// Local
	private String ACTIVITY_TITLE = "Settings";
	private Map<String, String> map;
	
	private GridView camerasGridView;
	
	CamerasGridViewAdapter camerasGridViewAdapter;
	private AssignedCamerasToSubuserActivity mContext;
	private RelativeLayout subUsersEmptylayout;
	SUB_USER_DB  _sUB_USER_DB;
	
	public static boolean isDataSynced=false;

	// Class Methods
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_cameras_grid);
		
	
		initComponents();
		
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
		  
		
	}

	// Helping Methods
	public void initComponents() {
		//super.initUIComponents(ACTIVITY_TITLE);
		mContext = this;
		view = (View) findViewById(R.id.rl_root);
		camerasGridView=(GridView) findViewById(R.id.camerasGridView);
		subUsersEmptylayout=(RelativeLayout) findViewById(R.id.subUsersEmptylayout);
		try
		{
		Intent subUserIntent=getIntent();
		
		String subUser_id=subUserIntent.getStringExtra(AppKeys.KEY_SUB_USER_TAG);
		
		_sUB_USER_DB = SUB_USER_DB.searchSubUserInDb(subUser_id);
		
		}catch(Exception ex)
		{
			
		}
		
		List<User_Camera_Model> user_Camera_Model_List=Manage_DB_Model.getAllAssociationOfSubUser(_sUB_USER_DB.sh_user_id);
		
		List<Camera_Model> camera_Model_List=Manage_DB_Model.getAllCamerasfromDb();
		
		
		//User_Camera_Model_Relation user_Camera_Model_Relation=Manage_DB_Model.getCameraWithRelationAgainst_UserId(_sUB_USER_DB.sh_user_id);
		
		if(camera_Model_List==null || camera_Model_List.size()==0)
		{
			camerasGridView.setVisibility(View.GONE);
			subUsersEmptylayout.setVisibility(View.VISIBLE);
		}
		
		camerasGridViewAdapter=new CamerasGridViewAdapter(AssignedCamerasToSubuserActivity.this,user_Camera_Model_List,camera_Model_List,_sUB_USER_DB);
		
		camerasGridView.setAdapter(camerasGridViewAdapter);
		
	}

	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		 finish();
		 Intent intent = new Intent(this, Add_OR_Edit_UserActivity.class);
		 startActivity(intent);
		 
		//overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		//overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
		 KisafaApplication.perFormActivityBackTransition(this);
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
		//overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
		
		KisafaApplication.perFormActivityBackTransition(this);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		
	}
	
	
	
	
	
}
