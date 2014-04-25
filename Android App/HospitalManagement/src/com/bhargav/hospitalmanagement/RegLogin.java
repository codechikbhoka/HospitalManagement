package com.bhargav.hospitalmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RegLogin extends Activity implements OnClickListener{

	Button btnRegister, btnLogin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.zzz_reglogin);
		btnRegister = (Button) findViewById(R.id.Register);
		btnLogin = (Button) findViewById(R.id.Login);
		
		btnRegister.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) 
		{
		case R.id.Register:
			Intent i2 = new Intent(RegLogin.this, Register.class);
			startActivity(i2);
			finish();
			break;
		case R.id.Login:
			Intent i3 = new Intent(RegLogin.this, Login.class);
			i3.putExtra("WHO", "User");
			startActivity(i3);
			finish();
			break;
		default:
			break;
		}
	}

}
