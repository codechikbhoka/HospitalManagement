<?php
require ("function.php");

if (!empty($_POST)) {
    if (empty($_POST['DrId']) || empty($_POST['PatientId']) || empty($_POST['PharmacyId'])|| empty($_POST['MedicineId'])|| empty($_POST['Dosage'])|| empty($_POST['Time1'])|| empty($_POST['Time2'])|| empty($_POST['Time3'])|| empty($_POST['Time4'])|| empty($_POST['Days'])|| empty($_POST['Disease'])) 
    {

        $response["success"] = 0;
        $response["message"] = "Please Enter Full information";
  
        die(json_encode($response));
    }

    $Dr_Id=$_POST['DrId'];
    $Patient_Id=$_POST['PatientId'];
    $Pharmacy_Id=$_POST['PharmacyId'];
    $Medicine_Id=$_POST['MedicineId'];
    $Dosage=$_POST['Dosage'];
    $Time1=$_POST['Time1'];
    $Time2=$_POST['Time2'];
    $Time3=$_POST['Time3'];
    $Time4=$_POST['Time4'];
    $Days=$_POST['Days'];
    $Disease=$_POST['Disease'];


    $query = "INSERT INTO prescribed_medicine (Pharmacy_Id,Patient_Id,Doctor_Id,Medicine_Id,Dosage,Time1,Time2,Time3,Time4,Days) VALUES (:Pharmacy_Id,:Patient_Id,:Dr_Id,:Medicine_Id,:Dosage,:Time1,:Time2,:Time3,:Time4,:Days)";    
    $query_params = array(
        ':Dr_Id'=> $Dr_Id,
        ':Patient_Id' => $Patient_Id,
        ':Pharmacy_Id' => $Pharmacy_Id,
        ':Medicine_Id' => $Medicine_Id,
        ':Dosage' => $Dosage,
        ':Time1' => $Time1,
        ':Time2' => $Time2,
        ':Time3' => $Time3,
        ':Time4' => $Time4,
        ':Days' => $Days

    );
    $stmt=ExecuteQuery($query,$query_params);

    //print("ayyayayyay");
    $query = "UPDATE patient SET Patient_Disease = :Disease where Patient_Id = :Patient_Id";    
 
    $query_params = array(
        ':Disease' => $Disease,
        ':Patient_Id' => $Patient_Id
    );    
    //print("ayayyayya");
    $stmt=ExecuteQuery($query,$query_params);
    $response["success"] = 1;
    $response["message"] = "Data Added!";
    echo json_encode($response);
    
    
} else {
?>
    <h1>Prescribe Medicine</h1> 
    <form action="PrescribeMedicine.php" method="post"> 
            Dr_Id:<br /> 
            <input type="text" name="DrId" placeholder="DrId" /> 
            <br /><br />
            Patient_Id:<br />
            <input type="text" name="PatientId" placeholder="PatientId" /> 
            <br /><br />
            Pharmacy_Id:<br />
            <input type="text" name="PharmacyId" placeholder="PharmacyId" /> 
            <br /><br />
            Medicine_Id:<br />
            <input type="text" name="MedicineId" placeholder="MedicineId" /> 
            <br /><br />
            Dosage:<br />
            <input type="text" name="Dosage" placeholder="Dosage" /> 
            <br /><br />
            Time1:<br />
            <input type="text" name="Time1" placeholder="Time1" /> 
            <br /><br />
            Time2:<br />
            <input type="text" name="Time2" placeholder="Time2" /> 
            <br /><br />
            Time3:<br />
            <input type="text" name="Time3" placeholder="Time3" /> 
            <br /><br />
            Time4:<br />
            <input type="text" name="Time4" placeholder="Time4" /> 
            <br /><br />
            Days:<br />
            <input type="text" name="Days" placeholder="Days" /> 
            <br /><br />


            Disease:<br />
            <input type="text" name="Disease" placeholder="Disease" /> 
            <br /><br />


            <input type="submit" values="Submit">
    </form>
 
    <?php
}

?>