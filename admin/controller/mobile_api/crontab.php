<?php
//$code = $session->data['language'];
session_start();
 $link = mysqli_connect("localhost", "quailakr_ocar795", "SMSX021p.j]97..(", "quailakr_ocar795");
// Check connection
if ($link === false) {
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
$get_customers = "SELECT oc.firstname,oc.email,oc.lastname,oc.app_customer_id,oc.customer_id,o.order_id,o.subscription_package_id,o.package_price,o.shipping_address_1,o.shipping_address_2,o.telephone,o.shipping_city,o.shipping_postcode,o.shipping_country,o.shipping_zone,op.name,op.product_id,op.quantity,op.mac_address,op.picture FROM ocn8_customer oc INNER JOIN ocn8_order o ON oc.customer_id = o.customer_id INNER JOIN ocn8_order_product op ON o.order_id = op.order_id WHERE (o.mobile_api_status = 0 OR o.mobile_api_status IS NULL) AND o.customer_id != 0";
$get_customers = mysqli_query($link, $get_customers);
if (mysqli_num_rows($get_customers) > 0) {
    $i = 0;
    while ($row = $get_customers->fetch_assoc()) {
        $data[] = $row;
    }

    $response = [];

    foreach ($data as $key => $customer) {
        $get_package_details = "SELECT * FROM subscription_package_price where id=".$customer['subscription_package_id'];

        $get_package_details =mysqli_query($link, $get_package_details);
        if(mysqli_num_rows($get_package_details)>0){
            $row = mysqli_fetch_assoc($get_package_details);
             $customer['package_id'] = $row['package_id'];
             $customer['subscription_id']=$row['subscription_id'];
            $customer['package_price']=$row['price'];
        }
//        var_dump($customer);
        $address = $customer['shipping_address_1'] . ' ' . $customer['shipping_address_2'] . ' ' . $customer['shipping_city'] . ' ' .
            $customer['shipping_postcode'] . ' ' . $customer['shipping_country'] . ' ' . $customer['shipping_zone'];
        $email = $customer['email'];
        if ($customer['app_customer_id'] == null) {
            $curl = curl_init();
            curl_setopt_array($curl, array(
                CURLOPT_URL => 'http://158.101.228.216/live/smarthome/v2/register.php',
                CURLOPT_RETURNTRANSFER => true,
                CURLOPT_ENCODING => '',
                CURLOPT_MAXREDIRS => 10,
                CURLOPT_TIMEOUT => 0,
                CURLOPT_FOLLOWLOCATION => true,
                CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
                CURLOPT_CUSTOMREQUEST => 'POST',
                CURLOPT_POSTFIELDS => array('email' => $email, 'contact' => $customer['telephone'], 'address' => $address,
                    'username' => $email),
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
                    'subscription_id' => $customer['subscription_id'],
                    'package_id' => $customer['package_id'],
                    'package_price' => $customer['package_price']
                ];
            }
            $order_details = array(
                'product_name' => $customer['name'],
                'quantity' => $customer['quantity'],
                'product_id' => $customer['product_id'],
                'mac_address' => $customer['mac_address'],
                'image' => $customer['picture']
            );
            $response[$customer['order_id']]["order_detail"][] = $order_details;
        } else {

            if (!key_exists($customer['order_id'], $response)) {
                $response[$customer['order_id']] = [
                    'user_id' => $customer['app_customer_id'],
                    'ref_customer_id' => $customer['customer_id'],
                    'ref_order_id' => $customer['order_id'],
                    'shipping_address' => $address,
                    'subscription_id' => $customer['subscription_id'],
                    'package_id' => $customer['package_id'],
                    'package_price' => $customer['package_price']
                ];
            }
            $order_details = array(
                'product_name' => $customer['name'],
                'product_id' => $customer['product_id'],
                'quantity' => $customer['quantity'],
                'mac_address' => $customer['mac_address'],
                'image' => $customer['picture']
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
}else{
    echo 'All Records Updated in Database';
}
$link->close();
?>
