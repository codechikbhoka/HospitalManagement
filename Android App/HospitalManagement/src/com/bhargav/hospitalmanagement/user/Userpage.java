package com.bhargav.hospitalmanagement.user;

import com.bhargav.hospitalmanagement.QueryExecutor;
import com.bhargav.hospitalmanagement.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Userpage extends Activity implements OnClickListener{


	private TextView appointment, history, ambulance, nurse, info, profile;
	private String UserId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_userpage);

		Bundle bundle = getIntent().getExtras();

		if(bundle.getString("UserId")!= null)
		{
			this.UserId = bundle.getString("UserId");
		}

		//setup buttons
		appointment = (TextView)findViewById(R.id.tv_appointment);
		history = (TextView)findViewById(R.id.tv_history);
		ambulance = (TextView)findViewById(R.id.tv_ambulance);
		nurse = (TextView)findViewById(R.id.tv_nurse);
		info = (TextView)findViewById(R.id.tv_info);
		profile = (TextView)findViewById(R.id.tv_profile);

		//register listeners
		appointment.setOnClickListener(this);
		history.setOnClickListener(this);
		ambulance.setOnClickListener(this);
		nurse.setOnClickListener(this);
		info.setOnClickListener(this);
		profile.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		QueryExecutor QE = new QueryExecutor(Userpage.this);

		switch (v.getId()) 
		{
		case R.id.tv_appointment:
			Intent i6 = new Intent(Userpage.this, Appointment.class);
			i6.putExtra("UserId", UserId);
			startActivity(i6);
			break;
		case R.id.tv_history:
			Intent i7 = new Intent(Userpage.this, UserHistory.class);
			i7.putExtra("UserId", UserId);
			startActivity(i7);
			break;
		case R.id.tv_ambulance:
			String PhoneNumberAmbulance = QE.CallAmbulance(UserId);
			if(PhoneNumberAmbulance.equals("FAILURE"))
			{
				break;
			}
			Intent callIntent1 = new Intent(Intent.ACTION_CALL);
			callIntent1.setData(Uri.parse("tel:" + PhoneNumberAmbulance));
			callIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			startActivity(callIntent1);
			break;
		case R.id.tv_nurse:
			String PhoneNumberNurse = QE.CallNurse(UserId);
			if(PhoneNumberNurse.equals("FAILURE"))
			{
				break;
			}
			Intent callIntent2 = new Intent(Intent.ACTION_CALL);
			callIntent2.setData(Uri.parse("tel:" + PhoneNumberNurse));
			callIntent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			startActivity(callIntent2);
			break;
		case R.id.tv_info:

			AlertDialog.Builder builderSingle = new AlertDialog.Builder(Userpage.this);
			builderSingle.setIcon(R.drawable.ic_launcher);
			builderSingle.setTitle("My Info");
			final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Userpage.this,android.R.layout.select_dialog_singlechoice);
			builderSingle.setNegativeButton("cancel",new DialogInterface.OnClickListener() 
			{
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});

			arrayAdapter.add("My Bill");
			arrayAdapter.add("Prescribed Medicines");
			arrayAdapter.add("Prescribed Tests");

			builderSingle.setAdapter(arrayAdapter,new DialogInterface.OnClickListener() 
			{
				@Override
				public void onClick(DialogInterface dialog, int which) {

					switch(which)
					{
					case 0 :
						Intent i12 = new Intent(Userpage.this, MyBill.class);
						i12.putExtra("UserId", UserId);
						startActivity(i12);
						break;
					case 1:
						Intent i13 = new Intent(Userpage.this, PrescribedMedicines.class);
						i13.putExtra("UserId", UserId);
						startActivity(i13);
						break;
					case 2 :
						Intent i14 = new Intent(Userpage.this, PrescribedTests.class);
						i14.putExtra("UserId", UserId);
						startActivity(i14);
						break;
					case 3:
						break;
					}


				}
			});
			builderSingle.show();


			break;
		case R.id.tv_profile :
			Intent i10 = new Intent(Userpage.this, EditUser.class);
			i10.putExtra("UserId", UserId);
			startActivity(i10);
			break;

		default:
			break;
		}

	}
}
