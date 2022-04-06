package com.livixa.apacam.client.activity;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.activeandroid.query.Select;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.TextView;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity.SwitchTypes;
import com.livixa.apacam.client.activity.Moods_RoomAndSwitchesTCPActivity.Buttons;
import com.livixa.apacam.client.activity.Moods_TCPClient_New.ServerMessageResopnse;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue.CustomDialogueClickListner;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.apacam.services.TCP_Client_Service;
import com.livixa.client.R;
import object.p2pipcam.adapter.MoodsRoomSwitchesGridViewAdapter;
import object.p2pipcam.adapter.MoodsRoomSwitchesGridViewAdapter.MoodsTCPTask;


public class MoodsRoomWithSwitchesActivity extends Activity implements MoodsTCPTask{

	private String mRoomTitle = "";

	private String mRoomId = "";

	private TextView mTVRoomTitle;

	private GridView mRoomSwitchesGridView;

	private View mEmptyView;

	MoodsRoomSwitchesGridViewAdapter mainScreenRoomSwitchesGridViewAdapter;

	private Room_Model mRoomModel;
	
	
	
    boolean mBound = false;
    
    
    

	private Switch_Model mSwitchModel;

	
    Moods_TCPClient_New singleTcpClient;
	
	connectTask task;
	
	boolean isLatestCommand=false;
    
    
    boolean isSocketConnected=false;
    
   public static  boolean isPerformingFirstConnection=true;
   
   
   boolean isCallFromPopup=false;
   
   
   boolean  isConnectionFailedEventOccured=false;
   
 
   String command1="";
   
   String command2="";
   
   String command3="";
    

	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_with_switches);

		mTVRoomTitle = (TextView) findViewById(R.id.tv_title);
		handleIntent();
		mTVRoomTitle.setText(mRoomTitle);

		initUiComponents();

		initOthers();
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
	}

	private void initUiComponents() {
		mRoomSwitchesGridView = (GridView) findViewById(R.id.roomSwitchesGridView);

		mEmptyView = findViewById(R.id.roomSwitchesEmptylayout);

	}

	private void initOthers() {

		mainScreenRoomSwitchesGridViewAdapter = new MoodsRoomSwitchesGridViewAdapter(MoodsRoomWithSwitchesActivity.this,
				mEmptyView, mRoomSwitchesGridView, mRoomModel);
		mRoomSwitchesGridView.setAdapter(mainScreenRoomSwitchesGridViewAdapter);
		
		mainScreenRoomSwitchesGridViewAdapter.setTCPStartCallBack(MoodsRoomWithSwitchesActivity.this);
		

	}

	public void handleIntent() {
		try {

			Intent intent = getIntent();

			mRoomId = intent.getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
			mRoomTitle = intent.getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
			mRoomModel = getRoomFromDb(mRoomId);

		} catch (Exception ex) {
			ex.toString();
		}
	}

	public void onbackButttonClick(View view) {
		onBackPressed();
	}

	public void onhomeButttonClick(View view) {
		
		
		
		
		
		finish();

		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		// overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		KisafaApplication.perFormActivityBackTransition(this);
		
		
		
		
		
	}
	
	
	

    @Override
    protected void onDestroy() {
    	 
    	super.onDestroy();
    	
    	
    }
    
    
	@Override
	public void onBackPressed() {
		
		
		
		
		super.onBackPressed();

		finish();

		Intent intent = new Intent(this, Moods_View_RoomsActivity.class);

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
	
	
	
	private  boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	

	@Override
	public void startTCPClientTask(Switch_Model switchModel) {
		
		
		
		if(!isNetworkAvailable() && AppPreference.getSavedData(MoodsRoomWithSwitchesActivity.this, AppKeys.KEY_REMOTE_OPTION_TAG))
		{
			WaitingStaticProgress.hideProgressDialog();
			
			final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(MoodsRoomWithSwitchesActivity.this,"No internet connection found");
			
			cusDial.setListner(new CustomDialogueClickListner() {
				
				@Override
				public void onCustomDialogueClick() {
					
					WaitingStaticProgress.hideProgressDialog();
					cusDial.dismiss();
					
					
					
					
				}
			});
			
			
			cusDial.show();
			
			return;
		}
		
		
		
		
         this.mSwitchModel=switchModel;
		
		
		WaitingStaticProgress.showProgressDialog("", MoodsRoomWithSwitchesActivity.this);
		
		isPerformingFirstConnection=true;
		
		 startTCPClient();
		 
		    AppPreference.saveValue(MoodsRoomWithSwitchesActivity.this, "", AppKeys.KEY_BUTTON_ONE_CMD);
			
			AppPreference.saveValue(MoodsRoomWithSwitchesActivity.this, "", AppKeys.KEY_BUTTON_TWO_CMD);
			
			AppPreference.saveValue(MoodsRoomWithSwitchesActivity.this, "", AppKeys.KEY_BUTTON_THREE_CMD);
		
		
	}
	
	
	public void startTCPClient()
    {
    	singleTcpClient=null;
    	
    	task=new connectTask();
    	task.execute("");
	    
    }
	
	
	 public  class connectTask extends AsyncTask<String,String,Moods_TCPClient_New> {
		 
	        boolean firstMessageArrived=false;
	        
	        int currentActionIndex=0;

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
								
								if(isPerformingFirstConnection)
								{
								
									isPerformingFirstConnection=false;
									currentActionIndex=1;
									
									onProgressUpdate(message);
								}
								
							}

							@Override
							public void onSocketSelfClose(String message) {
								
								currentActionIndex=3;
								isSocketConnected=false;
								isConnectionFailedEventOccured=true;
								onProgressUpdate(message);
								  
							}
			            },MoodsRoomWithSwitchesActivity.this);
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
					
					
					//readMessageReponse(values[0]);
					
					
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
	
	 
	 //events--------------------------------------------------------------------------------------------------------
	 
	 public void onsocketConnection(String tempButtomType ) {
		 
		 
		
		 
		
		 
		 byte[] cmd = MoodsCommandManager.requestReadCommand( mSwitchModel.mac_address.substring(0,6),mSwitchModel.type , tempButtomType );
		    
			
			
			
			
	     singleTcpClient.setCallbackForGetCommand(new ServerMessageResopnse() {
			
			@Override
			public void onGetCommandFromServer(final String cmd) {
				
				
				
				if(command1.length()==0)
				{
					command1=cmd;
					
					if(mSwitchModel.type.equals(SwitchTypes.SWITCH_2.getValue()) || mSwitchModel.type.equals(SwitchTypes.SWITCH_3.getValue()))
					{
						onsocketConnection(Buttons.BUTTON_TWO.getValue());
					}
					else
					{
						 move_toNext();
					}
					
					
				}
				else if(command2.length()==0)
				{
					command2=cmd;
					if( mSwitchModel.type.equals(SwitchTypes.SWITCH_3.getValue()))
					{
						onsocketConnection(Buttons.BUTTON_THREE.getValue());
					}
					else
					{
						
						 move_toNext();
						
					}
					
				}
				else
				{
					
					command3=cmd;
					
					 move_toNext();
				
				}
				 
				
				
			}
		}, cmd);
		 
		 
		 
		// move_toNext();
		 
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
	 
	 public void move_toNext()
	 {
		 
		 
		 stopTCPClient();
		 
		 String mergedCommand="CC  C5 56 43  03   02    01   08   09   07E0   01 01 01 08   09   07E0   01 01 01 08   09   07E001 01 01 08   09   07E0   01 01 01 08   09   07E0   01 01 01 08   09   07E0   01 01 01 08   09   07E0   01 01 01 08   09   07E0   01 01 01 08   09   07E0   01 01 01 08   09   07E0   01 01 01 08   09   07E0   01 01 01 08   09   07E0   01 01 01 08   09   07E0   01 01 01 08   09   07E0   01 01 01 08   09   07E0   01 01 01     CC       0D 0A";
		 
		 
		 String testRead="CC45BB04040101" //14
		 		+ "       00000000000000" //28
		 		+ "       00000000000000" //42
		 		+ "       00000000000000" //56
		 		+ "       00000000000000" //70
		 		+ "       00000000000000" //84
		 		+ "       00000000000000" //96
		 		+ "       00000000000000" //110
		 		+ "       00000000000000" //124
		 		+ "       00000000000000" //138
		 		+ "       00000000000000" //152
		 		+ "       00000000000000" //166
		 		+ "       00000000000000" //180
		 		+ "       00000000000000" //194
		 		+ "       00000000000000" //208
		 		+ "       00000000000000" //222
		 		+ "       00000D0A";      //230
		 
		 
		 //080907E001 01 01 08   09
		
		 
		 if(mSwitchModel.type.equals(SwitchTypes.SWITCH_2.getValue()))
		 {
		 
		     mergedCommand=buttonsResponsetoSwitchRespnset2Button(command1,command2);
		     
		     
		     
		     
		    AppPreference.saveValue(MoodsRoomWithSwitchesActivity.this, command1, AppKeys.KEY_BUTTON_ONE_CMD);
				
			AppPreference.saveValue(MoodsRoomWithSwitchesActivity.this, command2, AppKeys.KEY_BUTTON_TWO_CMD);
				
				
		 
		 }
		 else if(mSwitchModel.type.equals(SwitchTypes.SWITCH_3.getValue()))
		 {
			 
			AppPreference.saveValue(MoodsRoomWithSwitchesActivity.this, command1, AppKeys.KEY_BUTTON_ONE_CMD);
			
			AppPreference.saveValue(MoodsRoomWithSwitchesActivity.this, command2, AppKeys.KEY_BUTTON_TWO_CMD);
			
			AppPreference.saveValue(MoodsRoomWithSwitchesActivity.this, command3, AppKeys.KEY_BUTTON_THREE_CMD);
			 
			 mergedCommand=buttonsResponsetoSwitchRespnset3Button(command1,command2,command3);
		 }
		 else
		 {
			 mergedCommand=command1;
			 AppPreference.saveValue(MoodsRoomWithSwitchesActivity.this, command1, AppKeys.KEY_BUTTON_ONE_CMD);
		 }
		 
		 
		 final String mergedCommandCarrier=mergedCommand.replace(" ", "");
		 
		 runOnUiThread( new Runnable() {
				
				
				public void run() {
					
					
					
					
					Intent intent = new Intent(MoodsRoomWithSwitchesActivity.this, Moods_RoomAndSwitchesTCPActivity.class);
					
					String mRoomId = getIntent().getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
					String mRoomTitle = getIntent().getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
					
					intent.putExtra("CMD", mergedCommandCarrier);
					
					
					intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, mRoomId);
					intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, mRoomTitle);
					intent.putExtra(AppKeys.KEY_SWITCH_ID_TAG, mSwitchModel.switch_id);
					
					startActivity(intent);
					
					KisafaApplication.perFormActivityNextTransition(MoodsRoomWithSwitchesActivity.this);
					
					finish();
				 
					
					
					
					
				}
			});
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public static String buttonsResponsetoSwitchRespnset3Button(String cmd1,String cmd2,String cmd3)
		{
			
			
			String mesgingString=cmd1.substring(0,14);
			
			int currentIndex=0;
			
			int moodGap=14;
			
			
			
			for(int i=0; i < 14  ;i++) 
			{
				
				
				
				
				currentIndex=i+1;
				
				
				if(currentIndex!=14)
				{
				
				
				int startIndex=currentIndex*moodGap;
				
				int endIndex=startIndex + moodGap;
				
				
				String tempMoodValuesButton1=cmd1.substring( startIndex , endIndex);
				
				String tempMoodValuesButton2=cmd2.substring( startIndex , endIndex);
				
				String tempMoodValuesButton3=cmd3.substring( startIndex , endIndex);
				
				//  response 11111111111111111111111111111111111
				String hexOnOffState1=tempMoodValuesButton1.substring(12,14);
				
				
						
				
			     //  response 222222222222222222222222222222222222222
				String hexOnOffState2=tempMoodValuesButton2.substring(12,14);
				
				
				
			//  response 222222222222222222222222222222222222222
			
				String hexOnOffState3=tempMoodValuesButton3.substring(12,14);
				
				
				
				
				
				
				
				if(hexOnOffState3.equals("00") && hexOnOffState2.equals("00") && hexOnOffState1.equals("00"))
				{
					
					
					mesgingString +=tempMoodValuesButton1;
					
				}
				else
				{
					
					String tempString="";
					
					if(!hexOnOffState3.equals("00"))
					{
						tempString=cmd3;
					}
					else if(!hexOnOffState2.equals("00"))
					{
						tempString=cmd2;
					}
					else if(!hexOnOffState1.equals("00"))
					{
						tempString=cmd1;
					}
					
					
					mesgingString +=tempString.substring(startIndex,endIndex);
					
					
				}
				
				
				
				
				}
			
			
			
					else
					{
						int startIndex=currentIndex*moodGap;
						
						int endIndex=startIndex + 28;
						
						
						String tempMoodValuesButton1=cmd1.substring( startIndex , endIndex);
						
						String tempMoodValuesButton2=cmd2.substring( startIndex , endIndex);
						
						String tempMoodValuesButton3=cmd3.substring( startIndex , endIndex);
						
						
						if(!tempMoodValuesButton1.substring(26,28).equals("00"))
						{
							mesgingString +=tempMoodValuesButton1;
						}
						else if(!tempMoodValuesButton2.substring(26,28).equals("00"))
						{
							mesgingString +=tempMoodValuesButton2;
						}
						else if(!tempMoodValuesButton3.substring(26,28).equals("00"))
						{
							mesgingString +=tempMoodValuesButton3;
						}
						else
						{
							mesgingString +=tempMoodValuesButton1;
							
						}
						
					}
				
			}
			
			mesgingString+=cmd1.substring(mesgingString.length(),cmd1.length());
			
			return mesgingString;
			
		}
	 
	 
	 public static String buttonsResponsetoSwitchRespnset2Button(String cmd1,String cmd2)
		{
			
			String mesgingString=cmd1.substring(0,14);
			
			int currentIndex=0;
			
			int moodGap=14;
			
			
			
			for(int i=0; i < 14  ;i++) 
			{
				
				
				
				
				currentIndex=i+1;
			
				if(currentIndex!=14)
				{
				
				int startIndex=currentIndex*moodGap;
				
				int endIndex=startIndex + moodGap;
				
				
				String tempMoodValuesButton1=cmd1.substring( startIndex , endIndex);
				
				String tempMoodValuesButton2=cmd2.substring( startIndex , endIndex);
				
				
				
				//  response 11111111111111111111111111111111111
				String hexOnOffState1=tempMoodValuesButton1.substring(12,14);
				
				
						
				
			     //  response 222222222222222222222222222222222222222
				String hexOnOffState2=tempMoodValuesButton2.substring(12,14);
				
				
				
				
				if(hexOnOffState2.equals("00") && hexOnOffState1.equals("00"))
				{
					
					
					mesgingString +=tempMoodValuesButton1;
					
				}
				else
				{
					
					String tempString="";
					
					 if(!hexOnOffState1.equals("00") && hexOnOffState2.equals("00"))
					{
						tempString=cmd1;
					}
					 else if(!hexOnOffState2.equals("00") && hexOnOffState1.equals("00"))
					{
						tempString=cmd2;
					}
					 else
					 {
						 tempString=cmd2;
					 }
					
					
					mesgingString +=tempString.substring(startIndex,endIndex);
					
					
				}
				
				}
				else
				{
					int startIndex=currentIndex*moodGap;
					
					int endIndex=startIndex + 28;
					
					
					String tempMoodValuesButton1=cmd1.substring( startIndex , endIndex);
					
					String tempMoodValuesButton2=cmd2.substring( startIndex , endIndex);
					
					
					if(!tempMoodValuesButton1.substring(26,28).equals("00"))
					{
						mesgingString +=tempMoodValuesButton1;
					}
					else if(!tempMoodValuesButton2.substring(26,28).equals("00"))
					{
						mesgingString +=tempMoodValuesButton2;
					}
					else
					{
						mesgingString +=tempMoodValuesButton1;
						
					}
					
				}
				
				
				
			}
			
			
		mesgingString+=cmd1.substring(mesgingString.length(),cmd1.length());
			
			
			return mesgingString;
			
		}
		
		
		
		
		public Date getHextoSQLDate(String day,String month ,String year,String min,String hour,boolean repeat)
		{
			
			
			       int days=Integer.parseInt(day,16);
			       int months=Integer.parseInt(month,16);
			       int years=Integer.parseInt(year,16);
			       int mins=Integer.parseInt(min,16);
			       int hours=Integer.parseInt(hour,16);
			       
			       Calendar cal = Calendar.getInstance();
			       
			        if(repeat)
			        {
			
			        	cal.set(Calendar.YEAR, 1990);
						cal.set(Calendar.MONTH, 0);
						cal.set(Calendar.DAY_OF_MONTH, 0);
						cal.set(Calendar.HOUR_OF_DAY, hours);
						cal.set(Calendar.MINUTE, mins);
						cal.set(Calendar.SECOND, 0);
						cal.set(Calendar.MILLISECOND, 0);
					
					
			        }
			        else
			        {
			        	cal.set(Calendar.YEAR, years);
						cal.set(Calendar.MONTH, months);
						cal.set(Calendar.DAY_OF_MONTH, days);
						cal.set(Calendar.HOUR_OF_DAY, hours);
						cal.set(Calendar.MINUTE, mins);
						cal.set(Calendar.SECOND, 0);
						cal.set(Calendar.MILLISECOND, 0);
					
			        	
			        }
					
					
				    Date tempdate=new Date(cal.getTimeInMillis());
			
				return tempdate;
			
		}
		
		
		public boolean  isValidDateAndTime(String day,String month ,String year,String min,String hour)
		{
			
			
			       int days=Integer.parseInt(day,16);
			       int months=Integer.parseInt(month,16);
			       int years=Integer.parseInt(year,16);
			       int mins=Integer.parseInt(min,16);
			       int hours=Integer.parseInt(hour,16);
			     
			       
			        
			       if(days==0)
			       {
			    	   return false;
			       }
			       
			       if(months==0)
			       {
			    	   return false;
			       }
			
			       if(years==0)
			       {
			    	   return false;
			       }
					
			       
			
				return true;
			
		}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public void onSocketConnectedSelfClose(String message) {
			
			
			try
			{
			
				
			
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					
					WaitingStaticProgress.hideProgressDialog();
					
					
					
				}
			});
			
			singleTcpClient.stopClient();
			
			}catch(Exception ex){}
		}

			
	 public void unableToCreateConnection(String message) {
			
			
			try
			{
			
				
			
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					
					WaitingStaticProgress.hideProgressDialog();
					
					final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(MoodsRoomWithSwitchesActivity.this,getString(R.string.Errorinconnection));
					
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
		
	 
	 public void onSocketTimeOutFailier(String message) {
			
			try
			{
			
			
			
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					
					WaitingStaticProgress.hideProgressDialog();
					
					/*final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(RoomAndSwitchesTCPActivity.this,"Failed to connect");
					
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
	
}
