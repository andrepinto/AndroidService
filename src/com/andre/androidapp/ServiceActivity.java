package com.andre.androidapp;


import com.andre.androidapp.R;
import com.andre.androidapp.request.UserData;
import com.andre.androidapp.util.AsyncTaskData;
import com.andre.androidapp.util.OnDemandData;
import com.andre.androidapp.util.UserDataService;
import com.andre.androidapp.util.UserDataServiceForeground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceActivity extends Activity {

	TextView txState;
	static ServiceActivity currentActivity;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        
        currentActivity = this;
        
        final Button btnStartItent = (Button)findViewById(R.id.btnStartServiceIntent);
        txState = (TextView) findViewById(R.id.txState);
                
        btnStartItent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				userData();	
			}
		});
        
        
        final Button btnStart = (Button)findViewById(R.id.btnStartService);
        
        btnStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				userData2();
			}
		});
        
        final Button btnStartForeground = (Button)findViewById(R.id.btnStartServiceForeground);
       
        btnStartForeground.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				userDataForeground();
			}
		});

	}
	
	private void userData(){
		 Intent i = new Intent(this, OnDemandData.class);
		 i.putExtra("tagName", "info:");
		 startService(i);
	}
	
	private void userData2(){
		 Intent i = new Intent(this, UserDataService.class);
		 startService(i);
	}
	
	private void userDataForeground(){
		 Intent i = new Intent(this, UserDataServiceForeground.class);
		 startService(i);
	}
	
	public static TextView getActivityTxStateView(){
		return currentActivity.txState;
	}
	

}
