package com.livixa.apacam.client.response.isdatasyncedwithserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activeandroid.query.Select;
import com.github.mrengineer13.snackbar.SnackBar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.dbmodel.Sub_User_Cam_Association;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.R;
import com.livixa.client.R.style;
import retrofit2.Call;
import retrofit2.Response;

public class Sync_Sub_User_Cam_Association  implements ServerConnectListener{

	
	 User_Camera_Model  modelToSave;
	
	private ProgressDialog mProgressDialog;
	
	public Sync_Sub_User_Cam_Association(Context cntxt)
	{
		this.cntxt=cntxt;
	}
	Context cntxt;
	Map<String, String> map;
	
	
	
	SubuserAccociationStatus subuserAccociationStatus;
	
	
	
	public void makeWebServiceCall(String user_camera_id,String camera_id,String user_id,String is_access_camera_settings,String model_status)
	{
		
		if(model_status.equals(AppKeys.KEY_IS_DELETED))
		showProgressDialog("Unassign camera to user", 100);
		else
			showProgressDialog("Assigning camera to user", 100);	
		
		
			ApiService service = KisafaApplication.getRestClient().getApiService();
			
			
			
			modelToSave =	 new Select() .from(User_Camera_Model.class).where("User_Camera_Model.userCameraId = ?",user_camera_id).executeSingle();
			
			if(modelToSave==null)
			{
			 modelToSave=new User_Camera_Model();
			 modelToSave.userCameraId=user_camera_id;
			 modelToSave.cameraId=camera_id;
			 modelToSave.userId=user_id;
			 modelToSave.isAccessCameraSetting=is_access_camera_settings;
			 modelToSave.modelStatus=model_status;
			
			  map=new HashMap<String, String>();
			  map.put("session", AppPreference.getValue(cntxt,AppKeys.KEY_SESSION));
			  map.put("user_camera_id",user_camera_id);
			  map.put("camera_id",camera_id);
			  map.put("user_id", user_id);
			  map.put("is_access_camera_settings",is_access_camera_settings);
			  map.put("model_status",model_status);
			  
			    Call<Association_Response> call = service.sub_User_Cam_Asso(map);
				
				call.enqueue(new RestCallback<Association_Response>(this, ServerCodes.ServerRequestCodes.SYNCED_ASSOCIATION_DATA_REQUEST_CODE,cntxt));
			}
			else
			{
				
				 
				 if (mProgressDialog != null) {
						mProgressDialog.hide();
					}
			}
		
	}
	
	
	

	@Override
	public void onSuccess(ServerResponse response,String raw) {
		
		if (mProgressDialog != null) {
			mProgressDialog.hide();
		}	
		
		if (response.getRequestCode() == ServerCodes.ServerRequestCodes.SYNCED_ASSOCIATION_DATA_REQUEST_CODE) {

			
			Association_Response association_Response = (Association_Response) response;

			if (association_Response.getShMeta().getShErrorCode().equalsIgnoreCase("0")) {
				
				modelToSave.isSyncedWithServer="1";
				modelToSave.save();
				
				if(subuserAccociationStatus!=null)
				{
					subuserAccociationStatus.onSuccessofCreation(modelToSave);
				}
			}
			else
			{
				try
				{
				
				onFailure(association_Response.getShMeta().getShErrorCode());
				
				}catch(Exception ex){}
			}
		}
				
	}

	@Override
	public void onFailure(ServerResponse response) {
		if (mProgressDialog != null) {
			mProgressDialog.hide();
		}	
		try
		{
		Association_Response association_Response = (Association_Response) response;
		onFailure(association_Response.getShMeta().getShErrorCode());
		}catch(Exception ex){}
	}

public void onFailure(String retrofitError) {
		
		new SnackBar.Builder((Activity) cntxt).withMessage(retrofitError).withDuration(SnackBar.SHORT_SNACK).show();
	}

private void showProgressDialog(String text, int progress) {
	
	mProgressDialog = new ProgressDialog(cntxt, style.progressDialogTheme);
	mProgressDialog.setMessage(text);
	mProgressDialog.setIndeterminate(false);
	mProgressDialog.setMax(progress);
	
	mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	mProgressDialog.setCancelable(false);
	mProgressDialog.show();
}



public interface SubuserAccociationStatus
{
	public void onSuccessofCreation( User_Camera_Model  modelToSave);
}


public void setSubuserAccociationStatus(SubuserAccociationStatus listner)
{
	subuserAccociationStatus=listner;
}

}
