package com.livixa.apacam.client.activity;

import com.activeandroid.query.Select;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.livixa.apacam.client.activity.Add_Edit_SwitchActivity.SwitchTypes;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.sliderview.CircularSeekBar;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue;
import com.livixa.apacam.customprogressbar.CustomSimpleAlertDialogue.CustomDialogueClickListner;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.dbmodel.Switch_Model;
import com.livixa.apacam.services.TCP_Client_Service;
import com.livixa.apacam.services.TCP_Client_Service.MessageHandlerListner;
import com.livixa.apacam.services.TCP_Client_Service.TCP_Client_ServiceBinder;
import com.livixa.client.R;


public class ManageSwitchActivity extends Activity implements MessageHandlerListner{

	
	
	 
	 LinearLayout layoutContainer;
	private Switch_Model mSwitchModel;
	
	
	CheckBox    acSwitchCheckBox;
	
	boolean isProgressIsZero=true;
	
	boolean lockProgressOne=false;
	boolean lockProgressTwo=false;
	boolean lockProgressThree=false;
	
	private String mRoomId;
	private String mRoomTitle;
	
	
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
    
    TCP_Client_Service mService;
	
    boolean mBound = false;

    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aaaaatest);
		
		
		
		
		layoutContainer=(LinearLayout) findViewById(R.id.layoutContainer);
		
		handleIntent();
		
		addRequiredLayout();
		
		
		//TCPClient.SERVERIP =mSwitchModel.ip_address; 
		
		//TCPClient1.SERVERIP =mSwitchModel.ip_address; 
		
		
		//new connectTask().execute("");
		
		//new connectTask1().execute("");
		
		
		try
		{
		handleCommand(currentCommand);
		}catch(Exception ex){}
		
	}
	//hell
	
	
	
	
	@Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, TCP_Client_Service.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }
	
	private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
        	TCP_Client_ServiceBinder binder = (TCP_Client_ServiceBinder) service;
        	
            mService = binder.getService();
            
            mService.setMessageHandlerListner(ManageSwitchActivity.this);
            
            //Toast.makeText(getApplicationContext(), "Service Connected", Toast.LENGTH_SHORT).show();
            
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
        	
        	//Toast.makeText(getApplicationContext(), "Failed to Connect", Toast.LENGTH_SHORT).show();
            mBound = false;
        }
    };
	
	
	@Override
	protected void onDestroy() {

		
		try
		{
			
			
			try
			{
					
					if(mService!=null)
					{
					  mService.stopTCPClient();
					}
			}catch(Exception ex)
			{
				
			}
					
			if(mConnection!=null)
					if (mBound) {
			            unbindService(mConnection);
			            mBound = false;
			        }
				
			
		
		 
			
			
			
        
		}catch(Exception ex){}
		//mService.stopSelf();
        
		super.onDestroy();
	}
	
	
	@Override
    protected void onStop() {
        super.onStop();
       
        
        
       
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
		
		
		
		layoutContainer.addView(childLayout);
		
	}
	
	
	
	private void handleActionOfOneSwitch(View view)
	{
		
		
		SwitchCheckBoxrLayoutControl0=new CheckBox(ManageSwitchActivity.this);
		
		
		
		
		
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
	            	
	            	
	            	WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
	            	
	            	final String tempProgressToSend=progressToSend;
	            	
	            	new Thread( new Runnable() {
						public void run() {
							
							
							currentCommand=getCurrentCommandFromSwitch();
			            	
			            	byte []cmd=SwitchCommandManager.handleDimmerButtonOne( currentCommand ,"01", convertIntProgressToHEX(tempProgressToSend));
			            	mService.sendMessage(cmd);	
							
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
					
					WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
					
					new Thread( new Runnable() {
						public void run() {
							
							
							currentCommand=getCurrentCommandFromSwitch();
			            	
			            	byte []cmd=SwitchCommandManager.turnOnOFFButtonOne( currentCommand ,"01", tempOnOffcmd);
			            	mService.sendMessage(cmd);	
							
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
						//mService.sendMessage(cmd);
						
						WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
		            	
		            	final String tempProgressToSend=progressToSend;
		            	
		            	new Thread( new Runnable() {
							public void run() {
								
								
								currentCommand=getCurrentCommandFromSwitch();
				            	
				            	byte []cmd=SwitchCommandManager.handleDimmerButtonOne( currentCommand ,"01", tempProgressToSend);
				            	mService.sendMessage(cmd);	
								
							}
						}).start();
						
						
					}
					else
					{
						//byte[] cmd=SwitchCommandManager.handleDimmerButtonOne(currentCommand,"01", "0B");
						//mService.sendMessage(cmd);
						
						WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
		            	
		            	new Thread( new Runnable() {
							public void run() {
								
								
								currentCommand=getCurrentCommandFromSwitch();
				            	
				            	byte []cmd=SwitchCommandManager.handleDimmerButtonOne( currentCommand ,"01", "0B");
				            	mService.sendMessage(cmd);	
								
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
		
		//SwitchCheckBoxrLayoutControl0=new CheckBox(ManageSwitchActivity.this);
		
		
		
		handleActionOfOneSwitch(view);
		
		
		 SwitchCheckBoxrLayoutControl21=new CheckBox(ManageSwitchActivity.this);
		
		
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
		            	
		            	
		            	
		            	WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
		            	
		            	final String tempProgressToSend=progressToSend;
		            	
		            	new Thread( new Runnable() {
							public void run() {
								
								
								currentCommand=getCurrentCommandFromSwitch();
				            	
				            	byte []cmd=SwitchCommandManager.handleDimmerButtonTwo( currentCommand ,"01", convertIntProgressToHEX(tempProgressToSend));
				            	mService.sendMessage(cmd);	
								
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
						
						WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
						
						new Thread( new Runnable() {
							public void run() {
								
								
								currentCommand=getCurrentCommandFromSwitch();
				            	
				            	byte []cmd=SwitchCommandManager.turnOnOFFButtonTwo( currentCommand ,"01", tempOnOffcmd);
				            	mService.sendMessage(cmd);	
								
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
							
							
							
							WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
			            	
			            	final String tempProgressToSend=progressToSend;
			            	
			            	new Thread( new Runnable() {
								public void run() {
									
									
									currentCommand=getCurrentCommandFromSwitch();
					            	
					            	byte []cmd=SwitchCommandManager.handleDimmerButtonTwo( currentCommand ,"01", tempProgressToSend);
					            	mService.sendMessage(cmd);	
									
								}
							}).start();
							
							
							
						}
						else
						{
							
							WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
			            	
			            	
			            	
			            	new Thread( new Runnable() {
								public void run() {
									
									
									currentCommand=getCurrentCommandFromSwitch();
					            	
					            	byte []cmd=SwitchCommandManager.handleDimmerButtonTwo( currentCommand ,"01", "0B");
					            	mService.sendMessage(cmd);	
									
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
		
		 SwitchCheckBoxrLayoutControl32=new CheckBox(ManageSwitchActivity.this);
		 
		 
		
		
 
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
			            	
			            	
							WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
			            	
			            	final String tempProgressToSend=progressToSend;
			            	
			            	new Thread( new Runnable() {
								public void run() {
									
									
									currentCommand=getCurrentCommandFromSwitch();
					            	
					            	byte []cmd=SwitchCommandManager.handleDimmerButtonThree( currentCommand ,"01", convertIntProgressToHEX(tempProgressToSend));
					            	mService.sendMessage(cmd);	
									
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
							
							WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
							
							new Thread( new Runnable() {
								public void run() {
									
									
									currentCommand=getCurrentCommandFromSwitch();
					            	
					            	byte []cmd=SwitchCommandManager.turnOnOFFButtonThree( currentCommand ,"01", tempOnOffcmd);
					            	mService.sendMessage(cmd);	
									
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
								
								
								
								WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
				            	
				            	final String tempProgressToSend=progressToSend;
				            	
				            	new Thread( new Runnable() {
									public void run() {
										
										
										currentCommand=getCurrentCommandFromSwitch();
						            	
						            	byte []cmd=SwitchCommandManager.handleDimmerButtonThree( currentCommand ,"01", tempProgressToSend);
						            	mService.sendMessage(cmd);	
										
									}
								}).start();
								
								
							}
							else
							{
								
								
								WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
				            	
				            	
				            	
				            	new Thread( new Runnable() {
									public void run() {
										
										
										currentCommand=getCurrentCommandFromSwitch();
						            	
						            	byte []cmd=SwitchCommandManager.handleDimmerButtonThree( currentCommand ,"01", "0B");
						            	mService.sendMessage(cmd);	
										
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
		
		acSwitchCheckBox=new CheckBox(ManageSwitchActivity.this);
		
		
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
				
				WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
				
				new Thread( new Runnable() {
					public void run() {
						
						
						currentCommand=getCurrentCommandFromSwitch();
		            	
		            	byte []cmd=SwitchCommandManager.turnOnOFFAC( currentCommand ,"01", tempOnOffcmd);
		            	mService.sendMessage(cmd);	
						
					}
				}).start();
				
				
			}
		});
		
	}
	
	
	
	private void handleActionsOfSocketSwitch(View view)
	{
		
		acSwitchCheckBox=new CheckBox(ManageSwitchActivity.this);
		
		
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
				
				WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
				
				new Thread( new Runnable() {
					public void run() {
						
						
						currentCommand=getCurrentCommandFromSwitch();
		            	
		            	byte []cmd=SwitchCommandManager.turnOnOFFSocket( currentCommand ,"01", tempOnOffcmd);
		            	mService.sendMessage(cmd);	
						
					}
				}).start();
				
			}
		});
		
	}
	
	
	
	
	
	public static String hexToString(String hex) {
	    StringBuilder sb = new StringBuilder();
	    char[] hexData = hex.toCharArray();
	    for (int count = 0; count < hexData.length - 1; count += 2) {
	        int firstDigit = Character.digit(hexData[count], 16);
	        int lastDigit = Character.digit(hexData[count + 1], 16);
	        int decimal = firstDigit * 16 + lastDigit;
	        sb.append((char)decimal);
	    }
	    return sb.toString();
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	public String convertHexToString(String hex){

		  StringBuilder sb = new StringBuilder();
		  StringBuilder temp = new StringBuilder();

		  //49204c6f7665204a617661 split into two characters 49, 20, 4c...
		  for( int i=0; i<hex.length()-1; i+=2 ){

		      //grab the hex in pairs
		      String output = hex.substring(i, (i + 2));
		      //convert hex to decimal
		      int decimal = Integer.parseInt(output, 16);
		      //convert the decimal to character
		      sb.append((char)decimal);

		      temp.append(decimal);
		  }
		  

		  return sb.toString();
	  }
	
	private static String asciiToHex(String asciiValue)
	{
	    char[] chars = asciiValue.toCharArray();
	    StringBuffer hex = new StringBuffer();
	    for (int i = 0; i < chars.length; i++)
	    {
	        hex.append(Integer.toHexString((int) chars[i]));
	    }
	    return hex.toString();
	}
	
	 
	
	
	
	public void handleIntent() {
		try {

			Intent intent = getIntent();

			String mSwitchId = intent.getStringExtra(AppKeys.KEY_SWITCH_ID_TAG);
			
			mRoomId = intent.getStringExtra(AppKeys.KEY_ROOM_ID_TAG);
			mRoomTitle = intent.getStringExtra(AppKeys.KEY_ROOM_TITLE_TAG);
			
			currentCommand=intent.getStringExtra("CMD");
			
			mSwitchModel = getSwitchFromDb(mSwitchId);
			
			if(mSwitchModel!=null)
			{
				((TextView)findViewById(R.id.tv_title)).setText(mSwitchModel.title);
			}

		} catch (Exception ex) {
			ex.toString();
		}
	}
	
	private Switch_Model getSwitchFromDb(String roomId) {
		Switch_Model switchModel = null;

		try {
			switchModel = new Select().from(Switch_Model.class).where("switch_id = ?", roomId).executeSingle();
		} catch (Exception ex) {
			ex.toString();
		}

		return switchModel;
	}
	
	
	public void onmenuButttonClick(View view) {
		
		
		Toast.makeText(getApplicationContext(), "Clicket", Toast.LENGTH_SHORT).show();
	}
	
	
	public void onLastScreenButttonClick(View view) {
		 Intent intent = new Intent(this, MoodsRoomWithSwitchesActivity.class);
			intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, mRoomId);
			intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, mRoomTitle);
			startActivity(intent);
			KisafaApplication.perFormActivityNextTransition(this);
			finish();
	}

	public void onhomeButttonClick(View view) {
		finish();

		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		
		 Intent intent = new Intent(this, MoodsRoomWithSwitchesActivity.class);
		intent.putExtra(AppKeys.KEY_ROOM_ID_TAG, mRoomId);
		intent.putExtra(AppKeys.KEY_ROOM_TITLE_TAG, mRoomTitle);
		startActivity(intent);
		KisafaApplication.perFormActivityBackTransition(this);
		finish();

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
						
						
						WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
						
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								
								
								mService.sendMessage(SwitchCommandManager.hexStringToByteArray(finaltempCMD));
								
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
							
							
							WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
							
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									
									
									mService.sendMessage(SwitchCommandManager.hexStringToByteArray(finaltempCMD));
									
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
						
						
						WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
						
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								
								
								mService.sendMessage(SwitchCommandManager.hexStringToByteArray(finaltempCMD));
								
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
						
						
						WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
						
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								
								
								mService.sendMessage(SwitchCommandManager.hexStringToByteArray(finaltempCMD));
								
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
						
						
						WaitingStaticProgress.showProgressDialog("Please wait ...", ManageSwitchActivity.this);
						
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								
								
								mService.sendMessage(SwitchCommandManager.hexStringToByteArray(finaltempCMD));
								
							}
						});
						
					}
				});
			}
		 
		 
			
		

		
		 
		 
		 
		 
		
	}
	
	
	//--------------------------------------------method to perform actions on server response
	
	
	public void turnOnOffButtonOne(boolean turnOn)
	{
		
		if(turnOn)
		{
			((ImageView) rLayoutControl0.findViewById(R.id.bulbIconButton)).setImageResource(R.drawable.buld_yellow);
			
			((TextView)rLayoutControl0.findViewById(R.id.onOffStatus)).setText("ON");
		}
		else
		{
			((ImageView) rLayoutControl0.findViewById(R.id.bulbIconButton)).setImageResource(R.drawable.buld_gray);
			
			((TextView)rLayoutControl0.findViewById(R.id.onOffStatus)).setText("OFF");
			
		}
		
	}
	
	
	public void turnOnOffAC(boolean turnOn)
	{
		
		if(turnOn)
		{
			((ImageView) rLayoutControl0.findViewById(R.id.bulbIconButton)).setImageResource(R.drawable.buld_yellow);
			
			((TextView)rLayoutControl0.findViewById(R.id.onOffStatus)).setText("ON");
		}
		else
		{
			((ImageView) rLayoutControl0.findViewById(R.id.bulbIconButton)).setImageResource(R.drawable.buld_gray);
			
			((TextView)rLayoutControl0.findViewById(R.id.onOffStatus)).setText("OFF");
			
		}
		
	}
	
	
	public void turnOnOffSocketandAc(boolean turnOn)
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
			mSwitchModel.dimmingValue3=value.substring(4, 2);
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
			mSwitchModel.dimmingValue3=value.substring(4, 2);
		}
		
		
		lockProgressThree=false;
		
	}
	
	
	public void turnOnOffButtonTwo(boolean turnOn)
	{
		
		if(turnOn)
		{
			((ImageView) rLayoutControl21.findViewById(R.id.bulbIconButton2)).setImageResource(R.drawable.buld_yellow);
			
			((TextView)rLayoutControl21.findViewById(R.id.onOffStatus2)).setText("ON");
			
			
		}
		else
		{
			((ImageView) rLayoutControl21.findViewById(R.id.bulbIconButton2)).setImageResource(R.drawable.buld_gray);
			
			((TextView)rLayoutControl21.findViewById(R.id.onOffStatus2)).setText("OFF");
			
		}
		
		SwitchCheckBoxrLayoutControl21.setChecked(turnOn);
		
	}
	
	public void turnOnOffButtonThree(boolean turnOn)
	{
		
		if(turnOn)
		{
			((ImageView) rLayoutControl32.findViewById(R.id.bulbIconButton3)).setImageResource(R.drawable.buld_yellow);
			
			((TextView)rLayoutControl32.findViewById(R.id.onOffStatus3)).setText("ON");
		}
		else
		{
			((ImageView) rLayoutControl32.findViewById(R.id.bulbIconButton3)).setImageResource(R.drawable.buld_gray);
			
			((TextView)rLayoutControl32.findViewById(R.id.onOffStatus3)).setText("OFF");
			
		}
		
		SwitchCheckBoxrLayoutControl32.setChecked(turnOn);
		
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
        
          //Toast.makeText(ManageSwitchActivity.this,mac, Toast.LENGTH_SHORT).show();
          
          String type=SwitchCommandManager.getSwitchType(comingCommand);
          
          //Toast.makeText(ManageSwitchActivity.this,type, Toast.LENGTH_SHORT).show();
          
          String switchInsideTypes=SwitchCommandManager.getDimmerSettingValues(comingCommand);
          
          //Toast.makeText(ManageSwitchActivity.this,switchInsideTypes, Toast.LENGTH_SHORT).show();
          
          
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
	
	
	
	
public void on_Failure(String retrofitError) {
	
runOnUiThread(new Runnable() {
		
		@Override
		public void run() {
			
			
			WaitingStaticProgress.hideProgressDialog();
			
			final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(ManageSwitchActivity.this,"Failed to connect");
			
			cusDial.setListner(new CustomDialogueClickListner() {
				
				@Override
				public void onCustomDialogueClick() {
					
					
					cusDial.dismiss();
					
					
				}
			});
			
			
			cusDial.show();
			
		}
	});
	
	try
	{
	mService.stopTCPClient();
	}catch(Exception ex)
	{
		
	}
}


@Override
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


@Override
public void onFailure(String message) {
	
	
	
	runOnUiThread(new Runnable() {
		
		@Override
		public void run() {
			
			
			WaitingStaticProgress.hideProgressDialog();
			
			/*final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(ManageSwitchActivity.this,"Failed to connect");
			
			cusDial.setListner(new CustomDialogueClickListner() {
				
				@Override
				public void onCustomDialogueClick() {
					
					
					cusDial.dismiss();
					
					
					onBackPressed();
					
					
				}
			});
			
			
			cusDial.show();*/
			
		}
	});
	
	try
	{
	mService.stopTCPClient();
	}catch(Exception ex)
	{
		
	}
	
}




@Override
public void onSocketConnectedFailer(String message) {
	
	
runOnUiThread(new Runnable() {
		
		@Override
		public void run() {
			
			
			WaitingStaticProgress.hideProgressDialog();
			
			final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(ManageSwitchActivity.this,"Failed to connect");
			
			cusDial.setListner(new CustomDialogueClickListner() {
				
				@Override
				public void onCustomDialogueClick() {
					
					
					cusDial.dismiss();
					
					
					onBackPressed();
					
					
				}
			});
			
			
			cusDial.show();
			
		}
	});
	
	try
	{
	mService.stopTCPClient();
	}catch(Exception ex)
	{
		
	}
	
}




@Override
public void onsocketConnection(String message) {
	// TODO Auto-generated method stub
	
}




@Override
public void onSocketConnectedSelfClose(String message) {
	
	try
	{
	
	
	runOnUiThread(new Runnable() {
		
		@Override
		public void run() {
			
			WaitingStaticProgress.hideProgressDialog();
			
			
		}
	});
	
	if(mService!=null)
	{
		mService.stopTCPClient();
	}
	}catch(Exception ex){}
}




@Override
public void onFourTriesFailer(String message) {
	
	runOnUiThread(new Runnable() {
		
		@Override
		public void run() {
			
			
			WaitingStaticProgress.hideProgressDialog();
			
			final CustomSimpleAlertDialogue cusDial=new CustomSimpleAlertDialogue(ManageSwitchActivity.this,"Failed to connect");
			
			cusDial.setListner(new CustomDialogueClickListner() {
				
				@Override
				public void onCustomDialogueClick() {
					
					
					cusDial.dismiss();
					
					
				}
			});
			
			
			cusDial.show();
			
		}
	});
	

}




@Override
public void writeMessageReponse(String message) {
	// TODO Auto-generated method stub
	
}

   public String getCurrentCommandFromSwitch()
   {
	   
	    byte[] cmd=SwitchCommandManager.requestInPutCommand(mSwitchModel.mac_address.substring(0,6),mSwitchModel.type);
	    
	   return  mService.getCurrentCommand(cmd);
	   
   }


}
