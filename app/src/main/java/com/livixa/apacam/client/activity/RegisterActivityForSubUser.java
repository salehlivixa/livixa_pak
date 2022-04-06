package com.livixa.apacam.client.activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONObject;

import com.livixa.client.R;
import com.livixa.apacam.client.activity.RegisterActivity.GetCites;
import com.livixa.apacam.client.activity.RegisterActivity.SetLocation;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaActivity;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.network.SingleShotLocationProvider;
import com.livixa.apacam.client.network.SingleShotLocationProvider.GPSCoordinates;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.client.response.login.ShResult;
import com.livixa.apacam.client.response.login._SubUser;
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
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.FragmentActivity;

import com.github.mrengineer13.snackbar.SnackBar;
import com.kisafa.user.profile.CityPicker;
import com.kisafa.user.profile.CountryPicker;
import com.kisafa.user.profile.CountryPickerListener;

public class RegisterActivityForSubUser extends FragmentActivity implements
		OnClickListener, ServerConnectListener ,LocationListener{

	// Layout Variables
	private View view;
	private EditText mEtUsername;
	private EditText mEtEmail;
	
	private TextView mEtCity;
	private TextView mEtCountry;
	
	private EditText mEtPassword;
	private Button mBtnRegister;
	private ProgressDialog mProgressDialog;

	// Local
	private String ACTIVITY_TITLE = "Register";
	private Map<String, String> map;
	private RegisterActivityForSubUser mContext;
	
	
	private  String  mCountryId = "";
	
	private boolean canGetLocation;
	private Object mGoogleApiClient;
	
	LocationManager locManager;

	// Class Methods
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setOrientation();
		setContentView(R.layout.activity_register_for_sub_user);
		initComponents();
		//getSupportActionBar().hide();
		setClickListner(this);
		
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
		
	}
	
	
	
	@Override
	protected void onPostResume() {
		 
		super.onPostResume();
		
		
		
		
		view.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				
				
				
				 SingleShotLocationProvider.requestSingleUpdate(RegisterActivityForSubUser.this, 
						   new SingleShotLocationProvider.LocationCallback() {
						     @Override public void onNewLocationAvailable(GPSCoordinates location) {
						       
						    	 
						    	 try
						    	 {
								 SetLocation setLocation=new SetLocation(location);
								 setLocation.execute();
						    	 }catch(Exception ex)
						    	 {
						    		 ex.toString();
						    	 }
						    	 
						     }
						   });
				
				
			}
				
			
		}, 200);
		
		
	}

	// Helping Methods
	public void initComponents() {
		//super.initUIComponents(ACTIVITY_TITLE);
		mContext = this;
		view = (View) findViewById(R.id.rl_root);
		mBtnRegister = (Button) findViewById(R.id.btn_register);
		mEtUsername = (EditText) findViewById(R.id.et_username);
		mEtPassword = (EditText) findViewById(R.id.et_password);
		mEtEmail = (EditText) findViewById(R.id.et_email);
		mEtCity=   (TextView) findViewById(R.id.et_city);
		mEtCountry=   (TextView) findViewById(R.id.et_country);
		
		
		//handlePasswordFeildforArabic();
		
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
		
		
		KisafaApplication.perFormActivityBackTransition(this);
		startActivity(intent);
	}

	public void verifyData() {
		AppUtil.hideKeyBoard(mContext, mEtEmail);
		AppUtil.hideKeyBoard(mContext, mEtPassword);
		boolean flag = setErrorMsg(mEtUsername, getString(R.string.Error_MSG_UserName));
		if (!flag) {
			flag = setErrorMsg(mEtPassword, getString(R.string.Error_MSG_UserPass));
			if (!flag) {
				flag = setErrorMsg(mEtEmail,getString(R.string.Emailisrequired));
				
						if (!flag) {
							
							if (!flag) {
								flag = setErrorMsg(mEtCountry, getString(R.string.Countryisrequired));
							
							
							if (!flag) {
								flag = setErrorMsg(mEtCity, getString(R.string.Cityisrequired));
							
							
							if (AppUtil.isEmailValid(mEtEmail.getText()
									.toString())) {
								
								
								Calendar cal = Calendar.getInstance();
								TimeZone tz = cal.getTimeZone();
								
								String timeZoneId= tz.getID();
								String timeZoneName=tz.getDisplayName();
								
								
								validateLogin(mEtUsername.getText().toString(),mEtPassword.getText().toString(),mEtEmail.getText().toString().toLowerCase(),mEtCountry.getText().toString(),mCountryId,mEtCity.getText().toString(),timeZoneId,timeZoneName);
							} else {
								mEtEmail.setError(getString(R.string.Emailisnotvalid));
								mEtEmail.requestFocus();
							}
						}
					}
				}
			}	}
	}
	
	private boolean setErrorMsg(TextView editText, String msg) {
		if (editText.getText().toString().equals("")) {
			editText.requestFocus();
			editText.setError(msg);
			return true;
		}
		return false;
	}

	private boolean setErrorMsg(EditText editText, String msg) {
		if (editText.getEditableText().toString().equals("")) {
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
		
		WaitingStaticProgress.showProgressDialog(text, RegisterActivityForSubUser.this);
	}

	// Request
	public void validateLogin(String username, String password, String email,String country_name,String country_code,String city,String time_zone,String time_zone_name) {
		map = new HashMap<String, String>();
		map.put("username", username);
		map.put(AppKeys.KEY_CURRENT_LANGUAGE, AppPreference.getValue(this, AppKeys.KEY_CURRENT_LANGUAGE));
		map.put("password", password);
		map.put("email", email);
		map.put("full_name", "");
		map.put("is_subuser", "1");
		map.put("created_by_user_id",AppPreference.getValue(this, AppKeys.KEY_USER_ID));
		
		//map.put("country_name", country_name);
		map.put("country_code", country_code);
		map.put("city", city);
		//map.put("time_zone", time_zone);
		map.put("time_zone_name", time_zone);
		
		map.put("phone_number", "");
		map.put("device_token",
		AppPreference.getValue(this, AppKeys.deviceToken));
		map.put("device_type", AppPreference.getValue(this, AppKeys.deviceType));
		showProgressDialog("Signing up user...", 100);
		ApiService service = KisafaApplication.getRestClient().getApiService();
		Call<RegisterResponse> call = service.register(map);
		call.enqueue(new RestCallback<RegisterResponse>(this,
				ServerCodes.ServerRequestCodes.REGISTER_REQUEST_CODE, mContext));
	}

	@Override
	public void onSuccess(ServerResponse response) {
		/*if (mProgressDialog != null) {
			mProgressDialog.hide();
		}*/
		
		
		WaitingStaticProgress.hideProgressDialog();
		
		if (response.getRequestCode() == ServerCodes.ServerRequestCodes.REGISTER_REQUEST_CODE) {
			RegisterResponse registerResponse = (RegisterResponse) response;
			if (registerResponse.getShMeta().getShErrorCode()
					.equalsIgnoreCase("0")) {
				
				
				SaveSunUsersInDB(registerResponse.getShResult());
				
				
				Intent intnt=new Intent(RegisterActivityForSubUser.this,Add_OR_Edit_UserActivity.class);
				startActivity(intnt);
				finish();
				
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
	
	
	private void SaveSunUsersInDB(com.livixa.apacam.client.response.register.ShResult shResult)
	{
		
		
		 
		 if(shResult==null )
			 return;
		 
		
			 
			 SUB_USER_DB s_user_db=new SUB_USER_DB();
			 
			 com.livixa.apacam.client.response.register.ShResult  s_user=shResult;
			 
			 s_user_db.sh_user_id=s_user.getShUserId();
			 s_user_db.username=s_user.getShUsername();
			 s_user_db.email=s_user.getShEmail();
			 
			 s_user_db.is_subuser=s_user.getShIsSubuser();
			 s_user_db.created_by_user_id=s_user.getShCreatedByUserId();
			 s_user_db.full_name=s_user.getShFullName();
			 s_user_db.profile_image=s_user.getShProfileImage();
			 s_user_db.phone_number=s_user.getShPhoneNumber();
			 
			 
			 s_user_db.save();
	}
	
	
	
	@Override
	public void onBackPressed() {
		 
		super.onBackPressed();
		
		finish();
		
		Intent intent = new Intent(this, Add_OR_Edit_UserActivity.class);
		startActivity(intent);
		//overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		KisafaApplication.perFormActivityBackTransition(this);
	}
	
	public void onbackButttonClick(View view)
	{
		onBackPressed();
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
	                            MIN_DISTANCE_FOR_UPDATE,  this);
	                   
	                    if (locationManager != null) {
	                        location = locationManager
	                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
	                        
	                        
	                        if(location==null)
	                        {
	                        	
	                        	
	                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, RegisterActivityForSubUser.this);
	                        }
	                        
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
			
	
	
	public void onCountryClick(View view)
	{
		final CountryPicker picker = CountryPicker.newInstance("SelectCountry");
		 picker.show(getSupportFragmentManager(), "COUNTRY_CODE_PICKER");
		 
		 picker.setListener(new CountryPickerListener() {
			   
				@Override
				public void onSelectCountry(String name, String code) {
					
					picker.dismiss();
					
					mEtCountry.setText(name);
					mEtCountry.invalidate();
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
						mEtCity.invalidate();
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

	@Override
	public void onLocationChanged(Location location) {
		
		
		 
	        
	      
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
		
		 Toast.makeText(RegisterActivityForSubUser.this, "Status Changed", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		
		
		 Toast.makeText(RegisterActivityForSubUser.this, "onProviderEnabled", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		
		 Toast.makeText(RegisterActivityForSubUser.this, "onProviderDisabled", Toast.LENGTH_SHORT).show();
		
	}
	
	
	 class  SetLocation extends AsyncTask<Void, Void, List<Address>>
	 {
		 
		 
		 GPSCoordinates location;
		 
		 public SetLocation(GPSCoordinates location)
		 {
			 
			 this.location=location;
		 }

		@Override
		protected List<Address> doInBackground(Void... params) {
			
			
			List<Address> addresses=null;
			
			try
		       {
				 if(location!=null)
				 {
					
					 
					 Geocoder geocoder;
					 
					 geocoder = new Geocoder(RegisterActivityForSubUser.this, Locale.getDefault());
					 
					
			
					 try {
						addresses = geocoder.getFromLocation(location.latitude, location.longitude, 5);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // Here 1 represent max location result to returned, by documents it recommended 1 to 5
			
					 try {
							
							if(addresses==null)
							addresses = geocoder.getFromLocation(location.latitude, location.longitude, 5);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // Here 1 r
					 
					 
					
					
					 
					 //String completeAddress= address + city+  state + country + postalCode +  knownName;
					 
				 }
		       
		       }catch(Exception ex)
		       {
		       	
		       }
	    	 
			
			return addresses;
		}
		
		
		@Override
		protected void onPostExecute(List<Address> addresses) {
			 
			super.onPostExecute(addresses);
			
			try
			{
				String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
				
				 String city ="";
				
				 try
				 {
				 for (Address adr : addresses) {
			            if (adr.getLocality() != null && adr.getLocality().length() > 0) {
			                 city = adr.getLocality();
			            }
			        }
				 }catch(Exception ex){}
				
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
				
			}catch(Exception ex)
			{
				ex.toString();
			}
		}
		
		
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 private void handlePasswordFeildforArabic()
	 {
		 
		 String currentLanguage=AppPreference.getValue(getApplicationContext(), AppKeys.KEY_CURRENT_LANGUAGE);
		 
		  
		 if(currentLanguage!=null || currentLanguage.trim().length() > 0)
		 {
			 String  tempLanguage="en";
			 
			    if(currentLanguage.equals(AppKeys.LANGUAGES.ENGLISH.getValue()))
				{
			    	tempLanguage="en";
				}
			    else
			    {
			    	tempLanguage="ar";
			    	
			    	mEtPassword.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
			    	
			    	
			    	mEtPassword.addTextChangedListener(new TextWatcher() {
						
						@Override
						public void onTextChanged(CharSequence s, int start, int before, int count) {
							
							if(s.length()==0)
							{
								mEtPassword.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
							}
							else
							{
								
								mEtPassword.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
							}
							
						}
						
						@Override
						public void beforeTextChanged(CharSequence s, int start, int count, int after) {
							
							
						}
						
						@Override
						public void afterTextChanged(Editable s) {
							
							
							
						}
					});
			    	
			    }
		 }
	 }

}
