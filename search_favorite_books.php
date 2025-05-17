<?php
header("Content-Type: application/json; charset=UTF-8");
require_once "db.php";

$conn->set_charset("utf8mb4");

$user_id = isset($_GET['user_id']) ? intval($_GET['user_id']) : 0;
$keyword = isset($_GET['keyword']) ? trim($_GET['keyword']) : '';

if ($user_id === 0 || $keyword === '') {
    echo json_encode(["error" => "Missing user_id or keyword"]);
    exit;
}

$searchTerm = '%' . $keyword . '%';

$sql = "SELECT 
            b.book_id, 
            b.title, 
            b.author, 
            b.description, 
            b.cover_url, 
            b.file_url, 
            b.category_id,
            c.category_name,
            IFNULL(AVG(r.rating), 0) AS avg_rating,
            1 AS isFavorite
        FROM favorites f
        JOIN books b ON f.book_id = b.book_id
        JOIN categories c ON b.category_id = c.category_id
        LEFT JOIN ratings r ON b.book_id = r.book_id
        WHERE f.user_id = ?
          AND (b.title LIKE ? OR b.author LIKE ?)
        GROUP BY b.book_id
        ORDER BY b.book_id DESC";

$stmt = $conn->prepare($sql);
if (!$stmt) {
    echo json_encode(["error" => "Failed to prepare statement"]);
    exit;
}

$stmt->bind_param("iss", $user_id, $searchTerm, $searchTerm);
$stmt->execute();
$result = $stmt->get_result();

$books = [];
while ($row = $result->fetch_assoc()) {
    $books[] = [
        "book_id" => $row["book_id"],
        "title" => $row["title"],
        "author" => $row["author"],
        "description" => $row["description"],
        "cover_url" => $row["cover_url"],
        "file_url" => $row["file_url"],
        "category_id" => $row["category_id"],
        "category_name" => $row["category_name"],
        "avg_rating" => round(floatval($row["avg_rating"]), 1),
        "isFavorite" => true
    ];
}

echo json_encode($books);

$stmt->close();
$conn->close();
?>
