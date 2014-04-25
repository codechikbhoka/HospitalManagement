package com.bhargav.hospitalmanagement.user;

public class PatientObj {
	
	//private variables
	String _name;
	String _date;
	String _time;
	String _id;
	
	
	// Empty constructor
	public PatientObj(){
		
	}
	// constructor
	public PatientObj(String name, String date, String time, String id){
		this._name = name;
		this._date = date;
		this._time = time;
		this._id = id;
	}
		
	// getting name
	public String getName(){
		return this._name;
	}
	
	// setting event name
	public void setName(String name){
		this._name = name;
	}
	
	// getting phone number
	public String getDate(){
		return this._date;
	}
	
	// setting event day(0,1,2,3)
	public void setDate(String Date){
		this._date= Date;
	}
	
	public String getTime(){
		return this._time;
	}
	
	// setting event start-time
	public void setTime(String Time){
		this._time = Time ;
	}
	
	public String getId(){
		return this._id;
	}
	
	// setting event start-time
	public void setId(String Id){
		this._id = Id ;
	}
	
	
}

