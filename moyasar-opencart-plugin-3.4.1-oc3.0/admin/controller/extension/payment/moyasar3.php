<?php

class ControllerExtensionPaymentMoyasar3 extends Controller
{
    private $error = array();

    public function index()
    {
        $this->load->language('extension/payment/moyasar3');

        $this->document->setTitle($this->language->get('heading_title'));

        $this->load->model('setting/setting');

        if ($this->request->server['REQUEST_METHOD'] == 'POST' && $this->validate()) {

            $this->model_setting_setting->editSetting('payment_moyasar3', $this->request->post);
            $this->session->data['success'] = $this->language->get('text_success');
            $this->response->redirect($this->url->link('marketplace/extension', 'user_token=' . $this->session->data['user_token'] . '&type=payment', true));
        }
        $this->load->model('localisation/order_status');
        $data['order_statuses'] = $this->model_localisation_order_status->getOrderStatuses();
        $data['text_applepay_options'] = $this->language->get('text_applepay_options');

        $data['help_api_key'] = $this->language->get('help_api_key');
        $data['help_order_status'] = $this->language->get('help_order_status');
        
        $data['entry_sort_order'] = $this->language->get('entry_sort_order');
        $data['entery_api_key'] = $this->language->get('entery_api_key');
        $data['entery_api_secret_key'] = $this->language->get('entery_api_secret_key');
        $data['entry_status'] = $this->language->get('entry_status');
        $data['entry_order_status_id'] = $this->language->get('entry_order_status_id');
        $data['entry_failed_order_status_id'] = $this->language->get('entry_failed_order_status_id');
        $data['entry_payment_type'] = $this->language->get('entry_payment_type');
        $data['entery_enable_mada'] = $this->language->get('entery_enable_mada');
        $data['entery_enable_creditcard'] = $this->language->get('entery_enable_creditcard');
        $data['entery_enable_stc_pay'] = $this->language->get('entery_enable_stc_pay');
        $data['entery_enable_apple_pay'] = $this->language->get('entery_enable_apple_pay');
       
        $data['action'] = $this->url->link('extension/payment/moyasar3', 'user_token=' . $this->session->data['user_token'], true);
        $data['cancel'] = $this->url->link('marketplace/extension', 'user_token=' . $this->session->data['user_token'] . '&type=payment', true);

        if (isset($this->error['warning'])) {
            $data['error_warning'] = $this->error['warning'];
        } else {
            $data['error_warning'] = '';
        }

        $data['breadcrumbs'] = array();

        $data['breadcrumbs'][] = array(
            'text' => $this->language->get('text_home'),
            'href' => $this->url->link('common/dashboard', 'user_token=' . $this->session->data['user_token'], true)
        );
        $data['breadcrumbs'][] = array(
            'text' => $this->language->get('text_extension'),
            'href' => $this->url->link('marketplace/extension', 'user_token=' . $this->session->data['user_token'] . '&type=payment', true)
        );
        $data['breadcrumbs'][] = array(
            'text' => $this->language->get('heading_title'),
            'href' => $this->url->link('extension/payment/moyasar3', 'user_token=' . $this->session->data['user_token'], true)
        );

        $_items = [
            'payment_moyasar3_api_key',
            'payment_moyasar3_api_secret_key',
            'payment_moyasar3_payment_type',
            'payment_moyasar3_sort_order',
            'payment_moyasar3_help_api_key',
            'payment_moyasar3_order_status_id',
            'payment_moyasar3_failed_order_status_id',
            'payment_moyasar3_help_order_status',
            'payment_moyasar3_status'
        ];

        for ($item = 0; $item <= count($_items) - 1; $item++) {
            if (isset($this->request->post[$_items[$item]])) {
                $data[$_items[$item]] = $this->request->post[$_items[$item]];
            } else {
                $data[$_items[$item]] = $this->config->get($_items[$item]);
            }
        };

        $data['user_token'] = $this->session->data['user_token'];
        $data['header'] = $this->load->controller('common/header');
        $data['column_left'] = $this->load->controller('common/column_left');
        $data['footer'] = $this->load->controller('common/footer');
        $this->response->setOutput($this->load->view('extension/payment/moyasar3', $data));
    }

    protected function validate()
    {
        if (!$this->user->hasPermission('modify', 'extension/payment/moyasar3')) {
            $this->error['warning'] = $this->language->get('error_permission');
        }
        if (!$this->request->post['payment_moyasar3_api_key']) {
            $this->error['api_key'] = $this->language->get('error_api_key');
        }
        if (!$this->request->post['payment_moyasar3_api_secret_key']) {
            $this->error['api_key'] = $this->language->get('error_api_key');
        }
        if (!$this->request->post['payment_moyasar3_payment_type']) {
            $this->error['payment_type'] = $this->language->get('error_payment_type');
        }

        return !$this->error;
    }
}
