<?php
header("Content-Type: application/json");
require "db.php";

$response = [];

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $username = trim($_POST['username'] ?? '');
    $email = trim($_POST['email'] ?? '');
    $password = $_POST['password'] ?? '';

    if (!$username || !$email || !$password) {
        $response['status'] = false;
        $response['message'] = "All fields are required.";
    } elseif (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $response['status'] = false;
        $response['message'] = "Invalid email format.";
    } else {
        $stmt = $conn->prepare("SELECT * FROM users WHERE email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $result = $stmt->get_result();

        if ($result->num_rows > 0) {
            $response['status'] = false;
            $response['message'] = "Email already registered.";
        } else {
            $password_hash = password_hash($password, PASSWORD_BCRYPT);
            $stmt = $conn->prepare("INSERT INTO users (username, email, password_hash) VALUES (?, ?, ?)");
            $stmt->bind_param("sss", $username, $email, $password_hash);
            if ($stmt->execute()) {
                $user_id = $stmt->insert_id;
                $response['status'] = true;
                $response['message'] = "Registration successful.";
                $response['user'] = [
                    'user_id' => $user_id,
                    'username' => $username,
                    'email' => $email,
                    'avatar_url' => null
                ];
            } else {
                $response['status'] = false;
                $response['message'] = "Database error.";
            }
        }
    }
} else {
    $response['status'] = false;
    $response['message'] = "Invalid request method.";
}

echo json_encode($response);
?>
