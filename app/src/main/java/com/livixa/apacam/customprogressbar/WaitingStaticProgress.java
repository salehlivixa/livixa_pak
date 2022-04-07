package com.livixa.apacam.customprogressbar;

import android.app.ProgressDialog;
import android.content.Context;
import info.lamatricexiste.network.StaticProgress;
import com.livixa.client.R;

public class WaitingStaticProgress
{

	
	
	
	
	
	public static MyProgressDialog mProgressDialog;
	
	
	public WaitingStaticProgress()
	{
		
		
		
	}
	

	public static void showProgressDialog(String text, Context context) 
	{
		try
		{
		
			try
			{
			if(mProgressDialog!=null && mProgressDialog.isShowing())
			{
				mProgressDialog.dismiss();
			}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		
		mProgressDialog = new MyProgressDialog(context,text);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	public static boolean  isProgressVisisble()
	{
		if(mProgressDialog!=null)
		{
			return mProgressDialog.isShowing();
		}
		return false;
	}
	
	public static void hideProgressDialog()
	{
		
		try
		{
		
		if(mProgressDialog!=null && mProgressDialog.isShowing())
		{
			mProgressDialog.dismiss();
		}
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	
}
