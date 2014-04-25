<?php
function FindValidDate($DATE,$TIME,$StartTime,$EndTime,$TodayDate,$TodayTime)
{  
    if ($DATE < $TodayDate)
    {
        if($TodayTime<$StartTime)
        {
            $NewDate=date('m/d/y',$TodayDate);
            $NewTime=date('h:i:s',$StartTime);
            $ret=date('Y-m-d H:i:s',strtotime($NewDate.' '.$NewTime));            
        }
        else if($TodayTime>$StartTime AND $TodayTime < $EndTime)
        {
            $NewDate=date('m/d/y',$TodayDate);
            $NewTime=date('h:i:s',$TodayTime);
            $ret=date('Y-m-d H:i:s',strtotime($NewDate.' '.$NewTime));        
        }
        else if($TodayTime>$EndTime)
        {
            $NewDate=date('m/d/y',strtotime("+1 day", $TodayDate));
            $NewTime=date('h:i:s',$StartTime);
            $ret=date('Y-m-d H:i:s',strtotime($NewDate.' '.$NewTime));
        }
    }
    else if ($DATE >= $TodayDate)
    {
        if($TIME>$StartTime AND $TIME < ($EndTime-60*10))
        {
            $NewDate=date('m/d/y',$Date);
            $NewTime=date('h:i:s',($TIME+60*10));
            $ret=date('Y-m-d H:i:s',strtotime($NewDate.' '.$NewTime));   
        }
        else if($TIME>($EndTime-60*10))
        {
            $NewDate=date('m/d/y',strtotime("+1 day", $DATE));
            $NewTime=date('h:i:s',$StartTime);
            $ret=date('Y-m-d H:i:s',strtotime($NewDate.' '.$NewTime));
        }   
    }
    return $ret;
}

if(!empty($_POST))
{
    require "function.php";
    $DoctorId=$_POST['DoctorId'];
    $UserId=$_POST['UserId'];
	$query="SELECT DATE_FORMAT(Consult_Date_Time,'%H:%i') TIMEONLY,Date_FORMAT(Consult_Date_Time,'%m/%d/%Y') DATEONLY,DATE_FORMAT(Consult_Date_Time,'%H:%i:%s:%d/%m/%Y') FULLDATE
            From consults where Consult_Date_Time =
            (
                Select Consult_Date_Time From `consults` Where Consult_Date_Time =
                (
                    SELECT MAX(Consult_Date_Time) FROM `consults` WHERE Doctor_Id=:Doctor_Id
                )
            )";
    $query_params=array(
        ':Doctor_Id'=> $DoctorId
        );
    $stmt=ExecuteQuery($query,$query_params);
    $row1=$stmt->fetch();
    //var_dump($row1);
    $date = $row1['DATEONLY']; 
    //$DATE = date('m/d/y', strtotime($date));
    $DATE = strtotime($date);
    //echo $DATE;

    $time = $row1['TIMEONLY']; 
    //$TIME = date('h:i', strtotime($time));
   $TIME = strtotime($time);
   // echo $TIME;
    $query = "SELECT Doctor_Start_Time As STARTTIME,Doctor_End_Time AS ENDTIME
              FROM  doctors 
              WHERE Doctor_Id=:Doctor_Id";
    $query_params=array(
        ':Doctor_Id'=> $DoctorId
        );
    $stmt=ExecuteQuery($query,$query_params);
    $row1=$stmt->fetch();
    $time = $row1['STARTTIME']; 
    //$StartTime = date('h:i', strtotime($time));
    $StartTime = strtotime($time);
    //echo $StartTime;
    $time = $row1['ENDTIME']; 
    //$EndTime = date('h:i', strtotime($time));
    $EndTime = strtotime($time);
    //echo $EndTime;
    $TodayDate = strtotime(date("m/d/y"));
    $TodayTime = strtotime(date("h:i"));
    $ValidDateTime=FindValidDate($DATE,$TIME,$StartTime,$EndTime,$TodayDate,$TodayTime);
    $query="Insert into patient() VALUES ()";
    $query_params=array();
    $stmt=ExecuteQuery($query,$query_params);
    
    $query = "Select MAX(Patient_Id) from patient";    
    //Again, we need to update our tokens with the actual data:
    $query_params = array();
    //time to run our query, and create the user
    $stmt=ExecuteQuery($query,$query_params);
    $row=$stmt->fetch();
    $PatientId = $row['MAX(Patient_Id)'];
    
    $query="Insert into userpatient(User_Id,Patient_Id) VALUES (:UserId,:PatientId)";
    $query_params=array(
        ':UserId'=>$UserId,
        ':PatientId'=>$PatientId
        );
    $stmt=ExecuteQuery($query,$query_params);

    $query="Insert INTO consults(Patient_Id,Doctor_Id,Consult_Date_Time) Values (:Patient_Id,:Doctor_Id,:Consult_Date_Time)";
    $query_params=array(
        ':Patient_Id'=> $PatientId,
        ':Doctor_Id'=> $DoctorId,
        'Consult_Date_Time' => $ValidDateTime);
    $stmt=ExecuteQuery($query,$query_params);
    $response["success"]=1;
    $response["status"]="Appointment Taken!!!";
    $response["message"]=$ValidDateTime;
    echo json_encode($response);
    // $DATE = $row1["FULLDATE"];
    // $date = date_create_from_format('d/M/Y:H:i:s', $DATE);
    // $timezone = date_default_timezone_get();
    // $query="Select Doctor_Start_Time,TIMEDIFF(Doctor_End_Time,'00:15:00') From Doctors Where Doctor_Id=:Doctor_Id;";
    // $query_params=array(
    //     ':Doctor_Id'=> $Doctor_Id
    //     );
    // $stmt=ExecuteQuery($query,$query_params);
    // $row2=$stmt->fetch();
    // var_dump($row2);
    // $StartTime=strtotime($row2["Doctor_Start_Time"]);
    // $EndTime=strtotime($row2["TIMEDIFF(Doctor_End_Time,'00:15:00')"]);
    // $Diff1=$Time-$StartTime;
    // $Diff2=$EndTime-$Time;
    // if($Diff1>=15 && $Diff2>=15)
    // {
    //     $response["success"] = 1;
    //     $response["message"] = "Appointment Taken";
    //     echo json_encode($response);
    // }
}	
else{?>
	<h1>SetAppointment</h1> 
    <form action="SetAppointment.php" method="post"> 
            User_Id:<br /> 
            <input type="text" name="UserId" placeholder="UserId" />
            <br /> <br /> 
            Doctor_Id:<br /> 
            <input type="text" name="DoctorId" placeholder="DoctorId" /> 
            <br /><br />
            <input type="submit" values="Submit">
    </form>
<?php
    }
?>