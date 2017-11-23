<?php

$response = array();
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $fbid = $_POST['fbid'];

    require_once __DIR__ . '/db_connect.php';
    $db = new DB_CONNECT();
    $conn = $db->connect();
    $sql = "select*from user where fbid='$fbid'";
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
        $response["fullname"] = $ten;
        $response["user_name"] = $user_name;
        $response["user_id"] = $user_id;
        $response["success"] = 1;
        echo json_encode($response);
    } else {
          $db->close($conn);
        $response["success"] = 0;
        $response["user_name"] = -1;
        echo json_encode($response);
    }
    $db->close($conn);
} else {
    echo "Error try again!";
}
?>