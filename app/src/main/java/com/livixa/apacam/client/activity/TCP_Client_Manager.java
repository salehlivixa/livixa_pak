package com.livixa.apacam.client.activity;

import android.os.AsyncTask;


public class TCP_Client_Manager {

	
	static TCPClient  singleTcpClient;
	
	static connectTask task;
	
	
	
	public static TCPClient getClient()
	{
		
		
		if(task==null)
		{
		
			task=new connectTask();
			
			task.execute("");
		
		}
		
		while(singleTcpClient==null)
		{
			
		}
		
		return singleTcpClient;
		
	}
	
	
	public static void stop()
	{
		if(singleTcpClient!=null)
		{
			singleTcpClient.stopClient();
			singleTcpClient=null;
			task=null;
		}
	}
	
	
	public static class connectTask extends AsyncTask<String,String,TCPClient> {
		 
	       

		@Override
        protected TCPClient doInBackground(String... message) {
 
            //we create a TCPClient object and
			
			if(singleTcpClient==null)
			{
					singleTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
		                @Override
		               
		                public void messageReceived(final String message) {
		                   
		                	 publishProgress(message);
		                	 
		                	 
		                	
		                	
		                	
		                	
		                	
		                	
		                }

						@Override
						public void onsocketConnectionClosed(String message) {
							 
							
						}

						@Override
						public void onsocketConnectionFailer(String message) {
							 
							
						}

						@Override
						public void onSocketConnection(String message) {
							 
							
						}

						@Override
						public void onSocketSelfClose(String message) {
							 
							
						}
		            });
					singleTcpClient.run();
			}
 
            return null;
        }
		
	}
	
	
	
}
