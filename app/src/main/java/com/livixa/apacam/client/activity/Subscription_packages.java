package com.livixa.apacam.client.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Features;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Subscription;
import com.livixa.client.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import object.p2pipcam.adapter.Subscription_List_Adapter;
import object.p2pipcam.adapter.Tariff_List_Adapter;

public class Subscription_packages extends Activity {

    Context mContext;

    ListView subscriptionlistview;

    List<Sh_Subscription> sh_subscriptionList;
    List<Sh_Features> sh_features;
    Subscription_List_Adapter  subscription_list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_packages);

        mContext=this;

        subscriptionlistview = (ListView) findViewById(R.id.subscriptionlistview);



        sh_subscriptionList = new ArrayList<>();
        sh_features = new ArrayList<>();
        try {
            String _sh_subsciption = KisafaApplication.get_sh_subscriptions(Subscription_packages.this);
          if(!_sh_subsciption.equals("")) {
              Sh_Subscription[] List = new Gson().fromJson(_sh_subsciption, Sh_Subscription[].class);
              sh_subscriptionList = Arrays.asList(List);

          }
          String _sh_feature =  KisafaApplication.get_sh_features(Subscription_packages.this);
        if(!_sh_feature.equals("")){
            Sh_Features[] List = new Gson().fromJson(_sh_feature, Sh_Features[].class);
            sh_features = Arrays.asList(List);
        }
        }catch (Exception e){
            e.printStackTrace();
        }


        Subscription_List_Adapter adapter = new Subscription_List_Adapter(Subscription_packages.this,sh_subscriptionList,sh_features);
        subscriptionlistview.setAdapter(adapter);
    }


    public void onbackButttonClick(View view)
    {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();


        finish();

        Intent intent = new Intent(this, SettingsActivity.class);


        KisafaApplication.perFormActivityBackTransition(this);

        startActivity(intent);


    }
}