<?php
require_once 'db.php';
header("Content-Type: application/json");

$token = $_POST['token'] ?? '';

$stmt = $conn->prepare("SELECT expires_at FROM reset_tokens WHERE token=?");
$stmt->bind_param("s", $token);
$stmt->execute();
$stmt->store_result();

if ($stmt->num_rows === 0) {
    echo json_encode(["success" => false, "message" => "Token không tồn tại"]);
    exit;
}

$stmt->bind_result($expires_at);
$stmt->fetch();

if (strtotime($expires_at) < time()) {
    echo json_encode(["success" => false, "message" => "Token đã hết hạn (quá 15 phút)"]);
    exit;
}

echo json_encode(["success" => true, "message" => "Token hợp lệ"]);
