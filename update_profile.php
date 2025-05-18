<?php
require_once 'db.php';
header("Content-Type: application/json");

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $userId = $_POST['user_id'];
    $name = $_POST['username'];
    $email = $_POST['email'];


    $avatarUrl = '';
    if (isset($_FILES['avatar']['name'])) {
        $avatarName = uniqid() . "_" . basename($_FILES['avatar']['name']);
        $targetDir = "uploads/";
        if (!is_dir($targetDir)) {
            mkdir($targetDir, 0755, true);
        }
        $targetFile = $targetDir . $avatarName;

        if (move_uploaded_file($_FILES['avatar']['tmp_name'], $targetFile)) {
            $avatarUrl = $targetFile;
        }
    }

    $sql = "UPDATE users SET username=?, email=?" . ($avatarUrl ? ", avatar_url=?" : "") . " WHERE user_id=?";
    $stmt = $conn->prepare($sql);

    if ($avatarUrl) {
        $stmt->bind_param("sssi", $name, $email, $avatarUrl, $userId);
    } else {
        $stmt->bind_param("ssi", $name, $email, $userId);
    }

    if ($stmt->execute()) {
        $response['success'] = true;
        $response['message'] = "Cập nhật thành công";
    } else {
        $response['success'] = false;
        $response['message'] = "Lỗi khi cập nhật";
    }
} else {
    $response['success'] = false;
    $response['message'] = "Sai phương thức";
}

echo json_encode($response);
?>
