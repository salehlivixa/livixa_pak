package com.livixa.apacam.client.activity;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.activeandroid.query.Select;
import com.bumptech.glide.Glide;
import com.kisafa.user.profile.CircleImageView;
import com.kisafa.user.profile.USER_Model;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Order;
import com.livixa.apacam.client.response.tariff_energy.Sh_Room_Watage_result;
import com.livixa.apacam.client.response.tariff_energy.Sh_Watage_result;
import com.livixa.apacam.client.response.tariff_energy.Watage_Response;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ParseException;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.AppWebServices;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_Data_to_Server_WebServiceCall;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sync_Data_to_Server_WebServiceCall.Data_Status;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.BridgeService;
import com.livixa.client.MainActivity;
import com.livixa.client.R;
import com.sawas.ashisuto.methods.Method;

import object.p2pipcam.adapter.EnergyRoomsGridViewAdapter;
import object.p2pipcam.utils.MyDate;
import retrofit2.Call;


public class HomeActivity extends Activity implements OnClickListener {

    // Layout Variables
    private View view;
    private RelativeLayout mRlCamera;
    private ImageView mRlSettings;
    private LinearLayout rl_rooms;
    private LinearLayout rl_moods;
    private ImageView notigif;
    private TextView activity_home_wattage;
    private TextView activity_home_package_id;
    private TextView activity_home_package_expriy;
    private RelativeLayout rl_energy;

    private LinearLayout profileContainer;
    private ImageView iv_profile;

    private ProgressDialog mProgressDialog;

    // Local
    private String ACTIVITY_TITLE = "Home";

    private boolean isDataSynced = false;


    CircleImageView circleImageViewProfileButton;


    boolean isResponseArrieved = true;


    private MyTimerTask myTimerTask = null;

    private Timer timer;


    TextView syncDataTV;
    private ScheduledExecutorService scheduler;


    public static Activity MainCameraActivity;


    private boolean isAppExitEnabled = false;


    // Class Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        try {
            initComponents();
            //getSupportActionBar().hide();
            //getActionBar().hide();
            ((TextView) findViewById(R.id.userName)).setText(AppPreference.getValue(HomeActivity.this, AppKeys.KEY_USER_NAME).toUpperCase());

            Glide.with(this).load(R.drawable.notificationsani).into(notigif);

            setProfileImage(circleImageViewProfileButton);


            if (!isMyServiceRunning(Sync_Service.class)) {
                Intent in = new Intent(HomeActivity.this, Sync_Service.class);
                startService(in);
            }


            Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(HomeActivity.this);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();

        try {
            if (scheduler != null) {
                scheduler.shutdownNow();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        callServiceMehod(getMonth(), getYear());
    }

    // Helping Methods
    public void initComponents() {
        //super.initUIComponents(ACTIVITY_TITLE);
        HomeActivity mContext = this;
        view = (View) findViewById(R.id.rl_root);
//		mRlCamera = (RelativeLayout) findViewById(R.id.rl_camera);
        mRlSettings = (ImageView) findViewById(R.id.rl_settings);
        rl_rooms = (LinearLayout) findViewById(R.id.rl_rooms);
        rl_energy = (RelativeLayout) findViewById(R.id.rl_energy);
        activity_home_package_id = findViewById(R.id.activity_home_package_id);
        activity_home_package_expriy = findViewById(R.id.activity_home_package_expriy);
        notigif = (ImageView) findViewById(R.id.noti);
//
//
//		circleImageViewProfileButton = (CircleImageView) findViewById(R.id.iv_profile_button);
//
//
        rl_moods = (LinearLayout) findViewById(R.id.rl_moods);
//
        profileContainer = (LinearLayout) findViewById(R.id.profileContainer);
        iv_profile = (ImageView) findViewById(R.id.iv_profile);
        syncDataTV = (TextView) findViewById(R.id.syncDataTV);
        activity_home_wattage = findViewById(R.id.activity_home_wattage);

        hideui(); // ok // i m out //ok
    }

    public void setClickListner(OnClickListener onclick) {
//		mRlCamera.setOnClickListener(onclick);
        mRlSettings.setOnClickListener(onclick);
        rl_rooms.setOnClickListener(onclick);
        rl_moods.setOnClickListener(onclick);
        rl_energy.setOnClickListener(onclick);
    }

    @Override
    public void onClick(View v) {

        Intent intent = null;
        switch (v.getId()) {
//		case R.id.rl_camera:
//			intent = new Intent(this, MainActivity.class);
//
//			startActivity(intent);
//			finish();
//			KisafaApplication.perFormActivityNextTransition(this);
//			break;
            case R.id.rl_settings:
                intent = new Intent(this, SettingsActivity.class);

                startActivity(intent);
                finish();
                KisafaApplication.perFormActivityNextTransition(this);
                break;


            case R.id.rl_rooms:
                intent = new Intent(this, View_RoomsActivity.class);

                startActivity(intent);
                finish();
                KisafaApplication.perFormActivityNextTransition(this);
                break;


            case R.id.rl_moods:

                intent = new Intent(this, Moods_View_RoomsActivity.class);
                startActivity(intent);
                finish();
                KisafaApplication.perFormActivityNextTransition(this);
                break;

            case R.id.rl_energy:

                intent = new Intent(this, Energy_RoomsActivity.class);
                startActivity(intent);
                finish();
                KisafaApplication.perFormActivityNextTransition(this);
                break;


            default:
                break;
        }
    }


    private int getMonth() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        Log.d("month", "getMonth: " + month);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return month; //makeDateString(month);

    }

    private int getYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return year;
    }


    public void callServiceMehod(int month, int year) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("session", AppPreference.getValue(HomeActivity.this, AppKeys.KEY_SESSION));
        map.put(AppKeys.KEY_CURRENT_LANGUAGE, AppPreference.getValue(HomeActivity.this, AppKeys.KEY_CURRENT_LANGUAGE));
        map.put("month", month);
        map.put("year", year);

        ApiService service = KisafaApplication.getRestClient().getApiService();
        Call<Watage_Response> call = (Call<Watage_Response>) service.getWatageDetails(map);
        call.enqueue(new RestCallback<Watage_Response>(new ServerConnectListener() {
            @Override
            public void onSuccess(ServerResponse response, String raw) {
                if (response.getRequestCode() == ServerCodes.ServerRequestCodes.WATAGE_REQUEST_CODE) {
                    Watage_Response requestResponse = (Watage_Response) response;
                    if (!requestResponse.getShMeta().getShErrorCode()
                            .equalsIgnoreCase("0")) {
                        {
                            return;
                        }
                    }

                    Sh_Watage_result watageResult = requestResponse.getShResult();
                    if (watageResult != null) {
                        ArrayList<Sh_Room_Watage_result> roomResult = watageResult.getSh_rooms();
                        String watageUnit = "kWh";
                        String watagepUnit = "$";
                        if (roomResult != null && roomResult.size() > 0) {
                            watageUnit = watageResult.getSh_wattage_unit();
                            String watageValue = watageResult.getSh_total_wattage();
                            String watageprice = watageResult.getSh_total_price();
                            if (activity_home_wattage != null)
                                activity_home_wattage.setText(watageValue + " " + watageUnit + " " + watageprice + " " + watagepUnit);


                        } else {
                            if (activity_home_wattage != null)
                                activity_home_wattage.setText("0.0 kWh" + "   " + "0.0 $");
                        }
                    }
                }
            }

            @Override
            public void onFailure(ServerResponse response) {

            }
        },
                ServerCodes.ServerRequestCodes.WATAGE_REQUEST_CODE, HomeActivity.this));
        setUISubs();
        hideui(); //settings me bhi code he uihide ka
    }

    public void setUISubs() {

        ArrayList<String> value = KisafaApplication.getSubscription(HomeActivity.this);
        if (value.size() != 0) {
            String pack = "Package " + value.get(0);
            activity_home_package_id.setText(pack);
        }
        if (value.size() > 2) {
            Date date = stringToDate(value.get(3), "yyyy-MM-dd");
            String d = DateFormat.getDateInstance(DateFormat.SHORT).format(date);
            d = "Expiry : " + d;
            activity_home_package_expriy.setText(d);
            String current = Method.getCurrentDate("yyyy-MM-dd");
            Date current_date = stringToDate(current, "yyyy-MM-dd");
            if(current_date.after(date)){
                d = "Expired";
                value.set(0,"0");
                KisafaApplication.setSubscription(HomeActivity.this,value);
            }
            activity_home_package_expriy.setText(d);
        }
    }

    public void hideui() {
        rl_moods.setVisibility(View.INVISIBLE);
        rl_energy.setVisibility(View.INVISIBLE);

        ArrayList<String> value = KisafaApplication.getSubscription(HomeActivity.this);

        if (value != null && value.size() != 0) {
            String id = value.get(0);
            if (id.equals("0")) {
            } else if (id.equals("1")) {
            } else if (id.equals("2")) {
                rl_moods.setVisibility(View.VISIBLE);
            } else if (id.equals("3")) {
                rl_moods.setVisibility(View.VISIBLE);
            } else if (id.equals("4")) {
                rl_moods.setVisibility(View.VISIBLE);
                rl_energy.setVisibility(View.VISIBLE);

            }


        }

    }

    private Date stringToDate(String aDate, String aFormat) {

        if (aDate == null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }

//    public void moveto(View view){
//        Intent intent = new Intent(this, Subscription_packages.class);
//
//        startActivity(intent);
//        finish();
//        KisafaApplication.perFormActivityNextTransition(this);
//    }

    @Override
    protected void onResume() {

        isAppExitEnabled = false;

        super.onResume();


        setClickListner(this);

        try {


            if (AppPreference.getValue(HomeActivity.this, AppKeys.KEY_IS_SUB_USER) != null
                    && AppPreference.getValue(HomeActivity.this, AppKeys.KEY_IS_SUB_USER).equals("1")) {


            } else {

                myTimerTask = new MyTimerTask();
                scheduler = Executors.newScheduledThreadPool(1);
                scheduler.scheduleWithFixedDelay(myTimerTask, 5, 5, MINUTES);
                testCall();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            ex.toString();
        }


    }


    public static MyDate getAndroidDateFromString(String date) {
        //Date datte =null;

        MyDate cusDate = null;


        final DateFormat df = new SimpleDateFormat(DateAndTimePickerForMoodsActivity.dateFormate);
        final Calendar cal = Calendar.getInstance();
        try {
            try {
                cal.setTime(df.parse(date));
            } catch (java.text.ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            cusDate = new MyDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return cusDate;

    }


    public static String getAndroidStringDateFromDate(MyDate date) {


        date.setMonth(date.getMonth() - 1);

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, date.getYear());
        cal.set(Calendar.MONTH, date.getMonth());
        cal.set(Calendar.DAY_OF_MONTH, date.getDay());
        cal.set(Calendar.HOUR_OF_DAY, date.getHour());
        cal.set(Calendar.MINUTE, date.getMinute());
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        SimpleDateFormat sdf = new SimpleDateFormat(DateAndTimePickerForMoodsActivity.dateFormate);
        String dateString = sdf.format(cal.getTime());


        return dateString;

    }


    public static MyDate getAndroidDateFromValues(int year, int month, int dayofMonth, int hours, int minutes) {


        MyDate cusDate = new MyDate(year, month, dayofMonth, hours, minutes);


        return cusDate;

    }


    public static MyDate getAndroidDateFromHEXValues(String day, String month, String year, String min, String hour) {


        int days = Integer.parseInt(day, 16);
        int months = Integer.parseInt(month, 16);

        String firstPartOfYear = year.substring(0, 2);

        String secondPartOfYear = year.substring(2, 4);

        int year1 = Integer.parseInt(firstPartOfYear, 16);

        int year2 = Integer.parseInt(secondPartOfYear, 16);

        int mins = Integer.parseInt(min, 16);
        int hours = Integer.parseInt(hour, 16);


        MyDate date = HomeActivity.getAndroidDateFromValues(Integer.parseInt(firstPartOfYear + "" + secondPartOfYear, 16), months, days, hours, mins);


        return date;

    }


    public void onViewProfile(View view) {

        Intent intent = new Intent(this, UpdateProfileActivity.class);
        startActivity(intent);

//	        	 profileContainer.setVisibility(View.VISIBLE);
//
//	        	 profileContainer.postDelayed(new Runnable() {
//
//					@Override
//					public void run() {
//
//
//						((TextView)findViewById(R.id.userName)).setText(AppPreference.getValue(HomeActivity.this, AppKeys.KEY_USER_NAME).toUpperCase());
//
//			        	 CircleImageView imge=(CircleImageView) findViewById(R.id.iv_profile);
//
//			        	 setProfileImage(imge);
//
//					}
//				}, 100);


    }

    @Override
    public void onBackPressed() {


        if (profileContainer.getVisibility() == View.VISIBLE) {
            profileContainer.setVisibility(View.GONE);
            return;
        }


        try {
            if (isMyServiceRunning(BridgeService.class)) {
                Intent in = new Intent(this, BridgeService.class);
                stopService(in);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            profileContainer.postDelayed(new Runnable() {

                @Override
                public void run() {

                    if (MainCameraActivity != null) {
                        MainCameraActivity.finish();
                    }

                    isAppExitEnabled = true;

                    finish();
                }
            }, 300);


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    public void setProfileImage(final CircleImageView circleImageView) {

        ImageLoader imageLoader;

        imageLoader = ImageLoader.getInstance();

        try {
            final USER_Model user = USER_Model.GetUser();

            if (user != null && user.profile_url != null) {

                //circleImageView.setImageBitmap(ViewRoomsGridViewAdapter.bytesToBitmap(user.profile_image));

                //imageLoader.displayImage(AppWebServices.BASE_URL+user.profile_url,circleImageView,KisafaApplication.getImageDisplayOption());


                imageLoader.displayImage(AppWebServices.BASE_URL + user.profile_url, circleImageView, KisafaApplication.getImageDisplayOption(), new ImageLoadingListener() {

                    @Override
                    public void onLoadingStarted(String arg0, View arg1) {


                    }

                    @Override
                    public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {


                    }

                    @Override
                    public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {

                        circleImageView.setImageBitmap(null);

                        circleImageView.performLongClick();

                        circleImageView.setImageBitmap(arg2);

                        circleImageView.invalidate();

                        //circleImageView.bringToFront();


                        //Toast.makeText(getApplicationContext(), "hey", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onLoadingCancelled(String arg0, View arg1) {


                    }
                });
            }


        } catch (Exception ex) {
            ex.toString();
        }
    }

    public void onBackgroundClick(View view) {
        profileContainer.setVisibility(View.GONE);

    }


    class MyTimerTask extends Thread {


        @Override
        public void run() {


            runOnUiThread(new Runnable() {
                public void run() {


                    webServiceCall();


                }
            });


        }

    }


    private void webServiceCall() {
        final Sync_Data_to_Server_WebServiceCall sync_Data_to_Server_WebServiceCall = new Sync_Data_to_Server_WebServiceCall(HomeActivity.this);


        sync_Data_to_Server_WebServiceCall.setDataLiostner(new Data_Status() {

            @Override
            public void onRecieveData(boolean isSuccessfull) {


                if (isSuccessfull) {
                    syncDataTV.setVisibility(View.GONE);
                    isDataSynced = true;

                    if (scheduler != null) {
                        scheduler.shutdownNow();

                    }

                } else {
                    syncDataTV.setVisibility(View.VISIBLE);
                }

            }
        });


        if (sync_Data_to_Server_WebServiceCall.isSyncedRequired()) {


            if (!isDataSynced) {
                syncDataTV.setVisibility(View.GONE);

                Sync_Data_to_Server_WebServiceCall.SetClickedByUser(false);

                sync_Data_to_Server_WebServiceCall.execute();

            }

        } else {
            syncDataTV.setVisibility(View.GONE);
            scheduler.shutdownNow();
        }


    }


    public void onBannerClick(View view) {

        final Sync_Data_to_Server_WebServiceCall sync_Data_to_Server_WebServiceCall = new Sync_Data_to_Server_WebServiceCall(HomeActivity.this);

        sync_Data_to_Server_WebServiceCall.setDataLiostner(new Data_Status() {

            @Override
            public void onRecieveData(boolean isSuccessfull) {


                if (isSuccessfull) {
                    syncDataTV.setVisibility(View.GONE);
                    isDataSynced = true;

                    if (scheduler != null) {
                        scheduler.shutdownNow();

                    }


                } else {
                    syncDataTV.setVisibility(View.VISIBLE);
                }

            }
        });


        syncDataTV.setVisibility(View.GONE);

        if (sync_Data_to_Server_WebServiceCall.isSyncedRequired()) {

            Sync_Data_to_Server_WebServiceCall.SetClickedByUser(true);
            sync_Data_to_Server_WebServiceCall.execute();
        }


    }


    private void testCall() {

        final Sync_Data_to_Server_WebServiceCall sync_Data_to_Server_WebServiceCall = new Sync_Data_to_Server_WebServiceCall(HomeActivity.this);

        sync_Data_to_Server_WebServiceCall.setDataLiostner(new Data_Status() {

            @Override
            public void onRecieveData(boolean isSuccessfull) {


                if (isSuccessfull) {
                    syncDataTV.setVisibility(View.GONE);
                    isDataSynced = true;

                    if (scheduler != null) {
                        scheduler.shutdownNow();

                    }


                } else {
                    syncDataTV.setVisibility(View.VISIBLE);
                }

            }
        });


        if (sync_Data_to_Server_WebServiceCall.isSyncedRequired()) {
            syncDataTV.setVisibility(View.VISIBLE);
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void notification(View view) {
        Intent intent = new Intent();
        intent = new Intent(this, Notification.class);
        startActivity(intent);
    }
}
