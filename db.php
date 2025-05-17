<?php
$host = "localhost";
$db_name = "bookapp";
$username = "root";
$password = "";
$conn = new mysqli($host, $username, $password, $db_name);

if ($conn->connect_error) {
    die("Failed connection " . $conn->connect_error);
}
?>
