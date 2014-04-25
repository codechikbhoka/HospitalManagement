<?php
if(!empty($_POST))
{
	require "function.php";
	$UserId=$_POST['UserId'];
    $query="Select bills.Bill_Id,bills.Date,bills.Time,income.Amount 
    from issues_to,bills,users,userpatient,patient,incomebills,income
    where 
    users.User_Id=userpatient.User_Id and userpatient.Patient_Id=patient.Patient_Id and patient.Patient_Id=issues_to.Patient_Id AND issues_to.Bill_Id = bills.Bill_Id AND bills.Bill_Id=incomebills.Bill_Id and incomebills.Income_Id=income.Income_Id And users.User_Id=:UserId
    ";
    
    $query_params=array(
        ':UserId' => $_POST['UserId']);

    $stmt=ExecuteQuery($query,$query_params);
    $rows=$stmt->fetchAll();
    if($rows)
    {
        $response["success"]=1;
        $response["status"]="Schedule";
        $response["message"]=array();
        foreach($rows as $row)
            {
                $Info    = array();
                $Info["BillId"]  = $row["Bill_Id"];
                $Info["Date"] = $row["Date"];
                $Info["Time"] = $row["Time"];
                $Info["Amount"] = $row["Amount"];
                array_push($response["message"], $Info);            
            }
        
        echo json_encode($response);
    }
    else
    {
        $response["success"] = 0;
        $response["status"] = "No Doctor Available!";
        die(json_encode($response));
    }
}
else{?>
	<h1>GetBillInfo</h1> 
    <form action="GetBillInfo.php" method="post"> 
            User_Id:<br /> 
            <input type="text" name="UserId" placeholder="UserId" /> 
            <br /><br />
            <input type="submit" values="Submit">
    </form>
<?php
    }
?>