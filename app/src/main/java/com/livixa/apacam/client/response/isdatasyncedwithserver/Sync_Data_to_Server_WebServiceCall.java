package com.livixa.apacam.client.response.isdatasyncedwithserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.mrengineer13.snackbar.SnackBar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.login.LoginResponse;
import com.livixa.apacam.client.response.tariff_energy.ShTariffResult;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.dbmodel.Camera_Model;
import com.livixa.apacam.dbmodel.Manage_DB_Model;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model;
import com.livixa.apacam.dbmodel.User_Camera_Model_Relation;
import com.livixa.apacam.dbmodel.User_Room_Model;
import com.livixa.apacam.services.Sync_Service;

import com.livixa.client.IpcamClientActivity;
import retrofit2.Call;
import retrofit2.http.Header;

public class Sync_Data_to_Server_WebServiceCall extends AsyncTask<Void, Void, Boolean>
		implements ServerConnectListener {

	Context cntxt;
	Map<String, String> map;
	HashMap<String, String> map2;

	ArrayList<ShCamera> camList = new ArrayList<ShCamera>();
	ArrayList<ShUserCamera> camUserList = new ArrayList<ShUserCamera>();
	
	ArrayList<Object>  allModelsSendForCall=new ArrayList<Object>();
	

	boolean isSyncedRequired = false;

	Data_Status data_Status;
	
	public static boolean isClickedByUser=false;
	
	
	

	public Sync_Data_to_Server_WebServiceCall(Context cntxt) {
		this.cntxt = cntxt;
	}

	@Override
	public void onSuccess(ServerResponse response) {

		if (Sync_Service.stopWebserviceCalling) {
			return;
		}

		if (response.getRequestCode() == ServerCodes.ServerRequestCodes.SYNCED_DATA_REQUEST_CODE) {

			DataSynced_With_Server dataSynced_With_Server = (DataSynced_With_Server) response;

			if (dataSynced_With_Server.getShMeta().getShErrorCode().equalsIgnoreCase("0")) {
				
				
				
				
				UpdateDb  task=new UpdateDb(dataSynced_With_Server);
				
				task.execute();
				
				//check if user login or subuser login
				
				/*if(AppPreference.getValue(cntxt,AppKeys.KEY_IS_SUB_USER)!=null && AppPreference.getValue(cntxt,AppKeys.KEY_IS_SUB_USER).equals("1"))
				{
					
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
				}
				else
				{
					
					
					if(allModelsSendForCall!=null && allModelsSendForCall.size() > 0)
					{
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

						
					}
					
					
					
					
					
					
					
					allModelsSendForCall.clear();
					if (data_Status != null)
						data_Status.onRecieveData(true);
					
					
				}
				
				*/
                     
				
				

			} else {
				
				
				if (data_Status != null)
					data_Status.onRecieveData(false);
                //loginResponse.getShMeta().getShErrorCode()
				if(isClickedByUser)
				onFailure(dataSynced_With_Server.getShMeta().getShMessage());
				
				
			}

		} else if (response.getRequestCode() == ServerCodes.ServerRequestCodes.GET_SYNCED_DATA_REQUEST_CODE) {
			DataSynced_With_Server dataSynced_With_Server = (DataSynced_With_Server) response;

			if (dataSynced_With_Server.getShMeta().getShErrorCode().equalsIgnoreCase("0")) {

			} else {
				
				if (data_Status != null)
					data_Status.onRecieveData(false);
				
				if(isClickedByUser)
				onFailure(dataSynced_With_Server.getShMeta().getShMessage());
				
				
			}

		}

	}

	@Override
	public void onFailure(ServerResponse response) {

		if (data_Status != null)
			data_Status.onRecieveData(false);
		
		try
		{
		if(isClickedByUser)
		onFailure(response.getMessage());
		}catch(Exception ex){}
		
		
		
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
		
		
		if (moodsList != null && moodsList.size() > 0) {
			isSyncedRequired = true;
			allModelsSendForCall.add(moodsList);
		}
			

		return isSyncedRequired;
	}

	@SuppressWarnings("unchecked")
	public void SynchDatatoServer() {

		ApiService service = KisafaApplication.getRestClient().getApiService();

		try {

			map = createMap(AppPreference.getValue(cntxt, AppKeys.KEY_SESSION));

		} catch (Exception ex) {
			ex.toString();
		}

		Call<DataSynced_With_Server> call = service.upload2server(map);

		call.enqueue(new RestCallback<DataSynced_With_Server>(this,
				ServerCodes.ServerRequestCodes.SYNCED_DATA_REQUEST_CODE, cntxt));
	}

	public void getSyncDataFromServer() {
		map2 = new HashMap<String, String>();

		map2.put("session", AppPreference.getValue(cntxt, AppKeys.KEY_SESSION));
		map2.put(AppKeys.KEY_CURRENT_LANGUAGE, AppPreference.getValue(cntxt, AppKeys.KEY_CURRENT_LANGUAGE));
		ApiService service = KisafaApplication.getRestClient().getApiService();
		Call<DataSynced_With_Server> call = service.get_sync_data_from_server(map2);
		call.enqueue(new RestCallback<DataSynced_With_Server>(this,
				ServerCodes.ServerRequestCodes.GET_SYNCED_DATA_REQUEST_CODE, cntxt));
	}

	public void onFailure(String retrofitError) {

		new SnackBar.Builder((Activity) cntxt).withMessage(retrofitError).withDuration(SnackBar.MED_SNACK).show();
	}

	@Override
	protected Boolean doInBackground(Void... params) {

		SynchDatatoServer();
		return true;
	}

	@SuppressWarnings("unchecked")
	public Map createMap(String session) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("session", session);

		List<Switch_Model> switchList = null;
		List<Room_Model> roomsList = null;
		List<Camera_Model> camList = null;
		List<User_Camera_Model> camUserList = null;
		List<User_Room_Model> userRoomsList =null;
		
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		
		
		if (userRoomsList != null)
			for (int i = 0; i < userRoomsList.size(); i++) {
				try {
					map.put("rooms[" + i + "][user_room_id]", userRoomsList.get(i).user_room_id);
					map.put("rooms[" + i + "][room_id]", userRoomsList.get(i).room_id);
					map.put("rooms[" + i + "][user_id]", userRoomsList.get(i).userId);
					map.put("rooms[" + i + "][model_status]", userRoomsList.get(i).modelStatus);

				} catch (Exception e) {
					// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
					e.printStackTrace();
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

				}
			}
		
		
		
		

		return map;

	}

	public interface Data_Status {
		public void onRecieveData(boolean isSuccessfull);
	}

	public void setDataLiostner(Data_Status data_Status) {
		this.data_Status = data_Status;
	}
	
	
	public static void  SetClickedByUser(boolean _isClickedByUser)
	{
		
		isClickedByUser=_isClickedByUser;
	}
	
	
	class UpdateDb  extends  AsyncTask<Void, Void, Boolean>
	{

		private DataSynced_With_Server dataSynced_With_Server;
		
		
		protected void onPostExecute(Boolean result) 
		{
			
			if (data_Status != null)
				data_Status.onRecieveData(true);
			
		};
		
		UpdateDb(DataSynced_With_Server dataSynced_With_Server)
		{
			
			this.dataSynced_With_Server=dataSynced_With_Server;
		}
		@Override
		protected Boolean doInBackground(Void... params) {
			
			
			
			if (AppPreference.getValue(cntxt, AppKeys.KEY_IS_SUB_USER) != null
					&& AppPreference.getValue(cntxt, AppKeys.KEY_IS_SUB_USER).equals("1")) {
				
				
				

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

					allModelsSendForCall.clear();
					
					
					
					

					Upload_Room_Pictures_On_Server upload_Room_Pictures_On_Server=new Upload_Room_Pictures_On_Server(cntxt);
					
					upload_Room_Pictures_On_Server.syncImages();
					
					
					Upload_Moods_Pictures_On_Server upload_Moods_Pictures_On_Server=new Upload_Moods_Pictures_On_Server(cntxt);
					
					upload_Moods_Pictures_On_Server.syncImages();
					
				}
				
				
				
				dataSynced_With_Server=null;
				
			}
		
		     return true;
		}
	}
}
