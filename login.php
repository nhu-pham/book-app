<?php
require "db.php"; // kết nối MySQL

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = trim($_POST["email"]);
    $password = $_POST["password"];

    $stmt = $conn->prepare("SELECT * FROM users WHERE email = ?");
    $stmt->bind_param("s", $email);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($row = $result->fetch_assoc()) {
        if (password_verify($password, $row["password_hash"])) {
            echo json_encode([
                "success" => true,
                "user" => [
                    "user_id" => $row["user_id"],
                    "username" => $row["username"],
                    "email" => $row["email"],
                    "avatar_url" => $row["avatar_url"]
                ]
            ]);
        } else {
            echo json_encode(["success" => false, "message" => "Invalid password"]);
        }
    } else {
        echo json_encode(["success" => false, "message" => "Email not found"]);
    }
}
?>
