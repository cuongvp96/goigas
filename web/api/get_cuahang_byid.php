<?php

$response = array();
$idch = $_GET['idcuahang'];
require_once __DIR__ . '/db_connect.php';
$db = new DB_CONNECT();
$conn = $db->connect();
$sql = "SELECT * FROM cuahang WHERE idcuahang='" . $idch . "'";
$result = $conn->query($sql);

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
    $response["success"] = 1;
    $response["number_items"] = mysqli_num_rows($result);
    echo json_encode($response);
} else {
    $db->close($conn);
    $response["success"] = 0;
    $response["message"] = "Khong co cua hang nao";
    echo json_encode($response);
}
?>