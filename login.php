<?php
require "db.php"; // kết nối MySQL
header('Content-Type: application/json');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = trim($_POST["email"]);
    $password = $_POST["password"];

    $stmt = $conn->prepare("SELECT * FROM users WHERE email = ?");
    $stmt->bind_param("s", $email);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($row = $result->fetch_assoc()) {
        if (password_verify($password, $row["password_hash"])) {
            // Tạo access token ngẫu nhiên
            $token = bin2hex(random_bytes(32)); // 64 ký tự hex

            $now = new DateTime('now', new DateTimeZone('Asia/Ho_Chi_Minh')); // Hoặc timezone bạn dùng
            $expires = clone $now;
            $expires->modify('+15 minutes');
            $expires_at = $expires->format('Y-m-d H:i:s');

            $user_id = $row["user_id"];

            // Lưu token vào bảng user_tokens
            $insertToken = $conn->prepare("INSERT INTO user_tokens (user_id, token, expires_at) VALUES (?, ?, ?)");
            $insertToken->bind_param("iss", $user_id, $token, $expires_at);
            $insertToken->execute();

            // Trả về thông tin người dùng và token
            echo json_encode([
                "success" => true,
                "token" => $token,
                "expires_at" => $expires_at,
                "user" => [
                    "user_id" => $row["user_id"],
                    "username" => $row["username"],
                    "email" => $row["email"],
                    "avatar_url" => $row["avatar_url"]
                ]
            ]);
        } else {
            echo json_encode(["success" => false, "message" => "Mật khẩu không đúng"]);
        }
    } else {
        echo json_encode(["success" => false, "message" => "Không tìm thấy email"]);
    }
}
?>
