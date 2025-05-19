<?php
include 'db.php';
header("Content-Type: application/json");

$token = $_POST['token'] ?? '';
$new_password = $_POST['new_password'] ?? '';

if (empty($token) || empty($new_password)) {
    http_response_code(400);
    echo json_encode(["message" => "Thiếu dữ liệu"]);
    exit;
}

$stmt = $conn->prepare("SELECT user_id FROM reset_tokens WHERE token = ?");
$stmt->bind_param("s", $token);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows == 1) {
    $row = $result->fetch_assoc();
    $user_id = $row['user_id'];

    $hashed_password = password_hash($new_password, PASSWORD_DEFAULT);

    $stmt = $conn->prepare("UPDATE users SET password_hash = ? WHERE user_id = ?");
    $stmt->bind_param("si", $hashed_password, $user_id);
    $stmt->execute();

    // Xóa token sau khi sử dụng
    $stmt = $conn->prepare("DELETE FROM reset_tokens WHERE token = ?");
    $stmt->bind_param("s", $token);
    $stmt->execute();

    echo json_encode(["message" => "Đặt lại mật khẩu thành công"]);
} else {
    http_response_code(400);
    echo json_encode(["message" => "Token không hợp lệ"]);
}
