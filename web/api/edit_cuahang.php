<?php

require_once __DIR__ . '/db_connect.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $response = array();
    $idcuahang = $_POST['idcuahang'];
    $tencuahang = $_POST['tencuahang'];
    $loaigas = $_POST['loaigas'];
    $giagas = $_POST['giagas'];
    $sdt = $_POST['sdt'];
    $chucuahang = $_POST['chucuahang'];
    $diadiem = $_POST['diadiem'];
    $latlng = $_POST['latlng'];
    //$link_img      = $_POST['link_img'];
    $db = new DB_CONNECT();
    $conn = $db->connect();
    $sql = "update cuahang set tencuahang='$tencuahang',loaigas='$loaigas',giagas='$giagas',sdt='$sdt',
	chucuahang='$chucuahang',diadiem='$diadiem',latlng='$latlng' where idcuahang='$idcuahang'";
    $result = $conn->query($sql);
   
    if ($result) {
         $db->close($conn);
        $response["success"] = 1;
        $response["message"] = "cua hang successfully edit.";
        echo json_encode($response);
    } else {
         $db->close($conn);
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        echo json_encode($response);
    }
} else {
    echo "Error try again!";
}
?>		
