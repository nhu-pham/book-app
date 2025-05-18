<?php
require_once 'db.php';
header("Content-Type: application/json");

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    if (isset($_GET['user_id'])) {
        $userId = intval($_GET['user_id']);

        $sql = "SELECT user_id, username, email, avatar_url FROM users WHERE user_id=?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("i", $userId);

        if ($stmt->execute()) {
            $result = $stmt->get_result();
            if ($result->num_rows > 0) {
                $user = $result->fetch_assoc();
                $response['success'] = true;
                $response['user_id'] = $user['user_id'];
                $response['username'] = $user['username'];
                $response['email'] = $user['email'];
                $response['avatar_url'] = $user['avatar_url'];
            } else {
                $response['success'] = false;
                $response['message'] = "Không tìm thấy user";
            }
        } else {
            $response['success'] = false;
            $response['message'] = "Lỗi truy vấn";
        }
    } else {
        $response['success'] = false;
        $response['message'] = "Thiếu tham số user_id";
    }
} else {
    $response['success'] = false;
    $response['message'] = "Sai phương thức";
}

echo json_encode($response);
?>
