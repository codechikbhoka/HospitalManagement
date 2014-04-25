<?php
if(!empty($_POST))
{
	require "function.php";
	$PatientId=$_POST['PatientId'];
    $query="Select users.User_Id,users.User_Name,users.User_Password,users.User_Age,users.User_Sex ,users.User_Address,users.User_Phone
    from
    patient,users,userpatient
    where 
    patient.Patient_Id=userpatient.Patient_Id AND userpatient.User_Id=users.User_Id And patient.Patient_Id=:PatientId ";
    $query_params=array(
        ':PatientId' => $_POST['PatientId']);
    $stmt=ExecuteQuery($query,$query_params);
    $rows=$stmt->fetch();
    $response["success"]=1;
    $response["status"]="Schedule";
    $response["message"]=array();
    $Info    = array();
    $Info["UserId"]  = $rows["User_Id"];
    $Info["UserName"] = $rows["User_Name"];
    $Info["UserPassword"] = $rows["User_Password"];
    $Info["UserAge"] = $rows["User_Age"];
    $Info["UserSex"] = $rows["User_Sex"];
    $Info["UserAddress"] = $rows["User_Address"];
    $Info["UserPhone"] = $rows["User_Phone"];        
    array_push($response["message"], $Info);
    echo json_encode($response);
}

else{?>
	<h1>GetUserinfo</h1> 
    <form action="GetPatientInfo.php" method="post"> 
            Patient_Id:<br /> 
            <input type="text" name="PatientId" placeholder="PatientId" /> 
            <br /><br />
            <input type="submit" values="Submit">
    </form>
<?php
    }
?>