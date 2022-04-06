package com.livixa.apacam.client.activity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.activeandroid.query.Select;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mrengineer13.snackbar.SnackBar;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.livixa.apacam.client.activity.AddDeleteUpdateSwitchWithMoodsToServer.SwitchServerResult;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue.CustomDialogueClickListner;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.dbmodel.Camera_Model;
import com.livixa.apacam.dbmodel.Manage_DB_Model;
import com.livixa.apacam.dbmodel.Mood_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.client.R;
import object.p2pipcam.adapter.Switch_List_Adapter;
import object.p2pipcam.zxingtwodimensioncode.CaptureActivity;

public class Add_Edit_SwitchActivity extends Activity {

	public enum SwitchIdStatus {
		OK, BAD_LENGHT, ID_NOT_HEXADECIMAL, INVALID_TYPE
	};

	
	public enum SwitchTypes {
		SWITCH_1("01"), SWITCH_2("02"), SWITCH_3("03"),  SWITCH_SOCKET("04"), SWITCH_AC("05"),SWITCH_INVALID("");
		
		 private String value;

		 SwitchTypes(final String value) {
		        this.value = value;
		    }

		    public String getValue() {
		        return value;
		    }

		    @Override
		    public String toString() {
		        return this.getValue();
		    }
	};
	
	private ListView mSwitchListView;
	
	
	
	private View mEmptyView;

	private Switch_List_Adapter mSwitchListAdaptor;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_edit_switch_activity);
		initUiComponents();
		initOthers();
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
		
	}

	private void initUiComponents() {
		mSwitchListView = (ListView) findViewById(R.id.switchListView);
		
		mEmptyView= findViewById(R.id.switchEmptylayout);
	}

	private void initOthers() {
		
		mSwitchListAdaptor = new Switch_List_Adapter(Add_Edit_SwitchActivity.this,mEmptyView,mSwitchListView);
		
		mSwitchListView.setAdapter(mSwitchListAdaptor);

		
	}

	public void onManualAddSwitchClick(View view) {
		addSwitchManualPopupHandler();
	}

	public void onScannAddSwitchClick(View view) {
		
		
		Intent intent = new Intent(this, CaptureActivity.class);
		startActivityForResult(intent, 0);
		//overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		KisafaApplication.perFormActivityNextTransition(Add_Edit_SwitchActivity.this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			

			switch (isValidSwitchId(scanResult)) {
			case OK:

				Switch_Model tempSwitchModel=addSwitchToDb(scanResult);
				/*if(tempSwitchModel==null)
				{
					showErrorDialogue(getResources().getString(R.string.Switchwiththismacalreadyadded));
				}
				else
				{
					mSwitchListAdaptor.addSwitchtoList(tempSwitchModel);
					
				}*/
				
				
				break;
				
			case ID_NOT_HEXADECIMAL:
                showErrorDialogue(getResources().getString(R.string.Barcodeisnotvalid));
				break;
			case BAD_LENGHT:
				showErrorDialogue(getResources().getString(R.string.Barcodeisnotvalid));
				break;
			case INVALID_TYPE:
				showErrorDialogue(getResources().getString(R.string.Barcodeisnotvalid));
				break;

			default:
				break;
			}
		}
	}
	
	
	private void  addSwitchManualPopupHandler() {
		LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View popupView = layoutInflater.inflate(R.layout.add_switch_manual_popup, null);

		TextView tv_cancel = (TextView) popupView.findViewById(R.id.tv_Cancel);
		TextView tv_Done = (TextView) popupView.findViewById(R.id.tv_Done);
		final EditText et_SwitchId = (EditText) popupView.findViewById(R.id.switchidET);
		final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
				true);

		popupWindow.setTouchable(true);
		popupWindow.setFocusable(true);

		popupWindow.setAnimationStyle(R.style.BottonUpAnimation);
		popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		popupWindow.showAtLocation(popupView, 0, 0, 0);
		
		
		
		

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
				
				String mNewSwitchTempMac=et_SwitchId.getText().toString();

				switch (isValidSwitchId(mNewSwitchTempMac)) {
				case OK:

					Switch_Model tempSwitchModel=addSwitchToDb(mNewSwitchTempMac);
					/*if(tempSwitchModel==null)
					{
						showErrorDialogue(getResources().getString(R.string.Switchwiththismacalreadyadded));
					}
					else
					{
						mSwitchListAdaptor.addSwitchtoList(tempSwitchModel);
						
					}*/
					
					
					break;
					
				case ID_NOT_HEXADECIMAL:
                    showErrorDialogue(getResources().getString(R.string.Barcodeisnotvalid));
					break;
				case BAD_LENGHT:
					showErrorDialogue(getResources().getString(R.string.Barcodeisnotvalid));
					break;
				case INVALID_TYPE:
					showErrorDialogue(getResources().getString(R.string.Barcodeisnotvalid));
					break;

				default:
					break;
				}

				

			}
		});
		
		
		
	}

	private SwitchIdStatus isValidSwitchId(String switchId) {

		if (switchId == null || switchId.trim().length() < 8 || switchId.trim().length() >8) {
			return SwitchIdStatus.BAD_LENGHT;
		}

		else if (switchId.trim().length() == 8) {
			String hexaStringfromId = switchId.substring(0, 6);

			try {
				Long.parseLong(hexaStringfromId, 16);

			} catch (NumberFormatException ex) {
				return SwitchIdStatus.ID_NOT_HEXADECIMAL;
			}

			try {

				int switchType = Integer.parseInt(switchId.substring(6, 8));

				if (switchType < 1 || switchType > 5) {
					return SwitchIdStatus.INVALID_TYPE;
				}

			} catch (NumberFormatException ex) {
				return SwitchIdStatus.INVALID_TYPE;
			}

		}

		return SwitchIdStatus.OK;

	}

	public void onbackButttonClick(View view) {
		onBackPressed();
	}

	public void onhomeButttonClick(View view) {
		finish();

		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		/*overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);*/
		KisafaApplication.perFormActivityBackTransition(Add_Edit_SwitchActivity.this);
	}

	@Override
	public void onBackPressed() {
		 
		super.onBackPressed();

		
		
		
		Intent intent = new Intent();
		intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
		KisafaApplication.perFormActivityBackTransition(Add_Edit_SwitchActivity.this);
		finish();

	}

	private void showErrorDialogue(String message) {
		/*MaterialDialog dialog = new MaterialDialog.Builder(Add_Edit_SwitchActivity.this).content(message)
				.positiveText(android.R.string.ok)
				.positiveColor(KisafaApplication.getAppResources().getColor(R.color.app_header_bg))
				.callback(new MaterialDialog.ButtonCallback() {
					@Override
					public void onPositive(MaterialDialog dialog) {

					}
				}).build();
		dialog.show();*/
		
		
			final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(Add_Edit_SwitchActivity.this,message);
			
			cusDial.setListner(new CustomDialogueClickListner() {
				
				@Override
				public void onCustomDialogueClick() {
					
					
					cusDial.dismiss();
					
					
					
					
				}
			});
			
			
			cusDial.show();
			

		
	}
	
	private List<Mood_Model> fetchMoodsOfCurrentSwitchFromDb(String switchId)
	{
		List<Mood_Model> moodModelList=null;
		try
		{
			moodModelList = new Select().from(Mood_Model.class).where("Mood_Model.switchId = ?  ORDER BY Mood_Model.moodIdentifer", switchId).execute();
		}catch(Exception ex)
		{
			ex.toString();
		}
		return moodModelList;
	}
	
	
	public void associateOrUpdateMoodtoSwitchTest(Switch_Model swModel,boolean switchAlreadyExists)
	{
		
		
		if(switchAlreadyExists)
		{
			List<Mood_Model> moods=fetchMoodsOfCurrentSwitchFromDb(swModel.switch_id);
			
			if(moods!=null)
			{
				
				ByteArrayOutputStream baos=null;
				
				for(int i=0 ; i<moods.size() ; i++)
				{
					
					Mood_Model mMood=moods.get(i);
					
					mMood.time=null;
					
					mMood.awayEndTime=null;
					
					mMood.awayOnDuration="";
					
					mMood.awayOffDuration="";
					
					mMood.modelStatus=AppKeys.KEY_IS_UPDATED;
					
					mMood.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
					
					mMood.isRepeatOn="false";
					
					if(i > 5 && i < 13 )
					{
						
						if(baos==null)
						{
							try
							 {
							 Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.icon_mood_custom_white);
							 baos = new ByteArrayOutputStream();  
							 bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
							 mMood.picture= baos.toByteArray();
							 }catch(Exception ex)
							 {
								 ex.toString();
							 }
						
						}
						else
						{
							mMood.picture= baos.toByteArray();
						}
						
					}
					
					
					mMood.pictureURL="";
					mMood.isPictureSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
					
					mMood.save();
					
					mMood=null;
					
				}
				
				
			}
			
		}
		else
		{
			
			
			
			for(int i=0 ; i< 14 ; i++)
			{
				
				Mood_Model mMood = new  Mood_Model();
				
				mMood.moodIdentifer=i+1;
				
				mMood.moodId =  Manage_DB_Model.getUnitTimeStampForMood();
				
				int drawablePictureResourse=0;
				
				 if (mMood.moodIdentifer == 1) {
			         mMood.title = AppKeys.KEY_MOOD_TRAVEL;
			         mMood.modelStatus = AppKeys.KEY_IS_CREATED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_travel;
			         
			        }
				 else if (mMood.moodIdentifer == 2) {
			         mMood.title = AppKeys.KEY_MOOD_SLEEP;
			         mMood.modelStatus = AppKeys.KEY_IS_CREATED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_sleep;
			         
			        }
				 else if (mMood.moodIdentifer == 3) {
			         mMood.title = AppKeys.KEY_MOOD_WAKEUP;
			         mMood.modelStatus = AppKeys.KEY_IS_CREATED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_wakeup;
			         
			        }
				 
				 else if (mMood.moodIdentifer == 4) {
			         mMood.title = AppKeys.KEY_MOOD_GUEST;
			         mMood.modelStatus = AppKeys.KEY_IS_CREATED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_guest;
			         
			        }
				 
				 else if (mMood.moodIdentifer == 5) {
			         mMood.title = AppKeys.KEY_MOOD_Living;
			         mMood.modelStatus = AppKeys.KEY_IS_CREATED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_living;
			         
			        }
				
				 else if (mMood.moodIdentifer == 6) {
			         mMood.title = AppKeys.KEY_MOOD_SAFETY;
			         mMood.modelStatus = AppKeys.KEY_IS_CREATED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_safety;
			         
			        }
				 else if (mMood.moodIdentifer == 14) {
			         mMood.title = AppKeys.KEY_MOOD_AWAY;
			         mMood.modelStatus = AppKeys.KEY_IS_CREATED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_away;
			         
			        }
				 else
				 {
					 mMood.title = AppKeys.KEY_MOOD_NOT_AVAILABLE;
			         mMood.modelStatus = AppKeys.KEY_IS_DELETED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_custom_white;
                  }
				 
				 mMood.switchId=swModel.switch_id;
				 
				 mMood.isPictureSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
				 
				 mMood.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
				 
				 try
				 {
				 Bitmap bm = BitmapFactory.decodeResource(getResources(), drawablePictureResourse);
				 ByteArrayOutputStream baos = new ByteArrayOutputStream();  
				 bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
				 mMood.picture= baos.toByteArray();
				 }catch(Exception ex)
				 {
					 ex.toString();
				 }
				 
				 mMood.save();
				 
				 mMood=null;
				
			}
			
			
			
			
			
		}
		
		
	}
	
	public List<Mood_Model>  associateOrUpdateMoodtoSwitch(Switch_Model swModel,boolean switchAlreadyExists)
	{
		List<Mood_Model> moods=new ArrayList<>();
		
		if(switchAlreadyExists)
		{
			moods=fetchMoodsOfCurrentSwitchFromDb(swModel.switch_id);
			
			if(moods!=null)
			{
				
				ByteArrayOutputStream baos=null;
				
				for(int i=0 ; i<moods.size() ; i++)
				{
					
					Mood_Model mMood=moods.get(i);
					
					mMood.time=null;
					
					mMood.awayEndTime=null;
					
					mMood.awayOnDuration="";
					
					mMood.awayOffDuration="";
					
					mMood.modelStatus=AppKeys.KEY_IS_UPDATED;
					
					mMood.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
					
					mMood.isRepeatOn="false";
					
					if(i > 5 && i < 13 )
					{
						
						mMood.modelStatus=AppKeys.KEY_IS_DELETED;
						
						if(baos==null)
						{
							try
							 {
							 Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.icon_mood_custom_white);
							 baos = new ByteArrayOutputStream();  
							 bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
							 mMood.picture= baos.toByteArray();
							 }catch(Exception ex)
							 {
								 ex.toString();
							 }
						
						}
						else
						{
							mMood.picture= baos.toByteArray();
						}
						
					}
					
					
					mMood.pictureURL="";
					mMood.isPictureSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
					
					//mMood.save();
					
					//mMood=null;
					
				}
				
				
			}
			
		}
		else
		{
			
			
			
			for(int i=0 ; i< 14 ; i++)
			{
				
				Mood_Model mMood = new  Mood_Model();
				
				mMood.moodIdentifer=i+1;
				
				mMood.moodId =  Manage_DB_Model.getUnitTimeStampForMood();
				
				int drawablePictureResourse=0;
				
				 if (mMood.moodIdentifer == 1) {
			         mMood.title = AppKeys.KEY_MOOD_TRAVEL;
			         mMood.modelStatus = AppKeys.KEY_IS_CREATED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_travel;
			         
			        }
				 else if (mMood.moodIdentifer == 2) {
			         mMood.title = AppKeys.KEY_MOOD_SLEEP;
			         mMood.modelStatus = AppKeys.KEY_IS_CREATED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_sleep;
			         
			        }
				 else if (mMood.moodIdentifer == 3) {
			         mMood.title = AppKeys.KEY_MOOD_WAKEUP;
			         mMood.modelStatus = AppKeys.KEY_IS_CREATED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_wakeup;
			         
			        }
				 
				 else if (mMood.moodIdentifer == 4) {
			         mMood.title = AppKeys.KEY_MOOD_GUEST;
			         mMood.modelStatus = AppKeys.KEY_IS_CREATED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_guest;
			         
			        }
				 
				 else if (mMood.moodIdentifer == 5) {
			         mMood.title = AppKeys.KEY_MOOD_Living;
			         mMood.modelStatus = AppKeys.KEY_IS_CREATED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_living;
			         
			        }
				
				 else if (mMood.moodIdentifer == 6) {
			         mMood.title = AppKeys.KEY_MOOD_SAFETY;
			         mMood.modelStatus = AppKeys.KEY_IS_CREATED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_safety;
			         
			        }
				 else if (mMood.moodIdentifer == 14) {
			         mMood.title = AppKeys.KEY_MOOD_AWAY;
			         mMood.modelStatus = AppKeys.KEY_IS_CREATED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_away;
			         
			        }
				 else
				 {
					 mMood.title = AppKeys.KEY_MOOD_NOT_AVAILABLE;
			         mMood.modelStatus = AppKeys.KEY_IS_DELETED;
			         
			         drawablePictureResourse=R.drawable.icon_mood_custom_white;
                  }
				 
				 mMood.switchId=swModel.switch_id;
				 
				 mMood.isPictureSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
				 
				 mMood.isSyncedWithServer=AppKeys.KEY_IS_NOT_SYNCED;
				 
				 try
				 {
				 Bitmap bm = BitmapFactory.decodeResource(getResources(), drawablePictureResourse);
				 ByteArrayOutputStream baos = new ByteArrayOutputStream();  
				 bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
				 mMood.picture= baos.toByteArray();
				 }catch(Exception ex)
				 {
					 ex.toString();
				 }
				 
				 //mMood.save();
				 
				 moods.add(mMood);
				 
				 //mMood=null;
				
			}
			
			
			
			
			
		}
		
		return moods;
		
		
	}
	
	private Switch_Model addSwitchToDb_test(String switchMac)
	{
		Switch_Model switchModel=null;
		
		Switch_Model tempSwitchModelStatus=null;
		
		try
		{
		   try
		   {
	       switchModel = new Select().from(Switch_Model.class).where("Switch_Model.mac_address LIKE ?","%"+switchMac.trim().substring(0,6)+"%").executeSingle();
		   }catch(Exception ex)
		   {
			   ex.toString();
		   }
		   
	       if(switchModel==null)
	       {
	    	   switchModel=new Switch_Model(); 
	    	   switchModel.switch_id=Manage_DB_Model.getUnitTimeStampForSwitch();
	    	   switchModel.title=getSwitchTitle(switchMac.trim());
	    	   switchModel.type=getSwitchType(switchMac.trim()).getValue();
	    	   switchModel.model_status=AppKeys.KEY_IS_CREATED;
	    	   switchModel.isSyncedWithServer="0";
		       switchModel.mac_address=switchMac;
		       switchModel.user_id=AppPreference.getValue(Add_Edit_SwitchActivity.this, AppKeys.KEY_USER_ID);
		       
		       tempSwitchModelStatus=switchModel;
		       
		       associateOrUpdateMoodtoSwitchTest(switchModel,false);
		       
		       switchModel.save();
	       }
	       else 
	       {
	    	    if(switchModel.model_status.equals(AppKeys.KEY_IS_DELETED))
	    	    {
	    	    	
	    	    	
	    	    	switchModel.title="";
		    	    switchModel.model_status=AppKeys.KEY_IS_UPDATED;
		    	    switchModel.mac_address=switchMac;
		    	    switchModel.type=getSwitchType(switchMac.trim()).getValue();
		    	    switchModel.title=getSwitchTitle(switchMac.trim());
		    	    switchModel.isSyncedWithServer="0";
			    	
			    	tempSwitchModelStatus=switchModel;
			    	
			    	associateOrUpdateMoodtoSwitchTest(switchModel,true);
			    	
			    	switchModel.save();
	    	    }
	       }
	       
	    	
	    	
	      
		}catch(Exception ex)
		{
			ex.toString();
			return tempSwitchModelStatus;
		}
		
		return tempSwitchModelStatus;
	}
	private Switch_Model addSwitchToDb(String switchMac)
	{
		Switch_Model switchModel=null;
		
		Switch_Model tempSwitchModelStatus=null;
		
		
		WaitingStaticProgress.showProgressDialog("", Add_Edit_SwitchActivity.this);
		
		try
		{
		   try
		   {
	       switchModel = new Select().from(Switch_Model.class).where("Switch_Model.mac_address LIKE ?","%"+switchMac.trim().substring(0,6)+"%").executeSingle();
		   }catch(Exception ex)
		   {
			   ex.toString();
		   }
		   
	       if(switchModel==null)
	       {
	    	   switchModel=new Switch_Model(); 
	    	   switchModel.switch_id=Manage_DB_Model.getUnitTimeStampForSwitch();
	    	   switchModel.title="";
	    	   switchModel.type=getSwitchType(switchMac.trim()).getValue();
	    	   switchModel.model_status=AppKeys.KEY_IS_CREATED;
	    	   switchModel.isSyncedWithServer="0";
		       switchModel.mac_address=switchMac;
		       switchModel.user_id=AppPreference.getValue(Add_Edit_SwitchActivity.this, AppKeys.KEY_USER_ID);
		       
		           tempSwitchModelStatus=switchModel;
		       
		           final List<Mood_Model> moods=associateOrUpdateMoodtoSwitch(switchModel,false);
		       
		           final Switch_Model tempSwitchModel=switchModel;
		           
		            AddDeleteUpdateSwitchWithMoodsToServer addDeleteUpdateSwitchWithMoodsToServer=new AddDeleteUpdateSwitchWithMoodsToServer(Add_Edit_SwitchActivity.this, switchModel, moods);
		           
		           addDeleteUpdateSwitchWithMoodsToServer.addResultListner(new SwitchServerResult() {
					
					@Override
					public void onSuccess(ServerResponse response) {
						
						try
						{
							
							for(int i=0; i<moods.size();i++)
							{
								
								moods.get(i).save();
							}
							
							tempSwitchModel.save();
						}
						catch(Exception ex)
						{}
						
						
						
						mSwitchListAdaptor.addSwitchtoList(tempSwitchModel);
						
						WaitingStaticProgress.hideProgressDialog();
						
					}
					
					@Override
					public void onFailer(String response) {
						
						
						WaitingStaticProgress.hideProgressDialog();
						
						try
						{
						new SnackBar.Builder(Add_Edit_SwitchActivity.this).withMessage(response).withDuration(SnackBar.MED_SNACK).show();
						}catch(Exception ex)
						{
							
						}
						
						
						
						
					}
				});
		           
		           addDeleteUpdateSwitchWithMoodsToServer.makeCalltoServer();
	       }
	       else 
	       {
	    	      //if(switchModel.model_status.equals(AppKeys.KEY_IS_DELETED))
	    	    {
	    	    	
	    	    	
	    	    	switchModel.title="";
		    	    switchModel.model_status=AppKeys.KEY_IS_UPDATED;
		    	    switchModel.mac_address=switchMac;
		    	    switchModel.type=getSwitchType(switchMac.trim()).getValue();
		    	    switchModel.isSyncedWithServer="0";
			    	
			    	tempSwitchModelStatus=switchModel;
			    	
			    	final List<Mood_Model> moods=associateOrUpdateMoodtoSwitch(switchModel,true);
			    	
			    	final Switch_Model tempSwitchModel=switchModel;
			    	
			    	
			    	
			           
		            AddDeleteUpdateSwitchWithMoodsToServer addDeleteUpdateSwitchWithMoodsToServer=new AddDeleteUpdateSwitchWithMoodsToServer(Add_Edit_SwitchActivity.this, switchModel, moods);
		           
		           addDeleteUpdateSwitchWithMoodsToServer.addResultListner(new SwitchServerResult() {
					
					@Override
					public void onSuccess(ServerResponse response) {
						
						try
						{
							
							for(int i=0; i<moods.size();i++)
							{
								
								moods.get(i).save();
							}
							
							tempSwitchModel.save();
						}
						catch(Exception ex)
						{}
						
						mSwitchListAdaptor.addSwitchtoList(tempSwitchModel);
						
						WaitingStaticProgress.hideProgressDialog();
						
						
					}
					
					@Override
					public void onFailer(String response) {
					
						try
						{
						new SnackBar.Builder(Add_Edit_SwitchActivity.this).withMessage(response).withDuration(SnackBar.MED_SNACK).show();
						}catch(Exception ex)
						{
							
						}
						
						WaitingStaticProgress.hideProgressDialog();
						
						
					}
				});
		           
		           addDeleteUpdateSwitchWithMoodsToServer.makeCalltoServer();
	    	    }
	       }
	       
	    	
	    	
	      
		}catch(Exception ex)
		{
			ex.toString();
			WaitingStaticProgress.hideProgressDialog();
			return tempSwitchModelStatus;
		}
		
		return tempSwitchModelStatus;
	}
	
	
	public static SwitchTypes getSwitchType(String switchId)
	{
		
		if(switchId!=null)
		{
			String tempSwitchType=switchId.substring(6,8);
			if(tempSwitchType.equals("01"))
			{
				return SwitchTypes.SWITCH_1;
			}
			else if(tempSwitchType.equals("02"))
			{
				return SwitchTypes.SWITCH_2;
			}
			else if(tempSwitchType.equals("03"))
			{
				return SwitchTypes.SWITCH_3;
			}
			else if(tempSwitchType.equals("04"))
			{
				return SwitchTypes.SWITCH_SOCKET;
			}
			else if(tempSwitchType.equals("05"))
			{
				return SwitchTypes.SWITCH_AC;
			}
			 
		}
		return SwitchTypes.SWITCH_INVALID;
	}
	
	public static String getSwitchTitle(String switchId)
	{
		
		
		 if(KisafaApplication.currentAppLanguage!=null)
		 {
			 if(KisafaApplication.currentAppLanguage.equals(AppKeys.LANGUAGES.ENGLISH))
				{
				 if(switchId!=null)
					{
						String tempSwitchType=switchId.substring(6,8);
						if(tempSwitchType.equals("01"))
						{
							return "Switch 1";
						}
						else if(tempSwitchType.equals("02"))
						{
							return "Switch 2";
						}
						else if(tempSwitchType.equals("03"))
						{
							return "Switch 3";
						}
						else if(tempSwitchType.equals("04"))
						{
							return "Socket";
						}
						else if(tempSwitchType.equals("05"))
						{
							return "AC";
						}
						 
					}
				}
				 
			   {
			    	
			    	if(switchId!=null)
					{
						String tempSwitchType=switchId.substring(6,8);
						if(tempSwitchType.equals("01"))
						{
							return "?????  1";
						}
						else if(tempSwitchType.equals("02"))
						{
							return "?????  2";
						}
						else if(tempSwitchType.equals("03"))
						{
							return "?????  3";
						}
						else if(tempSwitchType.equals("04"))
						{
							return "???";
						}
						else if(tempSwitchType.equals("05"))
						{
							return "????";
						}
						 
					}
			    }
		 }
		
		
		return "";
	}
}
