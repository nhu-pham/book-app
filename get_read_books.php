<?php
header("Content-Type: application/json");
require_once "db.php";

$user_id = isset($_GET['user_id']) ? intval($_GET['user_id']) : 0;

if ($user_id <= 0) {
    echo json_encode(["error" => "Missing or invalid user_id"]);
    exit;
}

$sql = "SELECT 
            b.book_id, b.title, b.author, b.description, 
            b.cover_url, b.file_url, b.category_id, 
            c.category_name, IFNULL(AVG(r.rating), 0) AS avg_rating,
            CASE 
                WHEN f.user_id IS NOT NULL THEN 1
                ELSE 0
            END AS is_favorite
        FROM reading_progress rp
        JOIN books b ON rp.book_id = b.book_id
        JOIN categories c ON b.category_id = c.category_id
        LEFT JOIN ratings r ON b.book_id = r.book_id
        LEFT JOIN favorites f ON b.book_id = f.book_id AND f.user_id = ?
        WHERE rp.user_id = ?
        GROUP BY b.book_id
        ORDER BY rp.updated_at DESC";

$stmt = $conn->prepare($sql);
$stmt->bind_param("ii", $user_id, $user_id);
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
        "isFavorite" => boolval($row["is_favorite"])
    ];
}

echo json_encode($books);
$stmt->close();
$conn->close();
?>
