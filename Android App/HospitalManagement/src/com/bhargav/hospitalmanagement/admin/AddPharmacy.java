package com.bhargav.hospitalmanagement.admin;

import com.bhargav.hospitalmanagement.QueryExecutor;
import com.bhargav.hospitalmanagement.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddPharmacy extends Activity implements OnClickListener{

	EditText tv0,tv1,tv2,tv3,tv4,tv5,tv6;
	Button BtnOK; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.admin_addpharmacy);
		
		tv0 = (EditText)findViewById(R.id.tv0);
		tv1 = (EditText)findViewById(R.id.tv1);
		tv2 = (EditText)findViewById(R.id.tv2);
		tv3 = (EditText)findViewById(R.id.tv3);
		tv4 = (EditText)findViewById(R.id.tv4);
		tv5 = (EditText)findViewById(R.id.tv5);
		tv6 = (EditText)findViewById(R.id.tv6);

		BtnOK = (Button) findViewById(R.id.BtnOK);
		
		
		BtnOK.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) 
		{
		case R.id.BtnOK:
			QueryExecutor QE = new QueryExecutor(AddPharmacy.this);
						
			QE.AddPharmacy(
					tv0.getText().toString(),
					tv1.getText().toString(),
					tv2.getText().toString(),
					tv3.getText().toString() + " " +
					tv4.getText().toString() + " " +
					tv5.getText().toString(),
					tv6.getText().toString());
			finish();
			break;
		default:
			break;
		}
	}
	
}
