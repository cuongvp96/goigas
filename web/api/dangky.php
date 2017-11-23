<?php

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $response = array();

    $pass = $_POST['password'];
    $name = $_POST['user_name'];
    $sdt = $_POST['sdt'];
    $address = $_POST['address'];
    $fbid = $_POST['fbid'];
    $googleid = $_POST['googleid'];
    $fullname = $_POST['fullname'];
    $role = $_POST['role'];
    require_once __DIR__ . '/db_connect.php';
    $db = new DB_CONNECT();
    $conn = $db->connect();

    $sql = "SELECT * FROM user WHERE user_name='" . $name . "'";
    $result = $conn->query($sql);

    if (mysqli_num_rows($result) > 0) {
        $db->close($conn);

        $response["success"] = 2;
        echo json_encode($response);
    } else {
        $sql1 = "INSERT INTO user(password,user_name,sdt,address,fbid,googleid,fullname,role) VALUES('$pass','$name','$sdt','$address','$fbid','$googleid','$fullname','$role')";
        $result1 = $conn->query($sql1);

        if ($result) {
            $db->close($conn);
            $response["success"] = 1;
            echo json_encode($response);
        } else {
            $db->close($conn);
            $response["success"] = 0;
            echo json_encode($response);
        }
    }
} else {
    echo "Error try again!";
}
?>		
