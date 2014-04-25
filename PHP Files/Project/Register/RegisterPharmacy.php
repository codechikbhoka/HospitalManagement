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
    if (empty($_POST['PharmacyId']) || empty($_POST['PharmacyPhone']) || empty($_POST['Supervisor_Id']) || empty($_POST['PharmacyPhone'])|| empty($_POST['PharmacyAddress'])) 
    {

        // Create some data that will be the JSON response 
        $response["success"] = 0;
        $response["message"] = "Please Enter Full information";
        
        //die will kill the page and not execute any code below, it will also
        //display the parameter... in this case the JSON data our Android
        //app will parse
        die(json_encode($response));
    }
    $Pharmacy_Id=$_POST['PharmacyId'];
    $Pharmacy_Phone=$_POST['PharmacyPhone'];
    $Supervisor_Id=$_POST['Supervisor_Id'];
    $Pharmacy_Name=$_POST['PharmacyName'];
    $Pharmacy_Address=$_POST['PharmacyAddress'];
    

    //if the page hasn't died, we will check with our database to see if there is
    //already a user with the username specificed in the form.  ":user" is just
    //a blank variable that we will change before we execute the query.  We
    //do it this way to increase security, and defend against sql injections
    $query        = " SELECT 1 FROM pharmacy WHERE Pharmacy_Id = :Pharmacy_Id";
    $query_params = array(
       ':Pharmacy_Id' => $Pharmacy_Id
    ); 
    $stmt=ExecuteQuery($query,$query_params);
    $row=$stmt->fetch();
    //Now let's make run the query:
    
    //fetch is an array of returned data.  If any data is returned,
    //we know that the username is already in use, so we murder our
    //page
    if ($row) {
        // For testing, you could use a die and message. 
        //die("This username is already in use");
        
        //You could comment out the above die and use this one:
        $response["success"] = 0;
        $response["message"] ="Pharmacy Id unavailable!!";
        die(json_encode($response));
    }
    $query = "INSERT INTO infrastructure ( Supervisor_Id,Phone) VALUES (:Supervisor_Id,:Phone)";    
    //Again, we need to update our tokens with the actual data:
    $query_params = array(
        ':Supervisor_Id'=> $Supervisor_Id,
        ':Phone' => $Pharmacy_Phone
    );
    
    //time to run our query, and create the user
    $stmt=ExecuteQuery($query,$query_params);


    $query = "Select MAX(Infra_Id) from infrastructure";    
    //Again, we need to update our tokens with the actual data:
    $query_params = array();
    //time to run our query, and create the user
    $stmt=ExecuteQuery($query,$query_params);
    $row=$stmt->fetch();
    $Infra_Id = $row['MAX(Infra_Id)'];
    //If we have made it here without dying, then we are in the clear to 
    //create a new user.  Let's setup our new query to create a user.  
    //Again, to protect against sql injects, user tokens such as :user and :pass
    $query = "INSERT INTO pharmacy ( Pharmacy_Id,Pharmacy_Name,Pharmacy_Address) VALUES (:Pharmacy_Id,:Pharmacy_Name,:Pharmacy_Address)";    
    //Again, we need to update our tokens with the actual data:
    $query_params = array(
        ':Pharmacy_Id' => $Pharmacy_Id,
        ':Pharmacy_Name' => $Pharmacy_Name,
        ':Pharmacy_Address' => $Pharmacy_Address
    );    
    //time to run our query, and create the user
    $stmt=ExecuteQuery($query,$query_params);

    $query = "INSERT INTO infrastructurepharmacy ( Infra_ID,Pharmacy_Id) VALUES (:Infra_Id,:Pharmacy_Id)";    
    //Again, we need to update our tokens with the actual data:
    $query_params = array(
        ':Infra_Id' => $Infra_Id,
        ':Pharmacy_Id' => $Pharmacy_Id
    );
    $stmt=ExecuteQuery($query,$query_params);
    $response["success"] = 1;
    $response["message"] = "Pharmacy Successfully Added!";
    echo json_encode($response);
    
    //for a php webservice you could do a simple redirect and die.
    //header("Location: login.php"); 
    //die("Redirecting to login.php");
    
    
} else {
?>
    <h1>Register_Pharmacy</h1> 
    <form action="RegisterPharmacy.php" method="post"> 
            Pharmacy_Id:<br /> 
            <input type="text" name="PharmacyId" placeholder="PharmacyId" /> 
            <br /><br />
            Pharmacy_Phone:<br />
            <input type="text" name="PharmacyPhone" placeholder="Phone" /> 
            <br /><br />

            Supervisor_Id:<br />
            <input type="text" name="Supervisor_Id" placeholder="Supervisor_Id" /> 
              <br /><br />
            Pharmacy_Name:<br />
            <input type="text" name="PharmacyName" placeholder="Name" /> 
            <br /><br />
            Pharmacy_Address:<br />
            <input type="text" name="PharmacyAddress" placeholder="Address" /> 
            <br /><br />
            <br /><br />
            <input type="submit" values="Submit">
    </form>
 
    <?php
}

?>