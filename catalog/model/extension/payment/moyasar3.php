<?php

class ModelExtensionPaymentMoyasar3 extends Model
{
  public function getMethod($address, $total)
  {
    $this->load->language('extension/payment/moyasar3');

    if (!$this->isEnabled()) {

      return array();
    }
    $method_data = array(
      'code'  => 'moyasar3',
      'title' => $this->language->get('text_title'),
      'terms' => '',
      'sort_order' => $this->config->get('payment_moyasar3_sort_order')
    );

    return $method_data;
  }
  public function isEnabled()
  {
    $api_key = $this->config->get('payment_moyasar3_api_key');
    $enabled = $this->config->get('payment_moyasar3_status');

    return !empty($api_key) && $enabled == 1;
  }
}
