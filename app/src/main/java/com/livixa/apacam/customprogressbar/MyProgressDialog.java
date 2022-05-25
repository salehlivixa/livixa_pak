package com.livixa.apacam.customprogressbar;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.livixa.client.R;

public class MyProgressDialog  extends AlertDialog {
	
	
	//private AVLoadingIndicatorView avi;
	
	private TextView progressTV;
	
	Context mContext;
	
	String message="";

	private ImageView launchloader;

	private LayoutInflater inflater;

	protected MyProgressDialog(Context context,String message) {
		super(context,R.style.CustomDialog);
		
		this.mContext=context;
        this.message=message;

//		launchloader = (ImageView) findViewById(R.id.launchloader);
//		Glide.with(context).load(R.drawable.lanuchloaderani).into(launchloader);




	}



	@Override
    public void show() {

     super.show();



     inflater = LayoutInflater.from(mContext);
     
     View convertView = inflater.inflate(R.layout.custom_progress_layout, null);
     
     setContentView(convertView);
     
     launchloader= (ImageView) convertView.findViewById(R.id.avi);
		Glide.with(mContext).load(R.drawable.loaderani).into(launchloader);
     
     progressTV=(TextView) convertView.findViewById(R.id.progressTV);
     
     if(message.length()==0)
     {
    	 progressTV.setVisibility(View.GONE);
     }
     else
     {
    	 progressTV.setVisibility(View.VISIBLE);
     }
     progressTV.setText(message);
     
//     avi.setIndicator("BallClipRotatePulseIndicator");
//
//     avi.show();
     
    }
	
	
	public void hideProgress()
	{
		try
		{
	//	avi.hide();
		this.hide();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
