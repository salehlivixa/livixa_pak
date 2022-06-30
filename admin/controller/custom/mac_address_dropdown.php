<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    /* Attempt MySQL server connection. Assuming you are running MySQL
    server with default setting (user 'root' with no password) */
    $link = mysqli_connect("localhost", "quailakr_ocar795", "SMSX021p.j]97..(", "quailakr_ocar795");

    // Check connection
    if ($link === false) {
        die("ERROR: Could not connect. " . mysqli_connect_error());
    }


    // Count total files
    // $countfiles = count($_FILES['picture']['name']);

// Upload Location
    // $upload_location = "/home/quailakr/livixa.tracking.me/upload/";


// To store uploaded files path
    // $files_arr = array();

    // Escape user inputs for security
    $order_id = $_POST['order_id'];
    $order_product_id = $_POST['order_product_id'];
    $mac_addresses = $_POST['mac_addresses'];
    $product_quantity = $_POST['product_quantity'];
    $saved = [];
    $failed = [];
    $failed_mac = '';
    $saved_mac = '';
    $inven_unmatched = '';
    $get_product_id = "Select product_id from ocn8_order_product where order_product_id='$order_product_id'";
    $get_product_id = $link->query($get_product_id);
    foreach ($get_product_id as $product_idx) {
        $product_idx = $product_idx['product_id'];
    }
    foreach ($mac_addresses as $key => $mac_address) {

        $sql_mac_inven = "Select * from mac_inventory where product_id='$product_idx' AND mac_address='$mac_address'";
        $check_mac_inven = $link->query($sql_mac_inven);
        $check_mac_invenx = [];
        foreach ($check_mac_inven as $mac_inven) {
            $check_mac_invenx[] = $mac_inven['mac_address'];
        }
        if ($check_mac_inven->num_rows != 0) {
            $sql = "INSERT INTO order_mac_addresses (order_id, order_product_id, mac_address,product_quantity) VALUES ('$order_id', '$order_product_id', '$mac_address','$product_quantity')";
            if (mysqli_query($link, $sql)) {
                $saved[] = $mac_address . " added successfully <br>    ";
                $saved_mac = $saved_mac . ',' . $mac_address;

            } else {
                $failed[] = $mac_address . ' already exists in database <br>';

                $failed_mac = $failed_mac . ',' . $mac_address;
            }

            //deleting unselected data
            $mac_addressxx = array_map('apply_quotes', $mac_addresses);
            $mac_addressxx = implode(',', (array)$mac_addressxx);
            $sql = "DELETE FROM order_mac_addresses WHERE order_product_id=$order_product_id AND mac_address NOT IN ($mac_addressxx)";
            mysqli_query($link, $sql);
        } else {

            $inven_unmatched = $inven_unmatched . ',' . $mac_address;
        }
    }


    if ($saved_mac != null) {
        $get_mac = "Select * from order_mac_addresses where order_product_id=" . $order_product_id;
        $get_mac = $link->query($get_mac);
        $get_macx = [];
        foreach ($get_mac as $mac) {
            $get_macx[] = $mac['mac_address'];
        }
        // $filenamx= implode(',',(array)$filenamx);
        $mac_addressx = implode(',', (array)$get_macx);
        $sql_update = "UPDATE ocn8_order_product SET mac_address='" . $mac_addressx . "' WHERE order_product_id = " . $order_product_id;

        mysqli_query($link, $sql_update);
        // $sql_update = "UPDATE ocn8_order_product SET picture='" . $filenamx . "' WHERE order_product_id = " . $order_product_id;

        // mysqli_query($link, $sql_update);
    }
    mysqli_close($link);
    $saved_mac = substr($saved_mac, 1);
    $response = ($saved_mac != '') ? '<b style="color:green">Successfully Updated: </b>' . $saved_mac : '';
    $failed_mac = substr($failed_mac, 1);
    $response .= ($failed_mac != '') ? '<b style="color:red">Already Exists: </b>' . $failed_mac : '';
    $inven_unmatched = substr($inven_unmatched, 1);
    $response .= ($inven_unmatched != '') ? '<b style="color:orangered">Mac Address do not exist in Inventory against purchased product: </b>' . $inven_unmatched : '';

    echo $response;

}
function apply_quotes($item)
{
    return "'" . $item . "'";
}

?>