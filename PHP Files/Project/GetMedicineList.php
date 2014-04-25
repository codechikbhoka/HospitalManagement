<?php
	require "function.php";

    $query="Select medicine.Medicine_Id,medicine.Medicine_Name from medicine ";
    
    $query_params=array();

    $stmt=ExecuteQuery($query,$query_params);
    $rows=$stmt->fetchAll();
    if($rows)
    {
        $response["success"]=1;
        $response["status"]="AllMedicine";
        $response["message"]=array();
        foreach($rows as $row)
        {
            $medicine             = array();
                        $medicine["MedicineId"]  = $row["Medicine_Id"];
                        $medicine["MedicineName"] = $row["Medicine_Name"];
            array_push($response["message"], $medicine);
        }
        echo json_encode($response);
    }
    else
    {
        $response["success"] = 0;
        $response["status"] = "No Doctor Available!";
        die(json_encode($response));
    }
    //echo json_encode($response);

?>