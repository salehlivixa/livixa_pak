package com.livixa.apacam.client.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.client.R;
import object.p2pipcam.adapter.ESP_Result_List_Adapter;

public class ESP_REsult_Activity extends Activity {
	
	
	
	ListView espResultsListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_esp__result_);
		espResultsListView=(ListView) findViewById(R.id.espResultsListView);
		
		
		
		try
		{
	
			ESP_Result_List_Adapter esp_Result_List_Adapter = new ESP_Result_List_Adapter(ESP_REsult_Activity.this);	
			
		
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
	
	Intent intent = new Intent(this, SwitchConfigurationActivity.class);
	startActivity(intent);
	KisafaApplication.perFormActivityBackTransition(this);
	//overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
}





}
