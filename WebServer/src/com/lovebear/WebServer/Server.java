package com.lovebear.WebServer;

import com.opensymphony.xwork2.ActionSupport;

public class Server extends ActionSupport{
	public String Server(){
		System.out.println("Xzjzjzjjzjzjzjz");
		try {
			   //Spider.downloadPage("http://v.juhe.cn/toutiao/index?type=top&key=1ce4e176c63e93e0f32ba4b608f6b9b2");
			   data.EditJson();
		} catch (Exception e) {
			   e.printStackTrace();
		}
		return SUCCESS;
	}
}
