<?php
require_once 'db.php';
header("Content-Type: application/json");

$token = $_POST['token'] ?? '';

if (empty($token)) {
    echo json_encode([
        "success" => false,
        "message" => "Thiếu token trong yêu cầu."
    ]);
    exit;
}

$stmt = $conn->prepare("SELECT expires_at FROM reset_tokens WHERE token = ?");
if (!$stmt) {
    echo json_encode([
        "success" => false,
        "message" => "Lỗi truy vấn cơ sở dữ liệu."
    ]);
    exit;
}

$stmt->bind_param("s", $token);
$stmt->execute();
$stmt->store_result();

if ($stmt->num_rows === 0) {
    echo json_encode([
        "success" => false,
        "message" => "Token không tồn tại."
    ]);
    $stmt->close();
    exit;
}

$stmt->bind_result($expires_at);
$stmt->fetch();
$stmt->close();

if (strtotime($expires_at) < time()) {
    echo json_encode([
        "success" => false,
        "message" => "Token đã hết hạn (quá 15 phút)."
    ]);
    exit;
}

echo json_encode([
    "success" => true,
    "message" => "Token hợp lệ."
]);
exit;