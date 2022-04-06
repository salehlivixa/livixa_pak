package com.livixa.apacam.client.activity;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;

import com.activeandroid.query.Select;
import com.afollestad.materialdialogs.MaterialDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue.CustomDialogueClickListner;
import com.livixa.apacam.dbmodel.Manage_DB_Model;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.dbmodel.User_Room_Model;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.R;
import com.livixa.client.R.layout;
import object.p2pipcam.adapter.RoomsGridViewAdapter;

public class RoomActivity extends Activity implements OnClickListener{

	TextView tv_cancel;
	TextView tv_Done;
	EditText et_roomName;
	TextView tv_Camera;
	TextView tv_Photoes;
	ImageView iv_img_blob;
	ImageView bed,bathRoom,guest,dining,living,kitchen,garag,gym;
	public static int REQUEST_IMAGE_CAPTURE=1;
	
	private  Room_Model mRoomModel;
	private int PICK_PHOTO_FOR_AVATAR=2;
	
	private boolean mIsRoomImgaeChanged=false;
	
	private boolean  mIsRoomEditing=false;
	
	private boolean  mIsRoomNameEdited=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(layout.activity_room);
		
		 
		 
		 initLayouts();
		 
		 handleIntent();
		 
		 Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
	}
	
	
	
	private void initLayouts()
	{
		 tv_cancel=(TextView)findViewById(R.id.tv_Cancel);
		 tv_Done=(TextView)findViewById(R.id.tv_Done);
		 et_roomName=(EditText)findViewById(R.id.roomName);
		 tv_Camera=(TextView)findViewById(R.id.tv_Camera);
		 tv_Photoes=(TextView)findViewById(R.id.tv_Photoes);
		 iv_img_blob=(ImageView)findViewById(R.id.iv_img_blob);
		 
		 bed        =(ImageView)findViewById(R.id.bedSmall);
		 bathRoom   =(ImageView)findViewById(R.id.bathroomSmall);
		 guest       =(ImageView)findViewById(R.id.gestSmall);
		 dining     =(ImageView)findViewById(R.id.diningSmall);
		 living     =(ImageView)findViewById(R.id.bedLiving);
		 kitchen    =(ImageView)findViewById(R.id.kitchenSmall);
		 garag      =(ImageView)findViewById(R.id.garagSmall);
		 gym        =(ImageView)findViewById(R.id.gynSmall);
		 
		 tv_cancel.setOnClickListener(this);
		 tv_Done.setOnClickListener(this);
		 tv_Camera.setOnClickListener(this);
		 tv_Photoes.setOnClickListener(this);
		 
		 bed.setOnClickListener(this);
		 bathRoom.setOnClickListener(this);
		 guest.setOnClickListener(this);
		 dining.setOnClickListener(this);
		 living.setOnClickListener(this);
		 kitchen.setOnClickListener(this);
		 garag.setOnClickListener(this);
		 gym.setOnClickListener(this);
	}
	

	@Override
	public void onClick(View view) {
		
		
		switch (view.getId()) {
		case R.id.tv_Cancel:
			Intent intentt=new Intent(RoomActivity.this, Add_Edit_RoomsActivity.class);
			startActivity(intentt);
			overridePendingTransition(0,0);
			finish();
			break;
		case R.id.tv_Done:
			
			
			     
				if(mRoomModel!=null)
				{
					
					if(et_roomName.getText().toString().trim().length() == 0)
					{
						showErrorDialogue(getResources().getString(R.string.Roomnameisrequired));
						return ;
					}
					
					
					try
					{
						
						if(mIsRoomImgaeChanged)
						{
						    Bitmap tempBitmap=getBitmap(iv_img_blob);
						    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
							tempBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);   
					        byte[] photo = baos.toByteArray();
						    mRoomModel.picture=photo;
						    mRoomModel.isPictureSyncedWithServer="0";
						    
						}
					    
					    
					    
					    if(!mRoomModel.title.equals(et_roomName.getText().toString().trim()))
					    {
					    	mRoomModel.title=et_roomName.getText().toString().trim();
					    	mIsRoomNameEdited=true;
					    }
					    else
					    {
					    	mIsRoomNameEdited=false;
					    }
					    
					    if(!mIsRoomEditing)   // if room creation case than 
					    {
					    
						    if(addRoomToDb(mRoomModel)==null)
						    {
						    	showErrorDialogue(getResources().getString(R.string.Roomnamealreadyexist));
						    	return;
						    }
					    }
					    else
					    {
					    	mRoomModel.isSyncedWithServer="0";
					    	mRoomModel.modelStatus=AppKeys.KEY_IS_UPDATED;
					    	if(mIsRoomImgaeChanged)
					    	{
					    		mRoomModel.isPictureSyncedWithServer="0";
					    		
					    	}
					    	
					    	
					    	
					    	if(mIsRoomNameEdited && updateRoomToDb(mRoomModel)==null)
						    {
						    	showErrorDialogue("Room name already exist!");
						    	return;
						    }
					    	else
					    	{
					    		mRoomModel.save();
					    		User_Room_Model user_Room_Model=null;
					    		try
								   {
						    		user_Room_Model = new Select().from(User_Room_Model.class).where("User_Room_Model.room_id = ? AND userId = ?",mRoomModel.room_id.trim(),AppPreference.getValue(RoomActivity.this, AppKeys.KEY_USER_ID)).executeSingle();
						    		user_Room_Model.isSyncedWithServer="0";
						    		user_Room_Model.modelStatus=mRoomModel.modelStatus;
						    		user_Room_Model.save();
						    		
								   }catch(Exception ex)
								   {
									   ex.toString();
								   }
					    	}
					    	
					    }
					    
					}catch(Exception ex){}
				}
			Intent intenttt=new Intent(RoomActivity.this, Add_Edit_RoomsActivity.class);
			startActivity(intenttt);
			overridePendingTransition(0,0);
			finish();		
			break;
		case R.id.tv_Camera:
			Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
		        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
		    }
			break;
		case R.id.tv_Photoes:
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			  intent.setType("image/*");
			  startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
			break;
		case R.id.kitchenSmall:
			iv_img_blob.setImageResource(R.drawable.icon_kitchen_large);
			mIsRoomImgaeChanged=true;
			break;
		case R.id.bedSmall:
			iv_img_blob.setImageResource(R.drawable.icon_bedroom_large);
			mIsRoomImgaeChanged=true;
			break;
		case R.id.bathroomSmall:
			iv_img_blob.setImageResource(R.drawable.icon_bathroom_larg);
			mIsRoomImgaeChanged=true;
			break;
		case R.id.gestSmall:
			iv_img_blob.setImageResource(R.drawable.icon_guest_large);
			mIsRoomImgaeChanged=true;
			break;
		case R.id.diningSmall:
			iv_img_blob.setImageResource(R.drawable.icon_dinning_large);
			mIsRoomImgaeChanged=true;
			break;
		case R.id.gynSmall:
			iv_img_blob.setImageResource(R.drawable.icon_gym_large);
			mIsRoomImgaeChanged=true;
			break;
		case R.id.bedLiving:
			iv_img_blob.setImageResource(R.drawable.icon_living_large);
			mIsRoomImgaeChanged=true;
			break;
		case R.id.garagSmall:
			iv_img_blob.setImageResource(R.drawable.icon_garage_large);
			mIsRoomImgaeChanged=true;
			break;

		default:
			break;
		}
		
	}

	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
	        Bundle extras = data.getExtras();
	        Bitmap imageBitmap = (Bitmap) extras.get("data");
	        
	        /*ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);   
	        byte[] photo = baos.toByteArray();*/
	        
	        mIsRoomImgaeChanged=true;
	        Bitmap resized = Bitmap.createScaledBitmap(imageBitmap, 150, 200, true);
	        iv_img_blob.setImageBitmap(resized);
	    }
	    else if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == RESULT_OK) {
	    	
	    	 Uri selectedImage = data.getData();
	    	
	    	 
	         Bitmap bitmap = null;
			try {
				bitmap = getBitmapFromUri(selectedImage);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
			Bitmap resized = Bitmap.createScaledBitmap(bitmap, 150, 200, true);
			mIsRoomImgaeChanged=true;
	        iv_img_blob.setImageBitmap(resized);
	    }
	}
	
	private Bitmap getBitmap(ImageView img)
	{
		Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
		
		return bitmap;
	}
	
	private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
             getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
	}
	
	public void handleIntent()
	{
		try
		{

			 Intent intent=getIntent();
			 
			 boolean isRoomAreadyNotExists=intent.getBooleanExtra(AppKeys.KEY_IS_CREATED, true);
			 
			 if(isRoomAreadyNotExists)
			 {
				 mRoomModel=new Room_Model();
				 mIsRoomEditing=false;
			 }
			 else
			 {
				 String roomId=intent.getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
				 mRoomModel=getRoomFromDb(roomId);
				 
				 if(mRoomModel!=null)
				 {
					 mIsRoomEditing=true;
					 et_roomName.setText(mRoomModel.title);
					 if(mRoomModel.picture!=null && mRoomModel.picture.length > 0)
					 {
					         iv_img_blob.setImageBitmap(RoomsGridViewAdapter.bytesToBitmap(mRoomModel.picture));
					 }
					 else if(mRoomModel.pictureURL!=null && mRoomModel.pictureURL.trim().length() > 0 )
						{
						 ImageLoader  imageLoader=ImageLoader.getInstance();
						  imageLoader.displayImage(mRoomModel.pictureURL,iv_img_blob,KisafaApplication.getImageDisplayOption());
						}
					
				 }
				 
				 
			 }
		}catch(Exception ex)
		{
			ex.toString();
		}
	}
	
	
	private Room_Model getRoomFromDb(String roomId)
	{
		Room_Model roomModel=null;
		
		try
		   {
	             roomModel = new Select().from(Room_Model.class).where("Room_Model.room_id = ?",roomId).executeSingle();
		   }catch(Exception ex)
		   {
			   ex.toString();
		   }
		
		return roomModel;
	}
	
	
	
	
	
	private Room_Model addRoomToDb(Room_Model room)
	{
		Room_Model roomModel=null;
		
		Room_Model temproomModelStatus=null;
		//
		try
		{
		   try
		   {
	             roomModel = new Select().from(Room_Model.class).where("Room_Model.title COLLATE NOCASE = ?",room.title.trim()).executeSingle();
		   }catch(Exception ex)
		   {
			   ex.toString();
		   }
		   
	       if(roomModel==null)
	       {
	    	   roomModel=room; 
	    	   roomModel.room_id=Manage_DB_Model.getUnitTimeStampForRoom();
	    	   //roomModel.user_id=AppPreference.getValue(RoomActivity.this, AppKeys.KEY_USER_ID);
	    	   roomModel.modelStatus=AppKeys.KEY_IS_CREATED;
	    	   roomModel.isSyncedWithServer="0";
		      
		       roomModel.save();
		       
		       User_Room_Model  user_Room_Model=new User_Room_Model();
		       
		       user_Room_Model.isSyncedWithServer="0";
		       user_Room_Model.modelStatus=AppKeys.KEY_IS_CREATED;
		       user_Room_Model.userId=AppPreference.getValue(RoomActivity.this, AppKeys.KEY_USER_ID);
		       user_Room_Model.room_id=room.room_id;
		       user_Room_Model.user_room_id=Manage_DB_Model.getUnitTimeStampForRoomUser();
		       user_Room_Model.save();
		       
		       temproomModelStatus=roomModel;
	       }
	       else 
	       {
	    	    if(roomModel.modelStatus.equals(AppKeys.KEY_IS_DELETED))
	    	    {
		    	    roomModel.modelStatus=AppKeys.KEY_IS_UPDATED;
		    	    roomModel.isSyncedWithServer="0";
		    	   
			    	roomModel.save();
			    	
			    	User_Room_Model  user_Room_Model=null;
			    	
			    	try
					   {
			    		user_Room_Model = new Select().from(User_Room_Model.class).where("User_Room_Model.room_id = ? AND userId = ?",room.room_id.trim(),AppPreference.getValue(RoomActivity.this, AppKeys.KEY_USER_ID)).executeSingle();
					   }catch(Exception ex)
					   {
						   ex.toString();
					   }
					
			    	if(user_Room_Model!=null)
			    	{
			    		user_Room_Model.isSyncedWithServer="0";
			    		user_Room_Model.userId=AppPreference.getValue(RoomActivity.this, AppKeys.KEY_USER_ID);
			    		user_Room_Model.modelStatus=AppKeys.KEY_IS_UPDATED;
			    		user_Room_Model.save();
			    	}
			    	
			    	temproomModelStatus=roomModel;
	    	    }
	       }
	       
	    	
	    	
	      
		}catch(Exception ex)
		{
			ex.toString();
			return temproomModelStatus;
		}
		
		return temproomModelStatus;
	}
	
	
	private Room_Model updateRoomToDb(Room_Model room)
	{
		Room_Model roomModel=null;
		
		
		//
		try
		{
		   try
		   {
	             roomModel = new Select().from(Room_Model.class).where("Room_Model.title COLLATE NOCASE = ?",room.title.trim()).executeSingle();
		   }catch(Exception ex)
		   {
			   ex.toString();
		   }
		   
	       if(roomModel==null)
	       {
	    	   roomModel=room; 
	    	   roomModel.save();
		       
	       }
	       else
	       {
	    	   roomModel=null;
	       }
	       
	    	
	    	
	      
		}catch(Exception ex)
		{
			ex.toString();
			return roomModel;
		}
		
		return roomModel;
	}
	
	private void showErrorDialogue(String message) {
		/*MaterialDialog dialog = new MaterialDialog.Builder(RoomActivity.this).content(message)
				.positiveText(android.R.string.ok)
				.positiveColor(KisafaApplication.getAppResources().getColor(R.color.app_header_bg))
				.callback(new MaterialDialog.ButtonCallback() {
					@Override
					public void onPositive(MaterialDialog dialog) {

					}
				}).build();
		dialog.show();*/
		
		final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(RoomActivity.this,message);
		
		cusDial.setListner(new CustomDialogueClickListner() {
			
			@Override
			public void onCustomDialogueClick() {
				
				
				cusDial.dismiss();
				
				
				
				
			}
		});
		
		
		cusDial.show();

		
	}
	
	
	@Override
	public void onBackPressed() {
		 
		super.onBackPressed();
		
		Intent intenttt=new Intent(RoomActivity.this, Add_Edit_RoomsActivity.class);
		startActivity(intenttt);
		overridePendingTransition(0,0);
		finish();
	}
	
	
}
