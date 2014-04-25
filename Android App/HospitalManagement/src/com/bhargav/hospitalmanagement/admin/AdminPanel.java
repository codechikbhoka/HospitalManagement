package com.bhargav.hospitalmanagement.admin;

import com.bhargav.hospitalmanagement.R;
import com.bhargav.hospitalmanagement.doctor.RegisterDoctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AdminPanel extends Activity implements OnClickListener{


	private TextView tv1, tv2, tv3, tv4, tv5, tv6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_adminpanel);



		//setup buttons
		tv1 = (TextView)findViewById(R.id.tvtv1);
		tv2 = (TextView)findViewById(R.id.tvtv2);
		tv3 = (TextView)findViewById(R.id.tvtv3);
		tv4 = (TextView)findViewById(R.id.tvtv4);
		tv5 = (TextView)findViewById(R.id.tvtv5);
		tv6 = (TextView)findViewById(R.id.tvtv6);

		//register listeners
		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
		tv3.setOnClickListener(this);
		tv4.setOnClickListener(this);
		tv5.setOnClickListener(this);
		tv6.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) 
		{
		case R.id.tvtv1:
			Intent i6 = new Intent(AdminPanel.this, RegisterDoctor.class);
			startActivity(i6);
			break;

		case R.id.tvtv2:
			Intent i7 = new Intent(AdminPanel.this, AddPharmacy.class);
			startActivity(i7);
			break;
		case R.id.tvtv3:
			Intent i8 = new Intent(AdminPanel.this, AddLab.class);
			startActivity(i8);
			break;
		case R.id.tvtv4:
			Intent i9 = new Intent(AdminPanel.this, AddDepartment.class);
			startActivity(i9);
			break;
		case R.id.tvtv5:
			Intent i10 = new Intent(AdminPanel.this, AddWard.class);
			startActivity(i10);
			break;
		case R.id.tvtv6:
			Intent i11 = new Intent(AdminPanel.this, AddAmbulance.class);
			startActivity(i11);
			break;
		default:
			break;
		}

	}
}
