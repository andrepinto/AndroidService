package com.andre.androidapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Button button = (Button) findViewById(R.id.btnSimpleThread);
        button.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			Intent i = new Intent(v.getContext(), SimpleThreadActivity.class);
    			startActivity(i);
    		}
    	});
        
        
        final Button btnAsyncTask = (Button) findViewById(R.id.btnAsyncTask);
        btnAsyncTask.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			Intent i = new Intent(v.getContext(), AsyncTaskActivity.class);
    			startActivity(i);
    		}
    	});
        
        final Button btnService = (Button) findViewById(R.id.btnService);
        btnService.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			Intent i = new Intent(v.getContext(), ServiceActivity.class);
    			startActivity(i);
    		}
    	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
