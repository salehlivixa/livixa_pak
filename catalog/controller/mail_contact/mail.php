<?php

use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

require("Exception.php");
require("PHPMailer.php");
require("SMTP.php");

$email = 'sales@livixa.com';
// $email = 'fasukhera@gmail.com';

$mail = new PHPMailer;
$mail->isSMTP();
$mail->SMTPDebug = 0; // 0 = off (for production use) - 1 = client messages - 2 = client and server messages
$mail->Host = "smtp.googlemail.com"; // use $mail->Host = gethostbyname('smtp.gmail.com'); // if your network does not support SMTP

$mail->Port = 587;
$mail->SMTPSecure = 'tls';
$mail->SMTPAuth = true;
$mail->Username = 'sales@livixa.com';
$mail->Password = 'l!vX@1!2@';
$mail->setFrom('sales@livixa.com', 'Livixa');
$mail->addAddress($email);
$mail->Subject = 'Contact Form';
$first_name = $_POST['first_name'];
$last_name = $_POST['last_name'];
$email_client = $_POST['email'];
$phone = $_POST['phone'];
$message = $_POST['message'];

$mail->msgHTML("sales@livixa.com");
$mail->msgHTML("<div style='background-color: #3f3c43;'>
<table width='100%' style='color:white;border-spacing:0px;'>
<tr><td>&nbsp;</td></tr>
<tr><td></td><td align='center' ><img style='width:30%' src='https://tumbitech.com/Logo.png' /></td><td></td></tr>
<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr>
<tr><td></td><td width='80%'>Hi Livixa,</td><td></td></tr>
<tr><td width='10%'></td>
<td width='80%'><br/> First Name:$first_name $last_name</td><td width='10%'></td></tr>
<tr><td width='10%'></td>
<td width='80%'><br/> Email: $email_client</td><td width='10%'></td></tr>
<tr><td width='10%'></td>
<td width='80%'><br/> Phone:$phone</td><td width='10%'></td></tr>
<tr><td width='10%'></td>
<td width='80%'><br/> Message:$message</td><td width='10%'></td></tr>

<tr><td>&nbsp;</td></tr>
<tr><td colspan=3 width='100%' align='center'></td></tr>
<tr><td></td><td>Thanks,</td></tr>
<tr><td></td><td>Livixa Contact Form</td></tr>
<tr><td></td><td>&nbsp;</td></tr>
<tr style='background-color: #e7e5e8;border:none;border-collapse: collapse;'><td style='border:none' colspan='3'>&nbsp;</td></tr>
<tr style='background-color: #e7e5e8;border-spacing:0px;border-collapse: collapse;'><td></td><td colspan='2'></td></tr>
<tr style='background-color: #e7e5e8;border:none;border-collapse: collapse;'><td  colspan='3'>&nbsp;</td></tr>
</table></div>"); //$mail->msgHTML(file_get_contents('contents.html'), __DIR__); //Read an HTML message body from an external file, convert referenced images to embedded,
$mail->AltBody = 'HTML messaging not supported';
$mail->send();
?>