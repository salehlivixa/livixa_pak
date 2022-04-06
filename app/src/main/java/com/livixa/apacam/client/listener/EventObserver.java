package com.livixa.apacam.client.listener;

import android.os.Bundle;


/**
 * An asynchronous update interface for receiving notifications
 * about Change information as the Change is constructed.
 * 
 * @author arslan This interface help to notify that data set has been changed
 *         and the others need to call that methods
 */
public interface EventObserver {

	/**
	 * This method is called when information about an Change
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 * 
	 * @param tabType
	 *            the tab type
	 * @param event
	 *            the event
	 * @param data
	 *            the data
	 */
	public void broadCastEvent(ListinerCategory tabType, ChangeEvents event, Bundle data);

}
