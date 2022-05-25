package com.livixa.apacam.client.activity;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.github.mrengineer13.snackbar.SnackBar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.request.RequestResponse;
import com.livixa.apacam.client.response.tariff_energy.Sh_Room_Watage_result;
import com.livixa.apacam.client.response.tariff_energy.Sh_Watage_result;
import com.livixa.apacam.client.response.tariff_energy.Watage_Response;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.R;
import object.p2pipcam.adapter.CamerasGridViewAdapter;
import object.p2pipcam.adapter.EnergyRoomsGridViewAdapter;
import object.p2pipcam.adapter.RoomsGridViewAdapter;
import retrofit2.Call;





public class Energy_RoomsActivity extends Activity implements ServerConnectListener{


	private GridView roomsGridView;
	private View mEmptyView;
	TextView Month;
	TextView yeartextview;
	private DatePickerDialog datePickerDialog;

	private EnergyRoomsGridViewAdapter roomsGridViewAdapter;


	Sh_Watage_result watageResult=null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.energy_activity_rooms);
		initDatepicker();
		roomsGridView=(GridView) findViewById(R.id.roomsGridView);
		mEmptyView=findViewById(R.id.roomsEmptylayout);


//		Global date and year start

		Month = findViewById(R.id.Month);
		yeartextview= findViewById(R.id.year);

		Month.setText(makeDateString(getMonth()));
		yeartextview.setText(makeYearString(getYear()));


//    Global date and year end

		handleIntent();

		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);

		if(watageResult==null)
		{
			callServiceMehod(getMonth(),getYear());
		}
		else
		{
			ArrayList<Sh_Room_Watage_result> roomResult=watageResult.getSh_rooms();

			String priceUnit="$";
			String watageUnit="kWh";
			if(roomResult!=null && roomResult.size() > 0)
			{

				priceUnit= watageResult.getSh_price_unit();

				String priceValue=watageResult.getSh_total_price();

				watageUnit=watageResult.getSh_wattage_unit();

				String watageValue=watageResult.getSh_total_wattage();


				((TextView)findViewById(R.id.totalEnergyUsage)).setText(watageValue+" "+watageUnit);

				((TextView)findViewById(R.id.totalCostUsage)).setText(priceValue+" "+priceUnit);


			}


			try
			{

				roomsGridViewAdapter =new EnergyRoomsGridViewAdapter(Energy_RoomsActivity.this,mEmptyView,roomsGridView,roomResult,watageUnit,priceUnit,watageResult);
				roomsGridView.setAdapter(roomsGridViewAdapter);

			}
			catch(Exception ex)
			{
				ex.toString();
			}

		}


	}


//	Datepicker work //ek second aya mein //agaya ho

	private int getMonth() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		month = month + 1;
		Log.d("month", "getMonth: "+month);
		int  day = cal.get(Calendar.DAY_OF_MONTH);
		return month; //makeDateString(month);

	}

	private int getYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		month = month + 1;
		int  day = cal.get(Calendar.DAY_OF_MONTH);
		return year;
	}

	private void initDatepicker() {
		DatePickerDialog.OnDateSetListener dataSetListner = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker datePicker, int year, int month, int day) {
				month = month + 1;
				String date = makeDateString(month);
				String Year = makeYearString(year);
				Month.setText(date);
				yeartextview.setText(Year);
				callServiceMehod(month,year);
			}
		};

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int  day = cal.get(Calendar.DAY_OF_MONTH);

		int style = AlertDialog.THEME_HOLO_LIGHT;
		datePickerDialog = new DatePickerDialog(this,style,dataSetListner,year,month,day);
	}

	private String makeDateString(int month) {
		return  getMonthFormat(month);
	}

	private String makeYearString(int year) {
		return String.valueOf(year);
	}

	private String getMonthFormat(int month) {
		if(month == 1  )
			return "Jan";
		if(month ==2 )
			return "Feb";
		if(month ==3 )
			return "March";
		if(month ==4 )
			return "April";
		if(month ==5 )
			return "May";
		if(month ==6 )
			return "Jun";
		if(month ==7 )
			return "Jul";
		if(month ==8 )
			return "August";
		if(month == 9)
			return "Sept";
		if(month ==10 )
			return "Oct";
		if(month ==11 )
			return "Nov";
		if(month ==12 )
			return "December";

		return "Jan";
	}


	public void openmonth(View view){

		datePickerDialog.show();
		//function ?

//		Toast.makeText(this, "chal raha he ", Toast.LENGTH_SHORT).show();
	}

	//	Date picker work end
	public void onbackButttonClick(View view)
	{
		onBackPressed();
	}


	public void onhomeButttonClick(View view)
	{
		finish();

		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		//overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		KisafaApplication.perFormActivityBackTransition(this);
	}


	@Override
	public void onBackPressed() {

		super.onBackPressed();


		finish();

		Intent intent = new Intent(this, HomeActivity.class);

		KisafaApplication.perFormActivityBackTransition(this);
		//overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
//		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		startActivity(intent);


	}


	public void handleIntent() {
		try {

			Intent intent = getIntent();
			watageResult= (Sh_Watage_result)intent.getSerializableExtra("energy_result_to_load_again");


		} catch (Exception ex) {
			ex.toString();
		}
	}




	public void onAddRoomClick(View view)
	{
		Intent intent=new Intent(Energy_RoomsActivity.this, RoomActivity.class);

		intent.putExtra(AppKeys.KEY_IS_CREATED, true);

		startActivity(intent);

		overridePendingTransition(0,0);

		finish();
	}



	public void callServiceMehod(int month,int year) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("session", AppPreference.getValue(Energy_RoomsActivity.this, AppKeys.KEY_SESSION));
		map.put(AppKeys.KEY_CURRENT_LANGUAGE, AppPreference.getValue(Energy_RoomsActivity.this, AppKeys.KEY_CURRENT_LANGUAGE));
		map.put("month",month);
		map.put("year",year);

		showProgressDialog("", 100);
		ApiService service = KisafaApplication.getRestClient().getApiService();
		Call<Watage_Response> call = (Call<Watage_Response>) service.getWatageDetails(map);
		call.enqueue(new RestCallback<Watage_Response>(this,
				ServerCodes.ServerRequestCodes.WATAGE_REQUEST_CODE, Energy_RoomsActivity.this));
	}


	@Override
	public void onSuccess(ServerResponse response) {


		//Log.e("response",response.getMessage());

		if (response.getRequestCode() == ServerCodes.ServerRequestCodes.WATAGE_REQUEST_CODE) {




			Watage_Response requestResponse = (Watage_Response) response;
			Log.d("requestresponse", "onSuccess: "+requestResponse.getShResult());

			if (!requestResponse.getShMeta().getShErrorCode()
					.equalsIgnoreCase("0")) {
				{
					onFailure(requestResponse.getShMeta().getShMessage());
					return;
				}
			}

			watageResult=requestResponse.getShResult();


			if(watageResult!=null)
			{

				ArrayList<Sh_Room_Watage_result> roomResult=watageResult.getSh_rooms();

				String priceUnit="$";
				String watageUnit="kWh";
				if(roomResult!=null && roomResult.size() > 0)
				{

					priceUnit= watageResult.getSh_price_unit();
					String priceValue=watageResult.getSh_total_price();

					watageUnit=watageResult.getSh_wattage_unit();

					String watageValue=watageResult.getSh_total_wattage();


					((TextView)findViewById(R.id.totalEnergyUsage)).setText(watageValue+" "+watageUnit);

					((TextView)findViewById(R.id.totalCostUsage)).setText(priceValue+" "+priceUnit);


				}


				try
				{

					roomsGridViewAdapter =new EnergyRoomsGridViewAdapter(Energy_RoomsActivity.this,mEmptyView,roomsGridView,roomResult,watageUnit,priceUnit,watageResult);
					roomsGridView.setAdapter(roomsGridViewAdapter);

				}
				catch(Exception ex)
				{
					ex.toString();
				}


				WaitingStaticProgress.hideProgressDialog();

			}





		}
		else
		{
			onFailure(response.getMessage());
		}

	}


	public void onFailure(String retrofitError) {
		/*if (mProgressDialog != null) {
			mProgressDialog.hide();
		}*/

		WaitingStaticProgress.hideProgressDialog();

		new SnackBar.Builder(Energy_RoomsActivity.this).withMessage(retrofitError)
				.withDuration(SnackBar.MED_SNACK).show();
	}

	@Override
	public void onFailure(ServerResponse response) {
		Log.e("Fail",response.getMessage());
		onFailure(response.getMessage());

	}

	private void showProgressDialog(String text, int progress) {

		WaitingStaticProgress.showProgressDialog(text, Energy_RoomsActivity.this);
	}


}
