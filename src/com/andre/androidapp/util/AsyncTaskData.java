package com.andre.androidapp.util;


import com.andre.androidapp.request.UserData;

import android.os.AsyncTask;
import android.widget.TextView;

public class AsyncTaskData extends AsyncTask<TextView, String, Boolean> {

	TextView txState;
	
	@Override
	protected Boolean doInBackground(TextView... params) {
		Boolean result = false;
		try {
			if(params.length>0){
				txState = params[0];
				
				UserData srv = new UserData();
				
				//publishProgress call onProgressUpdate
				publishProgress("getPersonalData...");
				srv.getPersonalData();
				
				publishProgress("getSettings...");
				srv.getSettings();
				
				publishProgress("getLocation...");
				srv.getLocation();
				
				publishProgress("end user data");
				result=true;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		if(result.booleanValue())
			txState.setText("end");
		else
			txState.setText("error");
	}
	
	
	@Override
	protected void onProgressUpdate(String... values) {
		txState.setText(values[0]);
	}

	
	
	

}
