package com.livixa.client;

import object.p2pipcam.adapter.AlarmLogAdapter;
import object.p2pipcam.bean.AlarmLogBean;
import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.system.SystemValue;
import object.p2pipcam.utils.DataBaseHelper;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.livixa.apacam.client.activity.LoginActivity;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.utility.AppPreference;

/**
 * ÿһ����������30�������¼�
 * */
public class AlarmLogActivity extends BaseActivity implements OnClickListener {
	private Button btnBack,btnClean;
	private ListView listView;
	private DataBaseHelper helper = null;
	private AlarmLogAdapter adapter;
	private String did;
	private String camName;
	private TextView tvNoLog;
	private int play_tag_log = 0;
	private LinearLayout linearLayout_buttom;
	private Button button_cancel, button_ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.alarmlog);
		
		
		
		NoUserLoginAndCallFromNotificationThenMoveBack();
		
		
		findView();
		setListener();
		helper = DataBaseHelper.getInstance(this);
		adapter = new AlarmLogAdapter(this);
		listView.setAdapter(adapter);
		getDataFromOther();
		if (adapter.getCount() > 0) {
			listView.setVisibility(View.VISIBLE);
			tvNoLog.setVisibility(View.GONE);
		} else {
			listView.setVisibility(View.GONE);
			tvNoLog.setVisibility(View.VISIBLE);
		}
		if (play_tag_log == 1) {
			linearLayout_buttom.setVisibility(View.VISIBLE);
			btnBack.setVisibility(View.GONE);
		}
	}

	private void getDataFromOther() {
		Intent intent = getIntent();
		did = intent.getStringExtra(ContentCommon.STR_CAMERA_ID);
		camName = intent.getStringExtra(ContentCommon.STR_CAMERA_NAME);
		play_tag_log = intent.getIntExtra("play_tag_log", 0);
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				 
				initData();
			}
		});

	}
	
	
	private void NoUserLoginAndCallFromNotificationThenMoveBack()
	{
		
		Intent intnt=getIntent();
		
		if(intnt!=null)
		{
			boolean isCommingFromNotification=intnt.getBooleanExtra("commingFromNotification", false);
			
			boolean isUserLogin=AppPreference.getSavedData(this, AppKeys.KEY_IS_LOGIN);
			
			if(isCommingFromNotification && !isUserLogin)
			{
				
				Intent intent=new Intent(this,LoginActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				finish();
				
			}
		}
					
		
	}
	

	private void initData() {
		adapter.clearAllAlarmLog();
		Cursor cursor = helper.queryAllAlarmLog(did);
		int count = 0;
		if (cursor != null) {
			while (cursor.moveToNext()) {
				count++;
				if (count <= 30) {
					String createTime = cursor.getString(cursor
							.getColumnIndex(DataBaseHelper.KEY_CREATETIME));
					String content = cursor
							.getString(cursor
									.getColumnIndex(DataBaseHelper.KEY_ALARMLOG_CONTENT));
					Log.d("tag", "createTime:" + createTime);
					AlarmLogBean alarmLogBean = new AlarmLogBean();
					alarmLogBean.setContent(content);
					alarmLogBean.setCreatetime(createTime);
					alarmLogBean.setCamName(camName);
					adapter.addAlarmLog(alarmLogBean);
				} else {
					String createTime = cursor.getString(cursor
							.getColumnIndex(DataBaseHelper.KEY_CREATETIME));
					String content = cursor
							.getString(cursor
									.getColumnIndex(DataBaseHelper.KEY_ALARMLOG_CONTENT));
					helper.delAlarmLog(did, createTime);
					Log.d("tag", "ɾ������ı�����־��" + (count - 30));
				}
			}
			cursor.close();
			adapter.notifyDataSetChanged();
		}
	}

	private void setListener() {
		btnBack.setOnClickListener(this);
		btnClean.setOnClickListener(this);
		button_cancel.setOnClickListener(this);
		button_ok.setOnClickListener(this);
		btnClean.setOnClickListener(this);
	}

	private void findView() {
		btnBack = (Button) findViewById(R.id.back);
		btnClean=(Button) findViewById(R.id.clean);
		listView = (ListView) findViewById(R.id.listView1);
		tvNoLog = (TextView) findViewById(R.id.no_log);
		button_cancel = (Button) findViewById(R.id.aler_cancel);
		button_ok = (Button) findViewById(R.id.aler_ok);
		linearLayout_buttom = (LinearLayout) findViewById(R.id.alarm_bottom_layout);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		getDataFromOther();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		adapter = null;
		NotificationManager notificationManager = (NotificationManager) this
				.getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(1514);
	}
	
	@Override
	public void onBackPressed() {
		 
		super.onBackPressed();
		
		finish();
		overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);// �˳�����
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);// �˳�����
			break;
		case R.id.clean:
			adapter.clearAllAlarmLog();
			helper.delAlarmLogAll(did);
			adapter.notifyDataSetChanged();
			break;
		case R.id.aler_cancel:
			NotificationManager notificationManager = (NotificationManager) this
					.getSystemService(NOTIFICATION_SERVICE);
			notificationManager.cancel(1514);
			finish();
			overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);// �˳�����
			break;
		case R.id.aler_ok:
			if (SystemValue.ISRUN == false) {
				showToastLong(R.string.aler_ok_show);
			} else {
				NotificationManager notificationManager1 = (NotificationManager) this
						.getSystemService(NOTIFICATION_SERVICE);
				notificationManager1.cancel(1514);
				finish();
				Intent in = new Intent(AlarmLogActivity.this,
						PlayActivitySport.class);
				in.putExtra(ContentCommon.STR_CAMERA_TYPE,
						ContentCommon.CAMERA_TYPE_MJPEG);
				in.putExtra(ContentCommon.STR_STREAM_TYPE,
						ContentCommon.MJPEG_SUB_STREAM);
				in.putExtra(ContentCommon.STR_CAMERA_NAME, camName);
				in.putExtra(ContentCommon.STR_CAMERA_ID, did);
				in.putExtra(ContentCommon.STR_CAMERA_USER, retrunUser(did));
				in.putExtra(ContentCommon.STR_CAMERA_PWD, retrunUserPWD(did));
				in.putExtra(ContentCommon.STR_CAMERA_TYPE, 0);
				startActivity(in);
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);

			}
			break;
		default:
			break;
		}
	}

	private String retrunUser(String did) {

		for (int i = 0; i < SystemValue.arrayList.size(); i++) {
			if (did != null
					&& did.equals(SystemValue.arrayList.get(i).getDid())) {
				Log.d("tag", "testuser:"
						+ SystemValue.arrayList.get(i).getUser());
				return SystemValue.arrayList.get(i).getUser();
			}
		}
		return null;
	}

	private String retrunUserPWD(String did) {

		for (int i = 0; i < SystemValue.arrayList.size(); i++) {
			if (did != null
					&& did.equals(SystemValue.arrayList.get(i).getDid())) {
				Log.d("tag", "testuserpwd:"
						+ SystemValue.arrayList.get(i).getPwd());
				return SystemValue.arrayList.get(i).getPwd();
			}
		}
		return null;
	}
}
