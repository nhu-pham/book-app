<?php
require_once 'db.php';
header("Content-Type: application/json");

$user_id = $_GET['user_id'];
$book_id = $_GET['book_id'];

$stmt = $conn->prepare("SELECT last_page FROM reading_progress WHERE user_id = ? AND book_id = ?");
$stmt->bind_param("ii", $user_id, $book_id);
$stmt->execute();
$result = $stmt->get_result();

if ($row = $result->fetch_assoc()) {
    echo json_encode(["success" => true, "last_page" => $row['last_page']]);
} else {
    echo json_encode(["success" => true, "last_page" => 0]);
}
?>
