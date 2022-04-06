package com.livixa.apacam.customprogressbar;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.livixa.client.R;


public class Dialogue_Message extends AlertDialog{
	
	
    
	
	String message="";
	
	 LayoutInflater inflater ;
	 Context context;
	 View convertView ;

	public Dialogue_Message(Context context) {
		
		
         super(context,R.style.CustomDialog);
		
         this.context=context;
		
	}
	
	
	
	@Override
    public void show() {

     super.show();
     
     inflater = LayoutInflater.from(context);
		
     convertView = inflater.inflate(R.layout.custom_dialogue_layout_two_buttons, null);
     
     setContentView(convertView);
     
     
	}
	
	
	public View getContainer()
	{
		
		return convertView;
	}
	
	

}
