<?php
header("Content-Type: application/json");
require_once "db.php";

$book_id = isset($_GET['book_id']) ? intval($_GET['book_id']) : null;

if ($book_id === null) {
    echo json_encode(array("error" => "Missing book_id"));
    exit;
}

$sql = "SELECT ratings.comment, ratings.rating, DATE(ratings.created_at) AS cmtDate, users.username, users.avatar_url
        FROM ratings
        JOIN users ON ratings.user_id = users.user_id
        WHERE ratings.book_id = $book_id 
        ORDER BY ratings.created_at DESC";

$result = mysqli_query($conn, $sql);

if (!$result) {
    // Trả về lỗi nếu câu truy vấn sai
    echo json_encode(array("error" => mysqli_error($conn)));
    $conn->close();
    exit;
}

$comments = array();

while ($row = $result->fetch_assoc()) {
    $comment = array(
        'username' => $row['username'],
        'comment' => $row['comment'],
        'rating' => $row['rating'],
        'cmtDate' => $row['cmtDate'],
        'avatar_url' => $row['avatar_url']
    );
    array_push($comments, $comment);
}

// Luôn trả về JSON, kể cả khi mảng rỗng
echo json_encode($comments);

$conn->close();
?>
