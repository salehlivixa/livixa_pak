package info.lamatricexiste.network;

public  class LocalDeviceInfo
{
	 String iP="";
	 String mac="";
	 
	 LocalDeviceInfo(String ip,String mac)
	 {
		 this.iP=ip;
		 this.mac=mac;
	 }
	 
	    public String getiP() {
			return iP;
		}

		public void setiP(String iP) {
			this.iP = iP;
		}

		public String getMac() {
			return mac;
		}

		public void setMac(String mac) {
			this.mac = mac;
		}
	 
}

