package com.livixa.apacam.client.network;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.network.ImagesDownloader.ImageResult;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Room_Model;

public class ImageDownloadManager  implements Handler.Callback,ImageResult {
	
	
	private Context mContext;
	
	private ArrayList<Room_Model>   mRoomsList;
	
	private ArrayList<Mood_Model>   mMoodsList;
	
	private HashMap<String, Room_Model>  roomsMap=new HashMap<>();
	
	private HashMap<String, Mood_Model>  moodMap=new HashMap<>();
	
	
	
	public ImageDownloadManager(Context mContext,ArrayList<Room_Model>   mRoomsList,ArrayList<Mood_Model>   mMoodsList)
	{
	
		this.mContext=mContext;
		this.mRoomsList=mRoomsList;
		this.mMoodsList=mMoodsList;
		
	}
	
	
	public void initDownloader()
	{
		
		
		int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
		 
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>()
        );
 
        
        if(mRoomsList!=null && mRoomsList.size() > 0)
        {
		        for (int i = 0; i < mRoomsList.size(); i++) {
		            String imageUrl = mRoomsList.get(i).pictureURL;
		            
		            roomsMap.put(mRoomsList.get(i).room_id, mRoomsList.get(i));
		            
		            ImagesDownloader imagesDownloader = new ImagesDownloader(i, imageUrl,mRoomsList.get(i).room_id,1, new Handler(this));
		            
		            imagesDownloader.setImageDownloadListner(ImageDownloadManager.this);
		            
		            executor.execute(imagesDownloader);
		        }
        }
        
        for (int i = 0; i < mMoodsList.size(); i++) {
            String imageUrl = mMoodsList.get(i).pictureURL;
            
            moodMap.put(mMoodsList.get(i).moodId, mMoodsList.get(i));
            
            ImagesDownloader imagesDownloader = new ImagesDownloader(i, imageUrl,mMoodsList.get(i).moodId,2, new Handler(this));
            
            imagesDownloader.setImageDownloadListner(ImageDownloadManager.this);
            
            executor.execute(imagesDownloader);
        }
		
	}
	
	
	@Override
    public boolean handleMessage(Message msg) {
       
        return true;
    }


	@Override
	public void onSuccess(Bitmap bmp, String imageId,int imageType) {
		
		
		
		if(imageType==1)
		{
			Room_Model roomModel = roomsMap.get(imageId);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();  
			bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);   
	        byte[] photo = baos.toByteArray();
	        
	        
	        roomModel.picture=photo;
	        
	        roomModel.isPictureSyncedWithServer=AppKeys.KEY_IS_SYNCED;
	        
	        roomModel.save();
		}
		else if(imageType==2)
		{
			Mood_Model moododel = moodMap.get(imageId);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();  
			bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);   
	        byte[] photo = baos.toByteArray();
	        
	        
	        moododel.picture=photo;
	        
	        moododel.isPictureSyncedWithServer=AppKeys.KEY_IS_SYNCED;
	        
	        moododel.save();
			
			
		}
		
		
	}


	@Override
	public void onFailed(String imageId,int imageType) {
		
		
		
		
	}
	
	
	

}
