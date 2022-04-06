package com.livixa.apacam.client.activity;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class TCPClient_ForGettingTime {

    private String serverMessage="";
    public static  String SERVERIP = "192.168.15.38"; //your computer IP address
    public static  int SERVERPORT = 55555;
    private OnMessageReceived mMessageListener = null;
    private boolean mRun = false;

    PrintWriter out;
    BufferedReader in;
    DataInputStream inin;
    
    DataOutputStream outout;
    
    
    byte[] messageByte = new byte[19];
	private boolean end=false;
	private int bytesRead;
	
	
	Context context;
	
	boolean isSocketEventHandeled=false;
	
	
	boolean isResponseArrived=false;
	
	
	String currentResponseFromServer=null;
	
	 Socket socket=null;
	 
	 ServerMessageResopnse  getCommandListner;
	 
	 
	 LocalSocketConnection  localSocketConnection;
	 
	 Thread threadTimer; 
	 
	 
	 CountDownTimer countdowntimer;
	 
	 private boolean isCountdownTimerCanceled=false;
	 
	 
	 
	
    public TCPClient_ForGettingTime(OnMessageReceived listener,Context context) {
        mMessageListener = listener;
        this.context= context;
    }

    /**
     * Sends the message entered by client to the server
     * @param message text entered by client
     */
    public void sendMessage(String message){
        if (out != null && !out.checkError()) {
            out.println(message);
            out.flush();
        }
    }
    
   
    
public void sendStartUpMessageByte(final byte[] message){
    	
    	
    	
       
        
        
        
        Thread th=new Thread(new Runnable() {
			public void run() {
				
				
				 if (outout != null) {
			        	try {
							outout.write(message);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			            
			        }
			        
			        
			        try {
						outout.flush();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
			}
		});
    	
    	th.start();
        
        
        
    }



		

    public void stopClient(){
        mRun = false;
        if(socket!=null)
        {
        	try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    public boolean isClientRunning(){
        return mRun;
    }

    public void run() {

        mRun = true;
        
        isSocketEventHandeled=false;

        try {
            //here you must put your computer's IP address.
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);

            Log.e("TCP Client", "C: Connecting...");

            //create a socket to make the connection with the server
           
            try
            {
             //socket = new Socket(serverAddr, SERVERPORT);
             
             socket = new Socket();
             
             socket.setTcpNoDelay(true);
             
             socket.setSoTimeout(20000);
             
             socket.connect(new InetSocketAddress(serverAddr, SERVERPORT), 20000);
             
             
             socket.setTcpNoDelay(true);
           // socket.setKeepAlive(true);
             
            
            
            }catch(Exception ex)
            {
            	if ( mMessageListener != null) {
                    
                     mMessageListener.onsocketConnectionFailer(serverMessage);
                    
                     isSocketEventHandeled=true;
                }
            }
            
            
            

            try {

                //send the message to the server
                //out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                
                outout=new DataOutputStream(socket.getOutputStream());

                Log.e("TCP Client", "C: Sent.");

                Log.e("TCP Client", "C: Done.");

                //receive the message which the server sends back
                // in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF8"));
                //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                inin = new DataInputStream(socket.getInputStream());
                
                
                
                if ( mMessageListener != null && socket.isConnected() && !isSocketEventHandeled) {
                    
                	
                	if(localSocketConnection!=null)
                	{
                		localSocketConnection.onSocketConnection();
                		
                		localSocketConnection=null;
                	}
                	else
                	{
                      mMessageListener.onSocketConnection(serverMessage);
                	}
                }
                else
                {
                	
                	
                	
                	if ( mMessageListener != null && ! isSocketEventHandeled) {
                        
                        mMessageListener.onsocketConnectionFailer(serverMessage);
                        
                        isSocketEventHandeled=true;
                    }
                }
                
                
                
                //in this while the client listens for the messages sent by the server
                while (mRun) {
                    //int x = in.read();
                    //serverMessage=""+x;
                   //serverMessage = in.readLine();
                   
                   while(!end)
                   {
                       bytesRead = inin.read(messageByte);
                       serverMessage += new String(messageByte, 0, bytesRead);
                       if (serverMessage.length() >= 10)
                       {
                           end = true;
                       }
                   }

                    if (serverMessage != null && mMessageListener != null) {
                        //call the method messageReceived from MyActivity class
                    	serverMessage=serverMessage.replace("null", "");
                    	
                    	serverMessage=bytesToHex(messageByte);
                    	
                    	
                    	
                    	if(SwitchCommandManager.getCommandType(serverMessage).equals(SwitchCommandManager.READ_TIME))
                    	{
                    		try
                    		{
                    		stopTimmer();
                    		
                    		getCommandListner.onGetCommandFromServer(serverMessage);
                    		}catch(Exception ex)
                    		{
                    			
                    		}
                    		
                    		
                    	}
                    	else 
                    	{
                    		stopTimmer();
                           mMessageListener.messageReceived(serverMessage);
                    	}
                    	
                    	
                    	
                    	
                    	
                    	
                    	
                    	isResponseArrived=true;
                    }
                    serverMessage = null;
                    end=false;
                }
                
                
               

                
                Log.e("RESPONSE FROM SERVER", "S: Received Message: '" + serverMessage + "'");

            } catch (Exception e) {

                Log.e("TCP", "S: Error", e);
                
                
                stopTimmer();
                
                if ( mMessageListener != null && !socket.isClosed() && !isSocketEventHandeled) {
               	     socket.close();
               	  isSocketEventHandeled=true;
                   mMessageListener.onsocketConnectionClosed(serverMessage);
               }

            } finally {
                //the socket must be closed. It is not possible to reconnect to this socket
                // after it is closed, which means a new socket instance has to be created.
            	mRun=false;
            	
            	
            	 stopTimmer(); 
                
                
                	
                try
                {
                
                	if ( mMessageListener != null && !isSocketEventHandeled) {
                		isSocketEventHandeled=true;
                		 socket.close();
                        mMessageListener.onSocketSelfClose(serverMessage);
                        
                    }
               
                }catch(Exception ex)
                {
                	
                }
                
               
                
            }

        } catch (Exception e) {

            Log.e("TCP", "C: Error", e);
            
            if ( mMessageListener != null && !isSocketEventHandeled) {
            	isSocketEventHandeled=true;
                mMessageListener.onsocketConnectionFailer(serverMessage);
            }

        }

    }

    public static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
    //Declare the interface. The method messageReceived(String message) will must be implemented in the MyActivity
    //class at on asynckTask doInBackground
    public interface OnMessageReceived {
    	 public void onSocketSelfClose(String message);
    	 public void onSocketConnection(String message);
        public void messageReceived(String message);
        public void onsocketConnectionClosed(String message);
        public void onsocketConnectionFailer(String message);
    }
    
    
    public void setCallbackForGetCommand( ServerMessageResopnse callbackLintner,byte[] sentCMD)
    {
    	
    	isResponseArrived=false;
    	
    	getCommandListner=null;
    	
    	
    	  
    	runTimmer(sentCMD);
	       
    	
    	getCommandListner=callbackLintner;
    	
    }
    
    
    public void setCallbackForSocketConnection( LocalSocketConnection localSocketConnection)
    {
    	
    	this.localSocketConnection=localSocketConnection;
    	
    }
    
    
    
    
    
    public void sendMessageByte(byte[] message){
    	
    	isResponseArrived=false;
    	
    	getCommandListner=null;
    	
    	
    	runTimmer(message);
        
    }
    
    
    public interface ServerMessageResopnse
	{
		
		 public void onGetCommandFromServer(String cmd);
		
	}
    
    
    public interface LocalSocketConnection
	{
		
		 public void onSocketConnection();
		
	}
    
    
    public void runTimmer(final byte[] sentCMD)
    {
    	((Activity) context).runOnUiThread( new Runnable() {
			public void run() {
				
				
				
				isCountdownTimerCanceled=false;		
			
				
				
			
			
    	try
    	{
    	//4800
    	countdowntimer = new CountDownTimer(20000,4800){
    		
    		
    		int tick=0;
           
			public void onTick(long millisUntilFinished){
                
				
				
				
                if(isCountdownTimerCanceled)
                {
                   
                   
                }
                else 
                {
                	
                	
                	Thread th=new Thread(new Runnable() {
						public void run() {
							
							

							if (outout != null) {
		        	        	try {
		        					outout.write(sentCMD);
		        					
		        					
		        					
		        				} catch (IOException e1) {
		        					// TODO Auto-generated catch block
		        					e1.printStackTrace();
		        				}
		        	            
		        	        }
		        	        
		        	       try {
		        				outout.flush();
		        			} catch (Exception e) {
		        				// TODO Auto-generated catch block
		        				e.printStackTrace();
		        			}
							
							
							
						}
					});
                	
                	th.start();
                }
                
                //Toast.makeText(context, "Current Call" +millisUntilFinished, Toast.LENGTH_SHORT).show();
                
                
                tick++;
                
            }
			
			
            public void onFinish(){
                
            	
            	if(!isCountdownTimerCanceled)
            	{
            	
	            	if ( mMessageListener != null && ! isSocketEventHandeled) 
	            	{
	            		isSocketEventHandeled=true;
	                    mMessageListener.onsocketConnectionFailer("Failed to connect.....");
	                    
	                }
            	}
            	
            	
            }
        }.start();
        
        
       
        
        
        
        
        
    	}catch(Exception ex)
    	{
    		ex.toString();
    	}
    	
    	
			}
			
    	});
    	
    }
    
    
   
    
    public void stopTimmer()
    {
    	
    	countdowntimer.cancel();
    	isCountdownTimerCanceled=true;
    	
    }
    
    
    
}
