package com.bhargav.hospitalmanagement.user;

public class BillObj {
	
	//private variables
	String _BillId;
	String _Date;
	String _Time;
	String _Amount;
	
	
	// Empty constructor
	public BillObj(){
		
	}
	// constructor
	public BillObj(String BillId, String Date, String Time, String Amount){
		this._BillId = BillId;
		this._Date = Date;
		this._Time = Time;
		this._Amount = Amount;
	}
		
	// getting Bill ID
	public String getBillId(){
		return this._BillId;
	}
	
	// setting Bill ID
	public void setBillId(String BillId){
		this._BillId = BillId;
	}
	
	// getting Date
	public String getDate(){
		return this._Date;
	}
	
	// setting event day(0,1,2,3)
	public void setDate(String Date){
		this._Date= Date;
	}
	
	public String getTime(){
		return this._Time;
	}
	
	// setting event start-time
	public void setTime(String Time){
		this._Time = Time ;
	}
	
	public String getAmount(){
		return this._Amount;
	}
	
	// setting event start-time
	public void setAmount(String Amount){
		this._Amount = Amount ;
	}
	
	
}

