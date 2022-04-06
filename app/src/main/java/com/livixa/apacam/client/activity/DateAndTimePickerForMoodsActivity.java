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

public class DateAndTimePickerForMoodsActivity extends Activity {
	
	
	DatePicker datePicker;
	
	
	TimePicker timePicker;
	
	
	LinearLayout dateContainer;
	
	
	LinearLayout dateTab;
	
	LinearLayout timeTab;
	
	
	boolean isDateTabSelected=true;
	
	
	TextView dateTV;
	
	TextView timeTV;
	
	TextView dateDaysTV;
	
	View dateSelected;
	
	View timeSelected;
	
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
	
	private Context mContext;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_and_time_picker_for_moods);
		
		mContext=this;
		
          datePicker=(DatePicker) findViewById(R.id.dp);
          timePicker=(TimePicker) findViewById(R.id.tp);
          
          
          handleIntent();
          
          
          Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
          
          
         dateTV=(TextView) findViewById(R.id.dateTV);
         
         timeTV=(TextView) findViewById(R.id.timeTV);
         
         dateDaysTV =(TextView) findViewById(R.id.dateDaysTV);
		
		 dateTab=(LinearLayout) findViewById(R.id.dateTab);
		
		 timeTab=(LinearLayout) findViewById(R.id.timeTab);
		
		 dateSelected=findViewById(R.id.dateSelected);
		 
		 timeSelected=findViewById(R.id.timeSelected);
		 
		 reapeatCB=(CheckBox) findViewById(R.id.reapeatCB);
		 
		 
		 
		 calendar = Calendar.getInstance();
	      year = calendar.get(Calendar.YEAR);
	      
	      month = calendar.get(Calendar.MONTH);
	      day = calendar.get(Calendar.DAY_OF_MONTH);
	      
	       hours = calendar.get(Calendar.HOUR_OF_DAY);
	       minutes = calendar.get(Calendar.MINUTE);
	      
	       timePicker.setIs24HourView(true);
	      
	       
	       
	      

			Mood_Model moodModel=mModdModel;
			
			
			if(moodModel.time!=null)
			{
				
					int currentIndex=moodModel.moodIdentifer;
					
					int moodGap=14;
					
					
					int startIndex=currentIndex*moodGap;
					
					int endIndex=startIndex + moodGap;
					
					
					String tempMoodValues=currentCommand.substring( startIndex , endIndex);
					
					
					
					if(!tempMoodValues.equals("00000000000000"))
					{
					
					
							String hexDays=tempMoodValues.substring(0,2);
							
							String hexMonths=tempMoodValues.substring(2,4);
							
							String hexYears=tempMoodValues.substring(4,8);
							
							String hexHours=tempMoodValues.substring(8,10);
							
							String hexMinutes=tempMoodValues.substring(10,12);
							
							String hexOnOffState=tempMoodValues.substring(12,14);
							
							
							if(tempMoodValues.substring(0,8).equals("00000000"))
							{
								
								reapeatCB.setChecked(true);
								
								
								   int mins=Integer.parseInt(hexMinutes,16);
				      		       int hours=Integer.parseInt(hexHours,16);
				      		       
				      		     setRepeatDate(hours,mins);
				      		     
				      		   timePicker.setCurrentHour(hours);
				    	       
				    	       timePicker.setCurrentMinute(mins);
								
							}
							else
							{
								reapeatCB.setChecked(false);
								
								
								if(isValidDateAndTime(hexDays,hexMonths,hexYears,hexMinutes,hexHours))
								{
								
								   
									
									   day=Integer.parseInt(hexDays,16);
					      		       month=Integer.parseInt(hexMonths,16);
					      		       year=Integer.parseInt(hexYears,16);
					      		       int mins=Integer.parseInt(hexMinutes,16);
					      		       int hours=Integer.parseInt(hexHours,16);
					      		       
					      		     setDate(hours,mins);
					      		     
					      		   timePicker.setCurrentHour(hours);
					    	       
					    	       timePicker.setCurrentMinute(mins);
									
									
								}
								else
								{
									
									
									
									
								}
								
								
							}
							
							
							
					
					}
					else
					{
						
						setDate();
						
						
						
					}
			
			
			
			}//////////////////
			
			else
			{
				
				setDate();
				
				
				
			}
	       
	     
	      datePicker.init(year, month, day, new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				
				
				//Toast.makeText(DateAndTimePickerForMoodsActivity.this, ""+monthOfYear, Toast.LENGTH_SHORT).show();
				
				isDateChangedEventHappened=true;
				
				DateAndTimePickerForMoodsActivity.this.year=year;
				DateAndTimePickerForMoodsActivity.this.month=monthOfYear;//+1;
				DateAndTimePickerForMoodsActivity.this.day=dayOfMonth;
				
				
			}
		});
	      
	      
	      
	      
	   
	      
	      
		
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

		Intent intent = new Intent(DateAndTimePickerForMoodsActivity.this, Moods_RoomAndSwitchesTCPActivity.class);
		
		String mRoomId = getIntent().getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
		String mRoomTitle = getIntent().getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
		
		String swId = getIntent().getStringExtra(AppKeys.KEY_SWITCH_ID_TAG);
		
		intent.putExtra("CMD", currentCommand);
		intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, mRoomId);
		intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, mRoomTitle);
		intent.putExtra(AppKeys.KEY_SWITCH_ID_TAG, swId);
		intent.putExtra("scroll", (Parcelable) getIntent().getParcelableExtra("scroll"));
		
		KisafaApplication.perFormActivityBackTransition(this);

		startActivity(intent);
		
		
		
	

}

          public DatePicker getDatePicker()
          {
        	  
        	  DatePicker tempdatePicker=new DatePicker(this, null, R.style.CustomDatePickerTheme);
        	  
        	  tempdatePicker.setSpinnersShown(true);
        	  
        	 
        	  return tempdatePicker;
          }
          
          
          
          public void onSetDateTabClick(View view)
          {
        	  
        	  isDateTabSelected=true;
        	  
        	  
        	  datePicker.setVisibility(View.VISIBLE);
        	  timePicker.setVisibility(View.GONE);
        	  
        	  dateSelected.setVisibility(View.VISIBLE);
        	  timeSelected.setVisibility(View.INVISIBLE);
        	  
        	  
          }
          
          public void onSetTimeTabClick(View view)
          {
        	  
        	  isDateTabSelected=false;
        	  
        	  dateSelected.setVisibility(View.INVISIBLE);
        	  timeSelected.setVisibility(View.VISIBLE);
        	  
        	  datePicker.setVisibility(View.GONE);
        	  timePicker.setVisibility(View.VISIBLE);
        	  
          }
          
          public void onCancelClick(View view)
          {
        	  
        	  
        	  onBackPressed();
        	  
          }
          
          
          public void onOkClick(View view)
          {
        	  
              
        	  MyDate tempDate=setDate();
        	  
        	  String tempCommand=currentCommand;
              
              
              int currentIndex=mModdModel.moodIdentifer;
				
				int moodGap=14;
				
				
				int startIndex=currentIndex*moodGap;
				
				int endIndex=startIndex + moodGap;
				
				String hexDays=daysMonthsHoursToHexValue(tempDate.getDay());//tempMoodValues.substring(0,2);
				
				String hexMonths=daysMonthsHoursToHexValue(tempDate.getMonth());//tempMoodValues.substring(2,4);
				
				String hexYears=yearsToHexValue(tempDate.getYear());//tempMoodValues.substring(4,8);
				
				String hexHours=daysMonthsHoursToHexValue(tempDate.getHour());//tempMoodValues.substring(8,10);
				
				String hexMinutes=daysMonthsHoursToHexValue(tempDate.getMinute());//tempMoodValues.substring(10,12);
				
				
				
				
				if(reapeatCB.isChecked())
				{
				
					tempCommand=tempCommand.substring(0,startIndex) + AppKeys.KEY_CASE_REPEAT +hexHours+hexMinutes + tempCommand.substring(endIndex-2 ,tempCommand.length());
					
					mModdModel.isRepeatOn="1";
				
				}
				else
				{
					
					tempCommand=tempCommand.substring(0,startIndex) + hexDays + hexMonths + hexYears +hexHours+hexMinutes+tempCommand.substring(endIndex-2 ,tempCommand.length());
					
					mModdModel.isRepeatOn="0";
				}
				
				
				
				
				commandtoModify=tempCommand;
        	  
        	  
        	 if(mModdModel.time==null)
        	  {
        		 
        		 mModdModel.time=HomeActivity.getAndroidStringDateFromDate(tempDate);
        		 mModdModel.save();
        		 
        		 currentCommand=commandtoModify;
        		 
		                onBackPressed();
					
        	  }
        	  else
        	  {
        		  
        		  
        		
        		  
        		  String currentMoodButton1=getButtonCurrentMood(mModdModel.moodIdentifer, AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_ONE_CMD));
        		  String currentMoodButton2=getButtonCurrentMood(mModdModel.moodIdentifer, AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_TWO_CMD));
        		  String currentMoodButton3=getButtonCurrentMood(mModdModel.moodIdentifer, AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_THREE_CMD));
        		  
        		  if(!currentMoodButton1.substring(12, 14).equals("00") || !currentMoodButton2.substring(12, 14).equals("00") || !currentMoodButton3.substring(12, 14).equals("00"))
        		  {
        		 
        		    WaitingStaticProgress.showProgressDialog("Please wait...", DateAndTimePickerForMoodsActivity.this);
        		    startTCPClient();
        		    
        		    mModdModel.time=HomeActivity.getAndroidStringDateFromDate(tempDate);
        		  
        		  }
        		  
        		  else
        		  {
        			  mModdModel.time=HomeActivity.getAndroidStringDateFromDate(tempDate);
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
          
          public MyDate  setDate()
          {
        	  
        	  

        	  int hour = timePicker.getCurrentHour();
              int min = timePicker.getCurrentMinute();
        	  
              Calendar cal= Calendar.getInstance();
              
              //calendar.set(year, month, day+1);
              
              
              
             
              cal.set(year, month, day, hour, min);
              
              String dayLongName = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()).toLowerCase();
              
              dayLongName=dayLongName.substring(0,1).toUpperCase()+dayLongName.substring(1,dayLongName.length());
              
              String monthNmae =   calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()).toLowerCase();
              
              monthNmae=monthNmae.substring(0,1).toUpperCase()+monthNmae.substring(1);
              
              String tempDay="";
              
              if(day<10)
            	{
            	  
            	  tempDay="0"+day;
            	  
            	}
              else
              {
            	  tempDay=""+day;
              }
              
              dateTV.setText(dayLongName.substring(0,3)+", "+ monthNmae.substring(0,3)+" "+tempDay);
              
              dateDaysTV.setText(tempDay);
             
              
              
              String temphour="";
              
              if(hour<10)
          	{
          	  
            	  temphour="0"+hour;
          	  
          	}
            else
            {
            	temphour=""+hour;
            }
              
              
              
              String tempMin="";
              
              if(min<10)
          	{
          	  
            	  tempMin="0"+min;
          	  
          	}
            else
            {
            	tempMin=""+min;
            }
              
              
              timeTV.setText(temphour+":" + tempMin);
              
              
             
              
			return new MyDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
        	  
          }
          
          
          
          public void setDate(int hour, int min)
          {
        	  
        	  

        	  
        	  
              Calendar calendar= Calendar.getInstance();
              
              calendar.set(year, month=month-1, day,hour,min);
              
              
              
              
              String dayLongName = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()).toLowerCase();
              
              dayLongName=dayLongName.substring(0,1).toUpperCase()+dayLongName.substring(1);
              
              String monthNmae =   calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()).toLowerCase();
              
              monthNmae=monthNmae.substring(0,1).toUpperCase()+monthNmae.substring(1);
              
              String tempDay="";
              
              if(day<10)
            	{
            	  
            	  tempDay="0"+day;
            	  
            	}
              else
              {
            	  tempDay=""+day;
              }
              
              dateTV.setText(dayLongName.substring(0,3)+", "+ monthNmae.substring(0,3)+" "+tempDay);
              
              dateDaysTV.setText(tempDay);
             
              
              
              String temphour="";
              
              if(hour<10)
          	{
          	  
            	  temphour="0"+hour;
          	  
          	}
            else
            {
            	temphour=""+hour;
            }
              
              
              
              String tempMin="";
              
              if(min<10)
          	{
          	  
            	  tempMin="0"+min;
          	  
          	}
            else
            {
            	tempMin=""+min;
            }
              
              
              timeTV.setText(temphour+":" + tempMin);
        	  
          }
          
          
          
          public void setRepeatDate(int hour, int min)
          {
        	  
        	  

        	  
        	  
              Calendar calendar= Calendar.getInstance();
              
              calendar.set(year, month=month, day,hour,min);
              
              
              
              
              String dayLongName = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()).toLowerCase();
              
              dayLongName=dayLongName.substring(0,1).toUpperCase()+dayLongName.substring(1);
              
              String monthNmae =   calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()).toLowerCase();
              
              monthNmae=monthNmae.substring(0,1).toUpperCase()+monthNmae.substring(1);
              
              String tempDay="";
              
              if(day<10)
            	{
            	  
            	  tempDay="0"+day;
            	  
            	}
              else
              {
            	  tempDay=""+day;
              }
              
              dateTV.setText(dayLongName.substring(0,3)+", "+ monthNmae.substring(0,3)+" "+tempDay);
              
              dateDaysTV.setText(tempDay);
             
              
              
              String temphour="";
              
              if(hour<10)
          	{
          	  
            	  temphour="0"+hour;
          	  
          	}
            else
            {
            	temphour=""+hour;
            }
              
              
              
              String tempMin="";
              
              if(min<10)
          	{
          	  
            	  tempMin="0"+min;
          	  
          	}
            else
            {
            	tempMin=""+min;
            }
              
              
              timeTV.setText(temphour+":" + tempMin);
        	  
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
      			
      			
      			if(mModdModel.moodIdentifer > 6 && mModdModel.moodIdentifer < 14)
      	   			((TextView)findViewById(R.id.tv_title)).setText(mModdModel.title);
      	   			else
      	   			((TextView)findViewById(R.id.tv_title)).setText(getMoodTitle(mModdModel.moodIdentifer));
      			
     		   
      		} catch (Exception ex) {
      			ex.toString();
      		}
      	}
          
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
          
          private Switch_Model getSwitchFromDb(String roomId) {
      		Switch_Model switchModel = null;

      		try {
      			switchModel = new Select().from(Switch_Model.class).where("switch_id = ?", roomId).executeSingle();
      		} catch (Exception ex) {
      			ex.toString();
      		}

      		return switchModel;
      		
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
						
						Intent intent = new Intent(DateAndTimePickerForMoodsActivity.this, MoodsRoomWithSwitchesActivity.class);
						
						String mRoomId = getIntent().getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
						String mRoomTitle = getIntent().getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
						
						String swId = getIntent().getStringExtra(AppKeys.KEY_SWITCH_ID_TAG);
						
						intent.putExtra("CMD", currentCommand);
						intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, mRoomId);
						intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, mRoomTitle);
						intent.putExtra(AppKeys.KEY_SWITCH_ID_TAG, swId);
						
						overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);

						startActivity(intent);
						
						finish();
						
						
						
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
    			            },DateAndTimePickerForMoodsActivity.this);
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
    							buttonCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_ONE_CMD);
        						
        						
    						}
    						else if(isButtonTwoActive)
    						{
    							buttonCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_TWO_CMD);
        						
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
							buttonCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_ONE_CMD);
    						
    						
						}
						else if(isButtonTwoActive)
						{
							buttonCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_TWO_CMD);
    						
						}
						else if(isButtonThreeActive)
						{
							
							buttonCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_THREE_CMD);
    						
    						
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
					
					final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(DateAndTimePickerForMoodsActivity.this,mContext.getString(R.string.Errorinconnection));
					
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
		
		public void UpdateCommand(String message)
		{
			currentCommand=message;
			
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
				  
				    buttonCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_TWO_CMD);
						
					
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
					  
					    buttonCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_TWO_CMD);
							
						
					    String cmdToWrite0=prePareWiteCommandForButton1(commandtoModify,message);
						
						String cmdToWrite=prePareWiteCommandForButton1(cmdToWrite0,buttonCommand);
						
						
						byte []temp=MoodsCommandManager.writeCommand(cmdToWrite);
						
						singleTcpClient.sendMessageByte(temp);
					}
					else if(buttonType.equals(Buttons.BUTTON_TWO.getValue()))
					{
						
						String buttonCommand="";
						  
					    buttonCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_THREE_CMD);
							
						
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
						  
					    buttonCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_THREE_CMD);
							
						
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
						  
						    buttonCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_THREE_CMD);
								
							
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
						  
						    buttonCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_TWO_CMD);
								
							
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
    		
    		int endIndex=startIndex + moodGap;
    		
    		
    		
    		tempCommand=tempCommand.substring(0,startIndex) + preCommand.substring(startIndex, endIndex)  +tempCommand.substring(endIndex ,tempCommand.length());
    		
    		
    		
    		
    		return tempCommand;
    	}
    	
    	
    	public void getActiveButtonsforCurrentMood()
    	{
    		
    		 if(mSwitchModel.type.equals(SwitchTypes.SWITCH_2.getValue()))
			{
				
    			 String buttonOneCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_ONE_CMD);
    			 
    			 String buttonTwoCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_TWO_CMD);
    			 
    			
    			 
    			 if(!getButtonCurrentMoodState(mModdModel.moodIdentifer,buttonOneCommand).equals("00"))
    			 {
    				 isButtonOneActive=true;
    			 }
    			 else
    			 {
    				 isButtonOneActive=false;
    			 }
    			 
    			 if(!getButtonCurrentMoodState(mModdModel.moodIdentifer,buttonTwoCommand).equals("00"))
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
				String buttonOneCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_ONE_CMD);
   			 
   			    String buttonTwoCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_TWO_CMD);
   			    
   			    String buttonThreeCommand=AppPreference.getValue(DateAndTimePickerForMoodsActivity.this, AppKeys.KEY_BUTTON_THREE_CMD);
   			    
   			    
	   			 if(!getButtonCurrentMoodState(mModdModel.moodIdentifer,buttonOneCommand).equals("00"))
				 {
					 isButtonOneActive=true;
				 }
				 else
				 {
					 isButtonOneActive=false;
				 }
				 
				 if(!getButtonCurrentMoodState(mModdModel.moodIdentifer,buttonTwoCommand).equals("00"))
				 {
					 isButtonTwoActive=true;
				 }
				 else
				 {
					 isButtonTwoActive=false;
				 }
				 
				 
				 if(!getButtonCurrentMoodState(mModdModel.moodIdentifer,buttonThreeCommand).equals("00"))
				 {
					 isButtonThreeActive=true;
				 }
				 else
				 {
					 isButtonThreeActive=false;
				 }
   			    
				
			}
    		
    		
    		
    		
    		
    	}
    	
    	
    	
    	
    	public static  String getButtonCurrentMood(int moodIdentifire,String buttonCommand)
    	{
           
    		if(buttonCommand==null || buttonCommand.length()==0)
    		{
    			
    			return "00000000000000";
    		}
    		
    		int currentIndex=moodIdentifire;
    		
    		int moodGap=14;
    		
    		int startIndex=currentIndex*moodGap;
    		
    		int endIndex=startIndex + moodGap;
    		
    		
    		return  buttonCommand.substring(startIndex, endIndex);
    	}
    	
    	public static String getButtonCurrentMoodState(int moodIdentifire,String buttonCommand)
    	{
           
    		
    		
    		String tempMood=getButtonCurrentMood(moodIdentifire,buttonCommand);
    		
    		return  tempMood.substring(12,14); 
    	}
    	
    	
    	
    	
    	
    	
    	
    	public static String getButtonType(String buttonCommand)
    	{
           
    		
    		
    		
    		
    		return  buttonCommand.substring(12,14); 
    	}
    	
    	
    	public static Point getButtonCurrentMoodIndexes(int moodIdentifire,String buttonCommand)
    	{
           
    		
    		int currentIndex=moodIdentifire;
    		
    		int moodGap=14;
    		
    		int startIndex=currentIndex*moodGap;
    		
    		int endIndex=startIndex + moodGap;
    		
    		Point startEndIndexes=new Point(startIndex, endIndex);
    		
    		
    		return  startEndIndexes;
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
