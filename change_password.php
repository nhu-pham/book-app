<?php
require_once 'db.php';
header("Content-Type: application/json");

$user_id = $_POST['user_id'];
$old_password = $_POST['old_password'];
$new_password = $_POST['new_password'];

$stmt = $conn->prepare("SELECT password_hash FROM users WHERE user_id=?");
$stmt->bind_param("i", $user_id);
$stmt->execute();
$stmt->bind_result($hashedPassword);
$stmt->fetch();
$stmt->close();

if (!password_verify($old_password, $hashedPassword)) {
    echo json_encode([
        "success" => false,
        "message" => "Mật khẩu cũ không chính xác"
    ]);
    exit;
}

$newHashedPassword = password_hash($new_password, PASSWORD_BCRYPT);

$stmt = $conn->prepare("UPDATE users SET password_hash=? WHERE user_id=?");
$stmt->bind_param("si", $newHashedPassword, $user_id);

if ($stmt->execute()) {
    echo json_encode([
        "success" => true,
        "message" => "Cập nhật mật khẩu thành công",
        "responsePwd" => $newHashedPassword
    ]);
} else {
    echo json_encode([
        "success" => false,
        "message" => "Cập nhật mật khẩu không thành công"
    ]);
}
$stmt->close();
$conn->close();
