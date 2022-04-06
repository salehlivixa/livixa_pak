package object.p2pipcam.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.activeandroid.query.Select;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.livixa.apacam.client.activity.DateAndTimePickerForAwayMoodActivity;
import com.livixa.apacam.client.activity.DateAndTimePickerForMoodsActivity;
import com.livixa.apacam.client.activity.DeleteButtonMood;
import com.livixa.apacam.client.activity.DeleteButtonMood.DeleteMoodResultListner;
import com.livixa.apacam.client.activity.HomeActivity;
import com.livixa.apacam.client.activity.Moods_RoomAndSwitchesTCPActivity;
import com.livixa.apacam.client.activity.Moods_RoomAndSwitchesTCPActivity.Buttons;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.swipelistview.SwipeRevealLayout;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.client.R;
import object.p2pipcam.utils.MyDate;

public class Moods_Detail_List_Adapter extends BaseAdapter 
{
	
	Context context;
	private LayoutInflater inflater = null;
	List<Mood_Model> mood_Model_List;
	private String buttonType;
	
	HashMap<Integer, String> moodsButtobStatus=new HashMap<>();
	
	Switch_Model mSwitchModel;
	
	String currentCommand;
	
	private ImageLoader imageLoader;
	private HashMap<Integer, Integer> mMapNonCoustomMoodsImages=new HashMap<>();
	
	
	public Moods_Detail_List_Adapter(Context context,String switchId,String buttonType,String currentCommand,Switch_Model mSwitchModel)
	{
		
		this.context=context;
		
		this.buttonType=buttonType;
		
		this.mSwitchModel=mSwitchModel;
		
		this.currentCommand=currentCommand;
		
		this.mood_Model_List=fetchMoodsOfCurrentSwitchFromDb(switchId);
		
		
		if(mood_Model_List==null)
		{
			mood_Model_List=new ArrayList<>();
		}
		
		
		 imageLoader =ImageLoader.getInstance();
		 
		 
		    mMapNonCoustomMoodsImages.put(1, R.drawable.icon_mood_travel);
			mMapNonCoustomMoodsImages.put(2, R.drawable.icon_mood_sleep);
			mMapNonCoustomMoodsImages.put(3, R.drawable.icon_mood_wakeup);
			mMapNonCoustomMoodsImages.put(4, R.drawable.icon_mood_guest);
			mMapNonCoustomMoodsImages.put(5, R.drawable.icon_mood_living);
			mMapNonCoustomMoodsImages.put(6, R.drawable.icon_mood_safety);
			mMapNonCoustomMoodsImages.put(14, R.drawable.icon_mood_away);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mood_Model_List.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mood_Model_List.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		this.inflater = LayoutInflater.from(context);
		convertView= inflater.inflate(R.layout.moods_detail_list_item, null);
		
		
		   Mood_Model mModel=mood_Model_List.get(position);
			
		
		  View Delete =convertView.findViewById(R.id.tv_switchDelete);
		  
		  SwipeRevealLayout swipeRevealLayout =(SwipeRevealLayout) convertView.findViewById(R.id.moodsListItemRoot);
		  
		  ImageView moodIcon=(ImageView) convertView.findViewById(R.id.moodIcon);
		  
		  TextView tv_MoodName=(TextView) convertView.findViewById(R.id.tv_MoodName);
		  
		  TextView mooddate=(TextView) convertView.findViewById(R.id.mooddate);
		  
		  TextView moodTime=(TextView) convertView.findViewById(R.id.moodTime);
		  
		  TextView moodStatus=(TextView) convertView.findViewById(R.id.moodStatus);
		  
		  
		  
          onDeleteClicked(Delete,position,swipeRevealLayout);
		  
		  
          if(mModel.moodIdentifer > 6 && mModel.moodIdentifer < 14)
	    	 {
          
					  if(mModel.pictureURL!=null && mModel.pictureURL.length() > 0)
					  {
						  moodIcon.setImageResource(R.drawable.icon_mood_custom_white);  
						  imageLoader.displayImage(mModel.pictureURL, moodIcon,KisafaApplication.getImageDisplayOption());
					  }
					  else if(mModel.picture!=null)
					  {
					  try
				       {
						  moodIcon.setImageBitmap(ViewRoomsGridViewAdapter.bytesToBitmap(mModel.picture));
				       }catch(Exception ex)
				       {
				    	   
				       }
					  }
					  else
					  {
						  
						  moodIcon.setImageResource(R.drawable.icon_mood_custom_white);
					  }
		  
	    	 }
          else
          {
        	  
        	  moodIcon.setImageResource(mMapNonCoustomMoodsImages.get(mModel.moodIdentifer));
          }
		  
		  
          
            String moodtitle= mModel.title;
	      	if( mModel.moodIdentifer < 6 ||  mModel.moodIdentifer>13)
	      	{
	      		moodtitle=getMoodTitle(mModel.moodIdentifer);
	      	}
		    tv_MoodName.setText(moodtitle);
		  
		  
		  if(mModel.moodIdentifer!=14)
		  {
		  
		  try
	       {
	    	   
	    	   if(mModel.isRepeatOn.equals("1"))
	    	   {
	    		   
	    		   
	    		   mooddate.setText(context.getString(R.string.Date)+": "+context.getString(R.string.RepeatON));
	    	   }
	    	   else if(mModel.time!=null)
	    	   {
	    		 
	    		   MyDate date=HomeActivity.getAndroidDateFromString(mModel.time) ;
	    		   
	    		   mooddate.setText(context.getString(R.string.Date)+": "+ MyDate.getFormattedDate(date));
	    	   }
	    	   else
	    	   {
	    		   mooddate.setText(context.getString(R.string.Date)+": N/A");
	    	   }
	    	   
	       }catch(Exception ex)
	       {
	    	   
	       }
		  
		  
		  try
	       {
	    	   
	    	   if(mModel.time!=null)
	    	   {
	    		   
	    		   MyDate date=HomeActivity.getAndroidDateFromString(mModel.time) ;
	    		   
	    		   moodTime.setText(context.getString(R.string.Time)+": "+ MyDate.getFormattedTime(date));
	    		   
	    		  
	    		   
	    	   }
	    	   else
	    	   {
	    		   moodTime.setText(context.getString(R.string.Time)+": N/A");
	    		   
	    	   }
	    	   
	       }catch(Exception ex)
	       {
	    	   ex.toString();
	       }
		  
		  
	     }
			else
			{
				
				try
			       {
			    	   
			    	   if(mModel.isRepeatOn.equals("1"))
			    	   {
			    		   
			    		   
			    		   mooddate.setText(context.getString(R.string.Date)+": "+context.getString(R.string.RepeatON));
			    	   }
			    	   
			    	   else
			    	   {
			    		   mooddate.setText(context.getString(R.string.Date)+": N/A");
			    	   }
			    	   
			       }catch(Exception ex)
			       {
			    	   
			       }
				
				
				try
			       {
			    	   
			    	   
			    		   moodTime.setText(context.getString(R.string.Time)+" "+ mModel.awayStartTime);
			    		   
			    		
			    	   
			       }catch(Exception ex)
			       {
			    	   ex.toString();
			       }
				
				
			}
		  
		  if(moodsButtobStatus.containsKey(mModel.moodIdentifer))
		  {
		       moodStatus.setText(context.getString(R.string.ButtonState)+" " + moodsButtobStatus.get(mModel.moodIdentifer));
		  }
		  
		  
		
		return convertView;
	}
	
	
	
	public  String getMoodTitle(int identifer)
	{
		
		
		 
				 if(identifer>=1)
					{
						
						if(identifer==1)
						{
							return context.getResources().getString(R.string.Travel);
						}
						else if(identifer==2)
						{
							return context.getResources().getString(R.string.Sleep);
						}
						else if(identifer==3)
						{
							return context.getResources().getString(R.string.Wakeup);
						}
						else if(identifer==4)
						{
							return context.getResources().getString(R.string.Guest);
						}
						else if(identifer==5)
						{
							return context.getResources().getString(R.string.Living);
						}
						else if(identifer==6)
						{
							return context.getResources().getString(R.string.Safety);
						}
						else if(identifer==14)
						{
							return context.getResources().getString(R.string.Away);
						}
						
					}
				
			return "";
	}

	
	public void onDeleteClicked(View view,final int pos,final SwipeRevealLayout sView)
	{
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Mood_Model mModel=mood_Model_List.get(pos);
				
				String moodtitle= mModel.title;
		      	if( mModel.moodIdentifer < 6 ||  mModel.moodIdentifer>13)
		      	{
		      		moodtitle=getMoodTitle(mModel.moodIdentifer);
		      	}
				
				final CustomAlertDialogueTwoButtons cusTwo=new CustomAlertDialogueTwoButtons(context, context.getString(R.string.removeMoodbuttonfrom) + " "+moodtitle+" "+context.getString(R.string.Mood) );
				
				
               cusTwo.setListner(new CustomAlertDialogueTwoButtons.CustomDialogueTwoButtonsClickListner() {
				
				@Override
				public void onCustomDialoguePositiveClick() {
					
					cusTwo.dismiss();
					
					
					
					
					
					
					DeleteButtonMood deleteButtonMood=new DeleteButtonMood(context);
					
					deleteButtonMood.setActionCompleteListner(new DeleteMoodResultListner() {
						
						@Override
						public void onFailed() {
							
							
							sView.close(true);
							
						}
						
						@Override
						public void onDeleteMood(String currentCommand) {
							
							Moods_Detail_List_Adapter.this.currentCommand=currentCommand;
							sView.close(true);
							
							Mood_Model tempMood=mood_Model_List.get(pos);
							
							tempMood.time=null;
							tempMood.awayOnDuration=null;
							tempMood.awayOffDuration=null;
							tempMood.awayStartTime=null;
							tempMood.awayEndTime=null;
							
							tempMood.save();
							
							
							mood_Model_List.remove(pos);
							
							
							Moods_Detail_List_Adapter.this.notifyDataSetChanged();
							
						}
					});
					
					deleteButtonMood.deleteButtonMood(mSwitchModel, mood_Model_List.get(pos), currentCommand, buttonType);
					
					
					
				}
				
				@Override
				public void onCustomDialogueNegativeClick() {
					
					cusTwo.dismiss();
					
					sView.close(true);
					
				}
			});
				
				
				cusTwo.show();
				
				
				
				
			}
		});
	}
	
	
	
	
	private List<Mood_Model> fetchMoodsOfCurrentSwitchFromDb(String switchId)
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
  			currentButtonCommand=AppPreference.getValue(context, AppKeys.KEY_BUTTON_ONE_CMD);
  		}
  		else if(Buttons.BUTTON_TWO.getValue().equals(buttonType))
  		{
  			currentButtonCommand=AppPreference.getValue(context, AppKeys.KEY_BUTTON_TWO_CMD);
  		}
  		else if(Buttons.BUTTON_THREE.getValue().equals(buttonType))
  		{
  			currentButtonCommand=AppPreference.getValue(context, AppKeys.KEY_BUTTON_THREE_CMD);
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
		  				
		  				moodsButtobStatus.put(cMood.moodIdentifer, (  currentMoodData.substring(12, 14).equals("01") ? context.getString(R.string.ON) : context.getString(R.string.OFF)  ));
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
		  				
		  				
		  				
		  				
		  				if(!timetoCheck.endsWith("0000") && !timetoCheck.endsWith("fefe"))
		  	  			{
		  	  			
		  	  			
		  	  				
		  	  						
		  	  			int onDuration=DateAndTimePickerForAwayMoodActivity.getAwayOnDuration(currentMoodData);
						
						int offDuration=DateAndTimePickerForAwayMoodActivity.getAwayOnDuration(currentMoodData);
						
						MyDate startTime=DateAndTimePickerForAwayMoodActivity.getAwayStartTime(currentMoodData);
						
						MyDate endTime =DateAndTimePickerForAwayMoodActivity.getAwayEndTime(currentMoodData);
						
						if(!timetoCheck.endsWith("0000") && !timetoCheck.endsWith("fefe"))
						{
							cMood.awayEndTime=MyDate.getFormattedTime(endTime);
							cMood.awayStartTime=MyDate.getFormattedTime(startTime);
							cMood.awayOnDuration= onDuration + "";
							cMood.awayOffDuration=offDuration + "";
							cMood.isRepeatOn="1";
							
						}
						else
						{
							cMood.awayEndTime="N/A";
							cMood.awayStartTime="N/A";
							cMood.awayOnDuration= "N/A";
							cMood.awayOffDuration="N/A";
							cMood.isRepeatOn="0";
							
						}
		  	  				
		  	  				
		  	  			}
		  				moodsButtobStatus.put(cMood.moodIdentifer, (context.getString(R.string.ON)));
		  			}
	  				
	  			}
	  			
	  			
	  		}
  		}
		
		
		return moodModelList;
	}
	
	 private List<Mood_Model> fetchMoodsOfCurrentSwitchFromDbNew(String switchId)
  	{
  		List<Mood_Model> moodModelList=new ArrayList<>();
  		
  		String currentButtonCommand="";
  		
  		if(Buttons.BUTTON_ONE.getValue().equals(buttonType))
  		{
  			currentButtonCommand=AppPreference.getValue(context, AppKeys.KEY_BUTTON_ONE_CMD);
  		}
  		else if(Buttons.BUTTON_TWO.getValue().equals(buttonType))
  		{
  			currentButtonCommand=AppPreference.getValue(context, AppKeys.KEY_BUTTON_TWO_CMD);
  		}
  		else if(Buttons.BUTTON_THREE.getValue().equals(buttonType))
  		{
  			currentButtonCommand=AppPreference.getValue(context, AppKeys.KEY_BUTTON_THREE_CMD);
  		}
  		
  		
  		
  		for(int i=1; i <= 14 ;i++) 
  		{
  			
  			
  			if(i!=14)
  			{
  			
  			String currentMoodData=DateAndTimePickerForMoodsActivity.getButtonCurrentMood(i, currentButtonCommand);
  			
  			if(!currentMoodData.substring(12, 14).equals("00"))
  			{
  			
  				Mood_Model obj=new Mood_Model();
  				
  				String date=Moods_RoomAndSwitchesTCPActivity.getHextoSQLDate(currentMoodData.substring(0,2),currentMoodData.substring(2,4),currentMoodData.substring(4,8),currentMoodData.substring(8,10),currentMoodData.substring(10,12));
  				
  				
  				       obj.time=date;
  				       
  				     
  				
  				       if(currentMoodData.equals(AppKeys.KEY_CASE_REPEAT))
  						{
  				    	   obj.isRepeatOn="1";
  						}
  				
  			}
  			
  			}else
  			{
  				
  				String currentMoodData=DateAndTimePickerForAwayMoodActivity.getButtonAwayMood(i, currentButtonCommand);
  				
  				String timetoCheck=DateAndTimePickerForAwayMoodActivity.getAwayStartTimeHEX(currentMoodData);
  				
  				if(!timetoCheck.endsWith("0000") && !timetoCheck.endsWith("fefe"))
  	  			{
  	  			
  	  				Mood_Model obj=new Mood_Model();
  	  				
  	  				
  	  						
  	  			int onDuration=DateAndTimePickerForAwayMoodActivity.getAwayOnDuration(currentMoodData);
				
				int offDuration=DateAndTimePickerForAwayMoodActivity.getAwayOnDuration(currentMoodData);
				
				MyDate startTime=DateAndTimePickerForAwayMoodActivity.getAwayStartTime(currentMoodData);
				
				MyDate endTime =DateAndTimePickerForAwayMoodActivity.getAwayEndTime(currentMoodData);
				
				
				
				if(!timetoCheck.endsWith("0000") && !timetoCheck.endsWith("fefe"))
				{
					obj.awayEndTime=MyDate.getFormattedTime(endTime);
					obj.awayStartTime=MyDate.getFormattedTime(startTime);
					obj.awayOnDuration= onDuration + "";
					obj.awayOffDuration=offDuration + "";
					obj.isRepeatOn="1";
					
				}
				else
				{
					obj.awayEndTime="N/A";
					obj.awayStartTime="N/A";
					obj.awayOnDuration= "N/A";
					obj.awayOffDuration="N/A";
					obj.isRepeatOn="0";
					
				}
  	  				
  	  				
  	  			}
  				
  			}
  			
  			
  		}
  		
  		
  		
  		return moodModelList;
  	}
	 
	 public String getCurrentCommand()
	 {
		 
		 return currentCommand;
	 }
	
}
