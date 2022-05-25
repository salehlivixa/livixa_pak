package object.p2pipcam.adapter;

import java.util.ArrayList;
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
import android.widget.Toast;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity;
import com.livixa.apacam.client.activity.Moods_RoomAndSwitchesTCPActivity;
import com.livixa.apacam.client.activity.RoomActivity;
import com.livixa.apacam.client.activity.RoomAndSwitchesTCPActivity;
import com.livixa.apacam.client.activity.RoomSwitchesActivity;
import com.livixa.apacam.client.activity.MoodsRoomWithSwitchesActivity;
import com.livixa.apacam.client.activity.SettingsActivity;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.dbmodel.User_Room_Model;
import com.livixa.client.R;

@SuppressLint("ViewHolder")
public class Moods_ViewRoomsGridViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	
	
	Context context;
	
	
	private List<Room_Model> mRoomModelList;
	private View emptyView , listView;


	private LayoutInflater mInflater;

	
	
	protected boolean isDeleteDialogeShowing;


	private ImageLoader imageLoader;

	public Moods_ViewRoomsGridViewAdapter(Context context,View emptyView ,View listView) {
		inflater = LayoutInflater.from(context);
		this.context=context;
		
		
		this.mInflater = LayoutInflater.from(context);
		this.emptyView=emptyView;
		this.listView=listView;
		
		
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
			convertView = inflater.inflate(R.layout.rooms_view_grid_view_item, null);
			gridViewItemHolder.roomName=(TextView) convertView.findViewById(R.id.iv_bedName);
			
			gridViewItemHolder.placeHolder=(ImageView) convertView.findViewById(R.id.iv_img_placeHolder);
			gridViewItemHolder.tileLayout=(LinearLayout) convertView.findViewById(R.id.tileLayout);
			
			convertView.setTag(gridViewItemHolder);
		}
		else
		{
			gridViewItemHolder=(GridViewItemHolder) convertView.getTag();
		}
		
		
		
		
		onItemClick(gridViewItemHolder.tileLayout,pos);
		
		
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
		
		
		return convertView;
		 
		
	}
	
	
	
	public class GridViewItemHolder
	
	{
		
		
		public ImageView   placeHolder;
		
		public TextView    roomName;
		
		public LinearLayout tileLayout;
	}
	
	
	
	
	
	public void onItemClick(final View view,final int pos)
	{
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				Room_Model roomModel=mRoomModelList.get(pos);
				
				Intent intent = new Intent(context,MoodsRoomWithSwitchesActivity.class);
				intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, roomModel.room_id);
				intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, roomModel.title);
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
					.where("User_Room_Model.userId = ? AND User_Room_Model.modelStatus!= ? AND Room_Model.modelStatus!= ?   ORDER BY Room_Model.title" ,AppPreference.getValue(context, AppKeys.KEY_USER_ID), AppKeys.KEY_IS_DELETED, AppKeys.KEY_IS_DELETED)
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
