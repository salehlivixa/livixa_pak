<?php
//header('Content-Type: text/html; charset=utf-8');
$link = mysqli_connect("localhost", "quailakr_ocar795", "SMSX021p.j]97..(", "quailakr_ocar795");
mysqli_set_charset($link, 'utf8');
//mysqli_query("SET NAMES 'utf8'");
//mysqli_query("SET CHARACTER SET 'utf8'");

if ($link === false) {
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
$sql = "SELECT mi.id, mi.product_id, pd.name, mi.mac_address FROM mac_inventory mi
left JOIN order_mac_addresses oma ON mi.mac_address=oma.mac_address
INNER JOIN ocn8_product_description pd ON mi.product_id=pd.product_id ORDER BY mi.product_id ASC";


$get_details = $link->query($sql);
$mac_info = [];
foreach ($get_details as $key => $detail) {
    $mac_info[] = $detail;
    $detail_mac_address = $detail['mac_address'];
    $sql_check = "SELECT * from order_mac_addresses where mac_address ='$detail_mac_address'";
    $result = mysqli_query($link,$sql_check);
    $count = mysqli_num_rows($result);
    if($count>=1){
        $mac_info[$key]['status'] = 'Assigned';
    }else{
        $mac_info[$key]['status'] = 'Un-Assigned';
    }
}
echo json_encode($mac_info);
?>