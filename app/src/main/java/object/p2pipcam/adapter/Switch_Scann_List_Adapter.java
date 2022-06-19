package object.p2pipcam.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import com.activeandroid.Model;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.livixa.apacam.client.activity.ShSwitchView;
import com.livixa.client.R;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity.SwitchTypes;
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
import com.livixa.apacam.client.swipelistview.SwipeRevealLayout;
import com.livixa.apacam.client.utility.ACache;
import com.livixa.apacam.client.utility.AppConstants;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.dbmodel.Camera_Model;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import object.p2pipcam.gridview.PullToRefreshAdapterViewBase;
import retrofit2.Call;

public class Switch_Scann_List_Adapter extends BaseAdapter 
{
	
	private Context mContext;
	private LayoutInflater  mInflater;
    private List<Switch_Model>	 mSwitchModelList;


   
    private View emptyView , listView;
   
    private List<Room_Model> mRoomModelList;
    
    Map<String, Integer> mMapSwitchesToResourse = new HashMap<String, Integer>();
    
    private Map<String, String> mMapSwitchesToRooms=new  HashMap<String, String>();
   
	
	public Switch_Scann_List_Adapter(Context mContext,View emptyView ,View listView)
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
		
		Switch_Model     switch_Model=mSwitchModelList.get(position);
		
		   
			
			
			if(convertView==null)
			{
				convertView= mInflater.inflate(R.layout.scan_switch_listview_item, null);
				switchViewHolder=new SwitchViewHolder();
				switchViewHolder.switchIcon=(ImageView) convertView.findViewById(R.id.img_icon_switch);
				switchViewHolder.switchName=(TextView) convertView.findViewById(R.id.tv_switchName);
				switchViewHolder.switchId=(TextView) convertView.findViewById(R.id.switchId);
				switchViewHolder.switchIp=(TextView) convertView.findViewById(R.id.switchIP);
				switchViewHolder.IpStatus=(ShSwitchView) convertView.findViewById(R.id.ipStatus);
				switchViewHolder.IpStatus.setOn(false);
				switchViewHolder.switchAssignedTo=(TextView) convertView.findViewById(R.id.switchAssignedTo);
			}
			
			else
			{
				switchViewHolder=(SwitchViewHolder) convertView.getTag();
			}
			
			
			try
			{
				if(switch_Model.title==null || switch_Model.title.length()==0)
				{
					switchViewHolder.switchName.setText(Add_Edit_SwitchActivity.getSwitchTitle(switch_Model.mac_address));
				}
				else
				{
				  switchViewHolder.switchName.setText(switch_Model.title);
				}
				
			}catch(Exception ex)
			{
				ex.toString();
			}
			
			switchViewHolder.switchId.setText(switch_Model.mac_address);
			
			if(switch_Model.ip_address.equals(""))
			{
				switchViewHolder.switchIp.setText("N/A");
			    switchViewHolder.IpStatus.setOn(true);
			}
			else
			{
				switchViewHolder.switchIp.setText(switch_Model.ip_address);
				switchViewHolder.IpStatus.setOn(true);
			}
			
			
			if(switch_Model.room_id.equals(""))
				switchViewHolder.switchAssignedTo.setText(mContext.getResources().getString(R.string.Assignedto)+ " N/A");
			else
				switchViewHolder.switchAssignedTo.setText(mContext.getResources().getString(R.string.Assignedto)+ mMapSwitchesToRooms.get(switch_Model.room_id));
			
			try
			{
			switchViewHolder.switchIcon.setImageResource(mMapSwitchesToResourse.get(switch_Model.type));
			}catch(Exception ex)
			{
				ex.toString();
			}
			
			
		
			
			convertView.setTag(switchViewHolder);
		
		
		return convertView;
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
		public ShSwitchView IpStatus;
		public TextView  switchName;
		public TextView  switchIp;
		public TextView  switchId;
		
		public TextView  switchAssignedTo;
		
	}
	
	
	private List<Switch_Model> fetchSwitchesFromDb()
	{
		List<Switch_Model> switchModelList=null;
		try
		{
			switchModelList = new Select().from(Switch_Model.class).where("Switch_Model.model_status != ?   ORDER BY Switch_Model.type", AppKeys.KEY_IS_DELETED).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return switchModelList;
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
	
}
