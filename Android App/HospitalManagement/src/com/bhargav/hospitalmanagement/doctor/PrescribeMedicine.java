package com.bhargav.hospitalmanagement.doctor;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bhargav.hospitalmanagement.QueryExecutor;
import com.bhargav.hospitalmanagement.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class PrescribeMedicine extends Activity implements OnClickListener{


	private TextView patient, pharmacy, medicine, dosage, more, done, days ,T1, T2, T3, T4, T_Temp;
	private String DoctorId,PatientId, PharmacyId, MedicineId, Dosage;
	private EditText disease; 
	private Calendar cal;
	private int hour, min;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_prescribemedicine);

		Bundle bundle = getIntent().getExtras();

		if(bundle.getString("DoctorId")!= null)
		{
			this.DoctorId = bundle.getString("DoctorId");
		}

		//setup buttons
		patient = (TextView)findViewById(R.id.tv_S1);
		pharmacy = (TextView)findViewById(R.id.tv_S2);
		medicine = (TextView)findViewById(R.id.tv_S3);
		dosage = (TextView)findViewById(R.id.tv_S4);
		disease = (EditText)findViewById(R.id.et_S5);
		days = (TextView)findViewById(R.id.tv_S6);
		T1 = (TextView)findViewById(R.id.tv_T1);
		T2 = (TextView)findViewById(R.id.tv_T2);
		T3 = (TextView)findViewById(R.id.tv_T3);
		T4 = (TextView)findViewById(R.id.tv_T4);
		more = (TextView)findViewById(R.id.tv_B1);
		done = (TextView)findViewById(R.id.tv_B2);

		//register listeners
		patient.setOnClickListener(this);
		pharmacy.setOnClickListener(this);
		medicine.setOnClickListener(this);
		dosage.setOnClickListener(this);
		T1.setOnClickListener(this);
		T2.setOnClickListener(this);
		T3.setOnClickListener(this);
		T4.setOnClickListener(this);
		more.setOnClickListener(this);
		done.setOnClickListener(this);
		days.setOnClickListener(this);
	}


	@SuppressWarnings({ "deprecation" })
	@Override
	public void onClick(View v) {

		final QueryExecutor QE = new QueryExecutor(PrescribeMedicine.this);
		String result = null;

		switch (v.getId()) 
		{
		case R.id.tv_S1:
			result = QE.GetDoctorSchedule(DoctorId);
			if(result.equals("FAILURE"))
			{
				break;
			}
			result = "{'message': " + result + "}";
			showlist("Select Patient", result, "PatientId" ,"PatientName", patient);
			break;
		case R.id.tv_S2:
			if(medicine.getText().toString().equals("Select Medicine"))
			{
				Toast.makeText(getBaseContext(),"Select Medicine First", Toast.LENGTH_SHORT).show();
				break;
			}
			result = QE.GetPharmacyFromMedicine(MedicineId);
			result = "{'message': " + result + "}";
			showlist("Select Pharmacy", result, "PharmacyId" ,"PharmacyName", pharmacy);
			break;
		case R.id.tv_S3:
			result = QE.GetMedicineList();
			result = "{'message': " + result + "}";
			showlist("Select Medicine", result, "MedicineId" ,"MedicineName", medicine);
			break;
		case R.id.tv_S4:
			result = "{'message': [    {'Dosage':'1'},"
					+ "{'Dosage':'2'},"
					+ "{'Dosage':'3'},"
					+ "{'Dosage':'4'}]}";
			showlist("Select Dosage", result, "Dosage" ,"Dosage", dosage);

			break;
		case R.id.tv_S6:
			result = "{'message': [    {'Days':'1'},"
					+ "{'Days':'2'},"
					+ "{'Days':'3'},"
					+ "{'Days':'4'},"
					+ "{'Days':'5'},"
					+ "{'Days':'6'},"
					+ "{'Days':'7'},"
					+ "{'Days':'8'},"
					+ "{'Days':'9'},"
					+ "{'Days':'10'}]}";
			showlist("Select Days", result, "Days" ,"Days", days);
			break;
		case R.id.tv_T1:
			T_Temp =T1;
			showDialog(0);
			break;
		case R.id.tv_T2:
			T_Temp =T2;
			showDialog(0);
			break;
		case R.id.tv_T3:
			T_Temp =T3;
			showDialog(0);
			break;
		case R.id.tv_T4:
			T_Temp =T4;
			showDialog(0);
			break;
		case R.id.tv_B1:

			if(pharmacy.getText().toString().equals("Select Pharmacy") ||patient.getText().toString().equals("Select Patient") ||dosage.getText().toString().equals("Select Dosage Per day") || medicine.getText().toString().equals("Select Medicine"))
			{
				Toast.makeText(getBaseContext(),"Fill All The Fields", Toast.LENGTH_SHORT).show();
				break;
			}
			String result1 = QE.PrescribeMedicine(DoctorId, PatientId, PharmacyId, MedicineId, days.getText().toString() ,dosage.getText().toString(), disease.getText().toString(), T1.getText().toString(), T2.getText().toString(), T3.getText().toString(), T4.getText().toString());
			if(result1.equals("FAILURE"))
			{
				Toast.makeText(getBaseContext(),"Failure in Prescribing!", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(getBaseContext(),"Successfully Prescribed!", Toast.LENGTH_SHORT).show();
			}
			Intent i8 = new Intent(PrescribeMedicine.this, PrescribeMedicine.class);
			i8.putExtra("DoctorId", DoctorId);
			startActivity(i8);
			finish();
			break;

		case R.id.tv_B2:
			if(pharmacy.getText().toString().equals("Select Pharmacy") ||patient.getText().toString().equals("Select Patient") ||dosage.getText().toString().equals("Select Dosage Per day") || medicine.getText().toString().equals("Select Medicine"))
			{
				Toast.makeText(getBaseContext(),"Fill All The Fields", Toast.LENGTH_SHORT).show();
				break;
			}
			String result2 = QE.PrescribeMedicine(DoctorId, PatientId, PharmacyId, MedicineId, days.getText().toString() ,dosage.getText().toString(), disease.getText().toString(), T1.getText().toString(), T2.getText().toString(), T3.getText().toString(), T4.getText().toString());
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

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		cal = Calendar.getInstance();
		hour = cal.get(Calendar.HOUR_OF_DAY);
		min = cal.get(Calendar.MINUTE);
		return new TimePickerDialog(this, timePickerListener, hour, min, false);
	}

	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			T_Temp.setText(hourOfDay + " : " + minute);
		}
	};

	public void showlist(String Title, String result, final String keyid, String key, final TextView T)
	{

		AlertDialog.Builder builderSingle = new AlertDialog.Builder(PrescribeMedicine.this);
		builderSingle.setIcon(R.drawable.ic_launcher);
		builderSingle.setTitle(Title);

		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(PrescribeMedicine.this,android.R.layout.select_dialog_singlechoice);
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
					else if(keyid.equals("PharmacyId"))
					{
						PharmacyId = jsons[which].getString(keyid);
					}
					else if(keyid.equals("MedicineId"))
					{
						MedicineId = jsons[which].getString(keyid);
					}
					else if(keyid.equals("Dosage"))
					{
						Dosage = jsons[which].getString(keyid);
						switch(Integer.parseInt(Dosage))
						{
						case 1:
							T1.setVisibility(View.VISIBLE);
							break;
						case 2:
							T1.setVisibility(View.VISIBLE);
							T2.setVisibility(View.VISIBLE);
							break;
						case 3:
							T1.setVisibility(View.VISIBLE);
							T2.setVisibility(View.VISIBLE);
							T3.setVisibility(View.VISIBLE);
							break;
						case 4:
							T1.setVisibility(View.VISIBLE);
							T2.setVisibility(View.VISIBLE);
							T3.setVisibility(View.VISIBLE);
							T4.setVisibility(View.VISIBLE);
							break;
						}
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
