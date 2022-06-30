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

    foreach ($mac_addresses as $key => $mac_address) {
        // if (isset($_FILES['picture']['name'][$key]) && $_FILES['picture']['name'][$key] != '') {
        //     // File name
        //     $filename = $_FILES['picture']['name'][$key];
        //     $filenamx[]=$filename;

        //     // Get extension
        //     $ext = strtolower(pathinfo($filename, PATHINFO_EXTENSION));

        //     // Valid image extension
        //     $valid_ext = array("png", "jpeg", "jpg");

        //     // Check extension
        //     if (in_array($ext, $valid_ext)) {
        //         // File path
        //         $path = $upload_location . $filename;
        //         // Upload file
        //         if (move_uploaded_file($_FILES['picture']['tmp_name'][$key], $path)) {
        //             $files_arr[] = $path;
        //         }
        //     }
        // }
        // attempt insert query execution
        // ,picture
        $sql = "INSERT INTO order_mac_addresses (order_id, order_product_id, mac_address,product_quantity) VALUES ('$order_id', '$order_product_id', '$mac_address','$product_quantity')";
        // ,'$filename'
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
     $response = ($saved_mac != '') ? '<b style="color:green">Successfully Updated: </b>'.$saved_mac : '';
      $failed_mac = substr($failed_mac, 1);
    $response .= ($failed_mac != '') ? '<b style="color:red">Already Exists: </b>'.$failed_mac : '';
    echo $response;

}
function apply_quotes($item)
{
    return "'" . $item . "'";
}
?>