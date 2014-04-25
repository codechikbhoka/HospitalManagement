<?php
	require "function.php";
	$query="Select Pharmacy_Id,Pharmacy_Name From pharmacy";	
	$query_params = array(); 
    $stmt=ExecuteQuery($query,$query_params);
    $row=$stmt->fetch();
    if($rows)
        {
            $response["success"]=1;
            $response["status"]="Pharmacy";
            $response["message"]=array();
            //var_dump($rows);
            foreach($rows as $row)
            {
                $List    = array();
                $List["PharmacyId"]  = $row["Pharmacy_Id"];
                $List["PharmacyName"] = $row["Pharmacy_Name"];        
                array_push($response["message"], $List);
            }    
        }
        else
        {
            $response["success"]=0;
            $response["status"]="No pharmacy!!!";
        }
        echo json_encode($response);
?>