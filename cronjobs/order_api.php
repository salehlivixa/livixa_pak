<?php
session_start();
$link = mysqli_connect("localhost", "quailakr_ocar795", "SMSX021p.j]97..(", "quailakr_ocar795");
// Check connection
if ($link === false) {
    die("ERROR: Could not connect. " . mysqli_connect_error());
}

$count_down = 0;
while ($count_down < 55) {
$file = fopen("custom_log.txt","a",1);
fwrite($file,'OrderApi'.date('Y-m-d H:i:s').'. Loop started Number:'.$count_down .PHP_EOL);

   
$get_customers = "SELECT * from order_details_mobile_api where status =0 OR status IS NULL ORDER BY retries ASC";
$get_customers = mysqli_query($link, $get_customers);

if (mysqli_num_rows($get_customers) > 0) {
    $i = 0;
    while ($row = $get_customers->fetch_assoc()) {
        $data[] = $row;
    }

    foreach ($data as $order) {
        
        $response_data = [];
        $curl = curl_init();
        curl_setopt_array($curl, array(
            CURLOPT_URL => 'http://158.101.228.216/live/smarthome/v2/createorder.php',
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_ENCODING => '',
            CURLOPT_MAXREDIRS => 10,
            CURLOPT_TIMEOUT => 5,
            CURLOPT_FOLLOWLOCATION => true,
            CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
            CURLOPT_CUSTOMREQUEST => 'POST',
            CURLOPT_POSTFIELDS => array('user_id' => $order['user_id'], 'ref_customer_id' => $order['ref_customer_id'], 'ref_order_id' => $order['ref_order_id'],
                'shipping_address' => $order['shipping_address'], 'order_detail' => $order['order_details']),
        ));

        $response = curl_exec($curl);

        if ($response === false) {
            $get_tries = "SELECT * from order_details_mobile_api where id=" . $order['id'];
            $get_tries = mysqli_query($link, $get_tries);
            if (mysqli_num_rows($get_tries) > 0) {
                $i = 0;
                while ($row1 = $get_tries->fetch_assoc()) {
                    $get_try[] = $row1;
                }
            }
            $retries = $get_try[0]['retries']+1;
            $retries_update = "UPDATE order_details_mobile_api SET retries='" . $retries . "'  WHERE id=" . $order['id'];
            $result = $link->query($retries_update) === TRUE;

            curl_close($curl);
        } else {
            curl_close($curl);
            $response = json_decode($response);
            if ($response->status == 1) {
                $response_data[] = $response;
                $sql_update = "UPDATE order_details_mobile_api SET api_response='" . json_encode($response_data) . "',status=1 WHERE id=" . $order['id'];
                $result = $link->query($sql_update) === TRUE;
                echo json_encode($response);
            }
        }
    }

}
    
    fwrite($file,'OrderApi'.date('Y-m-d H:i:s').'. Loop Number:'.$count_down .PHP_EOL);
   
    fclose($file);
    $count_down++;
    sleep( 5 );
}
$link->close();
?>
