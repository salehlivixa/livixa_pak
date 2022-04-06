package com.livixa.apacam.services;

import android.app.Service;
import android.content.Intent;
import android.mtp.MtpConstants;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;import android.os.Message;
import android.widget.Toast;
import info.lamatricexiste.network.StaticProgress;
import com.livixa.apacam.client.activity.ManageSwitchActivity;
import com.livixa.apacam.client.activity.MoodsRoomWithSwitchesActivity;
import com.livixa.apacam.client.activity.SwitchCommandManager;
import com.livixa.apacam.client.activity.TCPClient;
import com.livixa.apacam.client.activity.TCP_Client_Manager.connectTask;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.dbmodel.Switch_Model;

public class TCP_Client_Service  extends Service
{

		TCPClient  singleTcpClient=null;
		
		MessageHandlerListner   messageHandlerListner;
		
		
		String  mSwitchModelCommand;
		
		
		boolean isConnectionLost=false;
		
		
		byte[] messageQeue=null;
		
		
		int curentIndex=1;
		
		connectTask task;
		
		  private boolean isNoResposeFromSwitch=true;
		  
		  
		  String currentCommand="";
		  
		  
		  byte[] commandToSend=null;
		  
		  String readCommand="";
		  
		  
		  boolean isReadCommandReceived=false;
		  
		  
		  boolean isStartUpCommandSent=false;
		  
		  
		  boolean stopHandleronFailier=false;
		  
	    
	    private final IBinder mBinder = new TCP_Client_ServiceBinder();
	    
	    public class TCP_Client_ServiceBinder extends Binder {
	    	public TCP_Client_Service getService() {
	           
	            return TCP_Client_Service.this;
	        }
	    }

	    @Override
	    public IBinder onBind(Intent intent) {
	        return mBinder;
	    }

	    
	    
	    
	    @Override
	    public void onCreate() {
	    
	    super.onCreate();
	    
	    
	    
	    
	    }
	    
	    @Override
	    public int onStartCommand(Intent intent, int flag, int startId)
	    {
	        return 0;
	    }
	    
	    public void creatTCPClient()
	    {
	    	singleTcpClient=null;
	    	isNoResposeFromSwitch=true;
	    	stopHandleronFailier=false;
	    	currentTries=0;
	    	task=new connectTask();
	    	task.execute("");
		    
	    }
	    
	    
	    public void stopTCPClient()
	    {
	    	isNoResposeFromSwitch=true;
	    	if(task!=null)
	    	{
	    		task.cancel(true);
	    		if(singleTcpClient!=null)
	    		{
	    		singleTcpClient.stopClient();
	    		currentTries=0;
	    		task=null;
	    		}
	    		
	    	}
	    }
	    
	    
	    public  class connectTask extends AsyncTask<String,String,TCPClient> {
			 
		       

			@Override
	        protected TCPClient doInBackground(String... message) {
	 
	            //we create a TCPClient object and
				try
				{
				if(singleTcpClient==null)
				{
						singleTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
			                @Override
			               
			                public void messageReceived(final String message) {
			                   
			                	isConnectionLost=false;
			                	isNoResposeFromSwitch=false;
			                	stopHandleronFailier=false;
			                	curentIndex=1;
			                	
			                	
			                	
			                	
			                	curentIndex=11;
			                	
			                	    
			                	onProgressUpdate(message);
			                	
			                	  
			                	isStartUpCommandSent=false;
			                	
			                	
			                }

							@Override
							public void onsocketConnectionClosed(String message) {
								
								curentIndex=2;
								stopHandleronFailier=true;
								onProgressUpdate(message);
								
								isConnectionLost=true;
								
								
								
								
							}

							@Override
							public void onsocketConnectionFailer(String message) {
								curentIndex=3;
								
								stopHandleronFailier=true;
								
								onProgressUpdate(message);
								
								isConnectionLost=true;
								
							}

							@Override
							public void onSocketConnection(String message) {
								
								isConnectionLost=false;
								
			                	 curentIndex=0;
			                	   onProgressUpdate(message);
			                	   
			                	
							}

							@Override
							public void onSocketSelfClose(String message) {
								
								   isConnectionLost=true;
								   stopHandleronFailier=true;
			                	   curentIndex=4;
			                	   onProgressUpdate(message);
							}
			            });
						singleTcpClient.run();
				}
				
				}catch(Exception ex){}
	 
	            return null;
	        }
			
			@Override
	        protected void onProgressUpdate(String... values) {
	            super.onProgressUpdate(values);
	            
	            
	            if(messageHandlerListner!=null && curentIndex==0)
            	{
       				messageHandlerListner.onsocketConnection(values[0]);
       				
            	}
	 
	            if(messageHandlerListner!=null && curentIndex==11)
            	{
	            	
	            	//Toast.makeText(TCP_Client_Service.this, values[0], Toast.LENGTH_SHORT).show();
	            	
       				messageHandlerListner.readMessageReponse(values[0]);
       				
       				currentTries=4;
            	}
	            
	            if(messageHandlerListner!=null && curentIndex==12)
            	{
       				messageHandlerListner.writeMessageReponse(values[0]);
       				
       				
            	}
	            
	            if(messageHandlerListner!=null && curentIndex==2)
            	{
       				messageHandlerListner.onFailure(values[0]);
       				currentTries=4;
            	}
	            
	            
	            if(messageHandlerListner!=null && curentIndex==3)
            	{
       				messageHandlerListner.onSocketConnectedFailer(values[0]);
       				currentTries=4;
       				
            	}
	            
	            if(messageHandlerListner!=null && curentIndex==4)
            	{
       				messageHandlerListner.onSocketConnectedSelfClose(values[0]);
       				
            	}
	            
	            
	            
	        }
			
		}
	    
	
       public interface MessageHandlerListner
       {
    	   public void onsocketConnection(String message);
    	   
    	   public void readMessageReponse(String message);
    	   
    	   public void writeMessageReponse(String message);
    	   
    	   public void onFailure(String message);
    	   
    	   public void onFourTriesFailer(String message);
    	   
    	   public void onSocketConnectedFailer(String message);
    	   
    	   public void onSocketConnectedSelfClose(String message);
       }
       
       public void setMessageHandlerListner(MessageHandlerListner   messageHandlerListner)
       {
    	   this.messageHandlerListner=messageHandlerListner;
       }
       
       
       public void sendMessage(final byte[] message)
       {
    	   
    		       if(isConnectionLost)
    			   {
    			     
    		    	   stopTCPClient();
    		    	   
    		    	   creatTCPClient();
    		    	   
    		    	   
    		    	   
    		    	   Thread parseThread= new Thread( new Runnable() {
						public void run() {
							
							
							
							 if(singleTcpClient!=null)
			    	    	   {
			    		    	 while(isConnectionLost)
			    		    	 {
			    		    		 
			    		    	 }
			    		    	 
			    		    	 connectToSwitchin4Tries();
			    		    	 
			    		    	 
			    		    	 while(isNoResposeFromSwitch)
			    		    	 {
			    		    		 
			    		    	 }
			    		    	 
			    		    	  singleTcpClient.sendMessageByte(message);
			    	    	   }
							
							
						}
					});
    		    	   
    		    	   
    		    	   parseThread.start();
    		    	   
    		    	  
    		    	   
    		    	  
    			   }
    		       else
    	          {
    		    	      if(singleTcpClient!=null)
		    	    	   {
		    		    	  
		    		    	   
		    		    	  singleTcpClient.sendMessageByte(message);
		    	    	   }
    		       }
    		       
    	   
       }
       
       String requestResult="";
       public String getCurrentCommand(final byte[] requestCommand)
       {
    	    
    	   
    	   if(isConnectionLost)
		   {
		     
    		   stopTCPClient();
	    	   
	    	   creatTCPClient();
	    	   
	    	   
	    	   connectToSwitchin4Tries();
	    	   
	    	   
	    	   Thread parseThread=new Thread( new Runnable() {
				public void run() {
					
					
					
					 if(singleTcpClient!=null)
	    	    	   {
	    		    	 while(isConnectionLost)
	    		    	 {
	    		    		
	    		    	 }
	    		    	 
	    		    	 
	    		    	 
	    		    	
	    		    	 requestResult = singleTcpClient.getCurrentCommand(requestCommand);
	    	    	   }
					
					
				}
			});
	    	   
	    	   parseThread.start();
	    	   
	    	   
	    	   
	    	   try {
				parseThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	   
	    	   
		   }
    	   else
    	   {
    		   
    		   return singleTcpClient.getCurrentCommand(requestCommand);
    		   
    	   }
    	   
    	   return requestResult;
    	   
       }
	 
       
       
       private int FIVE_SECONDS = 5000;
   	
   	public int maxTries=3;
   	
   	public int currentTries=0;

   	private Handler handler=new Handler();
   	public void connectToSwitchin4Tries() {
   	    handler.postDelayed(new Runnable() {
   	      

			public void run() {
				
				
				
				if(stopHandleronFailier)
				{
					return;
				}
   	                  
   	        	if(currentTries>=maxTries)
   	        	{
   	        		if(isNoResposeFromSwitch)
   	        		{
   	        			WaitingStaticProgress.hideProgressDialog();
   	        			
   	        			if(messageHandlerListner!=null)
   	        				messageHandlerListner.onFourTriesFailer("Failed to Connect");
   	        		}
   	        		
   	        		return;
   	        	}
   	        	
   	        	byte[] cmd=SwitchCommandManager.requestInPutCommand(mSwitchModelCommand.substring(0,6), mSwitchModelCommand.substring(6,8));
   	        	singleTcpClient.sendStartUpMessageByte(cmd);
   	        	isStartUpCommandSent=true;
   	        	currentTries++;
   	            handler.postDelayed(this, FIVE_SECONDS);
   	        }
   	    }, 300);
   	}
   	
	
   	public void setSwitchCommand(String command)
   	{
   		mSwitchModelCommand=command;
   	}




	



	
   	
}
