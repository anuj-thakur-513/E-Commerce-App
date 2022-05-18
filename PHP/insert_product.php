<?php

$connection = new mysqli("localhost", "root", "", "ecom");
$sqlCommand = $connection->prepare("insert into product_ecom values (?,?,?)");
$sqlCommand->bind_param("isi", $_GET["id"], $_GET["name"], $_GET["price"]);
$sqlCommand->execute();