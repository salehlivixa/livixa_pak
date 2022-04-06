package object.p2pipcam.adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.livixa.client.IpcamClientActivity;
import com.livixa.client.IpcamClientActivityFour;
import com.livixa.client.R;
import com.livixa.apacam.dbmodel.Camera_Model;
import com.livixa.apacam.dbmodel.Manage_DB_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import object.p2pipcam.bean.CameraParamsBean;
import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.system.SystemValue;
import object.p2pipcam.utils.DataBaseHelper;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CameraListAdapter extends BaseAdapter {

	private static final String LOG_TAG = "CameraListAdapter";
	private IpcamClientActivity ipcamClientActivity;

	private IpcamClientActivityFour ipcamClientActivity1;
	private LayoutInflater listContainer = null;
	private DataBaseHelper helper = null;
	@SuppressWarnings("unused")
	private Context context = null;
	public boolean isSearching = false;// �Ƿ�������
	private Matrix matrix;
	private String dids;

	public final class CameraListItem {
		public ImageView imgSnapShot;
		public ImageView imgBtnSetting;
		public TextView devName;
		public TextView devID;
		public TextView devType;
		public TextView devStatus;
		public Button leftBtn;
	}

	public CameraListAdapter(Context ct, IpcamClientActivity ipcamClientActivity) {
		this.ipcamClientActivity = ipcamClientActivity;
		context = ct;
		listContainer = LayoutInflater.from(ct);
		helper = DataBaseHelper.getInstance(context);
		
		
		
		//Manage_DB_Model.realDeleteAllrecode();
	}

	public CameraListAdapter(Context ct,
			IpcamClientActivityFour ipcamClientActivity) {
		this.ipcamClientActivity1 = ipcamClientActivity;
		context = ct;
		listContainer = LayoutInflater.from(ct);
		helper = DataBaseHelper.getInstance(context);
	}

	@Override
	public int getCount() {

		return SystemValue.arrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		synchronized (this) {
			CameraListItem cameraListItem = null;
			if (convertView == null) {
				cameraListItem = new CameraListItem();
				if (SystemValue.TAG_CAMERLIST == 0) {
					convertView = listContainer.inflate(
							R.layout.camera_list_item, null);
				} else {
					convertView = listContainer.inflate(
							R.layout.camera_list_item_f, null);
				}

				cameraListItem.imgSnapShot = (ImageView) convertView
						.findViewById(R.id.imgSnapshot);
				cameraListItem.devName = (TextView) convertView
						.findViewById(R.id.cameraDevName);
				cameraListItem.devID = (TextView) convertView
						.findViewById(R.id.cameraDevID);
				// cameraListItem.devType =
				// (TextView)convertView.findViewById(R.id.textPPPPMode);
				cameraListItem.devStatus = (TextView) convertView
						.findViewById(R.id.textPPPPStatus);
				cameraListItem.imgBtnSetting = (ImageView) convertView
						.findViewById(R.id.imgBtnPPPPSetting);
				convertView.setTag(cameraListItem);
			} else {
				cameraListItem = (CameraListItem) convertView.getTag();
			}

			MyOnTouchListener myOnTouchListener = new MyOnTouchListener(
					position);
			cameraListItem.imgBtnSetting.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					// TODO Auto-generated method stub
					if (ipcamClientActivity != null) {
						ipcamClientActivity.showSetting(position);
					}	
				}
			});
//			cameraListItem.imgBtnSetting.setOnTouchListener(myOnTouchListener);
			// Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),
			// R.drawable.main_right_menu);
			// int btmWidth = bmp.getWidth();
			// int btmHeight = bmp.getHeight();
			// float scaleW = ((float) 40) / btmWidth;
			// float scaleH = ((float) 40) / btmHeight;
			// Matrix matrix = new Matrix();
			// matrix.postScale(scaleW, scaleH);
			// Bitmap bt = Bitmap.createBitmap(bmp, 0, 0, btmWidth, btmHeight,
			// matrix, true);
			// cameraListItem.imgBtnSetting.setImageBitmap(bt);
			CameraParamsBean bean = SystemValue.arrayList.get(position);
			
			if(bean.isSubUser())
			{
				if(!bean.isSettingAllowed())
				{
					cameraListItem.imgBtnSetting.setVisibility(View.GONE);
				}
				else
				{
					cameraListItem.imgBtnSetting.setVisibility(View.VISIBLE);
				}
			}
			
			cameraListItem.devName.setText(bean.getName());
			cameraListItem.devID.setText(bean.getDid());
			Bitmap bmp2 = bean.getBmp();
			if (bmp2 != null) {
				Drawable drawable = new BitmapDrawable(bmp2);
				cameraListItem.imgSnapShot.setBackgroundDrawable(drawable);
			} else {
				Bitmap bitmap = getFirstPic(bean.getDid());
				if (bitmap != null) {
					Drawable dra = new BitmapDrawable(bitmap);
					cameraListItem.imgSnapShot.setBackgroundDrawable(dra);
				} else {
					cameraListItem.imgSnapShot
							.setBackgroundResource(R.drawable.vidicon);
				}
			}

			MyOnTouchLister myLister = new MyOnTouchLister(position);
			convertView.setOnTouchListener(myLister);
			// if(position==0){
			// convertView.setBackgroundResource(R.drawable.listitem_pressed_top_corner_selector);
			// }else if(position==listItems.size()-1){
			// convertView.setBackgroundResource(R.drawable.listitem_pressed_bottom_corner_selector);
			// }else{
			// }
			// convertView.setBackgroundResource(R.drawable.listitem_pressed_selector);
			// int status = (Integer)mapItem.get(ContentCommon.STR_PPPP_STATUS);
			int status = bean.getStatus();
			dids = bean.getDid();
			Log.d(LOG_TAG, "did:" + dids + "---status: " + status);
			int resid;
            //abc12345
			switch (status) {
			case ContentCommon.PPPP_STATUS_CONNECTING:
				resid = R.string.pppp_status_connecting;
				break;
			case ContentCommon.PPPP_STATUS_CONNECT_FAILED:
				resid = R.string.pppp_status_connect_failed;
				break;
			case ContentCommon.PPPP_STATUS_DISCONNECT:
				resid = R.string.pppp_status_disconnect;
				break;
			case ContentCommon.PPPP_STATUS_INITIALING:
				resid = R.string.pppp_status_initialing;
				break;
			case ContentCommon.PPPP_STATUS_INVALID_ID:
				resid = R.string.pppp_status_invalid_id;
				break;
			case ContentCommon.PPPP_STATUS_ON_LINE:
				resid = R.string.pppp_status_online;
				break;
			case ContentCommon.PPPP_STATUS_DEVICE_NOT_ON_LINE:
				resid = R.string.device_not_on_line;
				break;
			case ContentCommon.PPPP_STATUS_CONNECT_TIMEOUT:
				resid = R.string.pppp_status_connect_timeout;
				break;
			case ContentCommon.PPPP_STATUS_CONNECT_ERRER:
				resid = R.string.pppp_status_connect_log_errer;
				break;
			case ContentCommon.PPPP_STATUS_INVALID_TIME:
				resid = R.string.set_user_time_pppp_statu;
				break;
			default:
				resid = R.string.pppp_status_connecting;
			}

			cameraListItem.devStatus.setText(resid);
		}
		return convertView;
	}

	private Bitmap getFirstPic(String did) {
		Cursor cursor = helper.queryFirstpic(did);
		String filepath = null;
		if (cursor.getCount() > 0) {
			cursor.moveToNext();
			filepath = cursor.getString(cursor
					.getColumnIndex(DataBaseHelper.KEY_FILEPATH));
		}
		if (cursor != null) {
			cursor.close();
		}
		if (filepath != null) {
			Bitmap bitmap = BitmapFactory.decodeFile(filepath);
			return bitmap;

		} else {
			return null;
		}

	}

	private class MyOnTouchLister implements OnTouchListener {
		private int position;

		public MyOnTouchLister(int position) {
			this.position = position;
		}

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			CameraParamsBean bean = SystemValue.arrayList.get(position);
			IpcamClientActivity.changerCameraStatus(bean.getStatus());
			IpcamClientActivityFour.changerCameraStatus(bean.getStatus());
			return false;
		}
	}

	private class MyOnTouchListener implements OnTouchListener {
		private int position;

		// private Map<String, Object> mapItem;
		// public ImageButton imgBtn;

		public MyOnTouchListener(int position) {
			this.position = position;
		}

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// imgBtn = (ImageButton) v;
			switch (event.getAction()) {
			case MotionEvent.ACTION_UP:
				if (ipcamClientActivity != null) {
					ipcamClientActivity.showSetting(position);
				}
				break;
			}
			return false;
		}

	}

	public CameraParamsBean getItemCamera(int position) {
		return SystemValue.arrayList.get(position);
	}

	public boolean AddCamera(String name, String did, String user, String pwd) {
		// ��������MAC�ظ�������ӵ��б���
		if (!CheckCameraInfo(did)) {
			return false;
		}
		CameraParamsBean bean = new CameraParamsBean();
		bean.setAuthority(0);
		bean.setName(name);
		bean.setDid(did);
		bean.setUser(user);
		bean.setPwd(pwd);
		bean.setStatus(ContentCommon.PPPP_STATUS_UNKNOWN);
		bean.setMode(ContentCommon.PPPP_MODE_UNKNOWN);
		
		//Manage_DB_Model.createCameraModelAndUserModelRelationInDB(bean);
		
		
		synchronized (this) {
			SystemValue.arrayList.add(bean);
		}
		return true;
	}

	
	public boolean AddCameraSubUserCase(String name, String did, String user, String pwd,String isSettingAllowed) {
		// ��������MAC�ظ�������ӵ��б���
		if (!CheckCameraInfo(did)) {
			return false;
		}
		
		
		CameraParamsBean bean = new CameraParamsBean();
		if(isSettingAllowed!=null && isSettingAllowed.equals("1"))
		bean.setSettingAllowed(true);
		else
		bean.setSettingAllowed(false);
		
		bean.setSubUser(true);
		bean.setAuthority(0);
		bean.setName(name);
		bean.setDid(did);
		bean.setUser(user);
		bean.setPwd(pwd);
		bean.setStatus(ContentCommon.PPPP_STATUS_UNKNOWN);
		bean.setMode(ContentCommon.PPPP_MODE_UNKNOWN);
		
		//Manage_DB_Model.createCameraModelAndUserModelRelationInDB(bean);
		
		
		synchronized (this) {
			SystemValue.arrayList.add(bean);
		}
		return true;
	}
	/**
	 * �鿴�û���Ȩ��
	 * */
	//abc123456
	public void upadeUserAuthority(String did, String user1, String pwd1,
			String user2, String pwd2, String user3, String pwd3, int mode) {
		
		
		
		
		for (CameraParamsBean bean : SystemValue.arrayList) {
			String strDId = bean.getDid();
			Log.i(LOG_TAG, "1--mode4 " + mode + " DID: " + did + "\nSDID: "
					+ strDId);

			if (strDId.equalsIgnoreCase(did)) {
				String strUser = bean.getUser();
				String strPwd = bean.getPwd();
				Log.i(LOG_TAG, "2--mode4  " + mode + " DID: " + did
						+ "\nSDID: " + strDId);
				bean.setKmode(mode);

				if (user3.equals(strUser) && pwd3.equals(strPwd)) {
					bean.setAuthority(0);
					return;
				} else if (user2.equals(strUser) && pwd2.equals(strPwd)) {
					bean.setAuthority(1);
					return;
				} else {
					bean.setAuthority(2);
					return;
				}
			}
		}
	}

	/**
	 * 
	 * @param did
	 * @param status
	 * @return
	 */
	public boolean UpdataCameraStatus(String did, int status) {
		Log.d(LOG_TAG, "UpdataCameraStatus... did: " + did + " status: "
				+ status);
		
		
		
		synchronized (this) 
		{
			int size = SystemValue.arrayList.size();
			for (int i = 0; i < size; i++) 
			{
				CameraParamsBean bean = SystemValue.arrayList.get(i);
				String strDid = bean.getDid();
				if (did.equalsIgnoreCase(strDid)) {
					int _status = bean.getStatus();
					if (_status == status) {
						return false;
					}
					bean.setStatus(status);
					return true;
				}

			}
			return false;
		}
	}

	/**
	 * 
	 * @param did
	 * @param type
	 * @return
	 */
	public boolean UpdataCameraType(String did, int type) {
		
		
		
		synchronized (this) {
			for (CameraParamsBean bean : SystemValue.arrayList) {
				String strDid = bean.getDid();
				if (strDid.equalsIgnoreCase(did)) {
					int mode = bean.getMode();
					if (mode == type) {
						return false;
					}
					bean.setMode(type);
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * ����Ƿ��ظ�
	 * 
	 * @param mac
	 * @return
	 */
	private boolean CheckCameraInfo(String did) {
		
		
		
		
		// �����б?����Ƿ�����ͬmac��ַ�������
		synchronized (this) {
			int count = SystemValue.arrayList.size();
			for (int i = 0; i < count; i++) {
				String strDid = SystemValue.arrayList.get(i).getDid();
				if (strDid.equalsIgnoreCase(did)) {
					return false;
				}
			}
			return true;
		}
	}

	public CameraParamsBean getOnItem(int position) {
		synchronized (this) {
			if (position > SystemValue.arrayList.size()) {
				return null;
			}
			return SystemValue.arrayList.get(position);
		}
	}

	public void sendCameraStatus() {
		int count = SystemValue.arrayList.size();
		for (int i = 0; i < count; i++) {
			CameraParamsBean bean = SystemValue.arrayList.get(i);
			int status = bean.getStatus();
			String did = bean.getDid();
			Intent intent = new Intent("camera_status_change");
			intent.putExtra(ContentCommon.STR_CAMERA_ID, did);
			intent.putExtra(ContentCommon.STR_PPPP_STATUS, status);
			context.sendBroadcast(intent);
		}
	}

	public boolean delCamera(String did) {
		Log.d(LOG_TAG, "Call delCamera");
		synchronized (this) {
			int size = SystemValue.arrayList.size();
			for (int i = 0; i < size; i++) {
				String strDid = SystemValue.arrayList.get(i).getDid();
				if (strDid.equalsIgnoreCase(did)) {
					SystemValue.arrayList.remove(i);
					return true;
				}
			}
			return false;
		}
	}

	public boolean UpdateCamera(String oldDID, String name, String did,
			String user, String pwd) {
		synchronized (this) {
			int size = SystemValue.arrayList.size();
			for (int i = 0; i < size; i++) {
				CameraParamsBean bean = SystemValue.arrayList.get(i);
				String strDid = bean.getDid();
				if (oldDID.equalsIgnoreCase(strDid)) {
					bean.setAuthority(0);
					bean.setName(name);
					bean.setDid(did);
					bean.setUser(user);
					bean.setPwd(pwd);
					bean.setStatus(ContentCommon.PPPP_STATUS_UNKNOWN);
					return true;
				}
			}
			return false;
		}
	}

	public boolean UpdateCameraImage(String did, Bitmap bmp) {
		synchronized (this) {
			matrix = new Matrix();
			matrix.postScale(0.2f, 0.2f);
			bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
					bmp.getHeight(), matrix, true);
			int size = SystemValue.arrayList.size();
			for (int i = 0; i < size; i++) {
				CameraParamsBean bean = SystemValue.arrayList.get(i);
				String strDid = bean.getDid();
				if (strDid.equalsIgnoreCase(did)) {
					bean.setBmp(bmp);
					saveBmpToSDcard(did, bmp);
					return true;
				}
			}
			return false;
		}
	}

	private void saveBmpToSDcard(String did, Bitmap bitmap) {
		FileOutputStream fos = null;
		File div = new File(Environment.getExternalStorageDirectory(),
				"ipcamera/picid");
		if (!div.exists()) {
			div.mkdirs();
		}
		try {
			File file = new File(div, did + ".jpg");
			fos = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos)) {
				fos.flush();
				Cursor cursor = helper.queryFirstpic(did);
				if (cursor.getCount() <= 0) {
					Log.d("tag", "��û��ͼƬ���򱣴�");
					String path = file.getAbsolutePath();
					helper.addFirstpic(did, path);
				}
				fos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	

}