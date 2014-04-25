package com.bhargav.hospitalmanagement.doctor;

import java.util.Calendar;

import com.bhargav.hospitalmanagement.QueryExecutor;
import com.bhargav.hospitalmanagement.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class RegisterDoctor extends Activity implements OnClickListener{

	TextView DoctorID, tv_T1, tv_T2, T_Temp;
	EditText DoctorName, DoctorPassword, DoctorAge, DoctorSex, DoctorAddress, DoctorJoinDate, DoctorQualification, DoctorType, DoctorCategory, DoctorPhone;
	Button BtnOK; 
	private Spinner spinner1, spinner2;
	private Calendar cal;
	private int hour, min;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.doctor_registerdoctor);

		DoctorName = (EditText) findViewById(R.id.DoctorName);
		DoctorPassword = (EditText) findViewById(R.id.DoctorPassword);
		DoctorAge = (EditText) findViewById(R.id.DoctorAge);
		DoctorSex = (EditText) findViewById(R.id.DoctorSex);
		DoctorAddress = (EditText) findViewById(R.id.DoctorAddress);
		DoctorJoinDate = (EditText) findViewById(R.id.DoctorJoinDate);
		DoctorQualification = (EditText) findViewById(R.id.DoctorQualification);
		spinner2 = (Spinner) findViewById(R.id.SpinnerType);
		spinner1 = (Spinner) findViewById(R.id.SpinnerCategory);
		DoctorPhone = (EditText) findViewById(R.id.DoctorPhone);
		tv_T1 = (TextView) findViewById(R.id.tv_T1);
		tv_T2 = (TextView) findViewById(R.id.tv_T2);
		BtnOK = (Button) findViewById(R.id.BtnOK);

		tv_T1.setOnClickListener(this);
		tv_T2.setOnClickListener(this);
		BtnOK.setOnClickListener(this);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) 
		{
		case R.id.BtnOK:
			QueryExecutor QE = new QueryExecutor(RegisterDoctor.this);
			QE.RegisterDoctor(
					DoctorName.getText().toString(),
					DoctorPassword.getText().toString(),
					DoctorAge.getText().toString(),
					DoctorSex.getText().toString(),
					DoctorAddress.getText().toString(),
					DoctorJoinDate.getText().toString(),
					DoctorQualification.getText().toString(),
					spinner2.getSelectedItem().toString(),
					spinner1.getSelectedItem().toString(),
					DoctorPhone.getText().toString(),
					tv_T1.getText().toString(),
					tv_T2.getText().toString());
			finish();
			break;
		case R.id.tv_T1:
			T_Temp = tv_T1;
			showDialog(0);
			break;
		case R.id.tv_T2:
			T_Temp = tv_T2;
			showDialog(0);
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


}
