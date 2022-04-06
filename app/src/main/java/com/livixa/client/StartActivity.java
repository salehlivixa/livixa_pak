package com.livixa.client;

import java.util.Date;

import object.p2pipcam.nativecaller.NativeCaller;
import object.p2pipcam.system.SystemValue;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class StartActivity extends Activity implements OnClickListener {
	private static final String LOG_TAG = "StartActivity";
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			Intent in = new Intent(StartActivity.this, MainActivity.class);
			startActivity(in);
			finish();
		}
	};
	int CWJ_HEAP_SIZE = 20 * 1024 * 1024; // 10M���ڴ�
	private ImageView img;
	private PopupWindow popupWindow_bit = null;
	private View popv_bit = null;
	private Button button1, button2, button3, button4, button5, button6,
			button7, button8, button9;
	Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(LOG_TAG, "StartActivity onCreate");
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.start);
		// VMRuntime.getRuntime().setMinimumHeapSize(CWJ_HEAP_SIZE);
		// FrameLayout img=(FrameLayout)findViewById(R.id.start_root);
		// Animation animation = AnimationUtils.loadAnimation(this,
		// R.anim.splash_anim);
		// img.startAnimation(animation);
		
		
		Intent intentt = new Intent();
		intentt.setClass(StartActivity.this, BridgeService.class);
		stopService(intentt);
		
		img = (ImageView) findViewById(R.id.img);
		Intent intent = new Intent();
		intent.setClass(StartActivity.this, BridgeService.class);
		startService(intent);
		initExitPopupWindow_bit();
	}

	@Override
	protected void onResume() {
		 
		super.onResume();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				 
				popupWindow_bit.showAtLocation(img, Gravity.CENTER, 0, 0);
			}
		}, 2000);
	}

	public void initExitPopupWindow_bit() {
		LayoutInflater li = LayoutInflater.from(this);
		popv_bit = li.inflate(R.layout.popup_start, null);
		button1 = (Button) popv_bit.findViewById(R.id.button_start_1);
		button2 = (Button) popv_bit.findViewById(R.id.button_start_2);
		button3 = (Button) popv_bit.findViewById(R.id.button_start_3);
		button4 = (Button) popv_bit.findViewById(R.id.button_start_4);
		button5 = (Button) popv_bit.findViewById(R.id.button_start_5);
		button6 = (Button) popv_bit.findViewById(R.id.button_start_6);
		button7 = (Button) popv_bit.findViewById(R.id.button_start_7);
		button8 = (Button) popv_bit.findViewById(R.id.button_start_8);
		button9 = (Button) popv_bit.findViewById(R.id.button_start_9);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);
		button6.setOnClickListener(this);
		button7.setOnClickListener(this);
		button8.setOnClickListener(this);
		button9.setOnClickListener(this);
		popupWindow_bit = new PopupWindow(popv_bit,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		// popupWindow_bit.setAnimationStyle(R.style.AnimationPreview);
		popupWindow_bit.setFocusable(false);
		popupWindow_bit.setOutsideTouchable(false);
		popupWindow_bit.setBackgroundDrawable(new ColorDrawable(0));
		// popupWindow.update();
		popupWindow_bit
				.setOnDismissListener(new PopupWindow.OnDismissListener() {

					@Override
					public void onDismiss() {
						 
						popupWindow_bit.dismiss();
					}
				});
		popupWindow_bit.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupWindow_bit.dismiss();
				}
				return false;
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK)
			return true;
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		 
		switch (v.getId()) {
		case R.id.button_start_1:
			SystemValue.SystemSerVer = "EBGAEOBOKHJMHMJMENGKFIEEHBMDHNNEGNEBBCCCBIIHLHLOCIACCJOFHHLLJEKHBFMPLMCHPHMHAGDHJNNHIFBAMC";
			popupWindow_bit.dismiss();
			break;
		case R.id.button_start_2:
			SystemValue.SystemSerVer = "EFGBFFBJKCJNGBJHEBHLFFEKHNNHHANMHMFGBICMAKJELPKLDIAJDPPOGAKOICLDAANKKKDCOBNBBHCHJCMG";
			popupWindow_bit.dismiss();
			break;
		case R.id.button_start_3:
			SystemValue.SystemSerVer = "EFGBFFBJKAJOGCJOEIHCFGELGDNHHFNPHPFGBDCDAFJBLMLODCACDEPCGJLLILLLAPMMKADCOINIBPCNIF";
			popupWindow_bit.dismiss();
			break;
		case R.id.button_start_4:
			SystemValue.SystemSerVer = "EBGGEIBIKBJJHCJCEEGCEHEHHCMBHPNGGPEDBACABKIFLFLNCNBBCFOAHNKOJMKPBONOLCCJPJNOAEDEICMDILBMNCOCBK";
			popupWindow_bit.dismiss();
			break;
		case R.id.button_start_5:
			SystemValue.SystemSerVer = "EBGAEOBOKHJMHMJMENGKFIEEHBMDHNNEGNEBBCCCBIIHLHLOCIACCJOFHHLLJEKHBFMPLMCHPHMHAGDHJNNHIFBAMC";
			popupWindow_bit.dismiss();
			break;
		case R.id.button_start_6:
			SystemValue.SystemSerVer = "EBGFFBBMKFIBGCJJFDHGFJEDGLNMHKMMHBFIALDLAGIOKBKIDJBBDKPKHOKNICKOBJNILECPPONFAN";
			popupWindow_bit.dismiss();
			break;
		case R.id.button_start_7:
			SystemValue.SystemSerVer = "EFGBFFBJKDJBGNJBEBGMFOEIHPNFHGNOGHFBBOCPAJJOLDLNDBAHCOOPGJLMJGLKAOMPLMDIOLMFAFCJJPNEIGAM";
			popupWindow_bit.dismiss();
			break;
		case R.id.button_start_8:
			SystemValue.SystemSerVer = "EFGBFFBJKAJOGCJPECHIFMEKGCNBHHMBHNFEBNDCAJJGKGKEDGBODBPBHFKHIILOBGNBKKCOPFNLAD";
			popupWindow_bit.dismiss();
			break;
		case R.id.button_start_9:
			SystemValue.SystemSerVer = "EFGBFFBJKFJJGFJKEOGNFPEAHCMBHPNCGIFOBBCFBMJELLLACBALCDOCHJLFJOKIAAMGLACJOFMCABCNJLNDIDAJ";
			popupWindow_bit.dismiss();
			break;
		default:
			break;
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					NativeCaller.PPPPInitial(SystemValue.SystemSerVer);
					Log.d("test-server", "SystemValue.SystemSerVer");
					long lStartTime = new Date().getTime();
					//int nRes = NativeCaller.PPPPNetworkDetect();
					long lEndTime = new Date().getTime();
					if (lEndTime - lStartTime <= 1000) {
						Thread.sleep(1000);
					}
					Message msg = new Message();
					mHandler.sendMessage(msg);
				} catch (Exception e) {

				}
			}
		}).start();
	}

}