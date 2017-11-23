<?php

$response = array();

$iduser = $_GET['id'];
// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();
$conn = $db->connect();
// get all products from city table
$sql = "SELECT * FROM user WHERE user_id='" . $iduser . "'";
$result = $conn->query($sql);

// check for empty result
if (mysqli_num_rows($result) > 0) {
    $response["user"] = array();

    while ($row = mysqli_fetch_assoc($result)) {
        // temp user array
        $ch = array();
        $ch["user_id"] = $row["user_id"];
        $ch["user_name"] = $row["user_name"];
        $ch["sdt"] = $row["sdt"];
        $ch["address"] = $row["address"];
        $ch["fbid"] = $row["fbid"];
        $ch["googleid"] = $row["googleid"];
        $ch["fullname"] = $row["fullname"];
        $ch["role"] = $row["role"];
        array_push($response["user"], $ch);
    }
    $db->close($conn);
    $response["success"] = 1;
    $response["number_items"] = mysqli_num_rows($result);
    echo json_encode($response);
} else {
    $db->close($conn);
    $response["success"] = 0;
    $response["message"] = "khong co user nay";
    echo json_encode($response);
}
?>
			