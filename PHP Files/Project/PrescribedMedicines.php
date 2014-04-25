<?php
if(!empty($_POST))
{
	require "function.php";
	$UserId=$_POST['UserId'];
    $query="Select patient.Patient_Id,prescribed_medicine.Doctor_Id,doctors.Doctor_Name,prescribed_medicine.Pharmacy_Id,pharmacy.Pharmacy_Name,prescribed_medicine.Medicine_Id,medicine.Medicine_Name,prescribed_medicine.Dosage,prescribed_medicine.Days,prescribed_medicine.Time1,prescribed_medicine.Time2,prescribed_medicine.Time3,prescribed_medicine.Time4,patient.Patient_Disease from userpatient,doctors,patient,prescribed_medicine,medicine,pharmacy where 
    userpatient.User_Id =:UserId AND userpatient.Patient_Id = patient.Patient_Id AND prescribed_medicine.Patient_Id = patient.Patient_Id AND prescribed_medicine.Doctor_Id = doctors.Doctor_Id AND prescribed_medicine.Medicine_Id = medicine.Medicine_Id AND prescribed_medicine.Pharmacy_Id = pharmacy.Pharmacy_Id";
    
    $query_params=array(
        ':UserId' => $_POST['UserId']);

    $stmt=ExecuteQuery($query,$query_params);

    $rows=$stmt->fetchAll();
    if($rows){
    
        $response["success"]=1;
        $response["status"]="PrescribedMedicines";
        $response["message"]=array();
    
        foreach ($rows as $row) {
            $Info    = array();

         $Info["PatientId"]  = $row["Patient_Id"];
         $Info["DoctorId"] = $row["Doctor_Id"];
         $Info["DoctorName"] = $row["Doctor_Name"];
         $Info["PharmacyId"] = $row["Pharmacy_Id"];
         $Info["PharmacyName"] = $row["Pharmacy_Name"];
         $Info["MedicineId"] = $row["Medicine_Id"];
         $Info["MedicineName"] = $row["Medicine_Name"];
         $Info["Dosage"] = $row["Dosage"];
         $Info["Days"] = $row["Days"];
         $Info["Time1"] = $row["Time1"];
         $Info["Time2"] = $row["Time2"];
         $Info["Time3"] = $row["Time3"];
         $Info["Time4"] = $row["Time4"];
         $Info["PatientDisease"] = $row["Patient_Disease"];
    

         array_push($response["message"], $Info);
         }
     }

         else
        {
            $response["success"]=0;
            $response["status"]="No prescribed medicine";
        }




    echo json_encode($response);
}
else{?>
	<h1>Get User Information</h1> 
    <form action="PrescribedMedicines.php" method="post"> 
            User ID:<br /> 
            <input type="text" name="UserId" placeholder="UserId" /> 
            <br /><br />
            <input type="submit" values="Submit">
    </form>
<?php
    }
?>