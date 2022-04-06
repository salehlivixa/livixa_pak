package com.livixa.apacam.client.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity.SwitchTypes;
import com.livixa.apacam.client.activity.DateAndTimePickerForMoodsActivity.connectTask;
import com.livixa.apacam.client.activity.MoodsCommandManager.RequestTypes;
import com.livixa.apacam.client.activity.Moods_RoomAndSwitchesTCPActivity.Buttons;
import com.livixa.apacam.client.activity.Moods_TCPClient_New.ServerMessageResopnse;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue.CustomDialogueClickListner;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.client.R;

public class DeleteSwitchMood {
	
	Context mContext;
	
	String deleteCommand="";
	
     Moods_TCPClient_New singleTcpClient;
 	
 	connectTask task;
 	
 	boolean isLatestCommand=false;
     
     
    boolean isSocketConnected=false;
     
   
    Mood_Model mMoodModel;
    
    Switch_Model mSwitchModel;
    
    
    boolean  isConnectionFailedEventOccured=false;
    
    
    
       DeleteMoodResultListner listner;
    
       boolean isButtonOneActive=false;
	   boolean isButtonTwoActive=false;
	   boolean isButtonThreeActive=false;
    
	   
	   String command1="";
	   
	   String command2="";
	   
	   String command3="";
    
	
	public DeleteSwitchMood(Context mContext)
	{
		this.mContext=mContext;
		
	}
	
	
	public void deleteButtonMood( Switch_Model mSwitchModel , Mood_Model  mMoodModel)
	{
		
		
		this.mSwitchModel=mSwitchModel;
		this.mMoodModel=mMoodModel;
		
		
		
		
		startTCPClient();
	}
	
	
	public static  boolean isButtonAssociatedWithThisMood(int moodIdentifire,String buttonCommand)
	{
       
		String tempMood=DateAndTimePickerForMoodsActivity.getButtonCurrentMood(moodIdentifire,buttonCommand);
		
		return  tempMood.substring(12,14).equals("00") ? false : true; 
	}
	
	
     public void upDateButtonsStateAccordingtoSwitchType( String cmd1,String cmd2,String cmd3)
     {
    	 
    	 
    	 
    	 isButtonOneActive =   cmd1!=null && cmd1.trim().length() > 0  &&  isButtonAssociatedWithThisMood(mMoodModel.moodIdentifer,cmd1) ? true : false;
    	 
    	 isButtonTwoActive =   cmd2!=null && cmd2.trim().length() > 0  &&  isButtonAssociatedWithThisMood(mMoodModel.moodIdentifer,cmd2) ? true : false;
    	 
    	 isButtonThreeActive = cmd3!=null && cmd3.trim().length() > 0  &&  isButtonAssociatedWithThisMood(mMoodModel.moodIdentifer,cmd3) ? true : false;
    	 
    	
    	
    	 
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
		}
		}catch(Exception ex)
		{
			
		}
	    
    }
	
	
	 public  class connectTask extends AsyncTask<String,String,Moods_TCPClient_New> {
		 
	        boolean firstMessageArrived=false;
	        
	        int currentActionIndex=0;
	        
	        
	        
	        
	        @Override
	        protected void onPreExecute() {
	         
	        super.onPreExecute();
	        
	        
	        //WaitingStaticProgress.showProgressDialog("Please wait...", mContext);
	        
	        }
	        
	        

			@Override
	        protected Moods_TCPClient_New doInBackground(String... message) {
	 
	            //we create a TCPClient object and
				try
				{
				if(singleTcpClient==null)
				{
					
					firstMessageArrived=false;
					
					
					
						singleTcpClient = new Moods_TCPClient_New(new Moods_TCPClient_New.OnMessageReceived() {
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
					onsocketConnection(Buttons.BUTTON_ONE.getValue());
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
	 
	 
	 public void onsocketConnection(final String  buttonType) {
		 
 		
		
		 
		 byte[] cmd = MoodsCommandManager.requestReadCommand( mSwitchModel.mac_address.substring(0,6),mSwitchModel.type , buttonType );
		    
			
			
			
			
	     singleTcpClient.setCallbackForGetCommand(new ServerMessageResopnse() {
			
			

		

			@Override
			public void onGetCommandFromServer(final String cmd) {
				
				
				
				
				
				
				
				if(mSwitchModel.type.equals(SwitchTypes.SWITCH_2.getValue()))
				 {
					 
					if(buttonType.equals(Buttons.BUTTON_ONE.getValue()))
					{
						
						command1=cmd;
						
						onsocketConnection(Buttons.BUTTON_TWO.getValue());
						
					}
					else if(buttonType.equals(Buttons.BUTTON_TWO.getValue()))
					{
						
						command2=cmd;
						
						upDateButtonsStateAccordingtoSwitchType(command1,command2,command3);
						
						if(isButtonOneActive)
						{
							
							String commandToWrite=prepareDeleteCommand(command1);
							
							byte []temp=MoodsCommandManager.writeCommand(commandToWrite);
							
							singleTcpClient.sendMessageByte(temp);
							
						}
						else if(isButtonTwoActive)
						{
							String commandToWrite=prepareDeleteCommand(command2);
							
							byte []temp=MoodsCommandManager.writeCommand(commandToWrite);
							
							singleTcpClient.sendMessageByte(temp);
							
						}
						else
						{
							
							NoButtonAvailableToDelete();
							
						}
							
						
						
						
					}
					 
				 }
				else if(mSwitchModel.type.equals(SwitchTypes.SWITCH_3.getValue()))
				{
					
					if(buttonType.equals(Buttons.BUTTON_ONE.getValue()))
					{
						command1=cmd;
						onsocketConnection(Buttons.BUTTON_TWO.getValue());
						
					}
					else if(buttonType.equals(Buttons.BUTTON_TWO.getValue()))
					{
						command2=cmd;
						onsocketConnection(Buttons.BUTTON_THREE.getValue());
						
					}
					else
					{
						
						command3=cmd;
						
						upDateButtonsStateAccordingtoSwitchType(command1,command2,command3);
						
						
						if(isButtonOneActive)
						{
							
							String commandToWrite=prepareDeleteCommand(command1);
							
							byte []temp=MoodsCommandManager.writeCommand(commandToWrite);
							
							singleTcpClient.sendMessageByte(temp);
							
						}
						else if(isButtonTwoActive)
						{
							String commandToWrite=prepareDeleteCommand(command2);
							
							byte []temp=MoodsCommandManager.writeCommand(commandToWrite);
							
							singleTcpClient.sendMessageByte(temp);
							
						}
						else if(isButtonThreeActive)
						{
							String commandToWrite=prepareDeleteCommand(command3);
							
							byte []temp=MoodsCommandManager.writeCommand(commandToWrite);
							
							singleTcpClient.sendMessageByte(temp);
							
						}
						else
						{
							NoButtonAvailableToDelete();
							
						}
						
					}
					
				}
				else
				{
				
					 command1=cmd;
					upDateButtonsStateAccordingtoSwitchType(command1,command2,command3);
					
					if(isButtonOneActive)
					{
					       
					        
					        upDateButtonsStateAccordingtoSwitchType(command1,command2,command3);
					     
					        String commandToWrite=prepareDeleteCommand(command1);
							
							byte []temp=MoodsCommandManager.writeCommand(commandToWrite);
							
							singleTcpClient.sendMessageByte(temp);
							
					}
					else
					{
						NoButtonAvailableToDelete();
						
					}
					
					
				}
				
				
				
			}
		}, cmd);
		 
		 
		 
		 
		 
	 }
	 
	 
	 //-------------------------------------------------------------------------------
	 
	 
	 private void readMessageReponse(final String message) {
		 
		 
		 
		 
		 if(mSwitchModel.type.equals(SwitchTypes.SWITCH_2.getValue()))
		 {
			 
			 if(message.substring(12,14).equals(Buttons.BUTTON_ONE.getValue()))
			 {
				 
				    if(isButtonTwoActive)
					{
				    	
				    	command1=message;
				    	
						String commandToWrite=prepareDeleteCommand(command2);
						
						byte []temp=MoodsCommandManager.writeCommand(commandToWrite);
						
						singleTcpClient.sendMessageByte(temp);
					}
				    else
				    {
				    	
				    	AppPreference.saveValue(mContext, command1, AppKeys.KEY_BUTTON_ONE_CMD);
				    	
				    	if(listner!=null)
						{
							listner.onDeleteMood(mMoodModel);
						}
						
				    	stopTCPClient();
				    	
				    }
				    
				 
			 }
			 else  if(message.substring(12,14).equals(Buttons.BUTTON_TWO.getValue()))
			 {
				     AppPreference.saveValue(mContext, command1, AppKeys.KEY_BUTTON_ONE_CMD);
				     AppPreference.saveValue(mContext, command2, AppKeys.KEY_BUTTON_TWO_CMD);
			    	
			    	if(listner!=null)
					{
						listner.onDeleteMood(mMoodModel);
					}
			    	
			    	stopTCPClient();
				 
			 }
			 
			 
		 }
		 else if(mSwitchModel.type.equals(SwitchTypes.SWITCH_3.getValue()))
		 {
			 
			 if(message.substring(12,14).equals(Buttons.BUTTON_ONE.getValue()))
			 {
				 
				    if(isButtonTwoActive)
					{
				    	
				    	command1=message;
				    	
						String commandToWrite=prepareDeleteCommand(command2);
						
						byte []temp=MoodsCommandManager.writeCommand(commandToWrite);
						
						singleTcpClient.sendMessageByte(temp);
					}
				    else if(isButtonThreeActive)
				    {
				    	command1=message;
				    	
						String commandToWrite=prepareDeleteCommand(command3);
						
						byte []temp=MoodsCommandManager.writeCommand(commandToWrite);
						
						singleTcpClient.sendMessageByte(temp);
				    }
				    else
				    {
				    	
				    	AppPreference.saveValue(mContext, command1, AppKeys.KEY_BUTTON_ONE_CMD);
				    	
				    	if(listner!=null)
						{
							listner.onDeleteMood(mMoodModel);
						}
						
				    	stopTCPClient();
				    	
				    }
				    
				 
			 }
			 else  if(message.substring(12,14).equals(Buttons.BUTTON_TWO.getValue()))
			 {
				 
				  if(isButtonThreeActive)
				    {
				    	command2=message;
				    	
						String commandToWrite=prepareDeleteCommand(command3);
						
						byte []temp=MoodsCommandManager.writeCommand(commandToWrite);
						
						singleTcpClient.sendMessageByte(temp);
				    }
				  
				  else
				  {
				 
				     AppPreference.saveValue(mContext, command1, AppKeys.KEY_BUTTON_ONE_CMD);
				     AppPreference.saveValue(mContext, command2, AppKeys.KEY_BUTTON_TWO_CMD);
				     AppPreference.saveValue(mContext, command2, AppKeys.KEY_BUTTON_THREE_CMD);
			    	
			    	if(listner!=null)
					{
						listner.onDeleteMood(mMoodModel);
					}
			    	
			    	
			    	stopTCPClient();
			    	
				  }
				 
			 }
			 else  if(message.substring(12,14).equals(Buttons.BUTTON_THREE.getValue()))
			 {
				 
				 AppPreference.saveValue(mContext, command1, AppKeys.KEY_BUTTON_ONE_CMD);
			     AppPreference.saveValue(mContext, command2, AppKeys.KEY_BUTTON_TWO_CMD);
			     AppPreference.saveValue(mContext, command2, AppKeys.KEY_BUTTON_THREE_CMD);
		    	
		    	if(listner!=null)
				{
					listner.onDeleteMood(mMoodModel);
				}
		    	
		    	
		    	stopTCPClient();
				 
			 }
			 
		 }
		 else
		 {
			 
			    AppPreference.saveValue(mContext, command1, AppKeys.KEY_BUTTON_ONE_CMD);
		    	
		    	if(listner!=null)
				{
					listner.onDeleteMood(mMoodModel);
				}
				
		    	stopTCPClient();
			 
		 }
		 
		 
			
		
		 
			
		}

	 

		public void onSocketTimeOutFailier(String string) {
			
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
							
							if(listner!=null)
							{
								listner.onFailed();
							}
							
							
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
		
	 
	 
		public String  prepareDeleteCommand(String readCommand)
		{
		
			String tempCommand=readCommand.substring(0,8) + RequestTypes.WRITE.getValue() + readCommand.substring(10,readCommand.length());
    		
    		int currentIndex=mMoodModel.moodIdentifer;
    		
    		int moodGap=14;
    		
    		int startIndex=currentIndex*moodGap;
    		
    		
    		int endIndex=startIndex + moodGap;
    		
    		
    		
    		tempCommand=tempCommand.substring(0,startIndex) + "00000000000000"  +tempCommand.substring(endIndex ,tempCommand.length());
    		
    		
    		return tempCommand;
			
			
			
		}
	 
	 
		/*public String  upDateCurrentCommand(String readCommand)
		{
		
			String tempCommand=buttonCMD;
    		
    		int currentIndex=mMoodModel.moodIdentifer;
    		
    		int moodGap=14;
    		
    		int startIndex=currentIndex*moodGap;
    		
    		
    		int endIndex=startIndex + moodGap;
    		
    		
    		
    		tempCommand=tempCommand.substring(0,startIndex) + readCommand.substring(startIndex,endIndex) +tempCommand.substring(endIndex ,tempCommand.length());
    		
    		
    		return tempCommand;
			
			
			
		}*/
	 
	 
		public interface DeleteMoodResultListner
		{
			
			public void onDeleteMood(Mood_Model mMood);
			
			public void onFailed();
			
		}
		
		
		public void setActionCompleteListner( DeleteMoodResultListner listner)
		{
			
			this.listner=listner;
		}
	 
		
		public void NoButtonAvailableToDelete()
		{
			
			if(listner!=null)
			{
				listner.onDeleteMood(mMoodModel);
			}
			
	    	stopTCPClient();
		}

}
