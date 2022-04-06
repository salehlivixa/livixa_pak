package com.livixa.apacam.client.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.widget.Toast;

/**
 * Created by S-Shustikov on 08.06.14.
 */
public class ReportHelper implements Thread.UncaughtExceptionHandler {
    private final AlertDialog dialog=null;
    private       Context     context;

    public ReportHelper(Context context) {
        this.context = context;
        /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Application was stopped...")
                .setPositiveButton("Report to developer about this problem.", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Not worked!
                        dialog.dismiss();
                        System.exit(0);
                        android.os.Process.killProcess(android.os.Process.myPid());

                    }
                });

        dialog = builder.create();*/
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        showToastInThread("OOPS!");
    }

    public void showToastInThread(final String str){
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "OOPS! Application crashed", Toast.LENGTH_SHORT).show();
                if(!dialog.isShowing())
                    dialog.show();
                Looper.loop();

            }
        }.start();
    }
}
