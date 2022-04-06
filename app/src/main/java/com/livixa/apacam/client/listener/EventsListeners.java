package com.livixa.apacam.client.listener;

import android.os.Bundle;

/**
 * The Class EventsListeners.
 */
public class EventsListeners {


	/** The common listeners. */
	private static EventsListeners commonListeners = null;

	/** The listeners arry. */
	private EventListener[] listenersArry = null;


	/**
	 * Instantiates a new events listeners.
	 */
	private EventsListeners() {

		this.listenersArry = new EventListener[ListinerCategory.values().length];
		for (int i = 0; i < this.listenersArry.length; i++) {
			this.listenersArry[i] = new EventListener();
		}
	}


	/**
	 * Gets the single instance of EventsListeners.
	 * 
	 * @return single instance of EventsListeners
	 */
	public static EventsListeners getInstance() {

		if (EventsListeners.commonListeners == null) {
			EventsListeners.commonListeners = new EventsListeners();
		}
		return EventsListeners.commonListeners;
	}


	/**
	 * Register listeners.
	 * 
	 * @param listener
	 *            the listener
	 * @param type
	 *            the type
	 */
	public void registerListeners(EventObserver listener, ListinerCategory type) {

		this.listenersArry[type.ordinal()].registerListener(listener);
	}


	/**
	 * Contain.
	 * 
	 * @param listener
	 *            the listener
	 * @param type
	 *            the type
	 * @return true, if successful
	 */
	public boolean contain(EventObserver listener, ListinerCategory type) {

		return this.listenersArry[type.ordinal()].contain(listener);
	}


	/**
	 * Replace register.
	 * 
	 * @param listener
	 *            the listener
	 * @param type
	 *            the type
	 */
	public void replaceRegister(EventObserver listener, ListinerCategory type) {

		this.listenersArry[type.ordinal()].replaceRegister(listener);
	}


	/**
	 * Un register listener.
	 * 
	 * @param listener
	 *            the listener
	 * @param type
	 *            the type
	 */
	public void unRegisterListener(EventObserver listener, ListinerCategory type) {

		this.listenersArry[type.ordinal()].unRegisterListener(listener);
	}


	/**
	 * Un register all listener.
	 * 
	 * @param type
	 *            the type
	 */
	public void unRegisterAllListener(ListinerCategory type) {

		this.listenersArry[type.ordinal()].unRegisterAllListener();
	}


	/**
	 * Broad cast event.
	 * 
	 * @param type
	 *            the type
	 * @param event
	 *            the event
	 * @param classsName
	 *            the classs name
	 */
	public void broadCastEvent(ListinerCategory type, ChangeEvents event) {

		this.listenersArry[type.ordinal()].broadCastEvent(type, event);
	}


	/**
	 * Broad cast event.
	 * 
	 * @param type
	 *            the type
	 * @param event
	 *            the event
	 * @param data
	 *            the data
	 */
	public void broadCastEvent(ListinerCategory type, ChangeEvents event, Bundle data) {

		this.listenersArry[type.ordinal()].broadCastEvent(type, event, data);
	}


	/**
	 * Destroy object.
	 */
	public void destroyObject() {

		for (final EventListener element : this.listenersArry) {
			element.destoryObject();
		}
		this.listenersArry = null;
		EventsListeners.commonListeners = null;
	}
}
