<?php
	require "function.php";
	if(!empty($_POST))
	{
		$DoctorType = $_POST['DoctorType'];
		$query = "SELECT doctors.Doctor_Id,Doctor_Name 
				FROM doctors,inhouse,doctorsinhouse
				WHERE doctors.Doctor_Id=doctorsinhouse.Doctor_Id AND doctorsinhouse.In_House_Id=inhouse.In_House_Id and inhouse.Category = :DoctorType";
		$query_params=array(
			':DoctorType'=> $_POST['DoctorType']
		);
		$stmt=ExecuteQuery($query,$query_params);
		$rows1=$stmt->fetchAll();
		//var_dump($rows1);
		$query= "SELECT doctors.Doctor_Id,Doctor_Name 
				FROM doctors,consultant,doctorsconsultant
				WHERE doctors.Doctor_Id=doctorsconsultant.Doctor_Id AND doctorsconsultant.Consultant_Id=consultant.Consultant_Id and consultant.Category = :DoctorType";


		$query_params=array(
			':DoctorType'=> $DoctorType
		);
		$stmt=ExecuteQuery($query,$query_params);
		$rows2=$stmt->fetchAll();
		// var_dump($rows2);
		if($rows1 || $rows2)
		{
			$response["success"] = 1;
    		$response["status"] = "List Available!";
    		$response["message"]   = array();
			if($rows1)
			{
				foreach($rows1 as $row1)
				{		
					$doctors             = array();
					$doctors["DoctorId"]  = $row1["Doctor_Id"];
			        $doctors["DoctorName"] = $row1["Doctor_Name"];
			        $doctors["DoctorCategory"]    = "InHouse";			
			        array_push($response["message"], $doctors);
				}
			}
			if($rows2)
			{
				foreach($rows2 as $row2)
				{		
					$doctors             = array();
					$doctors["DoctorId"]  = $row2["Doctor_Id"];
			        $doctors["DoctorName"] = $row2["Doctor_Name"];
			        $doctors["DoctorCategory"]    = "Consultant";			
			        array_push($response["message"], $doctors);
				}
			}
			echo json_encode($response);
		}
		else
		{
			$response["success"] = 0;
			$response["status"] = "No Doctor Available!";
			die(json_encode($response));
		}
	}
	else
	{
		?>
		<h1>Doctor Type</h1> 
	    <form action="GetDoctorList.php" method="post"> 
	            Type:<br /> 
	            <input type="text" name="DoctorType" placeholder="DoctorType" /> 
	            <input type="submit" values="Submit">
	    </form>

		<?php
	}
	?>