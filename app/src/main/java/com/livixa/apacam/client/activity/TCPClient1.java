package com.livixa.apacam.client.activity;

import android.util.Log;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient1 {

    private String serverMessage="";
    public static  String SERVERIP = "192.168.15.38"; //your computer IP address
    public static final int SERVERPORT = 55555;
    private OnMessageReceived mMessageListener = null;
    private boolean mRun = false;

    PrintWriter out;
    BufferedReader in;
    DataInputStream inin;
    
    DataOutputStream outout;
    
    
    byte[] messageByte = new byte[1000];
	private boolean end=false;
	private int bytesRead;

    /**
     *  Constructor of the class. OnMessagedReceived listens for the messages received from server
     */
    public TCPClient1(OnMessageReceived listener) {
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
        if (outout != null) {
        	try {
				outout.write(message);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            try {
				outout.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    public void stopClient(){
        mRun = false;
    }

    public void run() {

        mRun = true;

        try {
            //here you must put your computer's IP address.
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);

            Log.e("TCP Client", "C: Connecting...");

            //create a socket to make the connection with the server
            Socket socket = new Socket(serverAddr, SERVERPORT);

            try {

                //send the message to the server
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                
                //outout=new DataOutputStream(socket.getOutputStream());

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
                        mMessageListener.messageReceived(serverMessage);
                    }
                    serverMessage = null;
                    end=false;
                }
                
                
               

                
                Log.e("RESPONSE FROM SERVER", "S: Received Message: '" + serverMessage + "'");

            } catch (Exception e) {

                Log.e("TCP", "S: Error", e);

            } finally {
                //the socket must be closed. It is not possible to reconnect to this socket
                // after it is closed, which means a new socket instance has to be created.
                socket.close();
            }

        } catch (Exception e) {

            Log.e("TCP", "C: Error", e);

        }

    }

    //Declare the interface. The method messageReceived(String message) will must be implemented in the MyActivity
    //class at on asynckTask doInBackground
    public interface OnMessageReceived {
        public void messageReceived(String message);
    }
}
