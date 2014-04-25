<?php
if(!empty($_POST))
{
	require "function.php";
	$UserId=$_POST['UserId'];
    $query="Select userpatient.Patient_Id,doctors.Doctor_Id,doctors.Doctor_Name,prescribed_tests.Lab_Test_Id,lab_tests.Lab_Test_Name from userpatient,prescribed_tests,lab_tests,doctors where 
    userpatient.User_Id =:UserId AND userpatient.Patient_Id = prescribed_tests.Patient_Id AND prescribed_tests.Lab_Test_Id = lab_tests.Lab_Test_Id AND prescribed_tests.Doctor_Id = doctors.Doctor_Id ";
    
    $query_params=array(
        ':UserId' => $_POST['UserId']);

    $stmt=ExecuteQuery($query,$query_params);
 
    
    $rows=$stmt->fetchAll();
    if($rows)
        {
            $response["success"]=1;
            $response["status"]="PrescribedTests";
            $response["message"]=array();
    
            foreach($rows as $row)
            {
                $Info    = array();

                $Info["PatientId"]  = $row["Patient_Id"];
                $Info["DoctorId"] = $row["Doctor_Id"];
                $Info["DoctorName"] = $row["Doctor_Name"];
                $Info["LabTestId"] = $row["Lab_Test_Id"];
                $Info["LabTestName"] = $row["Lab_Test_Name"];
                
                array_push($response["message"], $Info);
            }    
        }

    else
      {
            $response["success"]=0;
            $response["status"]="No prescribed tests";
        }

    echo json_encode($response);
}
else{?>
	<h1>Get User Information</h1> 
    <form action="PrescribedTests.php" method="post"> 
            User ID:<br /> 
            <input type="text" name="UserId" placeholder="UserId" /> 
            <br /><br />
            <input type="submit" values="Submit">
    </form>
<?php
    }
?>