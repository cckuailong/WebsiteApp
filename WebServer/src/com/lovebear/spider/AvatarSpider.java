package com.lovebear.spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AvatarSpider {

	private String str;
	private ArrayList<String> avList=new ArrayList<String>();

	public ArrayList<String> getAvList() {
		return avList;
	}

	public void setAvList(ArrayList<String> avList) {
		this.avList = avList;
	}
	
	public void avatarDowload(){
		
		String[] pathList={"http://api.avatardata.cn/WorldNews/Query?key=c75c465249a34e29b8089b8f4c069cb8&page=1&rows=20",
				"http://api.avatardata.cn/SportsNews/Query?key=cdbda65ed4c94166abea91f12b18f03d&page=1&rows=20",
				"http://api.avatardata.cn/TechNews/Query?key=172af8cec6c84c4db28b1089d26572a2&page=1&rows=20"};
		String[] filenameLiString={"internation","sport","tech"};
		for(int i=0;i<pathList.length;i++){
			String path=pathList[i];
			String filename="F:\\myjavacode\\app_server\\data\\avatar\\"+filenameLiString[i]+".txt";
			try {
				Spider.downloadPage(path, filename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void readTxtFile(String filePath){
	    try {
	            String encoding="UTF-8";
	            str="";
	            File file=new File(filePath);
	            if(file.isFile() && file.exists()){
	                InputStreamReader read = new InputStreamReader(
	                new FileInputStream(file),encoding);
	                BufferedReader bufferedReader = new BufferedReader(read);
	                String lineTxt = null;
	                while((lineTxt = bufferedReader.readLine()) != null){
	                    str+=lineTxt;
	                }
	                read.close();
		        }else{
		            System.out.println("file read error");
		        }
	    } catch (Exception e) {
	        System.out.println("content error");
	        e.printStackTrace();
	    }
	 
	}
	
	public void getAllJson(){
		String[] filenameLiString={"internation","sport","tech"};
		String filename;
		
		avatarDowload();
		
		for(int i=0;i<filenameLiString.length;i++){
			filename="F:\\myjavacode\\app_server\\data\\avatar\\"+filenameLiString[i]+".txt";
			readTxtFile(filename);
			JSONObject  Json=JSON.parseObject(str);
			JSONArray  dataJson=JSONObject.parseArray(Json.get("result").toString());
			avList.add(dataJson.toString());
		}
	}
	
}
