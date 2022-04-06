package com.livixa.apacam.client.network;

import com.livixa.apacam.client.response.ServerResponse;

public interface ServerConnectListener {
	/**
	 * Called if server call was successful
	 * 
	 * @param response
	 */

	void onSuccess(ServerResponse response);

	/**
	 * Called if server call was failed.
	 * 
	 * @param response
	 */
	void onFailure(ServerResponse response);
}
