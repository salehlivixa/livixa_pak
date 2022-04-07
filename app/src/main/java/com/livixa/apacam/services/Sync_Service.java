package com.livixa.apacam.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.activeandroid.query.Delete;
import com.github.mrengineer13.snackbar.DialogueMessage;
import com.github.mrengineer13.snackbar.SnackBar;
import com.github.mrengineer13.snackbar.SnackBar.OnMessageClickListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import static java.util.concurrent.TimeUnit.SECONDS;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.isdatasyncedwithserver.DataSynced_With_Server;
import com.livixa.apacam.client.response.isdatasyncedwithserver.ShCamera;
import com.livixa.apacam.client.response.isdatasyncedwithserver.ShResult;
import com.livixa.apacam.client.response.isdatasyncedwithserver.ShUserCamera;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Camera;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Mood;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Room;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Switch;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_UserCamera;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_User_Room;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Upload_Moods_Pictures_On_Server;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Upload_Room_Pictures_On_Server;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.client.response.tariff_energy.ShTariffResult;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.customprogressbar.Dialogue_Message;
import com.livixa.apacam.dbmodel.Camera_Model;
import com.livixa.apacam.dbmodel.ESP_Result_Model;
import com.livixa.apacam.dbmodel.Manage_DB_Model;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.dbmodel.Tariff_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model_Relation;
import com.livixa.apacam.dbmodel.User_Room_Model;
import com.livixa.client.R;
import object.p2pipcam.system.SystemValue;
import object.p2pipcam.utils.DataBaseHelper;
import retrofit2.Call;

public class Sync_Service extends Service implements ServerConnectListener {

	
	
	private ArrayList<Object> allModelsSendForCall = new ArrayList<Object>();
	

	private boolean isSyncedRequired = false;

	private DataBaseHelper helper;

	public static boolean stopWebserviceCalling = false;

	private int EVEN_CODE = 2222;

	private int ODD_CODE = 1111;

	private int SYNCED_DATA_REQUEST_CODE = ODD_CODE;
	
	
	
	private MyTimerTask myTimerTask=null;
	 
	
	
	private  ScheduledExecutorService scheduler;
	
	
	public static  boolean   isAlertShwoing=false;
	
	
	//private   View warningView;
	
	
	private SnackBar  tempSnackBar;
	

	public static void lockService() {
		Sync_Service.stopWebserviceCalling = true;
	}

	public static void unlockService() {
		Sync_Service.stopWebserviceCalling = false;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		stopWebserviceCalling = false;
		helper = DataBaseHelper.getInstance(Sync_Service.this);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		final Thread t = new Thread(new Runnable() {
			@Override
			public void run() {

				startJob();

			}
		});
		t.start();
		return START_NOT_STICKY;
	}

	private void startJob() {
		// do job here

		/*if (!stopWebserviceCalling) {
			isSyncedRequired();
			SynchDatatoServer();
		}*/

		// job completed. Rest for 5 second before doing another one
		/*try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

		// do job again
		//startJob();
		
		 myTimerTask=new MyTimerTask();
		
	     scheduler =Executors.newScheduledThreadPool(1);
	     
	     
	     scheduler.scheduleWithFixedDelay(myTimerTask, 5, 5, SECONDS);
		
		/*timer=new Timer();
		
		myTimerTask=new MyTimerTask();
		
		timer.schedul(myTimerTask, 5000, 5000);*/
		
		
	}

	@Override
	public void onSuccess(ServerResponse response) {

		if (stopWebserviceCalling) {
			removeWaringMessage();
			stopSelf();
			return;
		}
		
		
		if (response!=null && response.getRequestCode() == SYNCED_DATA_REQUEST_CODE) {

			DataSynced_With_Server dataSynced_With_Server = (DataSynced_With_Server) response;

			if (dataSynced_With_Server.getShMeta().getShErrorCode().equalsIgnoreCase("0")) {
				
				
				
				UpdateDb  task=new UpdateDb(dataSynced_With_Server);
				
				task.execute();
				

				/*if (AppPreference.getValue(Sync_Service.this, AppKeys.KEY_IS_SUB_USER) != null
						
						&& AppPreference.getValue(Sync_Service.this, AppKeys.KEY_IS_SUB_USER).equals("1")) {

					ShResult obj = dataSynced_With_Server.getShResult();

					final ArrayList<Sh_Camera> listcamera = obj.getShCameras();

					final ArrayList<Sh_UserCamera> listusercamera = obj.getShUserCamera();
					
					final ArrayList<ShTariffResult> listtariff=obj.getSHTariffList();
					
					final ArrayList<Sh_Mood> moodsList=obj.getSHMoodList();
					
					
					
					new Runnable() {
						public void run() {
							
							
							if (listusercamera != null)
								for (int i = 0; i < listusercamera.size(); i++) {
									Manage_DB_Model.update_User_CameraModelInDB(listusercamera.get(i));

								}
							
						}
					}.run();

					
					new Runnable() {
						public void run() {
							
							
							if (listcamera != null)
								for (int i = 0; i < listcamera.size(); i++) {
									Manage_DB_Model.update_CameraModelInDB(listcamera.get(i));

								}
							
						}
					}.run();
					

					

					final ArrayList<Sh_Room> listRoom = obj.getShRoomList();

					final ArrayList<Sh_Switch> listSwitch = obj.getShSwitchList();
					
					final ArrayList<Sh_User_Room> listUserRoom = obj.getShUserRoomList();

					
					
					
					new Runnable() {
						public void run() {
							
							
							if (listRoom != null)
								for (int i = 0; i < listRoom.size(); i++) {
									Manage_DB_Model.update_OR_Create_RoomModelInDB(listRoom.get(i),false);

								}
							
						}
					}.run();
					
					
					new Runnable() {
						public void run() {
							
							
							if (listUserRoom != null)
								for (int i = 0; i < listUserRoom.size(); i++) {
									Manage_DB_Model.update_OR_Create_UserRoomModelInDB(listUserRoom.get(i));

								}
						}
					}.run();
					
					
					new Runnable() {
						public void run() {
							
							
							if (listSwitch != null)
								for (int i = 0; i < listSwitch.size(); i++) {
									Manage_DB_Model.update_OR_Create_SwitchodelInDB(listSwitch.get(i));

								}
						}
					}.run();
					

					new Runnable() {
						public void run() {
							
							if (listtariff != null)
								for (int i = 0; i < listtariff.size(); i++) {
									Manage_DB_Model.update_OR_Create_Tariff_ModelInDB(listtariff.get(i));

								}
						}
					}.run();
					
					
					new Runnable() {
						public void run() {
							if (moodsList != null)
								for (int i = 0; i < moodsList.size(); i++) {
									Manage_DB_Model.update_OR_Create_MoodsInDB(moodsList.get(i),false);

								}
						}
					}.run();
					
					
					
					
					

					allModelsSendForCall.clear();

				} else {

					if (allModelsSendForCall != null && allModelsSendForCall.size() > 0) {

						ShResult obj = dataSynced_With_Server.getShResult();

						ArrayList<Sh_Camera> listcamera = obj.getShCameras();

						ArrayList<Sh_UserCamera> listusercamera = obj.getShUserCamera();
						
						ArrayList<ShTariffResult> listtariff=obj.getSHTariffList();
						
						ArrayList<Sh_Mood> moodsList=obj.getSHMoodList();

						if (listusercamera != null)
							for (int i = 0; i < listusercamera.size(); i++) {
								Manage_DB_Model.update_User_CameraModelInDB(listusercamera.get(i));

							}

						if (listcamera != null)
							for (int i = 0; i < listcamera.size(); i++) {
								Manage_DB_Model.update_CameraModelInDB(listcamera.get(i));

							}

						ArrayList<Sh_Room> listRoom = obj.getShRoomList();

						ArrayList<Sh_Switch> listSwitch = obj.getShSwitchList();
						
						ArrayList<Sh_User_Room> listUserRoom = obj.getShUserRoomList();

						if (listRoom != null)
						{
							for (int i = 0; i < listRoom.size(); i++) {
								Manage_DB_Model.update_OR_Create_RoomModelInDB(listRoom.get(i),false);

							}
							
							
							//
							
							
						
						}
						
						
						
						
						
						if (listUserRoom != null)
							for (int i = 0; i < listUserRoom.size(); i++) {
								Manage_DB_Model.update_OR_Create_UserRoomModelInDB(listUserRoom.get(i));

							}

						if (listSwitch != null)
							for (int i = 0; i < listSwitch.size(); i++) {
								Manage_DB_Model.update_OR_Create_SwitchodelInDB(listSwitch.get(i));

							}
						
						if (listtariff != null)
							for (int i = 0; i < listtariff.size(); i++) {
								Manage_DB_Model.update_OR_Create_Tariff_ModelInDB(listtariff.get(i));

							}
						
						
						if (moodsList != null)
							for (int i = 0; i < moodsList.size(); i++) {
								Manage_DB_Model.update_OR_Create_MoodsInDB(moodsList.get(i),false);

							}

						allModelsSendForCall.clear();
						
						
						
						

						Upload_Room_Pictures_On_Server upload_Room_Pictures_On_Server=new Upload_Room_Pictures_On_Server(Sync_Service.this);
						
						upload_Room_Pictures_On_Server.syncImages();
						
						
						Upload_Moods_Pictures_On_Server upload_Moods_Pictures_On_Server=new Upload_Moods_Pictures_On_Server(Sync_Service.this);
						
						upload_Moods_Pictures_On_Server.syncImages();
						
					}

				}*/

			} else {

				 onFailure(dataSynced_With_Server.getShMeta().getShMessage());
			}

		} else if (response.getRequestCode() == ServerCodes.ServerRequestCodes.GET_SYNCED_DATA_REQUEST_CODE) {
			DataSynced_With_Server dataSynced_With_Server = (DataSynced_With_Server) response;

			if (dataSynced_With_Server.getShMeta().getShErrorCode().equalsIgnoreCase("0")) {

			} else {
				try {

					// onFailure(dataSynced_With_Server.getShMeta().getShErrorCode());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}

	}

	@Override
	public void onFailure(ServerResponse response) {

		try {
			DataSynced_With_Server dataSynced_With_Server = (DataSynced_With_Server) response;
			// onFailure(dataSynced_With_Server.getShMeta().getShMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	
	
	static String errorMessage="";
	public void onFailure(String retrofitError) {

		/*if(activityToDisPlayErrorMessage!=null && !activityToDisPlayErrorMessage.isDestroyed())
		{
			new SnackBar.Builder((Activity) activityToDisPlayErrorMessage).withMessage(retrofitError)
			.withDuration(SnackBar.SHORT_SNACK).show();
		}*/
		
		errorMessage=retrofitError;
		
		
		
		if(!isAlertShwoing)
		{
		
			
			//showWarning(retrofitError);
			
			
			if(activityToDisPlayErrorMessage!=null && !activityToDisPlayErrorMessage.isDestroyed())
			{
				short x=-1;
				new SnackBar.Builder((Activity) activityToDisPlayErrorMessage).withMessage(retrofitError).withDuration(x).show__();
			}
			
		  isAlertShwoing=true;
		}
		
		
	}
	
	

	@SuppressWarnings("unchecked")
	public void SynchDatatoServer() {

		ApiService service = KisafaApplication.getRestClient().getApiService();

		Map map=null;
		try {

			map = createMap(AppPreference.getValue(Sync_Service.this, AppKeys.KEY_SESSION));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Call<DataSynced_With_Server> call = service.upload2server(map);

		call.enqueue(new RestCallback<DataSynced_With_Server>(this, SYNCED_DATA_REQUEST_CODE, Sync_Service.this));
	}

	@SuppressWarnings("unchecked")
	public Map createMap(String session) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("session", session);
		try
		{
		map.put(AppKeys.KEY_CURRENT_LANGUAGE, AppPreference.getValue(this, AppKeys.KEY_CURRENT_LANGUAGE));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		List<Switch_Model> switchList = null;
		List<Room_Model> roomsList = null;
		List<Camera_Model> camList = null;
		List<User_Camera_Model> camUserList = null;
		List<User_Room_Model> userRoomsList =null;
		List<Tariff_Model> tariffModelList =null;
		List<Mood_Model> moodsList=null;

		for (int i = 0; i < allModelsSendForCall.size(); i++) {

			ArrayList<Object> obj = (ArrayList<Object>) allModelsSendForCall.get(i);

			if (obj.get(0).getClass().getName().equals(Switch_Model.class.getName())) {

				switchList = (List<Switch_Model>) allModelsSendForCall.get(i);
			} 
			else if (obj.get(0).getClass().getName().equals(User_Room_Model.class.getName())) {
				userRoomsList = (List<User_Room_Model>) allModelsSendForCall.get(i);
			} 
			
			else if (obj.get(0).getClass().getName().equals(Room_Model.class.getName())) {
				roomsList = (List<Room_Model>) allModelsSendForCall.get(i);
			}
			else if (obj.get(0).getClass().getName().equals(Camera_Model.class.getName())) {

				camList = (List<Camera_Model>) allModelsSendForCall.get(i);
			} 
			else if (obj.get(0).getClass().getName().equals(User_Camera_Model.class.getName())) {
				camUserList = (List<User_Camera_Model>) allModelsSendForCall.get(i);
			}
			else if (obj.get(0).getClass().getName().equals(Tariff_Model.class.getName())) {
				tariffModelList = (List<Tariff_Model>) allModelsSendForCall.get(i);
			}
			else if(  obj.get(0).getClass().getName().equals(Mood_Model.class.getName())   )
			{
				moodsList = (List<Mood_Model>) allModelsSendForCall.get(i);
			}
			
		}

		if (roomsList != null)
			for (int i = 0; i < roomsList.size(); i++) {
				try {

					map.put("rooms[" + i + "][room_id]", roomsList.get(i).room_id);
					map.put("rooms[" + i + "][picture_url]", roomsList.get(i).pictureURL);
					map.put("rooms[" + i + "][title]", roomsList.get(i).title);
					map.put("rooms[" + i + "][model_status]", roomsList.get(i).modelStatus);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		
		
		if (userRoomsList != null)
			for (int i = 0; i < userRoomsList.size(); i++) {
				try {
					map.put("user_rooms[" + i + "][user_room_id]", userRoomsList.get(i).user_room_id);
					map.put("user_rooms[" + i + "][room_id]", userRoomsList.get(i).room_id);
					map.put("user_rooms[" + i + "][user_id]", userRoomsList.get(i).userId);
					map.put("user_rooms[" + i + "][model_status]", userRoomsList.get(i).modelStatus);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		
		

		if (camList != null)
			for (int i = 0; i < camList.size(); i++) {
				try {
					map.put("cameras[" + i + "][alarm]", camList.get(i).alarm);
					map.put("cameras[" + i + "][authority]", camList.get(i).authority);
					map.put("cameras[" + i + "][camera_id]", camList.get(i).cameraId);
					map.put("cameras[" + i + "][did]", camList.get(i).did);
					map.put("cameras[" + i + "][is_add]", camList.get(i).isAdd);
					map.put("cameras[" + i + "][model]", camList.get(i).model);
					map.put("cameras[" + i + "][model_status]", camList.get(i).modelStatus);
					map.put("cameras[" + i + "][name]", camList.get(i).name);
					map.put("cameras[" + i + "][password]", camList.get(i).password);
					map.put("cameras[" + i + "][pppp_mode]", camList.get(i).ppppMood);
					map.put("cameras[" + i + "][pppp_status]", camList.get(i).ppppStatus);
					map.put("cameras[" + i + "][user]", camList.get(i).user);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		if (camUserList != null)
			for (int i = 0; i < camUserList.size(); i++) {
				try {
					map.put("user_cameras[" + i + "][camera_id]", camUserList.get(i).cameraId);
					map.put("user_cameras[" + i + "][is_access_camera_settings]",
							camUserList.get(i).isAccessCameraSetting);
					map.put("user_cameras[" + i + "][model_status]", camUserList.get(i).modelStatus);
					map.put("user_cameras[" + i + "][user_camera_id]", camUserList.get(i).userCameraId);
					map.put("user_cameras[" + i + "][user_id]", camUserList.get(i).userId);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		if (switchList != null)
			for (int i = 0; i < switchList.size(); i++) {
				try {

					map.put("switches[" + i + "][switch_id]", switchList.get(i).switch_id);
					map.put("switches[" + i + "][title]", switchList.get(i).title);
					map.put("switches[" + i + "][type]", switchList.get(i).type);

					map.put("switches[" + i + "][mac_address]", switchList.get(i).mac_address);
					/*map.put("switches[" + i + "][ip_address]", switchList.get(i).ip_address);
					map.put("switches[" + i + "][port]", switchList.get(i).port);

					map.put("switches[" + i + "][is_connected]", switchList.get(i).is_connected);
					map.put("switches[" + i + "][is_activated]", switchList.get(i).is_activated);*/
					map.put("switches[" + i + "][room_id]", switchList.get(i).room_id);

					map.put("switches[" + i + "][user_id]", switchList.get(i).user_id);
					map.put("switches[" + i + "][model_status]", switchList.get(i).model_status);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		
		
		
		if (tariffModelList != null)
			for (int i = 0; i < tariffModelList.size(); i++) {
				try {
					map.put("tariffs[" + i + "][tariff_id]", tariffModelList.get(i).tariff_id);
					map.put("tariffs[" + i + "][lower_limit]",tariffModelList.get(i).lower_limit);
					map.put("tariffs[" + i + "][upper_limit]", tariffModelList.get(i).upper_limit);
					map.put("tariffs[" + i + "][price]", tariffModelList.get(i).price);
					map.put("tariffs[" + i + "][user_id]", tariffModelList.get(i).user_id);
					map.put("tariffs[" + i + "][model_status]", tariffModelList.get(i).model_status);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		
		
		if (moodsList != null)
			for (int i = 0; i < moodsList.size(); i++) {
				try {
					map.put("moods[" + i + "][mood_id]", moodsList.get(i).moodId);
					map.put("moods[" + i + "][title]",moodsList.get(i).title);
					map.put("moods[" + i + "][mood_identifier]", moodsList.get(i).moodIdentifer+"");
					map.put("moods[" + i + "][switch_id]", moodsList.get(i).switchId);
					map.put("moods[" + i + "][model_status]", moodsList.get(i).modelStatus);
					map.put("moods[" + i + "][picture_url]", moodsList.get(i).pictureURL);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		
		
		
		
		
		

		return map;

	}

	public boolean isSyncedRequired() {

		isSyncedRequired = false;

		// ------------------------Fetch Data From
				// DataBase-----------------------------------------------

				User_Camera_Model_Relation user_Camera_Model_Relation = Manage_DB_Model
						.showCameraModelAndUserModelRelationInDB();

				List<Camera_Model> tempCameraModel = user_Camera_Model_Relation.camera_Model;

				List<User_Camera_Model> tempUserCameraModel = user_Camera_Model_Relation.user_Camera_Model;

				List<Switch_Model> switchList = Manage_DB_Model.fetchAllNonSyncedSwitchesFromDb();

				List<Room_Model> roomsList = Manage_DB_Model.fetchAllNonSyncedRoomsFromDb();
				
				List<User_Room_Model> userRoomsList = Manage_DB_Model.fetchAllNonSyncedUserRoomsFromDb();
				
				
				List<Tariff_Model> tariffModeList = Manage_DB_Model.fetchAllNonSyncedTariffsFromDb();
				
				List<Mood_Model> moodsList	=Manage_DB_Model.fetchAllNonSyncedMoodsFromDb();

				// ------------------------------------------------------------------------------------------------------

				allModelsSendForCall.clear();

				if (tempCameraModel != null && tempCameraModel.size() > 0) {
					isSyncedRequired = true;
					allModelsSendForCall.add(tempCameraModel);
				}
				if (tempUserCameraModel != null && tempUserCameraModel.size() > 0) {
					isSyncedRequired = true;
					allModelsSendForCall.add(tempUserCameraModel);
				}

				if (roomsList != null && roomsList.size() > 0) {
					isSyncedRequired = true;
					allModelsSendForCall.add(roomsList);
				}

				if (switchList != null && switchList.size() > 0) {
					isSyncedRequired = true;
					allModelsSendForCall.add(switchList);
				}

				if (userRoomsList != null && userRoomsList.size() > 0) {
					isSyncedRequired = true;
					allModelsSendForCall.add(userRoomsList);
				}
				
				if (tariffModeList != null && tariffModeList.size() > 0) {
					isSyncedRequired = true;
					allModelsSendForCall.add(tariffModeList);
				}
				if (moodsList != null && moodsList.size() > 0) {
					isSyncedRequired = true;
					allModelsSendForCall.add(moodsList);
				}
				
				


		if (isSyncedRequired)
			SYNCED_DATA_REQUEST_CODE = EVEN_CODE;
		else
			SYNCED_DATA_REQUEST_CODE = ODD_CODE;

		return isSyncedRequired;
	}
	
	
	
	
	class MyTimerTask extends TimerTask {
		 
		
		 
		  @Override
		  public void run() {
			  
			  
			  if (!stopWebserviceCalling) {
				  
				 
					isSyncedRequired();
					SynchDatatoServer();
				}
			  else
			  {
				  removeWaringMessage();
			  }

			
			}
		  
	}
	
	
	class UpdateDb  extends  AsyncTask<Void, Void, Boolean>
	{

		private DataSynced_With_Server dataSynced_With_Server;
		
		
		protected void onPostExecute(Boolean result) 
		{
			
			
			 /*try
			  {
			    
			    Toast.makeText(Sync_Service.this, "abc", Toast.LENGTH_SHORT).show();
			    
			  }catch(Exception ex){}*/
			
			
		};
		
		UpdateDb(DataSynced_With_Server dataSynced_With_Server)
		{
			
			this.dataSynced_With_Server=dataSynced_With_Server;
		}
		@Override
		protected Boolean doInBackground(Void... params) {
			
			
			
			if (AppPreference.getValue(Sync_Service.this, AppKeys.KEY_IS_SUB_USER) != null
					&& AppPreference.getValue(Sync_Service.this, AppKeys.KEY_IS_SUB_USER).equals("1")) {
				
				
				
				
				
				
				/*try
				{
					new Delete().from(SUB_USER_DB.class).execute();
					new Delete().from(Camera_Model.class).execute();
					new Delete().from(User_Camera_Model.class).execute();
					new Delete().from(Switch_Model.class).execute();
					new Delete().from(Room_Model.class).execute();
					new Delete().from(User_Room_Model.class).execute();
					new Delete().from(Mood_Model.class).execute();
					new Delete().from(Tariff_Model.class).execute();
					
					
					SystemValue.arrayList.clear();
					helper.deleteAllCameras();
					
				
				}catch(Exception ex)
				{
					
				}
				*/
				
				

				ShResult obj = dataSynced_With_Server.getShResult();

				final ArrayList<Sh_Camera> listcamera = obj.getShCameras();

				final ArrayList<Sh_UserCamera> listusercamera = obj.getShUserCamera();
				
				final ArrayList<ShTariffResult> listtariff=obj.getSHTariffList();
				
				final ArrayList<Sh_Mood> moodsList=obj.getSHMoodList();
				
				
				
				
						
						
						if (listusercamera != null)
							for (int i = 0; i < listusercamera.size(); i++) {
								Manage_DB_Model.update_User_CameraModelInDB(listusercamera.get(i));

							}
					

				
						
						
						if (listcamera != null)
							for (int i = 0; i < listcamera.size(); i++) {
								Manage_DB_Model.update_CameraModelInDB(listcamera.get(i));

							}
					

				

				final ArrayList<Sh_Room> listRoom = obj.getShRoomList();

				final ArrayList<Sh_Switch> listSwitch = obj.getShSwitchList();
				
				final ArrayList<Sh_User_Room> listUserRoom = obj.getShUserRoomList();

				
				
				
						
						if (listRoom != null)
							for (int i = 0; i < listRoom.size(); i++) {
								Manage_DB_Model.update_OR_Create_RoomModelInDB(listRoom.get(i),false);

							}
				
						
						if (listUserRoom != null)
							for (int i = 0; i < listUserRoom.size(); i++) {
								Manage_DB_Model.update_OR_Create_UserRoomModelInDB(listUserRoom.get(i));

							}
				
				
						
						
						if (listSwitch != null)
							for (int i = 0; i < listSwitch.size(); i++) {
								Manage_DB_Model.update_OR_Create_SwitchodelInDB(listSwitch.get(i));

							}
				
				
						
						if (listtariff != null)
							for (int i = 0; i < listtariff.size(); i++) {
								Manage_DB_Model.update_OR_Create_Tariff_ModelInDB(listtariff.get(i));

							}
			
			
						if (moodsList != null)
							for (int i = 0; i < moodsList.size(); i++) {
								Manage_DB_Model.update_OR_Create_MoodsInDB(moodsList.get(i),false);

							}
					
				
				
				
				

				allModelsSendForCall.clear();

			} else {

				if (allModelsSendForCall != null && allModelsSendForCall.size() > 0) {

					ShResult obj = dataSynced_With_Server.getShResult();

					ArrayList<Sh_Camera> listcamera = obj.getShCameras();

					ArrayList<Sh_UserCamera> listusercamera = obj.getShUserCamera();
					
					ArrayList<ShTariffResult> listtariff=obj.getSHTariffList();
					
					ArrayList<Sh_Mood> moodsList=obj.getSHMoodList();

					if (listusercamera != null)
						for (int i = 0; i < listusercamera.size(); i++) {
							Manage_DB_Model.update_User_CameraModelInDBUserCase(listusercamera.get(i));

						}

					if (listcamera != null)
						for (int i = 0; i < listcamera.size(); i++) {
							Manage_DB_Model.update_CameraModelInDB_User_Case(listcamera.get(i));

						}

					ArrayList<Sh_Room> listRoom = obj.getShRoomList();

					ArrayList<Sh_Switch> listSwitch = obj.getShSwitchList();
					
					ArrayList<Sh_User_Room> listUserRoom = obj.getShUserRoomList();

					if (listRoom != null)
					{
						for (int i = 0; i < listRoom.size(); i++) {
							Manage_DB_Model.update_OR_Create_RoomModelInDB_User_Case(listRoom.get(i),false);

						}
						
						
						//
						
						
					
					}
					
					
					
					
					
					if (listUserRoom != null)
						for (int i = 0; i < listUserRoom.size(); i++) {
							Manage_DB_Model.update_OR_Create_UserRoomModelInDB_User_Case(listUserRoom.get(i));

						}

					if (listSwitch != null)
						for (int i = 0; i < listSwitch.size(); i++) {
							Manage_DB_Model.update_OR_Create_SwitchodelInDB_User_Case(listSwitch.get(i));

						}
					
					if (listtariff != null)
						for (int i = 0; i < listtariff.size(); i++) {
							Manage_DB_Model.update_OR_Create_Tariff_ModelInDB_User_Case(listtariff.get(i));

						}
					
					
					if (moodsList != null)
						for (int i = 0; i < moodsList.size(); i++) {
							Manage_DB_Model.update_OR_Create_MoodsInDB_User_Case(moodsList.get(i),false);

						}
					
					
					

					Upload_Room_Pictures_On_Server upload_Room_Pictures_On_Server=new Upload_Room_Pictures_On_Server(Sync_Service.this);
					
					upload_Room_Pictures_On_Server.syncImages();
					
					
					Upload_Moods_Pictures_On_Server upload_Moods_Pictures_On_Server=new Upload_Moods_Pictures_On_Server(Sync_Service.this);
					
					upload_Moods_Pictures_On_Server.syncImages();
					
				}
				
				
				
				dataSynced_With_Server=null;
				
			}
		
		     return false;
		}
	}
	

	public static Activity   activityToDisPlayErrorMessage;
	
	public static void setActivityToDisplayLogoutErrorThroughtTheApp(Activity activity)
	{
		activityToDisPlayErrorMessage=activity;
		
		try
		{
		if(activityToDisPlayErrorMessage!=null  && errorMessage.length()>0)
		{
			short x=-1;
			final SnackBar tempSnackBar=new SnackBar.Builder((Activity) activityToDisPlayErrorMessage).withOnClickListener(new OnMessageClickListener() {
				
				@Override
				public void onMessageClick(Parcelable token) {
					
					
					
				}
			}).withMessage(errorMessage).withDuration(x).show__();
			
		}
		}catch(Exception ex){}
	}
	
	public void showWarning(String message)
	{
		/*final WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

		LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

		 warningView= (View)inflater.inflate(R.layout.logout_warning, null);
		
		
		WindowManager.LayoutParams paramRemove = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
				PixelFormat.TRANSLUCENT);
		
		paramRemove.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

		
		
		windowManager.addView(warningView, paramRemove);
		
		
		TextView  messageView=(TextView) warningView.findViewById(R.id.snackMessage);
		
		messageView.setText(message);
		
		warningView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//isAlertShwoing=false;
			
				//windowManager.removeView(warningView);
				
			}
		});*/


	}
	
	public  void removeWaringMessage()
	{
		
		errorMessage="";
		isAlertShwoing=false;
		
	}
	
	public static  void removeWaringMessageOnLanguageChange()
	{
		
		errorMessage="";
		isAlertShwoing=false;
		
	}
	
	
}
