package com.livixa.client;

import object.p2pipcam.bean.AlermBean;
import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.nativecaller.NativeCaller;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.client.BridgeService.AlarmInterface;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * ��������
 * */
public class SettingAlarmActivity extends BaseActivity implements
		OnClickListener, OnCheckedChangeListener, OnGestureListener,
		OnTouchListener, AlarmInterface {
	private String TAG = "SettingAlermActivity";
	private String strDID = null;
	private String cameraName = null;
	private boolean successFlag = false;// ��ȡ�����õĽ��
	private final int TIMEOUT = 3000;
	private final int ALERMPARAMS = 3;
	private final int UPLOADTIMETOOLONG = 4;
	// private int motionAlerm=0;//�ƶ����� 0���أ�1����
	// private int ioAlerm=0;//�������벼�� 0���أ�1����
	private GestureDetector gt = new GestureDetector(this);
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:// ����ʧ��
				showToast(R.string.alerm_set_failed);
				break;
			case 1:// ���óɹ�
				showToast(R.string.setting_aler_sucess);
				finish();
				break;
			case ALERMPARAMS:// �ص��ɹ�
				successFlag = true;
				WaitingStaticProgress.hideProgressDialog();
				if (1 == alermBean.getRecord()) {
					cbxRecord.setChecked(true);
				} else {
					cbxRecord.setChecked(false);
				}
				if (1 == alermBean.getEnable_alarm_audio()) {
					cbxVoid.setChecked(true);
				} else {
					cbxVoid.setChecked(false);
				}
				if (0 == alermBean.getMotion_armed()) {// �ƶ���Ⲽ��0-��������1-����
					motionAlermView.setVisibility(View.GONE);
				} else {
					cbxMotionAlerm.setChecked(true);
					motionAlermView.setVisibility(View.VISIBLE);
					// ������
					if(alermBean
							.getMotion_sensitivity()==8){
					tvSensitivity.setText(getString(R.string.alarm_high));
					}else if(alermBean
							.getMotion_sensitivity()==5){
						tvSensitivity.setText(getString(R.string.alarm_middle));
					}else if(alermBean
							.getMotion_sensitivity()==2){
						tvSensitivity.setText(getString(R.string.alarm_low));
					}
				}

				if (0 == alermBean.getInput_armed()) {// �������벼�� 0-�������� 1-����
					ioAlermView.setVisibility(View.GONE);
				} else {
					cbxIOAlerm.setChecked(true);
//					ioAlermView.setVisibility(View.VISIBLE);
//					if (0 == alermBean.getIoin_level()) {// 0 ������ƽ �͵�ƽ
//						tvTriggerLevel.setText(getResources().getString(
//								R.string.alerm_ioin_levellow));
//					} else {// 1 ������ƽ �ߵ�ƽ
//						tvTriggerLevel.setText(getResources().getString(
//								R.string.alerm_ioin_levelhight));
//					}
				}

				if (0 == alermBean.getIolinkage()) {// ������IO���� 0-������ 1-����
					ioMotionView.setVisibility(View.GONE);
				} else {
					cbxIOMotion.setChecked(true);
					//ioMotionView.setVisibility(View.VISIBLE);
					if (0 == alermBean.getIoout_level()) {// 0 �����ƽ �͵�ƽ
						tvIoOutLevel.setText(getResources().getString(
								R.string.alerm_ioin_levellow));
					} else {// 1 �����ƽ �ߵ�ƽ
						tvIoOutLevel.setText(getResources().getString(
								R.string.alerm_ioin_levelhight));
					}
				}

				if (0 == alermBean.getMail()) {// �������Ƿ��ʼ�֪ͨ 0-��֪ͨ 1-֪ͨ
					cbxMail.setChecked(false);
				} else {
					cbxMail.setChecked(true);
				}

				if (0 == alermBean.getUpload_interval()) {// �������Ƿ��ϴ�ͼƬ 0--���ϴ�
															// 1--�ϴ�
					cbxUploadPicture.setChecked(false);
					uploadPictureView.setVisibility(View.GONE);
				} else {
					uploadPictureView.setVisibility(View.VISIBLE);
					cbxUploadPicture.setChecked(true);
					editUploadPicInterval.setText(String.valueOf(alermBean
							.getUpload_interval()));
				}
				if (alermBean.getAlermpresetsit() == 0) {// ������Ԥ��λ���� 0--������
															// 1--����
					tvPreset.setText(getResources().getString(
							R.string.alerm_preset_no));
				} else {
					tvPreset.setText(String.valueOf(alermBean
							.getAlermpresetsit()));
				}

				if (1 == alermBean.getMotion_armed()
						|| 1 == alermBean.getInput_armed()) {
					eventView.setVisibility(View.VISIBLE);
				} else {
					eventView.setVisibility(View.GONE);
				}

				break;
			case UPLOADTIMETOOLONG:
				editUploadPicInterval.setText("");
				break;

			default:
				break;
			}
		}
	};

	private Button btnOk = null;
	private Button btnCancel = null;
	private View motionAlermView = null;
	private View ioAlermView = null;
	private View ioMotionView = null;
	private View uploadPictureView = null;
	private View eventView = null;
	private EditText editUploadPicInterval = null;
	private ImageView imgTriggerLevelDrop = null;
	private ImageView imgSensitiveDrop = null;
	private ImageView imgPresetDrop = null;
	private ImageView imgIoOutLevelDrop = null;
	private TextView tvIoOutLevel = null;
	private TextView tvPreset = null;
	private TextView tvTriggerLevel = null;
	private TextView tvSensitivity = null;
	private CheckBox cbxUploadPicture = null;
	private CheckBox cbxMail = null;
	private CheckBox cbxIOMotion = null;
	private CheckBox cbxIOAlerm = null;
	private CheckBox cbxMotionAlerm = null;
	private AlermBean alermBean = null;
	private PopupWindow sensitivePopWindow = null;
	private PopupWindow triggerLevelPopWindow = null;
	private PopupWindow ioOutLevelPopWindow = null;
	private PopupWindow presteMovePopWindow = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDataFromOther();
		setContentView(R.layout.settingalarm);
		/*progressDialog = new ProgressDialog(this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage(getString(R.string.alerm_getparams));
		progressDialog.show();*/
		
		WaitingStaticProgress.showProgressDialog(getString(R.string.alerm_getparams), this);
		mHandler.postDelayed(runnable, TIMEOUT);
		alermBean = new AlermBean();
		findView();

		setListener();
		BridgeService.setAlarmInterface(this);
		NativeCaller.PPPPGetSystemParams(strDID,
				ContentCommon.MSG_TYPE_GET_PARAMS);
	}

	@Override
	protected void onPause() {
		KisafaApplication.perFormActivityBackTransition(this);// �˳�����
		super.onPause();
	}

	private Runnable runnable = new Runnable() {

		@Override
		public void run() {
			if (!successFlag) {
				// showToast(R.string.alerm_getparams_failed);
				WaitingStaticProgress.hideProgressDialog();
			}
		}
	};

	private void setListener() {
		btnOk.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		imgIoOutLevelDrop.setOnClickListener(this);
		imgPresetDrop.setOnClickListener(this);
		imgSensitiveDrop.setOnClickListener(this);
		imgTriggerLevelDrop.setOnClickListener(this);
		cbxMotionAlerm.setOnCheckedChangeListener(this);
		cbxIOAlerm.setOnCheckedChangeListener(this);
		cbxIOMotion.setOnCheckedChangeListener(this);
		cbxMail.setOnCheckedChangeListener(this);
		cbxUploadPicture.setOnCheckedChangeListener(this);
		cbxRecord.setOnCheckedChangeListener(this);
		cbxVoid.setOnCheckedChangeListener(this);
		eventView.setOnTouchListener(this);
		ioMotionView.setOnTouchListener(this);
		scrollView.setOnTouchListener(this);
		/*progressDialog.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {

				if (keyCode == KeyEvent.KEYCODE_BACK) {
					return true;
				}
				return false;
			}

		});*/
		editUploadPicInterval.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				String uploadInterval = s.toString();
				if (!TextUtils.isEmpty(uploadInterval)) {
					int result = Integer.parseInt(uploadInterval);
					if (result <= 30 && result > 0) {
						alermBean.setUpload_interval(result);
					} else {
						mHandler.sendEmptyMessage(UPLOADTIMETOOLONG);
						showToast(R.string.alerm_uploadinterval_toolong);
					}
				} else {
					alermBean.setUpload_interval(0);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.alerm_ok:// ȷ������
			setAlerm();
			break;
		case R.id.alerm_cancel:// ȡ������
			finish();
			overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);// �˳�����
			break;
		case R.id.alerm_img_ioout_level_drop:// �����ƽ
			if (ioOutLevelPopWindow != null && ioOutLevelPopWindow.isShowing()) {
				return;
			}
			if (sensitivePopWindow != null && sensitivePopWindow.isShowing()) {
				sensitivePopWindow.dismiss();
				Log.d(TAG, "onTouchEvent 2");
			}
			if (presteMovePopWindow != null && presteMovePopWindow.isShowing()) {
				presteMovePopWindow.dismiss();
			}
			if (triggerLevelPopWindow != null
					&& triggerLevelPopWindow.isShowing()) {
				triggerLevelPopWindow.dismiss();
			}
			LinearLayout outLayout = (LinearLayout) LayoutInflater.from(this)
					.inflate(R.layout.alermiooutpopwindow, null);
			TextView outHight = (TextView) outLayout
					.findViewById(R.id.ioout_hight);
			TextView outLow = (TextView) outLayout.findViewById(R.id.ioout_low);
			outHight.setOnClickListener(this);
			outLow.setOnClickListener(this);
			ioOutLevelPopWindow = new PopupWindow(outLayout, 160,
					WindowManager.LayoutParams.WRAP_CONTENT);
			ioOutLevelPopWindow.showAsDropDown(imgIoOutLevelDrop, -140, 0);
			break;
		case R.id.ioout_hight:// �����ƽ ����
			tvIoOutLevel.setText(getResources().getString(
					R.string.alerm_ioin_levelhight));
			ioOutLevelPopWindow.dismiss();
			alermBean.setIoout_level(1);
			break;
		case R.id.ioout_low:// �����ƽ����
			tvIoOutLevel.setText(getResources().getString(
					R.string.alerm_ioin_levellow));
			ioOutLevelPopWindow.dismiss();
			alermBean.setIoout_level(0);
			break;
		case R.id.alerm_img_preset_drop:// Ԥ��λdialog
			if (presteMovePopWindow != null && presteMovePopWindow.isShowing()) {
				return;
			}
			if (sensitivePopWindow != null && sensitivePopWindow.isShowing()) {
				sensitivePopWindow.dismiss();
			}
			if (triggerLevelPopWindow != null
					&& triggerLevelPopWindow.isShowing()) {
				triggerLevelPopWindow.dismiss();
			}
			if (ioOutLevelPopWindow != null && ioOutLevelPopWindow.isShowing()) {
				ioOutLevelPopWindow.dismiss();
			}

			LinearLayout preLayout = (LinearLayout) LayoutInflater.from(this)
					.inflate(R.layout.alermpresetmovepopwindow, null);
			TextView presetNo = (TextView) preLayout
					.findViewById(R.id.preset_no);
			TextView preset1 = (TextView) preLayout.findViewById(R.id.preset_1);
			TextView preset2 = (TextView) preLayout.findViewById(R.id.preset_2);
			TextView preset3 = (TextView) preLayout.findViewById(R.id.preset_3);
			TextView preset4 = (TextView) preLayout.findViewById(R.id.preset_4);
			TextView preset5 = (TextView) preLayout.findViewById(R.id.preset_5);
			TextView preset6 = (TextView) preLayout.findViewById(R.id.preset_6);
			TextView preset7 = (TextView) preLayout.findViewById(R.id.preset_7);
			TextView preset8 = (TextView) preLayout.findViewById(R.id.preset_8);
			TextView preset9 = (TextView) preLayout.findViewById(R.id.preset_9);
			TextView preset10 = (TextView) preLayout
					.findViewById(R.id.preset_10);
			TextView preset11 = (TextView) preLayout
					.findViewById(R.id.preset_11);
			TextView preset12 = (TextView) preLayout
					.findViewById(R.id.preset_12);
			TextView preset13 = (TextView) preLayout
					.findViewById(R.id.preset_13);
			TextView preset14 = (TextView) preLayout
					.findViewById(R.id.preset_14);
			TextView preset15 = (TextView) preLayout
					.findViewById(R.id.preset_15);
			TextView preset16 = (TextView) preLayout
					.findViewById(R.id.preset_16);
			presetNo.setOnClickListener(this);
			preset1.setOnClickListener(this);
			preset2.setOnClickListener(this);
			preset3.setOnClickListener(this);
			preset4.setOnClickListener(this);
			preset5.setOnClickListener(this);
			preset6.setOnClickListener(this);
			preset7.setOnClickListener(this);
			preset8.setOnClickListener(this);
			preset9.setOnClickListener(this);
			preset10.setOnClickListener(this);
			preset11.setOnClickListener(this);
			preset12.setOnClickListener(this);
			preset13.setOnClickListener(this);
			preset14.setOnClickListener(this);
			preset15.setOnClickListener(this);
			preset16.setOnClickListener(this);
			presteMovePopWindow = new PopupWindow(preLayout, 160,
					WindowManager.LayoutParams.WRAP_CONTENT);
			presteMovePopWindow.showAsDropDown(imgPresetDrop, -160, 0);

			break;
		case R.id.alerm_img_sensitive_drop:// ������dialog
			if (sensitivePopWindow != null && sensitivePopWindow.isShowing()) {
				return;
			}
			if (presteMovePopWindow != null && presteMovePopWindow.isShowing()) {
				presteMovePopWindow.dismiss();
			}
			if (triggerLevelPopWindow != null
					&& triggerLevelPopWindow.isShowing()) {
				triggerLevelPopWindow.dismiss();
			}
			if (ioOutLevelPopWindow != null && ioOutLevelPopWindow.isShowing()) {
				ioOutLevelPopWindow.dismiss();
			}
			LinearLayout layout1 = (LinearLayout) LayoutInflater.from(this)
					.inflate(R.layout.alermsensitivepopwindow, null);
			TextView sensitive10 = (TextView) layout1
					.findViewById(R.id.sensitive_10);
			TextView sensitive9 = (TextView) layout1
					.findViewById(R.id.sensitive_9);
			TextView sensitive8 = (TextView) layout1
					.findViewById(R.id.sensitive_8);
			TextView sensitive7 = (TextView) layout1
					.findViewById(R.id.sensitive_7);
			TextView sensitive6 = (TextView) layout1
					.findViewById(R.id.sensitive_6);
			TextView sensitive5 = (TextView) layout1
					.findViewById(R.id.sensitive_5);
			TextView sensitive4 = (TextView) layout1
					.findViewById(R.id.sensitive_4);
			TextView sensitive3 = (TextView) layout1
					.findViewById(R.id.sensitive_3);
			TextView sensitive2 = (TextView) layout1
					.findViewById(R.id.sensitive_2);
			TextView sensitive1 = (TextView) layout1
					.findViewById(R.id.sensitive_1);
			sensitive10.setOnClickListener(this);
			sensitive9.setOnClickListener(this);
			sensitive8.setOnClickListener(this);
			sensitive7.setOnClickListener(this);
			sensitive6.setOnClickListener(this);
			sensitive5.setOnClickListener(this);
			sensitive4.setOnClickListener(this);
			sensitive3.setOnClickListener(this);
			sensitive2.setOnClickListener(this);
			sensitive1.setOnClickListener(this);
			sensitivePopWindow = new PopupWindow(layout1, 160,
					WindowManager.LayoutParams.WRAP_CONTENT);
			sensitivePopWindow.showAsDropDown(tvSensitivity, -120, 10);
			break;
		case R.id.alerm_img_leveldrop:// ������ƽdialog
			if (triggerLevelPopWindow != null
					&& triggerLevelPopWindow.isShowing()) {
				return;
			}
			if (sensitivePopWindow != null && sensitivePopWindow.isShowing()) {
				sensitivePopWindow.dismiss();
			}
			if (presteMovePopWindow != null && presteMovePopWindow.isShowing()) {
				presteMovePopWindow.dismiss();
			}
			if (ioOutLevelPopWindow != null && ioOutLevelPopWindow.isShowing()) {
				ioOutLevelPopWindow.dismiss();
			}
			LinearLayout triggerLayout = (LinearLayout) LayoutInflater.from(
					this).inflate(R.layout.alermtriggerpopwindow, null);
			TextView tvHight = (TextView) triggerLayout
					.findViewById(R.id.trigger_hight);
			TextView tvLow = (TextView) triggerLayout
					.findViewById(R.id.trigger_low);
			tvLow.setOnClickListener(this);
			tvHight.setOnClickListener(this);
			triggerLevelPopWindow = new PopupWindow(triggerLayout, 160,
					WindowManager.LayoutParams.WRAP_CONTENT);
			triggerLevelPopWindow.showAsDropDown(imgTriggerLevelDrop, -140, 0);
			break;
		case R.id.trigger_hight:// ������ƽ ��
			tvTriggerLevel.setText(getResources().getString(
					R.string.alerm_ioin_levelhight));
			triggerLevelPopWindow.dismiss();
			alermBean.setIoin_level(1);
			break;
		case R.id.trigger_low:// ������ƽ ��
			tvTriggerLevel.setText(getResources().getString(
					R.string.alerm_ioin_levellow));
			triggerLevelPopWindow.dismiss();
			alermBean.setIoin_level(0);
			break;
		case R.id.sensitive_10:
			Log.d(TAG, "10");
			sensitivePopWindow.dismiss();
			alermBean.setMotion_sensitivity(10);
			//tvSensitivity.setText(String.valueOf(10));
			tvSensitivity.setText(getString(R.string.alarm_high));
			break;
		case R.id.sensitive_9:
			sensitivePopWindow.dismiss();
			alermBean.setMotion_sensitivity(9);
			tvSensitivity.setText(String.valueOf(9));
			break;
		case R.id.sensitive_8:
			sensitivePopWindow.dismiss();
			alermBean.setMotion_sensitivity(8);
			//tvSensitivity.setText(String.valueOf(8));
			tvSensitivity.setText(getString(R.string.alarm_high));
			break;
		case R.id.sensitive_7:
			sensitivePopWindow.dismiss();
			alermBean.setMotion_sensitivity(7);
			tvSensitivity.setText(String.valueOf(7));
			break;
		case R.id.sensitive_6:
			sensitivePopWindow.dismiss();
			alermBean.setMotion_sensitivity(6);
			tvSensitivity.setText(String.valueOf(6));
			break;
		case R.id.sensitive_5:
			sensitivePopWindow.dismiss();
			alermBean.setMotion_sensitivity(5);
			//tvSensitivity.setText(String.valueOf(5));
			tvSensitivity.setText(getString(R.string.alarm_middle));
			break;
		case R.id.sensitive_4:
			sensitivePopWindow.dismiss();
			alermBean.setMotion_sensitivity(4);
			tvSensitivity.setText(String.valueOf(4));
			break;
		case R.id.sensitive_3:
			sensitivePopWindow.dismiss();
			alermBean.setMotion_sensitivity(3);
			tvSensitivity.setText(String.valueOf(3));
			break;
		case R.id.sensitive_2:
			sensitivePopWindow.dismiss();
			alermBean.setMotion_sensitivity(2);
			//tvSensitivity.setText(String.valueOf(2));
			tvSensitivity.setText(getString(R.string.alarm_low));
			break;
		case R.id.sensitive_1:
			sensitivePopWindow.dismiss();
			alermBean.setMotion_sensitivity(1);
			//tvSensitivity.setText(String.valueOf(1));
			break;
		case R.id.preset_no:
			alermBean.setAlermpresetsit(0);
			tvPreset.setText(getResources().getString(R.string.alerm_preset_no));
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_1:
			alermBean.setAlermpresetsit(1);
			tvPreset.setText("1");
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_2:
			alermBean.setAlermpresetsit(2);
			tvPreset.setText("2");
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_3:
			alermBean.setAlermpresetsit(3);
			tvPreset.setText("3");
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_4:
			alermBean.setAlermpresetsit(4);
			tvPreset.setText("4");
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_5:
			alermBean.setAlermpresetsit(5);
			tvPreset.setText("5");
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_6:
			alermBean.setAlermpresetsit(6);
			tvPreset.setText("6");
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_7:
			alermBean.setAlermpresetsit(7);
			tvPreset.setText("7");
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_8:
			alermBean.setAlermpresetsit(8);
			tvPreset.setText("8");
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_9:
			alermBean.setAlermpresetsit(9);
			tvPreset.setText("9");
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_10:
			alermBean.setAlermpresetsit(10);
			tvPreset.setText("10");
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_11:
			alermBean.setAlermpresetsit(11);
			tvPreset.setText("11");
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_12:
			alermBean.setAlermpresetsit(12);
			tvPreset.setText("12");
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_13:
			alermBean.setAlermpresetsit(13);
			tvPreset.setText("13");
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_14:
			alermBean.setAlermpresetsit(14);
			tvPreset.setText("14");
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_15:
			alermBean.setAlermpresetsit(15);
			tvPreset.setText("15");
			presteMovePopWindow.dismiss();
			break;
		case R.id.preset_16:
			alermBean.setAlermpresetsit(16);
			tvPreset.setText("16");
			presteMovePopWindow.dismiss();
			break;
		default:
			break;
		}
	}

	private void setAlerm() {
		if (successFlag) {
			if (!cbxUploadPicture.isChecked()) {
				alermBean.setUpload_interval(0);
			}
			if(tvSensitivity
					.getText().toString().equals(getString(R.string.alarm_high))){
				alermBean.setMotion_sensitivity(8);
			}else if(tvSensitivity
					.getText().toString().equals(getString(R.string.alarm_middle))){
				alermBean.setMotion_sensitivity(5);
			}else if(tvSensitivity
					.getText().toString().equals(getString(R.string.alarm_low))){
				alermBean.setMotion_sensitivity(2);
			}
			//alermBean.setMotion_sensitivity(8);
			alermBean.setIoin_level(1);
			alermBean.setIoout_level(1);
//			alermBean.setMotion_sensitivity(Integer.parseInt(tvSensitivity
//					.getText().toString().trim()));
			Log.e(TAG, "alerm: " + alermBean.getMotion_sensitivity());
			NativeCaller.PPPPAlarmSetting(strDID, alermBean.getMotion_armed(),
					alermBean.getMotion_sensitivity(),
					alermBean.getInput_armed(), alermBean.getIoin_level(),
					alermBean.getIolinkage(), alermBean.getIoout_level(),
					alermBean.getAlermpresetsit(), alermBean.getMail(),
					alermBean.getSnapshot(), alermBean.getRecord(),
					alermBean.getUpload_interval(),
					alermBean.getSchedule_enable(),
					alermBean.getEnable_alarm_audio(), 0xFFFFFFFF, 0xFFFFFFFF,
					0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
					0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
					0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
					0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF);
			// oxFFFFFFFF �ճ�ȫѡ ox0 :��ѡ
			// setTimeOut();
		} else {
			showToast(R.string.alerm_set_failed);
		}

	}

	private void setTimeOut() {
		successFlag = false;
		mHandler.postAtTime(settingRunnable, TIMEOUT);
	}

	private Runnable settingRunnable = new Runnable() {

		@Override
		public void run() {
			if (!successFlag) {
				successFlag = false;
				showToast(R.string.alerm_set_failed);
			}
		}
	};
	private ScrollView scrollView;
	private CheckBox cbxRecord;
	private CheckBox cbxVoid;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gt.onTouchEvent(event);
	}

	private void findView() {
		cbxMotionAlerm = (CheckBox) findViewById(R.id.alerm_cbx_move_layout);
		cbxIOAlerm = (CheckBox) findViewById(R.id.alerm_cbx_i0_layout);
		cbxIOMotion = (CheckBox) findViewById(R.id.alerm_cbx_io_move);
		cbxMail = (CheckBox) findViewById(R.id.alerm_cbx_mail);
		cbxUploadPicture = (CheckBox) findViewById(R.id.alerm_cbx_upload_picture);
		cbxRecord = (CheckBox) findViewById(R.id.alerm_cbx_isrecord);
		cbxVoid = (CheckBox) findViewById(R.id.alerm_cbx_void);
		tvSensitivity = (TextView) findViewById(R.id.alerm_tv_sensitivity);
		tvTriggerLevel = (TextView) findViewById(R.id.alerm_tv_triggerlevel);
		tvPreset = (TextView) findViewById(R.id.alerm_tv_preset);
		tvIoOutLevel = (TextView) findViewById(R.id.alerm_tv_ioout_level_value);

		imgIoOutLevelDrop = (ImageView) findViewById(R.id.alerm_img_ioout_level_drop);
		imgPresetDrop = (ImageView) findViewById(R.id.alerm_img_preset_drop);
		imgSensitiveDrop = (ImageView) findViewById(R.id.alerm_img_sensitive_drop);
		imgTriggerLevelDrop = (ImageView) findViewById(R.id.alerm_img_leveldrop);

		editUploadPicInterval = (EditText) findViewById(R.id.alerm_edit_picture_interval);

		uploadPictureView = findViewById(R.id.alerm_uploadpicture_view);
		ioMotionView = findViewById(R.id.alerm_io_move_view);
		ioAlermView = findViewById(R.id.alerm_ioview);
		motionAlermView = findViewById(R.id.alerm_moveview);
		motionAlermView.setVisibility(View.GONE);
		eventView = findViewById(R.id.alerm_eventview);
		eventView.setVisibility(View.GONE);

		btnOk = (Button) findViewById(R.id.alerm_ok);
		btnCancel = (Button) findViewById(R.id.alerm_cancel);

		scrollView = (ScrollView) findViewById(R.id.scrollView1);

		RelativeLayout layout = (RelativeLayout) findViewById(R.id.top);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.top_bg);
		BitmapDrawable drawable = new BitmapDrawable(bitmap);
		drawable.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
		drawable.setDither(true);
		// layout.setBackgroundDrawable(drawable);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.alerm_cbx_move_layout:
			if (isChecked) {
				alermBean.setMotion_armed(1);
				motionAlermView.setVisibility(View.VISIBLE);
			} else {
				alermBean.setMotion_armed(0);
				motionAlermView.setVisibility(View.GONE);
			}

			break;
		case R.id.alerm_cbx_i0_layout:
			if (isChecked) {
				alermBean.setInput_armed(1);
				//ioAlermView.setVisibility(View.VISIBLE);
			} else {
				alermBean.setInput_armed(0);
				//ioAlermView.setVisibility(View.GONE);
			}
			break;
		case R.id.alerm_cbx_io_move:
			if (isChecked) {
				alermBean.setIolinkage(1);
				//ioMotionView.setVisibility(View.VISIBLE);
			} else {
				alermBean.setIolinkage(0);
				ioMotionView.setVisibility(View.GONE);
			}
			break;
		case R.id.alerm_cbx_mail:
			if (isChecked) {
				alermBean.setMail(1);
			} else {
				alermBean.setMail(0);
			}
			break;
		case R.id.alerm_cbx_isrecord:
			if (isChecked) {
				alermBean.setRecord(1);
			} else {
				alermBean.setRecord(0);
			}
			break;
		case R.id.alerm_cbx_void:
			if (isChecked) {
				alermBean.setEnable_alarm_audio(1);
			} else {
				alermBean.setEnable_alarm_audio(0);
			}
			break;
		case R.id.alerm_cbx_upload_picture:
			if (isChecked) {
				alermBean.setSnapshot(1);
				uploadPictureView.setVisibility(View.VISIBLE);
			} else {
				alermBean.setSnapshot(0);
				uploadPictureView.setVisibility(View.GONE);
			}
			break;
		}
		if (1 == alermBean.getMotion_armed() || 1 == alermBean.getInput_armed()) {
			eventView.setVisibility(View.VISIBLE);
		} else {
			eventView.setVisibility(View.GONE);
		}
	}

	private void getDataFromOther() {
		Intent intent = getIntent();
		strDID = intent.getStringExtra(ContentCommon.STR_CAMERA_ID);
		cameraName = intent.getStringExtra(ContentCommon.STR_CAMERA_NAME);
	}

	/**
	 * BridgeService Feedback execute
	 * **/
	public void CallBack_AlarmParams(String did, int motion_armed,
			int motion_sensitivity, int input_armed, int ioin_level,
			int iolinkage, int ioout_level, int alermpresetsit, int mail,
			int snapshot, int record, int upload_interval, int schedule_enable,
			int schedule_sun_0, int schedule_sun_1, int schedule_sun_2,
			int schedule_mon_0, int schedule_mon_1, int schedule_mon_2,
			int schedule_tue_0, int schedule_tue_1, int schedule_tue_2,
			int schedule_wed_0, int schedule_wed_1, int schedule_wed_2,
			int schedule_thu_0, int schedule_thu_1, int schedule_thu_2,
			int schedule_fri_0, int schedule_fri_1, int schedule_fri_2,
			int schedule_sat_0, int schedule_sat_1, int schedule_sat_2) {
		Log.d(TAG, "CallBack_AlarmParams  motion_armed="+ motion_armed+"   input_armed="+input_armed);

		alermBean.setDid(did);
		alermBean.setMotion_armed(motion_armed);
		alermBean.setMotion_sensitivity(motion_sensitivity);
		alermBean.setInput_armed(input_armed);
		alermBean.setIoin_level(ioin_level);
		alermBean.setIolinkage(iolinkage);
		alermBean.setIoout_level(ioout_level);
		alermBean.setAlermpresetsit(alermpresetsit);
		alermBean.setMail(mail);
		alermBean.setSnapshot(snapshot);
		alermBean.setRecord(record);
		alermBean.setUpload_interval(upload_interval);
		alermBean.setSchedule_enable(1);

		alermBean.setSchedule_sun_0(0xFFFFFFFF);
		alermBean.setSchedule_sun_1(0xFFFFFFFF);
		alermBean.setSchedule_sun_2(0xFFFFFFFF);
		alermBean.setSchedule_mon_0(0xFFFFFFFF);
		alermBean.setSchedule_mon_1(0xFFFFFFFF);
		alermBean.setSchedule_mon_2(0xFFFFFFFF);
		alermBean.setSchedule_thu_0(0xFFFFFFFF);
		alermBean.setSchedule_thu_1(0xFFFFFFFF);
		alermBean.setSchedule_thu_2(0xFFFFFFFF);
		alermBean.setSchedule_wed_0(0xFFFFFFFF);
		alermBean.setSchedule_wed_1(0xFFFFFFFF);
		alermBean.setSchedule_wed_2(0xFFFFFFFF);
		alermBean.setSchedule_tue_0(0xFFFFFFFF);
		alermBean.setSchedule_tue_1(0xFFFFFFFF);
		alermBean.setSchedule_tue_2(0xFFFFFFFF);
		alermBean.setSchedule_fri_0(0xFFFFFFFF);
		alermBean.setSchedule_fri_1(0xFFFFFFFF);
		alermBean.setSchedule_fri_2(0xFFFFFFFF);
		alermBean.setSchedule_sat_0(0xFFFFFFFF);
		alermBean.setSchedule_sat_1(0xFFFFFFFF);
		alermBean.setSchedule_sat_2(0xFFFFFFFF);
		mHandler.sendEmptyMessage(ALERMPARAMS);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (sensitivePopWindow != null && sensitivePopWindow.isShowing()) {
			sensitivePopWindow.dismiss();
		}
		if (presteMovePopWindow != null && presteMovePopWindow.isShowing()) {
			presteMovePopWindow.dismiss();
		}
		if (ioOutLevelPopWindow != null && ioOutLevelPopWindow.isShowing()) {
			ioOutLevelPopWindow.dismiss();
		}
		if (triggerLevelPopWindow != null && triggerLevelPopWindow.isShowing()) {
			triggerLevelPopWindow.dismiss();
		}
	}

	@Override
	public boolean onDown(MotionEvent e) {
		if (sensitivePopWindow != null && sensitivePopWindow.isShowing()) {
			sensitivePopWindow.dismiss();
		}
		if (presteMovePopWindow != null && presteMovePopWindow.isShowing()) {
			presteMovePopWindow.dismiss();
		}
		if (triggerLevelPopWindow != null && triggerLevelPopWindow.isShowing()) {
			triggerLevelPopWindow.dismiss();
		}
		if (ioOutLevelPopWindow != null && ioOutLevelPopWindow.isShowing()) {
			ioOutLevelPopWindow.dismiss();
		}
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (sensitivePopWindow != null && sensitivePopWindow.isShowing()) {
			sensitivePopWindow.dismiss();
			Log.d(TAG, "onTouchEvent 2");
		}
		if (presteMovePopWindow != null && presteMovePopWindow.isShowing()) {
			presteMovePopWindow.dismiss();
		}
		if (triggerLevelPopWindow != null && triggerLevelPopWindow.isShowing()) {
			triggerLevelPopWindow.dismiss();
		}
		if (ioOutLevelPopWindow != null && ioOutLevelPopWindow.isShowing()) {
			ioOutLevelPopWindow.dismiss();
		}
		return false;
	}

	@Override
	public void callBackAlarmParams(String did, int motion_armed,
			int motion_sensitivity, int input_armed, int ioin_level,
			int iolinkage, int ioout_level, int alermpresetsit, int mail,
			int snapshot, int record, int upload_interval, int schedule_enable,
			int enable_alarm_audio, int schedule_sun_0, int schedule_sun_1,
			int schedule_sun_2, int schedule_mon_0, int schedule_mon_1,
			int schedule_mon_2, int schedule_tue_0, int schedule_tue_1,
			int schedule_tue_2, int schedule_wed_0, int schedule_wed_1,
			int schedule_wed_2, int schedule_thu_0, int schedule_thu_1,
			int schedule_thu_2, int schedule_fri_0, int schedule_fri_1,
			int schedule_fri_2, int schedule_sat_0, int schedule_sat_1,
			int schedule_sat_2) {
		Log.d(TAG, "CallBack_AlarmParams enable_alarm_audio:"
				+ enable_alarm_audio);
		alermBean.setDid(did);
		alermBean.setMotion_armed(motion_armed);
		alermBean.setMotion_sensitivity(motion_sensitivity);
		alermBean.setInput_armed(input_armed);
		alermBean.setIoin_level(ioin_level);
		alermBean.setIolinkage(iolinkage);
		alermBean.setIoout_level(ioout_level);
		alermBean.setAlermpresetsit(alermpresetsit);
		alermBean.setMail(mail);
		alermBean.setSnapshot(snapshot);
		alermBean.setRecord(record);
		alermBean.setUpload_interval(upload_interval);
		alermBean.setSchedule_enable(1);
		alermBean.setEnable_alarm_audio(enable_alarm_audio);

		alermBean.setSchedule_sun_0(0xFFFFFFFF);
		alermBean.setSchedule_sun_1(0xFFFFFFFF);
		alermBean.setSchedule_sun_2(0xFFFFFFFF);
		alermBean.setSchedule_mon_0(0xFFFFFFFF);
		alermBean.setSchedule_mon_1(0xFFFFFFFF);
		alermBean.setSchedule_mon_2(0xFFFFFFFF);
		alermBean.setSchedule_thu_0(0xFFFFFFFF);
		alermBean.setSchedule_thu_1(0xFFFFFFFF);
		alermBean.setSchedule_thu_2(0xFFFFFFFF);
		alermBean.setSchedule_wed_0(0xFFFFFFFF);
		alermBean.setSchedule_wed_1(0xFFFFFFFF);
		alermBean.setSchedule_wed_2(0xFFFFFFFF);
		alermBean.setSchedule_tue_0(0xFFFFFFFF);
		alermBean.setSchedule_tue_1(0xFFFFFFFF);
		alermBean.setSchedule_tue_2(0xFFFFFFFF);
		alermBean.setSchedule_fri_0(0xFFFFFFFF);
		alermBean.setSchedule_fri_1(0xFFFFFFFF);
		alermBean.setSchedule_fri_2(0xFFFFFFFF);
		alermBean.setSchedule_sat_0(0xFFFFFFFF);
		alermBean.setSchedule_sat_1(0xFFFFFFFF);
		alermBean.setSchedule_sat_2(0xFFFFFFFF);
		mHandler.sendEmptyMessage(ALERMPARAMS);
	}

	@Override
	public void callBackSetSystemParamsResult(String did, int paramType,
			int result) {
		Log.d(TAG, "alerm set result:" + result);
		mHandler.sendEmptyMessage(result);

	}

}
