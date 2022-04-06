package com.livixa.client;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import object.p2pipcam.bean.UserTimeBean;
import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.nativecaller.NativeCaller;
import android.R.style;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AnalogClock;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import com.livixa.client.BridgeService.UserTimeInterface;

public class SettingUserTimeActivity extends BaseActivity implements
		UserTimeInterface, OnClickListener {

	String strDID = null;
	private RadioGroup group1;
	private RadioButton rb1, rb2;

	private RadioGroup group1_select;
	private RadioButton rb1_select, rb2_select;

	private LinearLayout layout_one, layout_two;
	private List<String> list = null;
	private Spinner spinner_start, spinner_end, spinner_start_2, spinner_end_2;
	private RelativeLayout relative_one, relative_two;

	private EditText editText_hour_1, editText_hour_2, editText_minu_1,
			editText_minu_2, editText_to_hour_1, editText_to_hour_2,
			editText_to_minu_1, editText_to_minu_2;

	private UserTimeBean userTimeBean;
	private Button userOk;
	private Button userCancel;
	private final int FAIL = 0;
	private final int SUCCESS = 1;
	private final int PARAMS = 3;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case PARAMS:
				if (userTimeBean != null) {

					if (userTimeBean.getUser_schedule_mode() == 1) {

						relative_one.setVisibility(View.GONE);
						relative_two.setVisibility(View.VISIBLE);
						rb2.setChecked(true);
					} else {
						relative_one.setVisibility(View.VISIBLE);
						relative_two.setVisibility(View.GONE);
						rb1.setChecked(true);
					}
					if (userTimeBean.getUser_schedule_enable() == 0) {
						linearsetting.setVisibility(View.GONE);
						rb1_select.setChecked(true);
					} else {
						linearsetting.setVisibility(View.VISIBLE);
						rb2_select.setChecked(true);
					}
					if (userTimeBean.getUser_schedule_startday() < 7) {
						spinner_start.setSelection(userTimeBean
								.getUser_schedule_startday());
						spinner_start_2.setSelection(userTimeBean
								.getUser_schedule_startday());
					}
					if (userTimeBean.getUser_schedule_endday() < 7) {
						spinner_end.setSelection(userTimeBean
								.getUser_schedule_endday());
						spinner_end_2.setSelection(userTimeBean
								.getUser_schedule_endday());
					}
					editText_hour_1.setText(returnIntToString(userTimeBean
							.getUser_schedule_starttime_min() / 60));
					editText_hour_2.setText(returnIntToString(userTimeBean
							.getUser_schedule_starttime_min() / 60));
					editText_minu_1.setText(returnIntToString(userTimeBean
							.getUser_schedule_starttime_min() % 60));
					editText_minu_2.setText(returnIntToString(userTimeBean
							.getUser_schedule_starttime_min() % 60));

					editText_to_hour_1.setText(returnIntToString(userTimeBean
							.getUser_schedule_endtime_min() / 60));
					editText_to_hour_2.setText(returnIntToString(userTimeBean
							.getUser_schedule_endtime_min() / 60));
					editText_to_minu_1.setText(returnIntToString(userTimeBean
							.getUser_schedule_endtime_min() % 60));
					editText_to_minu_2.setText(returnIntToString(userTimeBean
							.getUser_schedule_endtime_min() % 60));
				}
				break;

			default:
				break;
			}

		};
	};

	private String returnIntToString(int time) {
		if (time < 10) {
			return "0" + time;
		} else {
			return "" + time;
		}

	}

	private LinearLayout linearsetting;

	private void findviewSelf() {
		 
		userOk = (Button) findViewById(R.id.ok);
		userCancel = (Button) findViewById(R.id.back);
		userOk.setOnClickListener(this);
		userCancel.setOnClickListener(this);

		relative_one = (RelativeLayout) findViewById(R.id.relative_one);
		relative_two = (RelativeLayout) findViewById(R.id.relative_two);
		spinner_start = (Spinner) findViewById(R.id.spinner_start);
		spinner_start.setAdapter(showSpnner(list));
		spinner_start.setSelection(0);

		spinner_end = (Spinner) findViewById(R.id.spinner_end);
		spinner_end.setAdapter(showSpnner(list));
		spinner_end.setSelection(5);

		spinner_start_2 = (Spinner) findViewById(R.id.spinner_start_2);
		spinner_start_2.setAdapter(showSpnner(list));
		spinner_start_2.setSelection(5);

		spinner_end_2 = (Spinner) findViewById(R.id.spinner_end_2);
		spinner_end_2.setAdapter(showSpnner(list));
		spinner_end_2.setSelection(5);

		group1 = (RadioGroup) findViewById(R.id.group1);
		rb1 = (RadioButton) findViewById(R.id.rb1);
		rb2 = (RadioButton) findViewById(R.id.rb2);

		group1_select = (RadioGroup) findViewById(R.id.group_select);
		rb1_select = (RadioButton) findViewById(R.id.rb1_select);
		rb2_select = (RadioButton) findViewById(R.id.rb2_select);

		layout_one = (LinearLayout) findViewById(R.id.layout_one);
		layout_two = (LinearLayout) findViewById(R.id.layout_two);

		editText_hour_1 = (EditText) findViewById(R.id.editText_hour_1);
		editText_hour_2 = (EditText) findViewById(R.id.editText_hour_2);
		editText_minu_1 = (EditText) findViewById(R.id.editText_minu_1);
		editText_minu_2 = (EditText) findViewById(R.id.editText_minu_2);
		editText_to_hour_1 = (EditText) findViewById(R.id.editText_to_hour_1);
		editText_to_hour_2 = (EditText) findViewById(R.id.editText_to_hour_2);
		editText_to_minu_1 = (EditText) findViewById(R.id.editText_to_minu_1);
		editText_to_minu_2 = (EditText) findViewById(R.id.editText_to_minu_2);
		userTimeBean = new UserTimeBean();
		editText_hour_1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				try {
					String result = arg0.toString();
					if (result == null) {
						result = "0";
					}
					int reInt = Integer.parseInt(result);
					if (reInt > 23) {
						showToast("0-23");
						editText_hour_1.setText("");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		editText_hour_2.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				try {
					String result = arg0.toString();
					if (result == null) {
						result = "0";
					}
					int reInt = Integer.parseInt(result);
					if (reInt > 23) {
						showToast("0-23");
						editText_hour_2.setText("");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		editText_minu_1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				try {
					String result = arg0.toString();
					if (result == null) {
						result = "0";
					}
					int reInt = Integer.parseInt(result);
					if (reInt > 59) {
						showToast("0-59");
						editText_minu_1.setText("");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		editText_minu_2.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				try {
					String result = arg0.toString();
					if (result == null) {
						result = "0";
					}
					int reInt = Integer.parseInt(result);
					if (reInt > 59) {
						showToast("0-59");
						editText_minu_2.setText("");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		linearsetting = (LinearLayout) findViewById(R.id.linearsetting);

		editText_to_hour_1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				try {
					String result = arg0.toString();
					if (result == null) {
						result = "0";
					}
					int reInt = Integer.parseInt(result);
					if (reInt > 23) {
						showToast("0-23");
						editText_to_hour_1.setText("");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		editText_to_hour_2.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				try {
					String result = arg0.toString();
					if (result == null) {
						result = "0";
					}
					int reInt = Integer.parseInt(result);
					if (reInt > 23) {
						showToast("0-23");
						editText_to_hour_2.setText("");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		editText_to_minu_1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				try {
					String result = arg0.toString();
					if (result == null) {
						result = "0";
					}
					int reInt = Integer.parseInt(result);
					if (reInt > 59) {
						showToast("0-59");
						editText_to_minu_1.setText("");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		editText_to_minu_2.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				try {
					String result = arg0.toString();
					if (result == null) {
						result = "0";
					}
					int reInt = Integer.parseInt(result);
					if (reInt > 59) {
						showToast("0-59");
						editText_to_minu_2.setText("");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getDataFromOther();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.settingusertime);
		list = new ArrayList<String>();
		listAdd();
		findviewSelf();
		BridgeService.setUserTimeInterface(this);
		NativeCaller.PPPPGetSystemParams(strDID,
				ContentCommon.MSG_TYPE_GET_USER_TIME);
		group1_select
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						 
						if (checkedId == rb1_select.getId()) {
							linearsetting.setVisibility(View.GONE);
							userTimeBean.setUser_schedule_enable(0);
						} else if (checkedId == rb2_select.getId()) {
							linearsetting.setVisibility(View.VISIBLE);
							userTimeBean.setUser_schedule_enable(1);
						}
					}
				});

		group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				 
				if (checkedId == rb1.getId()) {
					relative_one.setVisibility(View.VISIBLE);
					relative_two.setVisibility(View.GONE);
					userTimeBean.setUser_schedule_mode(0);
				} else if (checkedId == rb2.getId()) {
					Log.d("tag", "wifiBean.setEncryp(1)");
					relative_one.setVisibility(View.GONE);
					relative_two.setVisibility(View.VISIBLE);
					userTimeBean.setUser_schedule_mode(1);
				}
			}
		});
	}

	private ArrayAdapter<String> showSpnner(List<String> list) {
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				SettingUserTimeActivity.this,
				android.R.layout.simple_spinner_item, list);
		        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
		        
		return arrayAdapter;
	}

	private void listAdd() {
		 
		list.add(getResources().getString(R.string.set_user_time_1));
		list.add(getResources().getString(R.string.set_user_time_2));
		list.add(getResources().getString(R.string.set_user_time_3));
		list.add(getResources().getString(R.string.set_user_time_4));
		list.add(getResources().getString(R.string.set_user_time_5));
		list.add(getResources().getString(R.string.set_user_time_6));
		list.add(getResources().getString(R.string.set_user_time_7));

	}

	private void getDataFromOther() {
		Intent intent = getIntent();
		strDID = intent.getStringExtra(ContentCommon.STR_CAMERA_ID);
		// cameraName = intent.getStringExtra(ContentCommon.STR_CAMERA_NAME);
	}

	@Override
	protected void onDestroy() {
		BridgeService.setUserTimeInterface(null);
		super.onDestroy();
	}

	@Override
	public void callBackUserTime(String did, int user_schedule_enable,
			int user_schedule_mode, int user_schedule_startday,
			int user_schedule_endday, int user_schedule_starttime_min,
			int user_schedule_endtime_min) {
		 
		userTimeBean.setUser_schedule_enable(user_schedule_enable);
		userTimeBean.setUser_schedule_endday(user_schedule_endday);
		userTimeBean.setUser_schedule_endtime_min(user_schedule_endtime_min);
		userTimeBean.setUser_schedule_mode(user_schedule_mode);
		userTimeBean.setUser_schedule_startday(user_schedule_startday);
		userTimeBean
				.setUser_schedule_starttime_min(user_schedule_starttime_min);
		handler.sendEmptyMessage(PARAMS);
	}

	@Override
	public void onClick(View arg0) {
		 
		switch (arg0.getId()) {
		case R.id.ok:
			if (retrunStringToInt(editText_hour_1.getText().toString()) > 23
					|| retrunStringToInt(editText_hour_1.getText().toString()) < 0
					|| retrunStringToInt(editText_hour_2.getText().toString()) > 23
					|| retrunStringToInt(editText_hour_2.getText().toString()) < 0
					|| retrunStringToInt(editText_to_hour_1.getText()
							.toString()) > 23
					|| retrunStringToInt(editText_to_hour_1.getText()
							.toString()) < 0
					|| retrunStringToInt(editText_to_hour_2.getText()
							.toString()) > 23
					|| retrunStringToInt(editText_to_hour_2.getText()
							.toString()) < 0) {
				showToast(R.string.set_user_time_show1);
				return;
			}

			if (retrunStringToInt(editText_minu_1.getText().toString()) > 59
					|| retrunStringToInt(editText_minu_1.getText().toString()) < 0
					|| retrunStringToInt(editText_minu_2.getText().toString()) > 59
					|| retrunStringToInt(editText_minu_2.getText().toString()) < 0
					|| retrunStringToInt(editText_to_minu_1.getText()
							.toString()) > 59
					|| retrunStringToInt(editText_to_minu_1.getText()
							.toString()) < 0
					|| retrunStringToInt(editText_to_minu_2.getText()
							.toString()) > 59
					|| retrunStringToInt(editText_to_minu_2.getText()
							.toString()) < 0) {
				showToast(R.string.set_user_time_show2);
				return;
			}
			if (userTimeBean.getUser_schedule_mode() == 0) {
				userTimeBean.setUser_schedule_startday(spinner_start
						.getSelectedItemPosition());
				userTimeBean.setUser_schedule_endday(spinner_end
						.getSelectedItemPosition());
				userTimeBean
						.setUser_schedule_starttime_min(retrunStringToInt(editText_hour_1
								.getText().toString())
								* 60
								+ retrunStringToInt(editText_minu_1.getText()
										.toString()));
				userTimeBean
						.setUser_schedule_endtime_min(retrunStringToInt(editText_to_hour_1
								.getText().toString())
								* 60
								+ retrunStringToInt(editText_to_minu_1
										.getText().toString()));

			} else {

				int startTime = retrunStringToInt(editText_hour_2.getText()
						.toString())
						* 60
						+ retrunStringToInt(editText_minu_2.getText()
								.toString());
				int endTime = retrunStringToInt(editText_to_hour_2.getText()
						.toString())
						* 60
						+ retrunStringToInt(editText_to_minu_2.getText()
								.toString());
				if (startTime >= endTime) {
					showToast(R.string.set_user_time_show3);
					return;
				}
				userTimeBean.setUser_schedule_startday(spinner_start_2
						.getSelectedItemPosition());
				userTimeBean.setUser_schedule_endday(spinner_end_2
						.getSelectedItemPosition());
				userTimeBean.setUser_schedule_starttime_min(startTime);
				userTimeBean.setUser_schedule_endtime_min(endTime);

			}

			NativeCaller.PPPPSetUserTime(strDID,
					userTimeBean.getUser_schedule_enable(),
					userTimeBean.getUser_schedule_mode(),
					userTimeBean.getUser_schedule_startday(),
					userTimeBean.getUser_schedule_endday(),
					userTimeBean.getUser_schedule_starttime_min(),
					userTimeBean.getUser_schedule_endtime_min());
			finish();
			overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);
			break;
		case R.id.back:
			finish();
			overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);
			break;
		default:
			break;
		}
	}

	private int retrunStringToInt(String time) {
		 
		if (time==null||time.length()<1) {
			time = "0";
		}
		time.trim();
		return Integer.parseInt(time);
	}
}
