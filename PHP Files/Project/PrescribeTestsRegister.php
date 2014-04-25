<?php
require ("function.php");

if (!empty($_POST)) {
    if (empty($_POST['DrId']) || empty($_POST['PatientId']) || empty($_POST['LabTestId'])) 
    {

        $response["success"] = 0;
        $response["message"] = "Please Enter Full information";
  
        die(json_encode($response));
    }

    $Dr_Id=$_POST['DrId'];
    $Patient_Id=$_POST['PatientId'];
    $Lab_Test_Id=$_POST['LabTestId'];

    $query = "INSERT INTO prescribed_tests (Lab_Test_Id,Patient_Id,Doctor_Id) VALUES (:Lab_Test_Id,:Patient_Id,:Dr_Id)";    
    $query_params = array(
        ':Dr_Id'=> $Dr_Id,
        ':Patient_Id' => $Patient_Id,
        ':Lab_Test_Id' => $Lab_Test_Id,
 
    );
    $stmt=ExecuteQuery($query,$query_params);

    $response["success"] = 1;
    $response["message"] = "Data Added!";
    echo json_encode($response);
    
    
} else {
?>
    <h1>Prescription</h1> 
    <form action="PrescribeTestsRegister.php" method="post"> 
            Dr_Id:<br /> 
            <input type="text" name="DrId" placeholder="DrId" /> 
            <br /><br />
            Patient_Id:<br />
            <input type="text" name="PatientId" placeholder="PatientId" /> 
            <br /><br />
            Lab_Test_Id:<br />
            <input type="text" name="LabTestId" placeholder="LabTestId" /> 
            <br /><br />
 
             <input type="submit" values="Submit">
    </form>
 
    <?php
}

?>