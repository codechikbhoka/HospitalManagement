package com.bhargav.hospitalmanagement.doctor;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.bhargav.hospitalmanagement.QueryExecutor;
import com.bhargav.hospitalmanagement.R;
import com.bhargav.hospitalmanagement.user.EditUser;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DoctorPage extends Activity implements OnClickListener{


	private TextView schedule, PatientInfo, PresMed, PresTest;
	private String DoctorId,PatientId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_doctorpage);

		Bundle bundle = getIntent().getExtras();

		if(bundle.getString("DoctorId")!= null)
		{
			this.DoctorId = bundle.getString("DoctorId");
		}

		//setup buttons
		schedule = (TextView)findViewById(R.id.tv_schedule);
		PatientInfo = (TextView)findViewById(R.id.tv_PatientInfo);
		PresMed = (TextView)findViewById(R.id.tv_PresMed);
		PresTest = (TextView)findViewById(R.id.tv_PresTest);


		//register listeners
		schedule.setOnClickListener(this);
		PatientInfo.setOnClickListener(this);
		PresMed.setOnClickListener(this);
		PresTest.setOnClickListener(this);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {

		QueryExecutor QE = new QueryExecutor(DoctorPage.this);

		switch (v.getId()) 
		{
		case R.id.tv_schedule:
			Intent i6 = new Intent(DoctorPage.this, DoctorSchedule.class);
			i6.putExtra("DoctorId", DoctorId);
			startActivity(i6);
			break;

		case R.id.tv_PresMed:
			Intent i8 = new Intent(DoctorPage.this, PrescribeMedicine.class);
			i8.putExtra("DoctorId", DoctorId);
			startActivity(i8);
			break;
		case R.id.tv_PresTest:
			Intent i9 = new Intent(DoctorPage.this, PrescribeTest.class);
			i9.putExtra("DoctorId", DoctorId);
			startActivity(i9);
			break;
		case R.id.tv_PatientInfo:
			String result = null;
						result = QE.GetDoctorSchedule(DoctorId);
						result = "{'message': " + result + "}";
//			result = "{'message': [{'PatientId':'1', 'PatientName':'John', 'Date': '10-11-2014', 'Time': '09:20'},"
//					+  "{'PatientId':'2', 'PatientName':'Vivek', 'Date': '08-09-2014', 'Time': '10:30'},"
//					+  "{'PatientId':'3', 'PatientName':'Neha', 'Date': '08-09-2014', 'Time': '10:30'},"
//					+  "{'PatientId':'4', 'PatientName':'Kishan', 'Date': '08-09-2014', 'Time': '10:30'},"
//					+  "{'PatientId':'5', 'PatientName':'Prerna', 'Date': '08-09-2014', 'Time': '10:30'},"
//					+  "{'PatientId':'6', 'PatientName':'Jeevan', 'Date': '08-09-2014', 'Time': '10:30'},"
//					+  "{'PatientId':'7', 'PatientName':'Soufix', 'Date': '08-09-2014', 'Time': '10:30'},"
//					+  "{'PatientId':'8', 'PatientName':'Vipin', 'Date': '04-04-2014', 'Time': '15:00'} ]}";
			showlist(result);
			break;
		default:
			break;
		}

	}


	public void showlist(String result)
	{

		AlertDialog.Builder builderSingle = new AlertDialog.Builder(DoctorPage.this);
		builderSingle.setIcon(R.drawable.ic_launcher);
		builderSingle.setTitle("Select Patient");

		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DoctorPage.this,android.R.layout.select_dialog_singlechoice);
		builderSingle.setNegativeButton("cancel",new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

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
				arrayAdapter.add(jsons[i].getString("PatientName"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		builderSingle.setAdapter(arrayAdapter,new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) {
				try {
					PatientId = jsons[which].getString("PatientId");
					QueryExecutor QE = new QueryExecutor(DoctorPage.this);
					String result = null;
					result = QE.GetPatientInfo(PatientId);
					result = "{'message': " + result + "}";

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
						Intent i9 = new Intent(DoctorPage.this, EditUser.class);
						i9.putExtra("Mode", "ShowOnly");
						i9.putExtra("UserId", "UserId");
						i9.putExtra("passed_UserID", jsons[0].getString("UserId"));
						i9.putExtra("passed_UserName" , jsons[0].getString("UserName"));
						i9.putExtra("passed_UserPassword" , jsons[0].getString("UserPassword"));
						i9.putExtra("passed_UserAge" , jsons[0].getString("UserAge"));
						i9.putExtra("passed_UserSex" , jsons[0].getString("UserSex"));
						i9.putExtra("passed_UserAddress" , jsons[0].getString("UserAddress"));
						i9.putExtra("passed_UserPhone" , jsons[0].getString("UserPhone"));
						startActivity(i9);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}



				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		builderSingle.show();
	}
}
