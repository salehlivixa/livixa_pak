package com.github.mrengineer13.snackbar;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.livixa.client.R;


public class DialogueMessage extends AlertDialog{
	
	
    
	
	String message="";
	
	 LayoutInflater inflater ;
	 
	 View convertView ;

	protected DialogueMessage(Context context) {
		
		
         super(context,R.style.CustomDialogSnack);
		
         inflater = LayoutInflater.from(context);
		
         convertView = inflater.inflate(R.layout.sb__snack, null);
		
	}
	
	
	
	@Override
    public void show() {

     super.show();
     
     
     setContentView(convertView);
     
     
	}
	
	
	public View getContainer()
	{
		
		return convertView;
	}
	
	

}
