package com.livixa.apacam.client.activity;

import java.math.BigInteger;

public class MoodsCommandManager 
{
	
	
	public enum RequestTypes {
		     READ("03")  , READ_RESPONSE("04"), WRITE("01"),WRITE_RESPONSE("02");
		
		 private String value;

		 RequestTypes(final String value) {
		        this.value = value;
		    }

		    public String getValue() {
		        return value;
		    }

		    @Override
		    public String toString() {
		        return this.getValue();
		    }
	};
	
	private static String _Header="CC";
	private static String _Footer="0D0A";
	private static String _DoNotCare="00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
	

	public static byte []  requestReadCommand(String Mac,String type,String buttonType)
	{
		
		
		
		String cmd=_Header + Mac + RequestTypes.READ.value  + type + buttonType + _DoNotCare  + _Footer;
		
		return hexStringToByteArray(cmd);
	}
	
	
	public static String  getTestWriteCommand(String Mac,String type,String buttonType)
	{
		
		
		
		String cmd=_Header + Mac + RequestTypes.WRITE.value  + type + buttonType + _DoNotCare  + _Footer;
		
		return cmd;
	}
	
	public static byte []  writeCommand(String cmd)
	{
		
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

	
	/*
	ccc55643040200000000
	00000000000000000000
	00000000000000000000
	0000d340100000000000
	00000000000000000000
	00000000000000000000
	00000000000000000000
	00000000000000000000
	00000000000000000000
	00000000000000000000
	00000000000000000000
	00009d0d0a
    */
}
