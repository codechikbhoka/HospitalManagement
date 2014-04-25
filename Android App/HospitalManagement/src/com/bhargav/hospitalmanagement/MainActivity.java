package com.bhargav.hospitalmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.bhargav.hospitalmanagement.admin.AdminPanel;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	TextView go;
	EditText ip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zzz_activity_main);

		go = (TextView) findViewById(R.id.tv_go);
		ip = (EditText) findViewById(R.id.et_ip);
		go.setOnClickListener(this);
		final ListView listview = (ListView) findViewById(R.id.welcomelist);
		String[] values = new String[] { "USER", "ADMIN", "DOCTOR"};

		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}
		final StableArrayAdapter2 adapter = new StableArrayAdapter2(this,android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {

				switch(position)
				{
				case 0 :
					Intent i1 = new Intent(MainActivity.this, RegLogin.class);
					startActivity(i1);
					break;
				case 1 :
					Intent i2 = new Intent(MainActivity.this, AdminPanel.class);
					startActivity(i2);
					break;
				case 2 :
					Intent i3 = new Intent(MainActivity.this, Login.class);
					i3.putExtra("WHO", "Doctor");
					startActivity(i3);
					break;
				default:
					break;

				}
			}

		});
	}

	private class StableArrayAdapter2 extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter2(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@SuppressLint("CommitPrefEdits")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
			SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
			Editor editor = sharedPreferences.edit();
			editor.putString("ip", ip.getText().toString());
			editor.commit();

	}

}
