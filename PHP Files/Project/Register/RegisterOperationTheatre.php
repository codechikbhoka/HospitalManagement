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
    if (empty($_POST['Operation_TheatreId']) || empty($_POST['Operation_TheatrePhone']) || empty($_POST['Supervisor_Id'])) 
    {

        // Create some data that will be the JSON response 
        $response["success"] = 0;
        $response["message"] = "Please Enter Full information";
        
        //die will kill the page and not execute any code below, it will also
        //display the parameter... in this case the JSON data our Android
        //app will parse
        die(json_encode($response));
    }
    $Operation_Theatre_Id=$_POST['Operation_TheatreId'];
    $Operation_Theatre_Phone=$_POST['Operation_TheatrePhone'];
    $Supervisor_Id=$_POST['Supervisor_Id'];
    //if the page hasn't died, we will check with our database to see if there is
    //already a user with the username specificed in the form.  ":user" is just
    //a blank variable that we will change before we execute the query.  We
    //do it this way to increase security, and defend against sql injections
    $query        = " SELECT 1 FROM operation_theatre WHERE Operation_Theatre_Id = :Operation_Theatre_Id";
    $query_params = array(
       ':Operation_Theatre_Id' => $Operation_Theatre_Id
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
        $response["message"] ="Operation_Theatre Id unavailable!!";
        die(json_encode($response));
    }
    $query = "INSERT INTO infrastructure ( Supervisor_Id,Phone) VALUES (:Supervisor_Id,:Phone)";    
    //Again, we need to update our tokens with the actual data:
    $query_params = array(
        ':Supervisor_Id'=> $Supervisor_Id,
        ':Phone' => $Operation_Theatre_Phone
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
    $query = "INSERT INTO operation_theatre ( Operation_Theatre_Id) VALUES (:Operation_Theatre_Id)";    
    //Again, we need to update our tokens with the actual data:
    $query_params = array(
        ':Operation_Theatre_Id' => $Operation_Theatre_Id,
    );    
    //time to run our query, and create the user
    $stmt=ExecuteQuery($query,$query_params);

    $query = "INSERT INTO infrastructureoperation_theatre ( Infra_ID,Operation_Theatre_Id) VALUES (:Infra_Id,:Operation_Theatre_Id)";    
    //Again, we need to update our tokens with the actual data:
    $query_params = array(
        ':Infra_Id' => $Infra_Id,
        ':Operation_Theatre_Id' => $Operation_Theatre_Id
    );
    $stmt=ExecuteQuery($query,$query_params);
    $response["success"] = 1;
    $response["message"] = "Operation_Theatre Successfully Added!";
    echo json_encode($response);
    
    //for a php webservice you could do a simple redirect and die.
    //header("Location: login.php"); 
    //die("Redirecting to login.php");
    
    
} else {
?>
    <h1>RegisterOperationTheatre</h1> 
    <form action="RegisterOperationTheatre.php" method="post"> 
            Operation_Theatre_Id:<br /> 
            <input type="text" name="Operation_TheatreId" placeholder="Operation_TheatreId" /> 
            <br /><br />
            Operation_Theatre_Phone:<br />
            <input type="text" name="Operation_TheatrePhone" placeholder="Phone" /> 
            <br /><br />
            Supervisor_Id:<br />
            <input type="text" name="Supervisor_Id" placeholder="Supervisor_Id" /> 
            <br /><br />
            <input type="submit" values="Submit">
    </form>
 
    <?php
}

?>