package object.p2pipcam.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activeandroid.query.Delete;
import com.afollestad.materialdialogs.MaterialDialog;

import com.github.mrengineer13.snackbar.SnackBar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.livixa.client.R;
import com.livixa.apacam.client.activity.Add_OR_Edit_UserActivity;
import com.livixa.apacam.client.activity.AssignedCamerasToSubuserActivity;
import com.livixa.apacam.client.activity.RegisterActivityForSubUser;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.deletion.DeletionResponse;
import com.livixa.apacam.client.response.login.LoginResponse;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.client.response.register.RegisterResponse;
import com.livixa.apacam.client.utility.ACache;
import com.livixa.apacam.client.utility.AppConstants;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.dbmodel.Sub_User_Cam_Association;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import object.p2pipcam.gridview.PullToRefreshAdapterViewBase;
import retrofit2.Call;

public class Sub_User_with_assigned_camera_List_Adapter extends BaseAdapter 
{
	
	Context context;
	private LayoutInflater inflater = null;
	List<User_Camera_Model> user_Camera_Model_List;
	
	private Map<String, String> map;
	
	private ProgressDialog mProgressDialog;
	
	int currentSelectedSubUserPosition=0;
	
	View listView, emptyImg;
	
	boolean isDeleteDialogeShowing=false;
	
	SUB_USER_DB _sUB_USER_DB;
	
	String cam_Name="";
	
	String User_Name="";

	String cam_DID="";
	
	HashMap<String, Object> subUsers;
	
	public Sub_User_with_assigned_camera_List_Adapter(Context context,List<User_Camera_Model> user_Camera_Model_List,HashMap<String, Object> subUsers,String cam_Name,String User_Name,String cam_DID)
	{
		this.user_Camera_Model_List=user_Camera_Model_List;
		this.context=context;
		this._sUB_USER_DB=_sUB_USER_DB;
		this.cam_Name=cam_Name;
		this.User_Name=User_Name;
		this.cam_DID=cam_DID;
		this.subUsers=subUsers;
	}

	@Override
	public int getCount() {
		 
		return user_Camera_Model_List.size();
	}

	@Override
	public Object getItem(int position) {
		 
		return user_Camera_Model_List.get(position);
	}

	@Override
	public long getItemId(int position) {
		 
		return position;
	}

	
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		this.inflater = LayoutInflater.from(context);
		convertView= inflater.inflate(R.layout.assigned_user_to_camera_list_item, null);
		
		TextView tv_subUserName=(TextView) convertView.findViewById(R.id.tv_subUserName);
		TextView camName=(TextView) convertView.findViewById(R.id.camName);
		TextView camID=(TextView) convertView.findViewById(R.id.camID);
		TextView camSettingStatus=(TextView) convertView.findViewById(R.id.camSettingStatus);
		
		User_Camera_Model obj=user_Camera_Model_List.get(position);
		if(position==0)
		tv_subUserName.setText(User_Name);
		else
		{
			SUB_USER_DB sUB_USER_DB=(SUB_USER_DB) subUsers.get(obj.userId);
			tv_subUserName.setText(sUB_USER_DB.username);	
		}
		camName.setText(cam_Name);
		camID.setText(cam_DID);
		if(obj.isAccessCameraSetting.equals("1"))
		{
		camSettingStatus.setText(context.getString(R.string.SettingsAllowed));
		}
		else
		{
			camSettingStatus.setText(context.getString(R.string.SettingsNotAllowed));
		}
		
		return convertView;
	}

	
	
}
