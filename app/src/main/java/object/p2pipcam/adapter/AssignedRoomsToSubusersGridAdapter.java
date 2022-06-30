package object.p2pipcam.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activeandroid.query.Select;
import com.afollestad.materialdialogs.MaterialDialog;
import com.livixa.apacam.client.network.AppWebServices;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity;
import com.livixa.apacam.client.activity.AssignedCamerasToSubuserActivity;
import com.livixa.apacam.client.activity.AssignedRoomtoSubuserActivity;
import com.livixa.apacam.client.activity.CameraAssignedToSubusersActivity;
import com.livixa.apacam.client.activity.RoomActivity;
import com.livixa.apacam.client.activity.RoomAssignedToSubusersActivity;
import com.livixa.apacam.client.activity.RoomSwitchesActivity;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_Data_to_Server_WebServiceCall;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_Data_to_Server_WebServiceCall.Data_Status;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.dbmodel.Camera_Model;
import com.livixa.apacam.dbmodel.Manage_DB_Model;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import com.livixa.apacam.dbmodel.User_Room_Model;
import com.livixa.client.R;

@SuppressLint("ViewHolder")
public class AssignedRoomsToSubusersGridAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	
	
	Context context;
	
	
	private List<Room_Model> mRoomModelList;
	
	private List<User_Room_Model> mUserRoomModelList;
	
	private View emptyView , listView;


	private LayoutInflater mInflater;

	SUB_USER_DB  _sUB_USER_DB;
	
	protected boolean isDeleteDialogeShowing;
	
	Map<String, Object>  mapRoomstoSubUser=new HashMap<>();


	


	private ImageLoader imageLoader;

	public AssignedRoomsToSubusersGridAdapter(Context context,View emptyView ,View listView,SUB_USER_DB subUser) {
		inflater = LayoutInflater.from(context);
		this.context=context;
		
		
		this.mInflater = LayoutInflater.from(context);
		this.emptyView=emptyView;
		this.listView=listView;
		this._sUB_USER_DB=subUser;
		
		
		mRoomModelList=ViewRoomsGridViewAdapter.fetchRoomsFromDb(context);
		
		mUserRoomModelList = getAllAssociationOfSubUserOfThisCamera(subUser.sh_user_id);
		
		
		
		
		
		if(mRoomModelList==null)
		{
			mRoomModelList=new ArrayList<>();
		}
		
		if(mUserRoomModelList==null)
		{
			mUserRoomModelList=new ArrayList<>();
		}
		
		
		for(int i=0; i<mUserRoomModelList.size();i++ )
		{
			mapRoomstoSubUser.put(mUserRoomModelList.get(i).room_id, mUserRoomModelList.get(i));
		}
		
		
		
		
		
		isListEmptyThenShowEmptyView();
		
		
		
	
		 imageLoader =ImageLoader.getInstance();
		
		
	}

	@Override
	public int getCount() {
		return mRoomModelList.size();
	}

	@Override
	public Object getItem(int arg0) 
	{
		return mRoomModelList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup arg2)
	{
		
		GridViewItemHolder gridViewItemHolder=null;
		Room_Model         roomModel=mRoomModelList.get(pos);
		
		if(convertView == null)
		{
			gridViewItemHolder=new GridViewItemHolder();
			convertView = inflater.inflate(R.layout.subuser_rooms_grid_item, null);
			gridViewItemHolder.roomName=(TextView) convertView.findViewById(R.id.iv_bedName);
			gridViewItemHolder.settingButton=(CheckBox) convertView.findViewById(R.id.roomSubuser_CheckBox);
			gridViewItemHolder.placeHolder=(ImageView) convertView.findViewById(R.id.iv_img_placeHolder);
			gridViewItemHolder.tileLayout=(LinearLayout) convertView.findViewById(R.id.tileLayout);
		}
		else
		{
			gridViewItemHolder=(GridViewItemHolder) convertView.getTag();
		}
		
		
		
		boolean isAssociationExist=mapRoomstoSubUser.containsKey(roomModel.room_id);
		
		
		if(isAssociationExist)
		{
			
			User_Room_Model user_Room_Model=(User_Room_Model) mapRoomstoSubUser.get(roomModel.room_id);
			
			if(!user_Room_Model.modelStatus.equals(AppKeys.KEY_IS_DELETED))
			{
				gridViewItemHolder.settingButton.setChecked(true);
			}
			else
			{
				gridViewItemHolder.settingButton.setChecked(false);
			}
		}
		else
		{
			gridViewItemHolder.settingButton.setChecked(false);
		}
		
		onItemClick(gridViewItemHolder.tileLayout,pos);
		settingIconClick(gridViewItemHolder.settingButton,pos);
		
		gridViewItemHolder.roomName.setText(roomModel.title);


		
		
		
		
		if(roomModel.picture!=null && roomModel.picture.length >0)
		{
		 gridViewItemHolder.placeHolder.setImageBitmap(bytesToBitmap(roomModel.picture));
		}
		else if(roomModel.pictureURL!=null && roomModel.pictureURL.trim().length() > 0 )
		{
			gridViewItemHolder.placeHolder.setImageResource(R.drawable.placeholder);
			imageLoader.displayImage(AppWebServices.imageUrl.concat(roomModel.pictureURL), gridViewItemHolder.placeHolder,KisafaApplication.getImageDisplayOption());
		}
		else
		{
			gridViewItemHolder.placeHolder.setImageResource(R.drawable.placeholder);
		}
		convertView.setTag(gridViewItemHolder);
		
		return convertView;
		 
		
	}
	
	
	
	public class GridViewItemHolder
	
	{
		public CheckBox   settingButton;
		
		public ImageView   placeHolder;
		
		public TextView    roomName;
		
		public LinearLayout tileLayout;
	}
	
	
	public void settingIconClick(final CheckBox view,final int pos)
	{
		
		
		
		
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
				/*if(view.isChecked())
					showProgressDialog("Assigning room to user", 100);
				else
				    showProgressDialog("Unassigning room to user", 100);*/
				if(view.isChecked())
					showProgressDialog("", 100);
				else
				    showProgressDialog("", 100);
				
				Sync_Data_to_Server_WebServiceCall sync_Data_to_Server_WebServiceCall=new Sync_Data_to_Server_WebServiceCall(context);
				
				Sync_Data_to_Server_WebServiceCall.SetClickedByUser(true);
				
				 sync_Data_to_Server_WebServiceCall.setDataLiostner(new Data_Status() {
						
						@Override
						public void onRecieveData(boolean isSuccessfull) {
							
							
							if(!isSuccessfull)
							{
								
								
								if(view.isChecked())
								{
									
									view.setChecked(false);
									
								}
								else
								{
									view.setChecked(true);
								}
								
								
								WaitingStaticProgress.hideProgressDialog();
								
								return;
							}
							
							
							if(isSuccessfull)
							{
								
								 assign_or_unassign_rooms(view,pos);
								
							}
							
							
						}
					});
				 
				 
				  if(sync_Data_to_Server_WebServiceCall.isSyncedRequired())
				  {
				   
					  
					  sync_Data_to_Server_WebServiceCall.execute(); 
					  
					  return;
				  }
				  else
				  {
					  
					  
					  
					  assign_or_unassign_rooms(view,pos);
					  
				  }
				
				
				
				
				
			}
		});
	}
	
	
	public void onItemClick(final View view,final int pos)
	{
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				((Activity) context).finish();
				
				Room_Model roomObject=mRoomModelList.get(pos);
				Intent intent = new Intent(context,RoomAssignedToSubusersActivity.class);
				
				intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, roomObject.room_id);
				
				intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, roomObject.title);
				
				intent.putExtra(AppKeys.KEY_SUB_USER_TAG, _sUB_USER_DB.sh_user_id);
				
				
				context.startActivity(intent);
				
				KisafaApplication.perFormActivityNextTransition(context);
			}
		});
	}
	
	
	private List<Room_Model> fetchRoomsFromDb()
	{
		List<Room_Model> roomModelList=null;
		try
		{
			roomModelList = new Select().from(Room_Model.class).where("User_Room_Model.userId = ? AND Room_Model.modelStatus != ?   ORDER BY Room_Model.title",AppPreference.getValue(context, AppKeys.KEY_USER_ID), AppKeys.KEY_IS_DELETED).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return roomModelList;
	}
	
	private void isListEmptyThenShowEmptyView()
	{
		if(mRoomModelList.size()==0)
		{
			listView.setVisibility(View.GONE);
			emptyView.setVisibility(View.VISIBLE);
		}
		else if(emptyView.getVisibility()==View.VISIBLE && mRoomModelList.size() >0)
		{
			listView.setVisibility(View.VISIBLE);
			emptyView.setVisibility(View.GONE);
		}
	}
	
	
	public static Bitmap bytesToBitmap(byte[] array)
	{
		return BitmapFactory.decodeByteArray(array, 0, array.length);
	}
	
	
	
	private void callPopup(final int pos, final ImageView view) {

		 LayoutInflater layoutInflater = (LayoutInflater)context
		 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		 View popupView = layoutInflater.inflate(R.layout.grid_activity_rooms_setting_popup, null);
		
		TextView tv_cancel=(TextView) popupView.findViewById(R.id.cancelButton);
		TextView tv_editRoom=(TextView) popupView.findViewById(R.id.tv_editRoom);
		TextView tv_deleteRoom=(TextView) popupView.findViewById(R.id.tv_deleteRoom);
		
		
		
		

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
		 
		 
		 tv_editRoom.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
                    ((Activity) context).finish();
					
					Room_Model roomObject=mRoomModelList.get(pos);
					Intent intent = new Intent(context,RoomActivity.class);
					
					intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, roomObject.room_id);
					
					intent.putExtra(AppKeys.KEY_IS_CREATED, false);
					
					context.startActivity(intent);
					
					KisafaApplication.perFormActivityNextTransition(context);
					
					
				}
			});
		 
		 
		 tv_deleteRoom.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					
					onDeleteRoom(pos);
					
				}
			});
		 
		 popupWindow.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				
				view.setImageResource(R.drawable.icon_room_setting);
			}
		});
		
	}
	
	
	private boolean deleteRoom(String roomId)
	{
		 Room_Model roomModel=null;
		 User_Room_Model user_Room_Model=null;
		try
		   {
			roomModel = new Select().from(Room_Model.class).where("room_id = ?",roomId.trim()).executeSingle();
			user_Room_Model=   new Select().from(User_Room_Model.class).where("room_id = ? AND userId = ?",roomId.trim(),AppPreference.getValue(context, AppKeys.KEY_USER_ID)).executeSingle();
			//user_Room_Model=   new Select().from(User_Room_Model.class).where("room_id = ? ",roomId.trim()).executeSingle();
		   }catch(Exception ex){}
		
		
		
		
		if(roomModel!=null && user_Room_Model!=null)
		{
			roomModel.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
			roomModel.modelStatus=AppKeys.KEY_IS_DELETED;
			roomModel.save();
			
			user_Room_Model.userId=AppPreference.getValue(context, AppKeys.KEY_USER_ID);
			user_Room_Model.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
			user_Room_Model.modelStatus=AppKeys.KEY_IS_DELETED;
			user_Room_Model.save();
			
			//unassigned  all switches of this room
			
			List<Switch_Model> switchModelList=null;
			try
			{
				switchModelList = new Select().from(Switch_Model.class).where("Switch_Model.room_id = ? AND  Switch_Model.model_status != ?",roomModel.room_id, AppKeys.KEY_IS_DELETED).execute();
			}catch(Exception ex)
			{
				ex.toString();
			}
			if(switchModelList!=null && switchModelList.size() > 0)
			{
				for(int i=0; i<switchModelList.size(); i++)
				{
					Switch_Model switch_Model=switchModelList.get(i);
					//switch_Model.model_status=AppKeys.KEY_IS_DELETED;
					switch_Model.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
					switch_Model.room_id="";
					try
					{
					
					switch_Model.title="";//Add_Edit_SwitchActivity.getSwitchTitle(switch_Model.mac_address);
					}catch(Exception ex)
					{
						ex.toString();
					}
					
					switch_Model.save();
					
				}
			}
			
			
			return true;
		}
		
		return false;
	}
	
	
	private void onDeleteRoom(final int pos)
	{

		
		
		if(!isDeleteDialogeShowing)
		{
		
						isDeleteDialogeShowing=true;
		
				Room_Model tempRoomModel=mRoomModelList.get(pos);
				MaterialDialog dialog = new MaterialDialog.Builder(context)
						.content("Are you sure to delete this room")
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
								
								
								Room_Model tempSwitchModel=mRoomModelList.remove(pos);
								
								
								if(deleteRoom(tempSwitchModel.room_id))
								{
									
									isListEmptyThenShowEmptyView();
									notifyDataSetChanged();
								}
								
							}
						}).build();
				dialog.show();
				
				dialog.setOnDismissListener(new Dialog.OnDismissListener() {
					
					@Override
					public void onDismiss(DialogInterface dialog) {
						
						isDeleteDialogeShowing=false;
					}
				});
				
		
		}
		
	
	}
	
	
	public static List<User_Room_Model> getAllAssociationOfSubUserOfThisCamera(String userId) {

		List<User_Room_Model> tempUserRoomModel = null;

		try {

			tempUserRoomModel = new Select().from(User_Room_Model.class)

					.where("User_Room_Model.userId = ?", userId).execute();

			return tempUserRoomModel;

		} catch (Exception ex) {
			ex.toString();
		}
		return tempUserRoomModel;

	}
	
	
	private void showProgressDialog(String text, int progress) {
		
		/*mProgressDialog = new ProgressDialog(context,R.style.progressDialogTheme);
		mProgressDialog.setMessage(text);
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(progress);
		
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();*/
		
		WaitingStaticProgress.showProgressDialog(text, context);
	}
	
	
	private void assign_or_unassign_rooms(final CheckBox view,final int pos)
	{
		
		Room_Model roomModel=mRoomModelList.get(pos);
		
		boolean isAssociationExist=mapRoomstoSubUser.containsKey(roomModel.room_id);
		
		if(view.isChecked())
		{
			
			if(isAssociationExist)
			{
				User_Room_Model user_Room_Model=(User_Room_Model) mapRoomstoSubUser.get(roomModel.room_id);
				
				
				if(user_Room_Model!=null)
				{
					user_Room_Model.modelStatus=AppKeys.KEY_IS_UPDATED;
					user_Room_Model.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
					user_Room_Model.save();
				}
				
			}
			else
			{
				
				
				User_Room_Model user_Room_Model=new User_Room_Model();
				
				user_Room_Model.user_room_id=Manage_DB_Model.getUnitTimeStampForRoomUser();
				user_Room_Model.room_id=roomModel.room_id;
				user_Room_Model.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
				user_Room_Model.modelStatus=AppKeys.KEY_IS_CREATED;
				user_Room_Model.userId=_sUB_USER_DB.sh_user_id;
				user_Room_Model.save();
				
				mapRoomstoSubUser.put(user_Room_Model.room_id,user_Room_Model);
				
			}
			
			
			
			
			
			Sync_Data_to_Server_WebServiceCall sync_Data_to_Server_WebServiceCall=new Sync_Data_to_Server_WebServiceCall(context);
			 sync_Data_to_Server_WebServiceCall.setDataLiostner(new Data_Status() {
					
					@Override
					public void onRecieveData(boolean isSuccessfull) {
						
						
						if(!isSuccessfull)
						{
							
							
							if(view.isChecked())
							{
								
								view.setChecked(false);
								
							}
							else
							{
								view.setChecked(true);
							}
							
							
						}
						
						WaitingStaticProgress.hideProgressDialog();
						
					}
				});
			  sync_Data_to_Server_WebServiceCall.isSyncedRequired();
			   sync_Data_to_Server_WebServiceCall.execute(); 
			
		}
		else
		{
			if(isAssociationExist)
			{
				User_Room_Model user_Room_Model=(User_Room_Model) mapRoomstoSubUser.get(roomModel.room_id);
				
				
				if(user_Room_Model!=null)
				{
					user_Room_Model.modelStatus=AppKeys.KEY_IS_DELETED;
				
					user_Room_Model.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
					user_Room_Model.save();
				}
				
				
				
					
				//showProgressDialog("Unassign room to user", 100);
				
				Sync_Data_to_Server_WebServiceCall sync_Data_to_Server_WebServiceCall=new Sync_Data_to_Server_WebServiceCall(context);
				 sync_Data_to_Server_WebServiceCall.setDataLiostner(new Data_Status() {
						
						@Override
						public void onRecieveData(boolean isSuccessfull) {
							
							if(!isSuccessfull)
							{
								
								
								if(view.isChecked())
								{
									
									view.setChecked(false);
									
								}
								else
								{
									view.setChecked(true);
								}
								
								
							}
							
							WaitingStaticProgress.hideProgressDialog();
							
							
						}
					});
				  sync_Data_to_Server_WebServiceCall.isSyncedRequired();
				   sync_Data_to_Server_WebServiceCall.execute(); 
				
			}
			else
			{
				
			}
		}
	
	}
	
}
