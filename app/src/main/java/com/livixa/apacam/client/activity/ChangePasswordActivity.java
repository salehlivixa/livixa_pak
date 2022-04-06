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
import android.renderscript.RSInvalidStateException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.github.mrengineer13.snackbar.SnackBar;

public class ChangePasswordActivity extends Activity implements
		OnClickListener, ServerConnectListener {

	// Layout Variables
	private View view;
	private EditText mEtOldPass;
	private EditText mEtNewPass;
	private EditText mEtConfirmNewPass;
	private Button mBtnSendEmail;
	private ProgressDialog mProgressDialog;

	// Local
	private String ACTIVITY_TITLE = "Forgot Password";
	private Map<String, String> map;
	private ChangePasswordActivity mContext;

	// Class Methods
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setOrientation();
		setContentView(R.layout.activity_change_password);
		initComponents();
		//getSupportActionBar().hide();
		setClickListner(this);
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
	}

	// Helping Methods
	public void initComponents() {
		//super.initUIComponents(ACTIVITY_TITLE);
		mContext = this;
		view = (View) findViewById(R.id.rl_root);
		mBtnSendEmail = (Button) findViewById(R.id.btn_send_email);
		mEtOldPass = (EditText) findViewById(R.id.et_password);
		mEtNewPass = (EditText) findViewById(R.id.et_new_password);
		mEtConfirmNewPass = (EditText) findViewById(R.id.et_confirm_new_password);
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
		     AppUtil.hideKeyBoard(mContext, mEtOldPass);
		     AppUtil.hideKeyBoard(mContext, mEtNewPass);
		     AppUtil.hideKeyBoard(mContext, mEtConfirmNewPass);
		     
		     
		     if(mEtOldPass.getText().toString().length()==0)
		     {
		    	 mEtOldPass.requestFocus();
		    	 mEtOldPass.setError(getString(R.string.Oldpasswordrequired));
		    	
		    	 return;
		     }
		     
		     if(mEtNewPass.getText().toString().length()==0)
		     {
		    	 mEtNewPass.requestFocus();
		    	 mEtNewPass.setError(getString(R.string.Newpasswordrequired));
		    	 return;
		     }
		     
		     if(mEtConfirmNewPass.getText().toString().length()==0)
		     {
		    	 mEtConfirmNewPass.requestFocus();
		    	 mEtConfirmNewPass.setError(getString(R.string.ConfirmNewpasswordrequired));
		    	 return;
		     }
		     
		     if(!mEtNewPass.getText().toString().equals(mEtConfirmNewPass.getText().toString()))
		     {
		    	 mEtConfirmNewPass.requestFocus();
		    	 mEtConfirmNewPass.setError(getString(R.string.Newpasswordandconfirmnewpasswordshouldbesame));
		    	 return;
		     }
		
			validate(mEtOldPass.getText().toString().toLowerCase(),mEtNewPass.getText().toString().toLowerCase());
		
	}

	private void showProgressDialog(String text, int progress) {
		/*mProgressDialog = new ProgressDialog(mContext,R.style.progressDialogTheme);
		mProgressDialog.setMessage(text);
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(progress);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();*/
		
		
		WaitingStaticProgress.showProgressDialog(text, ChangePasswordActivity.this);
	}

	// Request
	public void validate(String oldPass,String newPass) {
		map = new HashMap<String, String>();
		
		map.put("session", AppPreference.getValue(mContext, AppKeys.KEY_SESSION));
		map.put(AppKeys.KEY_CURRENT_LANGUAGE, AppPreference.getValue(this, AppKeys.KEY_CURRENT_LANGUAGE));
		map.put("old_password", oldPass);
		map.put("new_password",newPass);
		
		showProgressDialog("Changing password...", 100);
		ApiService service = KisafaApplication.getRestClient().getApiService();
		Call<RequestResponse> call = service.changePassword(map);
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
			
			if (requestResponse.getShMeta().getShErrorCode().equals("0"))
			{
			   onFailure(getString(R.string.Passwordupdatedsuccessfully));
			}
			else
			{
				 onFailure(requestResponse.getShMeta().getShMessage());
			}
		}
		else
		{
			
			 onFailure(response.getMessage());
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		finish();
		
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
		KisafaApplication.perFormActivityBackTransition(this);
	}
	
	
	public void onbackButttonClick(View view)
	{
		finish();
		
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
		KisafaApplication.perFormActivityBackTransition(this);
	}
}
