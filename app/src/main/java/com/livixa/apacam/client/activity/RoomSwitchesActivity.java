package com.livixa.apacam.client.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activeandroid.query.Select;
import com.afollestad.materialdialogs.MaterialDialog;
import com.devsmart.android.ui.HorizontalListView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity.SwitchTypes;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons.CustomDialogueTwoButtonsClickListner;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue.CustomDialogueClickListner;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.R;
import com.livixa.client.R.layout;
import com.livixa.client.R.string;
import object.p2pipcam.adapter.RoomSwitchesGridViewAdapter;
import object.p2pipcam.adapter.RoomsGridViewAdapter;
import object.p2pipcam.adapter.Switch_List_Adapter;

public class RoomSwitchesActivity extends Activity {

	
     private GridView mRoomSwitchesGridView;
     
     private HorizontalListView  mRoomSwitchesHorizontalListview;
	
	private View mEmptyView;

	private RoomSwitchesGridViewAdapter mRoomSwitchesGridAdaptor;
	
	private HAdapter   hListAdapter;

	private Room_Model mRoomModel;
	
	 private List<Switch_Model>	 mSwitchModelList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(layout.activity_room_switches);
		
		initUiComponents();
		handleIntent();
		initOthers();
		
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
	}
	
	
	private void initUiComponents() {
		mRoomSwitchesGridView = (GridView) findViewById(R.id.roomSwitchesGridView);
		mRoomSwitchesHorizontalListview=(HorizontalListView) findViewById(R.id.hlistview);
		mEmptyView= findViewById(R.id.roomSwitchesEmptylayout);
		
		
	}

	private void initOthers() {
		
		
		hListAdapter=new  HAdapter(RoomSwitchesActivity.this);
		mRoomSwitchesHorizontalListview.setAdapter(hListAdapter);
		
		
		mRoomSwitchesGridAdaptor = new RoomSwitchesGridViewAdapter(RoomSwitchesActivity.this,mEmptyView,mRoomSwitchesGridView,mRoomModel);
		mRoomSwitchesGridView.setAdapter(mRoomSwitchesGridAdaptor);
		
		
		mRoomSwitchesGridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {
				
				
				
				final Switch_Model tempSwitchModel=(Switch_Model) parent.getItemAtPosition(position);
				/*MaterialDialog dialog = new MaterialDialog.Builder(RoomSwitchesActivity.this)
						.content("Are you sure to remove switch with "+tempSwitchModel.title+" from room?")
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
								
								
								tempSwitchModel.room_id="";
								tempSwitchModel.isSyncedWithServer="0";
								try
								{
								tempSwitchModel.title=Add_Edit_SwitchActivity.getSwitchTitle(tempSwitchModel.mac_address);
								}catch(Exception ex){}
								tempSwitchModel.save();
								
								hListAdapter.addItem(tempSwitchModel);
								
								mRoomSwitchesGridAdaptor.removeItem(position);
								mRoomSwitchesGridAdaptor.notifyDataSetChanged();
								
							}
						}).build();
				dialog.show();*/
				
				
				final CustomAlertDialogueTwoButtons myASlertDialog=new CustomAlertDialogueTwoButtons(RoomSwitchesActivity.this,getString(string.removeswitchname)+" "+tempSwitchModel.title+" "+getString(string.fromroom));
				
				myASlertDialog.setListner(new CustomDialogueTwoButtonsClickListner() {
					
					@Override
					public void onCustomDialoguePositiveClick() {
						
						myASlertDialog.dismiss();
						
						
						tempSwitchModel.room_id="";
						tempSwitchModel.isSyncedWithServer="0";
						try
						{
						//tempSwitchModel.title=Add_Edit_SwitchActivity.getSwitchTitle(tempSwitchModel.mac_address);
						}catch(Exception ex){}
						tempSwitchModel.save();
						
						hListAdapter.addItem(tempSwitchModel);
						
						mRoomSwitchesGridAdaptor.removeItem(position);
						mRoomSwitchesGridAdaptor.notifyDataSetChanged();
						
					}
					
					@Override
					public void onCustomDialogueNegativeClick() {
						
						myASlertDialog.dismiss();
						
					}
				});
				
				myASlertDialog.show();
				
				
				
				
				
				
				
				return false;
			}
		});
		
		
		

		mRoomSwitchesHorizontalListview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				
				
				
				
				addSwitchNamePopupHandler(position);
				
				
				
				return false;
			}
		});
	}
	
	
	private class HAdapter extends BaseAdapter {

		
	     LayoutInflater inflater;
	     Map<String, Integer> mMapSwitchesToResourse = new HashMap<String, Integer>();
	     public HAdapter(Context context) {
	        inflater = LayoutInflater.from(context);
	        
	        mSwitchModelList = fetchUnAssignedSwitchesFromDb();
			
			if(mSwitchModelList==null)
			{
				mSwitchModelList=new ArrayList<>();
				
			}
			
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
	    
	    
	    public void addItem(Switch_Model swModel) {
	         
	    	
	    	if(mSwitchModelList==null)
	    	{
	    		mSwitchModelList=new ArrayList<>();
	    	}
	        mSwitchModelList.add(swModel);
	        notifyDataSetChanged();
	    }

	    @Override
	    public long getItemId(int position) {
	         
	        return position;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        HViewHolder holder;
	        Switch_Model switch_Model=mSwitchModelList.get(position);
	        if (convertView == null) {
	            convertView = inflater.inflate(layout.room_switches_listitem, null);
	            holder = new HViewHolder();
	            
	            
	            holder.img = (ImageView) convertView.findViewById(R.id.img_icon_switch);
	            holder.switchName = (TextView) convertView.findViewById(R.id.switchName);
	            holder.switchId = (TextView) convertView.findViewById(R.id.switchId);
	            
	            convertView.setTag(holder);

	        } else {
	            holder = (HViewHolder) convertView.getTag();
	        }
	        
	        
	        holder.img.setImageResource(mMapSwitchesToResourse.get(switch_Model.type));
	        
	        try
	        {
	        holder.switchName.setText(Add_Edit_SwitchActivity.getSwitchTitle(switch_Model.mac_address));
	        }catch(Exception ex)
	        {
	        	
	        }
	        
	        holder.switchId.setText("ID: " + switch_Model.mac_address);
	       
	        return convertView;
	    }

	}

	class HViewHolder {
	    ImageView img;
	    TextView  switchName;
	    TextView  switchId;
	}

	
	private List<Switch_Model> fetchUnAssignedSwitchesFromDb()
	{
		List<Switch_Model> switchModelList=null;
		try
		{
                  switchModelList = new Select().from(Switch_Model.class)
					.where("Switch_Model.model_status != ? AND Switch_Model.room_id = ?", AppKeys.KEY_IS_DELETED,"")
					.execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return switchModelList;
	}
	
	
	public void onbackButttonClick(View view)
	{
		onBackPressed();
	}
	
	
	public void onhomeButttonClick(View view)
	{
		finish();
		
		Intent intent = new Intent(this,HomeActivity.class);
		startActivity(intent);
		//overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		KisafaApplication.perFormActivityBackTransition(this);
	}
	
	
	@Override
	public void onBackPressed() {
		 
		super.onBackPressed();
		
		
		finish();
		
		Intent intent = new Intent(this, Add_Edit_RoomsActivity.class);
		
		
		KisafaApplication.perFormActivityBackTransition(this);
		
		startActivity(intent);
		
		
	}
	
	
	public void handleIntent()
	{
		try
		{

			 Intent intent=getIntent();
			 
			 String roomId=intent.getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
			 mRoomModel=getRoomFromDb(roomId);
			 
		}catch(Exception ex)
		{
			ex.toString();
		}
	}
	
	private Room_Model getRoomFromDb(String roomId)
	{
		Room_Model roomModel=null;
		
		try
		   {
	             roomModel = new Select().from(Room_Model.class).where("Room_Model.room_id = ?",roomId).executeSingle();
		   }catch(Exception ex)
		   {
			   ex.toString();
		   }
		
		return roomModel;
	}
	
	
	
	
	private void  addSwitchNamePopupHandler(final int pos) {
		LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View popupView = layoutInflater.inflate(layout.add_switch_name_popup, null);

		TextView tv_cancel = (TextView) popupView.findViewById(R.id.tv_Cancel);
		TextView tv_Done = (TextView) popupView.findViewById(R.id.tv_Done);
		final EditText et_SwitchName = (EditText) popupView.findViewById(R.id.switchNameET);
		final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
				true);

		popupWindow.setTouchable(true);
		popupWindow.setFocusable(true);

		popupWindow.setAnimationStyle(R.style.BottonUpAnimation);
		popupWindow.showAtLocation(popupView, 0, 0, 0);
		
		
		
		

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
				
				String mNewSwitchTempName=et_SwitchName.getText().toString();
				
				
				if(mNewSwitchTempName.trim().length() ==0)
				{
					final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(RoomSwitchesActivity.this,getString(string.SwitchNameisrequired));
					
					cusDial.setListner(new CustomDialogueClickListner() {
						
						@Override
						public void onCustomDialogueClick() {
							
							
							cusDial.dismiss();
							
							
							
							
						}
					});
					
					
					cusDial.show();
					
					return;
				}

				
				if(!isValidSwitchName(mNewSwitchTempName,mRoomModel.room_id))
				{
					showErrorDialogue(getString(string.Switchnamesshouldunique));
				}
				else
				{
					Switch_Model switchModel=mSwitchModelList.remove(pos);
					switchModel.title=mNewSwitchTempName;
					switchModel.isSyncedWithServer="0";
					mRoomSwitchesGridAdaptor.assignWwitchToThisRoom(switchModel);
					hListAdapter.notifyDataSetChanged();
				}
				

			}
		});
		
		
		
	}
	
	
	private void showErrorDialogue(String message) {
		/*MaterialDialog dialog = new MaterialDialog.Builder(RoomSwitchesActivity.this).content(message)
				.positiveText(android.R.string.ok)
				.positiveColor(KisafaApplication.getAppResources().getColor(R.color.app_header_bg))
				.callback(new MaterialDialog.ButtonCallback() {
					@Override
					public void onPositive(MaterialDialog dialog) {

					}
				}).build();
		dialog.show();*/
		
		
		final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(RoomSwitchesActivity.this,message);
		
		cusDial.setListner(new CustomDialogueClickListner() {
			
			@Override
			public void onCustomDialogueClick() {
				
				
				cusDial.dismiss();
				
				
				
				
			}
		});
		
		
		cusDial.show();
		

		
	}
	
	
	public boolean isValidSwitchName(String switchName,String roomId)
	{
		Switch_Model switchModel=null;
		try
		   {
	       switchModel = new Select().from(Switch_Model.class).where("Switch_Model.title COLLATE NOCASE = ?  AND Switch_Model.room_id = ? ",switchName.trim(),roomId).executeSingle();
			
			//List<Switch_Model> selectedRoomSwitches=mRoomSwitchesGridAdaptor.getAddedSwitchesFromGrid();
			
			/*if(selectedRoomSwitches!=null)
			{
			
				for(int i=0; i<selectedRoomSwitches.size(); i++)
				{
					
					if(selectedRoomSwitches.get(i).title.equals(switchName))
					{
						switchModel=selectedRoomSwitches.get(i);
						break;
					}
					
				}
			}*/
			
			
		   }catch(Exception ex)
		   {
			   ex.toString();
		   }
		
		if(switchModel==null)
		{
			return true;
		}
		
		return false;
	}
}
