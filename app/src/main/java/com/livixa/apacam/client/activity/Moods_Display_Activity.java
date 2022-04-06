package com.livixa.apacam.client.activity;

import com.activeandroid.query.Select;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import com.livixa.apacam.client.activity.Moods_RoomAndSwitchesTCPActivity.Buttons;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.client.R;
import object.p2pipcam.adapter.ESP_Result_List_Adapter;
import object.p2pipcam.adapter.Moods_Detail_List_Adapter;

public class Moods_Display_Activity extends Activity {
	
	
	
	ListView espResultsListView;
	private String currentCommand;
	private String currentButtonType=Buttons.BUTTON_ONE.getValue();
	private Switch_Model mSwitchModel;
	
	Moods_Detail_List_Adapter esp_Result_List_Adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_moods_list);
		espResultsListView=(ListView) findViewById(R.id.espResultsListView);
		
		
		handleIntent();
		try
		{
	
			 esp_Result_List_Adapter = new Moods_Detail_List_Adapter(Moods_Display_Activity.this,mSwitchModel.switch_id,currentButtonType,currentCommand,mSwitchModel);	
			
		
			espResultsListView.setAdapter(esp_Result_List_Adapter);
		
		}catch(Exception ex)
		{
			ex.toString();//SUB_USER_DB
		}
		
	}
	
	
	
	




public void onbackButttonClick(View view)
{
	onBackPressed();
}


@Override
public void onBackPressed() {
	
	
	
		super.onBackPressed();
		
		finish();

		Intent intent = new Intent(Moods_Display_Activity.this, Moods_RoomAndSwitchesTCPActivity.class);
		
		String mRoomId = getIntent().getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
		String mRoomTitle = getIntent().getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
		
		String swId = getIntent().getStringExtra(AppKeys.KEY_SWITCH_ID_TAG);
		
		currentCommand=esp_Result_List_Adapter.getCurrentCommand();
		
		intent.putExtra("CMD", currentCommand);
		intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, mRoomId);
		intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, mRoomTitle);
		intent.putExtra(AppKeys.KEY_SWITCH_ID_TAG, swId);
		intent.putExtra("scroll",(Parcelable)getIntent().getParcelableExtra("scroll"));
		overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);

		startActivity(intent);
		
		
		
	

}

public void handleIntent() {
	try {

		Intent intent = getIntent();

		String mSwitchId = intent.getStringExtra(AppKeys.KEY_SWITCH_ID_TAG);
		
		
		currentButtonType=intent.getStringExtra(AppKeys.KEY_BUTTON_TYPE);
		
		currentCommand=intent.getStringExtra("CMD");
		
		mSwitchModel = getSwitchFromDb(mSwitchId);

	} catch (Exception ex) {
		ex.toString();
	}
}


private Switch_Model getSwitchFromDb(String switchId) {
	Switch_Model switchModel = null;

	try {
		switchModel = new Select().from(Switch_Model.class).where("switch_id = ?", switchId).executeSingle();
	} catch (Exception ex) {
		ex.toString();
	}

	return switchModel;
}

}
