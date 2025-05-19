<?php
header("Content-Type: application/json; charset=UTF-8");
require_once 'db.php';
require __DIR__ . '/../vendor/autoload.php';  

use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;



$response = [];
$email = $_POST['email'] ?? '';

// Kiểm tra user tồn tại
$stmt = $conn->prepare("SELECT user_id FROM users WHERE email = ?");
$stmt->bind_param("s", $email);
$stmt->execute();
$result = $stmt->get_result();

if (!$result || $result->num_rows === 0) {
    echo json_encode([
        'success' => false,
        'message' => 'Email không tồn tại'
    ]);
    exit;
}

$row = $result->fetch_assoc();
$user_id = $row['user_id'];

// Tạo token reset
$token = bin2hex(random_bytes(32));
$expires_at = date('Y-m-d H:i:s', time() + 900); // 15 phút

// Lưu vào bảng reset_tokens
$insert = $conn->prepare("INSERT INTO reset_tokens (user_id, token, expires_at) VALUES (?, ?, ?)");
$insert->bind_param("iss", $user_id, $token, $expires_at);
$insert->execute();


$mail = new PHPMailer(true);

try {
    $mail->isSMTP();
    $mail->Host       = 'smtp.gmail.com';
    $mail->SMTPAuth   = true;
    $mail->Username   = 'nhupham0311044@gmail.com';     
    $mail->Password   = 'iwzx fuvq ybkq czhc';        
    $mail->SMTPSecure = 'tls';
    $mail->Port       = 587;

    $mail->setFrom('nhupham0311044@gmail.com', 'BookApp');
    $mail->addAddress($email);     // email người nhận

    $mail->isHTML(true);
    $mail->Subject = '=?UTF-8?B?' . base64_encode('Đặt lại mật khẩu') . '?=';
    $mail->Body = "
    <p>Xin chào,</p>
    <p>Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu cho tài khoản của bạn.</p>
    <p>Vui lòng nhấn vào liên kết bên dưới để tiến hành đặt lại mật khẩu trong ứng dụng BookApp:</p>
    <p><a href=\"http://10.0.2.2/api/redirect.php?token=$token\">Đặt lại mật khẩu</a></p>
    <p>Liên kết này sẽ hết hạn sau 15 phút vì lý do bảo mật.</p>
    <p>Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này.</p>
    <p>Trân trọng,<br/>Đội ngũ hỗ trợ BookApp</p>
";


    $mail->send();
    echo json_encode([
        'success' => true,
        'message' => 'Email đặt lại mật khẩu đã được gửi'
    ]);
} catch (Exception $e) {
    http_response_code(500);
    echo json_encode([
        'success' => false,
        'message' => 'Không thể gửi email: ' . $mail->ErrorInfo
    ]);
}
