package com.livixa.apacam.client.listener;

import java.util.ArrayList;

import android.os.Bundle;


/**
 * The listener interface for receiving event events.
 * The class that is interested in processing a event
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addEventListener<code> method. When
 * the event event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see EventEvent
 */
public class EventListener {


	/** The listener list. */
	private ArrayList<EventObserver> listenerList = null;


	/**
	 * Instantiates a new event listener.
	 */
	public EventListener() {

		this.listenerList = new ArrayList<EventObserver>();
	}

	/**
	 * Broad cast event.
	 * 
	 * @param tabType
	 * @param event
	 */
	public void broadCastEvent(ListinerCategory tabType, ChangeEvents event) {

		for (final EventObserver change : this.listenerList) {
			change.broadCastEvent(tabType, event, null);
		}
	}

	/**
	 * Broad cast event.
	 * 
	 * @param tabType
	 *            the tab type
	 * @param event
	 *            the event
	 * @param bundle
	 *            the bundle
	 */
	public void broadCastEvent(ListinerCategory tabType, ChangeEvents event, Bundle bundle) {

		for (final EventObserver change : this.listenerList) {
			change.broadCastEvent(tabType, event, bundle);
		}
	}


	/**
	 * Register listener.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void registerListener(EventObserver listener) {

		this.listenerList.add(listener);
	}


	/**
	 * Contain.
	 * 
	 * @param listener
	 *            the listener
	 * @return true, if successful
	 */
	public boolean contain(EventObserver listener) {

		return this.listenerList.contains(listener);
	}


	/**
	 * Replace register.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void replaceRegister(EventObserver listener) {

		if (this.contain(listener)) {
			this.listenerList.remove(listener);
		}
		this.registerListener(listener);
	}
	
	


	/**
	 * Un register listener.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void unRegisterListener(EventObserver listener) {

		this.listenerList.remove(listener);
	}


	/**
	 * Un register all listener.
	 */
	public void unRegisterAllListener() {

		this.listenerList.clear();
	}


	/**
	 * Destory object.
	 */
	public void destoryObject() {

		this.unRegisterAllListener();
		this.listenerList = null;
	}
}