package com.livixa.apacam.client.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.livixa.client.R;

public class Checkyouinbox extends Activity {

    ImageView inboxgif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkyouinbox);

        inboxgif = (ImageView) findViewById(R.id.inboxgif);
        Glide.with(this).load(R.drawable.inboxani).into(inboxgif);


    }


    public void createnewpassword(View view) {
        Intent intent = new Intent();
        intent = new Intent(this, createnewpassword.class);
        startActivity(intent);
    }

}