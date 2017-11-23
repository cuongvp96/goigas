<?php

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $response      = array();
    $tencuahang    = $_POST['tencuahang'];
    $loaigas       = $_POST['loaigas'];
    $giagas        = $_POST['giagas'];
    $sdt           = $_POST['sdt'];
    $chucuahang    = $_POST['chucuahang'];
    $diadiem       = $_POST['diadiem'];
    $latlng        = $_POST['latlng'];
    $linkimg      = $_POST['linkimg'];
    $user_id       = $_POST['user_id'];
    $votescore     = $_POST['votescore'];
    $votenumber    = $_POST['votenumber'];
    $commentnumber = $_POST['commentnumber'];
    
    require_once __DIR__ . '/db_connect.php';
    $db     = new DB_CONNECT();
    $conn   = $db->connect();
    $sql    = "INSERT INTO cuahang(tencuahang,loaigas,giagas,sdt,chucuahang,diadiem,latlng,linkimg,user_id,votescore,votenumber,commentnumber)
    VALUES('$tencuahang','$loaigas',$giagas,'$sdt','$chucuahang','$diadiem', '$latlng','$linkimg','$user_id',$votescore,$votenumber,$commentnumber)";
    $result = $conn->query($sql);
    
    if ($result) {
        $db->close($conn);
        $response["success"] = 1;
        $response["message"] = "Cua hang successfully created.";
        echo json_encode($response);
    } else {
        $db->close($conn);
        $response["success"] = 0;
        $response["message"] = "An error inserted.";
        echo json_encode($response);
    }
} else {
    echo "Error try again!";
}
?>        