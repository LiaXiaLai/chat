package com.jt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encoder {
	
	//º”√‹π§æﬂ
	public static String encode(String password){
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			byte[] bs = digest.digest(password.getBytes());
			
			bs = Base64.getEncoder().encode(bs);
			password = new String(bs);
			return password;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
