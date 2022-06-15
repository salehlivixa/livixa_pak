package com.livixa.apacam.client.network;

import java.io.IOException;

import com.google.gson.Gson;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.utility.AppStrings;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Context;

@SuppressWarnings("rawtypes")
public class RestCallback<T> implements Callback {

	private Context mContext;

	private ServerConnectListener listener;

	private boolean isCanceled;
	private int requestCode;

	public RestCallback(ServerConnectListener listener, int requestCode,
			Context context) {
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
		// Toast.makeText(mContext, response.message(),
		// Toast.LENGTH_SHORT).show();
		if (call.isCanceled()) {
			return;
		}
		if (response.isSuccessful()) {
			if (response.body() != null) {
				ServerResponse callResponse = (ServerResponse) response.body();
				callResponse.setRequestCode(requestCode);
				listener.onSuccess(callResponse,new Gson().toJson(response));

			} else {
				setErrorMessage(response.raw().message());
			}
		} else {
			setErrorMessage(response.raw().message());
		}
	}

	@Override
	public void onFailure(Call call, Throwable throwable) {
		ServerResponse errorResponse = new ServerResponse();
		errorResponse.setRequestCode(requestCode);
		errorResponse.setThrowable(throwable);
		if (throwable instanceof IOException) {
			// Add your code for displaying no network connection error
			errorResponse.setMessage(AppStrings.NETWORK_ERROR_MESSAGE);
		} else {
			errorResponse.setMessage(throwable.getLocalizedMessage());
		}
		listener.onFailure(errorResponse);
	}

	public void setErrorMessage(String error) {
		ServerResponse errorResponse = new ServerResponse();
		errorResponse.setRequestCode(requestCode);
		errorResponse.setMessage(error);
		listener.onFailure(errorResponse);
	}

}
