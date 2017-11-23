<?php
$idcuahang = $_GET['idcuahang'];
$response = array();
require_once __DIR__ . '/db_connect.php';
$db = new DB_CONNECT();
$conn = $db->connect();
$sql = "SELECT * FROM comment WHERE idcuahang='" . $idcuahang . "'";
$result = $conn->query($sql);

if (mysqli_num_rows($result) > 0) {
    $response["comment"] = array();

    while ($row = mysqli_fetch_assoc($result)) {

        $ch = array();
        $ch["idcuahang"] = $row["idcuahang"];
        $ch["cmtname"] = $row["cmtname"];
        $ch["cmttext"] = $row["cmttext"];

        array_push($response["comment"], $ch);
    }
    $db->close($conn);
    $response["success"] = 1;
    $response["number_items"] = mysqli_num_rows($result);
    echo json_encode($response);
} else {
    $db->close($conn);
    $response["success"] = 0;
    $response["message"] = "khong co comment nao";
    echo json_encode($response);
}
?>
			