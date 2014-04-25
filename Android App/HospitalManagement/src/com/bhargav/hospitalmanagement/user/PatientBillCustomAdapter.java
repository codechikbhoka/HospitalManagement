package com.bhargav.hospitalmanagement.user;


import java.util.ArrayList;
import com.bhargav.hospitalmanagement.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PatientBillCustomAdapter extends BaseAdapter {

	private ArrayList<BillObj> _data;
	Context _c;

	public PatientBillCustomAdapter (ArrayList<BillObj> data, Context c){
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
			v = vi.inflate(R.layout.user_listrow_mybill, null);
		}



		TextView tv_BillId = (TextView) v.findViewById(R.id.tv_BillId);
		TextView tv_Amount = (TextView) v.findViewById(R.id.tv_Amount);
		TextView tv_Date = (TextView) v.findViewById(R.id.tv_Date); 
		TextView tv_Time = (TextView) v.findViewById(R.id.tv_Time); 
		

		BillObj bill = _data.get(position);
		tv_BillId.setText("Bill No. : " + bill.getBillId());
		tv_Amount.setText("Amount : " + bill.getAmount() + "/-");
		tv_Date.setText(bill.getDate());
		tv_Time.setText(bill.getTime());


		return v;
	}
}

