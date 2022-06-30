<?php

class Controllercheckoutsubscriptionfeaturedropdown extends Controller
{
    public function index()
    {

        $package = $_POST['package'];
        $subscription = $_POST['subscription'];
//        $price = $this->cart->getTotal();
        $data['total_products'] = $this->cart->countProducts();

        $link = mysqli_connect("localhost", "quailakr_ocar795", "SMSX021p.j]97..(", "quailakr_ocar795");

        // Check connection
        if ($link === false) {
            die("ERROR: Could not connect. " . mysqli_connect_error());
        }
//        p.package,s.subscription,
        $sql = 'SELECT spp.id,p.package,spp.price FROM subscription_package_price spp
JOIN packages p ON spp.package_id = p.id
JOIN subscriptions s ON spp.subscription_id = s.id where spp.subscription_id=' . $subscription . ' AND spp.package_id=' . $package;

        $spp = $link->query($sql);
        foreach ($spp as $sp) {
            $data['price'] = $sp['price'];
            $data['subscription_feature_price_id'] = $sp['id'];
            $data['name']=$sp['package'];
        }

        $data['total'] = $data['price'] * $data['total_products'];
//        $data['order_id'] = $this->session->data['order_id'];
        $this->session->data['subscription_feature_data'] = $data;
        $this->session->data['subscription_feature_data']['per_product_price'] = $data['price'];
        $this->session->data['subscription_feature_data']['package_name'] = $data['name'];

        //$order_total_final = $this->session->data['subscription_feature_data']['total'];
        //$order_subscription_feature_price_id = $this->session->data['subscription_feature_data']['subscription_feature_price_id'];

//        print_r($this->session->data['subscription_feature_data']);
        echo json_encode($data);
    }

    public function save()
    {
        $this->load->language('checkout/checkout');

        $json = array();

        // Validate if customer is logged in.
        if (!$this->customer->isLogged()) {
            $json['redirect'] = $this->url->link('checkout/checkout', '', true);
        }

        // Validate cart has products and has stock.
        if ((!$this->cart->hasProducts() && empty($this->session->data['vouchers'])) || (!$this->cart->hasStock() && !$this->config->get('config_stock_checkout'))) {
            $json['redirect'] = $this->url->link('checkout/cart');
        }

        // Validate minimum quantity requirements.
        $products = $this->cart->getProducts();

        foreach ($products as $product) {
            $product_total = 0;

            foreach ($products as $product_2) {
                if ($product_2['product_id'] == $product['product_id']) {
                    $product_total += $product_2['quantity'];
                }
            }

            if ($product['minimum'] > $product_total) {
                $json['redirect'] = $this->url->link('checkout/cart');

                break;
            }
        }

        if (!$json) {
            $this->load->model('account/address');

            if (isset($this->request->post['payment_address']) && $this->request->post['payment_address'] == 'existing') {
                if (empty($this->request->post['address_id'])) {
                    $json['error']['warning'] = $this->language->get('error_address');
                } elseif (!in_array($this->request->post['address_id'], array_keys($this->model_account_address->getAddresses()))) {
                    $json['error']['warning'] = $this->language->get('error_address');
                }

                if (!$json) {
                    $this->session->data['payment_address'] = $this->model_account_address->getAddress($this->request->post['address_id']);

                    unset($this->session->data['payment_method']);
                    unset($this->session->data['payment_methods']);
                }
            } else {
                if ((utf8_strlen(trim($this->request->post['firstname'])) < 1) || (utf8_strlen(trim($this->request->post['firstname'])) > 32)) {
                    $json['error']['firstname'] = $this->language->get('error_firstname');
                }

                if ((utf8_strlen(trim($this->request->post['lastname'])) < 1) || (utf8_strlen(trim($this->request->post['lastname'])) > 32)) {
                    $json['error']['lastname'] = $this->language->get('error_lastname');
                }

                if ((utf8_strlen(trim($this->request->post['address_1'])) < 3) || (utf8_strlen(trim($this->request->post['address_1'])) > 128)) {
                    $json['error']['address_1'] = $this->language->get('error_address_1');
                }

                if ((utf8_strlen($this->request->post['city']) < 2) || (utf8_strlen($this->request->post['city']) > 32)) {
                    $json['error']['city'] = $this->language->get('error_city');
                }

                $this->load->model('localisation/country');

                $country_info = $this->model_localisation_country->getCountry($this->request->post['country_id']);

                if ($country_info && $country_info['postcode_required'] && (utf8_strlen(trim($this->request->post['postcode'])) < 2 || utf8_strlen(trim($this->request->post['postcode'])) > 10)) {
                    $json['error']['postcode'] = $this->language->get('error_postcode');
                }

                if ($this->request->post['country_id'] == '') {
                    $json['error']['country'] = $this->language->get('error_country');
                }

                if (!isset($this->request->post['zone_id']) || $this->request->post['zone_id'] == '' || !is_numeric($this->request->post['zone_id'])) {
                    $json['error']['zone'] = $this->language->get('error_zone');
                }

                // Custom field validation
                $this->load->model('account/custom_field');

                $custom_fields = $this->model_account_custom_field->getCustomFields($this->config->get('config_customer_group_id'));

                foreach ($custom_fields as $custom_field) {
                    if ($custom_field['location'] == 'address') {
                        if ($custom_field['required'] && empty($this->request->post['custom_field'][$custom_field['location']][$custom_field['custom_field_id']])) {
                            $json['error']['custom_field' . $custom_field['custom_field_id']] = sprintf($this->language->get('error_custom_field'), $custom_field['name']);
                        } elseif (($custom_field['type'] == 'text') && !empty($custom_field['validation']) && !filter_var($this->request->post['custom_field'][$custom_field['location']][$custom_field['custom_field_id']], FILTER_VALIDATE_REGEXP, array('options' => array('regexp' => $custom_field['validation'])))) {
                            $json['error']['custom_field' . $custom_field['custom_field_id']] = sprintf($this->language->get('error_custom_field'), $custom_field['name']);
                        }
                    }
                }

                if (!$json) {
                    $address_id = $this->model_account_address->addAddress($this->customer->getId(), $this->request->post);

                    $this->session->data['payment_address'] = $this->model_account_address->getAddress($address_id);

                    // If no default address ID set we use the last address
                    if (!$this->customer->getAddressId()) {
                        $this->load->model('account/customer');

                        $this->model_account_customer->editAddressId($this->customer->getId(), $address_id);
                    }

                    unset($this->session->data['payment_method']);
                    unset($this->session->data['payment_methods']);
                }
            }
        }

        $this->response->addHeader('Content-Type: application/json');
        $this->response->setOutput(json_encode($json));
    }
}