<?php
header("Content-Type: application/json");
require_once "db.php";

$user_id = isset($_GET['user_id']) ? intval($_GET['user_id']) : 0;

$sql = "
    SELECT 
        b.book_id, b.category_id, c.category_name,
        b.title, b.author, b.description,
        b.cover_url, b.file_url,
        IFNULL(AVG(r.rating), 0) AS avg_rating,
        EXISTS (
            SELECT 1 FROM favorites f 
            WHERE f.book_id = b.book_id AND f.user_id = ?
        ) AS is_favorite
    FROM books b
    JOIN categories c ON b.category_id = c.category_id
    LEFT JOIN ratings r ON b.book_id = r.book_id
    GROUP BY b.book_id
    HAVING avg_rating > 0
    ORDER BY avg_rating DESC
";

$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $user_id);
$stmt->execute();
$result = $stmt->get_result();

$books = array();
while ($row = $result->fetch_assoc()) {
    $books[] = array(
        "book_id" => $row["book_id"],
        "category_id" => $row["category_id"],
        "category_name" => $row["category_name"],
        "title" => $row["title"],
        "author" => $row["author"],
        "description" => $row["description"],
        "cover_url" => $row["cover_url"],
        "file_url" => $row["file_url"],
        "avg_rating" => round($row["avg_rating"], 1),
        "isFavorite" => (bool)$row["is_favorite"]
    );
}

echo json_encode($books);
?>
