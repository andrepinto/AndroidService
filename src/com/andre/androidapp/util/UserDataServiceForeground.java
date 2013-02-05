package com.andre.androidapp.util;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.andre.androidapp.ServiceActivity;
import com.andre.androidapp.request.UserData;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

public class UserDataServiceForeground extends Service {

	ExecutorService executorService;
    TextView txState;
    String message;
    Notification foregroundNotification; 
    final int notification_id = 1;
    NotificationManager notificationManager;
    
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		executorService = Executors.newSingleThreadExecutor();
		notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
		startInForeground();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Runnable run = new ServiceRunnable(this);
		executorService.execute(run);

		return Service.START_NOT_STICKY;
	}
	
	public void startInForeground(){
		int icon = R.drawable.stat_notify_sync;
		String notificationText = "A obter dados pessoais";
		long time = System.currentTimeMillis();
		foregroundNotification = new Notification();
		foregroundNotification.icon=icon;
		foregroundNotification.tickerText=notificationText;
		foregroundNotification.when=time;
		
		Intent serviceIntent = new Intent(this, ServiceActivity.class);
		PendingIntent startPendingIntent = PendingIntent.getActivity(this, 0, serviceIntent, 0);
		
		foregroundNotification.setLatestEventInfo(this, "Demo Service", "Get User Data", startPendingIntent);
		
		startForeground(notification_id, foregroundNotification);
		
		
	}
	
	public void setIcon(int iconId){
		foregroundNotification.icon=iconId;
		notificationManager.notify(notification_id, foregroundNotification);
	}
	
	class ServiceRunnable implements Runnable{

		UserDataServiceForeground service;
		
		public ServiceRunnable(UserDataServiceForeground service){
			this.service = service; 
		}
		@Override
		public void run() {
			try {
				service.setIcon(R.drawable.stat_notify_sync);
				
				txState = ServiceActivity.getActivityTxStateView();
				UserData srv = new UserData();
				
				updateStateMsg("getPersonalData");
				srv.getPersonalData();
				
				updateStateMsg("getSettings");
				srv.getSettings();
				
				updateStateMsg("getLocation");
				srv.getLocation();
				
				updateStateMsg("End");
				
				service.setIcon(R.drawable.ic_input_get);
			} catch (Exception e) {
				Log.v("UserDataService", "error:" + e.getMessage());
			}
		}
		void updateStateMsg(String msg){
			message = msg;
			if(txState!=null){
				txState.post(new Runnable() {
					@Override
					public void run() {
						txState.setText(message);
					}
				});
			}
		}
		
	}

}
