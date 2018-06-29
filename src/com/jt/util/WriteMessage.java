package com.jt.util;

import java.io.IOException;

import javax.servlet.ServletResponse;

import com.alibaba.fastjson.JSONObject;

public class WriteMessage {
	
	public static void write(ServletResponse resp,Object obj){
		
		try {
			byte[] bs = JSONObject.toJSONBytes(obj);
			resp.getOutputStream().write(bs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
