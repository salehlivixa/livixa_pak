package com.livixa.apacam.dbmodel;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.query.Delete;
import com.activeandroid.query.From;
import com.activeandroid.query.Select;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;
import com.livixa.apacam.client.activity.LoginActivity;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Camera;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Mood;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Room;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Switch;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_UserCamera;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_User_Room;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.client.response.tariff_energy.ShTariffResult;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.services.Sync_Service;
import object.p2pipcam.bean.CameraParamsBean;
import object.p2pipcam.utils.DataBaseHelper;

public class Manage_DB_Model {

	public static void createCameraModelAndUserModelRelationInDB(CameraParamsBean bean, String userId) {

		Camera_Model c_Model_test = new Select().from(Camera_Model.class).where("did = ?", bean.getDid().toUpperCase())
				.executeSingle();

		if (c_Model_test == null) {
			String userID = AppPreference.getValue(KisafaApplication.context, AppKeys.KEY_USER_ID);
			String isSyncedWithServerStatus = "0";

			Camera_Model c_Model = new Camera_Model();

			c_Model.alarm = "2"; // Need to confirm
			c_Model.authority = bean.isAuthority()+"";

			c_Model.cameraId = bean.getDid().toUpperCase() + getUnitTimeStamp();
			if (bean.getDid() != null)
				c_Model.did = bean.getDid().toUpperCase();
			c_Model.isAdd = bean.isSelected()+"";
			c_Model.isSyncedWithServer = isSyncedWithServerStatus; // Need to
																	// confirm
			c_Model.model = "123";
			c_Model.modelStatus = AppKeys.KEY_IS_CREATED;
			c_Model.name = bean.getName();
			c_Model.password = bean.getPwd();
			c_Model.ppppMood = bean.getMode() + "";
			c_Model.ppppStatus = bean.getStatus() + "";
			c_Model.user = bean.getUser();
			c_Model.userId = userID;

			c_Model.save();

			User_Camera_Model u_Camera_Model = new User_Camera_Model();

			u_Camera_Model.cameraId = c_Model.cameraId;
			u_Camera_Model.isAccessCameraSetting = "1";
			u_Camera_Model.isSyncedWithServer = isSyncedWithServerStatus;
			u_Camera_Model.modelStatus = c_Model.modelStatus;

			u_Camera_Model.userCameraId = c_Model.did + getUnitTimeStamp(); // Need
																			// to
																			// confirm
			u_Camera_Model.userId = userID;

			u_Camera_Model.save();

		} else {
			editCameraModelAndUserModelRelationInDB(bean, userId);
		}
	}

	

	public static CameraParamsBean addCameraModelAndUserModelRelationInDB() {

		String did = "TAABCDEFT" + getUnitTimeStamp();

		Camera_Model c_Model = new Camera_Model();

		c_Model.alarm = "2"; // Need to confirm
		c_Model.authority = "0";

		c_Model.cameraId = did + getUnitTimeStamp();

		c_Model.did = did;
		c_Model.isAdd = "";
		c_Model.isSyncedWithServer = "1"; // Need to confirm
		c_Model.model = "123";
		c_Model.modelStatus = AppKeys.KEY_IS_CREATED;
		c_Model.name = "Camera";
		c_Model.password = "12345";
		c_Model.ppppMood = "3";
		c_Model.ppppStatus = "7";
		c_Model.user = "Admin";

		String userID = AppPreference.getValue(KisafaApplication.context, AppKeys.KEY_USER_ID);

		c_Model.save();

		User_Camera_Model u_Camera_Model = new User_Camera_Model();

		u_Camera_Model.cameraId = c_Model.cameraId;
		u_Camera_Model.isAccessCameraSetting = "1";
		u_Camera_Model.isSyncedWithServer = "1";
		u_Camera_Model.modelStatus = c_Model.modelStatus;

		u_Camera_Model.userCameraId = c_Model.did + getUnitTimeStamp(); // Need
																		// to
																		// confirm
		u_Camera_Model.userId = userID;

		u_Camera_Model.save();

		CameraParamsBean cameraParamsBean = new CameraParamsBean();

		cameraParamsBean.setDid(c_Model.did);
		cameraParamsBean.setName(c_Model.name);
		cameraParamsBean.setStatus(3);
		cameraParamsBean.setPwd(c_Model.password);
		cameraParamsBean.setUser(c_Model.user);

		return cameraParamsBean;

	}

	public static void editCameraModelAndUserModelRelationInDB(CameraParamsBean bean, String userId) {

		try {

			Camera_Model c_Model = new Select().from(Camera_Model.class).where("did = ?", bean.getDid().toUpperCase())
					.executeSingle();

			c_Model.authority = bean.isAuthority()+"";
			c_Model.isAdd = bean.isSelected() +"";
			c_Model.isSyncedWithServer = "0";
			c_Model.modelStatus = AppKeys.KEY_IS_UPDATED;
			c_Model.name = bean.getName();
			c_Model.password = bean.getPwd();
			c_Model.ppppMood = bean.getMode() + "";
			c_Model.ppppStatus = bean.getStatus() + "";
			c_Model.user = bean.getUser();

			User_Camera_Model u_Camera_Model = new Select().from(User_Camera_Model.class)
					.where("cameraId = ? AND userId = ?", c_Model.cameraId.trim(), userId).executeSingle();

			u_Camera_Model.isSyncedWithServer = "0";
			u_Camera_Model.modelStatus = AppKeys.KEY_IS_UPDATED;

			c_Model.save();
			u_Camera_Model.save();

		} catch (Exception ex) {

		}

	}

	public static void editCameraModelAndUserModelRelationInDB(Sh_Camera sh_Camera, Sh_UserCamera sh_UserCamera) {

		String isSyncedWithServerStatus = "1";

		try {

			Camera_Model c_Model = new Select().from(Camera_Model.class)
					.where("did = ?", sh_Camera.getDid().toUpperCase()).executeSingle();

			c_Model.alarm = sh_Camera.isAlarm(); // Need to confirm
			c_Model.authority = sh_Camera.getAuthority()+"";

			c_Model.cameraId = sh_Camera.getCameraId();
			if (sh_Camera.getDid() != null)
				c_Model.did = sh_Camera.getDid().toUpperCase();
			c_Model.isAdd = sh_Camera.isAdd()+"";
			c_Model.isSyncedWithServer = isSyncedWithServerStatus; // Need to
																	// confirm
			c_Model.model = sh_Camera.getModel();
			c_Model.modelStatus = AppKeys.KEY_IS_CREATED;
			c_Model.name = sh_Camera.getName();
			c_Model.password = sh_Camera.getPassword();
			c_Model.ppppMood = sh_Camera.getPpppMood();
			c_Model.ppppStatus = sh_Camera.getPpppStatus();
			c_Model.user = sh_Camera.getUser();

			User_Camera_Model u_Camera_Model = new Select().from(User_Camera_Model.class)
					.where("cameraId = ?", c_Model.cameraId.trim()).executeSingle();

			u_Camera_Model.cameraId = sh_UserCamera.getCameraId();
			u_Camera_Model.isAccessCameraSetting = sh_UserCamera.getIsAccessCameraSetting();
			u_Camera_Model.isSyncedWithServer = isSyncedWithServerStatus;
			u_Camera_Model.modelStatus = sh_UserCamera.getModelStatus();

			u_Camera_Model.userCameraId = sh_UserCamera.getCameraId(); // Need
																		// to
																		// confirm
			u_Camera_Model.userId = sh_UserCamera.getUserId();

			u_Camera_Model.save();
			c_Model.save();

		} catch (Exception ex) {

		}

	}

	public static void editSyncedBitCameraModelAndUserModelRelationInDB(String did) {

		try {

			Camera_Model c_Model = new Select().from(Camera_Model.class).where("did = ?", did.toUpperCase())
					.executeSingle();

			c_Model.isSyncedWithServer = "1";

			User_Camera_Model u_Camera_Model = new Select().from(User_Camera_Model.class)
					.where("cameraId = ?", c_Model.cameraId.trim()).executeSingle();

			u_Camera_Model.isSyncedWithServer = "1";

			u_Camera_Model.save();
			c_Model.save();
		} catch (Exception e) {

		}

	}

	

	public static void add_New_SyncedBitCameraModelAndUserModelRelationInDB(ArrayList<Sh_Camera> listcamera,
			ArrayList<Sh_UserCamera> listusercamera, Context context) {

		new Delete().from(Camera_Model.class).execute();
		new Delete().from(User_Camera_Model.class).execute();

		try {

			// All cameras and user_camera relations are created in data base
			// coming from loadAllData

			if (listusercamera != null && listusercamera.size() > 0)
				for (int i = 0; i < listusercamera.size(); i++) {
					create_New_User_CameraModelInDB(listusercamera.get(i));

				}

			if (listcamera != null)
				for (int i = 0; i < listcamera.size(); i++) {
					create_New_CameraModelInDB(listcamera.get(i));
				}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static User_Camera_Model_Relation showCameraModelAndUserModelRelationInDB() {

		User_Camera_Model_Relation user_Camera_Model_Relation = new User_Camera_Model_Relation();

		try {

			List<Camera_Model> tempModel = new Select().from(Camera_Model.class).where("Camera_Model.isSyncedWithServer = ?", AppKeys.KEY_IS_NOT_SYNCED).execute();

			List<User_Camera_Model> tempUserCameraModel = new Select().from(User_Camera_Model.class).where("User_Camera_Model.isSyncedWithServer = ?", AppKeys.KEY_IS_NOT_SYNCED).execute();

			user_Camera_Model_Relation.camera_Model = tempModel;
			user_Camera_Model_Relation.user_Camera_Model = tempUserCameraModel;

			return user_Camera_Model_Relation;

		} catch (Exception ex) {
			ex.toString();
		}
		return user_Camera_Model_Relation;

	}

	public static void deleteCameraModelAndUserModelRelationInDB(String did, String userId) {

		try {

			Camera_Model c_Model = new Select().from(Camera_Model.class).where("did = ?", did).executeSingle();

			c_Model.isSyncedWithServer = "0";
			c_Model.modelStatus = AppKeys.KEY_IS_DELETED;
			c_Model.save();

			User_Camera_Model u_Camera_Model = new Select().from(User_Camera_Model.class)
					.where("cameraId = ? AND userId = ?", c_Model.cameraId, userId).executeSingle();
			u_Camera_Model.isSyncedWithServer = "0";
			u_Camera_Model.modelStatus = AppKeys.KEY_IS_DELETED;

			u_Camera_Model.save();

			List<User_Camera_Model> tempcamList = getAllAssociationOfSubUserAgainstCamera(c_Model.cameraId);

			if (tempcamList != null)
				for (int i = 0; i < tempcamList.size(); i++) {
					tempcamList.get(i).modelStatus = AppKeys.KEY_IS_DELETED;
					tempcamList.get(i).isSyncedWithServer = "0";
					tempcamList.get(i).isAccessCameraSetting = "1";
					tempcamList.get(i).save();
				}

		} catch (Exception ex) {
			ex.toString();
		}

	}

	public static void realDeleteAllrecode() {
		new Delete().from(User_Camera_Model.class).execute();
		new Delete().from(Camera_Model.class).execute();
	}

	public static String getUnitTimeStamp() {

		String uiqueId = System.currentTimeMillis() + "";

		// uiqueId=uiqueId.substring(uiqueId.length()-10);

		return uiqueId;
	}

	
	public static String getUnitTimeStampForRoom() {

		String uiqueId = "room_"+System.currentTimeMillis() + "";

		// uiqueId=uiqueId.substring(uiqueId.length()-10);

		return uiqueId;
	}
	
	
	public static String getUnitTimeStampForRoomUser() {

		String uiqueId = "room_user_"+System.currentTimeMillis() + "";

		// uiqueId=uiqueId.substring(uiqueId.length()-10);

		return uiqueId;
	}
	
	
	public static String getUnitTimeStampForMood() {

		String uiqueId = "moods_"+System.currentTimeMillis() + "";

		// uiqueId=uiqueId.substring(uiqueId.length()-10);

		return uiqueId;
	}
	
	public static String getUnitTimeStampForSwitch() {

		String uiqueId = "switch_"+System.currentTimeMillis() + "";

		// uiqueId=uiqueId.substring(uiqueId.length()-10);

		return uiqueId;
	}
	
	public static String getUnitTimeStampForTariff() {

		String uiqueId = "tariff_"+System.currentTimeMillis() + "";

		return uiqueId;
	}
	
	public void createUpdateUserModel(String camId, String camUserId, String isAccessCameraSetting, String did,
			String userId) {

		User_Camera_Model c_Model_test = new Select().from(User_Camera_Model.class).where("userCameraId = ?", camUserId)
				.executeSingle();

		if (c_Model_test == null) {
			User_Camera_Model u_Camera_Model = new User_Camera_Model();

			u_Camera_Model.cameraId = camId;
			u_Camera_Model.isAccessCameraSetting = "0";
			u_Camera_Model.isSyncedWithServer = "0";
			u_Camera_Model.modelStatus = AppKeys.KEY_IS_CREATED;
			u_Camera_Model.userCameraId = did + getUnitTimeStamp(); // Need to
																	// confirm
			u_Camera_Model.userId = userId;

			u_Camera_Model.save();
		} else {

			c_Model_test.isAccessCameraSetting = "0";
			c_Model_test.isSyncedWithServer = "0";
			c_Model_test.save();
		}
	}

	public static List<User_Camera_Model> getAllAssociationOfSubUser(String userCamId) {

		List<User_Camera_Model> tempUserCameraModel = null;

		try {

			tempUserCameraModel = new Select().from(User_Camera_Model.class)

					.where("User_Camera_Model.userId = ?", userCamId).execute();

			return tempUserCameraModel;

		} catch (Exception ex) {
			ex.toString();
		}
		return tempUserCameraModel;

	}

	public static List<User_Camera_Model> getAllAssociationOfSubUserAgainstCamera(String camId) {

		List<User_Camera_Model> tempCameraModel = null;

		try {

			tempCameraModel = new Select().from(User_Camera_Model.class)

					.where("User_Camera_Model.cameraId = ? AND User_Camera_Model.modelStatus != ?", camId,
							AppKeys.KEY_IS_DELETED)
					.execute();

			return tempCameraModel;

		} catch (Exception ex) {
			ex.toString();
		}
		return tempCameraModel;

	}

	public static User_Camera_Model_Relation getCameraWithRelationAgainst_UserId(String userId) {

		User_Camera_Model_Relation user_Camera_Model_Relation = new User_Camera_Model_Relation();

		List<Camera_Model> tempCameraModel = null;

		List<User_Camera_Model> tempUserCameraModel = null;
		try {

			tempCameraModel = new Select().distinct().from(Camera_Model.class).join(User_Camera_Model.class)
					.on("User_Camera_Model.cameraId=Camera_Model.cameraId")
					.where("User_Camera_Model.userId = ? AND User_Camera_Model.modelStatus!= ? AND Camera_Model.modelStatus!= ?",
							userId, AppKeys.KEY_IS_DELETED, AppKeys.KEY_IS_DELETED)
					.execute();

			tempUserCameraModel = new Select().distinct().from(User_Camera_Model.class).join(Camera_Model.class)
					.on("User_Camera_Model.cameraId=Camera_Model.cameraId")
					.where("User_Camera_Model.userId = ? AND User_Camera_Model.modelStatus!= ? AND Camera_Model.modelStatus!= ?",
							userId, AppKeys.KEY_IS_DELETED, AppKeys.KEY_IS_DELETED)
					.execute();

			user_Camera_Model_Relation.camera_Model = tempCameraModel;
			user_Camera_Model_Relation.user_Camera_Model = tempUserCameraModel;

			return user_Camera_Model_Relation;

		} catch (Exception ex) {
			ex.toString();
		}
		return user_Camera_Model_Relation;

	}

	public static User_Camera_Model_Relation getSubUserCameraWithRelationAgainst_UserId(String userId) {

		User_Camera_Model_Relation user_Camera_Model_Relation = new User_Camera_Model_Relation();

		List<Camera_Model> tempCameraModel = null;

		List<User_Camera_Model> tempUserCameraModel = null;
		try {

			tempCameraModel = new Select().distinct().from(Camera_Model.class).join(User_Camera_Model.class)
					.on("User_Camera_Model.cameraId=Camera_Model.cameraId")
					.where("User_Camera_Model.userId = ? ", userId).execute();

			tempUserCameraModel = new Select().distinct().from(User_Camera_Model.class).join(Camera_Model.class)
					.on("User_Camera_Model.cameraId=Camera_Model.cameraId")
					.where("User_Camera_Model.userId = ?", userId).execute();

			user_Camera_Model_Relation.camera_Model = tempCameraModel;
			user_Camera_Model_Relation.user_Camera_Model = tempUserCameraModel;

			return user_Camera_Model_Relation;

		} catch (Exception ex) {
			ex.toString();
		}
		return user_Camera_Model_Relation;

	}

	public static List<User_Camera_Model> getAllAssociationOfSubUserOfThisCamera(String cameraId) {

		List<User_Camera_Model> tempUserCameraModel = null;

		try {

			tempUserCameraModel = new Select().from(User_Camera_Model.class)

					.where("User_Camera_Model.cameraId = ?", cameraId).execute();

			return tempUserCameraModel;

		} catch (Exception ex) {
			ex.toString();
		}
		return tempUserCameraModel;

	}

	public static List<User_Camera_Model> getAllLigal_AssociationOfSubUser(String userCamId) {

		List<User_Camera_Model> tempUserCameraModel = null;

		try {

			tempUserCameraModel = new Select().from(User_Camera_Model.class)

					.where("User_Camera_Model.userId = ?", "User_Camera_Model.modelStatus != ?", userCamId,
							AppKeys.KEY_IS_DELETED)
					.execute();

			return tempUserCameraModel;

		} catch (Exception ex) {
			ex.toString();
		}
		return tempUserCameraModel;

	}

	public static List<Camera_Model> getAllCamerasfromDb() {

		List<Camera_Model> _tempUserCameraModel = null;

		try {

			_tempUserCameraModel = new Select().from(Camera_Model.class)
					.where("Camera_Model.modelStatus != ?", AppKeys.KEY_IS_DELETED).execute();

			return _tempUserCameraModel;

		} catch (Exception ex) {
			ex.toString();
		}
		return _tempUserCameraModel;

	}

	public static void create_New_CameraModelInDB(Sh_Camera sh_Camera) {

		Camera_Model c_Model_test = new Camera_Model();

		String isSyncedWithServerStatus = "1";

		Camera_Model c_Model = new Camera_Model();

		c_Model.alarm = sh_Camera.isAlarm(); // Need to confirm
		c_Model.authority = sh_Camera.getAuthority()+"";

		c_Model.cameraId = sh_Camera.getCameraId();
		if (sh_Camera.getDid() != null)
			c_Model.did = sh_Camera.getDid().toUpperCase();
		c_Model.isAdd = sh_Camera.isAdd()+"";
		c_Model.isSyncedWithServer = isSyncedWithServerStatus; // Need to
																// confirm
		c_Model.model = sh_Camera.getModel();
		c_Model.modelStatus = sh_Camera.getModelStatus();
		c_Model.name = sh_Camera.getName();
		c_Model.password = sh_Camera.getPassword();
		c_Model.ppppMood = sh_Camera.getPpppMood();
		c_Model.ppppStatus = sh_Camera.getPpppStatus();
		c_Model.user = sh_Camera.getUser();

		c_Model.save();

	}

	public static void create_New_User_CameraModelInDB(Sh_UserCamera sh_UserCamera) {

		User_Camera_Model u_Camera_Model = new User_Camera_Model();

		u_Camera_Model.cameraId = sh_UserCamera.getCameraId();
		u_Camera_Model.isAccessCameraSetting = sh_UserCamera.getIsAccessCameraSetting();
		u_Camera_Model.isSyncedWithServer = "1";
		u_Camera_Model.modelStatus = sh_UserCamera.getModelStatus();

		u_Camera_Model.userCameraId = sh_UserCamera.getUserCameraId(); // Need
																		// to
																		// confirm
		u_Camera_Model.userId = sh_UserCamera.getUserId();

		u_Camera_Model.save();

	}

	
	
	public static void update_CameraModelInDB(Sh_Camera sh_Camera) {

		try {

			Camera_Model c_Model = new Select().from(Camera_Model.class).where("cameraId = ?", sh_Camera.getCameraId())
					.executeSingle();

			if (c_Model == null) {
				c_Model = new Camera_Model();
			}

			String isSyncedWithServerStatus = "1";

			c_Model.alarm = sh_Camera.isAlarm(); // Need to confirm
			c_Model.authority = sh_Camera.getAuthority()+"";

			c_Model.cameraId = sh_Camera.getCameraId();
			if (sh_Camera.getDid() != null)
				c_Model.did = sh_Camera.getDid().toUpperCase();
			c_Model.isAdd = sh_Camera.isAdd()+"";
			c_Model.isSyncedWithServer = isSyncedWithServerStatus; // Need to
																	// confirm
			c_Model.model = sh_Camera.getModel();
			c_Model.modelStatus = sh_Camera.getModelStatus();
			c_Model.name = sh_Camera.getName();
			c_Model.password = sh_Camera.getPassword();
			c_Model.ppppMood = sh_Camera.getPpppMood();
			c_Model.ppppStatus = sh_Camera.getPpppStatus();
			c_Model.user = sh_Camera.getUser();

			c_Model.save();

		} catch (Exception ex) {
		}

	}
	
	
	public static void update_CameraModelInDB_User_Case(Sh_Camera sh_Camera) {

		try {

			Camera_Model c_Model = new Select().from(Camera_Model.class).where("cameraId = ?", sh_Camera.getCameraId())
					.executeSingle();

			if (c_Model == null) {
				c_Model = new Camera_Model();
			}
			
			
			if(c_Model!=null && c_Model.isSyncedWithServer.equals(AppKeys.KEY_IS_SYNCED))
			{
				
				return;
			}

			String isSyncedWithServerStatus = "1";

			c_Model.alarm = sh_Camera.isAlarm(); // Need to confirm
			c_Model.authority = sh_Camera.getAuthority()+"";

			c_Model.cameraId = sh_Camera.getCameraId();
			if (sh_Camera.getDid() != null)
				c_Model.did = sh_Camera.getDid().toUpperCase();
			c_Model.isAdd = sh_Camera.isAdd()+"";
			c_Model.isSyncedWithServer = isSyncedWithServerStatus; // Need to
																	// confirm
			c_Model.model = sh_Camera.getModel();
			c_Model.modelStatus = sh_Camera.getModelStatus();
			c_Model.name = sh_Camera.getName();
			c_Model.password = sh_Camera.getPassword();
			c_Model.ppppMood = sh_Camera.getPpppMood();
			c_Model.ppppStatus = sh_Camera.getPpppStatus();
			c_Model.user = sh_Camera.getUser();

			c_Model.save();

		} catch (Exception ex) {
		}

	}

	
	
	public static void update_User_CameraModelInDB(Sh_UserCamera sh_UserCamera) {

		try {

			User_Camera_Model u_Camera_Model = new Select().from(User_Camera_Model.class)
					.where("userCameraId = ?", sh_UserCamera.getUserCameraId()).executeSingle();
			
			
			
			

			if (u_Camera_Model == null) {
				u_Camera_Model = new User_Camera_Model();
			}

			u_Camera_Model.cameraId = sh_UserCamera.getCameraId();
			u_Camera_Model.isAccessCameraSetting = sh_UserCamera.getIsAccessCameraSetting();
			u_Camera_Model.isSyncedWithServer = "1";
			u_Camera_Model.modelStatus = sh_UserCamera.getModelStatus();

			u_Camera_Model.userCameraId = sh_UserCamera.getUserCameraId(); // Need
																			// to
																			// confirm
			u_Camera_Model.userId = sh_UserCamera.getUserId();

			u_Camera_Model.save();

		} catch (Exception ex) {

		}

	}

	
	public static void update_User_CameraModelInDBUserCase(Sh_UserCamera sh_UserCamera) {

		try {

			User_Camera_Model u_Camera_Model = new Select().from(User_Camera_Model.class)
					.where("userCameraId = ?", sh_UserCamera.getUserCameraId()).executeSingle();
			
			
			if(u_Camera_Model!=null && u_Camera_Model.isSyncedWithServer.equals(AppKeys.KEY_IS_SYNCED))
			{
				
				return;
			}
			

			if (u_Camera_Model == null) {
				u_Camera_Model = new User_Camera_Model();
			}

			u_Camera_Model.cameraId = sh_UserCamera.getCameraId();
			u_Camera_Model.isAccessCameraSetting = sh_UserCamera.getIsAccessCameraSetting();
			u_Camera_Model.isSyncedWithServer = "1";
			u_Camera_Model.modelStatus = sh_UserCamera.getModelStatus();

			u_Camera_Model.userCameraId = sh_UserCamera.getUserCameraId(); // Need
																			// to
																			// confirm
			u_Camera_Model.userId = sh_UserCamera.getUserId();

			u_Camera_Model.save();

		} catch (Exception ex) {

		}

	}

	
	
	public static List<Switch_Model> fetchAllNonSyncedSwitchesFromDb()
	{
		List<Switch_Model> switchModelList=null;
		try
		{
			switchModelList = new Select().from(Switch_Model.class).where("Switch_Model.isSyncedWithServer = ?", AppKeys.KEY_IS_NOT_SYNCED).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return switchModelList;
	}
	
	
	public static List<Room_Model> fetchAllNonSyncedRoomsFromDb()
	{
		List<Room_Model> roomModelList=null;
		try
		{
			roomModelList = new Select().from(Room_Model.class).where("Room_Model.isSyncedWithServer = ?", AppKeys.KEY_IS_NOT_SYNCED).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return roomModelList;
	}
	
	
	public static List<User_Room_Model> fetchAllNonSyncedUserRoomsFromDb()
	{
		List<User_Room_Model> userRoomModelList=null;
		try
		{
			userRoomModelList = new Select().from(User_Room_Model.class).where("User_Room_Model.isSyncedWithServer = ?", AppKeys.KEY_IS_NOT_SYNCED).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return userRoomModelList;
	}
	
	public static List<Tariff_Model> fetchAllNonSyncedTariffsFromDb()
	{
		List<Tariff_Model> tariffModelList=null;
		try
		{
			tariffModelList = new Select().from(Tariff_Model.class).where("Tariff_Model.isSyncedWithServer = ?", AppKeys.KEY_IS_NOT_SYNCED).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return tariffModelList;
	}
	
	
	public static List<Mood_Model> fetchAllNonSyncedPictureMoodsFromDb()
	{
		List<Mood_Model> moodsModelList=null;
		try
		{
			moodsModelList = new Select().from(Mood_Model.class).where("Mood_Model.isPictureSyncedWithServer = ? ", AppKeys.KEY_IS_NOT_SYNCED).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return moodsModelList;
	}
	
	
	
	public static List<Mood_Model> fetchAllNonSyncedMoodsFromDb()
	{
		List<Mood_Model> moodsModelList=null;
		try
		{
			moodsModelList = new Select().from(Mood_Model.class).where("Mood_Model.isSyncedWithServer = ?  ", AppKeys.KEY_IS_NOT_SYNCED).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return moodsModelList;
	}
	
	
	public static List<Room_Model> fetchAllNonSyncedRoomsPicturersFromDb()
	{
		List<Room_Model> roomModelList=null;
		try
		{
			roomModelList = new Select().from(Room_Model.class).where("Room_Model.isPictureSyncedWithServer = ? AND Room_Model.picture!= ?", AppKeys.KEY_IS_NOT_SYNCED,"null").execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return roomModelList;
	}
	
	
	public static void update_OR_Create_RoomModelInDB(Sh_Room sh_Room,boolean isFromLoadAllData) {

		try {

			Room_Model roomModel = new Select().from(Room_Model.class).where("room_id = ?", sh_Room.getSh_room_id())
					.executeSingle();

			if (roomModel == null) {
				roomModel = new Room_Model();
			}

			
			roomModel.room_id=sh_Room.getSh_room_id(); 
			roomModel.modelStatus=sh_Room.getSh_modelStatus();
			roomModel.title=sh_Room.sh_title;
			
			roomModel.pictureURL=sh_Room.getSh_sh_picture_url();
			roomModel.isSyncedWithServer=AppKeys.KEY_IS_SYNCED;
			try
			{
				if(isFromLoadAllData)
				{
					roomModel.isPictureSyncedWithServer=AppKeys.KEY_IS_SYNCED;
				}
			}catch(Exception ex)
			{
				ex.toString();
			}

			roomModel.save();

		} catch (Exception ex) {
		}

	}
	public static void update_OR_Create_RoomModelInDB_User_Case(Sh_Room sh_Room,boolean isFromLoadAllData) {

		try {

			Room_Model roomModel = new Select().from(Room_Model.class).where("room_id = ?", sh_Room.getSh_room_id())
					.executeSingle();
			
			
			
			
			if(roomModel!=null && roomModel.isSyncedWithServer.equals(AppKeys.KEY_IS_SYNCED))
			{
				
				return;
			}
			

			if (roomModel == null) {
				roomModel = new Room_Model();
			}

			
			roomModel.room_id=sh_Room.getSh_room_id(); 
			roomModel.modelStatus=sh_Room.getSh_modelStatus();
			roomModel.title=sh_Room.sh_title;
			
			roomModel.pictureURL=sh_Room.getSh_sh_picture_url();
			roomModel.isSyncedWithServer=AppKeys.KEY_IS_SYNCED;
			try
			{
				if(isFromLoadAllData)
				{
					roomModel.isPictureSyncedWithServer=AppKeys.KEY_IS_SYNCED;
				}
			}catch(Exception ex)
			{
				ex.toString();
			}

			roomModel.save();

		} catch (Exception ex) {
		}

	}
	
	
	public static void update_OR_Create_UserRoomModelInDB(Sh_User_Room sh_UserRoom) {

		try {

			User_Room_Model userRoomModel = new Select().from(User_Room_Model.class).where("user_room_id = ?", sh_UserRoom.getSh_user_room_id())
					.executeSingle();

			if (userRoomModel == null) {
				userRoomModel = new User_Room_Model();
			}

			
			userRoomModel.room_id=sh_UserRoom.getSh_room_id(); 
			userRoomModel.modelStatus=sh_UserRoom.getSh_model_status();
			userRoomModel.user_room_id=sh_UserRoom.getSh_user_room_id();
			userRoomModel.userId=sh_UserRoom.getSh_userId();
			userRoomModel.isSyncedWithServer=AppKeys.KEY_IS_SYNCED;

			userRoomModel.save();

		} catch (Exception ex) {
		}

	}
	
	public static void update_OR_Create_UserRoomModelInDB_User_Case(Sh_User_Room sh_UserRoom) {

		try {

			User_Room_Model userRoomModel = new Select().from(User_Room_Model.class).where("user_room_id = ?", sh_UserRoom.getSh_user_room_id())
					.executeSingle();
			
			
			if(userRoomModel!=null && userRoomModel.isSyncedWithServer.equals(AppKeys.KEY_IS_SYNCED))
			{
				
				return;
			}

			if (userRoomModel == null) {
				userRoomModel = new User_Room_Model();
			}

			
			userRoomModel.room_id=sh_UserRoom.getSh_room_id(); 
			userRoomModel.modelStatus=sh_UserRoom.getSh_model_status();
			userRoomModel.user_room_id=sh_UserRoom.getSh_user_room_id();
			userRoomModel.userId=sh_UserRoom.getSh_userId();
			userRoomModel.isSyncedWithServer=AppKeys.KEY_IS_SYNCED;

			userRoomModel.save();

		} catch (Exception ex) {
		}

	}
	
	
	
	public static void update_OR_Create_Tariff_ModelInDB(ShTariffResult sh_tariff) {

		
		if(sh_tariff.getSh_tariff_id()==null || sh_tariff.getSh_tariff_id().trim().length()==0)
		{
			return ;
		}
		
		
		try {

			Tariff_Model tariffModel = new Select().from(Tariff_Model.class).where("tariff_id = ?", sh_tariff.getSh_tariff_id())
					.executeSingle();

			if (tariffModel == null) {
				tariffModel = new Tariff_Model();
			}

			
			tariffModel.lower_limit=sh_tariff.getSh_lower_limit(); 
			tariffModel.upper_limit=sh_tariff.getSh_upper_limit();
			tariffModel.price=sh_tariff.getSh_price();
			tariffModel.user_id=sh_tariff.getSh_user_id();
			tariffModel.model_status=sh_tariff.getSh_model_status();
			tariffModel.tariff_id=sh_tariff.getSh_tariff_id();
			tariffModel.isSyncedWithServer=AppKeys.KEY_IS_SYNCED;

			tariffModel.save();

		} catch (Exception ex) {
		ex.printStackTrace();
		}

	}
	
	
public static void update_OR_Create_Tariff_ModelInDB_User_Case(ShTariffResult sh_tariff) {

		
		if(sh_tariff.getSh_tariff_id()==null || sh_tariff.getSh_tariff_id().trim().length()==0)
		{
			return ;
		}
		
		
		try {

			Tariff_Model tariffModel = new Select().from(Tariff_Model.class).where("tariff_id = ?", sh_tariff.getSh_tariff_id())
					.executeSingle();
			
			
			if(tariffModel!=null && tariffModel.isSyncedWithServer.equals(AppKeys.KEY_IS_SYNCED))
			{
				
				return;
			}
			

			if (tariffModel == null) {
				tariffModel = new Tariff_Model();
			}

			
			tariffModel.lower_limit=sh_tariff.getSh_lower_limit(); 
			tariffModel.upper_limit=sh_tariff.getSh_upper_limit();
			tariffModel.price=sh_tariff.getSh_price();
			tariffModel.user_id=sh_tariff.getSh_user_id();
			tariffModel.model_status=sh_tariff.getSh_model_status();
			tariffModel.tariff_id=sh_tariff.getSh_tariff_id();
			tariffModel.isSyncedWithServer=AppKeys.KEY_IS_SYNCED;

			tariffModel.save();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	

	
	
	public static void update_OR_Create_SwitchodelInDB(Sh_Switch sh_Switch) {

		try {

			Switch_Model switchModel = new Select().from(Switch_Model.class).where("switch_id = ?", sh_Switch.getSh_switch_id())
					.executeSingle();

			if (switchModel == null) {
				switchModel = new Switch_Model();
				
			}

			
			switchModel.switch_id=sh_Switch.getSh_switch_id();
			switchModel.model_status=sh_Switch.getSh_model_status();
			switchModel.title=sh_Switch.sh_title;
			switchModel.isSyncedWithServer=AppKeys.KEY_IS_SYNCED;
			switchModel.type=sh_Switch.getSh_type();
			
			
			switchModel.mac_address=sh_Switch.getSh_mac_address();
			
			switchModel.dimmingValue1=sh_Switch.getSh_dimmingValue1();
			switchModel.dimmingValue2=sh_Switch.getSh_dimmingValue2();
			switchModel.dimmingValue3=sh_Switch.getSh_dimmingValue3();
			
			/*switchModel.ip_address=sh_Switch.getSh_ip_address();
			switchModel.port=sh_Switch.getSh_port();
			switchModel.is_connected=sh_Switch.getSh_is_connected();
			switchModel.is_activated=sh_Switch.getSh_is_activated();*/
			
			switchModel.room_id=sh_Switch.getSh_room_id();
			switchModel.user_id=sh_Switch.getSh_user_id();
			switchModel.model_status=sh_Switch.getSh_model_status();

			switchModel.save();

		} catch (Exception ex) {
		}

	}
	
	public static void update_OR_Create_SwitchodelInDB_User_Case(Sh_Switch sh_Switch) {

		try {

			Switch_Model switchModel = new Select().from(Switch_Model.class).where("switch_id = ?", sh_Switch.getSh_switch_id())
					.executeSingle();
			
			
			
			if(switchModel!=null && switchModel.isSyncedWithServer.equals(AppKeys.KEY_IS_SYNCED))
			{
				
				return;
			}

			if (switchModel == null) {
				switchModel = new Switch_Model();
				
			}

			
			switchModel.switch_id=sh_Switch.getSh_switch_id();
			switchModel.model_status=sh_Switch.getSh_model_status();
			switchModel.title=sh_Switch.sh_title;
			switchModel.isSyncedWithServer=AppKeys.KEY_IS_SYNCED;
			switchModel.type=sh_Switch.getSh_type();
			
			
			switchModel.mac_address=sh_Switch.getSh_mac_address();
			
			switchModel.dimmingValue1=sh_Switch.getSh_dimmingValue1();
			switchModel.dimmingValue2=sh_Switch.getSh_dimmingValue2();
			switchModel.dimmingValue3=sh_Switch.getSh_dimmingValue3();
			
			/*switchModel.ip_address=sh_Switch.getSh_ip_address();
			switchModel.port=sh_Switch.getSh_port();
			switchModel.is_connected=sh_Switch.getSh_is_connected();
			switchModel.is_activated=sh_Switch.getSh_is_activated();*/
			
			switchModel.room_id=sh_Switch.getSh_room_id();
			switchModel.user_id=sh_Switch.getSh_user_id();
			switchModel.model_status=sh_Switch.getSh_model_status();

			switchModel.save();

		} catch (Exception ex) {
		}

	}
	
	
	
	public static void update_OR_Create_MoodsInDB(Sh_Mood sh_Mood,boolean isFromLoadAllData) {

		try {

			Mood_Model moodModel = new Select().from(Mood_Model.class).where("moodId = ?", sh_Mood.getSh_mood_id())
					.executeSingle();

			if (moodModel == null) {
				moodModel = new Mood_Model();
				
			}

			
			moodModel.moodId=sh_Mood.getSh_mood_id();
			moodModel.switchId=sh_Mood.getSh_switch_id();
			moodModel.modelStatus=sh_Mood.getSh_model_status();
			try
			{
			moodModel.moodIdentifer= Integer.parseInt(sh_Mood.getSh_mood_identifier());
			}catch(Exception ex){}
			moodModel.title=sh_Mood.sh_title;
			moodModel.isSyncedWithServer=AppKeys.KEY_IS_SYNCED;
			if(isFromLoadAllData)
			{
			moodModel.isPictureSyncedWithServer=AppKeys.KEY_IS_SYNCED;
			}
			moodModel.pictureURL=sh_Mood.getSh_picture_url();
			
			moodModel.save();

		} catch (Exception ex) {
		}

	}
	
	
	public static void update_OR_Create_MoodsInDB_User_Case(Sh_Mood sh_Mood,boolean isFromLoadAllData) {

		try {

			Mood_Model moodModel = new Select().from(Mood_Model.class).where("moodId = ?", sh_Mood.getSh_mood_id())
					.executeSingle();
			
			
			if(moodModel!=null && moodModel.isSyncedWithServer.equals(AppKeys.KEY_IS_SYNCED))
			{
				
				return;
			}

			if (moodModel == null) {
				moodModel = new Mood_Model();
				
			}

			
			
			
			moodModel.moodId=sh_Mood.getSh_mood_id();
			moodModel.switchId=sh_Mood.getSh_switch_id();
			moodModel.modelStatus=sh_Mood.getSh_model_status();
			try
			{
			moodModel.moodIdentifer= Integer.parseInt(sh_Mood.getSh_mood_identifier());
			}catch(Exception ex){}
			moodModel.title=sh_Mood.sh_title;
			moodModel.isSyncedWithServer=AppKeys.KEY_IS_SYNCED;
			if(isFromLoadAllData)
			{
			moodModel.isPictureSyncedWithServer=AppKeys.KEY_IS_SYNCED;
			}
			moodModel.pictureURL=sh_Mood.getSh_picture_url();
			
			moodModel.save();

		} catch (Exception ex) {
		}

	}
	
	
	
	
}
