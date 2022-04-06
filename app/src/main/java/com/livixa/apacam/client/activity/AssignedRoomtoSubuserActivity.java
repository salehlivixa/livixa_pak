package com.livixa.apacam.client.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_Data_to_Server_WebServiceCall;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_Data_to_Server_WebServiceCall.Data_Status;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.R;
import object.p2pipcam.adapter.AssignedRoomsToSubusersGridAdapter;
import object.p2pipcam.adapter.CamerasGridViewAdapter;
import object.p2pipcam.adapter.RoomsGridViewAdapter;





public class AssignedRoomtoSubuserActivity extends Activity {
	
	
	private GridView roomsGridView;
	private View mEmptyView;
	
	private AssignedRoomsToSubusersGridAdapter roomsGridViewAdapter;
	
	SUB_USER_DB  _sUB_USER_DB;
	
	public static boolean isDataSynced=false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.assigned_rooms_to_subusers);
		roomsGridView=(GridView) findViewById(R.id.roomsGridView);
		mEmptyView=findViewById(R.id.roomsEmptylayout);
		
		
		
		
		
		try
		{
		Intent subUserIntent=getIntent();
		
		String subUser_id=subUserIntent.getStringExtra(AppKeys.KEY_SUB_USER_TAG);
		
		_sUB_USER_DB = SUB_USER_DB.searchSubUserInDb(subUser_id);
		
		}catch(Exception ex)
		{
			
		}
		
		roomsGridViewAdapter =new AssignedRoomsToSubusersGridAdapter(AssignedRoomtoSubuserActivity.this,mEmptyView,roomsGridView,_sUB_USER_DB);
		roomsGridView.setAdapter(roomsGridViewAdapter);
		
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
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
	public void onBackPressed() {
		 
		super.onBackPressed();
		
		
		finish();
		
		Intent intent = new Intent(this, Add_OR_Edit_UserActivity.class);
		
		
		KisafaApplication.perFormActivityBackTransition(this);
		
		startActivity(intent);
		
		
	}
	
	
	
	
	
	
	
	public void onAddRoomClick(View view)
	{
		Intent intent=new Intent(AssignedRoomtoSubuserActivity.this, RoomActivity.class);
		
		intent.putExtra(AppKeys.KEY_IS_CREATED, true);
		
		startActivity(intent);
		
		finish();
	}
	
	
	

}
