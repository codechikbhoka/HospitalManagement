package com.bhargav.hospitalmanagement;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class QueryExecutor {

	public String SERVER_ADDRESS = "192.168.0.110";
	Context context;

	public QueryExecutor(Context context){
		this.context = context;
		loadSavedPreferences(context);
	}
	
	private void loadSavedPreferences(Context context) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		SERVER_ADDRESS = sharedPreferences.getString("ip", "192.168.0.110");
		Log.d("SERVERADDRESS", "SERVERADDRESS changed to " + SERVER_ADDRESS);
	}

	public void popup(List<NameValuePair> args_array, String TARGET_URL, String Entity)
	{
		
		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);
		
		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		Log.d("Login", output);

		if(output.equals("FAILURE"))
		{
			Toast.makeText(context, Entity + " Registration Error!", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(context, Entity + " Added Successfully!", Toast.LENGTH_SHORT).show();
		}
	}

	public void Register(String UserID, String UserName , String Password)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/Register/RegisterUser.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("UserId", UserID));
		args_array.add(new BasicNameValuePair("UserName", UserName));
		args_array.add(new BasicNameValuePair("UserPassword", Password));

		popup(args_array, TARGET_URL, "User");
	}

	public String Login(String UserId, String password)
	{
		
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/Login.php";
		Log.d("ip", TARGET_URL);
		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("UserId", UserId));
		args_array.add(new BasicNameValuePair("Password", password));
		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}

		if(output.equals("FAILURE"))
		{
			Toast.makeText(context,"User Unauthorised!", Toast.LENGTH_SHORT).show();
		}

		Log.d("Login", output);
		return output;
	}

	public String DoctorLogin(String DoctorId, String password)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/DoctorLogin.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("DoctorId", DoctorId));
		args_array.add(new BasicNameValuePair("Password", password));
		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}

		if(output.equals("FAILURE"))
		{
			Toast.makeText(context,"Doctor Unauthorised!", Toast.LENGTH_SHORT).show();
		}

		Log.d("Doctor Login", output);
		return output;
	}

	public String GetDoctorList(String DoctorType)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/GetDoctorList.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("DoctorType", DoctorType));

		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		if(output.equals("FAILURE"))
		{
			Toast.makeText(context,"Empty Doctor List!", Toast.LENGTH_SHORT).show();
		}
		Log.d("Doctor List", output);
		return output;
	}

	public String SetAppointment(String DoctorId, String UserId)

	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/SetAppointment.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("DoctorId", DoctorId));
		args_array.add(new BasicNameValuePair("UserId", UserId));

		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		if(output.equals("FAILURE"))
		{
			Toast.makeText(context,"Appointment Setting Failure!", Toast.LENGTH_SHORT).show();
		}
		Log.d("Set Appointment", output);
		return output;
	}

	public String CallAmbulance(String UserId)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/CallAmbulance.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("UserId", UserId));
		
		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		if(output.equals("FAILURE"))
		{
			Toast.makeText(context,"No Phone Number Available!", Toast.LENGTH_SHORT).show();
		}
		Log.d("Call Ambulance", output);
		return output;
	}

	public String CallNurse(String UserId)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/CallNurse.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("UserId", UserId));

		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		if(output.equals("FAILURE"))
		{
			Toast.makeText(context,"No Phone Number Available!", Toast.LENGTH_SHORT).show();
		}
		Log.d("Call Nurse", output);
		return output;
	}

	public String GetUserHistory(String UserId)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/UserHistory.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("UserId", UserId));

		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		if(output.equals("FAILURE"))
		{
			Toast.makeText(context,"Empty History!", Toast.LENGTH_SHORT).show();
		}
		Log.d("UserHistory", output);
		return output;
	}

	public String GetDoctorSchedule(String DoctorId)

	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/GetDoctorSchedule.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("DoctorId", DoctorId));

		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		if(output.equals("FAILURE"))
		{
			Toast.makeText(context,"No Patient On Your Schedule!", Toast.LENGTH_SHORT).show();
		}
		Log.d("Doctor Schedule", output);
		return output;
	}

	public String GetPatientInfo(String PatientId)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/GetPatientInfo.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("PatientId", PatientId));

		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		return output;
	}

	public String EditUser(String UserId, String UserName, String UserPassword, String UserAge, String UserSex, String UserAddress, String UserPhone)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/EditUser.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("UserId", UserId));
		args_array.add(new BasicNameValuePair("UserName", UserName ));
		args_array.add(new BasicNameValuePair("UserPassword", UserPassword ));
		args_array.add(new BasicNameValuePair("UserAge", UserAge ));
		args_array.add(new BasicNameValuePair("UserSex", UserSex ));
		args_array.add(new BasicNameValuePair("UserAddress", UserAddress ));
		args_array.add(new BasicNameValuePair("UserPhone", UserPhone ));

		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		return output;
	}

	public String GetUser(String UserId)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/GetUser.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("UserId", UserId));

		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		return output;
	}

	public void RegisterDoctor(String DoctorName, String DoctorPassword, String DoctorAge, String DoctorSex, String DoctorAddress, String DoctorJoinDate, String DoctorQualification, String DoctorType, String DoctorCategory, String DoctorPhone, String DoctorStartTime, String DoctorEndTime)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/Register/RegisterDoctor.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("DoctorName", DoctorName )); 
		args_array.add(new BasicNameValuePair("DoctorPassword", DoctorPassword )); 
		args_array.add(new BasicNameValuePair("DoctorAge", DoctorAge )); 
		args_array.add(new BasicNameValuePair("DoctorSex", DoctorSex )); 
		args_array.add(new BasicNameValuePair("DoctorAddress", DoctorAddress )); 
		args_array.add(new BasicNameValuePair("DoctorJoinDate", DoctorJoinDate )); 
		args_array.add(new BasicNameValuePair("DoctorQualification", DoctorQualification )); 
		args_array.add(new BasicNameValuePair("DoctorType", DoctorType )); 
		args_array.add(new BasicNameValuePair("DoctorCategory", DoctorCategory )); 
		args_array.add(new BasicNameValuePair("DoctorPhone", DoctorPhone )); 
		args_array.add(new BasicNameValuePair("DoctorStartTime", DoctorStartTime )); 
		args_array.add(new BasicNameValuePair("DoctorEndTime", DoctorEndTime ));

		popup(args_array, TARGET_URL, "Doctor");
	}

	public void AddAmbulance(String AmbulanceId, String AmbulancePhone, String  Supervisor_Id)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/Register/RegisterAmbulance.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("AmbulancePhone", AmbulancePhone));
		args_array.add(new BasicNameValuePair("Supervisor_Id", Supervisor_Id));
		args_array.add(new BasicNameValuePair("AmbulanceId", AmbulanceId));

		popup(args_array, TARGET_URL, "Ambulance");
	}

	public void AddDepartment(String DepartmentId, String  DepartmentName, String Supervisor_Id, String Phone)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/Register/RegisterDepartment.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("DepartmentId", DepartmentId));
		args_array.add(new BasicNameValuePair("DepartmentName", DepartmentName));
		args_array.add(new BasicNameValuePair("SupervisorName", Supervisor_Id));
		args_array.add(new BasicNameValuePair("Phone", Phone));

		popup(args_array, TARGET_URL, "Department");


	}

	public void AddWard(String WardId, String  WardPhone, String Supervisor_Id)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/Register/RegisterWard.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("WardId", WardId));
		args_array.add(new BasicNameValuePair("WardPhone", WardPhone));
		args_array.add(new BasicNameValuePair("Supervisor_Id", Supervisor_Id));

		popup(args_array, TARGET_URL, "Ward");
	}

	public void AddLabTest(String Lab_TestId, String  Lab_TestName, String Lab_TestPhone, String Supervisor_Id)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/Register/RegisterLabTest.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("Lab_TestId", Lab_TestId));
		args_array.add(new BasicNameValuePair("Lab_TestName", Lab_TestName));
		args_array.add(new BasicNameValuePair("Lab_TestPhone", Lab_TestPhone));
		args_array.add(new BasicNameValuePair("Supervisor_Id", Supervisor_Id));

		popup(args_array, TARGET_URL, "Lab Test");
	}

	public void AddPharmacy(String PharmacyId, String  PharmacyName, String PharmacyPhone, String PharmacyAddress, String Supervisor_Id)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/Register/RegisterPharmacy.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("PharmacyId", PharmacyId));
		args_array.add(new BasicNameValuePair("PharmacyName", PharmacyName));
		args_array.add(new BasicNameValuePair("PharmacyPhone", PharmacyPhone));
		args_array.add(new BasicNameValuePair("Supervisor_Id", Supervisor_Id));
		args_array.add(new BasicNameValuePair("PharmacyAddress", PharmacyAddress));


		popup(args_array, TARGET_URL, "Pharmacy");
	}


	public String PrescribeMedicine(String DoctorId, String PatientId, String PharmacyId, String  MedicineId, String  Days, String Dosage, String Disease, String T1, String T2, String T3, String T4)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/PrescribeMedicine.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("DrId", DoctorId));
		args_array.add(new BasicNameValuePair("PatientId", PatientId));
		args_array.add(new BasicNameValuePair("PharmacyId", PharmacyId));
		args_array.add(new BasicNameValuePair("MedicineId", MedicineId));
		args_array.add(new BasicNameValuePair("Days", Days));
		args_array.add(new BasicNameValuePair("Dosage", Dosage));
		args_array.add(new BasicNameValuePair("Disease", Disease));
		args_array.add(new BasicNameValuePair("Time1", T1));
		args_array.add(new BasicNameValuePair("Time2", T2));
		args_array.add(new BasicNameValuePair("Time3", T3));
		args_array.add(new BasicNameValuePair("Time4", T4));

		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		Log.d("Prescribe Medicine", output);
		return output;

	}

	public String PrescribeLabTest(String DoctorId, String PatientId, String LabTestId)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/PrescribeTestsRegister.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("DrId", DoctorId));
		args_array.add(new BasicNameValuePair("PatientId", PatientId));
		args_array.add(new BasicNameValuePair("LabTestId", LabTestId));

		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		Log.d("Prescribe Medicine", output);
		return output;

	}
	
	public String GetLabTestList()
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/GetLabTestList.php";
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		
		
		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);
		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		return output;
	}
	
	public String GetMedicineList()
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/GetMedicineList.php";
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();

		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		return output;
	}

	public String GetPharmacyFromMedicine(String MedicineId)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/GetPharmacyFromMedicine.php";
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();

		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);
		args_array.add(new BasicNameValuePair("MedicineId", MedicineId));


		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		return output;
	}

	public String GetBillInfo(String UserId)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/GetBillInfo.php";
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("UserId", UserId));


		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		return output;
	}

	public String GetPrescribedTests(String UserId)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/PrescribedTests.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("UserId", UserId));

		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		return output;
	}

	public String GetPrescribedMedicines(String UserId)
	{
		String TARGET_URL = "http://" + SERVER_ADDRESS + "/Project/PrescribedMedicines.php";

		// Building Parameters
		List<NameValuePair> args_array = new ArrayList<NameValuePair>();
		args_array.add(new BasicNameValuePair("UserId", UserId));

		ThreadExecute TE = new ThreadExecute(context, args_array, TARGET_URL);

		String output = null;
		try 
		{
			output =  TE.execute().get();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		}
		return output;
	}
}




