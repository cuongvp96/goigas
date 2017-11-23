<?php

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $response = array();
    $idcuahang = $_POST['idcuahang'];
    require_once __DIR__ . '/db_connect.php';
    $db = new DB_CONNECT();
    $conn = $db->connect();
    $sql = "delete from cuahang where idcuahang='$idcuahang'";
    $result = $conn->query($sql);
   
    if ($result) {
         $db->close($conn);
        $response["success"] = 1;
        $response["message"] = "cua hang successfully delete.";
        echo json_encode($response);
    } else {
         $db->close($conn);
        $response["success"] = 0;
        $response["message"] = "An error delete.";
        echo json_encode($response);
    }
} else {
    echo "Error try again!";
}
?>		
