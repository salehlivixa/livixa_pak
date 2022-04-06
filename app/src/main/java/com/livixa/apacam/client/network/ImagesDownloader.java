package com.livixa.apacam.client.network;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
 
import java.io.InputStream;
 
public class ImagesDownloader implements Runnable {
 
    int threadNo;
    Handler handler;
    String imageUrl;
    public static final String TAG = "LongThread";
    
    String  imageId;
    
    private ImageResult listner;
    
    int imagesType=0;
    
 
    public ImagesDownloader() {
    }
 
    public ImagesDownloader(int threadNo, String imageUrl,String imageId,int imagesType, Handler handler) {
        this.threadNo = threadNo;
        this.handler = handler;
        this.imageUrl = imageUrl;
        this.imageId = imageId;
        this.imagesType=imagesType;
    }
    @Override
    public void run() {
        Log.i(TAG, "Starting Thread : " + threadNo);
        Bitmap temp=getBitmap(imageUrl);
        
        if(listner!=null && temp!=null)
        {
        	listner.onSuccess(temp, imageId,imagesType);
        }
        
        if(listner!=null && temp==null)
        {
        	listner.onFailed(imageId,imagesType);
        }
        
       
    }
 
 
    public void sendMessage(int what, String msg) {
        Message message = handler.obtainMessage(what, msg);
        message.sendToTarget();
    }
 
    private Bitmap getBitmap(String url) {
        Bitmap bitmap = null;
        try {
            // Download Image from URL
            InputStream input = new java.net.URL(url).openStream();
            // Decode Bitmap
            bitmap = BitmapFactory.decodeStream(input);
            // Do extra processing with the bitmap
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    
    
    public  interface ImageResult
    {
    	
    	public void onSuccess(Bitmap bmp,String imageId,int imageType);
    	
    	
    	public void onFailed(String imageId,int imageType);
    	
    	
    	
    }
    
    public void setImageDownloadListner(ImageResult listner)
    {
    	this.listner=listner;
    }
    
 
}


