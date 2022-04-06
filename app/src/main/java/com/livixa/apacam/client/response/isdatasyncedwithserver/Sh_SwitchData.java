package com.livixa.apacam.client.response.isdatasyncedwithserver;

public class Sh_SwitchData {
	private String sh_switch_id;
	private String sh_title;
	private String sh_type;
	private String sh_mac_address;
	private String sh_ip_address;
	private String sh_is_connected;
	private String sh_is_activated;
	private String sh_room_id;
	private String sh_user_id;
	private String sh_port;
	private String sh_model_status;

	public Sh_SwitchData(String sh_switch_id, String sh_title, String sh_type, String sh_mac_address,
			String sh_ip_address, String sh_is_connected, String sh_is_activated, String sh_room_id, String sh_user_id,
			String sh_port, String sh_model_status) {
		this.sh_switch_id = sh_switch_id;
		this.sh_title = sh_title;
		this.sh_type = sh_type;
		this.sh_mac_address = sh_mac_address;
		this.sh_ip_address = sh_ip_address;
		this.sh_is_connected = sh_is_connected;
		this.sh_is_activated = sh_is_activated;
		this.sh_room_id = sh_room_id;
		this.sh_user_id = sh_user_id;
		this.sh_port = sh_port;
		this.sh_model_status = sh_model_status;
	}

	public String getSh_switch_id() {
		return sh_switch_id;
	}

	public void setSh_switch_id(String sh_switch_id) {
		this.sh_switch_id = sh_switch_id;
	}

	public String getSh_title() {
		return sh_title;
	}

	public void setSh_title(String sh_title) {
		this.sh_title = sh_title;
	}

	public String getSh_type() {
		return sh_type;
	}

	public void setSh_type(String sh_type) {
		this.sh_type = sh_type;
	}

	public String getSh_mac_address() {
		return sh_mac_address;
	}

	public void setSh_mac_address(String sh_mac_address) {
		this.sh_mac_address = sh_mac_address;
	}

	public String getSh_ip_address() {
		return sh_ip_address;
	}

	public void setSh_ip_address(String sh_ip_address) {
		this.sh_ip_address = sh_ip_address;
	}

	public String getSh_is_connected() {
		return sh_is_connected;
	}

	public void setSh_is_connected(String sh_is_connected) {
		this.sh_is_connected = sh_is_connected;
	}

	public String getSh_is_activated() {
		return sh_is_activated;
	}

	public void setSh_is_activated(String sh_is_activated) {
		this.sh_is_activated = sh_is_activated;
	}

	public String getSh_room_id() {
		return sh_room_id;
	}

	public void setSh_room_id(String sh_room_id) {
		this.sh_room_id = sh_room_id;
	}

	public String getSh_user_id() {
		return sh_user_id;
	}

	public void setSh_user_id(String sh_user_id) {
		this.sh_user_id = sh_user_id;
	}

	public String getSh_port() {
		return sh_port;
	}

	public void setSh_port(String sh_port) {
		this.sh_port = sh_port;
	}

	public String getSh_model_status() {
		return sh_model_status;
	}

	public void setSh_model_status(String sh_model_status) {
		this.sh_model_status = sh_model_status;
	}
}