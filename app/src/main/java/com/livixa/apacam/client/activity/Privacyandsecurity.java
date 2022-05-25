package com.livixa.apacam.client.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.client.R;

public class Privacyandsecurity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacyandsecurity);

    }

    public void changepassword(View view){
        Intent intent = new Intent(this,ChangePasswordActivity.class);
        startActivity(intent);
    }

    public void onbackButttonClick(View view)
    {
        onBackPressed();
    }


    public void onhomeButttonClick(View view)
    {

        finish();

        Intent intent = new Intent(this, SettingsActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        KisafaApplication.perFormActivityBackTransition(this);
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