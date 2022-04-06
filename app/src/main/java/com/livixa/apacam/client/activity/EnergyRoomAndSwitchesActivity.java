package com.livixa.apacam.client.activity;

import com.activeandroid.query.Select;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.response.tariff_energy.Sh_Room_Watage_result;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.apacam.services.TCP_Client_Service;
import com.livixa.client.R;
import object.p2pipcam.adapter.Energy_RoomandSwitchesGridViewAdapter;

public class EnergyRoomAndSwitchesActivity extends Activity {

	private String mRoomTitle = "";

	private String mRoomId = "";

	private TextView mTVRoomTitle;

	private GridView mRoomSwitchesGridView;

	private View mEmptyView;

	Energy_RoomandSwitchesGridViewAdapter mainScreenRoomSwitchesGridViewAdapter;

	private Room_Model mRoomModel;
	
	
	
	
	RelativeLayout switchesMainLayout;
	
	LinearLayout roomsMainLayout;
	
	
	
	
	Context mContext;
	
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	
	 LinearLayout layoutContainer;
		private Switch_Model mSwitchModel;
		
		
		
	    
		Sh_Room_Watage_result roomContainsSwitches;
	
    boolean mBound = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renergy_oom_and_switches);
		
		mContext=EnergyRoomAndSwitchesActivity.this;

		mTVRoomTitle = (TextView) findViewById(R.id.tv_title);
		handleIntent();
		mTVRoomTitle.setText(mRoomTitle);

		initUiComponents();

		initOthers();
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
	}

	private void initUiComponents() {
		mRoomSwitchesGridView = (GridView) findViewById(R.id.roomSwitchesGridView);
		
		switchesMainLayout=(RelativeLayout) findViewById(R.id.switchesMainLayout);
		
		roomsMainLayout=(LinearLayout) findViewById(R.id.roomsMainLayout);
		
		
		
		

		mEmptyView = findViewById(R.id.roomSwitchesEmptylayout);

	}

	private void initOthers() {

		mainScreenRoomSwitchesGridViewAdapter = new Energy_RoomandSwitchesGridViewAdapter(EnergyRoomAndSwitchesActivity.this,mEmptyView, mRoomSwitchesGridView, mRoomModel,roomContainsSwitches);
		
		
		
		mRoomSwitchesGridView.setAdapter(mainScreenRoomSwitchesGridViewAdapter);

	}

	public void handleIntent() {
		try {

			Intent intent = getIntent();

			mRoomId = intent.getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
			mRoomTitle = intent.getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
			mRoomModel = getRoomFromDb(mRoomId);
			
			roomContainsSwitches= (Sh_Room_Watage_result)intent.getSerializableExtra("RoomRelatedSwitches");
			

		} catch (Exception ex) {
			ex.toString();
		}
	}

	public void onbackButttonClick(View view) {
		onBackPressed();
	}

	public void onhomeButttonClick(View view) {
		
		
		Intent stopserviceIntent = new Intent(this, TCP_Client_Service.class);
		stopService(stopserviceIntent);
		
		
		finish();

		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		// overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		//overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);
		KisafaApplication.perFormActivityBackTransition(this);
		
		
		
		
	}
	
	
	@Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        
    }
	
	

    
    
    
	@Override
	public void onBackPressed() {
		
		
		
		
		
		
		
		
			super.onBackPressed();
			
			finish();
			
			
			
			
	
			Intent intent = new Intent(this, Energy_RoomsActivity.class);
			
			
			intent.putExtras(getIntent());
	
			KisafaApplication.perFormActivityBackTransition(this);
	
			startActivity(intent);
		

	}

	private Room_Model getRoomFromDb(String roomId) {
		Room_Model roomModel = null;

		try {
			roomModel = new Select().from(Room_Model.class).where("Room_Model.room_id = ?", roomId).executeSingle();
		} catch (Exception ex) {
			ex.toString();
		}

		return roomModel;
	}
	
	
	public void onSwitchStatusResponse(String command)
	{
		
		
		
		
		
		
		
	}

	


	
	
	
	
}
