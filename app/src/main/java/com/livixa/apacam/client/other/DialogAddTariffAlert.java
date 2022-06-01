package com.livixa.apacam.client.other;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.github.mrengineer13.snackbar.SnackBar;
import com.livixa.apacam.client.activity.Notification;
import com.livixa.apacam.client.activity.TariffMainActivity;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.notification.NotificatioinResponse;
import com.livixa.apacam.client.response.tariff_energy.TariffAlertResponse;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.client.widget.CustomEditText;
import com.livixa.apacam.client.widget.CustomTextView;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.client.R;

import java.util.HashMap;

import object.p2pipcam.system.MyTextView;
import retrofit2.Call;

public class DialogAddTariffAlert {

    private Activity mActivity;
    View popupView;
    CustomEditText lowerAlert;
    CustomEditText mediumAlert;
    CustomEditText hightAlert;

    public DialogAddTariffAlert(Activity activity) {
        mActivity = activity;
        LayoutInflater layoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.dialog_add_tariff_alert, null);


        ImageView Cancel = popupView.findViewById(R.id.tv_Cancel);
        lowerAlert = popupView.findViewById(R.id.lowerLimitAlert);
        mediumAlert = popupView.findViewById(R.id.mediumLimitAlert);
        hightAlert = popupView.findViewById(R.id.highLimitAlert);
        MyTextView tv_Done = popupView.findViewById(R.id.tv_Done);

        String low = AppPreference.getValue(mActivity, AppKeys.pTarrifAlertLow);
        String medium = AppPreference.getValue(mActivity, AppKeys.pTarrifAlertMedium);
        String high = AppPreference.getValue(mActivity, AppKeys.pTarrifAlertHigh);
        lowerAlert.setText(low != null ? low : "");
        mediumAlert.setText(medium != null ? medium : "");
        hightAlert.setText(high != null ? high : "");

        final PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,
                true);

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.setAnimationStyle(R.style.BottonUpAnimation);


        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);

        Cancel.setOnClickListener(v -> {
                popupWindow.dismiss();
        });

        tv_Done.setOnClickListener( v-> {

                if (validate()) {
                    showProgressDialog("", 100);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("session", AppPreference.getValue(mActivity, AppKeys.KEY_SESSION));
                    map.put(AppKeys.KEY_CURRENT_LANGUAGE, AppPreference.getValue(mActivity, AppKeys.KEY_CURRENT_LANGUAGE));
                    map.put("tariff_alert_low", lowerAlert.getText().toString());
                    map.put("tariff_alert_medium", mediumAlert.getText().toString());
                    map.put("tariff_alert_high", hightAlert.getText().toString());
                    ApiService service = KisafaApplication.getRestClient().getApiService();
                    Call<TariffAlertResponse> call = (Call<TariffAlertResponse>) service.setTarifAlert(map);
                    call.enqueue(new RestCallback<TariffAlertResponse>(new ServerConnectListener() {
                        @Override
                        public void onSuccess(ServerResponse response) {
                            if (response.getRequestCode() == ServerCodes.ServerRequestCodes.TARRIF_ALERT_REQUEST_CODE) {

                                AppPreference.saveValue(mActivity, lowerAlert.getText().toString(), AppKeys.pTarrifAlertLow);
                                AppPreference.saveValue(mActivity, mediumAlert.getText().toString(), AppKeys.pTarrifAlertMedium);
                                AppPreference.saveValue(mActivity, hightAlert.getText().toString(), AppKeys.pTarrifAlertHigh);

                                showErrorMessage("Tariff alert price updated successfully!");
                            }
                            WaitingStaticProgress.hideProgressDialog();
                        }

                        @Override
                        public void onFailure(ServerResponse response) {
                            Log.e("Fail", response.getMessage());
                            WaitingStaticProgress.hideProgressDialog();
                            onFailureMessage(response.getMessage());
                        }
                    },
                            ServerCodes.ServerRequestCodes.TARRIF_ALERT_REQUEST_CODE, mActivity));

                }
        });

    }
    public void onFailureMessage(String retrofitError) {
		/*if (mProgressDialog != null) {
			mProgressDialog.hide();
		}*/


        new SnackBar.Builder(mActivity).withMessage(retrofitError)
                .withDuration(SnackBar.MED_SNACK).show();
    }
    public boolean validate() {
        String Message = "";
        String lower = lowerAlert.getText().toString();
        String medium = mediumAlert.getText().toString();
        String high = hightAlert.getText().toString();
        if (lower.length() == 0) {
            Message = "Lower Limit is empty";
        } else if (medium.length() == 0) {
            Message = "Medium Limit is empty";
        } else if (high.length() == 0) {
            Message = "High Limit is empty";
        }

        if (Integer.parseInt(lower) > Integer.parseInt(medium)) {
            Message = "Lower limit cant be greater then medium limit";
        }
        if (Integer.parseInt(medium) > Integer.parseInt(high)) {
            Message = "Medium limit cant be greater then higher limit";
        }

        if (Message.length() != 0) {
            showErrorMessage(Message);
            return false;
        }

        return true;
    }

    private void showProgressDialog(String text, int progress) {
        WaitingStaticProgress.showProgressDialog(text, mActivity);
    }

    private void showErrorMessage(String message) {
        final CustomSimpleAlertDialogue cusDial = new CustomSimpleAlertDialogue(mActivity, message);
        cusDial.setListner(()->
                cusDial.dismiss());
        cusDial.show();
    }

}
