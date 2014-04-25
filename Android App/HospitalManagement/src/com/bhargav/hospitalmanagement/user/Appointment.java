package com.bhargav.hospitalmanagement.user;

import java.util.ArrayList;
import java.util.Calendar;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Appointment extends Activity implements OnClickListener{

	private TextView C1, C2,C3, C4, C5, C6;
	private String UserId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_appointment);

		Bundle bundle = getIntent().getExtras();

        if(bundle.getString("UserId")!= null)
        {
            this.UserId = bundle.getString("UserId");
        }
        
		//setup buttons
		C1 = (TextView)findViewById(R.id.textView1);
		C2 = (TextView)findViewById(R.id.textView2);
		C3 = (TextView)findViewById(R.id.textView3);
		C4 = (TextView)findViewById(R.id.textView4);
		C5 = (TextView)findViewById(R.id.textView5);
		C6 = (TextView)findViewById(R.id.textView6);


		//register listeners
		C1.setOnClickListener(this);
		C2.setOnClickListener(this);
		C3.setOnClickListener(this);
		C4.setOnClickListener(this);
		C5.setOnClickListener(this);
		C6.setOnClickListener(this);
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

		AlertDialog.Builder builderSingle = new AlertDialog.Builder(Appointment.this);
		builderSingle.setIcon(R.drawable.ic_launcher);
		TextView b = (TextView)v;
		String DoctorType = b.getText().toString();
		builderSingle.setTitle(DoctorType);

		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Appointment.this,android.R.layout.select_dialog_singlechoice);
		builderSingle.setNegativeButton("cancel",new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});


		final QueryExecutor QE = new QueryExecutor(Appointment.this);
		String result = null;
		result = QE.GetDoctorList(DoctorType);
		if(result.equals("FAILURE"))
		{
			return;
		}
		result = "{'message': " + result + "}";
//		result = "{'message': [{'DoctorId':'1', 'DoctorName':'John', 'DoctorCategory': 'Inhouse'},"
//							+  "{'DoctorId':'2', 'DoctorName':'Vivek', 'DoctorCategory': 'Consultant'},"
//							+  "{'DoctorId':'3', 'DoctorName':'Mainak', 'DoctorCategory': 'Inhouse'} ]}";
		
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
				Log.d("DoctorName", jsons[i].getString("DoctorName"));
				arrayAdapter.add(jsons[i].getString("DoctorName"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		builderSingle.setAdapter(arrayAdapter,new DialogInterface.OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String DoctorId = null;
				try {
					DoctorId = jsons[which].getString("DoctorId");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				Calendar beginCal = Calendar.getInstance();
				
				
				String Date_Time = QE.SetAppointment(DoctorId, UserId);
				if(Date_Time.equals("FAILURE"))
				{
					return;
				}
				String year = Date_Time.substring(0, 4);
				String month = Date_Time.substring(5, 7);
				String day = Date_Time.substring(8, 10);
				String hour = Date_Time.substring(11, 13);
				String minute = Date_Time.substring(14, 16);
				Log.d("ymdhm", year + "-" + month + "-" + day + "   " + hour + ":" + minute);
                beginCal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(minute));
                long startTime = beginCal.getTimeInMillis();
                
				Intent intent = new Intent(Intent.ACTION_EDIT);
				intent.setType("vnd.android.cursor.item/event");
				intent.putExtra("beginTime", startTime);
				intent.putExtra("allDay", false);
				intent.putExtra("rrule", "FREQ=DAILY");
				intent.putExtra("endTime", startTime + 2*60*1000);
				try {
					intent.putExtra("title", "You have an appointment with " + jsons[which].getString("DoctorName"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				startActivity(intent);
				
				
			}
		});
		builderSingle.show();

	}
}
