package com.livixa.apacam.client.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONObject;

import com.livixa.client.R;
import object.p2pipcam.adapter.ViewRoomsGridViewAdapter;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import com.livixa.apacam.client.activity.RegisterActivity.BitmapDownloaderTask;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaActivity;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.AppWebServices;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.login.LoginResponse;
import com.livixa.apacam.client.response.register.RegisterResponse;
import com.livixa.apacam.client.utility.ACache;
import com.livixa.apacam.client.utility.AppConstants;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.AppUtil;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.services.Sync_Service;
import retrofit2.Call;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.github.mrengineer13.snackbar.SnackBar;
import com.kisafa.user.profile.CircleImageView;
import com.kisafa.user.profile.CityPicker;
import com.kisafa.user.profile.CountryPicker;
import com.kisafa.user.profile.CountryPickerListener;
import com.kisafa.user.profile.LocationAddress;
import com.kisafa.user.profile.USER_Model;
import com.kisafa.user.profile.UpdateProfileResponse;
import com.nostra13.universalimageloader.core.ImageLoader;

public class UpdateProfileActivity extends FragmentActivity implements
		OnClickListener, ServerConnectListener , LocationListener {

	// Layout Variables
	private View view;
	private EditText mEtUsername;
	private EditText mEtEmail;
	private EditText mEtFullName;
	private EditText mEtPhoneNo;
	
	private Button mBtnRegister;
	private TextView mEtCity;
	private TextView mEtCountry;
	private CircleImageView    iv_profile;
	
	private ProgressDialog mProgressDialog;
	
	RelativeLayout welComeScreen;

	// Local
	private String ACTIVITY_TITLE = "Register";
	private Map<String, Object> map;
	private UpdateProfileActivity mContext;
	private boolean canGetLocation;
	
	public static int REQUEST_IMAGE_CAPTURE=1;
	private static int PICK_PHOTO_FOR_AVATAR=2;
	private static int MEDIA_TYPE_IMAGE=1;
	private static int MEDIA_TYPE_VIDEO=2;
	
	private boolean mIsRoomImgaeChanged=false;
	
	
	private  String  mCountryName = "";
	
	private  String  mCountryId = "";
	
	private  String  mCity = "";
	
	
	private File profileImagePath=null;
	
	
	

	// Class Methods
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(savedInstanceState);
		setOrientation();
		setContentView(R.layout.activity_update_profile);
		initComponents();
		//getSupportActionBar().hide();
		setClickListner(this);
		
		
		
		mEtUsername.setText(AppPreference.getValue(mContext, AppKeys.KEY_USER_NAME));
		mEtFullName.setText(AppPreference.getValue(mContext, AppKeys.KEY_USER_FULL_NAME));
		mEtEmail.setText(AppPreference.getValue(mContext, AppKeys.KEY_USER_EMAIL));
		mEtPhoneNo.setText(AppPreference.getValue(mContext, AppKeys.KEY_USER_PHONE));
		mEtCountry.setText(AppPreference.getValue(mContext, AppKeys.KEY_USER_COUNTRY));
		mEtCity.setText(AppPreference.getValue(mContext, AppKeys.KEY_USER_CITY));
		
		mCountryId=AppPreference.getValue(this, AppKeys.KEY_USER_COUNTYY_CODE);
		
		/*welComeScreen.postDelayed( new Runnable() {
			public void run() {
				
				getLocation(LocationManager.GPS_PROVIDER);
				
			}
		}, 300);*/
		
		
		setProfileImage(iv_profile);
		
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
	}

	// Helping Methods
	public void initComponents() {
		//super.initUIComponents(ACTIVITY_TITLE);
		mContext = this;
		view = (View) findViewById(R.id.rl_root);
		mBtnRegister = (Button) findViewById(R.id.btn_register);
		mEtUsername = (EditText) findViewById(R.id.et_username);
		
		mEtFullName = (EditText) findViewById(R.id.et_fullname);
		mEtPhoneNo = (EditText) findViewById(R.id.et_phone);
		mEtEmail = (EditText) findViewById(R.id.et_email);
		
		
		mEtCity=   (TextView) findViewById(R.id.et_city);
		mEtCountry=   (TextView) findViewById(R.id.et_country);
		iv_profile=   (CircleImageView) findViewById(R.id.iv_profile);
		
		
		welComeScreen=(RelativeLayout) findViewById(R.id.welComeScreen);
	}

	public void setClickListner(OnClickListener onclick) {
		mBtnRegister.setOnClickListener(onclick);
	}

	public void setOrientation() {
		if (AppPreference.getSavedData(mContext, AppKeys.KEY_IS_LANDSCAPE)) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
	}

	public void callIntentWithFlag() {
		Intent intent = new Intent();
		intent = new Intent(mContext, HomeActivity.class);
		
		startActivity(intent);
		overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
		
	}

	public void verifyData() {
		AppUtil.hideKeyBoard(mContext, mEtEmail);
		
		boolean flag = setErrorMsg(mEtUsername,getString(R.string.Error_MSG_UserName));
		
			if (!flag) {
				flag = setErrorMsg(mEtEmail, getString(R.string.Emailisrequired));
				
				if (!flag) {
					flag = setErrorMsg(mEtFullName,getString(R.string.Fullnameisrequired));
					if (!flag) {
						flag = setErrorMsg(mEtPhoneNo, getString(R.string.Cellisrequired));
						
						if (!flag) {
							flag = setErrorMsg(mEtCountry,getString(R.string.Countryisrequired));
						
						
						if (!flag) {
							flag = setErrorMsg(mEtCity, getString(R.string.Cityisrequired));
						
						
						
						if (!flag) {
							if (AppUtil.isEmailValid(mEtEmail.getText()
									.toString())) {
								
								
								welComeScreen.postDelayed(new Runnable() {
									
									@Override
									public void run() {
										
										showProgressDialog("", 100);
										
									}
								}, 100);
								
								
								
								String profileImage="";
								
								String timeZoneId="";
								String timeZoneName="";
								
								
								
								
								
								
								
								timeZoneId=AppPreference.getValue(mContext, AppKeys.KEY_USER_TIME_ZONE);
								timeZoneName=AppPreference.getValue(mContext, AppKeys.KEY_USER_TIME_ZONE_NAME);
								
								
								
							
								
								validateLogin(mEtUsername.getText().toString(),
										mEtFullName.getText().toString(),
										mEtPhoneNo.getText().toString(),
										"",
										mEtEmail.getText().toString()
										.toLowerCase(),profileImage,mEtCountry.getText().toString(),mCountryId,mEtCity.getText().toString(),timeZoneId,timeZoneName);
								
								
								
								
							} else {
								mEtEmail.setError(getString(R.string.Emailisnotvalid));
								mEtEmail.requestFocus();
							}
						}
					}
				}
			}
		}
	}
		
	}

	private boolean setErrorMsg(EditText editText, String msg) {
		if (editText.getEditableText().toString().equals("")) {
			editText.requestFocus();
			editText.setError(msg);
			return true;
		}
		return false;
	}
	
	private boolean setErrorMsg(TextView editText, String msg) {
		if (editText.getText().toString().equals("")) {
			editText.requestFocus();
			editText.setError(msg);
			return true;
		}
		return false;
	}

	private void showProgressDialog(String text, int progress) {
		/*mProgressDialog = new ProgressDialog(mContext,R.style.progressDialogTheme);
		mProgressDialog.setMessage(text);
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(progress);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();*/
		
		WaitingStaticProgress.showProgressDialog(text, UpdateProfileActivity.this);
	}

	// Request
	public void validateLogin(String username, String fullname, String phone,
			String password, String email,String binaryProfileImage,String country_name,String country_code,String city,String time_zone,String time_zone_name) {
		
		
		
		
		//https://futurestud.io/tutorials/retrofit-2-how-to-upload-multiple-files-to-server
		
		MultipartBody.Part body=null;
		if(profileImagePath!=null && profileImagePath.length()>0)
		{
		RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), profileImagePath);
		body = MultipartBody.Part.createFormData("profile_image", profileImagePath.getName(), reqFile);
		}
		
		RequestBody session = createPartFromString(AppPreference.getValue(mContext, AppKeys.KEY_SESSION));  
		RequestBody full_name = createPartFromString(fullname);  
		RequestBody phone_number = createPartFromString(phone);
		
		RequestBody countryname = createPartFromString(country_name);
		RequestBody countrycode = createPartFromString(country_code);
		RequestBody City = createPartFromString(city);
		
		RequestBody timeZone = createPartFromString(time_zone);
		RequestBody timeZoneName = createPartFromString(time_zone_name);
		
		RequestBody _email= createPartFromString(email);
		

		HashMap<String, RequestBody> mymap = new HashMap<>();  
		mymap.put("session", session);  
		try
		{
		map.put(AppKeys.KEY_CURRENT_LANGUAGE, AppPreference.getValue(this, AppKeys.KEY_CURRENT_LANGUAGE));
		}catch(Exception ex){}
		mymap.put("full_name", full_name);  
		mymap.put("email", _email); 
		mymap.put("phone_number", phone_number);
		mymap.put("country_name", countryname);
		mymap.put("country_code", countrycode);
		mymap.put("city", City);
		
		mymap.put("time_zone", timeZone);
		mymap.put("time_zone_name", timeZoneName);
		
		
		
		ApiService service = KisafaApplication.getRestClient().getApiService();
		Call<UpdateProfileResponse> call = service.editProfile(mymap,body);
		call.enqueue(new RestCallback<UpdateProfileResponse>(this,ServerCodes.ServerRequestCodes.UPDATE_PROFILE_REQUEST_CODE, mContext));
		
	}
	
	public static final String MULTIPART_FORM_DATA = "multipart/form-data";

	@NonNull
	private RequestBody createPartFromString(String descriptionString) {  
	    return RequestBody.create(
	            MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
	}
	
	public static MultipartBody uploadRequestBody(File file) {
		
		
		RequestBody photo = RequestBody.create(MediaType.parse("application/image"), file);
	    return new MultipartBody.Builder()
	            .setType(MultipartBody.FORM)
	            .addFormDataPart("upload", file.getName(),photo)
	            .build();
	}

	@Override
	public void onSuccess(ServerResponse response) {
		/*if (mProgressDialog != null) {
			mProgressDialog.hide();
		}*/
		
		WaitingStaticProgress.hideProgressDialog();
		
		if (response.getRequestCode() == ServerCodes.ServerRequestCodes.UPDATE_PROFILE_REQUEST_CODE) {
			UpdateProfileResponse registerResponse = (UpdateProfileResponse) response;
			if (registerResponse.getShMeta().getShErrorCode()
					.equalsIgnoreCase("0")) {
				
				
				//if(registerResponse.getShResult().getShProfileImage()!=null && registerResponse.getShResult().getShProfileImage().length() > 0)
				{
				
				/*BitmapDownloaderTask bitmapDownloaderTask=new BitmapDownloaderTask(iv_profile,AppWebServices.BASE_URL + registerResponse.getShResult().getShProfileImage(), "profile",registerResponse.getShMeta().getShMessage());
				bitmapDownloaderTask.execute();*/
					
					
					try
					{
						
						//AppPreference.saveValue(mContext,registerResponse.getShResult().getShUsername(),AppKeys.KEY_USER_NAME);
						AppPreference.saveValue(mContext, registerResponse.getShResult().getShFullName(),AppKeys.KEY_USER_FULL_NAME);
						//AppPreference.saveValue(mContext,registerResponse.getShResult().getShEmail(), AppKeys.KEY_USER_EMAIL);
						AppPreference.saveValue(mContext,registerResponse.getShResult().getShPhoneNumber(), AppKeys.KEY_USER_PHONE);
						AppPreference.saveValue(mContext,registerResponse.getShResult().getSh_country_name(), AppKeys.KEY_USER_COUNTRY);
						AppPreference.saveValue(mContext,registerResponse.getShResult().getSh_city(), AppKeys.KEY_USER_CITY);
						
						
						
					}catch(Exception ex)
					{
						
					}
					
					try
			        {
			        USER_Model   user =USER_Model.GetUser();
			        
			       	
				        
				        if(user==null)
				        {
					        user=new USER_Model();
					        user.sh_user_id=USER_Model.USER_TEMP_ID;
				        }
				        
				        if(registerResponse.getShResult().getShProfileImage()!=null && registerResponse.getShResult().getShProfileImage().length() > 0)
				            user.profile_url=registerResponse.getShResult().getShProfileImage();
			        	
			        	user.save();
			        
			        }catch(Exception ex)
			        {
			        	ex.toString();
			        }
				
					
					WaitingStaticProgress.hideProgressDialog();
					
					onFailure(getString(R.string.updatedsuccessfully));
					
				
				}
				/*else
				{
					WaitingStaticProgress.hideProgressDialog();
					
					onFailure(registerResponse.getShMeta().getShMessage());
				}
				*/
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
		/*if (mProgressDialog != null) {
			mProgressDialog.hide();
		}*/
		
		WaitingStaticProgress.hideProgressDialog();
		
		new SnackBar.Builder((Activity) mContext).withMessage(retrofitError)
				.withDuration(SnackBar.SHORT_SNACK).show();
	}

	// Override Methods
	@Override
	public void onClick(View v) {
		 
		switch (v.getId()) {
		case R.id.btn_register:
			verifyData();
			break;
		default:
			break;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void onBackPressed() {
		 
		super.onBackPressed();
		
		finish();
		
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		KisafaApplication.perFormActivityBackTransition(this);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	public void onbackButttonClick(View view)
	{
		finish();
		
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		KisafaApplication.perFormActivityBackTransition(this);
	}
	
	
	
	public void showWelComeScreen(final View view)
	{
		
		view.setVisibility(View.VISIBLE);
		view.postDelayed(new Runnable() {
			
			@Override
			public void run() 
			{
				//view.setVisibility(View.GONE);
				callIntentWithFlag();
				
			}
		}, 2000);
		
	}
	
	
	
	
	
	public Location getLocation() {
		
		 Location location = null;
		 
	    try {
	    	
	    	 LocationManager locationManager;
			
	         final long MIN_DISTANCE_FOR_UPDATE = 10;
			 final long MIN_TIME_FOR_UPDATE = 1000 * 60 * 2;
			 double latitude = 0.0;
             double longitude = 0.0;
			 
	        locationManager = (LocationManager) mContext
	                .getSystemService(LOCATION_SERVICE);

	        // getting GPS status
	        boolean isGPSEnabled = locationManager
	                .isProviderEnabled(LocationManager.GPS_PROVIDER);

	        // getting network status
	        boolean isNetworkEnabled = locationManager
	                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

	        if (!isGPSEnabled && !isNetworkEnabled) {
	            // no network provider is enabled
	        } else {
	            this.canGetLocation = true;
	            if (isNetworkEnabled) {
	                locationManager.requestLocationUpdates(
	                        LocationManager.NETWORK_PROVIDER,
	                        MIN_TIME_FOR_UPDATE,
	                        MIN_DISTANCE_FOR_UPDATE, this);
	                
	                if (locationManager != null) {
	                    location = locationManager
	                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	                    if (location != null) {
	                        latitude = location.getLatitude();
	                        longitude = location.getLongitude();
	                    }
	                }
	            }
	            // if GPS Enabled get lat/long using GPS Services
	            if (isGPSEnabled) {
	                if (location == null) {
	                    locationManager.requestLocationUpdates(
	                            LocationManager.GPS_PROVIDER,
	                            MIN_TIME_FOR_UPDATE,
	                            MIN_DISTANCE_FOR_UPDATE, this);
	                   
	                    if (locationManager != null) {
	                        location = locationManager
	                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
	                        if (location != null) {
	                            latitude = location.getLatitude();
	                            longitude = location.getLongitude();
	                        }
	                    }
	                }
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return location;
	}
	
	public void getLocation(String provider)
	{
		
		 Location location = getLocation();
         
        try
        {
		 if(location!=null)
		 {
			 Geocoder geocoder;
			 List<Address> addresses=null;
			 geocoder = new Geocoder(this, Locale.getDefault());
	
			 try {
				addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Here 1 represent max location result to returned, by documents it recommended 1 to 5
	
			 String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
			 String city = addresses.get(0).getLocality();
			 String state = addresses.get(0).getAdminArea();
			 String country = addresses.get(0).getCountryName();
			 String postalCode = addresses.get(0).getPostalCode();
			 String knownName = addresses.get(0).getFeatureName();
			 
			 mCountryId=addresses.get(0).getCountryCode();
			 
			 mEtCountry.setText(country);
			 
			 if(city==null)
			 mEtCity.setText(knownName);
			 else
			  mEtCity.setText(city);
			
			 
			 //String completeAddress= address + city+  state + country + postalCode +  knownName;
		 }
        
        }catch(Exception ex)
        {
        	
        }
		
	}
	
	
	private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            
            Toast.makeText(mContext, locationAddress, Toast.LENGTH_SHORT).show();
        }
    }

	@Override
	public void onLocationChanged(Location location) {
		 
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		 
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		 
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		 
		
	}
	
	public void onCameraClick(View view)
	{
		
		AppUtil.hideKeyBoard(mContext, mEtEmail);
		//AppUtil.hideKeyBoard(mContext, mEtPassword);
		AppUtil.hideKeyBoard(mContext, mEtFullName);
		AppUtil.hideKeyBoard(mContext, mEtUsername);
		AppUtil.hideKeyBoard(mContext, mEtPhoneNo);
		
		view.requestFocus();
		
		callPopup();
		
		
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
	        Bitmap resized = Bitmap.createScaledBitmap(imageBitmap, 200, 200, true);
	        iv_profile.setImageBitmap(resized);
	        
	        
	        Bitmap tempBitmap=resized;
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
			tempBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);   
	        byte[] photo = baos.toByteArray();
	        
	        File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
	        
	        profileImagePath=pictureFile;
	        
	        if (pictureFile == null){
	           
	            return;
	        }

	        try {
	            FileOutputStream fos = new FileOutputStream(pictureFile);
	            fos.write(photo);
	            fos.close();
	        } catch (FileNotFoundException e) {
	           // Log.d(TAG, "File not found: " + e.getMessage());
	        } catch (IOException e) {
	            
	        }
	        
	        
	        
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
	        
			    mIsRoomImgaeChanged=true;
		        Bitmap resized = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
		        iv_profile.setImageBitmap(resized);
		        
		        
		        Bitmap tempBitmap=resized;
			    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
				tempBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);   
		        byte[] photo = baos.toByteArray();
		        
		        File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
		        
		        profileImagePath=pictureFile;
		        
		        if (pictureFile == null){
		           
		            return;
		        }

		        try {
		            FileOutputStream fos = new FileOutputStream(pictureFile);
		            fos.write(photo);
		            fos.close();
		        } catch (FileNotFoundException e) {
		           // Log.d(TAG, "File not found: " + e.getMessage());
		        } catch (IOException e) {
		            
		        }
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
	
	String toBinary( byte[] bytes )
	{
	    StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
	    for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
	        sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
	    return sb.toString();
	}

	byte[] fromBinary( String s )
	{
	    int sLen = s.length();
	    byte[] toReturn = new byte[(sLen + Byte.SIZE - 1) / Byte.SIZE];
	    char c;
	    for( int i = 0; i < sLen; i++ )
	        if( (c = s.charAt(i)) == '1' )
	            toReturn[i / Byte.SIZE] = (byte) (toReturn[i / Byte.SIZE] | (0x80 >>> (i % Byte.SIZE)));
	        else if ( c != '0' )
	            throw new IllegalArgumentException();
	    return toReturn;
	}
	
	
	private static File getOutputMediaFile(int type){
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "MyCameraApp");
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
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    } else if(type == MEDIA_TYPE_VIDEO) {
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "VID_"+ timeStamp + ".mp4");
	    } else {
	        return null;
	    }

	    return mediaFile;
	}
	
	
	public void onCountryClick(View view)
	{
		final CountryPicker picker = CountryPicker.newInstance("SelectCountry");
		 picker.show(getSupportFragmentManager(), "COUNTRY_CODE_PICKER");
		 
		 picker.setListener(new CountryPickerListener() {
			   
				@Override
				public void onSelectCountry(String name, String code) {
					
					picker.dismiss();
					
					mEtCountry.setText(name);
					mCountryId=code;
					
					//Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
					
					
				}
			});
		 
		 
	}
	
	
	public void onCityClick(View view)
	{
		
		
		boolean	flag = setErrorMsg(mEtCountry, "Enter country first");
		
		if(flag)
		{
			return;
		}
		
		GetCites tast=new GetCites();
		
		tast.execute(mEtCountry.getText().toString());
		
	}
	
	
	class GetCites extends AsyncTask<String, Void, ArrayList<String>>
	{
	    String TAG = getClass().getSimpleName();

	    protected void onPreExecute (){
	       
	    	WaitingStaticProgress.showProgressDialog("Loading cites...", mContext);
	    }

	   
	    @Override
	    protected void onPostExecute(ArrayList<String> result) {
	    	 
	    	super.onPostExecute(result);
	    	
	    	WaitingStaticProgress.hideProgressDialog();
	    	
	    	
	    	final CityPicker picker = CityPicker.newInstance("SelectCity");
	    	
	    	 picker.setAllCities(result);
	    	
			 picker.show(getSupportFragmentManager(), "COUNTRY_CODE_PICKER");
			 
			 picker.setListener(new CountryPickerListener() {
				   
					@Override
					public void onSelectCountry(String name, String code) {
						
						picker.dismiss();
						
						
						
						mEtCity.setText(name);
						
					}
				});
	    	
	    	
	    	
	    }
	   
		@Override
		protected ArrayList<String> doInBackground(String... params) {
			
			ArrayList<String>  citesArray=new ArrayList<>();
			
			
			
			try {
			    
				    JSONObject jObject = new JSONObject(loadJSONFromAsset());
			         
					
					JSONArray mJsonCitiesArray = jObject.getJSONArray(params[0]);
					
					
					for(int cityRunner=0; cityRunner < mJsonCitiesArray.length(); cityRunner++)
					{
						
						citesArray.add((String) mJsonCitiesArray.get(cityRunner).toString().replace("�","a").replaceAll("[\\p{InLatin-1Supplement}]+", ""));//.replace("�","A")
						
					}
					
					
			
			} catch (Exception e) {
			    e.printStackTrace();
			}
			
			
			
			 return citesArray;
			
			
		}
	}
	
	public String loadJSONFromAsset() {
	    String json = null;
	    try {
	        InputStream is = getAssets().open("cities.json");
	        int size = is.available();
	        byte[] buffer = new byte[size];
	        is.read(buffer);
	        is.close();
	        json = new String(buffer, "UTF-8");
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	    return json;
	}
	
	private void callPopup() {

		 LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		 View popupView = layoutInflater.inflate(R.layout.select_photo_or_camera, null);
		
		TextView tv_cancel=(TextView) popupView.findViewById(R.id.tv_cancel);
		
		TextView tv_cameraSelection=(TextView) popupView.findViewById(R.id.tv_cameraSelection);
		
		TextView tv_photosSelection=(TextView) popupView.findViewById(R.id.tv_photosSelection);
		
		
		
		

		final PopupWindow popupWindow=new PopupWindow(popupView,
		         LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,        
		 true);

		 popupWindow.setTouchable(true);
		 popupWindow.setFocusable(true);
		 
		 popupWindow.setAnimationStyle(R.style.BottonUpAnimation);
		 
		

		 popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
		 
		 tv_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
				}
			});
		 
		 
		 tv_cameraSelection.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					
					mIsRoomImgaeChanged=false;
					
					
					
					Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
				        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
				    }
					
					
					
					
				}
			});
		 
		 tv_photosSelection.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					
					
					
					
					
					
					mIsRoomImgaeChanged=false;
					
					
				    
				      Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
					  intent.setType("image/*");
					  startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
				
					
				}
			});
		 
		 
		 
		 
		
	}
	
	
	public class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap> {


	    private String url;
	    private String path, imgName;
	    private final WeakReference<ImageView> imageViewReference;
	    
	    String message;


	    public BitmapDownloaderTask(ImageView imageView, String path, String imgName,String message) {

	        imageViewReference = new WeakReference<ImageView>(imageView);

	        this.path = path;
	        this.imgName = imgName;
	        this.message=message;

	    }//BitmapDownloaderTask


	    @Override
	    // Actual download method, run in the task thread
	    protected Bitmap doInBackground(String... params) {
	        // params comes from the execute() call: params[0] is the url.
	        return getBitmapFromURL(path);

	    }//doInBackground


	    @Override
	    // Once the image is downloaded, associates it to the imageView
	    protected void onPostExecute(Bitmap bitmap) {

	        if (isCancelled()) {
	            bitmap = null;
	        }

	        if (imageViewReference != null && bitmap != null) {
	            ImageView imageView = imageViewReference.get();
	            if (imageView != null) {
	                imageView.setImageBitmap(bitmap);

	               
	            }
	        }
	        
	        
	        try
	        {
	        USER_Model   user =USER_Model.GetUser();
	        
	        if( bitmap!=null)
	        {
	        	ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	        	bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);   
		        byte[] photo = baos.toByteArray();
		        
		        if(user==null)
		        {
		        	user=new USER_Model();
		        	user.sh_user_id=USER_Model.USER_TEMP_ID;
		        	
		        }
	        	user.profile_image=photo;
	        	
	        	user.save();
	        }
	        }catch(Exception ex)
	        {
	        	ex.toString();
	        }
	        
	        WaitingStaticProgress.hideProgressDialog();
	        
	        onFailure(message);

	    }//onPostExecute


	    public  Bitmap getBitmapFromURL(String link) {
	        /* this method downloads an Image from the given URL,
	        *  then decodes and returns a Bitmap object
	        */
	        try {
	            URL url = new URL(link);
	            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	            connection.setDoInput(true);
	            connection.connect();
	            InputStream input = connection.getInputStream();
	            Bitmap myBitmap = BitmapFactory.decodeStream(input);

	            return myBitmap;

	        } catch (IOException e) {
	            e.printStackTrace();
	            Log.e("getBmpFromUrl error: ", e.getMessage().toString());
	            return null;
	        }

	    }//getBitmapFromURL


	}//class
	

	
	public void setProfileImage(CircleImageView circleImageView)
    {
		
		ImageLoader imageLoader;
		
		 imageLoader =ImageLoader.getInstance();
		 
   	 try
        {
        USER_Model   user =USER_Model.GetUser();
        
        if(user!=null && user.profile_url!=null)
        {
        	
        	//circleImageView.setImageBitmap(ViewRoomsGridViewAdapter.bytesToBitmap(user.profile_image));
        	
        	imageLoader.displayImage(AppWebServices.BASE_URL+user.profile_url,circleImageView,KisafaApplication.getImageDisplayOption());
        }
        
        
        }catch(Exception ex)
        {
        	ex.toString();
        }
    }
}
