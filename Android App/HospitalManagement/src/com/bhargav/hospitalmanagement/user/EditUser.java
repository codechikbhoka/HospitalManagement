package com.bhargav.hospitalmanagement.user;

import com.bhargav.hospitalmanagement.QueryExecutor;
import com.bhargav.hospitalmanagement.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditUser extends Activity implements OnClickListener{

	TextView UserID;
	EditText UserName,UserPassword,UserAge,UserSex,UserAddress,UserPhone;
	Button BtnOK; 
	String UserId,Mode;
	String passed_UserID,passed_UserName,passed_UserPassword,passed_UserAge,passed_UserSex,passed_UserAddress, passed_UserPhone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.user_edituser);
		
		
		Bundle bundle = getIntent().getExtras();

		if(bundle.getString("UserId")!= null)
		{
			this.UserId = bundle.getString("UserId");
		}
		
		
		
		UserID = (TextView) findViewById(R.id.UserID);
		UserName = (EditText) findViewById(R.id.UserName);
		UserPassword = (EditText) findViewById(R.id.UserPassword);
		UserAge = (EditText) findViewById(R.id.UserAge);
		UserSex = (EditText) findViewById(R.id.UserSex);
		UserAddress = (EditText) findViewById(R.id.UserAddress);
		UserPhone = (EditText) findViewById(R.id.UserPhone);
		BtnOK = (Button) findViewById(R.id.BtnOK);
		
		
		
		
		UserID.setText("USER ID : " + UserId);

		Mode = "Editable";
		if(bundle.getString("Mode")!= null)
		{
			this.Mode = bundle.getString("Mode");
		}
		
		if(Mode.equals("ShowOnly"))
		{
			
			this.passed_UserID = bundle.getString("passed_UserID");
			this.passed_UserName = bundle.getString("passed_UserName");
			this.passed_UserPassword = bundle.getString("passed_UserPassword");
			this.passed_UserAge = bundle.getString("passed_UserAge");
			this.passed_UserSex = bundle.getString("passed_UserSex");
			this.passed_UserAddress = bundle.getString("passed_UserAddress");
			this.passed_UserPhone = bundle.getString("passed_UserPhone");
			
			UserName.setKeyListener(null);
			UserPassword.setKeyListener(null);
			UserAge.setKeyListener(null);
			UserSex.setKeyListener(null);
			UserAddress.setKeyListener(null);
			UserPhone.setKeyListener(null);
			BtnOK.setVisibility(View.GONE);
			
			UserID.setText("USER ID : " + passed_UserID);
			UserName.setText(passed_UserName);
			UserPassword.setText(passed_UserPassword);
			UserAge.setText(passed_UserAge);
			UserSex.setText(passed_UserSex);
			UserAddress.setText(passed_UserAddress);
			UserPhone.setText(passed_UserPhone);
			
		}
		
		
		BtnOK.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) 
		{
		case R.id.BtnOK:
			QueryExecutor QE = new QueryExecutor(EditUser.this);
			QE.EditUser(
					UserId,
					UserName.getText().toString(),
					UserPassword.getText().toString(),
					UserAge.getText().toString(),
					UserSex.getText().toString(),
					UserAddress.getText().toString(),
					UserPhone.getText().toString()        );
			finish();
			break;
		default:
			break;
		}
	}
	
}
