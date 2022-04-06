package info.lamatricexiste.network;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import info.lamatricexiste.network.Network.NetInfo;
import com.livixa.apacam.client.activity.SwitchScanActivity;

public class Ip_Scanner_Local  extends AsyncTask<Void, Void, Boolean>{

	
	
	
	
	  private static String TAG="KISAFA";
	public String   s_dns1 ;
	   public String   s_dns2;     
	   public String   s_gateway;  
	   public String   s_ipAddress;    
	   public String   s_leaseDuration;    
	   public String   s_netmask;  
	   public String   s_serverAddress;
	   
	   DhcpInfo d;
	   WifiManager wifii;
	   
	   Context _context;
	   
	   ScanResultLocal  scanResult;
	   
	   private final static String MAC_RE = "^%s\\s+0x1\\s+0x2\\s+([:0-9a-fA-F]+)\\s+\\*\\s+\\w+$";
	   
	   private final static int BUF = 8 * 1024;
	   
	   boolean isCanceledByUser=false;
	   
	   private HashMap<String, Boolean>   scannedIps=new HashMap<>();
	   
	   private ExecutorService executor;
	   
	 public  Ip_Scanner_Local(Context context)
	   {

		 try
		 {
		 this._context=context;
		 
	        wifii = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
	        d = wifii.getDhcpInfo();

	        s_dns1 = "DNS 1: " + String.valueOf(d.dns1);
	        s_dns2 = "DNS 2: " + String.valueOf(d.dns2);
	        s_gateway = "Default Gateway: " + String.valueOf(d.gateway);
	        s_ipAddress = "IP Address: " + String.valueOf(d.ipAddress);
	        s_leaseDuration = "Lease Time: " + String.valueOf(d.leaseDuration);
	        s_netmask = "Subnet Mask: " + String.valueOf(d.netmask);
	        s_serverAddress = "Server IP: " + String.valueOf(d.serverAddress);
		 }catch(Exception ex)
		 {
			 ex.toString();
		 }
	        
	        isCanceledByUser=false;
	   }
	 
	 
	 private static String toInetAddress(int ipAddress)
     {
             long ip = (ipAddress < 0) ? (long)Math.pow(2,32)+ipAddress : ipAddress;
             InetAddress inetAddress = null;
             String addr =  String.valueOf((ip >> 24)+"."+((ip >> 16) & 255)+"."+((ip >> 8) & 255)+"."+(ip & 255));
             return addr;
     }
	 
	 public static String getIpFromIntSigned(int ip_int) {
	        String ip = "";
	        for (int k = 0; k < 4; k++) {
	            ip = ip + ((ip_int >> k * 8) & 0xFF) + ".";
	        }
	        return ip.substring(0, ip.length() - 1);
	    }

	 
	 
	

	 
	 public void getConectedDevices()
	 {
		
		 
		    executor = Executors.newFixedThreadPool(10);
		
		    InetAddress address;
	        InetAddress host;
	        try
	        {
	            host = InetAddress.getByName(getIpFromIntSigned(d.dns1));
	            
	            byte[] ip = host.getAddress();
	            
	           
	            //List<Future<Long>> list = new ArrayList<Future<Long>>();

	            for(int i = 1; i <= 254; i++)
	            {
	                ip[3] = (byte) i;
	                
	                 address = InetAddress.getByAddress(ip);
	                
	                 
	                 MyCallable myCallable = new MyCallable(address);
	                 
	                 Future<LocalDeviceInfo> submit = executor.submit(myCallable);
	                
						if(submit!=null )
						 {
						
							
						  
						 }
					

	             
		                
	                 
	                 
	                 
	            }
	        }
	        catch(Exception e1)
	        {
	            e1.printStackTrace();
	        }


		 executor.shutdown();
	        
	        
	        
	        while (!executor.isTerminated()) {
				
			}
	       
	        
	        
			 
			 
	        
	      
	 }


	
	
	
	
	
	public static String getMacFromArpCache(String ip) {
	    if (ip == null)
	        return null;
	    BufferedReader br = null;
	    try {
	        br = new BufferedReader(new FileReader("/proc/net/arp"));
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] splitted = line.split(" +");
	            if (splitted != null && splitted.length >= 4 && ip.equals(splitted[0])) {
	                // Basic sanity check
	                String mac = splitted[3];
	                if (mac.matches("..:..:..:..:..:..")) {
	                    return mac;
	                } else {
	                    return null;
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            br.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return null;
	}
	
	 public static String getHardwareAddress(String ip) {
	        String hw = NetInfo.NOMAC;
	        BufferedReader bufferedReader = null;
	        try {
	            if (ip != null) {
	                String ptrn = String.format(MAC_RE, ip.replace(".", "\\."));
	                Pattern pattern = Pattern.compile(ptrn);
	                bufferedReader = new BufferedReader(new FileReader("/proc/net/arp"), BUF);
	                String line;
	                Matcher matcher;
	                while ((line = bufferedReader.readLine()) != null) {
	                    matcher = pattern.matcher(line);
	                    if (matcher.matches()) {
	                        hw = matcher.group(1);
	                        break;
	                    }
	                }
	            } else {
	                Log.e(TAG, "ip is null");
	            }
	        } catch (IOException e) {
	            Log.e(TAG, "Can't open/read file ARP: " + e.getMessage());
	            return hw;
	        } finally {
	            try {
	                if(bufferedReader != null) {
	                    bufferedReader.close();
	                }
	            } catch (IOException e) {
	                Log.e(TAG, e.getMessage());
	            }
	        }
	        return hw;
	    }
	 
	 public interface ScanResultLocal{
		 
		 public void onResult(LocalDeviceInfo deviceInfo);
		 
		 public void onResultComplete();
	 }
	 
	 public void setScannResultListner( ScanResultLocal listner)
	 {
		 scanResult=listner;
	 }
	 
	 
	
	 
	 
	 public class MyCallable implements Callable<LocalDeviceInfo> {
		 
		 InetAddress address;
		 
		 public MyCallable(InetAddress address){
		        this.address=address;
		    }
		 
	        @Override
	        public LocalDeviceInfo call() throws Exception {
	                
	        	
	        	
	        	
	        	if(address.isReachable(NetworkInterface.getByIndex(0), 10, 400))//address.isReachable(400)
				{
				    
					LocalDeviceInfo obj=new LocalDeviceInfo(address.getHostAddress(), "");
					
					
				    if(obj!=null)
				    {
				    	try
				    	{
				       //String mac=getMacFromArpCache(obj.getiP());
				       
				       String mac =getHardwareAddress(obj.getiP());
				       
				       obj.setMac(mac);
				       
				       if(scanResult!=null && !isCanceledByUser)
						 {
				    	   
				    	   
				    	   if(!scannedIps.containsKey(mac))
				    	   {
				    	      scannedIps.put(mac, true);
				    	      scanResult.onResult(obj);
				    	   }
						 }
				       
				    	}catch(Exception ex){}
				    }
					
					
					return obj;
				}
	        	else if(!address.getHostAddress().equals(address.getHostName()))
                {
	        		LocalDeviceInfo obj=new LocalDeviceInfo(address.getHostAddress(), "");
					
					
				    if(obj!=null)
				    {
				    	try
				    	{
				       String mac=getMacFromArpCache(obj.getiP());
				       
				       obj.setMac(mac);
				       
				       if(scanResult!=null && !isCanceledByUser)
						 {
				    	   
				    	   
				    	   if(!scannedIps.containsKey(mac))
				    	   {
				    	      scannedIps.put(mac, true);
				    	      scanResult.onResult(obj);
				    	   }
						 }
				       
				    	}catch(Exception ex){}
				    }
					
					
					return obj;
                }
	        	
	        	
	        	return null;
	        	
	        }

	}
	 
	 public class WorkerThread implements Runnable {

		 InetAddress address;

		    public WorkerThread(InetAddress address){
		        this.address=address;
		    }

		    @Override
		    public void run() {
		    	try {
					if(address.isReachable(200))
					{
					    
						LocalDeviceInfo obj=new LocalDeviceInfo(address.getHostAddress(), "");
						
						
						
						
						
						//list.add(obj);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }

	 }
	 
	 
	 @Override
	protected void onPreExecute() {
		 
		super.onPreExecute();
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		getConectedDevices();
		return false;
	}
	 
	 
	 @Override
	protected void onPostExecute(Boolean result) {
		 
		super.onPostExecute(result);
		
		if(scanResult!=null)
		 {
			 scanResult.onResultComplete();
		 }
		
	}
	 
	 
	 public void  cancelThisTask()
	 {
		 isCanceledByUser=true;
		 try
		 {
			 executor.shutdownNow();
			 scannedIps.clear();
		 }catch(Exception ex){}
	 }
	 
	 
}
