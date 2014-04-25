<?php
	require "function.php";

    $query="Select lab_tests.Lab_Test_Id,lab_tests.Lab_Test_Name from lab_tests ";
    
    $query_params=array();

    $stmt=ExecuteQuery($query,$query_params);
    $rows=$stmt->fetchAll();

    if($rows){

        $response["success"]=1;
        $response["status"]="AllLabTests";
        $response["message"]=array();

        foreach ($rows as $row) {
           $Info    = array();

           $Info["LabTestId"]  = $row["Lab_Test_Id"];
           $Info["LabTestName"] = $row["Lab_Test_Name"];
  
           array_push($response["message"], $Info);
         }
     }

     else{
                  $response["success"]=0;
            $response["status"]="No Labs";
  
     }

    
    echo json_encode($response);

?>