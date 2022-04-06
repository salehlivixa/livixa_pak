package com.livixa.client;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.mediatek.elian.ElianNative;

public class AllocationWifiActivity extends BaseActivity implements
		OnClickListener {
	private static final String LOG_TAG = "AllocationWifiActivity";

	private Button back;
	private Button mButtonStartV1;
	private Button mButtonStartV4;
	private Button mButtonStartV1V4;
	private Button mButtonStop;

	private TextView notice;

	private EditText mSSID;
	private EditText authmode;
	private EditText wifiPwd;
	private CheckBox show;
	private String custom;

	private ElianNative elian;

	private byte AuthModeOpen = 0x00;
	private byte AuthModeShared = 0x01;
	private byte AuthModeAutoSwitch = 0x02;
	private byte AuthModeWPA = 0x03;
	private byte AuthModeWPAPSK = 0x04;
	private byte AuthModeWPANone = 0x05;
	private byte AuthModeWPA2 = 0x06;
	private byte AuthModeWPA2PSK = 0x07;
	private byte AuthModeWPA1WPA2 = 0x08;
	private byte AuthModeWPA1PSKWPA2PSK = 0x09;

	public String getVersion() {
		try {
			PackageManager manager = this.getPackageManager();
			PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void showDialog(Context context, String title, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.app);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton(R.string.exit,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						finish();
					}
				});

		builder.show();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		WifiManager mWifiManager;
		String mConnectedSsid;
		byte mAuthMode = 0;
		super.onCreate(savedInstanceState);
		Log.d(LOG_TAG, "AboutActivity onCreate");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.allocation_wifi);

		boolean result = ElianNative.LoadLib();
		Log.i("", "result = " + result);
		if (!result) {
			showDialog(this, "Error", "can't load elianjni lib");
		}

		elian = new ElianNative();

		findView();
		mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

		if (mWifiManager.isWifiEnabled()) {
			WifiInfo WifiInfo = mWifiManager.getConnectionInfo();
			mConnectedSsid = WifiInfo.getSSID();
			int iLen = mConnectedSsid.length();

			if (iLen == 0) {
				return;
			}

			Log.i("", "mConnectedSsidd = " + mConnectedSsid);
			if (mConnectedSsid.startsWith("\"")
					&& mConnectedSsid.endsWith("\"")) {
				mConnectedSsid = mConnectedSsid.substring(1, iLen - 1);
			}
			// mConnectedSsid = mConnectedSsid.replace('\"', ' ');
			// mConnectedSsid = mConnectedSsid.trim();
			mSSID.setText(mConnectedSsid);

			List<ScanResult> ScanResultlist = mWifiManager.getScanResults();
			for (int i = 0, len = ScanResultlist.size(); i < len; i++) {
				ScanResult AccessPoint = ScanResultlist.get(i);
				custom = AccessPoint.capabilities;
				Log.i("", "AccessPoint = " + AccessPoint.toString());
				Log.i("", "custom = " + custom);
				if (AccessPoint.SSID.equals(mConnectedSsid)) {
					boolean WpaPsk = AccessPoint.capabilities
							.contains("WPA-PSK");
					boolean Wpa2Psk = AccessPoint.capabilities
							.contains("WPA2-PSK");
					boolean Wpa = AccessPoint.capabilities.contains("WPA-EAP");
					boolean Wpa2 = AccessPoint.capabilities
							.contains("WPA2-EAP");

					if (AccessPoint.capabilities.contains("WEP")) {
						mAuthMode = AuthModeOpen;
						break;
					}

					if (WpaPsk && Wpa2Psk) {
						mAuthMode = AuthModeWPA1PSKWPA2PSK;
						break;
					} else if (Wpa2Psk) {
						mAuthMode = AuthModeWPA2PSK;
						break;
					} else if (WpaPsk) {
						mAuthMode = AuthModeWPAPSK;
						break;
					}

					if (Wpa && Wpa2) {
						mAuthMode = AuthModeWPA1WPA2;
						break;
					} else if (Wpa2) {
						mAuthMode = AuthModeWPA2;
						break;
					} else if (Wpa) {
						mAuthMode = AuthModeWPA;
						break;
					}

					mAuthMode = AuthModeOpen;
				}
			}
			authmode.setText(String.valueOf(mAuthMode));

			int protoVersion = elian.GetProtoVersion();
			int libVersion = elian.GetLibVersion();

			notice.setText("Version:" + getVersion() + ", libVersion:"
					+ libVersion + ", protocolVersion:" + protoVersion);
		}
	}

	private void findView() {

		notice = (TextView) findViewById(R.id.allocation_notice);

		back = (Button) findViewById(R.id.allocaltion_back);
		mButtonStartV1 = (Button) findViewById(R.id.allocation_startV1);
		mButtonStartV4 = (Button) findViewById(R.id.allocation_startV4);
		mButtonStartV1V4 = (Button) findViewById(R.id.allocation_startV1V4);
		mButtonStop = (Button) findViewById(R.id.allocation_done);
		mButtonStop.setEnabled(false);

		mSSID = (EditText) findViewById(R.id.allocation_wifiName);
		authmode = (EditText) findViewById(R.id.allocation_authmode);
		wifiPwd = (EditText) findViewById(R.id.allocation_wifiPwd);
		show = (CheckBox) findViewById(R.id.allocation_wifi_show);
		// custom = (EditText) findViewById(R.id.allocation_custom);

		back.setOnClickListener(this);
		mButtonStartV1.setOnClickListener(this);
		mButtonStartV4.setOnClickListener(this);
		mButtonStartV1V4.setOnClickListener(this);
		mButtonStop.setOnClickListener(this);
		show.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				 
				if (isChecked) {
					// 如果选中，显示密�?
					wifiPwd.setTransformationMethod(HideReturnsTransformationMethod
							.getInstance());
				} else {
					// 否则隐藏密码
					wifiPwd.setTransformationMethod(PasswordTransformationMethod
							.getInstance());
				}

			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.allocaltion_back:
			startSet(0);
			finish();
			overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);
			break;
		case R.id.allocation_startV1:
			startSet(1);
			break;
		case R.id.allocation_startV4:
			startSet(4);
			break;
		case R.id.allocation_startV1V4:
			startSet(14);
			break;
		case R.id.allocation_done:
			startSet(0);
			finish();
			break;
		default:
			break;
		}
	}

	private void startSet(int id) {
		Log.i("", "custom = " + custom);
		String ssid = mSSID.getText().toString();
		String password = wifiPwd.getText().toString();
		String auth = authmode.getText().toString();
		byte authmode = 0;
		try {
			authmode = (byte) Integer.parseInt(auth);
		} catch (NumberFormatException e) {
			authmode = 0;
		}
		new MyThread(id, ssid, password, custom, authmode).start();

		if (id != 0) {
			startWifi();
		} else {
			stopWifi();
		}
	}

	private void startWifi() {
		mButtonStartV1.setEnabled(false);
		mButtonStartV4.setEnabled(false);
		mButtonStartV1V4.setEnabled(false);
		mButtonStop.setEnabled(true);
	}

	private void stopWifi() {
		mButtonStartV1.setEnabled(true);
		mButtonStartV4.setEnabled(true);
		mButtonStartV1V4.setEnabled(true);
		mButtonStop.setEnabled(false);
	}

	class MyThread extends Thread {
		private int id;
		private String ssid;
		private String password;
		private String custom;
		private byte authmode;

		public MyThread(int id, String ssid, String password, String custom,
				byte authmode) {
			this.id = id;
			this.ssid = ssid;
			this.password = password;
			this.custom = custom;
			this.authmode = authmode;
		}

		@Override
		public void run() {
			switch (id) {
			case 0:
				elian.StopSmartConnection();
				break;
			case 1:
				elian.InitSmartConnection(null, 1, 0);
				elian.StartSmartConnection(ssid, password, custom, authmode);
				break;
			case 4:
				elian.InitSmartConnection(null, 0, 1);
				elian.StartSmartConnection(ssid, password, custom, authmode);
				break;
			case 14:
				elian.InitSmartConnection(null, 1, 1);
				elian.StartSmartConnection(ssid, password, custom, authmode);
				break;

			default:
				break;
			}
			
		}
	}
}
