package com.bhargav.hospitalmanagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener{

	private EditText userid, username, pass;
	private Button  mRegister;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_register);

		userid = (EditText)findViewById(R.id.et_userid);
		username = (EditText)findViewById(R.id.et_username);
		pass = (EditText)findViewById(R.id.et_password);


		mRegister = (Button)findViewById(R.id.btn_register);
		mRegister.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		QueryExecutor QE = new QueryExecutor(Register.this);
		QE.Register(userid.getText().toString(),username.getText().toString(), pass.getText().toString());
		finish();
	}


}
