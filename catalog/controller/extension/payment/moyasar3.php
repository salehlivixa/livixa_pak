<?php

class ControllerExtensionPaymentMoyasar3 extends Controller 
{
    public function index() 
    {
        $this->load->language('extension/payment/moyasar3');
        $this->load->model('extension/payment/moyasar3');
        $this->load->model('checkout/order');

        $order_info = $this->model_checkout_order->getOrder($this->session->data['order_id']);
        $data['id'] = $this->session->data['order_id'];

        $data['action'] = 'https://api.moyasar.com/v1/payments.html';
        $data['payment_moyasar3_api_key']   = $this->config->get('payment_moyasar3_api_key');
        $data['payment_moyasar3_payment_type'] = $this->config->get('payment_moyasar3_payment_type');
        $data['payment_moyasar3_payment_methods'] = [];
        array_push($data['payment_moyasar3_payment_methods'], ($data['payment_moyasar3_payment_type']['cc'])? 'creditcard' : '');
        array_push($data['payment_moyasar3_payment_methods'], ($data['payment_moyasar3_payment_type']['stcpay'])? 'stcpay' : '');
        array_push($data['payment_moyasar3_payment_methods'], ($data['payment_moyasar3_payment_type']['applepay'])? 'applepay' : '');
        $data['payment_moyasar3_payment_methods_json'] = json_encode($data['payment_moyasar3_payment_methods']);
        
        $data['callback_url'] = $this->url->link('extension/payment/moyasar3/callback', '', true);
        $data['validate_merchant_url'] = 'https://api.moyasar.com/v1/applepay/initiate';

        $data['amount'] = $this->currency->format($order_info['total'], $order_info['currency_code'], $order_info['currency_value'], false);
        $data['amount_in_halals'] = $this->currency->format($order_info['total'], $order_info['currency_code'], $order_info['currency_value'], false)*100;
        $data['language_code'] = $order_info['language_code'];
        $data['currency'] = $order_info['currency_code'];
        $data['country'] = $order_info['payment_iso_code_2'];
        $data['store_name'] = $order_info['store_name'];
        $data['firstname'] = $order_info['firstname'];
        $data['lastname'] = $order_info['lastname'];
        $data['email'] = $order_info['email'];
        $data['orderdate'] = $order_info['date_added'];
        $data['description'] = 'Order id #'.$data['id'];
        $data['domain_name'] = isset($this->request->server['HTTP_HOST'])? $this->request->server['HTTP_HOST'] : $this->request->server['SERVER_NAME'];

        $data['text_cc'] = $this->language->get('text_cc');
        $data['text_mada'] = $this->language->get('text_mada');
        $data['text_cc_mada'] = $this->language->get('text_cc_mada');
        $data['text_stc_pay'] = $this->language->get('text_stc_pay');
        $data['text_applepay'] = $this->language->get('text_applepay');
        $data['text_stcpay'] = $this->language->get('text_stcpay');
        $data['text_applepay_not_configured'] = $this->language->get('text_applepay_not_configured');
        $data['text_applepay_not_supported'] = $this->language->get('text_applepay_not_supported');
        
        $data['entry_cc_name'] = $this->language->get('entry_cc_name');
        $data['entry_cc_type'] = $this->language->get('entry_cc_type');
        $data['entry_cc_number'] = $this->language->get('entry_cc_number');
        $data['entry_cc_expire_date'] = $this->language->get('entry_cc_expire_date');
        $data['entry_cc_cvc'] = $this->language->get('entry_cc_cvc');
        $data['entry_payment'] = $this->language->get('entry_payment');
        $data['entry_mobile_number'] = $this->language->get('entry_mobile_number');
        $data['entry_send_otp'] = $this->language->get('entry_send_otp');
        $data['text_otp_value'] = $this->language->get('text_otp_value');
        $data['text_opt_ending_msg'] = $this->language->get('text_opt_ending_msg');

        $data['applepay_on_cancel_url'] =  $this->url->link('checkout/failure', '', true);
        $data['applepay_on_payment_success_url'] =  $this->url->link('checkout/success', '', true);

        $data['button_confirm'] = $this->language->get('button_confirm');

        return $this->load->view('extension/payment/moyasar3', $data);
    }

    public function callback() 
    {
        if($this->updateOrder($this->request->post, $this->request->get)){
          $this->response->redirect($this->url->link('checkout/success', '', true));
        } else {
          $this->response->redirect($this->url->link('checkout/checkout', '', true));
        }
    }

    // old callback in general form
    protected function updateOrder($post_data, $get_data) 
    {
        $this->load->language('extension/payment/payment_moyasar3');
    
        // $order_id is never used
        if (isset($get_data['id'])) {
          $order_id = $get_data['id'];
        } else {
          $order_id = 0;
        }

        $this->load->model('checkout/order');
        $order_info = $this->model_checkout_order->getOrder($this->session->data['order_id']);
        $order_amount = $this->currency->format($order_info['total'], $order_info['currency_code'], $order_info['currency_value'], false)*100;

        if ($order_info) {
          $data = array_merge($post_data,$get_data);
          //payment was made successfully
          if ((isset($data['status']) && $data['status'] == 'paid') || (isset($get_data['status']) && $get_data['status'] == 'paid')) {
            if($this->verifyAmount($get_data['id'], $order_amount)){
              $this->model_checkout_order->addOrderHistory($this->session->data['order_id'], $this->config->get('payment_moyasar3_order_status_id'), 'Payment is successfull');
            } else {
              $this->log->write('Moyasar payment is successful but amount does not match paid, possible tampering.');
              $this->model_checkout_order->addOrderHistory($this->session->data['order_id'], $this->config->get('config_order_status_id'), $this->language->get('text_price_manipulated'));
            }
          }else {
            $error = $this->language->get('text_unable');
          }
        }else {
          $error = $this->language->get('text_unable');
        }

        if (isset($error)) {
          if (isset($get_data['message'])) {
            $data['message'] = $get_data['message'];
          }
          else {
            $data['message'] = 'Payment failed';
          }
          $this->session->data['error'] = $this->language->get('text_unable').' '.$data['message'];
          $this->log->write('Moyasar payment failed: #'.$this->session->data['order_id'].' '. $data['message']);
          $this->model_checkout_order->addOrderHistory($this->session->data['order_id'], $this->config->get('payment_moyasar3_failed_order_status_id'), 'Payment Failed: '.$data['message']);

          return false;
        }

        return true;
    }

    public function registerInitiatedOrder()
    {
        $this->log->write($this->request);
        $this->addOrder($this->request->get['id'], $this->request->post['payment_id']);

    }

    public function verifyAmount($payment_id, $order_amount) 
    {
        $header = array(
          'Authorization: Basic '. base64_encode($this->config->get('payment_moyasar3_api_secret_key').':')
        );
        // TODO: change this url
        $curl = curl_init('https://api.moyasar.com/v1/payments/'.$payment_id);
        curl_setopt($curl, CURLOPT_HTTPHEADER, $header);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_TIMEOUT, 60);
        $response = json_decode(curl_exec($curl), true);
        if (isset($response['message'])) $this->log->write('Moyasar Payment Verification Failed: '.$response['message']);

        if (isset($response['amount']) && $response['amount'] == $order_amount) return true;
        
        else return false;
    }
}
