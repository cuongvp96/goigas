<?php

$response = array();
//header("Content-type: application/json; charset=utf-8");
//  header('Content-Type: text/html; charset=utf-8');
//mb_internal_encoding("UTF-8");
require_once __DIR__ . '/db_connect.php';
$db = new db_connect();
$conn = $db->connect();
$sql = "SELECT * FROM cuahang";
$result = $conn->query($sql);
//var_dump($result);
//die(1);
//json_encode($result);
//$conn->set_charset("utf8");
//printf("Initial character set: %s\n", $conn->character_set_name());
if (mysqli_num_rows($result) > 0) {
    $response["cuahang"] = array();
    while ($row = mysqli_fetch_assoc($result)) {
        $ch = array();
        $ch["idcuahang"] = $row["idcuahang"];
        $ch["tencuahang"] = $row["tencuahang"];
        $ch["loaigas"] = $row["loaigas"];
        $ch["giagas"] = $row["giagas"];
        $ch["sdt"] = $row["sdt"];
        $ch["chucuahang"] = $row["chucuahang"];
        $ch["diadiem"] = $row["diadiem"];
        $ch["latlng"] = $row["latlng"];
        $ch["linkimg"] = $row["linkimg"];
        $ch["user_id"] = $row["user_id"];
        $ch["votescore"] = $row["votescore"];
        $ch["votenumber"] = $row["votenumber"];
        $ch["commentnumber"] = $row["commentnumber"];
        array_push($response["cuahang"], $ch);
    }

    $db->close($conn);

    $response["number_items"] = mysqli_num_rows($result);
    echo json_encode($response);
} else {
     $db->close($conn);
    $response["success"] = 0;
    $response["message"] = "khong co cua hang nao";
    echo json_encode($response);
}
?>