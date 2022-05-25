package com.livixa.apacam.client.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.livixa.client.R;

public class Sucess extends Activity {

    ImageView successgif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess);


        successgif = (ImageView) findViewById(R.id.success);
        Glide.with(this).load(R.drawable.completedani).into(successgif);
    }
}