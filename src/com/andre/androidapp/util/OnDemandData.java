package com.andre.androidapp.util;

import com.andre.androidapp.request.UserData;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class OnDemandData extends IntentService {

	public OnDemandData() {
		super("OnDemandData");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		try {

			String tag = intent.getStringExtra("tagName");
			if(tag == null)
				tag =  "tag";
					
			UserData srv = new UserData();
			
			srv.getPersonalData();
			srv.getSettings();
			srv.getLocation();
			
			Log.v("OnDemandData","Fim:"+tag);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
