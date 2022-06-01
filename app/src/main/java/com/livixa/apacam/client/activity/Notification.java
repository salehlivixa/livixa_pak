package com.livixa.apacam.client.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.google.gson.JsonObject;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.notification.NotificatioinResponse;
import com.livixa.apacam.client.response.notification.NotificationObject;
import com.livixa.apacam.client.response.notification.ShResult;
import com.livixa.apacam.client.response.tariff_energy.Watage_Response;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.client.R;
import com.sawas.ashisuto.methods.Method;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import object.p2pipcam.adapter.ColorsAdapter;
import retrofit2.Call;

public class Notification extends Activity implements ServerConnectListener {


    RecyclerView notification_recylerview;
    NotificationAdapter notificationAdapter;
    int offSet = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        offSet = 0;
        notification_recylerview = findViewById(R.id.notification_recylerview);
        notificationAdapter = new NotificationAdapter(Notification.this, new ArrayList<>());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(Notification.this, RecyclerView.VERTICAL, false);
        notification_recylerview.setLayoutManager(mLayoutManager);
        callServiceMehod();
    }

    public void onbackButttonClick(View view) {
        onBackPressed();
    }


    public void onhomeButttonClick(View view) {


        finish();

        Intent intent = new Intent(this, HomeActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        KisafaApplication.perFormActivityBackTransition(this);
    }

    public void callServiceMehod() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("session", AppPreference.getValue(Notification.this, AppKeys.KEY_SESSION));
        map.put(AppKeys.KEY_CURRENT_LANGUAGE, AppPreference.getValue(Notification.this, AppKeys.KEY_CURRENT_LANGUAGE));
        map.put("offset", offSet);
        map.put("limit", 10);

        showProgressDialog("", 100);
        ApiService service = KisafaApplication.getRestClient().getApiService();
        Call<NotificatioinResponse> call = (Call<NotificatioinResponse>) service.getNotificatioins(map);
        call.enqueue(new RestCallback<NotificatioinResponse>(this,
                ServerCodes.ServerRequestCodes.NOTIFICATION_REQUEST_CODE, Notification.this));
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();


        finish();

        Intent intent = new Intent(this, HomeActivity.class);


        KisafaApplication.perFormActivityBackTransition(this);

        startActivity(intent);


    }

    @Override
    public void onSuccess(ServerResponse response) {
        if (response.getRequestCode() == ServerCodes.ServerRequestCodes.NOTIFICATION_REQUEST_CODE) {
            NotificatioinResponse requestResponse = (NotificatioinResponse) response;
            ShResult Result = requestResponse.getShResult();
            if (Result.getSh_models() != null) {
                offSet = Result.getSh_models().size();
                notificationAdapter.setmDataSet(Result.getSh_models());
                WaitingStaticProgress.hideProgressDialog();
            } else {
                WaitingStaticProgress.hideProgressDialog();
            }
        } else {
            WaitingStaticProgress.hideProgressDialog();
            onFailure(response.getMessage());
        }
    }

    @Override
    public void onFailure(ServerResponse response) {
        Log.e("Fail", response.getMessage());
        onFailure(response.getMessage());
    }

    public void onFailure(String retrofitError) {
		/*if (mProgressDialog != null) {
			mProgressDialog.hide();
		}*/


        new SnackBar.Builder(Notification.this).withMessage(retrofitError)
                .withDuration(SnackBar.MED_SNACK).show();
    }

    private void showProgressDialog(String text, int progress) {

        WaitingStaticProgress.showProgressDialog(text, Notification.this);
    }


    private class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

        ArrayList<NotificationObject> mDataSet;
        Context mContext;

        public NotificationAdapter(Context context, ArrayList<NotificationObject> dataList) {
            mDataSet = dataList;
            mContext = context;
        }

        public void setmDataSet(ArrayList<NotificationObject> mDataSet) {
            this.mDataSet = mDataSet;
            notifyDataSetChanged();
        }

        @Override
        public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.notification_adapter_item, parent, false);
            NotificationAdapter.ViewHolder vh = new NotificationAdapter.ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(NotificationAdapter.ViewHolder holder, int position) {
            NotificationObject notificationObject = mDataSet.get(position);
            holder.itemView.setTag(notificationObject);
            holder.type.setText(notificationObject.getSh_type());
            holder.content.setText(notificationObject.getSh_content());
            holder.time.setText(notificationObject.getSh_date());

        }

        @Override
        public int getItemCount() {
            // Count the items
            return mDataSet.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView icon;
            public TextView type;
            public TextView content;
            public TextView time;

            public ViewHolder(View convertView) {
                super(convertView);

                icon = convertView.findViewById(R.id.notification_item_image);
                type = convertView.findViewById(R.id.notification_item_type);
                content = convertView.findViewById(R.id.notification_item_content);
                time = convertView.findViewById(R.id.notification_item_time);
            }


        }
    }

}