<?php

//load and connect to MySQL database stuff
require("function.php");

if (!empty($_POST)) {
    //gets user's info based off of a username.
    $query = "SELECT 
                User_Id,  
                User_Password
            FROM users 
            WHERE 
                User_Id = :User_Id ";
    
    $query_params = array(
        ':User_Id' => $_POST['UserId']
    );
    
    $stmt=ExecuteQuery($query,$query_params);
    $row=$stmt->fetch();
    //This will be the variable to determine whether or not the user's information is correct.
    //we initialize it as false.
    //fetching all the rows from the query
    $login_ok = false;
    if ($row) 
    {
        //if we encrypted the password, we would unencrypt it here, but in our case we just
        //compare the two passwords
        //echo md5($_POST['Password']);
        if (md5($_POST['Password']) === $row['User_Password']) 
        {
            $login_ok = true;
        }
    }
    else 
    {
        $response["success"] = 0;
        $response["message"] = "User Not Found";
        die(json_encode($response));
    }
    
    // If the user logged in successfully, then we send them to the private members-only page 
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
		<form action="Login.php" method="post"> 
		    Username:<br /> 
		    <input type="text" name="UserId" placeholder="UserId" /> 
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
