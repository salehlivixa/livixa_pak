package com.livixa.apacam.client.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.ViewCompat;

import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.R;
import object.p2pipcam.adapter.CamerasGridViewAdapter;
import object.p2pipcam.adapter.RoomsGridViewAdapter;
import object.p2pipcam.adapter.ViewRoomsGridViewAdapter;





public class View_RoomsActivity extends Activity {
	
	
	private GridView roomsGridView;
	private View mEmptyView;
	
	private ViewRoomsGridViewAdapter roomsGridViewAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_activity_rooms);
		roomsGridView=(GridView) findViewById(R.id.viewroomsGridView);
		mEmptyView=findViewById(R.id.roomsEmptylayout);
		
		
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
		
		roomsGridViewAdapter =new ViewRoomsGridViewAdapter(View_RoomsActivity.this,mEmptyView,roomsGridView);
		roomsGridView.setAdapter(roomsGridViewAdapter);
		
		ViewCompat.setLayoutDirection(roomsGridView, ViewCompat.LAYOUT_DIRECTION_LTR);
	}
	
	
	
	public void onViewHomebackButttonClick(View view)
	{
		onhomeButttonClick(null);
	}
	
	
	public void onhomeButttonClick(View view)
	{
		
		
		
		finish();
		
		Intent intent = new Intent(this, HomeActivity.class);
		//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		//overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		KisafaApplication.perFormActivityBackTransition(this);
	}
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		onhomeButttonClick(null);
		
		
	}
	
	
	

}
