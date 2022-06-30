<?php

use PhpOffice\PhpSpreadsheet\Reader\Xlsx;

$conn = mysqli_connect("localhost", "quailakr_ocar795", "SMSX021p.j]97..(", "quailakr_ocar795");

// Check connection
if ($conn === false) {
    die("ERROR: Could not connect. " . mysqli_connect_error());
}

require_once('/home/quailakr/livixa.tracking.me/vendor/autoload.php');

if (isset($_POST["import"])) {

    $allowedFileType = [
        'application/vnd.ms-excel',
        'text/xls',
        'text/xlsx',
        'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
    ];
    $failed_mac = '';
    $saved_mac = '';
    if (in_array($_FILES["file"]["type"], $allowedFileType)) {

        $targetPath = '/home/quailakr/livixa.tracking.me/upload/' . $_FILES['file']['name'];
        move_uploaded_file($_FILES['file']['tmp_name'], $targetPath);

        $Reader = new \PhpOffice\PhpSpreadsheet\Reader\Xlsx();

        $spreadSheet = $Reader->load($targetPath);
        $excelSheet = $spreadSheet->getActiveSheet();
        $spreadSheetAry = $excelSheet->toArray();
        $sheetCount = count($spreadSheetAry);
        if($sheetCount<=1){
            $response = '<b style="color:red">Data Sheet is Empty! Please add Records before uploading </b>';
            echo $response;
        }
        for ($i = 1; $i <= $sheetCount; $i++) {
            $product_id = "";
            if (isset($spreadSheetAry[$i][0])) {
                $product_id = mysqli_real_escape_string($conn, $spreadSheetAry[$i][0]);
            }
            $mac_address = "";
            if (isset($spreadSheetAry[$i][1])) {
                $mac_address = mysqli_real_escape_string($conn, $spreadSheetAry[$i][1]);
            }
            if (!empty($product_id) || !empty($mac_address)) {
                $query = "insert into mac_inventory(product_id,mac_address) values(?,?)";
                $paramType = "ss";
                $paramArray = array(
                    $product_id,
                    $mac_address
                );
                $query = "insert into mac_inventory(product_id,mac_address) values('" . $product_id . "','" . $mac_address . "')";
//                $result = mysqli_query($conn, $query);

                if (mysqli_query($conn, $query)) {
                    $type = "success";
//                    $saved_mac[] = $mac_address . ": Imported into the Database";
                    $saved_mac = $saved_mac . ',' . $mac_address;
                } else {
                    $type = "error";
//                    $failed_mac[] = $mac_address . ": Already Exists";
                    $failed_mac = $failed_mac . ',' . $mac_address;
                }
            }
        }
    } else {
        $type = "error";
        $message = "Invalid File Type. Upload Excel File.";
    }
    $saved_mac = substr($saved_mac, 1);
    $response = ($saved_mac != '') ? '<b style="color:green">Successfully Added: </b>'.$saved_mac : '';
    $failed_mac = substr($failed_mac, 1);
    $response.='<br>';
    $response .= ($failed_mac != '') ? '<b style="color:red">Already Exists: </b>'.$failed_mac : '';
    echo $response;
}
?>