package com.livixa.client;

import java.util.Date;

import object.p2pipcam.adapter.AlarmActivityAdapter;
import object.p2pipcam.bean.CameraParamsBean;
import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.system.SystemValue;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import com.livixa.apacam.client.base.KisafaApplication;
import android.widget.ListView;

/**
 * �����¼���ʾ������б�
 * */
public class AlarmActivity extends BaseActivity implements OnItemClickListener {
	private AlarmActivityAdapter listAdapter;
	private ListView listView;
	private MyStatusBroadCast broadcast;
	private int timeTag = 0;
	private int timeOne = 0;
	private int timeTwo = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarmactivity);
		findView();
		// initCamera();
		if (SystemValue.arrayList.size() == 0) {
			listView.setVisibility(View.GONE);
		} else {
			listView.setVisibility(View.VISIBLE);
		}
		listAdapter = new AlarmActivityAdapter(this, SystemValue.arrayList);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(this);
		broadcast = new MyStatusBroadCast();
		IntentFilter filter = new IntentFilter("camera_status_change");
		filter.addAction("del_add_modify_camera");
		registerReceiver(broadcast, filter);
	}

	private void findView() {
		listView = (ListView) findViewById(R.id.listview);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		CameraParamsBean bean = listAdapter.getItemCam(position);
		String did = bean.getDid();
		String name = bean.getName();
		Intent intent = new Intent(this, AlarmLogActivity.class);
		intent.putExtra(ContentCommon.STR_CAMERA_ID, did);
		intent.putExtra(ContentCommon.STR_CAMERA_NAME, name);
		startActivity(intent);
		KisafaApplication.perFormActivityNextTransition(this);

	}

	// ע��㲥�����������״̬�ĸı�
	private class MyStatusBroadCast extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			
			
			
			
			
			String action = intent.getAction();
			if ("camera_status_change".equals(action)) {
				String did = intent.getStringExtra(ContentCommon.STR_CAMERA_ID);
				int status = intent.getIntExtra(ContentCommon.STR_PPPP_STATUS,
						ContentCommon.PPPP_STATUS_UNKNOWN);
				listAdapter.notifyDataSetChanged();
			}
			if ("del_add_modify_camera".equals(action)) {
				
				//Toast.makeText(context, "Event Recieved IN Alarm", Toast.LENGTH_SHORT).show();
				
				int type = intent.getIntExtra("type", 0);
				String did = intent.getStringExtra(ContentCommon.STR_CAMERA_ID);
				switch (type) {
				case ContentCommon.ADD_CAMERA: {
					// String name = intent.getStringExtra("name");
					// CameraParamsBean bean = new CameraParamsBean();
					// bean.setDid(did);
					// bean.setName(name);
					// SystemValue.arrayList.add(bean);
					
					
				}
					break;
				case ContentCommon.EDIT_CAMERA: {
					// String oldDid = intent.getStringExtra("olddid");
					// String name = intent.getStringExtra("name");
					// int size = SystemValue.arrayList.size();
					// for (int i = 0; i < size; i++) {
					// CameraParamsBean bean = SystemValue.arrayList.get(i);
					// if (oldDid.equals(bean.getDid())) {
					// bean.setDid(did);
					// bean.setName(name);
					// break;
					// }
					// }
				}
					break;
				case ContentCommon.DEL_CAMERA: {
					// int size = SystemValue.arrayList.size();
					// for (int i = 0; i < size; i++) {
					// CameraParamsBean bean = SystemValue.arrayList.get(i);
					// if (did.equals(bean.getDid())) {
					// SystemValue.arrayList.remove(i);
					// break;
					// }
					// }
				}
					break;

				default:
					break;
				}
				if (SystemValue.arrayList.size() == 0) {
					listView.setVisibility(View.GONE);
				} else {
					listView.setVisibility(View.VISIBLE);
				}
				listAdapter.notifyDataSetChanged();
			}
		}
	}

	/*@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			showSureDialog(this);
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			Date date = new Date();
			if (timeTag == 0) {
				timeOne = date.getSeconds();
				timeTag = 1;
				Toast.makeText(AlarmActivity.this, R.string.main_show_back, 0)
						.show();
			} else if (timeTag == 1) {
				timeTwo = date.getSeconds();
				if (timeTwo - timeOne <= 3) {
					Intent intent = new Intent("back");
					sendBroadcast(intent);
					timeTag = 0;
				} else {
					timeTag = 1;
					Toast.makeText(AlarmActivity.this, R.string.main_show_back,
							0).show();
				}
			}

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
*/
	@Override
	public void onBackPressed() {
		
		try
		{
		getParent().onBackPressed();
		}catch(Exception ex){}
		
	}
	
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(broadcast);
	}
}
