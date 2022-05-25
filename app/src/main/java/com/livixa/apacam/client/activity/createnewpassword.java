package com.livixa.apacam.client.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.livixa.client.R;
import com.livixa.client.StartActivity;

public class createnewpassword extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnewpassword);


    }


    public void success(View view){
        Intent intent = new Intent();
        intent= new Intent(this,Sucess.class);
        startActivity(intent);
    }
}