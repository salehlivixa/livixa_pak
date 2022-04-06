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
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Sub_User_Cam_Association;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model_Relation;
import com.livixa.apacam.dbmodel.User_Room_Model;
import object.p2pipcam.gridview.PullToRefreshAdapterViewBase;
import retrofit2.Call;

public class RoomAssignedtoUsers_List_Adapter extends BaseAdapter 
{
	
	Context context;
	private LayoutInflater inflater = null;
	
	
	
	
	private ProgressDialog mProgressDialog;
	
	int currentSelectedSubUserPosition=0;
	
	View listView, emptyImg;
	
	boolean isDeleteDialogeShowing=false;
	
	RoomUser roomUserRelations;
	
	
	String roomTitle="";
	
	public RoomAssignedtoUsers_List_Adapter(Context context,String roomId,String roomTitle)
	{
		
		this.context=context;
		
		roomUserRelations= getAllUsersofThisRoom(roomId);
		this.roomTitle=roomTitle;
		// add default record for main user
		
		
		
	}

	@Override
	public int getCount() {
		 
		return roomUserRelations.roomsList.size() + 1;
	}

	@Override
	public Object getItem(int position) {
		 
		return roomUserRelations.roomsList.get(position);
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
		
		
		
		if(position==0)
		{
			
			tv_subUserName.setText("");	
			
			camName.setText(context.getString(R.string.Username)+" " + AppPreference.getValue(context, AppKeys.KEY_USER_NAME));
			camID.setText(context.getString(R.string.Email)+" " +  AppPreference.getValue(context, AppKeys.KEY_USER_EMAIL));
			camSettingStatus.setText(context.getString(R.string.RoomTitle)+" "+ roomTitle);
		}
		else
		{
			User_Room_Model objUserRoom=roomUserRelations.userRoomsList.get(position-1);
			
			Room_Model objRoom=roomUserRelations.roomsList.get(position-1);
			
			
			SUB_USER_DB objSubUser=roomUserRelations.subUserList.get(position-1);
			
			
			
			tv_subUserName.setText("");	
			
			camName.setText(context.getString(R.string.Username)+" "+ objSubUser.username);
			camID.setText(context.getString(R.string.Email)+" "  + objSubUser.email);
			camSettingStatus.setText(context.getString(R.string.RoomTitle)+" " + objRoom.title);
		
		}
		return convertView;
	}

	
	class  RoomUser
	{
		public List<Room_Model> roomsList= new ArrayList<>();
		
		public List<SUB_USER_DB> subUserList= new ArrayList<>();
		
		public List<User_Room_Model> userRoomsList= new ArrayList<>();
	}
	
	private  RoomUser getAllUsersofThisRoom(String roomId) {
//AND SUB_USER_DB.modelStatus!= ?
		RoomUser user_Room_Model_Relation = new RoomUser();
		List<Room_Model> tempModel=null;
		List<User_Room_Model> tempUserRoomModel=null;
		List<SUB_USER_DB> sunUserModel=null;
		try {

			tempModel = new Select().distinct().from(Room_Model.class).join(User_Room_Model.class)
					.on("User_Room_Model.room_id=Room_Model.room_id").join(SUB_USER_DB.class).on("SUB_USER_DB.sh_user_id=User_Room_Model.userId")
					.where("Room_Model.room_id = ? AND User_Room_Model.modelStatus!= ? AND Room_Model.modelStatus!= ?" ,roomId, AppKeys.KEY_IS_DELETED, AppKeys.KEY_IS_DELETED)
					.execute();

			tempUserRoomModel =  new Select().distinct().from(User_Room_Model.class).join(SUB_USER_DB.class)
					.on("SUB_USER_DB.sh_user_id=User_Room_Model.userId").join(Room_Model.class)
					.on("User_Room_Model.room_id=Room_Model.room_id")
					.where("Room_Model.room_id = ? AND User_Room_Model.modelStatus!= ? AND Room_Model.modelStatus!= ? " ,roomId, AppKeys.KEY_IS_DELETED, AppKeys.KEY_IS_DELETED)
					.execute();
			
			sunUserModel =  new Select().distinct().from(SUB_USER_DB.class).join(User_Room_Model.class)
					.on("SUB_USER_DB.sh_user_id=User_Room_Model.userId").join(Room_Model.class)
					.on("User_Room_Model.room_id=Room_Model.room_id")
					.where("Room_Model.room_id = ? AND User_Room_Model.modelStatus!= ? AND Room_Model.modelStatus!= ? " ,roomId, AppKeys.KEY_IS_DELETED, AppKeys.KEY_IS_DELETED)
					.execute();

			user_Room_Model_Relation.roomsList = tempModel;
			user_Room_Model_Relation.userRoomsList = tempUserRoomModel;
			user_Room_Model_Relation.subUserList=sunUserModel;
			
			
			return user_Room_Model_Relation;

		} catch (Exception ex) {
			ex.toString();
		}
		return user_Room_Model_Relation;

	}

}
