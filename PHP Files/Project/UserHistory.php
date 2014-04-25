<?php
	if(!empty($_POST))
	{
		require "function.php";
		$UserId = $_POST['UserId'];
		$query="Select doctors.DOCTOR_NAME,DATE_FORMAT(consults.Consult_Date_Time,'%H:%i') TIMEONLY,DATE_FORMAT(consults.Consult_Date_Time,'%d-%m-%y') DATEONLY FROM users,userpatient,patient,consults,doctors 
				where
 				users.User_Id=userpatient.User_Id AND userpatient.Patient_Id=patient.Patient_Id AND patient.Patient_Id=consults.Patient_Id AND consults.Doctor_Id=doctors.Doctor_Id AND users.USER_ID=:UserId";
 		$query_params=array(
 			':UserId'=> $UserId
 			);
 		$stmt=ExecuteQuery($query,$query_params);
 		$rows=$stmt->fetchAll();
 		if($rows)
 		{
 			$response["success"]=1;
 			$response["status"]="History";
 			$response["message"]=array();
 			//var_dump($rows);
 			foreach($rows as $row)
 			{
 				$History    = array();
				$History["DoctorName"]  = $row["DOCTOR_NAME"];
		        $History["Date"] = $row["DATEONLY"];
		        $History["Time"] = $row["TIMEONLY"];		
		        array_push($response["message"], $History);
 			}
 			echo json_encode($response);	
 		}
 		else
 		{
 			$response["success"]=0;
 			$response["status"]="No History";
 			echo json_encode($response);
 		}
	}
	else
	{
		?>
		<h1>UserId</h1> 
	    <form action="UserHistory.php" method="post"> 
	            UserId:<br /> 
	            <input type="text" name="UserId" placeholder="UserId" /> 
	            <br /> <br /> 
	            <input type="submit" values="Submit">
	    </form>
	<?php
	}
?>