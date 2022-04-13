package object.p2pipcam.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activeandroid.query.Select;
import com.github.mrengineer13.snackbar.SnackBar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.livixa.apacam.client.activity.AddDeleteUpdateSwitchWithMoodsToServer;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity;
import com.livixa.apacam.client.activity.AddDeleteUpdateSwitchWithMoodsToServer.SwitchServerResult;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity.SwitchTypes;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.swipelistview.SwipeRevealLayout;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons.CustomDialogueTwoButtonsClickListner;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.client.R;

public class Switch_List_Adapter extends BaseAdapter implements ServerConnectListener
{
	
	private Context mContext;
	private LayoutInflater          mInflater;
    private List<Switch_Model>	 mSwitchModelList;
    private List<Room_Model> mRoomModelList;
    private View emptyView , listView;
    private boolean isDeleteDialogeShowing;
    
    Map<String, Integer> mMapSwitchesToResourse = new HashMap<String, Integer>();
    private Map<String, String> mMapSwitchesToRooms=new  HashMap<String, String>();
	
	public Switch_List_Adapter(Context mContext,View emptyView ,View listView)
	{
		
		this.mContext=mContext;
		this.mInflater = LayoutInflater.from(mContext);
		this.emptyView=emptyView;
		this.listView=listView;
		
		mSwitchModelList = fetchSwitchesFromDb();
		
		mRoomModelList=ViewRoomsGridViewAdapter.fetchRoomsFromDb(mContext);
		
		if(mSwitchModelList==null)
		{
			mSwitchModelList=new ArrayList<>();
			
		}
		
		if(mRoomModelList==null)
		{
			mRoomModelList=new ArrayList<>();
		}
		
		
		for(int i=0; i<mRoomModelList.size(); i++)
		{
			mMapSwitchesToRooms.put(mRoomModelList.get(i).room_id, mRoomModelList.get(i).title);
		}
		
		isListEmptyThenShowEmptyView();
		
		
		mMapSwitchesToResourse.put(SwitchTypes.SWITCH_1.getValue(), R.drawable.sw1);
		mMapSwitchesToResourse.put(SwitchTypes.SWITCH_2.getValue(), R.drawable.sw2);
		mMapSwitchesToResourse.put(SwitchTypes.SWITCH_3.getValue(), R.drawable.sw3);
		mMapSwitchesToResourse.put(SwitchTypes.SWITCH_SOCKET.getValue(), R.drawable.sw_socket);
		mMapSwitchesToResourse.put(SwitchTypes.SWITCH_AC.getValue(), R.drawable.sw_ac);
		
		
		
	}

	@Override
	public int getCount() {
		
		return mSwitchModelList.size();
	}

	@Override
	public Object getItem(int position) {
		
		return mSwitchModelList.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		SwitchViewHolder switchViewHolder=null;
		
		Switch_Model switch_Model=mSwitchModelList.get(position);
		
		   
			
			
			if(convertView==null)
			{
				convertView= mInflater.inflate(R.layout.switch_listview_item, null);
				switchViewHolder=new SwitchViewHolder();
				switchViewHolder.switchIcon=(ImageView) convertView.findViewById(R.id.img_icon_switch);
				switchViewHolder.switchName=(TextView) convertView.findViewById(R.id.tv_switchName);
				switchViewHolder.switchId=(TextView) convertView.findViewById(R.id.switchId);
				switchViewHolder.switchAssignedTo=(TextView) convertView.findViewById(R.id.switchAssignedTo);
				switchViewHolder.deletebutton=(TextView) convertView.findViewById(R.id.tv_switchDelete);
				switchViewHolder.swipeRevealLayout=(SwipeRevealLayout) convertView.findViewById(R.id.switchesListItemRoot);
			}
			
			else
			{
				switchViewHolder=(SwitchViewHolder) convertView.getTag();
			}
			
			switchViewHolder.swipeRevealLayout.close(true);
			
			if(switch_Model.title==null || switch_Model.title.length()==0)
			{
				switchViewHolder.switchName.setText(Add_Edit_SwitchActivity.getSwitchTitle(switch_Model.mac_address));
			}
			else
			{
			  switchViewHolder.switchName.setText(switch_Model.title);
			}
			switchViewHolder.switchId.setText(switch_Model.mac_address);
			
			if(switch_Model.room_id.equals(""))
				switchViewHolder.switchAssignedTo.setText(mContext.getResources().getString(R.string.Assignedto)+ " N/A");
			else
				switchViewHolder.switchAssignedTo.setText(mContext.getResources().getString(R.string.Assignedto) + mMapSwitchesToRooms.get(switch_Model.room_id));
			
			try
			{
			switchViewHolder.switchIcon.setImageResource(mMapSwitchesToResourse.get(switch_Model.type));
			}catch(Exception ex)
			{
				ex.toString();
			}
			onDeleteSwitch(switchViewHolder.deletebutton,position,switchViewHolder.swipeRevealLayout);
			
		
			
			convertView.setTag(switchViewHolder);
		
		
		return convertView;
	}

	@Override
	public void onSuccess(ServerResponse response) {
		
		
	}

	@Override
	public void onFailure(ServerResponse response) {
		 
		
	}
	
	
	public void addSwitchtoList(Switch_Model switchModel)
	{
		if(mSwitchModelList==null)
		{
			mSwitchModelList=new ArrayList<>();
		}
		
		mSwitchModelList.add(switchModel);
		
		isListEmptyThenShowEmptyView();
		
		notifyDataSetChanged();
	}
	
	class SwitchViewHolder
	{
		public ImageView  switchIcon;
		public TextView       deletebutton;
		public TextView  switchName;
		public TextView  switchAssignedTo;
		public TextView  switchId;
		public SwipeRevealLayout swipeRevealLayout;
	}
	
	
	private List<Switch_Model> fetchSwitchesFromDb()
	{
		List<Switch_Model> switchModelList=null;
		try
		{
			switchModelList = new Select().from(Switch_Model.class).where("Switch_Model.model_status != ?  ORDER BY Switch_Model.type ", AppKeys.KEY_IS_DELETED).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return switchModelList;
	}
	
	private boolean deleteswitch(String switchId,final SwipeRevealLayout swipeRevealLayout)
	{
		 Switch_Model switchModel=null;
		try
		   {
	            switchModel = new Select().from(Switch_Model.class).where("switch_id = ?",switchId.trim()).executeSingle();
	            
		   }catch(Exception ex){}
		
		if(switchModel!=null)
		{
			switchModel.room_id="";
			
			switchModel.ip_address="";
			
			try
			{
			switchModel.title="";
			}catch(Exception ex){}
			switchModel.isSyncedWithServer="0";
			switchModel.model_status=AppKeys.KEY_IS_DELETED;
			
			
			try
			{
				
				WaitingStaticProgress.showProgressDialog("", mContext);
				
				final List<Mood_Model> moods=fetchMoodsOfCurrentSwitchFromDb(switchModel.switch_id);
				
					if(moods!=null)
					{
						for(int i=0 ; i<moods.size() ; i++)
						{
							Mood_Model mMood=moods.get(i);
							
							
							mMood.modelStatus=AppKeys.KEY_IS_DELETED;
							
							mMood.picture=null;
							
							mMood.pictureURL="";
							
							mMood.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
							
							mMood.save();
							
						}
					}
				
				
			       
		           final Switch_Model tempSwitchModel=switchModel;
		           
		            AddDeleteUpdateSwitchWithMoodsToServer addDeleteUpdateSwitchWithMoodsToServer=new AddDeleteUpdateSwitchWithMoodsToServer(mContext, switchModel, moods);
		           
		           addDeleteUpdateSwitchWithMoodsToServer.addResultListner(new SwitchServerResult() {
					
					@Override
					public void onSuccess(ServerResponse response) {
						
						try
						{
							
							for(int i=0; i<moods.size();i++)
							{
								
								moods.get(i).save();
							}
							
							tempSwitchModel.save();
							
							
							
						}
						catch(Exception ex)
						{}
						
						WaitingStaticProgress.hideProgressDialog();
						isListEmptyThenShowEmptyView();
						notifyDataSetChanged();
						swipeRevealLayout.close(false);
						
					}
					
					@Override
					public void onFailer(String response) {
						
						
						WaitingStaticProgress.hideProgressDialog();
						
						try
						{
						new SnackBar.Builder((Activity)mContext).withMessage(response).withDuration(SnackBar.MED_SNACK).show();
						}catch(Exception ex)
						{
							
						}
						
						swipeRevealLayout.close(false);
						
						
					}
				});
		           
		           addDeleteUpdateSwitchWithMoodsToServer.makeCalltoServer();
				
				
				
				
			}
			catch(Exception ex)
			{
				WaitingStaticProgress.hideProgressDialog();
				ex.toString();
			}
			
			return true;
		}
		
		return false;
	}
	
	
	private void onDeleteSwitch(View tView,final int pos,final SwipeRevealLayout swipeRevealLayout)
	{
		tView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				if(!isDeleteDialogeShowing)
				{
				
								isDeleteDialogeShowing=true;
								
								
								
								
				
						Switch_Model tempSwitchModel=mSwitchModelList.get(pos);
						
						
						
						final CustomAlertDialogueTwoButtons cusTwo=new CustomAlertDialogueTwoButtons(mContext, mContext.getResources().getString(R.string.DoyouwanttodeleteswitchwithMAC)+tempSwitchModel.mac_address);
						
						cusTwo.setListner(new CustomDialogueTwoButtonsClickListner() {
							
							@Override
							public void onCustomDialoguePositiveClick() {
								
								
								
								try
								{

								Switch_Model tempSwitchModel=mSwitchModelList.remove(pos);
								
								
								deleteswitch(tempSwitchModel.switch_id,swipeRevealLayout);
								
								}catch(Exception ex)
								{
									
								}
								
								cusTwo.dismiss();
								
								isDeleteDialogeShowing=false;
								
							}
							
							@Override
							public void onCustomDialogueNegativeClick() {
								
								
								cusTwo.dismiss();
								
								swipeRevealLayout.close(false);
								isDeleteDialogeShowing=false;
								
							}
						});
						
						
						
						cusTwo.show();
						
						/*MaterialDialog dialog = new MaterialDialog.Builder(mContext)
								.content("Do do want to delete switch with MAC="+tempSwitchModel.mac_address)
								.positiveText(android.R.string.ok)
								.negativeText(android.R.string.cancel)
								.negativeColor(
										KisafaApplication.getAppResources().getColor(
												R.color.app_header_bg))
								.positiveColor(
										KisafaApplication.getAppResources().getColor(
												R.color.app_header_bg))
								.callback(new MaterialDialog.ButtonCallback() {
									@Override
									public void onPositive(MaterialDialog dialog) {
										
										
										Switch_Model tempSwitchModel=mSwitchModelList.remove(pos);
										
										
										if(deleteswitch(tempSwitchModel.switch_id))
										{
											
											isListEmptyThenShowEmptyView();
											notifyDataSetChanged();
										}
										
									}
								}).build();
						dialog.show();
						
						dialog.setOnDismissListener(new OnDismissListener() {
							
							
		
							
		
							@Override
							public void onDismiss(DialogInterface dialog)
							{
								
							}
						});*/
						
				
				}
				
			}
		});
	}
	
	
	private void isListEmptyThenShowEmptyView()
	{
		if(mSwitchModelList.size()==0)
		{
			listView.setVisibility(View.GONE);
			emptyView.setVisibility(View.VISIBLE);
		}
		else if(emptyView.getVisibility()==View.VISIBLE && mSwitchModelList.size() >0)
		{
			listView.setVisibility(View.VISIBLE);
			emptyView.setVisibility(View.GONE);
		}
	}
	
	
	private List<Room_Model> fetchRoomsFromDb()
	{
		List<Room_Model> roomModelList=null;
		try
		{
			roomModelList = new Select().from(Room_Model.class).where("User_Room_Model.userId = ? AND Room_Model.modelStatus != ?     ORDER BY Room_Model.title",AppPreference.getValue(mContext, AppKeys.KEY_USER_ID), AppKeys.KEY_IS_DELETED).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return roomModelList;
	}
	
	private List<Mood_Model> fetchMoodsOfCurrentSwitchFromDb(String switchId)
	{
		List<Mood_Model> moodModelList=null;
		try
		{
			moodModelList = new Select().from(Mood_Model.class).where("Mood_Model.switchId = ?", switchId).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return moodModelList;
	}
}
