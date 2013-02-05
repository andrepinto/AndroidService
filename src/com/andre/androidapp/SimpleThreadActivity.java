package com.andre.androidapp;


import com.andre.androidapp.R;
import com.andre.androidapp.request.UserData;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleThreadActivity extends Activity {
	
	Thread dataThread;
	TextView txState;
	String stateMsg;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_thread);
        
        final Button btnStart = (Button)findViewById(R.id.btnStartService);
        txState = (TextView) findViewById(R.id.txState);
        
        
        btnStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				userData();
				
			}
		});
        
        
	}
	
	private void userData(){
		dataThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {

					UserData srv = new UserData();
					
					showMsg("getPersonalData...");
					srv.getPersonalData();
					
					showMsg("getSettings...");
					srv.getSettings();
					
					showMsg("getLocation...");
					srv.getLocation();
					
					showMsg("end user data");
					
				} catch (Exception e) {
					System.out.println("Error:"+e.getMessage());
				}
				
			}
		});
		
		dataThread.start();
		
	}
	
	private void showMsg(String msg){
		stateMsg = msg;
		txState.post(new Runnable() {
			
			@Override
			public void run() {
				txState.setText(stateMsg);
				
			}
		});
	}
	

}
