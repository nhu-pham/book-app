<?php
header("Content-Type: application/json");
require_once 'db.php';

$user_id = $_POST['user_id'];
$book_id = $_POST['book_id'];
$last_page = $_POST['last_page'];

$stmt = $conn->prepare("INSERT INTO reading_progress (user_id, book_id, last_page)
    VALUES (?, ?, ?)
    ON DUPLICATE KEY UPDATE last_page = VALUES(last_page)");
$stmt->bind_param("iii", $user_id, $book_id, $last_page);

if ($stmt->execute()) {
    echo json_encode(["success" => true]);
} else {
    echo json_encode(["success" => false, "error" => $stmt->error]);
}
?>
