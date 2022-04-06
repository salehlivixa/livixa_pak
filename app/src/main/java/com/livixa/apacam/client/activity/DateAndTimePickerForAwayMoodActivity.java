package com.livixa.apacam.client.activity;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.activeandroid.query.Select;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity.SwitchTypes;
import com.livixa.apacam.client.activity.MoodsCommandManager.RequestTypes;
import com.livixa.apacam.client.activity.Moods_RoomAndSwitchesTCPActivity.Buttons;
import com.livixa.apacam.client.activity.Moods_TCPClient_New.ServerMessageResopnse;
import com.livixa.apacam.client.activity.MoodsRoomWithSwitchesActivity.connectTask;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue.CustomDialogueClickListner;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.R;
import object.p2pipcam.utils.MyDate;

public class DateAndTimePickerForAwayMoodActivity extends Activity {
	
	
	TimePicker timePicker1;
	
	
	TimePicker timePicker2;
	
	NumberPicker numPicker1;
	
	
	NumberPicker numPicker2;
	
	
	LinearLayout dateContainer;
	
	
	LinearLayout dateTab;
	
	LinearLayout timeTab;
	
	
	boolean isDateTabSelected=true;
	
	boolean isDurationTabSelected=true;
	
	
	TextView startTimeTV;
	
	TextView endTimeTV;
	
	TextView onDurationTV;
	
	TextView offDurationTV;
	
	TextView dateDaysTV;
	
	View endTimeSelected;
	
	View startTimeSelected;
	
	View endDurationSelected;
	
	View startDurationSelected;
	
	private Calendar calendar;
	
	int year=2000;
	
	int month=1;
	int day=1;
	
	int hours=1;
	
	int minutes=1;
	
	
	Mood_Model mModdModel;
	
	CheckBox reapeatCB;


	private String currentCommand;
	
	private String commandtoModify="";
	
	
	   String command1="";
	   
	   String command2="";
	   
	   String command3="";
	   
	   
	  
	   boolean isWiriteResponseArrived=false;
	   
	   boolean isButtonOneActive=false;
	   boolean isButtonTwoActive=false;
	   boolean isButtonThreeActive=false;


	public final static String dateFormate = "yyyy/MM/dd HH:mm:ss";
	
	boolean isDateChangedEventHappened=false;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_and_time_picker_for_away_mood);
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
		mContext=this;
		
		
		  timePicker1=(TimePicker) findViewById(R.id.tp1);
          timePicker2=(TimePicker) findViewById(R.id.tp2);
          numPicker1=(NumberPicker) findViewById(R.id.numberPicker1);
          numPicker2=(NumberPicker) findViewById(R.id.numberPicker2);
          
          numPicker1.setMinValue(1);
          numPicker1.setMaxValue(255);
          
          numPicker2.setMinValue(1);
          numPicker2.setMaxValue(255);
          
          handleIntent();
          
          
          startTimeTV=(TextView) findViewById(R.id.startTimeTV);
         
         endTimeTV=(TextView) findViewById(R.id.endTimeTV);
         
         onDurationTV=(TextView) findViewById(R.id.onDurationTV);
         
         offDurationTV=(TextView) findViewById(R.id.offDurationTV);
         
         dateDaysTV =(TextView) findViewById(R.id.dateDaysTV);
		
		 dateTab=(LinearLayout) findViewById(R.id.dateTab);
		
		 timeTab=(LinearLayout) findViewById(R.id.timeTab);
		
		 startTimeSelected=findViewById(R.id.startTimeSelected);
		 
		 endTimeSelected=findViewById(R.id.endTimeSelected);
		 
		 
		 
		 startDurationSelected=findViewById(R.id.startDurationSelected);
		 
		 endDurationSelected=findViewById(R.id.endDurationSelected);
		 
		 reapeatCB=(CheckBox) findViewById(R.id.reapeatCB);
		 
		 
		 
		 calendar = Calendar.getInstance();
	      year = calendar.get(Calendar.YEAR);
	      
	      month = calendar.get(Calendar.MONTH);
	      day = calendar.get(Calendar.DAY_OF_MONTH);
	      
	       hours = calendar.get(Calendar.HOUR_OF_DAY);
	       minutes = calendar.get(Calendar.MINUTE);
	      
	       timePicker1.setIs24HourView(true);
	      
	       timePicker2.setIs24HourView(true);
	       
	      

			
			updateTimeinFeilds(mModdModel);
	      
	      
		
	}
	
	
	
	
	private void updateTimeinFeilds(Mood_Model moodModel)
	{
		
		if(moodModel.awayStartTime==null || moodModel.awayStartTime.equals("N/A") || moodModel.awayStartTime.equals("") )
		{
			
			MyDate myDate=new MyDate(year, month, day, timePicker1.getCurrentHour(), timePicker1.getCurrentMinute());
			
			startTimeTV.setText( getString(R.string.StartTime)+" "+ MyDate.getFormattedTime(myDate) );
			
			endTimeTV.setText(  getString(R.string.EndTime) +" "+ MyDate.getFormattedTime(myDate) );
			
			
			
			String awayMood=getButtonAwayMood(14,currentCommand);
			
			int onDuration=getAwayOnDuration(awayMood);
			
			if(onDuration > 0)
			
			numPicker1.setValue(onDuration);
			
			int offDuration=getAwayOffDuration(awayMood);
			if(offDuration > 0)
			numPicker2.setValue(offDuration);
			
			if(offDuration==0)
			{
				offDuration=1;
			}
			
			if(onDuration==0)
			{
				onDuration=1;
			}
			
			String onDurationString =getString(R.string.OnDuration);
			
			onDurationString+= " "+onDuration+getString(R.string.Mins);
			
			onDurationTV.setText(onDurationString);
			
			String offDurationString = getString(R.string.OffDuration);
			
			offDurationString+= " "+offDuration+getString(R.string.Mins);
			
			offDurationTV.setText(offDurationString);
			
			
		}
		else
		{
			
			
			String awayMood=getButtonAwayMood(14,currentCommand);
			
			int onDuration=getAwayOnDuration(awayMood);
			
			numPicker1.setValue(onDuration);
			
			int offDuration=getAwayOffDuration(awayMood);
			
			numPicker2.setValue(offDuration);
			
			
			MyDate startTime=getAwayStartTime(awayMood);
			
			MyDate endTime =getAwayEndTime(awayMood);
			
			timePicker1.setCurrentHour(startTime.getHour());
			
			timePicker1.setCurrentMinute(startTime.getMinute());
			
			timePicker2.setCurrentHour(endTime.getHour());
			
			timePicker2.setCurrentMinute(endTime.getMinute());
			
			startTimeTV.setText(  getString(R.string.StartTime) +" "+ MyDate.getFormattedTime(startTime) );
			
			endTimeTV.setText(   getString(R.string.EndTime) +" "+ MyDate.getFormattedTime(endTime) );
			
			
			String onDurationString =getString(R.string.OnDuration);
			
			onDurationString+= " "+onDuration+getString(R.string.Mins);
			
			onDurationTV.setText(onDurationString);
			
			String offDurationString = getString(R.string.OffDuration);
			
			offDurationString+= " "+offDuration+getString(R.string.Mins);
			
			offDurationTV.setText(offDurationString);
			
			
		}
		
	}
	
	
	 public void handleIntent() {
   		try {

   			Intent intent = getIntent();

   			
   			currentCommand=intent.getStringExtra("CMD");
   			
   			String moodId = intent.getStringExtra(AppKeys.KEY_MOOD_ID_TAG);
   			
   			try
  		   {
   				mModdModel = new Select().from(Mood_Model.class).where("Mood_Model.moodId = ?", moodId ).executeSingle();
  		   }catch(Exception ex)
  		   {
  			   ex.toString();
  		   }
   			
   			String switchId = intent.getStringExtra(AppKeys.KEY_SWITCH_ID_TAG);
   			
   			
   			mSwitchModel=getSwitchFromDb(switchId);
   			
   			
   			
  		   
   		} catch (Exception ex) {
   			ex.toString();
   		}
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
	
public void onhomeButttonClick(View view) {
		
		
		
		finish();

		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		// overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		//overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);
		KisafaApplication.perFormActivityBackTransition(this);
		
		
		
		
	}

public void onbackClick(View view)
{
	
	onBackPressed();
	
}


@Override
public void onBackPressed() {
	
	
	
		super.onBackPressed();
		
		finish();

		Intent intent = new Intent(DateAndTimePickerForAwayMoodActivity.this, Moods_RoomAndSwitchesTCPActivity.class);
		
		String mRoomId = getIntent().getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
		String mRoomTitle = getIntent().getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
		
		String swId = getIntent().getStringExtra(AppKeys.KEY_SWITCH_ID_TAG);
		intent.putExtra("scroll", (Parcelable)getIntent().getParcelableExtra("scroll"));
		intent.putExtra("CMD", currentCommand);
		intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, mRoomId);
		intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, mRoomTitle);
		intent.putExtra(AppKeys.KEY_SWITCH_ID_TAG, swId);
		
		KisafaApplication.perFormActivityBackTransition(this);

		startActivity(intent);
		
		
		
	

}

          public DatePicker getDatePicker()
          {
        	  
        	  DatePicker tempdatePicker=new DatePicker(this, null, R.style.CustomDatePickerTheme);
        	  
        	  tempdatePicker.setSpinnersShown(true);
        	  
        	 
        	  return tempdatePicker;
          }
          
          
          
          public void onStartTimeTabClick(View view)
          {
        	  
        	  isDateTabSelected=true;
        	  
        	  
        	  timePicker1.setVisibility(View.VISIBLE);
        	  timePicker2.setVisibility(View.GONE);
        	  
        	  startTimeSelected.setVisibility(View.VISIBLE);
        	  endTimeSelected.setVisibility(View.INVISIBLE);
        	  
        	  
          }
          
          public void onEndTimeTabClick(View view)
          {
        	  
        	  isDateTabSelected=false;
        	  
        	  startTimeSelected.setVisibility(View.INVISIBLE);
        	  endTimeSelected.setVisibility(View.VISIBLE);
        	  
        	  timePicker1.setVisibility(View.GONE);
        	  timePicker2.setVisibility(View.VISIBLE);
        	 
        	  
          }
          
          public void onSetStartDurationTabClick(View view)
          {
        	  
        	  isDurationTabSelected=true;
        	  
        	  
        	  numPicker1.setVisibility(View.VISIBLE);
        	  numPicker2.setVisibility(View.GONE);
        	  
        	  startDurationSelected.setVisibility(View.VISIBLE);
        	  endDurationSelected.setVisibility(View.INVISIBLE);
        	  
        	  
          }
          
          public void onSetEndDurationTabClick(View view)
          {
        	  
        	  isDurationTabSelected=false;
        	  
        	  startDurationSelected.setVisibility(View.INVISIBLE);
        	  endDurationSelected.setVisibility(View.VISIBLE);
        	  
        	  numPicker1.setVisibility(View.GONE);
        	  numPicker2.setVisibility(View.VISIBLE);
        	 
        	  
          }
          
          
          
          public void onCancelClick(View view)
          {
        	  
        	  
        	  onBackPressed();
        	  
          }
          
          
          public void onOkClick(View view)
          {
        	  
        	  
        	  
        	  MyDate startTime = new MyDate(year, month, day, timePicker1.getCurrentHour(), timePicker1.getCurrentMinute());
        	  
        	  MyDate endTime = new MyDate(year, month, day, timePicker2.getCurrentHour(), timePicker2.getCurrentMinute());
        	  
        	  int   onDuration=numPicker1.getValue();
        	  
        	  int   offDuration=numPicker2.getValue();
        	  
        	  
        	  if(false)
        	  {
        	  
        	  final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(DateAndTimePickerForAwayMoodActivity.this,getString(R.string.startendtimenovalid));
				
				cusDial.setListner(new CustomDialogueClickListner() {
					
					@Override
					public void onCustomDialogueClick() {
						
						
						cusDial.dismiss();
						
						
						
						
					}
				});
				
				
				cusDial.show();
				
				return;
				
        	  }
        	  
        	  
        	  //mModdModel.time=""+ startTime.getHour() + ":" + startTime.getMinute() + ":" + endTime.getHour() + ":" + endTime.getMinute() + ":" + onDuration + ":" + offDuration;
        	  
        	  mModdModel.awayEndTime=MyDate.getFormattedTime(endTime);
        	  mModdModel.awayStartTime=MyDate.getFormattedTime(startTime);
        	  mModdModel.awayOnDuration= onDuration + "";
        	  mModdModel.awayOffDuration=offDuration + "";
        	  
        	  String tempCommand=currentCommand;
              
              
                int currentIndex=14;
				
				int moodGap=14;
				
				
				int startIndex=currentIndex*moodGap;
				
				int endIndex=startIndex + 28;
				
				
				String hexStartHours=daysMonthsHoursToHexValue(startTime.getHour());
				
				String hexStartMinutes=daysMonthsHoursToHexValue(startTime.getMinute());
				
				String hexEndHours=daysMonthsHoursToHexValue(endTime.getHour());
				
				String hexEndMinutes=daysMonthsHoursToHexValue(endTime.getMinute());
				
				String hexOnDuration=daysMonthsHoursToHexValue(onDuration);
				
				String hexOffDuration=daysMonthsHoursToHexValue(offDuration);
				
				
				tempCommand=tempCommand.substring(0,startIndex) + "DCDCDCDC" + hexStartHours + hexStartMinutes +hexOnDuration + "DCDCDCDC"   + hexEndHours + hexEndMinutes +hexOffDuration +  tempCommand.substring(endIndex ,tempCommand.length());
					
					
				commandtoModify=tempCommand;
				
				
        	  
        	  
        	 if(mModdModel.awayStartTime==null || mModdModel.awayStartTime.equals("N/A") || mModdModel.awayStartTime.equals("fefe"))
        	  {
        		 
        		
        		 mModdModel.save();
        		 
        		 currentCommand=commandtoModify;
		                onBackPressed();
					
        	  }
        	  else
        	  {
        		  
        		  
        		
        		  
        		  String currentMoodButton1=getButtonAwayMood(mModdModel.moodIdentifer, AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_ONE_CMD));
        		  String currentMoodButton2=getButtonAwayMood(mModdModel.moodIdentifer, AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_TWO_CMD));
        		  String currentMoodButton3=getButtonAwayMood(mModdModel.moodIdentifer, AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_THREE_CMD));
        		  
        		  if((!currentMoodButton1.substring(8, 12).equals("0000") && !currentMoodButton1.substring(8, 12).equals("fefe"))|| (!currentMoodButton2.substring(8, 12).equals("0000") &&  !currentMoodButton2.substring(8, 12).equals("fefe") )
        				  || (!currentMoodButton3.substring(8, 12).equals("0000") 
        				  && !currentMoodButton3.substring(8, 12).equals("fefe")))
        		  {
        		 
        			  
        		    WaitingStaticProgress.showProgressDialog("Please wait...", DateAndTimePickerForAwayMoodActivity.this);
        		    startTCPClient();
        		    
        		   
        		  
        		  }
        		  
        		  else
        		  {
        			  
             		  mModdModel.save();
             		  
             		 currentCommand=commandtoModify;
        			  
        			  onBackPressed();
        			  
        		  }
        		  
        		  
        		  
        	  }      
              
        	  
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
            
           
          
          
          
          
   
          public String getHextoSQLDate(String day,String month ,String year,String min,String hour,boolean repeat)
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
      		        	cal.set(Calendar.YEAR, 2016);
      					cal.set(Calendar.MONTH, 8);
      					cal.set(Calendar.DAY_OF_MONTH, 53);
      					cal.set(Calendar.HOUR_OF_DAY, hours);
      					cal.set(Calendar.MINUTE, 10);
      					cal.set(Calendar.SECOND, 0);
      					cal.set(Calendar.MILLISECOND, 0);
      				
      		        	
      		        }
      				
      				
      		      Date date=cal.getTime();
  		        
  		        
   			   
  			    
  			    SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
  			    String dateString = sdf.format(date);
      		
      			return dateString;
      		
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
	
      	
      	
      	//-----------------------------------------------------------------------------------------------------
      //-----------------------------------------------------------------------------------------------------
      //-----------------------------------------------------------------------------------------------------
      //-----------------------------------------------------------------------------------------------------
      //-----------------------------------------------------------------------------------------------------
      	
      	
      	
      	
      	Moods_TCPClient_New singleTcpClient;
     	
     	connectTask task;
     	
     	boolean isLatestCommand=false;
         
         
        boolean isSocketConnected=false;
         
        public static  boolean isPerformingFirstConnection=true;
        
        
        boolean  isConnectionFailedEventOccured=false;


		private Switch_Model mSwitchModel;


		private Context mContext;
      	
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
						
						Intent intent = new Intent(DateAndTimePickerForAwayMoodActivity.this, MoodsRoomWithSwitchesActivity.class);
						
						String mRoomId = getIntent().getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
						String mRoomTitle = getIntent().getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
						
						String swId = getIntent().getStringExtra(AppKeys.KEY_SWITCH_ID_TAG);
						
						intent.putExtra("CMD", currentCommand);
						intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, mRoomId);
						intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, mRoomTitle);
						intent.putExtra(AppKeys.KEY_SWITCH_ID_TAG, swId);
						
						overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);

						startActivity(intent);
						
						
						
					}
				});
				
				
				cusDial.show();
				
				return;
			}
			
			command1="";
			command2="";
			command3="";
			
        	singleTcpClient=null;
        	
        	task=new connectTask();
        	task.execute("");
    	    
        }
    	
		
		public void stopTCPClient()
        {
			
			command1="";
			command2="";
			command3="";
			
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
    			            },DateAndTimePickerForAwayMoodActivity.this);
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
    	
    	 
    	 //----------------------------------------------------------------------------------------------------------------------
    	 
    	 
    	 
    	 public void onsocketConnection(String message ) {
    		 
    		
    		 String tempButtomType=Buttons.BUTTON_ONE.getValue();
    		 
    		 
    		 if(message.trim().length()==0)
    			 
    		 {
    			 tempButtomType=Buttons.BUTTON_ONE.getValue();
    		 }
    		 
    		 
    		 
    		 if(message.trim().length() > 0)
    		 {
    			 tempButtomType=message;
    		 }
    		 
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
    						 
    					
    						String tempReadCMD=cmd;
    						
    						
    						
    						String cmdToWrite=prePareWiteCommandForButton1(commandtoModify,tempReadCMD);
    						
    						byte []temp=MoodsCommandManager.writeCommand(cmdToWrite);
    						
    						singleTcpClient.sendMessageByte(temp);
    						
   						    isWiriteResponseArrived=false;
    						
    						//currentCommand=cmdToWrite;
    						
    						//wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww
    						
    						
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
    						
    						getActiveButtonsforCurrentMood();
    						
    						
    						String buttonCommand="";
        					
        					if(isButtonOneActive)
    						{
    							buttonCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_ONE_CMD);
        						
        						
    						}
    						else if(isButtonTwoActive)
    						{
    							buttonCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_TWO_CMD);
        						
    						}
    						
        					String cmdToWrite0=prePareWiteCommandForButton1(commandtoModify,cmd);
        					
        					String cmdToWrite=prePareWiteCommandForButton1(cmdToWrite0,buttonCommand);
    						
    						byte []temp=MoodsCommandManager.writeCommand(cmdToWrite);
    						
    						singleTcpClient.sendMessageByte(temp);
    						
    						isWiriteResponseArrived=false;
    						
    						//currentCommand=cmdToWrite;
    						
    						
    						
    						
    						//wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww
    						
    					}
    					
    				}
    				else
    				{
    					
    					command3=cmd;
    					
    					getActiveButtonsforCurrentMood();
    					
    					//wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww
    					
    					
    					String buttonCommand="";
    					
    					if(isButtonOneActive)
						{
							buttonCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_ONE_CMD);
    						
    						
						}
						else if(isButtonTwoActive)
						{
							buttonCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_TWO_CMD);
    						
						}
						else if(isButtonThreeActive)
						{
							
							buttonCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_THREE_CMD);
    						
    						
						}
    					
    					
    					String cmdToWrite0=prePareWiteCommandForButton1(commandtoModify,cmd);
    					
    					String cmdToWrite=prePareWiteCommandForButton1(cmdToWrite0,buttonCommand);
						
						byte []temp=MoodsCommandManager.writeCommand(cmdToWrite);
						
						singleTcpClient.sendMessageByte(temp);
						
						isWiriteResponseArrived=false;
						
						//currentCommand=cmdToWrite;
    					
    					
    				
    				}
    				 
    				
    				
    			}

				
    		}, cmd);
    		 
    		 
    		 
    		 
    		 
    	 }

    	 private void getActiveButtonsforCurrentMood() {
				
    		 
    		 if(mSwitchModel.type.equals(SwitchTypes.SWITCH_2.getValue()))
 			{
// 				/getButtonAwayMood
     			 String buttonOneCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_ONE_CMD);
     			 
     			 String buttonTwoCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_TWO_CMD);
     			 
     			String awayMoodButton1=getButtonAwayMood(14,buttonOneCommand);
     			
     			String awayMoodButton2=getButtonAwayMood(14,buttonTwoCommand);
     			 
     			 if(!awayMoodButton1.substring(26,28).equals("00"))
     			 {
     				 isButtonOneActive=true;
     			 }
     			 else
     			 {
     				 isButtonOneActive=false;
     			 }
     			 
     			 if(!awayMoodButton2.substring(26,28).equals("00"))
     			 {
     				 isButtonTwoActive=true;
     			 }
     			 else
     			 {
     				 isButtonTwoActive=false;
     			 }
     			 
 				
 			}
 			else if(mSwitchModel.type.equals(SwitchTypes.SWITCH_3.getValue()))
 			{
 				    String buttonOneCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_ONE_CMD);
    			 
    			    String buttonTwoCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_TWO_CMD);
    			    
    			    String buttonThreeCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_THREE_CMD);
    			    
    			    String awayMoodButton1=getButtonAwayMood(14,buttonOneCommand);
         			
         			String awayMoodButton2=getButtonAwayMood(14,buttonTwoCommand);
         			
         			String awayMoodButton3=getButtonAwayMood(14,buttonThreeCommand);
    			    
    			    
 	   			 if(!awayMoodButton1.substring(26,28).equals("00"))
 				 {
 					 isButtonOneActive=true;
 				 }
 				 else
 				 {
 					 isButtonOneActive=false;
 				 }
 				 
 				 if(!awayMoodButton2.substring(26,28).equals("00"))
 				 {
 					 isButtonTwoActive=true;
 				 }
 				 else
 				 {
 					 isButtonTwoActive=false;
 				 }
 				 
 				 
 				 if(!awayMoodButton3.substring(26,28).equals("00"))
 				 {
 					 isButtonThreeActive=true;
 				 }
 				 else
 				 {
 					 isButtonThreeActive=false;
 				 }
    			    
 				
 			}
     		
     		
				
			}


		public void onSocketTimeOutFailier(String string) {
			
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







		public void unableToCreateConnection(String string) {
			
			try
			{
			
				
			
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					
					WaitingStaticProgress.hideProgressDialog();
					
					final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(DateAndTimePickerForAwayMoodActivity.this,mContext.getString(R.string.Errorinconnection));
					
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
			
				
			
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					
					WaitingStaticProgress.hideProgressDialog();
					
					
					
				}
			});
			
			singleTcpClient.stopClient();
			
			}catch(Exception ex){}
			
			
		}
		
		
		
		public void performEndOperation(String message)
		{
			
			
			WaitingStaticProgress.hideProgressDialog();
			
			currentCommand=message;
			
			mModdModel.save();
			
			stopTCPClient();
			
			
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					
					onBackPressed();
				}
			});
			 
			
		}
		

		private void readMessageReponse(String message) {
			
			
			saveCurrentResponse(getButtonType(message),message);
			
			if(mSwitchModel.type.equals(SwitchTypes.SWITCH_1.getValue()) || mSwitchModel.type.equals(SwitchTypes.SWITCH_SOCKET.getValue()) || mSwitchModel.type.equals(SwitchTypes.SWITCH_AC.getValue()))
			{
				
				performEndOperation(message);
				
			}
			else if(mSwitchModel.type.equals(SwitchTypes.SWITCH_2.getValue()))
			{
				
				String buttonType=getButtonType(message);
				
				
				if(buttonType.equals(Buttons.BUTTON_ONE.getValue()) && isButtonTwoActive)
				{
					
				  String buttonCommand="";
				  
				    buttonCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_TWO_CMD);
						
					
				    String cmdToWrite0=prePareWiteCommandForButton1(commandtoModify,message);
					
					String cmdToWrite=prePareWiteCommandForButton1(cmdToWrite0,buttonCommand);
					
					byte []temp=MoodsCommandManager.writeCommand(cmdToWrite);
					
					singleTcpClient.sendMessageByte(temp);
					
					
					
					//currentCommand=RoomWithSwitchesActivity.buttonsResponsetoSwitchRespnset2Button(currentCommand , cmdToWrite);
					
					
					
					
				}
				else if(buttonType.equals(Buttons.BUTTON_ONE.getValue()) && (!isButtonTwoActive))
				{
					performEndOperation(MoodsRoomWithSwitchesActivity.buttonsResponsetoSwitchRespnset2Button(commandtoModify , message));
					
					
					
					
				}
				
				else if(buttonType.equals(Buttons.BUTTON_TWO.getValue()) )
				{
					performEndOperation(MoodsRoomWithSwitchesActivity.buttonsResponsetoSwitchRespnset2Button(commandtoModify , message));
					
					
					
				}
				
				
				
			}
			else if(mSwitchModel.type.equals(SwitchTypes.SWITCH_3.getValue()))
			{
				
				
				String buttonType=getButtonType(message);
				
				
				if(isButtonOneActive && isButtonTwoActive && isButtonThreeActive)
				{
				
					if(buttonType.equals(Buttons.BUTTON_ONE.getValue()))
					{
					
					  String buttonCommand="";
					  
					    buttonCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_TWO_CMD);
							
						
					    String cmdToWrite0=prePareWiteCommandForButton1(commandtoModify,message);
						
						String cmdToWrite=prePareWiteCommandForButton1(cmdToWrite0,buttonCommand);
						
						
						byte []temp=MoodsCommandManager.writeCommand(cmdToWrite);
						
						singleTcpClient.sendMessageByte(temp);
					}
					else if(buttonType.equals(Buttons.BUTTON_TWO.getValue()))
					{
						
						String buttonCommand="";
						  
					    buttonCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_THREE_CMD);
							
						
					    String cmdToWrite0=prePareWiteCommandForButton1(commandtoModify,message);
						
						String cmdToWrite=prePareWiteCommandForButton1(cmdToWrite0,buttonCommand);
						
						byte []temp=MoodsCommandManager.writeCommand(cmdToWrite);
						
						singleTcpClient.sendMessageByte(temp);
						
					}
					else if(buttonType.equals(Buttons.BUTTON_THREE.getValue()))
					{
						
						 performEndOperation(MoodsRoomWithSwitchesActivity.buttonsResponsetoSwitchRespnset2Button(commandtoModify , message));
						
					}
					
					
				}
				
				
				
				else if(!isButtonOneActive && isButtonTwoActive && isButtonThreeActive)
				{
					
					 if(buttonType.equals(Buttons.BUTTON_TWO.getValue()))
					{
						
						String buttonCommand="";
						  
					    buttonCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_THREE_CMD);
							
						
					    String cmdToWrite0=prePareWiteCommandForButton1(commandtoModify,message);
						
						String cmdToWrite=prePareWiteCommandForButton1(cmdToWrite0,buttonCommand);
						
						byte []temp=MoodsCommandManager.writeCommand(cmdToWrite);
						
						singleTcpClient.sendMessageByte(temp);
						
					}
					else if(buttonType.equals(Buttons.BUTTON_THREE.getValue()))
					{
						
						 performEndOperation(MoodsRoomWithSwitchesActivity.buttonsResponsetoSwitchRespnset2Button(commandtoModify , message));
						
					}
					
					
					
				}
				else if(!isButtonOneActive && isButtonTwoActive && !isButtonThreeActive)
				{
					
					 if(buttonType.equals(Buttons.BUTTON_TWO.getValue()))
					{
						
						 performEndOperation(MoodsRoomWithSwitchesActivity.buttonsResponsetoSwitchRespnset2Button(commandtoModify , message));
					}
					
					
					
					
				}
				else if(isButtonOneActive && !isButtonTwoActive && isButtonThreeActive)
				{
					
					 if(buttonType.equals(Buttons.BUTTON_ONE.getValue()))
					{
						
						 String buttonCommand="";
						  
						    buttonCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_THREE_CMD);
								
							
						    String cmdToWrite0=prePareWiteCommandForButton1(commandtoModify,message);
							
							String cmdToWrite=prePareWiteCommandForButton1(cmdToWrite0,buttonCommand);
							
							
							byte []temp=MoodsCommandManager.writeCommand(cmdToWrite);
							
							singleTcpClient.sendMessageByte(temp);
						
					}
					else if(buttonType.equals(Buttons.BUTTON_THREE.getValue()))
					{
						
						 performEndOperation(commandtoModify);
						
					}
					
					
					
				}
				
				else if(isButtonOneActive && isButtonTwoActive && !isButtonThreeActive)
				{
					
					 if(buttonType.equals(Buttons.BUTTON_ONE.getValue()))
					{
						
						 String buttonCommand="";
						  
						    buttonCommand=AppPreference.getValue(DateAndTimePickerForAwayMoodActivity.this, AppKeys.KEY_BUTTON_TWO_CMD);
								
							
						    String cmdToWrite0=prePareWiteCommandForButton1(commandtoModify,message);
							
							String cmdToWrite=prePareWiteCommandForButton1(cmdToWrite0,buttonCommand);
							
							
							byte []temp=MoodsCommandManager.writeCommand(cmdToWrite);
							
							singleTcpClient.sendMessageByte(temp);
						
					}
			    
					else if(buttonType.equals(Buttons.BUTTON_THREE.getValue()) || buttonType.equals(Buttons.BUTTON_TWO.getValue()))
					{
						
						 performEndOperation(commandtoModify);
						
					}
					
					
					
				}
				
				else if(!isButtonOneActive && !isButtonTwoActive && isButtonThreeActive)
				{
					
					if(buttonType.equals(Buttons.BUTTON_THREE.getValue()))
					{
						
						 performEndOperation(MoodsRoomWithSwitchesActivity.buttonsResponsetoSwitchRespnset2Button(commandtoModify , message));
						
					}
					
				}
				
				
				
				
			}
			
		}
    
    	public String prePareWiteCommandForButton1(String preCommand, String readCommand)
    	{
    	
    		String tempCommand=readCommand.substring(0,8) + RequestTypes.WRITE.getValue() + readCommand.substring(10,readCommand.length());
    		
    		int currentIndex=mModdModel.moodIdentifer;
    		
    		int moodGap=14;
    		
    		int startIndex=currentIndex*moodGap;
    		
    		int endIndex=startIndex + 28;
    		
    		
    		
    		tempCommand=tempCommand.substring(0,startIndex) + preCommand.substring(startIndex, endIndex)  +tempCommand.substring(endIndex ,tempCommand.length());
    		
    		
    		
    		
    		return tempCommand;
    	}
    	
    	
    	
    	
    	
    	
    	public  static  String getButtonAwayMood(int moodIdentifire,String buttonCommand)
    	{
           
    		if(buttonCommand==null || buttonCommand.length()==0)
    		{
    			
    			return "0000000000000000000000000000";
    		}
    		
    		int currentIndex=moodIdentifire;
    		
    		int moodGap=14;
    		
    		int startIndex=currentIndex*moodGap;
    		
    		int endIndex=startIndex + 28;
    		
    		
    		return  buttonCommand.substring(startIndex, endIndex);
    	}
    	
    	
    	public static int getAwayOnDuration(String moodSring)
    	{
    		String temp=moodSring.substring(12, 14);
    		
 		   int onDuration  =  Integer.parseInt(temp,16);
 		
			return onDuration;
    		
    	}
    	public static int getAwayOffDuration(String moodSring)
    	{
    		
    		String temp=moodSring.substring(26, 28);
    		
    		   int offDuration  =  Integer.parseInt(temp,16);
    		
			return offDuration;
    		
    	}
    	
    	
    	public static MyDate getAwayStartTime(String moodSring)
    	{
    		String tempHours=moodSring.substring(8, 10);
    		
    		String tempMinutes=moodSring.substring(10, 12);
    		
 		   int hours  =  Integer.parseInt(tempHours,16);
 		   
 		   int minutes  =  Integer.parseInt(tempMinutes,16);
 		   
 		       MyDate myDate=new MyDate(0, 0, 0, hours, minutes);
 		
			return myDate;
    		
    	}
    	
    	
    	public static String getAwayStartTimeHEX(String moodSring)
    	{
    		String tempHours=moodSring.substring(8, 12);
    		
    		
			return tempHours;
    		
    	}
    	
    	public static String getAwayEndTimeHEX(String moodSring)
    	{
    		String tempHours=moodSring.substring(22, 26);
    		
    		
			return tempHours;
    		
    	}
    	
    	public static MyDate getAwayEndTime(String moodSring)
    	{
    		
    		String tempHours=moodSring.substring(22, 24);
    		
    		String tempMinutes=moodSring.substring(24, 26);
    		
    		int hours  =  Integer.parseInt(tempHours,16);
  		   
  		   int minutes  =  Integer.parseInt(tempMinutes,16);
 		   
 		       MyDate myDate=new MyDate(0, 0, 0, hours, minutes);
 		
			return myDate;
    		
    	}
    	
    	public static String getButtonType(String buttonCommand)
    	{
           
    		
    		
    		
    		
    		return  buttonCommand.substring(12,14); 
    	}
    	
    	
    	private void saveCurrentResponse(String buttonType,String message)
    	{
    		if(buttonType.equals(Buttons.BUTTON_ONE.getValue()))
			{
			
				AppPreference.saveValue(mContext, message, AppKeys.KEY_BUTTON_ONE_CMD);
				
				
			}
			else if(buttonType.equals(Buttons.BUTTON_TWO.getValue()))
			{
			    AppPreference.saveValue(mContext, message, AppKeys.KEY_BUTTON_TWO_CMD);
				
			}
			else if(buttonType.equals(Buttons.BUTTON_THREE.getValue()))
			{
			    AppPreference.saveValue(mContext, message, AppKeys.KEY_BUTTON_THREE_CMD);
				
			}
    	}
		
      	
}
