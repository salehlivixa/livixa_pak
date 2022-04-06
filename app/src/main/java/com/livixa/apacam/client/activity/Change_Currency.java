package com.livixa.apacam.client.activity;

import java.util.HashMap;

import com.github.mrengineer13.snackbar.SnackBar;

import android.app.Activity;
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
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.client.R;
import retrofit2.Call;

public class Change_Currency   implements ServerConnectListener
{

	private String currency;
	
	private Context mContex;
	
	public Change_Currency(String currency,Context context)
	{
		this.currency=currency;
		this.mContex=context;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("session", AppPreference.getValue(mContex, AppKeys.KEY_SESSION));
		map.put(AppKeys.KEY_CURRENT_LANGUAGE, AppPreference.getValue(mContex, AppKeys.KEY_CURRENT_LANGUAGE));
		map.put("currency", currency);
		//showProgressDialog("Signing out...", 100);
		WaitingStaticProgress.showProgressDialog("Changing Currency...", mContex);
		ApiService service = KisafaApplication.getRestClient().getApiService();
		Call<RequestResponse> call = service.changeCurrency(map);
		call.enqueue(new RestCallback<RequestResponse>(Change_Currency.this,ServerCodes.ServerRequestCodes.CHANGE_CURRENCY_REQUEST_CODE, mContex));
		
	}
	
	
	@Override
	public void onSuccess(ServerResponse response) {
		
		if (response.getRequestCode() == ServerCodes.ServerRequestCodes.CHANGE_CURRENCY_REQUEST_CODE) {
			RequestResponse requestResponse = (RequestResponse) response;
			if (requestResponse.getShMeta().getShErrorCode()
					.equalsIgnoreCase("0")) {
				
				AppPreference.saveValue(mContex, currency, AppKeys.KEY_CURRENT_CURRENCY);
				
				onFailure(mContex.getString(R.string.Currencyupdatedsuccessfully));
				
			}
			else
			{
				onFailure(requestResponse.getShMeta().getShMessage());
			}
			
		}
		
	}

	@Override
	public void onFailure(ServerResponse response) {
		
		onFailure(response.getMessage());
		
		
	}
	
	public void onFailure(String retrofitError) {
		
		WaitingStaticProgress.hideProgressDialog();
	
	new SnackBar.Builder((Activity) mContex).withMessage(retrofitError)
			.withDuration(SnackBar.SHORT_SNACK).show();
}
	
	
}