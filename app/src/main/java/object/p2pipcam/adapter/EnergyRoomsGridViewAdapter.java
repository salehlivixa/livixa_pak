package object.p2pipcam.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.activeandroid.query.Select;
import com.afollestad.materialdialogs.MaterialDialog;
import com.livixa.apacam.client.network.AppWebServices;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity;
import com.livixa.apacam.client.activity.EnergyRoomAndSwitchesActivity;
import com.livixa.apacam.client.activity.RoomActivity;
import com.livixa.apacam.client.activity.RoomAndSwitchesTCPActivity;
import com.livixa.apacam.client.activity.RoomSwitchesActivity;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.response.tariff_energy.Sh_Room_Watage_result;
import com.livixa.apacam.client.response.tariff_energy.Sh_Watage_result;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons.CustomDialogueTwoButtonsClickListner;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.dbmodel.User_Room_Model;
import com.livixa.client.R;
import object.p2pipcam.adapter.ViewRoomsGridViewAdapter.GridViewItemHolder;

@SuppressLint("ViewHolder")
public class EnergyRoomsGridViewAdapter extends BaseAdapter {
private LayoutInflater inflater;
	
	
	Context context;
	
	
	private List<Room_Model> mRoomModelList;
	private View emptyView , listView;


	private LayoutInflater mInflater;

	
	
	protected boolean isDeleteDialogeShowing;
	
	
	private ArrayList<Sh_Room_Watage_result> roomResult; 
	
	
	private HashMap<String, Sh_Room_Watage_result> map=new  HashMap<>();
	
	private String watageUnit, costUnit;


	private ImageLoader imageLoader;
	
	
	Sh_Watage_result watageResult;
	
	

	public EnergyRoomsGridViewAdapter(Context context,View emptyView ,View listView,ArrayList<Sh_Room_Watage_result> roomResult,String watageUnit,String costUnit,Sh_Watage_result watageResult) {
		inflater = LayoutInflater.from(context);
		this.context=context;
		
		this.watageResult=watageResult;
		
		
		this.mInflater = LayoutInflater.from(context);
		this.emptyView=emptyView;
		this.listView=listView;
		this.roomResult=roomResult;
		this.watageUnit=watageUnit;
		this.costUnit=costUnit;
		
		
		if(roomResult!=null)
		{
			for(int i=0 ;i<roomResult.size();i++)
			{
				
				map.put(roomResult.get(i).getSh_room_id(), roomResult.get(i));
			}
		}
		
		if(AppPreference.getValue(context,AppKeys.KEY_IS_SUB_USER)!=null && AppPreference.getValue(context,AppKeys.KEY_IS_SUB_USER).equals("1"))
		{
			mRoomModelList=fetchRoomsFromDbSubUserCase();
		}
		else
		{
		   mRoomModelList=ViewRoomsGridViewAdapter.fetchRoomsFromDb(context);
		}
		
		if(mRoomModelList==null)
		{
			mRoomModelList=new ArrayList<>();
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
			convertView = inflater.inflate(R.layout.energy_rooms_grid_view_item, null);
			gridViewItemHolder.roomName=(TextView) convertView.findViewById(R.id.iv_bedName);
			gridViewItemHolder.roomCostUsage=(TextView) convertView.findViewById(R.id.roomCostUsage);
			gridViewItemHolder.roomEnergyUsage=(TextView) convertView.findViewById(R.id.roomEnergyUsage);
			
			gridViewItemHolder.placeHolder=(ImageView) convertView.findViewById(R.id.iv_img_placeHolder);
			gridViewItemHolder.tileLayout=(LinearLayout) convertView.findViewById(R.id.tileLayout);
		}
		else
		{
			gridViewItemHolder=(GridViewItemHolder) convertView.getTag();
		}
		
		
		
		
		onItemClick(gridViewItemHolder.tileLayout,pos);
		
		
		Sh_Room_Watage_result roomhash=map.get(roomModel.room_id);
		
		
		if(roomhash!=null)
		{
		gridViewItemHolder.roomCostUsage.setText(roomhash.getSh_price()+" "+costUnit);
		gridViewItemHolder.roomEnergyUsage.setText(roomhash.getSh_wattage()+" "+watageUnit);
		}
		
		gridViewItemHolder.roomName.setText(roomModel.title);
		
		
		
		if(roomModel.picture!=null && roomModel.picture.length >0)
		{
			 gridViewItemHolder.placeHolder.setImageBitmap(bytesToBitmap(roomModel.picture));
		}
			else if(roomModel.pictureURL!=null && roomModel.pictureURL.trim().length() > 0 )
		{
			
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
		
		
		public ImageView   placeHolder;
		
		public TextView roomName;
		public TextView roomCostUsage;
		public TextView roomEnergyUsage;
		
		public LinearLayout tileLayout;
	}
	
	
	
	
	
	public void onItemClick(final View view,final int pos)
	{
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				Room_Model roomModel=mRoomModelList.get(pos);
				
				Sh_Room_Watage_result roomhash=map.get(roomModel.room_id);
				
				Intent intent = new Intent(context,EnergyRoomAndSwitchesActivity.class);
				intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, roomModel.room_id);
				intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, roomModel.title);
				
				if(roomhash!=null)
				{
				   intent.putExtra("RoomRelatedSwitches", roomhash);
				}
				
				if(watageResult!=null)
				{
					
					 intent.putExtra("energy_result_to_load_again", watageResult);
				}
				
				context.startActivity(intent);
				KisafaApplication.perFormActivityNextTransition(context);
				((Activity) context).finish();
			}
		});
	}
	
	
	private List<Room_Model> fetchRoomsFromDb()
	{
		List<Room_Model> roomModelList=null;
		try
		{
			roomModelList = new Select().from(Room_Model.class).where("User_Room_Model.userId = ? AND Room_Model.modelStatus != ?     ORDER BY Room_Model.title",AppPreference.getValue(context, AppKeys.KEY_USER_ID), AppKeys.KEY_IS_DELETED).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return roomModelList;
	}
	
	
	private List<Room_Model> fetchRoomsFromDbSubUserCase()
	{
		List<Room_Model> roomModelList=null;
		try
		{
			roomModelList = new Select().distinct().from(Room_Model.class).join(User_Room_Model.class)
					.on("User_Room_Model.room_id=Room_Model.room_id")
					.where("User_Room_Model.userId = ? AND User_Room_Model.modelStatus!= ? AND Room_Model.modelStatus!= ?    ORDER BY Room_Model.title" ,AppPreference.getValue(context, AppKeys.KEY_USER_ID), AppKeys.KEY_IS_DELETED, AppKeys.KEY_IS_DELETED)
					.execute();
			
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
	
	
	
}
