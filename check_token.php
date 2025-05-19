<?php
require_once 'db.php';
header('Content-Type: application/json');

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $token = $_POST['token'] ?? '';

    $stmt = $conn->prepare("SELECT user_id FROM user_tokens WHERE token = ? AND expires_at > NOW()");
    $stmt->bind_param("s", $token);
    $stmt->execute();
    $result = $stmt->get_result();
    $user = $result->fetch_assoc();

    if ($user) {
        echo json_encode(["valid" => true, "user_id" => $user['user_id']]);
    } else {
        echo json_encode(["valid" => false]);
    }
} else {
    echo json_encode(["valid" => false, "message" => "Invalid request method"]);
}
?>
