package com.livixa.apacam.customprogressbar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.livixa.client.R;

public class CustomSimpleAlertDialogue  extends AlertDialog {
	
	
	private TextView okButton;
	
	private TextView progressTV;
	
	Context mContext;
	
	String message="";

	private LayoutInflater inflater;
	
	CustomDialogueClickListner listenner;

	public CustomSimpleAlertDialogue(Context context,String message) {
		super(context,R.style.CustomDialog);
		
		this.mContext=context;
        this.message=message;
		
		
	}
	
	
	
	@Override
    public void show() {

     super.show();
     
     inflater = LayoutInflater.from(mContext);
     
     View convertView = inflater.inflate(R.layout.custom_dialogue_layout, null);
     
     setContentView(convertView);
     
     okButton= (TextView) convertView.findViewById(R.id.tv_OK);
     
     okButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			
			if(listenner!=null)
			{
				listenner.onCustomDialogueClick();
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
	
	
	public interface CustomDialogueClickListner
	{
		
		public void onCustomDialogueClick();
	}
	
	public void setListner(CustomDialogueClickListner listenner)
	{
		this.listenner=listenner;
	}

}
