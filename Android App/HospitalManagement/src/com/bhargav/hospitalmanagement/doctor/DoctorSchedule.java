package com.bhargav.hospitalmanagement.doctor;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bhargav.hospitalmanagement.QueryExecutor;
import com.bhargav.hospitalmanagement.R;
import com.bhargav.hospitalmanagement.user.EditUser;
import com.bhargav.hospitalmanagement.user.PatientCustomAdapter;
import com.bhargav.hospitalmanagement.user.PatientObj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class DoctorSchedule extends Activity{

	ListView recordlist;
	ArrayList<PatientObj> details;
	String DoctorId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Bundle bundle = getIntent().getExtras();

		if(bundle.getString("DoctorId")!= null)
		{
			this.DoctorId = bundle.getString("DoctorId");
		}
		
		setContentView(R.layout.doctor_schedule);
		recordlist = (ListView) findViewById(R.id.list_data);
		details = new ArrayList<PatientObj>();
		QueryExecutor QE = new QueryExecutor(DoctorSchedule.this);
		String result = null;
		
		Log.d("Doctor ID IS", DoctorId);
		result = QE.GetDoctorSchedule(DoctorId);
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
				PatientObj x = new PatientObj();
				x.setId(jsons[i].getString("PatientId"));
				x.setName(jsons[i].getString("PatientName"));
				x.setDate(jsons[i].getString("Date"));
				x.setTime(jsons[i].getString("Time"));
				details.add(x);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		recordlist.setAdapter(new PatientCustomAdapter(details,this));
		recordlist.setOnItemClickListener(new OnItemClickListener() {
			@Override           	
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				String PatientId = details.get(position).getId();

				QueryExecutor QE = new QueryExecutor(DoctorSchedule.this);
				String result = null;
				Log.d("Clicked PatientId", PatientId);
				result = QE.GetPatientInfo(PatientId);
				result = "{'message': " + result + "}";

//				result = "{'message': [{ " 
//						+ " 'UserId':'1', "
//						+ " 'UserName':'Vivek', "
//						+ " 'UserPassword':'pass', "
//						+ " 'UserAge':'21', "
//						+ " 'UserSex':'M',"
//						+ " 'UserStreet':'Street 1', "
//						+ " 'Userlocality':'Locality 1', "
//						+ " 'UserCity':'City 1', "
//						+ " 'UserState':'State 1', "
//						+ " 'UserCountry':'Country 1', "
//						+ " 'UserHomeLandline':'HLL 1',"
//						+ " 'UserHomeMobile':'Hm 1',"
//						+ " 'UserPersonal':'Personal 1'}]}";

				JSONObject myjson2 = null;
				try {
					myjson2 = new JSONObject(result);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				JSONArray the_json_array2 = null;

				try {
					the_json_array2 = myjson2.getJSONArray("message");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ArrayList<JSONObject> arrays = new ArrayList<JSONObject>();
				JSONObject another_json_object = null;
				try {
					another_json_object = the_json_array2.getJSONObject(0);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				arrays.add(another_json_object);

				final JSONObject[] jsons = new JSONObject[arrays.size()];
				arrays.toArray(jsons);


				try {
					Intent i9 = new Intent(DoctorSchedule.this, EditUser.class);
					i9.putExtra("Mode", "ShowOnly");
					i9.putExtra("UserId", "UserId");
					i9.putExtra("passed_UserID", jsons[0].getString("UserId"));
					i9.putExtra("passed_UserName" , jsons[0].getString("UserName"));
					i9.putExtra("passed_UserPassword" , jsons[0].getString("UserPassword"));
					i9.putExtra("passed_UserAge" , jsons[0].getString("UserAge"));
					i9.putExtra("passed_UserSex" , jsons[0].getString("UserSex"));
//					i9.putExtra("passed_UserStreet" , jsons[0].getString("UserStreet"));
//					i9.putExtra("passed_Userlocality" , jsons[0].getString("Userlocality"));
//					i9.putExtra("passed_UserCity" , jsons[0].getString("UserCity"));
//					i9.putExtra("passed_UserState" , jsons[0].getString("UserState"));
//					i9.putExtra("passed_UserCountry" , jsons[0].getString("UserCountry"));
//					i9.putExtra("passed_UserHomeLandline" , jsons[0].getString("UserHomeLandline"));
//					i9.putExtra("passed_UserHomeMobile" , jsons[0].getString("UserHomeMobile"));
//					i9.putExtra("passed_UserPersonal" , jsons[0].getString("UserPersonal"));
					startActivity(i9);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		});
	}

}
