package object.p2pipcam.adapter;

import com.livixa.client.BridgeService;
import com.livixa.client.R;
import com.livixa.apacam.client.activity.Add_OR_Edit_UserActivity;
import com.livixa.apacam.client.activity.CameraAssignedToSubusersActivity;
import com.livixa.apacam.client.activity.AssignedCamerasToSubuserActivity;
import com.livixa.apacam.client.activity.ShSwitchView;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_Data_to_Server_WebServiceCall;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_Sub_User_Cam_Association;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_Data_to_Server_WebServiceCall.Data_Status;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_Sub_User_Cam_Association.SubuserAccociationStatus;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.dbmodel.Camera_Model;
import com.livixa.apacam.dbmodel.Manage_DB_Model;
import com.livixa.apacam.dbmodel.Sub_User_Cam_Association;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model_Relation;
import object.p2pipcam.bean.CameraParamsBean;
import object.p2pipcam.system.SystemValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.activeandroid.query.Select;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ViewHolder")
public class CamerasGridViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	
	
	Context context;
	private List<Camera_Model>  camera_Model;
	public List<User_Camera_Model>  user_Camera_Model;
	SUB_USER_DB  _sUB_USER_DB;
	
	HashMap<String, Object> cameraId=new HashMap<String, Object>();
	
	boolean lockCheckChange=false;


	private ProgressDialog mProgressDialog;
	
	

	public CamerasGridViewAdapter(Context context,List<User_Camera_Model> user_Camera_Model_List,List<Camera_Model> camera_Model_List,SUB_USER_DB  _sUB_USER_DB) {
		inflater = LayoutInflater.from(context);
		this.context=context;
		this.camera_Model=camera_Model_List;
		this.user_Camera_Model=user_Camera_Model_List;
		this._sUB_USER_DB=_sUB_USER_DB;
		
		
		try
		{
		
		if(user_Camera_Model_List!=null)
		 for(int i=0;i<user_Camera_Model_List.size();i++)
		  {
			
				cameraId.put(user_Camera_Model_List.get(i).cameraId, user_Camera_Model_List.get(i));	
		  }
		}
		catch(Exception ex)
		{
			  
		}
        
		
	}

	@Override
	public int getCount() {
		return camera_Model.size();
	}

	@Override
	public Object getItem(int arg0) 
	{
		return camera_Model.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2)
	{
		convertView = inflater.inflate(R.layout.camera_grid_view_item, null);
		
		final LinearLayout tileLayout= (LinearLayout)convertView.findViewById(R.id.tileLayout);
		
		TextView  cameraName=(TextView) convertView.findViewById(R.id.iv_camerName);
		
		TextView  cameraMac=(TextView) convertView.findViewById(R.id.iv_MacAddress);
		
		final CheckBox  camera_CheckBox=(CheckBox) convertView.findViewById(R.id.camera_CheckBox);
		
		
		camera_CheckBox.setTag(arg0);
		tileLayout.setTag(arg0);
		
		Camera_Model camParamBean=camera_Model.get(arg0);
		
		
		cameraName.setText(camParamBean.name);
		cameraMac.setText(camParamBean.did);
		try
		{
			
		if(cameraId!=null && cameraId.get(camParamBean.cameraId)!=null)
		{
			User_Camera_Model user_camera_Model=(User_Camera_Model) cameraId.get(camera_Model.get(arg0).cameraId);
			if(user_camera_Model!=null)
			if(!user_camera_Model.modelStatus.equals(AppKeys.KEY_IS_DELETED))
			{
			 lockCheckChange=true;
			 camera_CheckBox.setChecked(true);
			 lockCheckChange=false;
			}
		}
			
			
			
		}catch(Exception ex)
		{
			
		}
		/*camera_CheckBox.setOnCheckedChangeListener(new  OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				
				
				
				assign_or_unassign_cameras
				
				
			}
		});*/
		
		
		
		settingIconClick(camera_CheckBox,arg0);
		
		
		tileLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				LinearLayout lv=(LinearLayout)tileLayout;
				int pos=(Integer) lv.getTag();
				
				Camera_Model camParamBean=camera_Model.get(pos);
				
				
				 callPopup(pos);
				
			}
		});
		
		return convertView;
	}
	
	
	
	
	
	
	
	
	private void callPopup(final int pos) {

		 LayoutInflater layoutInflater = (LayoutInflater)context
		 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		 View popupView = layoutInflater.inflate(R.layout.grid_activity_camera_setting_popup, null);
		
		TextView tv_cancel=(TextView) popupView.findViewById(R.id.cancelButton);
		TextView tv_Camera=(TextView) popupView.findViewById(R.id.tv_Camera);
		TextView tv_subUsers=(TextView) popupView.findViewById(R.id.tv_subUsers);
		
		
		
		

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
		 
		 
		 tv_Camera.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					callPopupSetting(pos);
					
					
				}
			});
		 
		 
		 tv_subUsers.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					((Activity) context).finish();
					
					Camera_Model camObject=camera_Model.get(pos);
					Intent intent = new Intent(context,CameraAssignedToSubusersActivity.class);
					intent.putExtra("DID", camObject.did);
					
					intent.putExtra(AppKeys.KEY_SUB_USER_TAG, _sUB_USER_DB.sh_user_id+"");
					intent.putExtra(AppKeys.KEY_CAMERA_ID_TAG,camObject.cameraId);
					intent.putExtra(AppKeys.KEY_CAMERA_NAME_TAG,camObject.name);
					context.startActivity(intent);
					
					KisafaApplication.perFormActivityNextTransition(context);
					//overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
					
				}
			});
		
	}
	
	
	
	
	private void callPopupSetting(final int pos) {

		 LayoutInflater layoutInflater = (LayoutInflater)context
		 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		 View popupView = layoutInflater.inflate(R.layout.camera_grid_setting_popup, null);
		
		TextView tv_cancel=(TextView) popupView.findViewById(R.id.tv_Cancel);
		TextView tv_Done=(TextView) popupView.findViewById(R.id.tv_Done);
		
		final ShSwitchView   s_switch=(ShSwitchView)popupView.findViewById(R.id.settingAllowed);
		
		
		final Camera_Model camObject=camera_Model.get(pos);
		
		User_Camera_Model currentUserCamModel=null;
		currentUserCamModel=(User_Camera_Model) cameraId.get(camObject.cameraId);
		
		if(camObject!=null)
			
		{
		
			 
			
			
			
			if(currentUserCamModel!=null)
			{
				if(currentUserCamModel.isAccessCameraSetting.equals("0"))
				{
					lockCheckChange=true;
					s_switch.setOn(false);
					lockCheckChange=false;
				}
				else
				{
					lockCheckChange=true;
					s_switch.setOn(true);
					lockCheckChange=false;
				}
			}
		
		
		}
		

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
		 
		 tv_Done.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					
					
					
						
					User_Camera_Model currentUserCamModel=null;
					currentUserCamModel=(User_Camera_Model) cameraId.get(camObject.cameraId);
					
					
					if(currentUserCamModel!=null)
					{
						
						if(s_switch.isOn())
						{
							if(currentUserCamModel.isAccessCameraSetting.endsWith("0"))
							{
								currentUserCamModel.isAccessCameraSetting="1";
								currentUserCamModel.isSyncedWithServer="0";
								currentUserCamModel.save();
								
								
							}
							
						}
						else
						{
							if(currentUserCamModel.isAccessCameraSetting.endsWith("1"))
							{
								currentUserCamModel.isAccessCameraSetting="0";
								currentUserCamModel.isSyncedWithServer="0";
								currentUserCamModel.save();
								
								
							}
							
						}
					}
					 
					 
				}
			});
		 
		 
		
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



public void settingIconClick(final CheckBox view,final int pos)
{
	
	
	
	
	
	view.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			
			/*if(view.isChecked())
				showProgressDialog("Assigning camera to user", 100);
			else
			showProgressDialog("Unassigning camera to user", 100);*/
			
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
							
							assign_or_unassign_cameras(view,pos);
							
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
				  
				  
				  
				  assign_or_unassign_cameras(view,pos);
				  
			  }
			
			
			
			
			
		}
	});
}
	

private void assign_or_unassign_cameras(final CheckBox view, int pos) {
	

	
	
	
	if(view.isChecked())
	{
	
		
		
		Camera_Model camObject=camera_Model.get(pos);
		
		if(cameraId.get(camObject.cameraId)!=null)
		{
			User_Camera_Model user_camera_Model=(User_Camera_Model) cameraId.get(camObject.cameraId);
			if(user_camera_Model!=null)
			{
			user_camera_Model.modelStatus=AppKeys.KEY_IS_UPDATED;
			
			user_camera_Model.isSyncedWithServer="0";
			user_camera_Model.save();
			
			
			
			
			//showProgressDialog("Assigning camera to user", 100);
			
			   
			
			
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
		}
		else
			{
			
			
			//showProgressDialog("Assigning camera to user", 100);
			
			   
			 final  User_Camera_Model modelToSave=new User_Camera_Model();
			 modelToSave.userCameraId=camObject.did+Manage_DB_Model.getUnitTimeStamp();
			 modelToSave.cameraId=camObject.cameraId;
			 modelToSave.userId=_sUB_USER_DB.sh_user_id;
			 modelToSave.isAccessCameraSetting="1";
			 modelToSave.modelStatus=AppKeys.KEY_IS_CREATED;
			 modelToSave.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
			 modelToSave.save();
			
			 Sync_Data_to_Server_WebServiceCall sync_Data_to_Server_WebServiceCall=new Sync_Data_to_Server_WebServiceCall(context);
			 sync_Data_to_Server_WebServiceCall.setDataLiostner(new Data_Status() {
					
					@Override
					public void onRecieveData(boolean isSuccessfull) {
						
						if(isSuccessfull)
							cameraId.put(modelToSave.cameraId, modelToSave);
						
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
		
	}
	else
	{
		
		
		Camera_Model camObject=camera_Model.get(pos);
		
		User_Camera_Model user_camera_Model=(User_Camera_Model) cameraId.get(camObject.cameraId);
		if(user_camera_Model!=null)
		{
		user_camera_Model.modelStatus=AppKeys.KEY_IS_DELETED;
		user_camera_Model.isSyncedWithServer="0";
		user_camera_Model.isAccessCameraSetting="1";
		user_camera_Model.save();
		}
		
		
		showProgressDialog("Unassign camera to user", 100);
		
		   
		
		
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
	

}

}
