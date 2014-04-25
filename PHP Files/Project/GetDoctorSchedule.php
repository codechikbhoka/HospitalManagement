<?php
if(!empty($_POST))
{
    require "function.php";
    $query="Select patient.Patient_Id,users.User_Name,DATE_FORMAT(consult_Date_Time,'%H:%i:%s') TIMEONLY,Date_FORMAT(consult_Date_Time,'%d/%m/%Y') DATEONLY,DATE_FORMAT(consult_Date_Time,'%H:%i:%s:%d/%m/%Y') FULLDATE 
From patient,users,userpatient,consults,doctors
Where
doctors.Doctor_Id=consults.Doctor_Id AND consults.Patient_Id=patient.Patient_Id AND patient.Patient_Id=userpatient.Patient_Id AND userpatient.User_Id = users.User_Id AND
doctors.Doctor_Id=:DoctorId AND consult_Date_Time > NOW()";
    $query_params=array(
        ':DoctorId' => $_POST['DoctorId']);
    $stmt=ExecuteQuery($query,$query_params);
    $rows=$stmt->fetchAll();
    if($rows)
        {
            $response["success"]=1;
            $response["status"]="Schedule";
            $response["message"]=array();
            //var_dump($rows);
            foreach($rows as $row)
            {
                $Schedule    = array();
                $Schedule["PatientId"]  = $row["Patient_Id"];
                $Schedule["PatientName"] = $row["User_Name"];
                $Schedule["Date"] = $row["DATEONLY"];
                $Schedule["Time"] = $row["TIMEONLY"];        
                array_push($response["message"], $Schedule);
            }    
        }
        else
        {
            $response["success"]=0;
            $response["status"]="Doctor Free!!";
        }
        echo json_encode($response);
}

else{?>
	<h1>GetSchedule</h1> 
    <form action="GetDoctorSchedule.php" method="post"> 
            Doctor_Id:<br /> 
            <input type="text" name="DoctorId" placeholder="DoctorId" /> 
            <br /><br />
            <input type="submit" values="Submit">
    </form>
<?php
    }
?>