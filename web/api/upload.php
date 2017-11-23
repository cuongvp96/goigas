<?php

require_once __DIR__ . '/db_connect.php';
$db          = new DB_CONNECT();
$con         = $db->connect();
$upload_path = 'img/';

$server_ip  = gethostbyname(gethostname());
$upload_url = 'http://goigas.96.lt/goigas/img' . $upload_path;
$response   = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    if (isset($_POST['name']) and isset($_FILES['image']['name'])) {
        $name      = $_POST['name'];
        $fileinfo  = pathinfo($_FILES['image']['name']);
        $extension = $fileinfo['extension'];
        
        $file_path = $upload_path . basename($_FILES['image']['name']);
        try {
            move_uploaded_file($_FILES['image']['tmp_name'], $file_path);
            $url_im = 'http://goigas.96.lt/goigas/' . $file_path;
            $response['url']  = $url_im;
            $response['name'] = $name;
            $db->close($conn);
            echo json_encode($response);
            /*if(mysqli_query($con,$sql)){
            $response['error'] = false; 
            $response['url'] = $url_im; 
            $response['name'] = $name;
            echo json_encode($response);
            }*/
        }
        catch (Exception $e) {
                mysqli_close($con);
            $response['error']   = true;
            $response['message'] = $e->getMessage();
        }
            mysqli_close($con);
        echo json_encode($response);
    
    } else {
        $response['error']   = true;
        $response['message'] = 'Please choose a file';
    }
}
?>        