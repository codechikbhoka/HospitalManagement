<?php
if(!empty($_POST))
{
	require "function.php";
	$MedicineId=$_POST['MedicineId'];
    $query="Select pharmacy.Pharmacy_Id,Pharmacy_Name,Pharmacy_Address from pharmacymedicine,pharmacy
    where 
    pharmacymedicine.Medicine_Id =:MedicineId AND pharmacymedicine.Pharmacy_Id = pharmacy.Pharmacy_Id ";
    
    $query_params=array(
        ':MedicineId' => $_POST['MedicineId']);

    $stmt=ExecuteQuery($query,$query_params);
    $rows=$stmt->fetch();
    $response["success"]=1;
    $response["status"]="PharmacyList";
    $response["message"]=array();
    
    $Info    = array();

    $Info["PharmacyName"]  = $rows["Pharmacy_Name"];
    $Info["PharmacyId"] = $rows["Pharmacy_Id"];
    $Info["PharmacyAddress"] = $rows["Pharmacy_Address"];

    array_push($response["message"], $Info);
    echo json_encode($response);
}
else{?>
	<h1>Get Pharmacy From Medicine</h1> 
    <form action="GetPharmacyFromMedicine.php" method="post"> 
            Medicine_Id:<br /> 
            <input type="text" name="MedicineId" placeholder="MedicineId" /> 
            <br /><br />
            <input type="submit" values="Submit">
    </form>
<?php
    }
?>