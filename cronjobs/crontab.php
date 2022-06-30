<?php
//$code = $session->data['language'];
//session_start();
// $link = mysqli_connect("localhost", "root", "", "livixa_live_db_copy");
$link = mysqli_connect("localhost", "quailakr_ocar795", "SMSX021p.j]97..(", "quailakr_ocar795");
// Check connection
if ($link === false) {
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
$count_down = 0;

while ($count_down < 55) {

    $get_customers = "SELECT
oc.firstname,oc.email,oc.lastname,oc.app_customer_id,oc.customer_id,
o.order_id,o.total,o.shipping_address_1,
o.shipping_address_2,o.telephone,o.shipping_city,o.shipping_postcode,
o.shipping_country,o.shipping_zone,
op.name,op.product_id,op.quantity,op.mac_address,op.picture,spp.id AS subscription_package_price_id,spp.package_id,spp.subscription_id
FROM ocn8_customer oc
INNER JOIN ocn8_order o ON oc.customer_id = o.customer_id
INNER JOIN ocn8_order_product op ON o.order_id = op.order_id
INNER JOIN ocn8_order_option opt ON op.order_product_id = opt.order_product_id AND op.order_id = opt.order_id
INNER JOIN ocn8_product_option_value opov ON opt.product_option_value_id= opov.product_option_value_id
INNER JOIN subscription_package_price spp ON opov.option_value_id = spp.option_id
INNER JOIN packages pack ON pack.id = spp.package_id
INNER JOIN subscriptions sub ON sub.id = spp.subscription_id

WHERE
(o.mobile_api_status = 0 OR o.mobile_api_status IS NULL) AND
o.customer_id != 0 AND o.order_status_id =17";
    $get_customers = mysqli_query($link, $get_customers);
    if (mysqli_num_rows($get_customers) > 0) {
        $i = 0;
        while ($row = $get_customers->fetch_assoc()) {
            $data[] = $row;
        }

        $response = [];

        foreach ($data as $key => $customer) {
            $address = $customer['shipping_address_1'] . ' ' . $customer['shipping_address_2'] . ' ' . $customer['shipping_city'] . ' ' .
                $customer['shipping_postcode'] . ' ' . $customer['shipping_country'] . ' ' . $customer['shipping_zone'];
            $email = $customer['email'];
            $customer['customer_name'] = $customer['firstname'] . ' ' . $customer['lastname'];
            if ($customer['app_customer_id'] == null || $customer['app_customer_id'] == 0) {
                $curl = curl_init();
                curl_setopt_array($curl, array(
                    CURLOPT_URL => 'http://158.101.228.216/live/smarthome/v2/register_user.php',
                    CURLOPT_RETURNTRANSFER => true,
                    CURLOPT_ENCODING => '',
                    CURLOPT_MAXREDIRS => 10,
                    CURLOPT_TIMEOUT => 0,
                    CURLOPT_FOLLOWLOCATION => true,
                    CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
                    CURLOPT_CUSTOMREQUEST => 'POST',
                    CURLOPT_POSTFIELDS => array('email' => $email, 'customer_id', $customer['customer_id'], 'customer_email' => $email,
                        'customer_name' => $customer['customer_name'], 'customer_contact_number' => $customer['telephone'],
                        'customer_shipping_address' => $address),
                ));
                $response = curl_exec($curl);

                curl_close($curl);
                $response = json_decode($response, TRUE);
                $app_customer_id = $response['sh_result']['sh_user_id'];
                $sql = "UPDATE ocn8_customer SET app_customer_id=" . $app_customer_id . " WHERE customer_id=" . $customer['customer_id'];

                mysqli_query($link, $sql);

                if (!key_exists($customer['order_id'], $response)) {
                    $response[$customer['order_id']] = [
                        'user_id' => $app_customer_id,
                        'ref_customer_id' => $customer['customer_id'],
                        'ref_order_id' => $customer['order_id'],
                        'shipping_address' => $address,
                    ];
                }
                $order_details = array(
                    'product_name' => $customer['name'],
                    'quantity' => $customer['quantity'],
                    'product_id' => $customer['product_id'],
                    'mac_address' => $customer['mac_address'],
                    'image' => $customer['picture'],
                    'subscription_type' => $customer['package_id'],
                    // package_id
                    'subscription_period_id' => $customer['subscription_id']
                );
                $response[$customer['order_id']]["order_detail"][] = $order_details;
            } else {

                if (!key_exists($customer['order_id'], $response)) {
                    $response[$customer['order_id']] = [
                        'user_id' => $customer['app_customer_id'],
                        'ref_customer_id' => $customer['customer_id'],
                        'ref_order_id' => $customer['order_id'],
                        'shipping_address' => $address,
                    ];
                }
                $order_details = array(
                    'product_name' => $customer['name'],
                    'product_id' => $customer['product_id'],
                    'quantity' => $customer['quantity'],
                    'mac_address' => $customer['mac_address'],
                    'image' => $customer['picture'],
                    'subscription_type' => $customer['package_id'],
                    'subscription_period_id' => $customer['subscription_id']
                );
                $response[$customer['order_id']]["order_detail"][] = $order_details;
            }

        }


        foreach ($response as $record) {

            $user_id = $record['user_id'];
            $ref_customer_id = $record['ref_customer_id'];
            $ref_order_id = $record['ref_order_id'];
            $shipping_address = $record['shipping_address'];
            $order_detail = json_encode($record['order_detail'], true);
            $sql_get = "SELECT * from order_details_mobile_api where ref_order_id = $ref_order_id";
            $sql_get = mysqli_query($link, $sql_get);
            $check_get = $sql_get->fetch_assoc();
            if ($check_get == null) {
                $sql = "INSERT INTO order_details_mobile_api (user_id,ref_customer_id,ref_order_id,shipping_address,order_details) VALUES ('$user_id','$ref_customer_id','$ref_order_id','$shipping_address','$order_detail')";
                $result = $link->query($sql) === TRUE;
                $sql_update = "UPDATE ocn8_order SET mobile_api_status= 1  WHERE order_id = " . $ref_order_id;
                $result = $link->query($sql_update) === TRUE;
            } else {
                echo 'All Records Updated in Database';
            }
        }
    } else {
        echo 'All Records Updated in Database';
    }
    $file = fopen("custom_log.txt","a");
    fwrite($file,'CronTab_'.date('Y-m-d H:i:s').'. Loop Number:'.$count_down .PHP_EOL);
    fclose($file);
    $count_down++;
    sleep( 5 );
}
$link->close();
?>
