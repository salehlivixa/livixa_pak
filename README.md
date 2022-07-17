# Livixa

Opencart 3.0.3.8
# Prerequisites:
-Apache Version: 2.4.54 <br>
-PHP Version:    7.3.33 <br>
-MySQL Version:  10.3.35-MariaDB-cll-lve

Add following files in crontabs at 5 minutes interval to sync data with mobile apps:
-cronjobs/crontab.php
-cronjobs/order_api.php

# Moyasar OpenCart Plugin
[Moyasar](https://moyasar.com) Payment Gateway plugin for opencart compatible with versions 3.0.x

## Installation
------------------------------------------------------------------------
1. Copy the content of each folder, to your opencart installation keeping the folder structure.
2. Login to the Open Cart admin section and go to Extensions -> Payments
3. Find Moyasar Payment Method in the list of extensions
4. Click _Install_ and then _Edit_ the payment module settings
5. Set Extension Status to Enable and put your `publishable_key` and Save
6. Go to Extensions -> Modifications
7. Click the Refresh Button

## التثبيت
------------------------------------------------------------------------
١‫.‬أنسخ محتويات كل مجلد وألصقه في المسار المماثل في مجلد متجرك.
٢‫.‬سجل دخولك إلى لوحة التحكم واذهب إلى الإضافات -> طرق الدفع.
٣‫.‬ ستجد مُيسر في القائمة اضغط على تثبيت ثم تعديل ‪.‬
٤‫.‬ غيرحالة الحالة  إلى تمكين وضع `publishable_key` في المكان المخصص واضغط حفظ.
٥‫.‬ اذهب إلى الإضافات -> ادارة التعديلات‪.‬
٦‫.‬ اضغط على زر ‪`‬تحديث‪`‬

## Source Code details
### Admin folder
It has the following files
1. admin->controller->payment->moyasar3.php (Controller for the admin panel Moyasar page)
2. admin->language->(en-gb/ar)->payment->moyasar3.php (English and Arabic string constants used on the page)
3. admin->view->template->payment->moyasar3.twig (Template file for the admin page)

### CATALOG folder
It has the following files
1. catalog->controller->payment->moyasar3.php (Controller for the payment gateway on checkout page)
2. catalog->language->(en-gb/ar)->payment->moyasar3.php (English and Arabic string Constants for Catalog pages)
3. catalog->model->payment->moyasar3.php (Model for the plugin)
4. catalog->view->theme->default->template->payment->moyasar3.twig (Template file for the admin page)
