<?php

$connection = new mysqli("localhost", "root", "", "online_store_db");

$sqlCommand = $connection->prepare("SELECT price, amount "
        . "FROM electronic_products_table INNER JOIN invoice_details "
        . "ON electronic_products_table.id = invoice_details.product_id "
        . "WHERE invoice_details.invoice_num=?");

$sqlCommand->bind_param("i", $_GET["invoice_num"]);
$sqlCommand->execute();

$sqlResult = $sqlCommand->get_result();
$totalPrice = 0;

while($row = $sqlResult->fetch_assoc()){
    $totalPrice = $totalPrice + ($row["price"]*$row["amount"]);
}

echo $totalPrice;