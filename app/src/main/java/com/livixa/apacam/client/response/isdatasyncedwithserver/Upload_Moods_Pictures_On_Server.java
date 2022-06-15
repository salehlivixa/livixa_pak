package com.livixa.apacam.client.response.isdatasyncedwithserver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.activeandroid.query.Select;
import com.github.mrengineer13.snackbar.SnackBar;
import com.kisafa.user.profile.UpdateProfileResponse;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.livixa.apacam.client.activity.UpdateProfileActivity.BitmapDownloaderTask;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.AppWebServices;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.dbmodel.Manage_DB_Model;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Tariff_Model;
import object.p2pipcam.adapter.ViewRoomsGridViewAdapter;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class Upload_Moods_Pictures_On_Server implements  ServerConnectListener
{

	
	Context   mContext;
	
	ArrayList<Mood_Model>   mMoodsList;	
	
	private static int MEDIA_TYPE_IMAGE=1;
	private static int MEDIA_TYPE_VIDEO=2;
	
	
	HashMap<String, String>  map=new HashMap<>();
	
	
	public Upload_Moods_Pictures_On_Server(Context   mContext)
	{
		
		
		this.mContext=mContext;
		
	}
	
	
	
	public void syncImages()
	{
		
		
		
		mMoodsList= (ArrayList<Mood_Model>) Manage_DB_Model.fetchAllNonSyncedPictureMoodsFromDb();
		
		
		if(mMoodsList!=null && mMoodsList.size() > 0)
		{
			
			syncRoomsAndMoodsImagesOnServer();
			
		}
		
		
		
	}
	
	
	
	
	private String getMoodIdFromPath(String path)
	{
		
		String [] tempString =path.split("uploads");
		
		String [] temp2String = tempString[1].split("\\.");
		
		
		return  temp2String[0].substring(1, temp2String[0].length());
		
		
	}
	
	@Override
	public void onSuccess(ServerResponse response,String raw) {
		
		
          
		
		if (response.getRequestCode() == ServerCodes.ServerRequestCodes.UPDATE_PICTURES_REQUEST_CODE) {
			Uploaded_Pictures_Response registerResponse = (Uploaded_Pictures_Response) response;
			if (registerResponse.getShMeta().getShErrorCode()
					.equalsIgnoreCase("0")) {
				
				
				ArrayList<String> pathsList = registerResponse.getShResult().getPicturePaths();
				
				if(pathsList!=null)
				{
					ImageLoader imageLoader=ImageLoader.getInstance();
					for(int i=0; i<pathsList.size() ; i++)
					{
					
						String moodPath=pathsList.get(i);
						
					   String moodId=getMoodIdFromPath(moodPath);
					   
					   Mood_Model moodModel = null;
					   
					   try
					   {
						   moodModel = new Select().from(Mood_Model.class).where("Mood_Model.moodId  = ?",moodId.trim()).executeSingle();
					   }catch(Exception ex)
					   {
						   ex.toString();
					   }
					   
					   try
					   {
						
						   //if(moodModel.pictureURL!=null && moodModel.pictureURL.length() > 0)
						   {
						        MemoryCacheUtils.removeFromCache(moodPath, ImageLoader.getInstance().getMemoryCache());
						        
						        File imageFile = imageLoader.getDiscCache().get(moodPath);
						        if (imageFile.exists()) {
						            imageFile.delete();
						        }
						        MemoryCacheUtils.removeFromCache(moodPath, imageLoader.getMemoryCache());
						        
						        //Toast.makeText(mContext, imageFile.getName(), Toast.LENGTH_SHORT).show();
						   }
						   //DiscCacheUtils.removeFromCache(url, ImageLoader.getInstance().getDiscCache());
						   
						   File tempFile=new File(map.get(moodModel.moodId)); //
						   if(tempFile.exists())
						   tempFile.delete();
						   
						   
					   }catch(Exception ex)
					   {
						   ex.toString();
					   }
					   
					   
					   if(moodModel!=null && (!moodModel.modelStatus.equals(AppKeys.KEY_IS_DELETED)))
					   {
						   
						   moodModel.isPictureSyncedWithServer=AppKeys.KEY_IS_SYNCED;
						   moodModel.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
						   moodModel.pictureURL=moodPath;
						   
						   moodModel.picture=null;
						   
						   moodModel.save();
						   
					   }
				   
						
					}
					
					
				}
				
				
				
			} else {
				onFailure(registerResponse.getShMeta().getShMessage());
			}
		}
		
		
	}
	
	
	
	
	

	@Override
	public void onFailure(ServerResponse response) {
		onFailure(response.getMessage());
	}

	public void onFailure(String retrofitError) {
		
		
		/*new SnackBar.Builder((Activity) mContext).withMessage(retrofitError)
				.withDuration(SnackBar.SHORT_SNACK).show();*/
	}
	
	private void syncRoomsAndMoodsImagesOnServer()
	{
		
		
		
		
		
		
		MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
		
		
		
        for( int i=0; i<mMoodsList.size(); i++)
        {
        
        	Mood_Model  roomModel=mMoodsList.get(i);
        	
        	
        	if(roomModel.picture!=null)
        	{
        	
        	File profileImagePath=CreateFileFromBitmap(ViewRoomsGridViewAdapter.bytesToBitmap(roomModel.picture),roomModel.moodId);
        	
        	requestBodyBuilder.addFormDataPart("picture[]",profileImagePath.getName(),RequestBody.create(MediaType.parse("image/jpeg"), profileImagePath));
        	
        	map.put(roomModel.moodId, profileImagePath.getAbsolutePath());
        	
        	
        	}
        }
        
        RequestBody session = createPartFromString(AppPreference.getValue(mContext, AppKeys.KEY_SESSION)); 
        
        
        
        requestBodyBuilder.addFormDataPart("session", AppPreference.getValue(mContext, AppKeys.KEY_SESSION));
        
       
        
        HashMap<String, RequestBody> mymap = new HashMap<>();  
		mymap.put("session", session);  
		
		

		ApiService service = KisafaApplication.getRestClient().getApiService();
		//Call<Uploaded_Pictures_Response> call = service.uploadImagesToServer(AppPreference.getValue(mContext, AppKeys.KEY_SESSION),requestBodyBuilder.build());
		Call<Uploaded_Pictures_Response> call = service.uploadImagesToServer(requestBodyBuilder.build());
		call.enqueue(new RestCallback<Uploaded_Pictures_Response>(this,ServerCodes.ServerRequestCodes.UPDATE_PICTURES_REQUEST_CODE, mContext));
        
        
		
	}
	
	public static final String MULTIPART_FORM_DATA = "multipart/form-data";

	@NonNull
	private RequestBody createPartFromString(String descriptionString) {  
	    return RequestBody.create(
	            MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
	}
	
	
	
	private File  CreateFileFromBitmap(Bitmap map,String imageId)
	{
		Bitmap tempBitmap=map;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		tempBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);   
        byte[] photo = baos.toByteArray();
        
        File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE,imageId);
        
      
        
        if (pictureFile == null){
           
            return null;
        }

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(photo);
            fos.close();
        } catch (FileNotFoundException e) {
           // Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            
        }
        
        
        return  pictureFile;
	}
	
	
	
	private  File getOutputMediaFile_(int type,String imageNmae){
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

	    /*File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "MyCameraApp");*/
		
		
		File outputDir = mContext.getCacheDir(); // context being the Activity pointer
		
		
	    
	    
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	   /* if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("MyCameraApp", "failed to create directory");
	            return null;
	        }
	    }*/

		 File mediaFile=null;
		 
	    try {
	    	mediaFile = File.createTempFile(imageNmae, ".jpg", outputDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    // Create a media file name
	   /* String timeStamp = imageNmae;
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    } else if(type == MEDIA_TYPE_VIDEO) {
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "VID_"+ timeStamp + ".mp4");
	    } else {
	        return null;
	    }*/
	    
	    
	    
	    

	    return mediaFile;
	}
	
	private  File getOutputMediaFile(int type,String imageNmae){
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

	    /*File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "MyCameraApp");*/
		
		
		File outputDir = mContext.getCacheDir();
		
		File mediaStorageDir=null;
		if(outputDir==null)
		{
			mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyCameraApp");
		}
		else
		{
			mediaStorageDir=outputDir;
		}
		
		
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("MyCameraApp", "failed to create directory");
	            return null;
	        }
	    }

	    // Create a media file name
	    String timeStamp = imageNmae;
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        ""+ timeStamp + ".jpg");
	    } else if(type == MEDIA_TYPE_VIDEO) {
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "VID_"+ timeStamp + ".mp4");
	    } else {
	        return null;
	    }

	    return mediaFile;
	}
}
