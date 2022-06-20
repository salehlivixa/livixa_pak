package object.p2pipcam.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activeandroid.query.Select;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity.SwitchTypes;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.swipelistview.SwipeRevealLayout;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons.CustomDialogueTwoButtonsClickListner;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.dbmodel.Tariff_Model;
import com.livixa.client.R;

public class Tariff_List_Adapter extends BaseAdapter implements ServerConnectListener
{
	
	private Context mContext;
	private LayoutInflater  mInflater;
    private List<Tariff_Model>	 mTariffModelList;
   
    private View emptyView , listView;
    private boolean isDeleteDialogeShowing;
    
   
	
	public Tariff_List_Adapter(Context mContext,View emptyView ,View listView)
	{
		
		this.mContext=mContext;
		this.mInflater = LayoutInflater.from(mContext);
		this.emptyView=emptyView;
		this.listView=listView;
		
		mTariffModelList = fetchTriffesFromDb();
		
		
		
		if(mTariffModelList==null)
		{
			mTariffModelList=new ArrayList<>();
			
		}
		
		
		
		isListEmptyThenShowEmptyView();
		
		
		
		
	}

	@Override
	public int getCount() {
		
		return mTariffModelList.size();
	}

	@Override
	public Object getItem(int position) {
		
		return mTariffModelList.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		TariffViewHolder tariffViewHolder=null;
		
		Tariff_Model    tariff_Model=mTariffModelList.get(position);
		
		   
			

		if(convertView==null)
		{
			convertView= mInflater.inflate(R.layout.tariff_listview_item, null);
			tariffViewHolder=new TariffViewHolder();
			
			tariffViewHolder.serialNo=(TextView) convertView.findViewById(R.id.serialNo);
			tariffViewHolder.lowwerLimit=(TextView) convertView.findViewById(R.id.lowerLimit);
			tariffViewHolder.upperLimit=(TextView) convertView.findViewById(R.id.upperLimit);
			tariffViewHolder.priceView=(TextView) convertView.findViewById(R.id.tariffPrice);
			tariffViewHolder.deletebutton=(TextView) convertView.findViewById(R.id.tv_tariffDelete);
			tariffViewHolder.swipeRevealLayout=(SwipeRevealLayout) convertView.findViewById(R.id.switchesListItemRoot);
			convertView.setTag(tariffViewHolder);
		}
		
		else
		{
			tariffViewHolder=(TariffViewHolder) convertView.getTag();
		}
		
		
		if(mTariffModelList.size()==position+1)
		{
			tariffViewHolder.swipeRevealLayout.setLockDrag(false);
			
			
		}
		else
		{
			tariffViewHolder.swipeRevealLayout.setLockDrag(true);
		}
		
		
		
		tariffViewHolder.swipeRevealLayout.close(true);
		
		tariffViewHolder.serialNo.setText(""+(position+1));
		tariffViewHolder.lowwerLimit.setText(tariff_Model.lower_limit);
		
		try
		{
			String upperValue= tariff_Model.upper_limit.equals("-1") ? mContext.getString(R.string.INFINITE) : tariff_Model.upper_limit;
		tariffViewHolder.upperLimit.setText(upperValue);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		tariffViewHolder.priceView.setText(tariff_Model.price);
		
		onDeletetariff(tariffViewHolder.deletebutton,position,tariffViewHolder.swipeRevealLayout);
			
		
			
			
		
		
		return convertView;
	}

	@Override
	public void onSuccess(ServerResponse response,String raw) {
		
		
	}

	@Override
	public void onFailure(ServerResponse response) {
		 
		
	}
	
	
	public void addSwitchtoList(Tariff_Model tariffModel)
	{
		if(mTariffModelList==null)
		{
			mTariffModelList=new ArrayList<>();
		}
		
		mTariffModelList.add(tariffModel);
		
		isListEmptyThenShowEmptyView();
		
		notifyDataSetChanged();
	}
	
	
	
	public void clearList()
	{
		if(mTariffModelList==null)
		{
			mTariffModelList=new ArrayList<>();
		}
		
		mTariffModelList.clear();
		
		isListEmptyThenShowEmptyView();
		
		notifyDataSetChanged();
	}
	
	class TariffViewHolder
	{
		
		public TextView       serialNo;
		public TextView  lowwerLimit;
		public TextView  upperLimit;
		public TextView  priceView;
		public TextView  deletebutton;
		public SwipeRevealLayout swipeRevealLayout;
	}
	
	
	public List<Tariff_Model> fetchTriffesFromDb()
	{
		List<Tariff_Model> tariffModelList=null;
		try
		{
			tariffModelList = new Select().from(Tariff_Model.class).where("Tariff_Model.model_status != ? ORDER BY lower_limit", AppKeys.KEY_IS_DELETED).execute();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return tariffModelList;
	}

	
	
	private boolean deleteTariff(String tariffId)
	{
		 Tariff_Model tariffModel=null;
		try
		   {
			tariffModel = new Select().from(Tariff_Model.class).where("tariff_id = ?",tariffId.trim()).executeSingle();
	            
		   }catch(Exception ex){}
		
		if(tariffModel!=null)
		{
			
			
			tariffModel.isSyncedWithServer="0";
			tariffModel.model_status=AppKeys.KEY_IS_DELETED;
			tariffModel.save();
			
			
			
			return true;
		}
		
		return false;
	}
	
	
	private void onDeletetariff(View tView,final int pos,final SwipeRevealLayout swipeRevealLayout)
	{
		tView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				if(!isDeleteDialogeShowing)
				{
				
								isDeleteDialogeShowing=true;
								
								
								
								
				
						
						
						
						
						final CustomAlertDialogueTwoButtons cusTwo=new CustomAlertDialogueTwoButtons(mContext, mContext.getString(R.string.deletetariff));
						
						cusTwo.setListner(new CustomDialogueTwoButtonsClickListner() {
							
							@Override
							public void onCustomDialoguePositiveClick() {
								
								
								
								try
								{

									Tariff_Model tempModel=mTariffModelList.remove(pos);
								
								
								if(deleteTariff(tempModel.tariff_id))
								{
									
									isListEmptyThenShowEmptyView();
									notifyDataSetChanged();
								}
								}catch(Exception ex)
								{
									
								}
								
								cusTwo.dismiss();
								swipeRevealLayout.close(false);
								isDeleteDialogeShowing=false;
								
							}
							
							@Override
							public void onCustomDialogueNegativeClick() {
								
								
								cusTwo.dismiss();
								
								swipeRevealLayout.close(false);
								isDeleteDialogeShowing=false;
								
							}
						});
						
						
						
						cusTwo.show();
						
						/*MaterialDialog dialog = new MaterialDialog.Builder(mContext)
								.content("Do do want to delete switch with MAC="+tempSwitchModel.mac_address)
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
										
										
										Switch_Model tempSwitchModel=mSwitchModelList.remove(pos);
										
										
										if(deleteswitch(tempSwitchModel.switch_id))
										{
											
											isListEmptyThenShowEmptyView();
											notifyDataSetChanged();
										}
										
									}
								}).build();
						dialog.show();
						
						dialog.setOnDismissListener(new OnDismissListener() {
							
							
		
							
		
							@Override
							public void onDismiss(DialogInterface dialog)
							{
								
							}
						});*/
						
				
				}
				
			}
		});
	}
	
	
	private void isListEmptyThenShowEmptyView()
	{
		if(mTariffModelList.size()==0)
		{
			listView.setVisibility(View.GONE);
			emptyView.setVisibility(View.VISIBLE);
		}
		else if(emptyView.getVisibility()==View.VISIBLE && mTariffModelList.size() >0)
		{
			listView.setVisibility(View.VISIBLE);
			emptyView.setVisibility(View.GONE);
		}
	}
	
	
	
}
