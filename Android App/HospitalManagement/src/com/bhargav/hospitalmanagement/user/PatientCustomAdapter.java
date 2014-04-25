package com.bhargav.hospitalmanagement.user;


import java.util.ArrayList;

import com.bhargav.hospitalmanagement.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PatientCustomAdapter extends BaseAdapter {

	private ArrayList<PatientObj> _data;
	Context _c;

	public PatientCustomAdapter (ArrayList<PatientObj> data, Context c){
		_data = data;
		_c = c;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return _data.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _data.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null)
		{
			LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.doctor_listrow_doc_schedule, null);
		}



		TextView tv_Patient = (TextView) v.findViewById(R.id.tv_Patient);
		TextView tv_ViewInfo = (TextView) v.findViewById(R.id.tv_ViewInfo);
		TextView tv_Date = (TextView) v.findViewById(R.id.tv_Date); 
		TextView tv_Time = (TextView) v.findViewById(R.id.tv_Time); 
		

		PatientObj patient = _data.get(position);
		tv_Patient.setText(patient.getName());
		tv_ViewInfo.setText("View Info");
		tv_Date.setText(patient.getDate());
		tv_Time.setText(patient.getTime());


		return v;
	}
}

