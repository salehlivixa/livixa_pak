package info.lamatricexiste.network;

import android.app.ProgressDialog;
import android.content.Context;
import com.livixa.client.R;

public class StaticProgress
{

	
	
	
	
	
	public static ProgressDialog mProgressDialog;
	
	
	public StaticProgress()
	{
		
		
		
	}
	

	public static void showProgressDialog(String text, Context context) 
	{
		mProgressDialog = new ProgressDialog(context,R.style.progressDialogTheme);
		mProgressDialog.setMessage(text);
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(100);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();
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
		if(mProgressDialog!=null && mProgressDialog.isShowing())
		{
			mProgressDialog.dismiss();
		}
	}
	
	
	
}
