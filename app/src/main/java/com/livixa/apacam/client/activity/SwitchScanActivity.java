package com.livixa.apacam.client.activity;

import java.util.List;

import com.activeandroid.query.Select;
import com.afollestad.materialdialogs.MaterialDialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import info.lamatricexiste.network.Ip_Scanner_Local;
import info.lamatricexiste.network.Ip_Scanner_Local.ScanResultLocal;
import info.lamatricexiste.network.LocalDeviceInfo;
import info.lamatricexiste.network.Network.HostBean;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.R;
import object.p2pipcam.adapter.Switch_Scann_List_Adapter;
import object.p2pipcam.system.SystemValue;

public class SwitchScanActivity  extends Activity {
	
	
   //----------------------------------------------------------
	
	
	//---------------------------------------------------------
	
	public enum SwitchIdStatus {
		OK, BAD_LENGHT, ID_NOT_HEXADECIMAL, INVALID_TYPE
	};

	
	public enum SwitchTypes {
		SWITCH_1("01"), SWITCH_2("02"), SWITCH_3("03"),  SWITCH_SOCKET("04"), SWITCH_AC("05"),SWITCH_INVALID("");
		
		 private String value;

		 SwitchTypes(final String value) {
		        this.value = value;
		    }

		    public String getValue() {
		        return value;
		    }

		    @Override
		    public String toString() {
		        return this.getValue();
		    }
	};
	
	private GridView mSwitchListView;
	
	private View mEmptyView;

	private Switch_Scann_List_Adapter mSwitchListAdaptor;

	private TextView btn_discover;

	private WifiP2pManager mManager;

	private Channel mChannel;
	
	private LinearLayout cancelButtonLyout;
	
	Ip_Scanner_Local ip_Scann;
	
	boolean disableGoingBack=false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_switch_scann);
		initUiComponents();
		initOthers();
		
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
	}

	private void initUiComponents() {
		mSwitchListView = (GridView) findViewById(R.id.switchListView);
		mEmptyView= findViewById(R.id.switchEmptylayout);
		btn_discover= (TextView) findViewById(R.id.startScan);
		cancelButtonLyout=(LinearLayout) findViewById(R.id.cancelButtonLyout);
		
		btn_discover.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                
            	
            	callPopup(); 
            	
            }
        });
	}

	
	
	
	
	
	
	private List<Switch_Model> fetchSwitchesFromDb()
	{
		List<Switch_Model> switchModelList=null;
		try
		{
			switchModelList = new Select().from(Switch_Model.class).where("Switch_Model.model_status != ? ORDER BY Switch_Model.type", AppKeys.KEY_IS_DELETED).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return switchModelList;
	}
	
	private void initOthers() {
		
		mSwitchListAdaptor = new Switch_Scann_List_Adapter(SwitchScanActivity.this,mEmptyView,mSwitchListView);
		
		mSwitchListView.setAdapter(mSwitchListAdaptor);

		
	}
	
	
	
	public void onbackButttonClick(View view) {
		
		
		
		if(disableGoingBack)
			return;
			
			
		onBackPressed();
	}

	
	@Override
	public void onBackPressed() {
		 
		super.onBackPressed();

		
		
		
		Intent intent = new Intent();
		intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
		KisafaApplication.perFormActivityBackTransition(this);
		finish();

	}
	

	
	private void callPopup() {

		 LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		 View popupView = layoutInflater.inflate(R.layout.scann_switch_setting_popup, null);
		
		TextView tv_cancel=(TextView) popupView.findViewById(R.id.cancelButton);
		TextView tv_scanAll=(TextView) popupView.findViewById(R.id.tv_scanAll);
		TextView tv_ipNotAvailable=(TextView) popupView.findViewById(R.id.tv_ipNotAvailable);
		
		
		
		

		final PopupWindow popupWindow=new PopupWindow(popupView,
		         LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,        
		 true);

		 popupWindow.setTouchable(true);
		 popupWindow.setFocusable(true);
		 
		 popupWindow.setAnimationStyle(R.style.BottonUpAnimation);
		 
		

		 popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
		 
		 
		 disableGoingBack=true;
		 
		 tv_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					disableGoingBack=false;
				}
			});
		 
		 
		 tv_scanAll.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					popupWindow.dismiss();
					
					
					try
					{
					
					cancelButtonLyout.setVisibility(View.VISIBLE);
					
					
					TextView tv=(TextView) cancelButtonLyout.findViewById(R.id.tv_Cancel);
					
					tv.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							
							ip_Scann.cancel(true);
							ip_Scann.cancelThisTask();
							cancelButtonLyout.setVisibility(View.GONE);
							
							
						}
					} );
					
					
					List<Switch_Model> mSwitchModelList = fetchSwitchesFromDb();
					
					if(mSwitchModelList!=null)
					{
					
						for(int j=0; j<mSwitchModelList.size(); j++)
						{
							mSwitchModelList.get(j).ip_address="";
							mSwitchModelList.get(j).save();
						}
						
						
						mSwitchModelList.clear();
					}
					
					
					initOthers();
					
					
					ip_Scann=new Ip_Scanner_Local(SwitchScanActivity.this);
					
					ip_Scann.setScannResultListner(new ScanResultLocal() {
						
						@Override
						public void onResult(LocalDeviceInfo deviceInfo) {
							
							
							Switch_Model switchModel=null;
							String deviceMac=null;
							
							try
							{
							deviceMac=deviceInfo.getMac().replace(":", "").substring(6, 12).toUpperCase();
							}catch(Exception e){}
							
							if(deviceMac!=null && deviceMac.trim().length() > 0)
							{
							
								try
								   {
							       switchModel = new Select().from(Switch_Model.class).where("Switch_Model.mac_address LIKE ?","%"+deviceMac+"%").executeSingle();
								   }catch(Exception ex)
								   {
									   ex.toString();
								   }
								
								
								if(switchModel !=null && !switchModel.model_status.equalsIgnoreCase(AppKeys.KEY_IS_DELETED))
								{
									
									switchModel.ip_address=deviceInfo.getiP();
									switchModel.save();
									
									runOnUiThread(new  Runnable() {
										public void run() {
											
											mSwitchListAdaptor.notifyDataSetChanged();
										}
									});
									
									
								}
								
								
							}
							
							
						}

						@Override
						public void onResultComplete() {
							
							
							cancelButtonLyout.setVisibility(View.GONE);
							disableGoingBack=false;
						}
					});
					
					ip_Scann.execute();
					
					
					}catch(Exception ex)
					{
						ex.toString();
					}
					
				}
			});
		 
		 
		 tv_ipNotAvailable.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					popupWindow.dismiss();
					
					try
					{
					
					cancelButtonLyout.setVisibility(View.VISIBLE);
					
					
					TextView tv=(TextView) cancelButtonLyout.findViewById(R.id.tv_Cancel);
					
					tv.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							
							ip_Scann.cancel(true);
							
							ip_Scann.cancelThisTask();
							
							cancelButtonLyout.setVisibility(View.GONE);
							
							disableGoingBack=false;
							
							
						}
					} );
					
					
					
					
					ip_Scann=new Ip_Scanner_Local(SwitchScanActivity.this);
					
					ip_Scann.setScannResultListner(new ScanResultLocal() {
						
						@Override
						public void onResult(LocalDeviceInfo deviceInfo) {
							
							
							Switch_Model switchModel=null;
							String deviceMac=null;
							
							try
							{
							deviceMac=deviceInfo.getMac().replace(":", "").substring(6, 12).toUpperCase();
							}catch(Exception e){}
							
							if(deviceMac!=null && deviceMac.trim().length() > 0)
							{
							
								try
								   {
							       switchModel = new Select().from(Switch_Model.class).where("Switch_Model.mac_address LIKE ?","%"+deviceMac+"%").executeSingle();
								   }catch(Exception ex)
								   {
									   ex.toString();
								   }
								
								
								if(switchModel !=null && !switchModel.model_status.equalsIgnoreCase(AppKeys.KEY_IS_DELETED))
								{
									
									if(switchModel.ip_address==null || !(switchModel.ip_address.trim().length() >=8))
									{
										switchModel.ip_address=deviceInfo.getiP();
										switchModel.save();
										
										
										runOnUiThread(new  Runnable() {
											public void run() {
												
												mSwitchListAdaptor.notifyDataSetChanged();
											}
										});
									}
									
									
								}
								
								
							}
							
							
						}

						@Override
						public void onResultComplete() {
							
							
							cancelButtonLyout.setVisibility(View.GONE);
							
							disableGoingBack=false;
						}
					});
					
					ip_Scann.execute();
					
					}catch(Exception ex)
					{
						ex.toString();
					}
					
					
				}
			});
		 
		 
		 popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			
			@Override
			public void onDismiss() {
				
				
				disableGoingBack=false;
				
			}
		});
		
		
	}
	

}
