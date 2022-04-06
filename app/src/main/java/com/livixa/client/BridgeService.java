package com.livixa.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.nativecaller.NativeCaller;
import object.p2pipcam.system.SystemValue;
import object.p2pipcam.utils.DataBaseHelper;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;


public class BridgeService extends Service{
	private String TAG = BridgeService.class.getSimpleName();
	private NotificationManager ntfManager;
	private Notification mNotify2;
	private DataBaseHelper helper = null;
	private NotificationManager mCustomMgr;
	
	
	IBinder mBinder = new ControllerBinder();
	
	
	
	


	@Override
	public IBinder onBind(Intent intent) {
		Log.d("tag", "BridgeService onBind()");
		return mBinder;
	}

	
	

	
	/**
     * 
     * **/
	class ControllerBinder extends Binder {
		public BridgeService getBridgeService() {
			return BridgeService.this;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("tag", "BridgeService onCreate()");
		SystemValue.ISRUN = true;
		mCustomMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		helper = DataBaseHelper.getInstance(this);
		NativeCaller.PPPPSetCallbackContext(this);
		
		
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		startForeground(
				R.drawable.app,
				getNotification(getResources().getString(R.string.app_running),
						"", false));// set up service
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		SystemValue.ISRUN = false;
		stopForeground(true);
		
		playInterface=null;
		playBackTFInterface=null;
		userTimeInterface=null;
		ipPlayInterface=null;
		ipcamClientInterface=null;
		wifiInterface=null;
		userInterface=null;
		addCameraInterface=null;
		sCardInterface=null;
		ftpInterface=null;
		
		try
		{
			NotificationManager nManager = ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE));
			nManager.cancelAll();
		}catch(Exception ex){};
		
		mCustomMgr.cancel(R.drawable.app);
	}

	public void CallBack_AppVersion(String appVersion) {

	}

	public void CallBack_UserTime(String did, int user_schedule_enable,
			int user_schedule_mode, int user_schedule_startday,
			int user_schedule_endday, int user_schedule_starttime_min,
			int user_schedule_endtime_min) {
		Log.d("test", "CallBack_UserTime did:" + did
				+ "  user_schedule_enable:" + user_schedule_enable
				+ " user_schedule_mode:" + user_schedule_mode
				+ "  user_schedule_startday:" + user_schedule_startday
				+ " user_schedule_endday:" + user_schedule_endday
				+ "  user_schedule_starttime_min:"
				+ user_schedule_starttime_min + "  user_schedule_endtime_min:"
				+ user_schedule_endtime_min);
		if (userTimeInterface != null) {
			userTimeInterface.callBackUserTime(did, user_schedule_enable,
					user_schedule_mode, user_schedule_startday,
					user_schedule_endday, user_schedule_starttime_min,
					user_schedule_endtime_min);
		}
	}

	/**
	 * 
	 * PlayActivity feedback method
	 * 
	 * jni
	 * 
	 * @param videobuf
	 * 
	 * @param h264Data
	 * 
	 * @param len
	 * 
	 * @param width
	 * 
	 * @param height
	 * 
	 */
	// @SuppressWarnings("unused")
	// private void VideoData(String did, byte[] videobuf, int h264Data, int
	// len,
	// int width, int height, int time) {
	// // Log.d(TAG, "Call VideoData...h264Data: " + h264Data + " len: " + len
	// // + " videobuf len: " + videobuf.length + "  time==" + time);
	// if (ipPlayInterface != null) {
	// ipPlayInterface.callBaceVideoData(did, videobuf, h264Data, len,
	// width, height);
	// }
	// if (playInterface != null) {
	// playInterface.callBaceVideoData(did, videobuf, h264Data, len,
	// width, height, time);
	// }
	// }

	@SuppressWarnings("unused")
	private void VideoData(String did, byte[] videobuf, int h264Data, int len,int bufsize,
			int width, int height, int time) {
		Log.d(TAG, "Call VideoData...h264Data: " + h264Data + " len: " + len
		+ " videobuf len: " + videobuf.length + "  time==" + time);
		if (ipPlayInterface != null) {
			ipPlayInterface.callBaceVideoData(did, videobuf, h264Data, len,
					width, height);
		}
		if (playInterface != null) {
			playInterface.callBaceVideoData(did, videobuf, h264Data, len,bufsize,
					width, height, time);
		}
	}

	@SuppressWarnings("unused")
	/**
	 * PlayActivity feedback method
	 * 
	 * PPPP
	 * @param did
	 * @param msgType
	 * @param param
	 */
	
	//hell
	private void MessageNotify(String did, int msgType, int param) {
		Log.d("test_four_2", "MessageNotify did: " + did + " msgType: "
				+ msgType + " param: " + param);
		
		
		
		
		if (playInterface != null) {
			playInterface.callBackMessageNotify(did, msgType, param);
		}

	}

	/**
	 * PlayActivity feedback method
	 * 
	 * AudioData
	 * 
	 * @param pcm
	 * @param len
	 */
	@SuppressWarnings("unused")
	private void AudioData(byte[] pcm, int len) {
		//Log.d(TAG, "AudioData: len :+ " + len);
		if (playInterface != null) {
			playInterface.callBackAudioData(pcm, len);
		}
		if (playBackInterface != null) {
			playBackInterface.callBackPlaybackAudioData(pcm, len);
		}
	}

	/**
	 * IpcamClientActivity feedback method
	 * 
	 * p2p statu
	 * 
	 * @param msgtype
	 * @param param
	 */
	//hell
	@SuppressWarnings("unused")
	private void PPPPMsgNotify(String did, int msgtype, int param) {
		
		
		
		
		Log.d(TAG, "PPPPMsgNotify  did:" + did + " type:" + msgtype + " param:"
				+ param);
		if (ipPlayInterface != null) {
			ipPlayInterface.callBackMessageNotify(did, msgtype, param);
		}
		
		
		if (ipcamClientInterface != null) {
			ipcamClientInterface.BSMsgNotifyData(did, msgtype, param);
		}
		
		
		if (wifiInterface != null) {
			wifiInterface.callBackPPPPMsgNotifyData(did, msgtype, param);
		}

		if (userInterface != null) {
			userInterface.callBackPPPPMsgNotifyData(did, msgtype, param);

		}
	}

	/***
	 * SearchActivity feedback method
	 * 
	 * **/
	// public void SearchResult(String name, String did, String ip) {
	// if (did.length() == 0) {
	// return;
	// }
	// if (addCameraInterface != null) {
	// addCameraInterface.callBackSearchResultData(1, "", name, did, ip,
	// 8080);
	// }
	//
	// }

	public void SearchResult(int c1, String name, String did, String ip,
			String a1, int b1) {
		if (did.length() == 0) {
			return;
		}
		if (addCameraInterface != null) {
			addCameraInterface.callBackSearchResultData(c1, name, did, ip, a1,
					b1);
		}

	}

	// ======================callback==================================================
	/**
	 * 
	 * @param paramType
	 * @param result
	 *            0:fail 1：sucess
	 */
	public void CallBack_SetSystemParamsResult(String did, int paramType,
			int result) {
		switch (paramType) {
		case ContentCommon.MSG_TYPE_SET_WIFI:
			if (wifiInterface != null) {
				wifiInterface.callBackSetSystemParamsResult(did, paramType,
						result);
			}
			break;
		case ContentCommon.MSG_TYPE_SET_USER:
			if (userInterface != null) {
				userInterface.callBackSetSystemParamsResult(did, paramType,
						result);
			}
			break;
		case ContentCommon.MSG_TYPE_SET_ALARM:
			if (alarmInterface != null) {
				// Log.d(TAG,"user result:"+result+" paramType:"+paramType);
				alarmInterface.callBackSetSystemParamsResult(did, paramType,
						result);
			}
			break;
		case ContentCommon.MSG_TYPE_SET_MAIL:
			if (mailInterface != null) {
				mailInterface.callBackSetSystemParamsResult(did, paramType,
						result);
			}
			break;
		case ContentCommon.MSG_TYPE_SET_FTP:
			if (ftpInterface != null) {
				ftpInterface.callBackSetSystemParamsResult(did, paramType,
						result);
			}
			break;
		case ContentCommon.MSG_TYPE_SET_DATETIME:
			if (dateTimeInterface != null) {
				Log.d(TAG, "user result:" + result + " paramType:" + paramType);
				dateTimeInterface.callBackSetSystemParamsResult(did, paramType,
						result);
			}
			break;
		case ContentCommon.MSG_TYPE_SET_RECORD_SCH:
			if (sCardInterface != null) {
				sCardInterface.callBackSetSystemParamsResult(did, paramType,
						result);
			}
			break;
		default:
			break;
		}
	}

	public void CallBack_CameraParams(String did, int resolution,
			int brightness, int contrast, int hue, int saturation, int flip,
			int fram, int mode,int ircut) {
		Log.d("ddd", "CallBack_CameraParams fram==" + fram);
		if (playInterface != null) {
			playInterface.callBackCameraParamNotify(did, resolution,
					brightness, contrast, hue, saturation, flip, fram,ircut);
		}
	}

	public void CallBack_WifiParams(String did, int enable, String ssid,
			int wifi_link_status, int channel, int mode, int authtype,
			int encryp, int keyformat, int defkey, String key1, String key2,
			String key3, String key4, int key1_bits, int key2_bits,
			int key3_bits, int key4_bits, String wpa_psk) {
		Log.d("ddd", "CallBack_WifiParams  wifi_link_status��"
				+ wifi_link_status);
		if (wifiInterface != null) {
			wifiInterface.callBackWifiParams(did, enable, ssid,
					wifi_link_status, channel, mode, authtype, encryp,
					keyformat, defkey, key1, key2, key3, key4, key1_bits,
					key2_bits, key3_bits, key4_bits, wpa_psk);
		}
	}

	
	 public void CallBack_UserParams(String did, String user1, String pwd1,
	 String user2, String pwd2, String user3, String pwd3, int mode,
	 int test) {
	 Log.d("ddd", "CallBack_UserParams");
	 Log.i("", "mode3" + mode);
	 if (userInterface != null) {
	 userInterface.callBackUserParams(did, user1, pwd1, user2, pwd2,
	 user3, pwd3);
	 }
	 if (ipcamClientInterface != null) {
	 ipcamClientInterface.callBackUserParams(did, user1, pwd1, user2,
	 pwd2, user3, pwd3, mode);
	 }
	 }
	
//	 public void CallBack_UserParams(String did, String user1, String pwd1,
//	 String user2, String pwd2, String user3, String pwd3, int mode) {
//	 Log.d("ddd", "CallBack_UserParams");
//	 Log.i("", "mode3" + mode);
//	 if (userInterface != null) {
//	 userInterface.callBackUserParams(did, user1, pwd1, user2, pwd2,
//	 user3, pwd3);
//	 }
//	 if (ipcamClientInterface != null) {
//	 ipcamClientInterface.callBackUserParams(did, user1, pwd1, user2,
//	 pwd2, user3, pwd3, mode);
//	 }
//	 }
//	public void CallBack_UserParams(String did, String user1, String pwd1,
//			String user2, String pwd2, String user3, String pwd3) {
//		Log.d("ddd", "CallBack_UserParams");
//		// Log.i("", "mode3" + mode);
//		if (userInterface != null) {
//			userInterface.callBackUserParams(did, user1, pwd1, user2, pwd2,
//					user3, pwd3);
//		}
//		if (ipcamClientInterface != null) {
//			ipcamClientInterface.callBackUserParams(did, user1, pwd1, user2,
//					pwd2, user3, pwd3, 0);
//		}
//	}

	public void CallBack_FtpParams(String did, String svr_ftp, String user,
			String pwd, String dir, int port, int mode, int upload_interval) {
		if (ftpInterface != null) {
			ftpInterface.callBackFtpParams(did, svr_ftp, user, pwd, dir, port,
					mode, upload_interval);
		}
	}

	public void CallBack_DDNSParams(String did, int service, String user,
			String pwd, String host, String proxy_svr, int ddns_mode,
			int proxy_port) {
		Log.d("ddd", "CallBack_DDNSParams");
	}

	public void CallBack_MailParams(String did, String svr, int port,
			String user, String pwd, int ssl, String sender, String receiver1,
			String receiver2, String receiver3, String receiver4) {
		if (mailInterface != null) {
			mailInterface.callBackMailParams(did, svr, port, user, pwd, ssl,
					sender, receiver1, receiver2, receiver3, receiver4);
		}
	}

	public void CallBack_DatetimeParams(String did, int now, int tz,
			int ntp_enable, String ntp_svr, int xialingshi) {
		if (dateTimeInterface != null) {
			dateTimeInterface.callBackDatetimeParams(did, now, tz, ntp_enable,
					ntp_svr, xialingshi);
		}
	}

	/**
	 * IpcamClientActivity feedback method
	 * 
	 * snapshot result
	 * 
	 * @param did
	 * @param bImage
	 * @param len
	 */
	@SuppressWarnings("unused")
	private void PPPPSnapshotNotify(String did, byte[] bImage, int len) {
		Log.d(TAG, "did:" + did + " len:" + len);
		if (ipcamClientInterface != null) {
			ipcamClientInterface.BSSnapshotNotify(did, bImage, len);
		}
	}

	public void CallBack_Snapshot(String did, byte[] data, int len) {
		Log.d("ddd", "CallBack_Snapshot");
		if (ipcamClientInterface != null) {
			ipcamClientInterface.BSSnapshotNotify(did, data, len);
		}
	}

	public void CallBack_APParams(String did, String ssid, String pwd) {
		Log.d("shix", "CallBack_APParams---" + "ssid=" + ssid + "pwd=" + pwd);
	}

	public void CallBack_NetworkParams(String did, String ipaddr,
			String netmask, String gateway, String dns1, String dns2, int dhcp,
			int port, int rtsport) {
		Log.d("ddd", "CallBack_NetworkParams");
	}

	public void CallBack_CameraStatusParams(String did, String sysver,
			String devname, String devid, int alarmstatus, int sdcardstatus,
			int sdcardtotalsize, int sdcardremainsize) {
		Log.d("ddd", "CallBack_CameraStatusParams sysver:" + sysver);
	}

	public void CallBack_PTZParams(String did, int led_mod,
			int ptz_center_onstart, int ptz_run_times, int ptz_patrol_rate,
			int ptz_patrul_up_rate, int ptz_patrol_down_rate,
			int ptz_patrol_left_rate, int ptz_patrol_right_rate,
			int disable_preset) {
		Log.d("ddd", "CallBack_PTZParams");
	}

	public void CallBack_WifiScanResult(String did, String ssid, String mac,
			int security, int dbm0, int dbm1, int mode, int channel, int bEnd) {
		Log.d("tag", "CallBack_WifiScanResult");
		if (wifiInterface != null) {
			wifiInterface.callBackWifiScanResult(did, ssid, mac, security,
					dbm0, dbm1, mode, channel, bEnd);
		}
	}

	public void CallBack_AlarmParams(String did, int motion_armed,
			int motion_sensitivity, int input_armed, int ioin_level,
			int iolinkage, int ioout_level, int alarmpresetsit, int mail,
			int snapshot, int record, int upload_interval, int schedule_enable,
			int enable_alarm_audio, int schedule_sun_0, int schedule_sun_1,
			int schedule_sun_2, int schedule_mon_0, int schedule_mon_1,
			int schedule_mon_2, int schedule_tue_0, int schedule_tue_1,
			int schedule_tue_2, int schedule_wed_0, int schedule_wed_1,
			int schedule_wed_2, int schedule_thu_0, int schedule_thu_1,
			int schedule_thu_2, int schedule_fri_0, int schedule_fri_1,
			int schedule_fri_2, int schedule_sat_0, int schedule_sat_1,
			int schedule_sat_2) {
		if (alarmInterface != null) {
			alarmInterface.callBackAlarmParams(did, motion_armed,
					motion_sensitivity, input_armed, ioin_level, iolinkage,
					ioout_level, alarmpresetsit, mail, snapshot, record,
					upload_interval, schedule_enable, enable_alarm_audio,
					schedule_sun_0, schedule_sun_1, schedule_sun_2,
					schedule_mon_0, schedule_mon_1, schedule_mon_2,
					schedule_tue_0, schedule_tue_1, schedule_tue_2,
					schedule_wed_0, schedule_wed_1, schedule_wed_2,
					schedule_thu_0, schedule_thu_1, schedule_thu_2,
					schedule_fri_0, schedule_fri_1, schedule_fri_2,
					schedule_sat_0, schedule_sat_1, schedule_sat_2);
		}
	}

	public void CallBack_AlarmNotify(String did, int alarmtype) {
		Log.d("tag", "callBack_AlarmNotify did:" + did + " alarmtype:"
				+ alarmtype);
		switch (alarmtype) {
		case ContentCommon.MOTION_ALARM:
			String strMotionAlarm = getResources().getString(
					R.string.alerm_motion_alarm);
			getNotification(strMotionAlarm, did, true);
			break;
		case ContentCommon.GPIO_ALARM:
			String strGpioAlarm = getResources().getString(
					R.string.alerm_gpio_alarm);
			getNotification(strGpioAlarm, did, true);
			break;
		default:
			break;
		}

	}

	private void CallBack_RecordFileSearchResult(String did, String filename,
			int nFileSize, int nRecordCount, int nPageCount, int nPageIndex,
			int nPageSize, int bEnd) {
		Log.d(TAG, "CallBack_RecordFileSearchResult did: " + did
				+ " filename: " + filename + " size: " + nFileSize);
		if (playBackTFInterface != null) {
			playBackTFInterface.callBackRecordFileSearchResult(did, filename,
					nFileSize, nPageCount, bEnd);
		}
	}

	private void CallBack_PlaybackVideoData(String did, byte[] videobuf,
			int h264Data, int len, int width, int height, int time) {
		Log.d(TAG, "CallBack_PlaybackVideoData  len:" + len + " width:" + width
				+ " height:" + height + "  time:" + time);
		if (playBackInterface != null) {
			playBackInterface.callBackPlaybackVideoData(videobuf, h264Data,
					len, width, height, time);
		}
	}

	private void CallBack_H264Data(String did, byte[] h264, int type, int size) {
		// Log.e("tag", "did=" + did + "  h264=" + h264.length);
		if (playInterface != null) {
			playInterface.callBackH264Data(did, h264, type, size);
		}
	}

	public void CallBack_RecordSchParams(String did, int record_cover_enable,
			int record_timer, int record_size, int record_time_enable,
			int record_schedule_sun_0, int record_schedule_sun_1,
			int record_schedule_sun_2, int record_schedule_mon_0,
			int record_schedule_mon_1, int record_schedule_mon_2,
			int record_schedule_tue_0, int record_schedule_tue_1,
			int record_schedule_tue_2, int record_schedule_wed_0,
			int record_schedule_wed_1, int record_schedule_wed_2,
			int record_schedule_thu_0, int record_schedule_thu_1,
			int record_schedule_thu_2, int record_schedule_fri_0,
			int record_schedule_fri_1, int record_schedule_fri_2,
			int record_schedule_sat_0, int record_schedule_sat_1,
			int record_schedule_sat_2, int record_sd_status, int sdtotal,
			int sdfree) {
		Log.d("shix_sd", "shix_sd:���� record_cover_enable:"
				+ record_cover_enable);
		if (sCardInterface != null) {
			sCardInterface.callBackRecordSchParams(did, record_cover_enable,
					record_timer, record_size, record_time_enable,
					record_schedule_sun_0, record_schedule_sun_1,
					record_schedule_sun_2, record_schedule_mon_0,
					record_schedule_mon_1, record_schedule_mon_2,
					record_schedule_tue_0, record_schedule_tue_1,
					record_schedule_tue_2, record_schedule_wed_0,
					record_schedule_wed_1, record_schedule_wed_2,
					record_schedule_thu_0, record_schedule_thu_1,
					record_schedule_thu_2, record_schedule_fri_0,
					record_schedule_fri_1, record_schedule_fri_2,
					record_schedule_sat_0, record_schedule_sat_1,
					record_schedule_sat_2, record_sd_status, sdtotal, sdfree);
		}
	}

	private Notification getNotification(String content, String did,
			boolean isAlarm) {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = f.format(date);
		String titlePrompt = "";
		String title = "";
		PendingIntent pendingIntent = null;
		Intent intent = null;
		if (isAlarm) {
			boolean flag = false;
			String CamName = "";
			Cursor cursor = helper.fetchAllCameras();
			if (cursor != null) {
				while (cursor.moveToNext() && !flag) {
					String name = cursor.getString(1);
					String id = cursor.getString(2);
					String user = cursor.getString(3);
					String pwd = cursor.getString(4);
					Log.d("tag", "notification  name:" + name + " id:" + id);
					if (did.equals(id)) {
						CamName = name;
						flag = true;
					}
				}
				if (cursor != null) {
					cursor.close();
				}
			}

			if (!flag) {
				return null;
			}
			Log.d("tag", "alarm name:" + CamName);
			helper.insertAlarmLogToDB(did, content, strDate);
			titlePrompt = CamName + " " + content;
			intent = new Intent(this, AlarmLogActivity.class);
			intent.putExtra(ContentCommon.STR_CAMERA_ID, did);
			intent.putExtra(ContentCommon.STR_CAMERA_NAME, CamName);
			intent.putExtra("log_content", content);
			intent.putExtra("play_tag_log", 1);
			intent.putExtra("commingFromNotification",true);
			title = CamName;

		} else {
			titlePrompt = getResources().getString(R.string.app_name) + " "
					+ content;
			title = getResources().getString(R.string.app_name);
			intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			intent.setClass(this, MainActivity.class);
			
			intent.putExtra("commingFromNotification",true);
			
		}
		mNotify2 = new Notification(R.drawable.app, titlePrompt,
				System.currentTimeMillis());
		mNotify2.flags = Notification.FLAG_ONLY_ALERT_ONCE;
		
		pendingIntent = PendingIntent.getActivity(this, R.drawable.app, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		//-------------updated code----------------------------------------
		
		NotificationCompat.Builder bNotification = new NotificationCompat.Builder(BridgeService.this);
		
		/*bNotification.setAutoCancel(true)
	     .setDefaults(Notification.DEFAULT_ALL)
	     .setWhen(System.currentTimeMillis())         
	     .setSmallIcon(R.drawable.app)
	     .setTicker("Hearty365")            
	     .setContentTitle(title)
	     .setContentText(content)
	    // .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
	     .setContentIntent(contentIntent)
	     .setContentInfo("Info");*/
		
		//---------------------------------------------------------------------
	
		
		RemoteViews views = new RemoteViews(getPackageName(),R.layout.notification_layout);
		mNotify2.contentIntent = pendingIntent;
		mNotify2.contentView = views;
		mNotify2.contentView.setTextViewText(R.id.no_title, title);
		mNotify2.contentView.setTextViewText(R.id.no_content, content);
		mNotify2.contentView.setTextViewText(R.id.no_time, strDate);
		mNotify2.contentView.setImageViewResource(R.id.no_img, R.drawable.app);

		if (isAlarm) {
			mNotify2.defaults = Notification.DEFAULT_ALL;
			
			mCustomMgr.notify(1514, mNotify2);
		}
		return mNotify2;
	}

	public static IpcamClientInterface ipcamClientInterface;

	public static void setIpcamClientInterface(IpcamClientInterface ipcInterface) {
		ipcamClientInterface = ipcInterface;
	}

	public interface IpcamClientInterface {
		void BSMsgNotifyData(String did, int type, int param);

		void BSSnapshotNotify(String did, byte[] bImage, int len);

		void callBackUserParams(String did, String user1, String pwd1,
				String user2, String pwd2, String user3, String pwd3, int mode);
	}

	private static PictureInterface pictureInterface;

	public static void setPictureInterface(PictureInterface pi) {
		pictureInterface = pi;
	}

	public interface PictureInterface {
		void BSMsgNotifyData(String did, int type, int param);
	}

	private static VideoInterface videoInterface;

	public static void setVideoInterface(VideoInterface vi) {
		videoInterface = vi;
	}

	public interface VideoInterface {
		void BSMsgNotifyData(String did, int type, int param);
	}

	public static WifiInterface wifiInterface;

	public static void setWifiInterface(WifiInterface wi) {
		wifiInterface = wi;
	}

	public interface WifiInterface {
		void callBackWifiParams(String did, int enable, String ssid,
				int wifi_link_status, int channel, int mode, int authtype,
				int encryp, int keyformat, int defkey, String key1,
				String key2, String key3, String key4, int key1_bits,
				int key2_bits, int key3_bits, int key4_bits, String wpa_psk);

		void callBackWifiScanResult(String did, String ssid, String mac,
				int security, int dbm0, int dbm1, int mode, int channel,
				int bEnd);

		void callBackSetSystemParamsResult(String did, int paramType, int result);

		void callBackPPPPMsgNotifyData(String did, int type, int param);
	}

	public static UserInterface userInterface;

	public static void setUserInterface(UserInterface ui) {
		userInterface = ui;
	}

	public interface UserInterface {
		void callBackUserParams(String did, String user1, String pwd1,
				String user2, String pwd2, String user3, String pwd3);

		void callBackSetSystemParamsResult(String did, int paramType, int result);

		void callBackPPPPMsgNotifyData(String did, int type, int param);
	}

	private static AlarmInterface alarmInterface;

	public static void setAlarmInterface(AlarmInterface ai) {
		alarmInterface = ai;
	}

	public interface AlarmInterface {
		void callBackAlarmParams(String did, int motion_armed,
				int motion_sensitivity, int input_armed, int ioin_level,
				int iolinkage, int ioout_level, int alermpresetsit, int mail,
				int snapshot, int record, int upload_interval,
				int schedule_enable, int enable_alarm_audio,
				int schedule_sun_0, int schedule_sun_1, int schedule_sun_2,
				int schedule_mon_0, int schedule_mon_1, int schedule_mon_2,
				int schedule_tue_0, int schedule_tue_1, int schedule_tue_2,
				int schedule_wed_0, int schedule_wed_1, int schedule_wed_2,
				int schedule_thu_0, int schedule_thu_1, int schedule_thu_2,
				int schedule_fri_0, int schedule_fri_1, int schedule_fri_2,
				int schedule_sat_0, int schedule_sat_1, int schedule_sat_2);

		void callBackSetSystemParamsResult(String did, int paramType, int result);
	}

	private static DateTimeInterface dateTimeInterface;

	public static void setDateTimeInterface(DateTimeInterface di) {
		dateTimeInterface = di;
	}

	public interface DateTimeInterface {
		void callBackDatetimeParams(String did, int now, int tz,
				int ntp_enable, String ntp_svr, int xia_ling_shi_flag_status);

		void callBackSetSystemParamsResult(String did, int paramType, int result);
	}

	private static MailInterface mailInterface;

	public static void setMailInterface(MailInterface mi) {
		mailInterface = mi;
	}

	public interface MailInterface {
		void callBackMailParams(String did, String svr, int port, String user,
				String pwd, int ssl, String sender, String receiver1,
				String receiver2, String receiver3, String receiver4);

		void callBackSetSystemParamsResult(String did, int paramType, int result);
	}

	private static FtpInterface ftpInterface;

	public static void setFtpInterface(FtpInterface fi) {
		ftpInterface = fi;
	}

	public interface FtpInterface {
		void callBackFtpParams(String did, String svr_ftp, String user,
				String pwd, String dir, int port, int mode, int upload_interval);

		void callBackSetSystemParamsResult(String did, int paramType, int result);
	}

	private static SDCardInterface sCardInterface;

	public static void setSDCardInterface(SDCardInterface si) {
		sCardInterface = si;
	}

	public interface SDCardInterface {
		void callBackRecordSchParams(String did, int record_cover_enable,
				int record_timer, int record_size, int record_time_enable,
				int record_schedule_sun_0, int record_schedule_sun_1,
				int record_schedule_sun_2, int record_schedule_mon_0,
				int record_schedule_mon_1, int record_schedule_mon_2,
				int record_schedule_tue_0, int record_schedule_tue_1,
				int record_schedule_tue_2, int record_schedule_wed_0,
				int record_schedule_wed_1, int record_schedule_wed_2,
				int record_schedule_thu_0, int record_schedule_thu_1,
				int record_schedule_thu_2, int record_schedule_fri_0,
				int record_schedule_fri_1, int record_schedule_fri_2,
				int record_schedule_sat_0, int record_schedule_sat_1,
				int record_schedule_sat_2, int record_sd_status, int sdtotal,
				int sdfree);

		void callBackSetSystemParamsResult(String did, int paramType, int result);;
	}

	public static PlayInterface playInterface;

	public static void setPlayInterface(PlayInterface pi) {
		playInterface = pi;
	}

	public interface PlayInterface {
		void callBackCameraParamNotify(String did, int resolution,
				int brightness, int contrast, int hue, int saturation,
				int flip, int fram,int ircut);

		void callBaceVideoData(String did, byte[] videobuf, int h264Data,
				int len, int bufsize,int width, int height, int time);

		void callBackMessageNotify(String did, int msgType, int param);

		void callBackAudioData(byte[] pcm, int len);

		void callBackH264Data(String did, byte[] h264, int type, int size);
	}

	private static PlayBackTFInterface playBackTFInterface;

	public static void setPlayBackTFInterface(PlayBackTFInterface pbtfi) {
		playBackTFInterface = pbtfi;
	}

	public interface PlayBackTFInterface {
		void callBackRecordFileSearchResult(String did, String filename,
				int size, int nPageCount, int bEnd);
	}

	private static PlayBackInterface playBackInterface;

	public static void setPlayBackInterface(PlayBackInterface pbi) {
		playBackInterface = pbi;
	}

	public interface PlayBackInterface {
		void callBackPlaybackVideoData(byte[] videobuf, int h264Data, int len,
				int width, int height, int time);

		void callBackPlaybackAudioData(byte[] pcm, int len);
	}

	private static AddCameraInterface addCameraInterface;

	public static void setAddCameraInterface(AddCameraInterface aci) {
		addCameraInterface = aci;
	}

	public interface AddCameraInterface {
		void callBackSearchResultData(int cameraType, String strMac,
				String strName, String strDeviceID, String strIpAddr, int port);
	}

	public static IPPlayInterface ipPlayInterface;

	public static void setIpPlayInterface(IPPlayInterface pi) {
		ipPlayInterface = pi;
	}

	public interface IPPlayInterface {
		void callBackCameraParamNotify(String did, int resolution,
				int brightness, int contrast, int hue, int saturation, int flip);

		void callBaceVideoData(String did, byte[] videobuf, int h264Data,
				int len, int width, int height);

		void callBackMessageNotify(String did, int msgType, int param);

		void callBackAudioData(byte[] pcm, int len);

		void callBackH264Data(byte[] h264, int type, int size);
	}

	private static UserTimeInterface userTimeInterface;

	public static void setUserTimeInterface(UserTimeInterface pi) {
		userTimeInterface = pi;
	}

	public interface UserTimeInterface {
		void callBackUserTime(String did, int user_schedule_enable,
				int user_schedule_mode, int user_schedule_startday,
				int user_schedule_endday, int user_schedule_starttime_min,
				int user_schedule_endtime_min);
	}

	public static void freeNativeCallback()
	{
		//NativeCaller.Free();
	}
	
	
	
}
