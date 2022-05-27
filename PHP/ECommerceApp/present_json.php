<?php

$connection = new mysqli("localhost","root","","ecom");
$sqlCommand = $connection->prepare("select * from product_ecom where id=?");
$sqlCommand->bind_param("i", $_GET["id"]);
$sqlCommand->execute();


$result = $sqlCommand->get_result();
$rowOfData = $result->fetch_assoc();


//echo $rowOfData["id"];
//echo $rowOfData["name"];
//echo $rowOfData["price"];

echo json_encode($rowOfData);