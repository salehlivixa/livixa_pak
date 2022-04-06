package com.livixa.apacam.client.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity.SwitchTypes;
import com.livixa.apacam.client.activity.DateAndTimePickerForMoodsActivity.connectTask;
import com.livixa.apacam.client.activity.TCPClient_ForGettingTime.ServerMessageResopnse;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue.CustomDialogueClickListner;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.client.R;

public class GetSwitchTime {
	
	Context mContext;
	
	String deleteCommand="";
	
	TCPClient_ForGettingTime singleTcpClient;
 	
 	connectTask task;
 	
 	boolean isLatestCommand=false;
     
     
    boolean isSocketConnected=false;
     
   
    Mood_Model mMoodModel;
    
    Switch_Model mSwitchModel;
    
    
    boolean  isConnectionFailedEventOccured=false;
    
    
    SwitchTimeResult  timeListner;
    
	
	public GetSwitchTime(Context mContext)
	{
		this.mContext=mContext;
		
		
		
		
	}
	
	
	public void getTime( Switch_Model mSwitchModel)
	{
		
		
		this.mSwitchModel=mSwitchModel;
		
		try
		{
		
		if(AppPreference.getSavedData(mContext, AppKeys.KEY_REMOTE_OPTION_TAG))
		{
			TCPClient_ForGettingTime.SERVERIP="kisafavps.com";
			TCPClient_ForGettingTime.SERVERPORT=22222;
		}
		else
		{
			TCPClient_ForGettingTime.SERVERIP=mSwitchModel.ip_address;
			TCPClient_ForGettingTime.SERVERPORT=55555;
		}
			
		}catch (Exception e) {
			
		}
		
		startTCPClient();
	}
	
	
	
	
	private  boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	
  	
  	
	public void startTCPClient()
    {
		
		if(!isNetworkAvailable()&& AppPreference.getSavedData(mContext, AppKeys.KEY_REMOTE_OPTION_TAG))
		{
			
			WaitingStaticProgress.hideProgressDialog();
			
			final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(mContext,"No internet connection found");
			
			cusDial.setListner(new CustomDialogueClickListner() {
				
				@Override
				public void onCustomDialogueClick() {
					
					
					cusDial.dismiss();
					
					
					
					Intent intent = new Intent(mContext, MoodsRoomWithSwitchesActivity.class);
					
					String mRoomId = ((Activity)mContext).getIntent().getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
					String mRoomTitle = ((Activity)mContext).getIntent().getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
					
					String swId = ((Activity)mContext).getIntent().getStringExtra(AppKeys.KEY_SWITCH_ID_TAG);
					
					
					intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, mRoomId);
					intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, mRoomTitle);
					intent.putExtra(AppKeys.KEY_SWITCH_ID_TAG, swId);
					
					((Activity)mContext).overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);

					((Activity)mContext).startActivity(intent);
					
					((Activity)mContext).finish();
					
					
				}
			});
			
			
			cusDial.show();
			
			return;
		}
		
    	singleTcpClient=null;
    	
    	task=new connectTask();
    	task.execute("");
	    
    }
	
	
	public void stopTCPClient()
    {
		
		
		try
		{
		if(singleTcpClient!=null)
		{
    	singleTcpClient.stopClient();
    	
    	task.cancel(true);
    	task=null;
    	singleTcpClient=null;
		}
		}catch(Exception ex)
		{
			
		}
	    
    }
	
	
	 public  class connectTask extends AsyncTask<String,String,TCPClient_ForGettingTime> {
		 
	        boolean firstMessageArrived=false;
	        
	        int currentActionIndex=0;
	        
	        
	        
	        
	        @Override
	        protected void onPreExecute() {
	        // TODO Auto-generated method stub
	        super.onPreExecute();
	        
	        
	       
	        
	        }
	        
	        

			@Override
	        protected TCPClient_ForGettingTime doInBackground(String... message) {
	 
	            //we create a TCPClient object and
				try
				{
				if(singleTcpClient==null)
				{
					
					firstMessageArrived=false;
					
					
					
						singleTcpClient = new TCPClient_ForGettingTime(new TCPClient_ForGettingTime.OnMessageReceived() {
			                @Override
			               
			                public void messageReceived(final String message) {
			                   
			                
			                	currentActionIndex=2;
			                	
			                	isSocketConnected=true;
			                	
			                	onProgressUpdate(message);
			                	
			                }

							@Override
							public void onsocketConnectionClosed(String message) {
								
								
								currentActionIndex=5;
								isSocketConnected=false;
								isConnectionFailedEventOccured=true;
								onProgressUpdate(message);
								
								
								
							}

							@Override
							public void onsocketConnectionFailer(String message) {
								
								isSocketConnected=false;
								currentActionIndex=4;
								isConnectionFailedEventOccured=true;
								onProgressUpdate(message);
							}

							@Override
							public void onSocketConnection(String message) {
								
								isSocketConnected=true;
								
								isConnectionFailedEventOccured=false;
								
								
									currentActionIndex=1;
									
									onProgressUpdate(message);
							
								
							}

							@Override
							public void onSocketSelfClose(String message) {
								
								currentActionIndex=3;
								isSocketConnected=false;
								isConnectionFailedEventOccured=true;
								onProgressUpdate(message);
								  
							}
			            },mContext);
						singleTcpClient.run();
				}
				
				}catch(Exception ex){}
	 
	            return null;
	        }
			
			@Override
	        protected void onProgressUpdate(String... values) {
	            super.onProgressUpdate(values);
	            
	           //hello
	            
	            switch (currentActionIndex) {
				case 1:
					onsocketConnection("");
					break;
					
				case 2:
					
					
					readMessageReponse(values[0]);
					
					
					break;
					
				case 3:
					
					
					onSocketConnectedSelfClose(values[0]);
					
					
					break;
					
					
				case 4:
					
					
					unableToCreateConnection(values[0]);
					break;
					
					
				case 5:
					
					
					onSocketTimeOutFailier(values[0]);
					
					
					
					break;
					
				
					
					
					
					

				default:
					break;
				}
			
		}

			
			
	
			
	 }
	
	//////--------------------------------------------------------------------------------------
	 
	 
	 public void onsocketConnection(String message ) {
		 
 		
		
		 
		 byte[] cmd=SwitchCommandManager.requestGetSwitchTimeCommand(mSwitchModel.mac_address.substring(0,6),mSwitchModel.type);
		    
			
			
			
			
	     singleTcpClient.setCallbackForGetCommand(new ServerMessageResopnse() {
			
			

		

			@Override
			public void onGetCommandFromServer(final String cmd) {
				
				
				
				if(cmd!=null && cmd.length() > 0)
				{
					
					if(timeListner!=null)
					{
						
						timeListner.OnTimeResponse(cmd);
					}
				}
				
				
			}
		}, cmd);
		 
		 
		 
		 
		 
	 }
	 
	 
	 //-------------------------------------------------------------------------------
	 
	 
	 private void readMessageReponse(final String message) {
			
		 ((Activity) mContext).runOnUiThread(new Runnable() {
				public void run() {
					
					
					
					
					
					WaitingStaticProgress.hideProgressDialog();
					
				}
			});
		 
			
		}

	 

		public void onSocketTimeOutFailier(String string) {
			
			try
			{
			
			
			
				((Activity) mContext).runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					
					WaitingStaticProgress.hideProgressDialog();
					
					/*final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(mContext,"Failed to connect");
					
					cusDial.setListner(new CustomDialogueClickListner() {
						
						@Override
						public void onCustomDialogueClick() {
							
							
							cusDial.dismiss();
							
							
							
							
						}
					});
					
					
					cusDial.show();*/
					
					
				}
			});
			
			
			singleTcpClient.stopClient();
			
			
			}catch(Exception ex){}
			
		}







		public void unableToCreateConnection(String string) {
			
			try
			{
			
				
			
				((Activity) mContext).runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					
					WaitingStaticProgress.hideProgressDialog();
					
					final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(mContext,mContext.getString(R.string.Errorinconnection));
					
					cusDial.setListner(new CustomDialogueClickListner() {
						
						@Override
						public void onCustomDialogueClick() {
							
							
							cusDial.dismiss();
							
							
							
						}
					});
					
					
					cusDial.show();
				}
			});
			
			
			singleTcpClient.stopClient();
			
			
			}catch(Exception ex){}
		}
 	 
 	 
		private void onSocketConnectedSelfClose(String string) {
			
			
			try
			{
			
				
			
			((Activity) mContext).runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					
					WaitingStaticProgress.hideProgressDialog();
					
					
					
				}
			});
			
			singleTcpClient.stopClient();
			
			}catch(Exception ex){}
			
			
		}
		
	 
	     public interface SwitchTimeResult
	     {
	    	 public void  OnTimeResponse(String time);
	     }
		
	 
	     public void addResponceTimeListner(SwitchTimeResult lisntner)
	     {
	    	 timeListner=lisntner;
	     }

}
