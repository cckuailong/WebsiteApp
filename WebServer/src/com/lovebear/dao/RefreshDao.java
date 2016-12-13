package com.lovebear.dao;

import java.io.IOException;
import java.util.Date;

import javax.net.ssl.SSLEngineResult.Status;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class RefreshDao{

	
	public void work() {
		int statusCode = 0;
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod("http://101.200.61.191/app_server/refreshData?str=1");
		try {				
			statusCode = httpClient.executeMethod(getMethod);
			if(statusCode==HttpStatus.SC_OK){
				System.out.println("date: " + new Date().getTime());
			}
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
    }
}
