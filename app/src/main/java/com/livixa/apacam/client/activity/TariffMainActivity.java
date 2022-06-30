package com.livixa.apacam.client.activity;

import java.text.DecimalFormat;
import java.util.List;

import com.activeandroid.query.Select;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.livixa.apacam.client.activity.ShSwitchView.OnSwitchStateChangeListener;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.other.DialogAddTariffAlert;
import com.livixa.apacam.client.response.tariff_energy.CustomTextWatcher;
import com.livixa.apacam.client.response.tariff_energy.DecimalFilter;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons.CustomDialogueTwoButtonsClickListner;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue.CustomDialogueClickListner;
import com.livixa.apacam.dbmodel.Manage_DB_Model;
import com.livixa.apacam.dbmodel.Tariff_Model;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.R;

import org.w3c.dom.Text;

import object.p2pipcam.adapter.Tariff_List_Adapter;

public class TariffMainActivity extends Activity {
	
	
	Context mContext;
	
	ListView    tariffListView;
	
	View        emptyListView;
	
	
	Tariff_List_Adapter  tariff_List_Adapter;

	private boolean isEndExist=false;

	
	boolean isAllowedToMoveBack=false;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tariff_main);
		
		
		mContext=this;
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
		tariffListView=(ListView) findViewById(R.id.tariffListView);
		emptyListView=findViewById(R.id.tariffEmptylayout);
		
		tariff_List_Adapter=new Tariff_List_Adapter(mContext, emptyListView, tariffListView);
		
		tariffListView.setAdapter(tariff_List_Adapter);
		
		
		String tempWATAGE_UNIT= AppPreference.getValue(mContext, AppKeys.KEY_USER_WATAGE_UNIT);
		String tempPrice_UNIT= AppPreference.getValue(mContext, AppKeys.KEY_USER_PRICE_UNIT);
		
		
//		TextView watageUnit=(TextView) findViewById(R.id.watageUnit);
//
//		TextView priceUnit=(TextView) findViewById(R.id.priceUnit);
//
//		if(tempWATAGE_UNIT!=null)
//		watageUnit.setText(getString(R.string.Slabin)+" " + tempWATAGE_UNIT);
//
//
//		if(tempPrice_UNIT!=null)
//		priceUnit.setText(getString(R.string.ResidentialTariffin)+" " + tempPrice_UNIT);
		
		
	}




@Override
public void onBackPressed() {
	 
	
	
	
	
	
	

	isEndExist=false;
	
	List<Tariff_Model> tariffs=tariff_List_Adapter.fetchTriffesFromDb();
	
	if(tariffs!=null)
	{
		for(int i=0; i<tariffs.size(); i++)
		{
			Tariff_Model tampModel=tariffs.get(i);
			
			
			if(tampModel.upper_limit.equals("-1"))
			{
				isEndExist=true;
				break;
				
			}
			
			
		}
		
		
		
		
		
		if(!isEndExist && tariffs.size() > 0 )
		{
			
			if(!isAllowedToMoveBack)
			{
		
				final CustomAlertDialogueTwoButtons cusTwo=new CustomAlertDialogueTwoButtons(mContext, getString(R.string.notyetaddedupperlimit));
				
				cusTwo.setListner(new CustomDialogueTwoButtonsClickListner() {
					
					@Override
					public void onCustomDialoguePositiveClick() {
						
						
						cusTwo.dismiss();
						
						isAllowedToMoveBack=false;
						
						
					//	tariffSettingPopup();
						
						
					}
					
					@Override
					public void onCustomDialogueNegativeClick() {
						
						
						cusTwo.dismiss();
						
						isAllowedToMoveBack=true;
						
						 onBackPressed();
						 
						 
						   
						
					}
				});
				
				
				cusTwo.show();
			}
		}
		else
		{
			isAllowedToMoveBack=true;
		}
		
		
		
		
	}
	
	
	if(isAllowedToMoveBack)
	{
	
	    super.onBackPressed();
	
	     finish();
		
		Intent intent = new Intent(TariffMainActivity.this, SettingsActivity.class);
		
		
		KisafaApplication.perFormActivityBackTransition(this);
		
		startActivity(intent);
	}
	
	
	
}



public void onbackButttonClick(View view)
{
	onBackPressed();
}


public void oncountryclick(View view){
	countryPopup();
}

public void onOptionClick(View view)
{
	
	OptionPopup();
	
	
}



private void tariffSettingPopup_1() {

	 LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	 View popupView = layoutInflater.inflate(R.layout.tariff_setting_popup, null);
	
	ImageView tv_cancel=(ImageView) popupView.findViewById(R.id.tv_Cancel);
	TextView tv_Done=(TextView) popupView.findViewById(R.id.tv_Done);
	
	final TextView  lowerLimit=(TextView) popupView.findViewById(R.id.lowerLimit);
	final EditText  upperLimit=(EditText) popupView.findViewById(R.id.upperLimit);
	final EditText  price=(EditText) popupView.findViewById(R.id.priceTariff);
	
	final ShSwitchView s_switch=(ShSwitchView)popupView.findViewById(R.id.infiniteTriffValue);
	
	final PopupWindow popupWindow=new PopupWindow(popupView,LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);

	 popupWindow.setTouchable(true);
	 popupWindow.setFocusable(true);
	 
	 popupWindow.setAnimationStyle(R.style.BottonUpAnimation);
	 
	 popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

	 popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
	 
	 
	 
	 

	 
	 
	 //price.addTextChangedListener(new CustomTextWatcher(price));
	 
	 
	 price.addTextChangedListener(new DecimalFilter(price));
	 
	 
	 
	 int  currentLowerLimit=getLowerLimit();
	 
	 if(currentLowerLimit > 0)
	 lowerLimit.setText((currentLowerLimit+1)+"");
	 else
		 lowerLimit.setText(currentLowerLimit+"");
	 
	 s_switch.setOnSwitchStateChangeListener(new OnSwitchStateChangeListener() {
		
		@Override
		public void onSwitchStateChange(boolean isOn) {
			
			
			if(isOn)
			{
				
				upperLimit.setEnabled(false);
				upperLimit.setFocusableInTouchMode(false);
				upperLimit.setText("");
			}
			else
			{
				upperLimit.setEnabled(true);
				upperLimit.setFocusableInTouchMode(true);
				
			}
			
		}
	});
	 
	 
	 tv_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				popupWindow.dismiss();
			}
		});
	 
	 tv_Done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
				popupWindow.dismiss();
				
				
				if(lowerLimit.getText().toString().length()==0)
				 {
					showErrorMessage("Please enter valid input");
					
					return;
					
				 }
				
				if(upperLimit.getText().toString().length()==0 && !s_switch.isOn())
				 {
					showErrorMessage(getString(R.string.UpperlimitRQ));
					
					return;
					
				 }
				
				
				if(upperLimit.getText().toString().length() > 0)
				 {
					
					try
					{
					int lowerLimitValue=Integer.parseInt(lowerLimit.getText().toString().trim());
					
					int upperLimitValue=Integer.parseInt(upperLimit.getText().toString().trim());
					
					if(upperLimitValue <=lowerLimitValue)
					{
						showErrorMessage(getString(R.string.Upperlimitshouldbehigher));
						
						return;
					}
					
					
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
					
					
					
				 }
				
				if(price.getText().toString().length()==0)
				 {
					showErrorMessage(getString(R.string.Priceisrequired));
					
					return;
					
				 }
				
				
				
				
				
				
				boolean isInfiniteLimitExist=false;
				
				List<Tariff_Model> tariffs=tariff_List_Adapter.fetchTriffesFromDb();
				
				if(tariffs!=null)
				{
					for(int i=0; i<tariffs.size(); i++)
					{
						Tariff_Model tampModel=tariffs.get(i);
						
						
						if(tampModel.upper_limit.equals("-1"))
						{
							isInfiniteLimitExist=true;
							break;
							
						}
						
						
					}
				}
					
				
				if(isInfiniteLimitExist)
				 {
					showErrorMessage(getString(R.string.alreadyaddedtariff));
					
					return;
					
				 }
				
					
					
				
				
				
						
						Tariff_Model tempModel=addTriffToDB(lowerLimit.getText().toString() ,upperLimit.getText().toString() ,price.getText().toString(),s_switch.isOn());
						
						if(tempModel==null)
						{
							
							final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(mContext,"Tariff already exist with these values");
							
							cusDial.setListner(new CustomDialogueClickListner() {
								
								@Override
								public void onCustomDialogueClick() {
									
									
									cusDial.dismiss();
									
									
									
									
								}
							});
							
							
							cusDial.show();
							
						}
						else
						{
							tariff_List_Adapter.addSwitchtoList(tempModel);
						}
						
						
					
					
				
				
				
			}
		});
	 
	 
	
}

	private void countryPopup() {

		LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View popupView = layoutInflater.inflate(R.layout.activity_tariff_option_country_popup, null);


		TextView country = (TextView) popupView.findViewById(R.id.country);
		TextView tv_cancel=(TextView) popupView.findViewById(R.id.cancelButton);



		final PopupWindow popupWindow=new PopupWindow(popupView,
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
				true);

		popupWindow.setTouchable(true);
		popupWindow.setFocusable(true);

		popupWindow.setAnimationStyle(R.style.BottonUpAnimation);



		popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);

		country.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				tariffListView.setVisibility(View.VISIBLE);
				popupWindow.dismiss();
			}
		});

		tv_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				popupWindow.dismiss();
			}
		});




	}


private void OptionPopup() {

	 LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	 View popupView = layoutInflater.inflate(R.layout.tariff_option_popup, null);
	
	TextView tv_cancel=(TextView) popupView.findViewById(R.id.cancelButton);
	TextView tv_addNewTariff=(TextView) popupView.findViewById(R.id.tv_addNewTariff);
	TextView tv_deleteAllTariffs=(TextView) popupView.findViewById(R.id.tv_deleteAllTariff);
	TextView tv_settarrifalert=(TextView) popupView.findViewById(R.id.tv_settarrifalert);

	
	
	

	final PopupWindow popupWindow=new PopupWindow(popupView,
	         LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,        
	 true);

	 popupWindow.setTouchable(true);
	 popupWindow.setFocusable(true);
	 
	 popupWindow.setAnimationStyle(R.style.BottonUpAnimation);
	 
	

	 popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
	 
	 tv_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				popupWindow.dismiss();
			}
		});
	 
	 
	 tv_addNewTariff.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				popupWindow.dismiss();
				
				

				boolean isInfiniteLimitExist=false;
				
				List<Tariff_Model> tariffs=tariff_List_Adapter.fetchTriffesFromDb();
				
				if(tariffs!=null)
				{
					for(int i=0; i<tariffs.size(); i++)
					{
						Tariff_Model tampModel=tariffs.get(i);
						
						
						if(tampModel.upper_limit.equals("-1"))
						{
							isInfiniteLimitExist=true;
							break;
							
						}
						
						
					}
				}
					
				
				if(isInfiniteLimitExist)
				 {
					showErrorMessage(getString(R.string.alreadyaddedtariff));
					
					return;
					
				 }
				else
				{
				// tariffSettingPopup();
				}
				
				
			}
		});
	tv_settarrifalert.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View view) {

			popupWindow.dismiss();
			new DialogAddTariffAlert(TariffMainActivity.this);

		}
	});
	 
	 tv_deleteAllTariffs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				popupWindow.dismiss();
				
				
				
				final CustomAlertDialogueTwoButtons cusTwo=new CustomAlertDialogueTwoButtons(mContext, getString(R.string.deletetariffs));
				
				cusTwo.setListner(new CustomDialogueTwoButtonsClickListner() {
					
					@Override
					public void onCustomDialoguePositiveClick() {
						
						
						cusTwo.dismiss();
						
						
						List<Tariff_Model> tariffs=tariff_List_Adapter.fetchTriffesFromDb();
						
						if(tariffs!=null)
						{
							for(int i=0; i<tariffs.size(); i++)
							{
								Tariff_Model tampModel=tariffs.get(i);
								
								
								tampModel.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
								tampModel.model_status=AppKeys.KEY_IS_DELETED;
								//tampModel.user_id="";
								
								tampModel.save();
								
								
								
								
								
							}
							
							
							tariff_List_Adapter.clearList();
						}
						
					}
					
					@Override
					public void onCustomDialogueNegativeClick() {
						
						
						cusTwo.dismiss();
						
						
						
					}
				});
				
				
				
				cusTwo.show();
				
				
				
				
				
				
				
				
				
			}
		});
	 
	 
	
}


  private Tariff_Model addTriffToDB(String lowerLimit,String upperLimit,String price,boolean infinite)
  {
	  
	  Tariff_Model  tariffModel=null;
	  
	  try
	   {
		  tariffModel = new Select().from(Tariff_Model.class).where("Tariff_Model.lower_limit = ? ",lowerLimit.trim()).executeSingle();
		  //tariffModel = new Select().from(Tariff_Model.class).where("Tariff_Model.lower_limit = ?  AND Tariff_Model.upper_limit = ?",lowerLimit.trim(),upperLimit.trim()).executeSingle();
	   }catch(Exception ex)
	   {
		   ex.toString();
	   }
	  
	  if(tariffModel==null)
	  {
		  
		  tariffModel=new  Tariff_Model();
		  
		  tariffModel.user_id=AppPreference.getValue(mContext, AppKeys.KEY_USER_ID);
		  
		  tariffModel.tariff_id=Manage_DB_Model.getUnitTimeStampForTariff();
		  
		  tariffModel.lower_limit=lowerLimit;
		  
		  if(infinite)
		  {
			  tariffModel.upper_limit="-1";
		  }
		  else
		  tariffModel.upper_limit=upperLimit;
		  
		  tariffModel.price=price;
		  
		  tariffModel.model_status=AppKeys.KEY_IS_CREATED;
		  tariffModel.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
		  
		  
		  tariffModel.save();
		  
		  return tariffModel;
		  
		  
	  }
	  else
	  {
		 if(tariffModel.model_status.equals(AppKeys.KEY_IS_DELETED))
		 {
			 tariffModel.user_id=AppPreference.getValue(mContext, AppKeys.KEY_USER_ID);
			  
			  //tariffModel.tariff_id=Manage_DB_Model.getUnitTimeStampForTariff();
			  
			  tariffModel.lower_limit=lowerLimit;
			  
			  if(infinite)
			  {
				  tariffModel.upper_limit="-1";
			  }
			  else
			  tariffModel.upper_limit=upperLimit;
			  
			  tariffModel.price=price;
			  
			  tariffModel.model_status=AppKeys.KEY_IS_UPDATED;
			  tariffModel.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
			  
			  
			  tariffModel.save();
			  
			  return tariffModel;
			 
		 }
		 
		 
	  }
	  
	  
	  return null;
	   
	  
  }

  
  private void showErrorMessage(String message)
  {
	  
	  final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(TariffMainActivity.this,message);
		
		cusDial.setListner(new CustomDialogueClickListner() {
			@Override
			public void onCustomDialogueClick() {
				cusDial.dismiss();
			}
		});
		cusDial.show();

  }
  
  
  public int  getLowerLimit()
  {
	  
	  
	  int lowerLimit=0;
	  
	  List<Tariff_Model> tariffs=tariff_List_Adapter.fetchTriffesFromDb();
		
		if(tariffs!=null)
		{
			for(int i=0; i<tariffs.size(); i++)
			{
				Tariff_Model tampModel=tariffs.get(i);
				
				try
				{
					
				String Value =	tampModel.upper_limit;
				
				       int lLimit=Integer.parseInt(Value);
				       
				       if(lLimit > lowerLimit)
				       {
				    	   lowerLimit=lLimit;
				       }
				       
					
					
				}
				catch(Exception ex)
				{
					
				}
			}
		}
			
		
		
		return   lowerLimit;
	  
  }


}