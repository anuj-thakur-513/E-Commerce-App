<?php

$connection = new mysqli("localhost", "root", "", "online_store_db");
$sqlCommand = $connection->prepare("SELECT id, name, price, email, amount FROM temporary_place_order INNER JOIN electronic_products_table ON electronic_products_table.id = temporary_place_order.product_id WHERE email=?");
$sqlCommand->bind_param("s", $_GET["email"]);
$sqlCommand->execute();

$tempOrderArray = array();

$sqlResult = $sqlCommand->get_result();
while ($row = $sqlResult->fetch_assoc()){
    array_push($tempOrderArray, $row);
}

echo json_encode($tempOrderArray);