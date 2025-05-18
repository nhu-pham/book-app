<?php
require_once 'db.php';
header("Content-Type: application/json");

$user_id = $_POST['user_id'];
$old_password = $_POST['old_password'];
$new_password = $_POST['new_password'];

$stmt = $conn->prepare("SELECT password_hash FROM users WHERE id=?");
$stmt->bind_param("i", $user_id);
$stmt->execute();
$stmt->bind_result($hashedPassword);
$stmt->fetch();
$stmt->close();

if (!password_verify($old_password, $hashedPassword)) {
    echo json_encode([
        "success" => false,
        "message" => "Old password is incorrect"
    ]);
    exit;
}

$newHashedPassword = password_hash($new_password, PASSWORD_BCRYPT);

$stmt = $conn->prepare("UPDATE users SET password_hash=? WHERE user_id=?");
$stmt->bind_param("si", $newHashedPassword, $user_id);

if ($stmt->execute()) {
    echo json_encode([
        "success" => true,
        "message" => "Password updated",
        "responsePwd" => $newHashedPassword 
    ]);
} else {
    echo json_encode([
        "success" => false,
        "message" => "Failed to update password"
    ]);
}
$stmt->close();
$conn->close();
