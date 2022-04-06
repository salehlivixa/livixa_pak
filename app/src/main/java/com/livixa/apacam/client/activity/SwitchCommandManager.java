package com.livixa.apacam.client.activity;

import java.math.BigInteger;

public class SwitchCommandManager 
{
	
	
	String deviceResponce="";
	
	
	public static String READ_COMMAND="04";
	
	
	public static String READ_TIME="0b";
	
	public static String WRITE_COMMAND="02";
	
	public enum SwitchCommands {
		
		
		What_You_Contain("BB45BB040301EEEEEEEEEEEEEEEEEEEEEE0D0A"), 
		Turn_On_Bulb_One("BB 45BB04 04 01 0A EEEE 010101 01 EEEEEEEE0D0A"), 
		Turn_On_Bulb_Two("BB45BB040301EEEEEEEEEEEEEEEEEEEEEE0D0A"),
		Turn_On_Bulb_Three("BB45BB040301EEEEEEEEEEEEEEEEEEEEEE0D0A"), 
		
		Turn_Off_Bulb_One("BB45BB040301EEEEEEEEEEEEEEEEEEEEEE0D0A"), 
		Turn_Off_Bulb_Two("BB45BB040301EEEEEEEEEEEEEEEEEEEEEE0D0A"),
		Turn_Off_Bulb_Three("BB45BB040301EEEEEEEEEEEEEEEEEEEEEE0D0A");
		
		 private String value;

		 SwitchCommands(final String value) 
		 {
		        this.value = value;
		 }

		    public String getValue()
		    {
		        return value;
		    }

		    @Override
		    public String toString()
		    {
		        return this.getValue();
		    }
	};
	
	public static String _DoNotCare="EE";
	public static String _Header="BB";
	public static String _Footer="BB";
	
	private String requestONOFFCommand(String Mac,String type,String switchDim1,String switchDim2,String switchDim3,String switchLEDSET1,String switchLEDSET2,String switchLEDSET3,String LEDSTATUS)
	{
			   // 2       8     10   12       14           16          18                20            22             24             26            28       30            32         34        36    38
		       // 2       6     2    2        2            2            2                2              2              2              2             2        2             2           2         2   2 
		return _Header + Mac +"03"+ type + switchDim1 + switchDim2 + switchDim3 + switchLEDSET1 + switchLEDSET2 + switchLEDSET3 +LEDSTATUS + _DoNotCare + _DoNotCare + _DoNotCare+_DoNotCare + "0D"+"0A";
	}
	
	
	public static byte []  requestInPutCommand(String Mac,String type)
	{
		
		
		
		String cmd=_Header + Mac +"03"+ type + _DoNotCare + _DoNotCare + _DoNotCare + _DoNotCare + _DoNotCare + _DoNotCare +_DoNotCare + _DoNotCare + _DoNotCare + _DoNotCare+_DoNotCare + "0D"+"0A";
		
		return hexStringToByteArray(cmd);
	}
	
	
	public static byte []  requestGetSwitchTimeCommand(String Mac,String type)
	{
		
		
		
		String cmd=_Header + Mac +"0A"+ type + _DoNotCare + _DoNotCare + _DoNotCare + _DoNotCare + _DoNotCare + _DoNotCare +_DoNotCare + _DoNotCare + _DoNotCare + _DoNotCare+_DoNotCare + "0D"+"0A";
		
		return hexStringToByteArray(cmd);
	}
	
	
	
	public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len/2];

        for(int i = 0; i < len; i+=2){
            data[i/2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }

        return data;
    }

	final protected static char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	public static String byteArrayToHexString(byte[] bytes) {
    char[] hexChars = new char[bytes.length*2];
    int v;

    for(int j=0; j < bytes.length; j++) {
        v = bytes[j] & 0xFF;
        hexChars[j*2] = hexArray[v>>>4];
        hexChars[j*2 + 1] = hexArray[v & 0x0F];
    }

    return new String(hexChars);
}
	
	
	public static byte[] turnOnOFFButtonOne(String currentCMD,String ledIsOn,String value)
	{
		
		
		
		currentCMD=currentCMD.substring(0, 8) +"01" +currentCMD.substring(10, 12) + value + currentCMD.substring(14, 38); //+ ledIsOn + currentCMD.substring(26, 38);
		
		return hexStringToByteArray(currentCMD);
	}
	
	
	public static byte[] turnOnOFFSocket(String currentCMD,String ledIsOn,String value)
	{
		
	
		currentCMD=currentCMD.substring(0, 8) +"01" +currentCMD.substring(10, 12) + value + currentCMD.substring(14, 38); //+ ledIsOn + currentCMD.substring(26, 38);
		
		return hexStringToByteArray(currentCMD);
	}
	
	
	public static byte[] turnOnOFFAC(String currentCMD,String ledIsOn,String value)
	{
		
		
		
		currentCMD=currentCMD.substring(0, 8) +"01" +currentCMD.substring(10, 12) + value + currentCMD.substring(14, 38); //+ ledIsOn + currentCMD.substring(26, 38);
		
		return hexStringToByteArray(currentCMD);
	}
	
	
	public String replace(String str, int index, char replace){     
	    if(str==null){
	        return str;
	    }else if(index<0 || index>=str.length()){
	        return str;
	    }
	    char[] chars = str.toCharArray();
	    chars[index] = replace;
	    return String.valueOf(chars);       
	}
	
	public static String changeCharInPosition(int position, char ch, String str){
	    char[] charArray = str.toCharArray();
	    charArray[position] = ch;
	    return new String(charArray);
	}
	
	
	public static byte[] handleDimmerButtonOne(String currentCMD,String ledIsOn,String value)
	{
		
		
		
		
		
		currentCMD=currentCMD.substring(0, 8) +"01" + currentCMD.substring(10, 12) + value + currentCMD.substring(14, 38);// + ledIsOn+currentCMD.substring(26, 38);
		
		
		
		return hexStringToByteArray(currentCMD);
	}
	
	
	public static byte[] handleDimmerButtonTwo(String currentCMD,String ledIsOn,String value)
	{
		
		
		
		currentCMD=currentCMD.substring(0, 8) +"01" +currentCMD.substring(10, 14) + value + currentCMD.substring(16, 38);// + ledIsOn + currentCMD.substring(26, 38);
		
		
		return hexStringToByteArray(currentCMD);
	}
	
	
	public static byte[] handleDimmerButtonThree(String currentCMD,String ledIsOn,String value)
	{
		
		currentCMD=currentCMD.substring(0, 8) +"01" +currentCMD.substring(10, 16) + value + currentCMD.substring(18, 38);// + ledIsOn + currentCMD.substring(26, 38);
	
		return hexStringToByteArray(currentCMD);
	}
	
	public static byte[] turnOnOFFButtonTwo(String currentCMD,String ledIsOn,String value)
	{
		
		currentCMD=currentCMD.substring(0, 8) +"01" +currentCMD.substring(10, 14) + value + currentCMD.substring(16, 38);// + ledIsOn + currentCMD.substring(26, 38);
		
	
		return hexStringToByteArray(currentCMD);
	}

	
	public static  byte[]  turnOnOFFButtonThree(String currentCMD,String ledIsOn,String value)
	{
		currentCMD=currentCMD.substring(0, 8) +"01" +currentCMD.substring(10, 16) + value + currentCMD.substring(18, 38);// + ledIsOn + currentCMD.substring(26, 38);
		
		return hexStringToByteArray(currentCMD);
	}


	public static String getSwitchMac(String command)
	{
		String mac="";
		if(command!=null && command.length()>=19)
		{
			mac=command.substring(2,8);
		}
		
		return mac;
	}
	
	
	public static String getCommandType(String command)
	{
		String mac="";
		if(command!=null && command.length()>=19)
		{
			mac=command.substring(8,10);
		}
		
		return mac;
	}
	
	public static String getSwitchType(String command)
	{
		String type="";
		if(command!=null && command.length()>=19)
		{
			type=command.substring(10,12);
		}
		
		return type;
	}
	
	
	public static String getDimmerSettingValues(String command)
	{
		String dimmerSetting="";
		if(command!=null && command.length()>=19)
		{
			dimmerSetting=command.substring(18,24);
		}
		
		return dimmerSetting;
	}
	
	
	public static String getOnOffStatus(String command)
	{
		String status="";
		if(command!=null && command.length()>=19)
		{
			status=command.substring(12,18);
		}
		
		return status;
	}
	
	public static String getLEDStatus(String command)
	{
		String status="";
		if(command!=null && command.length()>=19)
		{
			status=command.substring(24,26);
		}
		
		return status;
	}
	
	
	
	

}
