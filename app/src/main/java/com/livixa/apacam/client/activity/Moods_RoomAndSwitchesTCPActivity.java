package com.livixa.apacam.client.activity;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activeandroid.query.Select;
import com.devsmart.android.ui.HorizontalListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity.SwitchTypes;
import com.livixa.apacam.client.activity.DeleteSwitchMood.DeleteMoodResultListner;
import com.livixa.apacam.client.activity.GetSwitchTime.SwitchTimeResult;
import com.livixa.apacam.client.activity.Moods_RoomAndSwitchesTCPActivity.Buttons;
import com.livixa.apacam.client.activity.Moods_TCPClient_New.ServerMessageResopnse;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.sliderview.CircularSeekBar;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.widget.CustomEditText;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons.CustomDialogueTwoButtonsClickListner;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue.CustomDialogueClickListner;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.R;

import object.p2pipcam.adapter.Moods_RoomandSwitchesTCPGridViewAdapter;
import object.p2pipcam.adapter.Moods_RoomandSwitchesTCPGridViewAdapter.TCPTask;
import object.p2pipcam.adapter.ViewRoomsGridViewAdapter;

import object.p2pipcam.utils.ItemClickSupport;
import object.p2pipcam.utils.MyDate;

public class Moods_RoomAndSwitchesTCPActivity extends Activity implements TCPTask{
	
	
	
	
	

	
	public enum Moods {
		TREVAL(1), SLEEP(2), WAKE_UP(3),  GUEST(4), LIVING(5),SAFETY(6) , AWAY(7);
		
		 private int value;

		 Moods(final int value) {
		        this.value = value;
		    }

		    public int getValue() {
		        return value;
		    }

		    
	};
	
	
	public enum Buttons {
		BUTTON_ONE("01"), BUTTON_TWO("02"), BUTTON_THREE("03");
		
		 private String value;

		 Buttons(final String value) {
		        this.value = value;
		    }

		    public String getValue() {
		        return value;
		    }

		    
	};
	
	

	private String mRoomTitle = "";

	private String mRoomId = "";

	
	Moods_RoomandSwitchesTCPGridViewAdapter mainScreenRoomSwitchesGridViewAdapter;

	
	RelativeLayout switchesMainLayout;
	
	LinearLayout roomsMainLayout;
	
	
	Moods_TCPClient_New singleTcpClient;
	
	connectTask task;
	
	Context mContext;
	
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	
	 LinearLayout layoutContainer;
		private Switch_Model mSwitchModel;
		
		
		CheckBox    acSwitchCheckBox;
		
		boolean isProgressIsZero=true;
		
		boolean lockProgressOne=false;
		boolean lockProgressTwo=false;
		boolean lockProgressThree=false;
		
		
		
		
		boolean isProgressIsZeroOne=true;
		
		boolean isProgressIsZeroTwo=true;
		
		boolean isProgressIsZeroThree=true;
		
		
		
		RelativeLayout rLayoutControl0;
		
		
		
		RelativeLayout rLayoutControl21;
		
	
		
		RelativeLayout rLayoutControl32;
		
		
		
		
		
		
		CheckBox    SwitchCheckBoxrLayoutControl0;
		
		
		
		CheckBox    SwitchCheckBoxrLayoutControl21;
		
		
		CheckBox    SwitchCheckBoxrLayoutControl32;
		
		
		
		
		CircularSeekBar seekBar;
		
		
		CircularSeekBar seekBarTwo;
		
		CircularSeekBar seekBarThree;
		
		
		
		 byte [] command=new byte[18];
		
		
	    View childLayout = null;
	    
	    
	    private String currentCommand="";
	    
	    
	    private String currentCommandForButton1="";
	    
	    private String currentCommandForButton2="";
	    
	    String currentSwitchLEDStatus="01";
	    
	    
	    boolean isLatestCommand=false;
	    
	    
	    boolean isSocketConnected=false;
	    
	   public static  boolean isPerformingFirstConnection=true;
	   
	   
	   boolean isCallFromPopup=false;
	   
	   
	   boolean  isConnectionFailedEventOccured=false;
	   
	   
	   
	   
	   //RecyclerView      hmoodsListviewTest;
	   
	   
	   
	   public static int REQUEST_IMAGE_CAPTURE=1;
		
		
		private int PICK_PHOTO_FOR_AVATAR=2;
		
		
		ImageView iv_img_blob;
		
		CustomEditText et_MoodName;
		
		
		View popupView;
		
		
	
	
    boolean mBound = false;

	
	
	private ColorsAdapter  colorsAdapter;
	
	private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
   
	private int lastHorizontalScrollPoisition=0;
    
	 List<Mood_Model>  mMoodsModelList;
	 
	 boolean isUserAddedMoodImage=false;
	 
	 
	 String currentSwitchData="01";
	 
	 
	 private GestureDetector gestureDetector;
	 
	 SingleTapConfirm singleTapConfirm;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moods_room_and_switches_tcp);
		
		mContext=Moods_RoomAndSwitchesTCPActivity.this;

		
		/*try
		{
		singleTapConfirm=new SingleTapConfirm();
		
		gestureDetector = new GestureDetector(this,singleTapConfirm);
		}catch(Exception ex)
		{
			
		}*/
		
		handleIntent();
		

		initUiComponents();

		initOthers();
		
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
		
		
	}
	
	
	
	
	
	
	private class SingleTapConfirm extends SimpleOnGestureListener {
		
		
		String buttonType=Buttons.BUTTON_ONE.getValue();
		
		public void setButtonData(String buttonType)
		{
			this.buttonType=buttonType;
		}
		
		
		@Override
		public boolean onDown(MotionEvent e) {
			
			
			
			return true;
		}
		
        
        @Override
        public boolean onDoubleTap(MotionEvent e) {
        	
        	
        	Intent intent = new Intent(Moods_RoomAndSwitchesTCPActivity.this, Moods_Display_Activity.class);
			
			String mRoomId = getIntent().getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
			String mRoomTitle = getIntent().getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
			
			intent.putExtra("CMD", currentCommand);
			
			intent.putExtra(AppKeys.KEY_BUTTON_TYPE, buttonType);
			intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, mRoomId);
			intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, mRoomTitle);
			intent.putExtra(AppKeys.KEY_SWITCH_ID_TAG, mSwitchModel.switch_id);
			
			startActivity(intent);
			
			KisafaApplication.perFormActivityNextTransition(mContext);
			
			finish();
			
        	
        	return true;
        }
        
    }


	private void initUiComponents() {
		
		
		switchesMainLayout=(RelativeLayout) findViewById(R.id.switchesMainLayout);
		
		mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Specify a layout for RecyclerView
        // Create a horizontal RecyclerView
        mLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
		
	}

	private void initOthers() {

		
		
		
		layoutContainer=(LinearLayout) findViewById(R.id.layoutContainer);
		
		addRequiredLayout();
		
		
		
		

	}

	public void handleIntent() {
		try {

			Intent intent = getIntent();

			String mSwitchId = intent.getStringExtra(AppKeys.KEY_SWITCH_ID_TAG);
			
			mRoomId = intent.getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
			mRoomTitle = intent.getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
			
			//lastHorizontalScrollPoisition=intent.getIntExtra("scroll", 0);
			
			currentCommand=intent.getStringExtra("CMD");
			
			mSwitchModel = getSwitchFromDb(mSwitchId);
			
			

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
		
		KisafaApplication.perFormActivityBackTransition(mContext);
		
		
		
		
		
	}
	
	
	private Switch_Model getSwitchFromDb(String roomId) {
		Switch_Model switchModel = null;

		try {
			switchModel = new Select().from(Switch_Model.class).where("switch_id = ?", roomId).executeSingle();
		} catch (Exception ex) {
			ex.toString();
		}

		return switchModel;
	}
	
	@Override
    protected void onStart() {
        super.onStart();
       
        
    }
	
	

    
    
    
	@Override
	public void onBackPressed() {
		
		
		
		
			super.onBackPressed();
			
			finish();
	
			Intent intent = new Intent(this, MoodsRoomWithSwitchesActivity.class);
			intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, mRoomId);
			intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, mRoomTitle);
			KisafaApplication.perFormActivityBackTransition(mContext);
	
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

	

	
	public void onSocketTimeOutFailier(String message) {
		
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
				
				final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(Moods_RoomAndSwitchesTCPActivity.this,mContext.getString(R.string.Errorinconnection));
				
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
	
	
	
	
	
	public String getCurrentButtonType()
	{
		
		String currentButtonToApplyMood=Buttons.BUTTON_ONE.value;
		if(mSwitchModel.type.equals(SwitchTypes.SWITCH_2.getValue()) && currentSwitchData.equals(Buttons.BUTTON_TWO.value))
		{
			currentButtonToApplyMood=Buttons.BUTTON_TWO.value;
			
		}
		
		else if(mSwitchModel.type.equals(SwitchTypes.SWITCH_3.getValue()) && currentSwitchData.equals(Buttons.BUTTON_TWO.value))
		{
			currentButtonToApplyMood=Buttons.BUTTON_TWO.value;
			
		}
		else if(mSwitchModel.type.equals(SwitchTypes.SWITCH_3.getValue()) && currentSwitchData.equals(Buttons.BUTTON_THREE.value))
		{
			currentButtonToApplyMood=Buttons.BUTTON_THREE.value;
			
		}
		else
		{
			currentButtonToApplyMood=Buttons.BUTTON_ONE.value;
		}
		
		return currentButtonToApplyMood;
		
	}

	
	public void onsocketConnection(final Mood_Model currentMoodModel,final boolean isBuutonOn) {
		
		
		
		
		final String tempCurrentButton=getCurrentButtonType();
			
			 byte[] cmd = MoodsCommandManager.requestReadCommand( mSwitchModel.mac_address.substring(0,6),mSwitchModel.type , tempCurrentButton );
			
			 
			 singleTcpClient.setCallbackForGetCommand(new ServerMessageResopnse() {
					
					@Override
					public void onGetCommandFromServer(final String cmd) {
						
					
						currentCommandForButton1=cmd;
						
						
						
						
						String commandtoWrite="";//prepareWriteCommandforButton1(currentMoodModel,currentCommandForButton1,isBuutonOn);
						
						if(tempCurrentButton.equals(Buttons.BUTTON_ONE.value))
						{
						//AppPreference.saveValue(mContext, cmd, AppKeys.KEY_BUTTON_ONE_CMD);
							
							commandtoWrite=prepareWriteCommandforButton1(currentMoodModel,AppPreference.getValue(mContext, AppKeys.KEY_BUTTON_ONE_CMD),isBuutonOn);
						}
						else if(tempCurrentButton.equals(Buttons.BUTTON_TWO.value))
						{
						//AppPreference.saveValue(mContext, cmd, AppKeys.KEY_BUTTON_TWO_CMD);
							commandtoWrite=prepareWriteCommandforButton1(currentMoodModel,AppPreference.getValue(mContext, AppKeys.KEY_BUTTON_TWO_CMD),isBuutonOn);
						}
						else if(tempCurrentButton.equals(Buttons.BUTTON_THREE.value))
						{
						//AppPreference.saveValue(mContext, cmd, AppKeys.KEY_BUTTON_THREE_CMD);
							commandtoWrite=prepareWriteCommandforButton1(currentMoodModel,AppPreference.getValue(mContext, AppKeys.KEY_BUTTON_THREE_CMD),isBuutonOn);
						}
						
						
						//Toast.makeText(mContext, ""+commandtoWrite.length(), Toast.LENGTH_SHORT).show();
						
						byte []temp=MoodsCommandManager.writeCommand(commandtoWrite.toUpperCase());
						
						
						
						
						singleTcpClient.sendMessageByte(temp);
						
						
						
						
					}
				}, cmd);
			
		
		
	}

	
	public String prepareWriteCommandforButton1(Mood_Model mMood, String currentCommand,boolean isON)
	{
		
		String tempCommand=currentCommand.substring(0,8) + MoodsCommandManager.RequestTypes.WRITE + currentCommand.substring(10,currentCommand.length());
		
		int currentIndex=mMood.moodIdentifer;
		
		int moodGap=14;
		
		int startIndex=currentIndex*moodGap;
		
		int endIndex=startIndex + moodGap;
		
		
		if(currentIndex!=14)
		{
		
		
		String tempMoodValues=currentCommand.substring( startIndex , endIndex);
		
		
		String tempTimeDate=mMood.time;
		
		
		MyDate date = HomeActivity.getAndroidDateFromString(tempTimeDate);
		
		
		
		int year= date.getYear();
		
		int month= date.getMonth();
		
		int dayofMonth= date.getDay();
		
		int hours= date.getHour();
		
		int minutes= date.getMinute();
		
		
		
		
		
		String hexDays=daysMonthsHoursToHexValue(dayofMonth);//tempMoodValues.substring(0,2);
		
		String hexMonths=daysMonthsHoursToHexValue(month);//tempMoodValues.substring(2,4);
		
		String hexYears=yearsToHexValue(year);//tempMoodValues.substring(4,8);
		
		String hexHours=daysMonthsHoursToHexValue(hours);//tempMoodValues.substring(8,10);
		
		String hexMinutes=daysMonthsHoursToHexValue(minutes);//tempMoodValues.substring(10,12);
		
		
		String hexOnOffState=  isON ? "01" : "02" ;
		
		
		
		if(mMood.isRepeatOn.equals("1"))
		{
		
			tempCommand=tempCommand.substring(0,startIndex) + AppKeys.KEY_CASE_REPEAT +hexHours+hexMinutes+ hexOnOffState+tempCommand.substring(endIndex ,tempCommand.length());
		
		}
		else
		{
			
			tempCommand=tempCommand.substring(0,startIndex) + hexDays + hexMonths + hexYears +hexHours+hexMinutes+ hexOnOffState+tempCommand.substring(endIndex ,tempCommand.length());
		}
		
		
		}
		else
		{
			
			moodGap=14;
			
			
			startIndex=currentIndex*moodGap;
			
			endIndex=startIndex + 28;
			
			String awayMood=DateAndTimePickerForAwayMoodActivity.getButtonAwayMood(14,this.currentCommand);
			
			tempCommand=tempCommand.substring(0,startIndex) + awayMood +  tempCommand.substring(endIndex ,tempCommand.length());
			
			
		}
		
		//tempCommand=MoodsCommandManager.getTestWriteCommand(mSwitchModel.mac_address.substring(0,6), mSwitchModel.type, "01");
		
		
		
		return tempCommand;
		
	}
	
	
	public String daysMonthsHoursToHexValue(int value)
	{
		String temp="";
		
		temp=Integer.toString(value, 16);
		
		if(temp.length()==1)
		{
			temp="0"+temp;
		}
		
		return temp;
		
	}
	
	public String yearsToHexValue(int value)
  	{
  		String temp=""+value;
  		
  		String completeYear="";
  		
  		completeYear=Integer.toString(value, 16);
  		
  		if(completeYear.length()<4)
  		{
  			
  			completeYear="0" + completeYear;
  		}
  		
  		
  		return completeYear;
  		
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

	

	
	
	//-------------------------------------------------------------------------------------------------------
	
	
		//-------------------------------------------------------------------------------------------------------
		
		//-------------------------------------------------------------------------------------------------------
		
		
		//-------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	 public  class connectTask extends AsyncTask<String,String,Moods_TCPClient_New> {
		 
	        boolean firstMessageArrived=false;
	        
	        int currentActionIndex=0;
	        
	        Mood_Model   currentMoodModel=null;
	        
	        boolean isBuutonOn=false;
	        
	        
	        connectTask(Mood_Model   currentMoodModel,boolean isBuutonOn)
	        {
	        	this.currentMoodModel=currentMoodModel;
	        	this.isBuutonOn=isBuutonOn;
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
			            },Moods_RoomAndSwitchesTCPActivity.this);
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
					
					
					onsocketConnection(currentMoodModel,isBuutonOn);
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
    
 	  
    
    
	@Override
	public void startTCPClientTask(Switch_Model switchModel) {
		
		this.mSwitchModel=switchModel;
		
		
		//WaitingStaticProgress.showProgressDialog("Connecting please wait..", Moods_RoomAndSwitchesTCPActivity.this);
		
		isPerformingFirstConnection=true;
		
		 //startTCPClient();
		
		
		
		//onsocketConnection("");
	
	
		
		
	}
    
	
	private  boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	public void startTCPClient(Mood_Model mModel, boolean isButtOn)
    {
		
		
		
		if(!isNetworkAvailable()  && AppPreference.getSavedData(mContext, AppKeys.KEY_REMOTE_OPTION_TAG))
		{
			WaitingStaticProgress.hideProgressDialog();
			
			final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(mContext,"No internet connection found");
			
			cusDial.setListner(new CustomDialogueClickListner() {
				
				@Override
				public void onCustomDialogueClick() {
					
					
					cusDial.dismiss();
					
					onBackPressed();
					
					
				}
			});
			
			
			cusDial.show();
			
			return;
		}
		
		
    	singleTcpClient=null;
    	
    	task=new connectTask(mModel,isButtOn);
    	task.execute("");
	    
    }
	
	
	
	
	
	public void addRequiredLayout()
	{

		
		layoutContainer.removeAllViews();
		
		LayoutInflater inflater = (LayoutInflater)      this.getSystemService(LAYOUT_INFLATER_SERVICE);
		
		SwitchTypes thisType=Add_Edit_SwitchActivity.getSwitchType(mSwitchModel.mac_address);
		
		
		
		
		
		
		if(thisType.equals(SwitchTypes.SWITCH_1))
		{
			childLayout=inflater.inflate(R.layout.switch_one_layout_mood,(ViewGroup) findViewById(R.id.switch_one_layoutt));
			
			rLayoutControl0=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl0);
			
			
			
			
			
			handleActionOfOneSwitch(childLayout);
			
		
			
			
			
			
			
		}
		else if(thisType.equals(SwitchTypes.SWITCH_2))
		{
			childLayout=inflater.inflate(R.layout.switch_two_layout_moods,(ViewGroup) findViewById(R.id.switch_two_layoutt));
			
			rLayoutControl0=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl0);
			
			
			
			
			
			rLayoutControl21=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl21);
			
			
			
			
			handleActionOfTwoSwitch(childLayout);
			
			
			
			
		}
		else if(thisType.equals(SwitchTypes.SWITCH_3))
		{
			childLayout=inflater.inflate(R.layout.switch_three_layout_moods,(ViewGroup) findViewById(R.id.switch_three_layoutt));
			
			
			rLayoutControl0=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl0);
			
			
			
			
			
			rLayoutControl21=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl21);
			
			
			
			rLayoutControl32=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl32);
			
			
			
			
			handleActionOfThreeSwitch(childLayout);
			
			ImageView img=(ImageView) childLayout.findViewById(R.id.switchIcon);
			
			
			
			
			
			
		}
		else if(thisType.equals(SwitchTypes.SWITCH_SOCKET))
		{
			childLayout=inflater.inflate(R.layout.switch_socket_layout_moods,(ViewGroup) findViewById(R.id.switch_socket_layoutt));
			handleActionsOfSocketSwitch(childLayout);
			
			ImageView img=(ImageView) childLayout.findViewById(R.id.switchIcon);
			
			
			
		}
		else if(thisType.equals(SwitchTypes.SWITCH_AC))
		{
			childLayout=inflater.inflate(R.layout.switch_ac_layout_moods,(ViewGroup) findViewById(R.id.ac_switch_layoutt));
			
			handleActionsOfACSwitch(childLayout);
			
			
			ImageView img=(ImageView) childLayout.findViewById(R.id.switchIcon);
			
			
			
			
		}
		
		
		((TextView)childLayout.findViewById(R.id.switchTitle)).setText(mSwitchModel.title);
		
		((TextView)findViewById(R.id.sw_tv_title)).setText(mSwitchModel.title);
		
		layoutContainer.addView(childLayout);
		
		
		
		
		colorsAdapter =new ColorsAdapter(mContext);
		
		mRecyclerView.setAdapter(colorsAdapter);
		
		//hmoodsListviewTest.setAdapter(hListAdapter);
		
		
		//mRecyclerView.scrollBy(lastHorizontalScrollPoisition, 0);
		
		Parcelable mListState=getIntent().getParcelableExtra("scroll");
		 if (mListState != null) {
		        mLayoutManager.onRestoreInstanceState(mListState);
		    }
		
		/*if(lastHorizontalScrollPoisition!=0)
		mRecyclerView.scrollToPosition(lastHorizontalScrollPoisition);*/
		
		
		ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
		      @Override
		      public void onItemClicked(RecyclerView recyclerView, int position, View v) {
		    	  
		    	  
		    	  Mood_Model   tempModel=(Mood_Model) v.getTag();
					
					if(tempModel.moodIdentifer > 6 && tempModel.moodIdentifer <= 13)
						OpenCustomMoodPopup(tempModel);
					else
						activateMood(tempModel);
		    	  
		       
		      }
		  });
		
		
		View addMood=findViewById(R.id.addMood);
		
		View getTime=findViewById(R.id.getTime);
		
		
		if(AppPreference.getValue(mContext,AppKeys.KEY_IS_SUB_USER)!=null && AppPreference.getValue(mContext,AppKeys.KEY_IS_SUB_USER).equals("1"))
		{
			
			addMood.setVisibility(View.GONE);
			
		}
		
		
		getTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				WaitingStaticProgress.showProgressDialog(getString(R.string.requestingTime), mContext);
				
				GetSwitchTime  getSwitchTime=new GetSwitchTime(mContext);
				getSwitchTime.addResponceTimeListner(new SwitchTimeResult() {
					
					@Override
					public void OnTimeResponse(final String time) {
						
						
						     WaitingStaticProgress.hideProgressDialog();
						     
						     
						     
						     runOnUiThread( new Runnable() {
								public void run() {
									
									
									 String hexDays=time.substring(12,14);
										
										String hexMonths=time.substring(14,16);
										
										String hexYears=time.substring(16,20);
										
										String hexHours=time.substring(20,22);
										
										String hexMinutes=time.substring(22,24);
										
										
										String hexVersion=time.substring(24,26).toUpperCase();
										
										String switchTime=getHextoSQLDate(hexDays,hexMonths,hexYears,hexMinutes,hexHours);
										
										MyDate mydate=HomeActivity.getAndroidDateFromString(switchTime);
							    		 String timeString=MyDate.getFormattedTime(mydate);
							    	     String dateString=MyDate.getFormattedDate(mydate);
							    	     
							    	     SimpleDateFormat phoneFormate;
							    	     
							    	     SimpleDateFormat phonetimeFormate = null;
							    	     
							    	     
							    	     final String format = Settings.System.getString(getContentResolver(), Settings.System.DATE_FORMAT);
							    	     //final String format = Settings.System.getString(getContentResolver(), Settings.System.);
							    	     
							    	     if (TextUtils.isEmpty(format)) {
							    	    	 phoneFormate = (SimpleDateFormat) android.text.format.DateFormat.getDateFormat(getApplicationContext());
							    	    	 phonetimeFormate=(SimpleDateFormat) android.text.format.DateFormat.getTimeFormat(getApplicationContext());
							    	     } else {
							    	    	 phoneFormate = new SimpleDateFormat(format);
							    	     }
							    	     
							    	     
							    	     
							    	     Calendar cal = Calendar.getInstance();
								  	       
							        	    cal.set(Calendar.YEAR, mydate.getYear());
								 			cal.set(Calendar.MONTH,mydate.getMonth()-1);
								 			cal.set(Calendar.DAY_OF_MONTH, mydate.getDay());
								 			cal.set(Calendar.HOUR_OF_DAY, mydate.getHour());
								 			cal.set(Calendar.MINUTE, mydate.getMinute());
								 			cal.set(Calendar.SECOND, 0);
								 			cal.set(Calendar.MILLISECOND, 0);
							        	 
							     	        
							     	        String datetoUse = phoneFormate.format(cal.getTime());
							     	        
							     	       timeString=phonetimeFormate.format(cal.getTime());
							     	        
									   
							    	     //String datetoUse=phoneFormate.format(dateString+" "+timeString);
									
									    final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(Moods_RoomAndSwitchesTCPActivity.this,"V: "+hexVersion+"\n"+datetoUse+" "+timeString);
										
										cusDial.setListner(new CustomDialogueClickListner() {
											
											@Override
											public void onCustomDialogueClick() {
												
												
												cusDial.dismiss();
												
												
												
												
											}
										});
										
										
										cusDial.show();
									
								}
							});
						
						   
						
						
						
					}
				});
				
				getSwitchTime.getTime(mSwitchModel);
				
			}
		});
		
		
		addMood.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				/*if(AppPreference.getValue(mContext,AppKeys.KEY_IS_SUB_USER)!=null && AppPreference.getValue(mContext,AppKeys.KEY_IS_SUB_USER).equals("1"))
				{
					
					final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(Moods_RoomAndSwitchesTCPActivity.this,"Subuser cannot add mood");
					
					cusDial.setListner(new CustomDialogueClickListner() {
						
						@Override
						public void onCustomDialogueClick() {
							
							
							cusDial.dismiss();
							
							
							
							
						}
					});
					
					
					cusDial.show();
					
					return;
				}	*/
				
			callPopupSetting(null);
				
			}
		});
		
	}
	
	
	
	
	
	
	private void handleActionOfOneSwitch(View view)
	{
		
		
		SwitchCheckBoxrLayoutControl0=new CheckBox(mContext);
		
		
		
		rLayoutControl0.setOnTouchListener(new ScaleDragViewTouchLintner(Buttons.BUTTON_ONE.value));
		
		
	
	        
		 
	   
	        
	}
	
	//123
	
	private void handleActionOfTwoSwitch(View view)
	{
		
		//SwitchCheckBoxrLayoutControl0=new CheckBox(mContext);
		
		
		
		 handleActionOfOneSwitch(view);
		
		
		 SwitchCheckBoxrLayoutControl21=new CheckBox(mContext);
		
		
	        //------------------------Switch Two Portion
	        
	        
	        
	        
		        
		 rLayoutControl21.setOnTouchListener(new ScaleDragViewTouchLintner(Buttons.BUTTON_TWO.value));
			 
			
			 
			 rLayoutControl21.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						
						
						
					}
				});
			 
			 
			
			 
			
	        
	        
	}
	
	
	public String convertIntProgressToHEX(String progressToSend)
	{
		
		
		if(progressToSend.equals("00"))
		{
			progressToSend="0B";
		}
		
		else if(progressToSend.equals("100"))
		{
			progressToSend="0A";
		}
		else
		{
			progressToSend=new StringBuilder(progressToSend).reverse().toString();
		}
		return progressToSend;
		
	}
	
	
	
	//321
	private void handleActionOfThreeSwitch(View view)
	{
		
		
		
		 handleActionOfTwoSwitch(view);
		
		 SwitchCheckBoxrLayoutControl32=new CheckBox(mContext);
		 
		 
		
		
 
			//------------------------Switch Three Portion
		        
		        
		       
		rLayoutControl32.setOnTouchListener(new ScaleDragViewTouchLintner(Buttons.BUTTON_THREE.value));
				 
				
				 
				 rLayoutControl32.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							
						}
					});
		        
			         
				 
	}
	
	
	private void handleActionsOfACSwitch(View view)
	{
		
		acSwitchCheckBox=new CheckBox(mContext);
		
		
		final ImageView frame=(ImageView) view.findViewById(R.id.acFrame);
		
		View acIcon =(View) view.findViewById(R.id.acIcon);
		
		
		acIcon.setOnTouchListener(new NoScaleDragViewTouchLintner(Buttons.BUTTON_ONE.value));
		
		
		
		
	}
	
	
	
	private void handleActionsOfSocketSwitch(View view)
	{
		
		acSwitchCheckBox=new CheckBox(mContext);
		
		
		final ImageView frame=(ImageView) view.findViewById(R.id.acFrame);
		
		
		View socketIcon =(View) view.findViewById(R.id.socketIcon);
		
		
		socketIcon.setOnTouchListener(new NoScaleDragViewTouchLintner(Buttons.BUTTON_ONE.value));
		
		frame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {}
		});
		
	}
	
	
	public void handleCommand(String command)
	{

       //123
		
        currentCommand= command.trim().toUpperCase();
        
        
          /*if(currentCommand!=null && currentCommand.substring(8,10).equals("04"))
          {
        	  currentTries=4;
        	  StaticProgress.hideProgressDialog();
          }*/
          
          
          
        
          String comingCommand= currentCommand;
        
          String mac=SwitchCommandManager.getSwitchMac(comingCommand);
        
          //Toast.makeText(mContext,mac, Toast.LENGTH_SHORT).show();
          
          String type=SwitchCommandManager.getSwitchType(comingCommand);
          
          //Toast.makeText(mContext,type, Toast.LENGTH_SHORT).show();
          
          String switchInsideTypes=SwitchCommandManager.getDimmerSettingValues(comingCommand);
          
          //Toast.makeText(mContext,switchInsideTypes, Toast.LENGTH_SHORT).show();
          
          
          currentSwitchLEDStatus=SwitchCommandManager.getLEDStatus(currentCommand);
          
          
          SwitchTypes thisType=Add_Edit_SwitchActivity.getSwitchType(mSwitchModel.mac_address);
          if(thisType.equals(SwitchTypes.SWITCH_1))
          {
        	 
        	 
        	  
          }
          else if(thisType.equals(SwitchTypes.SWITCH_2))
          {
        	 
        	 
        	  
          }
          else if(thisType.equals(SwitchTypes.SWITCH_3))
          {
        	 
        	
        	  
          }
          else if(thisType.equals(SwitchTypes.SWITCH_SOCKET))
          {
        	  
          }
          
          else if(thisType.equals(SwitchTypes.SWITCH_AC))
          {
        	  
          }
          
          
          
          
          
          WaitingStaticProgress.hideProgressDialog();
    
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
	
	
	public void readMessageReponse(final String message) {
		
		
		
		runOnUiThread(new Runnable() {
			public void run() {
				
				WaitingStaticProgress.hideProgressDialog();
				
				
				final String tempCurrentButton=getCurrentButtonType();
				
				if(tempCurrentButton.equals(Buttons.BUTTON_ONE.getValue()))
				{
				
					AppPreference.saveValue(mContext, message, AppKeys.KEY_BUTTON_ONE_CMD);
					
					
				}
				else if(tempCurrentButton.equals(Buttons.BUTTON_TWO.getValue()))
				{
				    AppPreference.saveValue(mContext, message, AppKeys.KEY_BUTTON_TWO_CMD);
					
				}
				else if(tempCurrentButton.equals(Buttons.BUTTON_THREE.getValue()))
				{
				    AppPreference.saveValue(mContext, message, AppKeys.KEY_BUTTON_THREE_CMD);
					
				}
				
				stopTCPClient();
			}
		});
		
		
	}
	
	
	public void onSwitchButttonClick(View view )
	{
		
		
		if(singleTcpClient!=null)
		{
		   singleTcpClient.stopClient();
		   if(task!=null)
		   {
			   task.cancel(true);
			   
			   task=null;
		   }
		   
		}
		
		
		onBackPressed();
		
	}
	
	
	public void onSwitchhomeButttonClick(View view)
	{
		onhomeButttonClick(view);
	}
	
	
	///////////////////////////------------------------------------------------------------------------------------------
	
	///////////////////////////------------------------------------------------------------------------------------------
	
	///////////////////////////------------------------------------------------------------------------------------------
	
	//mm
	
	
	private List<Mood_Model> fetchMoodsOfCurrentSwitchFromDb(String switchId)
	{
		List<Mood_Model> moodModelList=null;
		try
		{
			moodModelList = new Select().from(Mood_Model.class).where("Mood_Model.modelStatus != ? AND Mood_Model.switchId = ?",AppKeys.KEY_IS_DELETED ,switchId).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return moodModelList;
	}
	
	private List<Mood_Model> fetchAllMoodsOfCurrentSwitchFromDb(String switchId)
	{
		List<Mood_Model> moodModelList=null;
		try
		{
			moodModelList = new Select().from(Mood_Model.class).where("Mood_Model.switchId = ?" ,switchId).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return moodModelList;
	}
	
	
	
	//-------------------------------------------------------------------------------------------------------------
	
	
	
	 class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ViewHolder> {
	    private Context mContext;
	   
	    LayoutInflater inflater;
		private ImageLoader imageLoader;
		private Map<Integer, Integer> mMapNonCoustomMoodsImages=new  HashMap<Integer, Integer>();

	    public ColorsAdapter(Context context){
	       
	    	
	    	inflater = LayoutInflater.from(context);
	        
		      
	        
	         mMoodsModelList = fetchMoodsOfCurrentSwitchFromDb(mSwitchModel.switch_id);
			
			if(mMoodsModelList==null)
			{
				mMoodsModelList=new ArrayList<>();
				
			}
			
			
			
			
			
			Collections.sort(mMoodsModelList, new Comparator<Mood_Model>(){
			    public int compare(Mood_Model s1, Mood_Model s2) {
			    	
			        return  Integer.valueOf(s1.moodIdentifer).compareTo(s2.moodIdentifer);
			    }
			});

			
			updateMoodsOnSwitchResponse(mMoodsModelList,currentCommand);


			
			mMapNonCoustomMoodsImages.put(1, R.drawable.ic_travel_ic);
			mMapNonCoustomMoodsImages.put(2, R.drawable.ic_sleep_ic);
			mMapNonCoustomMoodsImages.put(3, R.drawable.ic_wake_up_ic);
			mMapNonCoustomMoodsImages.put(4, R.drawable.ic_guest_ic);
			mMapNonCoustomMoodsImages.put(5, R.drawable.ic_living_ic);
			mMapNonCoustomMoodsImages.put(6, R.drawable.ic_safety_ic);
			mMapNonCoustomMoodsImages.put(14, R.drawable.ic_away_ic);
			
			
			 imageLoader =ImageLoader.getInstance();
	        
	    }

	    public  class ViewHolder extends RecyclerView.ViewHolder{
	       
	    	public ImageView icon;
	    	public TextView  moodNmae;
	    	public TextView  repeatMood;
	    	public TextView time;
	 	    
	 	    LinearLayout moodItemBGLY;

	        public ViewHolder(View convertView){
	            super(convertView);
	            
	            icon = (ImageView) convertView.findViewById(R.id.img_icon_mood);
	            moodNmae = (TextView) convertView.findViewById(R.id.moodText);
	            repeatMood = (TextView) convertView.findViewById(R.id.repeatTV);
	            time = (TextView) convertView.findViewById(R.id.timeTV);
	            moodItemBGLY = (LinearLayout) convertView.findViewById(R.id.moodItemBGLY);
	        }
	    }

	    @Override
	    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
	       
	    	
	    	 LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			 View _view = layoutInflater.inflate(R.layout.moods_horizontal_listitem, null);
	    	
	        ViewHolder vh = new ViewHolder(_view);

	        return vh;
	    }

	    @Override
	    public void onBindViewHolder(ViewHolder holder, int position){
	        
	    	
	    	 Mood_Model mood_Model=mMoodsModelList.get(position);
	    	 
	    	 
	    	 holder.itemView.setTag(mood_Model);
	    	 
	    	
	    	if(mood_Model.moodIdentifer > 6 && mood_Model.moodIdentifer < 14)
	    	 {
				    		 try
				  	       {
				   		 if(mood_Model.picture!=null)  
				   		{
				    	
				              holder.icon.setImageBitmap(ViewRoomsGridViewAdapter.bytesToBitmap(mood_Model.picture));
				   		}
				   		else if(mood_Model.pictureURL!=null && mood_Model.pictureURL.trim().length() > 0 )
				   		{
				   			
				   			imageLoader.displayImage(mood_Model.pictureURL,holder.icon,KisafaApplication.getImageDisplayOption());
				   		}
				   		else
				   		{
				   		    holder.icon.setImageResource(R.drawable.icon_mood_custom_white);
				   		}
				       }catch(Exception ex)
				       {
				    	   
				       }
				    		 
				    		 
				    		 holder.moodNmae.setText(mood_Model.title);
	       
	       
	       }
	       else
	       {
	       	int _v = mood_Model.moodIdentifer;
	       	int value = mMapNonCoustomMoodsImages.get(_v != 0 ? _v : 1);
	       	if(value!=0)
	    	   holder.icon.setImageResource(value);
	    	   
	    	   
	    	   holder.moodNmae.setText(getMoodTitle(mood_Model.moodIdentifer));
	    	   
	       }
	       
	     
	       if(mood_Model.moodIdentifer != 14)
	       {
	    	   
	    	   try
		       {
		    	   
		    	   if(mood_Model.time!=null)
		    	   {
		    		   MyDate mydate = null;
		    		   String dtStart = mood_Model.time;
		    		   
		    		   mydate=HomeActivity.getAndroidDateFromString(dtStart);
		    		   
		    		  
		    		   
		    		   
		    		   holder.time.setText(MyDate.getFormattedTime(mydate));
		    		   
		    		   
		    		   holder.moodItemBGLY.setBackgroundResource(R.drawable.moods_status_active_bg);
		    	   }
		    	   else
		    	   {
		    		   holder.time.setText("N/A");
		    		   holder.moodItemBGLY.setBackgroundResource(R.drawable.moods_status_bg);
		    	   }
		    	   
		       }catch(Exception ex)
		       {
		    	   ex.toString();
		       }
		       
		       
		       
		       
		       try
		       {
		    	   
		    	   if(mood_Model.isRepeatOn.equals("1"))
		    	   {
		    		   holder.repeatMood.setText(getString(R.string.RepeatON));
		    	   }
		    	   else if(mood_Model.isRepeatOn.equals("0"))
		    	   {
		    		   
		    		   MyDate mydate = null;
		    		   String dtStart = mood_Model.time;
		    		   
		    		   mydate=HomeActivity.getAndroidDateFromString(dtStart);
		    		   
		    		  
		    		   
		    		   String dateToSet=MyDate.getFormattedDate(mydate);
		    		   
		    		   
		    		   holder.repeatMood.setText(dateToSet);
		    	   }
		    	   else
		    	   {
		    		   holder.repeatMood.setText("N/A");
		    	   }
		    	   
		       }catch(Exception ex)
		       {
		    	   
		       }
	    	   
	       }
	       else
	    	   
	       {
	    	   
	    	   if(mood_Model.awayStartTime!=null && mood_Model.awayStartTime.trim().length() > 0 && (!mood_Model.awayStartTime.equals("N/A") && !mood_Model.awayStartTime.equals("fefe") && !mood_Model.awayEndTime.equals("0000")))
	    	   {
	    		   
	    		   holder.time.setText(mood_Model.awayStartTime);
	    		   holder.repeatMood.setText(getString(R.string.RepeatON));
	    		   holder.moodItemBGLY.setBackgroundResource(R.drawable.moods_status_active_bg);
	    		   
	    	   }
	    	   else
	    	   {
	    		   holder.time.setText("N/A");
	    		   holder.repeatMood.setText("N/A");
	    		   holder.moodItemBGLY.setBackgroundResource(R.drawable.moods_status_bg);
	    	   }
	    	   
	    	   
	       }
	       
	       
	       
	       
	       
	      
	       
	       
	       holder.moodItemBGLY.setOnDragListener(new MyDragListener(mood_Model));

	        
	    }

	    @Override
	    public int getItemCount(){
	        // Count the items
	        return mMoodsModelList.size();
	    }
	    
	    
	    
	    public void upDateGUI(Mood_Model moodModel)
	    {
	    	
	    	 // mMoodsModelList = fetchMoodsOfCurrentSwitchFromDb(mSwitchModel.switch_id);
				
				if(mMoodsModelList==null)
				{
					mMoodsModelList=new ArrayList<>();
					
				}
				
				/*
				Collections.sort(mMoodsModelList, new Comparator<Mood_Model>(){
				    public int compare(Mood_Model s1, Mood_Model s2) {
				    	
				        return  Integer.valueOf(s1.moodIdentifer).compareTo(s2.moodIdentifer);
				    }
				});
				
				
				updateMoodsOnSwitchResponse(mMoodsModelList,currentCommand);*/
				
				
				if(!moodModel.modelStatus.equals(AppKeys.KEY_IS_DELETED))
				{
					for(int i=0; i<mMoodsModelList.size() ;i++) 
					{
						
						if(  moodModel.moodIdentifer == mMoodsModelList.get(i).moodIdentifer )
						{
							
							
							mMoodsModelList.get(i).picture=moodModel.picture;
							
							mMoodsModelList.get(i).title=moodModel.title;
							
							mMoodsModelList.get(i).pictureURL=moodModel.pictureURL;
							
							break;
							
						}
						else if(moodModel.moodIdentifer < mMoodsModelList.get(i).moodIdentifer)
						{
							mMoodsModelList.add(i, moodModel);
							
							break;
						}
						
					}
					
				}
				else
				{
					for(int i=0; i<mMoodsModelList.size() ;i++) 
					{
						
						if(  moodModel.moodIdentifer == mMoodsModelList.get(i).moodIdentifer )
						{
							mMoodsModelList.remove(i);
							
							break;
						}
						
					}
				
				}
				
				
				
				notifyDataSetChanged();
	    	
	    }

		
	}
	
	
	
	
	//--------------------------------------------------------------------------------------------------------------------
	
	
	
	
	public void updateMoodsOnSwitchResponse(List<Mood_Model>  mMoodsList,String switchResponseCMD)
	{
		
		
		
		
		
		Mood_Model   moodModel;
		
		int currentIndex=0;
		
		int moodGap=14;
		
		
		
		
		
		
		for(int i=0; i<mMoodsList.size() ;i++) 
		{
			
			moodModel=mMoodsList.get(i);
			
			
			
			
			currentIndex=moodModel.moodIdentifer;
			
			
			if(currentIndex!=14)
			{
			
			
							int startIndex=currentIndex*moodGap;
							
							int endIndex=startIndex + moodGap;
							
							
							String tempMoodValues=switchResponseCMD.substring( startIndex , endIndex);
							
							if(!tempMoodValues.equals("00000000000000"))
							{
							
							       String hexDays=tempMoodValues.substring(0,2);
									
									String hexMonths=tempMoodValues.substring(2,4);
									
									String hexYears=tempMoodValues.substring(4,8);
									
									String hexHours=tempMoodValues.substring(8,10);
									
									String hexMinutes=tempMoodValues.substring(10,12);
									
									String hexOnOffState=tempMoodValues.substring(12,14);
									
									if(tempMoodValues.substring(0,8).equals(AppKeys.KEY_CASE_REPEAT))
									{
										moodModel.isRepeatOn="1";
										moodModel.time=getHextoSQLDate(hexDays,hexMonths,hexYears,hexMinutes,hexHours);
										
									}
									else
									{
										moodModel.isRepeatOn="0";
										
										if(isValidDateAndTime(hexDays,hexMonths,hexYears,hexMinutes,hexHours))
										{
										     moodModel.time=getHextoSQLDate(hexDays,hexMonths,hexYears,hexMinutes,hexHours);
										}
										else
										{
											moodModel.isRepeatOn="N/A";
											moodModel.time=null;
										}
										
										
									}
									
								
							}
							else
							{
								
								
								moodModel.isRepeatOn="N/A";
								moodModel.time=null;
							}
			
			
			}
			else
			{
							moodGap=14;
							int startIndex=currentIndex*moodGap;
							int endIndex=startIndex + 28;
							
							
							String awayMood=switchResponseCMD.substring( startIndex , endIndex);
							
							
							
							int onDuration=DateAndTimePickerForAwayMoodActivity.getAwayOnDuration(awayMood);
							
							int offDuration=DateAndTimePickerForAwayMoodActivity.getAwayOnDuration(awayMood);
							
							MyDate startTime=DateAndTimePickerForAwayMoodActivity.getAwayStartTime(awayMood);
							
							MyDate endTime =DateAndTimePickerForAwayMoodActivity.getAwayEndTime(awayMood);
							
							String timetoCheck=DateAndTimePickerForAwayMoodActivity.getAwayStartTimeHEX(awayMood);
							
							if(!timetoCheck.endsWith("0000") && !timetoCheck.endsWith("fefe"))
							{
								moodModel.awayEndTime=MyDate.getFormattedTime(endTime);//DateAndTimePickerForAwayMoodActivity.getAwayEndTimeHEX(awayMood);
								moodModel.awayStartTime=MyDate.getFormattedTime(startTime);//DateAndTimePickerForAwayMoodActivity.getAwayStartTimeHEX(awayMood);
								moodModel.awayOnDuration= onDuration + "";
								moodModel.awayOffDuration=offDuration + "";
								
							}
							else
							{
								moodModel.awayEndTime="N/A";
								moodModel.awayStartTime="N/A";
								moodModel.awayOnDuration= "N/A";
								moodModel.awayOffDuration="N/A";
								
							}
				
				
			}
			
			
			//moodModel.save();
			
			
		}
		
		
		
		
	}
	
	
	
	
	public static String getHextoSQLDate(String day,String month ,String year,String min,String hour)
	{
		
		
		       int days=Integer.parseInt(day,16);
		       int months=Integer.parseInt(month,16);
		       
		       String firstPartOfYear=year.substring(0,2);
		       
		       String secondPartOfYear=year.substring(2,4);
		       
		       int year1=Integer.parseInt(firstPartOfYear,16);
		       
		       int year2=Integer.parseInt(secondPartOfYear,16);
		       
		       int mins=Integer.parseInt(min,16);
		       int hours=Integer.parseInt(hour,16);
		       
		       
		       
		       
		       MyDate date=HomeActivity.getAndroidDateFromValues(Integer.parseInt(firstPartOfYear+""+secondPartOfYear,16), months, days, hours, mins);
		        
		        
		      
		
			return HomeActivity.getAndroidStringDateFromDate(date);
		
	}
	
	
	public boolean  isValidDateAndTime(String day,String month ,String year,String min,String hour)
	{
		
		
		       int days=Integer.parseInt(day,16);
		       int months=Integer.parseInt(month,16);
		       
		       String firstPartOfYear=year.substring(0,2);
		       
		       String secondPartOfYear=year.substring(2,4);
		       
		       //int year1=Integer.parseInt(firstPartOfYear,16);
		       
		       int year2=Integer.parseInt(firstPartOfYear+secondPartOfYear,16);
		       
		       
		       int years=year2;
		       int mins=Integer.parseInt(min,16);
		       int hours=Integer.parseInt(hour,16);
		     
		       
		        
		       if(days>31)
		       {
		    	   return false;
		       }
		       
		       if(months>12)
		       {
		    	   return false;
		       }
		
		       if(years>2090)
		       {
		    	   return false;
		       }
				
		       
		
			return true;
		
	}
	
	
	

	class HViewHolder {
	    ImageView icon;
	    TextView  moodNmae;
	    TextView  repeatMood;
	    TextView time;
	    
	    LinearLayout moodItemBGLY;
	}
	
	
	
	
	private final class ScaleDragViewTouchLintner implements OnTouchListener {
		
		
		String swdata="";
		
		boolean MoveStared=false;
		
		
		public ScaleDragViewTouchLintner(String data)
		{
			this.swdata=data;
		}
		
		private long startClickTime=0;
		private long MAX_CLICK_DURATION=300;
        public boolean onTouch(View view, MotionEvent motionEvent) {
        	
        	
        	
        	
        	switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                startClickTime = Calendar.getInstance().getTimeInMillis();
                break;
            }
            
            case MotionEvent.ACTION_MOVE: {
                
            	long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if(clickDuration > MAX_CLICK_DURATION) {
            	
                    ClipData data = ClipData.newPlainText("", "");
                    MyDragShadowBuilder shadowBuilder = new MyDragShadowBuilder(
                            view,true);
                    
                    currentSwitchData=swdata;
                    
                    view.startDrag(data, shadowBuilder, view, 0);
                }
                	
              
            	
                break;
            }
            
            
            case MotionEvent.ACTION_UP: {
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if(clickDuration < MAX_CLICK_DURATION) {
                    
                	
                	List<Mood_Model> resuklt=fetchMoodsOf__CurrentSwitchFromDb(mSwitchModel.switch_id,swdata);
                	
                	if( resuklt!=null && resuklt.size() > 0 )
                	{
                	
	                	Intent intent = new Intent(Moods_RoomAndSwitchesTCPActivity.this, Moods_Display_Activity.class);
	        			
	        			String mRoomId = getIntent().getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
	        			String mRoomTitle = getIntent().getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
	        			
	        			intent.putExtra("CMD", currentCommand);
	        			
	        			intent.putExtra(AppKeys.KEY_BUTTON_TYPE, swdata);
	        			intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, mRoomId);
	        			intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, mRoomTitle);
	        			intent.putExtra(AppKeys.KEY_SWITCH_ID_TAG, mSwitchModel.switch_id);
	        			Parcelable mListState=null;
						if (mLayoutManager != null) 
						{
							//lastHorizontalScrollPoisition = ((LinearLayoutManager) mLayoutManager).findLastCompletelyVisibleItemPosition();
							 mListState = mLayoutManager.onSaveInstanceState();
						}
	        			intent.putExtra("scroll", mListState);
	        			
	        			startActivity(intent);
	        			
	        			KisafaApplication.perFormActivityNextTransition(mContext);
	        			
	        			finish();
        			
                	}
                	else
                	{
                		
                		final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(Moods_RoomAndSwitchesTCPActivity.this,mContext.getString(R.string.buttonNotAssociatedtoAnyModd));
        				
        				cusDial.setListner(new CustomDialogueClickListner() {
        					
        					@Override
        					public void onCustomDialogueClick() {
        						
        						
        						cusDial.dismiss();
        						
        						
        						
        						
        					}
        				});
        				
        				
        				cusDial.show();
                	}
                	
                }
                
            }
        }
        	
            	
        	return true;
            	
            
        }
}
	
	
	
	private final class NoScaleDragViewTouchLintner implements OnTouchListener {
		
		
        String swdata="01";
        
        boolean MoveStared=false;
		
		public NoScaleDragViewTouchLintner(String data)
		{
			this.swdata=data;
		}
		
		private long startClickTime=0;
		private long MAX_CLICK_DURATION=300;
        public boolean onTouch(View view, MotionEvent motionEvent) 
        {
        	

        	switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                startClickTime = Calendar.getInstance().getTimeInMillis();
                break;
            }
            
            case MotionEvent.ACTION_MOVE: {
                
            	
            	
            	long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if(clickDuration > MAX_CLICK_DURATION) {
            	
                    ClipData data = ClipData.newPlainText("", "");
                    MyDragShadowBuilder shadowBuilder = new MyDragShadowBuilder(
                            view,false);
                    
                    currentSwitchData=swdata;
                    
                    view.startDrag(data, shadowBuilder, view, 0);
                }
                	
              
            	
                break;
            }
            
            
            case MotionEvent.ACTION_UP: {
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if(clickDuration < MAX_CLICK_DURATION) {
                    
                	
                	
                	List<Mood_Model> resuklt=fetchMoodsOf__CurrentSwitchFromDb(mSwitchModel.switch_id,swdata);
                	
                	if( resuklt!=null && resuklt.size() > 0 )
                	{
                	
	                	Intent intent = new Intent(Moods_RoomAndSwitchesTCPActivity.this, Moods_Display_Activity.class);
	        			
	        			String mRoomId = getIntent().getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
	        			String mRoomTitle = getIntent().getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
	        			
	        			intent.putExtra("CMD", currentCommand);
	        			
	        			intent.putExtra(AppKeys.KEY_BUTTON_TYPE, swdata);
	        			intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, mRoomId);
	        			intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, mRoomTitle);
	        			intent.putExtra(AppKeys.KEY_SWITCH_ID_TAG, mSwitchModel.switch_id);
	        			Parcelable mListState=null;
						if (mLayoutManager != null) 
						{
							//lastHorizontalScrollPoisition = ((LinearLayoutManager) mLayoutManager).findLastCompletelyVisibleItemPosition();
							 mListState = mLayoutManager.onSaveInstanceState();
						}
	        			intent.putExtra("scroll", mListState);
	        			startActivity(intent);
	        			
	        			KisafaApplication.perFormActivityNextTransition(mContext);
	        			
	        			finish();
        			
                	}
                	else
                	{
                		
                		final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(Moods_RoomAndSwitchesTCPActivity.this,mContext.getString(R.string.buttonNotAssociatedtoAnyModd));
        				
        				cusDial.setListner(new CustomDialogueClickListner() {
        					
        					@Override
        					public void onCustomDialogueClick() {
        						
        						
        						cusDial.dismiss();
        						
        						
        						
        						
        					}
        				});
        				
        				
        				cusDial.show();
                	}
                	
                	
                }
                else
                {
                	MoveStared=true;
                }
                
            }
        }
        	
            	
        	return true;
        
    }
}
	
	private static class MyDragShadowBuilder extends View.DragShadowBuilder {

	    private Point mScaleFactor;
	    
	    boolean needtoScaled=false;
	    
	    
	    Paint myPaint=null;
	        // Defines the constructor for myDragShadowBuilder
	        public MyDragShadowBuilder(View v,boolean needtoScaled) {

	            // Stores the View parameter passed to myDragShadowBuilder.
	            super(v);
	            
	            this.needtoScaled=needtoScaled;
	            
	            if(!needtoScaled)
	            {
	            	myPaint = new Paint();
	                myPaint.setColor(Color.parseColor("#FDB71A"));
	            }

	        }

	        // Defines a callback that sends the drag shadow dimensions and touch point back to the
	        // system.
	        @Override
	        public void onProvideShadowMetrics (Point size, Point touch) {
	            // Defines local variables
	            double width;
	            double height;

	            if(needtoScaled)
	            {
	            // Sets the width of the shadow to half the width of the original View
	            width = getView().getWidth() / (1.2);

	            // Sets the height of the shadow to half the height of the original View
	            height = getView().getHeight() / (1.2);
	            }
	            else
	            {
	            	width = getView().getWidth();

		            // Sets the height of the shadow to half the height of the original View
		            height = getView().getHeight();
	            }

	            // Sets the size parameter's width and height values. These get back to the system
	            // through the size parameter.
	            size.set((int)width, (int)height);
	            // Sets size parameter to member that will be used for scaling shadow image.
	            mScaleFactor = size;

	            // Sets the touch point's position to be in the middle of the drag shadow
	            touch.set((int)(width / 2), (int)(height / 2));
	        }

	        @Override
	        public void onDrawShadow(Canvas canvas) {

	            
	        	if(myPaint!=null)
	        	{
	        		canvas.drawPaint(myPaint);
	        		
	        	}
	        	
	        	
	            canvas.scale(mScaleFactor.x/(float)getView().getWidth(), mScaleFactor.y/(float)getView().getHeight());
	            getView().draw(canvas);
	        }

	    }
	
	
	
	
	
	class MyDragListener implements OnDragListener {
		
		
		Mood_Model mModel;
		
		public MyDragListener(Mood_Model mModel)
		{
			this.mModel=mModel;
		}
		
		
       /* Drawable enterShape = getResources().getDrawable(
                        R.drawable.buld_gray);
        Drawable normalShape = getResources().getDrawable(R.drawable.buld_gray);*/

        @Override
        public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                // do nothing
                        break;
                case DragEvent.ACTION_DRAG_ENTERED:
                        //v.setBackgroundDrawable(enterShape);
                        break;
                case DragEvent.ACTION_DRAG_EXITED:
                        //v.setBackgroundDrawable(normalShape);
                        break;
                case DragEvent.ACTION_DROP:
                       
                        View view = (View) event.getLocalState();
                       
                        
                        
                        if(mModel.time==null && mModel.moodIdentifer!=14)
                        {
                        	String moodtitle= mModel.title;
                        	if( mModel.moodIdentifer < 6)
                        	{
                        		moodtitle=getMoodTitle(mModel.moodIdentifer);
                        	}
                        	
                        	final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(Moods_RoomAndSwitchesTCPActivity.this,moodtitle+" " + getString(R.string.TimeisNotSet));
    						
    						cusDial.setListner(new CustomDialogueClickListner() {
    							
    							@Override
    							public void onCustomDialogueClick() {
    								
    								
    								cusDial.dismiss();
    								
    								
    								
    								
    							}
    						});
    						
    						
    						cusDial.show();
                        	
                        }
                        else if(mModel.moodIdentifer==14)
                        {
                        	
                        	if(mModel.awayStartTime!=null && !mModel.awayStartTime.equals("N/A") && !mModel.awayStartTime.equals("fefe"))
                        	{
                        		
                        		//if(!currentSwitchData.equals(Buttons.BUTTON_THREE.value))
                        		{
                        		  //buttonOnOff(mModel);
                        			
                        			
                        			WaitingStaticProgress.showProgressDialog("", Moods_RoomAndSwitchesTCPActivity.this);
                					
                					startTCPClient(mModel,true);
                        			
                        			
                        		}
                        		/*else
                        		{
                        			
                        			final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(Moods_RoomAndSwitchesTCPActivity.this,"Unable to associate third button with AWAY mood!");
            						cusDial.setListner(new CustomDialogueClickListner() {
            							@Override
            							public void onCustomDialogueClick() {
            								cusDial.dismiss();
            																}
            						});
            						cusDial.show();
                        		}*/
                        	}
                        	else
                        	{
                        		
                        		/*if(currentSwitchData.equals(Buttons.BUTTON_THREE.value))
                        		{
                        		  return  true;
                        		}*/
                        		
                        		
                        		final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(Moods_RoomAndSwitchesTCPActivity.this,getString(R.string.Away) +" "+getString(R.string.TimeisNotSet));
        						
        						cusDial.setListner(new CustomDialogueClickListner() {
        							
        							@Override
        							public void onCustomDialogueClick() {
        								
        								
        								cusDial.dismiss();
        								
        								
        								
        								
        							}
        						});
        						
        						
        						cusDial.show();
                        		
                        	}
                        	
                        	
                        }
                        else
                        {
                        	
                        
                        	buttonOnOff(mModel);
                        	
                        	
                        }
                        
                        
                        break;
                case DragEvent.ACTION_DRAG_ENDED:
                        //v.setBackgroundDrawable(normalShape);
                        default:
                        break;
                }
                return true;
        }
}
	
	
	
	
	private void buttonOnOff(final Mood_Model mood) {

		 LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		 View popupView = layoutInflater.inflate(R.layout.button_on_off_popup, null);
		
		TextView tv_cancel=(TextView) popupView.findViewById(R.id.cancelButton);
		
		TextView onTV=(TextView) popupView.findViewById(R.id.tv_buttonON);
		
		TextView offTV=(TextView) popupView.findViewById(R.id.tv_buttonOFF);
		
		
		TextView headingTV=(TextView) popupView.findViewById(R.id.headingText);
		
		
		String moodtitle= mood.title;
    	if( mood.moodIdentifer < 6 ||  mood.moodIdentifer>13)
    	{
    		moodtitle=getMoodTitle(mood.moodIdentifer);
    	}
    	
		
		headingTV.setText(getString(R.string.PleaseselectButtonstateformood)+" "+ moodtitle);

		final PopupWindow popupWindow=new PopupWindow(popupView,
		         LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,        
		 true);

		 popupWindow.setTouchable(true);
		 popupWindow.setFocusable(true);
		 
		 popupWindow.setAnimationStyle(R.style.BottonUpAnimation);
		 
		

		 popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
		 
		 
		
		 
		 tv_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					
				}
			});
		 
		 
		 
				
		 
		 
		 onTV.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					popupWindow.dismiss();
					
					
					//abc
					
				
					WaitingStaticProgress.showProgressDialog("", Moods_RoomAndSwitchesTCPActivity.this);
					
					startTCPClient(mood,true);
					
				
					
					
				}
			});
		 
		 
		 offTV.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					popupWindow.dismiss();
					
					
					WaitingStaticProgress.showProgressDialog("", Moods_RoomAndSwitchesTCPActivity.this);
					
					startTCPClient(mood,false);
					
					
				}
			});
		 
		 
		 popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			
			@Override
			public void onDismiss() {
				
				
				
				
			}
		});
		
		
	
	

}
	
	
	
	private void callPopupSetting(final  Mood_Model mMood ) {

		TextView tv_cancel=null;
		
		TextView tv_Done=null;
		
		TextView tv_Camera=null;
		
		TextView tv_Photoes=null;
		
		
		
		 LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		  popupView = layoutInflater.inflate(R.layout.add_mood_popup, null);
		 
		 
		 //tv_Cancel
		 tv_cancel=(TextView)popupView.findViewById(R.id.tv_Cancel);
		 tv_Done=(TextView)popupView.findViewById(R.id.tv_Done);
		 et_MoodName=(CustomEditText)popupView.findViewById(R.id.moodName);
		 tv_Camera=(TextView)popupView.findViewById(R.id.tv_Camera);
		 tv_Photoes=(TextView)popupView.findViewById(R.id.tv_Photoes);
		 iv_img_blob=(ImageView)popupView.findViewById(R.id.iv_img_blob);
		 
		  
		 
		    
		if(mMood!=null)
		{
			
			
			et_MoodName.setText(mMood.title);
			
			if(mMood.pictureURL!=null && mMood.pictureURL.length() > 0)
			{
			
				 ImageLoader imageLoader;
				 imageLoader =ImageLoader.getInstance();
				 
				 imageLoader.displayImage(mMood.pictureURL, iv_img_blob,KisafaApplication.getImageDisplayOption());
				
			}
			else if(mMood.picture!=null)
			{
			try
			{
			
			iv_img_blob.setImageBitmap(ViewRoomsGridViewAdapter.bytesToBitmap(mMood.picture));
			}catch(Exception ex){}
			}
		} 
		
			
			
		 final PopupWindow popupWindow=new PopupWindow(popupView,
		         LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,        
		 true);

		 popupWindow.setTouchable(true);
		 popupWindow.setFocusable(true);
		 
		 popupWindow.setAnimationStyle(R.style.BottonUpAnimation);
		 
		 popupWindow.setSoftInputMode(LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

		 popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
		 
		 
		 tv_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				popupWindow.dismiss();
				
			}
		});
		 
		 tv_Done.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					popupWindow.dismiss();
					
					
					
					
					if(et_MoodName.getText().toString().trim().length() ==0)
					{
						final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(Moods_RoomAndSwitchesTCPActivity.this,getString(R.string.Moodnamerequired));
						
						cusDial.setListner(new CustomDialogueClickListner() {
							
							@Override
							public void onCustomDialogueClick() {
								
								
								cusDial.dismiss();
								
							}
						});
						
						
						cusDial.show();
						
						return;
					}
					
					
					if(mMood==null)
					{
					
					
					boolean isSlotEmpty=false;
					
					int emptyMoodIndex=6;
					
					Mood_Model currentMood=null;
					
					List<Mood_Model> mmMoodsModelList=fetchAllMoodsOfCurrentSwitchFromDb(mSwitchModel.switch_id);
					
					if(mmMoodsModelList==null)
						return;
					
					
					for(int x=6; x < mmMoodsModelList.size() - 1 ; x++)
					{
						
						if(mmMoodsModelList.get(x).modelStatus.equals(AppKeys.KEY_IS_DELETED))
						{
							
							isSlotEmpty=true;
							
							emptyMoodIndex=x;
							
							currentMood=mmMoodsModelList.get(x);
							
							break;
						}
						
					}
					
					if(!isSlotEmpty)
					{
						
						final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(Moods_RoomAndSwitchesTCPActivity.this,getString(R.string.morethan14moods));
						
						cusDial.setListner(new CustomDialogueClickListner() {
							
							@Override
							public void onCustomDialogueClick() {
								
								
								cusDial.dismiss();
								
								
								
								
							}
						});
						
						
						cusDial.show();
						
						return;
					}
					
					
					
					
					
					if(!addMoodToDB(emptyMoodIndex,currentMood,mSwitchModel.switch_id))
					{
						final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(Moods_RoomAndSwitchesTCPActivity.this,getString(R.string.Moodnamenotavailable));
						
						cusDial.setListner(new CustomDialogueClickListner() {
							
							@Override
							public void onCustomDialogueClick() {
								
								
								cusDial.dismiss();
								
								
								
								
							}
						});
						
						
						cusDial.show();
					}
					else
					{
						
						
						if(colorsAdapter!=null)
						{
							colorsAdapter.upDateGUI(currentMood);
						}
						
						
					}
						
						
					}
					else
					{
						UpdateMoodToDB(mMood);
						
						if(colorsAdapter!=null)
						{
							colorsAdapter.upDateGUI(mMood);
						}
					}
					
					
					//=new  HAdapter(mContext);
					//hmoodsListview.setAdapter(hListAdapter);
					
					
				}
			});
		 
		 tv_Camera.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					//popupWindow.dismiss();
					
					
					isUserAddedMoodImage=false;
					
					Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
				        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
				    }
					
				}
			});
		 
		 tv_Photoes.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					//popupWindow.dismiss();
					
					
					  isUserAddedMoodImage=false;
					  Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
					  intent.setType("image/*");
					  startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
					
					
				}
			});
		 
		 
		 
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
	        Bundle extras = data.getExtras();
	        Bitmap imageBitmap = (Bitmap) extras.get("data");
	        
	        /*ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);   
	        byte[] photo = baos.toByteArray();*/
	        
	        
	        Bitmap resized = Bitmap.createScaledBitmap(imageBitmap, 150, 160, true);
	        iv_img_blob.setImageBitmap(resized);
	        
	        
	        isUserAddedMoodImage=true;
	        
	       // callPopupSetting(true);
	        
	    }
	    else if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == RESULT_OK) {
	    	
	    	 Uri selectedImage = data.getData();
	    	
	    	 
	         Bitmap bitmap = null;
			try {
				bitmap = getBitmapFromUri(selectedImage);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
			Bitmap resized = Bitmap.createScaledBitmap(bitmap, 150, 160, true);
			
	        iv_img_blob.setImageBitmap(resized);
	        
	        
	        isUserAddedMoodImage=true;
	        
	        //callPopupSetting(true);
	        
	    }
	    
	    
	    
	   
	    
	}
	
	private Bitmap getBitmap(ImageView img)
	{
		Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
		
		return bitmap;
	}
	
	private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
             getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
	}
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	
	
	private void activateMood(final Mood_Model mood) {

		 LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		 View popupView = layoutInflater.inflate(R.layout.activate_mood_popup, null);
		
		TextView tv_cancel=(TextView) popupView.findViewById(R.id.cancelButton);
		
		TextView tv_updateTimeSettings=(TextView) popupView.findViewById(R.id.tv_updateTimeSettings);
		
		
		TextView moodNameTV=(TextView) popupView.findViewById(R.id.moodNameTV);
		
		
		String moodtitle= mood.title;
    	if( mood.moodIdentifer < 6 ||  mood.moodIdentifer>13)
    	{
    		moodtitle=getMoodTitle(mood.moodIdentifer);
    	}
    	
		
		moodNameTV.setText(getString(R.string.Optionsfor)+" " +moodtitle);
		

		final PopupWindow popupWindow=new PopupWindow(popupView,
		         LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,        
		 true);

		 popupWindow.setTouchable(true);
		 popupWindow.setFocusable(true);
		 
		 popupWindow.setAnimationStyle(R.style.BottonUpAnimation);
		 
		

		 popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
		 
		 
		
		 
		 tv_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					
				}
			});
		 
		 
		 
				
		 
		 
			 tv_updateTimeSettings.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					popupWindow.dismiss();
					
					Intent intent=null;
					
					
					if(mood.moodIdentifer == 14)
					{
					
					    intent = new Intent(Moods_RoomAndSwitchesTCPActivity.this, DateAndTimePickerForAwayMoodActivity.class);
					}
					else
					{
						intent = new Intent(Moods_RoomAndSwitchesTCPActivity.this, DateAndTimePickerForMoodsActivity.class);
					}
					
					String mRoomId = getIntent().getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
					String mRoomTitle = getIntent().getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
					
					
					intent.putExtra("CMD", currentCommand);
					intent.putExtra(AppKeys.KEY_MOOD_ID_TAG, mood.moodId);
					intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, mRoomId);
					intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, mRoomTitle);
					intent.putExtra(AppKeys.KEY_SWITCH_ID_TAG, mSwitchModel.switch_id);
					
					Parcelable mListState=null;
					if (mLayoutManager != null) 
					{
						//lastHorizontalScrollPoisition = ((LinearLayoutManager) mLayoutManager).findLastCompletelyVisibleItemPosition();
						 mListState = mLayoutManager.onSaveInstanceState();
					}
        			intent.putExtra("scroll", mListState);
					startActivity(intent);
					
					KisafaApplication.perFormActivityNextTransition(mContext);
					
					finish();
				
					
					
				}
			});
		 
		 
		 popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			
			@Override
			public void onDismiss() {
				
				
				
				
			}
		});
		
		
	
	

}
	
	
	private void OpenCustomMoodPopup(final Mood_Model mood) {

		 LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 
		 
		 View popupView=null;;
		 
		if(AppPreference.getValue(mContext,AppKeys.KEY_IS_SUB_USER)!=null && AppPreference.getValue(mContext,AppKeys.KEY_IS_SUB_USER).equals("1"))
			{
			 
			  popupView = layoutInflater.inflate(R.layout.up_date_activated_mood_popup_sub_user, null);
			
			}
		 else
		 {
			 popupView = layoutInflater.inflate(R.layout.up_date_activated_mood_popup, null);
		 }

		
		
		TextView tv_cancel=(TextView) popupView.findViewById(R.id.cancelButton);
		
		TextView tv_updateTimeSettings=(TextView) popupView.findViewById(R.id.tv_updateTimeSettings);
		
		
		TextView moodNameTV=(TextView) popupView.findViewById(R.id.moodNameTV);
		TextView tv_deleteMood =(TextView) popupView.findViewById(R.id.tv_deleteMood);
		TextView tv_editTitleORPic =(TextView) popupView.findViewById(R.id.tv_editTitleORPic);
		
		moodNameTV.setText(getString(R.string.Optionsfor) +" "+ mood.title);
		

		final PopupWindow popupWindow=new PopupWindow(popupView,
		         LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,        
		 true);

		 popupWindow.setTouchable(true);
		 popupWindow.setFocusable(true);
		 
		 popupWindow.setAnimationStyle(R.style.BottonUpAnimation);
		 
		

		 popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
		 
		 
		 
		   
		 
		 
		
		 
		 tv_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					
				}
			});
		 
		 
		 tv_editTitleORPic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				popupWindow.dismiss();
				
				
				callPopupSetting(mood);
				
				
			}
		});
		 
		 
		 tv_deleteMood.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					popupWindow.dismiss();
					
					
					
					
					
					
					
					
					final CustomAlertDialogueTwoButtons cusTwo=new CustomAlertDialogueTwoButtons(mContext, getString(R.string.deleteMoodOption)+" "+mood.title);
					
					cusTwo.setListner( new CustomDialogueTwoButtonsClickListner() {
						
						@Override
						public void onCustomDialoguePositiveClick() {
							cusTwo.dismiss();
							
							runOnUiThread(new Runnable() {
								public void run() {
									
									
									WaitingStaticProgress.showProgressDialog("", mContext);
									
									
								}
							});
							
							DeleteSwitchMood  deleteSwitchMood=new DeleteSwitchMood(mContext);
							
							deleteSwitchMood.setActionCompleteListner(new DeleteMoodResultListner() {
								
								@Override
								public void onFailed() {
									
									
									runOnUiThread(new Runnable() {
										public void run() {
											
											
											WaitingStaticProgress.hideProgressDialog();
											
											
										}
									});
									
									
								}
								
								@Override
								public void onDeleteMood(final Mood_Model mMood) {
									
									
									mood.time=null;
									mood.picture=null;
									
									//mood.pictureURL="";
									
									mMood.modelStatus=AppKeys.KEY_IS_DELETED;
									
									mMood.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
									
									mMood.isRepeatOn="0";
									
									mMood.save();
									
									
									String tempCommand=currentCommand;
						    		int currentIndex=mMood.moodIdentifer;
						    		int moodGap=14;
						    		int startIndex=currentIndex*moodGap;
						    		int endIndex=startIndex + moodGap;
						    		currentCommand=tempCommand.substring(0,startIndex) + "00000000000000" +tempCommand.substring(endIndex ,tempCommand.length());
						    		
						    		
						    		runOnUiThread(new Runnable() {
										public void run() {
											
											
											
											
											
											if(colorsAdapter!=null)
											{
												colorsAdapter.upDateGUI(mMood);
											}
											
											WaitingStaticProgress.hideProgressDialog();
											
										}
									});
									
									
								}
							});
							
							
							deleteSwitchMood.deleteButtonMood(mSwitchModel,mood);
							
						}
						
						@Override
						public void onCustomDialogueNegativeClick() {
							cusTwo.dismiss();
							
							
							
						}
					});
						
						
					
					
					cusTwo.show();
					
					
					
					
					
					
					
					
					
				}
			});
				
		 
		 
			 tv_updateTimeSettings.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					popupWindow.dismiss();
					
					
					
					Intent intent = new Intent(Moods_RoomAndSwitchesTCPActivity.this, DateAndTimePickerForMoodsActivity.class);
					
					String mRoomId = getIntent().getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
					String mRoomTitle = getIntent().getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
					
					
					intent.putExtra("CMD", currentCommand);
					intent.putExtra(AppKeys.KEY_MOOD_ID_TAG, mood.moodId);
					intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, mRoomId);
					intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, mRoomTitle);
					intent.putExtra(AppKeys.KEY_SWITCH_ID_TAG, mSwitchModel.switch_id);
					
					intent.putExtra("scroll", mRecyclerView.getScrollX());
					
					startActivity(intent);
					
					KisafaApplication.perFormActivityNextTransition(mContext);
					
					finish();
				
					
					
				}
			});
		 
		 
		 popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			
			@Override
			public void onDismiss() {
				
				
				
				
			}
		});
		
		
	
	

}
	
	
	
	 public boolean isAnyButtonAssociatedWiththisMood( String cmd1,String cmd2,String cmd3,int moodIdentifer)
     {
    	 
    	 boolean isButtonOneActiveLocal=false;
    	 boolean isButtonTwoActiveLocal=false;
    	 boolean isButtonThreeActiveLocal=false;
    	 
    	 isButtonOneActiveLocal =   cmd1!=null && cmd1.trim().length() > 0  &&  DeleteSwitchMood.isButtonAssociatedWithThisMood(moodIdentifer,cmd1) ? true : false;
    	 
    	 isButtonTwoActiveLocal =   cmd2!=null && cmd2.trim().length() > 0  &&  DeleteSwitchMood.isButtonAssociatedWithThisMood(moodIdentifer,cmd2) ? true : false;
    	 
    	 isButtonThreeActiveLocal = cmd3!=null && cmd3.trim().length() > 0  &&  DeleteSwitchMood.isButtonAssociatedWithThisMood(moodIdentifer,cmd3) ? true : false;
    	 
    	 
    	
    	 return  isButtonOneActiveLocal || isButtonTwoActiveLocal|| isButtonThreeActiveLocal ? true:false;
    	
    	 
     }
	
	
	
	
	
	public boolean addMoodToDB(int index,Mood_Model mood,String switchId)
	{
		
		Mood_Model temp = null;
		
		 try
		   {
			 temp = new Select().from(Mood_Model.class).where("Mood_Model.title COLLATE NOCASE = ? AND Mood_Model.switchId = ?  ",et_MoodName.getText().toString(),switchId).executeSingle();
		   }catch(Exception ex)
		   {
			   ex.toString();
		   }
		   
		
		 
		 if(temp!=null && (!temp.modelStatus.equals(AppKeys.KEY_IS_DELETED)))
		 {
			 
			 return false;
			 
		 }
		 
		 
		try
		{
		
		if(isUserAddedMoodImage)
		{
			if(iv_img_blob!=null)
			{
			Bitmap tempBitmap=getBitmap(iv_img_blob);
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
			tempBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);   
	        byte[] photo = baos.toByteArray();
	         mood.picture=photo;
			}
			
		}
		else
		{
			if(iv_img_blob!=null)
			{
			Bitmap tempBitmap=getBitmap(iv_img_blob);
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
			tempBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);   
	        byte[] photo = baos.toByteArray();
	         mood.picture=photo;
			}
		}
		
		}catch(Exception ex)
		{
			ex.toString();
		}
		mood.title=et_MoodName.getText().toString();
		
		mood.modelStatus=AppKeys.KEY_IS_UPDATED;
		mood.isPictureSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
		mood.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
		//mood.isPictureSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
		
		mood.save();
		
		
		return true;
		
		
	}
	
	
	public boolean UpdateMoodToDB(Mood_Model mood)
	{
		
		Mood_Model temp = mood;
		
		 
		 
		 if(temp!=null && !temp.modelStatus.equals(AppKeys.KEY_IS_DELETED))
		 {
			 
			
			 temp.title=et_MoodName.getText().toString();
		
		
		if(isUserAddedMoodImage)
		{
			if(iv_img_blob!=null)
			{
			Bitmap tempBitmap=getBitmap(iv_img_blob);
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
			tempBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);   
	        byte[] photo = baos.toByteArray();
	         mood.picture=photo;
	         //mood.pictureURL="";
			}
		}
		
		mood.title=et_MoodName.getText().toString();
		
		mood.modelStatus=AppKeys.KEY_IS_UPDATED;
		mood.isPictureSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
		mood.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
		
		mood.save();
		
		 }
		 else
		 {
			 return false;
		 }
		
		
		return true;
		
		
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	
	
	public  String getMoodTitle(int identifer)
	{
		
		
		 
				 if(identifer>=1)
					{
						
						if(identifer==1)
						{
							return getResources().getString(R.string.Travel);
						}
						else if(identifer==2)
						{
							return getResources().getString(R.string.Sleep);
						}
						else if(identifer==3)
						{
							return getResources().getString(R.string.Wakeup);
						}
						else if(identifer==4)
						{
							return getResources().getString(R.string.Guest);
						}
						else if(identifer==5)
						{
							return getResources().getString(R.string.Living);
						}
						else if(identifer==6)
						{
							return getResources().getString(R.string.Safety);
						}
						else if(identifer==14)
						{
							return getResources().getString(R.string.Away);
						}
						
					}
				
			return "";
	}

	//----------------------------------------------------------------------------
	
	private List<Mood_Model> fetchMoodsOf__CurrentSwitchFromDb(String switchId,String buttonType)
	{
		
		List<Mood_Model> moodModelList=null;
		try
		{
			//moodModelList = new Select().from(Mood_Model.class).where("Mood_Model.modelStatus != ? AND Mood_Model.switchId = ? AND Mood_Model.time != ?   ",AppKeys.KEY_IS_DELETED ,switchId,"").execute();
			moodModelList = new Select().from(Mood_Model.class).where("Mood_Model.modelStatus != ? AND Mood_Model.switchId = ? ",AppKeys.KEY_IS_DELETED ,switchId).execute();
		}catch(Exception ex)
		{
			ex.toString();
			
		}
		
		
		
        String currentButtonCommand="";
  		
  		if(Buttons.BUTTON_ONE.getValue().equals(buttonType))
  		{
  			currentButtonCommand=AppPreference.getValue(mContext, AppKeys.KEY_BUTTON_ONE_CMD);
  		}
  		else if(Buttons.BUTTON_TWO.getValue().equals(buttonType))
  		{
  			currentButtonCommand=AppPreference.getValue(mContext, AppKeys.KEY_BUTTON_TWO_CMD);
  		}
  		else if(Buttons.BUTTON_THREE.getValue().equals(buttonType))
  		{
  			currentButtonCommand=AppPreference.getValue(mContext, AppKeys.KEY_BUTTON_THREE_CMD);
  		}
		
  		if(moodModelList!=null)
  		{
		
	  		for(int i=0; i <moodModelList.size() ;i++) 
	  		{
	  			Mood_Model cMood=moodModelList.get(i);
	  			
	  			if(cMood.moodIdentifer != 14)
	  			{
		  			String currentMoodData=DateAndTimePickerForMoodsActivity.getButtonCurrentMood(cMood.moodIdentifer, currentButtonCommand);
		  			
		  			if(currentMoodData.substring(12, 14).equals("00"))
		  			{
		  				
		  				moodModelList.remove(i);
		  				i=i-1;
		  			}
		  			else
		  			{
		  				
		  				//moodsButtobStatus.put(cMood.moodIdentifer, (  currentMoodData.substring(12, 14).equals("01") ? context.getString(R.string.ON) : context.getString(R.string.OFF)  ));
		  			}
	  			}
	  			else
	  			{
	  				String currentMoodData=DateAndTimePickerForAwayMoodActivity.getButtonAwayMood(cMood.moodIdentifer, currentButtonCommand);
	  				
	  				String timetoCheck=DateAndTimePickerForAwayMoodActivity.getAwayStartTimeHEX(currentMoodData);
	  				
	  				if(timetoCheck.endsWith("0000") || timetoCheck.endsWith("fefe"))
		  			{
		  				
		  				moodModelList.remove(i);
		  				i=i-1;
		  			}
		  			else
		  			{
		  				
		  			}
	  				
	  			}
	  			
	  			
	  		}
  		}
		
		
		return moodModelList;
	}
	
}
