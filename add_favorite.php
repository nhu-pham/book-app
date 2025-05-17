<?php
require "db.php";
header("Content-Type: application/json");

$user_id = isset($_POST['user_id']) ? intval($_POST['user_id']) : null;
$book_id = isset($_POST['book_id']) ? intval($_POST['book_id']) : null;

$sql = "INSERT INTO favorites(user_id, book_id) VALUES (?, ?)";
$stmt = $conn->prepare($sql);
$stmt->bind_param("ii", $user_id, $book_id);
$stmt->execute();

echo json_encode([
    "isFavorite" => true
]);
