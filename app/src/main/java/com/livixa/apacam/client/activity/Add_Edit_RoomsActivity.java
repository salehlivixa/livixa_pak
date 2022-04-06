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

import androidx.core.view.ViewCompat;

import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.R;
import object.p2pipcam.adapter.CamerasGridViewAdapter;
import object.p2pipcam.adapter.RoomsGridViewAdapter;





public class Add_Edit_RoomsActivity extends Activity {
	
	
	private GridView roomsGridView;
	private View mEmptyView;
	
	private RoomsGridViewAdapter roomsGridViewAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_edit_activity_rooms);
		roomsGridView=(GridView) findViewById(R.id.roomsGridView);
		mEmptyView=findViewById(R.id.roomsEmptylayout);
		
		
		roomsGridViewAdapter =new RoomsGridViewAdapter(Add_Edit_RoomsActivity.this,mEmptyView,roomsGridView);
		roomsGridView.setAdapter(roomsGridViewAdapter);
		
		ViewCompat.setLayoutDirection (roomsGridView, ViewCompat.LAYOUT_DIRECTION_LOCALE);
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(Add_Edit_RoomsActivity.this);
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
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		
		finish();
		
		Intent intent = new Intent(this, SettingsActivity.class);
		
		
		KisafaApplication.perFormActivityBackTransition(this);
		
		startActivity(intent);
		
		
	}
	
	
	
	
	
	
	
	public void onAddRoomClick(View view)
	{
		Intent intent=new Intent(Add_Edit_RoomsActivity.this, RoomActivity.class);
		
		intent.putExtra(AppKeys.KEY_IS_CREATED, true);
		
		startActivity(intent);
		
		overridePendingTransition(0,0);
		
		finish();
	}
	
	
	

}
