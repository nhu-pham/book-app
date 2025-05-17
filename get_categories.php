 <?php
include 'db.php';

$sql = "SELECT * FROM categories LIMIT 6";
$result = mysqli_query($conn, $sql);

$categories = array();

while ($row = mysqli_fetch_assoc($result)) {
    $categories[] = $row;
}

echo json_encode($categories);
?>

