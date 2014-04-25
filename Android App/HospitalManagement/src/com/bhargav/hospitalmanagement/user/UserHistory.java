package com.bhargav.hospitalmanagement.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bhargav.hospitalmanagement.QueryExecutor;
import com.bhargav.hospitalmanagement.R;
import com.bhargav.hospitalmanagement.RegLogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UserHistory extends Activity {

	private String UserId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_userhistory);

		Bundle bundle = getIntent().getExtras();

		if(bundle.getString("UserId")!= null)
		{
			this.UserId = bundle.getString("UserId");
		}

		final ListView listview = (ListView) findViewById(R.id.historylist);

		final QueryExecutor QE = new QueryExecutor(UserHistory.this);
		String result = null;
		result = QE.GetUserHistory(UserId);
		
		if(result.equals("FAILURE"))
		{
			return;
		}
		result = "{'message': " + result + "}";
//		result = "{'message': [{'DoctorName':'John', 'Date': '10-11-2014', 'Time': '09:20'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//				+  "{'DoctorName':'Mainak', 'Date': '04-04-2014', 'Time': '15:00'} ]}";
		
		JSONObject myjson = null;
		try {
			myjson = new JSONObject(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray the_json_array = null;
		
		try {
			the_json_array = myjson.getJSONArray("message");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int size = the_json_array.length();
		ArrayList<JSONObject> arrays = new ArrayList<JSONObject>();
		for (int i = 0; i < size; i++) {
			JSONObject another_json_object = null;
			try {
				another_json_object = the_json_array.getJSONObject(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			arrays.add(another_json_object);
		}
		
		final JSONObject[] jsons = new JSONObject[arrays.size()];
		arrays.toArray(jsons);
		
		final ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<jsons.length;i++)
		{
			try {
				list.add("Appointment with : " + jsons[i].getString("DoctorName") 
									+ "\non " + jsons[i].getString("Date")
									+ " at " + jsons[i].getString("Time"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		final StableArrayAdapter1 adapter = new StableArrayAdapter1(this,android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {

				switch(position)
				{
				case 0 :
					Intent i1 = new Intent(UserHistory.this, RegLogin.class);
					startActivity(i1);
					break;

				default:
					break;

				}
			}

		});
	}

	private class StableArrayAdapter1 extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter1(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
