package com.livixa.apacam.client.activity;

import java.util.List;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.espressif.iot.esptouch.EsptouchTask;
import com.espressif.iot.esptouch.IEsptouchListener;
import com.espressif.iot.esptouch.IEsptouchResult;
import com.espressif.iot.esptouch.IEsptouchTask;
import com.espressif.iot.esptouch.demo_activity.EspWifiAdminSimple;
import com.espressif.iot.esptouch.task.__IEsptouchTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.widget.CustomEditText;
import com.livixa.apacam.client.widget.CustomTextView;
import com.livixa.apacam.dbmodel.ESP_Result_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.R;

public class SwitchConfigurationActivity extends Activity {

	
	
	private  CustomTextView  mSSID;
	private  CustomEditText mTaskCound;
	private  CustomEditText mPassword;
	
	private String TAG=SwitchConfigurationActivity.class.getName();
	
	private EspWifiAdminSimple mWifiAdmin;
	
	
	LinearLayout cancelButtonLyout;
	
	private IEsptouchTask mEsptouchTask;
	
	
	boolean stopFromBackMove=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_switch_configuration);
		
		
		mSSID=(CustomTextView) findViewById(R.id.sSDID);
		mPassword=(CustomEditText) findViewById(R.id.pass);
		mTaskCound=(CustomEditText) findViewById(R.id.taskCount);
		
		cancelButtonLyout=(LinearLayout) findViewById(R.id.cancelButtonLyout);
		
		//mShSwitchView=(ShSwitchView) findViewById(R.id.sSIDHidden);
		
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
		cancelButtonLyout.setVisibility(View.GONE);
		
		
		mWifiAdmin = new EspWifiAdminSimple(this);
		
		
		TextView cancel=(TextView) cancelButtonLyout.findViewById(R.id.tv_Cancel);
		 
		 cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				stopFromBackMove=false;
				
				if (__IEsptouchTask.DEBUG) {
					Log.i(TAG, "progress dialog is canceled");
				}
				if (mEsptouchTask != null) {
					
					mEsptouchTask.interrupt();
				}
				
				cancelButtonLyout.setVisibility(View.GONE);
				
			}
		});
	}
	
	
	@Override
	protected void onResume() {
	
		
		String apSsid = mWifiAdmin.getWifiConnectedSsid();
		if (apSsid != null) {
			mSSID.setText(apSsid);
		} else {
			mSSID.setText("");
		}
		
		
		boolean isApSsidEmpty = TextUtils.isEmpty(apSsid);
		
		
		super.onResume();
	}
	
	public void onCofirmClick(View view)
	{
		
		
		new Delete().from(ESP_Result_Model.class).execute();
		
		String apSsid = mSSID.getText().toString();
		String apPassword = mPassword.getText().toString();
		String apBssid = mWifiAdmin.getWifiConnectedBssid();
		
		String isSsidHiddenStr = "NO";
		String taskResultCountStr = mTaskCound.getText().toString();
		
		if(taskResultCountStr.trim().length()==0)
		{
			taskResultCountStr="0";
		}
		
		
		
		
		if (false) 
		{
			isSsidHiddenStr = "YES";
		}
		if (__IEsptouchTask.DEBUG) {
			Log.d(TAG, "mBtnConfirm is clicked, mEdtApSsid = " + apSsid
					+ ", " + " mEdtApPassword = " + apPassword);
		}
		new EsptouchAsyncTask3().execute(apSsid, apBssid, apPassword,
				isSsidHiddenStr, taskResultCountStr);
	}
	
	
	@Override
	public void onBackPressed() {
		 
		
		
		if(stopFromBackMove)
		{
			return;
		}
		
		super.onBackPressed();
		
		Intent intent;
		intent = new Intent(SwitchConfigurationActivity.this, SettingsActivity.class);
		
		startActivity(intent);
		KisafaApplication.perFormActivityBackTransition(this);
		finish();
		
		
		
	}
	
	
	public void OnBackClick(View view)
	{
		onBackPressed();
	}
	
	
	
	
	private List<Switch_Model> fetchSwitchesFromDb()
	{
		List<Switch_Model> switchModelList=null;
		try
		{
			switchModelList = new Select().from(Switch_Model.class).where("Switch_Model.model_status != ?", AppKeys.KEY_IS_DELETED).execute();
		}catch(Exception ex)
		{
			ex.printStackTrace();;
		}
		return switchModelList;
	}
	
	private void onEsptoucResultAddedPerform(final IEsptouchResult result) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				
				
				String text = result.getBssid() +" "+getString(R.string.isconnectedtothewifi);
				Toast.makeText(SwitchConfigurationActivity.this, text,
						Toast.LENGTH_LONG).show();
				
				
				
				/*List<Switch_Model> mSwitchModelList = fetchSwitchesFromDb();
				
				
				if(mSwitchModelList!=null && mSwitchModelList.size() > 0  && result!=null )
					
				{
					
					
					
					
					
					
					for(int i=0; i<mSwitchModelList.size(); i++)
					{
						Switch_Model switch_Model=mSwitchModelList.get(i);
						
						for(int j=0; j<SystemValue._hosts.size(); j++)
						{
							
							
							
							String deviceMac=result.getInetAddress().get.replace(":", "").substring(6, 12).toUpperCase();
							String switchMac=switch_Model.mac_address.substring(0,6);

                             if(deviceMac.equals(switchMac))
                             {
                            	 
                            	 switch_Model.ip_address=hostBean.ipAddress;
                            	 switch_Model.save();
                            	 break;
                             }
							
						}
						
						
					}
					
				}*/
				
				
				
				/*String SSID=result.getBssid();
				String iNetAddress=result.getInetAddress().getHostAddress();
				String hostName=result.getInetAddress().getHostName();
				String success="Yes";
				
				ESP_Result_Model esp_Result_Model = new Select().from(ESP_Result_Model.class).where("hostName = ?", hostName)
				.executeSingle();
				
				
				if(esp_Result_Model==null)
				{
					esp_Result_Model=new ESP_Result_Model();
				}
				esp_Result_Model.hostName=hostName;
				esp_Result_Model.BSSID=SSID;
				esp_Result_Model.IpAddress=iNetAddress;
				esp_Result_Model.isSuccessfull=success;
				esp_Result_Model.save();*/
			}

		});
	}
	
	
	private IEsptouchListener myListener = new IEsptouchListener() {

		@Override
		public void onEsptouchResultAdded(final IEsptouchResult result) {
			onEsptoucResultAddedPerform(result);
		}
	};
	
	private class EsptouchAsyncTask3 extends AsyncTask<String, Void, List<IEsptouchResult>> {

		private ProgressDialog mProgressDialog;

		
		// without the lock, if the user tap confirm and cancel quickly enough,
		// the bug will arise. the reason is follows:
		// 0. task is starting created, but not finished
		// 1. the task is cancel for the task hasn't been created, it do nothing
		// 2. task is created
		// 3. Oops, the task should be cancelled, but it is running
		private final Object mLock = new Object();

		@Override
		protected void onPreExecute() {
			
			
			cancelButtonLyout.setVisibility(View.VISIBLE);
			
			stopFromBackMove=true;
			
			 //AVLoadingIndicatorView  aviConfiguration =(AVLoadingIndicatorView) cancelButtonLyout.findViewById(R.id.aviConfiguration)
			
			
			 
			
			
			/*mProgressDialog = new ProgressDialog(SwitchConfigurationActivity.this);
			mProgressDialog
					.setMessage("Esptouch is configuring, please wait for a moment...");
			mProgressDialog.setCanceledOnTouchOutside(false);
			mProgressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					synchronized (mLock) {
						if (__IEsptouchTask.DEBUG) {
							Log.i(TAG, "progress dialog is canceled");
						}
						if (mEsptouchTask != null) {
							mEsptouchTask.interrupt();
						}
					}
				}
			});
			mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE,
					"Waiting...", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			mProgressDialog.show();
			mProgressDialog.getButton(DialogInterface.BUTTON_POSITIVE)
					.setEnabled(false);*/
		}

		@Override
		protected List<IEsptouchResult> doInBackground(String... params) {
			int taskResultCount = -1;
			synchronized (mLock) {
				String apSsid = params[0];
				String apBssid = params[1];
				String apPassword = params[2];
				String isSsidHiddenStr = params[3];
				String taskResultCountStr = params[4];
				boolean isSsidHidden = false;
				if (isSsidHiddenStr.equals("YES")) {
					isSsidHidden = true;
				}
				taskResultCount = Integer.parseInt(taskResultCountStr);
				mEsptouchTask = new EsptouchTask(apSsid, apBssid, apPassword,
						isSsidHidden, SwitchConfigurationActivity.this);
				mEsptouchTask.setEsptouchListener(myListener);
			}
			List<IEsptouchResult> resultList = mEsptouchTask.executeForResults(taskResultCount);
			return resultList;
		}

		@Override
		protected void onPostExecute(List<IEsptouchResult> result) {
			/*mProgressDialog.getButton(DialogInterface.BUTTON_POSITIVE)
					.setEnabled(true);
			mProgressDialog.getButton(DialogInterface.BUTTON_POSITIVE).setText(
					"Confirm");*/
			IEsptouchResult firstResult = result.get(0);
			// check whether the task is cancelled and no results received
			if (!firstResult.isCancelled()) {
				int count = 0;
				// max results to be displayed, if it is more than maxDisplayCount,
				// just show the count of redundant ones
				final int maxDisplayCount = 5;
				// the task received some results including cancelled while
				// executing before receiving enough results
				if (firstResult.isSuc()) {
					StringBuilder sb = new StringBuilder();
					for (IEsptouchResult resultInList : result) {
						sb.append("Esptouch success, bssid = "
								+ resultInList.getBssid()
								+ ",InetAddress = "
								+ resultInList.getInetAddress()
										.getHostAddress() + "\n");
						count++;
						if (count >= maxDisplayCount) {
							break;
						}
					}
					if (count < result.size()) {
						sb.append("\nthere's " + (result.size() - count)
								+ " more result(s) without showing\n");
					}
					
					
					
					
					
					/*mProgressDialog.setMessage(sb.toString());
					mProgressDialog.dismiss();*/
					
					cancelButtonLyout.setVisibility(View.GONE);
					
				} else {
					
					cancelButtonLyout.setVisibility(View.GONE);
					
					/*mProgressDialog.setMessage("Esptouch fail");
					mProgressDialog.dismiss();*/
					
					
					
				}
				
				stopFromBackMove=false;
				handleConfigurationResult(result);
				//onTest(null);
				
				
			}
		}
	}
	
	
	public void onTest(View view)
	{
		//ESP_REsult_Activity
		
		
		Intent intent;
		intent = new Intent(SwitchConfigurationActivity.this, ESP_REsult_Activity.class);
		
		startActivity(intent);
		KisafaApplication.perFormActivityNextTransition(this);
		finish();
	
		
	}
	
	
	public void handleConfigurationResult(List<IEsptouchResult> result )
	{

		
		
		Switch_Model switchModel=null;
		String deviceMac=null;
		
		if(result!=null)
		{
		
					for(int i=0; i<result.size(); i++)
					{
					
						IEsptouchResult iEsptouchResult =result.get(i);
						
						if(iEsptouchResult.isSuc())
						{
							try
							{
							deviceMac=iEsptouchResult.getBssid().replace(":", "").substring(6, 12).toUpperCase();
							}catch(Exception e){
								e.printStackTrace();
							}
							
							if(deviceMac!=null && deviceMac.trim().length() > 0)
							{
							
								try
								   {
							       switchModel = new Select().from(Switch_Model.class).where("Switch_Model.mac_address LIKE ?","%"+deviceMac+"%").executeSingle();
								   }catch(Exception ex)
								   {
									   ex.printStackTrace();
								   }
								
								
								if(switchModel !=null && !switchModel.model_status.equalsIgnoreCase(AppKeys.KEY_IS_DELETED))
								{
									
									switchModel.ip_address=iEsptouchResult.getInetAddress().getHostAddress();
									switchModel.save();
									
									
									
								}
							}
						
						
					}
						
						//----------------------------
						
						try
						{
						
						String SSID=iEsptouchResult.getBssid();
						String iNetAddress=iEsptouchResult.getInetAddress().getHostAddress();
						//String hostName=iEsptouchResult.getInetAddress().getHostName();
						String success="Yes";
						
						
						
						ESP_Result_Model esp_Result_Model=null;
						
						esp_Result_Model=new ESP_Result_Model();
						
						//esp_Result_Model.hostName=hostName;
						esp_Result_Model.BSSID=SSID;
						esp_Result_Model.IpAddress=iNetAddress;
						esp_Result_Model.isSuccessfull=success;
						esp_Result_Model.save();
						
						}catch(Exception ex)
						{
							ex.printStackTrace();
						}
						
						
						//---------------------------
					
					}
		}
		
		onTest(null);
	
	}
	
	
}
