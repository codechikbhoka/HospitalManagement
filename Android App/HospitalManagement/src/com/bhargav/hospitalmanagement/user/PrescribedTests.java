package com.bhargav.hospitalmanagement.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bhargav.hospitalmanagement.QueryExecutor;
import com.bhargav.hospitalmanagement.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PrescribedTests extends Activity {

	private String UserId;
	private TextView title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_prescribed);

		Bundle bundle = getIntent().getExtras();

		if(bundle.getString("UserId")!= null)
		{
			this.UserId = bundle.getString("UserId");
		}

		title = (TextView)findViewById(R.id.tv_title);
		title.setText("My Prescribed Tests");
		
		final ListView listview = (ListView) findViewById(R.id.list_data);

		final QueryExecutor QE = new QueryExecutor(PrescribedTests.this);
		String result = null;
		result = QE.GetPrescribedTests(UserId);
		
		if(result.equals("FAILURE"))
		{
			return;
		}
		result = "{'message': " + result + "}";
		
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
				list.add(     "Prescribed By " + jsons[i].getString("DoctorName") 
							+ "\nTest Name : "          + jsons[i].getString("LabTestName"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		final StableArrayAdapter1 adapter = new StableArrayAdapter1(this,android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);

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
