<?php
header("Content-Type: application/json");
require_once "db.php";

$user_id = isset($_GET['user_id']) ? intval($_GET['user_id']) : 0;

$sql = "
    SELECT 
        books.book_id, books.category_id, categories.category_name,
        books.title, books.author, books.description,
        books.cover_url, books.file_url,
        ROUND(AVG(ratings.rating), 1) AS avg_rating,
        EXISTS (
            SELECT 1 FROM favorites f 
            WHERE f.book_id = books.book_id AND f.user_id = ?
        ) AS is_favorite
    FROM books
    JOIN categories ON books.category_id = categories.category_id
    LEFT JOIN ratings ON books.book_id = ratings.book_id
    GROUP BY books.book_id
    ORDER BY books.book_id DESC
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
        "avg_rating" => $row["avg_rating"] ?? "0.0",
        "isFavorite" => (bool)$row["is_favorite"]
    );
}

echo json_encode($books);
?>
