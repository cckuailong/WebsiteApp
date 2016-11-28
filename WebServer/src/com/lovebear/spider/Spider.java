package com.lovebear.spider;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class Spider {

	private static HttpClient httpClient = new HttpClient();
	 
	 public static boolean downloadPage(String path,String filename) throws Exception {
	  InputStream input = null;
	  OutputStream output = null;
	  GetMethod getMethod = new GetMethod(path);
	  int statusCode = httpClient.executeMethod(getMethod);
	  
	  if (statusCode == HttpStatus.SC_OK) {
	   input = getMethod.getResponseBodyAsStream();
	   output = new FileOutputStream(filename);
	   int tempByte = -1;
	   while ((tempByte = input.read()) > 0) {
	    output.write(tempByte);
	   }
	   if (input != null) {
	    input.close();
	   }
	   if (output != null) {
	    output.close();
	   }
	   return true;
	  }
	  return false;
	 }
}
