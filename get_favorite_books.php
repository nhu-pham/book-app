<?php
require "db.php";
header("Content-Type: application/json");

$user_id = isset($_GET['user_id']) ? intval($_GET['user_id']) : 0;

if ($user_id > 0) {
    $stmt = $conn->prepare("
        SELECT b.book_id, b.title, b.author, b.description, b.cover_url, b.file_url,
               b.category_id, c.category_name,
               (SELECT AVG(rating) FROM ratings WHERE book_id = b.book_id) AS avg_rating
        FROM favorites f
        JOIN books b ON f.book_id = b.book_id
        JOIN categories c ON b.category_id = c.category_id
        WHERE f.user_id = ?
    ");
    $stmt->bind_param("i", $user_id);
    $stmt->execute();
    $result = $stmt->get_result();

    $books = array();
    while ($row = $result->fetch_assoc()) {
        $row['avg_rating'] = isset($row['avg_rating']) ? floatval($row['avg_rating']) : 0.0;
        
        $row['isFavorite'] = true;

        $books[] = $row;
    }

    echo json_encode($books, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
} else {
    echo json_encode([]);
}
?>
