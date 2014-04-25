package com.bhargav.hospitalmanagement;

import com.bhargav.hospitalmanagement.QueryExecutor;
import com.bhargav.hospitalmanagement.R;
import com.bhargav.hospitalmanagement.doctor.DoctorPage;
import com.bhargav.hospitalmanagement.user.Userpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity implements OnClickListener{

	private EditText userid, pass;
	private Button mLogin;
	private String WHO;
	private TextView tv_x;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login);

		Bundle bundle = getIntent().getExtras();

		if(bundle.getString("WHO")!= null)
		{
			this.WHO = bundle.getString("WHO");
		}
		
	
		//setup input fields
		userid = (EditText)findViewById(R.id.et_UserId);
		pass = (EditText)findViewById(R.id.et_password);
		tv_x = (TextView)findViewById(R.id.tv_userid);
		if(WHO.equals("Doctor"))
		{
			tv_x.setText("Doctor Login ID");
		}
		else
		{
			tv_x.setText("User Login ID");
		}

		//setup buttons
		mLogin = (Button)findViewById(R.id.btn_login);
		//register listeners
		mLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		QueryExecutor QE = new QueryExecutor(Login.this);

		switch (v.getId()) 
		{
		case R.id.btn_login:
			if(WHO.equals("Doctor"))
			{
				if(!QE.DoctorLogin(userid.getText().toString(), pass.getText().toString()).equals("FAILURE"))
				{
					Intent i5 = new Intent(Login.this, DoctorPage.class);
					i5.putExtra("DoctorId", userid.getText().toString());
					startActivity(i5);
					finish();
				}
				
			}
			else
			{
				if(!QE.Login(userid.getText().toString(), pass.getText().toString()).equals("FAILURE"))
				{
					Intent i5 = new Intent(Login.this, Userpage.class);
					i5.putExtra("UserId", userid.getText().toString());
					startActivity(i5);
					finish();
				}
			}

			break;
		default:
			break;
		}

	}
}
