package com.livixa.apacam.client.activity;

import java.util.HashMap;
import java.util.Map;

import com.livixa.client.R;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaActivity;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.request.RequestResponse;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.AppUtil;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.services.Sync_Service;
import retrofit2.Call;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.github.mrengineer13.snackbar.SnackBar;

public class ForgotPasswordActivity extends Activity implements
		OnClickListener, ServerConnectListener {

	// Layout Variables
	private View view;
	private EditText mEtEmail;
	private Button mBtnSendEmail;
	private ProgressDialog mProgressDialog;

	// Local
	private String ACTIVITY_TITLE = "Forgot Password";
	private Map<String, String> map;
	private ForgotPasswordActivity mContext;

	// Class Methods
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setOrientation();
		setContentView(R.layout.activity_forgot);
		initComponents();
		//getSupportActionBar().hide();
		setClickListner(this);
	}

	// Helping Methods
	public void initComponents() {
		//super.initUIComponents(ACTIVITY_TITLE);
		mContext = this;
		view = (View) findViewById(R.id.rl_root);
		mBtnSendEmail = (Button) findViewById(R.id.btn_send_email);
		mEtEmail = (EditText) findViewById(R.id.et_email);
	}

	public void setClickListner(OnClickListener onclick) {
		mBtnSendEmail.setOnClickListener(onclick);
	}

	public void setOrientation() {
		if (AppPreference.getSavedData(mContext, AppKeys.KEY_IS_LANDSCAPE)) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
	}

	public void verifyData() {
		AppUtil.hideKeyBoard(mContext, mEtEmail);
		if (AppUtil.isEmailValid(mEtEmail.getText().toString())) {
			validate(mEtEmail.getText().toString().toLowerCase());
		} else {
			mEtEmail.setError(getResources().getString(R.string.Emailisnotvalid));
			mEtEmail.requestFocus();
		}
	}

	private void showProgressDialog(String text, int progress) {
		/*mProgressDialog = new ProgressDialog(mContext,R.style.progressDialogTheme);
		mProgressDialog.setMessage(text);
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(progress);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();*/
		
		
		WaitingStaticProgress.showProgressDialog(text, ForgotPasswordActivity.this);
	}

	// Request
	public void validate(String email) {
		map = new HashMap<String, String>();
		map.put("email", email);
		map.put(AppKeys.KEY_CURRENT_LANGUAGE, AppPreference.getValue(this, AppKeys.KEY_CURRENT_LANGUAGE));
		map.put("device_token",
				AppPreference.getValue(this, AppKeys.deviceToken));
		map.put("device_type", AppPreference.getValue(this, AppKeys.deviceType));
		showProgressDialog("", 100);
		ApiService service = KisafaApplication.getRestClient().getApiService();
		Call<RequestResponse> call = service.forgot(map);
		call.enqueue(new RestCallback<RequestResponse>(this,
				ServerCodes.ServerRequestCodes.FORGOT_REQUEST_CODE, mContext));
	}

	@Override
	public void onSuccess(ServerResponse response) {
		/*if (mProgressDialog != null) {
			mProgressDialog.hide();
		}*/
		
		WaitingStaticProgress.hideProgressDialog();
		
		if (response.getRequestCode() == ServerCodes.ServerRequestCodes.FORGOT_REQUEST_CODE) {
			RequestResponse requestResponse = (RequestResponse) response;
			onFailure(getResources().getString(R.string.Please_check_your_email_msg));
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
		 
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn_send_email:
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
		
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		
		overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);
	}
	
	
	public void onbackButttonClick(View view)
	{
		finish();
		
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		
		overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);
	}
}
