package com.livixa.apacam.client.activity;

import com.activeandroid.query.Select;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity.SwitchTypes;
import com.livixa.apacam.client.activity.TCPClient_New.ServerMessageResopnse;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.sliderview.CircularSeekBar;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue.CustomDialogueClickListner;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.dbmodel.Room_Model;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.services.Sync_Service;
import com.livixa.apacam.services.TCP_Client_Service;
import com.livixa.client.R;
import object.p2pipcam.adapter.RoomandSwitchesTCPGridViewAdapter;
import object.p2pipcam.adapter.RoomandSwitchesTCPGridViewAdapter.TCPTask;

public class RoomAndSwitchesTCPActivity extends Activity implements TCPTask{

	private String mRoomTitle = "";

	private String mRoomId = "";

	private TextView mTVRoomTitle;

	private GridView mRoomSwitchesGridView;

	private View mEmptyView;

	RoomandSwitchesTCPGridViewAdapter mainScreenRoomSwitchesGridViewAdapter;

	private Room_Model mRoomModel;
	
	
	
	
	RelativeLayout switchesMainLayout;
	
	LinearLayout roomsMainLayout;
	
	
	TCPClient_New singleTcpClient;
	
	connectTask task;
	
	Context mContext;
	
	Animation animation1__ ;
	
	Animation animation2__ ;
	
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	
	 LinearLayout layoutContainer;
		private Switch_Model mSwitchModel;
		
		
		CheckBox    acSwitchCheckBox;
		
		boolean isProgressIsZero=true;
		
		boolean lockProgressOne=false;
		boolean lockProgressTwo=false;
		boolean lockProgressThree=false;
		
		
		
		
		boolean isProgressIsZeroOne=true;
		
		boolean isProgressIsZeroTwo=true;
		
		boolean isProgressIsZeroThree=true;
		
		
		
		RelativeLayout rLayoutControl0;
		
		RelativeLayout rLayoutControl1;
		
		RelativeLayout rLayoutControl21;
		
		RelativeLayout rLayoutControl22;
		
		RelativeLayout rLayoutControl32;
		
		RelativeLayout rLayoutControl33;
		
		
		
		
		CheckBox    SwitchCheckBoxrLayoutControl0;
		
		
		
		CheckBox    SwitchCheckBoxrLayoutControl21;
		
		
		CheckBox    SwitchCheckBoxrLayoutControl32;
		
		
		
		
		CircularSeekBar seekBar;
		
		
		CircularSeekBar seekBarTwo;
		
		CircularSeekBar seekBarThree;
		
		
		
		 byte [] command=new byte[18];
		
		
	    View childLayout = null;
	    
	    
	    private String currentCommand="";
	    
	    
	    String currentSwitchLEDStatus="01";
	    
	    
	    boolean isLatestCommand=false;
	    
	    
	    boolean isSocketConnected=false;
	    
	   private   boolean isPerformingFirstConnection=true;
	   
	   
	   boolean isCallFromPopup=false;
	   
	   
	   boolean  isConnectionFailedEventOccured=false;
	   
	    
	
	
    boolean mBound = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_and_switches_tcp);
		
		mContext=RoomAndSwitchesTCPActivity.this;

		mTVRoomTitle = (TextView) findViewById(R.id.tv_title);
		handleIntent();
		mTVRoomTitle.setText(mRoomTitle);

		initUiComponents();

		initOthers();
		
		
		Sync_Service.setActivityToDisplayLogoutErrorThroughtTheApp(this);
	}

	private void initUiComponents() {
		mRoomSwitchesGridView = (GridView) findViewById(R.id.roomSwitchesGridView);
		
		switchesMainLayout=(RelativeLayout) findViewById(R.id.switchesMainLayout);
		
		roomsMainLayout=(LinearLayout) findViewById(R.id.roomsMainLayout);
		
		
		
		

		mEmptyView = findViewById(R.id.roomSwitchesEmptylayout);

	}

	private void initOthers() {

		mainScreenRoomSwitchesGridViewAdapter = new RoomandSwitchesTCPGridViewAdapter(RoomAndSwitchesTCPActivity.this,
				mEmptyView, mRoomSwitchesGridView, mRoomModel);
		
		mainScreenRoomSwitchesGridViewAdapter.setTCPStartCallBack(RoomAndSwitchesTCPActivity.this);
		
		mRoomSwitchesGridView.setAdapter(mainScreenRoomSwitchesGridViewAdapter);

	}

	public void handleIntent() {
		try {

			Intent intent = getIntent();

			mRoomId = intent.getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
			mRoomTitle = intent.getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
			mRoomModel = getRoomFromDb(mRoomId);

		} catch (Exception ex) {
			ex.toString();
		}
	}

	public void onbackButttonClick(View view) {
		onBackPressed();
	}

	public void onhomeButttonClick(View view) {
		
		
		Intent stopserviceIntent = new Intent(this, TCP_Client_Service.class);
		stopService(stopserviceIntent);
		
		
		finish();

		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		// overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		KisafaApplication.perFormActivityBackTransition(this);
		
		
		
		
		
	}
	
	
	@Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        
    }
	
	

    
    
    
	@Override
	public void onBackPressed() {
		
		
		
		
		
		
		
		
		
		if(switchesMainLayout!=null && switchesMainLayout.getVisibility()==View.VISIBLE)
		{
			
			onSwitchButttonClick(null);
		}
		else
		{
			super.onBackPressed();
			
			finish();
	
			Intent intent = new Intent(this, View_RoomsActivity.class);
	
			KisafaApplication.perFormActivityBackTransition(this);
	
			startActivity(intent);
		}

	}

	private Room_Model getRoomFromDb(String roomId) {
		Room_Model roomModel = null;

		try {
			roomModel = new Select().from(Room_Model.class).where("Room_Model.room_id = ?", roomId).executeSingle();
		} catch (Exception ex) {
			ex.toString();
		}

		return roomModel;
	}
	
	
	public void onSwitchStatusResponse(String command)
	{
		
		
		
		
		
		
		
	}

	

	
	public void onSocketTimeOutFailier(String message) {
		
		try
		{
		
		
		
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				WaitingStaticProgress.hideProgressDialog();
				/*
				final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(RoomAndSwitchesTCPActivity.this,"Failed to connect");
				
				cusDial.setListner(new CustomDialogueClickListner() {
					
					@Override
					public void onCustomDialogueClick() {
						
						
						cusDial.dismiss();
						
						
						
						
					}
				});
				
				
				cusDial.show();*/
			}
		});
		
		
		singleTcpClient.stopClient();
		
		
		}catch(Exception ex){}
		
	}
	
	

	
	public void unableToCreateConnection(String message) {
		
		
		try
		{
		
			
		
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				WaitingStaticProgress.hideProgressDialog();
				
				final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(RoomAndSwitchesTCPActivity.this,getString(R.string.Errorinconnection));
				
				cusDial.setListner(new CustomDialogueClickListner() {
					
					@Override
					public void onCustomDialogueClick() {
						
						
						cusDial.dismiss();
						
						if(switchesMainLayout.getVisibility()==View.VISIBLE)
						{
							onSwitchButttonClick(null);
						}
						
						
					}
				});
				
				
				cusDial.show();
			}
		});
		
		
		singleTcpClient.stopClient();
		
		
		}catch(Exception ex){}
		
		
	}
	
	
	

	
	public void onsocketConnection(String message) {
		
		
		
		
		byte[] cmd=SwitchCommandManager.requestInPutCommand(mSwitchModel.mac_address.substring(0,6),mSwitchModel.type);
	    
		
		
		
		
	     singleTcpClient.setCallbackForGetCommand(new ServerMessageResopnse() {
			
			@Override
			public void onGetCommandFromServer(final String cmd) {
				
				
				runOnUiThread( new Runnable() {
					public void run() {
						
						//overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
						
						
						layoutContainer=(LinearLayout) findViewById(R.id.layoutContainer);
						
						addRequiredLayout();
						
						
						
						
						currentCommand=cmd;
						
						try
						{
						handleCommand(currentCommand);
						}catch(Exception ex)
						{
							ex.toString();
							
						}
						
						
						Animation animation1 ;
					      
						Animation animation2;
						
						
						
						String currentLanguage=AppPreference.getValue(getApplicationContext(), AppKeys.KEY_CURRENT_LANGUAGE);
						 
						if(currentLanguage.equals(AppKeys.LANGUAGES.ENGLISH.getValue()))
						{
							animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in_from_right);
						      
							animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out_to_left);
							
						}
						else
						{
						
							animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out_to_right);
						      
							animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in_from_left);
							
						}
						
						
						switchesMainLayout.setVisibility(View.VISIBLE);
						roomsMainLayout.setVisibility(View.VISIBLE);
						
						roomsMainLayout.setAnimation(animation2);
						
						switchesMainLayout.setAnimation(animation1);
						
						animation1.start();
						animation2.start();
						
						
						animation2.setAnimationListener(new AnimationListener() {
							
							@Override
							public void onAnimationStart(Animation animation) {
								
								
								
							}
							
							@Override
							public void onAnimationRepeat(Animation animation) {
								 
								
							}
							
							@Override
							public void onAnimationEnd(Animation animation) {
								
								
								
								
								
								roomsMainLayout.setVisibility(View.GONE);
								
								
								 
								 String currentLanguage=AppPreference.getValue(getApplicationContext(), AppKeys.KEY_CURRENT_LANGUAGE);
								 
									if(currentLanguage.equals(AppKeys.LANGUAGES.ENGLISH.getValue()))
									{
										animation1__ = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in_from_left);
									      
										 animation2__ = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out_to_right);
										
									}
									else
									{
									
										animation1__ = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out_to_left);
									      
										 animation2__ = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in_from_right);
										
									}
								
								
							}
						});
						
						
						
					}
				});
				
				
				 
				
				
			}
		}, cmd);
		
		
		
	}

	
	public void onSocketConnectedSelfClose(String message) {
		
		
		try
		{
		
			
		
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				WaitingStaticProgress.hideProgressDialog();
				
				
				
			}
		});
		
		singleTcpClient.stopClient();
		
		}catch(Exception ex){}
	}

	

	
	
	//-------------------------------------------------------------------------------------------------------
	
	
		//-------------------------------------------------------------------------------------------------------
		
		//-------------------------------------------------------------------------------------------------------
		
		
		//-------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	 public  class connectTask extends AsyncTask<String,String,TCPClient_New> {
		 
	        boolean firstMessageArrived=false;
	        
	        int currentActionIndex=0;

			@Override
	        protected TCPClient_New doInBackground(String... message) {
	 
	            //we create a TCPClient object and
				try
				{
				if(singleTcpClient==null)
				{
					
					firstMessageArrived=false;
					
					
					
						singleTcpClient = new TCPClient_New(new TCPClient_New.OnMessageReceived() {
			                @Override
			               
			                public void messageReceived(final String message) {
			                   
			                
			                	currentActionIndex=2;
			                	
			                	isSocketConnected=true;
			                	
			                	onProgressUpdate(message);
			                	
			                }

							@Override
							public void onsocketConnectionClosed(String message) {
								
								
								currentActionIndex=5;
								isSocketConnected=false;
								isConnectionFailedEventOccured=true;
								onProgressUpdate(message);
								
								
								
							}

							@Override
							public void onsocketConnectionFailer(String message) {
								
								isSocketConnected=false;
								currentActionIndex=4;
								isPerformingFirstConnection=true;
								isConnectionFailedEventOccured=true;
								onProgressUpdate(message);
							}

							@Override
							public void onSocketConnection(String message) {
								
								isSocketConnected=true;
								
								isConnectionFailedEventOccured=false;
								
								if(isPerformingFirstConnection)
								{
								
									isPerformingFirstConnection=false;
									currentActionIndex=1;
									
									onProgressUpdate(message);
								}
								
							}

							@Override
							public void onSocketSelfClose(String message) {
								
								currentActionIndex=3;
								isSocketConnected=false;
								isConnectionFailedEventOccured=true;
								onProgressUpdate(message);
								  
							}
			            },RoomAndSwitchesTCPActivity.this);
						singleTcpClient.run();
				}
				
				}catch(Exception ex){}
	 
	            return null;
	        }
			
			@Override
	        protected void onProgressUpdate(String... values) {
	            super.onProgressUpdate(values);
	            
	           //hello
	            
	            switch (currentActionIndex) {
				case 1:
					
					
					onsocketConnection(values[0]);
					break;
					
				case 2:
					
					
					readMessageReponse(values[0]);
					
					
					break;
					
				case 3:
					
					
					onSocketConnectedSelfClose(values[0]);
					
					
					break;
					
					
				case 4:
					
					
					unableToCreateConnection(values[0]);
					break;
					
					
				case 5:
					
					
					onSocketTimeOutFailier(values[0]);
					
					
					
					break;
					
				
					
					
					
					

				default:
					break;
				}
			
		}
	    
	
			
	 }
    
 	  
    
    
	@Override
	public void startTCPClientTask(Switch_Model switchModel) {
		
		this.mSwitchModel=switchModel;
		
		
		WaitingStaticProgress.showProgressDialog("", RoomAndSwitchesTCPActivity.this);
		
		//isPerformingFirstConnection=true;
		
		 startTCPClient();
		
		
	}
    
	
	public void startTCPClient()
    {
    	singleTcpClient=null;
    	
    	task=new connectTask();
    	task.execute("");
	    
    }
	
	
	
	
	
	public void addRequiredLayout()
	{
		
		
		layoutContainer.removeAllViews();
		
		LayoutInflater inflater = (LayoutInflater)      this.getSystemService(LAYOUT_INFLATER_SERVICE);
		
		SwitchTypes thisType=Add_Edit_SwitchActivity.getSwitchType(mSwitchModel.mac_address);
		
		
		
		
		
		
		if(thisType.equals(SwitchTypes.SWITCH_1))
		{
			childLayout=inflater.inflate(R.layout.switch_one_layout,(ViewGroup) findViewById(R.id.switch_one_layoutt));
			
			rLayoutControl0=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl0);
			
			rLayoutControl1=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl1);
			
			
			
			handleActionOfOneSwitch(childLayout);
			
			ImageView img=(ImageView) childLayout.findViewById(R.id.switchIcon);
			
			
			img.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					callPopupSetting(childLayout);
					
				}
			});
			
			
			

			
			
			
		}
		else if(thisType.equals(SwitchTypes.SWITCH_2))
		{
			childLayout=inflater.inflate(R.layout.switch_two_layout,(ViewGroup) findViewById(R.id.switch_two_layoutt));
			
			rLayoutControl0=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl0);
			
			rLayoutControl1=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl1);
			
			
			
			rLayoutControl21=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl21);
			
			rLayoutControl22=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl22);
			
			
			handleActionOfTwoSwitch(childLayout);
			
			ImageView img=(ImageView) childLayout.findViewById(R.id.switchIcon);
			
			
			img.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					callPopupSetting(childLayout);
					
				}
			});
			
			
			
		}
		else if(thisType.equals(SwitchTypes.SWITCH_3))
		{
			childLayout=inflater.inflate(R.layout.switch_three_layout,(ViewGroup) findViewById(R.id.switch_three_layoutt));
			
			
			rLayoutControl0=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl0);
			
			rLayoutControl1=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl1);
			
			
			
			rLayoutControl21=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl21);
			
			rLayoutControl22=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl22);
			
			
			rLayoutControl32=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl32);
			
			rLayoutControl33=(RelativeLayout) childLayout.findViewById(R.id.rLayoutControl33);
			
			
			handleActionOfThreeSwitch(childLayout);
			
			ImageView img=(ImageView) childLayout.findViewById(R.id.switchIcon);
			
			
			img.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					callPopupSetting(childLayout);
					
				}
			});
			
			

			
			
			
		}
		else if(thisType.equals(SwitchTypes.SWITCH_SOCKET))
		{
			childLayout=inflater.inflate(R.layout.switch_socket_layout,(ViewGroup) findViewById(R.id.switch_socket_layoutt));
			handleActionsOfSocketSwitch(childLayout);
			
			ImageView img=(ImageView) childLayout.findViewById(R.id.switchIcon);
			
			
			img.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					callPopupSetting(childLayout);
					
				}
			});
		}
		else if(thisType.equals(SwitchTypes.SWITCH_AC))
		{
			childLayout=inflater.inflate(R.layout.switch_ac_layout,(ViewGroup) findViewById(R.id.ac_switch_layoutt));
			
			handleActionsOfACSwitch(childLayout);
			
			
			ImageView img=(ImageView) childLayout.findViewById(R.id.switchIcon);
			
			
			img.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					callPopupSetting(childLayout);
					
				}
			});
			
		}
		
		
		((TextView)childLayout.findViewById(R.id.switchTitle)).setText(mSwitchModel.title);
		
		((TextView)findViewById(R.id.sw_tv_title)).setText(mSwitchModel.title);
		
		layoutContainer.addView(childLayout);
		
	}
	
	
	
	
	
	
	private void handleActionOfOneSwitch(View view)
	{
		
		
		SwitchCheckBoxrLayoutControl0=new CheckBox(mContext);
		
		
		
		
		
		seekBar = (CircularSeekBar) view.findViewById(R.id.seekBarSwitchOne);
		 
		 
		 final TextView tv=(TextView) view.findViewById(R.id.progressPercentage);
		 
		 
		 
		 seekBar.setProgress(0);
		 
		 
		 final ImageView bulb=(ImageView) view.findViewById(R.id.bulbIcon);
		 
	        seekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
	            @Override
	            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
	               
	            	if(lockProgressOne)
	                	
	                	return;
	            	
	            	
	            	
	                tv.setText((int)progress+"%");
	                
	                if((int)progress==0)
	                {
	                	bulb.setImageResource(R.drawable.buld_gray);
	                	isProgressIsZeroOne=true;
	                }
	                else
	                {
	                	if(isProgressIsZeroOne)
	                	{
	                	 bulb.setImageResource(R.drawable.buld_yellow);
	                	}
	                	
	                	isProgressIsZeroOne=false;
	                }
	            }

	            @Override
	            public void onStopTrackingTouch(CircularSeekBar seekBar) {
	                
	            	
	            	int progress=(int) seekBar.getProgress();
	            	

	            	
	            	
	            	String progressToSend="";
	            	
	            	
	            	
	            	
	            	if(progress<=5)
	            	{
	            		progressToSend="00";
	            	}
	            	else if(progress<=9)
	            	{
	            		progressToSend="10";
	            	}
	            	else if(progress >=87 && progress<=92)
	            	{
	            		progressToSend="90";
	            	}
	            	else
		            	progressToSend=""+progress;
	            	
	            	
	            	WaitingStaticProgress.showProgressDialog("", mContext);
	            	
	            	final String tempProgressToSend=progressToSend;
	            	
	            	new Thread( new Runnable() {
						public void run() {
							
							
							currentCommand=sendReadMessagetoSwitch();
			            	
			            	byte []cmd=SwitchCommandManager.handleDimmerButtonOne( currentCommand ,"01", convertIntProgressToHEX(tempProgressToSend));
			            	sendMessageToSwitch(cmd);
			            		
							
						}
					}).start();
	            	
	            	
	            	
	            	
	                
	            }

	            @Override
	            public void onStartTrackingTouch(CircularSeekBar seekBar) {
	                
	            }
	        });
	        
	        
	        
	        rLayoutControl0.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					if(SwitchCheckBoxrLayoutControl0.isChecked())
					{
						
						SwitchCheckBoxrLayoutControl0.setChecked(false);
					}
					else
					{
						SwitchCheckBoxrLayoutControl0.setChecked(true);
					}
					
					
					String cmdOnOff="";
					
					if(SwitchCheckBoxrLayoutControl0.isChecked())
					{
						
						cmdOnOff="0A";
						
					}
					else
					{
						
						cmdOnOff="0B";
						
						
					}
					
					
					final String tempOnOffcmd=cmdOnOff;
					
					WaitingStaticProgress.showProgressDialog("", mContext);
					
					new Thread( new Runnable() {
						public void run() {
							
							
							currentCommand=sendReadMessagetoSwitch();
			            	
			            	byte []cmd=SwitchCommandManager.turnOnOFFButtonOne( currentCommand ,"01", tempOnOffcmd);
			            	sendMessageToSwitch(cmd);	
							
						}
					}).start();
					
					
					
				}
			});
	        
	        
	        rLayoutControl1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					if(SwitchCheckBoxrLayoutControl0.isChecked())
					{
						
						SwitchCheckBoxrLayoutControl0.setChecked(false);
					}
					else
					{
						SwitchCheckBoxrLayoutControl0.setChecked(true);
					}
					
					
					int progress=(int) seekBar.getProgress();
					
					
					
					String progressToSend="";
					
					
					if(progress<=5)
	            	{
	            		progressToSend="00";
	            	}
	            	else if(progress<=9)
	            	{
	            		progressToSend="10";
	            	}
	            	else if(progress >=87 && progress<=92)
	            	{
	            		progressToSend="90";
	            	}
	            	else
		            	progressToSend=""+progress;
					
					if(SwitchCheckBoxrLayoutControl0.isChecked())
					{
						
						String tempProgress=mSwitchModel.dimmingValue1;
						
						if(tempProgress!=null && tempProgress.length() > 0)
						{
							progressToSend=tempProgress;
						}
						else
						{
							progressToSend=convertIntProgressToHEX(progressToSend);
						}
						
						//byte[] cmd=SwitchCommandManager.handleDimmerButtonOne(currentCommand,"01", progressToSend);
						//sendMessageToSwitch(cmd);
						
						WaitingStaticProgress.showProgressDialog("", mContext);
		            	
		            	final String tempProgressToSend=progressToSend;
		            	
		            	new Thread( new Runnable() {
							public void run() {
								
								
								currentCommand=sendReadMessagetoSwitch();
				            	
				            	byte []cmd=SwitchCommandManager.handleDimmerButtonOne( currentCommand ,"01", tempProgressToSend);
				            	sendMessageToSwitch(cmd);	
								
							}
						}).start();
						
						
					}
					else
					{
						//byte[] cmd=SwitchCommandManager.handleDimmerButtonOne(currentCommand,"01", "0B");
						//sendMessageToSwitch(cmd);
						
						WaitingStaticProgress.showProgressDialog("", mContext);
		            	
		            	new Thread( new Runnable() {
							public void run() {
								
								
								currentCommand=sendReadMessagetoSwitch();
				            	
				            	byte []cmd=SwitchCommandManager.handleDimmerButtonOne( currentCommand ,"01", "0B");
				            	sendMessageToSwitch(cmd);	
								
							}
						}).start();
						
						
						mSwitchModel.dimmingValue1=convertIntProgressToHEX(progressToSend);
						mSwitchModel.save();
					}
					
					
					
				}
			});
		 
	        
	}
	
	//123
	
	private void handleActionOfTwoSwitch(View view)
	{
		
		//SwitchCheckBoxrLayoutControl0=new CheckBox(mContext);
		
		
		
		handleActionOfOneSwitch(view);
		
		
		 SwitchCheckBoxrLayoutControl21=new CheckBox(mContext);
		
		
	        //------------------------Switch Two Portion
	        
	        
	        
	        
	         seekBarTwo = (CircularSeekBar) view.findViewById(R.id.seekBarSwitchTwo);
			 
			 
			 final TextView tvTwo=(TextView) view.findViewById(R.id.progressPercentagetwo);
			 
			 
			 
			 seekBarTwo.setProgress(0);
			 
			 
			 final ImageView bulbTwo=(ImageView) view.findViewById(R.id.bulbIconTwo);
	        
	        
	        
			 seekBarTwo.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
		            @Override
		            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
		               
		                
		            	if(lockProgressTwo)
		                	
		                	return;
		            	
		            	
		                tvTwo.setText((int)progress+"%");
		                
		                if((int)progress==0)
		                {
		                	bulbTwo.setImageResource(R.drawable.buld_gray);
		                	isProgressIsZeroTwo=true;
		                }
		                else
		                {
		                	if(isProgressIsZeroTwo)
		                	{
		                	 bulbTwo.setImageResource(R.drawable.buld_yellow);
		                	}
		                	
		                	isProgressIsZeroTwo=false;
		                }
		            }

		            @Override
		            public void onStopTrackingTouch(CircularSeekBar seekBar) {
		                
		            	
		            	int progress=(int) seekBar.getProgress();
		            	

		            	
		            	
		            	String progressToSend="";
		            	
		            	
		            	
		            	if(progress<=5)
		            	{
		            		progressToSend="00";
		            	}
		            	else if(progress<=9)
		            	{
		            		progressToSend="10";
		            	}
		            	else if(progress>=87 && progress <=92)
		            	{
		            		progressToSend="90";
		            	}
		            	else
		            	{
		            		progressToSend=progress+"";
		            	}
		            	
		            	
		            	
		            	WaitingStaticProgress.showProgressDialog("", mContext);
		            	
		            	final String tempProgressToSend=progressToSend;
		            	
		            	new Thread( new Runnable() {
							public void run() {
								
								
								currentCommand=sendReadMessagetoSwitch();
				            	
				            	byte []cmd=SwitchCommandManager.handleDimmerButtonTwo( currentCommand ,"01", convertIntProgressToHEX(tempProgressToSend));
				            	sendMessageToSwitch(cmd);	
								
							}
						}).start();
		            	
		            	
		            	
		            }

		            @Override
		            public void onStartTrackingTouch(CircularSeekBar seekBar) {
		                
		            }
		        });
		        
	        
			 
			
			 
			 rLayoutControl21.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						
						if(SwitchCheckBoxrLayoutControl21.isChecked())
						{
							
							SwitchCheckBoxrLayoutControl21.setChecked(false);
						}
						else
						{
							SwitchCheckBoxrLayoutControl21.setChecked(true);
						}
						
						
						
						String cmdOnOff="";
						
						if(SwitchCheckBoxrLayoutControl21.isChecked())
						{
							
							cmdOnOff="0A";
							
						}
						else
						{
							
							cmdOnOff="0B";
							
							
						}
						
						
						final String tempOnOffcmd=cmdOnOff;
						
						WaitingStaticProgress.showProgressDialog("", mContext);
						
						new Thread( new Runnable() {
							public void run() {
								
								
								currentCommand=sendReadMessagetoSwitch();
				            	
				            	byte []cmd=SwitchCommandManager.turnOnOFFButtonTwo( currentCommand ,"01", tempOnOffcmd);
				            	sendMessageToSwitch(cmd);	
								
							}
						}).start();
						
					}
				});
			 
			 
			 
			 
			 rLayoutControl22.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						
						if(SwitchCheckBoxrLayoutControl21.isChecked())
						{
							
							SwitchCheckBoxrLayoutControl21.setChecked(false);
						}
						else
						{
							SwitchCheckBoxrLayoutControl21.setChecked(true);
						}
						
						
						int progress=(int) seekBarTwo.getProgress();
						
						
						
						String progressToSend="";
						
						
						if(progress<=5)
		            	{
		            		progressToSend="00";
		            	}
		            	else if(progress<=9)
		            	{
		            		progressToSend="10";
		            	}
		            	else if(progress>=87 && progress <=92)
		            	{
		            		progressToSend="90";
		            	}
						else
						{
							progressToSend=progress+"";
						}
						
						if(SwitchCheckBoxrLayoutControl21.isChecked())
						{
							String tempProgress=mSwitchModel.dimmingValue2;
							
							if(tempProgress!=null && tempProgress.length() > 0)
							{
								progressToSend=tempProgress;
							}
							else
							{
								progressToSend=convertIntProgressToHEX(progressToSend);
							}
							
							
							
							WaitingStaticProgress.showProgressDialog("", mContext);
			            	
			            	final String tempProgressToSend=progressToSend;
			            	
			            	new Thread( new Runnable() {
								public void run() {
									
									
									currentCommand=sendReadMessagetoSwitch();
					            	
					            	byte []cmd=SwitchCommandManager.handleDimmerButtonTwo( currentCommand ,"01", tempProgressToSend);
					            	sendMessageToSwitch(cmd);	
									
								}
							}).start();
							
							
							
						}
						else
						{
							
							WaitingStaticProgress.showProgressDialog("", mContext);
			            	
			            	
			            	
			            	new Thread( new Runnable() {
								public void run() {
									
									
									currentCommand=sendReadMessagetoSwitch();
					            	
					            	byte []cmd=SwitchCommandManager.handleDimmerButtonTwo( currentCommand ,"01", "0B");
					            	sendMessageToSwitch(cmd);	
									
								}
							}).start();
							
							mSwitchModel.dimmingValue2=convertIntProgressToHEX(progressToSend);
							mSwitchModel.save();
							
						}
						
						
						
						
					}
				});
	        
	        
	        
	}
	
	
	public String convertIntProgressToHEX(String progressToSend)
	{
		
		
		if(progressToSend.equals("00"))
		{
			progressToSend="0B";
		}
		
		else if(progressToSend.equals("100"))
		{
			progressToSend="0A";
		}
		else
		{
			progressToSend=new StringBuilder(progressToSend).reverse().toString();
		}
		return progressToSend;
		
	}
	
	
	
	//321
	private void handleActionOfThreeSwitch(View view)
	{
		
		
		
		 handleActionOfTwoSwitch(view);
		
		 SwitchCheckBoxrLayoutControl32=new CheckBox(mContext);
		 
		 
		
		
 
			//------------------------Switch Three Portion
		        
		        
		        
		        
		         seekBarThree = (CircularSeekBar) view.findViewById(R.id.seekBarSwitchThree);
				 
				 
				 final TextView tvThree=(TextView) view.findViewById(R.id.progressPercentageThree);
				 
				 
				 
				 seekBarThree.setProgress(0);
				 
				 
				 final ImageView bulbThree=(ImageView) view.findViewById(R.id.bulbIconThree);
		        
		        
		        
				 seekBarThree.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
			            @Override
			            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
			               
			                
			            	if(lockProgressThree)
				            	   return;
			            	
			            	
			                tvThree.setText((int)progress+"%");
			                
			                if((int)progress==0)
			                {
			                	bulbThree.setImageResource(R.drawable.buld_gray);
			                	isProgressIsZeroThree=true;
			                }
			                else
			                {
			                	if(isProgressIsZeroThree)
			                	{
			                	 bulbThree.setImageResource(R.drawable.buld_yellow);
			                	}
			                	
			                	isProgressIsZeroThree=false;
			                }
			            }

			            @Override
			            public void onStopTrackingTouch(CircularSeekBar seekBar) {
			                
			            	
			            	int progress=(int) seekBar.getProgress();
			            	
			            	
			            	String progressToSend="";
			            	
			            	
			            	if(progress<=5)
			            	{
			            		progressToSend="00";
			            	}
			            	else if(progress<=9)
			            	{
			            		progressToSend="10";
			            	}
			            	else if(progress >=87 && progress<=92)
			            	{
			            		progressToSend="90";
			            	}
			            	else
				            	progressToSend=""+progress;
			            	
			            	
							WaitingStaticProgress.showProgressDialog("", mContext);
			            	
			            	final String tempProgressToSend=progressToSend;
			            	
			            	new Thread( new Runnable() {
								public void run() {
									
									
									currentCommand=sendReadMessagetoSwitch();
					            	
					            	byte []cmd=SwitchCommandManager.handleDimmerButtonThree( currentCommand ,"01", convertIntProgressToHEX(tempProgressToSend));
					            	sendMessageToSwitch(cmd);	
									
								}
							}).start();
			                
			            }

			            @Override
			            public void onStartTrackingTouch(CircularSeekBar seekBar) {
			                
			            }
			        });
				 
				 
				 
				
				 
				
				 
				 rLayoutControl32.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							
							if(SwitchCheckBoxrLayoutControl32.isChecked())
							{
								
								SwitchCheckBoxrLayoutControl32.setChecked(false);
							}
							else
							{
								SwitchCheckBoxrLayoutControl32.setChecked(true);
							}
							
							
							String cmdOnOff="";
							
							if(SwitchCheckBoxrLayoutControl32.isChecked())
							{
								
								cmdOnOff="0A";
								
							}
							else
							{
								
								cmdOnOff="0B";
								
								
							}
							
							
							final String tempOnOffcmd=cmdOnOff;
							
							WaitingStaticProgress.showProgressDialog("", mContext);
							
							new Thread( new Runnable() {
								public void run() {
									
									
									currentCommand=sendReadMessagetoSwitch();
					            	
					            	byte []cmd=SwitchCommandManager.turnOnOFFButtonThree( currentCommand ,"01", tempOnOffcmd);
					            	sendMessageToSwitch(cmd);	
									
								}
							}).start();
							
						}
					});
		        
			         
				 rLayoutControl33.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							
							if(SwitchCheckBoxrLayoutControl32.isChecked())
							{
								
								SwitchCheckBoxrLayoutControl32.setChecked(false);
							}
							else
							{
								SwitchCheckBoxrLayoutControl32.setChecked(true);
							}
							
							
							int progress=(int) seekBarThree.getProgress();
							
							
							
							String progressToSend="";
							
							
							if(progress<=5)
			            	{
			            		progressToSend="00";
			            	}
			            	else if(progress<=9)
			            	{
			            		progressToSend="10";
			            	}
			            	else if(progress >=87 && progress<=92)
			            	{
			            		progressToSend="90";
			            	}
			            	else
				            	progressToSend=""+progress;
							
							if(SwitchCheckBoxrLayoutControl32.isChecked())
							{
								String tempProgress=mSwitchModel.dimmingValue3;
								
								if(tempProgress!=null && tempProgress.length() > 0)
								{
									progressToSend=tempProgress;
								}
								else
								{
									progressToSend=convertIntProgressToHEX(progressToSend);
								}
								
								
								
								WaitingStaticProgress.showProgressDialog("", mContext);
				            	
				            	final String tempProgressToSend=progressToSend;
				            	
				            	new Thread( new Runnable() {
									public void run() {
										
										
										currentCommand=sendReadMessagetoSwitch();
						            	
						            	byte []cmd=SwitchCommandManager.handleDimmerButtonThree( currentCommand ,"01", tempProgressToSend);
						            	sendMessageToSwitch(cmd);	
										
									}
								}).start();
								
								
							}
							else
							{
								
								
								WaitingStaticProgress.showProgressDialog("", mContext);
				            	
				            	
				            	
				            	new Thread( new Runnable() {
									public void run() {
										
										
										currentCommand=sendReadMessagetoSwitch();
						            	
						            	byte []cmd=SwitchCommandManager.handleDimmerButtonThree( currentCommand ,"01", "0B");
						            	sendMessageToSwitch(cmd);	
										
									}
								}).start();
								
								mSwitchModel.dimmingValue3=convertIntProgressToHEX(progressToSend);
								mSwitchModel.save();
								
								
							}
							
							
							
							
						}
					});
	        
	}
	
	
	private void handleActionsOfACSwitch(View view)
	{
		
		acSwitchCheckBox=new CheckBox(mContext);
		
		
		final ImageView frame=(ImageView) view.findViewById(R.id.acFrame);
		
		
		frame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(acSwitchCheckBox.isChecked())
				{
					acSwitchCheckBox.setChecked(false);
				}
				else
				{
					acSwitchCheckBox.setChecked(true);
				}
				
				
				
				
				String cmdOnOff="";
				
				if(acSwitchCheckBox.isChecked())
				{
					
					cmdOnOff="0A";
					
				}
				else
				{
					
					cmdOnOff="0B";
					
					
				}
				
				
				final String tempOnOffcmd=cmdOnOff;
				
				WaitingStaticProgress.showProgressDialog("", mContext);
				
				new Thread( new Runnable() {
					public void run() {
						
						
						currentCommand=sendReadMessagetoSwitch();
		            	
		            	byte []cmd=SwitchCommandManager.turnOnOFFAC( currentCommand ,"01", tempOnOffcmd);
		            	sendMessageToSwitch(cmd);	
						
					}
				}).start();
				
				
			}
		});
		
	}
	
	
	
	private void handleActionsOfSocketSwitch(View view)
	{
		
		acSwitchCheckBox=new CheckBox(mContext);
		
		
		final ImageView frame=(ImageView) view.findViewById(R.id.acFrame);
		
		
		frame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(acSwitchCheckBox.isChecked())
				{
					acSwitchCheckBox.setChecked(false);
				}
				else
				{
					acSwitchCheckBox.setChecked(true);
				}
				
				
				String cmdOnOff="";
				
				if(acSwitchCheckBox.isChecked())
				{
					
					cmdOnOff="0A";
					
				}
				else
				{
					
					cmdOnOff="0B";
					
					
				}
				
				
				final String tempOnOffcmd=cmdOnOff;
				
				WaitingStaticProgress.showProgressDialog("", mContext);
				
				new Thread( new Runnable() {
					public void run() {
						
						
						currentCommand=sendReadMessagetoSwitch();
		            	
		            	byte []cmd=SwitchCommandManager.turnOnOFFSocket( currentCommand ,"01", tempOnOffcmd);
		            	sendMessageToSwitch(cmd);	
						
					}
				}).start();
				
			}
		});
		
	}
	
	
	public void handleCommand(String command)
	{

       //123
		
        currentCommand= command.trim().toUpperCase();
        
        
          /*if(currentCommand!=null && currentCommand.substring(8,10).equals("04"))
          {
        	  currentTries=4;
        	  StaticProgress.hideProgressDialog();
          }*/
          
          
          
        
          String comingCommand= currentCommand;
        
          String mac=SwitchCommandManager.getSwitchMac(comingCommand);
        
          //Toast.makeText(mContext,mac, Toast.LENGTH_SHORT).show();
          
          String type=SwitchCommandManager.getSwitchType(comingCommand);
          
          //Toast.makeText(mContext,type, Toast.LENGTH_SHORT).show();
          
          String switchInsideTypes=SwitchCommandManager.getDimmerSettingValues(comingCommand);
          
          //Toast.makeText(mContext,switchInsideTypes, Toast.LENGTH_SHORT).show();
          
          
          currentSwitchLEDStatus=SwitchCommandManager.getLEDStatus(currentCommand);
          
          
          SwitchTypes thisType=Add_Edit_SwitchActivity.getSwitchType(mSwitchModel.mac_address);
          if(thisType.equals(SwitchTypes.SWITCH_1))
          {
        	 
        	 if(switchInsideTypes.substring(0, 2).equals("01"))
        	 {
        		 rLayoutControl0.setVisibility(View.GONE); 
            	 rLayoutControl1.setVisibility(View.VISIBLE);
            	 
            	 
            	 String switchStatus=SwitchCommandManager.getOnOffStatus(comingCommand);
            	 
            	 DimDimmerOne(switchStatus);
            	 
            	 
            	 
        	 }
        	 else
        	 {
        		 rLayoutControl0.setVisibility(View.VISIBLE); 
            	 rLayoutControl1.setVisibility(View.GONE);
            	 
            	 
            	 String switchStatus=SwitchCommandManager.getOnOffStatus(comingCommand);
            	 
            	 
            	 if(switchStatus.substring(0, 2).equals("0A"))
            	 {
            		 turnOnOffButtonOne(true);
            	 }
            	 else if(switchStatus.substring(0, 2).equals("0B"))
            	 {
            		 turnOnOffButtonOne(false);
            	 }
            	 
            	 
        	 }
        	  
          }
          else if(thisType.equals(SwitchTypes.SWITCH_2))
          {
        	 
        	 if(switchInsideTypes.substring(0, 2).equals("01"))
        	 {
        		 rLayoutControl0.setVisibility(View.GONE); 
            	 rLayoutControl1.setVisibility(View.VISIBLE);
            	 
            	 String switchStatus=SwitchCommandManager.getOnOffStatus(comingCommand);
            	 
            	 DimDimmerOne(switchStatus);
        	 }
        	 else
        	 {
        		 rLayoutControl0.setVisibility(View.VISIBLE); 
            	 rLayoutControl1.setVisibility(View.GONE);
            	 
            	 
            	 String switchStatus=SwitchCommandManager.getOnOffStatus(comingCommand);
            	 
            	 
            	 if(switchStatus.substring(0, 2).equals("0A"))
            	 {
            		 turnOnOffButtonOne(true);
            	 }
            	 else if(switchStatus.substring(0, 2).equals("0B"))
            	 {
            		 turnOnOffButtonOne(false);
            	 }
            	 
        	 }
        	 
        	 if(switchInsideTypes.substring(2, 4).equals("01"))
        	 {
        		 rLayoutControl21.setVisibility(View.GONE); 
            	 rLayoutControl22.setVisibility(View.VISIBLE);
            	 
            	 
            	 String switchStatus=SwitchCommandManager.getOnOffStatus(comingCommand);
            	 
            	 DimDimmerTwo(switchStatus);
        	 }
        	 else
        	 {
        		 rLayoutControl21.setVisibility(View.VISIBLE); 
            	 rLayoutControl22.setVisibility(View.GONE);
            	 
            	 
            	 String switchStatus=SwitchCommandManager.getOnOffStatus(comingCommand);
            	 
            	 
            	 if(switchStatus.substring(2, 4).equals("0A"))
            	 {
            		 turnOnOffButtonTwo(true);
            	 }
            	 else if(switchStatus.substring(2, 4).equals("0B"))
            	 {
            		 turnOnOffButtonTwo(false);
            	 }
        	 }
        	 
        	  
          }
          else if(thisType.equals(SwitchTypes.SWITCH_3))
          {
        	 
        	 if(switchInsideTypes.substring(0, 2).equals("01"))
        	 {
        		 rLayoutControl0.setVisibility(View.GONE); 
            	 rLayoutControl1.setVisibility(View.VISIBLE);
            	 
            	 String switchStatus=SwitchCommandManager.getOnOffStatus(comingCommand);
            	 
            	 DimDimmerOne(switchStatus);
        	 }
        	 else
        	 {
        		 rLayoutControl0.setVisibility(View.VISIBLE); 
            	 rLayoutControl1.setVisibility(View.GONE);
            	 
            	 String switchStatus=SwitchCommandManager.getOnOffStatus(comingCommand);
            	 
            	 
            	 if(switchStatus.substring(0, 2).equals("0A"))
            	 {
            		 turnOnOffButtonOne(true);
            	 }
            	 else if(switchStatus.substring(0, 2).equals("0B"))
            	 {
            		 turnOnOffButtonOne(false);
            	 }
        	 }
        	 
        	 if(switchInsideTypes.substring(2, 4).equals("01"))
        	 {
        		 rLayoutControl21.setVisibility(View.GONE); 
            	 rLayoutControl22.setVisibility(View.VISIBLE);
            	 
            	 
            	 String switchStatus=SwitchCommandManager.getOnOffStatus(comingCommand);
            	 
            	 DimDimmerTwo(switchStatus);
        	 }
        	 else
        	 {
        		 rLayoutControl21.setVisibility(View.VISIBLE); 
            	 rLayoutControl22.setVisibility(View.GONE);
            	 
            	 String switchStatus=SwitchCommandManager.getOnOffStatus(comingCommand);
            	 
            	 
            	 if(switchStatus.substring(2, 4).equals("0A"))
            	 {
            		 turnOnOffButtonTwo(true);
            	 }
            	 else if(switchStatus.substring(2, 4).equals("0B"))
            	 {
            		 turnOnOffButtonTwo(false);
            	 }
        	 }
        	 
        	 if(switchInsideTypes.substring(4, 6).equals("01"))
        	 {
        		 rLayoutControl32.setVisibility(View.GONE); 
            	 rLayoutControl33.setVisibility(View.VISIBLE);
            	 
            	 String switchStatus=SwitchCommandManager.getOnOffStatus(comingCommand);
            	 
            	 DimDimmerThree(switchStatus);
        	 }
        	 else
        	 {
        		 rLayoutControl32.setVisibility(View.VISIBLE); 
            	 rLayoutControl33.setVisibility(View.GONE);
            	 
            	 String switchStatus=SwitchCommandManager.getOnOffStatus(comingCommand);
            	 
            	 
            	 if(switchStatus.substring(4, 6).equals("0A"))
            	 {
            		 turnOnOffButtonThree(true);
            	 }
            	 else if(switchStatus.substring(4, 6).equals("0B"))
            	 {
            		 turnOnOffButtonThree(false);
            	 }
        	 }
        	 
        	  
          }
          else if(thisType.equals(SwitchTypes.SWITCH_SOCKET))
          {
        	  String switchStatus=SwitchCommandManager.getOnOffStatus(comingCommand);
        	 
        	  
        	  if(switchStatus.substring(0, 2).equals("0A"))
            	 {
        		  turnOnOffSocketandAc(true);
            	 }
            	 else if(switchStatus.substring(0, 2).equals("0B"))
            	 {
            		 turnOnOffSocketandAc(false);
            	 }
          }
          
          else if(thisType.equals(SwitchTypes.SWITCH_AC))
          {
        	  String switchStatus=SwitchCommandManager.getOnOffStatus(comingCommand);
        	 
        	  
        	  if(switchStatus.substring(0, 2).equals("0A"))
            	 {
        		  turnOnOffSocketandAc(true);
            	 }
            	 else if(switchStatus.substring(0, 2).equals("0B"))
            	 {
            		 turnOnOffSocketandAc(false);
            	 }
          }
          
          
          WaitingStaticProgress.hideProgressDialog();
    
	}
	
	private void callPopupSetting(View rootView) {

		 LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		 View popupView = layoutInflater.inflate(R.layout.dimmer_setting_popup, null);
		 
		    TextView tv_cancel=(TextView) popupView.findViewById(R.id.tv_Cancel);
			TextView tv_Done=(TextView) popupView.findViewById(R.id.tv_Done);
		 
		 SwitchTypes thisType=Add_Edit_SwitchActivity.getSwitchType(mSwitchModel.mac_address);
			
			
		 final PopupWindow popupWindow=new PopupWindow(popupView,
		         LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,        
		 true);

		 popupWindow.setTouchable(true);
		 popupWindow.setFocusable(true);
		 
		 popupWindow.setAnimationStyle(R.style.BottonUpAnimation);
		 
		

		 popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
			
			
			
			if(thisType.equals(SwitchTypes.SWITCH_1))
			{
				
				LinearLayout dimmer0ly= (LinearLayout) popupView.findViewById(R.id.linearLayout1);
				final ShSwitchView switch0=(ShSwitchView) dimmer0ly.findViewById(R.id.ledStatus);
				
				
				LinearLayout dimmer1ly= (LinearLayout) popupView.findViewById(R.id.linearLayout2);
				
				final ShSwitchView switch1=(ShSwitchView) dimmer1ly.findViewById(R.id.sw_Dimmer1);
				
				
				///-------------------------------------------------------------------
				
				if(currentSwitchLEDStatus.equals("01"))
				{
					switch0.setOn(true);
				}
				else
				{
					switch0.setOn(false);
				}
				
				String switchInsideTypes=SwitchCommandManager.getDimmerSettingValues(currentCommand);
				
				if(switchInsideTypes.substring(0,2).equals("01"))
				{
					switch1.setOn(true);
				}
				else
				{
					switch1.setOn(false);
				}
				
				LinearLayout dimmer2ly= (LinearLayout) popupView.findViewById(R.id.linearLayout3);
				dimmer2ly.setVisibility(View.GONE);
				
				LinearLayout dimmer3ly= (LinearLayout) popupView.findViewById(R.id.linearLayout4);
				dimmer3ly.setVisibility(View.GONE);
				
				
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
						
						String tempCMD=currentCommand;
						
						if(switch0.isOn())
						{
							    //turn on the led bits
							
							tempCMD=tempCMD.substring(0,24)+"01"+tempCMD.substring(26, 38);
							
						}
						else
						{
							tempCMD=tempCMD.substring(0,24)+"02"+tempCMD.substring(26, 38);
						}
						
						if(switch1.isOn())
						{
							//rLayoutControl1.setVisibility(View.VISIBLE);
							//rLayoutControl0.setVisibility(View.GONE);
							
							tempCMD=tempCMD.substring(0,18)+"01"+tempCMD.substring(20, 38);
							
						}
						else
						{
							//rLayoutControl1.setVisibility(View.GONE);
							//rLayoutControl0.setVisibility(View.VISIBLE);
							
							tempCMD=tempCMD.substring(0,18)+"02"+tempCMD.substring(20, 38);
						}
						
						
						
						final String finaltempCMD=tempCMD=tempCMD.substring(0,8)+"01" + tempCMD.substring(10,38);
						
						
						WaitingStaticProgress.showProgressDialog("", mContext);
						
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								
								isCallFromPopup=true;
								sendMessageToSwitch(SwitchCommandManager.hexStringToByteArray(finaltempCMD));
								
							}
						});
						
						
						
						
						
					}
				});
				
				
				
			}
			else if(thisType.equals(SwitchTypes.SWITCH_2))
			{
				
				
				LinearLayout dimmer0ly= (LinearLayout) popupView.findViewById(R.id.linearLayout1);
				final ShSwitchView switch0=(ShSwitchView) dimmer0ly.findViewById(R.id.ledStatus);
				
				
				LinearLayout dimmer1ly= (LinearLayout) popupView.findViewById(R.id.linearLayout2);
				LinearLayout dimmer2ly= (LinearLayout) popupView.findViewById(R.id.linearLayout3);
				
				
				final ShSwitchView switch1=(ShSwitchView) dimmer1ly.findViewById(R.id.sw_Dimmer1);
				
				
				
				
				
				final ShSwitchView switch2=(ShSwitchView) dimmer2ly.findViewById(R.id.sw_Dimmer2);
				
				
				
				if(currentSwitchLEDStatus.equals("01"))
				{
					switch0.setOn(true);
				}
				else
				{
					switch0.setOn(false);
				}
				
				String switchInsideTypes=SwitchCommandManager.getDimmerSettingValues(currentCommand);
				
				if(switchInsideTypes.substring(0,2).equals("01"))
				{
					switch1.setOn(true);
				}
				else
				{
					switch1.setOn(false);
				}
				
				
				if(switchInsideTypes.substring(2,4).equals("01"))
				{
					switch2.setOn(true);
				}
				else
				{
					switch2.setOn(false);
				}
				
				
				LinearLayout dimmer3ly= (LinearLayout) popupView.findViewById(R.id.linearLayout4);
				dimmer3ly.setVisibility(View.GONE);
				
				
				
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
						
						String tempCMD=currentCommand;
						
						if(switch0.isOn())
						{
							    //turn on the led bits
							
							tempCMD=tempCMD.substring(0,24)+"01"+tempCMD.substring(26, 38);
							
						}
						else
						{
							tempCMD=tempCMD.substring(0,24)+"02"+tempCMD.substring(26, 38);
						}
						
						if(switch1.isOn())
						{
							//rLayoutControl1.setVisibility(View.VISIBLE);
							//rLayoutControl0.setVisibility(View.GONE);
							
							tempCMD=tempCMD.substring(0,18)+"01"+tempCMD.substring(20, 38);
							
						}
						else
						{
							//rLayoutControl1.setVisibility(View.GONE);
							//rLayoutControl0.setVisibility(View.VISIBLE);
							
							tempCMD=tempCMD.substring(0,18)+"02"+tempCMD.substring(20, 38);
						}
						
						
						if(switch2.isOn())
						{
							//rLayoutControl1.setVisibility(View.VISIBLE);
							//rLayoutControl0.setVisibility(View.GONE);
							
							tempCMD=tempCMD.substring(0,20)+"01"+tempCMD.substring(22, 38);
							
						}
						else
						{
							//rLayoutControl1.setVisibility(View.GONE);
							//rLayoutControl0.setVisibility(View.VISIBLE);
							
							tempCMD=tempCMD.substring(0,20)+"02"+tempCMD.substring(22, 38);
						}
						
						
						
							final String finaltempCMD=tempCMD=tempCMD.substring(0,8)+"01" + tempCMD.substring(10,38);
							
							
							WaitingStaticProgress.showProgressDialog("", mContext);
							
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									
									isCallFromPopup=false;
									sendMessageToSwitch(SwitchCommandManager.hexStringToByteArray(finaltempCMD));
									
								}
							});
							
							
							
						
						
						
					}
				});
				
				
			}
			else if(thisType.equals(SwitchTypes.SWITCH_3))
			{
				
				
				LinearLayout dimmer0ly= (LinearLayout) popupView.findViewById(R.id.linearLayout1);
				final ShSwitchView switch0=(ShSwitchView) dimmer0ly.findViewById(R.id.ledStatus);
				
				
				LinearLayout dimmer1ly= (LinearLayout) popupView.findViewById(R.id.linearLayout2);
				LinearLayout dimmer2ly= (LinearLayout) popupView.findViewById(R.id.linearLayout3);
				
				
				final ShSwitchView switch1=(ShSwitchView) dimmer1ly.findViewById(R.id.sw_Dimmer1);
				
				
				
				
				
				final ShSwitchView switch2=(ShSwitchView) dimmer2ly.findViewById(R.id.sw_Dimmer2);
				
				
				
				
				LinearLayout dimmer3ly= (LinearLayout) popupView.findViewById(R.id.linearLayout4);
				
				final ShSwitchView switch3=(ShSwitchView) dimmer3ly.findViewById(R.id.sw_Dimmer3);
				
				
				
				if(currentSwitchLEDStatus.equals("01"))
				{
					switch0.setOn(true);
				}
				else
				{
					switch0.setOn(false);
				}
				
				String switchInsideTypes=SwitchCommandManager.getDimmerSettingValues(currentCommand);
				
				if(switchInsideTypes.substring(0,2).equals("01"))
				{
					switch1.setOn(true);
				}
				else
				{
					switch1.setOn(false);
				}
				
				
				if(switchInsideTypes.substring(2,4).equals("01"))
				{
					switch2.setOn(true);
				}
				else
				{
					switch2.setOn(false);
				}
				
				
				if(switchInsideTypes.substring(4,6).equals("01"))
				{
					switch3.setOn(true);
				}
				else
				{
					switch3.setOn(false);
				}
				
				
				
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
						
						
						String tempCMD=currentCommand;
						
						if(switch0.isOn())
						{
							    //turn on the led bits
							
							tempCMD=tempCMD.substring(0,24)+"01"+tempCMD.substring(26, 38);
							
						}
						else
						{
							tempCMD=tempCMD.substring(0,24)+"02"+tempCMD.substring(26, 38);
						}
						
						if(switch1.isOn())
						{
							//rLayoutControl1.setVisibility(View.VISIBLE);
							//rLayoutControl0.setVisibility(View.GONE);
							
							tempCMD=tempCMD.substring(0,18)+"01"+tempCMD.substring(20, 38);
							
						}
						else
						{
							//rLayoutControl1.setVisibility(View.GONE);
							//rLayoutControl0.setVisibility(View.VISIBLE);
							
							tempCMD=tempCMD.substring(0,18)+"02"+tempCMD.substring(20, 38);
						}
						
						
						if(switch2.isOn())
						{
							//rLayoutControl1.setVisibility(View.VISIBLE);
							//rLayoutControl0.setVisibility(View.GONE);
							
							tempCMD=tempCMD.substring(0,20)+"01"+tempCMD.substring(22, 38);
							
						}
						else
						{
							//rLayoutControl1.setVisibility(View.GONE);
							//rLayoutControl0.setVisibility(View.VISIBLE);
							
							tempCMD=tempCMD.substring(0,20)+"02"+tempCMD.substring(22, 38);
						}
						
						
						if(switch3.isOn())
						{
							//rLayoutControl1.setVisibility(View.VISIBLE);
							//rLayoutControl0.setVisibility(View.GONE);
							
							tempCMD=tempCMD.substring(0,22)+"01"+tempCMD.substring(24, 38);
							
						}
						else
						{
							//rLayoutControl1.setVisibility(View.GONE);
							//rLayoutControl0.setVisibility(View.VISIBLE);
							
							tempCMD=tempCMD.substring(0,22)+"02"+tempCMD.substring(24, 38);
						}
						
						
						final String finaltempCMD=tempCMD=tempCMD.substring(0,8)+"01" + tempCMD.substring(10,38);
						
						
						WaitingStaticProgress.showProgressDialog("", mContext);
						
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								
								isCallFromPopup=true;
								sendMessageToSwitch(SwitchCommandManager.hexStringToByteArray(finaltempCMD));
								
							}
						});
						
						
					}
				});
				
				
				
			}
			else if(thisType.equals(SwitchTypes.SWITCH_SOCKET))
			{
				
				
				LinearLayout dimmer1ly= (LinearLayout) popupView.findViewById(R.id.linearLayout2);
				
				dimmer1ly.setVisibility(View.GONE);
				
				
				LinearLayout dimmer0ly= (LinearLayout) popupView.findViewById(R.id.linearLayout1);
				final ShSwitchView switch0=(ShSwitchView) dimmer0ly.findViewById(R.id.ledStatus);
				
				if(currentSwitchLEDStatus.equals("01"))
				{
					switch0.setOn(true);
				}
				else
				{
					switch0.setOn(false);
				}
				
				
				LinearLayout dimmer2ly= (LinearLayout) popupView.findViewById(R.id.linearLayout3);
				dimmer2ly.setVisibility(View.GONE);
				
				LinearLayout dimmer3ly= (LinearLayout) popupView.findViewById(R.id.linearLayout4);
				dimmer3ly.setVisibility(View.GONE);
				
				
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
						
						String tempCMD=currentCommand;
						
						if(switch0.isOn())
						{
							    //turn on the led bits
							
							tempCMD=tempCMD.substring(0,24)+"01"+tempCMD.substring(26, 38);
							
						}
						else
						{
							tempCMD=tempCMD.substring(0,24)+"02"+tempCMD.substring(26, 38);
						}
						
						final String finaltempCMD=tempCMD=tempCMD.substring(0,8)+"01" + tempCMD.substring(10,38);
						
						
						WaitingStaticProgress.showProgressDialog("", mContext);
						
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								
								isCallFromPopup=true;
								sendMessageToSwitch(SwitchCommandManager.hexStringToByteArray(finaltempCMD));
								
							}
						});
						
					}
				});
			}
			else if(thisType.equals(SwitchTypes.SWITCH_AC))
			{
				LinearLayout dimmer1ly= (LinearLayout) popupView.findViewById(R.id.linearLayout2);
				
				dimmer1ly.setVisibility(View.GONE);
				
				
				LinearLayout dimmer0ly= (LinearLayout) popupView.findViewById(R.id.linearLayout1);
				final ShSwitchView switch0=(ShSwitchView) dimmer0ly.findViewById(R.id.ledStatus);
				
				
				if(currentSwitchLEDStatus.equals("01"))
				{
					switch0.setOn(true);
				}
				else
				{
					switch0.setOn(false);
				}
				
				LinearLayout dimmer2ly= (LinearLayout) popupView.findViewById(R.id.linearLayout3);
				dimmer2ly.setVisibility(View.GONE);
				
				LinearLayout dimmer3ly= (LinearLayout) popupView.findViewById(R.id.linearLayout4);
				dimmer3ly.setVisibility(View.GONE);
				
				
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
						
						String tempCMD=currentCommand;
						
						if(switch0.isOn())
						{
							    //turn on the led bits
							
							tempCMD=tempCMD.substring(0,24)+"01"+tempCMD.substring(26, 38);
							
						}
						else
						{
							tempCMD=tempCMD.substring(0,24)+"02"+tempCMD.substring(26, 38);
						}
						
						final String finaltempCMD=tempCMD=tempCMD.substring(0,8)+"01" + tempCMD.substring(10,38);
						
						
						WaitingStaticProgress.showProgressDialog("", mContext);
						
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								
								isCallFromPopup=true;
								sendMessageToSwitch(SwitchCommandManager.hexStringToByteArray(finaltempCMD));
								
							}
						});
						
					}
				});
			}
		 
		 
			
		

		
		 
		 
		 
		 
		
	}
	
	
	
	public void turnOnOffButtonOne(boolean turnOn)
	{
		
		try
		{
		
		if(turnOn)
		{
			((ImageView) rLayoutControl0.findViewById(R.id.bulbIconButton)).setImageResource(R.drawable.buld_yellow);
			
			((TextView)rLayoutControl0.findViewById(R.id.onOffStatus)).setText(getResources().getString(R.string.ON));
			
			
			SwitchCheckBoxrLayoutControl0.setChecked(true);
		}
		else
		{
			((ImageView) rLayoutControl0.findViewById(R.id.bulbIconButton)).setImageResource(R.drawable.buld_gray);
			
			((TextView)rLayoutControl0.findViewById(R.id.onOffStatus)).setText(getResources().getString(R.string.OFF));
			
			SwitchCheckBoxrLayoutControl0.setChecked(false);
			
		}
		
		}catch(Exception ex)
		{
			
		}
		
		
	}
	
	
	/*public void turnOnOffAC(boolean turnOn)
	{
		try
		{
		if(turnOn)
		{
			((ImageView) rLayoutControl0.findViewById(R.id.bulbIconButton)).setImageResource(R.drawable.buld_yellow);
			
			((TextView)rLayoutControl0.findViewById(R.id.onOffStatus)).setText(getResources().getString(R.string.ON));
			
			SwitchCheckBoxrLayoutControl0.setChecked(true);
		}
		else
		{
			((ImageView) rLayoutControl0.findViewById(R.id.bulbIconButton)).setImageResource(R.drawable.buld_gray);
			
			((TextView)rLayoutControl0.findViewById(R.id.onOffStatus)).setText(getResources().getString(R.string.OFF));
			
			
			SwitchCheckBoxrLayoutControl0.setChecked(false);
			
		}
		
	}catch(Exception ex)
	{
		
	}
	
		
	}*/
	
	
	public void turnOnOffSocketandAc(boolean turnOn)
	{
		
		try
		{
		
		if(turnOn)
		{
			((ImageView) childLayout.findViewById(R.id.acFrame)).setImageResource(R.drawable.rotating_image_on);
			
			((ImageView) childLayout.findViewById(R.id.onStatusbar)).setVisibility(View.VISIBLE);
			
			
		}
		else
		{
			
			((ImageView) childLayout.findViewById(R.id.acFrame)).setImageResource(R.drawable.rotating_image_off);
			
			((ImageView) childLayout.findViewById(R.id.onStatusbar)).setVisibility(View.INVISIBLE);
			
			
		}
		
		}catch(Exception ex){}
		acSwitchCheckBox.setChecked(turnOn);
		
	}
	
	
	public void DimDimmerOne(String value)
	{
		
		
		//int currentDimmerValue=Integer.parseInt(value);
		
		
		lockProgressOne=true;
		
		if(value.substring(0, 2).equals("0A"))
		{
			((ImageView)childLayout.findViewById(R.id.bulbIcon)).setImageResource(R.drawable.buld_yellow);
			((TextView) childLayout.findViewById(R.id.progressPercentage)).setText("100%");
			seekBar.setProgress(100);
			SwitchCheckBoxrLayoutControl0.setChecked(true);
			mSwitchModel.dimmingValue1=value.substring(0, 2);
		}
		else if(value.substring(0, 2).equals("0B"))
		{
			((ImageView)childLayout.findViewById(R.id.bulbIcon)).setImageResource(R.drawable.buld_gray);
			((TextView) childLayout.findViewById(R.id.progressPercentage)).setText("0%");
			seekBar.setProgress(0);
			SwitchCheckBoxrLayoutControl0.setChecked(false);
			//mSwitchModel.dimmingValue1=value.substring(0, 2);
			
		}
		else if(!value.substring(0, 2).equals("EE"))
		{
			((ImageView)childLayout.findViewById(R.id.bulbIcon)).setImageResource(R.drawable.buld_yellow);
			int i= Integer.parseInt(value.substring(0, 2),16);
			seekBar.setProgress(i*10);
			((TextView) childLayout.findViewById(R.id.progressPercentage)).setText(i*10+"%");
			SwitchCheckBoxrLayoutControl0.setChecked(true);
			mSwitchModel.dimmingValue1=value.substring(0, 2);
		}
		
		
		lockProgressOne=false;
		
		
	}
	
	
	public void DimDimmerTwo(String value)
	{
		
		
		//int currentDimmerValue=Integer.parseInt(value);
		
		
		lockProgressTwo=true;
		
		if(value.substring(2, 4).equals("0A"))
		{
			((ImageView)childLayout.findViewById(R.id.bulbIconTwo)).setImageResource(R.drawable.buld_yellow);
			seekBarTwo.setProgress(100);
			((TextView) childLayout.findViewById(R.id.progressPercentagetwo)).setText("100%");
			
			SwitchCheckBoxrLayoutControl21.setChecked(true);
			
			mSwitchModel.dimmingValue2=value.substring(2, 4);
		}
		else if(value.substring(2, 4).equals("0B"))
		{
			((ImageView)childLayout.findViewById(R.id.bulbIconTwo)).setImageResource(R.drawable.buld_gray);
			seekBarTwo.setProgress(0);
			((TextView) childLayout.findViewById(R.id.progressPercentagetwo)).setText("0%");
			
			SwitchCheckBoxrLayoutControl21.setChecked(false);
			//mSwitchModel.dimmingValue2=value.substring(2, 4);
		}
		else if(!value.substring(2, 4).equals("EE"))
		{
			((ImageView)childLayout.findViewById(R.id.bulbIconTwo)).setImageResource(R.drawable.buld_yellow);
			int i= Integer.parseInt(value.substring(2, 4),16);
			seekBarTwo.setProgress(i*10);
			((TextView) childLayout.findViewById(R.id.progressPercentagetwo)).setText(i*10+"%");
			SwitchCheckBoxrLayoutControl21.setChecked(true);
			mSwitchModel.dimmingValue2=value.substring(2, 4);
		}
		
		lockProgressTwo=false;
	}
	
	
	public void DimDimmerThree(String value)
	{
		
		lockProgressThree=true;
		//int currentDimmerValue=Integer.parseInt(value);
		
		
		if(value.substring(4, 6).equals("0A"))
		{
			((ImageView)childLayout.findViewById(R.id.bulbIconThree)).setImageResource(R.drawable.buld_yellow);
			seekBarThree.setProgress(100);
			((TextView) childLayout.findViewById(R.id.progressPercentageThree)).setText("100%");
			
			SwitchCheckBoxrLayoutControl32.setChecked(true);
			mSwitchModel.dimmingValue3=value.substring(4, 6);
		}
		else if(value.substring(4, 6).equals("0B"))
		{
			((ImageView)childLayout.findViewById(R.id.bulbIconThree)).setImageResource(R.drawable.buld_gray);
			seekBarThree.setProgress(0);
			((TextView) childLayout.findViewById(R.id.progressPercentageThree)).setText("0%");
			
			SwitchCheckBoxrLayoutControl32.setChecked(false);
			//mSwitchModel.dimmingValue3=value.substring(4, 2);
		}
		else if(!value.substring(4, 6).equals("EE"))
		{
			((ImageView)childLayout.findViewById(R.id.bulbIconThree)).setImageResource(R.drawable.buld_yellow);
			
			int i= Integer.parseInt(value.substring(4, 6),16);
			seekBarThree.setProgress(i*10);
			((TextView) childLayout.findViewById(R.id.progressPercentageThree)).setText(i*10+"%");
			SwitchCheckBoxrLayoutControl32.setChecked(true);
			mSwitchModel.dimmingValue3=value.substring(4, 6);
		}
		
		
		lockProgressThree=false;
		
	}
	
	
	public void turnOnOffButtonTwo(boolean turnOn)
	{
		
		if(turnOn)
		{
			((ImageView) rLayoutControl21.findViewById(R.id.bulbIconButton2)).setImageResource(R.drawable.buld_yellow);
			
			((TextView)rLayoutControl21.findViewById(R.id.onOffStatus2)).setText(getResources().getString(R.string.ON));
			
			
		}
		else
		{
			((ImageView) rLayoutControl21.findViewById(R.id.bulbIconButton2)).setImageResource(R.drawable.buld_gray);
			
			((TextView)rLayoutControl21.findViewById(R.id.onOffStatus2)).setText(getResources().getString(R.string.OFF));
			
		}
		
		SwitchCheckBoxrLayoutControl21.setChecked(turnOn);
		
	}
	
	public void turnOnOffButtonThree(boolean turnOn)
	{
		
		if(turnOn)
		{
			((ImageView) rLayoutControl32.findViewById(R.id.bulbIconButton3)).setImageResource(R.drawable.buld_yellow);
			
			((TextView)rLayoutControl32.findViewById(R.id.onOffStatus3)).setText(getResources().getString(R.string.ON));
		}
		else
		{
			((ImageView) rLayoutControl32.findViewById(R.id.bulbIconButton3)).setImageResource(R.drawable.buld_gray);
			
			((TextView)rLayoutControl32.findViewById(R.id.onOffStatus3)).setText(getResources().getString(R.string.OFF));
			
		}
		
		SwitchCheckBoxrLayoutControl32.setChecked(turnOn);
		
	}

	
	
	
	
	public String sendReadMessagetoSwitch()
	   {
		   
		isLatestCommand=false;
		
		if(!isSocketConnected)
		{
			isConnectionFailedEventOccured=false;
			startTCPClient();
			
			
			 while(!isSocketConnected)
		     {
		    	 if(isConnectionFailedEventOccured)
		    	 {
		    		 break;
		    	 }
		    	 
		    	 try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		     }
			 
			
			if(singleTcpClient!=null && !isConnectionFailedEventOccured)
			{
				
				
				
				byte[] cmd=SwitchCommandManager.requestInPutCommand(mSwitchModel.mac_address.substring(0,6),mSwitchModel.type);
			    
			     singleTcpClient.setCallbackForGetCommand(new ServerMessageResopnse() {
					
					@Override
					public void onGetCommandFromServer(String cmd) {
						
						isLatestCommand=true;
						
						currentCommand=cmd;
						
						
						
						
					}
				}, cmd);
				
				
			}
			
			
		     
		     
		     while(!isLatestCommand)
		     {
		    	 if(!isSocketConnected)
		    	 {
		    		 break;
		    	 }
		    	 
		    	 try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     }
		     
			
			
			
		}
		else
		{
		
		isLatestCommand=false;
		
		    byte[] cmd=SwitchCommandManager.requestInPutCommand(mSwitchModel.mac_address.substring(0,6),mSwitchModel.type);
		    
		     singleTcpClient.setCallbackForGetCommand(new ServerMessageResopnse() {
				
				@Override
				public void onGetCommandFromServer(String cmd) {
					
					isLatestCommand=true;
					
					currentCommand=cmd;
					
					
					
					
				}
			}, cmd);
		     
		     
		     try
		     {
		     while(!isLatestCommand)
		     {
		    	 if(!isSocketConnected)
		    	 {
		    		 break;
		    	 }
		    	 
		    	 Thread.sleep(500);
		     }
		     }catch(Exception ex){}
		     
		}
		     
		     
		     return currentCommand;
		     
		   
	   }
	
	
	public void sendMessageToSwitch(final byte[] cmd)
	{
		
		if(!isSocketConnected && isCallFromPopup)
		{
			
			isCallFromPopup=false;
			
			startTCPClient();
			
			roomsMainLayout.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					
					if(singleTcpClient!=null)
					{
						singleTcpClient.sendMessageByte(cmd);
					}
					 
					
				}
			}, 300);
			
		}
		else
		{
			if(singleTcpClient!=null)
			{
				singleTcpClient.sendMessageByte(cmd);
			}
		}
	}
	
	//
	
	
	public void readMessageReponse(final String message) {
		
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				try
				{
				handleCommand(message);
				}catch(Exception ex){}
				WaitingStaticProgress.hideProgressDialog();
			}
		});
		
	}
	
	
	public void onSwitchButttonClick(View view )
	{
		//overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);
		
		
		
		
		switchesMainLayout.setVisibility(View.VISIBLE);
		roomsMainLayout.setVisibility(View.VISIBLE);
		
		roomsMainLayout.setAnimation(animation2__);
		
		switchesMainLayout.setAnimation(animation1__);
		
		animation1__.start();
		animation2__.start();
		
		
		animation2__.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
				
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				 
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				
				
				
				switchesMainLayout.setVisibility(View.GONE);
				
				roomsMainLayout.setVisibility(View.VISIBLE);
				
				isPerformingFirstConnection=true;
				
				
			}
		});
		
		
		
		if(singleTcpClient!=null)
		{
		   singleTcpClient.stopClient();
		   if(task!=null)
		   {
			   task.cancel(true);
			   
			   task=null;
		   }
		   
		}
		
	}
	
	
	public void onSwitchhomeButttonClick(View view)
	{
		onhomeButttonClick(view);
	}
	
	
}
