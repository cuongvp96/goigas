<?php
header("Content-type: application/json; charset=utf-8");
  header('Content-Type: text/html; charset=utf-8');
class DB_CONNECT {
    function __construct() {
        // connecting to database
        $this->connect();
    }

    // destructor
   // function __destruct() {
        // closing db connection
      //  $this->close();
    //}
    function connect() {
        // import database connection variables
        require_once __DIR__ . '/db_config.php';
        // Connecting to mysql database
       $con = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
       mysqli_query( $con, 'SET NAMES "utf8" COLLATE "utf8_unicode_ci"' );
     //  mysqli_set_charset($con, "utf8");
      // $con->set_charset("utf8");
       
        // returing connection cursor
        return $con;
    }

    /**
     * Function to close db connection
     */
    function close($con) {
        // closing db connection
        mysqli_close($con);
    }

}

?>