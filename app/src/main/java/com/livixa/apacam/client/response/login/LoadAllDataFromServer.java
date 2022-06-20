package com.livixa.apacam.client.response.login;

import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;

import com.github.mrengineer13.snackbar.SnackBar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.livixa.apacam.client.activity.HomeActivity;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.isdatasyncedwithserver.DataSynced_With_Server;
import com.livixa.apacam.client.response.isdatasyncedwithserver.ShResult;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Camera;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Mood;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Order;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Room;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Subscription;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Switch;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_UserCamera;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_User_Room;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Subscription;
import com.livixa.apacam.client.response.tariff_energy.ShTariffResult;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.dbmodel.Manage_DB_Model;
import com.livixa.client.R;
import com.sawas.ashisuto.db.TinyDB;
import com.sawas.ashisuto.methods.Method;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;

public class LoadAllDataFromServer extends AsyncTask<Void, Void, Boolean> implements ServerConnectListener {

    HashMap<String, String> map = new HashMap<String, String>();

    String session;
    Context cntxt;

    private ProgressDialog mProgressDialog;

    public LoadAllDataFromServer(String session, Context cntxt) {
        this.session = session;
        this.cntxt = cntxt;
    }

    @Override
    public void onSuccess(ServerResponse response, String raw) {
        if (response.getRequestCode() == ServerCodes.ServerRequestCodes.GET_SYNCED_DATA_REQUEST_CODE) {

            DataSynced_With_Server dataSynced_With_Server = (DataSynced_With_Server) response;
            if (dataSynced_With_Server.getShMeta().getShErrorCode().equalsIgnoreCase("0")) {


                UpdateDb task = new UpdateDb(dataSynced_With_Server, raw);

                task.execute();
				
				/*ShResult obj=dataSynced_With_Server.getShResult();
				
				ArrayList<Sh_Camera> listcamera=obj.getShCameras();
				
				ArrayList<Sh_UserCamera> listusercamera=obj.getShUserCamera();
				
				Manage_DB_Model.add_New_SyncedBitCameraModelAndUserModelRelationInDB(listcamera,listusercamera,cntxt);
				
				ArrayList<Sh_Room> listRoom = obj.getShRoomList();

				ArrayList<Sh_Switch> listSwitch = obj.getShSwitchList();
				ArrayList<Sh_User_Room> listUserRoom=obj.getShUserRoomList();
				
				ArrayList<ShTariffResult> listtariff=obj.getSHTariffList();
				
				ArrayList<Sh_Mood> moodsList=obj.getSHMoodList();
				
				
				
				if (listRoom != null)
					for (int i = 0; i < listRoom.size(); i++) {
						Manage_DB_Model.update_OR_Create_RoomModelInDB(listRoom.get(i),true);

					}
				
				
				if (listSwitch != null)
					for (int i = 0; i < listSwitch.size(); i++) {
						Manage_DB_Model.update_OR_Create_SwitchodelInDB(listSwitch.get(i));

					}
				
				
				
				if (listUserRoom != null)
					for (int i = 0; i < listUserRoom.size(); i++) {
						Manage_DB_Model.update_OR_Create_UserRoomModelInDB(listUserRoom.get(i));

					}
				
				
				if (listtariff != null)
					for (int i = 0; i < listtariff.size(); i++) {
						Manage_DB_Model.update_OR_Create_Tariff_ModelInDB(listtariff.get(i));

					}
				
				if (moodsList != null)
					for (int i = 0; i < moodsList.size(); i++) {
						Manage_DB_Model.update_OR_Create_MoodsInDB(moodsList.get(i),true);

					}*/


            } else {
                try {

                    onFailure(dataSynced_With_Server.getShMeta().getShMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                WaitingStaticProgress.hideProgressDialog();
            }
						
						/*if(mProgressDialog!=null)
							mProgressDialog.hide();*/


        }

    }


    public void callIntentWithFlag() {
        Intent intent = new Intent();
        intent = new Intent(cntxt, HomeActivity.class);
		/*intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);*/
        cntxt.startActivity(intent);

        ((Activity) cntxt).finish();
    }

    @Override
    public void onFailure(ServerResponse response) {

        WaitingStaticProgress.hideProgressDialog();

        try {
            DataSynced_With_Server dataSynced_With_Server = (DataSynced_With_Server) response;
            onFailure(response.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        AppPreference.saveData(cntxt, false, AppKeys.KEY_IS_LOGIN);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Boolean doInBackground(Void... params) {


        map.put("session", session);
        map.put(AppKeys.KEY_CURRENT_LANGUAGE, AppPreference.getValue(cntxt, AppKeys.KEY_CURRENT_LANGUAGE));


        ApiService service = KisafaApplication.getRestClient().getApiService();
        Call<DataSynced_With_Server> call = service.get_sync_data_from_server(map);
        call.enqueue(new RestCallback<DataSynced_With_Server>(this, ServerCodes.ServerRequestCodes.GET_SYNCED_DATA_REQUEST_CODE, cntxt));


        return true;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

        showProgressDialog(cntxt.getString(R.string.Pleasewaitwhileyourdata), 100);
    }

    private void showProgressDialog(String text, int progress) {
		/*mProgressDialog = new ProgressDialog(cntxt,R.style.MyTheme);
		mProgressDialog.setMessage(text);
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(progress);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(false);
		mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		mProgressDialog.show();*/

        WaitingStaticProgress.showProgressDialog(text, cntxt);
    }

    public void onFailure(String retrofitError) {

        new SnackBar.Builder((Activity) cntxt).withMessage(retrofitError).withDuration(SnackBar.SHORT_SNACK).show();
    }


    class UpdateDb extends AsyncTask<Void, Void, Boolean> {

        private DataSynced_With_Server dataSynced_With_Server;
        String _raw;

        protected void onPostExecute(Boolean result) {
            WaitingStaticProgress.hideProgressDialog();

            callIntentWithFlag();

        }

        ;

        UpdateDb(DataSynced_With_Server dataSynced_With_Server, String raw) {

            this.dataSynced_With_Server = dataSynced_With_Server;
            this._raw = raw;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            ShResult obj = dataSynced_With_Server.getShResult();

            ArrayList<Sh_Camera> listcamera = obj.getShCameras();

            ArrayList<Sh_UserCamera> listusercamera = obj.getShUserCamera();

            Manage_DB_Model.add_New_SyncedBitCameraModelAndUserModelRelationInDB(listcamera, listusercamera, cntxt);

            ArrayList<Sh_Room> listRoom = obj.getShRoomList();

            ArrayList<Sh_Switch> listSwitch = obj.getShSwitchList();
            ArrayList<Sh_User_Room> listUserRoom = obj.getShUserRoomList();

            ArrayList<ShTariffResult> listtariff = obj.getSHTariffList();

            ArrayList<Sh_Mood> moodsList = obj.getSHMoodList();

            ArrayList<Sh_Order> orders = obj.getShOrders();
            if (orders != null) {
                if (orders.size() != 0) {
                    ArrayList<String> values = new ArrayList<>();
                    Sh_Order order = orders.get(0);
                    String Sh_subscription_end = order.getSh_subscription_end();
                    Subscription sus = order.getSh_order_details().get(0).getSubscriptions().get(0);
                    String _id = sus.getId();
                    String text_eng = sus.getSubscription_eng();
                    String text_ara = sus.getSubscription_arabic();

                    values.add(_id); // "4"
                    values.add(text_eng);
                    values.add(text_ara);
                    values.add(Sh_subscription_end);
                    KisafaApplication.setSubscription(cntxt, values);
                }
            }

//    Sh_Subscription
                       String Sh_subscriptions = new Gson().toJson(obj.getSh_subscriptions());
                       KisafaApplication.set_sh_subscriptions(cntxt,Sh_subscriptions);
                       String sh_features = new Gson().toJson(obj.getSh_features());
                       KisafaApplication.set_sh_features(cntxt, sh_features);


            //if(Member.va)
            // ArrayList<Sh_Subscription> sh_subscriptions = obj.getSh_subscription();


            if (listRoom != null)
                for (int i = 0; i < listRoom.size(); i++) {
                    Manage_DB_Model.update_OR_Create_RoomModelInDB(listRoom.get(i), true);

                }


            if (listSwitch != null)
                for (int i = 0; i < listSwitch.size(); i++) {
                    Manage_DB_Model.update_OR_Create_SwitchodelInDB(listSwitch.get(i));

                }


            if (listUserRoom != null)
                for (int i = 0; i < listUserRoom.size(); i++) {
                    Manage_DB_Model.update_OR_Create_UserRoomModelInDB(listUserRoom.get(i));

                }


            if (listtariff != null)
                for (int i = 0; i < listtariff.size(); i++) {
                    Manage_DB_Model.update_OR_Create_Tariff_ModelInDB(listtariff.get(i));
                }

            if (moodsList != null)
                for (int i = 0; i < moodsList.size(); i++) {
                    Manage_DB_Model.update_OR_Create_MoodsInDB(moodsList.get(i), true);

                }


            return false;
        }
    }
}
