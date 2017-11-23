<?php

$response = array();
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $name = $_POST['user_name'];
    $pass = $_POST['password'];
    require_once __DIR__ . '/db_connect.php';
    $db = new DB_CONNECT();
    $conn = $db->connect();
    $sql = "select*from user where user_name='$name' and password='$pass'";
    $result = $conn->query($sql);
  
    if (mysqli_num_rows($result) > 0) {
        $response["user"] = array();
        while ($row = mysqli_fetch_assoc($result)) {
            $ch = array();
            //$ch["user_name"] = $row["user_name"];
            //$ch["fullname"] = $row["fullname"];
            $ten = $row["fullname"];
            $user_name = $row["user_name"];
            $user_id = $row["user_id"];
            array_push($response["user"], $ch);
        }
          $db->close($conn);
        $response["user_name"] = $user_name;
        $response["user_id"] = $user_id;
        $response["fullname"] = $ten;
        $response["success"] = 1;
        echo json_encode($response);
    } else {
          $db->close($conn);
        $response["success"] = 0;
        echo json_encode($response);
    }
} else {
    echo "Error try again!";
}
?>