<?php
	require "function.php";
	$query="select * from users";
	$query_params=array();
	$stmt=ExecuteQuery($query,$query_params);
	$rows=$stmt->fetchAll();
	foreach ($rows as $row)
	{
		var_dump($row);
		echo("\n");
	}
?>