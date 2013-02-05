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

public class UserDataService extends Service {

	ExecutorService executorService;
    TextView txState;
    String message;
    Notification foregroundNotification; 
    
    
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		executorService = Executors.newSingleThreadExecutor();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					txState = ServiceActivity.getActivityTxStateView();
					UserData srv = new UserData();
					
					updateStateMsg("getPersonalData");
					srv.getPersonalData();
					
					updateStateMsg("getSettings");
					srv.getSettings();
					
					updateStateMsg("getLocation");
					srv.getLocation();
					
					updateStateMsg("End");
					
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
		});

		return Service.START_NOT_STICKY;
	}
	

}
