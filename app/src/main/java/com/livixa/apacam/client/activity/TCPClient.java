package com.livixa.apacam.client.activity;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class TCPClient {

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
	
	
	ManageSwitchActivity  currentActivity;
	
	MoodsRoomWithSwitchesActivity  simpleActivity;
	
	
	boolean isSocketEventHandeled=false;
	
	
	boolean isResponseArrived=false;
	
	
	String  currentSwitchCommand="abc";
	
	String  preSwitchCommand="abc";
	

    /**
     *  Constructor of the class. OnMessagedReceived listens for the messages received from server
     */
	
	
	 Socket socket=null;
	 
	 
	 MyTimerTask myTimerTask=null;
	 
	 Timer timer;
	 
	 
	 class MyTimerTask extends TimerTask {
		 
		 
		 MyTimerTask( byte[] message)
		 {
			 this.message=message;
		 }

		 byte[] message=null;
		 
		 int count=0;
		 int maxCount=3;
		 
		  @Override
		  public void run() {
		   
			  
			  isResponseArrived=false;
			  
			  if(count >=maxCount )
			  {
				  myTimerTask.cancel();
				  
				  timer.cancel();
				  
				  timer=null;
				  
				  //myTimerTask=null;
				  
				  if ( mMessageListener != null && ! isSocketEventHandeled) {
	                    
	                    mMessageListener.onsocketConnectionFailer("Failed to connect.....");
	                    
	                    isSocketEventHandeled=true;
	                }
			  }
			  
			  count++;
			  
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
		  
		 }
	
    public TCPClient(OnMessageReceived listener) {
        mMessageListener = listener;
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
    
    public void sendMessageByte(byte[] message){
    	
    	isResponseArrived=false;
    	
        /*if (outout != null) {
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
		}*/
        
       if(timer==null)
        {
        	
        	timer = new Timer();
	        myTimerTask=new MyTimerTask(message);
	        timer.schedule(myTimerTask, 100, 5000);
        }
        
        
        
    }
    
    
public void sendStartUpMessageByte(byte[] message){
    	
    	
    	
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



		public String getCurrentCommand(byte[] message){
			
			currentSwitchCommand=null;
			isResponseArrived=false;
		    
		    
		    if(timer==null)
	        {
	        	timer = new Timer();
		        myTimerTask=new MyTimerTask(message);
		        timer.schedule(myTimerTask, 100, 5000);
	        }
		    
		    
		    while(currentSwitchCommand==null)
		    {
		    	
		    	if(!mRun)
		    	{
		    		
		    		currentSwitchCommand=preSwitchCommand;
		    		
		    		break;
		    		
		    		
		    	}
		    	
		    }
		    
		    
		    return currentSwitchCommand;
		    
		    
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
             
             socket.connect(new InetSocketAddress(serverAddr, SERVERPORT), 30000);
             
            socket.setKeepAlive(true);
             
            // socket.setSoTimeout(30000);
            
            }catch(Exception ex)
            {
            	if ( mMessageListener != null) {
                    
                    mMessageListener.onsocketConnectionFailer(serverMessage);
                    
                    isSocketEventHandeled=true;
                }
            }
            
            if ( mMessageListener != null && socket.isConnected() && !isSocketEventHandeled) {
                
                mMessageListener.onSocketConnection(serverMessage);
            }
            else
            {
            	if ( mMessageListener != null && ! isSocketEventHandeled) {
                    
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
                
                //in this while the client listens for the messages sent by the server
                while (mRun) {
                    //int x = in.read();
                    //serverMessage=""+x;
                   //serverMessage = in.readLine();
                   
                   while(!end)
                   {
                       bytesRead = inin.read(messageByte);
                       serverMessage += new String(messageByte, 0, bytesRead);
                       if (serverMessage.length() >= 19)
                       {
                           end = true;
                       }
                   }

                    if (serverMessage != null && mMessageListener != null) {
                        //call the method messageReceived from MyActivity class
                    	serverMessage=serverMessage.replace("null", "");
                    	
                    	serverMessage=bytesToHex(messageByte);
                    	
                    	if(currentSwitchCommand==null)
                    	{
                    		currentSwitchCommand=serverMessage;
                    	}
                    	else
                        mMessageListener.messageReceived(serverMessage);
                    	
                    	
                    	preSwitchCommand=serverMessage;
                    	
                    	if(myTimerTask!=null)
                    	{
                    		myTimerTask.cancel();
                    		timer=null;
                    	}
                    	
                    	
                    	isResponseArrived=true;
                    }
                    serverMessage = null;
                    end=false;
                }
                
                
               

                
                Log.e("RESPONSE FROM SERVER", "S: Received Message: '" + serverMessage + "'");

            } catch (Exception e) {

                Log.e("TCP", "S: Error", e);
                
                if ( mMessageListener != null && !socket.isClosed()) {
               	     socket.close();
               	  isSocketEventHandeled=true;
                   mMessageListener.onsocketConnectionClosed(serverMessage);
               }

            } finally {
                //the socket must be closed. It is not possible to reconnect to this socket
                // after it is closed, which means a new socket instance has to be created.
            	mRun=false;
            	
            	
                
                
                
                	
                try
                {
                
                	if ( mMessageListener != null && !isSocketEventHandeled) {
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
}
