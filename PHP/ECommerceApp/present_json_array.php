<?php

$connection = new mysqli("localhost","root","","ecom");
$sqlCommand = $connection->prepare("select * from product_ecom");
$sqlCommand->execute();

$result = $sqlCommand->get_result();
$products = array();

while($rowOfProduct = $result->fetch_assoc()){
    array_push($products, $rowOfProduct);
}

echo json_encode($products);
