package object.p2pipcam.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activeandroid.query.Select;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity.SwitchTypes;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.client.R;

@SuppressLint("ViewHolder")
public class RoomSwitchesGridViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	
	
	Context context;
	
	
	
	private View emptyView , listView;


	private LayoutInflater mInflater;

	private Room_Model mRoomModel;
	private List<Switch_Model>	 mSwitchModelList;
	
	protected boolean isDeleteDialogeShowing;
	
	Map<String, Integer> mMapSwitchesToResourse = new HashMap<String, Integer>();
	
	

	public RoomSwitchesGridViewAdapter(Context context,View emptyView ,View listView,Room_Model mRoomModel) {
		inflater = LayoutInflater.from(context);
		this.context=context;
		
		
		this.mInflater = LayoutInflater.from(context);
		this.emptyView=emptyView;
		this.listView=listView;
		this.mRoomModel=mRoomModel;
		
		
		mSwitchModelList=fetchAssignedSwitchesFromDb(mRoomModel.room_id);
		
		
		
		if(mSwitchModelList==null)
		{
			mSwitchModelList=new ArrayList<>();
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
	public Object getItem(int arg0) 
	{
		return mSwitchModelList.get(arg0);
	}
	
	public Switch_Model removeItem(int arg0) 
	{
		return mSwitchModelList.remove(arg0);
	}
	

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup arg2)
	{
		
		HViewHolder holder;
        Switch_Model switch_Model=mSwitchModelList.get(pos);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.room_switches_listitem, null);
            holder = new HViewHolder();
            
            
            holder.img = (ImageView) convertView.findViewById(R.id.img_icon_switch);
            holder.switchName = (TextView) convertView.findViewById(R.id.switchName);
            holder.switchId = (TextView) convertView.findViewById(R.id.switchId);
            
            convertView.setTag(holder);

        } else {
            holder = (HViewHolder) convertView.getTag();
        }
        
        
        holder.img.setImageResource(mMapSwitchesToResourse.get(switch_Model.type));
        if(switch_Model.title!=null && switch_Model.title.length() >0)
        holder.switchName.setText(switch_Model.title);
        else
        holder.switchName.setText(Add_Edit_SwitchActivity.getSwitchTitle(switch_Model.mac_address));	
        
        holder.switchId.setText("ID: "+switch_Model.mac_address);
		
		
		
		return convertView;
		 
		
	}
	
	
	
	class HViewHolder {
	    ImageView img;
	    TextView  switchName;
	    TextView  switchId;
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
	
	
	public static Bitmap bytesToBitmap(byte[] array)
	{
		return BitmapFactory.decodeByteArray(array, 0, array.length);
	}
	
	
	
	public void assignWwitchToThisRoom(Switch_Model switchModel)
	{
		
		switchModel.room_id=mRoomModel.room_id;
		switchModel.save();
		if(mSwitchModelList!=null)
		{
		   mSwitchModelList.add(switchModel);
		   
		   notifyDataSetChanged();
		   
		}
		
		isListEmptyThenShowEmptyView();
		
	}
	
	
	
	public List<Switch_Model>  getAddedSwitchesFromGrid()
	{
		
		
		return mSwitchModelList;
		
	}
	
	
	
	private List<Switch_Model> fetchAssignedSwitchesFromDb(String roomId)
	{
		List<Switch_Model> switchModelList=null;
		try
		{
                  switchModelList = new Select().from(Switch_Model.class)
					.where("Switch_Model.model_status != ? AND Switch_Model.room_id = ?    ORDER BY Switch_Model.type", AppKeys.KEY_IS_DELETED,roomId)
					.execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return switchModelList;
	}
	
}
