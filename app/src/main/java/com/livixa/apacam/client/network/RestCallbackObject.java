package com.livixa.apacam.client.network;

import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.ServerCodes;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Context;

@SuppressWarnings("rawtypes")
public class RestCallbackObject<T> implements Callback {
	private boolean isCanceled;
	private ServerConnectListenerObject listener;
	private int requestCode;

	private Context mContext;

	public RestCallbackObject(ServerConnectListenerObject listener,
			int requestCode, Context context) {
		this.listener = listener;
		this.requestCode = requestCode;
		this.mContext = context;
	}

	public void cancel() {
		isCanceled = true;
	}

	public boolean isCancelled() {
		return isCanceled;
	}

	@Override
	public void onResponse(Call call, Response response) {
		if (isCancelled()) {
			return;
		}
		if (response.body() != null) {
			try {
				String s = response.body().toString();
				s = s.replaceAll("\n", "");
				s = s.replaceAll("\r", "");
				JSONObject obj = new JSONObject(s);
				if (obj.getInt("ResultType") == ServerCodes.ServerResultTypes.DeActivated
						.value()) {
					AppPreference.saveData(KisafaApplication.getAppContext(),
							true, AppKeys.KEY_IS_LOGIN);
					// Intent intent = new Intent(this.mContext,
					// HomeActivity.class);
					// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
					// | Intent.FLAG_ACTIVITY_NEW_TASK);
					// this.mContext.startActivity(intent);
				} else {
					listener.onSuccess(response.body());
				}
			} catch (Exception e) {
				listener.onSuccess(response.body());
			}
		}
	}

	@Override
	public void onFailure(Call call, Throwable throwable) {
		listener.onFailure(throwable);
	}
}
