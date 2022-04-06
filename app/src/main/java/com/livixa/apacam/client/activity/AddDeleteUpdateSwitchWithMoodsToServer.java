package com.livixa.apacam.client.activity;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;

import com.livixa.apacam.client.response.request.RequestResponse;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.client.R;
import retrofit2.Call;

public class AddDeleteUpdateSwitchWithMoodsToServer implements ServerConnectListener
{

	Context mContext;
	
	Switch_Model mSwitchModel;
	
	List<Mood_Model>  mMoods;
	
	
	SwitchServerResult   resultLintner;

	
	
	public AddDeleteUpdateSwitchWithMoodsToServer(Context mContext,Switch_Model switchModel,List<Mood_Model>  moods)
	{
		this.mContext=mContext;
		this.mSwitchModel=switchModel;
		this.mMoods =  moods;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void makeCalltoServer()
	{
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			
		
		map.put("session", AppPreference.getValue(mContext, AppKeys.KEY_SESSION));
		map.put(AppKeys.KEY_CURRENT_LANGUAGE, AppPreference.getValue(mContext, AppKeys.KEY_CURRENT_LANGUAGE));
		map.put("switch_model_status", mSwitchModel.model_status);
		map.put("switch_mac_address", mSwitchModel.mac_address);
		
		map.put("switches[0][switch_id]", mSwitchModel.switch_id);
		map.put("switches[0][title]", mSwitchModel.title);
		map.put("switches[0][type]", mSwitchModel.type);
        map.put("switches[0][mac_address]", mSwitchModel.mac_address);
		map.put("switches[0][room_id]", mSwitchModel.room_id);
        map.put("switches[0][user_id]", mSwitchModel.user_id);
		map.put("switches[0][model_status]", mSwitchModel.model_status);
		
		
		if (mMoods != null)
			for (int i = 0; i < mMoods.size(); i++) {
				try {
					map.put("moods[" + i + "][mood_id]", mMoods.get(i).moodId);
					map.put("moods[" + i + "][title]",mMoods.get(i).title);
					map.put("moods[" + i + "][mood_identifier]", mMoods.get(i).moodIdentifer+"");
					map.put("moods[" + i + "][switch_id]", mMoods.get(i).switchId);
					map.put("moods[" + i + "][model_status]", mMoods.get(i).modelStatus);
					map.put("moods[" + i + "][picture_url]", mMoods.get(i).pictureURL);
				} catch (Exception ex) {

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ApiService service = KisafaApplication.getRestClient().getApiService();
		Call<RequestResponse> call = service.add_edit_switch(map);
		call.enqueue(new RestCallback<RequestResponse>(this,ServerCodes.ServerRequestCodes.ADD_EDIT_SWITCH_REQUEST_CODE, mContext));
		
	}



	@Override
	public void onSuccess(ServerResponse response) 
	{
		
		if (response.getRequestCode() == ServerCodes.ServerRequestCodes.ADD_EDIT_SWITCH_REQUEST_CODE)
		{
			RequestResponse requestResponse = (RequestResponse) response;
			
			if(requestResponse.getShMeta().getShErrorCode().equals("0"))
			{
				
				if(resultLintner!=null)
				{
					
					resultLintner.onSuccess(requestResponse);
				}
				
			}
			else
			{
				if(resultLintner!=null)
				{
					
					resultLintner.onFailer(requestResponse.getShMeta().getShMessage());
				}
			}
			
		}
		
		
	}



	@Override
	public void onFailure(ServerResponse response)
	{
	
		if(resultLintner!=null)
		{
			
			resultLintner.onFailer(response.getMessage());
		}
		
	}
	
	
	public interface SwitchServerResult
	{
		  
		public void onSuccess(ServerResponse response);
		
		public void onFailer(String response);
		
	}
	
	public void addResultListner(SwitchServerResult   resultLintner)
	{
		this.resultLintner=resultLintner;
	}
	
}
