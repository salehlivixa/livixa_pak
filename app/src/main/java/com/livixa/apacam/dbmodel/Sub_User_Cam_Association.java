package com.livixa.apacam.dbmodel;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.query.Select;

import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.client.utility.AppPreference;

public class Sub_User_Cam_Association extends Model
{
	
	

	@Column(name = "session")
	public
	String session="";
	
	@Column(name = "user_camera_id")
	public
	String user_camera_id="";
	
	
	@Column(name = "camera_id")
	public
	String camera_id="";
	
	@Column(name = "did")
	public
	String did="";
	
	@Column(name = "user_id")
	public
	String user_id="";
	
	
	@Column(name = "is_access_camera_settings")
	public
	int is_access_camera_settings=1;
	
	
	@Column(name = "isSyncedWithServer")
	public
	String isSyncedWithServer="";
	
	@Column(name = "modelStatus")
	public
	String modelStatus="";
	
	@Column(name = "cam_name")
	public
	String cam_name="";
	
	@Column(name = "user_name")
	public
	String user_name="";
	
	public static void create_Sub_User_Cam_AssociationInDb(String userName,String camName,String subUserId,String cameraId,int is_access_camera_settings,String session,String userCameraId,String did)
	   {
		 Sub_User_Cam_Association _sUB_USER_DB=null;
		 _sUB_USER_DB=search_Sub_User_Cam_AssociationInDb(subUserId,cameraId);
		 
		 if(_sUB_USER_DB!=null)
		 {
			 update_Sub_User_Cam_AssociationInDb(subUserId,cameraId,is_access_camera_settings);
		 }
		 else
		 {
			_sUB_USER_DB=new Sub_User_Cam_Association();
			
			_sUB_USER_DB.user_camera_id=userName;
			_sUB_USER_DB.cam_name=camName;
			_sUB_USER_DB.camera_id=cameraId;
			_sUB_USER_DB.did=did;
			_sUB_USER_DB.is_access_camera_settings=is_access_camera_settings;
			_sUB_USER_DB.isSyncedWithServer="0";
			_sUB_USER_DB.session=session;
			_sUB_USER_DB.user_id=subUserId;
			_sUB_USER_DB.modelStatus="created";
			_sUB_USER_DB.user_camera_id=userCameraId;
			_sUB_USER_DB.save();
		 }
	   }
	
	
	 public static Sub_User_Cam_Association search_Sub_User_Cam_AssociationInDb(String subUserId,String cameraId)
	   {
		 Sub_User_Cam_Association _sUB_USER_DB=null;
		   
		   try
		   {
		   return _sUB_USER_DB = new Select().from(SUB_USER_DB.class).where("user_id = ?",subUserId,"camera_id = ?",cameraId ).executeSingle();
		   }catch(Exception ex)
		   {
			   
		   }
		   return _sUB_USER_DB;
	   }
	
	 public static void update_Sub_User_Cam_AssociationInDb(String subUserId,String cameraId,int is_access_camera_settings)
	   {
		 Sub_User_Cam_Association _sUB_USER_DB=null;
		   
		   try
		   {
		   _sUB_USER_DB = new Select().from(SUB_USER_DB.class).where("user_id = ?",subUserId,"camera_id = ?",cameraId ).executeSingle();
		   }catch(Exception ex)
		   {
			   
		   }
		   
		   _sUB_USER_DB.is_access_camera_settings=is_access_camera_settings;
		   _sUB_USER_DB.modelStatus="updated";
		   _sUB_USER_DB.save();
	   }
	 
	 
	 public static  List<Sub_User_Cam_Association> getAll_Sub_User_Cam_Associations()
	 {
		 List<Sub_User_Cam_Association>   tempModel=	new Select()
				    .from(Sub_User_Cam_Association.class).execute();
		 
		 return tempModel;
	 }
	
	 public static  List<Sub_User_Cam_Association> getAll_Sub_User_Cam_Associations(String userId)
	 {
		 List<Sub_User_Cam_Association>   tempModel=null;
		 try
		   {
		 tempModel=	new Select()
				    .from(Sub_User_Cam_Association.class).where("user_id = ?",userId).execute();
	 }catch(Exception ex)
	   {
		   
	   }
		 return tempModel;
	 }
	 
	 public static  List<Sub_User_Cam_Association> getAll_Unsynced_Sub_User_Cam_Associations()
	 {
		 List<Sub_User_Cam_Association>   tempModel=null;
		 try
		   {
		 tempModel=	new Select()
				    .from(Sub_User_Cam_Association.class).where("isSyncedWithServer = ?","0").execute();
	 }catch(Exception ex)
	   {
		   
	   }
		 return tempModel;
	 }
	 
}
