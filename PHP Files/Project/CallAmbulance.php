<?php
if(!empty($_POST))
{
    		require "function.php";
            $UserId=$_POST['UserId'];
    		$query="Select Phone,ambulance.Ambulance_Id from brings,ambulance,alloted_to,driver
    where	brings.Ambulance_Id=ambulance.Ambulance_Id And ambulance.Ambulance_Id=alloted_to.Ambulance_Id And alloted_to.Driver_Id=driver.Driver_Id
    And brings.Ambulance_Id=
    (
    Select T.Ambulance_Id
    FROM
        (
            Select brings.Ambulance_Id,MAX(Date_Time) FROM brings
            GROUP BY brings.AMBULANCE_ID
        ) AS T
        WHERE NOT EXISTS
    (
        Select table1.Ambulance_Id
        FROM
        (
            Select brings.Ambulance_Id,MAX(Date_Time) AS COL FROM brings
            GROUP BY brings.AMBULANCE_ID
        ) As table1,
        (
            Select brings.Ambulance_Id,MAX(Date_Time) AS COL FROM brings
            GROUP BY brings.AMBULANCE_ID
        ) As table2
        WHERE table1.Ambulance_Id<>table2.Ambulance_Id AND table1.Ambulance_Id=T.Ambulance_Id AND 	table1.COL > table2.COL
    ))";
     		$query_params=array();
     		$stmt=ExecuteQuery($query,$query_params);
     		$row=$stmt->fetch();
     		//var_dump($row);
     		if($row)
     		{
     			$response["success"]=1;
     			$response["status"]="phone";
     			$response["message"]=$row["Phone"];
                $AmbulanceId=$row['Ambulance_Id'];
                $query="Insert INTO brings(User_Id,Ambulance_Id,Date_Time) values(:UserId,:AmbulanceId,NOW())";
                $query_params = array(
                ':UserId' => $_POST['UserId'],
                ':AmbulanceId' => $AmbulanceId
                ); 
                $stmt=ExecuteQuery($query,$query_params);
     			echo json_encode($response);
     		}
     		else
     		{
     			$response["success"]=0;
     			$response["status"]="None Available";
                die(json_encode($response));
     		}
}
else 
{
?>
    <h1>CallAmbulance</h1> 
    <form action="CallAmbulance.php" method="post"> 
            User_Id:<br /> 
            <input type="text" name="UserId" placeholder="User_Id" /> 
            <br /><br />
            <input type="submit" values="Submit">
    </form>
 
    <?php
}
?>





















