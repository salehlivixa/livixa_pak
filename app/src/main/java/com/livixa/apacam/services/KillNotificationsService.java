package com.livixa.apacam.services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class KillNotificationsService extends Service {

    public class KillBinder extends Binder {
        public final Service service;

        public KillBinder(Service service) {
            this.service = service;
        }

    }

    private final IBinder mBinder = new KillBinder(this);

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }
    @Override
    public void onCreate() {
    	try
		{
			NotificationManager nManager = ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE));
			nManager.cancelAll();
		}catch(Exception ex)
    	{
            ex.printStackTrace();
    	};
    }
    
    @Override
    public void onTaskRemoved(Intent rootIntent) {
    	
    	//Toast.makeText(getBaseContext(), "Task Removed", Toast.LENGTH_SHORT).show();
    	
    	super.onTaskRemoved(rootIntent);
    }
}
