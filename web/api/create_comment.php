<?php

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $response = array();
    $idcuahang = $_POST['idcuahang'];
    $name = $_POST['name'];
    $text = $_POST['text'];
    require_once __DIR__ . '/db_connect.php';
    $db = new DB_CONNECT();
    $conn = $db->connect();
    $sql = "INSERT INTO comment(idcuahang, cmtname,cmttext) VALUES('$idcuahang','$name', '$text')";
    $result = $conn->query($sql);

    if ($result) {
        $db->close($conn);
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
