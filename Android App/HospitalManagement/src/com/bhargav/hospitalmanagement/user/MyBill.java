package com.bhargav.hospitalmanagement.user;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.bhargav.hospitalmanagement.QueryExecutor;
import com.bhargav.hospitalmanagement.R;
import com.bhargav.hospitalmanagement.user.BillObj;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MyBill extends Activity{

	ListView recordlist;
	ArrayList<BillObj> details;
	String UserId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Bundle bundle = getIntent().getExtras();

		if(bundle.getString("UserId")!= null)
		{
			this.UserId = bundle.getString("UserId");
		}
		
		setContentView(R.layout.user_mybill);
		recordlist = (ListView) findViewById(R.id.billlist);
		details = new ArrayList<BillObj>();
		QueryExecutor QE = new QueryExecutor(MyBill.this);
		String result = null;
		
		result = QE.GetBillInfo(UserId);
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

		for(int i=0;i<jsons.length;i++)
		{
			try {
				BillObj x = new BillObj();
				x.setBillId(jsons[i].getString("BillId"));
				x.setDate(jsons[i].getString("Date"));
				x.setTime(jsons[i].getString("Time"));
				x.setAmount(jsons[i].getString("Amount"));
				details.add(x);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		recordlist.setAdapter(new PatientBillCustomAdapter(details,this));
	}

}
