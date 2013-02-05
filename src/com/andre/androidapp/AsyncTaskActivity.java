package com.andre.androidapp;


import com.andre.androidapp.R;
import com.andre.androidapp.request.UserData;
import com.andre.androidapp.util.AsyncTaskData;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskActivity extends Activity {
	
	
	TextView txState;
	AsyncTaskData asyncTaskData;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        
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
		asyncTaskData = new AsyncTaskData();
		asyncTaskData.execute(txState);
		
	}
	
	

}
