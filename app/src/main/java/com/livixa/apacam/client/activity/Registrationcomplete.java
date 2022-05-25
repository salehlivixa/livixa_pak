package com.livixa.apacam.client.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.livixa.client.R;

public class Registrationcomplete extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationcomplete);
    }

    @Override
    public void onClick(View view) {

    }

    public void home(View view){

        Intent intent = new Intent();
        intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}