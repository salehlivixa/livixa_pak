package com.livixa.apacam.client.listener;


public interface MyGPSListener {
    /**
     * Called if server call was successful
     *
     */

    void onSuccess();

    /**
     * Called if server call was failed.
     *
     */
    void onFailure();
}
