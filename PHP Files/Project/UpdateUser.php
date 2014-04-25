<?php
require ("function.php");
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
    
    //if the page hasn't died, we will check with our database to see if there is
    //already a user with the username specificed in the form.  ":user" is just
    //a blank variable that we will change before we execute the query.  We
    //do it this way to increase security, and defend against sql injections
    if(!empty($_POST['UserName']))
    {
        $query="Update users Set User_Name=:UserName where User_Id=:UserId";
         $query_params = array(
        ':UserId' => $_POST['UserId'],      
        ':UserName' => $_POST['UserName']
        );
    
    //time to run our query, and create the user
        $stmt=ExecuteQuery($query,$query_params);
    }
    if(!empty($_POST['UserPassword']))
    {
        $query="Update users Set User_Password=:UserPassword where User_Id=:UserId";
         $query_params = array(
        ':UserId' => $_POST['UserId'],      
        ':UserPassword' => $_POST['UserPassword']
        );
    
    //time to run our query, and create the user
        $stmt=ExecuteQuery($query,$query_params);
    }
    if(!empty($_POST['UserAge']))
    {
        $query="Update users Set User_Age=:UserAge where User_Id=:UserId";
         $query_params = array(
        ':UserId' => $_POST['UserId'],      
        ':UserAge' => $_POST['UserAge']
        );
    
    //time to run our query, and create the user
        $stmt=ExecuteQuery($query,$query_params);
    }
    if(!empty($_POST['UserSex']))
    {
        $query="Update users Set User_Sex=:UserSex where User_Id=:UserId";
         $query_params = array(
        ':UserId' => $_POST['UserId'],      
        ':UserSex' => $_POST['UserSex']
        );
    
    //time to run our query, and create the user
        $stmt=ExecuteQuery($query,$query_params);
    }
    if(!empty($_POST['UserAddress']))
    {
        $query="Update users Set User_Address=:UserAddress where User_Id=:UserId";
         $query_params = array(
        ':UserId' => $_POST['UserId'],      
        ':UserAddress' => $_POST['UserAddress']
        );
    
    //time to run our query, and create the user
        $stmt=ExecuteQuery($query,$query_params);
    }
    if(!empty($_POST['UserPhone']))
    {
        $query="Update users Set User_Phone=:UserPhone where User_Id=:UserId";
         $query_params = array(
        ':UserId' => $_POST['UserId'],      
        ':UserPhone' => $_POST['UserPhone']
        );
    
    //time to run our query, and create the user
        $stmt=ExecuteQuery($query,$query_params);
    }
    //If we have made it here without dying, then we are in the clear to 
    //create a new user.  Let's setup our new query to create a user.  
    //Again, to protect against sql injects, user tokens such as :user and :pass
    $response["success"] = 1;
    $response["message"] = "Updated";
    echo json_encode($response);
    
    //for a php webservice you could do a simple redirect and die.
    //header("Location: login.php"); 
    //die("Redirecting to login.php");
    
    
} else {
?>
    <h1>Register</h1> 
    <form action="UpdateUser.php" method="post">  
            <br /><br />
            User_Id:<br/>
            <input type="text" name="UserId" placeholder="User_Id" /> 
            <br /><br />
            User_Name:<br/>
            <input type="text" name="UserName" placeholder="User_Name" /> 
            <br /><br />
            User_Password:<br /> 
            <input type="text" name="UserPassword" placeholder="User_Password" /> 
            <br /><br />
            User_Age:<br /> 
            <input type="text" name="UserAge" placeholder="User_Age" /> 
            <br /><br />
            User_Sex:<br /> 
            <input type="text" name="UserSex" placeholder="User_Sex" /> 
            <br /><br />
            User_Address:<br /> 
            <input type="text" name="UserAddress" placeholder="User_Address" /> 
            <br /><br />
            User_Phone:<br /> 
            <input type="text" name="UserPhone" placeholder="User_Phone" /> 
            <br /><br />
            <input type="submit" values="Submit">
    </form>
 
    <?php
}

?>