package object.p2pipcam.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
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
import com.livixa.apacam.dbmodel.Camera_Model;
import com.livixa.apacam.dbmodel.ESP_Result_Model;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Sub_User_Cam_Association;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model_Relation;
import com.livixa.apacam.dbmodel.User_Room_Model;
import object.p2pipcam.adapter.RoomAssignedtoUsers_List_Adapter.RoomUser;
import object.p2pipcam.gridview.PullToRefreshAdapterViewBase;
import retrofit2.Call;

public class ESP_Result_List_Adapter extends BaseAdapter 
{
	
	Context context;
	private LayoutInflater inflater = null;
	
	
	List<ESP_Result_Model> espList=null;
	
	
	
	public ESP_Result_List_Adapter(Context context)
	{
		
		this.context=context;
		
		
		espList=getESP_Result();
		
		
		if(espList==null || espList.size()==0)
		{
			espList=new ArrayList<>();
			
			ESP_Result_Model esp_Result_Model=new ESP_Result_Model();
			
			esp_Result_Model.hostName="";
			esp_Result_Model.BSSID="N/A";
			esp_Result_Model.IpAddress="N/A";
			esp_Result_Model.isSuccessfull="NO";
			
			
			espList.add(esp_Result_Model);
		}
		
	}

	@Override
	public int getCount() {
		 
		return espList.size();
	}

	@Override
	public Object getItem(int position) {
		 
		return espList.get(position);
	}

	@Override
	public long getItemId(int position) {
		 
		return position;
	}

	
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		this.inflater = LayoutInflater.from(context);
		convertView= inflater.inflate(R.layout.esp_result_list_item, null);
		
		ESP_Result_Model esp_Result_Model=espList.get(position);
		
		
		
		
		
		TextView camName=(TextView) convertView.findViewById(R.id.camName);
		TextView camID=(TextView) convertView.findViewById(R.id.camID);
		TextView camSettingStatus=(TextView) convertView.findViewById(R.id.camSettingStatus);
		
		if(esp_Result_Model.isSuccessfull.toLowerCase().equals("on"))
		{
		camName.setText(context.getString(R.string.SuccessNo));
		}
		else
		{
			camName.setText(context.getString(R.string.SuccessYes));
		}
		
		camID.setText(esp_Result_Model.BSSID);
		
		camSettingStatus.setText(esp_Result_Model.IpAddress);
		
		return convertView;
	}

	private  List<ESP_Result_Model> getESP_Result() {
		//AND SUB_USER_DB.modelStatus!= ?
				
		List<ESP_Result_Model> tempModel=null;
				
				try {

					tempModel = new Select().distinct().from(ESP_Result_Model.class).execute();

					
					
					
					return tempModel;

				} catch (Exception ex) {
					ex.toString();
				}
				return tempModel;

			}

	
}
