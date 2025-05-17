<?php
header("Content-Type: application/json");
require_once "db.php";

$book_id = isset($_POST['book_id']) ? intval($_POST['book_id']) : null;
$user_id = isset($_POST['user_id']) ? intval($_POST['user_id']) : null;
$comment = isset($_POST['comment']) ? trim($_POST['comment']) : null;
$rating = isset($_POST['rating']) ? intval($_POST['rating']) : null;

if (!$book_id || !$user_id || !$comment || !$rating) {
    echo json_encode(["error" => "Missing parameters"]);
    exit;
}

$sql = "INSERT INTO ratings (book_id, user_id, comment, rating, created_at) VALUES (?, ?, ?, ?, NOW())";
$stmt = $conn->prepare($sql);
$stmt->bind_param("iisi", $book_id, $user_id, $comment, $rating);

if ($stmt->execute()) {
    $avg_sql = "SELECT ROUND(AVG(rating), 1) as avg_rating FROM ratings WHERE book_id = ?";
    $avg_stmt = $conn->prepare($avg_sql);
    $avg_stmt->bind_param("i", $book_id);
    $avg_stmt->execute();
    $avg_result = $avg_stmt->get_result();

    $avg_rating = 0;
    if ($row = $avg_result->fetch_assoc()) {
        $avg_rating = $row['avg_rating'];
    }

    echo json_encode([
        "success" => true,
        "message" => "Comment added successfully",
        "avg_rating" => $avg_rating
    ]);

    $avg_stmt->close();
} else {
    echo json_encode(["error" => $stmt->error]);
}

$stmt->close();
$conn->close();
?>
