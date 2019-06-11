package com.jeesite.modules.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class SecurityMD {
	public static String md5(String message) {
	  String md5Str = null;
	  MessageDigest md;
	try {
		md = MessageDigest.getInstance("MD5");
		byte[] md5Bytes = md.digest(message.getBytes());
		md5Str = BytesToHex(md5Bytes);
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
		return md5Str.substring(8, 24).toUpperCase();	
	}

	public static String BytesToHex(byte[] bytes){
	    StringBuffer s = new StringBuffer();
	    for(int i=0;i<bytes.length;i++){
	      //如果数值小于16且大于0，如3，将其转换为03
	      if(bytes[i]>=0 && bytes[i] <= 15) { s.append("0");}
	      s.append(Integer.toHexString(bytes[i] & 0xFF));
	    }
	    return s.toString();
	  }
	public static void main(String[] args) {
		System.out.println(md5("111111HHS"));
		
		//Map<String,Long> m=new HashMap<String,Long>();
		//long l=m.get("abc");
		//System.out.println(l);
		
		
	}
}
