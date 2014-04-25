package com.bhargav.hospitalmanagement;


import java.util.List;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class ThreadExecute extends AsyncTask<Void, Void, String> {

		private ProgressDialog pDialog;
		private static final String TAG_SUCCESS = "success";
		private static final String TAG_MESSAGE = "message";
		JSONParser jsonParser = new JSONParser();
		List<NameValuePair> args_array;
		String target;
		Context context;
		
		public ThreadExecute(Context context, List<NameValuePair> args_array, String target){
			this.args_array = args_array;
			this.target = target;
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage("Requesting...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(Void... x) {
			// TODO Auto-generated method stub
			// Check for success tag
			int success;
			try {				
				// getting json result after making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(target, "POST", args_array);
				// json success tag
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Log.d("Json Data", json.toString());
					return json.getString(TAG_MESSAGE);
				}
				else{
					return "FAILURE";
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;

		}
		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String result) {
			// dismiss the dialog once product deleted
			pDialog.dismiss();
			if (result != null){
				//Toast.makeText(context, result, Toast.LENGTH_LONG).show();
			}

		}


	}
