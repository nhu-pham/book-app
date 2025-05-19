<?php
$token = $_GET['token'];
header("Location: bookapp://reset-password?token=" . urlencode($token));
exit;
?>
