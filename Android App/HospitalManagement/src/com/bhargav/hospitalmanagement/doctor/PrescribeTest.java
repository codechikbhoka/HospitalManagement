package com.bhargav.hospitalmanagement.doctor;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bhargav.hospitalmanagement.QueryExecutor;
import com.bhargav.hospitalmanagement.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PrescribeTest extends Activity implements OnClickListener{


	private TextView patient, labtest, more, done;
	private String DoctorId, PatientId, LabTestId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_prescribetest);

		Bundle bundle = getIntent().getExtras();

		if(bundle.getString("DoctorId")!= null)
		{
			this.DoctorId = bundle.getString("DoctorId");
		}

		//setup buttons
		patient = (TextView)findViewById(R.id.tv_S1);
		labtest = (TextView)findViewById(R.id.tv_S2);
		more = (TextView)findViewById(R.id.tv_B1);
		done = (TextView)findViewById(R.id.tv_B2);

		//register listeners
		patient.setOnClickListener(this);
		labtest.setOnClickListener(this);
		more.setOnClickListener(this);
		done.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {

		final QueryExecutor QE = new QueryExecutor(PrescribeTest.this);
		String result = null;

		switch (v.getId()) 
		{
		case R.id.tv_S1:
			result = QE.GetDoctorSchedule(DoctorId);
			result = "{'message': " + result + "}";

			showlist("Select Patient", result, "PatientId" ,"PatientName", patient);
			break;
		case R.id.tv_S2:
			result = QE.GetLabTestList();
			result = "{'message': " + result + "}";
			//			result = "{'message': [{'LabTestId':'1', 'LabTestName':'Lab Test 1'},"
			//					+  "{'LabTestId':'2', 'LabTestName':'Lab Test 1'},"
			//					+  "{'LabTestId':'3', 'LabTestName':'Lab Test 2'},"
			//					+  "{'LabTestId':'4', 'LabTestName':'Lab Test 3'},"
			//					+  "{'LabTestId':'5', 'LabTestName':'Lab Test 4'},"
			//					+  "{'LabTestId':'6', 'LabTestName':'Lab Test 5'},"
			//					+  "{'LabTestId':'7', 'LabTestName':'Lab Test 6'},"
			//					+  "{'LabTestId':'8', 'LabTestName':'Lab Test 7'} ]}";

			showlist("Select LabTest", result, "LabTestId" ,"LabTestName", labtest);
			break;

		case R.id.tv_B1:

			if(patient.getText().toString().equals("Select Patient") || labtest.getText().toString().equals("Select LabTest"))
			{
				Toast.makeText(getBaseContext(),"Fill All The Fields", Toast.LENGTH_SHORT).show();
				break;
			}
			String result1 = QE.PrescribeLabTest(DoctorId, PatientId, LabTestId);
			if(result1.equals("FAILURE"))
			{
				Toast.makeText(getBaseContext(),"Failure in Prescribing!", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(getBaseContext(),"Successfully Prescribed!", Toast.LENGTH_SHORT).show();
			}
			Intent i8 = new Intent(PrescribeTest.this, PrescribeTest.class);
			i8.putExtra("DoctorId", DoctorId);
			startActivity(i8);
			finish();
			break;

		case R.id.tv_B2:
			if(patient.getText().toString().equals("Select Patient") || labtest.getText().toString().equals("Select LabTest"))
			{
				Toast.makeText(getBaseContext(),"Fill All The Fields", Toast.LENGTH_SHORT).show();
				break;
			}
			String result2 = QE.PrescribeLabTest(DoctorId, PatientId, LabTestId);
			if(result2.equals("FAILURE"))
			{
				Toast.makeText(getBaseContext(),"Failure in Prescribing!", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(getBaseContext(),"Successfully Prescribed!", Toast.LENGTH_SHORT).show();
			}
			finish();
			break;

		default:
			break;

		}
	}

	public void showlist(String Title, String result, final String keyid, String key, final TextView T)
	{

		AlertDialog.Builder builderSingle = new AlertDialog.Builder(PrescribeTest.this);
		builderSingle.setIcon(R.drawable.ic_launcher);
		builderSingle.setTitle(Title);

		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(PrescribeTest.this,android.R.layout.select_dialog_singlechoice);
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
				arrayAdapter.add(jsons[i].getString(key));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		builderSingle.setAdapter(arrayAdapter,new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) {
				T.setText(arrayAdapter.getItem(which));
				try {
					if(keyid.equals("PatientId"))
					{
						PatientId = jsons[which].getString(keyid);
					}
					else if(keyid.equals("LabTestId"))
					{
						LabTestId = jsons[which].getString(keyid);
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
