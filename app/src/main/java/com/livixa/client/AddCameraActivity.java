package com.livixa.client;

import java.util.Map;

import object.p2pipcam.adapter.SearchListAdapter;
import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.nativecaller.NativeCaller;
import object.p2pipcam.system.SystemValue;
import object.p2pipcam.zxingtwodimensioncode.CaptureActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.client.BridgeService.AddCameraInterface;

/**
 * ï¿½ï¿½Óºï¿½ï¿½Þ¸ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
 * */
public class AddCameraActivity extends BaseActivity implements OnClickListener,
		AddCameraInterface {
	// private static final String LOG_TAG = "AddCameraActivity";

	// private Button btnAdd = null;
	// private Button btnCancel = null;
	private EditText devNameEdit = null;
	private EditText userEdit = null;
	private EditText pwdEdit = null;
	private EditText didEdit = null;
	private TextView back;
	private TextView done;
	private String strName = "";
	private String strUser = "";
	private String strPwd = "";
	private String strOldDID = "";
	private static final int SEARCH_TIME = 3000;
	private int option = ContentCommon.INVALID_OPTION;
	private TextView textViewAddCamera = null;
	private int CameraType = ContentCommon.CAMERA_TYPE_MJPEG;
	private RelativeLayout btnScanId;
	private RelativeLayout btnSearchCamera;
	private SearchListAdapter listAdapter = null;
	private ProgressDialog progressdlg = null;
	private boolean isSearched;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.add_camera);
		Intent in = getIntent();
		option = in.getIntExtra(ContentCommon.CAMERA_OPTION,
				ContentCommon.INVALID_OPTION);
		if (option != ContentCommon.INVALID_OPTION) {
			strName = in.getStringExtra(ContentCommon.STR_CAMERA_NAME);
			strOldDID = in.getStringExtra(ContentCommon.STR_CAMERA_ID);
			strUser = in.getStringExtra(ContentCommon.STR_CAMERA_USER);
			strPwd = in.getStringExtra(ContentCommon.STR_CAMERA_PWD);
		}
		progressdlg = new ProgressDialog(this,R.style.progressDialogTheme);
		progressdlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressdlg.setMessage(getString(R.string.searching_tip));
		listAdapter = new SearchListAdapter(this);
		findView();
		InitParams();
		BridgeService.setAddCameraInterface(this);
	}

	private void hideSof(EditText edit) {
		 
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
	}

	@Override
	protected void onResume() {
		 
		/*if (progressdlg != null && progressdlg.isShowing()) {
			progressdlg.cancel();
		}*/
		
		WaitingStaticProgress.hideProgressDialog();
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		// overridePendingTransition(R.anim.out_to_right,
		// R.anim.in_from_left);// ï¿½Ë³ï¿½ï¿½ï¿½ï¿½ï¿½
		// hideSof(didEdit);
		super.onPause();
	}

	private void InitParams() {
		if (option == ContentCommon.EDIT_CAMERA) {
			textViewAddCamera.setText(R.string.EditCamera);
		} else {
			textViewAddCamera.setText(R.string.AddCamera);
		}

		if (option != ContentCommon.INVALID_OPTION) {
			devNameEdit.setText(strName);
			userEdit.setText(strUser);
			pwdEdit.setText(strPwd);
			didEdit.setText(strOldDID.trim());
		}
		back.setOnClickListener(this);
		done.setOnClickListener(this);
		btnScanId.setOnClickListener(this);
		btnSearchCamera.setOnClickListener(this);
	}

	@Override
	protected void onStop() {
		// progressdlg.dismiss();
		// NativeCaller.StopSearch();
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	Runnable updateThread = new Runnable() {

		public void run() {
			NativeCaller.StopSearch();
			//progressdlg.dismiss();
            WaitingStaticProgress.hideProgressDialog();
			Message msg = updateListHandler.obtainMessage();
			msg.what = 1;
			updateListHandler.sendMessage(msg);
		}
	};

	Handler updateListHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			if (msg.what == 1) {
				listAdapter.notifyDataSetChanged();
				if (listAdapter.getCount() > 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							AddCameraActivity.this);
					dialog.setTitle(getResources().getString(
							R.string.SearchResults));
					dialog.setCancelable(false);
					dialog.setPositiveButton(
							getResources().getString(R.string.refresh),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									startSearch();
								}
							});
					dialog.setNegativeButton(
							getResources().getString(R.string.Cancel), null);
					dialog.setAdapter(listAdapter,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int arg2) {
									Map<String, Object> mapItem = (Map<String, Object>) listAdapter
											.getItemContent(arg2);
									if (mapItem == null) {
										return;
									}

									String strName = (String) mapItem
											.get(ContentCommon.STR_CAMERA_NAME);
									String strDID = (String) mapItem
											.get(ContentCommon.STR_CAMERA_ID);
									String strUser = ContentCommon.DEFAULT_USER_NAME;
									String strPwd = ContentCommon.DEFAULT_USER_PWD;
									devNameEdit.setText(strName);
									userEdit.setText(strUser);
									pwdEdit.setText(strPwd);
									didEdit.setText(strDID.trim());

								}
							});

					
					final AlertDialog dialogh = dialog.create();

					dialogh.show();
					
					
					try
					
					{
					  Button negativeButton = ((AlertDialog)dialogh).getButton(DialogInterface.BUTTON_NEGATIVE);
				      Button positiveButton = ((AlertDialog)dialogh).getButton(DialogInterface.BUTTON_POSITIVE);
				      
				      if(positiveButton != null)
				      {
				    	  positiveButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_button_selector_white));
				    	  positiveButton.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getResources().getDisplayMetrics()));
				    	  LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams) positiveButton.getLayoutParams();
				    	  lp.setMargins(0, 0, 5, 0);
				    	  positiveButton.setTextColor(Color.parseColor("#2072C7"));
				      }
				      
				      if(negativeButton != null)
				      {
				    	  negativeButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_button_selector_white));
				    	  negativeButton.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getResources().getDisplayMetrics()));
				    	  LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams) positiveButton.getLayoutParams();
				    	  lp.setMargins(5, 0, 5, 0);
				    	  negativeButton.setTextColor(Color.parseColor("#2072C7"));
				      }
				      
					}catch(Exception ex)
					{
						
					}
					
					
					
				} else {
					Toast.makeText(AddCameraActivity.this,
							getResources().getString(R.string.add_search_no),
							Toast.LENGTH_LONG).show();
					isSearched = false;// ï¿½ï¿½ï¿½Ã»ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ù´ï¿½ï¿½ï¿½ï¿½ï¿½
				}
			}
		}
	};

	private void startSearch() {
		listAdapter.ClearAll();
		/*progressdlg.setMessage(getString(R.string.searching_tip));
		progressdlg.setCancelable(false);
		progressdlg.show();*/
		
		WaitingStaticProgress.showProgressDialog("", AddCameraActivity.this);
		
		new Thread(new SearchThread()).start();
		updateListHandler.postDelayed(updateThread, SEARCH_TIME);
	}

	private class SearchThread implements Runnable {
		@Override
		public void run() {
			Log.d("tag", "startSearch");
			NativeCaller.StartSearch();
		}
	}

	private void findView() {
		// btnAdd = (Button) findViewById(R.id.btnAdd);
		// btnCancel = (Button) findViewById(R.id.btnCancel);

		back = (TextView) findViewById(R.id.back);
		done = (TextView) findViewById(R.id.done);
		devNameEdit = (EditText) findViewById(R.id.editDevName);
		userEdit = (EditText) findViewById(R.id.editUser);
		pwdEdit = (EditText) findViewById(R.id.editPwd);
		didEdit = (EditText) findViewById(R.id.editDID);
		btnScanId = (RelativeLayout) findViewById(R.id.rl_scan);
		btnSearchCamera = (RelativeLayout) findViewById(R.id.rl_search);
		textViewAddCamera = (TextView) findViewById(R.id.textview_add_camera);
		findViewById(R.id.main_one).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			// overridePendingTransition(R.anim.out_to_right,
			// R.anim.in_from_left);// ï¿½Ë³ï¿½ï¿½ï¿½ï¿½ï¿½
			break;
		case R.id.done:
			done();
			KisafaApplication.perFormActivityBackTransition(this);
			break;
		case R.id.rl_scan:
			/*progressdlg.setMessage(getResources().getString(
					R.string.add_twodimensioncode));
			progressdlg.setCancelable(false);
			progressdlg.show();*/
			
			WaitingStaticProgress.showProgressDialog(getResources().getString(
					R.string.add_twodimensioncode), AddCameraActivity.this);
			
			Intent intent = new Intent(this, CaptureActivity.class);
			startActivityForResult(intent, 0);
			KisafaApplication.perFormActivityNextTransition(this);// ï¿½ï¿½ï¿½ë¶¯ï¿½ï¿½
			break;
		case R.id.rl_search:
			searchCamera();
			break;
		case R.id.main_one:
			Intent intent1 = new Intent(this, AllocationWifiActivity.class);
			startActivity(intent1);
			KisafaApplication.perFormActivityNextTransition(this);
			break;

		default:
			break;
		}
	}

	// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ê§ï¿½Ä·ï¿½ï¿½ï¿½
	private void hiddenInputMethod() {
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	private void searchCamera() {
		if (!isSearched) {
			isSearched = true;
			startSearch();
		} else {
			
			AlertDialog.Builder dialog = new AlertDialog.Builder(
					AddCameraActivity.this);
			dialog.setTitle(getResources().getString(
					R.string.SearchResults));
			dialog.setCancelable(false);
			dialog.setPositiveButton(
					getResources().getString(R.string.refresh),
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							startSearch();
						}
					});
			dialog.setNegativeButton(
					getResources().getString(R.string.Cancel), null);
			dialog.setAdapter(listAdapter,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
								int arg2) {
							Map<String, Object> mapItem = (Map<String, Object>) listAdapter
									.getItemContent(arg2);
							if (mapItem == null) {
								return;
							}

							String strName = (String) mapItem
									.get(ContentCommon.STR_CAMERA_NAME);
							String strDID = (String) mapItem
									.get(ContentCommon.STR_CAMERA_ID);
							String strUser = ContentCommon.DEFAULT_USER_NAME;
							String strPwd = ContentCommon.DEFAULT_USER_PWD;
							devNameEdit.setText(strName);
							userEdit.setText(strUser);
							pwdEdit.setText(strPwd);
							didEdit.setText(strDID.trim());

						}
					});

			
			final AlertDialog dialogh = dialog.create();

			dialogh.show();
			
			
			/*AlertDialog.Builder dialog = new AlertDialog.Builder(AddCameraActivity.this);
			dialog.setTitle(getResources()
					.getString(R.string.add_search_result));
			dialog.setCancelable(false);
			dialog.setPositiveButton(
					getResources().getString(R.string.refresh),
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							startSearch();

						}
					});
			dialog.setNegativeButton(
					getResources().getString(R.string.str_cancel), null);
			dialog.setAdapter(listAdapter,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int arg2) {
							Map<String, Object> mapItem = (Map<String, Object>) listAdapter
									.getItemContent(arg2);
							if (mapItem == null) {
								return;
							}

							String strName = (String) mapItem
									.get(ContentCommon.STR_CAMERA_NAME);
							String strDID = (String) mapItem
									.get(ContentCommon.STR_CAMERA_ID);
							String strUser = ContentCommon.DEFAULT_USER_NAME;
							String strPwd = ContentCommon.DEFAULT_USER_PWD;
							devNameEdit.setText(strName);
							userEdit.setText(strUser);
							pwdEdit.setText(strPwd);
							didEdit.setText(strDID.trim());

						}
					});
			
			final AlertDialog dialogh = dialog.create();

			dialogh.show();
			*/
			
			try
			
			{
			  Button negativeButton = ((AlertDialog)dialogh).getButton(DialogInterface.BUTTON_NEGATIVE);
		      Button positiveButton = ((AlertDialog)dialogh).getButton(DialogInterface.BUTTON_POSITIVE);
		      
		      if(positiveButton != null)
		      {
		    	  positiveButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_button_selector_white));
		    	  positiveButton.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getResources().getDisplayMetrics()));
		    	  LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams) positiveButton.getLayoutParams();
		    	  lp.setMargins(0, 0, 5, 0);
		    	  positiveButton.setTextColor(Color.parseColor("#2072C7"));
		      }
		      
		      if(negativeButton != null)
		      {
		    	  negativeButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_button_selector_white));
		    	  negativeButton.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getResources().getDisplayMetrics()));
		    	  LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams) positiveButton.getLayoutParams();
		    	  lp.setMargins(5, 0, 5, 0);
		    	  negativeButton.setTextColor(Color.parseColor("#2072C7"));
		      }
		      
			}catch(Exception ex)
			{
				
			}
			
			
		      
		}
	}

	private void done() {
		Intent in = new Intent();
		String strDevName = devNameEdit.getText().toString();
		String strUser = userEdit.getText().toString();
		String strPwd = pwdEdit.getText().toString();
		String strDID = didEdit.getText().toString().trim();
		int si = 0;
		if (strDevName.length() == 0) {
			showToast(R.string.input_camera_name);
			return;
		}

		if (strDID.length() == 0) {
			showToast(R.string.input_camera_id);
			return;
		}
		String str = null;
		if (strDID.length() > 8) {
			str = strDID.substring(0, 8);
		} else {
			str = strDID;
		}
		boolean istrue = false;
		for (int i = 0; i < str.length(); i++) {
			Log.d("tag", "test:" + (str.charAt(i) >= 48)
					+ (str.charAt(i) <= 57) + "  str:" + str);
			if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
				istrue = true;
				break;
			}
		}
		if (!istrue) {
			showToast(R.string.add_camer_invi);
			return;
		}
		for (int i = 0; i < SystemValue.arrayList.size(); i++) {
			if (!strOldDID.endsWith(strDID)
					&& strDID.equalsIgnoreCase(SystemValue.arrayList.get(i)
							.getDid())) {
				showToast(R.string.input_camera_all_include);
				return;
			}
		}

		if (strUser.length() == 0) {
			showToast(R.string.input_camera_user);
			return;
		}
		in.setAction(ContentCommon.STR_CAMERA_INFO_RECEIVER);
		if (option == ContentCommon.INVALID_OPTION) {
			option = ContentCommon.ADD_CAMERA;
		}
		in.putExtra(ContentCommon.CAMERA_OPTION, option);
		if (option != ContentCommon.INVALID_OPTION) {
			in.putExtra(ContentCommon.STR_CAMERA_OLD_ID, strOldDID);
		}
		
		
		
		in.putExtra(ContentCommon.STR_CAMERA_NAME, strDevName);
		in.putExtra(ContentCommon.STR_CAMERA_ID, strDID);
		in.putExtra(ContentCommon.STR_CAMERA_USER, strUser);
		in.putExtra(ContentCommon.STR_CAMERA_PWD, strPwd);
		in.putExtra(ContentCommon.STR_CAMERA_TYPE, CameraType);
		sendBroadcast(in);
		Log.e("zhaogenghuai", "sendBroadcast");
		AddCameraActivity.this.finish();
		// finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			didEdit.setText(scanResult.trim());
		}
	}

	/**
	 * BridgeService callback
	 * **/
	@Override
	public void callBackSearchResultData(int cameraType, String strMac,
			String strName, String strDeviceID, String strIpAddr, int port) {
		Log.i("", "11mac11=" + strMac + " 1111name1111=" + strName
				+ " 111did111=" + strDeviceID + "   strIpAddr =" + strIpAddr);
		// è®¾ç½®æ”¶æ�œåˆ°çš„è®¾å¤‡å��é»˜è®¤ä¸ºYIPC
		if (!listAdapter.AddCamera(strMac, "Camera", strDeviceID)) {
			return;
		}
	}

}