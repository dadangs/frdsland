<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: null");
header("access-control-allow-credentials: true");
header("access-control-allow-headers: null");
//https://chrome.google.com/webstore/detail/allow-control-allow-origi/nlfbmbojpeacfghkpbjhddihlkkiljbi/related
require 'config.php';
require 'Slim/Slim.php';

\Slim\Slim::registerAutoloader();
$app = new \Slim\Slim();

$app->post('/login','login'); /* User login */
$app->post('/inventaris','inventaris'); /* Inventaris */
$app->post('/inventarisSave','inventarisSave'); /* Inventaris Save*/
$app->run();

/************************* USER LOGIN *************************************/
/* ### User login ### */
function login() {
    
    $request = \Slim\Slim::getInstance()->request();
    $data = json_decode($request->getBody());
    
    try {
        
        $db = getDB();
        $userData ='';
        $sql = "SELECT user_id, name, email, username FROM users WHERE (username=:username or email=:username) and password=:password ";
        $stmt = $db->prepare($sql);
        $stmt->bindParam("username", $data->username, PDO::PARAM_STR);
        $password=md5($data->password);
        $stmt->bindParam("password", $password, PDO::PARAM_STR);
        $stmt->execute();
        $mainCount=$stmt->rowCount();
        $userData = $stmt->fetch(PDO::FETCH_OBJ);
        
        if(!empty($userData))
        {
            $user_id=$userData->user_id;
            $userData->token = apiToken($user_id);
        }
        
        $db = null;
         if($userData){
               $userData = json_encode($userData);
                echo '{"userData": ' .$userData . '}';
            } else {
               echo '{"error":{"text":"Bad request wrong username and password"}}';
            }

           
    }
    catch(PDOException $e) {
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }
}

/************************* INVENTARIS LERENG *************************************/
/* ### Inventaris ### */

function inventaris(){
    $request = \Slim\Slim::getInstance()->request();
   
    try {
            $feedData = '';
            $db = getDB();
            $sql = "SELECT * FROM m_inventaris ORDER BY kode DESC";
            $stmt = $db->prepare($sql);
            $stmt->execute();
            $feedData = $stmt->fetchAll(PDO::FETCH_OBJ);
           
            $db = null;
            echo '{"feedData": ' . json_encode($feedData) . '}';
       
    } catch(PDOException $e) {
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }    
}

function inventarisSave(){

    $request = \Slim\Slim::getInstance()->request();
    $data = json_decode($request->getBody());
    $kode_inventaris=$data->kode_inventaris;
    $tapak_nomor_lereng=$data->tapak_nomor_lereng;
    $geometrik_jenis_lereng=$data->geometrik_jenis_lereng;
    
    try {
            $uid = uniqid();
            $feedData = '';
            $db = getDB();
            $sql = "INSERT INTO t_inventaris ( id, kode_inventaris, tapak_nomor_lereng, geometrik_jenis_lereng) VALUES (:uid, :kode_inventaris,:tapak_nomor_lereng,:geometrik_jenis_lereng)";
            $stmt = $db->prepare($sql);
			$stmt->bindParam("uid", $uid, PDO::PARAM_STR);
            $stmt->bindParam("kode_inventaris", $kode_inventaris, PDO::PARAM_STR);
            $stmt->bindParam("tapak_nomor_lereng", $tapak_nomor_lereng, PDO::PARAM_STR);
			$stmt->bindParam("geometrik_jenis_lereng", $geometrik_jenis_lereng, PDO::PARAM_STR);
            $stmt->execute();

            $sql1 = "SELECT * FROM t_inventaris WHERE id=:uid ORDER BY kode_inventaris DESC LIMIT 1";
            $stmt1 = $db->prepare($sql1);
            $stmt1->bindParam("uid", $uid, PDO::PARAM_STR);
            $stmt1->execute();
            $feedData = $stmt1->fetch(PDO::FETCH_OBJ);
            $db = null;
            echo '{"feedData": ' . json_encode($feedData) . '}';
       
    } catch(PDOException $e) {
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }

}


?>
