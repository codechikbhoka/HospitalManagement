<?php
require ("../function.php");
/*
Our "config.inc.php" file connects to database every time we include or require
it within a php script.  Since we want this script to add a new user to our db,
we will be talking with our database, and therefore,
let's require the connection to happen:
*/

//if posted data is not empty
if (!empty($_POST)) {
    //If the username or password is empty when the user submits
    //the form, the page will die.
    //Using die isn't a very good practice, you may want to look into
    //displaying an error message within the form instead.  
    //We could also do front-end form validation from within our Android App,
    //but it is good to have a have the back-end code do a double check.
    if (empty($_POST['DoctorName']) || empty($_POST['DoctorAge']) || empty($_POST['DoctorSex']) || empty($_POST['DoctorJoinDate']) || empty($_POST['DoctorQualification']) || empty($_POST['DoctorType']) || empty($_POST['DoctorCategory']) || empty($_POST['DoctorPhone'])|| empty($_POST['DoctorAddress'])|| empty($_POST['DoctorPassword'])|| empty($_POST['DoctorStartTime'])|| empty($_POST['DoctorEndTime'])) 
    {

        // Create some data that will be the JSON response 
        $response["success"] = 0;
        $response["message"] = "Please Enter Full information";
        
        //die will kill the page and not execute any code below, it will also
        //display the parameter... in this case the JSON data our Android
        //app will parse
        die(json_encode($response));
    }
    $DoctorName=$_POST['DoctorName'];
    $DoctorAge=$_POST['DoctorAge'];
    $DoctorSex=$_POST['DoctorSex'];
    $DoctorJoinDate=$_POST['DoctorJoinDate'];
    $DoctorQualification=$_POST['DoctorQualification'];
    $DoctorType=$_POST['DoctorType'];
    $DoctorAddress=$_POST['DoctorAddress'];
    $DoctorPhone=$_POST['DoctorPhone'];
    $DoctorPassword=md5($_POST['DoctorPassword']);
    $DoctorCategory=$_POST['DoctorCategory'];
    $DoctorStartTime=$_POST['DoctorStartTime'];
    $DoctorEndTime=$_POST['DoctorEndTime'];
    //if the page hasn't died, we will check with our database to see if there is
    //already a user with the username specificed in the form.  ":user" is just
    //a blank variable that we will change before we execute the query.  We
    //do it this way to increase security, and defend against sql injections
    $query = "INSERT INTO doctors ( Doctor_Name ,Doctor_Age ,Doctor_Sex ,Doctor_JoinDate ,Doctor_Qualification
        ,Doctor_Address
,Doctor_Phone
,Doctor_Password,Doctor_Start_Time,Doctor_End_Time) VALUES (:DoctorName,:DoctorAge,:DoctorSex,:DoctorJoinDate,:DoctorQualification,:DoctorAddress
,:DoctorPhone
,:DoctorPassword,:DoctorStartTime,:DoctorEndTime)";  
    //Again, we need to update our tokens with the actual data:
    $query_params = array(
    ':DoctorName'=>$DoctorName,
    ':DoctorAge'=>$DoctorAge,
    ':DoctorSex'=>$DoctorSex,
    ':DoctorJoinDate'=>$DoctorJoinDate,
    ':DoctorQualification'=>$DoctorQualification,
    ':DoctorAddress' => $DoctorAddress,
    ':DoctorPhone' => $DoctorPhone,
    ':DoctorPassword' => $DoctorPassword,
    ':DoctorStartTime'=>$DoctorStartTime,
    ':DoctorEndTime'=>$DoctorEndTime,
    );
    $stmt=ExecuteQuery($query,$query_params);
    $query = "Select MAX(Doctor_Id) from doctors";    
    //Again, we need to update our tokens with the actual data:
    $query_params = array();
    //time to run our query, and create the user
    $stmt=ExecuteQuery($query,$query_params);
    $row=$stmt->fetch();
    $Doctor_Id = $row['MAX(Doctor_Id)'];
    //time to run our query, and create the user
    //If we have made it here without dying, then we are in the clear to 
    //create a new user.  Let's setup our new query to create a user.  
    //Again, to protect against sql injects, user tokens such as :user and :pass
    if($DoctorType==="Consultant")
    {
        $query = "INSERT INTO Consultant (Day,Time,Category) VALUES (\"Monday\",12,:DoctorCategory)";    
        //Again, we need to update our tokens with the actual data:
        $query_params = array(
            ':DoctorCategory'=> $DoctorCategory
    );    
        //time to run our query, and create the user
        $stmt=ExecuteQuery($query,$query_params);
        $query = "Select MAX(Consultant_Id) from Consultant";    
    //Again, we need to update our tokens with the actual data:
    $query_params = array();
    //time to run our query, and create the user
    $stmt=ExecuteQuery($query,$query_params);
    $row=$stmt->fetch();
    $Consultant_Id = $row['MAX(Consultant_Id)'];
        $query = "INSERT INTO doctorsconsultant (Doctor_Id,Consultant_Id) VALUES (:Doctor_Id,:Consultant_Id)";    
        //Again, we need to update our tokens with the actual data:
        $query_params = array(
            ':Doctor_Id' => $Doctor_Id,
            ':Consultant_Id' => $Consultant_Id
        );
        $stmt=ExecuteQuery($query,$query_params);
        $response["success"] = 1;
        $response["message"] = "Doctor Successfully Added!";
        echo json_encode($response);
    }
    elseif ($DoctorType==="Inhouse") 
    {
        $query = "INSERT INTO inhouse (Category) VALUES (:DoctorCategory)";    
        //Again, we need to update our tokens with the actual data:
        $query_params = array(
            ':DoctorCategory'=> $DoctorCategory,
    );    
        //time to run our query, and create the user
        $stmt=ExecuteQuery($query,$query_params);
        $query = "Select MAX(In_House_Id) from inhouse";    
    //Again, we need to update our tokens with the actual data:
    $query_params = array();
    //time to run our query, and create the user
    $stmt=ExecuteQuery($query,$query_params);
    $row=$stmt->fetch();
    $In_House_Id = $row['MAX(In_House_Id)'];
        $query = "INSERT INTO doctorsinhouse (Doctor_Id,In_House_Id) VALUES (:Doctor_Id,:In_House_Id)";    
        //Again, we need to update our tokens with the actual data:
        $query_params = array(
            ':Doctor_Id' => $Doctor_Id,
            ':In_House_Id' => $In_House_Id,
        );
        $stmt=ExecuteQuery($query,$query_params);
        $response["success"] = 1;
        $response["message"] = "Doctor Successfully Added!";
        echo json_encode($response);   
    }
    elseif ($DoctorType==="Surgeon") 
    {
       $query = "INSERT INTO Surgeon (Category) VALUES (:DoctorCategory)";    
        //Again, we need to update our tokens with the actual data:
        $query_params = array(
            ':DoctorCategory'=> $DoctorCategory,
    );    
        //time to run our query, and create the user
        $stmt=ExecuteQuery($query,$query_params);
        $query = "Select MAX(Surgeon_Id) from Surgeon";    
    //Again, we need to update our tokens with the actual data:
    $query_params = array();
    //time to run our query, and create the user
    $stmt=ExecuteQuery($query,$query_params);
    $row=$stmt->fetch();
    $Surgeon_Id = $row['MAX(Surgeon_Id)'];
        $query = "INSERT INTO DoctorsSurgeon (Doctor_Id,Surgeon_Id) VALUES (:Doctor_Id,:Surgeon_Id)";    
        //Again, we need to update our tokens with the actual data:
        $query_params = array(
            ':Doctor_Id' => $Doctor_Id,
            ':Surgeon_Id' => $Surgeon_Id,
        );
        $stmt=ExecuteQuery($query,$query_params);
        $response["success"] = 1;
        $response["message"] = "Doctor Successfully Added!";
        echo json_encode($response);
    }
    //for a php webservice you could do a simple redirect and die.
    //header("Location: login.php"); 
    //die("Redirecting to login.php");
    
    
} else {
?>
    <h1>Register_Doctor</h1> 
    <form action="RegisterDoctor.php" method="post"> 
            Doctor_Name:<br /> 
            <input type="text" name="DoctorName" placeholder="DoctorName" /> 
            <br /><br />
            <input type="text" name="DoctorAge" placeholder="Age" /> 
            <br /><br />
            <input type="text" name="DoctorSex" placeholder="Sex" /> 
            <br /><br />
            <input type="text" name="DoctorJoinDate" placeholder="JoinDate" /> 
            <br /><br />
            <input type="text" name="DoctorQualification" placeholder="Qualification" /> 
            <br /><br />
            <input type="text" name="DoctorType" placeholder="Type" /> 
            <br /><br />
            <input type="text" name="DoctorCategory" placeholder="Category" /> 
            <br /><br />
            <input type="text" name="DoctorPhone" placeholder="DoctorPhone" /> 
            <br /><br />
            <input type="text" name="DoctorAddress" placeholder="DoctorAddress" /> 
            <br /><br />
            <input type="text" name="DoctorPassword" placeholder="DoctorPassword" /> 
            <br /><br />
            <input type="text" name="DoctorStartTime" placeholder="DoctorStartTime" /> 
            <br /><br />
            <input type="text" name="DoctorEndTime" placeholder="DoctorEndTime" /> 
            <br /><br />
            <input type="submit" values="Submit">
    </form>
 
    <?php
}

?>