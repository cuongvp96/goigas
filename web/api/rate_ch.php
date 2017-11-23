<?php

$response = array();
$votescore_new = $_POST['votescore'];
$idcuahang = $_POST['idcuahang'];
require_once __DIR__ . '/db_connect.php';
//$link_img      = $_POST['link_img'];
$db = new DB_CONNECT();
$conn = $db->connect();

$sql1 = "SELECT * FROM cuahang WHERE idcuahang='$idcuahang'";
$result1 = $conn->query($sql1);
if (mysqli_num_rows($result1) > 0) {
    $row = mysqli_fetch_assoc($result1);
    $votescore = $row["votescore"];
    $votenumber = $row["votenumber"];
    $votenumber_new = $votenumber + 1;
}
$votescore_n = ($votescore_new + $votescore * ($votenumber)) / $votenumber_new;
if (strstr($votescore_n, ",")) {
//    $votescore_n = str_replace(".", "", $votescore_n); // replace dots (thousand seps) with blancs 
    $votescore_n = str_replace(",", ".", $votescore_n); // replace ',' with '.' 
}
$sql = "update cuahang set votescore='$votescore_n',votenumber='$votenumber_new' where idcuahang='$idcuahang'";
$result = $conn->query($sql);

if ($result) {
    $db->close($conn);
    $response["success"] = 1;
    $response["message"] = "Vote thanh cong.";
    echo json_encode($response);
} else {
    $db->close($conn);
    $response["success"] = 0;
    $response["message"] = "Oops! An error occurred.";
    echo json_encode($response);
}
?>		
