<?php
if(!empty($_POST))
{
	require "function.php";
	$UserId=$_POST['UserId'];
	$query="Select T1.Patient_Id 
	From
		(
		    SELECT patient.Patient_Id,T2.Consult_Date_Time
		    FROM users,userpatient,patient,consults AS T2
		    Where users.User_Id=:UserId and users.User_Id=userpatient.User_ID and 	  userpatient.Patient_Id=patient.Patient_Id and patient.Patient_Id=T2.Patient_Id and T2.Consult_Date_Time = 
		    (
		    
		        SELECT MAX(consults.Consult_Date_Time)
		        FROM users,userpatient,patient,consults
		        Where users.User_Id=:UserId and users.User_Id=userpatient.User_ID and userpatient.Patient_Id=patient.Patient_Id and patient.Patient_Id=consults.Patient_Id
			)
		) AS T1
	Where
		T1.Patient_Id In
		(
		   Select patient.Patient_Id from
		    patient,patientinpatient,inpatient AS T2
		    Where patient.Patient_Id=patientinpatient.Patient_Id AND patientinpatient.In_Patient_Id=T2.In_Patient_Id AND T2.In_Patient_Id NOT IN
		    (
		    	Select In_Patient_Id from inpatientoutpatient
		    )
		    AND T2.In_Patient_Id NOT IN
		    (
		    	Select In_Patient_Id from inpatientcasualty
		    )
	)";
	$query_params=array(
		':UserId' => $UserId
		);
	$stmt=ExecuteQuery($query,$query_params);
	$row=$stmt->fetch();
	$Patient_Id=$row['Patient_Id'];
	//var_dump($row);
	if($row)
	{
		$response["success"]=1;
		$response["status"]="Calling Nurse!!!";
		$query="Select Staff_Phone From 
patient,admitted_to,department,works_for_deptt_nurses,nurses,staffnurse,staff 
WHERE
patient.Patient_Id=:PatientId AND patient.Patient_Id = admitted_to.Patient_Id And admitted_to.Deptt_Id=department.Department_Id AND department.Department_Id=works_for_deptt_nurses.Deptt_Id AND works_for_deptt_nurses.Nurse_Id=nurses.Nurse_Id AND nurses.Nurse_Id=staffnurse.Nurse_Id AND staffnurse.Staff_Id = staff.Staff_Id;";
	$query_params=array(
		':PatientId' => $Patient_Id
		);
		$stmt=ExecuteQuery($query,$query_params);
		$row=$stmt->fetch();
		$response["message"]=$row["Staff_Phone"];
		echo json_encode($response);
	}
	else
	{
		$response["success"]=0;
		$response["status"]="Patient is Not Admitted!!";
		echo json_encode($response);
	}
}








else
	{
		?>
		<h1>Doctor Type</h1> 
	    <form action="CallNurse.php" method="post"> 
	            UserId<br /> 
	            <input type="text" name="UserId" placeholder="UserId" /> 
	        </br></br>
	            <input type="submit" values="Submit">
	    </form>

		<?php
	}
	?>