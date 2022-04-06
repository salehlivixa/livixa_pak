package com.livixa.apacam.customprogressbar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.livixa.client.R;

public class CustomAlertDialogueTwoButtons  extends AlertDialog {
	
	
	private TextView okButton;
	
	private TextView noButton;
	
	private TextView progressTV;
	
	Context mContext;
	
	String message="";

	private LayoutInflater inflater;
	
	CustomDialogueTwoButtonsClickListner listenner;

	public CustomAlertDialogueTwoButtons(Context context,String message) {
		super(context,R.style.CustomDialog);
		
		this.mContext=context;
        this.message=message;
		
		
	}
	
	
	
	@Override
    public void show() {

     super.show();
     
     inflater = LayoutInflater.from(mContext);
     
     View convertView = inflater.inflate(R.layout.custom_dialogue_layout_two_buttons, null);
     
     setContentView(convertView);
     
     okButton= (TextView) convertView.findViewById(R.id.tv_OK);
     
     noButton= (TextView) convertView.findViewById(R.id.tv_NO);
     
     okButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			
			if(listenner!=null)
			{
				listenner.onCustomDialoguePositiveClick();
			}
			
		}
	});
     
     
     noButton.setOnClickListener(new View.OnClickListener() {
 		
 		@Override
 		public void onClick(View v) {
 			
 			
 			if(listenner!=null)
 			{
 				listenner.onCustomDialogueNegativeClick();
 			}
 			
 		}
 	});
     
     progressTV=(TextView) convertView.findViewById(R.id.progressTV);
     
     progressTV.setText(message);
     
     
     
    }
	
	
	public void hideProgress()
	{
		
		this.hide();
	}
	
	
	public interface CustomDialogueTwoButtonsClickListner
	{
		
		public void onCustomDialoguePositiveClick();
		
		public void onCustomDialogueNegativeClick();
	}
	
	public void setListner(CustomDialogueTwoButtonsClickListner listenner)
	{
		this.listenner=listenner;
	}

}
