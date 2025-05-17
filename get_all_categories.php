<?php
require 'db.php';

$query = "SELECT * FROM categories";
$result = mysqli_query($conn, $query);

$categories = [];
while ($row = mysqli_fetch_assoc($result)) {
    $categories[] = $row;
}
echo json_encode($categories);
?>
