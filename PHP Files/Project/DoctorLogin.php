<?php

//load and connect to MySQL database stuff
require("function.php");

if (!empty($_POST)) {
    //gets Doctor's info based off of a Doctorname.
    $query = "SELECT 
                Doctor_Id,  
                Doctor_Password
            FROM doctors 
            WHERE 
                Doctor_Id = :Doctor_Id ";
    
    $query_params = array(
        ':Doctor_Id' => $_POST['DoctorId']
    );
    
    $stmt=ExecuteQuery($query,$query_params);
    $row=$stmt->fetch();
    //This will be the variable to determine whether or not the Doctor's information is correct.
    //we initialize it as false.
    //fetching all the rows from the query
    $login_ok = false;

    $Password=$_POST['Password'] ;
    //echo md5($Password);
   if ($row) 
    {
        //if we encrypted the password, we would unencrypt it here, but in our case we just
        //compare the two passwords
        //echo md5($Password);
        if (md5($Password) === $row['Doctor_Password']) 
        {
            $login_ok = true;
        }
    }
    else 
    {
        $response["success"] = 0;
        $response["message"] = "Doctor Not Found";
        die(json_encode($response));
    }
    
    // If the Doctor logged in successfully, then we send them to the private members-only page 
    // Otherwise, we display a login failed message and show the login form again 
    if ($login_ok) 
    {
        $response["success"] = 1;
        $response["message"] = "Login successful!";
        die(json_encode($response));
    } 
    else 
    {
        $response["success"] = 0;
        $response["message"] = "Invalid Credentials!";
        die(json_encode($response));
    }
} else {
?>
        <h1>Login</h1> 
        <form action="DoctorLogin.php" method="post"> 
            DoctorId:<br /> 
            <input type="text" name="DoctorId" placeholder="DoctorId" /> 
            <br /><br /> 
            Password:<br /> 
            <input type="password" name="Password" placeholder="Password" value="" /> 
            <br /><br /> 
            <input type="submit" value="Login" /> 
        </form> 
        <a href="Register.php">Register</a>
    <?php
}

?> 
