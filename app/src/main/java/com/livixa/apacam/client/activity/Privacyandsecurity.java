package com.livixa.apacam.client.activity;

import static android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.client.R;

import java.util.concurrent.Executor;

public class Privacyandsecurity extends Activity {

    ShSwitchView touchid;
    private static  final int REQUEST_CODE = 101010;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacyandsecurity);

        boolean value = KisafaApplication.get_biometricstatus(this);
        ShSwitchView touchid = (ShSwitchView)findViewById(R.id.touchid);
        touchid.setOn(value);
        touchid.setOnSwitchStateChangeListener(b->{KisafaApplication.setbiometricstatus(Privacyandsecurity.this,b);
            if (b) {
                KisafaApplication.setbiometricstatus(this,true);

                BiometricManager biometricManager = BiometricManager.from(this);
                switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
                    case BiometricManager.BIOMETRIC_SUCCESS:
                        Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
                       break;
                    case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                        Log.e("MY_APP_TAG", "No biometric features available on this device.");
                        break;
                    case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                        Log.e("MY_APP_TAG", "Biometric features are currently unavailable.");
                        break;
                    case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                        // Prompts the user to create credentials that your app accepts.
                        final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                        enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
                        startActivityForResult(enrollIntent, REQUEST_CODE);
                        break;
                }


            }
        });



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