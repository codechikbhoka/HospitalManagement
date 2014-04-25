<?php
require("config.inc.php");
function FetchRow($table,$Id)
    {
        global $db;
        $query="Select * from $table where $Id = :$Id";
        $query_params=array(
            ':Patient_Id' => $Id
        );
        $stmt=ExecuteQuery($query,$query_params);
        
        //var_dump($row);
        return $row;
    }
function ExecuteQuery($query,$query_params)
    {
        global $db;
        try {
            // These two statements run the query against your database table. 
            $stmt   = $db->prepare($query);
          //  var_dump($stmt);
            $result = $stmt->execute($query_params);
        }
        catch (PDOException $ex) {
            // For testing, you could use a die and message. 
            //die("Failed to run query: " . $ex->getMessage());
            //or just use this use this one to product JSON data:
            
            $response["success"] = 0;
            $response["message"] = "Database Error1. Please Try Again!";
            //var_dump($ex);
            die(json_encode($response));
        }
        //var_dump($stmt);
        return $stmt;
    }
?>